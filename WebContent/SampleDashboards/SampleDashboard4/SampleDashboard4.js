/// <reference path="./Intellisense/cfx.visualStudio.js" />

function doInitializeDash()
{
    var title;

    // Admissions by Age Group
    chart1.setGallery(cfx.Gallery.Bar);
    populateAdmissionsByAgeGroup(chart1);
    chart1.getLegendBox().setVisible(false);
    title = new cfx.TitleDockable();
    title.setText("Admissions by Age Group");
    chart1.getTitles().add(title);

    // Admissions by Specialty
    var treemap = new cfx.treemap.TreeMap();
    chart2.setGalleryAttributes(treemap);
    populateAdmissionsBySpecialty(chart2);
    title = new cfx.TitleDockable();
    title.setText("Admissions by Specialty");
    chart2.getTitles().add(title);

    // Revenue/Patients per Year
    chart3.setGallery(cfx.Gallery.Bar);
    populateRevenue(chart3);
    chart3.getLegendBox().setDock(cfx.DockArea.Bottom);
    var series1 = chart3.getSeries().getItem(1);
    series1.setGallery(cfx.Gallery.Lines);
    series1.setAxisY(chart3.getAxisY2());
    series1.bringToFront();
    chart3.getAxisY2().getGrids().getMajor().setVisible(false);
    chart3.getAxisY2().getGrids().getMajor().setTickMark(cfx.TickMark.None);
    title = new cfx.TitleDockable();
    title.setText("Revenue/Patients per Year");
    chart3.getTitles().add(title);

    // Accounts Pending
    radial1.getMainScale().setMax(100);
    radial1.setMainValue(46.3);
    title = new cfx.gauge.Title();
    title.setText("Accounts Pending");
    radial1.getTitles().add(title);
    title = new cfx.gauge.Title();
    title.setText("%v%%");
    title.setY(0.5);
    title.setTag("TitleLarge");
    title.setFont("36pt");
    radial1.getTitles().add(title);

    // KPI's
    single1.getCurrent().setValue(7350000);
    single1.getCurrent().setFormat(cfx.gauge.TrendFormat.Currency);
    title = new cfx.gauge.Title();
    title.setText("YTD Invoices");
    single1.getTitles().add(title);

    single2.getCurrent().setValue(3345612);
    single2.getCurrent().setScaleUnit(1);
    single2.getCurrent().setFormat(cfx.gauge.TrendFormat.Currency);
    title = new cfx.gauge.Title();
    title.setText("Pending Collections");
    single2.getTitles().add(title);

    // Customer Service

    title = new cfx.gauge.Title();
    title.setText("Customer Service KPIs");
    border1.getTitles().add(title);

    horizontal1.setMainValue(3.9);
    horizontal1.getMainScale().getIndicators().getItem(1).setValue(4.5);

    horizontal2.setMainValue(3.61);
    horizontal2.getMainScale().getIndicators().getItem(1).setValue(3.5);

    horizontal3.setMainValue(4.16);
    horizontal3.getMainScale().getIndicators().getItem(1).setValue(4);

    horizontal4.setMainValue(3.83);
    horizontal4.getMainScale().getIndicators().getItem(1).setValue(4.25);

    horizontal5.setMainValue(3.9);
    horizontal5.getMainScale().getIndicators().getItem(1).setValue(4);

    title = new cfx.gauge.Title();
    title.setText("Overall Satisfaction Over Time");
    border2.getTitles().add(title);

    trend1.getCurrent().setValue(3.9);
    trend1.getReference().setValue(4.2);
    trend1.setLayout(cfx.gauge.TrendLayout.Square);
    trend1.getReference().setVisible(false);
    trend1.getDelta().setVisible(false);

    populateOverallSatisfaction(chart4);
    var sparkline = new cfx.sparkline.SparkLine();
    sparkline.setGallery(cfx.sparkline.SparkGallery.Line);
    sparkline.setLabelTemplate('<DataTemplate/>');
    chart4.getPlotAreaMargin().setTop(1);
    chart4.getPlotAreaMargin().setBottom(1);
    chart4.getPlotAreaMargin().setLeft(1);
    chart4.getPlotAreaMargin().setRight(1);
    chart4.setGalleryAttributes(sparkline);

    var items = getStaffRatings();
    for (var index = 0; index < items.length; index++) {
        var item = items[index]; // define the item that will be evaluated
        $('<option>').text(item.FirstName + " " + item.LastName).appendTo('#select1');
    }
    
    title = new cfx.gauge.Title();
    title.setText("Medical Staff Rating");
    border3.getTitles().add(title);    
    chart5.setGallery(cfx.Gallery.Gantt);
    chart5.getLegendBox().setVisible(false);
    chart5.getDataSourceSettings().setStyle(chart5.getDataSourceSettings().getStyle() | cfx.DataSourceStyles.Transpose);
    // Use chart to calculate average doctors rating
    chart5.setDataSource(items);
    var data = chart5.getData();
    var nPoints = data.getPoints();
    var nSeries = data.getSeries();
    var total = 0;
    for (var i = 0; i < nSeries; i++)
        for (var j = 0; j < nPoints; j++)
            total += data.getItem(i, j);
    var totalAverage = total / (nPoints * nSeries);
    single3.getReference().setValue(totalAverage);
    single3.getConditional().setAppliesTo(cfx.gauge.ConditionalApplies.PrimaryValue);
    horizontal6.getMainScale().setMax(10);
    horizontal6.getMainScale().getIndicators().getItem(1).setValue(totalAverage);
    horizontal6.getMainScale().getTickmarks().getMajor().setVisible(false);
    title = new cfx.gauge.Title();
    title.setText("Group Average: " + totalAverage.toFixed(2).toString());
    title.setY(0.9);
    horizontal6.getTitles().add(title);
    selectDoctor();
    title = new cfx.gauge.Title();
    title.setText("Average Rating");
    border4.getTitles().add(title);

}

function selectDoctor()
{
    var person = $('#select1').val();
    var index = person.indexOf(" ");
    if (index >= 0)
    {
        var firstname = person.substring(0, index);
        var lastname = person.substring(index + 1, person.length);
        var items = getPersonStaffRatings(firstname, lastname);
        chart5.setDataSource(items);
        var total = 0;
        var data = chart5.getData();
        var nPoints = data.getPoints();
        for(var i=0;i<nPoints;i++)
            total += data.getItem(0, i);
        var average = total / nPoints;
        single3.getCurrent().setValue(average);
        horizontal6.setMainValue(average);
    }
    chart5.getAxisY().setMax(10);
    
}

function populateAdmissionsByAgeGroup(chart)
{
    var items = [
  {
      "Description": "0-18, excl newborns",
      "Patients": 3478
  },
  {
      "Description": "18-44",
      "Patients": 5734
  },
  {
      "Description": "45-64",
      "Patients": 5306
  },
  {
      "Description": "65-74",
      "Patients": 2542
  },
  {
      "Description": "75-84",
      "Patients": 2668
  },
  {
      "Description": "85+",
      "Patients": 1507
  }
    ];

    chart.setDataSource(items);
}

function populateAdmissionsBySpecialty(chart)
{
    var items = [
  {
      "Description": "Cardiology",
      "Patients": 9543
  },
  {
      "Description": "OB-GYN",
      "Patients": 876
  },
  {
      "Description": "Ophthalmology",
      "Patients": 89
  },
  {
      "Description": "Orthopaedics",
      "Patients": 128
  },
  {
      "Description": "Plastic Surgery",
      "Patients": 76
  },
  {
      "Description": "Imaging & Diagnostics",
      "Patients": 2713
  },
  {
      "Description": "Cancer Treament Care",
      "Patients": 6754
  }
    ];

    chart.setDataSource(items);
}

function populateRevenue(chart)
{
    var items = [
  {
      "Year":"2005",
      "Patients": 890,
      "Revenue": 23455000
  },
  {
      "Year":"2006",
      "Patients": 1347,
      "Revenue": 35897000
  },
  {
      "Year":"2007",
      "Patients": 1576,
      "Revenue": 32450000
  },
  {
      "Year":"2008",
      "Patients": 1980,
      "Revenue": 39123000
  },
  {
      "Year":"2009",
      "Patients": 2358,
      "Revenue": 44210000
  },
  {
      "Year":"2010",
      "Patients": 2876,
      "Revenue": 51278000
  },
  {
      "Year":"2011",
      "Patients": 2951,
      "Revenue": 56321000
  },
  {
      "Year":"2012",
      "Patients": 3135,
      "Revenue": 67980000
  },
  {
      "Year":"2013",
      "Patients": 3675,
      "Revenue": 88350000
  },
  {
      "Year":"2014",
      "Patients": 3890,
      "Revenue": 102489000
  }
    ];

    chart.setDataSource(items);
}

function populateOverallSatisfaction(chart)
{
    var items = [
  {
    "SatisfactionRating": 2.4,
    "AvgMonth": "15/1/2011"
      },
      {
    "SatisfactionRating": 2.7,
    "AvgMonth": "15/2/2011"
      },
      {
    "SatisfactionRating": 2.5,
    "AvgMonth": "15/3/2011"
      },
      {
    "SatisfactionRating": 2.9,
    "AvgMonth": "15/4/2011"
      },
      {
    "SatisfactionRating": 3.2,
    "AvgMonth": "15/5/2011"
      },
      {
    "SatisfactionRating": 3,
    "AvgMonth": "15/6/2011"
      },
      {
    "SatisfactionRating": 3.1,
    "AvgMonth": "15/7/2011"
      },
      {
    "SatisfactionRating": 3.3,
    "AvgMonth": "15/8/2011"
      },
      {
    "SatisfactionRating": 3.9,
    "AvgMonth": "15/9/2011"
      },
      {
    "SatisfactionRating": 3.7,
    "AvgMonth": "15/10/2011"
      },
      {
    "SatisfactionRating": 3.6,
    "AvgMonth": "15/11/2011"
      },
      {
    "SatisfactionRating": 3.9,
    "AvgMonth": "15/12/2011"
      },
      {
    "SatisfactionRating": 4,
    "AvgMonth": "15/1/2012"
      },
      {
    "SatisfactionRating": 4.2,
    "AvgMonth": "15/2/2012"
      },
      {
    "SatisfactionRating": 4.3,
    "AvgMonth": "15/3/2012"
      },
      {
    "SatisfactionRating": 4.3,
    "AvgMonth": "15/4/2012"
      },
      {
    "SatisfactionRating": 4,
    "AvgMonth": "15/5/2012"
      },
      {
    "SatisfactionRating": 4.2,
    "AvgMonth": "15/6/2012"
      },
      {
    "SatisfactionRating": 4.7,
    "AvgMonth": "15/7/2012"
      },
      {
    "SatisfactionRating": 4.5,
    "AvgMonth": "15/8/2012"
      },
      {
    "SatisfactionRating": 4.6,
    "AvgMonth": "15/9/2012"
      },
      {
    "SatisfactionRating": 4.9,
    "AvgMonth": "15/10/2012"
      },
      {
    "SatisfactionRating": 4.8,
    "AvgMonth": "15/11/2012"
      },
      {
    "SatisfactionRating": 4.7,
    "AvgMonth": "15/12/2012"
      },
      {
    "SatisfactionRating": 4.3,
    "AvgMonth": "15/1/2013"
      },
      {
    "SatisfactionRating": 4.2,
    "AvgMonth": "15/2/2013"
      },
      {
    "SatisfactionRating": 3.8,
    "AvgMonth": "15/3/2013"
      },
      {
    "SatisfactionRating": 3.7,
    "AvgMonth": "15/4/2013"
      },
      {
    "SatisfactionRating": 3.4,
    "AvgMonth": "15/5/2013"
      },
      {
    "SatisfactionRating": 3.4,
    "AvgMonth": "15/6/2013"
      },
      {
    "SatisfactionRating": 3.5,
    "AvgMonth": "15/7/2013"
      },
      {
    "SatisfactionRating": 3.9,
    "AvgMonth": "15/8/2013"
      },
      {
    "SatisfactionRating": 3.8,
    "AvgMonth": "15/9/2013"
      },
      {
    "SatisfactionRating": 4,
    "AvgMonth": "15/10/2013"
      },
      {
    "SatisfactionRating": 4.2,
    "AvgMonth": "15/11/2013"
      },
      {
    "SatisfactionRating": 3.9,
    "AvgMonth": "15/12/2013"
      }
    ];

    chart.setDataSource(items);
}

function getPersonStaffRatings(firstname,lastname)
{
    var items = getStaffRatings();
    var expression = '(eq(item.FirstName, firstname) && eq(item.LastName, lastname))';

    var newitems = [];
    for (var index = 0; index < items.length; index++) {
        var item = items[index]; // define the item that will be evaluated
        if (eval(expression) == true) {
            newitems.push(item);
        }
    }

    return (newitems);
}

function getStaffRatings()
{
    var items = [
  {
      "FirstName": "John",
      "LastName": "Studdard",
      "Education": 8.75,
      "Certifications": 5.9,
      "Residency": 6.75,
      "Fellowships": 6.75,
      "Reputation": 8.85,
      "MedSchool": 7.25,
      "Experience": 7.25,
      "Training": 7.85,
      "Publications": 5.4
  },
  {
      "FirstName": "Amy",
      "LastName": "Kylako",
      "Education": 7.85,
      "Certifications": 8.5,
      "Residency": 8.7,
      "Fellowships": 5.25,
      "Reputation": 8.5,
      "MedSchool": 8.75,
      "Experience": 8.65,
      "Training": 4.25,
      "Publications": 3.2
  },
  {
      "FirstName": "Andrea",
      "LastName": "Rooney",
      "Education": 9.25,
      "Certifications": 7.65,
      "Residency": 9.25,
      "Fellowships": 7.85,
      "Reputation": 7.25,
      "MedSchool": 6.75,
      "Experience": 9.25,
      "Training": 5.25,
      "Publications": 5.75
  },
  {
      "FirstName": "Briana",
      "LastName": "Sommers",
      "Education": 7.25,
      "Certifications": 8.25,
      "Residency": 7.85,
      "Fellowships": 6.75,
      "Reputation": 9.55,
      "MedSchool": 9.25,
      "Experience": 8.25,
      "Training": 6.75,
      "Publications": 6.75
  },
  {
      "FirstName": "Carla",
      "LastName": "Ross",
      "Education": 9.75,
      "Certifications": 9,
      "Residency": 6.5,
      "Fellowships": 7.7,
      "Reputation": 9.1,
      "MedSchool": 7.75,
      "Experience": 7.75,
      "Training": 6.25,
      "Publications": 2.5
  },
  {
      "FirstName": "Edward",
      "LastName": "Carrol",
      "Education": 7.85,
      "Certifications": 9.25,
      "Residency": 8.9,
      "Fellowships": 7.25,
      "Reputation": 8.5,
      "MedSchool": 5.75,
      "Experience": 8.1,
      "Training": 7.25,
      "Publications": 3.45
  },
  {
      "FirstName": "Elizabeth",
      "LastName": "Garner",
      "Education": 8.75,
      "Certifications": 8.75,
      "Residency": 5.75,
      "Fellowships": 6.75,
      "Reputation": 8.75,
      "MedSchool": 6.75,
      "Experience": 9.25,
      "Training": 8.15,
      "Publications": 4.25
  },
  {
      "FirstName": "Jamie",
      "LastName": "Huss",
      "Education": 8.25,
      "Certifications": 7.75,
      "Residency": 7.75,
      "Fellowships": 3.5,
      "Reputation": 7.8,
      "MedSchool": 7.25,
      "Experience": 8.85,
      "Training": 9.1,
      "Publications": 5.25
  },
  {
      "FirstName": "Joanne",
      "LastName": "Anderson",
      "Education": 9.15,
      "Certifications": 6.75,
      "Residency": 8.5,
      "Fellowships": 5.5,
      "Reputation": 6.9,
      "MedSchool": 8.35,
      "Experience": 9.15,
      "Training": 8.7,
      "Publications": 3.75
  },
  {
      "FirstName": "Joe",
      "LastName": "Alterian",
      "Education": 9.35,
      "Certifications": 8.35,
      "Residency": 7.25,
      "Fellowships": 9.25,
      "Reputation": 8.5,
      "MedSchool": 6.25,
      "Experience": 6.75,
      "Training": 6.75,
      "Publications": 2.8
  },
  {
      "FirstName": "Laura",
      "LastName": "Flynn",
      "Education": 7.65,
      "Certifications": 9,
      "Residency": 6.5,
      "Fellowships": 8.25,
      "Reputation": 8.7,
      "MedSchool": 6.65,
      "Experience": 8.65,
      "Training": 6.25,
      "Publications": 4.85
  },
  {
      "FirstName": "Shawna",
      "LastName": "Rogers",
      "Education": 8.5,
      "Certifications": 8.25,
      "Residency": 7.75,
      "Fellowships": 7.75,
      "Reputation": 9.25,
      "MedSchool": 7.15,
      "Experience": 7.15,
      "Training": 7.25,
      "Publications": 2.15
  },
  {
      "FirstName": "Shawn",
      "LastName": "Gustav",
      "Education": 9.25,
      "Certifications": 6.75,
      "Residency": 5.5,
      "Fellowships": 8.25,
      "Reputation": 8.5,
      "MedSchool": 8.15,
      "Experience": 5.25,
      "Training": 8.9,
      "Publications": 4.25
  },
  {
      "FirstName": "Susan",
      "LastName": "Estridge",
      "Education": 7.75,
      "Certifications": 7.25,
      "Residency": 8.75,
      "Fellowships": 7.75,
      "Reputation": 8.75,
      "MedSchool": 9,
      "Experience": 9.25,
      "Training": 8.35,
      "Publications": 5.75
  },
  {
      "FirstName": "Tim",
      "LastName": "Gulver",
      "Education": 8.25,
      "Certifications": 8.8,
      "Residency": 8.25,
      "Fellowships": 9.25,
      "Reputation": 6.75,
      "MedSchool": 5.5,
      "Experience": 8.75,
      "Training": 7.85,
      "Publications": 3.75
  }
    ];

    return (items);
}

function eq(d1, d2) { return d1 == d2; }
function lt(d1, d2) { return d1 < d2; }
function gt(d1, d2) { return d1 > d2; }