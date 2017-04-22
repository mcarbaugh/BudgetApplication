
window.onclick = function(event) {
    
    // Close popup windows and reset their scroll position
    var dropDown = document.getElementById("monthContainer");
    dropDown.scrollTop = 0;
    
    if (!event.target.matches('.dropbtn')) {
        var dropdowns = document.getElementsByClassName("dropdown-content");
        var i;
        for (i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
};