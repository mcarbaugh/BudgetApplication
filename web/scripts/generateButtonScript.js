

        
function constructAddTransactionButton(id, rowId) {
    var addButton = document.createElement("input");
    addButton.type = "button";
    addButton.value = "+";
    addButton.classList.add("addButton");
    addButton.onclick = function() {
        openTransactionDialog(id, rowId);
    };
    
    return addButton;
}

function constructEditItemButton(id, category, rowId) {
    
    var editButton = document.createElement("input");
    editButton.type = "button";
    editButton.value = "Edit";
    editButton.classList.add("editButton");
    editButton.onclick = function() {
        openDialogWithCurrentValues(id, category, rowId);
    };
    
    return editButton;
}


function constructDeleteItemButton(id) {
    
    var deleteButton = document.createElement("input");
    deleteButton.type = "button";
    deleteButton.value = "Delete";
    deleteButton.classList.add("deleteButton");
    deleteButton.onclick = function() {
        deleteItemCallback(id, deleteButton);
    };
    
    return deleteButton;
}