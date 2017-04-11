
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<div class="modal" id="newHousingItemDialog">
    <div class="modal-content">
        <form id="newHousingItemForm" action="BudgetSummary" method="post">
            <span class="close" onClick="closeNewHousingItemDialog()">&times;</span>
            <h1>New Housing Item</h1>
            
            <label>
                <span>Amount: </span>
                <input type="text" name="amount"/>
            </label>
            
            <label>
                <span>Description: </span>
                <input type="text" name="description"/>
            </label>
            
            <input type="text" name="operation" value="createItem" class="hiddenField"/>
            <input type="text" name="category" value="HOUSING" class="hiddenField"/>
            <input type="submit" value="Submit"/>
            <input type="button" value="Cancel" onclick="closeNewHousingItemDialog()"/>
        </form>
    </div>
</div>