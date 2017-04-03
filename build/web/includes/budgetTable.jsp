
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<div class="budgetTableContainer">
    <table>
        <c:if test="${fn:length(budgets) gt 0}">
            <thead>
                <tr>
                    <th>Budget</th>
                    <th>Spent</th>
                    <th>Remaining</th>
                    <th>Total</th>
                    <th>Remove</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${budgets}" var="budget" varStatus="counter">
                    <tr>
                        <td>
                            <a href="/#AccountSummary">
                                <c:out value="${budget.year} ${budget.month}"/>
                            </a>
                        </td>
                        <td class="moneyCell"><fmt:formatNumber type="currency" value="${budget.totalSpent}"/></td>
                        <td class="moneyCell"><fmt:formatNumber type="currency" value="${budget.getRemainingBalance()}"/></td>
                        <td class="moneyCell"><fmt:formatNumber type="currency" value="${budget.totalAmount}"/></td>
                        <td><a href="AccountSummary?operation=delete&budgetId=${budget.id}">X</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </c:if>
    </table>
</div>