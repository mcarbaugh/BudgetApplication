
<!DOCTYPE html>
<div class="modal" id="EditIncomeDialog">
    <div class="modal-content">
        <form class="modalForm" id="EditIncomeForm">
            <h1 id="EditIncomeHeader">Edit Income</h1>
            <label>
                <span>Name: </span>
                <input type="text" id="EditIncomeNameField"/>
            </label>
            <label>
                <span>Amount: </span>
                <input type="text" class="NumericField" id="EditIncomeAmountField" />
            </label>
        </form>
        <input type="text" value="0" class="HiddenField" id="EditIncomeIdField"/>
        <input type=button value="Submit" id="SaveEditIncomeButton"/>
        <input type="button" value="Cancel" id="CancelEditIncomeButton"/>
    </div>
</div>
