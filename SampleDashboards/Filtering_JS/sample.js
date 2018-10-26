var ChartSummary;
var ChartDetails;
var flag = false;

var dataPoints = {
    "Countries": [{
        "Name": "Argentina",
        Value: 40401800
    }, {
        "Name": "United States",
        "Value": 255395600
    }, {
        "Name": "Germany",
        "Value": 97151000
    }, {
        "Name": "South Africa",
        "Value": 42300000
    }, {
        "Name": "Japan",
        "Value": 100525000
    }]
};

var items = [{
    "Year": "2000",
    "Argentina": 17.64,
    "United States": 38.9,
    "Germany": 58.6,
    "South Africa": 18.28,
    "Japan": 52.62
}, {
    "Year": "2001",
    "Argentina": 18.14,
    "United States": 45.07,
    "Germany": 68.08,
    "South Africa": 23.39,
    "Japan": 58.78
}, {
    "Year": "2002",
    "Argentina": 17.5,
    "United States": 49.25,
    "Germany": 71.64,
    "South Africa": 29.43,
    "Japan": 63.65
}, {
    "Year": "2003",
    "Argentina": 20.71,
    "United States": 55.24,
    "Germany": 78.52,
    "South Africa": 35.93,
    "Japan": 67.9
}, {
    "Year": "2004",
    "Argentina": 35.35,
    "United States": 62.94,
    "Germany": 86.45,
    "South Africa": 44.14,
    "Japan": 71.58
}, {
    "Year": "2005",
    "Argentina": 57.41,
    "United States": 71.43,
    "Germany": 95.87,
    "South Africa": 71.6,
    "Japan": 75.33
}, {
    "Year": "2006",
    "Argentina": 80.52,
    "United States": 86.32,
    "Germany": 103.55,
    "South Africa": 83.33,
    "Japan": 79.32
}, {
    "Year": "2007",
    "Argentina": 102.2,
    "United States": 89.51,
    "Germany": 117.62,
    "South Africa": 87.08,
    "Japan": 78.56
}];

window.onload = function () {
    onLoadDoc();
};

function onLoadDoc() {

    //Chart Initialization

    ChartSummary = new cfx.Chart();
    ChartDetails = new cfx.Chart();

    ChartSummary.setDataSource(dataPoints.Countries);
    ChartDetails.setDataSource(items);

    // Chart Summary

    ChartSummary.setGallery(cfx.Gallery.Doughnut);
    ChartSummary.getAxisX().getDataFormat().setFormat(cfx.AxisFormat.Number);
    ChartSummary.getAllSeries().getPointLabels().setVisible(true);
    ChartSummary.getAllSeries().getPointLabels().setFormat("%v");
	ChartSummary.getAxisY().getLabelsFormat().setFormat(cfx.AxisFormat.Number);
	ChartSummary.getAnimations().getLoad().setEnabled(true);
	

    var myDoughnut;
    myDoughnut = (ChartSummary.getGalleryAttributes());
    myDoughnut.setShadows(true);
    myDoughnut.setShowLines(false);

    //Chart Details

    ChartDetails.setGallery(cfx.Gallery.Curve);
    ChartDetails.getAxisX().getDataFormat().setFormat(cfx.AxisFormat.Number);
    ChartDetails.getAllSeries().getPointLabels().setVisible(true);
    ChartDetails.getAllSeries().getPointLabels().setFormat("%v");
	ChartDetails.getAllSeries().getPointLabels().setAlignment(cfx.StringAlignment.Far);
	ChartDetails.getAnimations().getLoad().setEnabled(true);
	
    initializeChart(false);

    var divHolder = document.getElementById('myDiv');
    var divHolder2 = document.getElementById('myDiv2');

    ChartSummary.create(divHolder);
    ChartDetails.create(divHolder2);

    //Title Customization

    var summaryTitle;
    var summarySubtitle;

    summaryTitle = new cfx.TitleDockable();
    summaryTitle.setText("Total mobile cellular subscribers");
    summaryTitle.setFont("18px Arial");

    summarySubtitle = new cfx.TitleDockable();
    summarySubtitle.setText("2007");
    summarySubtitle.setFont("14px Arial");

    ChartSummary.getTitles().add(summaryTitle);
    ChartSummary.getTitles().add(summarySubtitle);

    var detailsTitle;
    var detailsSubtitle;

    detailsTitle = new cfx.TitleDockable();
    detailsTitle.setText("Mobile cellular subscribers per 100 inhabitants");
    detailsTitle.setFont("18px Arial");

    detailsSubtitle = new cfx.TitleDockable();
    detailsSubtitle.setText("2000 - 2007");
    detailsSubtitle.setFont("14px Arial");

    ChartDetails.getTitles().add(detailsTitle);
    ChartDetails.getTitles().add(detailsSubtitle);
}

function initializeChart(flag) {

    var i = 0;

    flag ? i = 0 : i = 2;

    while (i < 5) {
        ChartDetails.getSeries().getItem(i).setVisible(false);
        ChartSummary.getData().setItem(0, i, cfx.Chart.Hidden);
        ChartSummary.getData().getLabels().setItem(i, "");
        i++;
    }
}

function hideChart() {
    ChartSummary.setMessageText("NoData", "Please click on a country to compare");
    ChartSummary.getData().setSeries(0);
    ChartSummary.getData().setPoints(0);
    ChartDetails.setMessageText("NoData", "Please click on a country to compare");
    ChartDetails.getData().setSeries(0);
}

function showChart(pointId) {
    ChartSummary.setDataSource(dataPoints.Countries);
    ChartDetails.setDataSource(items);
    initializeChart(true);
    ChartSummary.getData().setItem(0, pointId - 1, dataPoints.Countries[pointId - 1].Value);
    ChartSummary.getData().getLabels().setItem(pointId - 1, dataPoints.Countries[pointId - 1].Name);
}

function checkState() {
    var state = false;

    if (!Checkbox1.checked && !Checkbox2.checked && !Checkbox3.checked && !Checkbox4.checked && !Checkbox5.checked) {
        return state;
    } else {
        return !state;
    }
}

function doHide(pointId, state) {

    if (state) {
        if (flag) {
            flag = false;
            showChart(pointId);
        } else {
            ChartSummary.getData().setItem(0, pointId - 1, dataPoints.Countries[pointId - 1].Value);
            ChartSummary.getData().getLabels().setItem(pointId - 1, dataPoints.Countries[pointId - 1].Name);
        }
    } else {
        if (!checkState()) {
            flag = true;
            hideChart();
        } else {
            ChartSummary.getData().setItem(0, pointId - 1, cfx.Chart.Hidden);
            ChartSummary.getData().getLabels().setItem(pointId - 1, "");
        }
    }

    ChartDetails.getSeries().getItem(pointId - 1).getVisible() ? ChartDetails.getSeries().getItem(pointId - 1).setVisible(false) : ChartDetails.getSeries().getItem(pointId - 1).setVisible(true);

}

function onFilter(pointId, checkBoxId) {
    checkBoxId.checked ? doHide(pointId, true) : doHide(pointId, false);
}