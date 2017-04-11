

function openAddBudgetDialog() {
    // close popups on the page
 
    document.getElementById("NewBudgetModal").style.display = "block";
}

function closeAddBudgetDialog() {
    document.getElementById("NewBudgetModal").style.display = "none";
    document.getElementById("NewBudgetForm").reset();
}