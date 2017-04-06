
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="styles/main.css">  
        <link rel="stylesheet" type="text/css" href="styles/menuBar.css">
        <link rel="stylesheet" type="text/css" href="styles/itemTable.css">
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Budget Application | Budget Details</title>
    </head>
    <body>
        <div class="header">
            <jsp:include page="/includes/menuBar.jsp"/>
        </div>
        <div class="wrapper">
            <jsp:include page="/includes/itemTable.jsp"/>
        </div>        
    </body>
</html>
