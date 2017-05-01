
function TransactionHistoryViewController() {
    var budgetId = 0;
    var Model = new TransactionHistoryModel();
    
    Model.TransactionLoaded.subscribe(loadTransaction);
    Model.TransactionDeleted.subscribe(removeTransactionFromView);
    Model.TransactionChanged.subscribe(refreshTransactionInView);
    
    budgetId = document.getElementById("BudgetIdField").value;
    Model.sendGetAllTransactionsRequest(budgetId);
    
    Model.ListChanged.subscribe(refreshTransactionTable);
    
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
            amountCell.innerHTML = "$" + parseFloat(transaction.amount).toFixed(2);
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
        var row, vendorCell, amountCell;
        
        row = document.getElementById(transaction.id);
        if(row) {
            
            vendorCell = row.cells[0];
            amountCell = row.cells[3];

            vendorCell.innerHTML = transaction.vendor;
            amountCell.innerHTML = "$" + parseFloat(transaction.amount).toFixed(2);
        }
    }
    
    function removeTransactionFromView(transactionId) {
        var row;
        row = document.getElementById(transactionId);
        row.parentNode.removeChild(row);
    }
    
    
    
    // make changes to the model
    function saveExistingTransaction() {
        var id, vendor, amount, transaction;
        
        id = document.getElementById("EditTransactionIdField").value;
        vendor = document.getElementById("EditTransactionVendorField").value;
        amount = document.getElementById("EditTransactionAmountField").value;
        transaction = new TransactionDetails(id, vendor, "", "", amount, "");
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
            document.getElementById("EditTransactionAmountField").value = transaction.amount;
            document.getElementById("EditTransactionDialog").style.display = "block";
        }
    }
    
    function closeTransactionDialog(event) {       
        document.getElementById("EditTransactionDialog").style.display = "none";
        document.getElementById("EditTransactionForm").reset();
    }
    
    
    function sortTransactions() {
        var field, direction;
        field = document.getElementById("SortField").value;
        direction = document.getElementById("SortDirection").value;
        
        switch(field) {
            case "category":
                Model.SortByCategory(direction);
                break;
            case "amount":
                Model.SortByAmount(direction);
                break;
            case "date":
                Model.SortByDate(direction);
                break;
            default:
                break;
        }
    }
    
    function refreshTransactionTable() {
        var row, vendorCell, itemCell, categoryCell, amountCell, dateCell, actionCell, transaction;
        var oldTable = document.getElementById('transactionTableBody');
        var newTable = document.createElement('Tbody');
        newTable.id = "transactionTableBody";
        
        var i;
        for(i = 0; i< Model.transactionsList.List.length; i = i + 1) {
            
            transaction = Model.transactionsList.List[i];
            row = newTable.insertRow(-1);
            
            vendorCell = row.insertCell(0);
            itemCell = row.insertCell(1);
            categoryCell = row.insertCell(2);
            amountCell = row.insertCell(3);
            dateCell = row.insertCell(4);
            actionCell = row.insertCell(5);
            
            vendorCell.innerHTML = transaction.vendor;
            itemCell.innerHTML = transaction.item;
            categoryCell.innerHTML = transaction.category;
            amountCell.innerHTML = "$" + parseFloat(transaction.amount).toFixed(2);
            dateCell.innerHTML = transaction.date;
            actionCell.append(new ButtonFactory().EditItem(openEditTransactionDialog));
            actionCell.append(new ButtonFactory().DeleteItem(deleteTransaction));
            
            vendorCell.classList.add("leftAlignColumn");
            itemCell.classList.add("leftAlignColumn");
            categoryCell.classList.add("leftAlignColumn");
            amountCell.classList.add("rightAlignColumn");
        }
        
        oldTable.parentNode.replaceChild(newTable, oldTable);
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
        var i, numericField, numericFields;
               
        // edit transaction form save and close events
        document.getElementById("SaveEditTransactionButton").addEventListener('click', saveExistingTransaction);
        document.getElementById("CancelEditTransactionButton").addEventListener('click', closeTransactionDialog);
        
        document.getElementById("ApplySortButton").addEventListener("click", sortTransactions);
        
        // window events
        window.addEventListener("click", handleWindowClick);
        
        document.getElementById("UserDropDownBtn").addEventListener("click", function() {
            if(!document.getElementById("userInfoContainer").classList.contains("show")) {
                document.getElementById("userInfoContainer").classList.toggle("show");
            }
        });
        
        document.addEventListener("click", function(event) {
            if (!event.target.matches('.dropbtn')) {
                document.getElementById("userInfoContainer").classList.remove("show");
            }
        });
        
        // input field events
        numericFields = document.getElementsByClassName("NumericField");
        
        for(i = 0; i < numericFields.length; i += 1) {
            numericField = numericFields[i];
            numericField.addEventListener("paste", cancelPasteEvent);
            numericField.addEventListener("keypress", isNumberKey);
        }
    })();
}