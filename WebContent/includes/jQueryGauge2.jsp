<!DOCTYPE html>
<html>
	<head>
		<% String appURL = Support.Misc.getAppURL(request) ;  %>
	    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jquery-1.8.3.min.js"></script>
	    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.system.js"></script>
	    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.coreVector.js"></script>
	    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.advanced.js"></script>
	    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.gauge.js"></script>
	    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.funnel.js"></script>
	    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/motifs/jchartfx.motif.js"></script>
		<script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.animation.js"></script>
		<% 
		String gaugeWidth = request.getParameter("_gauge_width") ; 
		String gaugeHeight = request.getParameter("_gauge_height") ; 
		String gaugeTitle = request.getParameter("_gauge_title") ;
		String gaugeValue = request.getParameter("gaugeValue");
		String limit0 = request.getParameter("limit0");
		String limit1 = request.getParameter("limit1");
		String limit2 = request.getParameter("limit2");
		limit2 = (limit2==null)? "100" : limit2 ; 
		
		%>
		   <!-- Uncomment this section to add extended User Interaction capabilities, including the End-User Menu.
	       The UI features require a jChartFX Plus license for deployment.
		<script type="text/javascript" src="jChartFX/jchartfx.userinterface.js"></script>
		<link rel="stylesheet" type="text/css" href="jchartfx.userinterface.css" />
	  -->
	  <link rel="stylesheet" type="text/css" href="jchartfx.attributes.css" />
	  <link rel="stylesheet" type="text/css" href="jchartfx.palette.css" />
	</head>
	<body onload="loadChart()">
	  <div id='div_obj' style='width:<%=gaugeWidth%>px;height:<%=gaugeHeight%>px;'></div>
	  <script type="text/javascript" language="javascript">
	  var radialGauge1;
	  function loadChart() {
		  var radialGauge1 = new cfx.gauge.RadialGauge();

		  radialGauge1.getBorder().setTemplate('<DataTemplate xmlns:x="http://schemas.microsoft.com/winfx/2006/xaml" xmlns:sys="clr-namespace:System;assembly=mscorlib"><DataTemplate.Resources><Thickness x:Key="borderFactor">0</Thickness><sys:String x:Key="ratio">2</sys:String><sys:String x:Key="startLocation">0</sys:String><sys:String x:Key="endLocation">360</sys:String></DataTemplate.Resources><Canvas><Rectangle Stroke="#000000" ></Rectangle></Canvas></DataTemplate>');

		  radialGauge1.setResizeableFont(cfx.gauge.ResizeFont.Never);

		  radialGauge1.setMainValue(<%=gaugeValue%>);

		  // Customize scale
		  var mainScale = radialGauge1.getMainScale();
		  mainScale.setMax(100);
		  mainScale.setAlignment(cfx.StringAlignment.Near);
		  mainScale.setPosition(0);
		  mainScale.setAllowHalves(false);
		  mainScale.setStartAngle(180);
		  mainScale.setSweepAngle(180);
		  mainScale.setThickness(0.5);

		  var major = mainScale.getTickmarks().getMajor();
		  major.setSize(0.1);
		  major.setWidth(0.02);
		  major.setPosition(0);
		  major.setColor("#80000000");
		  var medium = mainScale.getTickmarks().getMedium();
		  medium.setSize(0.05);
		  medium.setWidth(0.02);
		  medium.setPosition(0);
		  medium.setColor("#80000000");

		      var borderSemi = '<DataTemplate xmlns:x="http://schemas.microsoft.com/winfx/2006/xaml" xmlns:sys="clr-namespace:System;assembly=mscorlib">' +
		        '<DataTemplate.Resources>' +
		          '<sys:String x:Key="ratio">2</sys:String>' +
		          '<sys:String x:Key="startLocation">180</sys:String>' +
		          '<sys:String x:Key="endLocation">360</sys:String>' +
		        '</DataTemplate.Resources>' +
		        '<Viewbox ViewWidth="100" ViewHeight="50">' +
		          '<Canvas>' +
		            '<Path Fill="{Binding Path=Fill}" Data="M7,50 C7,26.2517557586 26.2517557586,7 50,7 C 73.7482442414,7 93,26.2517557586 93,50 L75,50 C75,36.192881255 63.807118745,25 50,25 C 36.192881255,25 25,36.192881255 25,50 Z" ></Path>' +
		          '</Canvas>' +
		        '</Viewbox>' +
		       '</DataTemplate>';

		      var glareSemi = '<DataTemplate xmlns:x="http://schemas.microsoft.com/winfx/2006/xaml" xmlns:sys="clr-namespace:System;assembly=mscorlib">' +
		        '<DataTemplate.Resources>' +
		          '<sys:String x:Key="ratio">2</sys:String>' +
		        '</DataTemplate.Resources>' +
		        '<Viewbox ViewWidth="100" ViewHeight="50">' +
		          '<Canvas>' +
		            '<Path Data="M0,50 C0,22.38576251 22.38576251,0 50,0 C 77.61423749,0 100,22.38576251 100,50 L75,50 C75,36.192881255 63.807118745,25 50,25 C 36.192881255,25 25,36.192881255 25,50 Z">' +
		                '<Path.Fill>' +
		                  '<RadialGradientBrush>' +
		                    '<RadialGradientBrush.RelativeTransform>' +
		                      '<TransformGroup>' +
		                        '<ScaleTransform ScaleX="1" ScaleY="2"></ScaleTransform>' +
		                        '<TranslateTransform X="0" Y="0.5"></TranslateTransform>' +
		                      '</TransformGroup>' +
		                    '</RadialGradientBrush.RelativeTransform>' +
		                    '<GradientStop Color="#00808080" Offset="0"></GradientStop>' +
		                    '<GradientStop Color="#10000000" Offset="0.5"></GradientStop>' +
		                    '<GradientStop Color="#30FFFFFF" Offset="0.51"></GradientStop>' +
		                    '<GradientStop Color="#A0FFFFFF" Offset="0.53"></GradientStop>' +
		                    '<GradientStop Color="#B0FFFFFF" Offset="0.55"></GradientStop>' +
		                    '<GradientStop Color="#00FFFFFF" Offset="0.85"></GradientStop>' +
		                    '<GradientStop Color="#00FFFFFF" Offset="1"></GradientStop>' +
		                  '</RadialGradientBrush>' +
		                '</Path.Fill>' +
		            '</Path>' +
		          '</Canvas>' +
		        '</Viewbox>' +
		       '</DataTemplate>';

		      var border = radialGauge1.getBorder();
		      border.setTemplate(borderSemi);
		      //border.setInsideColor("#000000");
		      border.getGlare().setTemplate(glareSemi);
		      border.setInsideGap(-0.04);

		  var bar = mainScale.getBar();
		  bar.setVisible(false);

		  var defaultAttributes = radialGauge1.getDefaultAttributes();
		  defaultAttributes.setSectionThickness(0.83);
		  defaultAttributes.setSectionPosition(0);

		  var section = new cfx.gauge.ScaleSection();
		  section.setFrom(0);
		  section.setTo(<%=limit0%>);
		  mainScale.getSections().add(section);

		  section = new cfx.gauge.ScaleSection();
		  section.setFrom(<%=limit0%>);
		  section.setTo(<%=limit1%>);
		  mainScale.getSections().add(section);

		  section = new cfx.gauge.ScaleSection();
		  section.setFrom(<%=limit1%>);
		  section.setTo(<%=limit2%>);
		  mainScale.getSections().add(section);

		  var cap = mainScale.getCap();
		  cap.setColor("#FFB4B4");
		  cap.setTemplate("CapSimple");
		  cap.setSize(0.05);

		  var indicatorTemplate = '<DataTemplate xmlns:x="http://schemas.microsoft.com/winfx/2006/xaml" xmlns:sys="clr-namespace:System;assembly=mscorlib"><DataTemplate.Resources><sys:String x:Key="ratio">0.1</sys:String><sys:String x:Key="yPivot">0.1</sys:String></DataTemplate.Resources><Viewbox ViewWidth="30" ViewHeight="200"><Grid><Path Fill="{Binding Path=Fill}" Stroke="{Binding Path=Stroke}" Data="M15,0 L30,200 0,200 z"></Path></Grid></Viewbox></DataTemplate>';

		  var indicator = radialGauge1.getMainIndicator();
		  indicator.setColor("#0000FF");
		  indicator.setBorderColor("#A7A7A7");
		  indicator.setTemplate(indicatorTemplate);
		  indicator.setSize(0.8);

		  radialGauge1.getDashboardBorder().setTemplate("<DataTemplate></DataTemplate>"); // This is only required if you use motifs

		  //End Do Not Show
		  radialGauge1.create('div_obj');
	  }
	  // If there are additional data population functions in the Javascript section, copy them here
	  </script>
	</body>
</html>
	