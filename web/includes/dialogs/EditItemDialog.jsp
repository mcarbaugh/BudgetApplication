
<!DOCTYPE html>
<div class="modal" id="EditItemDialog">
    <div class="modal-content">
        <form class="modalForm" id="EditItemForm">
            <h1 id="EditItemHeader">Edit Item</h1>
            <label>
                <span>Name: </span>
                <input type="text" id="EditItemNameField"/>
            </label>
            <label>
                <span>Amount: </span>
                <input type="text" class="NumericField" id="EditItemAmountField" />
            </label>
            <input type="text" class="HiddenField" id="EditItemCategoryField" value="NONE"/>
        </form>
        <input type="text" value="0" class="HiddenField" id="EditItemIdField"/>
        <input type=button value="Submit" id="SaveEditItemButton"/>
        <input type="button" value="Cancel" id="CancelEditItemButton"/>
    </div>
</div>
