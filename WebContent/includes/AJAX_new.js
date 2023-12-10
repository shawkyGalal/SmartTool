


function sendAjaxRequestToJsp(url , zoneTobeUpdated)
{
    var xhr; 
    var div = document.getElementById(zoneTobeUpdated) ;
    var isInParentDoc = false ; 
    var doc = document ; 
	if (div == null ){div = parent.document.getElementById(zoneTobeUpdated) ; if (div!=null){ doc = parent.document ;  isInParentDoc = true  } } // This is Used to search for the div outside the current iframe
	if (div == null ){div = parent.parent.document.getElementById(zoneTobeUpdated) ; if (div!=null){ doc = parent.parent.document ; isInParentDoc = true  } } // This is Used to search for the div outside the current iframe
	if (div == null ){div = parent.parent.parent.document.getElementById(zoneTobeUpdated) ; if (div!=null){ doc = parent.parent.parent.document ; isInParentDoc = true  } } // This is Used to search for the div outside the current iframe	
	if (div == null) div = document.getElementById("sysMessage") ;
	if (div == null ){alert('System Could Not find the div :  ' + zoneTobeUpdated ) ;  return ; }
    if (! isInParentDoc)
	   { div.innerHTML = "<img width = 20px height = 20px  src = '/SmartTool/images/ajaxLoading.gif' ></img> Please Wait ..."; }

	xhr = createRequestObject();
    xhr.onreadystatechange  = function()
    { 
         if(xhr.readyState  == 4)
         {
              if(xhr.status  == 200) 
              {
            	  resText = xhr.responseText.trim() ;
            	  // Consider the response as successful if it contains Green Ok
            	  if (resText.indexOf("<font color='00FF00'>Ok</font>") != -1 || zoneTobeUpdated != "sysMessage" )
            	  {
            		  div.innerHTML = resText;
            		  
            		  // run the scripts returned
            		  var x = doc.scripts.length ; 
            		  // var x = (isInParentDoc)? parent.document.scripts.length : document.scripts.length ; 
          			
	          			for ( i =0 ; i< x ; i++)
	          			{     var script = doc.scripts[i] ; // (isInParentDoc)? parent.document.scripts[i] : document.scripts[i] ;  
	          				  scrText = script.text ; 
	          				  if (div.contains (script) &&  scrText != null && scrText.trim()!= '' && scrText.indexOf("sendAjaxRequestToJsp")==-1 )
	          				  {
	          					  try {  eval( scrText ) ; }
	          					  catch (Exception ) {alert ('System is unable to execute the script ' + 'Due To Exception ' +   Exception ) }
	          				  
	          				  }
	          				   
	          			}
            		  // End of run the scripts returned	          			
            	  }
            	  else // The response Indicates an Error or a notification to the client
            		  // So Open the response in a Jquery dialoge 
            	  {
            		  document.getElementById("sysMessage").innerHTML = resText;
            		  $( "#sysMessage" ).dialog( "open" );
            	  }
              }	  
              else if(xhr.status == 404)
              {
               // Add a custom message or redirect the user to another page
               div.innerHTML = "File not found : " + url;
               $( "#sysMessage" ).dialog( "open" );
              }
              
              else 
              {   var errorMsg = "Error code " + xhr.status + " While Loading :" +url;
            	  try { div.innerHTML= errorMsg ; 
            	  $( "#sysMessage" ).dialog( "open" );}
            	  catch(exception) {alert (errorMsg) ;}
              }
              
              
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
{   var div = document.getElementById(toZone) ; 
	if (div == null) div = document.getElementById("sysMessage") ;
	
   if ( div.innerHTML != "" && toZone != "sysMessage")
   {
	   div.innerHTML = "";
   }
   else
   {
	   sendAjaxRequestToJsp(url , toZone);
   }
} 

function adjustNewValue( newValue)
{
	// replacing all single Qute with a 
	var adjustedNewvalue = newValue.split('\'').join('@@@') ; 
	// alert (adjustedNewvalue);  
	return  adjustedNewvalue ;
}


function getJsonResponse(m_url) {
	var jqXHR = $.ajax({
                url : m_url,
                async: false , 
            }); 
	var resposne = jqXHR.responseText.trim() ;
	var jsonObj = JSON.parse(resposne ) ;
	return  jsonObj ; 
}

function alerJsonInfo(m_json)
{
	alert ("No. Of Records" + m_json.length) ; 
	alert (  JSON.stringify ( m_json , null , 2) ) ; 
}