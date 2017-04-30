
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="<c:url value='/styles/index.css'/>" type="text/css">
        <link rel="stylesheet" href="<c:url value='/styles/CreateAccount.css'/>" type="text/css">
        <link rel="stylesheet" href="<c:url value='/styles/budgetBanner.css'/>" type="text/css">
        <link rel="stylesheet" href="<c:url value='/styles/links.css'/>" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>
    <body>
        <div class="budgetBannerContainer">
            <form post="GET" action="Login" id="BackToLoginForm">
                <input type="submit" value="Back to Login" id="BackToLoginButton"/>
            </form>
        </div>
        <div class="wrapper">
            <div id="CreateAccountFormContainer">
                <h2>Create Account:</h2>
                <form id="CreateAccountForm" method="POST" action="CreateAccount">
                    <label>
                        <span>Username:</span>
                        <input type="text" name="username" value="${username}"/>
                        <span class="RedText">${username_message}</span>
                    </label>
                    <label>
                        <span>First Name:</span>
                        <input type="text" name="firstName" value="${firstName}"/>
                        <span class="RedText">${firstName_message}</span>
                    </label>
                    <label>
                        <span>Last Name:</span>
                        <input type="text" name="lastName" value="${lastName}"/>
                        <span class="RedText">${lastName_message}</span>
                    </label>
                    <label>
                        <span>Password:</span>
                        <input type="password" name="password"/>
                        <span class="RedText">${password_message}</span>
                    </label>
                    <label>
                        <span>Confirm Password:</span>
                        <input name="confirmPassword" type="password"/>
                        <span class="RedText"/>
                    </label>
                    <input type="submit" value="Submit" id="SubmitNewAccountButton"/>
                </form>
            </div>
        </div>
    </body>
</html>
