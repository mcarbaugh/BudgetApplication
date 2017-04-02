<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<div class="budgetTableContainer">
    <table>
        <c:if test="${fn:length(budgets) gt 0}">
            <thead>
                <tr>
                    <th>Month</th>
                    <th>Year</th>
                    <th>Spent</th>
                    <th>Total</th>
                    <th>Remove</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${budgets}" var="budget" varStatus="counter">
                    <tr>
                        <td><c:out value="${budget.month}"/></td>
                        <td><c:out value="${budget.year}"/></td>
                        <td class="moneyCell">$<c:out value="${budget.totalSpent}"/></td>
                        <td class="moneyCell">$<c:out value="${budget.totalAmount}"/></td>
                        <td><input type="button" value="X" class="deleteBudgetButton"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </c:if>
    </table>
</div>