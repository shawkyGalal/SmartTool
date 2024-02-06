<%@ page errorPage="../../errorPage.jsp" %>
<%@page  language="java" contentType="text/html;charset=UTF-8"%>
<%@page import="com.implex.database.map.services.* , com.implex.database.map.SecUsrDta, Support.Misc" %>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<% String appURL = Support.Misc.getAppURL(request) ;  %>

<% SecUsrDta loggedUser = (SecUsrDta) session.getAttribute("loggedUser") ;%>

<header id="topnav" class="navbar navbar-default navbar-fixed-top clearfix" role="banner">
	<div class="navbar-header">
		<a class="navbar-brand" href="https://ahyaa.org.sa/" target = "New Window" title = "جمعية مراكز الاحياء - الموقع الرسمى العام "  >
			<!--<img class="img-white" src="assets/img/logo-white.png" alt="Tectonic">-->
			<img class="img-dark" src="assets/img/logo-dark.png" alt="Tectonic">
		</a>
	</div>

	<!-- Trigger -->
	<span id="trigger-leftbar" class="toolbar-trigger">
		<a data-toggle="tooltips" data-placement="right" title="Toggle Leftbar"><span class="icon"><i class="fa fa-navicon"></i></span></a>
	</span>

	<span id="trigger-infobar" class="toolbar-trigger">
		<a data-toggle="tooltips" data-placement="left" title="Toggle Infobar"><span class="icon"><i class="fa fa-sitemap"></i></span></a>
	</span>

	<span class="toolbar-trigger">
		<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#tectonic-topnav-collapsible" aria-expanded="false">
			<span class="icon"><i class="ion-more"></i></span>
		</button>
	</span>
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
		<ul class="yamm nav navbar-nav navbar-right">
			
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
			

			<li class="dropdown username">
				<a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
					<span class="icon visible-xs"><i class="ion-android-person"></i></span>
					<span class="text hidden-sm" data-localize="topnav_person"><%=loggedUser.getEmail() %></span>
					<span class="avatar"><img class="img-responsive img-rounded" src="assets/demo/avatar/avatar_06.png"></span>
				</a>
				<ul class="dropdown-menu">
					<li>
						<div class="userinfo">
							<span class="avatar"><img class="img-responsive img-circle" src="assets/demo/avatar/avatar_06.png"></span>
							<span class="name" data-localize="topnav_jonathan_smith"><%= loggedUser.getUsrNameValue()%></span>
							<span class="email" data-localize="topnav_person"><%=loggedUser.getEmail()%></span>
							<button class="btn btn-orange btn-sm mt-md" data-localize="topnav_upgrade">تعديل</button>
						</div>
					</li>
					<li role="separator" class="divider"></li>
					<li><a href="#"><i class="ion-android-create"></i><span data-localize="topnav_edit_profile">حسابى</span></a></li>
					<li><a href="#"><i class="ion-arrow-down-c"></i><span data-localize="topnav_downloads">تنويهات</span></a></li>
					<li><a href="#"><i class="ion-ios-settings-strong"></i><span data-localize="topnav_settings">ضبط</span></a></li>
					<li><a href="#"><i class="ion-help-buoy"></i><span data-localize="topnav_support">الدعم</span></a></li>
					<li role="separator" class="divider"></li>
					<li><a href="loginScreen.jsp"><i class="ion-arrow-right-c"></i><span data-localize="topnav_logout">خروج</span></a></li>
				</ul>
			</li>
		</ul>
	</div>
</header>              
