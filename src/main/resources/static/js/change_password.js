document.querySelector("changePassword").addEventListener("submit", evt => {
    const password1 = evt.target.elements.password1.value;
    const password2 = evt.target.elements.password2.value;
    let message = "Password validation is successful";
    if (password1 == password2) {
        const minLength = 6;
        if (password1.length >= minLength) {
            const regexPassword = /\d+/;
            if (!regexPassword.test(password1)) {
                message = "Error: password must contain at least on digit";
            }
        } else {
            message = "Error: password must be at least ${minLength} characters long";
        }
    } else {
        message = "Error: password don't match";
    }
    document.getElementById("passwordHelp").textContent = message;
    evt.preventDefault();
    }
)