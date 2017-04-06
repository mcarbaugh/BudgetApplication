
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<div class="budgetTableContainer">
    <input type="submit" value="Add Budget"/>
    <div id="NewBudgetModal" class="modal">
        <div class="modal-content">
            <form id="NewBudgetForm" action="#" method="post">
                <h3>Create budget</h3>
                Month:
                <select name="monthDropdown" form="NewBudgetForm">
                    <option value="month"><c:out value="${months[0]}"/></option>
                    <option value="month"><c:out value="${months[1]}"/></option>
                </select>
                <br/>
                Year:
                <select name="yearDropdown" form="NewBudgetForm">
                    <option value="month"></option>
                </select>
            </form>
        </div>
    </div>
    <table>
        <c:if test="${fn:length(budgets) gt 0}">
            <thead>
                <tr>
                    <th>View</th>
                    <th>Year</th>
                    <th>Month</th>
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
                            <a href="BudgetDetails?operation=read&budgetId=${budget.id}">
                                <c:out value="Details"/>
                            </a>
                        </td>
                        <td><c:out value="${budget.year}"/></td>
                        <td><c:out value="${budget.month.toString()}"/></td>
                        <td><fmt:formatNumber type="currency" value="${budget.totalSpent}"/></td>
                        <td><fmt:formatNumber type="currency" value="${budget.getRemainingBalance()}"/></td>
                        <td><fmt:formatNumber type="currency" value="${budget.totalAmount}"/></td>
                        <td><a href="AccountSummary?operation=delete&budgetId=${budget.id}">X</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </c:if>
    </table>
</div>