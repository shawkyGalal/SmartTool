<!DOCTYPE html>
<html>
<head>
    <% String appURL = Support.Misc.getAppURL(request) ;  %>


   <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.system.js"></script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.coreVector.js"></script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.advanced.js"></script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.gauge.js"></script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.treemap.js">    </script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.sparkline.js">    </script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/motifs/jchartfx.motif.whitespace.js">    </script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.animation.js"></script>
   
    <script type="text/javascript">
        var chart1, chart2, chart3, chart4, chart5;
        var radial1;
        var single1, single2, single3, trend1;
        var border1, border2, border3, border4;
        var horizontal1, horizontal2, horizontal3, horizontal4, horizontal5, horizontal6;

        function initialize() {
            chart1 = new cfx.Chart();
            chart1.getAnimations().getLoad().setEnabled(true);
            chart2 = new cfx.Chart();
            chart2.getAnimations().getLoad().setEnabled(true);
            chart3 = new cfx.Chart();
            chart3.getAnimations().getLoad().setEnabled(true);
            chart4 = new cfx.Chart("group");
            chart4.getAnimations().getLoad().setEnabled(true);
            chart5 = new cfx.Chart("group");
            chart5.getAnimations().getLoad().setEnabled(true);

            radial1 = new cfx.gauge.RadialGauge("progress");

            border1 = new cfx.gauge.Border();
            border2 = new cfx.gauge.Border();
            border3 = new cfx.gauge.Border();
            border4 = new cfx.gauge.Border();

            single1 = new cfx.gauge.Trend("single");
            single2 = new cfx.gauge.Trend("single");
            single3 = new cfx.gauge.Trend("single-group");
            trend1 = new cfx.gauge.Trend("group");

            horizontal1 = new cfx.gauge.HorizontalGauge("single-bullet-sections:1.5,3.5,5-group");
            horizontal1.getAnimations().getLoad().setEnabled(true);
            horizontal2 = new cfx.gauge.HorizontalGauge("single-bullet-sections:1.5,3.5,5-group");
            horizontal2.getAnimations().getLoad().setEnabled(true);
            horizontal3 = new cfx.gauge.HorizontalGauge("single-bullet-sections:1.5,3.5,5-group");
            horizontal3.getAnimations().getLoad().setEnabled(true);
            horizontal4 = new cfx.gauge.HorizontalGauge("single-bullet-sections:1.5,3.5,5-group");
            horizontal4.getAnimations().getLoad().setEnabled(true);
            horizontal5 = new cfx.gauge.HorizontalGauge("single-bullet-sections:1.5,3.5,5-group");
            horizontal5.getAnimations().getLoad().setEnabled(true);
            horizontal6 = new cfx.gauge.HorizontalGauge("single-bullet-group");
            horizontal6.getAnimations().getLoad().setEnabled(true);

            doInitializeDash();

            chart1.create('divChart1');
            chart2.create('divChart2');
            chart3.create('divChart3');
            chart4.create('divChart4');
            chart5.create('divChart5');

            radial1.create('divRadial1');

            single1.create('divSingle1');
            single2.create('divSingle2');
            single3.create('divSingle3');
            trend1.create('divTrend1');

            border1.create('divBorder1');
            border2.create('divBorder2');
            border3.create('divBorder3');
            border4.create('divBorder4');

            horizontal1.create('divHorizontal1');
            horizontal2.create('divHorizontal2');
            horizontal3.create('divHorizontal3');
            horizontal4.create('divHorizontal4');
            horizontal5.create('divHorizontal5');
            horizontal6.create('divHorizontal6');
        }
    </script>
    <script type="text/javascript" src="SampleDashboard4.js">
    </script>
    <link rel="stylesheet" type="text/css" href="http://www.jchartfx.com/libs/v7/current/styles/Attributes/jchartfx.attributes.whitespace.css" />
    <link rel="stylesheet" type="text/css" href="http://www.jchartfx.com/libs/v7/current/styles/Palettes/jchartfx.palette.whitespace.css" />
</head>
<body class="jchartfx_body">
    <div class="jchartfx_container">
        <h1>jChartFX+ Healthcare Dashboard Sample <a href="http://support.softwarefx.com/jChartFX/article/1500134" class="smallLabel"> How do I remove the jChartFX watermark?</a></h1>
        <div id="myDash" style="width:1200px;height:620px;display:inline-block;position:relative">
            <div id="divChart1" style="width:490px;height:360px;display:inline-block;position:absolute;left:0px;top:0px"></div>
            <div id="divChart2" style="width:600px;height:360px;display:inline-block;position:absolute;left:500px;top:0px"></div>
            <div id="divChart3" style="width:490px;height:280px;display:inline-block;position:absolute;left:0px;top:370px"></div>
            <div id="divRadial1" style="width:295px;height:280px;display:inline-block;position:absolute;left:500px;top:370px"></div>
            <div id="divSingle1" style="width:295px;height:135px;display:inline-block;position:absolute;left:805px;top:370px"></div>
            <div id="divSingle2" style="width:295px;height:135px;display:inline-block;position:absolute;left:805px;top:515px"></div>
            <div id="divBorder1" style="width:600px;height:420px;display:inline-block;position:absolute;left:500px;top:660px"></div>
            <div id="divText1" class="label" style="width:240px; height: 80px; display: inline-block; position: absolute; left: 515px; top: 700px; display: table"><span style="display:table-cell;vertical-align:middle">Overall Satisfaction</span></div>
            <div id="divHorizontal1" style="width: 300px; height: 80px; display: inline-block; position: absolute; left: 770px; top: 700px"></div>
            <div id="divText2" class="label" style="width:240px; height: 80px; display: inline-block; position: absolute; left: 515px; top: 770px; display: table"><span style="display:table-cell;vertical-align:middle">Pain Management</span></div>
            <div id="divHorizontal2" style="width: 300px; height: 80px; display: inline-block; position: absolute; left: 770px; top: 770px"></div>
            <div id="divText3" class="label" style="width:240px; height: 80px; display: inline-block; position: absolute; left: 515px; top: 840px; display: table"><span style="display:table-cell;vertical-align:middle">Quality of Meals</span></div>
            <div id="divHorizontal3" style="width: 300px; height: 80px; display: inline-block; position: absolute; left: 770px; top: 840px"></div>
            <div id="divText4" class="label" style="width:240px; height: 80px; display: inline-block; position: absolute; left: 515px; top: 910px; display: table"><span style="display:table-cell;vertical-align:middle">Cleanliness</span></div>
            <div id="divHorizontal4" style="width: 300px; height: 80px; display: inline-block; position: absolute; left: 770px; top: 910px"></div>
            <div id="divText5" class="label" style="width:240px; height: 80px; display: inline-block; position: absolute; left: 515px; top: 980px; display: table"><span style="display:table-cell;vertical-align:middle">Courtesy of Staff</span></div>
            <div id="divHorizontal5" style="width: 300px; height: 80px; display: inline-block; position: absolute; left: 770px; top: 980px"></div>
            <div id="divBorder2" style="width:600px;height:150px;display:inline-block;position:absolute;left:500px;top:1090px"></div>
            <div id="divTrend1" style="width:90px;height:90px;display:inline-block;position:absolute;left:520px;top:1140px"></div>
            <div id="divChart4" style="width:510px;height:70px;display:inline-block;position:absolute;left:610px;top:1140px"></div>
            <div id="divBorder3" style="width:490px;height:420px;display:inline-block;position:absolute;left:0px;top:660px"></div>
            <select id="select1" style="width:450px;height:30px;display:inline-block;position:absolute;left:20px;top:710px" onchange="javascript:selectDoctor();"></select>            
            <div id="divChart5" style="width:450px;height:320px;display:inline-block;position:absolute;left:20px;top:740px"></div>
            <div id="divBorder4" style="width:490px;height:150px;display:inline-block;position:absolute;left:0px;top:1090px"></div>
            <div id="divSingle3" style="width:120px;height:100px;display:inline-block;position:absolute;left:20px;top:1120px"></div>
            <div id="divHorizontal6" style="width: 300px; height: 80px; display: inline-block; position: absolute; left: 160px; top: 1130px"></div>
        </div>
        <script language="javascript">
            $(document).ready(function ($) {
                initialize();
            });
        </script>
    </div>
</body>
</html>