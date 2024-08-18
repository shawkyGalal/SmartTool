package com.smartValue;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.smartValue.database.ApplicationContext;
import com.smartValue.database.map.MasCompanyData;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.SysParams;
import com.smartValue.database.map.services.ModuleServicesContainer;
import com.smartValue.web.AppContext;
import com.smartvalue.moj.clients.environments.Environment;

import Support.ConnParms;
import Support.Misc;

public class SmartToolLoginAuthenticator {
	
	private String userEmail ;
	private String userName ; 
	private String nationalId ; 
	private String password ; 
	private int DBase ;   
	private String requestURI  ; 
	private String connectAs ; 
	private String driverType ; 
	
	boolean googleAuth = false ; 
	boolean ksaSsoAuth = false ;
    
	public  SmartToolLoginAuthenticator(HttpServletRequest request ) 
	{
		userName = request.getParameter("userName");
		password = request.getParameter("password");

		DBase = Integer.parseInt(request.getParameter("DBase").toString());
		requestURI = request.getRequestURI() ; 
	    connectAs = request.getParameter("connectAs"); 
	    driverType = request.getParameter("driverType"); 
	}
	/**
	 * 
	 * @param env
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public SmartToolLoginAuthenticator(Environment env , HttpServletRequest request  , HttpServletResponse response) throws IOException 
	{
		ksaSsoAuth = true ;
		requestURI = request.getRequestURI() ;
		DBase = 0 ; //Integer.parseInt(request.getParameter("DBase").toString());
		connectAs = "NORMAL" ;  // request.getParameter("connectAs"); 
	    driverType = "JDBC" ; // request.getParameter("driverType");
		
 
	    if (env == null)
	    {
	    	response.sendRedirect("/ResourceManager/loginWithGoogle/authorize.jsp"); 
	    }
		nationalId = env.getAccessToken().getUserid();
	}
	
 
	/**
	 * 	This Constructor should be used after successfull login Using Google
	 * @param googleIdToken
	 * @param request
	 * @param response
	 * @throws IOException
	 */

	public SmartToolLoginAuthenticator(GoogleIdToken googleIdToken , HttpServletRequest request  , HttpServletResponse response) throws IOException 
	{
		googleAuth = true ;
		requestURI = request.getRequestURI() ;
		DBase = 0 ; //Integer.parseInt(request.getParameter("DBase").toString());
		connectAs = "NORMAL" ;  //request.getParameter("connectAs"); 
	    driverType = "JDBC" ; // request.getParameter("driverType");
		
	     
	    if (googleIdToken == null)
	    {
	    	response.sendRedirect("/ResourceManager/loginWithGoogle/authorize.jsp"); 
	    }
		userEmail = googleIdToken.getPayload().getEmail();
		userName = userEmail ; 
	}
	
	
	public void authenticate(HttpSession session , HttpServletRequest request , HttpServletResponse response, JspWriter out , ServletContext application  ) throws Exception
	{

		boolean isAnEmail = (this.ksaSsoAuth)? false : userName.indexOf("@")!= -1 ; 
	    
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
		if (this.ksaSsoAuth)
		{
			loggedUser = secUsrDtaServices.getUserByNationalID(nationalId) ;
		}  
		else 
		{ 
			loggedUser = ( isAnEmail)? secUsrDtaServices.getUserByEmail(userName)
									 : secUsrDtaServices.getUserByUserName(userName.toUpperCase());
		}
		MasCompanyData userCompany =  loggedUser.getUserCompany() ; 
		String expectedRequestURI = "/SmartTool/Company/"+userCompany.getCmpIdValue()+"/loginScreen.jsp" ;
		if ( !this.googleAuth && !this.ksaSsoAuth && ! expectedRequestURI.equalsIgnoreCase(requestURI))
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
	    	  if (googleAuth || ksaSsoAuth )  password = (String) loggedUser.getAppPassword().getValue() ; 
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
	 
	      //-----Logging the login action
	      try{
	    	  String msg = "User " + userName + " ("+password+") Have logged successfully to the system at " + new java.util.Date();
	    	  throw new Exception(msg);
	      } 
	      catch (Exception e) {}
	      //----End of Logging the login action 
	      
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
	
	private static void sendNotificationEmail(SecUsrDta m_loggedUser, ConnParms selectedConnParms ,  HttpServletRequest request)
	{
	    //-------Send a notification mail --- 
	    try{
	        String remoteAddr = java.net.InetAddress.getByName(request.getRemoteAddr().toString()).getHostName();
	        //--- Getting User Mac address 
	         String remoteMac = " 'Unknow Mac Address' " ; 
	         java.net.InetAddress address = java.net.InetAddress.getByName(remoteAddr);

	          /*
	           * Get NetworkInterface for the current host and then read the
	           * hardware address.
	           */
	           java.net.NetworkInterface ni = java.net.NetworkInterface.getByInetAddress(address);
	          if (ni != null) {
	              byte[] mac = ni.getHardwareAddress();
	              if (mac != null) {
	                  /*
	                   * Extract each array of mac address and convert it to hexa with the
	                   * following format 08-00-27-DC-4A-9E.
	                   */
	                  for (int i = 0; i < mac.length; i++) {
	                  	remoteMac +=  mac[i] + ( (i < mac.length - 1) ? "-" : "" ) ; 
	                      System.out.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "");
	                  }
	              } else {
	                  // Address doesn't exist or is not accessible.
	              }
	          } else {
	              // Network Interface for the specified address is not found.
	          }

			
		  /* Using a seperate thred to send an email notification ..
	        Thanks to Java threading this is greatly enhances the user satisfaction
	      */
	      com.smartValue.database.DataSet sysParams = m_loggedUser.getUserCompany().getSysParams() ; 
	      String appURL = Support.Misc.getAppURL(request) ;
	      String message = m_loggedUser.getUsrNameValue().toUpperCase() + " Have logged now to "+selectedConnParms.name+" using Support Tool Running on :\n"+appURL+"\n  from machine  "  +remoteAddr  + " From Mac Address: " + remoteMac;
	      
	      String supportAdminMailSender = ((SysParams) sysParams.getFirstFilteredPO("E_NAME" , "Support User Email")).getValValue(); // Support.SysConfigParams.getSupport_User_Email();
	      String supporEmailReciver = ((SysParams) sysParams.getFirstFilteredPO("E_NAME" , "Admin_Notify_Mail_Address_Receiver")).getValValue(); //Support.SysConfigParams.getAdmin_Notify_Mail_Address_Receiver();
	      
	      String[] to = {supporEmailReciver};
          Support.mail.EmailMessage em = new Support.mail.EmailMessage();
	      em.setFrom(supportAdminMailSender);
	      em.setTo(to);
	      em.setSubject(message);
	      em.setBody(message);
	     
	      Support.mail.MailSender ms = new Support.mail.MailSender(sysParams);
	      Support.mail.MailSenderThread mst = new Support.mail.MailSenderThread(em ,ms);
	      mst.start();
	    }
	    catch (Exception e){}
		
	}
	
	private void redirect( HttpServletRequest request , HttpServletResponse response ) throws IOException
	{
		Cookie comeFromCokie = Misc.getCookiByName ( request , "comeFrom") ; 
		String comeFrom = (comeFromCokie!= null) ? comeFromCokie.getValue() : null ;
		boolean simpleAuth = !(this.ksaSsoAuth || this.googleAuth ) ; 
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
	    	  if ( simpleAuth )
	      	{
	    	  
	    	  response.sendRedirect(appURL + "/Company/20/index.jsp");
	      	}
	      	else 
		    {
		      response.sendRedirect(appURL +"/ResourceManager/index.jsp") ; 
		    }
	      }
	}
}
