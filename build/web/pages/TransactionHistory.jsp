
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Transaction History</title>
        <link rel="stylesheet" href="<c:url value='/styles/index.css'/>" type="text/css">
        <link rel="stylesheet" href="<c:url value='/styles/budgetBanner.css'/>" type="text/css">
        <link rel="stylesheet" href="<c:url value='/styles/menuBar.css'/>" type="text/css">
        <link rel="stylesheet" href="<c:url value='/styles/links.css'/>" type="text/css">
        <link rel="stylesheet" href="<c:url value='/styles/table.css'/>" type="text/css">
        <link rel="stylesheet" href="<c:url value='/styles/categoryTable.css'/>" type="text/css">
        <link rel="stylesheet" href="<c:url value='/styles/categorySummaryPanel.css'/>" type="text/css">
        <link rel="stylesheet" href="<c:url value='/styles/transactionDetails.css'/>" type="text/css">
        <script src="<c:url value='/scripts/budgetBannerScript.js'/>"></script>
        <script src="<c:url value='/scripts/windowScript.js'/>"></script>
        <script src="<c:url value='/scripts/Models/TransactionHistoryModel.js'/>"></script>
        <script src="<c:url value='/scripts/Models/TransactionList.js'/>"></script>
        <script src="<c:url value='/scripts/Models/TransactionDetails.js'/>"></script>
        <script src="<c:url value='/scripts/ViewControllers/TransactionHistoryViewController.js'/>"></script>
        <script src="<c:url value='/scripts/ViewControllers/ButtonFactory.js'/>"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700" rel="stylesheet">
        <meta http-equi v="Content-Type" content="text/html; charset=UTF-8">
    </head>

    <body>
        <jsp:include page="/includes/budgetBanner.jsp"/>
        <jsp:include page="/includes/menuBar.jsp"/>
        <div class="wrapper">
            <div class="columnContainer">
                <div class="leftColumn">
                    <jsp:include page="/includes/categorySummaryPanel.jsp"/>    
                </div>
                <div class="rightColumn">
                    <div class="transactionHistoryContainer">
                        <div id="BudgetItemsButtonContainer">
                            <c:url value="/TransactionHistory" var="transactionURL"/>
                            <form method="GET" Action="${transactionURL}">
                                <input type="text" value="${user.getId()}" name="userId" class="HiddenField"/>
                                <input type="submit" value="Show Budget Items" id="TransactionsButton"/>
                            </form>
                        </div>
                        <jsp:include page="/includes/transactionHistoryDetails.jsp"/>
                        <jsp:include page="/includes/transactionHistoryGraphics.jsp"/>
                    </div>
                </div>
            </div>
            <input type="text" name="budgetId" class="HiddenField" value="${budgetId}" id="BudgetIdField"/>
        </div>        
        <script>
            (function() {
                var viewController = new TransactionHistoryViewController();
            })();
        </script>
    </body>

</html>
