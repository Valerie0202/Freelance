function validateEmail() {

    var user = document.getElementById("email").value;
    var user2 = document.getElementById("email");
    var re = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    if (re.test(user)) {
        return true;
    }
    else {
        alert("Please enter a valid email");
        user2.style.border = "red solid 3px";
        return false;
    }
}

function validatePhone() {

    var phoneNumber = document.getElementById("phone").value;
    var phoneNumber2 = document.getElementById("phone");
    var phoneRegex = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;

    if (phoneRegex.test(phoneNumber)) {
        var formattedPhoneNumber =
            phoneNumber.replace(phoneRegex, "($1) $2-$3");
        document.getElementById("phone").value = formattedPhoneNumber;
        return true;
    } else {
        alert("Please enter a valid phone number");
        phoneNumber2.style.border = "red solid 3px";
        return false;
    }
}

function validate() {
    if(validateEmail() & validatePhone()) {
        return true;
    } else {
        return false;
    }
}