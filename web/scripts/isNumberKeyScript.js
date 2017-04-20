
function isNumberKey(e) {
        
    var characterCode = (e.which) ? e.which : e.code;

    // check for a decimal
    if(characterCode === 46 && e.target.value.split('.').length <= 1) {
        return true;
    }

    // check for non-numeric characters
    if(characterCode > 31 && (characterCode < 48 || characterCode > 57)) {
        return false;
    }

    return true;
}