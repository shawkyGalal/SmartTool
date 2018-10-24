<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>
<%
	String appPath = Support.Misc.getAppURL(request) ;
  java.sql.Connection conx = (java.sql.Connection)session.getAttribute("con");
  if (conx == null )
 	{response.sendRedirect("loginScreen.jsp");}
  //-----------if RollBack Pressed------------------------------
  if (request.getParameter("RollBack.x") != null )
  {
    conx.rollback();
    %>
    	<script> 
	 	  $(function() { $( "#rollBackDialog" ).dialog({ position: { my: "center top", at: "center top", of: window  } } , {modal: true});  }); 
	 	</script>
	 	
	 	<div id="rollBackDialog" title="إلغاء التعديلات ">
  			<p align="center"> Transaction(s) Rolled Back </p><p align="center"> تم الغاء التعديلات بنجاح  </p>
		</div>
    <%
  }
  //----------------------------------------------------------

  //-----------if Commit Pressed------------------------------
  if (request.getParameter("Commit.x") != null )
  {
    conx.commit();
     %>
    	<script> 
	 	  $(function() { $( "#commitDialog" ).dialog({ position: { my: "center top", at: "center top", of: window  } } , {modal: true});  }); 
	 	</script>
	 	
	 	<div id="commitDialog" title="حفظ  التعديلات ">
  			<p align="center"> Transaction(s) Commited </p><p align="center"> تم حفظ جميع التعديلات  </p>
		</div>
    <%
  }
  String conMetadata = conx.getMetaData().getUserName() ; //+ "@" + conx.getMetaData().getURL().toUpperCase() ; 
%>
<form name="form1" method="post" action="">
	<td width="30">
		<input title=" الغاء جميع التعديلات  RollBack All Transactions By <%=conMetadata%>" type="image"
				style ="hight:25px; width:25px"  
				src="<%=appPath%>/images/rollback.gif" 
				name="RollBack" value="RollBack"
				onClick="return confirm('سوف يتم الغاء جميع التعديلات التى قمت بها. هل انت متأكد ؟  Are you sure you want to RollBack Your Transaction(s)?');">
	</td>
	<td width="30">		
		<input title=" حفظ جميع التعديلات  Commit All Transactions By <%=conMetadata%>" type="image" 
				style ="hight:25px; width:25px"
				src="<%=appPath%>/images/save.gif" name="Commit" value="Commit"
				onClick="return confirm('سوف يتم حفظ جميع التعديلات التى قمت بها. هل انت متأكد؟ Are you sure you want to Commit Your Transaction(s)?');">
	</td>
</form>
