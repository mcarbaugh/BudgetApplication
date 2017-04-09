
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head> 
        <link rel="stylesheet" type="text/css" href="styles/index.css">        
        <link rel="stylesheet" type="text/css" href="styles/menuBar.css">
        <link rel="stylesheet" type="text/css" href="styles/form.css">
        <link rel="stylesheet" type="text/css" href="styles/links.css">
        <link rel="stylesheet" type="text/css" href="styles/table.css">
        <link rel="stylesheet" type="text/css" href="styles/popupWindow.css">       
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Summary</title>
    </head>
    <body>
        <script src="scripts/budgetTableScript.js"></script>
        <jsp:include page="/includes/newBudgetModal.jsp"/>
        <jsp:include page="/includes/menuBar.jsp"/>
        <div class="wrapper">
            <div class="columnContainer">
                <div class="leftColumn">
                    <jsp:include page="/includes/accountSummary.jsp"/>
                </div>
                <div class="rightColumn">
                    <jsp:include page="/includes/budgetTable.jsp"/>
                </div>
            </div>
        </div>
    </body>
</html>