
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<div class="budgetBannerContainer">
    <div class="dropdown">
        <button onclick="getMonthContainer()" class="dropbtn">
            ${budget.year} ${budget.month}
        </button>
        
        <div id="monthContainer" class="dropdown-content">
            <c:forEach items="${budgets}" var="budget" varStatus="counter">
                <a href="BudgetSummary?operation=read&budgetId=${budget.id}">
                    <c:out value="${budget.year} ${budget.month}"/>
                </a>
            </c:forEach>
                <a href="/#" id="newBudgetLink">CREATE BUDGET</a>
        </div>
    </div>
    
    <div id="userInfoDropDown" class="dropdown">
        <button onclick="getUserInfoContainer()" class="dropbtn">
            ${user.getUsername()}
        </button>
    
        <div id="userInfoContainer" class="dropdown-content">
            <a href="/#">SETTINGS</a>
            <hr/>
            <a href="Login?operation=signout">SIGN OUT</a>
        </div>
    </div>
</div>