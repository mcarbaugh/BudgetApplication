
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
    }
};