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
	<script type="text/javascript">
	<% 
	String gaugeWidth = request.getParameter("_gauge_width") ; 
	String gaugeHeight = request.getParameter("_gauge_height") ; 
	String gaugeTitle = request.getParameter("_gauge_title") ;
	String gaugeValue = request.getParameter("gaugeValue");
	String limit0 = request.getParameter("limit0");
	String limit1 = request.getParameter("limit1");
	String limit2 = request.getParameter("limit2");

	%>

	function doInitializeDash()
	{
	    var title;

	    radial1.setMainValue('<%=gaugeValue%>');
	    if (radial1.getTitles().getCount() == 0) {
	        title = new cfx.gauge.Title();
	        radial1.getTitles().add(title);
	    }
	    else
	        title = radial1.getTitles().getItem(0);
	    title.setText("<%=gaugeTitle%>");
	    doConfigureSpendingSections(radial1,<%=(limit0==null)? "60":limit0 %> 
	                                ,<%=(limit1==null)? "100":limit1 %>
	                                ,<%=(limit2==null)? "100":limit2 %>);
  
	}

	function doConfigureSpendingSections(gauge, limit0, limit1, limit2)
	{
	    var scale = gauge.getMainScale();

	    var section = new cfx.gauge.ScaleSection();
	    section.setFrom(0);
	    section.setTo(limit0);
	    section.setTag("Section0");
	    scale.getSections().add(section);

	    section = new cfx.gauge.ScaleSection();
	    section.setFrom(limit0);
	    section.setTo(limit1);
	    section.setTag("Section2");
	    scale.getSections().add(section);

	    section = new cfx.gauge.ScaleSection();
	    section.setFrom(limit1);
	    section.setTo(limit2);
	    section.setTag("Section0");
	    scale.getSections().add(section);

	    scale.setMax(limit2);
	}
		
	</script>
    <script type="text/javascript">
        var radial1, radial2, radial3;
        var chart1, chart2, chart3;
        var border1;
        var vertical1, vertical2, vertical3;

        function initialize() {
            
            radial1 = new cfx.gauge.RadialGauge();
			//Animation does not work Properly 
			//radial1.getAnimations().getLoad().setEnabled(true);
            doInitializeDash();
            radial1.create('divRadial1');
    
        }
    </script>
    
    <link rel="stylesheet" type="text/css" href="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/styles/Attributes/jchartfx.attributes.css" />
    <link rel="stylesheet" type="text/css" href="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/styles/Palettes/jchartfx.palette.css" />
</head>


          <div id="divRadial1" style="width:<%=gaugeWidth%>px;height:<%=gaugeHeight%>px;display:inline-block;position:absolute;left:0px;top:0px"></div>
                 
        <script language="javascript">
            $(document).ready(function ($) {
                initialize();
            });
        </script>
</html>