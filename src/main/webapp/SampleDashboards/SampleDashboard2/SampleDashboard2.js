/// <reference path="./Intellisense/cfx.visualStudio.js" />

var USmap, stateMap;
var currentState = "";

function doInitializeDash()
{
    var title;

    // Sales Overview

    title = new cfx.gauge.Title();
    title.setText("Sales Today");
    border1.getTitles().add(title);

    title = new cfx.gauge.Title();
    title.setText("Sales this Week");
    border2.getTitles().add(title);

    trend1.setLayout(cfx.gauge.TrendLayout.Vertical);
    trend1.getCurrent().setValue(247677);
    trend1.getCurrent().setScaleUnit(1);
    trend1.getCurrent().setFormat(cfx.gauge.TrendFormat.Currency);
    trend1.getReference().setValue(220175);
    title = new cfx.gauge.Title();
    title.setText("Sales this Month");
    trend1.getTitles().add(title);

    trend2.setLayout(cfx.gauge.TrendLayout.Vertical);
    trend2.getCurrent().setValue(1328531);
    trend2.getCurrent().setScaleUnit(1);
    trend2.getCurrent().setFormat(cfx.gauge.TrendFormat.Currency);
    trend2.getReference().setValue(1913695);
    title = new cfx.gauge.Title();
    title.setText("Sales this Year");
    trend2.getTitles().add(title);


    // Total Yearly Sales
    chart1.setGallery(cfx.Gallery.Bar);
    populateYearlySales(chart1);
    chart1.getLegendBox().setVisible(false);
    chart1.getAllSeries().getPointLabels().setVisible(true);
    chart1.getAxisY().getDataFormat().setDecimals(2);
    title = new cfx.TitleDockable();
    title.setText("Total Yearly Sales");
    chart1.getTitles().add(title);
    title = new cfx.TitleDockable();
    title.setText("In Millions");
    title.setDock(cfx.DockArea.Left);
    chart1.getTitles().add(title);


    // Monthly Sales per Year
    var heatmap = new cfx.heatmap.HeatMap();
    chart2.setGalleryAttributes(heatmap);
    populateMonthlySalesPerYear(chart2);
    chart2.getAxisX().setStaggered(true);
    
    title = new cfx.TitleDockable();
    title.setText("Monthly Sales per Year");
    chart2.getTitles().add(title);

    // US Sales Maps
    USmap = new cfx.maps.Map();
    USmap.setPath("Maps/US/Continental States.cmf");
    map2.setGalleryAttributes(USmap);
    addCondition(map2, 0, 25000, "#FF0000", "#FF0000", "0 - 25K",false);
    addCondition(map2, 25000, 50000, "#A2C975", "#C1DBA3", "25K - 50K",false);
    addCondition(map2, 50000, 75000, "#B18CBC", "#D0BAD6", "50K - 75K",false);
    addCondition(map2, 75000, 500000, "#90AB97", "#B6C7BA", "75K+",false);
    var mapLayer;
    mapLayer = USmap.getLayers().find("@main");
    if (mapLayer == undefined) {
        mapLayer = new cfx.maps.MapLayer();
        mapLayer.setPath("@main");
        USmap.getLayers().add(mapLayer);
    }
    mapLayer.setFieldLabel("NAME");
    mapLayer.setFieldName("STUSPS");  
    populateUSSales(map2);
    map2.getLegendBox().getItemAttributes().getItem(map2.getSeries(), -1).setVisible(false);
    title = new cfx.TitleDockable();
    title.setText("US Sales Distribution");
    map2.getTitles().add(title);
    title = new cfx.TitleDockable();
    title.setText("Click on any State for details by county");
    title.setDock(cfx.DockArea.Bottom);
    map2.getTitles().add(title);

    // State Map
    stateMap = new cfx.maps.Map();
    map1.setGalleryAttributes(stateMap);
    map1.getLegendBox().setDock(cfx.DockArea.Bottom);
    map1.getLegendBox().setContentLayout(cfx.ContentLayout.Center);
    addCondition(map1, 0, 5, "#FF0000", "#FF0000", null, true);
    addCondition(map1, 5, 10, "#FF0000", "#FF0000", null, true);
    addCondition(map1, 10, 25, "#FF0000", "#FF0000", null, true);
    addCondition(map1, 25, 50, "#A2C975", "#C1DBA3", null, true);
    addCondition(map1, 50, 75, "#B18CBC", "#D0BAD6", null, true);
    addCondition(map1, 75, 100, "#90AB97", "#B6C7BA", null, true);
    map1.getLegendBox().getItemAttributes().getItem(map2.getSeries(), -1).setVisible(false);
    title = new cfx.TitleDockable();
    map1.getTitles().add(title);
    changeMap(map1, "FL", "Florida");
    $("#divMap2").click(function (evt) {
        onMapClick(evt);
    });
}

function onMapClick(evt)
{
    if (evt.hitType == cfx.HitType.Point) {
        var shape = evt.targetObject;
        var stateAbbr = "";
        var state = "";

        stateAbbr = shape.getFieldValue("STUSPS");
        state = shape.getFieldValue("NAME");
        changeMap(map1, stateAbbr, state);

        return;
    }
}

function changeMap(chartMap,stateAbbr,state)
{
    if (stateAbbr != currentState) {
        var path = "Maps/US/" + stateAbbr + "_Counties.cmf";
        stateMap.setPath(path);
        populateStateSales(chartMap, stateAbbr);
        currentState = stateAbbr;
        var title = chartMap.getTitles().getItem(0);
        title.setText(state + " Percentile Sales Distribution by County");
    }
}

function populateYearlySales(chart)
{

    var items = [
      {
          "Total": 2.80,
          "Year": 2010
      },
      {
          "Total": 2.77,
          "Year": 2011
      },
      {
          "Total": 2.52,
          "Year": 2012
      },
      {
          "Total": 1.91,
          "Year": 2013
      },
      {
          "Total": 1.32,
          "Year": 2014
      }
    ];

    var fields = chart.getDataSourceSettings().getFields();
    var field = new cfx.FieldMap();
    field.setName("Year");
    field.setUsage(cfx.FieldUsage.Label);
    fields.add(field);
    field = new cfx.FieldMap();
    field.setName("Total");
    field.setUsage(cfx.FieldUsage.Value);
    fields.add(field);

    chart.setDataSource(items);
}

function populateMonthlySalesPerYear(chart)
{
    var items = [
  {
      "Month": "Jan",
      "2010": 159710.14,
      "2011": 128766.40,
      "2012": 101131.86,
      "2013": 165698.97,
      "2014": 153532.41
  },
  {
      "Month": "Feb",
      "2010": 123940.40,
      "2011": 180037.00,
      "2012": 127970.98,
      "2013": 154150.31,
      "2014": 189528.51
  },
  {
      "Month": "Mar",
      "2010": 192728.93,
      "2011": 216411.84,
      "2012": 189344.07,
      "2013": 131982.92,
      "2014": 250000.00
  },
  {
      "Month": "Apr",
      "2010": 228273.64,
      "2011": 100000.00,
      "2012": 173235.57,
      "2013": 204594.03,
      "2014": 131854.38
  },
  {
      "Month": "May",
      "2010": 138152.98,
      "2011": 117768.92,
      "2012": 112904.76,
      "2013": 109341.98,
      "2014": 149051.34
  },
  {
      "Month": "Jun",
      "2010": 219902.59,
      "2011": 139948.51,
      "2012": 138902.15,
      "2013": 181871.52,
      "2014": 210786.95
  },
  {
      "Month": "Jul",
      "2010": 191406.10,
      "2011": 223817.05,
      "2012": 199352.78,
      "2013": 213874.40,
      "2014": 241976.01
  },
  {
      "Month": "Aug",
      "2010": 193003.85,
      "2011": 211518.99,
      "2012": 185637.34,
      "2013": 149504.23,
      "2014": null
  },
  {
      "Month": "Sep",
      "2010": 121699.18,
      "2011": 131387.78,
      "2012": 127797.00,
      "2013": 138477.35,
      "2014": null
  },
  {
      "Month": "Oct",
      "2010": 107308.89,
      "2011": 100703.38,
      "2012": 109803.65,
      "2013": 134047.13,
      "2014": null
  },
  {
      "Month": "Nov",
      "2010": 109255.39,
      "2011": 147278.02,
      "2012": 105030.74,
      "2013": 124784.55,
      "2014": null
  },
  {
      "Month": "Dec",
      "2010": 199854.34,
      "2011": 210601.25,
      "2012": 241025.34,
      "2013": 188141.43,
      "2014": null
  }
    ];

    chart.setDataSource(items);

}

function populateUSSales(chart)
{

    var items = [
      {
          "State": "AK",
          "Total": 13615.94
      },
      {
          "State": "AL",
          "Total": 62434.48
      },
      {
          "State": "AR",
          "Total": 27741.13
      },
      {
          "State": "AZ",
          "Total": 34562.51
      },
      {
          "State": "CA",
          "Total": 295367.64
      },
      {
          "State": "CO",
          "Total": 44828.90
      },
      {
          "State": "CT",
          "Total": 25175.55
      },
      {
          "State": "DE",
          "Total": 9499.26
      },
      {
          "State": "FL",
          "Total": 185372.43
      },
      {
          "State": "GA",
          "Total": 90374.48
      },
      {
          "State": "HI",
          "Total": 8472.06
      },
      {
          "State": "IA",
          "Total": 39559.12
      },
      {
          "State": "ID",
          "Total": 15660.88
      },
      {
          "State": "IL",
          "Total": 95129.82
      },
      {
          "State": "IN",
          "Total": 57024.08
      },
      {
          "State": "KS",
          "Total": 33733.16
      },
      {
          "State": "KY",
          "Total": 22454.45
      },
      {
          "State": "LA",
          "Total": 46915.85
      },
      {
          "State": "MA",
          "Total": 47043.54
      },
      {
          "State": "MD",
          "Total": 40706.91
      },
      {
          "State": "ME",
          "Total": 9315.32
      },
      {
          "State": "MI",
          "Total": 83447.78
      },
      {
          "State": "MN",
          "Total": 30585.47
      },
      {
          "State": "MO",
          "Total": 64018.33
      },
      {
          "State": "MS",
          "Total": 20880.97
      },
      {
          "State": "MT",
          "Total": 2150.43
      },
      {
          "State": "NC",
          "Total": 57409.02
      },
      {
          "State": "ND",
          "Total": 2998.12
      },
      {
          "State": "NE",
          "Total": 8929.33
      },
      {
          "State": "NH",
          "Total": 5544.16
      },
      {
          "State": "NJ",
          "Total": 47640.13
      },
      {
          "State": "NM",
          "Total": 7048.40
      },
      {
          "State": "NV",
          "Total": 11663.97
      },
      {
          "State": "NY",
          "Total": 166012.52
      },
      {
          "State": "OH",
          "Total": 70312.34
      },
      {
          "State": "OK",
          "Total": 28840.71
      },
      {
          "State": "OR",
          "Total": 33185.50
      },
      {
          "State": "PA",
          "Total": 78313.71
      },
      {
          "State": "RI",
          "Total": 9341.52
      },
      {
          "State": "SC",
          "Total": 40589.54
      },
      {
          "State": "SD",
          "Total": 1112.76
      },
      {
          "State": "TN",
          "Total": 54457.62
      },
      {
          "State": "TX",
          "Total": 211827.80
      },
      {
          "State": "UT",
          "Total": 13469.01
      },
      {
          "State": "VA",
          "Total": 41770.54
      },
      {
          "State": "VT",
          "Total": 10058.08
      },
      {
          "State": "WA",
          "Total": 102030.68
      },
      {
          "State": "WI",
          "Total": 64719.25
      },
      {
          "State": "WV",
          "Total": 16405.64
      },
      {
          "State": "WY",
          "Total": 1309.60
      }
    ];

    chart.setDataSource(items);

}

function addCondition(chartMap, from, to, color, borderColor, title, isPercentage) {
    var condAttr = new cfx.ConditionalAttributes();
    condAttr.getCondition().setFrom(from);
    condAttr.getCondition().setTo(to);
    if (isPercentage)
        condAttr.getCondition().setUnit(cfx.ConditionUnit.Percentage);
    condAttr.getBorder().setColor("#FFFFFF");
    if (title == null) {
        if (isPercentage)
            title = from + "% - " + to + "%";
        else
            title = from + " - " + to;
    }

    condAttr.setText(title);
    chartMap.getConditionalAttributes().add(condAttr);
}