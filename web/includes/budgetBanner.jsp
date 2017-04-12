
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<div class="budgetBannerContainer">
    <div id="monthDropdown" class="dropdown">
        <button onclick="getMonthContainer()" class="dropbtn">
            ${budget.year} ${budget.month}
        </button>
        
        <div id="monthContainer" class="dropdown-content">
            <c:forEach items="${budgets}" var="budget" varStatus="counter">
                <c:url value="BudgetSummary" var="readBudgetURL">
                    <c:param name="operation" value="read"/>
                    <c:param name="budgetId" value="${budget.id}"/>
                </c:url>
                <a href="${readBudgetURL}">
                    <c:out value="${budget.year} ${budget.month}"/>
                </a>
            </c:forEach>
            <button id="createBudgetButton" onclick="openAddBudgetDialog()">NEW BUDGET</button>
        </div>
    </div>
    
    <div id="userInfoDropDown" class="dropdown">
        <button onclick="getUserInfoContainer()" class="dropbtn">
            ${user.getUsername()}
        </button>
        <div id="userInfoContainer" class="dropdown-content">
            <a href="/#">PROFILE</a>
            <a href="/#">SETTINGS</a>
            <hr/>
            <c:url value="Login" var="logoutURL">
                <c:param name="operation" value="signout"/>
            </c:url>
            <a href="${logoutURL}">SIGN OUT</a>
        </div>
    </div>
</div>