
function ButtonFactory() {
    
    this.AddTransaction = function(handler) {
        var button = document.createElement("input");
        button.type = "button";
        button.value = "+";
        button.classList.add("addButton");
        button.addEventListener('click', handler);
        return button;
    };
    
    this.EditItem = function(handler) {
        var button = document.createElement("input");
        button.type = "button";
        button.value = "Edit";
        button.classList.add("editButton");
        button.addEventListener('click', handler);
        return button;
    };
    
    this.DeleteItem = function(handler) {
        var button = document.createElement("input");
        button.type = "button";
        button.value = "Delete";
        button.classList.add("deleteButton");
        button.addEventListener('click', handler);
        return button;
    };
}