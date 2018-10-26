<%@page import="com.implex.database.PersistantObject"%>
<%@page import="java.util.ArrayList"%>
<%@ page errorPage="../../errorPage.jsp" %>
<%@page  language="java" contentType="text/html;charset=UTF-8"%>
<%@page import="com.implex.database.map.services.* , com.implex.database.map.*, Support.Misc" %>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<% String appURL = Support.Misc.getAppURL(request) ;  %>

<% SecUsrDta loggedUser = Misc.getLoggedUserFromSession(session ) ; // (SecUsrDta) session.getAttribute("loggedUser") ;
 java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
 String userDefaultRoleName = "NO Default Role Defined"  ;
 SecRole userDefaultSecRole = null; 
 try{
	 	userDefaultSecRole = loggedUser.getDefaultSecRole();
 		userDefaultRoleName = userDefaultSecRole.getRoleNameValue() ; 
 } catch (Exception e){ }
%>

<header id="topnav" class="navbar navbar-default navbar-fixed-top clearfix" role="banner">
	

	<!-- Trigger -->
	<span id="trigger-leftbar" class="toolbar-trigger">
		<a data-toggle="tooltips" data-placement="right" title="Toggle Leftbar"><span class="icon"><i class="fa fa-navicon"></i></span></a>
	</span>
	
	<% String companyId = loggedUser.getUsrCmpIdValue() ; 
		boolean huloolCompany = companyId.equals("20") || companyId.equals("55"); 
	 if (! huloolCompany) {%>
	<span id="trigger-infobar" class="toolbar-trigger">
		<a data-toggle="tooltips" data-placement="left" title="Toggle Infobar"><span class="icon"><i class="fa fa-sitemap"></i></span></a>
	</span>
	<% } %>
	
 <script type="text/javascript">
function performAction(action)
{
	var v_url = '/SmartTool/includes/dbTransc.jsp?'+ action+'=Y' ; 
	sendAjaxRequestToJsp( v_url , 'sysMessage' ) ; 
	$( "#sysMessage" ).dialog({modal: true}, { position: { my: "center center", at: "center center", of: window  } });
}
</script>
 <div class="collapse navbar-collapse" id="tectonic-topnav-collapsible">
		<ul class="yamm nav navbar-nav navbar-left">
			
			<li class="hidden-sm hidden-md hidden-lg"><a href=""><span class="icon"><i class="ion-plus"></i></span><span class="text hidden-lg hidden-sm hidden-md">Add item</span></a></li>
		</ul>
		<ul class="yamm nav navbar-nav navbar-right" >
			<% if (con.getMetaData().getURL().contains("Demo") ) { %>
			<li class="dropdown dropdown-lg">
                <a href="#" class="dropdown-toggle" data-toggle='dropdown'>
                 <font color = red size="4" >  Demo Version </font> 
                </a>
            </li>
			<%} %>
			
			<li class="dropdown dropdown-lg">
				<a href="#" target = "New Window" title = "<%=loggedUser.getUserCompany().getCmpDescValue() %>"  
					style="padding-top: 0px;padding-bottom: 0px;padding-right: 0px;padding-left: 10px;margin-left: 0px;margin-top: 0px;margin-bottom: 0px;height: 60px;left: 0px;" >
					<img class="img-dark" src="<%=loggedUser.getUserCompany().getAttributeValue("LOGO_LINK") %>" alt="Tectonic" width="60"  height= "60">
				</a>
			</li>
			<li class="dropdown dropdown-lg" >
                <a href="#" target = "New Window" 
                	style=" padding-top: 20px; padding-bottom: 0px; margin-top: 0px; margin-bottom: 0px; height: 60px;  padding-right: 8px;" >  
                     <span class="text hidden-sm" data-localize="topnav_person">  
                     	<strong> <%=loggedUser.getUserCompany().getCmpDescValue() %> </strong>
                     </span>
                </a>
            </li>
			
            
			
			<li class="dropdown dropdown-lg">
				<a href = "javascript:performAction('RollBack');" class="dropdown-toggle" >  
					<img src="/SmartTool/images/rollback.gif" 
						title="RollBack Transactions الغاء جميع التعديلات "
						onClick="return confirm('سوف يتم الغاء جميع التعديلات التى قمت بها. هل انت متأكد ؟Are you sure you want to RollBack Your Transaction(s)?');"
						width="25" height="25" >
				</a>
			</li>
			<li class="dropdown dropdown-lg">			
				<a href = "javascript:performAction('Commit');" class="dropdown-toggle">					
					<img src="/SmartTool/images/save.gif" 
						title="Commit Transactions حفظ نهائى لجميع التعديلات "
						onClick="return confirm('سوف يتم حفظ نهائى لجميع التعديلات التى قمت بها. هل انت متأكد؟ Are you sure you want to Commit Your Transaction(s)?');"
						width="25" height="25" >
				</a>
			</li>
			<% if (!huloolCompany) {%>

            <li class="dropdown dropdown-lg">

                <a href="#" class="dropdown-toggle" data-toggle='dropdown'>
                    <span class="icon"><i class="ion-settings"></i></span>
                    <span class="text hidden-sm hidden-lg hidden-md">Configure</span>


                </a>
            </li>
			
            <li class="dropdown dropdown-lg">

                <a href="#" class="dropdown-toggle" data-toggle='dropdown'>
                    <span class="icon"><i class="fa fa-key"></i></span>
                    <span class="text hidden-sm hidden-lg hidden-md">Logs</span>
                  

                </a>
            </li>
			
            <li class="dropdown dropdown-lg">

                <a href="#" class="dropdown-toggle" data-toggle='dropdown'>
                    <span class="icon"><i class="fa fa-life-ring"></i></span>
                    <span class="text hidden-sm hidden-lg hidden-md">Tech.User Guide</span>


                </a>
            </li>
            <li class="dropdown dropdown-lg">

                <a href="#" class="dropdown-toggle" data-toggle='dropdown'>
                    <span class="icon"><i class="ion-android-mail"></i></span>
                    <span class="text hidden-sm hidden-lg hidden-md">Messages</span>
                    <span class="badge badge-pink">2</span>
                    
                </a>
            </li>

            <li class="dropdown dropdown-lg">

                <a href="#" class="dropdown-toggle" data-toggle='dropdown'>
                    <span class="icon"><i class="fa fa-bell"></i></span>
                    <span class="text hidden-sm hidden-lg hidden-md">Notifications</span>
                    <span class="badge badge-pink">4</span>

                </a>
            </li>
			<%} %>

			<li class="dropdown dropdown-lg">
			<script type="text/javascript">
			function  Set_User_Default_Role(defaultRole)
			{
				sendAjaxRequestToJsp('/SmartTool/executeExecutable.jsp?id=49620&imbedded=Y&user_role='+defaultRole , 'sysMessage' ) ; 
			}
			</script>
			
			
				الوظيفة: 
				<% ArrayList<PersistantObject> userRoles =  loggedUser.getUserGroupsDS().getPersistantObjectList();  %> 
						<br><select class="dropdown-toggle" name = "userDefaultRoleId" onchange="Set_User_Default_Role(this.value); ">
							<% for (PersistantObject po : userRoles)
								{
								SecUserGroups scUserGroup = (SecUserGroups) po; 
								SecRole secRole= scUserGroup.getSecRole(false) ; 
								boolean isTheDefault = userDefaultSecRole!= null && userDefaultSecRole.getRoleIdValue().equals(secRole.getRoleIdValue()) ; 
								%><option value='<%=secRole.getRoleIdValue()%>'  label = '<%=secRole.getRoleNameValue()%>' <%=(isTheDefault)? "selected='selected'" : "" %> ><%=secRole.getRoleNameValue()%> </option><%
								}
							%>
					 	</select>
			</li>
			<li class="dropdown username">
				<a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
					<span class="icon visible-xs"><i class="ion-android-person"></i></span>
					<span class="text hidden-sm" data-localize="topnav_person"><%=loggedUser.getUsrFullnameValue() %></span>

					<span class="avatar"><img class="img-responsive img-rounded" src="assets/demo/avatar/avatar_06.png"></span>
					
				</a>
				<ul class="dropdown-menu">
					<li>
						<div class="userinfo">
							<span class="avatar"><img class="img-responsive img-circle" src="assets/demo/avatar/avatar_06.png"></span>
							<span class="name" data-localize="topnav_jonathan_smith"><%= loggedUser.getUsrNameValue()%></span>
							<span class="email" data-localize="topnav_person"><%=loggedUser.getUsrFullnameValue()%></span>
							
						</div>
					</li>
					<li role="separator" class="divider"></li>
					<li><a href="queryResult.jsp?id=28234" target = "User Prefrences" ><i class="ion-android-create"></i><span data-localize="topnav_edit_profile">حسابى</span></a></li>
					<li><a href="queryResult.jsp?id=66547" target = "User Prefrences"><i class="ion-arrow-down-c"></i><span data-localize="topnav_downloads">تنويهات</span></a></li>
					<li><a href="queryResult.jsp?id=19339" target = "User Prefrences"><i class="ion-ios-settings-strong"></i><span data-localize="topnav_settings">ضبط</span></a></li>
					<li><a href="../../TableInsertForm.jsp?owner=MOEP&tableName=SERVICE_REQUEST" target = "User Prefrences"><i class="ion-help-buoy"></i><span data-localize="topnav_support">الدعم</span></a></li>
					<li role="separator" class="divider"></li>
					<% Cookie loginPageCookie  = Misc.getCookiByName(request , Misc.LoginScreenPageNameCookiVarName) ; %>
					<li><a href="<%=appURL+"/"+loginPageCookie.getValue()%>" onclick="return confirm('This Will Discard All Unsaved Changes, Are You Sure ? سوف يتم إلغاء جميع التعديلات التى لم يتم حفظها .. هل انت متأكد ؟')"><i class="ion-arrow-right-c"></i><span data-localize="topnav_logout">خروج</span></a></li>
				</ul>
			</li>
		</ul>
	</div>
</header>              
