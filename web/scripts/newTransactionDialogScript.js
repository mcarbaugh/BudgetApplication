
var transactionRequest;
var rowId;

function openTransactionDialog(itemId, itemRowId) {
    document.getElementById("transactionItemIdInput").value = itemId;
    document.getElementById("newTransactionDialog").style.display = "block";
    rowId = itemRowId;
}

function closeTransactionDialog() {
    document.getElementById("newTransactionDialog").style.display = "none";
    document.getElementById("newTransactionForm").reset();
}

function initializeTransactionDialog() {
    document.getElementById("NewTransactionButton").addEventListener('click', newTransactionCallback);
}

function newTransactionCallback() {
    var url = "CreateTransaction";
    var method = "POST";
    var isAsync = true;
    
    var itemId = document.getElementById("transactionItemIdInput").value;
    var transactionName = document.getElementById("transactionNameInput").value;
    var transactionVendor = document.getElementById("transactionVendorInput").value;
    var transactionAmount = document.getElementById("transactionAmountInput").value;
    var transactionDate = document.getElementById("transactionDateInput").value;
    
    transactionRequest = new XMLHttpRequest();
    transactionRequest.onreadystatechange = updateTransaction;
    transactionRequest.open(method, url, isAsync);
    transactionRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    var arguments = "itemId=" + encodeURIComponent(itemId)
           + "&transactionName=" + encodeURIComponent(transactionName) 
           + "&transactionVendor=" + encodeURIComponent(transactionVendor) 
           + "&transactionAmount=" + encodeURIComponent(transactionAmount)
           + "&transactionDate=" + encodeURIComponent(transactionDate);
    transactionRequest.send(arguments);
}

function updateTransaction() {
    if (transactionRequest.readyState === XMLHttpRequest.DONE) {
        if (transactionRequest.status === 200) {

            if(rowId !== null) {
                updateExistingItemWithTransactionInfo();
            }
        }
        else {
            alert("Unable to add transaction. Try again after refreshing the page.");
        }
    }

    document.getElementById("newTransactionForm").reset();
    document.getElementById("newTransactionDialog").style.display = "none";
}

function updateExistingItemWithTransactionInfo() {
    
    // get data from XML file
    var xml = transactionRequest.responseXML;
    var items = xml.getElementsByTagName("items")[0];
    var item = items.childNodes[0];

    var amount = item.getElementsByTagName("amount")[0].childNodes[0].nodeValue;
    var spent = item.getElementsByTagName("spent")[0].childNodes[0].nodeValue;
    var remaining = amount - spent;
    
    // get cells for the row
    var row = document.getElementById(rowId);
    var cell3 = row.cells[2];
    var cell4 = row.cells[3];
    var cell5 = row.cells[4];
    
    cell3.innerHTML = "$" + parseFloat(amount).toFixed(2);
    cell4.innerHTML = "$" + parseFloat(spent).toFixed(2);
    
    if(remaining < 0) {
        cell5.innerHTML = "($" + Math.abs(parseFloat(remaining).toFixed(2)) + ")";
    } 
    else {
        cell5.innerHTML = "$" + parseFloat(remaining).toFixed(2);
    }
}