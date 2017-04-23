
function ItemList() {
    this.List = [];
}

ItemList.prototype = {
    AddItem: function(item) {
        this.List.push(item);
    },
    UpdateItem: function(item) {
        var id, oldItem;
        
        id = item.id;
        oldItem = this.GetItemById(id);
        
        if(oldItem) {
            oldItem.budgetId = item.budgetId;
            oldItem.name = item.name;
            oldItem.amount = item.amount;
            oldItem.spent = item.spent;
        }
    },    
    RemoveItem: function(id) {
        var i;
        for(i = 0; i < this.List.length; i += 1) {
            if(this.List[i].id === id) {
                this.List.splice(i, 1);
                return;
            }
        }
    },
    GetItemById: function(id) {
        var i;
        for(i = 0; i < this.List.length; i += 1) {
            if(this.List[i].id === id) {
                return this.List[i];
            }
        }
    },
    GetTotalAmount: function() {
        var i;
        
        var total = 0;
        for(i = 0; i < this.List.length; i += 1) {
            total = (parseFloat(this.List[i].amount) + total);
        }
        
        return total.toFixed(2);
    },
    GetTotalSpent: function() {
        var i;
        
        var total = 0.0;
        for(i = 0; i < this.List.length; i += 1) {
            total = (parseFloat(this.List[i].spent) + total);
        }
        
        return total.toFixed(2);
    }
};