
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<div class="modal" id="newBudgetDialog">
    <div class="modal-content">
        <form id="newBudgetForm" action="CreateBudget" method="post" class="modalForm">
            <span class="close" onClick="closeAddBudgetDialog()">&times;</span>
            <h1>New Budget</h1>
            <label>
                <span>Month:</span>
                <select name="monthDropdown" form="newBudgetForm">
                    <c:forEach items="${months}" var="month" varStatus="counter">
                        <option><c:out value="${months[counter.index]}"/></option>
                    </c:forEach>
                </select>
            </label>
            <label>
                <span>Year:</span>
                <select name="yearDropdown" form="newBudgetForm">
                    <c:forEach items="${years}" var="year" varStatus="counter">
                        <option><c:out value="${year}"/></option>
                    </c:forEach>
                </select>
            </label>
            <input type="submit" value="Submit"/>
            <input type="button" value="Cancel" onclick="closeAddBudgetDialog()"/>
        </form>
    </div>
</div>