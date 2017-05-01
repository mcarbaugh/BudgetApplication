
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Profile</title>
        <link rel="stylesheet" type="text/css" href="styles/index.css">
        <link rel="stylesheet" type="text/css" href="styles/profile.css">
        <link rel="stylesheet" type="text/css" href="styles/budgetBanner.css">
        <link rel="stylesheet" type="text/css" href="styles/menuBar.css">
        <link rel="stylesheet" type="text/css" href="styles/links.css">
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div class="budgetBannerContainer">
            <form post="GET" action="DefaultBudget" id="BackToBudgetsForm">
                <input type="submit" value="Back to Budget" id="BackToBudgetsButton"/>
            </form>
            <div id="userInfoDropDown" class="dropdown">
                <button class="dropbtn" id="UserDropDownBtn">
                    ${user.getUsername()}
                </button>
                <div id="userInfoContainer" class="dropdown-content">
                    <c:url value="/Profile" var="profileURL"></c:url>
                    <a href="${profileURL}">PROFILE</a>
                    <form method="post" action="Logout">
                        <input type="submit" value="SIGN OUT" id="signoutButton"/>
                    </form>
                </div>
            </div>
        </div>      
        <div class="wrapper">
            <div id="UserFormContainer">
                <h2>Profile Information:</h2>
                <form method="POST" action="UpdateProfile" id="profileInfoForm">
                    <label>
                        <span>Username:</span>
                        <input name="username" value="${user.username}" disabled id="usernameField"/>
                        <span id="usernameMessage" class="RedText"/>
                    </label>
                    <label>
                        <span>First Name:</span>
                        <input name="firstName" value="${user.firstName}" disabled id="firstNameField"/>
                        <span id="firstNameMessage" class="RedText"/>
                    </label>
                    <label>
                        <span>Last Name:</span>
                        <input name="lastName" value="${user.lastName}" disabled id="lastNameField"/>
                        <span id="lastNameMessage" class="RedText"/>
                    </label>
                    <label>
                        <span>Email (optional):</span>
                        <input name="email" value="${user.email}" disabled id="emailField"/>
                    </label>
                    <label>
                        <span>Phone (optional):</span>
                        <input name="phone" value="${user.phoneNumber}" disabled id="phoneNumberField"/>
                    </label>
                </form>
                <div id="UserButtonContainer">
                    <button id="editInfoBtn">Edit Information</button>
                    <button id="saveInfoBtn" class="HiddenField">Save Changes</button>
                    <button id="cancelBtn" class="HiddenField">Cancel</button>
                    <form action="DeleteProfile" method="post" id="deleteForm">
                        <input type="hidden" name="userId" value="<c:out value="${user.id}"/>">
                        <input type="button" value="Delete Account" class="RedButton" id="deleteAccountBtn">
                    </form>
                </div>
            </div>
        </div>
        <script>
            (function() {
                var username, firstName, lastName, email, phone;
                
                document.getElementById("editInfoBtn").addEventListener("click", function() {
                    var userNameField, firstNameField, lastNameField, emailField, phoneField;
        
                    document.getElementById("editInfoBtn").classList.add("HiddenField");
                    document.getElementById("saveInfoBtn").classList.remove("HiddenField");
                    document.getElementById("cancelBtn").classList.remove("HiddenField");
                    document.getElementById("deleteAccountBtn").classList.add("HiddenField");                 
                    
                    userNameField = document.getElementById("usernameField");
                    username = userNameField.value;
                    userNameField.disabled = false;
                    
                    firstNameField = document.getElementById("firstNameField");
                    firstName = firstNameField.value;
                    firstNameField.disabled = false;
                    
                    lastNameField = document.getElementById("lastNameField");
                    lastName = lastNameField.value;
                    lastNameField.disabled = false;
                    
                    
                    emailField = document.getElementById("emailField");
                    email = emailField.value;
                    emailField.disabled = false;
                    
                    phoneField = document.getElementById("phoneNumberField");
                    phone = phoneField.value;
                    phoneField.disabled = false;
                });
                
                document.getElementById("cancelBtn").addEventListener("click", function() {
                    document.getElementById("editInfoBtn").classList.remove("HiddenField");
                    document.getElementById("saveInfoBtn").classList.add("HiddenField");
                    document.getElementById("cancelBtn").classList.add("HiddenField");
                    document.getElementById("deleteAccountBtn").classList.remove("HiddenField");                 
                    
                    userNameField = document.getElementById("usernameField");
                    userNameField.value = username;
                    userNameField.disabled = true;
                    
                    firstNameField = document.getElementById("firstNameField");
                    firstNameField.value = firstName;
                    firstNameField.disabled = true;
                    
                    lastNameField = document.getElementById("lastNameField");
                    lastNameField.value = lastName;
                    lastNameField.disabled = true;
                    
                    
                    emailField = document.getElementById("emailField");
                    emailField.value = email;
                    emailField.disabled = true;
                    
                    phoneField = document.getElementById("phoneNumberField");
                    phoneField.value = phone;
                    phoneField.disabled = true;
                    
                    document.getElementById("usernameMessage").innerHTML = "";
                    document.getElementById("firstNameMessage").innerHTML = "";
                    document.getElementById("lastNameMessage").innerHTML = "";
                });
                
                document.getElementById("saveInfoBtn").addEventListener("click", function() {
                    var errorFound, usernameValue, firstNameValue, lastNameValue, usernameField, firstNameField, lastNameField;
                    
                    usernameField = document.getElementById("usernameField");
                    firstNameField = document.getElementById("firstNameField");
                    lastNameField = document.getElementById("lastNameField");
                    
                    errorFound = false;
                    usernameValue = usernameField.value.trim();
                    firstNameValue = firstNameField.value.trim();
                    lastNameValue = lastNameField.value.trim();
                    
                    if(usernameValue === "") {
                        errorFound = true;
                        usernameField.value = usernameValue;
                        document.getElementById("usernameMessage").innerHTML = "Enter a valid username.";
                    }
                    else {
                        document.getElementById("usernameMessage").innerHTML = "";
                    }
                    
                    if(firstNameValue === "") {
                        errorFound = true;
                        firstNameField.value = firstNameValue;
                        document.getElementById("firstNameMessage").innerHTML = "Enter your first name.";
                    }
                    else {
                        document.getElementById("firstNameMessage").innerHTML = "";
                    }
                    
                    if(lastNameValue === "") {
                        errorFound = true;
                        lastNameField.value = lastNameValue;
                        document.getElementById("lastNameMessage").innerHTML = "Enter your last name.";
                    }
                    else {
                        document.getElementById("lastNameMessage").innerHTML = "";
                    }
                    
                    if(!errorFound) {
                        document.getElementById("profileInfoForm").submit();
                    }
                });
                
                document.getElementById("deleteAccountBtn").addEventListener("click", function() {
                    var r = confirm("Your information will be permanently removed. Are you sure you want to delete your account?");
                    if(r === true){
                        document.getElementById('deleteForm').submit();
                    }
                });
                
                document.getElementById("UserDropDownBtn").addEventListener("click", function() {
                    
                    if(!document.getElementById("userInfoContainer").classList.contains("show")) {
                        document.getElementById("userInfoContainer").classList.toggle("show");
                    }
                    
                    
                });
                
                document.addEventListener("click", function(event) {
                    if (!event.target.matches('.dropbtn')) {
                        document.getElementById("userInfoContainer").classList.remove("show");
                    }
                });
            })();
        </script>
    </body>
</html>