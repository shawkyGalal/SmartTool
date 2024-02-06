<%@page import="java.util.Vector"%>
<%@ page errorPage="../../errorPage.jsp" %>
<%@page  language="java" contentType="text/html;charset=UTF-8"%>
<%@page import="com.implex.database.map.services.* , com.implex.database.map.SecUsrDta, Support.Misc" %>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<% String appURL = Support.Misc.getAppURL(request) ;  
   SecUsrDta loggedUser = Misc.getLoggedUserFromSession(session ) ; //(SecUsrDta) session.getAttribute("loggedUser") ;
   Support.LookupTreeV10 mainQueriesTree = (Support.LookupTreeV10) session.getAttribute("queriesTree");
   
%>
<html>
<body>
<div class="static-leftbar-wrapper leftbar-default">
	<div class="static-leftbar">
    	<div class="leftbar">
		<div class="widget hide-on-horizontal hide-on-small">
			<div class="widget-body">
				<div class="userinfo clearfix">
					<div class="avatar">
						<img class="img-responsive img-circle" src="assets/demo/avatar/avatar_06.png">
						<!-- <span class="status online"></span> -->
					</div>
					<div class="info">
						<span class="name"><%=loggedUser.getUsrNameValue()%></span>
						<span class="email"><%=loggedUser.getEmail() %></span>
					</div>
				</div>
			</div>
		</div>
		<div class="widget stay-on-collapse" id="widget-leftbar">
			<nav role="navigation" class="widget-body">
				<% String queryId = "" ;  %>
				<ul class="acc-menu">
					
					<li>
							<a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/mainScreen.jsp' , 'contentDiv'  ) ;	"> 
								<span class="icon"><i class="ion-android-home"></i></span>
								<span class="text" data-localize="الرئيسية">الرئيسية</span>
							</a>
							
					</li>
					
					<li><a href= "javascript:;" > 
							<span class="icon"><i class="ion-android-done-all"></i></span>
							<span class="text" data-localize="الخارطة الاستراتيجية">الخارطة الاستراتيجية</span>
						</a>
						
						
						<% 
						String navBarList = mainQueriesTree.getNavBarSonsList("66541" , false) ; 
						out.println (navBarList) ; 
						%>
			            
					</li>
					
					<li><a href= "javascript:;" > 
							<span class="icon"><i class="ion-android-done-all"></i></span>
							<span class="text" data-localize="مؤشرات الأداء">مؤشرات الأداء</span>
						</a>
						<% 
						navBarList = mainQueriesTree.getNavBarSonsList("66544" , false) ; 
						out.println (navBarList) ; 
						%>
						
					</li>
					
			        <li>
			            <a href="javascript:;"><span class="icon"><i class="fa fa-dashboard"></i></span><span class="text" data-localize="لوحات التقييم">لوحات التقييم</span></a>
			            <% 
						navBarList = mainQueriesTree.getNavBarSonsList("66545" , false) ; 
						out.println (navBarList) ; 
						%>
			        </li>
			
					<li>
			            <a href="javascript:;"><span class="icon"><i class="fa fa-dashboard"></i></span><span class="text" data-localize="التقارير">التقارير</span></a>
			            <ul class="acc-menu">
			            <% 
						navBarList = mainQueriesTree.getNavBarSonsList("66546" , false) ; 
						out.println (navBarList) ; 
						%>
			            	
			             </ul>
			        </li>
					
					<li>
			            <a href="javascript:;"><span class="icon"><i class="fa fa-dashboard"></i></span><span class="text" data-localize="التنبيهات">التنبيهات</span></a>
			            <% 
						navBarList = mainQueriesTree.getNavBarSonsList("66547" , false) ; 
						out.println (navBarList) ; 
						%>
			            
			        </li>
			        
			        <li>
			            <a href="javascript:;"><span class="icon"><i class="fa fa-dashboard"></i></span><span class="text" data-localize="التحليلات">التحليلات</span></a>
			            <% 
						navBarList = mainQueriesTree.getNavBarSonsList("66550" , false) ; 
						out.println (navBarList) ; 
						%>
			        </li>
					<% queryId = "34441" ; try { mainQueriesTree.getPathLinks(queryId) ; %>
					<li>
			        	<a href = "javascript:;" ><span class="icon"><i class="fa fa-sitemap"></i></span><span class="text" data-localize="الهيكل التنظيمي للمؤسسة">الهيكل التنظيمي للمؤسسة</span></a>
			        	<% 
						navBarList = mainQueriesTree.getNavBarSonsList(queryId , false) ; 
						out.println (navBarList) ; 
						%>
			        </li>
			        <% } catch (Exception e) {}%>
			        
			        <% queryId = "66553" ; try { mainQueriesTree.getPathLinks(queryId) ; %>
			        <li>
			        	<a href="javascript:;">
			        		<span class="icon"><i class="fa fa-group"></i></span>
			        		<span class="text" data-localize="إدارة المستخدمين">إدارة المستخدمين </span>
			        	</a>
			        	<% 
						navBarList = mainQueriesTree.getNavBarSonsList("66553", false) ; 
						out.println (navBarList) ; 
						%>
			        	
			        </li>
			        <% } catch (Exception e) {}%>
			        
			        <% queryId = "70752" ; try { mainQueriesTree.getPathLinks(queryId) ; %>
			        <li>
			        	<a href="javascript:;">
			        		<span class="icon"><i class="fa fa-group"></i></span>
			        		<span class="text" data-localize="دورات الاعتماد"> دورات الاعتماد  </span>
			        	</a>
			        	<% 
						navBarList = mainQueriesTree.getNavBarSonsList(queryId, false) ; 
						out.println (navBarList) ; 
						%>
			        	
			        </li>
			        <% } catch (Exception e) {}%>
			        
			        
			        <% if (loggedUser.isSmartToolAdmin()) { %>
					<li>
						<a href="javascript:;" target = "System Maintenance">
							<span class="icon"><i class="fa fa-cogs"></i></span>
							<span class="text" data-localize="إدارة و تصميم  النظام">إدارة و تصميم النظام</span>
						</a>
						<ul class="acc-menu">
						   	<li>
			               		<a href="/SmartTool/index.jsp?reportTopOpen=33884" target = "Sys Admin" >
			               			<span class="text"></span>
			               			<span class="text" data-localize="System Admin">System Admin </span>
			               		</a>
			               	</li>
			              <% queryId = "33951" ; try { mainQueriesTree.getPathLinks(queryId) ; %>	
			               	<li>
			               		<a href="javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/executeQuery.jsp?id=33951' , 'contentDiv' ) ;"  >
			               			<span class="text"></span>
			               			<span class="text" data-localize="إعدادات">إعدادات النظام </span>
			               		</a>
			               	</li>
						  <% } catch (Exception e) {}%>
			           	</ul>		
					</li>
					<% } %>
					
			        <li>
			        <% Cookie loginPageCookie  = Misc.getCookiByName(request , Misc.LoginScreenPageNameCookiVarName) ; %>
			            <a href="<%= appURL + "/" + loginPageCookie.getValue()%>" onclick="return confirm('This Will Discard All Unsaved Changes, Are You Sure ? سوف يتم إلغاء جميع التعديلات التى لم يتم حفظها .. هل انت متأكد ؟')"><span class="icon"><i class="fa fa-sign-out"></i></span><span class="text" data-localize="الخروج من النظام">الخروج من النظام</span></a>
			
			        </li>
					 
				</ul>
			</nav>
		</div>
	</div>
</div>
</div>
</body>
</html>