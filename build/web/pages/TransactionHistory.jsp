
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transaction History</title>
        <link rel="stylesheet" href="<c:url value='/styles/index.css'/>" type="text/css">
        <link rel="stylesheet" href="<c:url value='/styles/budgetBanner.css'/>" type="text/css">
        <link rel="stylesheet" href="<c:url value='/styles/menuBar.css'/>" type="text/css">
        <link rel="stylesheet" href="<c:url value='/styles/links.css'/>" type="text/css">
        <link rel="stylesheet" href="<c:url value='/styles/table.css'/>" type="text/css">
        <link rel="stylesheet" href="<c:url value='/styles/categorySummaryPanel.css'/>" type="text/css">
        <link rel="stylesheet" href="<c:url value='/styles/transactionDetails.css'/>" type="text/css">
        <script src="<c:url value='/scripts/budgetBannerScript.js'/>"></script>
        <script src="<c:url value='/scripts/windowScript.js'/>"></script>
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700" rel="stylesheet">
    </head>

    <body>
        <jsp:include page="/includes/budgetBanner.jsp"/>
        <jsp:include page="/includes/menuBar.jsp"/>
        <jsp:include page="/includes/dialogs/newBudgetDialog.jsp"/>
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
                                <input type="text" value="${user.getId()}" name="userId" class="hiddenField"/>
                                <input type="submit" value="Show Budget Items" id="TransactionsButton"/>
                            </form>
                        </div>
                        <jsp:include page="/includes/transactionHistoryDetails.jsp"/>
                        <jsp:include page="/includes/transactionHistoryGraphics.jsp"/>
                    </div>
                </div>
            </div>
        </div>        
    </body>

</html>
