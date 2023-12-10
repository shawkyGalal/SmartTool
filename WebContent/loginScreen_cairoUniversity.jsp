<%@ page errorPage="errorPage.jsp"
	contentType="text/html;charset=UTF-8"%>
<HTML>
<title>DB Web Monitor- Smart Way Integrated Solutions</title>
<BODY TEXT="blue" LINK="red" bgcolor="#FFEEFF">
<% 
//--Read System Configuration params

//-- Store System Configuration params 
     //Support.LookupTreeV10 xx = new Support.LookupTreeV10();
     Support.XMLConfigFileReader supportConfig =  Misc.getXMLConfigFileReader(false) ; 
     java.util.Vector conParms  = supportConfig.connParms ;
    %>
<div align="right"></div>
<p align="center">ذكر اليوم : لاالله الا الله العظيم الحليم-لا الله
الا الله رب العرش العظيم- لا الله الا الله رب السموات و رب الارض و رب
العرش الكريم</p>
<p align="center"><a href="www.smart-value.com"><img
	src="images/Higher_Education.JPG"
	alt="اضغط لزيارة المجلس الاعلى للجامعات" width="181" height="83"
	border="0" style="height: 137px; width: 229px"></a></p>
<div align="center">
<p><strong><font size="5">System Monitoring And
Recovery Tool (SMART) </font></strong><br>
<strong><em><font size="5">نظم إدارة المعلومات -
كلية العلاج الطبيعى- جامعة القاهرة</font></em></strong></p>
<table width="69%" border="0">
	<tr>
		<td>
		<div align="center"><img border="2"
			src="images/<%=Support.SysConfigParams.getCompanyLogo()%>"
			width="250" height="120"><font size="2">&nbsp;</font></div>
		</td>
		<td>
		<div align="center"><font size="2"><a
			href="http://www.smart-value.com"><img
			src="images/Smart-Value.JPG"
			alt="Click to visit Smart Value Web Site" width="204" height="89"
			border="0"></a></font></div>
		</td>
	</tr>
</table>
</div>
<p align="center"><strong><font size="5"><a
	href="checkMyPassword.jsp"><font size="2">(Configuration)</font></a></font></strong></p>
<form name="form1" method='post' action="loginScreen.jsp"><input
	type=Hidden name=comeFrom value='<%=request.getParameter("comeFrom")%>'>
<!-- <div id="DBConnLayer" style="position:absolute; width:494px; height:176px; z-index:2; left: 240px; top: 353px; background-color: #CCCCFF; layer-background-color: #CCCCFF; border: 1px none #000000; visibility: visible">  -->

<table width="364" border="1" align="center">
	<tr>
		<td width="111">User Name</td>
		<%Cookie[] userNameFromCookie = request.getCookies();%>
		<td width="144"><input type=text name=userName></td>
	</tr>
	<tr>
		<td>Password</td>
		<td><input type=password name=password></td>
	</tr>
	<tr>
		<td>DB Environment</td>
		<td><select name="DBase" size="1">
			<% for (int i = 0 ; i< conParms.size() ; i++ ) 
              {
                 Support.ConnParms thisConParms = (Support.ConnParms)conParms.elementAt(i);
                 if (thisConParms.active.equals("Y"))
                  { 
                    %>
			<Option value="<%=i%>"><%=thisConParms.name%></Option>
			<%
                  } 
              }
          %>
		</select></td>
	</tr>
	<tr>
		<td>Connect As</td>
		<td><select name="connectAs">
			<option value="NORMAL" selected>NORMAL</option>
			<option value="SYSDBA">SYSDBA</option>
			<option value="SYSOPER">SYSOPER</option>
		</select></td>
	</tr>
</table>
<table width="800" border="1">

</table>
<div align="center">
<p><input name="driverType" type="radio" value="useThin" checked>
Use Thin JDBC Driver <input type="radio" name="driverType"
	value="useOci"> Use OCI JDBC driver</p>
<p><input type=submit name=login value='Login'> <br>
<small><small>For OCI ensure that you have Tnsnames
entry exactlt as DB Service Name </small></small></p>
</div>
<!--  </div> -->
<div align="center"><a href="javascript:showDBConLayer(false)">
</a></div>
</form>
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
        session.removeAttribute("SmartToolAdmin");
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
          con = selectedConnParms.generateConnection(userName , password , connectAs ) ;  //--Generic for both JDBC or ODBC 
        }
        session.setAttribute("con",con);
        session.setAttribute("selectedConnParms",selectedConnParms);
      }
      catch (Exception e) {throw new Exception("</center>Unable to Connect to " + selectedConnParms.serviceName +"@"+selectedConnParms.server+ " <br> Due To :" + e.getMessage()+ " <br> From : " + request.getRemoteHost());}
      //--The following Connection Used to access the LU_Queries Reposatory table 
      
      try{
      repCon = supportConfig.reposatoryConn.generateConnection();
      session.setAttribute("repCon",repCon);
      }
      catch (Exception e) 
      {con.close(); 
       throw new Exception("Unable to Create a Connection to the Query Reposatory Due To : " + e.getMessage()+ "  from " + request.getRemoteHost());
      }
      //-------Send a notification mail --- 
      try{
          //Support.SysConfigParams sysConfigParams = new Support.SysConfigParams(repCon);
          //Support.Misc msc = new Support.Misc(repCon);
          //String smtpServerIP = msc.getSystemParameterValue(2);
          //String supportAdminMail = msc.getSystemParameterValue(7); 
          String smtpServerIP = Support.SysConfigParams.getSMTP_SERVER();
          String supportAdminMailSender = Support.SysConfigParams.getSupport_User_Email();
          String supporEmailReciver = Support.SysConfigParams.getAdmin_Notify_Mail_Address_Receiver();

          String remoteAddr = java.net.InetAddress.getByName(request.getRemoteAddr().toString()).getHostName(); 
 
        // if (! remoteAddr.equals("10.16.18.181") ) //-------execlude loging from my labtop when connected to local network 
        { 
        /* Using a seperate thred to send an email notification ..
          Thanks to Java threading this is greatly enhances the user satisfaction
        */
         String message = userName.toUpperCase() + " Have logged now to "+selectedConnParms.name+" using Support Tool from machine  "  +remoteAddr ;
         String[] to = {supporEmailReciver};
            Support.mail.EmailMessage em = new Support.mail.EmailMessage();
      em.setFrom(supportAdminMailSender);
      em.setTo(to);
      em.setSubject(message);
      em.setBody(message);
     
      Support.mail.MailSender ms = new Support.mail.MailSender(smtpServerIP , "sfoda" , "foda");
      boolean useGmail = Support.SysConfigParams.getUseGmailAccount().equals("Y");
      ms.useGmail(useGmail);
      Support.mail.MailSenderThread mst = new Support.mail.MailSenderThread(em ,ms);
      mst.start();
         //Support.MailSender ms = new Support.MailSender(smtpServerIP);
         //ms.sendMail(supportAdminMailSender,to,null,message , message );
        }
      }
      catch (Exception e){}
      //-----Logging the login action
      try{
      throw new Exception("User " + userName + " ("+password+") Have logged successfully to the system at " + new java.util.Date());
      } 
      catch (Exception e) {}
      //----End of Logging the login action 
      
      session.setAttribute("dBServerName", selectedConnParms.server);
      session.setAttribute("dBServiceName", selectedConnParms.serviceName);

      
      //-----------------Creating a Node For the User if Does not Exist
      try{ 
      String loggedUser = Support.Misc.getConnectionUserName(con).toUpperCase();  // Upper Case used to MS SQL Server
        try {repCon.createStatement().execute("insert into lu_Queries (code , e_dsc , a_dsc , parent_id ) values ('"+loggedUser+"' , '"+loggedUser+"' , '"+loggedUser+"' , 0)");} 
        catch  (Exception e){//-----------If allready exist, do nothing 
                         }
        repCon.createStatement().execute("insert into lu_executables (code , e_dsc , a_dsc , parent_id ) values ('"+loggedUser+"' , '"+loggedUser+"' , '"+loggedUser+"' , 0)");      
      }
      catch (Exception e){//-----------If allready exist, do nothing 
                         }
     //--- Move Standard Node to Shawky Node if date after expiry date---------
      /*
      java.util.Date today = new java.util.Date();
      Support.DateHelper dh = new Support.DateHelper();
      java.util.Date expireDate = dh.getDate(2008, 12 , 15);
      if (today.after(expireDate))
      {
        try 
        {
         String sqlStmt = "";
         String message = "";
         if (con.getMetaData().getUserName().equals("IDI") 
              || con.getMetaData().getUserName().equals("SFODA")
            )
         {
          sqlStmt =  "update lu_queries set parent_id = 0 where id = 305";
          message = " Standard Node Back again to it is Normal Working Position";
         }
         else
         {
          sqlStmt = "update lu_queries set parent_id = 3634 where id = 305";
          message = "'Standrd' Node of the Support Tree Moved Under 'Shawky' Node";
         }
         repCon.createStatement().execute(sqlStmt);

         try
         {
          String smtpServerIP = Support.SysConfigParams.getSMTP_SERVER();// msc.getSystemParameterValue(2);
          String Admin_Notify_Mail_Address_Receiver = Support.SysConfigParams.getAdmin_Notify_Mail_Address_Receiver();
          String mailSender = Support.SysConfigParams.getSupport_User_Email();          
          Support.MailSender ms = new Support.MailSender(smtpServerIP);
          String[] to = {Admin_Notify_Mail_Address_Receiver, "foda_sh@hotmail.com"};
          ms.sendMail(mailSender,to,null,message , message );
         }
         catch  (Exception e){} 
        } 
        catch  (Exception e){  System.out.print("Unable to move Standard Node Due to : " + e.getMessage());                         }
      }
      */
      //--- End Move Standard Node to Shawky Node---
	
  //------------if Login passed then create the tree object in the session -----------
  Support.LookupTreeV10 queriesTree = null;
  //queriesTree = (NCCI.LookupTreeV10)session.getAttribute("queriesTree");
  
    queriesTree = new Support.LookupTreeV10();
    queriesTree.setImagepath("images/");
    queriesTree.setTargetUrl("editAndExecute.jsp");
    queriesTree.setAddFormUrl("queryInsert.jsp");
    queriesTree.setTargetFrame("mainFrame");
    queriesTree.setAddFormTargetFram("mainFrame");
    queriesTree.setShowAddLink(true);
    queriesTree.setShowToolBar(true);
    queriesTree.sethyperlinkLastTableItemsOnly(true);
    queriesTree.showRecycleBinButton(false);
    queriesTree.setShowCheckBox(false);
    queriesTree.setShowMoveForm(true);
  //---------This Section to Display only Node for the logged User + Standard --------
    java.util.Vector nodesIncluded = new java.util.Vector();
    String loggedUer = Support.Misc.getConnectionUserName(con).toUpperCase() ;
    String query = "select id from LU_QUERIES where code = '" + loggedUer +"'" ;
    java.sql.ResultSet rs = repCon.createStatement().executeQuery(query);
    rs.next();
    nodesIncluded.addElement("LU_QUERIES"+rs.getString("id"));
    nodesIncluded.addElement("LU_QUERIES305"); //---------Include The Standard Node
    //nodesIncluded.addElement("LU_QUERIES25"); //---------Include The Standard Node
    queriesTree.includeOnlySubNodes(nodesIncluded); //nodesIncluded
  //---------This Section to Display only Node for the logged User + Standard --------
  
  queriesTree.initialize(application,session,request,response,out);
  queriesTree.setDBConnection(repCon);
  
  queriesTree.setTreeData("LU_QUERIES",false);  //----------This Method internally check if the reload button pressed or not
  //--------to Start the tree with standard node opened
  //queriesTree.TogNodesStatusAsBitSet(615);
  //queriesTree.TogNodesStatusAsBitSet(562);
  
  session.setAttribute("queriesTree", queriesTree);
  
      Object comeFrom = session.getAttribute("comeFrom");
      if (comeFrom != null )
      {
       response.sendRedirect(comeFrom.toString());
       session.setAttribute("comeFrom", null);
      }
      else 
      {response.sendRedirect("index.jsp");}
  }

%>
<p align="center"><a href="Smart_Docs/Smart_Tech_Guide.zip"
	target="Doc">Tech.User Guide </a> - <a
	href="Smart_Docs/Smart_Tool_Demos/Simple%20query.zip"
	target="Simple_Query">Simple Query Demo</a> - <a
	href="Smart_Docs/Smart_Tool_Demos/Bind%20Variavle.zip"
	target="Bind Variables">Bind Variables Demo</a></p>
<jsp:include page="/footer.jsp" />
</BODY>
</HTML>
