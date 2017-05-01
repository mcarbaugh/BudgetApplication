
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
        <link rel="stylesheet" href="<c:url value='/styles/dialogWindow.css'/>" type="text/css">
        <link rel="stylesheet" href="<c:url value='/styles/transactionDetails.css'/>" type="text/css">
        <link rel="stylesheet" href="<c:url value='/styles/transactionTable.css'/>" type="text/css">
        <link rel="stylesheet" href="<c:url value='/styles/categorySummaryPanel.css'/>" type="text/css">
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
        <jsp:include page="/includes/dialogs/EditTransactionDialog.jsp"/>
        <div class="budgetBannerContainer">
            <div id="BalanceDisplayContainer">
                <label id="LeftToBudget">Total Transactions</label>
                <div>
                    <span id="RemainingBalance">$0.00</span>
                </div>
            </div>
            <c:url value="/Budget" var="budgetURL"/>
            <form method="GET" Action="${budgetURL}" id="BackToBudgetForm">
                <input type="text" value="${budgetId}" name="budgetId" class="HiddenField"/>
                <input type="submit" value="Back to Budget" id="BackToBudgetButton"/>
            </form>
            <div id="userInfoDropDown" class="dropdown">
                <button class="dropbtn" id="UserDropDownBtn">
                    ${user.getUsername()}
                </button>
                <div id="userInfoContainer" class="dropdown-content">
                    <c:url value="/Profile" var="profileURL">
                    </c:url>
                    <a href="${profileURL}">PROFILE</a>
                    <form method="post" action="Logout">
                        <input type="submit" value="SIGN OUT" id="signoutButton"/>
                    </form>
                </div>
            </div>
        </div>
                    
        <div class="wrapper" id="Wrapper">
            <div class="columnContainer">
                <div class="leftColumn">
                    <div id="TransactionSummaryPanel">
                        <div id="TransactionChartContainer">
                            <h2>Transactions by Category</h2>
                            <canvas id="TransactionChart"></canvas>
                        </div>    
                    </div>
                </div>
                <div class="rightColumn">
                    <div class="transactionHistoryContainer">
                        <div class="transactionDetailsSection">
                            <div id="TransactionFilterOptions">
                                <select id="SortField">
                                    <option value="default" selected>Sort By:</option>
                                    <option value="category">Category</option>
                                    <option value="amount">Amount</option>
                                    <option value="date">Date</option>
                                </select>
                                <select id="SortDirection">
                                    <option value="ascending">Ascending</option>
                                    <option value="descending">Descending</option>
                                </select>
                                <input type="submit" value="Apply" id="ApplySortButton"/>
                            </div>
                            <div id="TransactionTableContainer">
                                <table id="TransactionTable">
                                    <thead>
                                        <tr class="headerRow">
                                            <th class="leftAlignColumn">Vendor</th>
                                            <th class="leftAlignColumn">Item</th>
                                            <th class="leftAlignColumn">Category</th>
                                            <th class="rightAlignColumn">Amount</th>
                                            <th>Date</th>
                                            <th>Action</th>
                                        </tr>  
                                    </thead>
                                    <tbody id="transactionTableBody"></tbody>
                                </table>    
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <input type="text" name="budgetId" class="HiddenField" value="${budgetId}" id="BudgetIdField"/>
        </div>                 
        <script>
            var viewController = new TransactionHistoryViewController();
        </script>
    </body>

</html>
