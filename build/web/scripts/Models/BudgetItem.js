
function BudgetItem(id, budgetId, name, category, amount, spent) {
    
    this.id = id;
    this.budgetId = budgetId;
    this.name = name;
    this.category = category;
    this.amount = amount;
    this.spent = spent;
    this.getRemaining = function() {
        return (this.amount - this.spent);
    };
}

BudgetItem.prototype = {
    updateBudgetId: function(value) {
        this.budgetId = value;
    },
    updateName: function(value) {
        this.name = value;
    },
    updateAmount: function(value) {
        this.amount = value;
    },
    addTransaction: function(value) {
        this.spent += value;
    }
};