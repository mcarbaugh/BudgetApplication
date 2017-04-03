
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="styles/main.css">
        <link rel="stylesheet" type="text/css" href="styles/loginForm.css">
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Budget Application</title>
    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="/includes/loginForm.jsp"/>
            <div class="errorNotification">
                <label>${message}</label>                
            </div>
        </div>
    </body>
</html>