/// <reference path="./Intellisense/cfx.visualStudio.js" />

function doInitializeDash()
{
    var title;

    // Sales Overview

    title = new cfx.gauge.Title();
    title.setText("Active Support Technicians");
    border1.getTitles().add(title);
    var items = populateActiveSupportTechnicians();
    doCreateTable("divTable1", items);

    title = new cfx.gauge.Title();
    title.setText("Technician's Average Rating");
    border2.getTitles().add(title);

    single1.getCurrent().setValue(33443.38);
    single1.getCurrent().setScaleUnit(1);
    single1.getCurrent().setFormat(cfx.gauge.TrendFormat.Currency);
    title = new cfx.gauge.Title();
    title.setText("Service Invoices (YTD)");
    single1.getTitles().add(title);

    single2.getCurrent().setValue(88.47);
    single2.getCurrent().setFormat(cfx.gauge.TrendFormat.Currency);
    title = new cfx.gauge.Title();
    title.setText("Average Invoice");
    single2.getTitles().add(title);


    // Work Hours per Employee
    chart1.setGallery(cfx.Gallery.Area);
    populateWorkHours(chart1);
    chart1.getView3D().setEnabled(true);
    title = new cfx.TitleDockable();
    title.setText("History of Work Hours per Employee");
    chart1.getTitles().add(title);

    // Workload
    chart2.getDataSourceSettings().setStyle(chart2.getDataSourceSettings().getStyle() | cfx.DataSourceStyles.Transpose);
    populateWorkload(chart2);
    var j = chart2.getData().getSeries();
    for (var i = 0; i < j; i++) {
        chart2.getSeries().getItem(i).setText("");
    }
    chart2.setGallery(cfx.Gallery.Doughnut);
    var doughnut = chart2.getGalleryAttributes();
    doughnut.setStacked(true);
    doughnut.setDoughnutThickness(30);
    var series;
    series = chart2.getSeries().getItem(0);
    series.setVolume(100);
    series = chart2.getSeries().getItem(1);
    series.setGallery(cfx.Gallery.Pie);
    series.setVolume(60);
    title = new cfx.TitleDockable();
    title.setText("Workload Distribution");
    chart2.getTitles().add(title);
    title = new cfx.TitleDockable();
    title.setText("Outer Ring: Previous Month");
    title.setDock(cfx.DockArea.Left);
    chart2.getTitles().add(title);
    title = new cfx.TitleDockable();
    title.setText("Inner Ring: Current Month");
    title.setDock(cfx.DockArea.Left);
    chart2.getTitles().add(title);

    // Estimation Accuracy and Rating

    chart3.setGallery(cfx.Gallery.Bar);
    populateDurationRating(chart3);
    chart3.getLegendBox().getItemAttributes().getItem(chart3.getSeries(), -1).setVisible(false);
    chart3.getAxisY().getTitle().setText("Hours");
    chart3.getAxisX().setVisible(false);
    var mainPane = chart3.getPanes().getItem(0);
    mainPane.getTitle().setText("Delivered Time");
    var pane = new cfx.Pane();
    pane.getTitle().setText("Customer Rating");
    chart3.getPanes().add(pane);
    chart3.getSeries().getItem(1).setPane(pane);
    title = new cfx.TitleDockable();
    title.setText("Estimation Accuracy and Job Rating (Last 20 Jobs)");
    chart3.getTitles().add(title);
    var conditionals = chart3.getConditionalAttributes();
    var conditional = new cfx.ConditionalAttributes();
    conditional.getCondition().setFrom(0);
    conditional.getCondition().setTo(1000);
    conditional.setText("Over Estimated");
    conditional.setTag("Attribute4"); // For illustration purposes only. Replace this tag wih your custom css class
    conditional.setSeries(0);
    conditional.setColor("#FFFFFF");
    conditionals.add(conditional);
    conditional = new cfx.ConditionalAttributes();
    conditional.getCondition().setFrom(-1000);
    conditional.getCondition().setTo(0);
    conditional.setText("Under Estimated");
    conditional.setTag("Attribute5"); // For illustration purposes only. Replace this tag wih your custom css class
    conditional.setSeries(0);
    conditional.setColor("#44FFFFFF");
    conditionals.add(conditional);

    doConfigureHorizontal(horizontal1,7);
    doConfigureHorizontal(horizontal2,8);
    doConfigureHorizontal(horizontal3,4);
    doConfigureHorizontal(horizontal4,5);
}

function doConfigureHorizontal(horizontal,value)
{
    var scale = horizontal.getMainScale();
    scale.setThickness(0.7);
    scale.setPosition(0.15);
    scale.setMax(10);
    horizontal.setMainValue(value);
}

function populateActiveSupportTechnicians()
{
    var items = [
  {
      "Id": 15,
      "FullName": "Ben Burton",
      "MainPhone": "555-1009"
  },
  {
      "Id": 16,
      "FullName": "Chris Perry",
      "MainPhone": "555-1010"
  },
  {
      "Id": 21,
      "FullName": "Judith Walker",
      "MainPhone": "555-1012"
  },
  {
      "Id": 22,
      "FullName": "Matthew Pereira",
      "MainPhone": "555-1011"
  }
    ];

    return (items);
}

function populateWorkHours(chart)
{
    var items = [
  {
      "Date": "2007-01-01",
      "Judith": 49,
      "Matthew": 15,
      "Chris": 68,
      "Ben": 92
  },
  {
      "Date": "2007-02-01",
      "Judith": 33,
      "Matthew": 36,
      "Chris": 49,
      "Ben": 83
  },
  {
      "Date": "2007-03-01",
      "Judith": 24,
      "Matthew": 45,
      "Chris": 69,
      "Ben": 71
  },
  {
      "Date": "2007-04-01",
      "Judith": 59,
      "Matthew": 39,
      "Chris": 64,
      "Ben": 66
  },
  {
      "Date": "2007-05-01",
      "Judith": 26,
      "Matthew": 46,
      "Chris": 64,
      "Ben": 82
  },
  {
      "Date": "2007-06-01",
      "Judith": 45,
      "Matthew": 19,
      "Chris": 78,
      "Ben": 83
  },
  {
      "Date": "2007-07-01",
      "Judith": 60,
      "Matthew": 37,
      "Chris": 68,
      "Ben": 58
  },
  {
      "Date": "2007-08-01",
      "Judith": 68,
      "Matthew": 34,
      "Chris": 45,
      "Ben": 71
  },
  {
      "Date": "2007-09-01",
      "Judith": 42,
      "Matthew": 44,
      "Chris": 57,
      "Ben": 57
  },
  {
      "Date": "2007-10-01",
      "Judith": 41,
      "Matthew": 47,
      "Chris": 75,
      "Ben": 47
  },
  {
      "Date": "2007-11-01",
      "Judith": 38,
      "Matthew": 24,
      "Chris": 63,
      "Ben": 71
  },
  {
      "Date": "2007-12-01",
      "Judith": 38,
      "Matthew": 23,
      "Chris": 56,
      "Ben": 89
  }
    ];

    chart.setDataSource(items);
}

function populateWorkload(chart)
{
    var items = [
  {
      "Date": "2007-01-01",
      "Judith": 49,
      "Matthew": 15,
      "Chris": 68,
      "Ben": 92
  },
  {
      "Date": "2007-02-01",
      "Judith": 33,
      "Matthew": 36,
      "Chris": 49,
      "Ben": 83
  }
    ];

    var fields = chart.getDataSourceSettings().getFields();
    var field = new cfx.FieldMap();
    field.setName("Date");
    field.setUsage(cfx.FieldUsage.Label);
    fields.add(field);
    field = new cfx.FieldMap();
    field.setName("Judith");
    field.setUsage(cfx.FieldUsage.Value);
    fields.add(field);
    field = new cfx.FieldMap();
    field.setName("Matthew");
    field.setUsage(cfx.FieldUsage.Value);
    fields.add(field);
    field = new cfx.FieldMap();
    field.setName("Chris");
    field.setUsage(cfx.FieldUsage.Value);
    fields.add(field);
    field = new cfx.FieldMap();
    field.setName("Ben");
    field.setUsage(cfx.FieldUsage.Value);
    fields.add(field);
    chart.setDataSource(items);
}

function populateDurationRating(chart)
{
    var items = [
  {
      "Duration": 17,
      "Rating": 9
  },
  {
      "Duration": 3,
      "Rating": 6
  },
  {
      "Duration": 3,
      "Rating": 6
  },
  {
      "Duration": 1,
      "Rating": 8
  },
  {
      "Duration": 15,
      "Rating": 5
  },
  {
      "Duration": -1,
      "Rating": 9
  },
  {
      "Duration": 3,
      "Rating": 5
  },
  {
      "Duration": 1,
      "Rating": 9
  },
  {
      "Duration": 1,
      "Rating": 9
  },
  {
      "Duration": 2,
      "Rating": 9
  },
  {
      "Duration": 3,
      "Rating": 9
  },
  {
      "Duration": 15,
      "Rating": 9
  },
  {
      "Duration": 0,
      "Rating": 9
  },
  {
      "Duration": -1,
      "Rating": 8
  },
  {
      "Duration": -9,
      "Rating": 6
  },
  {
      "Duration": 1,
      "Rating": 9
  },
  {
      "Duration": 15,
      "Rating": 5
  },
  {
      "Duration": 2,
      "Rating": 6
  },
  {
      "Duration": 16,
      "Rating": 9
  },
  {
      "Duration": 0,
      "Rating": 9
  }
    ];

    chart.setDataSource(items);
}

function doCreateTable(tableContainer, items) {
    $('#' + tableContainer).append('<table></table>');
    var table = $('#' + tableContainer).children();
    var cols = GetHeaders(items);
    var th = "<thead><tr>";
    for (var i = 0; i < cols.length; i++) {
        th += "<th>" + cols[i] + "</th>";
    }
    th += "</tr></thead>";
    table.append(th);

    for (var j = 0; j < items.length; j++) {
        var row = items[j];
        var tr = $('<tr></tr>');
        for (var k = 0; k < cols.length; k++) {
            var columnName = cols[k];
            tr.append('<td>' + row[columnName] + '</td>');
        }
        table.append(tr);
    }
}

function GetHeaders(obj) {
    var cols = new Array();
    var p = obj[0];
    for (var key in p) {
        cols.push(key);
    }
    return cols;
}