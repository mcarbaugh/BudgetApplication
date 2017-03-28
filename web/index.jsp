<%-- 
    Document   : index
    Created on : Mar 24, 2017, 2:36:52 PM
    Author     : mcarb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/index.css">
        <link rel="stylesheet" type="text/css" href="styles/menuBar.css">
        <link rel="stylesheet" type="text/css" href="styles/loginForm.css">
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700" rel="stylesheet">
        <title>Budget Application</title>
    </head>
    <body>
        <div class="header">
            <jsp:include page="/includes/menuBar.jsp"/>
        </div>
        <div class="wrapper">
            <jsp:include page="/includes/loginForm.jsp"/>
        </div>
    </body>
</html>
