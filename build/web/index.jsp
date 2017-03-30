<%-- 
    Document   : index
    Created on : Mar 24, 2017, 2:36:52 PM
    Author     : mcarb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="styles/main.css">
        <link rel="stylesheet" type="text/css" href="styles/loginForm.css">
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Budget Application</title>
        <script>
            
        </script>
    </head>
    <body>
        <div class="wrapper">
            <div class="errorNotification">
                <label>${message}</label>                
            </div>
            <jsp:include page="/includes/loginForm.jsp"/>
        </div>
    </body>
</html>
