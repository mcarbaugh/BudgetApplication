
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<div class="modal" id="NewBudgetDialog">
    <div class="modal-content">
        <form id="NewBudgetForm" action="CreateBudget" method="post" class="modalForm">
            <h1>New Budget</h1>
            <label>
                <span>Month:</span>
                <select name="monthDropdown" form="NewBudgetForm">
                    <c:forEach items="${months}" var="month" varStatus="counter">
                        <option><c:out value="${months[counter.index]}"/></option>
                    </c:forEach>
                </select>
            </label>
            <label>
                <span>Year:</span>
                <select name="yearDropdown" form="NewBudgetForm">
                    <c:forEach items="${years}" var="year" varStatus="counter">
                        <option><c:out value="${year}"/></option>
                    </c:forEach>
                </select>
            </label>
            <input type="submit" value="Submit"/>
            <input type="button" value="Cancel" id="CancelNewBudgetButton"/>
        </form>
    </div>
</div>