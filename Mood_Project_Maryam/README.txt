-:-:-Code Review-:-:-

Most Critical Error:  
1. There were various configuration errors. One critical error is there was no MOODS table configuration at all. It was missed entirely. 

Server:
1. Local config: file - application.properties
There is a setting inside of application.properties that allows for local files like data.sql to be picked up as well as schema.sql that were programmed to populate the inserts but not included. A side note: Schema files should be separated from the data files as they don't live together. 

2. (TABLE names and details have been capitalised this standard when writing SQL)

3. CORS errors can be handled by adding:
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.config.Customizer;

@Bean
    public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    // Allow your frontend origin - match What ever your port is
    configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); 
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
    configuration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    s[[ource.registerCorsConfiguration("/**", configuration);
    return source;
}

4. Inside of the controller I have put:
@CrossOrigin(origins = "*") // This will fix localhost cors errors @maryam
I have also created another map to daily mood that will populate the last 7 days.

Client:
1. I created a matching daily mood scores within the client calling out to the backend. I corrected some CORS related errors for localhost connections. This is why I was asking if whether you wanted to host the DB or client side. If everything remained local you must run your client side on a localhost network but on a different port for eg. 3000. You can use python to host a local server by running the code: python3 -m http.server 3000

2. I have also included the fetch method inside of weeklyreview.js, have a look to see what you can understand from it. 

-:-:-Code Review Ends-:-:-

Notes:

This should put you in a good space to now continue plotting your data for Weekly Average as it's already displaying, and moving on to monthly averages.



===================================================================================

	code review 12 April

===================================================================================

<——::CODE REVIEW::——>

I have fixed the code and Sign in and login in now working. You must fix email validation. 
Please ask your teacher to cover these topics in more detail. You require more help in this area as there’s syntax errors more so on the frontend. Someone needs to sit down and explain these things. I can’t program for you because that means you won’t learn - or know the skill - that will become a serious issue in the future.
 BACKEND ISSUES:
1. Securityconfig file has two configs, and one has to be conflicting - it’s fixed now.
2. userDetailsServiceImpl file had hardcoded password123 template it’s not working it needs to compare to your password rather than template.
3. Password hash was not working so when using the above file to even compare to the bcrypt has it was struggling to find a match hence causing a 401 (unauthorised) login attempt.
4. You’ve just set up to cross-reference email only not username so stickwith email to login. This is now working.
5. There were security flaws that needed to be  .anyRequest().authenticated()
6. JWT Token was not being set on request leading to 401 too.


FRONTEND ISSUES:
1. Login success file (index.html) was not even present leading to a 404.
2. Email must be entered rather than username - that’s how backend reads it.
3. There are many data sets from your schema that were missed from the frontend: Pronouns, FirstName, LastName, StudentStatus, Age - I’ve added these.
4. <script> tags in newindex.html are in the wrong place and need to come after. The DOM has no way to read the contents of the elements until after they have been defined.
5. There was no <head> at start to wrap <title>.
6. FIREBASE is not used if we’re using H2
7. login.js is not used file saved with comment //blank (code removed - delete file)
8. validation.js file is not being used - commented out in newindex.html. (Use validation from it to get email working).

<——::CODE REVIEW::——>
