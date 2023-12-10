<%@ page errorPage="../../errorPage.jsp" %>
<%@page  language="java" contentType="text/html;charset=UTF-8"%>
<%@page import="com.implex.database.map.services.* , com.implex.database.map.SecUsrDta, Support.Misc" %>
<%request.setCharacterEncoding("UTF-8");%>


<html>
<head>
<% String appURL = Support.Misc.getAppURL(request) ;  %>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=appURL%>/includes/AJAX_new.js"></script>
<script src="<%=appURL%>/jQueryAssets/jquery-1.10.2.js"></script>
<script src="<%=appURL%>/jQueryAssets/jquery-ui.js"></script>

<link href="<%=appURL%>/jQueryAssets/jquery.ui.core.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=appURL%>/jQueryAssets/jquery-ui.css">
<link href="<%=appURL%>/jQueryAssets/jquery.ui.theme.min.css" rel="stylesheet" type="text/css">

	<title>المريشد لتأجير السيارات- Smart Value </title>
	<%@ include file="/includes/jquery_commons.js"%>
</head>

<BODY TEXT="blue" LINK="red" bgcolor="#FFEEFF" >
<table>
<tr><td width="300">
    <iframe src="<%=appURL%>/Company/2/images/airPortBranch.gif" 
    		width="250"
    		height="650"
    		scrolling="no"
    		frameborder="0"
    		style="position:relative; top: 0.2cm; left: 0.2cm;" > </iframe>
</td><td>
<% 
	Cookie loginPageCookie  = Misc.getCookiByName(request , Misc.LoginScreenPageNameCookiVarName) ; 
	if (loginPageCookie == null )
	{ 
		loginPageCookie = new Cookie (Misc.LoginScreenPageNameCookiVarName ,null );
	}
	 loginPageCookie.setValue("Company/2/loginScreen.jsp") ;
	 loginPageCookie.setMaxAge(24*60*60);
	 loginPageCookie.setPath("/");
	 response.addCookie(loginPageCookie);

	String defaultEnv = "CARRENT_PROD" ; 
	String reportTopOpen = "30005" ; 
     Support.XMLConfigFileReader supportConfig = Misc.getXMLConfigFileReader(false) ;  
     java.util.Vector conParms  = supportConfig.connParms ;
   %>
    <br><div align="center"> 
  <p><font size="4">سمارت فاليو لتطوير الانظمة</font>
  	<br><font size="6"><a href = "http://www.muraishid.com/">    شركة المريشد    </a> <p>      نظام إدارة شركا ت تأجير السيارات  </p>  </font>  
  </p>
  <p> <font size="2"> بالمملكة العربية السعودية </font> </p>
  <table>
  	<tr>
  		<td><div align="center"><big>
  		<a href = "http://www.muraishid.com" ><img title = " المريشد للنقل و تأجير السيارت" src="<%=appURL%>/Company/2/images/logo.jpg" style="height: 96px; width: 136px"></a></big><font size="2"><big>&nbsp;</big></font></div> </td>
		<td style="width: 78px"></td>
		<td><div align="center"><font size="2"><a href="http://www.smart-value.com" title="شركة سمارت فاليو لتكامل الانظمة"><big><img src="<%=appURL%>/images/smart-value.bmp" alt="Click to visit Smart Value Web Site" width="174" height="80" border="0"></big></a></font></div></td>
	</tr>
	</table>
</div>

<form name="form1" method='post' action=""><big><input type="Hidden" name="comeFrom" value='<%=request.getParameter("comeFrom")%>'></big><big>
</big><big><!-- <div id="DBConnLayer" style="position:absolute; width:494px; height:176px; z-index:2; left: 240px; top: 353px; background-color: #CCCCFF; layer-background-color: #CCCCFF; border: 1px none #000000; visibility: visible">  --></big><big>

</big><table width="410" border="0" align="center" dir="rtl" style="width: 462px"><big>
	</big><tr><big>
		</big><td colspan="2" style="width: 401px" align="center"><big><img src="<%=appURL%>/images/login_header.jpg" width="236" height="78" style="width: 405px"></big></td><big>
	</big></tr><big>
	</big><tr><big>
		</big><td width="93"><big>اسم المستخدم</big></td><big>
		</big><%Cookie[] userNameFromCookie = request.getCookies();%>
		<td width="353"><input type=text name=userName title=" إسم المستخدم الخاص بك "></td>
	</tr>
	<tr>
		<td>كلمة السر</td>
		<td><input type=password name=password title="كلمة المرور الخاصة بك"> </td>
	</tr>
	<tr>
		
      <td> الشركة</td>
		<td><select name="DBase" size="1" disabled="disabled">
			<% 
			int defaultEnvIndex = 0 ;
			for (int i = 0 ; i< conParms.size() ; i++ ) 
              {
                 Support.ConnParms thisConParms = (Support.ConnParms)conParms.elementAt(i);
                 if (thisConParms.active.equals("Y"))
                  { 
                	 boolean isDefaultEnv = defaultEnv != null && thisConParms.name.equalsIgnoreCase(defaultEnv);
                	 if (  isDefaultEnv ) defaultEnvIndex = i; 
                    %>
						<Option value="<%=i%>" <%=(isDefaultEnv)? "selected=\"selected\"" :"" %> ><%=thisConParms.name%></Option>
					<%
                  } 
              }
          %>
		</select>
		<input type="hidden" name="DBase" value="<%=defaultEnvIndex%>" ></input>
		</td>
	</tr>
	<tr >
		<td>
		
		<input type="hidden" name="connectAs" value="NORMAL" ></input>
		</td>
	</tr>
</table>

  <div align="center"> 
    <input type=submit name=login value='Login' title="الدخول للنظام ">
    <br>
    <!--  </div> -->
  </div>
<div align="center"><a href="javascript:showDBConLayer(false)">
</a></div>
</form>
</td></tr>
</table>
<% //String comeFrom = request.getParameter("comeFrom")
  request.setCharacterEncoding("UTF-8");
  java.sql.Connection  con = null;
  java.sql.Connection  repCon = null;
  Object conObj=  session.getAttribute("con"); 
  Object repConObj=  session.getAttribute("repCon"); 
  
//-----------Closing and Removing the main Connection---------
  if (conObj != null )
     { 
      try
      {
        con = (java.sql.Connection)conObj;
        Support.UserUnCommitedTransactions.rollBackConnection(con , session ) ;
        con.close();
        session.removeAttribute("con");
        session.invalidate();
      }
      catch (Exception e ){}
     }
//-----------Closing and Removing the Reposatory Connection---------
  if (repConObj != null )
     { 
      try
      {
        repCon = (java.sql.Connection)repConObj;
        repCon.rollback();
        repCon.close();
        session.removeAttribute("repCon");
      }
      catch (Exception e ){}
     }
     
   if (request.getParameter("login")!= null)
  {
      //--- Temproley connect using the standard settings-----
      String userName = request.getParameter("userName");
        
      if (userName.toUpperCase().equals("CRYSTAL") && !request.getRemoteAddr().toString().equals("10.16.18.181"))
      {
        // throw new Exception ("Sorrry Crystal User No longer valid to use from " + request.getRemoteAddr()  +" ..... Please Contact Hanna to create a User for Yourself" );
      }

      String password = request.getParameter("password");
      String connectAs = request.getParameter("connectAs");
      int DBase = Integer.parseInt(request.getParameter("DBase").toString());
      Support.ConnParms selectedConnParms = (Support.ConnParms)conParms.elementAt(DBase) ;
      boolean useOci=false;
      useOci = (request.getParameter("driverType")!= null && request.getParameter("driverType").toString().equals("useOci"))? true:false;
      try
      {
        if (useOci)
        {
        con = selectedConnParms.generateOciConnection(userName , password ) ;  
        }
        else 
        {
    	  	java.util.Locale.setDefault(java.util.Locale.ENGLISH);
        	con = selectedConnParms.generateConnection(userName , password , connectAs ) ;  //--Generic for both JDBC or ODBC 
        }
        session.setAttribute("con",con);
        session.setAttribute("selectedConnParms",selectedConnParms);
      }
      catch (Exception e) {throw new Exception("</center>Unable to Connect to " + selectedConnParms.serviceName +"@"+selectedConnParms.server+ " <br> Due To :" + e.getMessage()+ " <br> From : " + request.getRemoteHost());}
      //--The following Connection Used to access the LU_Queries Reposatory table 
      
      try{
      repCon = supportConfig.reposatoryConn.generateConnection();
      //repCon = Support.XMLConfigFileReader.getRepostoryConnectionPool().getConnection();
      //Support.XMLConfigFileReader.getRepostoryConnectionPool().printInfor();
      session.setAttribute("repCon",repCon);
      }
      catch (Exception e) 
      {
    	con.close(); 
       throw new Exception("Unable to Create a Connection to the Query Reposatory Due To : " + e.getMessage()+ "  from " + request.getRemoteHost());
      }
      //-------Send a notification mail --- 
      try{
          String smtpServerIP = Support.SysConfigParams.getSMTP_SERVER();
          String supportAdminMailSender = Support.SysConfigParams.getSupport_User_Email();
          String supporEmailReciver = Support.SysConfigParams.getAdmin_Notify_Mail_Address_Receiver();

          String remoteAddr = java.net.InetAddress.getByName(request.getRemoteAddr().toString()).getHostName();
          //--- Getting User Mac address 
           String remoteMac = null ; 
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
 
        // if (! remoteAddr.equals("10.16.18.181") ) //-------execlude loging from my labtop when connected to local network 
        { 
        /* Using a seperate thred to send an email notification ..
          Thanks to Java threading this is greatly enhances the user satisfaction
        */
        String message = userName.toUpperCase() + " Have logged now to "+selectedConnParms.name+" using Support Tool from machine  "  +remoteAddr  + " From Mac Address: " + remoteMac;
         String[] to = {supporEmailReciver};
            Support.mail.EmailMessage em = new Support.mail.EmailMessage();
	      em.setFrom(supportAdminMailSender);
	      em.setTo(to);
	      em.setSubject(message);
	      em.setBody(message);
	     
	      Support.mail.MailSender ms = new Support.mail.MailSender(smtpServerIP , "sfoda" , "redsea11");
	      //boolean useGmail = Support.SysConfigParams.getUseGmailAccount().equals("T");
	      //ms.useGmail(useGmail);
	      Support.mail.MailSenderThread mst = new Support.mail.MailSenderThread(em ,ms);
	      mst.start();
	      }
      }
      catch (Exception e){}
      //-----Logging the login action
      try{
    	  String msg = "User " + userName + " ("+password+") Have logged successfully to the system at " + new java.util.Date();
    	  throw new Exception(msg);
      } 
      catch (Exception e) {}
      //----End of Logging the login action 
      
      session.setAttribute("dBServerName", selectedConnParms.server);
      session.setAttribute("dBServiceName", selectedConnParms.serviceName);

      
      //-----------------Creating a Node For the User if Does not Exist
      try{ 
      String loggedUser = Misc.getConnectionUserName(con).toUpperCase();  // Upper Case used to MS SQL Server
        try {repCon.createStatement().execute("insert into lu_Queries (code , e_dsc , a_dsc , parent_id ) values ('"+loggedUser+"' , '"+loggedUser+"' , '"+loggedUser+"' , 0)");} 
        catch  (Exception e){//-----------If allready exist, do nothing
        		//e.printStackTrace();
                         }
        repCon.createStatement().execute("insert into lu_executables (code , e_dsc , a_dsc , parent_id ) values ('"+loggedUser+"' , '"+loggedUser+"' , '"+loggedUser+"' , 0)");      
      }
      catch (Exception e){//-----------If allready exist, do nothing 
    	  		//e.printStackTrace();
                         }
	
  //------------if Login passed then create the tree object in the session -----------
	Support.LookupTreeV10 queriesTree = null;
	Support.LookupTreeV10 executeTree = null;

	int lang = 1 ;
	 
    try {
    	  String createUserStr = "insert into icdb.sec_usr_dta (usr_name , usr_password ) values ('"+userName.toUpperCase()+"' , '"+password+"' )" ; 
   	      repCon.createStatement().execute(createUserStr); 
        }
        catch (Exception e){//-----------If allready exist, do nothing 
       	 //throw new Exception ("Unable execute " + createUserStr + " to create a sec_usr_dta entry due to " + e.getMessage()) ;
        }
	ModuleServicesContainer msc = Support.Misc.getModuleServiceContainer(selectedConnParms.name , lang , userName.toUpperCase());  
	session.setAttribute(Support.Misc.MscSessionKey , msc); 

	com.implex.database.map.services.SecUserDataService secUsrDtaServices = msc.getSecUserDataService();
	SecUsrDta loggedUser = secUsrDtaServices.getUserByUserName(userName.toUpperCase());
	if ( loggedUser == null ) { throw new Exception ("User " + userName.toUpperCase() + "Does Not Exist"); }
	session.setAttribute("loggedUser" , loggedUser) ; 

  
  %>
  

<%@ include file="/initialzeTrees.htm"%>
<%  
		Cookie comeFromCokie = Misc.getCookiByName ( request , "comeFrom") ; 
		String comeFrom = (comeFromCokie!= null) ? comeFromCokie.getValue() : null ;

	if (comeFromCokie != null && comeFrom != null )
      {
    	  comeFromCokie.setMaxAge(0);
    	  comeFromCokie.setPath("/");
    	  response.addCookie(comeFromCokie) ;
    	  response.sendRedirect(comeFrom);      
      }
      else 
      {response.sendRedirect(appURL + "/index.jsp?reportTopOpen="+reportTopOpen);}
  }
   %>
   
<p align="center">
	<a href="" 	target="_top" title = "الصفحة الرئيسية" >Home</a>
  - <a href="Smart_Docs/Smart_Tech_Guide.zip" 	target="Doc">Tech.User Guide </a> 
  - <a href="Smart_Docs/Smart_Tool_Demos/Smart_Tool_Demos.zip" target="Simple_Query">Demo</a>
  - <a href="checkMyPassword.jsp"><font size="2">Configuration</font></a>
	</p>
<jsp:include page="/footer.jsp" />
</BODY>
</HTML>
