<!DOCTYPE html>
<html>
<head>
	<% String appURL = Support.Misc.getAppURL(request) ;  %>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.system.js"></script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.coreVector.js"></script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.coreVector3d.js">  </script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.advanced.js"></script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.gauge.js"></script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/motifs/jchartfx.motif.aurora.js"></script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.animation.js"></script>
    <script type="text/javascript">
        var chart1, chart2, chart3;
        var border1, border2;
        var single1, single2;
        var horizontal1, horizontal2, horizontal3, horizontal4;

        function initialize() {
            chart1 = new cfx.Chart();

            chart2 = new cfx.Chart();
            chart2.getAnimations().getLoad().setEnabled(true);
            chart3 = new cfx.Chart();
            chart3.getAnimations().getLoad().setEnabled(true);

            border1 = new cfx.gauge.Border();
            border2 = new cfx.gauge.Border();

            single1 = new cfx.gauge.Trend("single");
            single2 = new cfx.gauge.Trend("single");

            horizontal1 = new cfx.gauge.HorizontalGauge("group");
            horizontal1.getAnimations().getLoad().setEnabled(true);
            horizontal2 = new cfx.gauge.HorizontalGauge("group");
            horizontal2.getAnimations().getLoad().setEnabled(true);
            horizontal3 = new cfx.gauge.HorizontalGauge("group");
            horizontal3.getAnimations().getLoad().setEnabled(true);
            horizontal4 = new cfx.gauge.HorizontalGauge("group");
            horizontal4.getAnimations().getLoad().setEnabled(true);

            doInitializeDash();

            chart1.create('divChart1');
            chart2.create('divChart2');
            chart3.create('divChart3');

            border1.create('divBorder1');
            border2.create('divBorder2');
            single1.create('divSingle1');
            single2.create('divSingle2');

            horizontal1.create('divHorizontal1');
            horizontal2.create('divHorizontal2');
            horizontal3.create('divHorizontal3');
            horizontal4.create('divHorizontal4');
        }
    </script>
    <script type="text/javascript" src="SampleDashboard3.js">
    </script>
    <link rel="stylesheet" type="text/css" href="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/styles/Attributes/jchartfx.attributes.aurora.css" />
    <link rel="stylesheet" type="text/css" href="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/styles/Palettes/jchartfx.palette.aurora.css" />
</head>
<body class="jchartfx_body">
    <div class="jchartfx_container">
        <h1>SmartValue Technician Analysis Dashboard Sample <a href="http://support.softwarefx.com/jChartFX/article/1500134" class="smallLabel"> How do I remove the jChartFX watermark?</a></h1>
        <div id="myDash" style="width:1200px;height:620px;display:inline-block;position:relative">
            <div id="divBorder1" style="width:490px;height:275px;display:inline-block;position:absolute;left:0px;top:0px"></div>
            <div id="divTable1" style="width:490px;height:275px;display:inline-block;position:absolute;left:0px;top:0px"></div>
            <div id="divSingle1" style="width:240px;height:130px;display:inline-block;position:absolute;left:500px;top:0px"></div>
            <div id="divSingle2" style="width:240px;height:130px;display:inline-block;position:absolute;left:500px;top:145px"></div>
            <div id="divBorder2" style="width:380px;height:275px;display:inline-block;position:absolute;left:750px;top:0px"></div>
            <div id="divText1" class="smallLabel" style="width:180px;height:65px; display:inline-block;position:absolute;left:770px;top:5px;display:table;text-align:left"><span style="display:table-cell;vertical-align:middle">Ben Burton</span></div>
            <div id="divHorizontal1" style="width:240px;height:65px;display:inline-block;position:absolute;left:870px;top:5px"></div>
            <div id="divText2" class="smallLabel" style="width: 180px; height: 65px; display: inline-block; position: absolute; left: 770px; top: 63px; display: table; text-align: left"><span style="display:table-cell;vertical-align:middle">Chris Perry</span></div>
            <div id="divHorizontal2" style="width:240px;height:65px;display:inline-block;position:absolute;left:870px;top:63px"></div>
            <div id="divText3" class="smallLabel" style="width: 180px; height: 65px; display: inline-block; position: absolute; left: 770px; top: 121px; display: table; text-align: left"><span style="display:table-cell;vertical-align:middle">Judith Walker</span></div>
            <div id="divHorizontal3" style="width:240px;height:65px;display:inline-block;position:absolute;left:870px;top:121px"></div>
            <div id="divText4" class="smallLabel" style="width: 180px; height: 65px; display: inline-block; position: absolute; left: 770px; top: 179px; display: table; text-align: left"><span style="display:table-cell;vertical-align:middle">Matthew Pereira</span></div>
            <div id="divHorizontal4" style="width:240px;height:65px;display:inline-block;position:absolute;left:870px;top:179px"></div>
            <div id="divChart1" style="width:490px;height:270px;display:inline-block;position:absolute;left:0px;top:575px"></div>
            <div id="divChart2" style="width:490px;height:280px;display:inline-block;position:absolute;left:0px;top:285px"></div>
            <div id="divChart3" style="width:630px;height:560px;display:inline-block;position:absolute;left:500px;top:285px"></div>
        </div>
        <script language="javascript">
            $(document).ready(function ($) {
                initialize();
            });
        </script>
    </div>
</body>
</html>