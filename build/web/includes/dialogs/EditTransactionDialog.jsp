
<!DOCTYPE html>
<div class="modal" id="EditTransactionDialog">
    <div class="modal-content">
        <form id="EditTransactionForm" class="modalForm">
            <h1>Edit Transaction</h1>
            <label>
                <span>Vendor: </span>
                <input type="text" name="vendor" id="EditTransactionVendorField"/>
            </label>
            <label>
                <span>Item: </span>
                <input type="text" name="vendor" id="EditTransactionItemField"/>
            </label>
            <label>
                <span>Category: </span>
                <input type="text" name="vendor" id="EditTransactionCategoryField"/>
            </label>
            <label>
                <span>Amount: </span>
                <input type="text" class="NumericField" id="EditTransactionAmountField"/>
            </label>
            <label>
                <span>Date: </span>
                <input type="date" id="EditTransactionDateField"/>
            </label>
        </form>
        <input type="text" value="0" class="HiddenField" id="EditTransactionIdField"/>
        <input type=button value="Submit" id ="SaveEditTransactionButton"/>
        <input type="button" value="Cancel" id="CancelEditTransactionButton"/>
    </div>
</div>