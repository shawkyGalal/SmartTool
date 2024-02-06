
function sendAjaxRequestToJsp(url , zoneTobeUpdated)
{
	alert ('xxxxxxxxx') ;
    var xhr; 
    xhr = createRequestObject();
    xhr.onreadystatechange  = function()
    { 
         if(xhr.readyState  == 4)
         {
              if(xhr.status  == 200) 
                  //document.ajax.dyn.value="Received:"  + xhr.responseText; 
                  document.getElementById(zoneTobeUpdated).innerHTML = xhr.responseText;

              else 
                 //document.ajax.dyn.value="Error code " + xhr.status;
                 document.getElementById(zoneTobeUpdated).innerHTML= "Error code " + xhr.status;
         }
    }; 
   xhr.open("GET", url,  true); 
   xhr.send(null); 
}

function createRequestObject() {
    var tmpXmlHttpObject;
    
    try {  tmpXmlHttpObject = new ActiveXObject('Msxml2.XMLHTTP');   }
    catch (e) 
    {
        try {   tmpXmlHttpObject = new ActiveXObject('Microsoft.XMLHTTP');    }
        catch (e2) 
        {
          try {  tmpXmlHttpObject = new XMLHttpRequest();     }
          catch (e3) {  tmpXmlHttpObject = false;   }
        }
     }
    return tmpXmlHttpObject;
}

function loadDetails(url , toZone)
{   
   if ( document.getElementById(toZone).innerHTML != "")
   {
    document.getElementById(toZone).innerHTML = "";
   }
   else
   {
	   sendAjaxRequestToJsp(url , toZone);
   }
} 

