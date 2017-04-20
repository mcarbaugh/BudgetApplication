
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<div class="modal" id="newTransactionDialog">
    <div class="modal-content">
        <form id="newTransactionForm" class="modalForm">
            <span class="close" onClick="closeTransactionDialog()">&times;</span>
            <h1>New Transaction</h1>
            <label>
                <span>Name: </span>
                <input type="text" name="name" id="transactionNameInput"/>
            </label>
            <label>
                <span>Vendor: </span>
                <input type="text" name="vendor" id="transactionVendorInput"/>
            </label>
            <label>
                <span>Amount: </span>
                <input type="text" onpaste="return false" onkeypress="return isNumberKey(event)" id="transactionAmountInput"/>
            </label>
            <label>
                <span>Date: </span>
                <input type="date" onpaste="return false" id="transactionDateInput"/>
            </label>
        </form>
        <input type="text" value="0" class="hiddenField" id="transactionItemIdInput"/>
        <input type=button value="Submit" id ="NewTransactionButton"/>
        <input type="button" value="Cancel" onclick="closeTransactionDialog()"/>
    </div>
</div>