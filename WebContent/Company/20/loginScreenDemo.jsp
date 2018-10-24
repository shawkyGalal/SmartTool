<!DOCTYPE html>
<%@ page errorPage="../../errorPage.jsp" %>
<%@page  language="java" contentType="text/html;charset=UTF-8"%>
<%@page import="com.implex.database.map.services.* , com.implex.database.map.SecUsrDta, Support.Misc" %>
<%request.setCharacterEncoding("UTF-8");%>
<% String appURL = Support.Misc.getAppURL(request) ;  %>


<%@page import="com.implex.database.map.SysParams"%>
<%@page  contentType="text/html;charset=UTF-8"%>

<html lang="en" class="coming-soon">
  <head>
    <meta charset="utf-8">
    <title>EPM System</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta name="description" content="">
    <meta name="author" content="KaijuThemes">
	<!-- <link href='http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700' rel='stylesheet' type='text/css'>  -->
    <!-- <link type="text/css" href="assets/plugins/iCheck/skins/minimal/blue.css" rel="stylesheet"> -->
    <link type="text/css" href="assets/fonts/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link type="text/css" href="assets/fonts/ionicons/css/ionicons.min.css" rel="stylesheet">                <!-- Ion Icons -->
    <link type="text/css" href="assets/css/styles.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries. Placeholdr.js enables the placeholder attribute -->
    <!--[if lt IE 9]>
    <link type="text/css" href="assets/css/ie8.css" rel="stylesheet">
    <script type="text/javascript" src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- The following CSS are included as plugins and can be removed if unused-->
    
  </head>
  <body class="coming-soon rtl">
   <% 
	Cookie loginPageCookie  = Misc.getCookiByName(request , Misc.LoginScreenPageNameCookiVarName) ; 
	if (loginPageCookie == null )
	{ 
		loginPageCookie = new Cookie (Misc.LoginScreenPageNameCookiVarName ,null );
	}
	 loginPageCookie.setValue("Company/20/loginScreenDemo.jsp") ;
	 loginPageCookie.setMaxAge(24*60*60);
	 loginPageCookie.setPath("/");
	 response.addCookie(loginPageCookie);

	String defaultEnv =  "EPM_DEMO" ; //"GIHAZ_GP_PRIOD" ;; 
	String reportTopOpen = "37428" ; 
     Support.XMLConfigFileReader supportConfig =  Misc.getXMLConfigFileReader(false) ; 
     java.util.Vector conParms  = supportConfig.connParms ;
    //String comeFrom = request.getParameter("comeFrom")
  request.setCharacterEncoding("UTF-8");
  java.sql.Connection  con = null;
  java.sql.Connection  repCon = null;
  Object conObj=  session.getAttribute("con"); 
  Object repConObj=  session.getAttribute("repCon"); 
  con = (java.sql.Connection)conObj;
//-----------Closing and Removing the main Connection---------
  if (conObj != null )
     { 
      try
      {
    	session.removeAttribute("con");
        session.removeAttribute("repCon");
        session.invalidate();
        try { Support.UserUnCommitedTransactions.rollBackConnection(con , session ) ;} catch(Exception e ){}
        con.close();
      }
      catch (Exception e ){}
     }
//-----------Closing and Removing the Reposatory Connection---------
  if (repConObj != null )
     { 
      try
      {
    	repCon = (java.sql.Connection)repConObj;
        try {repCon.rollback();} catch (Exception e ) {};
        repCon.close();
        
      }
      catch (Exception e ){e.printStackTrace(); }
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
        session.setAttribute(Misc.selectedConnParmsSessionKey,selectedConnParms);
      }
      catch (Exception e) {throw new Exception("</center>Unable to Connect to " + selectedConnParms.serviceName +"@"+selectedConnParms.server+ " <br> Due To :" + e.getMessage()+ " <br> From : " + request.getRemoteHost());}
      //--The following Connection Used to access the LU_Queries Reposatory table 
      
      try{
      repCon = selectedConnParms.generateConnection("ICDB" , "demo123" , connectAs ) ; 
            //= supportConfig.reposatoryConn.generateConnection();
      session.setAttribute("repCon",repCon);
      }
      catch (Exception e) 
      {
    	con.close(); 
       throw new Exception("Unable to Create a Connection to the Query Reposatory Due To : " + e.getMessage()+ "  from " + request.getRemoteHost());
      }
 
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
        try {
        	  // Create a Private Node for the User 
        	  repCon.createStatement().execute("insert into support.lu_Queries (code , e_dsc , a_dsc , parent_id ) values ('"+loggedUser+"' , '"+loggedUser+"' , '"+loggedUser+"' , 0)");
        	      
        } 
        catch  (Exception e){//-----------If allready exist, do nothing
        		//e.printStackTrace();
                         }
        try {// Create a "My Favofrite Node Under  Private Node "
      	  String favoriteNodeInsStmt = "Insert into support.lu_Queries (code , e_dsc , a_dsc , parent_id  , query_body , header_id ) " ; 
    	  favoriteNodeInsStmt += " Values ('My Favorites' , 'My Favorites' ,'My Favorites' , (Select id from support.lu_Queries Where code = '"+loggedUser+"' and e_dsc = '"+loggedUser+"' and a_dsc = '"+loggedUser+"') ,"+
    			        " '\\ My Favorite Links\n " + "@@37411[0]' , '0'  ) "  ;  
    	  repCon.createStatement().execute (favoriteNodeInsStmt) ; }
        catch (Exception e) {}
        
        repCon.createStatement().execute("insert into support.lu_executables (code , e_dsc , a_dsc , parent_id ) values ('"+loggedUser+"' , '"+loggedUser+"' , '"+loggedUser+"' , 0)");      
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
	ModuleServicesContainer msc = Support.Misc.getModuleServiceContainer(selectedConnParms.name , lang );  
	session.setAttribute(Support.Misc.MscSessionKey , msc); 

	com.implex.database.map.services.SecUserDataService secUsrDtaServices = msc.getSecUserDataService();
	
	SecUsrDta loggedUser = secUsrDtaServices.getUserByUserName(userName.toUpperCase());
	if ( loggedUser == null ) { throw new Exception ("User " + userName.toUpperCase() + " Does Not Exist"); }
	session.setAttribute("loggedUser" , loggedUser) ; 
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
      com.implex.database.DataSet sysParams = loggedUser.getUserCompany().getSysParams() ; 
      String message = userName.toUpperCase() + " Have logged now to "+selectedConnParms.name+" using Support Tool from machine  "  +remoteAddr  + " From Mac Address: " + remoteMac;
      
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
  
	Misc.initializeMainSystemTrees(request, response , session , application , out) ;
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
      {response.sendRedirect(appURL + "/Company/20/index.jsp");}
  }
   %> 
    
<div class="container" id="login-form">
	
	<div class="row">
		<div class="col-md-4 col-md-offset-4">

            <div class="text-center titlehomecom">
                <h3> نظام مراقبة و متابعة الأداء الإستراتيجى للمؤسسات </h3>
                <a href="#" class="logostock"><img src="assets/img/smart-value.bmp"></a>
                <span>Enterprise Performance Management System (EPM)  </span>
                <span> Demo And Evaluation Version  </span>
                
            </div>

           
			<div class="panel panel-default alt with-footer">
				<div class="panel-heading"><h2>تسجيل دخول</h2></div>
				<div class="panel-body">
					
					<form action="" class="" id="validate-form">
						<div class="form-group">

							<div class="input-group">							
								<span class="input-group-addon">
									<i class="ion-android-person"></i>
								</span>
								<input type="text" class="form-control" placeholder="أسم المستخدم" name = "userName" data-parsley-minlength="6" placeholder="At least 6 characters" required>
							</div>

						</div>

						<div class="form-group">

							<div class="input-group">
								<span class="input-group-addon">
									<i class="ion-android-unlock"></i>
								</span>
								<input type="password" class="form-control" id="exampleInputPassword1" name = "password"  placeholder="كلمة السر">
							</div>

						</div>

                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="ion-ios-briefcase"></i>
                                </span>
                                <select name="DBase" id="DBase"  class="form-control" disabled="disabled">
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
                                <input type="hidden" name="connectAs" value="NORMAL" ></input>  
                            </div>
                        </div>

						<div class="form-group">

							<div class="checkbox block">
								<input type="checkbox" class="tectonic" id="rememberme1" value="1" name="ham">
								<label for="rememberme1" class="pl-n"></label>
								<span>تذكرنى</span>
							</div>

						</div>

                        <div class="form-group">

                            <input type="submit" class="btn btn-primary pull-right" name="login" title="دخول" value="دخول" />


                        </div>

						<div class="form-group">
							<a href="extras-forgotpassword.html" class="pull-left text-small">نسيت كلمة السر ؟</a>
						</div>
												
					</form>
				</div>
				
			</div>

            <div class="text-center linkslogin">
                <a href="#">دليل المستخدم</a>
                <a href="#">مساعدة</a>
            </div>

			
		</div>
	</div>
</div>


      <div class="footerfix">

          <img src="assets/img/smart-value.bmp" style="width: 49px; height: 23px"/> 
          Copyright © 1998,2017
          <img src="images/HuloolLogo.png" /> 

      </div>


    <!-- Load site level scripts -->

<script type="text/javascript" src="assets/js/jquery-1.10.2.min.js"></script> 							<!-- Load jQuery -->
<script type="text/javascript" src="assets/js/jqueryui-1.9.2.min.js"></script> 							<!-- Load jQueryUI -->

<script type="text/javascript" src="assets/js/bootstrap.min.js"></script> 								<!-- Load Bootstrap -->

<!-- End loading site level scripts -->
    <!-- Load page level scripts-->
    

    <!-- End loading page level scripts-->
  </body>
</html>