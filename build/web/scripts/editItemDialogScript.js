
    var editItemRequest;
    var rowId;
    
    function openDialogWithCurrentValues(id, name, amount, spent, category, editRowId) {
        document.getElementById("editItemCategoryInput").value = "FOOD";
        document.getElementById("editItemDialog").style.display = "block";
        
        document.getElementById("editItemIdInput").value = id;
        document.getElementById("editItemNameInput").value = name;
        document.getElementById("editItemAmountInput").value = amount;
        document.getElementById("editItemCategoryInput").value = category;
        document.getElementById("editItemSpentInput").value = spent;
        
        rowId = editRowId;
    }

    function closeEditItemDialog() {
        document.getElementById("editItemCategoryInput").value = "NONE";
        document.getElementById("editItemDialog").style.display = "none";
        document.getElementById("editItemForm").reset();
    }
    
    function initializeEditItemDialog() {
        document.getElementById("EditItemButton").addEventListener('click', editItemCallback);
    }
    
    function editItemCallback() {
        var url = "EditItem";
        var method = "POST";
        var isAsync = true;
        
        var itemId = document.getElementById("editItemIdInput").value;
        var itemName = document.getElementById("editItemNameInput").value;
        var itemAmount = document.getElementById("editItemAmountInput").value;
        var itemCategory = document.getElementById("editItemCategoryInput").value;
        var itemSpent = document.getElementById("editItemSpentInput").value;

        editItemRequest = new XMLHttpRequest();
        editItemRequest.onreadystatechange = updateExistingItem;
        editItemRequest.open(method, url, isAsync);
        editItemRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

        var arguments = "itemId=" + encodeURIComponent(itemId)
               + "&itemName=" + encodeURIComponent(itemName) 
               + "&itemAmount=" + encodeURIComponent(itemAmount)
               + "&itemCategory=" + encodeURIComponent(itemCategory)
               + "&itemSpent=" + encodeURIComponent(itemSpent);
        editItemRequest.send(arguments);
    }

    function updateExistingItem() {
        if (editItemRequest.readyState === XMLHttpRequest.DONE) {
            if (editItemRequest.status === 200) {
                
                if(rowId !== null) {
                    updateExistingItemInTable();
                }
            }
            else {
                alert("Unable to create new item. Try again after refreshing the page.");
            }
        }
        
        document.getElementById("editItemForm").reset();
        document.getElementById("editItemDialog").style.display = "none";
    }
    
    function updateExistingItemInTable() {
        
        // get data from XML file
        var xml = editItemRequest.responseXML;
        var items = xml.getElementsByTagName("items")[0];
        var item = items.childNodes[0];
        var id = item.getElementsByTagName("id")[0].childNodes[0].nodeValue;
        
        var name;
        if(item.getElementsByTagName("name")[0].childNodes.length > 0) {
            name = item.getElementsByTagName("name")[0].childNodes[0].nodeValue;
        } else {
            name = "";    
        }
        
        var category = item.getElementsByTagName("category")[0].childNodes[0].nodeValue;
        var amount = item.getElementsByTagName("amount")[0].childNodes[0].nodeValue;
        var spent = item.getElementsByTagName("spent")[0].childNodes[0].nodeValue;
        var remaining = amount - spent;
        
        // get cells for the row
        var row = document.getElementById(rowId);
        var cell2 = row.cells[1];
        var cell3 = row.cells[2];
        var cell4 = row.cells[3];
        var cell5 = row.cells[4];
        var cell6 = row.cells[5];
        
        // update new information
        cell2.innerHTML = name;
        cell3.innerHTML = "$" + parseFloat(amount).toFixed(2);
        cell4.innerHTML = "$" + parseFloat(spent).toFixed(2);
        cell5.innerHTML = "$" + parseFloat(remaining).toFixed(2);
        
        // reconstruct edit button
        var editButton = document.createElement("input");
        editButton.type = "button";
        editButton.value = "Edit";
        editButton.classList.add("editButton");
        editButton.onclick = function() {
            openDialogWithCurrentValues(id, name, amount, spent, category, rowId);
        };
        
        // reconstruct delete button
        var deleteButton = document.createElement("input");
        deleteButton.type = "button";
        deleteButton.value = "Delete";
        deleteButton.classList.add("deleteButton");
        deleteButton.onclick = function() {
            deleteItemCallback(id, deleteButton);
        };
        
        // add buttons back to row
        var row = document.getElementById("rowId");
        cell6.innerHTML = "";
        cell6.append(editButton);
        cell6.append(deleteButton);
    }