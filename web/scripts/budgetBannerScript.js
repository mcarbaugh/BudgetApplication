

/* When the user clicks on the button, 
toggle between hiding and showing the dropdown content */
function getMonthContainer() {
    var dropDown = document.getElementById("monthContainer");
    dropDown.scrollTop = 0;
    
    // close all other popup windows
    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
        var openDropdown = dropdowns[i];
        if (openDropdown.classList.contains('show')) {
            openDropdown.classList.remove('show');
        }
    }
    
    document.getElementById("monthContainer").classList.toggle("show");
}

function getUserInfoContainer() {
    //reset month modal position (bug fix)
    var dropDown = document.getElementById("monthContainer");
    dropDown.scrollTop = 0;
    
    // close all other popup windows
    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
        var openDropdown = dropdowns[i];
        if (openDropdown.classList.contains('show')) {
            openDropdown.classList.remove('show');
        }
    }
    
    document.getElementById("userInfoContainer").classList.toggle("show");
}