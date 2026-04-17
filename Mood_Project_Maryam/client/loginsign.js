const BACKEND_URL = "http://localhost:8080";

document.addEventListener("DOMContentLoaded", function () {
    const mailformat=/^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    // ─────────────────────────────────────────────
    //  LOGIN
    //  Sends POST /login  →  reads JWT from response
    //  header  →  saves to localStorage
    // ─────────────────────────────────────────────
    document.getElementById("login-form").addEventListener("submit", async function (e) {
        e.preventDefault(); // prevent page refresh

        const email    = document.getElementById("login-email").value.trim();
        const password = document.getElementById("login-password").value.trim();
        const errorBox = document.getElementById("login-error");

        errorBox.textContent="";
        errorBox.style.display="none";

        if (!email || !password) {
            errorBox.textContent = "Please enter your email and password.";
            errorBox.style.display = "block";
            return;
        }
        if(!mailformat.test(email)){
            errorBox.textContent="Invalid email address";
            errorBox.style.display= "block";
            return;
        }

        try {
            const response = await fetch(`${BACKEND_URL}/login`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                // Your LoginFilter reads these fields
                body: JSON.stringify({ username: email, password: password })
            });
            

            if (response.ok) {
                // Your JWTService.java puts the token in the Authorization header
                let token = response.headers.get("Authorization");

                if (token) {
                    if(!token.startsWith("Bearer ")){
                        token = "Bearer " + token;
                    }
                    localStorage.setItem("jwt", token);
                    console.log("Login sucessful. ");
                    window.location.href = "dashboard.html";
                } else {
                    errorBox.textContent = "Login failed: no token received from server.";
                    errorBox.style.display = "block";
                }
            } else {
                errorBox.textContent = "Incorrect email or password.";
                errorBox.style.display = "block";
            }

        } catch (err) {
            console.error("Login error:", err);
            errorBox.textContent = "Cannot reach server. Make sure your Spring Boot app is running.";
            errorBox.style.display = "block";
        }
    });


    // ─────────────────────────────────────────────
    //  SIGN UP
    //  Sends POST /user  →  permitted without auth
    //  in your Secureconfig.java
    // ─────────────────────────────────────────────
    document.getElementById("signup-form").addEventListener("submit", async function (e) {
        e.preventDefault();

        const firstName       = document.getElementById("signup-firstname").value.trim();
        const lastName       = document.getElementById("signup-lastname").value.trim();
        const email      = document.getElementById("signup-email").value.trim();
        const username   = document.getElementById("signup-username").value.trim();
        const password   = document.getElementById("signup-password").value.trim();
        const agreed     = document.getElementById("signup-checkbox").checked;
        const studentStatus = document.getElementById("signup-studentstatus").value;
        const pronouns      = document.getElementById("signup-pronouns").value.trim();
        const age      = document.getElementById("signup-age").value.trim();

        const errorBox   = document.getElementById("signup-error");
        const successBox = document.getElementById("signup-success");

        errorBox.textContent = "";
        successBox.textContent = " ";
        errorBox.style.display="none";
        successBox.style.display = "none";

        // ── Frontend checks ──────────────────────
        if (!firstName || !lastName || !email || !username || !password) {
            errorBox.textContent = "Please fill in all fields.";
            errorBox.style.display = "block";
            return;
        }
        if (!mailformat.test(email)) {
            errorBox.textContent = "Invalid email address";
            errorBox.style.display = "block";
            return;
        }
        if (password.length < 8) {
            errorBox.textContent = "Password must be at least 8 characters.";
            errorBox.style.display = "block";
            return;
        }
        if(!agreed){
            errorBox.textContent="You must agree to the privacy policy";
            errorBox.style.display="block";
            return;
        }

        try {
            // POST /user — your Secureconfig allows this without a token
            const response = await fetch(`${BACKEND_URL}/user`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                
                body: JSON.stringify({
                     firstName: firstName,
                     lastName: lastName,
                     email: email,
                     userName: username,
                     passwordHash: password,
                     pronouns: pronouns,
                     age: age,
                     studentStatus: studentStatus,

                 })
            });

            if (response.ok) {
                successBox.textContent = "Account created! You can now log in.";
                successBox.style.display = "block";
                document.getElementById("signup-form").reset();
            } else {
                const msg = await response.text();
                errorBox.textContent = "Sign up failed: " + (msg || "server error " + response.status);
                errorBox.style.display = "block";
            }

        } catch (err) {
            console.error("Signup error:", err);
            errorBox.textContent = "Cannot reach server. Make sure your Spring Boot app is running.";
            errorBox.style.display = "block";
        }
    });

});


// ─────────────────────────────────────────────────────────────────────────────
//  authFetch()
//  Use this instead of fetch() everywhere else in your app (mood entries etc.)
//  It automatically attaches the saved JWT token to every request.
//
//  Example:
//    const data = await authFetch("http://localhost:8080/api/moods").then(r => r.json());
// ─────────────────────────────────────────────────────────────────────────────
    // If token exists and doesn't start with "Bearer ", add it
function authFetch(url, options = {}) {
    let token = localStorage.getItem("jwt");

    if (token && !token.startsWith("Bearer ")) {
        token = "Bearer " + token;
    }

    return fetch(url, {
        ...options,
        headers: {
            "Content-Type": "application/json",
            "Authorization": token || "",
            ...(options.headers || {})
        }
    });
}

