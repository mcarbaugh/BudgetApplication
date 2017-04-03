
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="styles/main.css">  
        <link rel="stylesheet" type="text/css" href="styles/menuBar.css">
        <link rel="stylesheet" type="text/css" href="styles/budgetTable.css">
        <link rel="stylesheet" type="text/css" href="styles/userAccountSummary.css">
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Budget Application | Overview</title>
    </head>
    <body>
        <div class="header">
            <jsp:include page="/includes/menuBar.jsp"/>
        </div>
        <div class="wrapper">
            <jsp:include page="/includes/accountSummary.jsp"/>
            <br/>
            <jsp:include page="/includes/budgetTable.jsp"/>
        </div>
    </body>
</html>