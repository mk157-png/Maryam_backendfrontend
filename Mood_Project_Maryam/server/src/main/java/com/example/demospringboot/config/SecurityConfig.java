
package com.example.demospringboot.config;

// import org.springframework.context.annotation.Bean; CONFLICT
// import org.springframework.context.annotation.Configuration; CONFLICT
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

// NOTE: This configuration is disabled in favor of Secureconfig.java which provides JWT authentication
//@Configuration CONFLICT
public class SecurityConfig {

    //@Bean CONFLICT
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors(Customizer.withDefaults()) 
        
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/login", "/user", "/h2-console/**", "").permitAll()
                .requestMatchers("/api/**").permitAll()
                .anyRequest().authenticated()
        )
        .headers(headers -> headers.frameOptions(frame -> frame.disable()));

    return http.build();
}

}

