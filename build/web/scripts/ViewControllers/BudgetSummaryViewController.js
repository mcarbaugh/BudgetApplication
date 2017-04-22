
function BudgetSummaryViewController() {
    
    var BudgetId = document.getElementById("ActiveBudgetIdField").value;
    var Model = new BudgetSummaryModel();
    Model.ItemLoaded.subscribe(loadItem);
    Model.ItemDeleted.subscribe(removeItemFromView);
    Model.ItemChanged.subscribe(refreshItemInView);
    Model.SendGetAllItemsRequest(BudgetId);
    
    function loadItem(item) {
        var row, addTransactionCell, nameCell, plannedCell, spentCell, 
                remainingCell, actionCell, addButton;
        
        row = {};
        if(item) {
            switch(item.category) {
            case "FOOD":
                row = document.getElementById("FoodCategoryTable").insertRow(-1);
                break;
            case "GIVING":
                row = document.getElementById("GivingCategoryTable").insertRow(-1);
                break;
            case "HOUSING":
                row = document.getElementById("HousingCategoryTable").insertRow(-1);
                break;
            case "INSURANCE_TAX":
                row = document.getElementById("InsuranceCategoryTable").insertRow(-1);
                break;
            case "LIFESTYLE":
                row = document.getElementById("LifestyleCategoryTable").insertRow(-1);
                break;
            case "TRANSPORTATION":
                row = document.getElementById("TransportationCategoryTable").insertRow(-1);
                break;
            }
        }
        
        if(row) {
            // insert cells
            addTransactionCell = row.insertCell(0);
            nameCell = row.insertCell(1);
            plannedCell = row.insertCell(2);
            spentCell = row.insertCell(3);
            
            remainingCell = row.insertCell(4);
            actionCell = row.insertCell(5);
            
            row.id = item.id;
            
            // fill cell content with data
            addTransactionCell.append(new ButtonFactory().AddTransaction(openNewTransactionDialog));
            nameCell.innerHTML = item.name;
            plannedCell.innerHTML = "$" + parseFloat(item.amount).toFixed(2);
            spentCell.innerHTML = "$" + parseFloat(item.spent).toFixed(2);
            
            if(item.getRemaining() > 0) {
                remainingCell.innerHTML = "$" + parseFloat(item.getRemaining()).toFixed(2);
                remainingCell.classList.add("positiveNumber");
                remainingCell.classList.remove("negativeNumber");
            }
            else {
                remainingCell.innerHTML = "-$" + Math.abs(parseFloat(item.getRemaining())).toFixed(2);
                remainingCell.classList.add("negativeNumber");
                remainingCell.classList.remove("positiveNumber");
            }
            
            actionCell.append(new ButtonFactory().EditItem(openEditItemDialog));
            actionCell.append(new ButtonFactory().DeleteItem(deleteItem));
            nameCell.classList.add("leftAlignColumn");
            plannedCell.classList.add("rightAlignColumn");
            spentCell.classList.add("rightAlignColumn");
            remainingCell.classList.add("rightAlignColumn");
        }
    }
    
    function refreshItemInView(item) {
        var row;
        
        row = document.getElementById(item.id);
        if(row) {
            
            nameCell = row.cells[1];
            plannedCell = row.cells[2];
            spentCell = row.cells[3];
            remainingCell = row.cells[4];
                        
            // fill cell content with data
            nameCell.innerHTML = item.name;
            plannedCell.innerHTML = "$" + parseFloat(item.amount).toFixed(2);
            spentCell.innerHTML = "$" + parseFloat(item.spent).toFixed(2);
            
            if(item.getRemaining() > 0) {
                remainingCell.innerHTML = "$" + parseFloat(item.getRemaining()).toFixed(2);
                remainingCell.classList.add("positiveNumber");
                remainingCell.classList.remove("negativeNumber");
            }
            else {
                remainingCell.innerHTML = "-$" + Math.abs(parseFloat(item.getRemaining())).toFixed(2);
                remainingCell.classList.add("negativeNumber");
                remainingCell.classList.remove("positiveNumber");
            }
            
        }
    }
    
    function removeItemFromView(itemId) {
        var row;
        row = document.getElementById(itemId);
        row.parentNode.removeChild(row);
    }
    
    function saveNewItem() {
        var name, category, amount, item;
        
        name = document.getElementById("NewItemNameField").value;
        category = document.getElementById("NewItemCategoryField").value;
        amount = document.getElementById("NewItemAmountField").value;
        item = new BudgetItem(0, BudgetId, name, category, amount, 0);
        Model.SendSaveItemRequest(item);
        closeItemDialog();
    }
    
    function saveExistingItem() {
        var id, name, category, amount, item;
        
        id = document.getElementById("EditItemIdField").value;
        name = document.getElementById("EditItemNameField").value;
        category = document.getElementById("EditItemCategoryField").value;
        amount = document.getElementById("EditItemAmountField").value;
        item = new BudgetItem(id, BudgetId, name, category, amount, 0);
        Model.SendUpdateItemRequest(item);
        document.getElementById("EditItemDialog").style.display = "none";
        document.getElementById("EditItemForm").reset();
    }
    
    function deleteItem(sender) {
        var message, itemId;
        
        itemId = sender.target.parentNode.parentNode.id;
        
        message = "Deleting a budget item may also delete a few transactions. Continue?";
        if(confirm(message)) {
            Model.SendDeleteItemRequest(itemId);
        }
    }
    
    function saveTransaction(sender) {
        var itemId, name, vendor, amount, date;
        
        itemId = document.getElementById("ItemIdField").value;
        name = document.getElementById("TransactionNameField").value;
        vendor = document.getElementById("TransactionVendorField").value;
        amount = document.getElementById("TransactionAmountField").value;
        date = document.getElementById("TransactionDateField").value;
        // make a new transaction and pass it to model
        closeTransactionDialog();
    }
    
    function openNewItemDialog(event) {
        var id, categoryField;
        
        categoryField = document.getElementById("NewItemCategoryField");
        id = event.target.id;
        switch(id) {
            case "AddFoodItemButton":
                categoryField.value = "FOOD";
                break;
            case "AddGivingItemButton":
                categoryField.value = "GIVING";
                break;
            case "AddHousingItemButton":
                categoryField.value = "HOUSING";
                break;
            case "AddInsuranceItemButton":
                categoryField.value = "INSURANCE_TAX";
                break;
            case "AddLifestyleItemButton":
                categoryField.value = "LIFESTYLE";
                break;
            case "AddTransportationItemButton":
                categoryField.value = "TRANSPORTATION";
                break;
            default:
                categoryField.value = "NONE";
                break;
        }
        
        document.getElementById("NewItemDialog").style.display = "block";
    }
    
    function openEditItemDialog(event) {
        var table, id, item;
        
        table = event.target.parentNode.parentNode.parentNode.id;
        id = event.target.parentNode.parentNode.id;
        
        switch(table) {
            case "FoodCategoryTable":
                item = Model.GetFoodItemById(id);
                break;
            case "GivingCategoryTable":
                item = Model.GetGivingItemById(id);
                break;
            case "HousingCategoryTable":
                item = Model.GetHousingItemById(id);
                break;
            case "InsuranceCategoryTable":
                item = Model.GetInsuranceItemById(id);
                break;
            case "LifestyleCategoryTable":
                item = Model.GetLifestyleItemById(id);
                break;
            case "TransportationCategoryTable":
                item = Model.GetTransportationItemById(id);
                break;
            default:
                break;
        }
        
        if(item) {
            document.getElementById("EditItemIdField").value = id;
            document.getElementById("EditItemNameField").value = item.name;
            document.getElementById("EditItemAmountField").value = item.amount;
            document.getElementById("EditItemCategoryField").value = item.category;
            document.getElementById("EditItemDialog").style.display = "block";
        }
    }
    
    function openNewTransactionDialog(event) {
        var itemId, itemIdField;
        
        itemId = event.target.parentNode.parentNode.id;
        itemIdField = document.getElementById("ItemIdField");
        document.getElementById("NewTransactionDialog").style.display = "block";
    }
    
    function closeItemDialog(event) {
        document.getElementById("NewItemDialog").style.display = "none";
        document.getElementById("NewItemForm").reset();
        
        document.getElementById("EditItemDialog").style.display = "none";
        document.getElementById("EditItemForm").reset();
    }
    
    function closeTransactionDialog(event) {
        document.getElementById("NewTransactionDialog").style.display = "none";
        document.getElementById("NewTransactionForm").reset();
    }
    
    function handleWindowClick(event) {
        var newItemDialog, editItemDialog, newTransactionDialog;
        
        newItemDialog = document.getElementById("NewItemDialog");
        if(event.target === newItemDialog) {
            newItemDialog.style.display = "none";
            document.getElementById("NewItemForm").reset();
        }
        
        editItemDialog = document.getElementById("EditItemDialog");
        if(event.target === editItemDialog) {
            editItemDialog.style.display = "none";
            document.getElementById("EditItemForm").reset();
        }
        
        newTransactionDialog = document.getElementById("NewTransactionDialog");
        if(event.target === newTransactionDialog) {
            newTransactionDialog.style.display = "none";
            document.getElementById("NewTransactionForm").reset();
        }
    }
    
    function isNumberKey(event) {
        
        var characterCode = (event.which) ? event.which : event.code;

        // check for a decimal
        if(characterCode === 46 && event.target.value.split('.').length <= 1) {
            return;
        }

        // check for non-numeric characters
        if(characterCode > 31 && (characterCode < 48 || characterCode > 57)) {
            event.preventDefault();
        }
    }
    
    function cancelPasteEvent(event) {
        event.preventDefault();
    }
    
    // initialize listeners
    (function() {
        var i, button, numericField, numericFields, addItemButtons;
    
        // new item form save and close events
        document.getElementById("SaveNewItemButton").addEventListener('click', saveNewItem);
        document.getElementById("CancelNewItemButton").addEventListener('click', closeItemDialog);
        
        // edit item form save and close events
        document.getElementById("SaveEditItemButton").addEventListener('click', saveExistingItem);
        document.getElementById("CancelEditItemButton").addEventListener('click', closeItemDialog);
        
        // tranaction save and close events
        document.getElementById("SaveNewTransactionButton").addEventListener('click', saveTransaction);
        document.getElementById("CancelNewTransactionButton").addEventListener('click', closeTransactionDialog);
        
        // window events
        window.addEventListener("click", handleWindowClick);
        
        // input field events
        numericFields = document.getElementsByClassName("NumericField");
        addItemButtons = document.getElementsByClassName("AddItemButton");
        
        for(i = 0; i < addItemButtons.length; i += 1) {
            button = addItemButtons[i];
            button.addEventListener("click", openNewItemDialog);
        }
        
        for(i = 0; i < numericFields.length; i += 1) {
            numericField = numericFields[i];
            numericField.addEventListener("paste", cancelPasteEvent);
            numericField.addEventListener("keypress", isNumberKey);
        }
    })();
}