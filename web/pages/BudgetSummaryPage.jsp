
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
        <script src="<c:url value='/scripts/Models/Income.js'/>"></script>
        <script src="<c:url value='/scripts/Models/ItemList.js'/>"></script>
        <script src="<c:url value='/scripts/Models/IncomeList.js'/>"></script>
        <script src="<c:url value='/scripts/Models/BudgetSummaryModel.js'/>"></script>
        <script src="<c:url value='/scripts/ViewControllers/ButtonFactory.js'/>"></script>
        <script src="<c:url value='/scripts/ViewControllers/BudgetSummaryViewController.js'/>"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <jsp:include page="/includes/dialogs/NewBudgetDialog.jsp"/>
        <jsp:include page="/includes/dialogs/NewIncomeDialog.jsp"/>
        <jsp:include page="/includes/dialogs/EditIncomeDialog.jsp"/>
        <jsp:include page="/includes/dialogs/NewItemDialog.jsp"/>
        <jsp:include page="/includes/dialogs/EditItemDialog.jsp"/>
        <jsp:include page="/includes/dialogs/NewTransactionDialog.jsp"/>
        <div class="budgetBannerContainer">
            <div id="monthDropdown" class="dropdown">
                <button onclick="getMonthContainer()" class="dropbtn">
                    ${budget.year} ${budget.month}
                </button>

                <div id="monthContainer" class="dropdown-content">
                    <c:forEach items="${budgets}" var="budget" varStatus="counter">
                        <c:url value="/Budget" var="readBudgetURL">
                            <c:param name="budgetId" value="${budget.id}"/>
                        </c:url>
                        <a href="${readBudgetURL}">
                            <c:out value="${budget.year} ${budget.month.name()}"/>
                        </a>
                    </c:forEach>
                    <button id="NewBudgetButton">NEW BUDGET</button>
                </div>
            </div>
            <div id="userInfoDropDown" class="dropdown">
                <button onclick="getUserInfoContainer()" class="dropbtn">
                    ${user.getUsername()}
                </button>
                <div id="userInfoContainer" class="dropdown-content">
                    <c:url value="/Profile" var="profileURL">
                    </c:url>
                    <a href="${profileURL}">PROFILE</a>
                    <a href="/#">SETTINGS</a>
                    <form method="post" action="Logout">
                        <input type="submit" value="SIGN OUT" id="signoutButton"/>
                    </form>
                </div>
            </div>
        </div>
        <div class="wrapper" id="Wrapper">
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