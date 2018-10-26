// The Below Example Demostrate How To issue an ajax request to check if a system user exist with the given emil address 
// This checkURL is configured to return a list of users with the given email Address
// You Should Include Ajax_new.js file 

 
checkURL = "/SmartTool/queryResultToJSON.jsp?id=68634&seq=0&user_email="+object.value ; 
var msg= "Error During connection to Server " ; 
var sts= false ; 

var json ; 
try
{ 
    json = getJsonResponse(checkURL ); 
    if ( json.length > 0 )
   {
       msg= "This Email " + object.value + "Is Used by another system User "  ; 
       sts= false ; 
   } 
   else 
  {
      msg= "" ; 
      sts= true ; 
   }
}
catch ( err) { msg = err.message ; }

vr = {message :msg , status : sts} ;
return vr ; 
