package com.smartValue.authenticators;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.map.MasCompanyData;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.services.ModuleServicesContainer;

import Support.ConnParms;
import Support.Misc;

public class BasicAuthenticator extends Authenticator {
	
	private String userName ; 
	private String password ; 
	private int DBase ;   
	private String requestURI  ; 
	private String connectAs ; 
	private String driverType ; 
    
	public  BasicAuthenticator(HttpServletRequest request ) 
	{
		userName = request.getParameter("userName");
		password = request.getParameter("password");

		DBase = Integer.parseInt(request.getParameter("DBase").toString());
		requestURI = request.getRequestURI() ; 
	    connectAs = request.getParameter("connectAs"); 
	    driverType = request.getParameter("driverType"); 
	}
	
	public void authenticate(HttpSession session , HttpServletRequest request , HttpServletResponse response, JspWriter out , ServletContext application  ) throws Exception
	{

		Support.XMLConfigFileReader supportConfig =  Misc.getXMLConfigFileReader(false) ; 
	    Vector<ConnParms> conParms  = supportConfig.connParms ;
		java.sql.Connection  userCon = null;
		java.sql.Connection  repCon = null;
		
		
	    Support.ConnParms selectedConnParms = (Support.ConnParms)conParms.elementAt(DBase) ;
		int lang = 1 ;
	    ModuleServicesContainer msc = ApplicationContext.generateModuleServicesContainer(selectedConnParms.name , lang );  
	    repCon = msc.getReposatoryConnection() ;
	    com.smartValue.database.map.services.SecUserDataService secUsrDtaServices = msc.getSecUserDataService();
 
		SecUsrDta loggedUser  ;
		 
		loggedUser = secUsrDtaServices.getUserByUserName(userName);
		
		MasCompanyData userCompany =  loggedUser.getUserCompany() ; 
		String expectedRequestURI = "/SmartTool/Company/"+userCompany.getCmpIdValue()+"/loginScreen.jsp" ;
		if ( ! expectedRequestURI.equalsIgnoreCase(requestURI))
		{
			String appURL = "appURL Not Defined" ; 
			try{appURL = userCompany.getSysParams().getFirstFilteredPO("E_NAME", "AppURL").getAttributeValue("VAL").toString() ;}
			catch (Exception e ){}
			throw new Exception("You Are Not Allowed to Login from this page, Please Contact System Administrator or use the following ling to login :<BR>  <a href = '"+appURL+expectedRequestURI+"'>صفحة الدخول - Login Page </a>" ) ;  
		}

	    boolean useOci=false;
	    useOci = (driverType!= null &&  driverType.toString().equals("useOci"))? true:false;
	      try
	      {
	        if (useOci)
	        {
	        userCon = selectedConnParms.generateOciConnection(loggedUser.getUsrNameValue() , password ) ;  
	        }
	        else 
	        {
	    	  java.util.Locale.setDefault(java.util.Locale.ENGLISH);

	    	  if ( loggedUser.getAppPassword().getValue().equals(password))
	    	  {
	    		  userCon = selectedConnParms.generateConnection(loggedUser.getUsrNameValue() , password , connectAs ) ;  //--Generic for both JDBC or ODBC
	    	  }
	    	  else {throw new Exception("Invalid App Password : Provided Password does not match with registered password ") ; }
	        }
	        // Replace the persistent Layer connection with the logged user connection
	        msc.getDbServices().setConnection(userCon) ; 
	        userCon.setAutoCommit(false);
	        
	        session.setAttribute("con",userCon);
	        session.setAttribute(Misc.selectedConnParmsSessionKey,selectedConnParms);
	      }
	      catch (Exception e) {throw new Exception("</center>Unable to Connect to " + selectedConnParms.serviceName +"@"+selectedConnParms.server+ " <br> Due To :" + e.getMessage()+ " <br> From : " + request.getRemoteHost());}
	      //--The following Connection Used to access the LU_Queries Reposatory table 
	      
	      session.setAttribute("repCon",repCon);
	      
	      session.setAttribute("dBServerName", selectedConnParms.server);
	      session.setAttribute("dBServiceName", selectedConnParms.serviceName);
	      session.setAttribute("loggedUser" , loggedUser) ; 
	      msc.getDbServices().setLoggedUser(loggedUser);
		  session.setAttribute(Support.Misc.MscSessionKey , msc); 
      
	      //-----------------Creating a Node For the User if Does not Exist
	      try
	      { 
	    	 String loggedUserName = Misc.getConnectionUserName(userCon).toUpperCase();  // Upper Case used to MS SQL Server
	         // Create a Private Node for the User 
	         repCon.createStatement().execute("insert into support.lu_Queries (code , e_dsc , a_dsc , parent_id ) values ('"+loggedUserName+"' , '"+loggedUserName+"' , '"+loggedUserName+"' , 0)");
	         repCon.createStatement().execute("insert into support.lu_executables (code , e_dsc , a_dsc , parent_id ) values ('"+loggedUserName+"' , '"+loggedUserName+"' , '"+loggedUserName+"' , 0)");      
	      
	         // Create a "My Favofrite Node Under  Private Node "
		     String favoriteNodeInsStmt = "Insert into support.lu_Queries (code , e_dsc , a_dsc , parent_id  , query_body , header_id ) " ; 
		     favoriteNodeInsStmt += " Values ('My Favorites' , 'My Favorites' ,'My Favorites' , (Select id from support.lu_Queries Where code = '"+loggedUserName+"' and e_dsc = '"+loggedUserName+"' and a_dsc = '"+loggedUserName+"') ,"+
		    			        " '\\ My Favorite Links\n " + "@@37411[0]' , '0'  ) "  ;  
		     repCon.createStatement().execute (favoriteNodeInsStmt) ; 
		  }
	      catch (Exception e)
	      {//-----------If allready exist, do nothing 
	    	//e.printStackTrace();
	      }
 
 
	    try {
	    	  String createUserStr = "insert into icdb.sec_usr_dta (usr_name , usr_password , app_password ) values ('"+userName.toUpperCase()+"' , '"+password+"' , '"+password+"' )" ; 
	   	      repCon.createStatement().execute(createUserStr); 
	        }
	        catch (Exception e){//-----------If allready exist, do nothing 
	       	 //throw new Exception ("Unable execute " + createUserStr + " to create a sec_usr_dta entry due to " + e.getMessage()) ;
	        }

	    //-- Upon Successful Login 
	    sendNotificationEmail(loggedUser , selectedConnParms , request) ; 
	    //------------Create System tree object in the session -----------	    
	    Misc.initializeMainSystemTrees(request, response , session , application , out) ;
		
	    redirect (request , response) ; 
	}
	

	
	private void redirect( HttpServletRequest request , HttpServletResponse response ) throws IOException
	{
		Cookie comeFromCokie = Misc.getCookiByName ( request , "comeFrom") ; 
		String comeFrom = (comeFromCokie!= null) ? comeFromCokie.getValue() : null ;
		if (comeFromCokie != null && comeFrom != null && ! comeFrom.contains("mainScreen.jsp") )
	      {
	    	  comeFromCokie.setMaxAge(0);
	    	  comeFromCokie.setPath("/");
	    	  response.addCookie(comeFromCokie) ;
	    	  response.sendRedirect(comeFrom); 
	      }
	      else 
	      {	  
	    	  String appURL = Support.Misc.getAppURL(request) ;
	    	  response.sendRedirect(appURL + "/Company/20/index.jsp");
	      }
	}
}
