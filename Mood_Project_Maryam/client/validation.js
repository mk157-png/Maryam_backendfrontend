//Let's define some variables to represent the form data
//At the beginning, the form is empty, and therefore, not valid

//Data to send
let formData = {
    username: "",
    email: "",
    password: "",
    
}

//This data will be read but not be sent as part of the form submission
let formValid= false;

let tosCheckBox=false;


//Function to read the form
function readForm(){
   formData.username = document.getElementById("signup-username").value.trim();
   formData.email = document.getElementById("signup-email").value.trim();
   formData.password = document.getElementById("signup-password").value.trim();
   

    

    tosCheckBox = document.getElementById("signup-checkbox").checked;

}

//Function to validate the form
function validateForm() {
    formValid = false;
    let mailformat = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    document.getElementById("signup-error").innerText="";
    if (formData.email.length==0){
       document.getElementById("signup-error").innerText="Email is required";
    } else if (!mailformat.test(formData.email)){
       document.getElementById("signup-error").innerText="Invalid email address";
    } else if (formData.password.length < 8){
       document.getElementById("signup-error").innerText="Password must be at least 8 characters";

    } else if (!tosCheckBox){
       document.getElementById("signup-error").innerText ="Please accept terms and conditions";
    }else {
        formValid = true;
    }
}

//Function to write the Registration success on the page
function createNewParagraph(content){
    let text = document.createTextNode(content);
    let paragraph = document.createElement("p");
    paragraph.appendChild(text);
    paragraph.style.whiteSpace = "pre";
    paragraph.id = "hiddenParagraph";

    let element = document.getElementById("hiddenSection");
    let existingParagraph = document.getElementById("hiddenParagraph");
    if(existingParagraph){
        element.replaceChild(paragraph,existingParagraph);
    }
    else{
        element.appendChild(paragraph);
    }
}

document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("signup-form").addEventListener("submit", function(e){
        readForm();
        validateForm();
        if(!formValid){
            e.preventDefault();
        } else{
            let formText = formData.username + " registered sucessfully";
            console.log(formText);
            createNewParagraph(formText);
        }
    });
});