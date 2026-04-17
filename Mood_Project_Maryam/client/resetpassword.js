const BACKEND_URL="http://localhost:8080";
document.addEventListener("DOMContentLoaded", ()=> {
    document.getElementById("reset-password-form").addEventListener("submit", async function(e){
        e.preventDefault();
        const email = document.getElementById("email").value.trim();
        const newPassword = document.getElementById("newpass").value.trim();
        const confirmPassword = document.getElementById("confirmpass").value.trim();

        if(!email || !newPassword || !confirmPassword){
            alert("Please fill in all fields. ");
            return;
        }
        const mailformat = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if(!mailformat.test(email)){
            alert("Invalid email address");
            return;
        }
        if(newPassword !== confirmPassword){
            alert("Passwords do not match. ");
            return;
        }
        if(newPassword.length < 8){
            alert("Password must be at least 8 characters. ");
            return;
        }
        try{
            const response = await fetch(`${BACKEND_URL}/api/auth/reset-password`, {
                method: "POST",
                headers:  {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    email:email,
                    newPassword: newPassword
                })
            });
            if (response.ok){
          alert("Password reset sucessful.Please login in");
          window.location.href="newindex.html";
        }else{

            alert("Password reset failed.Please try again");
        }
        
            

        }
        
        catch(err){
            console.error("Reset error:", err);
            alert("Cannot reach server");
        }
    

    });
});