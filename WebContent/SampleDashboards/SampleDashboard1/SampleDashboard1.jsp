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
    <script type="text/javascript" src="SampleDashboard1.js"></script>
    <script type="text/javascript">
        var radial1, radial2, radial3;
        var chart1, chart2, chart3;
        var border1;
        var vertical1, vertical2, vertical3;

        function initialize() {
            chart1 = new cfx.Chart();
			chart1.getAnimations().getLoad().setEnabled(true);
            chart2 = new cfx.Chart();
			chart2.getAnimations().getLoad().setEnabled(true);
            chart3 = new cfx.Chart();
			chart3.getAnimations().getLoad().setEnabled(true);
            border1 = new cfx.gauge.Border();
            
			radial1 = new cfx.gauge.RadialGauge();
			radial1.getAnimations().getLoad().setEnabled(true);
            radial2 = new cfx.gauge.RadialGauge();
			radial2.getAnimations().getLoad().setEnabled(true);
            radial3 = new cfx.gauge.RadialGauge();
			radial3.getAnimations().getLoad().setEnabled(true);

            vertical1 = new cfx.gauge.VerticalGauge("group");
            vertical1.getAnimations().getLoad().setEnabled(true);
			vertical2 = new cfx.gauge.VerticalGauge("group");
			vertical2.getAnimations().getLoad().setEnabled(true);
            vertical3 = new cfx.gauge.VerticalGauge("group");
			vertical3.getAnimations().getLoad().setEnabled(true);

            doInitializeDash();

            chart1.create('divChart1');
            chart2.create('divChart2');
            chart3.create('divChart3');

            border1.create('divBorder1');
            radial1.create('divRadial1');
            radial2.create('divRadial2');
            radial3.create('divRadial3');

            vertical1.create('divVertical1');
            vertical2.create('divVertical2');
            vertical3.create('divVertical3');
        }
    </script>
    <link rel="stylesheet" type="text/css" href="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/styles/Attributes/jchartfx.attributes.css" />
    <link rel="stylesheet" type="text/css" href="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/styles/Palettes/jchartfx.palette.css" />
</head>
<body class="jchartfx_body">
    <div class="jchartfx_container">
        <h1>SmartTool Dashboard Sample <a href="http://support.softwarefx.com/jChartFX/article/1500134" class="smallLabel"> How do I remove the jChartFX watermark?</a></h1>
        <div id="myDash" style="width:1200px;height:620px;display:inline-block;position:relative">
            <div id="divChart1" style="width:480px;height:280px;display:inline-block;position:absolute;left:0px;top:0px"></div>
            <div id="divBorder1" style="width:350px;height:280px;display:inline-block;position:absolute;left:490px;top:0px"></div>
            <div id="divVertical1" style="width:100px;height:200px;display:inline-block;position:absolute;left:505px;top:45px"></div>
            <div id="divVertical2" style="width:100px;height:200px;display:inline-block;position:absolute;left:615px;top:45px"></div>
            <div id="divVertical3" style="width:100px;height:200px;display:inline-block;position:absolute;left:725px;top:45px"></div>
            <div id="divChart2" style="width:380px;height:280px;display:inline-block;position:absolute;left:850px;top:0px"></div>
            <div id="divRadial1" style="width:240px;height:280px;display:inline-block;position:absolute;left:0px;top:290px"></div>
            <div id="divRadial2" style="width:240px;height:280px;display:inline-block;position:absolute;left:250px;top:290px"></div>
            <div id="divRadial3" style="width:240px;height:280px;display:inline-block;position:absolute;left:500px;top:290px"></div>
            <div id="divChart3" style="width:480px;height:280px;display:inline-block;position:absolute;left:750px;top:290px"></div>
        </div>
        <script language="javascript">
            $(document).ready(function ($) {
                initialize();
            });
        </script>
    </div>
</body>
</html>