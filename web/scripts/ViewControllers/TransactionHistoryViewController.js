
function TransactionHistoryViewController() {
    var budgetId = 0;
    var Model = new TransactionHistoryModel();
    
    Model.TransactionLoaded.subscribe(loadTransaction);
    Model.TransactionDeleted.subscribe(removeTransactionFromView);
    Model.TransactionChanged.subscribe(refreshTransactionInView);
    
    budgetId = document.getElementById("BudgetIdField").value;
    Model.sendGetAllTransactionsRequest(budgetId);
    
    // event handlers
    function loadTransaction(transaction) {
        var row, vendorCell, itemCell, categoryCell, amountCell, dateCell, actionCell;
        
        row = document.getElementById("transactionTableBody").insertRow(-1);
        if(row) {  
            row.id = transaction.id;

            vendorCell = row.insertCell(0);
            itemCell = row.insertCell(1);
            categoryCell = row.insertCell(2);
            amountCell = row.insertCell(3);
            dateCell = row.insertCell(4);
            actionCell = row.insertCell(5);
            
            vendorCell.innerHTML = transaction.vendor;
            itemCell.innerHTML = transaction.item;
            categoryCell.innerHTML = transaction.category;
            amountCell.innerHTML = transaction.amount;
            dateCell.innerHTML = transaction.date;
            actionCell.append(new ButtonFactory().EditItem(openEditTransactionDialog));
            actionCell.append(new ButtonFactory().DeleteItem(deleteTransaction));
            
            vendorCell.classList.add("leftAlignColumn");
            itemCell.classList.add("leftAlignColumn");
            categoryCell.classList.add("leftAlignColumn");
            amountCell.classList.add("rightAlignColumn");
            
        }
        
    }
    
    function refreshTransactionInView(transaction) {
        var row, vendorCell, itemCell, categoryCell, amountCell, dateCell;
        
        row = document.getElementById(transaction.id);
        if(row) {
            
            vendorCell = row.cells[1];
            itemCell = row.cells[2];
            categoryCell = row.cells[3];
            amountCell = row.cells[4];
            dateCell = row.cells[5];
                        
            // fill cell content with data
            vendorCell.innerHTML = transaction.vendor;
            itemCell.innerHTML = transaction.item;
            categoryCell.innerHTML = transaction.category;
            amountCell.innerHTML = transaction.amount;
            dateCell.innerHTML = transaction.date;    
        }
    }
    
    function removeTransactionFromView(transactionId) {
        var row;
        row = document.getElementById(transactionId);
        row.parentNode.removeChild(row);
    }
    
    // make changes to the model
    function saveExistingTransaction() {
        var id, vendor, item, category, amount, date, transaction;
        
        id = document.getElementById("EditTransactionIdField").value;
        vendor = document.getElementById("EditTransactionVendorField").value;
        item = document.getElementById("EditTransactionItemField").value;
        category = document.getElementById("EditTransactionCategoryField").value;
        amount = document.getElementById("EditTransactionAmountField").value;
        date = document.getElementById("EditTransactionDateField").value;
        transaction = new TransactionDetails(id, vendor, item, category, amount, date);
        Model.SendUpdateTransactionRequest(transaction);
        document.getElementById("EditTransactionDialog").style.display = "none";
        document.getElementById("EditTransactionForm").reset();
    }
    
    function deleteTransaction(sender) {
        var message, transactionId;
        
        transactionId = sender.target.parentNode.parentNode.id;
        
        message = "Delete Transaction. Continue?";
        if(confirm(message)) {
            Model.SendDeleteTransactionRequest(transactionId);
            Model.transactionsList.RemoveTransaction(transactionId);
        }
    }
    
    // open and close dialogs
    function openEditTransactionDialog(event) {
        var id, transaction;
        
        id = event.target.parentNode.parentNode.id;
        transaction = Model.transactionsList.GetTransactionById(id);
        
        if(transaction) {
            
            document.getElementById("EditTransactionIdField").value = id;
            document.getElementById("EditTransactionVendorField").value = transaction.vendor;
            document.getElementById("EditTransactionItemField").value = transaction.item;
            document.getElementById("EditTransactionCategoryField").value = transaction.category;
            document.getElementById("EditTransactionAmountField").value = transaction.amount;
            document.getElementById("EditTransactionDateField").value = transaction.date;
            document.getElementById("EditTransactionDialog").style.display = "block";
        }
    }
    
    function closeTransactionDialog(event) {       
        document.getElementById("EditTransactionDialog").style.display = "none";
        document.getElementById("EditTransactionForm").reset();
    }
    
    // Miscellaneous
    function handleWindowClick(event) {
        var editTransactionDialog;
        editTransactionDialog = document.getElementById("EditTransactionDialog");
        if(event.target === editTransactionDialog) {
            editTransactionDialog.style.display = "none";
            document.getElementById("EditTransactionForm").reset();
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
        var i, button, numericField, numericFields;
               
        // edit transaction form save and close events
        document.getElementById("SaveEditTransactionButton").addEventListener('click', saveExistingTransaction);
        document.getElementById("CancelEditTransactionButton").addEventListener('click', closeTransactionDialog);
        // ^^^^ DIALOG BUTTONS ^^^
         
        
        // window events
        window.addEventListener("click", handleWindowClick);
        
        // input field events
        numericFields = document.getElementsByClassName("NumericField");
        
        for(i = 0; i < numericFields.length; i += 1) {
            numericField = numericFields[i];
            numericField.addEventListener("paste", cancelPasteEvent);
            numericField.addEventListener("keypress", isNumberKey);
        }
    })();
}