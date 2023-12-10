<!DOCTYPE html>
<%@page import="com.smartValue.LoginAuthenticator"%>
<%@ page errorPage="../../errorPage.jsp" %>
<%@page  language="java" contentType="text/html;charset=UTF-8"%>
<%@page import="com.implex.database.map.services.* , com.implex.database.map.SecUsrDta, Support.Misc" %>
<%
request.setCharacterEncoding("UTF-8");%>
<% String appURL = Support.Misc.getAppURL(request) ;  %>


<%@page import="com.implex.database.map.SysParams"%>
<%@page  contentType="text/html;charset=UTF-8"%>

<html lang="en" class="coming-soon">
  <head>
    <meta charset="utf-8">
    <title>نظام تقييم الاداء لوزارة العدل</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta name="description" content="">
    <meta name="author" content="KaijuThemes">
	<!-- <link href='http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700' rel='stylesheet' type='text/css'>  -->
    <!-- <link type="text/css" href="assets/plugins/iCheck/skins/minimal/blue.css" rel="stylesheet"> -->
    <link type="text/css" href="../20/assets/fonts/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link type="text/css" href="../20/assets/fonts/ionicons/css/ionicons.min.css" rel="stylesheet">                <!-- Ion Icons -->
    <link type="text/css" href="../20/assets/css/styles.css" rel="stylesheet">

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
	 loginPageCookie.setValue("Company/40/loginScreen.jsp") ;
	 loginPageCookie.setMaxAge(24*60*60);
	 loginPageCookie.setPath("/");
	 response.addCookie(loginPageCookie);

	String defaultEnv =  "PNU_PROD" ; //"GIHAZ_GP_PRIOD" ;; 
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
	LoginAuthenticator.authenticate(session, request, response, out, application) ; 
	return ; 
  }
   %> 
    
<div class="container" id="login-form">
	
	<div class="row">
		<div class="col-md-4 col-md-offset-4">

            <div class="text-center titlehomecom">
                <h3> نظام مراقبة و متابعة الأداء الإستراتيجى للمؤسسات </h3>
                <a href="#" ><img src="images/MOJ_LOGO.png" width="350" height="100"></a>
                <span dir="ltr">Enterprise Performance Management System (EPM)</span>
            </div>
			<div class="panel panel-default alt with-footer">
				<div class="panel-heading"><h2>تسجيل دخول</h2></div>
				<div class="panel-body">
					
					<form action="loginScreen.jsp" class="" id="validate-form" method="post" >
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
							<a href="../../resetPassword.jsp" class="pull-left text-small">نسيت كلمة المرور ؟</a>
						</div>
												
					</form>
				</div>
				
			</div>

            <div class="text-center linkslogin" style="width:800">
                <a href="#">دليل المستخدم</a>
                <a href="#">مساعدة</a>
            </div>

			
		</div>
	</div>
</div>


      <div class="footerfix">

          <img src="../20/assets/img/smart-value.bmp" style="width: 49px; height: 23px"/> 
          Copyright © 1998,2017

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