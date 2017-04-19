
var deleteRequest;
var row;

function deleteItemCallback(itemId, sender) {
    var url = "DeleteItem";
    var method = "POST";
    var isAsync = true;

    var message = "Deleting a budget item may also delete a few transactions. Continue?";
    if(confirm(message)) {
        row = sender.parentNode.parentNode;
        
        deleteRequest = new XMLHttpRequest();
        deleteRequest.onreadystatechange = deleteItemFromTable;
        deleteRequest.open(method, url, isAsync);
        deleteRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

        var arguments = "itemId=" + encodeURIComponent(itemId);
        deleteRequest.send(arguments);
    }
}

function deleteItemFromTable() {
    if (deleteRequest.readyState === XMLHttpRequest.DONE) {
        if (deleteRequest.status === 200) {
            row.parentNode.removeChild(row);
        }
        else {
            row = null;
            alert("Unable to create new item. Try again after refreshing the page.");
        }
    }
}