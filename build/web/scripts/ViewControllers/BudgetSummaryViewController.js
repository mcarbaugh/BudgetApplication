
function BudgetSummaryViewController() {
    var piechart;
    var BudgetId = document.getElementById("ActiveBudgetIdField").value;
    var Model = new BudgetSummaryModel();
    initializeChart(); // make sure the pie chart is initiated for an empty budget
    
    Model.ItemLoaded.subscribe(loadItem);
    Model.ItemDeleted.subscribe(removeItemFromView);
    Model.ItemChanged.subscribe(refreshItemInView);
    
    Model.ItemLoaded.subscribe(updateCategoryProgressBars);
    Model.ItemDeleted.subscribe(updateCategoryProgressBars);
    Model.ItemChanged.subscribe(updateCategoryProgressBars);
    
    Model.ItemLoaded.subscribe(updatePieChart);
    Model.ItemDeleted.subscribe(updatePieChart);
    Model.ItemChanged.subscribe(updatePieChart);
    Model.SendGetAllItemsRequest(BudgetId);
    
    // event handlers
    function loadItem(item) {
        var row, addTransactionCell, nameCell, plannedCell, spentCell, 
                remainingCell, actionCell, addButton;
        
        row = {};
        if(item) {
            switch(item.category) {
            case "FOOD":
                row = document.getElementById("FoodCategoryTable").insertRow(-1);
                break;
            case "GIVING":
                row = document.getElementById("GivingCategoryTable").insertRow(-1);
                break;
            case "HOUSING":
                row = document.getElementById("HousingCategoryTable").insertRow(-1);
                break;
            case "INSURANCE_TAX":
                row = document.getElementById("InsuranceCategoryTable").insertRow(-1);
                break;
            case "LIFESTYLE":
                row = document.getElementById("LifestyleCategoryTable").insertRow(-1);
                break;
            case "TRANSPORTATION":
                row = document.getElementById("TransportationCategoryTable").insertRow(-1);
                break;
            }
        }
        
        if(row) {
            // insert cells
            addTransactionCell = row.insertCell(0);
            nameCell = row.insertCell(1);
            plannedCell = row.insertCell(2);
            spentCell = row.insertCell(3);
            
            remainingCell = row.insertCell(4);
            actionCell = row.insertCell(5);
            
            row.id = item.id;
            
            // fill cell content with data
            addTransactionCell.append(new ButtonFactory().AddTransaction(openNewTransactionDialog));
            nameCell.innerHTML = item.name;
            plannedCell.innerHTML = "$" + parseFloat(item.amount).toFixed(2);
            spentCell.innerHTML = "$" + parseFloat(item.spent).toFixed(2);
            
            if(item.getRemaining() >= 0) {
                remainingCell.innerHTML = "$" + parseFloat(item.getRemaining()).toFixed(2);
                remainingCell.classList.add("positiveNumber");
                remainingCell.classList.remove("negativeNumber");
            }
            else {
                remainingCell.innerHTML = "-$" + Math.abs(parseFloat(item.getRemaining())).toFixed(2);
                remainingCell.classList.add("negativeNumber");
                remainingCell.classList.remove("positiveNumber");
            }
            
            actionCell.append(new ButtonFactory().EditItem(openEditItemDialog));
            actionCell.append(new ButtonFactory().DeleteItem(deleteItem));
            nameCell.classList.add("leftAlignColumn");
            plannedCell.classList.add("rightAlignColumn");
            spentCell.classList.add("rightAlignColumn");
            remainingCell.classList.add("rightAlignColumn");
        }
    }
    
    function refreshItemInView(item) {
        var row;
        
        row = document.getElementById(item.id);
        if(row) {
            
            nameCell = row.cells[1];
            plannedCell = row.cells[2];
            spentCell = row.cells[3];
            remainingCell = row.cells[4];
                        
            // fill cell content with data
            nameCell.innerHTML = item.name;
            plannedCell.innerHTML = "$" + parseFloat(item.amount).toFixed(2);
            spentCell.innerHTML = "$" + parseFloat(item.spent).toFixed(2);
            
            if(item.getRemaining() >= 0) {
                remainingCell.innerHTML = "$" + parseFloat(item.getRemaining()).toFixed(2);
                remainingCell.classList.add("positiveNumber");
                remainingCell.classList.remove("negativeNumber");
            }
            else {
                remainingCell.innerHTML = "-$" + Math.abs(parseFloat(item.getRemaining())).toFixed(2);
                remainingCell.classList.add("negativeNumber");
                remainingCell.classList.remove("positiveNumber");
            }
            
        }
    }
    
    function removeItemFromView(itemId) {
        var row;
        row = document.getElementById(itemId);
        row.parentNode.removeChild(row);
    }
    
    function updateCategoryProgressBars() {
        
        var width, totalSpent, totalAmount, progressWidth;
        width = document.getElementById("FoodProgressBarBottom").offsetWidth;
        
        updateFoodProgress();
        updateTransportationProgress();
        updateLifeStyleProgress();
        updateHousingProgress();
        updateInsuranceProgress();
        updateGivingProgress();
        
        function updateFoodProgress() {
            totalSpent = Model.FoodItemList.GetTotalSpent();
            totalAmount = Model.FoodItemList.GetTotalAmount();
            document.getElementById("FoodTotalSpent").innerHTML = "$" + totalSpent;
            document.getElementById("FoodTotalAmount").innerHTML = "$" + totalAmount;

            progressWidth = (totalSpent/totalAmount) * width;
            document.getElementById("FoodProgressBar").style.width = progressWidth + "px";
        }
        
        function updateTransportationProgress() {
            totalSpent = Model.TransportationItemList.GetTotalSpent();
            totalAmount = Model.TransportationItemList.GetTotalAmount();
            document.getElementById("TransportationTotalSpent").innerHTML = "$" + totalSpent;
            document.getElementById("TransportationTotalAmount").innerHTML = "$" + totalAmount;

            progressWidth = (totalSpent/totalAmount) * width;
            document.getElementById("TransportationProgressBar").style.width = progressWidth + "px";
        }
        
        function updateLifeStyleProgress() {
            totalSpent = Model.LifestyleItemList.GetTotalSpent();
            totalAmount = Model.LifestyleItemList.GetTotalAmount();
            document.getElementById("LifestyleTotalSpent").innerHTML = "$" + totalSpent;
            document.getElementById("LifestyleTotalAmount").innerHTML = "$" + totalAmount;

            progressWidth = (totalSpent/totalAmount) * width;
            document.getElementById("LifestyleProgressBar").style.width = progressWidth + "px";
        }
        
        function updateHousingProgress() {
            totalSpent = Model.HousingItemList.GetTotalSpent();
            totalAmount = Model.HousingItemList.GetTotalAmount();
            document.getElementById("HousingTotalSpent").innerHTML = "$" + totalSpent;
            document.getElementById("HousingTotalAmount").innerHTML = "$" + totalAmount;

            progressWidth = (totalSpent/totalAmount) * width;
            document.getElementById("HousingProgressBar").style.width = progressWidth + "px";
        }
        
        function updateInsuranceProgress() {
            totalSpent = Model.InsuranceItemList.GetTotalSpent();
            totalAmount = Model.InsuranceItemList.GetTotalAmount();
            document.getElementById("InsuranceTotalSpent").innerHTML = "$" + totalSpent;
            document.getElementById("InsuranceTotalAmount").innerHTML = "$" + totalAmount;

            progressWidth = (totalSpent/totalAmount) * width;
            document.getElementById("InsuranceProgressBar").style.width = progressWidth + "px";
        }
        
        function updateGivingProgress() {
            totalSpent = Model.GivingItemList.GetTotalSpent();
            totalAmount = Model.GivingItemList.GetTotalAmount();
            document.getElementById("GivingTotalSpent").innerHTML = "$" + totalSpent;
            document.getElementById("GivingTotalAmount").innerHTML = "$" + totalAmount;

            progressWidth = (totalSpent/totalAmount) * width;
            document.getElementById("GivingProgressBar").style.width = progressWidth + "px";
        }
        
    }
    
    function updatePieChart() {
        var foodAmount, transportationAmount, lifestyleAmount, housingAmount,
                insuranceAmount, givingAmount, categoryData ,labels, backgroundColor,
                data;
        
        foodAmount = Model.FoodItemList.GetTotalAmount();
        transportationAmount = Model.TransportationItemList.GetTotalAmount();
        lifestyleAmount = Model.LifestyleItemList.GetTotalAmount();
        housingAmount = Model.HousingItemList.GetTotalAmount();
        insuranceAmount = Model.InsuranceItemList.GetTotalAmount();
        givingAmount = Model.GivingItemList.GetTotalAmount();
        
        if(piechart) {
            // make sure something shows up if data is empty
            if(foodAmount <= 0 && transportationAmount <= 0 && lifestyleAmount <= 0 && 
                    housingAmount <= 0 && insuranceAmount <= 0 && givingAmount <= 0) {
                
                foodAmount = 1;
                transportationAmount = 1;
                lifestyleAmount = 1;
                housingAmount = 1;
                insuranceAmount = 1;
                givingAmount = 1;
            }
            
            piechart.data.datasets[0].data[0] = foodAmount;
            piechart.data.datasets[0].data[1] = transportationAmount;
            piechart.data.datasets[0].data[2] = lifestyleAmount;
            piechart.data.datasets[0].data[3] = housingAmount;
            piechart.data.datasets[0].data[4] = insuranceAmount;
            piechart.data.datasets[0].data[5] = givingAmount;
            piechart.update();
        } 
        else {
            
            categoryData = [foodAmount, transportationAmount, lifestyleAmount, housingAmount, insuranceAmount, givingAmount];
            backgroundColor = ["#113d59", "#1d5374", "#3d5d76", "#518198", "#659db8", "#aadbff"];
            labels = ["Food", "Transportation", "Lifestyle", "Housing", "Insurance", "Giving"];
            data = {labels: labels,
                    datasets: [{
                            data: categoryData, 
                            backgroundColor: backgroundColor, 
                            hoverBackgroundColor: backgroundColor}]
                    };
            
            piechart = document.getElementById("PieChart");
            piechart = new Chart(piechart, {
                type: 'doughnut',
                data: data,
                options: {
                    legend: {
                        display: false
                    }
                }
            });
            
            piechart.update();
        }
    }
    
    function initializeChart() {
        var categoryData ,labels, backgroundColor, data;
        
        categoryData = [1, 1, 1, 1, 1, 1];
        backgroundColor = ["#e55a50", "#ffa866", "#698c9e", "#80c6e2", "#64afa4", "#aa75b2"];
        labels = [" Food", " Transportation", " Lifestyle", " Housing", " Insurance", " Giving"];
        data = {labels: labels,
                datasets: [{
                        data: categoryData, 
                        backgroundColor: backgroundColor, 
                        hoverBackgroundColor: backgroundColor}]
                };

        piechart = document.getElementById("PieChart");
        piechart = new Chart(piechart, {
            type: 'doughnut',
            data: data,
            labels: labels,
            options: {
                legend: {
                    display: false
                }
            }
        });
        piechart.update();
    }
    
    
    // make changes to the model
    function saveNewItem() {
        var name, category, amount, item;
        
        name = document.getElementById("NewItemNameField").value;
        category = document.getElementById("NewItemCategoryField").value;
        amount = document.getElementById("NewItemAmountField").value;
        item = new BudgetItem(0, BudgetId, name, category, amount, 0);
        Model.SendSaveItemRequest(item);
        closeItemDialog();
    }
    
    function saveExistingItem() {
        var id, name, category, amount, item;
        
        id = document.getElementById("EditItemIdField").value;
        name = document.getElementById("EditItemNameField").value;
        category = document.getElementById("EditItemCategoryField").value;
        amount = document.getElementById("EditItemAmountField").value;
        item = new BudgetItem(id, BudgetId, name, category, amount, 0);
        Model.SendUpdateItemRequest(item);
        document.getElementById("EditItemDialog").style.display = "none";
        document.getElementById("EditItemForm").reset();
    }
    
    function deleteItem(sender) {
        var message, itemId, table;
        
        itemId = sender.target.parentNode.parentNode.id;
        
        message = "Deleting a budget item may also delete a few transactions. Continue?";
        if(confirm(message)) {
            Model.SendDeleteItemRequest(itemId);
            table = sender.target.parentNode.parentNode.parentNode.id;
            
            switch(table) {
                case "FoodCategoryTable":
                    Model.FoodItemList.RemoveItem(itemId);
                    break;
                case "GivingCategoryTable":
                    Model.GivingItemList.RemoveItem(itemId);
                    break;
                case "HousingCategoryTable":
                    Model.HousingItemList.RemoveItem(itemId);
                    break;
                case "InsuranceCategoryTable":
                    Model.InsuranceItemList.RemoveItem(itemId);
                    break;
                case "LifestyleCategoryTable":
                    Model.LifestyleItemList.RemoveItem(itemId);
                    break;
                case "TransportationCategoryTable":
                    Model.TransportationItemList.RemoveItem(itemId);
                    break;
            }
        }
    }
    
    function saveTransaction() {
        var itemId, name, vendor, amount, date, transaction;
        
        itemId = document.getElementById("ItemIdField").value;
        vendor = document.getElementById("TransactionVendorField").value;
        amount = document.getElementById("TransactionAmountField").value;
        date = document.getElementById("TransactionDateField").value;
        
        // make a new transaction and pass it to model
        transaction = new Transaction(0, itemId, "NONE", vendor, amount, date);
        Model.SendSaveTransactionRequest(transaction);
        closeTransactionDialog();
    }
    
    
    // open and close dialogs
    function openNewItemDialog(event) {
        var id, categoryField;
        
        categoryField = document.getElementById("NewItemCategoryField");
        id = event.target.id;
        switch(id) {
            case "AddFoodItemButton":
                categoryField.value = "FOOD";
                break;
            case "AddGivingItemButton":
                categoryField.value = "GIVING";
                break;
            case "AddHousingItemButton":
                categoryField.value = "HOUSING";
                break;
            case "AddInsuranceItemButton":
                categoryField.value = "INSURANCE_TAX";
                break;
            case "AddLifestyleItemButton":
                categoryField.value = "LIFESTYLE";
                break;
            case "AddTransportationItemButton":
                categoryField.value = "TRANSPORTATION";
                break;
            default:
                categoryField.value = "NONE";
                break;
        }
        
        document.getElementById("NewItemDialog").style.display = "block";
    }
    
    function openEditItemDialog(event) {
        var table, id, item;
        
        table = event.target.parentNode.parentNode.parentNode.id;
        id = event.target.parentNode.parentNode.id;
        
        switch(table) {
            case "FoodCategoryTable":
                item = Model.FoodItemList.GetItemById(id);
                break;
            case "GivingCategoryTable":
                item = Model.GivingItemList.GetItemById(id);
                break;
            case "HousingCategoryTable":
                item = Model.HousingItemList.GetItemById(id);
                break;
            case "InsuranceCategoryTable":
                item = Model.InsuranceItemList.GetItemById(id);
                break;
            case "LifestyleCategoryTable":
                item = Model.LifestyleItemList.GetItemById(id);
                break;
            case "TransportationCategoryTable":
                item = Model.TransportationItemList.GetItemById(id);
                break;
            default:
                break;
        }
        
        if(item) {
            document.getElementById("EditItemIdField").value = id;
            document.getElementById("EditItemNameField").value = item.name;
            document.getElementById("EditItemAmountField").value = item.amount;
            document.getElementById("EditItemCategoryField").value = item.category;
            document.getElementById("EditItemDialog").style.display = "block";
        }
    }
    
    function openNewTransactionDialog(event) {
        var itemId;
        
        itemId = event.target.parentNode.parentNode.id;
        document.getElementById("ItemIdField").value = itemId;
        document.getElementById("NewTransactionDialog").style.display = "block";
    }
    
    function openNewBudgetDialog(event) {
        document.getElementById("NewBudgetDialog").style.display = "block";
    }
    
    function closeItemDialog(event) {
        document.getElementById("NewItemDialog").style.display = "none";
        document.getElementById("NewItemForm").reset();
        
        document.getElementById("EditItemDialog").style.display = "none";
        document.getElementById("EditItemForm").reset();
    }
    
    function closeTransactionDialog(event) {
        document.getElementById("NewTransactionDialog").style.display = "none";
        document.getElementById("NewTransactionForm").reset();
    }
    
    function closeBudgetDialog(event) {
        document.getElementById("NewBudgetDialog").style.display = "none";
        document.getElementById("NewBudgetForm").reset();
    }
    
    
    // Miscellaneous
    function handleWindowClick(event) {
        var newBudgetDialog, newItemDialog, editItemDialog, newTransactionDialog;
        
        newBudgetDialog = document.getElementById("NewBudgetDialog");
        if(event.target === newBudgetDialog) {
            newBudgetDialog.style.display = "none";
            document.getElementById("NewBudgetForm").reset();
        }
        
        newItemDialog = document.getElementById("NewItemDialog");
        if(event.target === newItemDialog) {
            newItemDialog.style.display = "none";
            document.getElementById("NewItemForm").reset();
        }
        
        editItemDialog = document.getElementById("EditItemDialog");
        if(event.target === editItemDialog) {
            editItemDialog.style.display = "none";
            document.getElementById("EditItemForm").reset();
        }
        
        newTransactionDialog = document.getElementById("NewTransactionDialog");
        if(event.target === newTransactionDialog) {
            newTransactionDialog.style.display = "none";
            document.getElementById("NewTransactionForm").reset();
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
    
    function handlePieChartClick(event) {
        
        var activePoints = piechart.getElementsAtEvent(event);
        var firstPoint = activePoints[0];
        var label = piechart.data.labels[firstPoint._index];        
        location.href = "#";
        location.href = "#" + label;
    }
    
    // initialize listeners
    (function() {
        var i, button, numericField, numericFields, addItemButtons;
    
        document.getElementById("PieChart").addEventListener('click', handlePieChartClick);
        
        // new item form save and close events
        document.getElementById("SaveNewItemButton").addEventListener('click', saveNewItem);
        document.getElementById("CancelNewItemButton").addEventListener('click', closeItemDialog);
        
        // edit item form save and close events
        document.getElementById("SaveEditItemButton").addEventListener('click', saveExistingItem);
        document.getElementById("CancelEditItemButton").addEventListener('click', closeItemDialog);
        
        // tranaction save and close events
        document.getElementById("SaveNewTransactionButton").addEventListener('click', saveTransaction);
        document.getElementById("CancelNewTransactionButton").addEventListener('click', closeTransactionDialog);
        
        // budget close events
        document.getElementById("NewBudgetButton").addEventListener('click', openNewBudgetDialog);
        document.getElementById("CancelNewBudgetButton").addEventListener('click', closeBudgetDialog);
        
        // window events
        window.addEventListener("click", handleWindowClick);
        
        // input field events
        numericFields = document.getElementsByClassName("NumericField");
        addItemButtons = document.getElementsByClassName("AddItemButton");
        
        for(i = 0; i < addItemButtons.length; i += 1) {
            button = addItemButtons[i];
            button.addEventListener("click", openNewItemDialog);
        }
        
        for(i = 0; i < numericFields.length; i += 1) {
            numericField = numericFields[i];
            numericField.addEventListener("paste", cancelPasteEvent);
            numericField.addEventListener("keypress", isNumberKey);
        }
    })();
}