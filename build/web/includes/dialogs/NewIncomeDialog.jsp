

<!DOCTYPE html>
<div class="modal" id="NewIncomeDialog">
    <div class="modal-content">
        <form class="modalForm" id="NewIncomeForm">
            <h1 id="NewIncomeHeader">New Income</h1>
            <label>
                <span>Source: </span>
                <input type="text" id="NewIncomeNameField"/>
            </label>
            <label>
                <span>Amount: </span>
                <input type="text" class="NumericField" id="NewIncomeAmountField" />
            </label>
        </form>
        <input type=button value="Submit" id="SaveNewIncomeButton"/>
        <input type="button" value="Cancel" id="CancelNewIncomeButton"/>
    </div>
</div>
