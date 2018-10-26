<html >
<head>
  <META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
  <%request.setCharacterEncoding("UTF-8");%>
  
  <title>نظام إدارة الاداء - جمعية مراكز الاحياء</title>
  
   
      <link rel="stylesheet" href="css/style.css">

  
</head>

<body>
  <div id="wrapper">
  <div id="leftWrapper">
      <div id="listView" class="list">
        <li class="list-item-active"><a href="#" onclick = "changeFrameSrc('mainFrame' , '../EPM_DOCS/BSC.pdf' )">Home</a></li>
        <li><a href="#" onclick = "changeFrameSrc('mainFrame' , '../editAndExecute.jsp?id=37428' )" >Favorites</a></li>
        <li><a href="#" onclick = "changeFrameSrc('mainFrame' , '../editAndExecute.jsp?id=33885')">الاهداف الاستراتيجية</a></li>
        <li><a href="#">المحاور الاستراتيجية</a></li>
        <li><a href="#" onclick = "changeFrameSrc('mainFrame' , '../editAndExecute.jsp?id=64930')">Dashboards - شاشات المتابعة</a></li>
        
        <li><a href="#">صيانة المستخدمين</a></li>
        <li><a href="#"> الهيكل التنظيمي للمؤسسة</a></li>
        <li><a href="#" onclick = "changeFrameSrc('mainFrame' , '../index.jsp')" > إدارة النظام</a></li>
        <li><a target="_parent" href="/SmartTool/Company/20/loginScreen.jsp" title="الخروج من النظام" onclick="return confirm('This Will Discard All Unsaved Changes, Are You Sure ? سوف يتم إلغاء جميع التعديلات التى لم يتم حفظها .. هل انت متأكد ؟')">  الخروج من النظام</a></li>
        
        
      </div>
    </div>

    <div id="rightWrapper">
     <div id="header" ><a id="fullPage" href="#">|||</a></div>
     <div id="contentWrapper">
   		<iframe id="mainFrame" src="../index.jsp" width = 100% height = 800  allowtransparency="true" frameborder="0" scrolling="yes"> </iframe><br />
    </div>
</div>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script src="js/index.js"></script>
    <script src="../includes/smartTool.js"></script>

</body>
</html>
