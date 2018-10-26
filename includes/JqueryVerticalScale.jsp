<!DOCTYPE html>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>

<html>
<head>

	<% String appURL = Support.Misc.getAppURL(request) ;  

	String gaugeWidth = request.getParameter("_gauge_width") ; 
	String gaugeHeight = request.getParameter("_gauge_height") ; 
	String gaugeTitle = request.getParameter("_gauge_title") ;
	String gaugeValue = request.getParameter("gaugeValue");
	String limit0 = request.getParameter("limit0");
	String limit1 = request.getParameter("limit1");
	String limit2 = request.getParameter("limit2");
	String kpiDesc = request.getParameter("kpiDesc");

	%>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.system.js"></script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.coreVector.js"></script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.advanced.js"></script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.gauge.js"></script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.funnel.js"></script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/motifs/jchartfx.motif.js"></script>
	<script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.animation.js"></script>
    <script type="text/javascript">
        var border1;
        var vertical1, vertical2, vertical3;

        function initialize() {
            border1 = new cfx.gauge.Border();
            
			radial1 = new cfx.gauge.RadialGauge();
			radial1.getAnimations().getLoad().setEnabled(true);
            radial2 = new cfx.gauge.RadialGauge();
			radial2.getAnimations().getLoad().setEnabled(true);
            radial3 = new cfx.gauge.RadialGauge();
			radial3.getAnimations().getLoad().setEnabled(true);

            vertical1 = new cfx.gauge.VerticalGauge("group");
            //vertical1.getAnimations().getLoad().setEnabled(true);
            // Shawky Animation is not working fine ... the scale is incorrect 
			vertical2 = new cfx.gauge.VerticalGauge("group");
			//vertical2.getAnimations().getLoad().setEnabled(true);
            vertical3 = new cfx.gauge.VerticalGauge("group");
			//vertical3.getAnimations().getLoad().setEnabled(true);

            doInitializeDash();

            border1.create('divBorder1');
            vertical2.create('divVertical2');

        }
    </script>
    <link rel="stylesheet" type="text/css" href="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/styles/Attributes/jchartfx.attributes.css" />
    <link rel="stylesheet" type="text/css" href="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/styles/Palettes/jchartfx.palette.css" />
   <script>
    function doInitializeDash()
    {
        var title;
        title = new cfx.gauge.Title();
        title.setText("<%=gaugeTitle%>");
        border1.getTitles().add(title);

        vertical2.getMainScale().setMax(100);
        vertical2.setMainValue(<%=gaugeValue%>);
        vertical2.getMainIndicator().setUseSectionColor(true);
        title = new cfx.gauge.Title();
        title.setText("<%=kpiDesc%>");
        title.setAngle(0);
        title.setDock(cfx.gauge.DockArea.Bottom);
        vertical2.getTitles().add(title);
        doConfigureLevelsSections(vertical2);

    }


    function doConfigureLevelsSections(gauge)
    {
        var scale = gauge.getMainScale();

        var section = new cfx.gauge.ScaleSection();
        section.setFrom(0);
        section.setTo(<%=limit0%>);
        scale.getSections().add(section);

        section = new cfx.gauge.ScaleSection();
        section.setFrom(<%=limit0%>);
        section.setTo(<%=limit1%>);
        scale.getSections().add(section);

        section = new cfx.gauge.ScaleSection();
        section.setFrom(<%=limit1%>);
        section.setTo(100);
        scale.getSections().add(section);
    }
        
    </script>
    
</head>
<body class="jchartfx_body">

    <% int gw = Integer.valueOf(gaugeWidth).intValue(); 
       int gh = Integer.valueOf(gaugeHeight).intValue();
    
    %>
    
    <div id="divBorder1" style="width:<%=gw%>px;height:<%=gh%>px;display:inline-block;position:absolute;left:20px;top:0px"></div>
    <div id="divVertical2" style="width:<%=gw*100/250%>px;height:<%=gh*200/280 %>px;display:inline-block;position:absolute;left:<%=gw*90/250 %>px;top:<%=gh*45/280 %>px"></div>


        <script language="javascript">
            $(document).ready(function ($) {
                initialize();
            });
        </script>

</body>
</html>