
function openNewHousingItemDialog() {
    // close popups on the page
 
    document.getElementById("newHousingItemDialog").style.display = "block";
}

function closeNewHousingItemDialog() {
    document.getElementById("newHousingItemDialog").style.display = "none";
    document.getElementById("newHousingItemForm").reset();
}

