
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<div class="budgetTableContainer">
    <table>
        <thead>
            <tr>
                <th>Budget</th>
                <th class="yearColumn">Year</th>
                <th class="monthColumn">Month</th>
                <th class="currencyColumn">Spent</th>
                <th class="currencyColumn">Remaining</th>
                <th class="currencyColumn">Total</th>
                <th>Remove</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${budgets}" var="budget" varStatus="counter">
                <tr>
                    <td>
                        <span>
                            <a href="BudgetDetails?operation=read&budgetId=${budget.id}">
                                <c:out value="view"/>
                            </a>
                        </span>
                        &nbsp
                        <span>
                            <a href="#editBudget">
                                <c:out value="edit"/>
                            </a>
                        </span>
                    </td>
                    <td class="yearColumn"><c:out value="${budget.year}"/></td>
                    <td class="monthColumn"><c:out value="${budget.month.toString()}"/></td>
                    <td class="currencyColumn"><fmt:formatNumber type="currency" value="${budget.totalSpent}"/></td>
                    <td class="currencyColumn"><fmt:formatNumber type="currency" value="${budget.getRemainingBalance()}"/></td>
                    <td class="currencyColumn"><fmt:formatNumber type="currency" value="${budget.totalAmount}"/></td>
                    <td><a class="deleteButton" href="AccountSummary?operation=delete&budgetId=${budget.id}">X</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>