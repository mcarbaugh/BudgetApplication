
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>  
        <link rel="stylesheet" type="text/css" href="styles/menuBar.css">
        <link rel="stylesheet" type="text/css" href="styles/form.css">
        <link rel="stylesheet" type="text/css" href="styles/links.css">
        <link rel="stylesheet" type="text/css" href="styles/popupWindow.css">
        <link rel="stylesheet" type="text/css" href="styles/table.css">
        <link rel="stylesheet" type="text/css" href="styles/menuBar.css">
        <link rel="stylesheet" type="text/css" href="styles/index.css">
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700" rel="stylesheet">
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.0.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Budget Application | Account Summary</title>
    </head>
    <body>
        <script src="scripts/budgetTableScript.js"></script>
        <jsp:include page="/includes/newBudgetModal.jsp"/>
        <div class="wrapper">
            <jsp:include page="/includes/menuBar.jsp"/>
            
            <div class="columnContainer">
                <div class="leftColumn">
                    <jsp:include page="/includes/accountSummary.jsp"/>
                </div>
                <div class="rightColumn">
                    <div class="tableToolbar">
                        <button class="tableToolbarButton" onClick="openAddBudgetDialog()">New</button>
                        <div class="verticalRule"></div>
                        <button class="tableToolbarButton">Sort by:</button>
                    </div>
                    <jsp:include page="/includes/budgetTable.jsp"/>
                </div>
            </div>
        </div>
    </body>
</html>