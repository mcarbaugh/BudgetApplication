
    var httpRequest;

    function openNewHousingItemDialog() {
        // close popups on the page

        document.getElementById("newHousingItemDialog").style.display = "block";
    }

    function closeNewHousingItemDialog() {
        document.getElementById("newHousingItemDialog").style.display = "none";
        document.getElementById("newHousingItemForm").reset();
    }

    function init() {
        document.getElementById("AddHousingItemButton").addEventListener('click', createNewHousingItem);
    }

    function createNewHousingItem() {
        var url = "CreateItem";
        var method = "POST";
        var isAsync = true;
        
        httpRequest = new XMLHttpRequest();
        httpRequest.onreadystatechange = updateHousingItemTable;
        httpRequest.open(method, url, isAsync);
        httpRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        httpRequest.send();
    }

    function updateHousingItemTable() {
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
            if (httpRequest.status === 200) {
                
                document.getElementById("newHousingItemDialog").style.display = "none";
                
                var table = document.getElementById("housingCategoryTableBody");
                var row = table.insertRow(-1);
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                var cell3 = row.insertCell(2);
                var cell4 = row.insertCell(3);
                var cell5 = row.insertCell(4);
                cell1.innerHTML = "+";
                cell2.innerHTML = "NEW CELL2";
                cell3.innerHTML = "NEW CELL3";
                cell4.innerHTML = "NEW CELL4";
                cell5.innerHTML = "NEW CELL5";
                
                cell2.className = "leftAlignColumn";
                cell3.className = "rightAlignColumn";
                cell4.className = "rightAlignColumn";
                cell5.className = "rightAlignColumn";
                
            }
            else {
                
            }
        }
    }
    
    function parseItemData() {
        
    }