(function(){

var cfx = { };
window.cfx = cfx;
cfx._isCFXNamespace = true;

var AnimationDelay = {
    Auto: 0,
    None: 4096,
    PerPoint: 1,
    PerSeries: 2,
    PerPointInverse: 17,
    PerSeriesInverse: 18,
    PerPointRandom: 33,
    PerSeriesRandom: 34,
    PerPointSeries: 3,
    PerPointSeriesRandom: 35
};
AnimationDelay._isCFXEnum = true;
cfx.AnimationDelay = AnimationDelay;

var AnimationDirection = {
    Auto: 0,
    Upward: 1,
    Downward: 2,
    FromCenter: 3
};
AnimationDirection._isCFXEnum = true;
cfx.AnimationDirection = AnimationDirection;

var AnimationTiming = {
    Auto: 0,
    Linear: 1,
    EaseIn: 2,
    EaseOut: 3,
    EaseInEaseOut: 4,
    EaseOutBounce: 5
};
AnimationTiming._isCFXEnum = true;
cfx.AnimationTiming = AnimationTiming;

var AxesStyle = {
    None: 0,
    Frame3D: 1,
    Math: 2,
    FlatFrame: 3,
    X: 4,
    Y: 5
};
AxesStyle._isCFXEnum = true;
cfx.AxesStyle = AxesStyle;

var AxisFormat = {
    None: 0,
    Number: 1,
    Currency: 2,
    Scientific: 3,
    Percentage: 4,
    Date: 5,
    LongDate: 6,
    Time: 7,
    DateTime: 8
};
AxisFormat._isCFXEnum = true;
cfx.AxisFormat = AxisFormat;

var AxisPosition = {
    Near: 0,
    Far: 2
};
AxisPosition._isCFXEnum = true;
cfx.AxisPosition = AxisPosition;

var AxisStyles = {
    HideText: 1,
    Notify: 2,
    Show2Levels: 4,
    SingleLine: 8,
    AutoScaleUnit: 16,
    BreakZero: 32,
    Hide: 64,
    NotClipped: 128,
    ClientScroll: 256,
    Centered: 512,
    RotateText: 1024,
    LongTick: 2048,
    AutoScale: 4096,
    ForceZero: 8192,
    ShowEnds: 16384,
    RoundStep: 32768,
    IgnoreLabels: 65536,
    GridFront: 131072,
    Inverted: 262144,
    ShowIntermediateLogLabels: 1048576,
    AutoMinorStep: 2097152,
    AutoFirstLabel: 4194304,
    AutoCenter: 8388608,
    Inside: 16777216,
    AutoLabelLayout: 33554432,
    AllowHalf: 67108864,
    LeftAligned: 268435456,
    DoNotReset: 536870912,
    AutoDecimals: 1073741824,
    Default: 250132520
};
AxisStyles._isCFXEnum = true;
cfx.AxisStyles = AxisStyles;

var BarAnimation = {
    Value: 0,
    Drop: 1
};
BarAnimation._isCFXEnum = true;
cfx.BarAnimation = BarAnimation;

var BarShape = {
    Rectangle: 0,
    Cylinder: 1,
    Cone: 2,
    Pyramid: 3
};
BarShape._isCFXEnum = true;
cfx.BarShape = BarShape;

var BorderEffect = {
    None: 0,
    Raised: 1,
    Light: 2,
    Dark: 3,
    Opposite: 4,
    Shadow: 5
};
BorderEffect._isCFXEnum = true;
cfx.BorderEffect = BorderEffect;

var BubbleAnimation = {
    Position: 0,
    Size: 1,
    PositionAndSize: 2
};
BubbleAnimation._isCFXEnum = true;
cfx.BubbleAnimation = BubbleAnimation;

var ChartStyles = {
    PaintMarker: 32,
    OnlyChart: 64,
    SideBySide: 256,
    Monochrome: 512,
    FastLegends: 32768,
    ForceDataDriven: 2097152,
    PaintNoChart: 536870912,
    HideZLabels: 1073741824,
    Default: 1852418
};
ChartStyles._isCFXEnum = true;
cfx.ChartStyles = ChartStyles;

var ConditionSpecialValue = {
    None: 0,
    First: 1,
    Last: 2,
    Min: 3,
    Max: 4
};
ConditionSpecialValue._isCFXEnum = true;
cfx.ConditionSpecialValue = ConditionSpecialValue;

var ConditionUnit = {
    Absolute: 0,
    Percentage: 1
};
ConditionUnit._isCFXEnum = true;
cfx.ConditionUnit = ConditionUnit;

var ContentLayout = {
    Spread: 0,
    Near: 1,
    Center: 2,
    Far: 3
};
ContentLayout._isCFXEnum = true;
cfx.ContentLayout = ContentLayout;

var CustomGridLineStyles = {
    None: 0,
    HideText: 1,
    Hide: 2,
    BackOnly: 8,
    ColorText: 16,
    HideLine: 32,
    OutsideText: 64,
    ShowValue: 128
};
CustomGridLineStyles._isCFXEnum = true;
cfx.CustomGridLineStyles = CustomGridLineStyles;

var DataSourceStyles = {
    KeepScale: 8,
    Transpose: 16,
    KeepSeriesLegend: 64,
    KeepLabels: 128,
    ReadXValues: 512,
    ReadYFromValues: 1024,
    DatesAsLabels: 2048,
    RewindBeforeReading: 4096,
    DataChange: 8192,
    None: 0,
    Default: 4096
};
DataSourceStyles._isCFXEnum = true;
cfx.DataSourceStyles = DataSourceStyles;

var DockArea = {
    Left: 0,
    Top: 1,
    Right: 2,
    Bottom: 3
};
DockArea._isCFXEnum = true;
cfx.DockArea = DockArea;

var DockBorder = {
    None: 0,
    External: 1,
    Internal: 2
};
DockBorder._isCFXEnum = true;
cfx.DockBorder = DockBorder;

var ExplodingMode = {
    None: 0,
    All: 1,
    First: 2,
    Last: 3
};
ExplodingMode._isCFXEnum = true;
cfx.ExplodingMode = ExplodingMode;

var FieldUsage = {
    Default: 0,
    Label: 1,
    Value: 2,
    XValue: 3,
    FromValue: 5,
    KeyLabel: 4,
    ColumnHeading: 6,
    RowHeading: 7,
    NotUsed: 8
};
FieldUsage._isCFXEnum = true;
cfx.FieldUsage = FieldUsage;

var FillMode = {
    Solid: 0,
    Monochrome: 1,
    Pattern: 2,
    Gradient: 3
};
FillMode._isCFXEnum = true;
cfx.FillMode = FillMode;

var Gallery = {
    None: 0,
    Lines: 1,
    Bar: 2,
    Area: 3,
    Scatter: 4,
    Pie: 5,
    Step: 8,
    HighLowClose: 9,
    Radar: 11,
    Polar: 11,
    Cube: 12,
    Bubble: 15,
    OpenHighLowClose: 16,
    Candlestick: 17,
    Gantt: 20,
    Curve: 6,
    Pareto: 7,
    Surface: 10,
    Doughnut: 13,
    Pyramid: 14,
    Contour: 18,
    CurveArea: 19
};
Gallery._isCFXEnum = true;
cfx.Gallery = Gallery;

var HitType = {
    None: 0,
    Background: 1,
    InsideArea: 2,
    Between: 3,
    Point: 4,
    Axis: 5,
    Title: 6,
    CustomGridLine: 7,
    Drag: 8,
    Crosshair: 9,
    Scroll: 11,
    LegendBox: 12,
    DataGrid: 13,
    Other: 14,
    AxisSection: 15
};
HitType._isCFXEnum = true;
cfx.HitType = HitType;

var Interlaced = {
    None: 0,
    Horizontal: 1,
    Vertical: 2
};
Interlaced._isCFXEnum = true;
cfx.Interlaced = Interlaced;

var LegendBoxStyles = {
    Wordbreak: 1,
    ShowMenu: 4,
    RightAlign: 16,
    NoColor: 32,
    HidePartial: 64,
    Default: 4
};
LegendBoxStyles._isCFXEnum = true;
cfx.LegendBoxStyles = LegendBoxStyles;

var LineAnimation = {
    DrawX: 0,
    YData: 1,
    YProgress: 2
};
LineAnimation._isCFXEnum = true;
cfx.LineAnimation = LineAnimation;

var MarkerShape = {
    None: 0,
    Rect: 1,
    Circle: 2,
    Triangle: 3,
    Diamond: 4,
    Marble: 5,
    HorizontalLine: 6,
    VerticalLine: 7,
    Cross: 8,
    InvertedTriangle: 9,
    X: 10,
    Picture: 11,
    Template: 12,
    Many: 13
};
MarkerShape._isCFXEnum = true;
cfx.MarkerShape = MarkerShape;

var MarkerStyle = {
    Filled: 0,
    Hollow: 1
};
MarkerStyle._isCFXEnum = true;
cfx.MarkerStyle = MarkerStyle;

var PieAnimation = {
    Radial: 1,
    Angle: 2,
    RadialAndAngle: 3,
    AngleFromTop: 258,
    RadialAngleFromTop: 259
};
PieAnimation._isCFXEnum = true;
cfx.PieAnimation = PieAnimation;

var RealTimeAction = {
    Append: 0,
    Insert: 1
};
RealTimeAction._isCFXEnum = true;
cfx.RealTimeAction = RealTimeAction;

var RealTimeMode = {
    Scroll: 0,
    Loop: 1
};
RealTimeMode._isCFXEnum = true;
cfx.RealTimeMode = RealTimeMode;

var Shadow = {
    None: 0,
    Fixed: 1,
    Realistic: 2
};
Shadow._isCFXEnum = true;
cfx.Shadow = Shadow;

var Stacked = {
    No: 0,
    Normal: 1,
    Stacked100: 2
};
Stacked._isCFXEnum = true;
cfx.Stacked = Stacked;

var TickMark = {
    None: 0,
    Outside: 1,
    Inside: 2,
    Cross: 3
};
TickMark._isCFXEnum = true;
cfx.TickMark = TickMark;

var GradientType = {
    Horizontal: 0,
    Vertical: 1,
    BackwardDiagonal: 2,
    ForwardDiagonal: 3,
    Radial: 4,
    Angled: 5
};
GradientType._isCFXEnum = true;
cfx.GradientType = GradientType;

var StepStyle = {
    HorizontalVertical: 0,
    VerticalHorizontal: 1
};
StepStyle._isCFXEnum = true;
cfx.StepStyle = StepStyle;

var VectorBorder = function() {
}
cfx.VectorBorder = VectorBorder;
VectorBorder.prototype = {
    getColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setTemplate: function (value) {
        /// <param name="value" type="string"/>>
    }
};

var by = function() {
}
cfx.by = by;
by.prototype = {
    getColors: function () {
        /// <returns type="Color[]"/>
        return new Color[]();
    },
    setColors: function (value) {
        /// <param name="value" type="Color[]"/>>
    },
    getShowContourLines: function () {
        /// <returns type="bool"/>
        return true;
    },
    setShowContourLines: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getShowGridLines: function () {
        /// <returns type="bool"/>
        return true;
    },
    setShowGridLines: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getStep: function () {
        /// <returns type="number"/>
        return 0;
    },
    setStep: function (value) {
        /// <param name="value" type="number"/>>
    }
};

var bz = function() {
}
cfx.bz = bz;
bz.prototype = {

};

var bA = function() {
}
cfx.bA = bA;
bA.prototype = {
    getCircular: function () {
        /// <returns type="bool"/>
        return true;
    },
    setCircular: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getClockwise: function () {
        /// <returns type="bool"/>
        return true;
    },
    setClockwise: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getClosed: function () {
        /// <returns type="bool"/>
        return true;
    },
    setClosed: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getFillArea: function () {
        /// <returns type="bool"/>
        return true;
    },
    setFillArea: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getShadows: function () {
        /// <returns type="bool"/>
        return true;
    },
    setShadows: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getShowLines: function () {
        /// <returns type="bool"/>
        return true;
    },
    setShowLines: function (value) {
        /// <param name="value" type="bool"/>>
    }
};

var bB = function() {
}
cfx.bB = bB;
bB.prototype = {
    getConic: function () {
        /// <returns type="bool"/>
        return true;
    },
    setConic: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getDistributeHeight: function () {
        /// <returns type="bool"/>
        return true;
    },
    setDistributeHeight: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getFaces: function () {
        /// <returns type="number"/>
        return 0;
    },
    setFaces: function (value) {
        /// <param name="value" type="number"/>>
    },
    getInverted: function () {
        /// <returns type="bool"/>
        return true;
    },
    setInverted: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getSeparation: function () {
        /// <returns type="number"/>
        return 0;
    },
    setSeparation: function (value) {
        /// <param name="value" type="number"/>>
    },
    getShadow2D: function () {
        /// <returns type="bool"/>
        return true;
    },
    setShadow2D: function (value) {
        /// <param name="value" type="bool"/>>
    }
};

var bE = function() {
}
cfx.bE = bE;
bE.prototype = {

};

var bF = function() {
}
cfx.bF = bF;
bF.prototype = {
    getAnimationStyle: function () {
        /// <returns type="cfx.LineAnimation"/>
        return new LineAnimation();
    },
    setAnimationStyle: function (value) {
        /// <param name="value" type="cfx.LineAnimation"/>>
    },
    getStepStyle: function () {
        /// <returns type="cfx.StepStyle"/>
        return new StepStyle();
    },
    setStepStyle: function (value) {
        /// <param name="value" type="cfx.StepStyle"/>>
    }
};

var bG = function() {
}
cfx.bG = bG;
bG.prototype = {
    getHighLowClose4Series: function () {
        /// <returns type="bool"/>
        return true;
    },
    setHighLowClose4Series: function (value) {
        /// <param name="value" type="bool"/>>
    }
};

var bH = function() {
}
cfx.bH = bH;
bH.prototype = {
    getAnimationStyle: function () {
        /// <returns type="cfx.LineAnimation"/>
        return new LineAnimation();
    },
    setAnimationStyle: function (value) {
        /// <param name="value" type="cfx.LineAnimation"/>>
    }
};

var bJ = function() {
}
cfx.bJ = bJ;
bJ.prototype = {
    getAnimationStyle: function () {
        /// <returns type="cfx.BarAnimation"/>
        return new BarAnimation();
    },
    setAnimationStyle: function (value) {
        /// <param name="value" type="cfx.BarAnimation"/>>
    },
    getIntraSeriesGap: function () {
        /// <returns type="number"/>
        return 0;
    },
    setIntraSeriesGap: function (value) {
        /// <param name="value" type="number"/>>
    },
    getOverlap: function () {
        /// <returns type="bool"/>
        return true;
    },
    setOverlap: function (value) {
        /// <param name="value" type="bool"/>>
    }
};

var bK = function() {
}
cfx.bK = bK;
bK.prototype = {
    getAnimationStyle: function () {
        /// <returns type="cfx.LineAnimation"/>
        return new LineAnimation();
    },
    setAnimationStyle: function (value) {
        /// <param name="value" type="cfx.LineAnimation"/>>
    }
};

var ImageBackground = function() {
}
cfx.ImageBackground = ImageBackground;
ImageBackground.prototype = {

};

var GradientBackground = function() {
}
cfx.GradientBackground = GradientBackground;
GradientBackground.prototype = {
    getAngle: function () {
        /// <returns type="number"/>
        return 0;
    },
    setAngle: function (value) {
        /// <param name="value" type="number"/>>
    },
    getColorFrom: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setColorFrom: function (value) {
        /// <param name="value" type="String"/>>
    },
    getColors: function () {
        /// <returns type="cfx.bQ"/>
        return new bQ();
    },
    getColorTo: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setColorTo: function (value) {
        /// <param name="value" type="String"/>>
    },
    getEffectArea: function () {
        /// <returns type="number"/>
        return 0;
    },
    setEffectArea: function (value) {
        /// <param name="value" type="number"/>>
    },
    getFactor: function () {
        /// <returns type="cfx.bP"/>
        return new bP();
    },
    getPosition: function () {
        /// <returns type="cfx.bP"/>
        return new bP();
    },
    getType: function () {
        /// <returns type="cfx.GradientType"/>
        return new GradientType();
    },
    setType: function (value) {
        /// <param name="value" type="cfx.GradientType"/>>
    }
};

var LegendItemAttributes = function() {
}
cfx.LegendItemAttributes = LegendItemAttributes;
LegendItemAttributes.prototype = {
    getFontStyle: function () {
        /// <summary>Gets the font style for the legend box item.</summary>
        /// <returns type="FontStyle"/>
        return new FontStyle();
    },
    setFontStyle: function (value) {
        /// <summary>Sets the font style for the legend box item.</summary>
        /// <param name="value" type="FontStyle"/>>
    },
    getInverted: function () {
        /// <summary>Gets a value allowing you to invert the order of the LegendBox Items.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setInverted: function (value) {
        /// <summary>Sets a value allowing you to invert the order of the LegendBox Items.</summary>
        /// <param name="value" type="bool"/>>
    },
    getMarkerShape: function () {
        /// <returns type="cfx.MarkerShape"/>
        return new MarkerShape();
    },
    setMarkerShape: function (value) {
        /// <param name="value" type="cfx.MarkerShape"/>>
    },
    getMarkerSize: function () {
        /// <returns type="number"/>
        return 0;
    },
    setMarkerSize: function (value) {
        /// <param name="value" type="number"/>>
    },
    getOrderIndex: function () {
        /// <summary>Gets the index for ordering the legend  box items.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setOrderIndex: function (value) {
        /// <summary>Sets the index for ordering the legend  box items.</summary>
        /// <param name="value" type="number"/>>
    },
    getTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setTemplate: function (value) {
        /// <param name="value" type="string"/>>
    },
    getTextColor: function () {
        /// <summary>Gets a value allowing you to set the text color for the legend item.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setTextColor: function (value) {
        /// <summary>Sets a value allowing you to set the text color for the legend item.</summary>
        /// <param name="value" type="String"/>>
    },
    getVisible: function () {
        /// <summary>Gets a value allowing you hide or show the legend item.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <summary>Sets a value allowing you hide or show the legend item.</summary>
        /// <param name="value" type="bool"/>>
    }
};

var FieldMap = function() {
}
cfx.FieldMap = FieldMap;
FieldMap.prototype = {
    getDisplayName: function () {
        /// <summary>Gets the name of the field which will be displayed by the Chart.</summary>
        /// <returns type="string"/>
        return "";
    },
    setDisplayName: function (value) {
        /// <summary>Sets the name of the field which will be displayed by the Chart.</summary>
        /// <param name="value" type="string"/>>
    },
    getName: function () {
        /// <summary>Gets the name of the DataSource's field related to the FieldMap.</summary>
        /// <returns type="string"/>
        return "";
    },
    setName: function (value) {
        /// <summary>Sets the name of the DataSource's field related to the FieldMap.</summary>
        /// <param name="value" type="string"/>>
    },
    getUsage: function () {
        /// <summary>Gets the usage of the related column of the ResultSet.</summary>
        /// <returns type="cfx.FieldUsage"/>
        return new FieldUsage();
    },
    setUsage: function (value) {
        /// <summary>Sets the usage of the related column of the ResultSet.</summary>
        /// <param name="value" type="cfx.FieldUsage"/>>
    }
};

var bY = function() {
}
cfx.bY = bY;
bY.prototype = {
    getCompactFormula: function () {
        /// <returns type="cfx.CompactFormulaHandler"/>
        return new CompactFormulaHandler();
    },
    setCompactFormula: function (value) {
        /// <param name="value" type="cfx.CompactFormulaHandler"/>>
    },
    getHasData: function () {
        /// <returns type="bool"/>
        return true;
    },
    getShared: function () {
        /// <returns type="bool"/>
        return true;
    },
    setShared: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getItem1: function (point) {
        /// <param name="point" type="number"/>>
        /// <returns type="number"/>
        return 0;
    },
    setItem1: function (point, value) {
        /// <param name="point" type="number"/>>
        /// <param name="value" type="number"/>>
    },
    getItem: function (series, point) {
        /// <param name="series" type="number"/>>
        /// <param name="point" type="number"/>>
        /// <returns type="number"/>
        return 0;
    },
    setItem: function (series, point, value) {
        /// <param name="series" type="number"/>>
        /// <param name="point" type="number"/>>
        /// <param name="value" type="number"/>>
    }
};

var CustomLegendItem = function() {
}
cfx.CustomLegendItem = CustomLegendItem;
CustomLegendItem.prototype = {
    getAlternateColor: function () {
        /// <summary>Gets the background color of a custom legend item marker when a pattern is configured using the Pattern  property.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setAlternateColor: function (value) {
        /// <summary>Sets the background color of a custom legend item marker when a pattern is configured using the Pattern  property.</summary>
        /// <param name="value" type="String"/>>
    },
    getAttributes: function () {
        /// <summary>Gets the PointAttributes object for the custom legend item.</summary>
        /// <returns type="cfx.PointAttributes"/>
        return new PointAttributes();
    },
    setAttributes: function (value) {
        /// <summary>Sets the PointAttributes object for the custom legend item.</summary>
        /// <param name="value" type="cfx.PointAttributes"/>>
    },
    getBorder: function () {
        /// <summary>Gets a BorderAttributes object allowing you to modify the border properties for this CustomLegendItem.</summary>
        /// <returns type="cfx.cn"/>
        return new cn();
    },
    getColor: function () {
        /// <summary>Gets the Color for the marker of the Custom Legend Item.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <summary>Sets the Color for the marker of the Custom Legend Item.</summary>
        /// <param name="value" type="String"/>>
    },
    getMarkerShape: function () {
        /// <summary>Represents the shape of the marker.</summary>
        /// <returns type="cfx.MarkerShape"/>
        return new MarkerShape();
    },
    setMarkerShape: function (value) {
        /// <summary>Represents the shape of the marker.</summary>
        /// <param name="value" type="cfx.MarkerShape"/>>
    },
    getPattern: function () {
        /// <summary>Gets a HatchStyle pattern for the marker of a CustomLegendItem.</summary>
        /// <returns type="HatchStyle"/>
        return new HatchStyle();
    },
    setPattern: function (value) {
        /// <summary>Sets a HatchStyle pattern for the marker of a CustomLegendItem.</summary>
        /// <param name="value" type="HatchStyle"/>>
    },
    getPicture: function () {
        /// <summary>Gets an image to be used as a marker for a CustomLegendItem.</summary>
        /// <returns type="Image"/>
        return new Image();
    },
    setPicture: function (value) {
        /// <summary>Sets an image to be used as a marker for a CustomLegendItem.</summary>
        /// <param name="value" type="Image"/>>
    },
    getShowLine: function () {
        /// <summary>Gets a value used to display a line marker for a CustomLegendItem.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setShowLine: function (value) {
        /// <summary>Sets a value used to display a line marker for a CustomLegendItem.</summary>
        /// <param name="value" type="bool"/>>
    },
    getText: function () {
        /// <summary>Gets the text string of the custom legend item.</summary>
        /// <returns type="string"/>
        return "";
    },
    setText: function (value) {
        /// <summary>Sets the text string of the custom legend item.</summary>
        /// <param name="value" type="string"/>>
    }
};

var ConditionDataValue = function() {
}
cfx.ConditionDataValue = ConditionDataValue;
ConditionDataValue.prototype = {
    getActive: function () {
        /// <returns type="bool"/>
        return true;
    },
    setActive: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getDataElement: function () {
        /// <returns type="cfx.DataElement"/>
        return new DataElement();
    },
    setDataElement: function (value) {
        /// <param name="value" type="cfx.DataElement"/>>
    },
    getFrom: function () {
        /// <returns type="number"/>
        return 0;
    },
    setFrom: function (value) {
        /// <param name="value" type="number"/>>
    },
    getFromOpen: function () {
        /// <returns type="bool"/>
        return true;
    },
    setFromOpen: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getInverse: function () {
        /// <returns type="bool"/>
        return true;
    },
    setInverse: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getSpecialValue: function () {
        /// <returns type="cfx.ConditionSpecialValue"/>
        return new ConditionSpecialValue();
    },
    setSpecialValue: function (value) {
        /// <param name="value" type="cfx.ConditionSpecialValue"/>>
    },
    getTo: function () {
        /// <returns type="number"/>
        return 0;
    },
    setTo: function (value) {
        /// <param name="value" type="number"/>>
    },
    getToOpen: function () {
        /// <returns type="bool"/>
        return true;
    },
    setToOpen: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getUnit: function () {
        /// <returns type="cfx.ConditionUnit"/>
        return new ConditionUnit();
    },
    setUnit: function (value) {
        /// <param name="value" type="cfx.ConditionUnit"/>>
    }
};

var b5 = function() {
}
cfx.b5 = b5;
b5.prototype = {
    getAverage: function () {
        /// <summary>Returns the average points set by the CompactedBy property.</summary>
        /// <returns type="cfx.CompactFormulaHandler"/>
        return new CompactFormulaHandler();
    },
    getFirst: function () {
        /// <summary>Returns the first points set by the CompactedBy property.</summary>
        /// <returns type="cfx.CompactFormulaHandler"/>
        return new CompactFormulaHandler();
    },
    getHiLowClose: function () {
        /// <summary>Returns the high, low, and close points set by the CompactedBy property.</summary>
        /// <returns type="cfx.CompactFormulaHandler"/>
        return new CompactFormulaHandler();
    },
    getLast: function () {
        /// <summary>Returns the last points set by the CompactedBy property.</summary>
        /// <returns type="cfx.CompactFormulaHandler"/>
        return new CompactFormulaHandler();
    },
    getMax: function () {
        /// <summary>Returns the max points set by the CompactedBy property.</summary>
        /// <returns type="cfx.CompactFormulaHandler"/>
        return new CompactFormulaHandler();
    },
    getMin: function () {
        /// <summary>Returns the min points set by the CompactedBy property.</summary>
        /// <returns type="cfx.CompactFormulaHandler"/>
        return new CompactFormulaHandler();
    },
    getOpenHiLowClose: function () {
        /// <summary>Returns the open, high, low, and close points set by the CompactedBy property.</summary>
        /// <returns type="cfx.CompactFormulaHandler"/>
        return new CompactFormulaHandler();
    }
};

var b6 = function() {
}
cfx.b6 = b6;
b6.prototype = {

};

var CellAttributes = function() {
}
cfx.CellAttributes = CellAttributes;
CellAttributes.prototype = {
    getBackColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setBackColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getBorderColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setBorderColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getBorderWidth: function () {
        /// <returns type="number"/>
        return 0;
    },
    setBorderWidth: function (value) {
        /// <param name="value" type="number"/>>
    },
    getFontStyle: function () {
        /// <returns type="FontStyle"/>
        return new FontStyle();
    },
    setFontStyle: function (value) {
        /// <param name="value" type="FontStyle"/>>
    },
    getTextColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setTextColor: function (value) {
        /// <param name="value" type="String"/>>
    }
};

var VectorPyramid = function() {
}
cfx.VectorPyramid = VectorPyramid;
VectorPyramid.prototype = {
    getConic: function () {
        /// <returns type="bool"/>
        return true;
    },
    setConic: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getDistributeHeight: function () {
        /// <returns type="bool"/>
        return true;
    },
    setDistributeHeight: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getFaces: function () {
        /// <returns type="number"/>
        return 0;
    },
    setFaces: function (value) {
        /// <param name="value" type="number"/>>
    },
    getInverted: function () {
        /// <returns type="bool"/>
        return true;
    },
    setInverted: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getSeparation: function () {
        /// <returns type="number"/>
        return 0;
    },
    setSeparation: function (value) {
        /// <param name="value" type="number"/>>
    },
    getCacheResources: function () {
        /// <returns type="bool"/>
        return true;
    },
    setCacheResources: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setTemplate: function (value) {
        /// <param name="value" type="string"/>>
    }
};

var VectorPie = function() {
}
cfx.VectorPie = VectorPie;
VectorPie.prototype = {
    getAnimationStyle: function () {
        /// <returns type="cfx.PieAnimation"/>
        return new PieAnimation();
    },
    setAnimationStyle: function (value) {
        /// <param name="value" type="cfx.PieAnimation"/>>
    },
    getExplodingMode: function () {
        /// <returns type="cfx.ExplodingMode"/>
        return new ExplodingMode();
    },
    setExplodingMode: function (value) {
        /// <param name="value" type="cfx.ExplodingMode"/>>
    },
    getLabelLineBreak: function () {
        /// <returns type="number"/>
        return 0;
    },
    setLabelLineBreak: function (value) {
        /// <param name="value" type="number"/>>
    },
    getLabelsInside: function () {
        /// <returns type="bool"/>
        return true;
    },
    setLabelsInside: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getMaximumLabelMargin: function () {
        /// <returns type="number"/>
        return 0;
    },
    setMaximumLabelMargin: function (value) {
        /// <param name="value" type="number"/>>
    },
    getShadows: function () {
        /// <returns type="bool"/>
        return true;
    },
    setShadows: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getShowLines: function () {
        /// <returns type="bool"/>
        return true;
    },
    setShowLines: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getSliceSeparation: function () {
        /// <returns type="number"/>
        return 0;
    },
    setSliceSeparation: function (value) {
        /// <param name="value" type="number"/>>
    },
    getSquare: function () {
        /// <returns type="bool"/>
        return true;
    },
    setSquare: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getStacked: function () {
        /// <returns type="bool"/>
        return true;
    },
    setStacked: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getStartAngle: function () {
        /// <returns type="number"/>
        return 0;
    },
    setStartAngle: function (value) {
        /// <param name="value" type="number"/>>
    },
    getCacheResources: function () {
        /// <returns type="bool"/>
        return true;
    },
    setCacheResources: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setTemplate: function (value) {
        /// <param name="value" type="string"/>>
    }
};

var VectorDoughnut = function() {
}
cfx.VectorDoughnut = VectorDoughnut;
VectorDoughnut.prototype = {
    getDoughnutThickness: function () {
        /// <returns type="number"/>
        return 0;
    },
    setDoughnutThickness: function (value) {
        /// <param name="value" type="number"/>>
    },
    getAnimationStyle: function () {
        /// <returns type="cfx.PieAnimation"/>
        return new PieAnimation();
    },
    setAnimationStyle: function (value) {
        /// <param name="value" type="cfx.PieAnimation"/>>
    },
    getExplodingMode: function () {
        /// <returns type="cfx.ExplodingMode"/>
        return new ExplodingMode();
    },
    setExplodingMode: function (value) {
        /// <param name="value" type="cfx.ExplodingMode"/>>
    },
    getLabelLineBreak: function () {
        /// <returns type="number"/>
        return 0;
    },
    setLabelLineBreak: function (value) {
        /// <param name="value" type="number"/>>
    },
    getLabelsInside: function () {
        /// <returns type="bool"/>
        return true;
    },
    setLabelsInside: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getMaximumLabelMargin: function () {
        /// <returns type="number"/>
        return 0;
    },
    setMaximumLabelMargin: function (value) {
        /// <param name="value" type="number"/>>
    },
    getShadows: function () {
        /// <returns type="bool"/>
        return true;
    },
    setShadows: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getShowLines: function () {
        /// <returns type="bool"/>
        return true;
    },
    setShowLines: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getSliceSeparation: function () {
        /// <returns type="number"/>
        return 0;
    },
    setSliceSeparation: function (value) {
        /// <param name="value" type="number"/>>
    },
    getSquare: function () {
        /// <returns type="bool"/>
        return true;
    },
    setSquare: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getStacked: function () {
        /// <returns type="bool"/>
        return true;
    },
    setStacked: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getStartAngle: function () {
        /// <returns type="number"/>
        return 0;
    },
    setStartAngle: function (value) {
        /// <param name="value" type="number"/>>
    },
    getCacheResources: function () {
        /// <returns type="bool"/>
        return true;
    },
    setCacheResources: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setTemplate: function (value) {
        /// <param name="value" type="string"/>>
    }
};

var VectorBubble = function() {
}
cfx.VectorBubble = VectorBubble;
VectorBubble.prototype = {
    getAnimationStyle: function () {
        /// <returns type="cfx.BubbleAnimation"/>
        return new BubbleAnimation();
    },
    setAnimationStyle: function (value) {
        /// <param name="value" type="cfx.BubbleAnimation"/>>
    },
    getMaximumBubbleSize: function () {
        /// <returns type="number"/>
        return 0;
    },
    setMaximumBubbleSize: function (value) {
        /// <param name="value" type="number"/>>
    },
    getCacheResources: function () {
        /// <returns type="bool"/>
        return true;
    },
    setCacheResources: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setTemplate: function (value) {
        /// <param name="value" type="string"/>>
    }
};

var VectorBar = function() {
}
cfx.VectorBar = VectorBar;
VectorBar.prototype = {
    getAnimationStyle: function () {
        /// <returns type="cfx.BarAnimation"/>
        return new BarAnimation();
    },
    setAnimationStyle: function (value) {
        /// <param name="value" type="cfx.BarAnimation"/>>
    },
    getIntraSeriesGap: function () {
        /// <returns type="number"/>
        return 0;
    },
    setIntraSeriesGap: function (value) {
        /// <param name="value" type="number"/>>
    },
    getOverlap: function () {
        /// <returns type="bool"/>
        return true;
    },
    setOverlap: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getCacheResources: function () {
        /// <returns type="bool"/>
        return true;
    },
    setCacheResources: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setTemplate: function (value) {
        /// <param name="value" type="string"/>>
    }
};

var VectorGantt = function() {
}
cfx.VectorGantt = VectorGantt;
VectorGantt.prototype = {
    getAnimationStyle: function () {
        /// <returns type="cfx.BarAnimation"/>
        return new BarAnimation();
    },
    setAnimationStyle: function (value) {
        /// <param name="value" type="cfx.BarAnimation"/>>
    },
    getIntraSeriesGap: function () {
        /// <returns type="number"/>
        return 0;
    },
    setIntraSeriesGap: function (value) {
        /// <param name="value" type="number"/>>
    },
    getOverlap: function () {
        /// <returns type="bool"/>
        return true;
    },
    setOverlap: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getCacheResources: function () {
        /// <returns type="bool"/>
        return true;
    },
    setCacheResources: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setTemplate: function (value) {
        /// <param name="value" type="string"/>>
    }
};

var VectorCube = function() {
}
cfx.VectorCube = VectorCube;
VectorCube.prototype = {
    getAnimationStyle: function () {
        /// <returns type="cfx.BarAnimation"/>
        return new BarAnimation();
    },
    setAnimationStyle: function (value) {
        /// <param name="value" type="cfx.BarAnimation"/>>
    },
    getIntraSeriesGap: function () {
        /// <returns type="number"/>
        return 0;
    },
    setIntraSeriesGap: function (value) {
        /// <param name="value" type="number"/>>
    },
    getOverlap: function () {
        /// <returns type="bool"/>
        return true;
    },
    setOverlap: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getCacheResources: function () {
        /// <returns type="bool"/>
        return true;
    },
    setCacheResources: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setTemplate: function (value) {
        /// <param name="value" type="string"/>>
    }
};

var VectorLine = function() {
}
cfx.VectorLine = VectorLine;
VectorLine.prototype = {
    getAnimationStyle: function () {
        /// <returns type="cfx.LineAnimation"/>
        return new LineAnimation();
    },
    setAnimationStyle: function (value) {
        /// <param name="value" type="cfx.LineAnimation"/>>
    },
    getCacheResources: function () {
        /// <returns type="bool"/>
        return true;
    },
    setCacheResources: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setTemplate: function (value) {
        /// <param name="value" type="string"/>>
    }
};

var VectorStepLine = function() {
}
cfx.VectorStepLine = VectorStepLine;
VectorStepLine.prototype = {
    getStepStyle: function () {
        /// <returns type="cfx.StepStyle"/>
        return new StepStyle();
    },
    setStepStyle: function (value) {
        /// <param name="value" type="cfx.StepStyle"/>>
    },
    getAnimationStyle: function () {
        /// <returns type="cfx.LineAnimation"/>
        return new LineAnimation();
    },
    setAnimationStyle: function (value) {
        /// <param name="value" type="cfx.LineAnimation"/>>
    },
    getCacheResources: function () {
        /// <returns type="bool"/>
        return true;
    },
    setCacheResources: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setTemplate: function (value) {
        /// <param name="value" type="string"/>>
    }
};

var VectorCurve = function() {
}
cfx.VectorCurve = VectorCurve;
VectorCurve.prototype = {
    getAnimationStyle: function () {
        /// <returns type="cfx.LineAnimation"/>
        return new LineAnimation();
    },
    setAnimationStyle: function (value) {
        /// <param name="value" type="cfx.LineAnimation"/>>
    },
    getCacheResources: function () {
        /// <returns type="bool"/>
        return true;
    },
    setCacheResources: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setTemplate: function (value) {
        /// <param name="value" type="string"/>>
    }
};

var VectorArea = function() {
}
cfx.VectorArea = VectorArea;
VectorArea.prototype = {
    getAnimationStyle: function () {
        /// <returns type="cfx.LineAnimation"/>
        return new LineAnimation();
    },
    setAnimationStyle: function (value) {
        /// <param name="value" type="cfx.LineAnimation"/>>
    },
    getCacheResources: function () {
        /// <returns type="bool"/>
        return true;
    },
    setCacheResources: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setTemplate: function (value) {
        /// <param name="value" type="string"/>>
    }
};

var VectorCurveArea = function() {
}
cfx.VectorCurveArea = VectorCurveArea;
VectorCurveArea.prototype = {
    getAnimationStyle: function () {
        /// <returns type="cfx.LineAnimation"/>
        return new LineAnimation();
    },
    setAnimationStyle: function (value) {
        /// <param name="value" type="cfx.LineAnimation"/>>
    },
    getCacheResources: function () {
        /// <returns type="bool"/>
        return true;
    },
    setCacheResources: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setTemplate: function (value) {
        /// <param name="value" type="string"/>>
    }
};

var b_ = function() {
}
cfx.b_ = b_;
b_.prototype = {
    getItemList: function (list) {
        /// <param name="list" type="cfx.object"/>>
        /// <returns type="cfx.LegendItemAttributes"/>
        return new LegendItemAttributes();
    },
    setItemList: function (list, value) {
        /// <param name="list" type="cfx.object"/>>
        /// <param name="value" type="cfx.LegendItemAttributes"/>>
    },
    getItem: function (list, index) {
        /// <param name="list" type="cfx.object"/>>
        /// <param name="index" type="number"/>>
        /// <returns type="cfx.LegendItemAttributes"/>
        return new LegendItemAttributes();
    },
    setItem: function (list, index, value) {
        /// <param name="list" type="cfx.object"/>>
        /// <param name="index" type="number"/>>
        /// <param name="value" type="cfx.LegendItemAttributes"/>>
    },
    removeItemFromList: function (list, index) {
        /// <param name="list" type="cfx.object"/>>
        /// <param name="index" type="number"/>>
    }
};

var cd = function() {
}
cfx.cd = cd;
cd.prototype = {
    getRow: function (row) {
        /// <param name="row" type="number"/>>
        /// <returns type="cfx.CellAttributes"/>
        return new CellAttributes();
    },
    setRow: function (row, value) {
        /// <param name="row" type="number"/>>
        /// <param name="value" type="cfx.CellAttributes"/>>
    },
    getItem: function (row, column) {
        /// <param name="row" type="number"/>>
        /// <param name="column" type="number"/>>
        /// <returns type="cfx.CellAttributes"/>
        return new CellAttributes();
    },
    setItem: function (row, column, value) {
        /// <param name="row" type="number"/>>
        /// <param name="column" type="number"/>>
        /// <param name="value" type="cfx.CellAttributes"/>>
    }
};

var cf = function() {
}
cfx.cf = cf;
cf.prototype = {
    getAutoSize: function () {
        /// <summary>Allows Chart FX to calculate the best size according to the tool position.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setAutoSize: function (value) {
        /// <summary>Allows Chart FX to calculate the best size according to the tool position.</summary>
        /// <param name="value" type="bool"/>>
    },
    getBackColor: function () {
        /// <summary>Gets a value for the background color of a dockable bar.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setBackColor: function (value) {
        /// <summary>Sets a value for the background color of a dockable bar.</summary>
        /// <param name="value" type="String"/>>
    },
    getBorderObject: function () {
        /// <returns type="cfx.object"/>
        return new object();
    },
    setBorderObject: function (value) {
        /// <param name="value" type="cfx.object"/>>
    }
};

var DataGrid = function() {
}
cfx.DataGrid = DataGrid;
DataGrid.prototype = {
    getBackColorData: function () {
        /// <summary>Sets the background color of the cells on the data grid.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setBackColorData: function (value) {
        /// <summary>Sets the background color of the cells on the data grid.</summary>
        /// <param name="value" type="String"/>>
    },
    getBackColorDataAlternate: function () {
        /// <summary>Assigns the alternate color for displaying the DataGrid rows or columns.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setBackColorDataAlternate: function (value) {
        /// <summary>Assigns the alternate color for displaying the DataGrid rows or columns.</summary>
        /// <param name="value" type="String"/>>
    },
    getBackColorHeader: function () {
        /// <summary>Sets the background color of the header for the Data Editor.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setBackColorHeader: function (value) {
        /// <summary>Sets the background color of the header for the Data Editor.</summary>
        /// <param name="value" type="String"/>>
    },
    getCells: function () {
        /// <summary>Gets the collection of CellAttributes that belong to the DataGrid.</summary>
        /// <returns type="cfx.cd"/>
        return new cd();
    },
    getContentLayout: function () {
        /// <summary>Allows you to align the data editor in the bottom of the chart area.</summary>
        /// <returns type="cfx.ContentLayout"/>
        return new ContentLayout();
    },
    setContentLayout: function (value) {
        /// <summary>Allows you to align the data editor in the bottom of the chart area.</summary>
        /// <param name="value" type="cfx.ContentLayout"/>>
    },
    getFont: function () {
        /// <summary>Allows you to set the font used throughout the data grid.</summary>
        /// <returns type="Font"/>
        return new Font();
    },
    setFont: function (value) {
        /// <summary>Allows you to set the font used throughout the data grid.</summary>
        /// <param name="value" type="Font"/>>
    },
    getHideXValues: function () {
        /// <summary>Used to hide the XValues in the data editor of an XY plot chart.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setHideXValues: function (value) {
        /// <summary>Used to hide the XValues in the data editor of an XY plot chart.</summary>
        /// <param name="value" type="bool"/>>
    },
    getHorizontalGridColor: function () {
        /// <summary>Allows you to set the Horizontal color for the gridlines displayed in the data grid.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setHorizontalGridColor: function (value) {
        /// <summary>Allows you to set the Horizontal color for the gridlines displayed in the data grid.</summary>
        /// <param name="value" type="String"/>>
    },
    getInterlaced: function () {
        /// <summary>Alternates the background color of on the data grid to improve readability.</summary>
        /// <returns type="cfx.Interlaced"/>
        return new Interlaced();
    },
    setInterlaced: function (value) {
        /// <summary>Alternates the background color of on the data grid to improve readability.</summary>
        /// <param name="value" type="cfx.Interlaced"/>>
    },
    getMaxSizePercentage: function () {
        /// <summary>Allows you to specify the maximum percentage of the chart area the data grid may occupy.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setMaxSizePercentage: function (value) {
        /// <summary>Allows you to specify the maximum percentage of the chart area the data grid may occupy.</summary>
        /// <param name="value" type="number"/>>
    },
    getReadOnly: function () {
        /// <returns type="bool"/>
        return true;
    },
    setReadOnly: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getScrollPosition: function () {
        /// <summary>Scrolls the data horizontally to the specific column in the Grid.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setScrollPosition: function (value) {
        /// <summary>Scrolls the data horizontally to the specific column in the Grid.</summary>
        /// <param name="value" type="number"/>>
    },
    getScrollSize: function () {
        /// <summary>Returns the number of scrolls in the DataEditor based on the scroll view configuration.</summary>
        /// <returns type="number"/>
        return 0;
    },
    getShowHeader: function () {
        /// <summary>Gets a value indicating the header of the columns should be visible or not.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setShowHeader: function (value) {
        /// <summary>Sets a value indicating the header of the columns should be visible or not.</summary>
        /// <param name="value" type="bool"/>>
    },
    getShowHiddenSeries: function () {
        /// <summary>Gets a value indicating hidden series should be visible in data editor.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setShowHiddenSeries: function (value) {
        /// <summary>Sets a value indicating hidden series should be visible in data editor.</summary>
        /// <param name="value" type="bool"/>>
    },
    getShowMarkers: function () {
        /// <summary>Gets a value indicating whether series markers should be visible in the series headers.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setShowMarkers: function (value) {
        /// <summary>Sets a value indicating whether series markers should be visible in the series headers.</summary>
        /// <param name="value" type="bool"/>>
    },
    getTextColorData: function () {
        /// <summary>Sets the text color of the cells on the data grid.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setTextColorData: function (value) {
        /// <summary>Sets the text color of the cells on the data grid.</summary>
        /// <param name="value" type="String"/>>
    },
    getTextColorDataAlternate: function () {
        /// <summary>Sets the alternate text color for text in the data grid cells.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setTextColorDataAlternate: function (value) {
        /// <summary>Sets the alternate text color for text in the data grid cells.</summary>
        /// <param name="value" type="String"/>>
    },
    getTextColorHeader: function () {
        /// <summary>Gets the text color of the headers on the data editor.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setTextColorHeader: function (value) {
        /// <summary>Sets the text color of the headers on the data editor.</summary>
        /// <param name="value" type="String"/>>
    },
    getUseRawData: function () {
        /// <summary>Gets a value indicating whether to use raw data or use calculated values in the DataEditor.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setUseRawData: function (value) {
        /// <summary>Sets a value indicating whether to use raw data or use calculated values in the DataEditor.</summary>
        /// <param name="value" type="bool"/>>
    },
    getVerticalGridColor: function () {
        /// <summary>Allows you to select the Vertical line color for the gridline displayed at the data editor box.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setVerticalGridColor: function (value) {
        /// <summary>Allows you to select the Vertical line color for the gridline displayed at the data editor box.</summary>
        /// <param name="value" type="String"/>>
    },
    getXValuesAsHeaders: function () {
        /// <summary>Gets a value instructing that XValues be used as the headers in the data editor.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setXValuesAsHeaders: function (value) {
        /// <summary>Sets a value instructing that XValues be used as the headers in the data editor.</summary>
        /// <param name="value" type="bool"/>>
    },
    getAutoSize: function () {
        /// <summary>Allows Chart FX to calculate the best size according to the tool position.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setAutoSize: function (value) {
        /// <summary>Allows Chart FX to calculate the best size according to the tool position.</summary>
        /// <param name="value" type="bool"/>>
    },
    getBackColor: function () {
        /// <summary>Gets a value for the background color of a dockable bar.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setBackColor: function (value) {
        /// <summary>Sets a value for the background color of a dockable bar.</summary>
        /// <param name="value" type="String"/>>
    },
    getBorderObject: function () {
        /// <returns type="cfx.object"/>
        return new object();
    },
    setBorderObject: function (value) {
        /// <param name="value" type="cfx.object"/>>
    }
};

var LegendBox = function() {
}
cfx.LegendBox = LegendBox;
LegendBox.prototype = {
    getContentLayout: function () {
        /// <summary>Allows you to align the content for the legend box.</summary>
        /// <returns type="cfx.ContentLayout"/>
        return new ContentLayout();
    },
    setContentLayout: function (value) {
        /// <summary>Allows you to align the content for the legend box.</summary>
        /// <param name="value" type="cfx.ContentLayout"/>>
    },
    getCustomItems: function () {
        /// <summary>Returns the collection of the custom items in the legend box.</summary>
        /// <returns type="cfx.cY"/>
        return new cY();
    },
    getFont: function () {
        /// <summary>Gets the font of the Legend Box.</summary>
        /// <returns type="Font"/>
        return new Font();
    },
    setFont: function (value) {
        /// <summary>Sets the font of the Legend Box.</summary>
        /// <param name="value" type="Font"/>>
    },
    getItemAttributes: function () {
        /// <summary>Allows you to configure the Legend Box Items in the chart.</summary>
        /// <returns type="cfx.b_"/>
        return new b_();
    },
    getLineSpacing: function () {
        /// <summary>Gets a value indicating the LineSpacing for a LegendBox.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setLineSpacing: function (value) {
        /// <summary>Sets a value indicating the LineSpacing for a LegendBox.</summary>
        /// <param name="value" type="number"/>>
    },
    getMarginX: function () {
        /// <summary>Gets a value specifying the margin width for a LegendBox.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setMarginX: function (value) {
        /// <summary>Sets a value specifying the margin width for a LegendBox.</summary>
        /// <param name="value" type="number"/>>
    },
    getMarginY: function () {
        /// <summary>Gets a value specifying the margin height for a LegendBox.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setMarginY: function (value) {
        /// <summary>Sets a value specifying the margin height for a LegendBox.</summary>
        /// <param name="value" type="number"/>>
    },
    getPlotAreaOnly: function () {
        /// <returns type="bool"/>
        return true;
    },
    setPlotAreaOnly: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getStyle: function () {
        /// <returns type="cfx.LegendBoxStyles"/>
        return new LegendBoxStyles();
    },
    setStyle: function (value) {
        /// <param name="value" type="cfx.LegendBoxStyles"/>>
    },
    getTextColor: function () {
        /// <summary>Gets the color used for the text in the legend box.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setTextColor: function (value) {
        /// <summary>Sets the color used for the text in the legend box.</summary>
        /// <param name="value" type="String"/>>
    },
    getTitles: function () {
        /// <returns type="cfx.cP"/>
        return new cP();
    },
    sizeToFit: function () {
    },
    getAutoSize: function () {
        /// <summary>Allows Chart FX to calculate the best size according to the tool position.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setAutoSize: function (value) {
        /// <summary>Allows Chart FX to calculate the best size according to the tool position.</summary>
        /// <param name="value" type="bool"/>>
    },
    getBackColor: function () {
        /// <summary>Gets a value for the background color of a dockable bar.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setBackColor: function (value) {
        /// <summary>Sets a value for the background color of a dockable bar.</summary>
        /// <param name="value" type="String"/>>
    },
    getBorderObject: function () {
        /// <returns type="cfx.object"/>
        return new object();
    },
    setBorderObject: function (value) {
        /// <param name="value" type="cfx.object"/>>
    }
};

var ck = function() {
}
cfx.ck = ck;
ck.prototype = {
    getItem: function (n) {
        /// <param name="n" type="number"/>>
        /// <returns type="?"/>
        return new ?();
    },
    add: function (obj) {
        /// <param name="obj" type="?"/>>
    },
    clear: function () {
    },
    find: function (type) {
        /// <param name="type" type="Type"/>>
        /// <returns type="?"/>
        return new ?();
    },
    indexOf: function (extension) {
        /// <param name="extension" type="?"/>>
        /// <returns type="number"/>
        return 0;
    },
    remove: function (obj) {
        /// <param name="obj" type="?"/>>
    }
};

var cm = function() {
}
cfx.cm = cm;
cm.prototype = {
    getEndCap: function () {
        /// <summary>Gets a marker shape at the end of a PointAttribute's Line.</summary>
        /// <returns type="LineCap"/>
        return new LineCap();
    },
    setEndCap: function (value) {
        /// <summary>Sets a marker shape at the end of a PointAttribute's Line.</summary>
        /// <param name="value" type="LineCap"/>>
    },
    getStartCap: function () {
        /// <summary>Gets a marker shape at the start of a PointAttribute's Line.</summary>
        /// <returns type="LineCap"/>
        return new LineCap();
    },
    setStartCap: function (value) {
        /// <summary>Sets a marker shape at the start of a PointAttribute's Line.</summary>
        /// <param name="value" type="LineCap"/>>
    },
    getStyle: function () {
        /// <summary>Gets the style for a PointAttribute's Line.</summary>
        /// <returns type="DashStyle"/>
        return new DashStyle();
    },
    setStyle: function (value) {
        /// <summary>Sets the style for a PointAttribute's Line.</summary>
        /// <param name="value" type="DashStyle"/>>
    },
    getWidth: function () {
        /// <summary>Gets the width for a PointAttribute's Line.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setWidth: function (value) {
        /// <summary>Sets the width for a PointAttribute's Line.</summary>
        /// <param name="value" type="number"/>>
    }
};

var cn = function() {
}
cfx.cn = cn;
cn.prototype = {
    getBetweenSegments: function () {
        /// <summary>Gets a property which makes the border to be shown between segments.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setBetweenSegments: function (value) {
        /// <summary>Sets a property which makes the border to be shown between segments.</summary>
        /// <param name="value" type="bool"/>>
    },
    getColor: function () {
        /// <summary>Gets the color of the border of a point marker.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <summary>Sets the color of the border of a point marker.</summary>
        /// <param name="value" type="String"/>>
    },
    getEffect: function () {
        /// <summary>Gets the BorderEffect for the border of a point marker.</summary>
        /// <returns type="cfx.BorderEffect"/>
        return new BorderEffect();
    },
    setEffect: function (value) {
        /// <summary>Sets the BorderEffect for the border of a point marker.</summary>
        /// <param name="value" type="cfx.BorderEffect"/>>
    },
    getStyle: function () {
        /// <summary>Represents the style of the line for the border of a point marker.</summary>
        /// <returns type="DashStyle"/>
        return new DashStyle();
    },
    setStyle: function (value) {
        /// <summary>Represents the style of the line for the border of a point marker.</summary>
        /// <param name="value" type="DashStyle"/>>
    },
    getUseForLines: function () {
        /// <summary>Gets a property which applies the BorderAttributes to the lines of a line chart.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setUseForLines: function (value) {
        /// <summary>Sets a property which applies the BorderAttributes to the lines of a line chart.</summary>
        /// <param name="value" type="bool"/>>
    },
    getVisible: function () {
        /// <summary>Gets a value making the border visible or not.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <summary>Sets a value making the border visible or not.</summary>
        /// <param name="value" type="bool"/>>
    },
    getWidth: function () {
        /// <summary>Gets a value in pixels with the width for the border of a point marker.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setWidth: function (value) {
        /// <summary>Sets a value in pixels with the width for the border of a point marker.</summary>
        /// <param name="value" type="number"/>>
    }
};

var co = function() {
}
cfx.co = co;
co.prototype = {
    getAlignment: function () {
        /// <summary>Gets the vertical alignment for the point label.</summary>
        /// <returns type="StringAlignment"/>
        return new StringAlignment();
    },
    setAlignment: function (value) {
        /// <summary>Sets the vertical alignment for the point label.</summary>
        /// <param name="value" type="StringAlignment"/>>
    },
    getAngle: function () {
        /// <summary>Gets the angle of the point label.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setAngle: function (value) {
        /// <summary>Sets the angle of the point label.</summary>
        /// <param name="value" type="number"/>>
    },
    getBackColor: function () {
        /// <summary>Gets the backgrond color of the point label.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setBackColor: function (value) {
        /// <summary>Sets the backgrond color of the point label.</summary>
        /// <param name="value" type="String"/>>
    },
    getBackgroundVisible: function () {
        /// <returns type="bool"/>
        return true;
    },
    setBackgroundVisible: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getBorderColor: function () {
        /// <summary>Gets the border color of the point label.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setBorderColor: function (value) {
        /// <summary>Sets the border color of the point label.</summary>
        /// <param name="value" type="String"/>>
    },
    getBorderWidth: function () {
        /// <summary>Gets a value indicating the width of the border surrounding the point label.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setBorderWidth: function (value) {
        /// <summary>Sets a value indicating the width of the border surrounding the point label.</summary>
        /// <param name="value" type="number"/>>
    },
    getFont: function () {
        /// <summary>Gets a Font object to use as rendering font for the point label.</summary>
        /// <returns type="Font"/>
        return new Font();
    },
    setFont: function (value) {
        /// <summary>Sets a Font object to use as rendering font for the point label.</summary>
        /// <param name="value" type="Font"/>>
    },
    getFormat: function () {
        /// <summary>Gets a string pattern mask used to format the label.</summary>
        /// <returns type="string"/>
        return "";
    },
    setFormat: function (value) {
        /// <summary>Sets a string pattern mask used to format the label.</summary>
        /// <param name="value" type="string"/>>
    },
    getLineAlignment: function () {
        /// <summary>Gets the horizontal alignment for the point label.</summary>
        /// <returns type="StringAlignment"/>
        return new StringAlignment();
    },
    setLineAlignment: function (value) {
        /// <summary>Sets the horizontal alignment for the point label.</summary>
        /// <param name="value" type="StringAlignment"/>>
    },
    getOffset: function () {
        /// <returns type="Point"/>
        return new Point();
    },
    setOffset: function (value) {
        /// <param name="value" type="Point"/>>
    },
    getRichText: function () {
        /// <summary>Sets or Gets a value indicating the Text will be treated as RichText</summary>
        /// <returns type="bool"/>
        return true;
    },
    setRichText: function (value) {
        /// <summary>Sets or Gets a value indicating the Text will be treated as RichText</summary>
        /// <param name="value" type="bool"/>>
    },
    getTextColor: function () {
        /// <summary>Gets a value for the point label color.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setTextColor: function (value) {
        /// <summary>Sets a value for the point label color.</summary>
        /// <param name="value" type="String"/>>
    },
    getVisible: function () {
        /// <summary>Gets a value indicating if point label should be displayed or not.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <summary>Sets a value indicating if point label should be displayed or not.</summary>
        /// <param name="value" type="bool"/>>
    }
};

var PointAttributes = function() {
}
cfx.PointAttributes = PointAttributes;
PointAttributes.prototype = {
    getAlternateColor: function () {
        /// <summary>Gets the alternate color for a selected item when using gradient FillMode.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setAlternateColor: function (value) {
        /// <summary>Sets the alternate color for a selected item when using gradient FillMode.</summary>
        /// <param name="value" type="String"/>>
    },
    getBarShape: function () {
        /// <summary>Gets the Shape for the Bars.</summary>
        /// <returns type="cfx.BarShape"/>
        return new BarShape();
    },
    setBarShape: function (value) {
        /// <summary>Sets the Shape for the Bars.</summary>
        /// <param name="value" type="cfx.BarShape"/>>
    },
    getBorder: function () {
        /// <summary>Provides access to the border attributes object.</summary>
        /// <returns type="cfx.cn"/>
        return new cn();
    },
    getColor: function () {
        /// <summary>Allows you to set a color for the selected item.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <summary>Allows you to set a color for the selected item.</summary>
        /// <param name="value" type="String"/>>
    },
    getFillMode: function () {
        /// <summary>Gets a value indicating how Chart FX should fill the area representing the point.</summary>
        /// <returns type="cfx.FillMode"/>
        return new FillMode();
    },
    setFillMode: function (value) {
        /// <summary>Sets a value indicating how Chart FX should fill the area representing the point.</summary>
        /// <param name="value" type="cfx.FillMode"/>>
    },
    getLine: function () {
        /// <summary>Gets the LineAttributes object for the particular point.</summary>
        /// <returns type="cfx.cm"/>
        return new cm();
    },
    getMarkerShape: function () {
        /// <summary>Gets the the type used to paint markers for the selected item.</summary>
        /// <returns type="cfx.MarkerShape"/>
        return new MarkerShape();
    },
    setMarkerShape: function (value) {
        /// <summary>Sets the the type used to paint markers for the selected item.</summary>
        /// <param name="value" type="cfx.MarkerShape"/>>
    },
    getMarkerSize: function () {
        /// <summary>Gets a value controlling the size of the markers for the selected item.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setMarkerSize: function (value) {
        /// <summary>Sets a value controlling the size of the markers for the selected item.</summary>
        /// <param name="value" type="number"/>>
    },
    getMarkerStyle: function () {
        /// <summary>Gets the the style used to paint markers for the selected item.</summary>
        /// <returns type="cfx.MarkerStyle"/>
        return new MarkerStyle();
    },
    setMarkerStyle: function (value) {
        /// <summary>Sets the the style used to paint markers for the selected item.</summary>
        /// <param name="value" type="cfx.MarkerStyle"/>>
    },
    getMarkerTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setMarkerTemplate: function (value) {
        /// <param name="value" type="string"/>>
    },
    getMarkerWidth: function () {
        /// <returns type="number"/>
        return 0;
    },
    setMarkerWidth: function (value) {
        /// <param name="value" type="number"/>>
    },
    getPattern: function () {
        /// <returns type="HatchStyle"/>
        return new HatchStyle();
    },
    setPattern: function (value) {
        /// <param name="value" type="HatchStyle"/>>
    },
    getPicture: function () {
        /// <summary>Gets a value specifying the picture associated to the selected item.</summary>
        /// <returns type="Image"/>
        return new Image();
    },
    setPicture: function (value) {
        /// <summary>Sets a value specifying the picture associated to the selected item.</summary>
        /// <param name="value" type="Image"/>>
    },
    getPointLabels: function () {
        /// <summary>Provides access to PointLabelAttributes allowing for the manipulation of attributes pertaining to the point label.</summary>
        /// <returns type="cfx.co"/>
        return new co();
    },
    getSeparateSlice: function () {
        /// <summary>Gets a value allowing you to separate a slice of a Pie or Doughnut chart for a selected item.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setSeparateSlice: function (value) {
        /// <summary>Sets a value allowing you to separate a slice of a Pie or Doughnut chart for a selected item.</summary>
        /// <param name="value" type="number"/>>
    },
    getTag: function () {
        /// <summary>Gets and object for the specific item.</summary>
        /// <returns type="?"/>
        return new ?();
    },
    setTag: function (value) {
        /// <summary>Sets and object for the specific item.</summary>
        /// <param name="value" type="?"/>>
    },
    getText: function () {
        /// <summary>Gets a value for labeling the point or series attribute.</summary>
        /// <returns type="string"/>
        return "";
    },
    setText: function (value) {
        /// <summary>Sets a value for labeling the point or series attribute.</summary>
        /// <param name="value" type="string"/>>
    },
    getVolume: function () {
        /// <summary>Gets a value indicating a volume for a data point markers in supported galleries.  By adjusting the volume, you can modify the amount of space you want between the items.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setVolume: function (value) {
        /// <summary>Sets a value indicating a volume for a data point markers in supported galleries.  By adjusting the volume, you can modify the amount of space you want between the items.</summary>
        /// <param name="value" type="number"/>>
    }
};

var ConditionalAttributes = function() {
}
cfx.ConditionalAttributes = ConditionalAttributes;
ConditionalAttributes.prototype = {
    getCondition: function () {
        /// <summary>Returns a Condition object for the specified ConditionalAttributes object.</summary>
        /// <returns type="cfx.object"/>
        return new object();
    },
    setCondition: function (value) {
        /// <summary>Returns a Condition object for the specified ConditionalAttributes object.</summary>
        /// <param name="value" type="cfx.object"/>>
    },
    getIndex: function () {
        /// <returns type="number"/>
        return 0;
    },
    getInterpolateColor: function () {
        /// <returns type="bool"/>
        return true;
    },
    setInterpolateColor: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getSeries: function () {
        /// <summary>Gets a value indicating which series index the ConditionalAttribute will be applied to.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setSeries: function (value) {
        /// <summary>Sets a value indicating which series index the ConditionalAttribute will be applied to.</summary>
        /// <param name="value" type="number"/>>
    },
    getVisible: function () {
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getAlternateColor: function () {
        /// <summary>Gets the alternate color for a selected item when using gradient FillMode.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setAlternateColor: function (value) {
        /// <summary>Sets the alternate color for a selected item when using gradient FillMode.</summary>
        /// <param name="value" type="String"/>>
    },
    getBarShape: function () {
        /// <summary>Gets the Shape for the Bars.</summary>
        /// <returns type="cfx.BarShape"/>
        return new BarShape();
    },
    setBarShape: function (value) {
        /// <summary>Sets the Shape for the Bars.</summary>
        /// <param name="value" type="cfx.BarShape"/>>
    },
    getBorder: function () {
        /// <summary>Provides access to the border attributes object.</summary>
        /// <returns type="cfx.cn"/>
        return new cn();
    },
    getColor: function () {
        /// <summary>Allows you to set a color for the selected item.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <summary>Allows you to set a color for the selected item.</summary>
        /// <param name="value" type="String"/>>
    },
    getFillMode: function () {
        /// <summary>Gets a value indicating how Chart FX should fill the area representing the point.</summary>
        /// <returns type="cfx.FillMode"/>
        return new FillMode();
    },
    setFillMode: function (value) {
        /// <summary>Sets a value indicating how Chart FX should fill the area representing the point.</summary>
        /// <param name="value" type="cfx.FillMode"/>>
    },
    getLine: function () {
        /// <summary>Gets the LineAttributes object for the particular point.</summary>
        /// <returns type="cfx.cm"/>
        return new cm();
    },
    getMarkerShape: function () {
        /// <summary>Gets the the type used to paint markers for the selected item.</summary>
        /// <returns type="cfx.MarkerShape"/>
        return new MarkerShape();
    },
    setMarkerShape: function (value) {
        /// <summary>Sets the the type used to paint markers for the selected item.</summary>
        /// <param name="value" type="cfx.MarkerShape"/>>
    },
    getMarkerSize: function () {
        /// <summary>Gets a value controlling the size of the markers for the selected item.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setMarkerSize: function (value) {
        /// <summary>Sets a value controlling the size of the markers for the selected item.</summary>
        /// <param name="value" type="number"/>>
    },
    getMarkerStyle: function () {
        /// <summary>Gets the the style used to paint markers for the selected item.</summary>
        /// <returns type="cfx.MarkerStyle"/>
        return new MarkerStyle();
    },
    setMarkerStyle: function (value) {
        /// <summary>Sets the the style used to paint markers for the selected item.</summary>
        /// <param name="value" type="cfx.MarkerStyle"/>>
    },
    getMarkerTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setMarkerTemplate: function (value) {
        /// <param name="value" type="string"/>>
    },
    getMarkerWidth: function () {
        /// <returns type="number"/>
        return 0;
    },
    setMarkerWidth: function (value) {
        /// <param name="value" type="number"/>>
    },
    getPattern: function () {
        /// <returns type="HatchStyle"/>
        return new HatchStyle();
    },
    setPattern: function (value) {
        /// <param name="value" type="HatchStyle"/>>
    },
    getPicture: function () {
        /// <summary>Gets a value specifying the picture associated to the selected item.</summary>
        /// <returns type="Image"/>
        return new Image();
    },
    setPicture: function (value) {
        /// <summary>Sets a value specifying the picture associated to the selected item.</summary>
        /// <param name="value" type="Image"/>>
    },
    getPointLabels: function () {
        /// <summary>Provides access to PointLabelAttributes allowing for the manipulation of attributes pertaining to the point label.</summary>
        /// <returns type="cfx.co"/>
        return new co();
    },
    getSeparateSlice: function () {
        /// <summary>Gets a value allowing you to separate a slice of a Pie or Doughnut chart for a selected item.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setSeparateSlice: function (value) {
        /// <summary>Sets a value allowing you to separate a slice of a Pie or Doughnut chart for a selected item.</summary>
        /// <param name="value" type="number"/>>
    },
    getTag: function () {
        /// <summary>Gets and object for the specific item.</summary>
        /// <returns type="?"/>
        return new ?();
    },
    setTag: function (value) {
        /// <summary>Sets and object for the specific item.</summary>
        /// <param name="value" type="?"/>>
    },
    getText: function () {
        /// <summary>Gets a value for labeling the point or series attribute.</summary>
        /// <returns type="string"/>
        return "";
    },
    setText: function (value) {
        /// <summary>Sets a value for labeling the point or series attribute.</summary>
        /// <param name="value" type="string"/>>
    },
    getVolume: function () {
        /// <summary>Gets a value indicating a volume for a data point markers in supported galleries.  By adjusting the volume, you can modify the amount of space you want between the items.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setVolume: function (value) {
        /// <summary>Sets a value indicating a volume for a data point markers in supported galleries.  By adjusting the volume, you can modify the amount of space you want between the items.</summary>
        /// <param name="value" type="number"/>>
    }
};

var SeriesAttributes = function() {
}
cfx.SeriesAttributes = SeriesAttributes;
SeriesAttributes.prototype = {
    getAxisX: function () {
        /// <summary>Gets a value indicating the AxisX the series is connected to.</summary>
        /// <returns type="cfx.AxisX"/>
        return new AxisX();
    },
    setAxisX: function (value) {
        /// <summary>Sets a value indicating the AxisX the series is connected to.</summary>
        /// <param name="value" type="cfx.AxisX"/>>
    },
    getAxisY: function () {
        /// <summary>Gets a value indicating the AxisY the series is connected to.</summary>
        /// <returns type="cfx.AxisY"/>
        return new AxisY();
    },
    setAxisY: function (value) {
        /// <summary>Sets a value indicating the AxisY the series is connected to.</summary>
        /// <param name="value" type="cfx.AxisY"/>>
    },
    getColor: function () {
        /// <summary>Gets a Color for the selected series.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <summary>Sets a Color for the selected series.</summary>
        /// <param name="value" type="String"/>>
    },
    getGallery: function () {
        /// <summary>Gets a gallery type for a particular series.  All chart types are available in both 2D and 3D modes.</summary>
        /// <returns type="cfx.Gallery"/>
        return new Gallery();
    },
    setGallery: function (value) {
        /// <summary>Sets a gallery type for a particular series.  All chart types are available in both 2D and 3D modes.</summary>
        /// <param name="value" type="cfx.Gallery"/>>
    },
    getGalleryAttributes: function () {
        /// <summary>Used to create a gallery object to assign additional attributes for a particular series.</summary>
        /// <returns type="?"/>
        return new ?();
    },
    setGalleryAttributes: function (value) {
        /// <summary>Used to create a gallery object to assign additional attributes for a particular series.</summary>
        /// <param name="value" type="?"/>>
    },
    getMarkerFont: function () {
        /// <summary>Gets a wingding font for use as marker shapes.</summary>
        /// <returns type="Font"/>
        return new Font();
    },
    setMarkerFont: function (value) {
        /// <summary>Sets a wingding font for use as marker shapes.</summary>
        /// <param name="value" type="Font"/>>
    },
    getMarkerStep: function () {
        /// <summary>Controls how frequently point markers are shown in the selected series.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setMarkerStep: function (value) {
        /// <summary>Controls how frequently point markers are shown in the selected series.</summary>
        /// <param name="value" type="number"/>>
    },
    getOriginalPosition: function () {
        /// <summary>Returns the original position of the item in the SeriesAttributes Collection.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setOriginalPosition: function (value) {
        /// <summary>Returns the original position of the item in the SeriesAttributes Collection.</summary>
        /// <param name="value" type="number"/>>
    },
    getPane: function () {
        /// <summary>Gets the Pane which the series belongs to.</summary>
        /// <returns type="cfx.Pane"/>
        return new Pane();
    },
    setPane: function (value) {
        /// <summary>Sets the Pane which the series belongs to.</summary>
        /// <param name="value" type="cfx.Pane"/>>
    },
    getStacked: function () {
        /// <summary>Gets a value allowing you to stack the selected series with the previous series to achieve multi-stacked charts.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setStacked: function (value) {
        /// <summary>Sets a value allowing you to stack the selected series with the previous series to achieve multi-stacked charts.</summary>
        /// <param name="value" type="bool"/>>
    },
    getVisible: function () {
        /// <summary>Gets a value allowing you to show or hide the series.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <summary>Sets a value allowing you to show or hide the series.</summary>
        /// <param name="value" type="bool"/>>
    },
    bringToFront: function () {
    },
    sendToBack: function () {
    },
    getAlternateColor: function () {
        /// <summary>Gets the alternate color for a selected item when using gradient FillMode.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setAlternateColor: function (value) {
        /// <summary>Sets the alternate color for a selected item when using gradient FillMode.</summary>
        /// <param name="value" type="String"/>>
    },
    getBarShape: function () {
        /// <summary>Gets the Shape for the Bars.</summary>
        /// <returns type="cfx.BarShape"/>
        return new BarShape();
    },
    setBarShape: function (value) {
        /// <summary>Sets the Shape for the Bars.</summary>
        /// <param name="value" type="cfx.BarShape"/>>
    },
    getBorder: function () {
        /// <summary>Provides access to the border attributes object.</summary>
        /// <returns type="cfx.cn"/>
        return new cn();
    },
    getColor: function () {
        /// <summary>Allows you to set a color for the selected item.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <summary>Allows you to set a color for the selected item.</summary>
        /// <param name="value" type="String"/>>
    },
    getFillMode: function () {
        /// <summary>Gets a value indicating how Chart FX should fill the area representing the point.</summary>
        /// <returns type="cfx.FillMode"/>
        return new FillMode();
    },
    setFillMode: function (value) {
        /// <summary>Sets a value indicating how Chart FX should fill the area representing the point.</summary>
        /// <param name="value" type="cfx.FillMode"/>>
    },
    getLine: function () {
        /// <summary>Gets the LineAttributes object for the particular point.</summary>
        /// <returns type="cfx.cm"/>
        return new cm();
    },
    getMarkerShape: function () {
        /// <summary>Gets the the type used to paint markers for the selected item.</summary>
        /// <returns type="cfx.MarkerShape"/>
        return new MarkerShape();
    },
    setMarkerShape: function (value) {
        /// <summary>Sets the the type used to paint markers for the selected item.</summary>
        /// <param name="value" type="cfx.MarkerShape"/>>
    },
    getMarkerSize: function () {
        /// <summary>Gets a value controlling the size of the markers for the selected item.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setMarkerSize: function (value) {
        /// <summary>Sets a value controlling the size of the markers for the selected item.</summary>
        /// <param name="value" type="number"/>>
    },
    getMarkerStyle: function () {
        /// <summary>Gets the the style used to paint markers for the selected item.</summary>
        /// <returns type="cfx.MarkerStyle"/>
        return new MarkerStyle();
    },
    setMarkerStyle: function (value) {
        /// <summary>Sets the the style used to paint markers for the selected item.</summary>
        /// <param name="value" type="cfx.MarkerStyle"/>>
    },
    getMarkerTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setMarkerTemplate: function (value) {
        /// <param name="value" type="string"/>>
    },
    getMarkerWidth: function () {
        /// <returns type="number"/>
        return 0;
    },
    setMarkerWidth: function (value) {
        /// <param name="value" type="number"/>>
    },
    getPattern: function () {
        /// <returns type="HatchStyle"/>
        return new HatchStyle();
    },
    setPattern: function (value) {
        /// <param name="value" type="HatchStyle"/>>
    },
    getPicture: function () {
        /// <summary>Gets a value specifying the picture associated to the selected item.</summary>
        /// <returns type="Image"/>
        return new Image();
    },
    setPicture: function (value) {
        /// <summary>Sets a value specifying the picture associated to the selected item.</summary>
        /// <param name="value" type="Image"/>>
    },
    getPointLabels: function () {
        /// <summary>Provides access to PointLabelAttributes allowing for the manipulation of attributes pertaining to the point label.</summary>
        /// <returns type="cfx.co"/>
        return new co();
    },
    getSeparateSlice: function () {
        /// <summary>Gets a value allowing you to separate a slice of a Pie or Doughnut chart for a selected item.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setSeparateSlice: function (value) {
        /// <summary>Sets a value allowing you to separate a slice of a Pie or Doughnut chart for a selected item.</summary>
        /// <param name="value" type="number"/>>
    },
    getTag: function () {
        /// <summary>Gets and object for the specific item.</summary>
        /// <returns type="?"/>
        return new ?();
    },
    setTag: function (value) {
        /// <summary>Sets and object for the specific item.</summary>
        /// <param name="value" type="?"/>>
    },
    getText: function () {
        /// <summary>Gets a value for labeling the point or series attribute.</summary>
        /// <returns type="string"/>
        return "";
    },
    setText: function (value) {
        /// <summary>Sets a value for labeling the point or series attribute.</summary>
        /// <param name="value" type="string"/>>
    },
    getVolume: function () {
        /// <summary>Gets a value indicating a volume for a data point markers in supported galleries.  By adjusting the volume, you can modify the amount of space you want between the items.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setVolume: function (value) {
        /// <summary>Sets a value indicating a volume for a data point markers in supported galleries.  By adjusting the volume, you can modify the amount of space you want between the items.</summary>
        /// <param name="value" type="number"/>>
    }
};

var cq = function() {
}
cfx.cq = cq;
cq.prototype = {
    getGallery: function () {
        /// <summary>Gets and sets the chart type for all Series.</summary>
        /// <returns type="cfx.Gallery"/>
        return new Gallery();
    },
    setGallery: function (value) {
        /// <summary>Gets and sets the chart type for all Series.</summary>
        /// <param name="value" type="cfx.Gallery"/>>
    },
    getHorizontal: function () {
        /// <summary>Gets a value specifying to transpose X and Y axes in a chart.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setHorizontal: function (value) {
        /// <summary>Sets a value specifying to transpose X and Y axes in a chart.</summary>
        /// <param name="value" type="bool"/>>
    },
    getMultipleColors: function () {
        /// <summary>Gets and sets the Multiple colors to allow for independently colored data points.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setMultipleColors: function (value) {
        /// <summary>Gets and sets the Multiple colors to allow for independently colored data points.</summary>
        /// <param name="value" type="bool"/>>
    },
    getStackedStyle: function () {
        /// <returns type="cfx.Stacked"/>
        return new Stacked();
    },
    setStackedStyle: function (value) {
        /// <param name="value" type="cfx.Stacked"/>>
    },
    getAxisX: function () {
        /// <summary>Gets a value indicating the AxisX the series is connected to.</summary>
        /// <returns type="cfx.AxisX"/>
        return new AxisX();
    },
    setAxisX: function (value) {
        /// <summary>Sets a value indicating the AxisX the series is connected to.</summary>
        /// <param name="value" type="cfx.AxisX"/>>
    },
    getAxisY: function () {
        /// <summary>Gets a value indicating the AxisY the series is connected to.</summary>
        /// <returns type="cfx.AxisY"/>
        return new AxisY();
    },
    setAxisY: function (value) {
        /// <summary>Sets a value indicating the AxisY the series is connected to.</summary>
        /// <param name="value" type="cfx.AxisY"/>>
    },
    getColor: function () {
        /// <summary>Gets a Color for the selected series.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <summary>Sets a Color for the selected series.</summary>
        /// <param name="value" type="String"/>>
    },
    getGallery: function () {
        /// <summary>Gets a gallery type for a particular series.  All chart types are available in both 2D and 3D modes.</summary>
        /// <returns type="cfx.Gallery"/>
        return new Gallery();
    },
    setGallery: function (value) {
        /// <summary>Sets a gallery type for a particular series.  All chart types are available in both 2D and 3D modes.</summary>
        /// <param name="value" type="cfx.Gallery"/>>
    },
    getGalleryAttributes: function () {
        /// <summary>Used to create a gallery object to assign additional attributes for a particular series.</summary>
        /// <returns type="?"/>
        return new ?();
    },
    setGalleryAttributes: function (value) {
        /// <summary>Used to create a gallery object to assign additional attributes for a particular series.</summary>
        /// <param name="value" type="?"/>>
    },
    getMarkerFont: function () {
        /// <summary>Gets a wingding font for use as marker shapes.</summary>
        /// <returns type="Font"/>
        return new Font();
    },
    setMarkerFont: function (value) {
        /// <summary>Sets a wingding font for use as marker shapes.</summary>
        /// <param name="value" type="Font"/>>
    },
    getMarkerStep: function () {
        /// <summary>Controls how frequently point markers are shown in the selected series.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setMarkerStep: function (value) {
        /// <summary>Controls how frequently point markers are shown in the selected series.</summary>
        /// <param name="value" type="number"/>>
    },
    getOriginalPosition: function () {
        /// <summary>Returns the original position of the item in the SeriesAttributes Collection.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setOriginalPosition: function (value) {
        /// <summary>Returns the original position of the item in the SeriesAttributes Collection.</summary>
        /// <param name="value" type="number"/>>
    },
    getPane: function () {
        /// <summary>Gets the Pane which the series belongs to.</summary>
        /// <returns type="cfx.Pane"/>
        return new Pane();
    },
    setPane: function (value) {
        /// <summary>Sets the Pane which the series belongs to.</summary>
        /// <param name="value" type="cfx.Pane"/>>
    },
    getStacked: function () {
        /// <summary>Gets a value allowing you to stack the selected series with the previous series to achieve multi-stacked charts.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setStacked: function (value) {
        /// <summary>Sets a value allowing you to stack the selected series with the previous series to achieve multi-stacked charts.</summary>
        /// <param name="value" type="bool"/>>
    },
    getVisible: function () {
        /// <summary>Gets a value allowing you to show or hide the series.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <summary>Sets a value allowing you to show or hide the series.</summary>
        /// <param name="value" type="bool"/>>
    },
    bringToFront: function () {
    },
    sendToBack: function () {
    },
    getAlternateColor: function () {
        /// <summary>Gets the alternate color for a selected item when using gradient FillMode.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setAlternateColor: function (value) {
        /// <summary>Sets the alternate color for a selected item when using gradient FillMode.</summary>
        /// <param name="value" type="String"/>>
    },
    getBarShape: function () {
        /// <summary>Gets the Shape for the Bars.</summary>
        /// <returns type="cfx.BarShape"/>
        return new BarShape();
    },
    setBarShape: function (value) {
        /// <summary>Sets the Shape for the Bars.</summary>
        /// <param name="value" type="cfx.BarShape"/>>
    },
    getBorder: function () {
        /// <summary>Provides access to the border attributes object.</summary>
        /// <returns type="cfx.cn"/>
        return new cn();
    },
    getColor: function () {
        /// <summary>Allows you to set a color for the selected item.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <summary>Allows you to set a color for the selected item.</summary>
        /// <param name="value" type="String"/>>
    },
    getFillMode: function () {
        /// <summary>Gets a value indicating how Chart FX should fill the area representing the point.</summary>
        /// <returns type="cfx.FillMode"/>
        return new FillMode();
    },
    setFillMode: function (value) {
        /// <summary>Sets a value indicating how Chart FX should fill the area representing the point.</summary>
        /// <param name="value" type="cfx.FillMode"/>>
    },
    getLine: function () {
        /// <summary>Gets the LineAttributes object for the particular point.</summary>
        /// <returns type="cfx.cm"/>
        return new cm();
    },
    getMarkerShape: function () {
        /// <summary>Gets the the type used to paint markers for the selected item.</summary>
        /// <returns type="cfx.MarkerShape"/>
        return new MarkerShape();
    },
    setMarkerShape: function (value) {
        /// <summary>Sets the the type used to paint markers for the selected item.</summary>
        /// <param name="value" type="cfx.MarkerShape"/>>
    },
    getMarkerSize: function () {
        /// <summary>Gets a value controlling the size of the markers for the selected item.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setMarkerSize: function (value) {
        /// <summary>Sets a value controlling the size of the markers for the selected item.</summary>
        /// <param name="value" type="number"/>>
    },
    getMarkerStyle: function () {
        /// <summary>Gets the the style used to paint markers for the selected item.</summary>
        /// <returns type="cfx.MarkerStyle"/>
        return new MarkerStyle();
    },
    setMarkerStyle: function (value) {
        /// <summary>Sets the the style used to paint markers for the selected item.</summary>
        /// <param name="value" type="cfx.MarkerStyle"/>>
    },
    getMarkerTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setMarkerTemplate: function (value) {
        /// <param name="value" type="string"/>>
    },
    getMarkerWidth: function () {
        /// <returns type="number"/>
        return 0;
    },
    setMarkerWidth: function (value) {
        /// <param name="value" type="number"/>>
    },
    getPattern: function () {
        /// <returns type="HatchStyle"/>
        return new HatchStyle();
    },
    setPattern: function (value) {
        /// <param name="value" type="HatchStyle"/>>
    },
    getPicture: function () {
        /// <summary>Gets a value specifying the picture associated to the selected item.</summary>
        /// <returns type="Image"/>
        return new Image();
    },
    setPicture: function (value) {
        /// <summary>Sets a value specifying the picture associated to the selected item.</summary>
        /// <param name="value" type="Image"/>>
    },
    getPointLabels: function () {
        /// <summary>Provides access to PointLabelAttributes allowing for the manipulation of attributes pertaining to the point label.</summary>
        /// <returns type="cfx.co"/>
        return new co();
    },
    getSeparateSlice: function () {
        /// <summary>Gets a value allowing you to separate a slice of a Pie or Doughnut chart for a selected item.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setSeparateSlice: function (value) {
        /// <summary>Sets a value allowing you to separate a slice of a Pie or Doughnut chart for a selected item.</summary>
        /// <param name="value" type="number"/>>
    },
    getTag: function () {
        /// <summary>Gets and object for the specific item.</summary>
        /// <returns type="?"/>
        return new ?();
    },
    setTag: function (value) {
        /// <summary>Sets and object for the specific item.</summary>
        /// <param name="value" type="?"/>>
    },
    getText: function () {
        /// <summary>Gets a value for labeling the point or series attribute.</summary>
        /// <returns type="string"/>
        return "";
    },
    setText: function (value) {
        /// <summary>Sets a value for labeling the point or series attribute.</summary>
        /// <param name="value" type="string"/>>
    },
    getVolume: function () {
        /// <summary>Gets a value indicating a volume for a data point markers in supported galleries.  By adjusting the volume, you can modify the amount of space you want between the items.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setVolume: function (value) {
        /// <summary>Sets a value indicating a volume for a data point markers in supported galleries.  By adjusting the volume, you can modify the amount of space you want between the items.</summary>
        /// <param name="value" type="number"/>>
    }
};

var cr = function() {
}
cfx.cr = cr;
cr.prototype = {
    getDelay: function () {
        /// <returns type="cfx.AnimationDelay"/>
        return new AnimationDelay();
    },
    setDelay: function (value) {
        /// <param name="value" type="cfx.AnimationDelay"/>>
    },
    getDirection: function () {
        /// <returns type="cfx.AnimationDirection"/>
        return new AnimationDirection();
    },
    setDirection: function (value) {
        /// <param name="value" type="cfx.AnimationDirection"/>>
    },
    getDuration: function () {
        /// <returns type="number"/>
        return 0;
    },
    setDuration: function (value) {
        /// <param name="value" type="number"/>>
    },
    getEnabled: function () {
        /// <returns type="bool"/>
        return true;
    },
    setEnabled: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getTiming: function () {
        /// <returns type="cfx.AnimationTiming"/>
        return new AnimationTiming();
    },
    setTiming: function (value) {
        /// <param name="value" type="cfx.AnimationTiming"/>>
    }
};

var cs = function() {
}
cfx.cs = cs;
cs.prototype = {
    getDataChange: function () {
        /// <returns type="cfx.cr"/>
        return new cr();
    },
    getLoad: function () {
        /// <returns type="cfx.cr"/>
        return new cr();
    },
    getShowSpeed: function () {
        /// <returns type="bool"/>
        return true;
    },
    setShowSpeed: function (value) {
        /// <param name="value" type="bool"/>>
    },
    reset: function () {
    }
};

var ToolTipAttributes = function() {
}
cfx.ToolTipAttributes = ToolTipAttributes;
ToolTipAttributes.prototype = {
    getAlignment: function () {
        /// <summary>Description goes here</summary>
        /// <returns type="StringAlignment"/>
        return new StringAlignment();
    },
    setAlignment: function (value) {
        /// <summary>Description goes here</summary>
        /// <param name="value" type="StringAlignment"/>>
    },
    getAllSeries: function () {
        /// <summary>Description goes here</summary>
        /// <returns type="bool"/>
        return true;
    },
    setAllSeries: function (value) {
        /// <summary>Description goes here</summary>
        /// <param name="value" type="bool"/>>
    },
    getBackColor: function () {
        /// <summary>Description goes here</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setBackColor: function (value) {
        /// <summary>Description goes here</summary>
        /// <param name="value" type="String"/>>
    },
    getBorder: function () {
        /// <summary>Description goes here</summary>
        /// <returns type="cfx.Line"/>
        return new Line();
    },
    getBorderTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setBorderTemplate: function (value) {
        /// <param name="value" type="string"/>>
    },
    getContentTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setContentTemplate: function (value) {
        /// <param name="value" type="string"/>>
    },
    getEnabled: function () {
        /// <summary>Description goes here</summary>
        /// <returns type="bool"/>
        return true;
    },
    setEnabled: function (value) {
        /// <summary>Description goes here</summary>
        /// <param name="value" type="bool"/>>
    },
    getFont: function () {
        /// <summary>Description goes here</summary>
        /// <returns type="Font"/>
        return new Font();
    },
    setFont: function (value) {
        /// <summary>Description goes here</summary>
        /// <param name="value" type="Font"/>>
    },
    getFormat: function () {
        /// <summary>Description goes here</summary>
        /// <returns type="string"/>
        return "";
    },
    setFormat: function (value) {
        /// <summary>Description goes here</summary>
        /// <param name="value" type="string"/>>
    },
    getTextColor: function () {
        /// <summary>Description goes here</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setTextColor: function (value) {
        /// <summary>Description goes here</summary>
        /// <param name="value" type="String"/>>
    },
    assignObject: function (obj) {
        /// <param name="obj" type="?"/>>
    }
};

var cE = function() {
}
cfx.cE = cE;
cE.prototype = {
    getAngleX: function () {
        /// <summary>Gets the 3D rotation angle around the X-axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setAngleX: function (value) {
        /// <summary>Sets the 3D rotation angle around the X-axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getAngleY: function () {
        /// <summary>Gets a 3D rotation angle around the Y-axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setAngleY: function (value) {
        /// <summary>Sets a 3D rotation angle around the Y-axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getBoxThickness: function () {
        /// <summary>Gets the thickness for the box surrounding the chart.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setBoxThickness: function (value) {
        /// <summary>Sets the thickness for the box surrounding the chart.</summary>
        /// <param name="value" type="number"/>>
    },
    getCluster: function () {
        /// <summary>Displays a z-clustered 3D chart.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setCluster: function (value) {
        /// <summary>Displays a z-clustered 3D chart.</summary>
        /// <param name="value" type="bool"/>>
    },
    getDepth: function () {
        /// <summary>Gets the thickness along the Z-axis for the series.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setDepth: function (value) {
        /// <summary>Sets the thickness along the Z-axis for the series.</summary>
        /// <param name="value" type="number"/>>
    },
    getEnabled: function () {
        /// <summary>Gets a value indicating whether 3D effects should be applied.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setEnabled: function (value) {
        /// <summary>Sets a value indicating whether 3D effects should be applied.</summary>
        /// <param name="value" type="bool"/>>
    },
    getPerspective: function () {
        /// <summary>Allows you to set a 3D perspective to control the view of the chart depth.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setPerspective: function (value) {
        /// <summary>Allows you to set a 3D perspective to control the view of the chart depth.</summary>
        /// <param name="value" type="number"/>>
    },
    getRotated: function () {
        /// <summary>Gets a value indicating the chart is Rotated.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setRotated: function (value) {
        /// <summary>Sets a value indicating the chart is Rotated.</summary>
        /// <param name="value" type="bool"/>>
    },
    getShadow: function () {
        /// <summary>Gets a value indicating the type of shading to display when rotating the chart.</summary>
        /// <returns type="cfx.Shadow"/>
        return new Shadow();
    },
    setShadow: function (value) {
        /// <summary>Sets a value indicating the type of shading to display when rotating the chart.</summary>
        /// <param name="value" type="cfx.Shadow"/>>
    }
};

var Palette = function() {
}
cfx.Palette = Palette;
Palette.prototype = {

};

var cF = function() {
}
cfx.cF = cF;
cF.prototype = {
    getBottom: function () {
        /// <summary>Gets the distance in pixels between the bottom border of the bounding rectangle and the bottom border of the plot area of the chart.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setBottom: function (value) {
        /// <summary>Sets the distance in pixels between the bottom border of the bounding rectangle and the bottom border of the plot area of the chart.</summary>
        /// <param name="value" type="number"/>>
    },
    getLeft: function () {
        /// <summary>Gets the distance in pixels between the left border of the bounding rectangle and the left border of the plot area of the chart.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setLeft: function (value) {
        /// <summary>Sets the distance in pixels between the left border of the bounding rectangle and the left border of the plot area of the chart.</summary>
        /// <param name="value" type="number"/>>
    },
    getRight: function () {
        /// <summary>Gets the distance in pixels between the right border of the bounding rectangle and the right border of the plot area of the chart.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setRight: function (value) {
        /// <summary>Sets the distance in pixels between the right border of the bounding rectangle and the right border of the plot area of the chart.</summary>
        /// <param name="value" type="number"/>>
    },
    getTop: function () {
        /// <summary>Gets the distance in pixels between the top border of the bounding rectangle and the top border of the plot area of the chart.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setTop: function (value) {
        /// <summary>Sets the distance in pixels between the top border of the bounding rectangle and the top border of the plot area of the chart.</summary>
        /// <param name="value" type="number"/>>
    }
};

var cG = function() {
}
cfx.cG = cG;
cG.prototype = {
    getItem: function (n) {
        /// <param name="n" type="number"/>>
        /// <returns type="cfx.FieldMap"/>
        return new FieldMap();
    },
    setItem: function (n, value) {
        /// <param name="n" type="number"/>>
        /// <param name="value" type="cfx.FieldMap"/>>
    },
    add: function (item) {
        /// <param name="item" type="cfx.FieldMap"/>>
    },
    clear: function () {
    },
    contains: function (item) {
        /// <param name="item" type="cfx.FieldMap"/>>
        /// <returns type="bool"/>
        return true;
    },
    fillFromSchema: function () {
    },
    indexOf: function (obj) {
        /// <param name="obj" type="cfx.FieldMap"/>>
        /// <returns type="number"/>
        return 0;
    },
    insert: function (index, item) {
        /// <param name="index" type="number"/>>
        /// <param name="item" type="cfx.FieldMap"/>>
    },
    remove: function (item) {
        /// <param name="item" type="cfx.FieldMap"/>>
    },
    removeAt: function (index) {
        /// <param name="index" type="number"/>>
    }
};

var DataSourceSettings = function() {
}
cfx.DataSourceSettings = DataSourceSettings;
DataSourceSettings.prototype = {
    getDataSource: function () {
        /// <summary>Gets the source containing the values used to populate the chart.</summary>
        /// <returns type="?"/>
        return new ?();
    },
    setDataSource: function (value) {
        /// <summary>Sets the source containing the values used to populate the chart.</summary>
        /// <param name="value" type="?"/>>
    },
    getFields: function () {
        /// <summary>Returns a collection of FieldMap objects. Those objects allow you to configure how Chart FX will use the data provided.</summary>
        /// <returns type="cfx.cG"/>
        return new cG();
    },
    getLabelSeparator: function () {
        /// <summary>Gets a value defining the label separator character used when concatenating labels.</summary>
        /// <returns type="string"/>
        return "";
    },
    setLabelSeparator: function (value) {
        /// <summary>Sets a value defining the label separator character used when concatenating labels.</summary>
        /// <param name="value" type="string"/>>
    },
    getStyle: function () {
        /// <summary>Controls how Chart FX plots the fields in the resultset.</summary>
        /// <returns type="cfx.DataSourceStyles"/>
        return new DataSourceStyles();
    },
    setStyle: function (value) {
        /// <summary>Controls how Chart FX plots the fields in the resultset.</summary>
        /// <param name="value" type="cfx.DataSourceStyles"/>>
    },
    reloadData: function () {
    }
};

var cN = function() {
}
cfx.cN = cN;
cN.prototype = {
    getItem1: function (point) {
        /// <param name="point" type="number"/>>
        /// <returns type="cfx.PointAttributes"/>
        return new PointAttributes();
    },
    setItem1: function (point, value) {
        /// <param name="point" type="number"/>>
        /// <param name="value" type="cfx.PointAttributes"/>>
    },
    getItem: function (series, point) {
        /// <param name="series" type="number"/>>
        /// <param name="point" type="number"/>>
        /// <returns type="cfx.PointAttributes"/>
        return new PointAttributes();
    },
    setItem: function (series, point, value) {
        /// <param name="series" type="number"/>>
        /// <param name="point" type="number"/>>
        /// <param name="value" type="cfx.PointAttributes"/>>
    }
};

var Pane = function() {
}
cfx.Pane = Pane;
Pane.prototype = {
    getAxes: function () {
        /// <summary>Provides access to the Y Axis Collection for the selected Pane.</summary>
        /// <returns type="cfx.cS"/>
        return new cS();
    },
    getAxisY: function () {
        /// <summary>Provides access to the members of the main Y Axis for the selected Pane.</summary>
        /// <returns type="cfx.AxisY"/>
        return new AxisY();
    },
    getBackColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setBackColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getBackground: function () {
        /// <summary>Gets an ImageBackground or GradientBackground object for a pane.</summary>
        /// <returns type="cfx.object"/>
        return new object();
    },
    setBackground: function (value) {
        /// <summary>Sets an ImageBackground or GradientBackground object for a pane.</summary>
        /// <param name="value" type="cfx.object"/>>
    },
    getBoundingRectangle: function () {
        /// <summary>Returns a rectangle object that contains attributes for the selected pane.</summary>
        /// <returns type="Rectangle"/>
        return new Rectangle();
    },
    getProportion: function () {
        /// <summary>Gets a value indicating the proportional size of the selected pane.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setProportion: function (value) {
        /// <summary>Sets a value indicating the proportional size of the selected pane.</summary>
        /// <param name="value" type="number"/>>
    },
    getSeparation: function () {
        /// <summary>Gets a value indicating the space between panes.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setSeparation: function (value) {
        /// <summary>Sets a value indicating the space between panes.</summary>
        /// <param name="value" type="number"/>>
    },
    getTitle: function () {
        /// <summary>Gets the Title object for a selected pane.</summary>
        /// <returns type="cfx.Title"/>
        return new Title();
    },
    setTitle: function (value) {
        /// <summary>Gets the Title object for a selected pane.</summary>
        /// <param name="value" type="cfx.Title"/>>
    },
    getVisible: function () {
        /// <summary>Gets a value indicating if the Pane object should be shown or not.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <summary>Sets a value indicating if the Pane object should be shown or not.</summary>
        /// <param name="value" type="bool"/>>
    }
};

var cP = function() {
}
cfx.cP = cP;
cP.prototype = {
    getItem: function (index) {
        /// <param name="index" type="number"/>>
        /// <returns type="cfx.TitleDockable"/>
        return new TitleDockable();
    },
    add: function (title) {
        /// <param name="title" type="cfx.TitleDockable"/>>
    },
    insert: function (index, title) {
        /// <param name="index" type="number"/>>
        /// <param name="title" type="cfx.TitleDockable"/>>
    },
    clear: function () {
    }
};

var cQ = function() {
}
cfx.cQ = cQ;
cQ.prototype = {
    getItem: function (index) {
        /// <param name="index" type="number"/>>
        /// <returns type="cfx.Pane"/>
        return new Pane();
    },
    add: function (pane) {
        /// <param name="pane" type="cfx.Pane"/>>
    },
    insert: function (index, pane) {
        /// <param name="index" type="number"/>>
        /// <param name="pane" type="cfx.Pane"/>>
    },
    remove: function (pane) {
        /// <param name="pane" type="cfx.Pane"/>>
    },
    clear: function () {
    }
};

var cR = function() {
}
cfx.cR = cR;
cR.prototype = {
    clear: function () {
    }
};

var cS = function() {
}
cfx.cS = cS;
cS.prototype = {
    getItem: function (index) {
        /// <param name="index" type="number"/>>
        /// <returns type="cfx.AxisY"/>
        return new AxisY();
    },
    setItem: function (index, value) {
        /// <param name="index" type="number"/>>
        /// <param name="value" type="cfx.AxisY"/>>
    },
    add: function (item) {
        /// <param name="item" type="cfx.AxisY"/>>
    },
    insert: function (index, item) {
        /// <param name="index" type="number"/>>
        /// <param name="item" type="cfx.AxisY"/>>
    },
    remove: function (item) {
        /// <param name="item" type="cfx.AxisY"/>>
    },
    clear: function () {
    }
};

var cT = function() {
}
cfx.cT = cT;
cT.prototype = {
    getItem: function (index) {
        /// <param name="index" type="number"/>>
        /// <returns type="cfx.AxisX"/>
        return new AxisX();
    },
    setItem: function (index, value) {
        /// <param name="index" type="number"/>>
        /// <param name="value" type="cfx.AxisX"/>>
    },
    add: function (item) {
        /// <param name="item" type="cfx.AxisX"/>>
    },
    insert: function (index, item) {
        /// <param name="index" type="number"/>>
        /// <param name="item" type="cfx.AxisX"/>>
    },
    remove: function (item) {
        /// <param name="item" type="cfx.AxisX"/>>
    },
    clear: function () {
    }
};

var cV = function() {
}
cfx.cV = cV;
cV.prototype = {
    getItem: function (index) {
        /// <param name="index" type="number"/>>
        /// <returns type="cfx.SeriesAttributes"/>
        return new SeriesAttributes();
    },
    add: function (series) {
        /// <param name="series" type="cfx.SeriesAttributes"/>>
    },
    clearText: function () {
    },
    insert: function (index, item) {
        /// <param name="index" type="number"/>>
        /// <param name="item" type="cfx.SeriesAttributes"/>>
    },
    remove: function (item) {
        /// <param name="item" type="cfx.SeriesAttributes"/>>
    },
    resetOrder: function () {
    },
    clear: function () {
    }
};

var cY = function() {
}
cfx.cY = cY;
cY.prototype = {
    getItem: function (index) {
        /// <param name="index" type="number"/>>
        /// <returns type="cfx.CustomLegendItem"/>
        return new CustomLegendItem();
    },
    add: function (item) {
        /// <param name="item" type="cfx.CustomLegendItem"/>>
    },
    contains: function (item) {
        /// <param name="item" type="cfx.CustomLegendItem"/>>
        /// <returns type="bool"/>
        return true;
    },
    indexOf: function (item) {
        /// <param name="item" type="cfx.CustomLegendItem"/>>
        /// <returns type="number"/>
        return 0;
    },
    insert: function (index, item) {
        /// <param name="index" type="number"/>>
        /// <param name="item" type="cfx.CustomLegendItem"/>>
    },
    remove: function (item) {
        /// <param name="item" type="cfx.CustomLegendItem"/>>
    },
    clear: function () {
    }
};

var cZ = function() {
}
cfx.cZ = cZ;
cZ.prototype = {
    get_auto_ShowInLegend: function () {
        /// <returns type="bool"/>
        return true;
    },
    set_auto_ShowInLegend: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getItem: function (index) {
        /// <param name="index" type="number"/>>
        /// <returns type="cfx.ConditionalAttributes"/>
        return new ConditionalAttributes();
    },
    add: function (item) {
        /// <param name="item" type="cfx.ConditionalAttributes"/>>
    },
    clear: function () {
    },
    conditionForElement: function (series, point) {
        /// <param name="series" type="number"/>>
        /// <param name="point" type="number"/>>
        /// <returns type="cfx.PointAttributes"/>
        return new PointAttributes();
    },
    insert: function (index, item) {
        /// <param name="index" type="number"/>>
        /// <param name="item" type="cfx.ConditionalAttributes"/>>
    },
    recalculate: function () {
    },
    remove: function (item) {
        /// <param name="item" type="cfx.ConditionalAttributes"/>>
    },
    resumeUpdate: function () {
    },
    suspendUpdate: function () {
    },
    clear: function () {
    }
};

var c0 = function() {
}
cfx.c0 = c0;
c0.prototype = {
    getItem: function (index) {
        /// <param name="index" type="number"/>>
        /// <returns type="string"/>
        return "";
    },
    setItem: function (index, value) {
        /// <param name="index" type="number"/>>
        /// <param name="value" type="string"/>>
    },
    add: function (item) {
        /// <param name="item" type="string"/>>
    },
    insert: function (index, item) {
        /// <param name="index" type="number"/>>
        /// <param name="item" type="string"/>>
    },
    clear: function () {
    }
};

var c1 = function() {
}
cfx.c1 = c1;
c1.prototype = {
    getAxis: function () {
        /// <returns type="cfx.c_"/>
        return new c_();
    },
    getItem: function (index) {
        /// <param name="index" type="number"/>>
        /// <returns type="cfx.CustomGridLine"/>
        return new CustomGridLine();
    },
    add: function (constLine) {
        /// <param name="constLine" type="cfx.CustomGridLine"/>>
    },
    insert: function (index, item) {
        /// <param name="index" type="number"/>>
        /// <param name="item" type="string"/>>
    },
    remove: function (item) {
        /// <param name="item" type="cfx.CustomGridLine"/>>
    },
    clear: function () {
    }
};

var c2 = function() {
}
cfx.c2 = c2;
c2.prototype = {
    getAxis: function () {
        /// <returns type="cfx.c_"/>
        return new c_();
    },
    getItem: function (index) {
        /// <param name="index" type="number"/>>
        /// <returns type="cfx.AxisSection"/>
        return new AxisSection();
    },
    add: function (axisSection) {
        /// <param name="axisSection" type="cfx.AxisSection"/>>
    },
    insert: function (index, axisSection) {
        /// <param name="index" type="number"/>>
        /// <param name="axisSection" type="cfx.AxisSection"/>>
    },
    remove: function (item) {
        /// <param name="item" type="cfx.AxisSection"/>>
    },
    clear: function () {
    }
};

var c3 = function() {
}
cfx.c3 = c3;
c3.prototype = {
    getCulture: function () {
        /// <returns type="CultureInfo"/>
        return new CultureInfo();
    },
    setCulture: function (value) {
        /// <param name="value" type="CultureInfo"/>>
    },
    getCustomFormat: function () {
        /// <summary>Gets a custom format to the selected label, tooltip or value.</summary>
        /// <returns type="string"/>
        return "";
    },
    setCustomFormat: function (value) {
        /// <summary>Sets a custom format to the selected label, tooltip or value.</summary>
        /// <param name="value" type="string"/>>
    },
    getDecimals: function () {
        /// <summary>Gets the number of decimals for the selected label, tooltip or value.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setDecimals: function (value) {
        /// <summary>Sets the number of decimals for the selected label, tooltip or value.</summary>
        /// <param name="value" type="number"/>>
    },
    getFormat: function () {
        /// <summary>Used to format the selected label, tooltip or value to numbers, date, time, scientific or currency.</summary>
        /// <returns type="cfx.AxisFormat"/>
        return new AxisFormat();
    },
    setFormat: function (value) {
        /// <summary>Used to format the selected label, tooltip or value to numbers, date, time, scientific or currency.</summary>
        /// <param name="value" type="cfx.AxisFormat"/>>
    },
    getIsDate: function () {
        /// <summary>Returns a value indicating whether or not a value is a Date.</summary>
        /// <returns type="bool"/>
        return true;
    },
    getIsDateTime: function () {
        /// <summary>Returns a value indicating whether or not a value is a DateTime.</summary>
        /// <returns type="bool"/>
        return true;
    },
    getIsPercentage: function () {
        /// <summary>Returns a value indicating whether or not a value is a Percentage.</summary>
        /// <returns type="bool"/>
        return true;
    },
    getIsTime: function () {
        /// <summary>Returns a value indicating whether or not a value is a Time.</summary>
        /// <returns type="bool"/>
        return true;
    }
};

var Title = function() {
}
cfx.Title = Title;
Title.prototype = {
    getAlignment: function () {
        /// <summary>Allows you to set the alignment of the specified title.</summary>
        /// <returns type="StringAlignment"/>
        return new StringAlignment();
    },
    setAlignment: function (value) {
        /// <summary>Allows you to set the alignment of the specified title.</summary>
        /// <param name="value" type="StringAlignment"/>>
    },
    getBackColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setBackColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getFont: function () {
        /// <returns type="Font"/>
        return new Font();
    },
    setFont: function (value) {
        /// <param name="value" type="Font"/>>
    },
    getIndentation: function () {
        /// <summary>Specifies the space in pixels to the front and back of the selected title when read from top to bottom.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setIndentation: function (value) {
        /// <summary>Specifies the space in pixels to the front and back of the selected title when read from top to bottom.</summary>
        /// <param name="value" type="number"/>>
    },
    getLineAlignment: function () {
        /// <summary>Gets the vertical alignment for the lines of the chart title.</summary>
        /// <returns type="StringAlignment"/>
        return new StringAlignment();
    },
    setLineAlignment: function (value) {
        /// <summary>Sets the vertical alignment for the lines of the chart title.</summary>
        /// <param name="value" type="StringAlignment"/>>
    },
    getRichText: function () {
        /// <summary>Gets the RichText property for the selected Title.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setRichText: function (value) {
        /// <summary>Sets the RichText property for the selected Title.</summary>
        /// <param name="value" type="bool"/>>
    },
    getSeparation: function () {
        /// <summary>Specifies the space in pixels above and below the selected title text when read from right to left.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setSeparation: function (value) {
        /// <summary>Specifies the space in pixels above and below the selected title text when read from right to left.</summary>
        /// <param name="value" type="number"/>>
    },
    getText: function () {
        /// <summary>Gets the text for the selected title.</summary>
        /// <returns type="string"/>
        return "";
    },
    setText: function (value) {
        /// <summary>Sets the text for the selected title.</summary>
        /// <param name="value" type="string"/>>
    },
    getTextColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setTextColor: function (value) {
        /// <param name="value" type="String"/>>
    }
};

var TitleDockable = function() {
}
cfx.TitleDockable = TitleDockable;
TitleDockable.prototype = {
    getDock: function () {
        /// <summary>Sets or Gets the Dock property, which sets the position where the title will be docked.</summary>
        /// <returns type="cfx.DockArea"/>
        return new DockArea();
    },
    setDock: function (value) {
        /// <summary>Sets or Gets the Dock property, which sets the position where the title will be docked.</summary>
        /// <param name="value" type="cfx.DockArea"/>>
    },
    getPlotAreaOnly: function () {
        /// <summary>Gets a value indicating whether the title may use the entire length of the chart, or only above the plot area.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setPlotAreaOnly: function (value) {
        /// <summary>Sets a value indicating whether the title may use the entire length of the chart, or only above the plot area.</summary>
        /// <param name="value" type="bool"/>>
    },
    getAlignment: function () {
        /// <summary>Allows you to set the alignment of the specified title.</summary>
        /// <returns type="StringAlignment"/>
        return new StringAlignment();
    },
    setAlignment: function (value) {
        /// <summary>Allows you to set the alignment of the specified title.</summary>
        /// <param name="value" type="StringAlignment"/>>
    },
    getBackColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setBackColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getFont: function () {
        /// <returns type="Font"/>
        return new Font();
    },
    setFont: function (value) {
        /// <param name="value" type="Font"/>>
    },
    getIndentation: function () {
        /// <summary>Specifies the space in pixels to the front and back of the selected title when read from top to bottom.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setIndentation: function (value) {
        /// <summary>Specifies the space in pixels to the front and back of the selected title when read from top to bottom.</summary>
        /// <param name="value" type="number"/>>
    },
    getLineAlignment: function () {
        /// <summary>Gets the vertical alignment for the lines of the chart title.</summary>
        /// <returns type="StringAlignment"/>
        return new StringAlignment();
    },
    setLineAlignment: function (value) {
        /// <summary>Sets the vertical alignment for the lines of the chart title.</summary>
        /// <param name="value" type="StringAlignment"/>>
    },
    getRichText: function () {
        /// <summary>Gets the RichText property for the selected Title.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setRichText: function (value) {
        /// <summary>Sets the RichText property for the selected Title.</summary>
        /// <param name="value" type="bool"/>>
    },
    getSeparation: function () {
        /// <summary>Specifies the space in pixels above and below the selected title text when read from right to left.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setSeparation: function (value) {
        /// <summary>Specifies the space in pixels above and below the selected title text when read from right to left.</summary>
        /// <param name="value" type="number"/>>
    },
    getText: function () {
        /// <summary>Gets the text for the selected title.</summary>
        /// <returns type="string"/>
        return "";
    },
    setText: function (value) {
        /// <summary>Sets the text for the selected title.</summary>
        /// <param name="value" type="string"/>>
    },
    getTextColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setTextColor: function (value) {
        /// <param name="value" type="String"/>>
    }
};

var SimpleBorder = function() {
}
cfx.SimpleBorder = SimpleBorder;
SimpleBorder.prototype = {

};

var Grids = function() {
}
cfx.Grids = Grids;
Grids.prototype = {
    getInterlaced: function () {
        /// <summary>Creates a grid of alternating colors for a selected axis to improve chart readability.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setInterlaced: function (value) {
        /// <summary>Creates a grid of alternating colors for a selected axis to improve chart readability.</summary>
        /// <param name="value" type="bool"/>>
    },
    getInterlacedColor: function () {
        /// <summary>Gets the AlternateColor for a selected Grids object.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setInterlacedColor: function (value) {
        /// <summary>Sets the AlternateColor for a selected Grids object.</summary>
        /// <param name="value" type="String"/>>
    },
    getMajor: function () {
        /// <summary>Provides access to the GridLine class to customize the Mayor Grid.</summary>
        /// <returns type="cfx.GridLine"/>
        return new GridLine();
    },
    getMinor: function () {
        /// <summary>Provides access to the GridLine class to customize the Minor Grid.</summary>
        /// <returns type="cfx.GridLine"/>
        return new GridLine();
    }
};

var AxisSection = function() {
}
cfx.AxisSection = AxisSection;
AxisSection.prototype = {
    getAxis: function () {
        /// <returns type="cfx.c_"/>
        return new c_();
    },
    getBackColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setBackColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getFontStyle: function () {
        /// <returns type="FontStyle"/>
        return new FontStyle();
    },
    setFontStyle: function (value) {
        /// <param name="value" type="FontStyle"/>>
    },
    getFrom: function () {
        /// <summary>Gets a value indicating the beginning value of the range for a selected AxisSection object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setFrom: function (value) {
        /// <summary>Sets a value indicating the beginning value of the range for a selected AxisSection object.</summary>
        /// <param name="value" type="number"/>>
    },
    getText: function () {
        /// <summary>Gets the text string for a selected BaseAxisSection object.</summary>
        /// <returns type="string"/>
        return "";
    },
    setText: function (value) {
        /// <summary>Sets the text string for a selected BaseAxisSection object.</summary>
        /// <param name="value" type="string"/>>
    },
    getTo: function () {
        /// <summary>Gets a value indicating the ending value of the range for a selected AxisSection object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setTo: function (value) {
        /// <summary>Sets a value indicating the ending value of the range for a selected AxisSection object.</summary>
        /// <param name="value" type="number"/>>
    },
    getVisible: function () {
        /// <summary>Shows or hides the selected axis section.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <summary>Shows or hides the selected axis section.</summary>
        /// <param name="value" type="bool"/>>
    },
    getGrids: function () {
        /// <summary>Allows customization of grid lines by providing access to the supported members of the Grids class.</summary>
        /// <returns type="cfx.Grids"/>
        return new Grids();
    },
    getTextColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setTextColor: function (value) {
        /// <param name="value" type="String"/>>
    }
};

var c_ = function() {
}
cfx.c_ = c_;
c_.prototype = {
    getAutoScale: function () {
        /// <summary>Instructs Chart FX to always recalculate the specified Axis scale values (Min, Max) when new values are set to the chart.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setAutoScale: function (value) {
        /// <summary>Instructs Chart FX to always recalculate the specified Axis scale values (Min, Max) when new values are set to the chart.</summary>
        /// <param name="value" type="bool"/>>
    },
    getAutoScroll: function () {
        /// <summary>Enables or disables scrolling in an Axis.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setAutoScroll: function (value) {
        /// <summary>Enables or disables scrolling in an Axis.</summary>
        /// <param name="value" type="bool"/>>
    },
    getClientScroll: function () {
        /// <returns type="bool"/>
        return true;
    },
    setClientScroll: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getCustomGridLines: function () {
        /// <summary>Gets the collection of CustomGridLines that belong to the Chart.</summary>
        /// <returns type="cfx.c1"/>
        return new c1();
    },
    getCustomSteps: function () {
        /// <returns type="Double[]"/>
        return new Double[]();
    },
    setCustomSteps: function (value) {
        /// <param name="value" type="Double[]"/>>
    },
    getDataFormat: function () {
        /// <summary>Used to format the data displayed on point labels, and tool tips shown at mouseover.</summary>
        /// <returns type="cfx.c3"/>
        return new c3();
    },
    getDrawingArea: function () {
        /// <returns type="Rectangle"/>
        return new Rectangle();
    },
    getFirstLabel: function () {
        /// <summary>Gets a value used to customize the first label displayed on a selected axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setFirstLabel: function (value) {
        /// <summary>Sets a value used to customize the first label displayed on a selected axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getFont: function () {
        /// <returns type="Font"/>
        return new Font();
    },
    setFont: function (value) {
        /// <param name="value" type="Font"/>>
    },
    getForceZero: function () {
        /// <summary>Used to set the minimum value of a numerical axis to zero even if the data minimum value does not start at zero.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setForceZero: function (value) {
        /// <summary>Used to set the minimum value of a numerical axis to zero even if the data minimum value does not start at zero.</summary>
        /// <param name="value" type="bool"/>>
    },
    getHorizontal: function () {
        /// <summary>Returns a boolean value specifying whether the selected axis is a horizontal or vertical axis.</summary>
        /// <returns type="bool"/>
        return true;
    },
    getInverted: function () {
        /// <summary>Sets a selected numerical axis max value lesser than the min value.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setInverted: function (value) {
        /// <summary>Sets a selected numerical axis max value lesser than the min value.</summary>
        /// <param name="value" type="bool"/>>
    },
    getKeyLabels: function () {
        /// <summary>Used to assign a key (short) legend to tickmarks in a categorical axis.</summary>
        /// <returns type="cfx.c0"/>
        return new c0();
    },
    getLabelAngle: function () {
        /// <summary>Sets the text rotation for the labels in the selected axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setLabelAngle: function (value) {
        /// <summary>Sets the text rotation for the labels in the selected axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getLabels: function () {
        /// <summary>Allows you to get or set the tick mark labels for the selected axis.</summary>
        /// <returns type="cfx.c0"/>
        return new c0();
    },
    getLabelsFormat: function () {
        /// <summary>Used to format the labels displayed on the selected axis.</summary>
        /// <returns type="cfx.c3"/>
        return new c3();
    },
    getLabelTrimming: function () {
        /// <returns type="StringTrimming"/>
        return new StringTrimming();
    },
    setLabelTrimming: function (value) {
        /// <param name="value" type="StringTrimming"/>>
    },
    getLabelValue: function () {
        /// <summary>Allows you to set and associate text labels to major tick marks in a numerical axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setLabelValue: function (value) {
        /// <summary>Allows you to set and associate text labels to major tick marks in a numerical axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getLine: function () {
        /// <summary>Allows you to apply supported Line class members to a selected axis line.</summary>
        /// <returns type="cfx.Line"/>
        return new Line();
    },
    getLineSpacing: function () {
        /// <summary>Gets a value indicating the LineSpacing for an axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setLineSpacing: function (value) {
        /// <summary>Sets a value indicating the LineSpacing for an axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getLogBase: function () {
        /// <summary>Used to set a logarithmic scale for a numerical axis and recalculate the values as powers equal to the setting of this property.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setLogBase: function (value) {
        /// <summary>Used to set a logarithmic scale for a numerical axis and recalculate the values as powers equal to the setting of this property.</summary>
        /// <param name="value" type="number"/>>
    },
    getMax: function () {
        /// <summary>Gets the Max value assigned to the specified axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setMax: function (value) {
        /// <summary>Sets the Max value assigned to the specified axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getMaxSizePercentage: function () {
        /// <summary>Allows you to specify the maximum percentage of the chart area the AxisX labels may occupy.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setMaxSizePercentage: function (value) {
        /// <summary>Allows you to specify the maximum percentage of the chart area the AxisX labels may occupy.</summary>
        /// <param name="value" type="number"/>>
    },
    getMin: function () {
        /// <summary>Gets the Min value assigned to the specified axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setMin: function (value) {
        /// <summary>Sets the Min value assigned to the specified axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getMinorStep: function () {
        /// <summary>Specifies the increment you want between minor tick marks and minor gridlines (if they are displayed) on the selected axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setMinorStep: function (value) {
        /// <summary>Specifies the increment you want between minor tick marks and minor gridlines (if they are displayed) on the selected axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getNotify: function () {
        /// <summary>Used to enable or disable the GetAxisLabel event so you can customize labels on the specified axis.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setNotify: function (value) {
        /// <summary>Used to enable or disable the GetAxisLabel event so you can customize labels on the specified axis.</summary>
        /// <param name="value" type="bool"/>>
    },
    getPixelOffset: function () {
        /// <returns type="number"/>
        return 0;
    },
    setPixelOffset: function (value) {
        /// <param name="value" type="number"/>>
    },
    getPixelsPerUnit: function () {
        /// <summary>Gives you the ability to control how many pixels per unit are in the selected axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setPixelsPerUnit: function (value) {
        /// <summary>Gives you the ability to control how many pixels per unit are in the selected axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getPosition: function () {
        /// <summary>Specifies the placement of the axis labels and tick marks relative to the chart.</summary>
        /// <returns type="cfx.AxisPosition"/>
        return new AxisPosition();
    },
    setPosition: function (value) {
        /// <summary>Specifies the placement of the axis labels and tick marks relative to the chart.</summary>
        /// <param name="value" type="cfx.AxisPosition"/>>
    },
    getScaleUnit: function () {
        /// <summary>Returns or sets the scale unit of a selected axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setScaleUnit: function (value) {
        /// <summary>Returns or sets the scale unit of a selected axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getScrollPosition: function () {
        /// <summary>Allows you to set the starting scroll position when a chart is scrollable.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setScrollPosition: function (value) {
        /// <summary>Allows you to set the starting scroll position when a chart is scrollable.</summary>
        /// <param name="value" type="number"/>>
    },
    getScrollSize: function () {
        /// <summary>Returns the number of scrolls in a chart based on the scroll view configuration.</summary>
        /// <returns type="number"/>
        return 0;
    },
    getSections: function () {
        /// <summary>Returns a selected AxisSection object.</summary>
        /// <returns type="cfx.c2"/>
        return new c2();
    },
    getSeparation: function () {
        /// <summary>Allows you to specify a gap in pixels between multiple axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setSeparation: function (value) {
        /// <summary>Allows you to specify a gap in pixels between multiple axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getStaggered: function () {
        /// <summary>Alternates the level of the axis labels for the selected axis.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setStaggered: function (value) {
        /// <summary>Alternates the level of the axis labels for the selected axis.</summary>
        /// <param name="value" type="bool"/>>
    },
    getStep: function () {
        /// <summary>Specifies the interval of major tick marks and gridlines on the selected axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setStep: function (value) {
        /// <summary>Specifies the interval of major tick marks and gridlines on the selected axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getStepCore: function () {
        /// <returns type="number"/>
        return 0;
    },
    getStyle: function () {
        /// <summary>Allows you to add additional styles to the selected axis.</summary>
        /// <returns type="cfx.AxisStyles"/>
        return new AxisStyles();
    },
    setStyle: function (value) {
        /// <summary>Allows you to add additional styles to the selected axis.</summary>
        /// <param name="value" type="cfx.AxisStyles"/>>
    },
    getTitle: function () {
        /// <summary>Gets the title for a specific axis.</summary>
        /// <returns type="cfx.Title"/>
        return new Title();
    },
    setTitle: function (value) {
        /// <summary>Sets the title for a specific axis.</summary>
        /// <param name="value" type="cfx.Title"/>>
    },
    getVisible: function () {
        /// <summary>Shows or hides the lines, labels, tick marks and grids for the selected axis.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <summary>Shows or hides the lines, labels, tick marks and grids for the selected axis.</summary>
        /// <param name="value" type="bool"/>>
    },
    adjustScale: function () {
    },
    calculateStep: function (scaleInfo, gx, availableSpace, processPane) {
        /// <param name="scaleInfo" type="cfx.cC"/>>
        /// <param name="gx" type="cfx.object"/>>
        /// <param name="availableSpace" type="number"/>>
        /// <param name="processPane" type="bool"/>>
    },
    distanceToPixel: function (distance) {
        /// <param name="distance" type="number"/>>
        /// <returns type="number"/>
        return 0;
    },
    getLabelsCore: function () {
        /// <returns type="cfx.object"/>
        return new object();
    },
    getScrollView: function (_p_) {
        /// <param name="_p_" type="cfx.GetScrollView_Double_r_Double_r"/>>
    },
    pixelToValue: function (pixel) {
        /// <param name="pixel" type="number"/>>
        /// <returns type="number"/>
        return 0;
    },
    resetScale: function () {
    },
    setScrollView: function (min, max) {
        /// <param name="min" type="number"/>>
        /// <param name="max" type="number"/>>
    },
    valueToPixel: function (value) {
        /// <param name="value" type="number"/>>
        /// <returns type="number"/>
        return 0;
    },
    getGrids: function () {
        /// <summary>Allows customization of grid lines by providing access to the supported members of the Grids class.</summary>
        /// <returns type="cfx.Grids"/>
        return new Grids();
    },
    getTextColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setTextColor: function (value) {
        /// <param name="value" type="String"/>>
    }
};

var AxisY = function() {
}
cfx.AxisY = AxisY;
AxisY.prototype = {
    getPane: function () {
        /// <summary>Gets an integer value specifying the Pane for a Y Axis object.</summary>
        /// <returns type="cfx.Pane"/>
        return new Pane();
    },
    getAutoScale: function () {
        /// <summary>Instructs Chart FX to always recalculate the specified Axis scale values (Min, Max) when new values are set to the chart.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setAutoScale: function (value) {
        /// <summary>Instructs Chart FX to always recalculate the specified Axis scale values (Min, Max) when new values are set to the chart.</summary>
        /// <param name="value" type="bool"/>>
    },
    getAutoScroll: function () {
        /// <summary>Enables or disables scrolling in an Axis.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setAutoScroll: function (value) {
        /// <summary>Enables or disables scrolling in an Axis.</summary>
        /// <param name="value" type="bool"/>>
    },
    getClientScroll: function () {
        /// <returns type="bool"/>
        return true;
    },
    setClientScroll: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getCustomGridLines: function () {
        /// <summary>Gets the collection of CustomGridLines that belong to the Chart.</summary>
        /// <returns type="cfx.c1"/>
        return new c1();
    },
    getCustomSteps: function () {
        /// <returns type="Double[]"/>
        return new Double[]();
    },
    setCustomSteps: function (value) {
        /// <param name="value" type="Double[]"/>>
    },
    getDataFormat: function () {
        /// <summary>Used to format the data displayed on point labels, and tool tips shown at mouseover.</summary>
        /// <returns type="cfx.c3"/>
        return new c3();
    },
    getDrawingArea: function () {
        /// <returns type="Rectangle"/>
        return new Rectangle();
    },
    getFirstLabel: function () {
        /// <summary>Gets a value used to customize the first label displayed on a selected axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setFirstLabel: function (value) {
        /// <summary>Sets a value used to customize the first label displayed on a selected axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getFont: function () {
        /// <returns type="Font"/>
        return new Font();
    },
    setFont: function (value) {
        /// <param name="value" type="Font"/>>
    },
    getForceZero: function () {
        /// <summary>Used to set the minimum value of a numerical axis to zero even if the data minimum value does not start at zero.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setForceZero: function (value) {
        /// <summary>Used to set the minimum value of a numerical axis to zero even if the data minimum value does not start at zero.</summary>
        /// <param name="value" type="bool"/>>
    },
    getHorizontal: function () {
        /// <summary>Returns a boolean value specifying whether the selected axis is a horizontal or vertical axis.</summary>
        /// <returns type="bool"/>
        return true;
    },
    getInverted: function () {
        /// <summary>Sets a selected numerical axis max value lesser than the min value.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setInverted: function (value) {
        /// <summary>Sets a selected numerical axis max value lesser than the min value.</summary>
        /// <param name="value" type="bool"/>>
    },
    getKeyLabels: function () {
        /// <summary>Used to assign a key (short) legend to tickmarks in a categorical axis.</summary>
        /// <returns type="cfx.c0"/>
        return new c0();
    },
    getLabelAngle: function () {
        /// <summary>Sets the text rotation for the labels in the selected axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setLabelAngle: function (value) {
        /// <summary>Sets the text rotation for the labels in the selected axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getLabels: function () {
        /// <summary>Allows you to get or set the tick mark labels for the selected axis.</summary>
        /// <returns type="cfx.c0"/>
        return new c0();
    },
    getLabelsFormat: function () {
        /// <summary>Used to format the labels displayed on the selected axis.</summary>
        /// <returns type="cfx.c3"/>
        return new c3();
    },
    getLabelTrimming: function () {
        /// <returns type="StringTrimming"/>
        return new StringTrimming();
    },
    setLabelTrimming: function (value) {
        /// <param name="value" type="StringTrimming"/>>
    },
    getLabelValue: function () {
        /// <summary>Allows you to set and associate text labels to major tick marks in a numerical axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setLabelValue: function (value) {
        /// <summary>Allows you to set and associate text labels to major tick marks in a numerical axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getLine: function () {
        /// <summary>Allows you to apply supported Line class members to a selected axis line.</summary>
        /// <returns type="cfx.Line"/>
        return new Line();
    },
    getLineSpacing: function () {
        /// <summary>Gets a value indicating the LineSpacing for an axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setLineSpacing: function (value) {
        /// <summary>Sets a value indicating the LineSpacing for an axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getLogBase: function () {
        /// <summary>Used to set a logarithmic scale for a numerical axis and recalculate the values as powers equal to the setting of this property.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setLogBase: function (value) {
        /// <summary>Used to set a logarithmic scale for a numerical axis and recalculate the values as powers equal to the setting of this property.</summary>
        /// <param name="value" type="number"/>>
    },
    getMax: function () {
        /// <summary>Gets the Max value assigned to the specified axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setMax: function (value) {
        /// <summary>Sets the Max value assigned to the specified axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getMaxSizePercentage: function () {
        /// <summary>Allows you to specify the maximum percentage of the chart area the AxisX labels may occupy.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setMaxSizePercentage: function (value) {
        /// <summary>Allows you to specify the maximum percentage of the chart area the AxisX labels may occupy.</summary>
        /// <param name="value" type="number"/>>
    },
    getMin: function () {
        /// <summary>Gets the Min value assigned to the specified axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setMin: function (value) {
        /// <summary>Sets the Min value assigned to the specified axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getMinorStep: function () {
        /// <summary>Specifies the increment you want between minor tick marks and minor gridlines (if they are displayed) on the selected axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setMinorStep: function (value) {
        /// <summary>Specifies the increment you want between minor tick marks and minor gridlines (if they are displayed) on the selected axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getNotify: function () {
        /// <summary>Used to enable or disable the GetAxisLabel event so you can customize labels on the specified axis.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setNotify: function (value) {
        /// <summary>Used to enable or disable the GetAxisLabel event so you can customize labels on the specified axis.</summary>
        /// <param name="value" type="bool"/>>
    },
    getPixelOffset: function () {
        /// <returns type="number"/>
        return 0;
    },
    setPixelOffset: function (value) {
        /// <param name="value" type="number"/>>
    },
    getPixelsPerUnit: function () {
        /// <summary>Gives you the ability to control how many pixels per unit are in the selected axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setPixelsPerUnit: function (value) {
        /// <summary>Gives you the ability to control how many pixels per unit are in the selected axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getPosition: function () {
        /// <summary>Specifies the placement of the axis labels and tick marks relative to the chart.</summary>
        /// <returns type="cfx.AxisPosition"/>
        return new AxisPosition();
    },
    setPosition: function (value) {
        /// <summary>Specifies the placement of the axis labels and tick marks relative to the chart.</summary>
        /// <param name="value" type="cfx.AxisPosition"/>>
    },
    getScaleUnit: function () {
        /// <summary>Returns or sets the scale unit of a selected axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setScaleUnit: function (value) {
        /// <summary>Returns or sets the scale unit of a selected axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getScrollPosition: function () {
        /// <summary>Allows you to set the starting scroll position when a chart is scrollable.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setScrollPosition: function (value) {
        /// <summary>Allows you to set the starting scroll position when a chart is scrollable.</summary>
        /// <param name="value" type="number"/>>
    },
    getScrollSize: function () {
        /// <summary>Returns the number of scrolls in a chart based on the scroll view configuration.</summary>
        /// <returns type="number"/>
        return 0;
    },
    getSections: function () {
        /// <summary>Returns a selected AxisSection object.</summary>
        /// <returns type="cfx.c2"/>
        return new c2();
    },
    getSeparation: function () {
        /// <summary>Allows you to specify a gap in pixels between multiple axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setSeparation: function (value) {
        /// <summary>Allows you to specify a gap in pixels between multiple axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getStaggered: function () {
        /// <summary>Alternates the level of the axis labels for the selected axis.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setStaggered: function (value) {
        /// <summary>Alternates the level of the axis labels for the selected axis.</summary>
        /// <param name="value" type="bool"/>>
    },
    getStep: function () {
        /// <summary>Specifies the interval of major tick marks and gridlines on the selected axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setStep: function (value) {
        /// <summary>Specifies the interval of major tick marks and gridlines on the selected axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getStepCore: function () {
        /// <returns type="number"/>
        return 0;
    },
    getStyle: function () {
        /// <summary>Allows you to add additional styles to the selected axis.</summary>
        /// <returns type="cfx.AxisStyles"/>
        return new AxisStyles();
    },
    setStyle: function (value) {
        /// <summary>Allows you to add additional styles to the selected axis.</summary>
        /// <param name="value" type="cfx.AxisStyles"/>>
    },
    getTitle: function () {
        /// <summary>Gets the title for a specific axis.</summary>
        /// <returns type="cfx.Title"/>
        return new Title();
    },
    setTitle: function (value) {
        /// <summary>Sets the title for a specific axis.</summary>
        /// <param name="value" type="cfx.Title"/>>
    },
    getVisible: function () {
        /// <summary>Shows or hides the lines, labels, tick marks and grids for the selected axis.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <summary>Shows or hides the lines, labels, tick marks and grids for the selected axis.</summary>
        /// <param name="value" type="bool"/>>
    },
    adjustScale: function () {
    },
    calculateStep: function (scaleInfo, gx, availableSpace, processPane) {
        /// <param name="scaleInfo" type="cfx.cC"/>>
        /// <param name="gx" type="cfx.object"/>>
        /// <param name="availableSpace" type="number"/>>
        /// <param name="processPane" type="bool"/>>
    },
    distanceToPixel: function (distance) {
        /// <param name="distance" type="number"/>>
        /// <returns type="number"/>
        return 0;
    },
    getLabelsCore: function () {
        /// <returns type="cfx.object"/>
        return new object();
    },
    getScrollView: function (_p_) {
        /// <param name="_p_" type="cfx.GetScrollView_Double_r_Double_r"/>>
    },
    pixelToValue: function (pixel) {
        /// <param name="pixel" type="number"/>>
        /// <returns type="number"/>
        return 0;
    },
    resetScale: function () {
    },
    setScrollView: function (min, max) {
        /// <param name="min" type="number"/>>
        /// <param name="max" type="number"/>>
    },
    valueToPixel: function (value) {
        /// <param name="value" type="number"/>>
        /// <returns type="number"/>
        return 0;
    },
    getGrids: function () {
        /// <summary>Allows customization of grid lines by providing access to the supported members of the Grids class.</summary>
        /// <returns type="cfx.Grids"/>
        return new Grids();
    },
    getTextColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setTextColor: function (value) {
        /// <param name="value" type="String"/>>
    }
};

var AxisX = function() {
}
cfx.AxisX = AxisX;
AxisX.prototype = {
    getAutoScale: function () {
        /// <summary>Instructs Chart FX to always recalculate the specified Axis scale values (Min, Max) when new values are set to the chart.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setAutoScale: function (value) {
        /// <summary>Instructs Chart FX to always recalculate the specified Axis scale values (Min, Max) when new values are set to the chart.</summary>
        /// <param name="value" type="bool"/>>
    },
    getAutoScroll: function () {
        /// <summary>Enables or disables scrolling in an Axis.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setAutoScroll: function (value) {
        /// <summary>Enables or disables scrolling in an Axis.</summary>
        /// <param name="value" type="bool"/>>
    },
    getClientScroll: function () {
        /// <returns type="bool"/>
        return true;
    },
    setClientScroll: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getCustomGridLines: function () {
        /// <summary>Gets the collection of CustomGridLines that belong to the Chart.</summary>
        /// <returns type="cfx.c1"/>
        return new c1();
    },
    getCustomSteps: function () {
        /// <returns type="Double[]"/>
        return new Double[]();
    },
    setCustomSteps: function (value) {
        /// <param name="value" type="Double[]"/>>
    },
    getDataFormat: function () {
        /// <summary>Used to format the data displayed on point labels, and tool tips shown at mouseover.</summary>
        /// <returns type="cfx.c3"/>
        return new c3();
    },
    getDrawingArea: function () {
        /// <returns type="Rectangle"/>
        return new Rectangle();
    },
    getFirstLabel: function () {
        /// <summary>Gets a value used to customize the first label displayed on a selected axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setFirstLabel: function (value) {
        /// <summary>Sets a value used to customize the first label displayed on a selected axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getFont: function () {
        /// <returns type="Font"/>
        return new Font();
    },
    setFont: function (value) {
        /// <param name="value" type="Font"/>>
    },
    getForceZero: function () {
        /// <summary>Used to set the minimum value of a numerical axis to zero even if the data minimum value does not start at zero.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setForceZero: function (value) {
        /// <summary>Used to set the minimum value of a numerical axis to zero even if the data minimum value does not start at zero.</summary>
        /// <param name="value" type="bool"/>>
    },
    getHorizontal: function () {
        /// <summary>Returns a boolean value specifying whether the selected axis is a horizontal or vertical axis.</summary>
        /// <returns type="bool"/>
        return true;
    },
    getInverted: function () {
        /// <summary>Sets a selected numerical axis max value lesser than the min value.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setInverted: function (value) {
        /// <summary>Sets a selected numerical axis max value lesser than the min value.</summary>
        /// <param name="value" type="bool"/>>
    },
    getKeyLabels: function () {
        /// <summary>Used to assign a key (short) legend to tickmarks in a categorical axis.</summary>
        /// <returns type="cfx.c0"/>
        return new c0();
    },
    getLabelAngle: function () {
        /// <summary>Sets the text rotation for the labels in the selected axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setLabelAngle: function (value) {
        /// <summary>Sets the text rotation for the labels in the selected axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getLabels: function () {
        /// <summary>Allows you to get or set the tick mark labels for the selected axis.</summary>
        /// <returns type="cfx.c0"/>
        return new c0();
    },
    getLabelsFormat: function () {
        /// <summary>Used to format the labels displayed on the selected axis.</summary>
        /// <returns type="cfx.c3"/>
        return new c3();
    },
    getLabelTrimming: function () {
        /// <returns type="StringTrimming"/>
        return new StringTrimming();
    },
    setLabelTrimming: function (value) {
        /// <param name="value" type="StringTrimming"/>>
    },
    getLabelValue: function () {
        /// <summary>Allows you to set and associate text labels to major tick marks in a numerical axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setLabelValue: function (value) {
        /// <summary>Allows you to set and associate text labels to major tick marks in a numerical axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getLine: function () {
        /// <summary>Allows you to apply supported Line class members to a selected axis line.</summary>
        /// <returns type="cfx.Line"/>
        return new Line();
    },
    getLineSpacing: function () {
        /// <summary>Gets a value indicating the LineSpacing for an axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setLineSpacing: function (value) {
        /// <summary>Sets a value indicating the LineSpacing for an axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getLogBase: function () {
        /// <summary>Used to set a logarithmic scale for a numerical axis and recalculate the values as powers equal to the setting of this property.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setLogBase: function (value) {
        /// <summary>Used to set a logarithmic scale for a numerical axis and recalculate the values as powers equal to the setting of this property.</summary>
        /// <param name="value" type="number"/>>
    },
    getMax: function () {
        /// <summary>Gets the Max value assigned to the specified axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setMax: function (value) {
        /// <summary>Sets the Max value assigned to the specified axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getMaxSizePercentage: function () {
        /// <summary>Allows you to specify the maximum percentage of the chart area the AxisX labels may occupy.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setMaxSizePercentage: function (value) {
        /// <summary>Allows you to specify the maximum percentage of the chart area the AxisX labels may occupy.</summary>
        /// <param name="value" type="number"/>>
    },
    getMin: function () {
        /// <summary>Gets the Min value assigned to the specified axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setMin: function (value) {
        /// <summary>Sets the Min value assigned to the specified axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getMinorStep: function () {
        /// <summary>Specifies the increment you want between minor tick marks and minor gridlines (if they are displayed) on the selected axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setMinorStep: function (value) {
        /// <summary>Specifies the increment you want between minor tick marks and minor gridlines (if they are displayed) on the selected axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getNotify: function () {
        /// <summary>Used to enable or disable the GetAxisLabel event so you can customize labels on the specified axis.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setNotify: function (value) {
        /// <summary>Used to enable or disable the GetAxisLabel event so you can customize labels on the specified axis.</summary>
        /// <param name="value" type="bool"/>>
    },
    getPixelOffset: function () {
        /// <returns type="number"/>
        return 0;
    },
    setPixelOffset: function (value) {
        /// <param name="value" type="number"/>>
    },
    getPixelsPerUnit: function () {
        /// <summary>Gives you the ability to control how many pixels per unit are in the selected axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setPixelsPerUnit: function (value) {
        /// <summary>Gives you the ability to control how many pixels per unit are in the selected axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getPosition: function () {
        /// <summary>Specifies the placement of the axis labels and tick marks relative to the chart.</summary>
        /// <returns type="cfx.AxisPosition"/>
        return new AxisPosition();
    },
    setPosition: function (value) {
        /// <summary>Specifies the placement of the axis labels and tick marks relative to the chart.</summary>
        /// <param name="value" type="cfx.AxisPosition"/>>
    },
    getScaleUnit: function () {
        /// <summary>Returns or sets the scale unit of a selected axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setScaleUnit: function (value) {
        /// <summary>Returns or sets the scale unit of a selected axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getScrollPosition: function () {
        /// <summary>Allows you to set the starting scroll position when a chart is scrollable.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setScrollPosition: function (value) {
        /// <summary>Allows you to set the starting scroll position when a chart is scrollable.</summary>
        /// <param name="value" type="number"/>>
    },
    getScrollSize: function () {
        /// <summary>Returns the number of scrolls in a chart based on the scroll view configuration.</summary>
        /// <returns type="number"/>
        return 0;
    },
    getSections: function () {
        /// <summary>Returns a selected AxisSection object.</summary>
        /// <returns type="cfx.c2"/>
        return new c2();
    },
    getSeparation: function () {
        /// <summary>Allows you to specify a gap in pixels between multiple axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setSeparation: function (value) {
        /// <summary>Allows you to specify a gap in pixels between multiple axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getStaggered: function () {
        /// <summary>Alternates the level of the axis labels for the selected axis.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setStaggered: function (value) {
        /// <summary>Alternates the level of the axis labels for the selected axis.</summary>
        /// <param name="value" type="bool"/>>
    },
    getStep: function () {
        /// <summary>Specifies the interval of major tick marks and gridlines on the selected axis.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setStep: function (value) {
        /// <summary>Specifies the interval of major tick marks and gridlines on the selected axis.</summary>
        /// <param name="value" type="number"/>>
    },
    getStepCore: function () {
        /// <returns type="number"/>
        return 0;
    },
    getStyle: function () {
        /// <summary>Allows you to add additional styles to the selected axis.</summary>
        /// <returns type="cfx.AxisStyles"/>
        return new AxisStyles();
    },
    setStyle: function (value) {
        /// <summary>Allows you to add additional styles to the selected axis.</summary>
        /// <param name="value" type="cfx.AxisStyles"/>>
    },
    getTitle: function () {
        /// <summary>Gets the title for a specific axis.</summary>
        /// <returns type="cfx.Title"/>
        return new Title();
    },
    setTitle: function (value) {
        /// <summary>Sets the title for a specific axis.</summary>
        /// <param name="value" type="cfx.Title"/>>
    },
    getVisible: function () {
        /// <summary>Shows or hides the lines, labels, tick marks and grids for the selected axis.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <summary>Shows or hides the lines, labels, tick marks and grids for the selected axis.</summary>
        /// <param name="value" type="bool"/>>
    },
    adjustScale: function () {
    },
    calculateStep: function (scaleInfo, gx, availableSpace, processPane) {
        /// <param name="scaleInfo" type="cfx.cC"/>>
        /// <param name="gx" type="cfx.object"/>>
        /// <param name="availableSpace" type="number"/>>
        /// <param name="processPane" type="bool"/>>
    },
    distanceToPixel: function (distance) {
        /// <param name="distance" type="number"/>>
        /// <returns type="number"/>
        return 0;
    },
    getLabelsCore: function () {
        /// <returns type="cfx.object"/>
        return new object();
    },
    getScrollView: function (_p_) {
        /// <param name="_p_" type="cfx.GetScrollView_Double_r_Double_r"/>>
    },
    pixelToValue: function (pixel) {
        /// <param name="pixel" type="number"/>>
        /// <returns type="number"/>
        return 0;
    },
    resetScale: function () {
    },
    setScrollView: function (min, max) {
        /// <param name="min" type="number"/>>
        /// <param name="max" type="number"/>>
    },
    valueToPixel: function (value) {
        /// <param name="value" type="number"/>>
        /// <returns type="number"/>
        return 0;
    },
    getGrids: function () {
        /// <summary>Allows customization of grid lines by providing access to the supported members of the Grids class.</summary>
        /// <returns type="cfx.Grids"/>
        return new Grids();
    },
    getTextColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setTextColor: function (value) {
        /// <param name="value" type="String"/>>
    }
};

var Line = function() {
}
cfx.Line = Line;
Line.prototype = {
    getColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getEndCap: function () {
        /// <returns type="LineCap"/>
        return new LineCap();
    },
    setEndCap: function (value) {
        /// <param name="value" type="LineCap"/>>
    },
    getStartCap: function () {
        /// <returns type="LineCap"/>
        return new LineCap();
    },
    setStartCap: function (value) {
        /// <param name="value" type="LineCap"/>>
    },
    getStyle: function () {
        /// <returns type="DashStyle"/>
        return new DashStyle();
    },
    setStyle: function (value) {
        /// <param name="value" type="DashStyle"/>>
    },
    getWidth: function () {
        /// <returns type="number"/>
        return 0;
    },
    setWidth: function (value) {
        /// <param name="value" type="number"/>>
    }
};

var GridLine = function() {
}
cfx.GridLine = GridLine;
GridLine.prototype = {
    getMargin: function () {
        /// <returns type="number"/>
        return 0;
    },
    setMargin: function (value) {
        /// <param name="value" type="number"/>>
    },
    getTickMark: function () {
        /// <summary>Gets the style for the tick marks of an GridLine object.</summary>
        /// <returns type="cfx.TickMark"/>
        return new TickMark();
    },
    setTickMark: function (value) {
        /// <summary>Sets the style for the tick marks of an GridLine object.</summary>
        /// <param name="value" type="cfx.TickMark"/>>
    },
    getVisible: function () {
        /// <summary>Gets a value indicating whether the grid line for the selected Gridline will be visible.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <summary>Sets a value indicating whether the grid line for the selected Gridline will be visible.</summary>
        /// <param name="value" type="bool"/>>
    },
    getColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getEndCap: function () {
        /// <returns type="LineCap"/>
        return new LineCap();
    },
    setEndCap: function (value) {
        /// <param name="value" type="LineCap"/>>
    },
    getStartCap: function () {
        /// <returns type="LineCap"/>
        return new LineCap();
    },
    setStartCap: function (value) {
        /// <param name="value" type="LineCap"/>>
    },
    getStyle: function () {
        /// <returns type="DashStyle"/>
        return new DashStyle();
    },
    setStyle: function (value) {
        /// <param name="value" type="DashStyle"/>>
    },
    getWidth: function () {
        /// <returns type="number"/>
        return 0;
    },
    setWidth: function (value) {
        /// <param name="value" type="number"/>>
    }
};

var CustomGridLine = function() {
}
cfx.CustomGridLine = CustomGridLine;
CustomGridLine.prototype = {
    getAlignment: function () {
        /// <summary>Gets the Alignment for the Label in a CustomGridLine.</summary>
        /// <returns type="StringAlignment"/>
        return new StringAlignment();
    },
    setAlignment: function (value) {
        /// <summary>Sets the Alignment for the Label in a CustomGridLine.</summary>
        /// <param name="value" type="StringAlignment"/>>
    },
    getAxis: function () {
        /// <returns type="cfx.c_"/>
        return new c_();
    },
    getExtraStyle: function () {
        /// <summary>Allows you to set additional visual attributes in a custom grid line.</summary>
        /// <returns type="cfx.CustomGridLineStyles"/>
        return new CustomGridLineStyles();
    },
    setExtraStyle: function (value) {
        /// <summary>Allows you to set additional visual attributes in a custom grid line.</summary>
        /// <param name="value" type="cfx.CustomGridLineStyles"/>>
    },
    getFont: function () {
        /// <returns type="Font"/>
        return new Font();
    },
    setFont: function (value) {
        /// <param name="value" type="Font"/>>
    },
    getFrom: function () {
        /// <returns type="number"/>
        return 0;
    },
    setFrom: function (value) {
        /// <param name="value" type="number"/>>
    },
    getLineAlignment: function () {
        /// <summary>Gets the alignment for the Text line relative to the CustomeGridLine.</summary>
        /// <returns type="StringAlignment"/>
        return new StringAlignment();
    },
    setLineAlignment: function (value) {
        /// <summary>Sets the alignment for the Text line relative to the CustomeGridLine.</summary>
        /// <param name="value" type="StringAlignment"/>>
    },
    getMargin: function () {
        /// <returns type="number"/>
        return 0;
    },
    setMargin: function (value) {
        /// <param name="value" type="number"/>>
    },
    getOutsideText: function () {
        /// <summary>Gets or Set the OutsideText which controls the positioning of the CustomGridLine label outside the chart area.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setOutsideText: function (value) {
        /// <summary>Gets or Set the OutsideText which controls the positioning of the CustomGridLine label outside the chart area.</summary>
        /// <param name="value" type="bool"/>>
    },
    getShowLine: function () {
        /// <summary>Gets or Set the ShowLine, which shows or hides the CustomGridLine line.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setShowLine: function (value) {
        /// <summary>Gets or Set the ShowLine, which shows or hides the CustomGridLine line.</summary>
        /// <param name="value" type="bool"/>>
    },
    getShowText: function () {
        /// <summary>Gets or Set the Showtext, which shows or hides the CustomGridLine text.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setShowText: function (value) {
        /// <summary>Gets or Set the Showtext, which shows or hides the CustomGridLine text.</summary>
        /// <param name="value" type="bool"/>>
    },
    getText: function () {
        /// <summary>Gets the Text for a label in a CustomGridLine.</summary>
        /// <returns type="string"/>
        return "";
    },
    setText: function (value) {
        /// <summary>Sets the Text for a label in a CustomGridLine.</summary>
        /// <param name="value" type="string"/>>
    },
    getTextColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setTextColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getTo: function () {
        /// <returns type="number"/>
        return 0;
    },
    setTo: function (value) {
        /// <param name="value" type="number"/>>
    },
    getValue: function () {
        /// <summary>Gets the Value for a CustomGridLine.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setValue: function (value) {
        /// <summary>Sets the Value for a CustomGridLine.</summary>
        /// <param name="value" type="number"/>>
    },
    getColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getEndCap: function () {
        /// <returns type="LineCap"/>
        return new LineCap();
    },
    setEndCap: function (value) {
        /// <param name="value" type="LineCap"/>>
    },
    getStartCap: function () {
        /// <returns type="LineCap"/>
        return new LineCap();
    },
    setStartCap: function (value) {
        /// <param name="value" type="LineCap"/>>
    },
    getStyle: function () {
        /// <returns type="DashStyle"/>
        return new DashStyle();
    },
    setStyle: function (value) {
        /// <param name="value" type="DashStyle"/>>
    },
    getWidth: function () {
        /// <returns type="number"/>
        return 0;
    },
    setWidth: function (value) {
        /// <param name="value" type="number"/>>
    }
};

var RealTimeSettings = function() {
}
cfx.RealTimeSettings = RealTimeSettings;
RealTimeSettings.prototype = {
    getBufferSize: function () {
        /// <summary>Gets the buffer size for real-time charts. When setting this property, the data array will be cleared.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setBufferSize: function (value) {
        /// <summary>Sets the buffer size for real-time charts. When setting this property, the data array will be cleared.</summary>
        /// <param name="value" type="number"/>>
    },
    getFastScroll: function () {
        /// <summary>Gets the Fast Scrolling feature.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setFastScroll: function (value) {
        /// <summary>Sets the Fast Scrolling feature.</summary>
        /// <param name="value" type="bool"/>>
    },
    getIsBufferFull: function () {
        /// <summary>Gets the status of the Buffer to see if it is full.</summary>
        /// <returns type="bool"/>
        return true;
    },
    getLoopMarker: function () {
        /// <summary>Allows you to apply the supported Lineclass members to the real-time loop marker.</summary>
        /// <returns type="cfx.Line"/>
        return new Line();
    },
    getMode: function () {
        /// <summary>Sets or Gets the RealTime Mode.</summary>
        /// <returns type="cfx.RealTimeMode"/>
        return new RealTimeMode();
    },
    setMode: function (value) {
        /// <summary>Sets or Gets the RealTime Mode.</summary>
        /// <param name="value" type="cfx.RealTimeMode"/>>
    },
    beginAddData: function (points, action) {
        /// <param name="points" type="number"/>>
        /// <param name="action" type="cfx.RealTimeAction"/>>
    },
    endAddData: function (scrollLabels, scrollToEnd) {
        /// <param name="scrollLabels" type="bool"/>>
        /// <param name="scrollToEnd" type="bool"/>>
    }
};

var db = function() {
}
cfx.db = db;
db.prototype = {
    getAggressiveAllocation: function () {
        /// <summary>Gets a value indicating ChartFX should agreesively allocate space for future values.  This increases performance at the cost of additional memory usage.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setAggressiveAllocation: function (value) {
        /// <summary>Sets a value indicating ChartFX should agreesively allocate space for future values.  This increases performance at the cost of additional memory usage.</summary>
        /// <param name="value" type="bool"/>>
    },
    getCompactedBy: function () {
        /// <summary>Gets a value indicating the interval of units to compress data.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setCompactedBy: function (value) {
        /// <summary>Sets a value indicating the interval of units to compress data.</summary>
        /// <param name="value" type="number"/>>
    },
    getInitializeHidden: function () {
        /// <summary>Gets a value indicating to intialize all non-initialized values to hidden value.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setInitializeHidden: function (value) {
        /// <summary>Sets a value indicating to intialize all non-initialized values to hidden value.</summary>
        /// <param name="value" type="bool"/>>
    },
    getLabels: function () {
        /// <summary>Provides access to label collection of the main x-axis.</summary>
        /// <returns type="cfx.c0"/>
        return new c0();
    },
    getItem: function (series, point) {
        /// <param name="series" type="number"/>>
        /// <param name="point" type="number"/>>
        /// <returns type="number"/>
        return 0;
    },
    setItem: function (series, point, value) {
        /// <param name="series" type="number"/>>
        /// <param name="point" type="number"/>>
        /// <param name="value" type="number"/>>
    },
    clear: function () {
    },
    compact: function (step) {
        /// <param name="step" type="number"/>>
    },
    removeSeries: function (seriesIndex) {
        /// <param name="seriesIndex" type="number"/>>
    },
    undoCompact: function () {
    }
};

var Chart = function() {
}
cfx.Chart = Chart;
Chart.prototype = {
    getAllSeries: function () {
        /// <summary>Gets the global attributes which are shared across the series of the chart.</summary>
        /// <returns type="cfx.cq"/>
        return new cq();
    },
    getAnimations: function () {
        /// <returns type="cfx.cs"/>
        return new cs();
    },
    getAxesStyle: function () {
        /// <summary>Used to change the style of the axes.</summary>
        /// <returns type="cfx.AxesStyle"/>
        return new AxesStyle();
    },
    setAxesStyle: function (value) {
        /// <summary>Used to change the style of the axes.</summary>
        /// <param name="value" type="cfx.AxesStyle"/>>
    },
    getAxesX: function () {
        /// <summary>Returns the collection of the X axes of the chart.</summary>
        /// <returns type="cfx.cT"/>
        return new cT();
    },
    getAxesY: function () {
        /// <summary>Returns the collection of the Y axes of the chart.</summary>
        /// <returns type="cfx.cS"/>
        return new cS();
    },
    getAxisX: function () {
        /// <summary>Assigns properties specifically to the primary X axis.</summary>
        /// <returns type="cfx.AxisX"/>
        return new AxisX();
    },
    getAxisY: function () {
        /// <summary>Assigns properties specifically to the primary Y axis of the chart.</summary>
        /// <returns type="cfx.AxisY"/>
        return new AxisY();
    },
    getAxisY2: function () {
        /// <summary>Assigns properties specifically to the secondary Y axis of the chart.</summary>
        /// <returns type="cfx.AxisY"/>
        return new AxisY();
    },
    getBackground: function () {
        /// <summary>Allows you to assign a gradient or an image to the background of a chart.</summary>
        /// <returns type="cfx.object"/>
        return new object();
    },
    setBackground: function (value) {
        /// <summary>Allows you to assign a gradient or an image to the background of a chart.</summary>
        /// <param name="value" type="cfx.object"/>>
    },
    getBorder: function () {
        /// <summary>Gets a border to the chart.</summary>
        /// <returns type="cfx.object"/>
        return new object();
    },
    setBorder: function (value) {
        /// <summary>Sets a border to the chart.</summary>
        /// <param name="value" type="cfx.object"/>>
    },
    getConditionalAttributes: function () {
        /// <summary>Returns the collection of ConditionalAttributes objects.</summary>
        /// <returns type="cfx.cZ"/>
        return new cZ();
    },
    getCulture: function () {
        /// <summary>Allows you to localize charts for a specific language and region.</summary>
        /// <returns type="CultureInfo"/>
        return new CultureInfo();
    },
    setCulture: function (value) {
        /// <summary>Allows you to localize charts for a specific language and region.</summary>
        /// <param name="value" type="CultureInfo"/>>
    },
    getData: function () {
        /// <summary>Allows you to set the Y values. Alternatively, the Y values can be access through the Y property of the DataValuesText.</summary>
        /// <returns type="cfx.db"/>
        return new db();
    },
    getDataGrid: function () {
        /// <summary>Gets the Data Grid of the chart. The Data Gird is a spreadsheet that shows the data points in tabular format.  When shown, it allows the user to see both the chart and the grid at the same time.</summary>
        /// <returns type="cfx.DataGrid"/>
        return new DataGrid();
    },
    getDataSource: function () {
        /// <summary>Gets the source containing the values used to populate the chart.</summary>
        /// <returns type="?"/>
        return new ?();
    },
    setDataSource: function (value) {
        /// <summary>Sets the source containing the values used to populate the chart.</summary>
        /// <param name="value" type="?"/>>
    },
    getDataSourceSettings: function () {
        /// <summary>Used to assign data related attributes to your chart applications.</summary>
        /// <returns type="cfx.DataSourceSettings"/>
        return new DataSourceSettings();
    },
    getDefaultPalette: function () {
        /// <returns type="cfx.Palette"/>
        return new Palette();
    },
    setDefaultPalette: function (value) {
        /// <param name="value" type="cfx.Palette"/>>
    },
    getExtensions: function () {
        /// <summary>Collection used to add, clear and assign  extension objects.</summary>
        /// <returns type="cfx.ck"/>
        return new ck();
    },
    getExtraStyle: function () {
        /// <summary>Allows you to set additional visual attributes in the chart.</summary>
        /// <returns type="cfx.ChartStyles"/>
        return new ChartStyles();
    },
    setExtraStyle: function (value) {
        /// <summary>Allows you to set additional visual attributes in the chart.</summary>
        /// <param name="value" type="cfx.ChartStyles"/>>
    },
    getGallery: function () {
        /// <summary>Sets the gallery type for the whole chart.</summary>
        /// <returns type="cfx.Gallery"/>
        return new Gallery();
    },
    setGallery: function (value) {
        /// <summary>Sets the gallery type for the whole chart.</summary>
        /// <param name="value" type="cfx.Gallery"/>>
    },
    getGalleryAttributes: function () {
        /// <summary>Used to create a gallery object to assign additional attributes to the selected gallery type of the chart. The gallery attribute can also be accessed through the AllSeries property.</summary>
        /// <returns type="?"/>
        return new ?();
    },
    setGalleryAttributes: function (value) {
        /// <summary>Used to create a gallery object to assign additional attributes to the selected gallery type of the chart. The gallery attribute can also be accessed through the AllSeries property.</summary>
        /// <param name="value" type="?"/>>
    },
    getLegendBox: function () {
        /// <summary>Allows you to acces the legend box of the chart.</summary>
        /// <returns type="cfx.LegendBox"/>
        return new LegendBox();
    },
    getMainPane: function () {
        /// <summary>Gets the default pane of the chart.</summary>
        /// <returns type="cfx.Pane"/>
        return new Pane();
    },
    getPanes: function () {
        /// <summary>Returns the collection of the defined panes of the chart.</summary>
        /// <returns type="cfx.cQ"/>
        return new cQ();
    },
    getPlotAreaBackground: function () {
        /// <summary>Allows you to assign a gradient or an image to the inside area of a chart.</summary>
        /// <returns type="cfx.object"/>
        return new object();
    },
    setPlotAreaBackground: function (value) {
        /// <summary>Allows you to assign a gradient or an image to the inside area of a chart.</summary>
        /// <param name="value" type="cfx.object"/>>
    },
    getPlotAreaColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setPlotAreaColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getPlotAreaMargin: function () {
        /// <summary>Gets and Sets the top, bottom, left and right gaps of the chart.</summary>
        /// <returns type="cfx.cF"/>
        return new cF();
    },
    getPlotAreaTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setPlotAreaTemplate: function (value) {
        /// <param name="value" type="string"/>>
    },
    getPoints: function () {
        /// <summary>Assigns properties directly to a specific point in the chart.</summary>
        /// <returns type="cfx.cN"/>
        return new cN();
    },
    getRealTime: function () {
        /// <summary>Allows access to the RealTimeSettings class supported members used to create real-time charts.</summary>
        /// <returns type="cfx.RealTimeSettings"/>
        return new RealTimeSettings();
    },
    getSeries: function () {
        /// <summary>Assigns properties directly to the selected series in the chart.</summary>
        /// <returns type="cfx.cV"/>
        return new cV();
    },
    getTitles: function () {
        /// <summary>Returns the collection of titles defined in the chart.</summary>
        /// <returns type="cfx.cP"/>
        return new cP();
    },
    getToolTips: function () {
        /// <summary>Allows you to enable or disable tooltips over a particular element in the chart.</summary>
        /// <returns type="cfx.ToolTipAttributes"/>
        return new ToolTipAttributes();
    },
    getView3D: function () {
        /// <summary>Allows you to enable or disable 3D view of the chart.  Also used to customize the 3D view.</summary>
        /// <returns type="cfx.cE"/>
        return new cE();
    },
    beginUpdate: function () {
    },
    cancelUpdate: function () {
    },
    endUpdate: function () {
    },
    expandMacros: function (mask, series, point, inPoint) {
        /// <param name="mask" type="string"/>>
        /// <param name="series" type="number"/>>
        /// <param name="point" type="number"/>>
        /// <param name="inPoint" type="bool"/>>
        /// <returns type="string"/>
        return "";
    },
    getMessageText: function (key) {
        /// <param name="key" type="string"/>>
        /// <returns type="string"/>
        return "";
    },
    recalculateScale: function () {
    },
    setMessageText: function (key, message) {
        /// <param name="key" type="string"/>>
        /// <param name="message" type="string"/>>
    },
    updateSizeNow: function () {
    }
};

cfx.annotation = { };
cfx.annotation._isCFXNamespace = true;

var AnnImageMode = {
    Original: 0,
    Stretch: 1,
    Tile: 2
};
AnnImageMode._isCFXEnum = true;
cfx.annotation.AnnImageMode = AnnImageMode;

var AttachMode = {
    None: 0,
    Point: 1,
    Elastic: 2
};
AttachMode._isCFXEnum = true;
cfx.annotation.AttachMode = AttachMode;

var BalloonTailCorner = {
    TopLeft: 0,
    TopRight: 1,
    BottomRight: 2,
    BottomLeft: 3,
    Top: 4,
    Right: 5,
    Bottom: 6,
    Left: 7
};
BalloonTailCorner._isCFXEnum = true;
cfx.annotation.BalloonTailCorner = BalloonTailCorner;

var AnnotationVector = function() {
}
cfx.annotation.AnnotationVector = AnnotationVector;
AnnotationVector.prototype = {
    getTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setTemplate: function (value) {
        /// <param name="value" type="string"/>>
    },
    getAnchor: function () {
        /// <summary>Gets the Anchor style for the AnnotationObject.</summary>
        /// <returns type="AnchorStyles"/>
        return new AnchorStyles();
    },
    setAnchor: function (value) {
        /// <summary>Sets the Anchor style for the AnnotationObject.</summary>
        /// <param name="value" type="AnchorStyles"/>>
    },
    getBorder: function () {
        /// <summary>Allows you to apply border attributes to a selected annotation object.</summary>
        /// <returns type="cfx.annotation.Line"/>
        return new Line();
    },
    setBorder: function (value) {
        /// <summary>Allows you to apply border attributes to a selected annotation object.</summary>
        /// <param name="value" type="cfx.annotation.Line"/>>
    },
    getColor: function () {
        /// <summary>Sets the inside fill color for annotation object.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <summary>Sets the inside fill color for annotation object.</summary>
        /// <param name="value" type="String"/>>
    },
    getHeight: function () {
        /// <summary>Gets the height in pixels for the selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setHeight: function (value) {
        /// <summary>Sets the height in pixels for the selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    getLeft: function () {
        /// <summary>Allows you to specify or read the left position of a selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setLeft: function (value) {
        /// <summary>Allows you to specify or read the left position of a selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    getObjectBounds: function () {
        /// <summary>Allows you to specify the bounds for a selected annotation object.</summary>
        /// <returns type="Rectangle"/>
        return new Rectangle();
    },
    setObjectBounds: function (value) {
        /// <summary>Allows you to specify the bounds for a selected annotation object.</summary>
        /// <param name="value" type="Rectangle"/>>
    },
    getPaintBefore: function () {
        /// <summary>Configures the selected annotation object to paint before the rest of the chart elements.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setPaintBefore: function (value) {
        /// <summary>Configures the selected annotation object to paint before the rest of the chart elements.</summary>
        /// <param name="value" type="bool"/>>
    },
    getPattern: function () {
        /// <summary>Allows you to set a pattern for the selected annotation object.</summary>
        /// <returns type="HatchStyle"/>
        return new HatchStyle();
    },
    setPattern: function (value) {
        /// <summary>Allows you to set a pattern for the selected annotation object.</summary>
        /// <param name="value" type="HatchStyle"/>>
    },
    getPlotAreaOnly: function () {
        /// <summary>Gets a value specifying if annotation objects will be drawn outside the plot area.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setPlotAreaOnly: function (value) {
        /// <summary>Sets a value specifying if annotation objects will be drawn outside the plot area.</summary>
        /// <param name="value" type="bool"/>>
    },
    getTag: function () {
        /// <summary>Allows you to get or set a unique identifier to the objects you have created in the chart area.</summary>
        /// <returns type="?"/>
        return new ?();
    },
    setTag: function (value) {
        /// <summary>Allows you to get or set a unique identifier to the objects you have created in the chart area.</summary>
        /// <param name="value" type="?"/>>
    },
    getTop: function () {
        /// <summary>Allows you to specify or read the top position of a selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setTop: function (value) {
        /// <summary>Allows you to specify or read the top position of a selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    getVisible: function () {
        /// <summary>Used to show or hide a selected annotation object in the chart area.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <summary>Used to show or hide a selected annotation object in the chart area.</summary>
        /// <param name="value" type="bool"/>>
    },
    getWidth: function () {
        /// <summary>Gets the Width in pixels for the selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setWidth: function (value) {
        /// <summary>Sets the Width in pixels for the selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    attach: function (dx, dy) {
        /// <param name="dx" type="number"/>>
        /// <param name="dy" type="number"/>>
    },
    attachElastic: function (dxLeft, dyTop, dxRight, dyBottom) {
        /// <param name="dxLeft" type="number"/>>
        /// <param name="dyTop" type="number"/>>
        /// <param name="dxRight" type="number"/>>
        /// <param name="dyBottom" type="number"/>>
    },
    attachAlign: function (horizontalAlignment, dx, verticalAlignment, dy) {
        /// <param name="horizontalAlignment" type="StringAlignment"/>>
        /// <param name="dx" type="number"/>>
        /// <param name="verticalAlignment" type="StringAlignment"/>>
        /// <param name="dy" type="number"/>>
    },
    detach: function () {
    },
    flip: function (horizontal) {
        /// <param name="horizontal" type="bool"/>>
    },
    refresh: function () {
    },
    rotate: function (clockWise) {
        /// <param name="clockWise" type="bool"/>>
    }
};

var AnnotationText = function() {
}
cfx.annotation.AnnotationText = AnnotationText;
AnnotationText.prototype = {
    getAlign: function () {
        /// <summary>Allows you to set the alignment for the selected annotation text object.</summary>
        /// <returns type="StringAlignment"/>
        return new StringAlignment();
    },
    setAlign: function (value) {
        /// <summary>Allows you to set the alignment for the selected annotation text object.</summary>
        /// <param name="value" type="StringAlignment"/>>
    },
    getClip: function () {
        /// <summary>Gets a value indicating whether or not text will be clipped by the border of an AnnotationText.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setClip: function (value) {
        /// <summary>Sets a value indicating whether or not text will be clipped by the border of an AnnotationText.</summary>
        /// <param name="value" type="bool"/>>
    },
    getCornerRadius: function () {
        /// <returns type="number"/>
        return 0;
    },
    setCornerRadius: function (value) {
        /// <param name="value" type="number"/>>
    },
    getFont: function () {
        /// <summary>Allows you to change the font attributes for an annotation object.</summary>
        /// <returns type="Font"/>
        return new Font();
    },
    setFont: function (value) {
        /// <summary>Allows you to change the font attributes for an annotation object.</summary>
        /// <param name="value" type="Font"/>>
    },
    getLineAlignment: function () {
        /// <summary>Sets the vertical alignment for the lines of an annotation text object.</summary>
        /// <returns type="StringAlignment"/>
        return new StringAlignment();
    },
    setLineAlignment: function (value) {
        /// <summary>Sets the vertical alignment for the lines of an annotation text object.</summary>
        /// <param name="value" type="StringAlignment"/>>
    },
    getOrientation: function () {
        /// <summary>Used to set the text orientation for a annotation text object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setOrientation: function (value) {
        /// <summary>Used to set the text orientation for a annotation text object.</summary>
        /// <param name="value" type="number"/>>
    },
    getText: function () {
        /// <summary>Used to set the text for an annotation text object.</summary>
        /// <returns type="string"/>
        return "";
    },
    setText: function (value) {
        /// <summary>Used to set the text for an annotation text object.</summary>
        /// <param name="value" type="string"/>>
    },
    getTextColor: function () {
        /// <summary>Sets the color of the text for an annotation object.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setTextColor: function (value) {
        /// <summary>Sets the color of the text for an annotation object.</summary>
        /// <param name="value" type="String"/>>
    },
    getWordWrap: function () {
        /// <summary>Gets a value indicating whether the text of an annotation text object will be word wrapped.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setWordWrap: function (value) {
        /// <summary>Sets a value indicating whether the text of an annotation text object will be word wrapped.</summary>
        /// <param name="value" type="bool"/>>
    },
    sizeToFit: function () {
    },
    getAnchor: function () {
        /// <summary>Gets the Anchor style for the AnnotationObject.</summary>
        /// <returns type="AnchorStyles"/>
        return new AnchorStyles();
    },
    setAnchor: function (value) {
        /// <summary>Sets the Anchor style for the AnnotationObject.</summary>
        /// <param name="value" type="AnchorStyles"/>>
    },
    getBorder: function () {
        /// <summary>Allows you to apply border attributes to a selected annotation object.</summary>
        /// <returns type="cfx.annotation.Line"/>
        return new Line();
    },
    setBorder: function (value) {
        /// <summary>Allows you to apply border attributes to a selected annotation object.</summary>
        /// <param name="value" type="cfx.annotation.Line"/>>
    },
    getColor: function () {
        /// <summary>Sets the inside fill color for annotation object.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <summary>Sets the inside fill color for annotation object.</summary>
        /// <param name="value" type="String"/>>
    },
    getHeight: function () {
        /// <summary>Gets the height in pixels for the selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setHeight: function (value) {
        /// <summary>Sets the height in pixels for the selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    getLeft: function () {
        /// <summary>Allows you to specify or read the left position of a selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setLeft: function (value) {
        /// <summary>Allows you to specify or read the left position of a selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    getObjectBounds: function () {
        /// <summary>Allows you to specify the bounds for a selected annotation object.</summary>
        /// <returns type="Rectangle"/>
        return new Rectangle();
    },
    setObjectBounds: function (value) {
        /// <summary>Allows you to specify the bounds for a selected annotation object.</summary>
        /// <param name="value" type="Rectangle"/>>
    },
    getPaintBefore: function () {
        /// <summary>Configures the selected annotation object to paint before the rest of the chart elements.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setPaintBefore: function (value) {
        /// <summary>Configures the selected annotation object to paint before the rest of the chart elements.</summary>
        /// <param name="value" type="bool"/>>
    },
    getPattern: function () {
        /// <summary>Allows you to set a pattern for the selected annotation object.</summary>
        /// <returns type="HatchStyle"/>
        return new HatchStyle();
    },
    setPattern: function (value) {
        /// <summary>Allows you to set a pattern for the selected annotation object.</summary>
        /// <param name="value" type="HatchStyle"/>>
    },
    getPlotAreaOnly: function () {
        /// <summary>Gets a value specifying if annotation objects will be drawn outside the plot area.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setPlotAreaOnly: function (value) {
        /// <summary>Sets a value specifying if annotation objects will be drawn outside the plot area.</summary>
        /// <param name="value" type="bool"/>>
    },
    getTag: function () {
        /// <summary>Allows you to get or set a unique identifier to the objects you have created in the chart area.</summary>
        /// <returns type="?"/>
        return new ?();
    },
    setTag: function (value) {
        /// <summary>Allows you to get or set a unique identifier to the objects you have created in the chart area.</summary>
        /// <param name="value" type="?"/>>
    },
    getTop: function () {
        /// <summary>Allows you to specify or read the top position of a selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setTop: function (value) {
        /// <summary>Allows you to specify or read the top position of a selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    getVisible: function () {
        /// <summary>Used to show or hide a selected annotation object in the chart area.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <summary>Used to show or hide a selected annotation object in the chart area.</summary>
        /// <param name="value" type="bool"/>>
    },
    getWidth: function () {
        /// <summary>Gets the Width in pixels for the selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setWidth: function (value) {
        /// <summary>Sets the Width in pixels for the selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    attach: function (dx, dy) {
        /// <param name="dx" type="number"/>>
        /// <param name="dy" type="number"/>>
    },
    attachElastic: function (dxLeft, dyTop, dxRight, dyBottom) {
        /// <param name="dxLeft" type="number"/>>
        /// <param name="dyTop" type="number"/>>
        /// <param name="dxRight" type="number"/>>
        /// <param name="dyBottom" type="number"/>>
    },
    attachAlign: function (horizontalAlignment, dx, verticalAlignment, dy) {
        /// <param name="horizontalAlignment" type="StringAlignment"/>>
        /// <param name="dx" type="number"/>>
        /// <param name="verticalAlignment" type="StringAlignment"/>>
        /// <param name="dy" type="number"/>>
    },
    detach: function () {
    },
    flip: function (horizontal) {
        /// <param name="horizontal" type="bool"/>>
    },
    refresh: function () {
    },
    rotate: function (clockWise) {
        /// <param name="clockWise" type="bool"/>>
    }
};

var AnnotationBalloon = function() {
}
cfx.annotation.AnnotationBalloon = AnnotationBalloon;
AnnotationBalloon.prototype = {
    getTailCorner: function () {
        /// <summary>Gets the corner of the balloon the tip or arrow.</summary>
        /// <returns type="cfx.annotation.BalloonTailCorner"/>
        return new BalloonTailCorner();
    },
    setTailCorner: function (value) {
        /// <summary>Sets the corner of the balloon the tip or arrow.</summary>
        /// <param name="value" type="cfx.annotation.BalloonTailCorner"/>>
    },
    getTailFactor: function () {
        /// <summary>Used to get or set the annotation balloon tip size.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setTailFactor: function (value) {
        /// <summary>Used to get or set the annotation balloon tip size.</summary>
        /// <param name="value" type="number"/>>
    },
    getAlign: function () {
        /// <summary>Allows you to set the alignment for the selected annotation text object.</summary>
        /// <returns type="StringAlignment"/>
        return new StringAlignment();
    },
    setAlign: function (value) {
        /// <summary>Allows you to set the alignment for the selected annotation text object.</summary>
        /// <param name="value" type="StringAlignment"/>>
    },
    getClip: function () {
        /// <summary>Gets a value indicating whether or not text will be clipped by the border of an AnnotationText.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setClip: function (value) {
        /// <summary>Sets a value indicating whether or not text will be clipped by the border of an AnnotationText.</summary>
        /// <param name="value" type="bool"/>>
    },
    getCornerRadius: function () {
        /// <returns type="number"/>
        return 0;
    },
    setCornerRadius: function (value) {
        /// <param name="value" type="number"/>>
    },
    getFont: function () {
        /// <summary>Allows you to change the font attributes for an annotation object.</summary>
        /// <returns type="Font"/>
        return new Font();
    },
    setFont: function (value) {
        /// <summary>Allows you to change the font attributes for an annotation object.</summary>
        /// <param name="value" type="Font"/>>
    },
    getLineAlignment: function () {
        /// <summary>Sets the vertical alignment for the lines of an annotation text object.</summary>
        /// <returns type="StringAlignment"/>
        return new StringAlignment();
    },
    setLineAlignment: function (value) {
        /// <summary>Sets the vertical alignment for the lines of an annotation text object.</summary>
        /// <param name="value" type="StringAlignment"/>>
    },
    getOrientation: function () {
        /// <summary>Used to set the text orientation for a annotation text object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setOrientation: function (value) {
        /// <summary>Used to set the text orientation for a annotation text object.</summary>
        /// <param name="value" type="number"/>>
    },
    getText: function () {
        /// <summary>Used to set the text for an annotation text object.</summary>
        /// <returns type="string"/>
        return "";
    },
    setText: function (value) {
        /// <summary>Used to set the text for an annotation text object.</summary>
        /// <param name="value" type="string"/>>
    },
    getTextColor: function () {
        /// <summary>Sets the color of the text for an annotation object.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setTextColor: function (value) {
        /// <summary>Sets the color of the text for an annotation object.</summary>
        /// <param name="value" type="String"/>>
    },
    getWordWrap: function () {
        /// <summary>Gets a value indicating whether the text of an annotation text object will be word wrapped.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setWordWrap: function (value) {
        /// <summary>Sets a value indicating whether the text of an annotation text object will be word wrapped.</summary>
        /// <param name="value" type="bool"/>>
    },
    sizeToFit: function () {
    },
    getAnchor: function () {
        /// <summary>Gets the Anchor style for the AnnotationObject.</summary>
        /// <returns type="AnchorStyles"/>
        return new AnchorStyles();
    },
    setAnchor: function (value) {
        /// <summary>Sets the Anchor style for the AnnotationObject.</summary>
        /// <param name="value" type="AnchorStyles"/>>
    },
    getBorder: function () {
        /// <summary>Allows you to apply border attributes to a selected annotation object.</summary>
        /// <returns type="cfx.annotation.Line"/>
        return new Line();
    },
    setBorder: function (value) {
        /// <summary>Allows you to apply border attributes to a selected annotation object.</summary>
        /// <param name="value" type="cfx.annotation.Line"/>>
    },
    getColor: function () {
        /// <summary>Sets the inside fill color for annotation object.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <summary>Sets the inside fill color for annotation object.</summary>
        /// <param name="value" type="String"/>>
    },
    getHeight: function () {
        /// <summary>Gets the height in pixels for the selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setHeight: function (value) {
        /// <summary>Sets the height in pixels for the selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    getLeft: function () {
        /// <summary>Allows you to specify or read the left position of a selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setLeft: function (value) {
        /// <summary>Allows you to specify or read the left position of a selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    getObjectBounds: function () {
        /// <summary>Allows you to specify the bounds for a selected annotation object.</summary>
        /// <returns type="Rectangle"/>
        return new Rectangle();
    },
    setObjectBounds: function (value) {
        /// <summary>Allows you to specify the bounds for a selected annotation object.</summary>
        /// <param name="value" type="Rectangle"/>>
    },
    getPaintBefore: function () {
        /// <summary>Configures the selected annotation object to paint before the rest of the chart elements.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setPaintBefore: function (value) {
        /// <summary>Configures the selected annotation object to paint before the rest of the chart elements.</summary>
        /// <param name="value" type="bool"/>>
    },
    getPattern: function () {
        /// <summary>Allows you to set a pattern for the selected annotation object.</summary>
        /// <returns type="HatchStyle"/>
        return new HatchStyle();
    },
    setPattern: function (value) {
        /// <summary>Allows you to set a pattern for the selected annotation object.</summary>
        /// <param name="value" type="HatchStyle"/>>
    },
    getPlotAreaOnly: function () {
        /// <summary>Gets a value specifying if annotation objects will be drawn outside the plot area.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setPlotAreaOnly: function (value) {
        /// <summary>Sets a value specifying if annotation objects will be drawn outside the plot area.</summary>
        /// <param name="value" type="bool"/>>
    },
    getTag: function () {
        /// <summary>Allows you to get or set a unique identifier to the objects you have created in the chart area.</summary>
        /// <returns type="?"/>
        return new ?();
    },
    setTag: function (value) {
        /// <summary>Allows you to get or set a unique identifier to the objects you have created in the chart area.</summary>
        /// <param name="value" type="?"/>>
    },
    getTop: function () {
        /// <summary>Allows you to specify or read the top position of a selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setTop: function (value) {
        /// <summary>Allows you to specify or read the top position of a selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    getVisible: function () {
        /// <summary>Used to show or hide a selected annotation object in the chart area.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <summary>Used to show or hide a selected annotation object in the chart area.</summary>
        /// <param name="value" type="bool"/>>
    },
    getWidth: function () {
        /// <summary>Gets the Width in pixels for the selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setWidth: function (value) {
        /// <summary>Sets the Width in pixels for the selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    attach: function (dx, dy) {
        /// <param name="dx" type="number"/>>
        /// <param name="dy" type="number"/>>
    },
    attachElastic: function (dxLeft, dyTop, dxRight, dyBottom) {
        /// <param name="dxLeft" type="number"/>>
        /// <param name="dyTop" type="number"/>>
        /// <param name="dxRight" type="number"/>>
        /// <param name="dyBottom" type="number"/>>
    },
    attachAlign: function (horizontalAlignment, dx, verticalAlignment, dy) {
        /// <param name="horizontalAlignment" type="StringAlignment"/>>
        /// <param name="dx" type="number"/>>
        /// <param name="verticalAlignment" type="StringAlignment"/>>
        /// <param name="dy" type="number"/>>
    },
    detach: function () {
    },
    flip: function (horizontal) {
        /// <param name="horizontal" type="bool"/>>
    },
    refresh: function () {
    },
    rotate: function (clockWise) {
        /// <param name="clockWise" type="bool"/>>
    }
};

var AnnotationRectangle = function() {
}
cfx.annotation.AnnotationRectangle = AnnotationRectangle;
AnnotationRectangle.prototype = {
    getAnchor: function () {
        /// <summary>Gets the Anchor style for the AnnotationObject.</summary>
        /// <returns type="AnchorStyles"/>
        return new AnchorStyles();
    },
    setAnchor: function (value) {
        /// <summary>Sets the Anchor style for the AnnotationObject.</summary>
        /// <param name="value" type="AnchorStyles"/>>
    },
    getBorder: function () {
        /// <summary>Allows you to apply border attributes to a selected annotation object.</summary>
        /// <returns type="cfx.annotation.Line"/>
        return new Line();
    },
    setBorder: function (value) {
        /// <summary>Allows you to apply border attributes to a selected annotation object.</summary>
        /// <param name="value" type="cfx.annotation.Line"/>>
    },
    getColor: function () {
        /// <summary>Sets the inside fill color for annotation object.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <summary>Sets the inside fill color for annotation object.</summary>
        /// <param name="value" type="String"/>>
    },
    getHeight: function () {
        /// <summary>Gets the height in pixels for the selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setHeight: function (value) {
        /// <summary>Sets the height in pixels for the selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    getLeft: function () {
        /// <summary>Allows you to specify or read the left position of a selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setLeft: function (value) {
        /// <summary>Allows you to specify or read the left position of a selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    getObjectBounds: function () {
        /// <summary>Allows you to specify the bounds for a selected annotation object.</summary>
        /// <returns type="Rectangle"/>
        return new Rectangle();
    },
    setObjectBounds: function (value) {
        /// <summary>Allows you to specify the bounds for a selected annotation object.</summary>
        /// <param name="value" type="Rectangle"/>>
    },
    getPaintBefore: function () {
        /// <summary>Configures the selected annotation object to paint before the rest of the chart elements.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setPaintBefore: function (value) {
        /// <summary>Configures the selected annotation object to paint before the rest of the chart elements.</summary>
        /// <param name="value" type="bool"/>>
    },
    getPattern: function () {
        /// <summary>Allows you to set a pattern for the selected annotation object.</summary>
        /// <returns type="HatchStyle"/>
        return new HatchStyle();
    },
    setPattern: function (value) {
        /// <summary>Allows you to set a pattern for the selected annotation object.</summary>
        /// <param name="value" type="HatchStyle"/>>
    },
    getPlotAreaOnly: function () {
        /// <summary>Gets a value specifying if annotation objects will be drawn outside the plot area.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setPlotAreaOnly: function (value) {
        /// <summary>Sets a value specifying if annotation objects will be drawn outside the plot area.</summary>
        /// <param name="value" type="bool"/>>
    },
    getTag: function () {
        /// <summary>Allows you to get or set a unique identifier to the objects you have created in the chart area.</summary>
        /// <returns type="?"/>
        return new ?();
    },
    setTag: function (value) {
        /// <summary>Allows you to get or set a unique identifier to the objects you have created in the chart area.</summary>
        /// <param name="value" type="?"/>>
    },
    getTop: function () {
        /// <summary>Allows you to specify or read the top position of a selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setTop: function (value) {
        /// <summary>Allows you to specify or read the top position of a selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    getVisible: function () {
        /// <summary>Used to show or hide a selected annotation object in the chart area.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <summary>Used to show or hide a selected annotation object in the chart area.</summary>
        /// <param name="value" type="bool"/>>
    },
    getWidth: function () {
        /// <summary>Gets the Width in pixels for the selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setWidth: function (value) {
        /// <summary>Sets the Width in pixels for the selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    attach: function (dx, dy) {
        /// <param name="dx" type="number"/>>
        /// <param name="dy" type="number"/>>
    },
    attachElastic: function (dxLeft, dyTop, dxRight, dyBottom) {
        /// <param name="dxLeft" type="number"/>>
        /// <param name="dyTop" type="number"/>>
        /// <param name="dxRight" type="number"/>>
        /// <param name="dyBottom" type="number"/>>
    },
    attachAlign: function (horizontalAlignment, dx, verticalAlignment, dy) {
        /// <param name="horizontalAlignment" type="StringAlignment"/>>
        /// <param name="dx" type="number"/>>
        /// <param name="verticalAlignment" type="StringAlignment"/>>
        /// <param name="dy" type="number"/>>
    },
    detach: function () {
    },
    flip: function (horizontal) {
        /// <param name="horizontal" type="bool"/>>
    },
    refresh: function () {
    },
    rotate: function (clockWise) {
        /// <param name="clockWise" type="bool"/>>
    }
};

var AnnotationPicture = function() {
}
cfx.annotation.AnnotationPicture = AnnotationPicture;
AnnotationPicture.prototype = {
    getMode: function () {
        /// <summary>Sets the rendering style for the image used as the annotation picture.</summary>
        /// <returns type="cfx.annotation.AnnImageMode"/>
        return new AnnImageMode();
    },
    setMode: function (value) {
        /// <summary>Sets the rendering style for the image used as the annotation picture.</summary>
        /// <param name="value" type="cfx.annotation.AnnImageMode"/>>
    },
    getPicture: function () {
        /// <summary>Allows you to set the image for an annotation picture object.</summary>
        /// <returns type="Image"/>
        return new Image();
    },
    setPicture: function (value) {
        /// <summary>Allows you to set the image for an annotation picture object.</summary>
        /// <param name="value" type="Image"/>>
    },
    sizeToFit: function () {
    },
    getAnchor: function () {
        /// <summary>Gets the Anchor style for the AnnotationObject.</summary>
        /// <returns type="AnchorStyles"/>
        return new AnchorStyles();
    },
    setAnchor: function (value) {
        /// <summary>Sets the Anchor style for the AnnotationObject.</summary>
        /// <param name="value" type="AnchorStyles"/>>
    },
    getBorder: function () {
        /// <summary>Allows you to apply border attributes to a selected annotation object.</summary>
        /// <returns type="cfx.annotation.Line"/>
        return new Line();
    },
    setBorder: function (value) {
        /// <summary>Allows you to apply border attributes to a selected annotation object.</summary>
        /// <param name="value" type="cfx.annotation.Line"/>>
    },
    getColor: function () {
        /// <summary>Sets the inside fill color for annotation object.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <summary>Sets the inside fill color for annotation object.</summary>
        /// <param name="value" type="String"/>>
    },
    getHeight: function () {
        /// <summary>Gets the height in pixels for the selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setHeight: function (value) {
        /// <summary>Sets the height in pixels for the selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    getLeft: function () {
        /// <summary>Allows you to specify or read the left position of a selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setLeft: function (value) {
        /// <summary>Allows you to specify or read the left position of a selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    getObjectBounds: function () {
        /// <summary>Allows you to specify the bounds for a selected annotation object.</summary>
        /// <returns type="Rectangle"/>
        return new Rectangle();
    },
    setObjectBounds: function (value) {
        /// <summary>Allows you to specify the bounds for a selected annotation object.</summary>
        /// <param name="value" type="Rectangle"/>>
    },
    getPaintBefore: function () {
        /// <summary>Configures the selected annotation object to paint before the rest of the chart elements.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setPaintBefore: function (value) {
        /// <summary>Configures the selected annotation object to paint before the rest of the chart elements.</summary>
        /// <param name="value" type="bool"/>>
    },
    getPattern: function () {
        /// <summary>Allows you to set a pattern for the selected annotation object.</summary>
        /// <returns type="HatchStyle"/>
        return new HatchStyle();
    },
    setPattern: function (value) {
        /// <summary>Allows you to set a pattern for the selected annotation object.</summary>
        /// <param name="value" type="HatchStyle"/>>
    },
    getPlotAreaOnly: function () {
        /// <summary>Gets a value specifying if annotation objects will be drawn outside the plot area.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setPlotAreaOnly: function (value) {
        /// <summary>Sets a value specifying if annotation objects will be drawn outside the plot area.</summary>
        /// <param name="value" type="bool"/>>
    },
    getTag: function () {
        /// <summary>Allows you to get or set a unique identifier to the objects you have created in the chart area.</summary>
        /// <returns type="?"/>
        return new ?();
    },
    setTag: function (value) {
        /// <summary>Allows you to get or set a unique identifier to the objects you have created in the chart area.</summary>
        /// <param name="value" type="?"/>>
    },
    getTop: function () {
        /// <summary>Allows you to specify or read the top position of a selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setTop: function (value) {
        /// <summary>Allows you to specify or read the top position of a selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    getVisible: function () {
        /// <summary>Used to show or hide a selected annotation object in the chart area.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <summary>Used to show or hide a selected annotation object in the chart area.</summary>
        /// <param name="value" type="bool"/>>
    },
    getWidth: function () {
        /// <summary>Gets the Width in pixels for the selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setWidth: function (value) {
        /// <summary>Sets the Width in pixels for the selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    attach: function (dx, dy) {
        /// <param name="dx" type="number"/>>
        /// <param name="dy" type="number"/>>
    },
    attachElastic: function (dxLeft, dyTop, dxRight, dyBottom) {
        /// <param name="dxLeft" type="number"/>>
        /// <param name="dyTop" type="number"/>>
        /// <param name="dxRight" type="number"/>>
        /// <param name="dyBottom" type="number"/>>
    },
    attachAlign: function (horizontalAlignment, dx, verticalAlignment, dy) {
        /// <param name="horizontalAlignment" type="StringAlignment"/>>
        /// <param name="dx" type="number"/>>
        /// <param name="verticalAlignment" type="StringAlignment"/>>
        /// <param name="dy" type="number"/>>
    },
    detach: function () {
    },
    flip: function (horizontal) {
        /// <param name="horizontal" type="bool"/>>
    },
    refresh: function () {
    },
    rotate: function (clockWise) {
        /// <param name="clockWise" type="bool"/>>
    }
};

var AnnotationContainer = function() {
}
cfx.annotation.AnnotationContainer = AnnotationContainer;
AnnotationContainer.prototype = {
    getElement: function () {
        /// <returns type="?"/>
        return new ?();
    },
    setElement: function (value) {
        /// <param name="value" type="?"/>>
    },
    getAnchor: function () {
        /// <summary>Gets the Anchor style for the AnnotationObject.</summary>
        /// <returns type="AnchorStyles"/>
        return new AnchorStyles();
    },
    setAnchor: function (value) {
        /// <summary>Sets the Anchor style for the AnnotationObject.</summary>
        /// <param name="value" type="AnchorStyles"/>>
    },
    getBorder: function () {
        /// <summary>Allows you to apply border attributes to a selected annotation object.</summary>
        /// <returns type="cfx.annotation.Line"/>
        return new Line();
    },
    setBorder: function (value) {
        /// <summary>Allows you to apply border attributes to a selected annotation object.</summary>
        /// <param name="value" type="cfx.annotation.Line"/>>
    },
    getColor: function () {
        /// <summary>Sets the inside fill color for annotation object.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <summary>Sets the inside fill color for annotation object.</summary>
        /// <param name="value" type="String"/>>
    },
    getHeight: function () {
        /// <summary>Gets the height in pixels for the selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setHeight: function (value) {
        /// <summary>Sets the height in pixels for the selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    getLeft: function () {
        /// <summary>Allows you to specify or read the left position of a selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setLeft: function (value) {
        /// <summary>Allows you to specify or read the left position of a selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    getObjectBounds: function () {
        /// <summary>Allows you to specify the bounds for a selected annotation object.</summary>
        /// <returns type="Rectangle"/>
        return new Rectangle();
    },
    setObjectBounds: function (value) {
        /// <summary>Allows you to specify the bounds for a selected annotation object.</summary>
        /// <param name="value" type="Rectangle"/>>
    },
    getPaintBefore: function () {
        /// <summary>Configures the selected annotation object to paint before the rest of the chart elements.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setPaintBefore: function (value) {
        /// <summary>Configures the selected annotation object to paint before the rest of the chart elements.</summary>
        /// <param name="value" type="bool"/>>
    },
    getPattern: function () {
        /// <summary>Allows you to set a pattern for the selected annotation object.</summary>
        /// <returns type="HatchStyle"/>
        return new HatchStyle();
    },
    setPattern: function (value) {
        /// <summary>Allows you to set a pattern for the selected annotation object.</summary>
        /// <param name="value" type="HatchStyle"/>>
    },
    getPlotAreaOnly: function () {
        /// <summary>Gets a value specifying if annotation objects will be drawn outside the plot area.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setPlotAreaOnly: function (value) {
        /// <summary>Sets a value specifying if annotation objects will be drawn outside the plot area.</summary>
        /// <param name="value" type="bool"/>>
    },
    getTag: function () {
        /// <summary>Allows you to get or set a unique identifier to the objects you have created in the chart area.</summary>
        /// <returns type="?"/>
        return new ?();
    },
    setTag: function (value) {
        /// <summary>Allows you to get or set a unique identifier to the objects you have created in the chart area.</summary>
        /// <param name="value" type="?"/>>
    },
    getTop: function () {
        /// <summary>Allows you to specify or read the top position of a selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setTop: function (value) {
        /// <summary>Allows you to specify or read the top position of a selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    getVisible: function () {
        /// <summary>Used to show or hide a selected annotation object in the chart area.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <summary>Used to show or hide a selected annotation object in the chart area.</summary>
        /// <param name="value" type="bool"/>>
    },
    getWidth: function () {
        /// <summary>Gets the Width in pixels for the selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setWidth: function (value) {
        /// <summary>Sets the Width in pixels for the selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    attach: function (dx, dy) {
        /// <param name="dx" type="number"/>>
        /// <param name="dy" type="number"/>>
    },
    attachElastic: function (dxLeft, dyTop, dxRight, dyBottom) {
        /// <param name="dxLeft" type="number"/>>
        /// <param name="dyTop" type="number"/>>
        /// <param name="dxRight" type="number"/>>
        /// <param name="dyBottom" type="number"/>>
    },
    attachAlign: function (horizontalAlignment, dx, verticalAlignment, dy) {
        /// <param name="horizontalAlignment" type="StringAlignment"/>>
        /// <param name="dx" type="number"/>>
        /// <param name="verticalAlignment" type="StringAlignment"/>>
        /// <param name="dy" type="number"/>>
    },
    detach: function () {
    },
    flip: function (horizontal) {
        /// <param name="horizontal" type="bool"/>>
    },
    refresh: function () {
    },
    rotate: function (clockWise) {
        /// <param name="clockWise" type="bool"/>>
    }
};

var AnnotationCircle = function() {
}
cfx.annotation.AnnotationCircle = AnnotationCircle;
AnnotationCircle.prototype = {
    getAnchor: function () {
        /// <summary>Gets the Anchor style for the AnnotationObject.</summary>
        /// <returns type="AnchorStyles"/>
        return new AnchorStyles();
    },
    setAnchor: function (value) {
        /// <summary>Sets the Anchor style for the AnnotationObject.</summary>
        /// <param name="value" type="AnchorStyles"/>>
    },
    getBorder: function () {
        /// <summary>Allows you to apply border attributes to a selected annotation object.</summary>
        /// <returns type="cfx.annotation.Line"/>
        return new Line();
    },
    setBorder: function (value) {
        /// <summary>Allows you to apply border attributes to a selected annotation object.</summary>
        /// <param name="value" type="cfx.annotation.Line"/>>
    },
    getColor: function () {
        /// <summary>Sets the inside fill color for annotation object.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <summary>Sets the inside fill color for annotation object.</summary>
        /// <param name="value" type="String"/>>
    },
    getHeight: function () {
        /// <summary>Gets the height in pixels for the selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setHeight: function (value) {
        /// <summary>Sets the height in pixels for the selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    getLeft: function () {
        /// <summary>Allows you to specify or read the left position of a selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setLeft: function (value) {
        /// <summary>Allows you to specify or read the left position of a selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    getObjectBounds: function () {
        /// <summary>Allows you to specify the bounds for a selected annotation object.</summary>
        /// <returns type="Rectangle"/>
        return new Rectangle();
    },
    setObjectBounds: function (value) {
        /// <summary>Allows you to specify the bounds for a selected annotation object.</summary>
        /// <param name="value" type="Rectangle"/>>
    },
    getPaintBefore: function () {
        /// <summary>Configures the selected annotation object to paint before the rest of the chart elements.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setPaintBefore: function (value) {
        /// <summary>Configures the selected annotation object to paint before the rest of the chart elements.</summary>
        /// <param name="value" type="bool"/>>
    },
    getPattern: function () {
        /// <summary>Allows you to set a pattern for the selected annotation object.</summary>
        /// <returns type="HatchStyle"/>
        return new HatchStyle();
    },
    setPattern: function (value) {
        /// <summary>Allows you to set a pattern for the selected annotation object.</summary>
        /// <param name="value" type="HatchStyle"/>>
    },
    getPlotAreaOnly: function () {
        /// <summary>Gets a value specifying if annotation objects will be drawn outside the plot area.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setPlotAreaOnly: function (value) {
        /// <summary>Sets a value specifying if annotation objects will be drawn outside the plot area.</summary>
        /// <param name="value" type="bool"/>>
    },
    getTag: function () {
        /// <summary>Allows you to get or set a unique identifier to the objects you have created in the chart area.</summary>
        /// <returns type="?"/>
        return new ?();
    },
    setTag: function (value) {
        /// <summary>Allows you to get or set a unique identifier to the objects you have created in the chart area.</summary>
        /// <param name="value" type="?"/>>
    },
    getTop: function () {
        /// <summary>Allows you to specify or read the top position of a selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setTop: function (value) {
        /// <summary>Allows you to specify or read the top position of a selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    getVisible: function () {
        /// <summary>Used to show or hide a selected annotation object in the chart area.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <summary>Used to show or hide a selected annotation object in the chart area.</summary>
        /// <param name="value" type="bool"/>>
    },
    getWidth: function () {
        /// <summary>Gets the Width in pixels for the selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setWidth: function (value) {
        /// <summary>Sets the Width in pixels for the selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    attach: function (dx, dy) {
        /// <param name="dx" type="number"/>>
        /// <param name="dy" type="number"/>>
    },
    attachElastic: function (dxLeft, dyTop, dxRight, dyBottom) {
        /// <param name="dxLeft" type="number"/>>
        /// <param name="dyTop" type="number"/>>
        /// <param name="dxRight" type="number"/>>
        /// <param name="dyBottom" type="number"/>>
    },
    attachAlign: function (horizontalAlignment, dx, verticalAlignment, dy) {
        /// <param name="horizontalAlignment" type="StringAlignment"/>>
        /// <param name="dx" type="number"/>>
        /// <param name="verticalAlignment" type="StringAlignment"/>>
        /// <param name="dy" type="number"/>>
    },
    detach: function () {
    },
    flip: function (horizontal) {
        /// <param name="horizontal" type="bool"/>>
    },
    refresh: function () {
    },
    rotate: function (clockWise) {
        /// <param name="clockWise" type="bool"/>>
    }
};

var AnnotationArrow = function() {
}
cfx.annotation.AnnotationArrow = AnnotationArrow;
AnnotationArrow.prototype = {
    getEndCap: function () {
        /// <summary>Allows you to control the ending arrow head height, width and style of a selected annotation arrow object.</summary>
        /// <returns type="AdjustableArrowCap"/>
        return new AdjustableArrowCap();
    },
    setEndCap: function (value) {
        /// <summary>Allows you to control the ending arrow head height, width and style of a selected annotation arrow object.</summary>
        /// <param name="value" type="AdjustableArrowCap"/>>
    },
    getStartCap: function () {
        /// <summary>Allows you to control the starting arrow head height, width and style of a selected annotation arrow object.</summary>
        /// <returns type="AdjustableArrowCap"/>
        return new AdjustableArrowCap();
    },
    setStartCap: function (value) {
        /// <summary>Allows you to control the starting arrow head height, width and style of a selected annotation arrow object.</summary>
        /// <param name="value" type="AdjustableArrowCap"/>>
    },
    getAnchor: function () {
        /// <summary>Gets the Anchor style for the AnnotationObject.</summary>
        /// <returns type="AnchorStyles"/>
        return new AnchorStyles();
    },
    setAnchor: function (value) {
        /// <summary>Sets the Anchor style for the AnnotationObject.</summary>
        /// <param name="value" type="AnchorStyles"/>>
    },
    getBorder: function () {
        /// <summary>Allows you to apply border attributes to a selected annotation object.</summary>
        /// <returns type="cfx.annotation.Line"/>
        return new Line();
    },
    setBorder: function (value) {
        /// <summary>Allows you to apply border attributes to a selected annotation object.</summary>
        /// <param name="value" type="cfx.annotation.Line"/>>
    },
    getColor: function () {
        /// <summary>Sets the inside fill color for annotation object.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <summary>Sets the inside fill color for annotation object.</summary>
        /// <param name="value" type="String"/>>
    },
    getHeight: function () {
        /// <summary>Gets the height in pixels for the selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setHeight: function (value) {
        /// <summary>Sets the height in pixels for the selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    getLeft: function () {
        /// <summary>Allows you to specify or read the left position of a selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setLeft: function (value) {
        /// <summary>Allows you to specify or read the left position of a selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    getObjectBounds: function () {
        /// <summary>Allows you to specify the bounds for a selected annotation object.</summary>
        /// <returns type="Rectangle"/>
        return new Rectangle();
    },
    setObjectBounds: function (value) {
        /// <summary>Allows you to specify the bounds for a selected annotation object.</summary>
        /// <param name="value" type="Rectangle"/>>
    },
    getPaintBefore: function () {
        /// <summary>Configures the selected annotation object to paint before the rest of the chart elements.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setPaintBefore: function (value) {
        /// <summary>Configures the selected annotation object to paint before the rest of the chart elements.</summary>
        /// <param name="value" type="bool"/>>
    },
    getPattern: function () {
        /// <summary>Allows you to set a pattern for the selected annotation object.</summary>
        /// <returns type="HatchStyle"/>
        return new HatchStyle();
    },
    setPattern: function (value) {
        /// <summary>Allows you to set a pattern for the selected annotation object.</summary>
        /// <param name="value" type="HatchStyle"/>>
    },
    getPlotAreaOnly: function () {
        /// <summary>Gets a value specifying if annotation objects will be drawn outside the plot area.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setPlotAreaOnly: function (value) {
        /// <summary>Sets a value specifying if annotation objects will be drawn outside the plot area.</summary>
        /// <param name="value" type="bool"/>>
    },
    getTag: function () {
        /// <summary>Allows you to get or set a unique identifier to the objects you have created in the chart area.</summary>
        /// <returns type="?"/>
        return new ?();
    },
    setTag: function (value) {
        /// <summary>Allows you to get or set a unique identifier to the objects you have created in the chart area.</summary>
        /// <param name="value" type="?"/>>
    },
    getTop: function () {
        /// <summary>Allows you to specify or read the top position of a selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setTop: function (value) {
        /// <summary>Allows you to specify or read the top position of a selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    getVisible: function () {
        /// <summary>Used to show or hide a selected annotation object in the chart area.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <summary>Used to show or hide a selected annotation object in the chart area.</summary>
        /// <param name="value" type="bool"/>>
    },
    getWidth: function () {
        /// <summary>Gets the Width in pixels for the selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setWidth: function (value) {
        /// <summary>Sets the Width in pixels for the selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    attach: function (dx, dy) {
        /// <param name="dx" type="number"/>>
        /// <param name="dy" type="number"/>>
    },
    attachElastic: function (dxLeft, dyTop, dxRight, dyBottom) {
        /// <param name="dxLeft" type="number"/>>
        /// <param name="dyTop" type="number"/>>
        /// <param name="dxRight" type="number"/>>
        /// <param name="dyBottom" type="number"/>>
    },
    attachAlign: function (horizontalAlignment, dx, verticalAlignment, dy) {
        /// <param name="horizontalAlignment" type="StringAlignment"/>>
        /// <param name="dx" type="number"/>>
        /// <param name="verticalAlignment" type="StringAlignment"/>>
        /// <param name="dy" type="number"/>>
    },
    detach: function () {
    },
    flip: function (horizontal) {
        /// <param name="horizontal" type="bool"/>>
    },
    refresh: function () {
    },
    rotate: function (clockWise) {
        /// <param name="clockWise" type="bool"/>>
    }
};

var AnnotationArc = function() {
}
cfx.annotation.AnnotationArc = AnnotationArc;
AnnotationArc.prototype = {
    getAnchor: function () {
        /// <summary>Gets the Anchor style for the AnnotationObject.</summary>
        /// <returns type="AnchorStyles"/>
        return new AnchorStyles();
    },
    setAnchor: function (value) {
        /// <summary>Sets the Anchor style for the AnnotationObject.</summary>
        /// <param name="value" type="AnchorStyles"/>>
    },
    getBorder: function () {
        /// <summary>Allows you to apply border attributes to a selected annotation object.</summary>
        /// <returns type="cfx.annotation.Line"/>
        return new Line();
    },
    setBorder: function (value) {
        /// <summary>Allows you to apply border attributes to a selected annotation object.</summary>
        /// <param name="value" type="cfx.annotation.Line"/>>
    },
    getColor: function () {
        /// <summary>Sets the inside fill color for annotation object.</summary>
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <summary>Sets the inside fill color for annotation object.</summary>
        /// <param name="value" type="String"/>>
    },
    getHeight: function () {
        /// <summary>Gets the height in pixels for the selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setHeight: function (value) {
        /// <summary>Sets the height in pixels for the selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    getLeft: function () {
        /// <summary>Allows you to specify or read the left position of a selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setLeft: function (value) {
        /// <summary>Allows you to specify or read the left position of a selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    getObjectBounds: function () {
        /// <summary>Allows you to specify the bounds for a selected annotation object.</summary>
        /// <returns type="Rectangle"/>
        return new Rectangle();
    },
    setObjectBounds: function (value) {
        /// <summary>Allows you to specify the bounds for a selected annotation object.</summary>
        /// <param name="value" type="Rectangle"/>>
    },
    getPaintBefore: function () {
        /// <summary>Configures the selected annotation object to paint before the rest of the chart elements.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setPaintBefore: function (value) {
        /// <summary>Configures the selected annotation object to paint before the rest of the chart elements.</summary>
        /// <param name="value" type="bool"/>>
    },
    getPattern: function () {
        /// <summary>Allows you to set a pattern for the selected annotation object.</summary>
        /// <returns type="HatchStyle"/>
        return new HatchStyle();
    },
    setPattern: function (value) {
        /// <summary>Allows you to set a pattern for the selected annotation object.</summary>
        /// <param name="value" type="HatchStyle"/>>
    },
    getPlotAreaOnly: function () {
        /// <summary>Gets a value specifying if annotation objects will be drawn outside the plot area.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setPlotAreaOnly: function (value) {
        /// <summary>Sets a value specifying if annotation objects will be drawn outside the plot area.</summary>
        /// <param name="value" type="bool"/>>
    },
    getTag: function () {
        /// <summary>Allows you to get or set a unique identifier to the objects you have created in the chart area.</summary>
        /// <returns type="?"/>
        return new ?();
    },
    setTag: function (value) {
        /// <summary>Allows you to get or set a unique identifier to the objects you have created in the chart area.</summary>
        /// <param name="value" type="?"/>>
    },
    getTop: function () {
        /// <summary>Allows you to specify or read the top position of a selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setTop: function (value) {
        /// <summary>Allows you to specify or read the top position of a selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    getVisible: function () {
        /// <summary>Used to show or hide a selected annotation object in the chart area.</summary>
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <summary>Used to show or hide a selected annotation object in the chart area.</summary>
        /// <param name="value" type="bool"/>>
    },
    getWidth: function () {
        /// <summary>Gets the Width in pixels for the selected annotation object.</summary>
        /// <returns type="number"/>
        return 0;
    },
    setWidth: function (value) {
        /// <summary>Sets the Width in pixels for the selected annotation object.</summary>
        /// <param name="value" type="number"/>>
    },
    attach: function (dx, dy) {
        /// <param name="dx" type="number"/>>
        /// <param name="dy" type="number"/>>
    },
    attachElastic: function (dxLeft, dyTop, dxRight, dyBottom) {
        /// <param name="dxLeft" type="number"/>>
        /// <param name="dyTop" type="number"/>>
        /// <param name="dxRight" type="number"/>>
        /// <param name="dyBottom" type="number"/>>
    },
    attachAlign: function (horizontalAlignment, dx, verticalAlignment, dy) {
        /// <param name="horizontalAlignment" type="StringAlignment"/>>
        /// <param name="dx" type="number"/>>
        /// <param name="verticalAlignment" type="StringAlignment"/>>
        /// <param name="dy" type="number"/>>
    },
    detach: function () {
    },
    flip: function (horizontal) {
        /// <param name="horizontal" type="bool"/>>
    },
    refresh: function () {
    },
    rotate: function (clockWise) {
        /// <param name="clockWise" type="bool"/>>
    }
};

var dQ = function() {
}
cfx.annotation.dQ = dQ;
dQ.prototype = {
    getItem: function (n) {
        /// <param name="n" type="number"/>>
        /// <returns type="cfx.annotation.dO"/>
        return new dO();
    },
    add: function (annObj) {
        /// <param name="annObj" type="cfx.annotation.dO"/>>
    },
    contains: function (annObj) {
        /// <param name="annObj" type="cfx.annotation.dO"/>>
        /// <returns type="bool"/>
        return true;
    },
    copyTo: function (array, index) {
        /// <param name="array" type="cfx.annotation.AnnotationObject[]"/>>
        /// <param name="index" type="number"/>>
    },
    indexOf: function (annObject) {
        /// <param name="annObject" type="cfx.annotation.dO"/>>
        /// <returns type="number"/>
        return 0;
    },
    insert: function (index, annObj) {
        /// <param name="index" type="number"/>>
        /// <param name="annObj" type="cfx.annotation.dO"/>>
    },
    remove: function (annObj) {
        /// <param name="annObj" type="cfx.annotation.dO"/>>
    }
};

var AnnotationList = function() {
}
cfx.annotation.AnnotationList = AnnotationList;
AnnotationList.prototype = {
    getItem: function (n) {
        /// <param name="n" type="number"/>>
        /// <returns type="cfx.annotation.dO"/>
        return new dO();
    },
    add: function (annObj) {
        /// <param name="annObj" type="cfx.annotation.dO"/>>
    },
    contains: function (annObj) {
        /// <param name="annObj" type="cfx.annotation.dO"/>>
        /// <returns type="bool"/>
        return true;
    },
    copyTo: function (array, index) {
        /// <param name="array" type="cfx.annotation.AnnotationObject[]"/>>
        /// <param name="index" type="number"/>>
    },
    indexOf: function (annObject) {
        /// <param name="annObject" type="cfx.annotation.dO"/>>
        /// <returns type="number"/>
        return 0;
    },
    insert: function (index, annObj) {
        /// <param name="index" type="number"/>>
        /// <param name="annObj" type="cfx.annotation.dO"/>>
    },
    remove: function (annObj) {
        /// <param name="annObj" type="cfx.annotation.dO"/>>
    }
};

var Annotations = function() {
}
cfx.annotation.Annotations = Annotations;
Annotations.prototype = {
    getList: function () {
        /// <summary>Allows you to access the CollectionBase members used modify the annotation list.</summary>
        /// <returns type="cfx.annotation.AnnotationList"/>
        return new AnnotationList();
    }
};

cfx.maps = { };
cfx.maps._isCFXNamespace = true;

var MapLayerGallery = {
    Bubble: 0,
    Surface: 1
};
MapLayerGallery._isCFXEnum = true;
cfx.maps.MapLayerGallery = MapLayerGallery;

var MapProjection = {
    None: 0,
    Mercator: 1
};
MapProjection._isCFXEnum = true;
cfx.maps.MapProjection = MapProjection;

var NumberComparison = {
    Equals: 0,
    GreaterThan: 1,
    LessThan: 2,
    Between: 3
};
NumberComparison._isCFXEnum = true;
cfx.maps.NumberComparison = NumberComparison;

var StringsComparison = {
    Equals: 0,
    StartsWith: 1,
    EndsWith: 2,
    Contains: 3
};
StringsComparison._isCFXEnum = true;
cfx.maps.StringsComparison = StringsComparison;

var SurfaceExtendMethod = {
    None: 0,
    Proyect: 1,
    Average: 2
};
SurfaceExtendMethod._isCFXEnum = true;
cfx.maps.SurfaceExtendMethod = SurfaceExtendMethod;

var WinnerCondition = function() {
}
cfx.maps.WinnerCondition = WinnerCondition;
WinnerCondition.prototype = {
    getWinnerGreatest: function () {
        /// <returns type="bool"/>
        return true;
    },
    setWinnerGreatest: function (value) {
        /// <param name="value" type="bool"/>>
    }
};

var StringFieldCondition = function() {
}
cfx.maps.StringFieldCondition = StringFieldCondition;
StringFieldCondition.prototype = {
    getComparison: function () {
        /// <returns type="cfx.maps.StringsComparison"/>
        return new StringsComparison();
    },
    setComparison: function (value) {
        /// <param name="value" type="cfx.maps.StringsComparison"/>>
    },
    getInverse: function () {
        /// <returns type="bool"/>
        return true;
    },
    setInverse: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getName: function () {
        /// <returns type="string"/>
        return "";
    },
    setName: function (value) {
        /// <param name="value" type="string"/>>
    },
    getValue: function () {
        /// <returns type="string"/>
        return "";
    },
    setValue: function (value) {
        /// <param name="value" type="string"/>>
    }
};

var NumberFieldCondition = function() {
}
cfx.maps.NumberFieldCondition = NumberFieldCondition;
NumberFieldCondition.prototype = {
    getComparison: function () {
        /// <returns type="cfx.maps.NumberComparison"/>
        return new NumberComparison();
    },
    setComparison: function (value) {
        /// <param name="value" type="cfx.maps.NumberComparison"/>>
    },
    getFromValue: function () {
        /// <returns type="number"/>
        return 0;
    },
    setFromValue: function (value) {
        /// <param name="value" type="number"/>>
    },
    getName: function () {
        /// <returns type="string"/>
        return "";
    },
    setName: function (value) {
        /// <param name="value" type="string"/>>
    },
    getUnit: function () {
        /// <returns type="cfx.maps.ConditionUnit"/>
        return new ConditionUnit();
    },
    setUnit: function (value) {
        /// <param name="value" type="cfx.maps.ConditionUnit"/>>
    },
    getValue: function () {
        /// <returns type="number"/>
        return 0;
    },
    setValue: function (value) {
        /// <param name="value" type="number"/>>
    }
};

var d4 = function() {
}
cfx.maps.d4 = d4;
d4.prototype = {
    getLabel: function () {
        /// <returns type="string"/>
        return "";
    },
    getLabelLatitude: function () {
        /// <returns type="number"/>
        return 0;
    },
    getLabelLongitude: function () {
        /// <returns type="number"/>
        return 0;
    },
    getLayer: function () {
        /// <returns type="cfx.maps.MapLayer"/>
        return new MapLayer();
    },
    getMaxLatitude: function () {
        /// <returns type="number"/>
        return 0;
    },
    getMaxLongitude: function () {
        /// <returns type="number"/>
        return 0;
    },
    getMinLatitude: function () {
        /// <returns type="number"/>
        return 0;
    },
    getMinLongitude: function () {
        /// <returns type="number"/>
        return 0;
    },
    getName: function () {
        /// <returns type="string"/>
        return "";
    },
    getPaintAttributes: function () {
        /// <returns type="cfx.maps.PointAttributes"/>
        return new PointAttributes();
    },
    getFieldValue: function (name) {
        /// <param name="name" type="string"/>>
        /// <returns type="string"/>
        return "";
    },
    hasLabelPosition: function () {
        /// <returns type="bool"/>
        return true;
    }
};

var MapLayerFilter = function() {
}
cfx.maps.MapLayerFilter = MapLayerFilter;
MapLayerFilter.prototype = {
    getContextAttributes: function () {
        /// <returns type="cfx.maps.ConditionalAttributes"/>
        return new ConditionalAttributes();
    },
    getRestrictToData: function () {
        /// <returns type="bool"/>
        return true;
    },
    setRestrictToData: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getShowContext: function () {
        /// <returns type="bool"/>
        return true;
    },
    setShowContext: function (value) {
        /// <param name="value" type="bool"/>>
    },
    addCondition: function (condition) {
        /// <param name="condition" type="cfx.maps.object"/>>
    },
    addLabel: function (label) {
        /// <param name="label" type="string"/>>
    }
};

var ec = function() {
}
cfx.maps.ec = ec;
ec.prototype = {
    getBlur: function () {
        /// <returns type="number"/>
        return 0;
    },
    setBlur: function (value) {
        /// <param name="value" type="number"/>>
    },
    getColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getDataOnly: function () {
        /// <returns type="bool"/>
        return true;
    },
    setDataOnly: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getXOffset: function () {
        /// <returns type="number"/>
        return 0;
    },
    setXOffset: function (value) {
        /// <param name="value" type="number"/>>
    },
    getYOffset: function () {
        /// <returns type="number"/>
        return 0;
    },
    setYOffset: function (value) {
        /// <param name="value" type="number"/>>
    }
};

var MapLayer = function() {
}
cfx.maps.MapLayer = MapLayer;
MapLayer.prototype = {
    getConditions: function () {
        /// <returns type="cfx.maps.cZ"/>
        return new cZ();
    },
    getContentTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setContentTemplate: function (value) {
        /// <param name="value" type="string"/>>
    },
    getDefaultAttributes: function () {
        /// <returns type="cfx.maps.ConditionalAttributes"/>
        return new ConditionalAttributes();
    },
    getFieldLabel: function () {
        /// <returns type="string"/>
        return "";
    },
    setFieldLabel: function (value) {
        /// <param name="value" type="string"/>>
    },
    getFieldName: function () {
        /// <returns type="string"/>
        return "";
    },
    setFieldName: function (value) {
        /// <param name="value" type="string"/>>
    },
    getFilter: function () {
        /// <returns type="cfx.maps.MapLayerFilter"/>
        return new MapLayerFilter();
    },
    getGallery: function () {
        /// <returns type="cfx.maps.MapLayerGallery"/>
        return new MapLayerGallery();
    },
    setGallery: function (value) {
        /// <param name="value" type="cfx.maps.MapLayerGallery"/>>
    },
    getGalleryAttributes: function () {
        /// <returns type="?"/>
        return new ?();
    },
    setGalleryAttributes: function (value) {
        /// <param name="value" type="?"/>>
    },
    getHandleEvents: function () {
        /// <returns type="bool"/>
        return true;
    },
    setHandleEvents: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getIndex: function () {
        /// <returns type="number"/>
        return 0;
    },
    getMaxLatitude: function () {
        /// <returns type="number"/>
        return 0;
    },
    getMaxLongitude: function () {
        /// <returns type="number"/>
        return 0;
    },
    getMinLatitude: function () {
        /// <returns type="number"/>
        return 0;
    },
    getMinLongitude: function () {
        /// <returns type="number"/>
        return 0;
    },
    getPath: function () {
        /// <returns type="string"/>
        return "";
    },
    setPath: function (value) {
        /// <param name="value" type="string"/>>
    },
    getShadow: function () {
        /// <returns type="cfx.maps.ec"/>
        return new ec();
    },
    getShapes: function () {
        /// <returns type="cfx.maps.MapShapeCollection"/>
        return new MapShapeCollection();
    },
    getShowWithoutLabels: function () {
        /// <returns type="bool"/>
        return true;
    },
    setShowWithoutLabels: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getTextShadow: function () {
        /// <returns type="bool"/>
        return true;
    },
    setTextShadow: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getUsesData: function () {
        /// <returns type="bool"/>
        return true;
    },
    setUsesData: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getVisible: function () {
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <param name="value" type="bool"/>>
    }
};

var MapShapeCollection = function() {
}
cfx.maps.MapShapeCollection = MapShapeCollection;
MapShapeCollection.prototype = {
    getCount: function () {
        /// <returns type="number"/>
        return 0;
    },
    getItem: function (index) {
        /// <param name="index" type="number"/>>
        /// <returns type="cfx.maps.d4"/>
        return new d4();
    }
};

var ColorGradientCollection = function() {
}
cfx.maps.ColorGradientCollection = ColorGradientCollection;
ColorGradientCollection.prototype = {
    getCount: function () {
        /// <returns type="number"/>
        return 0;
    },
    getItem: function (index) {
        /// <param name="index" type="number"/>>
        /// <returns type="cfx.maps.ColorGradient"/>
        return new ColorGradient();
    },
    add: function (colorGradient) {
        /// <param name="colorGradient" type="cfx.maps.ColorGradient"/>>
    },
    clear: function () {
    }
};

var ColorGradient = function() {
}
cfx.maps.ColorGradient = ColorGradient;
ColorGradient.prototype = {
    getColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getOffset: function () {
        /// <returns type="number"/>
        return 0;
    },
    setOffset: function (value) {
        /// <param name="value" type="number"/>>
    }
};

var CollectionCondition = function() {
}
cfx.maps.CollectionCondition = CollectionCondition;
CollectionCondition.prototype = {
    add: function (condition) {
        /// <param name="condition" type="cfx.maps.object"/>>
    }
};

var OrCondition = function() {
}
cfx.maps.OrCondition = OrCondition;
OrCondition.prototype = {
    add: function (condition) {
        /// <param name="condition" type="cfx.maps.object"/>>
    }
};

var AndCondition = function() {
}
cfx.maps.AndCondition = AndCondition;
AndCondition.prototype = {
    add: function (condition) {
        /// <param name="condition" type="cfx.maps.object"/>>
    }
};

var MapTranslationAttributes = function() {
}
cfx.maps.MapTranslationAttributes = MapTranslationAttributes;
MapTranslationAttributes.prototype = {
    getBorder: function () {
        /// <returns type="cfx.maps.Line"/>
        return new Line();
    },
    getMargin: function () {
        /// <returns type="number"/>
        return 0;
    },
    setMargin: function (value) {
        /// <param name="value" type="number"/>>
    },
    getTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setTemplate: function (value) {
        /// <param name="value" type="string"/>>
    },
    endPaint: function () {
    },
    startPaint: function () {
    }
};

var en = function() {
}
cfx.maps.en = en;
en.prototype = {
    getCount: function () {
        /// <returns type="number"/>
        return 0;
    },
    getItem: function (index) {
        /// <param name="index" type="number"/>>
        /// <returns type="cfx.maps.MapLayer"/>
        return new MapLayer();
    },
    add: function (layer) {
        /// <param name="layer" type="cfx.maps.MapLayer"/>>
    },
    find: function (name) {
        /// <param name="name" type="string"/>>
        /// <returns type="cfx.maps.MapLayer"/>
        return new MapLayer();
    },
    insert: function (index, layer) {
        /// <param name="index" type="number"/>>
        /// <param name="layer" type="cfx.maps.MapLayer"/>>
    },
    removeAt: function (index) {
        /// <param name="index" type="number"/>>
    }
};

var Map = function() {
}
cfx.maps.Map = Map;
Map.prototype = {
    getBackColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setBackColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getBackCoversPlotArea: function () {
        /// <returns type="bool"/>
        return true;
    },
    setBackCoversPlotArea: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getLayers: function () {
        /// <returns type="cfx.maps.en"/>
        return new en();
    },
    getMargin: function () {
        /// <returns type="cfx.maps.cF"/>
        return new cF();
    },
    getMaxLatitude: function () {
        /// <returns type="number"/>
        return 0;
    },
    setMaxLatitude: function (value) {
        /// <param name="value" type="number"/>>
    },
    getMaxLongitude: function () {
        /// <returns type="number"/>
        return 0;
    },
    setMaxLongitude: function (value) {
        /// <param name="value" type="number"/>>
    },
    getMinLatitude: function () {
        /// <returns type="number"/>
        return 0;
    },
    setMinLatitude: function (value) {
        /// <param name="value" type="number"/>>
    },
    getMinLongitude: function () {
        /// <returns type="number"/>
        return 0;
    },
    setMinLongitude: function (value) {
        /// <param name="value" type="number"/>>
    },
    getPath: function () {
        /// <returns type="string"/>
        return "";
    },
    setPath: function (value) {
        /// <param name="value" type="string"/>>
    },
    getProjection: function () {
        /// <returns type="cfx.maps.MapProjection"/>
        return new MapProjection();
    },
    setProjection: function (value) {
        /// <param name="value" type="cfx.maps.MapProjection"/>>
    },
    getShowAdditionalLayers: function () {
        /// <returns type="bool"/>
        return true;
    },
    setShowAdditionalLayers: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getTranslationAttributes: function () {
        /// <returns type="cfx.maps.MapTranslationAttributes"/>
        return new MapTranslationAttributes();
    }
};

cfx.gauge = { };
cfx.gauge._isCFXNamespace = true;

var Anchor = {
    Border: 0,
    Value: 1
};
Anchor._isCFXEnum = true;
cfx.gauge.Anchor = Anchor;

var AnimationTiming = {
    Auto: 0,
    Linear: 1,
    EaseIn: 2,
    EaseOut: 3,
    EaseInEaseOut: 4,
    EaseOutBounce: 5
};
AnimationTiming._isCFXEnum = true;
cfx.gauge.AnimationTiming = AnimationTiming;

var ConditionalApplies = {
    Indicator: 1,
    PrimaryValue: 2,
    IndicatorPrimaryBack: 4
};
ConditionalApplies._isCFXEnum = true;
cfx.gauge.ConditionalApplies = ConditionalApplies;

var DockArea = {
    Left: 0,
    Top: 1,
    Right: 2,
    Bottom: 3,
    None: 4
};
DockArea._isCFXEnum = true;
cfx.gauge.DockArea = DockArea;

var IndicatorBackground = {
    None: 0,
    Circle: 1,
    Rectangle: 2,
    RoundRectangle: 3
};
IndicatorBackground._isCFXEnum = true;
cfx.gauge.IndicatorBackground = IndicatorBackground;

var IndicatorPosition = {
    Auto: 0,
    Current: 1,
    Reference: 2,
    Delta: 3,
    PercentChange: 4,
    Primary: 5,
    Secondary: 6
};
IndicatorPosition._isCFXEnum = true;
cfx.gauge.IndicatorPosition = IndicatorPosition;

var IndicatorStyle = {
    ArrowDiagonal: 0,
    ArrowVertical: 1,
    ArrowVerticalPointer: 2,
    TriangleVertical: 3,
    PlusMinus: 4,
    ThickDiagonal: 5
};
IndicatorStyle._isCFXEnum = true;
cfx.gauge.IndicatorStyle = IndicatorStyle;

var PaletteColor = {
    DashboardBack: 0,
    DashboardInside: 1,
    BorderBack: 2,
    BorderInside: 3,
    Indicator: 4,
    IndicatorBorder: 5,
    Filler: 6,
    FillerBorder: 7,
    Cap: 8,
    CapBorder: 9,
    ScaleText: 10,
    BarBack: 11,
    BarBorder: 12,
    BarAlternate: 13,
    SectionBack: 14,
    SectionBorder: 15,
    SectionAlternate: 16,
    Tickmark: 17,
    TickmarkInside: 18,
    Title: 19,
    TitleDocked: 20,
    Caption: 21,
    TrendText: 22,
    ConditionalLess: 23,
    ConditionalEquals: 24,
    ConditionalGreater: 25
};
PaletteColor._isCFXEnum = true;
cfx.gauge.PaletteColor = PaletteColor;

var SecondaryValuesShadow = {
    None: 0,
    Normal: 1,
    Inverted: 2
};
SecondaryValuesShadow._isCFXEnum = true;
cfx.gauge.SecondaryValuesShadow = SecondaryValuesShadow;

var SectionFill = {
    Solid: 0,
    GradientData: 1
};
SectionFill._isCFXEnum = true;
cfx.gauge.SectionFill = SectionFill;

var TickmarkStyle = {
    Line: 0,
    RoundLine: 1,
    Rectangle: 2,
    Circle: 3,
    Triangle: 4,
    None: 5
};
TickmarkStyle._isCFXEnum = true;
cfx.gauge.TickmarkStyle = TickmarkStyle;

var TrendFormat = {
    None: 0,
    Number: 1,
    Currency: 2,
    Percent: 3
};
TrendFormat._isCFXEnum = true;
cfx.gauge.TrendFormat = TrendFormat;

var TrendLayout = {
    Auto: 0,
    Horizontal: 1,
    Vertical: 2,
    Square: 3
};
TrendLayout._isCFXEnum = true;
cfx.gauge.TrendLayout = TrendLayout;

var TrendValues = {
    Current: 1,
    Reference: 2,
    Delta: 3,
    PercentChange: 4
};
TrendValues._isCFXEnum = true;
cfx.gauge.TrendValues = TrendValues;

var ZOrder = {
    Above: 0,
    Behind: 1
};
ZOrder._isCFXEnum = true;
cfx.gauge.ZOrder = ZOrder;

var dZ = function() {
}
cfx.gauge.dZ = dZ;
dZ.prototype = {
    getAppliesTo: function () {
        /// <returns type="cfx.gauge.ConditionalApplies"/>
        return new ConditionalApplies();
    },
    setAppliesTo: function (value) {
        /// <param name="value" type="cfx.gauge.ConditionalApplies"/>>
    },
    getColorEqualToReference: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setColorEqualToReference: function (value) {
        /// <param name="value" type="String"/>>
    },
    getColorGreaterThanReference: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setColorGreaterThanReference: function (value) {
        /// <param name="value" type="String"/>>
    },
    getColorLessThanReference: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setColorLessThanReference: function (value) {
        /// <param name="value" type="String"/>>
    }
};

var d0 = function() {
}
cfx.gauge.d0 = d0;
d0.prototype = {
    getAlignment: function () {
        /// <returns type="StringAlignment"/>
        return new StringAlignment();
    },
    setAlignment: function (value) {
        /// <param name="value" type="StringAlignment"/>>
    },
    getBackground: function () {
        /// <returns type="cfx.gauge.IndicatorBackground"/>
        return new IndicatorBackground();
    },
    setBackground: function (value) {
        /// <param name="value" type="cfx.gauge.IndicatorBackground"/>>
    },
    getHeightRatio: function () {
        /// <returns type="number"/>
        return 0;
    },
    setHeightRatio: function (value) {
        /// <param name="value" type="number"/>>
    },
    getPosition: function () {
        /// <returns type="cfx.gauge.IndicatorPosition"/>
        return new IndicatorPosition();
    },
    setPosition: function (value) {
        /// <param name="value" type="cfx.gauge.IndicatorPosition"/>>
    },
    getRoundPen: function () {
        /// <returns type="bool"/>
        return true;
    },
    setRoundPen: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getStyle: function () {
        /// <returns type="cfx.gauge.IndicatorStyle"/>
        return new IndicatorStyle();
    },
    setStyle: function (value) {
        /// <param name="value" type="cfx.gauge.IndicatorStyle"/>>
    },
    getVisible: function () {
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <param name="value" type="bool"/>>
    }
};

var d1 = function() {
}
cfx.gauge.d1 = d1;
d1.prototype = {
    getLabelMax: function () {
        /// <returns type="number"/>
        return 0;
    },
    setLabelMax: function (value) {
        /// <param name="value" type="number"/>>
    },
    getLabelSpacing: function () {
        /// <returns type="number"/>
        return 0;
    },
    setLabelSpacing: function (value) {
        /// <param name="value" type="number"/>>
    }
};

var d2 = function() {
}
cfx.gauge.d2 = d2;
d2.prototype = {
    getAlphaForeground: function () {
        /// <returns type="number"/>
        return 0;
    },
    setAlphaForeground: function (value) {
        /// <param name="value" type="number"/>>
    },
    getMultiColumn: function () {
        /// <returns type="bool"/>
        return true;
    },
    setMultiColumn: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getSeparatorColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setSeparatorColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getSeparatorStyle: function () {
        /// <returns type="DashStyle"/>
        return new DashStyle();
    },
    setSeparatorStyle: function (value) {
        /// <param name="value" type="DashStyle"/>>
    },
    getSeparatorWidth: function () {
        /// <returns type="number"/>
        return 0;
    },
    setSeparatorWidth: function (value) {
        /// <param name="value" type="number"/>>
    },
    getShadows: function () {
        /// <returns type="cfx.gauge.SecondaryValuesShadow"/>
        return new SecondaryValuesShadow();
    },
    setShadows: function (value) {
        /// <param name="value" type="cfx.gauge.SecondaryValuesShadow"/>>
    },
    getSharedValueSize: function () {
        /// <returns type="bool"/>
        return true;
    },
    setSharedValueSize: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getValueSpacing: function () {
        /// <returns type="number"/>
        return 0;
    },
    setValueSpacing: function (value) {
        /// <param name="value" type="number"/>>
    },
    getLabelMax: function () {
        /// <returns type="number"/>
        return 0;
    },
    setLabelMax: function (value) {
        /// <param name="value" type="number"/>>
    },
    getLabelSpacing: function () {
        /// <returns type="number"/>
        return 0;
    },
    setLabelSpacing: function (value) {
        /// <param name="value" type="number"/>>
    }
};

var d3 = function() {
}
cfx.gauge.d3 = d3;
d3.prototype = {
    getLabelMaxWidth: function () {
        /// <returns type="number"/>
        return 0;
    },
    setLabelMaxWidth: function (value) {
        /// <param name="value" type="number"/>>
    },
    getShadowColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setShadowColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getShadowOffset: function () {
        /// <returns type="Point"/>
        return new Point();
    },
    setShadowOffset: function (value) {
        /// <param name="value" type="Point"/>>
    },
    getValue: function () {
        /// <returns type="cfx.gauge.TrendValues"/>
        return new TrendValues();
    },
    setValue: function (value) {
        /// <param name="value" type="cfx.gauge.TrendValues"/>>
    },
    getLabelMax: function () {
        /// <returns type="number"/>
        return 0;
    },
    setLabelMax: function (value) {
        /// <param name="value" type="number"/>>
    },
    getLabelSpacing: function () {
        /// <returns type="number"/>
        return 0;
    },
    setLabelSpacing: function (value) {
        /// <param name="value" type="number"/>>
    }
};

var d4 = function() {
}
cfx.gauge.d4 = d4;
d4.prototype = {
    getDecimals: function () {
        /// <returns type="number"/>
        return 0;
    },
    setDecimals: function (value) {
        /// <param name="value" type="number"/>>
    },
    getFontStyle: function () {
        /// <returns type="FontStyle"/>
        return new FontStyle();
    },
    setFontStyle: function (value) {
        /// <param name="value" type="FontStyle"/>>
    },
    getFormat: function () {
        /// <returns type="cfx.gauge.TrendFormat"/>
        return new TrendFormat();
    },
    setFormat: function (value) {
        /// <param name="value" type="cfx.gauge.TrendFormat"/>>
    },
    getLabel: function () {
        /// <returns type="string"/>
        return "";
    },
    setLabel: function (value) {
        /// <param name="value" type="string"/>>
    },
    getLabelStyle: function () {
        /// <returns type="FontStyle"/>
        return new FontStyle();
    },
    setLabelStyle: function (value) {
        /// <param name="value" type="FontStyle"/>>
    },
    getPriority: function () {
        /// <returns type="number"/>
        return 0;
    },
    setPriority: function (value) {
        /// <param name="value" type="number"/>>
    },
    getScaleUnit: function () {
        /// <returns type="number"/>
        return 0;
    },
    setScaleUnit: function (value) {
        /// <param name="value" type="number"/>>
    },
    getSymbol: function () {
        /// <returns type="string"/>
        return "";
    },
    setSymbol: function (value) {
        /// <param name="value" type="string"/>>
    },
    getSymbolBeforeValue: function () {
        /// <returns type="bool"/>
        return true;
    },
    setSymbolBeforeValue: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getUseSuperscript: function () {
        /// <returns type="bool"/>
        return true;
    },
    setUseSuperscript: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getVisible: function () {
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <param name="value" type="bool"/>>
    }
};

var d5 = function() {
}
cfx.gauge.d5 = d5;
d5.prototype = {
    getScaleUnit: function () {
        /// <returns type="number"/>
        return 0;
    },
    setScaleUnit: function (value) {
        /// <param name="value" type="number"/>>
    },
    getDecimals: function () {
        /// <returns type="number"/>
        return 0;
    },
    setDecimals: function (value) {
        /// <param name="value" type="number"/>>
    },
    getFontStyle: function () {
        /// <returns type="FontStyle"/>
        return new FontStyle();
    },
    setFontStyle: function (value) {
        /// <param name="value" type="FontStyle"/>>
    },
    getFormat: function () {
        /// <returns type="cfx.gauge.TrendFormat"/>
        return new TrendFormat();
    },
    setFormat: function (value) {
        /// <param name="value" type="cfx.gauge.TrendFormat"/>>
    },
    getLabel: function () {
        /// <returns type="string"/>
        return "";
    },
    setLabel: function (value) {
        /// <param name="value" type="string"/>>
    },
    getLabelStyle: function () {
        /// <returns type="FontStyle"/>
        return new FontStyle();
    },
    setLabelStyle: function (value) {
        /// <param name="value" type="FontStyle"/>>
    },
    getPriority: function () {
        /// <returns type="number"/>
        return 0;
    },
    setPriority: function (value) {
        /// <param name="value" type="number"/>>
    },
    getScaleUnit: function () {
        /// <returns type="number"/>
        return 0;
    },
    setScaleUnit: function (value) {
        /// <param name="value" type="number"/>>
    },
    getSymbol: function () {
        /// <returns type="string"/>
        return "";
    },
    setSymbol: function (value) {
        /// <param name="value" type="string"/>>
    },
    getSymbolBeforeValue: function () {
        /// <returns type="bool"/>
        return true;
    },
    setSymbolBeforeValue: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getUseSuperscript: function () {
        /// <returns type="bool"/>
        return true;
    },
    setUseSuperscript: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getVisible: function () {
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <param name="value" type="bool"/>>
    }
};

var d6 = function() {
}
cfx.gauge.d6 = d6;
d6.prototype = {
    getShowPlus: function () {
        /// <returns type="bool"/>
        return true;
    },
    setShowPlus: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getShowSign: function () {
        /// <returns type="bool"/>
        return true;
    },
    setShowSign: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getDecimals: function () {
        /// <returns type="number"/>
        return 0;
    },
    setDecimals: function (value) {
        /// <param name="value" type="number"/>>
    },
    getFontStyle: function () {
        /// <returns type="FontStyle"/>
        return new FontStyle();
    },
    setFontStyle: function (value) {
        /// <param name="value" type="FontStyle"/>>
    },
    getFormat: function () {
        /// <returns type="cfx.gauge.TrendFormat"/>
        return new TrendFormat();
    },
    setFormat: function (value) {
        /// <param name="value" type="cfx.gauge.TrendFormat"/>>
    },
    getLabel: function () {
        /// <returns type="string"/>
        return "";
    },
    setLabel: function (value) {
        /// <param name="value" type="string"/>>
    },
    getLabelStyle: function () {
        /// <returns type="FontStyle"/>
        return new FontStyle();
    },
    setLabelStyle: function (value) {
        /// <param name="value" type="FontStyle"/>>
    },
    getPriority: function () {
        /// <returns type="number"/>
        return 0;
    },
    setPriority: function (value) {
        /// <param name="value" type="number"/>>
    },
    getScaleUnit: function () {
        /// <returns type="number"/>
        return 0;
    },
    setScaleUnit: function (value) {
        /// <param name="value" type="number"/>>
    },
    getSymbol: function () {
        /// <returns type="string"/>
        return "";
    },
    setSymbol: function (value) {
        /// <param name="value" type="string"/>>
    },
    getSymbolBeforeValue: function () {
        /// <returns type="bool"/>
        return true;
    },
    setSymbolBeforeValue: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getUseSuperscript: function () {
        /// <returns type="bool"/>
        return true;
    },
    setUseSuperscript: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getVisible: function () {
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <param name="value" type="bool"/>>
    }
};

var d7 = function() {
}
cfx.gauge.d7 = d7;
d7.prototype = {
    getValue: function () {
        /// <returns type="number"/>
        return 0;
    },
    setValue: function (value) {
        /// <param name="value" type="number"/>>
    },
    getDecimals: function () {
        /// <returns type="number"/>
        return 0;
    },
    setDecimals: function (value) {
        /// <param name="value" type="number"/>>
    },
    getFontStyle: function () {
        /// <returns type="FontStyle"/>
        return new FontStyle();
    },
    setFontStyle: function (value) {
        /// <param name="value" type="FontStyle"/>>
    },
    getFormat: function () {
        /// <returns type="cfx.gauge.TrendFormat"/>
        return new TrendFormat();
    },
    setFormat: function (value) {
        /// <param name="value" type="cfx.gauge.TrendFormat"/>>
    },
    getLabel: function () {
        /// <returns type="string"/>
        return "";
    },
    setLabel: function (value) {
        /// <param name="value" type="string"/>>
    },
    getLabelStyle: function () {
        /// <returns type="FontStyle"/>
        return new FontStyle();
    },
    setLabelStyle: function (value) {
        /// <param name="value" type="FontStyle"/>>
    },
    getPriority: function () {
        /// <returns type="number"/>
        return 0;
    },
    setPriority: function (value) {
        /// <param name="value" type="number"/>>
    },
    getScaleUnit: function () {
        /// <returns type="number"/>
        return 0;
    },
    setScaleUnit: function (value) {
        /// <param name="value" type="number"/>>
    },
    getSymbol: function () {
        /// <returns type="string"/>
        return "";
    },
    setSymbol: function (value) {
        /// <param name="value" type="string"/>>
    },
    getSymbolBeforeValue: function () {
        /// <returns type="bool"/>
        return true;
    },
    setSymbolBeforeValue: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getUseSuperscript: function () {
        /// <returns type="bool"/>
        return true;
    },
    setUseSuperscript: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getVisible: function () {
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <param name="value" type="bool"/>>
    }
};

var d8 = function() {
}
cfx.gauge.d8 = d8;
d8.prototype = {
    getTitles: function () {
        /// <returns type="cfx.gauge.ef"/>
        return new ef();
    },
    addExtension: function (obj) {
        /// <param name="obj" type="?"/>>
    }
};

var Trend = function() {
}
cfx.gauge.Trend = Trend;
Trend.prototype = {
    getBorder: function () {
        /// <returns type="cfx.gauge.eo"/>
        return new eo();
    },
    getConditional: function () {
        /// <returns type="cfx.gauge.dZ"/>
        return new dZ();
    },
    getCulture: function () {
        /// <returns type="CultureInfo"/>
        return new CultureInfo();
    },
    setCulture: function (value) {
        /// <param name="value" type="CultureInfo"/>>
    },
    getCurrent: function () {
        /// <returns type="cfx.gauge.d7"/>
        return new d7();
    },
    getDelta: function () {
        /// <returns type="cfx.gauge.d6"/>
        return new d6();
    },
    getFontName: function () {
        /// <returns type="string"/>
        return "";
    },
    setFontName: function (value) {
        /// <param name="value" type="string"/>>
    },
    getFontStyle: function () {
        /// <returns type="FontStyle"/>
        return new FontStyle();
    },
    setFontStyle: function (value) {
        /// <param name="value" type="FontStyle"/>>
    },
    getIndicator: function () {
        /// <returns type="cfx.gauge.d0"/>
        return new d0();
    },
    getLayout: function () {
        /// <returns type="cfx.gauge.TrendLayout"/>
        return new TrendLayout();
    },
    setLayout: function (value) {
        /// <param name="value" type="cfx.gauge.TrendLayout"/>>
    },
    getPercentChange: function () {
        /// <returns type="cfx.gauge.d5"/>
        return new d5();
    },
    getPrimaryValue: function () {
        /// <returns type="cfx.gauge.d3"/>
        return new d3();
    },
    getReference: function () {
        /// <returns type="cfx.gauge.d7"/>
        return new d7();
    },
    getSecondaryValues: function () {
        /// <returns type="cfx.gauge.d2"/>
        return new d2();
    },
    getShowGuides: function () {
        /// <returns type="bool"/>
        return true;
    },
    setShowGuides: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getTextColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setTextColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getTitles: function () {
        /// <returns type="cfx.gauge.ef"/>
        return new ef();
    },
    addExtension: function (obj) {
        /// <param name="obj" type="?"/>>
    }
};

var Border = function() {
}
cfx.gauge.Border = Border;
Border.prototype = {
    getColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getInsideColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setInsideColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getStrokeWidth: function () {
        /// <returns type="number"/>
        return 0;
    },
    setStrokeWidth: function (value) {
        /// <param name="value" type="number"/>>
    },
    getTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setTemplate: function (value) {
        /// <param name="value" type="string"/>>
    },
    getTitles: function () {
        /// <returns type="cfx.gauge.ef"/>
        return new ef();
    },
    addExtension: function (obj) {
        /// <param name="obj" type="?"/>>
    }
};

var GaugeDefaultAttributes = function() {
}
cfx.gauge.GaugeDefaultAttributes = GaugeDefaultAttributes;
GaugeDefaultAttributes.prototype = {
    getSectionPosition: function () {
        /// <returns type="number"/>
        return 0;
    },
    setSectionPosition: function (value) {
        /// <param name="value" type="number"/>>
    },
    getSectionThickness: function () {
        /// <returns type="number"/>
        return 0;
    },
    setSectionThickness: function (value) {
        /// <param name="value" type="number"/>>
    }
};

var Palette = function() {
}
cfx.gauge.Palette = Palette;
Palette.prototype = {
    getDefaultPalette: function () {
        /// <returns type="cfx.gauge.Palette"/>
        return new Palette();
    },
    setDefaultPalette: function (value) {
        /// <param name="value" type="cfx.gauge.Palette"/>>
    },
    getColor: function (palColor) {
        /// <param name="palColor" type="cfx.gauge.PaletteColor"/>>
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (palColor, value) {
        /// <param name="palColor" type="cfx.gauge.PaletteColor"/>>
        /// <param name="value" type="String"/>>
    }
};

var ea = function() {
}
cfx.gauge.ea = ea;
ea.prototype = {
    getDuration: function () {
        /// <returns type="number"/>
        return 0;
    },
    setDuration: function (value) {
        /// <param name="value" type="number"/>>
    },
    getEnabled: function () {
        /// <returns type="bool"/>
        return true;
    },
    setEnabled: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getFactorScale: function () {
        /// <returns type="bool"/>
        return true;
    },
    setFactorScale: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getTiming: function () {
        /// <returns type="cfx.gauge.AnimationTiming"/>
        return new AnimationTiming();
    },
    setTiming: function (value) {
        /// <param name="value" type="cfx.gauge.AnimationTiming"/>>
    }
};

var eb = function() {
}
cfx.gauge.eb = eb;
eb.prototype = {
    getDataChange: function () {
        /// <returns type="cfx.gauge.ea"/>
        return new ea();
    },
    getLoad: function () {
        /// <returns type="cfx.gauge.ea"/>
        return new ea();
    },
    getShowSpeed: function () {
        /// <returns type="bool"/>
        return true;
    },
    setShowSpeed: function (value) {
        /// <param name="value" type="bool"/>>
    },
    reset: function () {
    }
};

var ec = function() {
}
cfx.gauge.ec = ec;
ec.prototype = {
    getColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getOffsetX: function () {
        /// <returns type="number"/>
        return 0;
    },
    setOffsetX: function (value) {
        /// <param name="value" type="number"/>>
    },
    getOffsetY: function () {
        /// <returns type="number"/>
        return 0;
    },
    setOffsetY: function (value) {
        /// <param name="value" type="number"/>>
    },
    getTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setTemplate: function (value) {
        /// <param name="value" type="string"/>>
    },
    getVisible: function () {
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <param name="value" type="bool"/>>
    }
};

var ed = function() {
}
cfx.gauge.ed = ed;
ed.prototype = {
    getTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setTemplate: function (value) {
        /// <param name="value" type="string"/>>
    },
    getVisible: function () {
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <param name="value" type="bool"/>>
    }
};

var GaugeElement = function() {
}
cfx.gauge.GaugeElement = GaugeElement;
GaugeElement.prototype = {

};

var ef = function() {
}
cfx.gauge.ef = ef;
ef.prototype = {
    getCount: function () {
        /// <returns type="number"/>
        return 0;
    },
    getItem: function (index) {
        /// <param name="index" type="number"/>>
        /// <returns type="cfx.gauge.Title"/>
        return new Title();
    },
    setItem: function (index, value) {
        /// <param name="index" type="number"/>>
        /// <param name="value" type="cfx.gauge.Title"/>>
    },
    add: function (title) {
        /// <param name="title" type="cfx.gauge.Title"/>>
    },
    clear: function () {
    },
    removeAt: function (index) {
        /// <param name="index" type="number"/>>
    }
};

var Title = function() {
}
cfx.gauge.Title = Title;
Title.prototype = {
    getAlignment: function () {
        /// <returns type="StringAlignment"/>
        return new StringAlignment();
    },
    setAlignment: function (value) {
        /// <param name="value" type="StringAlignment"/>>
    },
    getAnchor: function () {
        /// <returns type="cfx.gauge.Anchor"/>
        return new Anchor();
    },
    setAnchor: function (value) {
        /// <param name="value" type="cfx.gauge.Anchor"/>>
    },
    getAngle: function () {
        /// <returns type="number"/>
        return 0;
    },
    setAngle: function (value) {
        /// <param name="value" type="number"/>>
    },
    getDock: function () {
        /// <returns type="cfx.gauge.DockArea"/>
        return new DockArea();
    },
    setDock: function (value) {
        /// <param name="value" type="cfx.gauge.DockArea"/>>
    },
    getFont: function () {
        /// <returns type="Font"/>
        return new Font();
    },
    setFont: function (value) {
        /// <param name="value" type="Font"/>>
    },
    getResizeableFont: function () {
        /// <returns type="bool"/>
        return true;
    },
    setResizeableFont: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getTag: function () {
        /// <returns type="string"/>
        return "";
    },
    setTag: function (value) {
        /// <param name="value" type="string"/>>
    },
    getText: function () {
        /// <returns type="string"/>
        return "";
    },
    setText: function (value) {
        /// <param name="value" type="string"/>>
    },
    getTextColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setTextColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getX: function () {
        /// <returns type="number"/>
        return 0;
    },
    setX: function (value) {
        /// <param name="value" type="number"/>>
    },
    getY: function () {
        /// <returns type="number"/>
        return 0;
    },
    setY: function (value) {
        /// <param name="value" type="number"/>>
    },
    getZOrder: function () {
        /// <returns type="cfx.gauge.ZOrder"/>
        return new ZOrder();
    },
    setZOrder: function (value) {
        /// <param name="value" type="cfx.gauge.ZOrder"/>>
    }
};

var eg = function() {
}
cfx.gauge.eg = eg;
eg.prototype = {
    getMajor: function () {
        /// <returns type="cfx.gauge.eh"/>
        return new eh();
    },
    getMedium: function () {
        /// <returns type="cfx.gauge.eh"/>
        return new eh();
    }
};

var eh = function() {
}
cfx.gauge.eh = eh;
eh.prototype = {
    getColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getInsideColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setInsideColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getLabelGap: function () {
        /// <returns type="number"/>
        return 0;
    },
    setLabelGap: function (value) {
        /// <param name="value" type="number"/>>
    },
    getPosition: function () {
        /// <returns type="number"/>
        return 0;
    },
    setPosition: function (value) {
        /// <param name="value" type="number"/>>
    },
    getSize: function () {
        /// <returns type="number"/>
        return 0;
    },
    setSize: function (value) {
        /// <param name="value" type="number"/>>
    },
    getSkipEnds: function () {
        /// <returns type="bool"/>
        return true;
    },
    setSkipEnds: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getStep: function () {
        /// <returns type="number"/>
        return 0;
    },
    setStep: function (value) {
        /// <param name="value" type="number"/>>
    },
    getStyle: function () {
        /// <returns type="cfx.gauge.TickmarkStyle"/>
        return new TickmarkStyle();
    },
    setStyle: function (value) {
        /// <param name="value" type="cfx.gauge.TickmarkStyle"/>>
    },
    getUseSectionColor: function () {
        /// <returns type="bool"/>
        return true;
    },
    setUseSectionColor: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getVisible: function () {
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getWidth: function () {
        /// <returns type="number"/>
        return 0;
    },
    setWidth: function (value) {
        /// <param name="value" type="number"/>>
    }
};

var ei = function() {
}
cfx.gauge.ei = ei;
ei.prototype = {
    getItem: function (index) {
        /// <param name="index" type="number"/>>
        /// <returns type="cfx.gauge.ScaleSection"/>
        return new ScaleSection();
    },
    setItem: function (index, value) {
        /// <param name="index" type="number"/>>
        /// <param name="value" type="cfx.gauge.ScaleSection"/>>
    },
    add: function (section) {
        /// <param name="section" type="cfx.gauge.ScaleSection"/>>
    },
    clear: function () {
    },
    removeAt: function (index) {
        /// <param name="index" type="number"/>>
    }
};

var ej = function() {
}
cfx.gauge.ej = ej;
ej.prototype = {
    getColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getSize: function () {
        /// <returns type="number"/>
        return 0;
    },
    setSize: function (value) {
        /// <param name="value" type="number"/>>
    },
    getTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setTemplate: function (value) {
        /// <param name="value" type="string"/>>
    },
    getVisible: function () {
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getZOrder: function () {
        /// <returns type="cfx.gauge.ZOrder"/>
        return new ZOrder();
    },
    setZOrder: function (value) {
        /// <param name="value" type="cfx.gauge.ZOrder"/>>
    }
};

var ek = function() {
}
cfx.gauge.ek = ek;
ek.prototype = {
    getAlternateColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setAlternateColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getBorderColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setBorderColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getFillType: function () {
        /// <returns type="cfx.gauge.SectionFill"/>
        return new SectionFill();
    },
    setFillType: function (value) {
        /// <param name="value" type="cfx.gauge.SectionFill"/>>
    },
    getPosition: function () {
        /// <returns type="number"/>
        return 0;
    },
    setPosition: function (value) {
        /// <param name="value" type="number"/>>
    },
    getStrokeThickness: function () {
        /// <returns type="number"/>
        return 0;
    },
    setStrokeThickness: function (value) {
        /// <param name="value" type="number"/>>
    },
    getTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setTemplate: function (value) {
        /// <param name="value" type="string"/>>
    },
    getThickness: function () {
        /// <returns type="number"/>
        return 0;
    },
    setThickness: function (value) {
        /// <param name="value" type="number"/>>
    },
    getVisible: function () {
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <param name="value" type="bool"/>>
    }
};

var ScaleSection = function() {
}
cfx.gauge.ScaleSection = ScaleSection;
ScaleSection.prototype = {
    getFrom: function () {
        /// <returns type="number"/>
        return 0;
    },
    setFrom: function (value) {
        /// <param name="value" type="number"/>>
    },
    getTo: function () {
        /// <returns type="number"/>
        return 0;
    },
    setTo: function (value) {
        /// <param name="value" type="number"/>>
    },
    getAlternateColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setAlternateColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getBorderColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setBorderColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getFillType: function () {
        /// <returns type="cfx.gauge.SectionFill"/>
        return new SectionFill();
    },
    setFillType: function (value) {
        /// <param name="value" type="cfx.gauge.SectionFill"/>>
    },
    getPosition: function () {
        /// <returns type="number"/>
        return 0;
    },
    setPosition: function (value) {
        /// <param name="value" type="number"/>>
    },
    getStrokeThickness: function () {
        /// <returns type="number"/>
        return 0;
    },
    setStrokeThickness: function (value) {
        /// <param name="value" type="number"/>>
    },
    getTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setTemplate: function (value) {
        /// <param name="value" type="string"/>>
    },
    getThickness: function () {
        /// <returns type="number"/>
        return 0;
    },
    setThickness: function (value) {
        /// <param name="value" type="number"/>>
    },
    getVisible: function () {
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <param name="value" type="bool"/>>
    }
};

var el = function() {
}
cfx.gauge.el = el;
el.prototype = {
    getAlignment: function () {
        /// <returns type="StringAlignment"/>
        return new StringAlignment();
    },
    setAlignment: function (value) {
        /// <param name="value" type="StringAlignment"/>>
    },
    getAllowHalves: function () {
        /// <returns type="bool"/>
        return true;
    },
    setAllowHalves: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getBar: function () {
        /// <returns type="cfx.gauge.ek"/>
        return new ek();
    },
    getDecimals: function () {
        /// <returns type="number"/>
        return 0;
    },
    setDecimals: function (value) {
        /// <param name="value" type="number"/>>
    },
    getDrawLabels: function () {
        /// <returns type="bool"/>
        return true;
    },
    setDrawLabels: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getFont: function () {
        /// <returns type="Font"/>
        return new Font();
    },
    setFont: function (value) {
        /// <param name="value" type="Font"/>>
    },
    getIndicators: function () {
        /// <returns type="cfx.gauge.en"/>
        return new en();
    },
    getLabelSpacing: function () {
        /// <returns type="number"/>
        return 0;
    },
    setLabelSpacing: function (value) {
        /// <param name="value" type="number"/>>
    },
    getMainIndicator: function () {
        /// <returns type="cfx.gauge.Indicator"/>
        return new Indicator();
    },
    setMainIndicator: function (value) {
        /// <param name="value" type="cfx.gauge.Indicator"/>>
    },
    getMax: function () {
        /// <returns type="number"/>
        return 0;
    },
    setMax: function (value) {
        /// <param name="value" type="number"/>>
    },
    getMin: function () {
        /// <returns type="number"/>
        return 0;
    },
    setMin: function (value) {
        /// <param name="value" type="number"/>>
    },
    getPosition: function () {
        /// <returns type="number"/>
        return 0;
    },
    setPosition: function (value) {
        /// <param name="value" type="number"/>>
    },
    getScaleUnit: function () {
        /// <returns type="number"/>
        return 0;
    },
    setScaleUnit: function (value) {
        /// <param name="value" type="number"/>>
    },
    getSections: function () {
        /// <returns type="cfx.gauge.ei"/>
        return new ei();
    },
    getTextColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setTextColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getThickness: function () {
        /// <returns type="number"/>
        return 0;
    },
    setThickness: function (value) {
        /// <param name="value" type="number"/>>
    },
    getTickmarks: function () {
        /// <returns type="cfx.gauge.eg"/>
        return new eg();
    },
    getVisible: function () {
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <param name="value" type="bool"/>>
    }
};

var em = function() {
}
cfx.gauge.em = em;
em.prototype = {
    getCap: function () {
        /// <returns type="cfx.gauge.ej"/>
        return new ej();
    },
    getStartAngle: function () {
        /// <returns type="number"/>
        return 0;
    },
    setStartAngle: function (value) {
        /// <param name="value" type="number"/>>
    },
    getSweepAngle: function () {
        /// <returns type="number"/>
        return 0;
    },
    setSweepAngle: function (value) {
        /// <param name="value" type="number"/>>
    },
    getAlignment: function () {
        /// <returns type="StringAlignment"/>
        return new StringAlignment();
    },
    setAlignment: function (value) {
        /// <param name="value" type="StringAlignment"/>>
    },
    getAllowHalves: function () {
        /// <returns type="bool"/>
        return true;
    },
    setAllowHalves: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getBar: function () {
        /// <returns type="cfx.gauge.ek"/>
        return new ek();
    },
    getDecimals: function () {
        /// <returns type="number"/>
        return 0;
    },
    setDecimals: function (value) {
        /// <param name="value" type="number"/>>
    },
    getDrawLabels: function () {
        /// <returns type="bool"/>
        return true;
    },
    setDrawLabels: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getFont: function () {
        /// <returns type="Font"/>
        return new Font();
    },
    setFont: function (value) {
        /// <param name="value" type="Font"/>>
    },
    getIndicators: function () {
        /// <returns type="cfx.gauge.en"/>
        return new en();
    },
    getLabelSpacing: function () {
        /// <returns type="number"/>
        return 0;
    },
    setLabelSpacing: function (value) {
        /// <param name="value" type="number"/>>
    },
    getMainIndicator: function () {
        /// <returns type="cfx.gauge.Indicator"/>
        return new Indicator();
    },
    setMainIndicator: function (value) {
        /// <param name="value" type="cfx.gauge.Indicator"/>>
    },
    getMax: function () {
        /// <returns type="number"/>
        return 0;
    },
    setMax: function (value) {
        /// <param name="value" type="number"/>>
    },
    getMin: function () {
        /// <returns type="number"/>
        return 0;
    },
    setMin: function (value) {
        /// <param name="value" type="number"/>>
    },
    getPosition: function () {
        /// <returns type="number"/>
        return 0;
    },
    setPosition: function (value) {
        /// <param name="value" type="number"/>>
    },
    getScaleUnit: function () {
        /// <returns type="number"/>
        return 0;
    },
    setScaleUnit: function (value) {
        /// <param name="value" type="number"/>>
    },
    getSections: function () {
        /// <returns type="cfx.gauge.ei"/>
        return new ei();
    },
    getTextColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setTextColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getThickness: function () {
        /// <returns type="number"/>
        return 0;
    },
    setThickness: function (value) {
        /// <param name="value" type="number"/>>
    },
    getTickmarks: function () {
        /// <returns type="cfx.gauge.eg"/>
        return new eg();
    },
    getVisible: function () {
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <param name="value" type="bool"/>>
    }
};

var LinearScale = function() {
}
cfx.gauge.LinearScale = LinearScale;
LinearScale.prototype = {
    getAlignment: function () {
        /// <returns type="StringAlignment"/>
        return new StringAlignment();
    },
    setAlignment: function (value) {
        /// <param name="value" type="StringAlignment"/>>
    },
    getAllowHalves: function () {
        /// <returns type="bool"/>
        return true;
    },
    setAllowHalves: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getBar: function () {
        /// <returns type="cfx.gauge.ek"/>
        return new ek();
    },
    getDecimals: function () {
        /// <returns type="number"/>
        return 0;
    },
    setDecimals: function (value) {
        /// <param name="value" type="number"/>>
    },
    getDrawLabels: function () {
        /// <returns type="bool"/>
        return true;
    },
    setDrawLabels: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getFont: function () {
        /// <returns type="Font"/>
        return new Font();
    },
    setFont: function (value) {
        /// <param name="value" type="Font"/>>
    },
    getIndicators: function () {
        /// <returns type="cfx.gauge.en"/>
        return new en();
    },
    getLabelSpacing: function () {
        /// <returns type="number"/>
        return 0;
    },
    setLabelSpacing: function (value) {
        /// <param name="value" type="number"/>>
    },
    getMainIndicator: function () {
        /// <returns type="cfx.gauge.Indicator"/>
        return new Indicator();
    },
    setMainIndicator: function (value) {
        /// <param name="value" type="cfx.gauge.Indicator"/>>
    },
    getMax: function () {
        /// <returns type="number"/>
        return 0;
    },
    setMax: function (value) {
        /// <param name="value" type="number"/>>
    },
    getMin: function () {
        /// <returns type="number"/>
        return 0;
    },
    setMin: function (value) {
        /// <param name="value" type="number"/>>
    },
    getPosition: function () {
        /// <returns type="number"/>
        return 0;
    },
    setPosition: function (value) {
        /// <param name="value" type="number"/>>
    },
    getScaleUnit: function () {
        /// <returns type="number"/>
        return 0;
    },
    setScaleUnit: function (value) {
        /// <param name="value" type="number"/>>
    },
    getSections: function () {
        /// <returns type="cfx.gauge.ei"/>
        return new ei();
    },
    getTextColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setTextColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getThickness: function () {
        /// <returns type="number"/>
        return 0;
    },
    setThickness: function (value) {
        /// <param name="value" type="number"/>>
    },
    getTickmarks: function () {
        /// <returns type="cfx.gauge.eg"/>
        return new eg();
    },
    getVisible: function () {
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <param name="value" type="bool"/>>
    }
};

var en = function() {
}
cfx.gauge.en = en;
en.prototype = {
    getCount: function () {
        /// <returns type="number"/>
        return 0;
    },
    getItem: function (index) {
        /// <param name="index" type="number"/>>
        /// <returns type="cfx.gauge.Indicator"/>
        return new Indicator();
    },
    setItem: function (index, value) {
        /// <param name="index" type="number"/>>
        /// <param name="value" type="cfx.gauge.Indicator"/>>
    },
    add: function (indicator) {
        /// <param name="indicator" type="cfx.gauge.Indicator"/>>
    },
    clear: function () {
    },
    removeAt: function (index) {
        /// <param name="index" type="number"/>>
    }
};

var Indicator = function() {
}
cfx.gauge.Indicator = Indicator;
Indicator.prototype = {
    getBorderColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setBorderColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getPosition: function () {
        /// <returns type="number"/>
        return 0;
    },
    setPosition: function (value) {
        /// <param name="value" type="number"/>>
    },
    getSize: function () {
        /// <returns type="number"/>
        return 0;
    },
    setSize: function (value) {
        /// <param name="value" type="number"/>>
    },
    getTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setTemplate: function (value) {
        /// <param name="value" type="string"/>>
    },
    getUseSectionColor: function () {
        /// <returns type="bool"/>
        return true;
    },
    setUseSectionColor: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getValue: function () {
        /// <returns type="number"/>
        return 0;
    },
    setValue: function (value) {
        /// <param name="value" type="number"/>>
    },
    getVisible: function () {
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getZOrder: function () {
        /// <returns type="cfx.gauge.ZOrder"/>
        return new ZOrder();
    },
    setZOrder: function (value) {
        /// <param name="value" type="cfx.gauge.ZOrder"/>>
    }
};

var Needle = function() {
}
cfx.gauge.Needle = Needle;
Needle.prototype = {
    getBorderColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setBorderColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getPosition: function () {
        /// <returns type="number"/>
        return 0;
    },
    setPosition: function (value) {
        /// <param name="value" type="number"/>>
    },
    getSize: function () {
        /// <returns type="number"/>
        return 0;
    },
    setSize: function (value) {
        /// <param name="value" type="number"/>>
    },
    getTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setTemplate: function (value) {
        /// <param name="value" type="string"/>>
    },
    getUseSectionColor: function () {
        /// <returns type="bool"/>
        return true;
    },
    setUseSectionColor: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getValue: function () {
        /// <returns type="number"/>
        return 0;
    },
    setValue: function (value) {
        /// <param name="value" type="number"/>>
    },
    getVisible: function () {
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getZOrder: function () {
        /// <returns type="cfx.gauge.ZOrder"/>
        return new ZOrder();
    },
    setZOrder: function (value) {
        /// <param name="value" type="cfx.gauge.ZOrder"/>>
    }
};

var Marker = function() {
}
cfx.gauge.Marker = Marker;
Marker.prototype = {
    getBorderColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setBorderColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getPosition: function () {
        /// <returns type="number"/>
        return 0;
    },
    setPosition: function (value) {
        /// <param name="value" type="number"/>>
    },
    getSize: function () {
        /// <returns type="number"/>
        return 0;
    },
    setSize: function (value) {
        /// <param name="value" type="number"/>>
    },
    getTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setTemplate: function (value) {
        /// <param name="value" type="string"/>>
    },
    getUseSectionColor: function () {
        /// <returns type="bool"/>
        return true;
    },
    setUseSectionColor: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getValue: function () {
        /// <returns type="number"/>
        return 0;
    },
    setValue: function (value) {
        /// <param name="value" type="number"/>>
    },
    getVisible: function () {
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getZOrder: function () {
        /// <returns type="cfx.gauge.ZOrder"/>
        return new ZOrder();
    },
    setZOrder: function (value) {
        /// <param name="value" type="cfx.gauge.ZOrder"/>>
    }
};

var Filler = function() {
}
cfx.gauge.Filler = Filler;
Filler.prototype = {
    getBorderColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setBorderColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getPosition: function () {
        /// <returns type="number"/>
        return 0;
    },
    setPosition: function (value) {
        /// <param name="value" type="number"/>>
    },
    getSize: function () {
        /// <returns type="number"/>
        return 0;
    },
    setSize: function (value) {
        /// <param name="value" type="number"/>>
    },
    getTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setTemplate: function (value) {
        /// <param name="value" type="string"/>>
    },
    getUseSectionColor: function () {
        /// <returns type="bool"/>
        return true;
    },
    setUseSectionColor: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getValue: function () {
        /// <returns type="number"/>
        return 0;
    },
    setValue: function (value) {
        /// <param name="value" type="number"/>>
    },
    getVisible: function () {
        /// <returns type="bool"/>
        return true;
    },
    setVisible: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getZOrder: function () {
        /// <returns type="cfx.gauge.ZOrder"/>
        return new ZOrder();
    },
    setZOrder: function (value) {
        /// <param name="value" type="cfx.gauge.ZOrder"/>>
    }
};

var eo = function() {
}
cfx.gauge.eo = eo;
eo.prototype = {
    getColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getGlare: function () {
        /// <returns type="cfx.gauge.ed"/>
        return new ed();
    },
    getInsideColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setInsideColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getInsideGap: function () {
        /// <returns type="number"/>
        return 0;
    },
    setInsideGap: function (value) {
        /// <param name="value" type="number"/>>
    },
    getShadow: function () {
        /// <returns type="cfx.gauge.ec"/>
        return new ec();
    },
    getTemplate: function () {
        /// <returns type="string"/>
        return "";
    },
    setTemplate: function (value) {
        /// <param name="value" type="string"/>>
    },
    getWidth: function () {
        /// <returns type="number"/>
        return 0;
    },
    setWidth: function (value) {
        /// <param name="value" type="number"/>>
    }
};

var Gauge = function() {
}
cfx.gauge.Gauge = Gauge;
Gauge.prototype = {
    getAnimations: function () {
        /// <returns type="cfx.gauge.eb"/>
        return new eb();
    },
    getBorder: function () {
        /// <returns type="cfx.gauge.eo"/>
        return new eo();
    },
    getDashboardBorder: function () {
        /// <returns type="cfx.gauge.eo"/>
        return new eo();
    },
    getDefaultAttributes: function () {
        /// <returns type="cfx.gauge.GaugeDefaultAttributes"/>
        return new GaugeDefaultAttributes();
    },
    getFont: function () {
        /// <returns type="Font"/>
        return new Font();
    },
    setFont: function (value) {
        /// <param name="value" type="Font"/>>
    },
    getMainIndicator: function () {
        /// <returns type="cfx.gauge.Indicator"/>
        return new Indicator();
    },
    setMainIndicator: function (value) {
        /// <param name="value" type="cfx.gauge.Indicator"/>>
    },
    getMainValue: function () {
        /// <returns type="number"/>
        return 0;
    },
    setMainValue: function (value) {
        /// <param name="value" type="number"/>>
    },
    getResizeableFont: function () {
        /// <returns type="bool"/>
        return true;
    },
    setResizeableFont: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getTextColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setTextColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getTitles: function () {
        /// <returns type="cfx.gauge.ef"/>
        return new ef();
    },
    addExtension: function (obj) {
        /// <param name="obj" type="?"/>>
    }
};

var LinearGauge = function() {
}
cfx.gauge.LinearGauge = LinearGauge;
LinearGauge.prototype = {
    getMainScale: function () {
        /// <returns type="cfx.gauge.LinearScale"/>
        return new LinearScale();
    },
    getAnimations: function () {
        /// <returns type="cfx.gauge.eb"/>
        return new eb();
    },
    getBorder: function () {
        /// <returns type="cfx.gauge.eo"/>
        return new eo();
    },
    getDashboardBorder: function () {
        /// <returns type="cfx.gauge.eo"/>
        return new eo();
    },
    getDefaultAttributes: function () {
        /// <returns type="cfx.gauge.GaugeDefaultAttributes"/>
        return new GaugeDefaultAttributes();
    },
    getFont: function () {
        /// <returns type="Font"/>
        return new Font();
    },
    setFont: function (value) {
        /// <param name="value" type="Font"/>>
    },
    getMainIndicator: function () {
        /// <returns type="cfx.gauge.Indicator"/>
        return new Indicator();
    },
    setMainIndicator: function (value) {
        /// <param name="value" type="cfx.gauge.Indicator"/>>
    },
    getMainValue: function () {
        /// <returns type="number"/>
        return 0;
    },
    setMainValue: function (value) {
        /// <param name="value" type="number"/>>
    },
    getResizeableFont: function () {
        /// <returns type="bool"/>
        return true;
    },
    setResizeableFont: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getTextColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setTextColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getTitles: function () {
        /// <returns type="cfx.gauge.ef"/>
        return new ef();
    },
    addExtension: function (obj) {
        /// <param name="obj" type="?"/>>
    }
};

var VerticalGauge = function() {
}
cfx.gauge.VerticalGauge = VerticalGauge;
VerticalGauge.prototype = {
    getMainScale: function () {
        /// <returns type="cfx.gauge.LinearScale"/>
        return new LinearScale();
    },
    getAnimations: function () {
        /// <returns type="cfx.gauge.eb"/>
        return new eb();
    },
    getBorder: function () {
        /// <returns type="cfx.gauge.eo"/>
        return new eo();
    },
    getDashboardBorder: function () {
        /// <returns type="cfx.gauge.eo"/>
        return new eo();
    },
    getDefaultAttributes: function () {
        /// <returns type="cfx.gauge.GaugeDefaultAttributes"/>
        return new GaugeDefaultAttributes();
    },
    getFont: function () {
        /// <returns type="Font"/>
        return new Font();
    },
    setFont: function (value) {
        /// <param name="value" type="Font"/>>
    },
    getMainIndicator: function () {
        /// <returns type="cfx.gauge.Indicator"/>
        return new Indicator();
    },
    setMainIndicator: function (value) {
        /// <param name="value" type="cfx.gauge.Indicator"/>>
    },
    getMainValue: function () {
        /// <returns type="number"/>
        return 0;
    },
    setMainValue: function (value) {
        /// <param name="value" type="number"/>>
    },
    getResizeableFont: function () {
        /// <returns type="bool"/>
        return true;
    },
    setResizeableFont: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getTextColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setTextColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getTitles: function () {
        /// <returns type="cfx.gauge.ef"/>
        return new ef();
    },
    addExtension: function (obj) {
        /// <param name="obj" type="?"/>>
    }
};

var HorizontalGauge = function() {
}
cfx.gauge.HorizontalGauge = HorizontalGauge;
HorizontalGauge.prototype = {
    getMainScale: function () {
        /// <returns type="cfx.gauge.LinearScale"/>
        return new LinearScale();
    },
    getAnimations: function () {
        /// <returns type="cfx.gauge.eb"/>
        return new eb();
    },
    getBorder: function () {
        /// <returns type="cfx.gauge.eo"/>
        return new eo();
    },
    getDashboardBorder: function () {
        /// <returns type="cfx.gauge.eo"/>
        return new eo();
    },
    getDefaultAttributes: function () {
        /// <returns type="cfx.gauge.GaugeDefaultAttributes"/>
        return new GaugeDefaultAttributes();
    },
    getFont: function () {
        /// <returns type="Font"/>
        return new Font();
    },
    setFont: function (value) {
        /// <param name="value" type="Font"/>>
    },
    getMainIndicator: function () {
        /// <returns type="cfx.gauge.Indicator"/>
        return new Indicator();
    },
    setMainIndicator: function (value) {
        /// <param name="value" type="cfx.gauge.Indicator"/>>
    },
    getMainValue: function () {
        /// <returns type="number"/>
        return 0;
    },
    setMainValue: function (value) {
        /// <param name="value" type="number"/>>
    },
    getResizeableFont: function () {
        /// <returns type="bool"/>
        return true;
    },
    setResizeableFont: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getTextColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setTextColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getTitles: function () {
        /// <returns type="cfx.gauge.ef"/>
        return new ef();
    },
    addExtension: function (obj) {
        /// <param name="obj" type="?"/>>
    }
};

var RadialGauge = function() {
}
cfx.gauge.RadialGauge = RadialGauge;
RadialGauge.prototype = {
    getMainScale: function () {
        /// <returns type="cfx.gauge.em"/>
        return new em();
    },
    getAnimations: function () {
        /// <returns type="cfx.gauge.eb"/>
        return new eb();
    },
    getBorder: function () {
        /// <returns type="cfx.gauge.eo"/>
        return new eo();
    },
    getDashboardBorder: function () {
        /// <returns type="cfx.gauge.eo"/>
        return new eo();
    },
    getDefaultAttributes: function () {
        /// <returns type="cfx.gauge.GaugeDefaultAttributes"/>
        return new GaugeDefaultAttributes();
    },
    getFont: function () {
        /// <returns type="Font"/>
        return new Font();
    },
    setFont: function (value) {
        /// <param name="value" type="Font"/>>
    },
    getMainIndicator: function () {
        /// <returns type="cfx.gauge.Indicator"/>
        return new Indicator();
    },
    setMainIndicator: function (value) {
        /// <param name="value" type="cfx.gauge.Indicator"/>>
    },
    getMainValue: function () {
        /// <returns type="number"/>
        return 0;
    },
    setMainValue: function (value) {
        /// <param name="value" type="number"/>>
    },
    getResizeableFont: function () {
        /// <returns type="bool"/>
        return true;
    },
    setResizeableFont: function (value) {
        /// <param name="value" type="bool"/>>
    },
    getTextColor: function () {
        /// <returns type="Object"/>
        return new ();
    },
    setTextColor: function (value) {
        /// <param name="value" type="String"/>>
    },
    getTitles: function () {
        /// <returns type="cfx.gauge.ef"/>
        return new ef();
    },
    addExtension: function (obj) {
        /// <param name="obj" type="?"/>>
    }
};
})();
