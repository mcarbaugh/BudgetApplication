
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transaction History</title>
    </head>

    <body>
        <jsp:include page="/includes/budgetBanner.jsp"/>
        <jsp:include page="/includes/menuBar.jsp"/>
        <jsp:include page="/includes/dialogs/newBudgetDialog.jsp"/>
        <jsp:include page="/includes/dialogs/newItemDialog.jsp"/>
        <div class="wrapper">
            <div class="columnContainer">
                <div class="leftColumn">
                    <jsp:include page="/includes/categorySummaryPanel.jsp"/>    
                </div>
                <div class="rightColumn">
                    <jsp:include page="/includes/transactionHistoryDetails.jsp"/>
                    <jsp:include page="/includes/transactionHistoryGraphics.jsp"/>
                </div>
            </div>
        </div>        
    </body>

</html>
