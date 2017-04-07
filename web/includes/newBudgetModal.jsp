
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<div class="modal" id="NewBudgetModal">
    <div class="modal-content">
        <form id="NewBudgetForm" action="#" method="post">
            <span class="close" onClick="closeAddBudgetDialog()">&times;</span>
            <h1>Create Budget</h1>
            <label>
                <span>Month:</span>
                <select name="monthDropdown" form="NewBudgetForm">
                    <c:forEach items="${months}" var="month" varStatus="counter">
                        <option value="month"><c:out value="${months[counter.index]}"/></option>
                    </c:forEach>
                </select>
            </label>
            <label>
                <span>Year:</span>
                <select name="yearDropdown" form="NewBudgetForm">
                    <option value="year">1980</option>
                    <option value="year">1981</option>
                </select>
            </label>
            <input type="submit" value="Create"/>
            <input type="button" value="Cancel"/>
        </form>
    </div>
</div>
