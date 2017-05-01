
function TransactionList() {
    this.List = [];
}

TransactionList.prototype = {
    UpdateTransaction: function(transaction) {
        var id, oldTransaction;
        
        id = transaction.id;
        oldTransaction = this.GetTransactionById(id);
        
        if(oldTransaction) {
            oldTransaction.vendor = transaction.vendor;
            oldTransaction.item = transaction.item;
            oldTransaction.cateogry = transaction.category;
            oldTransaction.amount = transaction.amount;
            oldTransaction.date = transaction.date;
        }
    },
    RemoveTransaction: function(id) {
        var i;
        for(i = 0; i < this.List.length; i += 1) {
            if(this.List[i].id === id) {
                this.List.splice(i, 1);
                return;
            }
        }
    },
    GetTransactionById: function(id) {
        var i;
        for(i = 0; i < this.List.length; i += 1) {
            if(this.List[i].id === id) {
                return this.List[i];
            }
        }
    },
    AddTransaction: function(transaction) {
        this.List.push(transaction);
    },
    SortByCategoryAscending: function() {
        this.List.sort(CategoryAscendingComp);
    },
    SortByCategoryDescending: function() {
        this.List.sort(CategoryDescendingComp);
    },
    SortByAmountAscending: function() {
        this.List.sort(AmountAscendingComp);
    },
    SortByAmountDescending: function() {
        this.List.sort(AmountDescendingComp);
    },
    SortByDateAscending: function() {
        this.List.sort(DateAscendingComp);
    },
    SortByDateDescending: function() {
        this.List.sort(DateDescendingComp);
    }
};

function CategoryAscendingComp(a,b) {
    if (a.category < b.category)
        return -1;
    if (a.category > b.category)
        return 1;
    return 0;
}

function CategoryDescendingComp(a,b) {
    if (a.category < b.category)
        return 1;
    if (a.category > b.category)
        return -1;
    return 0;
}

function AmountAscendingComp(a, b) {
    var a_value = parseFloat(a.amount);
    var b_value = parseFloat(b.amount);
    
    if (a_value < b_value)
        return -1;
    if (a_value > b_value)
        return 1;
    return 0;
}

function AmountDescendingComp(a, b) {
    var a_value = parseFloat(a.amount);
    var b_value = parseFloat(b.amount);
    
    if (a_value < b_value)
        return 1;
    if (a_value > b_value)
        return -1;
    return 0;
}

function DateAscendingComp(a, b) {
    
    if (a.date < b.date)
        return -1;
    if (a.date > b.date)
        return 1;
    return 0;
}

function DateDescendingComp(a, b) {
    
    if (a.date < b.date)
        return 1;
    if (a.date > b.date)
        return -1;
    return 0;
}