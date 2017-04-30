<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<div class="loginFormContainer">
    <h1>Budget Application</h1>
    <form method="post" action="Login">
        <input type="text" placeholder="Username" name="username"/><br/>
        <input type="password" placeholder="Password" name="password"/><br/>
        <div class="errorNotification">
            <label>${message}&nbsp;</label>                
        </div>
        <input type="submit" value="Sign in"/>
        <input type="button" value="Create Account" id="CreateAccountButton"/><br/>
    </form>
    <script>
        (function() {
            document.getElementById("CreateAccountButton").addEventListener("click", function() {
               location.href="CreateAccount";
            });
        })();
    </script>
</div>
