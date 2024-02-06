<!DOCTYPE html>
<html>
<head>
	<% String appURL = Support.Misc.getAppURL(request) ;  %>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.system.js"></script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.coreVector.js"></script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.advanced.js"></script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.gauge.js"></script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.maps.js">  </script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.heatmap.js">  </script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/motifs/jchartfx.motif.hook.js">  </script>

	<script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.animation.js"></script>
    <script type="text/javascript" src="SampleDashboard1.js"></script>

    <script type="text/javascript" src="SampleStateSales.js">
    </script>
    <script type="text/javascript">
        var chart1, chart2
        var map1, map2;
        var border1, border2;
        var trend1, trend2;

        function initialize() {
            chart1 = new cfx.Chart();
			chart2 = new cfx.Chart();
            
            
            map1 = new cfx.Chart();
            map2 = new cfx.Chart();

            border1 = new cfx.gauge.Border();
            border2 = new cfx.gauge.Border();

            trend1 = new cfx.gauge.Trend();
            trend2 = new cfx.gauge.Trend();

            chart1.create('divChart1');
            chart2.create('divChart2');
            map1.create('divMap1');
            map2.create('divMap2');

            border1.create('divBorder1');
            border2.create('divBorder2');
            trend1.create('divTrend1');
            trend2.create('divTrend2');

            doInitializeDash();
        }
    </script>
    <script type="text/javascript" src="SampleDashboard2.js">
    </script>
    <link rel="stylesheet" type="text/css" href="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/styles/Attributes/jchartfx.attributes.hook.css" />
    <link rel="stylesheet" type="text/css" href="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/styles/Palettes/jchartfx.palette.hook.css" />
</head>
<body class="jchartfx_body">
    <div class="jchartfx_container">
        <h1>SmartValue Sales Overview Dashboard Sample <a href="http://support.softwarefx.com/jChartFX/article/1500134" class="smallLabel"> How do I remove the jChartFX watermark?</a></h1>
        <div id="myDash" style="width:1200px;height:620px;display:inline-block;position:relative">
            <div id="divBorder1" style="width:240px;height:100px;display:inline-block;position:absolute;left:0px;top:0px"></div>
            <div id="divText1" class="labelIndicator" style="width:240px;height:100px;display:inline-block;position:absolute;left:0px;top:0px">$5,025</div>
            <div id="divBorder2" style="width:240px;height:100px;display:inline-block;position:absolute;left:250px;top:0px"></div>
            <div id="divText2" class="labelIndicator" style="width:240px;height:100px;display:inline-block;position:absolute;left:250px;top:0px">$51,572</div>
            <div id="divTrend1" style="width:240px;height:160px;display:inline-block;position:absolute;left:0px;top:110px"></div>
            <div id="divTrend2" style="width:240px;height:160px;display:inline-block;position:absolute;left:250px;top:110px"></div>
            <div id="divChart1" style="width:490px;height:270px;display:inline-block;position:absolute;left:500px;top:0px"></div>
            <div id="divChart2" style="width:490px;height:280px;display:inline-block;position:absolute;left:0px;top:280px"></div>
            <div id="divMap1" style="width:490px;height:570px;display:inline-block;position:absolute;left:500px;top:280px"></div>
            <div id="divMap1Warning" class="smallLabel" style="width:490px;height:50px;display:inline-block;position:absolute;left:500px;top:320px">Is this map not loading? <a href="http://support.softwarefx.com/jChartFX/article/2501235#a0a18d2e-05bc-e311-8836-0019b9e6b500" target="_blank">Find out why...</a></div>
            <div id="divMap2" style="width:490px;height:280px;display:inline-block;position:absolute;left:0px;top:570px"></div>
            <div id="divMap1Warning" class="smallLabel" style="width:490px;height:50px;display:inline-block;position:absolute;left:0px;top:610px">Is this map not loading? <a href="http://support.softwarefx.com/jChartFX/article/2501235#a0a18d2e-05bc-e311-8836-0019b9e6b500" target="_blank">Find out why...</a></div>
            <div id="divMapMarketplace" class="smallLabel" style="width:950px;height:50px;display:inline-block;position:absolute;left:0px;top:870px">Do you need a different map? <a href="http://maps.softwarefx.com" target="_blank">Visit the Software FX Maps Marketplace for hundreds of maps</a></div>
        </div>
        <script language="javascript">
            $(document).ready(function ($) {
                initialize();
            });
        </script>
    </div>
</body>
</html>