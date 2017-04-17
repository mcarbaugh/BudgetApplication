
    var httpRequest;

    function openNewFoodItemDialog() {
        document.getElementById("itemCategoryInput").value = "FOOD";
        document.getElementById("newItemDialog").style.display = "block";
    }
    
    function openNewGivingItemDialog() {
        document.getElementById("itemCategoryInput").value = "GIVING";
        document.getElementById("newItemDialog").style.display = "block";
    }
    
    function openNewHousingItemDialog() {
        document.getElementById("itemCategoryInput").value = "HOUSING";
        document.getElementById("newItemDialog").style.display = "block";
    }
    
    function openNewInsuranceItemDialog() {
        document.getElementById("itemCategoryInput").value = "INSURANCE_TAX";
        document.getElementById("newItemDialog").style.display = "block";
    }
    
    function openNewLifestyleItemDialog() {
        document.getElementById("itemCategoryInput").value = "LIFESTYLE";
        document.getElementById("newItemDialog").style.display = "block";
    }
    
    function openNewTransportationItemDialog() {
        document.getElementById("itemCategoryInput").value = "TRANSPORTATION";
        document.getElementById("newItemDialog").style.display = "block";
    }

    function closeItemDialog() {
        document.getElementById("itemCategoryInput").value = "NONE";
        document.getElementById("newItemDialog").style.display = "none";
        document.getElementById("newItemForm").reset();
    }
    
    function isNumberKey(e) {
        
        var characterCode = (e.which) ? e.which : event.keyCode;
        
        // check for a decimal
        if(characterCode === 46 && e.srcElement.value.split('.').length <= 1) {
            return true;
        }
        
        // check for non-numeric characters
        if(characterCode > 31 && (characterCode < 48 || characterCode > 57)) {
            return false;
        }
        
        return true;
    }

    function init() {
        document.getElementById("NewItemButton").addEventListener('click', createNewItemCallback);
    }

    function createNewItemCallback() {
        var url = "CreateItem";
        var method = "POST";
        var isAsync = true;
        
        var budgetId = document.getElementById("budgetIdInput").value;
        var itemAmount = document.getElementById("itemAmountInput").value;
        var itemName = document.getElementById("itemNameInput").value;
        var itemCategory = document.getElementById("itemCategoryInput").value;
        
        httpRequest = new XMLHttpRequest();
        httpRequest.onreadystatechange = updateHousingItemTable;
        httpRequest.open(method, url, isAsync);
        httpRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        
        var arguments = "budgetId=" + encodeURIComponent(budgetId)
               + "&itemAmount=" + encodeURIComponent(itemAmount) 
               + "&itemName=" + encodeURIComponent(itemName)
               + "&itemCategory=" + encodeURIComponent(itemCategory);
   
        httpRequest.send(arguments);
        
    }

    function updateHousingItemTable() {
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
            if (httpRequest.status === 200) {
                parseItemData();
            }
            else {
                
                alert("Unable to create new item. Try again after refreshing the page.");
            }
        }
        
        document.getElementById("newItemForm").reset();
        document.getElementById("newItemDialog").style.display = "none";
    }
    
    function parseItemData() {
        var xml = httpRequest.responseXML;
        var items = xml.getElementsByTagName("items")[0];
        var item = items.childNodes[0];
        var id = item.getElementsByTagName("id")[0].childNodes[0].nodeValue;
        var name = item.getElementsByTagName("name")[0].childNodes[0].nodeValue;
        var category = item.getElementsByTagName("category")[0].childNodes[0].nodeValue;
        var amount = item.getElementsByTagName("amount")[0].childNodes[0].nodeValue;
        var spent = item.getElementsByTagName("spent")[0].childNodes[0].nodeValue;
        var remaining = amount - spent;

        var table;
        
        switch (category) {
            case "FOOD":
                table = document.getElementById("foodCategoryTableBody");
                break;
            case "GIVING":
                table = document.getElementById("givingCategoryTableBody");
                break;
            case "HOUSING":
                table = document.getElementById("housingCategoryTableBody");
                break;
            case "INSURANCE_TAX":
                table = document.getElementById("insuranceCategoryTableBody");
                break;
            case "LIFESTYLE":
                table = document.getElementById("lifestyleCategoryTableBody");
                break;
            case "TRANSPORTATION":
                table = document.getElementById("transportationCategoryTableBody");
                break
            default:
                break;
        }
        
        var row = table.insertRow(-1);
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        var cell4 = row.insertCell(3);
        var cell5 = row.insertCell(4);
        cell1.innerHTML = "+";
        cell2.innerHTML = name;
        cell3.innerHTML = "$" + parseFloat(amount).toFixed(2);
        cell4.innerHTML = "$" + parseFloat(spent).toFixed(2);
        cell5.innerHTML = "$" + parseFloat(remaining).toFixed(2);

        cell2.className = "leftAlignColumn";
        cell3.className = "rightAlignColumn";
        cell4.className = "rightAlignColumn";
        cell5.className = "rightAlignColumn";
    }