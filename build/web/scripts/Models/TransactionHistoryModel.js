
function TransactionHistoryModel() {
    this.transactionsList = new TransactionList();
    this.TransactionLoaded = new Event();
    this.TransactionChanged = new Event();
    this.TransactionDeleted = new Event();
    this.ListChanged = new Event();
    this.sendGetAllTransactionsRequest = function(budgetId) {
        var getAllTransactionsRequest, url, method, isAsync, self, arguments;
        
        this.BudgetId = budgetId;
        url = "GetAllTransactions";
        method = "POST";
        isAsync = true;
        self = this;
        
        getAllTransactionsRequest = new XMLHttpRequest();
        getAllTransactionsRequest.onreadystatechange = handleGetAllTransactionsResponse;
        getAllTransactionsRequest.open(method, url, isAsync);
        getAllTransactionsRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        arguments = "budgetId=" + encodeURIComponent(budgetId);
        getAllTransactionsRequest.send(arguments);
        
        function handleGetAllTransactionsResponse() {
            if (getAllTransactionsRequest.readyState === XMLHttpRequest.DONE) {
                if (getAllTransactionsRequest.status === 200) {
                   
                    var transaction, id, vendor, item, category, amount, date, i, xml, transactions, newTransaction;
                   
                    xml = getAllTransactionsRequest.responseXML;
                    transactions = xml.getElementsByTagName("transactions")[0];
                    
                    for(i = 0; i < transactions.childNodes.length; i += 1) {
                        transaction = transactions.childNodes[i];
                        id = transaction.getElementsByTagName("id")[0].childNodes[0].nodeValue;
                        
                        if(transaction.getElementsByTagName("vendor")[0].childNodes.length > 0) {
                            vendor = transaction.getElementsByTagName("vendor")[0].childNodes[0].nodeValue;
                        } else {
                            vendor = "";    
                        }
                        if(transaction.getElementsByTagName("item")[0].childNodes.length > 0) {
                            item = transaction.getElementsByTagName("item")[0].childNodes[0].nodeValue;
                        } else {
                            item = "";    
                        }
                        if(transaction.getElementsByTagName("category")[0].childNodes.length > 0) {
                            category = transaction.getElementsByTagName("category")[0].childNodes[0].nodeValue;
                        } else {
                            category = "";    
                        }
                        if(transaction.getElementsByTagName("amount")[0].childNodes.length > 0) {
                            amount = transaction.getElementsByTagName("amount")[0].childNodes[0].nodeValue;
                        } 
                        
                        if(transaction.getElementsByTagName("date")[0].childNodes.length > 0) {
                            date = transaction.getElementsByTagName("date")[0].childNodes[0].nodeValue;
                        } else {
                            date = "";    
                        }
                        newTransaction = new TransactionDetails(id, vendor, item, category, amount, date);
                        self.transactionsList.AddTransaction(newTransaction);
                        self.TransactionLoaded.fire(newTransaction);
                    }   
                }
                else {
                    alert("Unable to find the selected budget.");
                }
            }
        };
    };
    this.SendUpdateTransactionRequest = function(transaction) {
        var updateTransactionRequest, url, method, isAsync, self, arguments;
        url = "EditTransaction";
        method = "POST";
        isAsync = true;
        self = this;
        
        updateTransactionRequest = new XMLHttpRequest();
        updateTransactionRequest.onreadystatechange = handleUpdateTransactionResponse;
        updateTransactionRequest.open(method, url, isAsync);
        updateTransactionRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        arguments = "transactionId=" + encodeURIComponent(transaction.id)
                + "&transactionVendor=" + encodeURIComponent(transaction.vendor)
                + "&transactionAmount=" + encodeURIComponent(transaction.amount);
       
        updateTransactionRequest.send(arguments);
        
        function handleUpdateTransactionResponse() {
            if (updateTransactionRequest.readyState === XMLHttpRequest.DONE) {
                if (updateTransactionRequest.status === 200) {
                   
                   var transaction, id, vendor, item, category, amount, date, newTransaction, i, xml, transactions;
                   
                    xml = updateTransactionRequest.responseXML;
                    transactions = xml.getElementsByTagName("transactions")[0];
                    
                    for(i = 0; i < transactions.childNodes.length; i += 1) {
                        transaction = transactions.childNodes[i];
                        id = transaction.getElementsByTagName("id")[0].childNodes[0].nodeValue;
                        
                        if(transaction.getElementsByTagName("vendor")[0].childNodes.length > 0) {
                            vendor = transaction.getElementsByTagName("vendor")[0].childNodes[0].nodeValue;
                        } 
                        else {
                            vendor = "";    
                        }
                        
                        item = transaction.getElementsByTagName("item")[0].childNodes[0].nodeValue;
                        category = transaction.getElementsByTagName("category")[0].childNodes[0].nodeValue;
                        amount = transaction.getElementsByTagName("amount")[0].childNodes[0].nodeValue;
                        date = transaction.getElementsByTagName("date")[0].childNodes[0].nodeValue;
                        newTransaction = new TransactionDetails(id, vendor, item, category, amount, date);
                        
                        self.transactionsList.UpdateTransaction(newTransaction);
                        self.TransactionChanged.fire(newTransaction);
                    }
                }
                else {
                    alert("Unable to update transaction. Try again after refreshing the page.");
                }
            }
        }
    };
    this.SendDeleteTransactionRequest = function(transactionId) {
        var deleteTransactionRequest, url, method, isAsync, self, arguments;
        url = "DeleteTransaction";
        method = "POST";
        isAsync = true;
        self = this;
        
        deleteTransactionRequest = new XMLHttpRequest();
        deleteTransactionRequest.onreadystatechange = handleDeleteTransactionResponse;
        deleteTransactionRequest.open(method, url, isAsync);
        deleteTransactionRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        arguments = "transactionId=" + encodeURIComponent(transactionId);
        
        deleteTransactionRequest.send(arguments);
        
        function handleDeleteTransactionResponse() {
            if (deleteTransactionRequest.readyState === XMLHttpRequest.DONE) {
                if (deleteTransactionRequest.status === 200) {
                    var xml;
                    
                    xml = deleteTransactionRequest.responseXML;
                    self.TransactionDeleted.fire(transactionId);
                }
                else {
                    alert("Unable to delete transaction. Try again after refreshing the page.");
                }
            }
        }
    };
    this.SortByCategory = function(direction) {
        
        switch(direction) {
            case "ascending":
                this.transactionsList.SortByCategoryAscending();
                break;
            case "descending":
                this.transactionsList.SortByCategoryDescending();
                break;
        }
            
        this.ListChanged.fire();
    };
    this.SortByAmount = function(direction) {
        switch(direction) {
            case "ascending":
                this.transactionsList.SortByAmountAscending();
                break;
            case "descending":
                this.transactionsList.SortByAmountDescending();
                break;
        }
            
        this.ListChanged.fire();
    };
    this.SortByDate = function(direction) {
        switch(direction) {
            case "ascending":
                this.transactionsList.SortByDateAscending();
                break;
            case "descending":
                this.transactionsList.SortByDateDescending();
                break;
        }
            
        this.ListChanged.fire();
    };
} 
    
function Event() {
    this.handlers = [];  // observers
}

Event.prototype = {
    subscribe: function(fn) {
        this.handlers.push(fn);
    },
    unsubscribe: function(fn) {
        this.handlers = this.handlers.filter(
            function(item) {
                if (item !== fn) {
                    return item;
                }
            }
        );
    },
    fire: function(o, thisObj) {
        var scope = thisObj || window;
        this.handlers.forEach(function(item) {
            item.call(scope, o);
        });
    }
};