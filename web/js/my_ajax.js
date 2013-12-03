//ตั้งชือว่า controllerId เอาไปลงที่ id content
function load(controlId , url){
    var xhr = getXHRObject();
    
    if(xhr != null){
        xhr.open("get",url);
        xhr.onreadystatechange = function(){
            if(xhr.status == 200){
                if(xhr.readySate == 4){
                  getControlId(control).innerHTML=xhr.responseText;
                }
            }
        }
        xhr.send(null);
    }
}

function getXHRobject(){
    
    if(window.ActiveXObject){
        return ActiveXObject("MICROSOFT.XMLHTTP");
    }else if(window.XMLHttpRequest){
        return new XMLHttpRequest();
    }
    return null;
}
function getControlId(id){
    return document.getElementById(id);
    
}