
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<div class="modal" id="editItemDialog">
    <div class="modal-content">
        <form id="editItemForm" class="modalForm">
            <span class="close" onClick="closeEditItemDialog()">&times;</span>
            <h1>Edit Item</h1>
            <label>
                <span>Name: </span>
                <input type="text" name="description" id="editItemNameInput"/>
            </label>
            <label>
                <span>Amount: </span>
                <input type="text" name="amount" onpaste="return false" onkeypress="return isNumberKey(event)" id="editItemAmountInput"/>
            </label>
            <input type="text" name="itemId" class="hiddenField" value="0" id="editItemIdInput"/>
            <input type="text" name="budgetId" class="hiddenField" value="${budget.getId()}"  id="editItemBudgetIdInput"/>
            <input type="text" name="category" class="hiddenField" value="NONE"  id="editItemCategoryInput"/>
            <input type="text" name="spent" class="hiddenField"  value="0" id="editItemSpentInput"/>
        </form>
        <input type=button value="Save" id ="EditItemButton"/>
        <input type="button" value="Cancel" onclick="closeEditItemDialog()"/>
    </div>
</div>
