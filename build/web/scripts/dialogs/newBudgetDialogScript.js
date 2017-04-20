

function openAddBudgetDialog() {
    // close popups on the page
 
    document.getElementById("newBudgetDialog").style.display = "block";
}

function closeAddBudgetDialog() {
    document.getElementById("newBudgetDialog").style.display = "none";
    document.getElementById("newBudgetDialog").reset();
}