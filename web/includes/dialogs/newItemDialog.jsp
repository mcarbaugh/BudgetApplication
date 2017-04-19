
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<div class="modal" id="newItemDialog">
    <div class="modal-content">
        <form id="newItemForm" class="modalForm">
            <span class="close" onClick="closeItemDialog()">&times;</span>
            <h1>New Item</h1>
            <label>
                <span>Name: </span>
                <input type="text" name="description" id="itemNameInput"/>
            </label>
            <label>
                <span>Amount: </span>
                <input type="text" name="amount" onpaste="return false" onkeypress="return isNumberKey(event)" id="itemAmountInput"/>
            </label>
            <input type="text" name="budgetId" class="hiddenField" value="${budget.getId()}"  id="budgetIdInput"/>
            <input type="text" name="category" class="hiddenField" value="NONE"  id="itemCategoryInput"/>
        </form>
        <input type=button value="Submit" id ="NewItemButton"/>
        <input type="button" value="Cancel" onclick="closeItemDialog()"/>
    </div>
</div>