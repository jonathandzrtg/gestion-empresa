function onlyNumber(e){
    const key = window.Event ? e.which : e.keyCode;
    let inputKey = String.fromCharCode(key);
    const numbers = "0123456789";
    const specialKeys = [];
    let specialKey = false
    for(let i in specialKeys){
        if(key === specialKeys[i]){
            specialKey = true;
            break;
        }
    }
    if(numbers.indexOf(inputKey)===-1 && !specialKey){
        return false;
    }
}