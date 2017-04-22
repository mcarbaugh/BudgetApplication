
<!DOCTYPE html>
<div class="modal" id="NewItemDialog">
    <div class="modal-content">
        <form class="modalForm" id="NewItemForm">
            <h1 id="NewItemHeader">New Item</h1>
            <label>
                <span>Name: </span>
                <input type="text" id="NewItemNameField"/>
            </label>
            <label>
                <span>Amount: </span>
                <input type="text" class="NumericField" id="NewItemAmountField" />
            </label>
            <label>
                <span>Category: </span>
                <input type="text" id="NewItemCategoryField" class="No-Border" value="NONE" readonly/>
            </label>
        </form>
        <input type=button value="Submit" id="SaveNewItemButton"/>
        <input type="button" value="Cancel" id="CancelNewItemButton"/>
    </div>
</div>
