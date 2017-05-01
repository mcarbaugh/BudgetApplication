
function IncomeList() {
    this.List = [];
}

IncomeList.prototype = {
    AddIncome: function(income) {
        this.List.push(income);
    },
    UpdateItem: function(income) {
        var id, oldIncome;
        
        id = income.id;
        oldIncome = this.GetIncomeById(id);
        
        if(oldIncome) {
            oldIncome.budgetId = income.budgetId;
            oldIncome.name = income.name;
            oldIncome.amount = income.amount;
        }
    },    
    RemoveIncome: function(id) {
        var i;
        for(i = 0; i < this.List.length; i += 1) {
            if(this.List[i].id === id) {
                this.List.splice(i, 1);
                return;
            }
        }
    },
    GetIncomeById: function(id) {
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
            if(this.List[i].amount) {
                total = (parseFloat(this.List[i].amount) + total);
            }
        }
        
        return total.toFixed(2);
    }
};