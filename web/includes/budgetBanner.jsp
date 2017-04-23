
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
            <a href="/#">PROFILE</a>
            <a href="/#">SETTINGS</a>
            <form method="post" action="Logout">
                <input type="submit" value="SIGN OUT" id="signoutButton"/>
            </form>
        </div>
    </div>
</div>