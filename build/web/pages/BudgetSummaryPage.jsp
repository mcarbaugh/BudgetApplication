
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Budget Summary</title>
        <link rel="stylesheet" href="<c:url value='/styles/index.css'/>" type="text/css">
        <link rel="stylesheet" href="<c:url value='/styles/budgetBanner.css'/>" type="text/css">
        <link rel="stylesheet" href="<c:url value='/styles/menuBar.css'/>" type="text/css">
        <link rel="stylesheet" href="<c:url value='/styles/links.css'/>" type="text/css">
        <link rel="stylesheet" href="<c:url value='/styles/table.css'/>" type="text/css">
        <link rel="stylesheet" href="<c:url value='/styles/categorySummaryPanel.css'/>" type="text/css">
        <link rel="stylesheet" href="<c:url value='/styles/dialogWindow.css'/>" type="text/css">
        <link rel="stylesheet" href="<c:url value='/styles/categoryTable.css'/>" type="text/css">
        <script src="<c:url value='/scripts/budgetBannerScript.js'/>"></script>
        <script src="<c:url value='/scripts/windowScript.js'/>"></script>
        <script src="<c:url value='/scripts/Models/Transaction.js'/>"></script>
        <script src="<c:url value='/scripts/Models/BudgetItem.js'/>"></script>
        <script src="<c:url value='/scripts/Models/BudgetSummaryModel.js'/>"></script>
        <script src="<c:url value='/scripts/ViewControllers/ButtonFactory.js'/>"></script>
        <script src="<c:url value='/scripts/ViewControllers/BudgetSummaryViewController.js'/>"></script>
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <jsp:include page="/includes/budgetBanner.jsp"/>
        <jsp:include page="/includes/dialogs/NewItemDialog.jsp"/>
        <jsp:include page="/includes/dialogs/EditItemDialog.jsp"/>
        <jsp:include page="/includes/dialogs/NewTransactionDialog.jsp"/>
        <div class="wrapper">
            <div class="columnContainer">
                <div class="leftColumn">
                    <jsp:include page="/includes/categorySummaryPanel.jsp"/>    
                </div>
                <div class="rightColumn">
                    <jsp:include page="/includes/categoryTable.jsp"/>     
                </div>
            </div>
        </div>
        <input value="${budget.id}" class="HiddenField" id="ActiveBudgetIdField"/>
        <script>
            var viewController = new BudgetSummaryViewController();
        </script>
    </body>
</html>