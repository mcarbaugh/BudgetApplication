
function BudgetSummaryModel() {
    this.FoodItemList = new ItemList();
    this.GivingItemList = new ItemList();
    this.HousingItemList = new ItemList();
    this.InsuranceItemList = new ItemList();
    this.LifestyleItemList = new ItemList();
    this.TransportationItemList = new ItemList();
    this.ItemLoaded = new Event();
    this.ItemDeleted = new Event();
    this.ItemChanged = new Event();
    this.SendGetAllItemsRequest = function(budgetId) {
        var getAllItemsRequest, url, method, isAsync, self, arguments;
        
        this.BudgetId = budgetId;
        url = "GetAllItems";
        method = "POST";
        isAsync = true;
        self = this;
        
        getAllItemsRequest = new XMLHttpRequest();
        getAllItemsRequest.onreadystatechange = handleGetAllItemsResponse;
        getAllItemsRequest.open(method, url, isAsync);
        getAllItemsRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        arguments = "budgetId=" + encodeURIComponent(budgetId);
        getAllItemsRequest.send(arguments);
        
        function handleGetAllItemsResponse() {
            if (getAllItemsRequest.readyState === XMLHttpRequest.DONE) {
                if (getAllItemsRequest.status === 200) {
                   
                    var item, id, name, category, amount, spent, newItem, i, xml, items;
                   
                    xml = getAllItemsRequest.responseXML;
                    items = xml.getElementsByTagName("items")[0];
                    
                    for(i = 0; i < items.childNodes.length; i += 1) {
                        item = items.childNodes[i];
                        id = item.getElementsByTagName("id")[0].childNodes[0].nodeValue;
                        
                        if(item.getElementsByTagName("name")[0].childNodes.length > 0) {
                            name = item.getElementsByTagName("name")[0].childNodes[0].nodeValue;
                        } else {
                            name = "";    
                        }

                        category = item.getElementsByTagName("category")[0].childNodes[0].nodeValue;
                        amount = item.getElementsByTagName("amount")[0].childNodes[0].nodeValue;
                        spent = item.getElementsByTagName("spent")[0].childNodes[0].nodeValue;
                        newItem = new BudgetItem(id, self.BudgetId, name, category, amount, spent);
                        
                        switch(category) {
                            case "FOOD":
                                self.FoodItemList.AddItem(newItem);
                                break;
                            case "GIVING":
                                self.GivingItemList.AddItem(newItem);
                                break;
                            case "HOUSING":
                                self.HousingItemList.AddItem(newItem);
                                break;
                            case "INSURANCE_TAX":
                                self.InsuranceItemList.AddItem(newItem);
                                break;
                            case "LIFESTYLE":
                                self.LifestyleItemList.AddItem(newItem);
                                break;
                            case "TRANSPORTATION":
                                self.TransportationItemList.AddItem(newItem);
                                break;
                            default:
                                break;
                        }
                        
                        self.ItemLoaded.fire(newItem);
                    }
                }
                else {
                    alert("Unable to find the selected budget.");
                }
            }
        };
    };
    this.SendSaveItemRequest = function(item) {
        var saveItemRequest, url, method, isAsync, self, arguments;
        url = "CreateItem";
        method = "POST";
        isAsync = true;
        self = this;
        
        saveItemRequest = new XMLHttpRequest();
        saveItemRequest.onreadystatechange = handleSaveItemResponse;
        saveItemRequest.open(method, url, isAsync);
        saveItemRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        arguments = "budgetId=" + encodeURIComponent(item.budgetId)
               + "&itemName=" + encodeURIComponent(item.name)
               + "&itemCategory=" + encodeURIComponent(item.category)
               + "&itemAmount=" + encodeURIComponent(item.amount);
       
       
        saveItemRequest.send(arguments);
        
        function handleSaveItemResponse() {
            if (saveItemRequest.readyState === XMLHttpRequest.DONE) {
                if (saveItemRequest.status === 200) {
                   
                   var item, id, name, category, amount, spent, newItem, i, xml, items;
                   
                    xml = saveItemRequest.responseXML;
                    items = xml.getElementsByTagName("items")[0];
                    
                    for(i = 0; i < items.childNodes.length; i += 1) {
                        item = items.childNodes[i];
                        id = item.getElementsByTagName("id")[0].childNodes[0].nodeValue;
                        
                        if(item.getElementsByTagName("name")[0].childNodes.length > 0) {
                            name = item.getElementsByTagName("name")[0].childNodes[0].nodeValue;
                        } else {
                            name = "";    
                        }

                        category = item.getElementsByTagName("category")[0].childNodes[0].nodeValue;
                        amount = item.getElementsByTagName("amount")[0].childNodes[0].nodeValue;
                        spent = item.getElementsByTagName("spent")[0].childNodes[0].nodeValue;
                        newItem = new BudgetItem(id, self.BudgetId, name, category, amount, spent);
                        
                        switch(category) {
                            case "FOOD":
                                self.FoodItemList.AddItem(newItem);
                                break;
                            case "GIVING":
                                self.GivingItemList.AddItem(newItem);
                                break;
                            case "HOUSING":
                                self.HousingItemList.AddItem(newItem);
                                break;
                            case "INSURANCE_TAX":
                                self.InsuranceItemList.AddItem(newItem);
                                break;
                            case "LIFESTYLE":
                                self.LifestyleItemList.AddItem(newItem);
                                break;
                            case "TRANSPORTATION":
                                self.TransportationItemList.AddItem(newItem);
                                break;
                            default:
                                break;
                        }
                        
                        self.ItemLoaded.fire(newItem);
                    }
                }
                else {
                    alert("Unable to create new item. Try again after refreshing the page.");
                }
            }
        }
    };
    this.SendDeleteItemRequest = function(itemId) {
        var deleteItemRequest, url, method, isAsync, self, arguments;
        url = "DeleteItem";
        method = "POST";
        isAsync = true;
        self = this;
        
        deleteItemRequest = new XMLHttpRequest();
        deleteItemRequest.onreadystatechange = handleDeleteItemResponse;
        deleteItemRequest.open(method, url, isAsync);
        deleteItemRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        arguments = "itemId=" + encodeURIComponent(itemId);
        
        deleteItemRequest.send(arguments);
        
        function handleDeleteItemResponse() {
            if (deleteItemRequest.readyState === XMLHttpRequest.DONE) {
                if (deleteItemRequest.status === 200) {
                    var xml;
                    
                    xml = deleteItemRequest.responseXML;
                    self.ItemDeleted.fire(itemId);
                }
                else {
                    alert("Unable to create new item. Try again after refreshing the page.");
                }
            }
        }
    };
    this.SendUpdateItemRequest = function(item) {
        var updateItemRequest, url, method, isAsync, self, arguments;
        url = "EditItem";
        method = "POST";
        isAsync = true;
        self = this;
        
        updateItemRequest = new XMLHttpRequest();
        updateItemRequest.onreadystatechange = handleSaveItemResponse;
        updateItemRequest.open(method, url, isAsync);
        updateItemRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        arguments = "itemId=" + encodeURIComponent(item.id)
               + "&itemName=" + encodeURIComponent(item.name)
               + "&itemCategory=" + encodeURIComponent(item.category)
               + "&itemAmount=" + encodeURIComponent(item.amount);
       
        updateItemRequest.send(arguments);
        
        function handleSaveItemResponse() {
            if (updateItemRequest.readyState === XMLHttpRequest.DONE) {
                if (updateItemRequest.status === 200) {
                   
                   var item, id, name, category, amount, spent, newItem, i, xml, items, oldItem;
                   
                    xml = updateItemRequest.responseXML;
                    items = xml.getElementsByTagName("items")[0];
                    
                    for(i = 0; i < items.childNodes.length; i += 1) {
                        item = items.childNodes[i];
                        id = item.getElementsByTagName("id")[0].childNodes[0].nodeValue;
                        
                        if(item.getElementsByTagName("name")[0].childNodes.length > 0) {
                            name = item.getElementsByTagName("name")[0].childNodes[0].nodeValue;
                        } else {
                            name = "";    
                        }

                        category = item.getElementsByTagName("category")[0].childNodes[0].nodeValue;
                        amount = item.getElementsByTagName("amount")[0].childNodes[0].nodeValue;
                        spent = item.getElementsByTagName("spent")[0].childNodes[0].nodeValue;
                        newItem = new BudgetItem(id, self.BudgetId, name, category, amount, spent);
                        
                        switch(category) {
                            case "FOOD":
                                self.FoodItemList.UpdateItem(newItem);
                                self.ItemChanged.fire(newItem);
                                break;
                            case "GIVING":
                                self.GivingItemList.UpdateItem(newItem);
                                self.ItemChanged.fire(newItem);
                                break;
                            case "HOUSING":
                                self.HousingItemList.UpdateItem(newItem);
                                self.ItemChanged.fire(newItem);
                                break;
                            case "INSURANCE_TAX":
                                self.InsuranceItemList.UpdateItem(newItem);
                                self.ItemChanged.fire(newItem);
                                break;
                            case "LIFESTYLE":
                                self.LifestyleItemList.UpdateItem(newItem);
                                self.ItemChanged.fire(newItem);
                                break;
                            case "TRANSPORTATION":
                                self.TransportationItemList.UpdateItem(newItem);
                                self.ItemChanged.fire(newItem);
                                break;
                            default:
                                break;
                        }
                    }
                }
                else {
                    alert("Unable to update item. Try again after refreshing the page.");
                }
            }
        }
    };
    this.SendSaveTransactionRequest = function(transaction) {
        var saveTransactionRequest, url, method, isAsync, self, arguments;
        url = "CreateTransaction";
        method = "POST";
        isAsync = true;
        self = this;
        
        saveTransactionRequest = new XMLHttpRequest();
        saveTransactionRequest.onreadystatechange = handleSaveItemResponse;
        saveTransactionRequest.open(method, url, isAsync);
        saveTransactionRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        arguments = "itemId=" + encodeURIComponent(transaction.itemId)
               + "&transactionName=" + encodeURIComponent(transaction.name)
               + "&transactionVendor=" + encodeURIComponent(transaction.vendor)
               + "&transactionAmount=" + encodeURIComponent(transaction.amount)
               + "&transactionDate=" + encodeURIComponent(transaction.date);
       
        saveTransactionRequest.send(arguments);
        
        function handleSaveItemResponse() {
            if (saveTransactionRequest.readyState === XMLHttpRequest.DONE) {
                if (saveTransactionRequest.status === 200) {
                   
                   var item, id, name, category, amount, spent, newItem, i, xml, items;
                   
                    xml = saveTransactionRequest.responseXML;
                    items = xml.getElementsByTagName("items")[0];
                    
                    for(i = 0; i < items.childNodes.length; i += 1) {
                        item = items.childNodes[i];
                        id = item.getElementsByTagName("id")[0].childNodes[0].nodeValue;
                        
                        if(item.getElementsByTagName("name")[0].childNodes.length > 0) {
                            name = item.getElementsByTagName("name")[0].childNodes[0].nodeValue;
                        } else {
                            name = "";    
                        }

                        category = item.getElementsByTagName("category")[0].childNodes[0].nodeValue;
                        amount = item.getElementsByTagName("amount")[0].childNodes[0].nodeValue;
                        spent = item.getElementsByTagName("spent")[0].childNodes[0].nodeValue;
                        newItem = new BudgetItem(id, self.BudgetId, name, category, amount, spent);
                        
                        switch(category) {
                            case "FOOD":
                                self.FoodItemList.UpdateItem(newItem);
                                break;
                            case "GIVING":
                                self.GivingItemList.UpdateItem(newItem);
                                break;
                            case "HOUSING":
                                self.HousingItemList.UpdateItem(newItem);
                                break;
                            case "INSURANCE_TAX":
                                self.InsuranceItemList.UpdateItem(newItem);
                                break;
                            case "LIFESTYLE":
                                self.LifestyleItemList.UpdateItem(newItem);
                                break;
                            case "TRANSPORTATION":
                                self.TransportationItemList.UpdateItem(newItem);
                                break;
                            default:
                                break;
                        }
                        
                        self.ItemChanged.fire(newItem);
                    }
                }
                else {
                    alert("Unable to create new item. Try again after refreshing the page.");
                }
            }
        }
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