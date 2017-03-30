<%-- 
    Document   : accountOverview
    Created on : Mar 26, 2017, 7:28:01 PM
    Author     : mcarb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="styles/main.css">  
        <link rel="stylesheet" type="text/css" href="styles/menuBar.css">
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Budget Application | Overview</title>
    </head>
    <body>
        <div class="header">
            <jsp:include page="/includes/menuBar.jsp"/>
        </div>
        <div class="wrapper">
            <h1>Account</h1>
            username: ${user.userName}<br/>
            name: ${user.firstName}<br/>
            phone: ${user.phoneNumber} <br/>
        </div>
    </body>
</html>
