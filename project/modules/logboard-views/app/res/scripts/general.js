function hideElementById(eid){
    document.getElementById(eid).style.visibility="hidden";
}
function showElementById(eid){
    document.getElementById(eid).style.visibility="visible";

}
function removeElementById(eid){
    document.getElementById(eid).style.display="none";
}

function showOrHideDOM(ele) {

    if (document.getElementById(ele).style.visibility == "visible") {
        document.getElementById(ele).style.visibility = "hidden";
    } else {
        document.getElementById(ele).style.visibility = "visible";
    }

}