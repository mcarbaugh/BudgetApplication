
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<div class="modal" id="newHousingItemDialog">
    <div class="modal-content">
        <form id="newHousingItemForm">
            <span class="close" onClick="closeNewHousingItemDialog()">&times;</span>
            <h1>New Housing Item</h1>
            <label>
                <span>Amount: </span>
                <input type="text" name="amount" onpaste="return false" onkeypress="return isNumberKey(event)" id="housingItemAmount"/>
            </label>
            <label>
                <span>Description: </span>
                <input type="text" name="description" id="housingItemName"/>
            </label>
            <input type="text" name="budgetId" class="hiddenField" value="${budget.getId()}"  id="housingBudgetId"/>
            <input type="text" name="category" class="hiddenField" value="HOUSING"  id="housingItemCategory"/>
        </form>
        <input type=button id ="AddHousingItemButton" value="Submit"/>
        <input type="button" value="Cancel" onclick="closeNewHousingItemDialog()"/>
    </div>
</div>