<%@ page errorPage="../errorPage.jsp" import="java.util.*, java.io.* , java.sql.* , java.text.SimpleDateFormat , javax.faces.model.SelectItem"%>
<%@ page import="java.util.* , Support.Misc"%>
<%@page import="com.implex.database.map.*"%>
<%@page import="java.math.BigDecimal"%>
<%@page contentType="text/html;charset=windows-1256"%>
<%request.setCharacterEncoding("windows-1256");%>
<html xmlns:v="urn:schemas-microsoft-com:vml"
xmlns:o="urn:schemas-microsoft-com:office:office"
xmlns:w="urn:schemas-microsoft-com:office:word"
xmlns:m="http://schemas.microsoft.com/office/2004/12/omml"
xmlns="http://www.w3.org/TR/REC-html40">
<title>طباعة جديدة للعقد</title>

<head>
<meta http-equiv=Content-Type content="text/html; charset=windows-1256">
<meta name=ProgId content=Word.Document>
<meta name=Generator content="Microsoft Word 14">
<meta name=Originator content="Microsoft Word 14">
<link rel=File-List href="contract_print_files/filelist.xml">
<link rel=Edit-Time-Data href="contract_print_files/editdata.mso">
<!--[if !mso]>
<style>
v\:* {behavior:url(#default#VML);}
o\:* {behavior:url(#default#VML);}
w\:* {behavior:url(#default#VML);}
.shape {behavior:url(#default#VML);}
</style>
<![endif]-->
<link rel=themeData href="contract_print_files/themedata.thmx">
<link rel=colorSchemeMapping href="contract_print_files/colorschememapping.xml">
<!--[if gte mso 9]><xml>
 <w:WordDocument>
  <w:Zoom>95</w:Zoom>
  <w:SpellingState>Clean</w:SpellingState>
  <w:GrammarState>Clean</w:GrammarState>
  <w:TrackMoves>false</w:TrackMoves>
  <w:TrackFormatting/>
  <w:ValidateAgainstSchemas/>
  <w:SaveIfXMLInvalid>false</w:SaveIfXMLInvalid>
  <w:IgnoreMixedContent>false</w:IgnoreMixedContent>
  <w:AlwaysShowPlaceholderText>false</w:AlwaysShowPlaceholderText>
  <w:DoNotPromoteQF/>
  <w:LidThemeOther>EN-US</w:LidThemeOther>
  <w:LidThemeAsian>X-NONE</w:LidThemeAsian>
  <w:LidThemeComplexScript>X-NONE</w:LidThemeComplexScript>
  <w:Compatibility>
   <w:BreakWrappedTables/>
   <w:SplitPgBreakAndParaMark/>
  </w:Compatibility>
  <w:BrowserLevel>MicrosoftInternetExplorer4</w:BrowserLevel>
  <m:mathPr>
   <m:mathFont m:val="Cambria Math"/>
   <m:brkBin m:val="before"/>
   <m:brkBinSub m:val="&#45;-"/>
   <m:smallFrac m:val="off"/>
   <m:dispDef/>
   <m:lMargin m:val="0"/>
   <m:rMargin m:val="0"/>
   <m:defJc m:val="centerGroup"/>
   <m:wrapIndent m:val="1440"/>
   <m:intLim m:val="subSup"/>
   <m:naryLim m:val="undOvr"/>
  </m:mathPr></w:WordDocument>
</xml><![endif]--><!--[if gte mso 9]><xml>
 <w:LatentStyles DefLockedState="false" DefUnhideWhenUsed="true"
  DefSemiHidden="true" DefQFormat="false" DefPriority="99"
  LatentStyleCount="267">
  <w:LsdException Locked="false" Priority="0" SemiHidden="false"
   UnhideWhenUsed="false" QFormat="true" Name="Normal"/>
  <w:LsdException Locked="false" Priority="9" SemiHidden="false"
   UnhideWhenUsed="false" QFormat="true" Name="heading 1"/>
  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 2"/>
  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 3"/>
  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 4"/>
  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 5"/>
  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 6"/>
  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 7"/>
  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 8"/>
  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 9"/>
  <w:LsdException Locked="false" Priority="39" Name="toc 1"/>
  <w:LsdException Locked="false" Priority="39" Name="toc 2"/>
  <w:LsdException Locked="false" Priority="39" Name="toc 3"/>
  <w:LsdException Locked="false" Priority="39" Name="toc 4"/>
  <w:LsdException Locked="false" Priority="39" Name="toc 5"/>
  <w:LsdException Locked="false" Priority="39" Name="toc 6"/>
  <w:LsdException Locked="false" Priority="39" Name="toc 7"/>
  <w:LsdException Locked="false" Priority="39" Name="toc 8"/>
  <w:LsdException Locked="false" Priority="39" Name="toc 9"/>
  <w:LsdException Locked="false" Priority="35" QFormat="true" Name="caption"/>
  <w:LsdException Locked="false" Priority="10" SemiHidden="false"
   UnhideWhenUsed="false" QFormat="true" Name="Title"/>
  <w:LsdException Locked="false" Priority="1" Name="Default Paragraph Font"/>
  <w:LsdException Locked="false" Priority="11" SemiHidden="false"
   UnhideWhenUsed="false" QFormat="true" Name="Subtitle"/>
  <w:LsdException Locked="false" Priority="22" SemiHidden="false"
   UnhideWhenUsed="false" QFormat="true" Name="Strong"/>
  <w:LsdException Locked="false" Priority="20" SemiHidden="false"
   UnhideWhenUsed="false" QFormat="true" Name="Emphasis"/>
  <w:LsdException Locked="false" Priority="59" SemiHidden="false"
   UnhideWhenUsed="false" Name="Table Grid"/>
  <w:LsdException Locked="false" UnhideWhenUsed="false" Name="Placeholder Text"/>
  <w:LsdException Locked="false" Priority="1" SemiHidden="false"
   UnhideWhenUsed="false" QFormat="true" Name="No Spacing"/>
  <w:LsdException Locked="false" Priority="60" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light Shading"/>
  <w:LsdException Locked="false" Priority="61" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light List"/>
  <w:LsdException Locked="false" Priority="62" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light Grid"/>
  <w:LsdException Locked="false" Priority="63" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Shading 1"/>
  <w:LsdException Locked="false" Priority="64" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Shading 2"/>
  <w:LsdException Locked="false" Priority="65" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium List 1"/>
  <w:LsdException Locked="false" Priority="66" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium List 2"/>
  <w:LsdException Locked="false" Priority="67" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 1"/>
  <w:LsdException Locked="false" Priority="68" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 2"/>
  <w:LsdException Locked="false" Priority="69" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 3"/>
  <w:LsdException Locked="false" Priority="70" SemiHidden="false"
   UnhideWhenUsed="false" Name="Dark List"/>
  <w:LsdException Locked="false" Priority="71" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful Shading"/>
  <w:LsdException Locked="false" Priority="72" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful List"/>
  <w:LsdException Locked="false" Priority="73" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful Grid"/>
  <w:LsdException Locked="false" Priority="60" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light Shading Accent 1"/>
  <w:LsdException Locked="false" Priority="61" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light List Accent 1"/>
  <w:LsdException Locked="false" Priority="62" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light Grid Accent 1"/>
  <w:LsdException Locked="false" Priority="63" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Shading 1 Accent 1"/>
  <w:LsdException Locked="false" Priority="64" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Shading 2 Accent 1"/>
  <w:LsdException Locked="false" Priority="65" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium List 1 Accent 1"/>
  <w:LsdException Locked="false" UnhideWhenUsed="false" Name="Revision"/>
  <w:LsdException Locked="false" Priority="34" SemiHidden="false"
   UnhideWhenUsed="false" QFormat="true" Name="List Paragraph"/>
  <w:LsdException Locked="false" Priority="29" SemiHidden="false"
   UnhideWhenUsed="false" QFormat="true" Name="Quote"/>
  <w:LsdException Locked="false" Priority="30" SemiHidden="false"
   UnhideWhenUsed="false" QFormat="true" Name="Intense Quote"/>
  <w:LsdException Locked="false" Priority="66" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium List 2 Accent 1"/>
  <w:LsdException Locked="false" Priority="67" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 1 Accent 1"/>
  <w:LsdException Locked="false" Priority="68" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 2 Accent 1"/>
  <w:LsdException Locked="false" Priority="69" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 3 Accent 1"/>
  <w:LsdException Locked="false" Priority="70" SemiHidden="false"
   UnhideWhenUsed="false" Name="Dark List Accent 1"/>
  <w:LsdException Locked="false" Priority="71" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful Shading Accent 1"/>
  <w:LsdException Locked="false" Priority="72" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful List Accent 1"/>
  <w:LsdException Locked="false" Priority="73" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful Grid Accent 1"/>
  <w:LsdException Locked="false" Priority="60" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light Shading Accent 2"/>
  <w:LsdException Locked="false" Priority="61" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light List Accent 2"/>
  <w:LsdException Locked="false" Priority="62" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light Grid Accent 2"/>
  <w:LsdException Locked="false" Priority="63" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Shading 1 Accent 2"/>
  <w:LsdException Locked="false" Priority="64" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Shading 2 Accent 2"/>
  <w:LsdException Locked="false" Priority="65" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium List 1 Accent 2"/>
  <w:LsdException Locked="false" Priority="66" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium List 2 Accent 2"/>
  <w:LsdException Locked="false" Priority="67" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 1 Accent 2"/>
  <w:LsdException Locked="false" Priority="68" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 2 Accent 2"/>
  <w:LsdException Locked="false" Priority="69" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 3 Accent 2"/>
  <w:LsdException Locked="false" Priority="70" SemiHidden="false"
   UnhideWhenUsed="false" Name="Dark List Accent 2"/>
  <w:LsdException Locked="false" Priority="71" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful Shading Accent 2"/>
  <w:LsdException Locked="false" Priority="72" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful List Accent 2"/>
  <w:LsdException Locked="false" Priority="73" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful Grid Accent 2"/>
  <w:LsdException Locked="false" Priority="60" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light Shading Accent 3"/>
  <w:LsdException Locked="false" Priority="61" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light List Accent 3"/>
  <w:LsdException Locked="false" Priority="62" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light Grid Accent 3"/>
  <w:LsdException Locked="false" Priority="63" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Shading 1 Accent 3"/>
  <w:LsdException Locked="false" Priority="64" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Shading 2 Accent 3"/>
  <w:LsdException Locked="false" Priority="65" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium List 1 Accent 3"/>
  <w:LsdException Locked="false" Priority="66" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium List 2 Accent 3"/>
  <w:LsdException Locked="false" Priority="67" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 1 Accent 3"/>
  <w:LsdException Locked="false" Priority="68" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 2 Accent 3"/>
  <w:LsdException Locked="false" Priority="69" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 3 Accent 3"/>
  <w:LsdException Locked="false" Priority="70" SemiHidden="false"
   UnhideWhenUsed="false" Name="Dark List Accent 3"/>
  <w:LsdException Locked="false" Priority="71" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful Shading Accent 3"/>
  <w:LsdException Locked="false" Priority="72" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful List Accent 3"/>
  <w:LsdException Locked="false" Priority="73" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful Grid Accent 3"/>
  <w:LsdException Locked="false" Priority="60" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light Shading Accent 4"/>
  <w:LsdException Locked="false" Priority="61" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light List Accent 4"/>
  <w:LsdException Locked="false" Priority="62" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light Grid Accent 4"/>
  <w:LsdException Locked="false" Priority="63" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Shading 1 Accent 4"/>
  <w:LsdException Locked="false" Priority="64" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Shading 2 Accent 4"/>
  <w:LsdException Locked="false" Priority="65" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium List 1 Accent 4"/>
  <w:LsdException Locked="false" Priority="66" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium List 2 Accent 4"/>
  <w:LsdException Locked="false" Priority="67" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 1 Accent 4"/>
  <w:LsdException Locked="false" Priority="68" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 2 Accent 4"/>
  <w:LsdException Locked="false" Priority="69" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 3 Accent 4"/>
  <w:LsdException Locked="false" Priority="70" SemiHidden="false"
   UnhideWhenUsed="false" Name="Dark List Accent 4"/>
  <w:LsdException Locked="false" Priority="71" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful Shading Accent 4"/>
  <w:LsdException Locked="false" Priority="72" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful List Accent 4"/>
  <w:LsdException Locked="false" Priority="73" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful Grid Accent 4"/>
  <w:LsdException Locked="false" Priority="60" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light Shading Accent 5"/>
  <w:LsdException Locked="false" Priority="61" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light List Accent 5"/>
  <w:LsdException Locked="false" Priority="62" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light Grid Accent 5"/>
  <w:LsdException Locked="false" Priority="63" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Shading 1 Accent 5"/>
  <w:LsdException Locked="false" Priority="64" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Shading 2 Accent 5"/>
  <w:LsdException Locked="false" Priority="65" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium List 1 Accent 5"/>
  <w:LsdException Locked="false" Priority="66" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium List 2 Accent 5"/>
  <w:LsdException Locked="false" Priority="67" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 1 Accent 5"/>
  <w:LsdException Locked="false" Priority="68" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 2 Accent 5"/>
  <w:LsdException Locked="false" Priority="69" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 3 Accent 5"/>
  <w:LsdException Locked="false" Priority="70" SemiHidden="false"
   UnhideWhenUsed="false" Name="Dark List Accent 5"/>
  <w:LsdException Locked="false" Priority="71" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful Shading Accent 5"/>
  <w:LsdException Locked="false" Priority="72" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful List Accent 5"/>
  <w:LsdException Locked="false" Priority="73" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful Grid Accent 5"/>
  <w:LsdException Locked="false" Priority="60" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light Shading Accent 6"/>
  <w:LsdException Locked="false" Priority="61" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light List Accent 6"/>
  <w:LsdException Locked="false" Priority="62" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light Grid Accent 6"/>
  <w:LsdException Locked="false" Priority="63" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Shading 1 Accent 6"/>
  <w:LsdException Locked="false" Priority="64" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Shading 2 Accent 6"/>
  <w:LsdException Locked="false" Priority="65" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium List 1 Accent 6"/>
  <w:LsdException Locked="false" Priority="66" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium List 2 Accent 6"/>
  <w:LsdException Locked="false" Priority="67" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 1 Accent 6"/>
  <w:LsdException Locked="false" Priority="68" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 2 Accent 6"/>
  <w:LsdException Locked="false" Priority="69" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 3 Accent 6"/>
  <w:LsdException Locked="false" Priority="70" SemiHidden="false"
   UnhideWhenUsed="false" Name="Dark List Accent 6"/>
  <w:LsdException Locked="false" Priority="71" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful Shading Accent 6"/>
  <w:LsdException Locked="false" Priority="72" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful List Accent 6"/>
  <w:LsdException Locked="false" Priority="73" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful Grid Accent 6"/>
  <w:LsdException Locked="false" Priority="19" SemiHidden="false"
   UnhideWhenUsed="false" QFormat="true" Name="Subtle Emphasis"/>
  <w:LsdException Locked="false" Priority="21" SemiHidden="false"
   UnhideWhenUsed="false" QFormat="true" Name="Intense Emphasis"/>
  <w:LsdException Locked="false" Priority="31" SemiHidden="false"
   UnhideWhenUsed="false" QFormat="true" Name="Subtle Reference"/>
  <w:LsdException Locked="false" Priority="32" SemiHidden="false"
   UnhideWhenUsed="false" QFormat="true" Name="Intense Reference"/>
  <w:LsdException Locked="false" Priority="33" SemiHidden="false"
   UnhideWhenUsed="false" QFormat="true" Name="Book Title"/>
  <w:LsdException Locked="false" Priority="37" Name="Bibliography"/>
  <w:LsdException Locked="false" Priority="39" QFormat="true" Name="TOC Heading"/>
 </w:LatentStyles>
</xml><![endif]-->
<style>
<!--
 /* Font Definitions */
 @font-face
	{font-family:Calibri;
	panose-1:2 15 5 2 2 2 4 3 2 4;
	mso-font-charset:0;
	mso-generic-font-family:swiss;
	mso-font-pitch:variable;
	mso-font-signature:-536870145 1073786111 1 0 415 0;}
@font-face
	{font-family:Tahoma;
	panose-1:2 11 6 4 3 5 4 4 2 4;
	mso-font-charset:0;
	mso-generic-font-family:swiss;
	mso-font-pitch:variable;
	mso-font-signature:-520081665 -1073717157 41 0 66047 0;}
@font-face
	{font-family:"Segoe UI";
	panose-1:2 11 5 2 4 2 4 2 2 3;
	mso-font-charset:0;
	mso-generic-font-family:swiss;
	mso-font-pitch:variable;
	mso-font-signature:-520084737 -1073683329 41 0 479 0;}
@font-face
	{font-family:"Copperplate Gothic Bold";
	panose-1:2 14 7 5 2 2 6 2 4 4;
	mso-font-charset:0;
	mso-generic-font-family:swiss;
	mso-font-pitch:variable;
	mso-font-signature:3 0 0 0 1 0;}
 /* Style Definitions */
 p.MsoNormal, li.MsoNormal, div.MsoNormal
	{mso-style-unhide:no;
	mso-style-qformat:yes;
	mso-style-parent:"";
	margin-top:0in;
	margin-right:0in;
	margin-bottom:10.0pt;
	margin-left:0in;
	line-height:115%;
	mso-pagination:widow-orphan;
	font-size:11.0pt;
	font-family:"Calibri","sans-serif";
	mso-ascii-font-family:Calibri;
	mso-ascii-theme-font:minor-latin;
	mso-fareast-font-family:Calibri;
	mso-fareast-theme-font:minor-latin;
	mso-hansi-font-family:Calibri;
	mso-hansi-theme-font:minor-latin;
	mso-bidi-font-family:"Times New Roman";
	mso-bidi-theme-font:minor-bidi;}
p.MsoAcetate, li.MsoAcetate, div.MsoAcetate
	{mso-style-noshow:yes;
	mso-style-priority:99;
	mso-style-link:"Balloon Text Char";
	margin:0in;
	margin-bottom:.0001pt;
	mso-pagination:widow-orphan;
	font-size:8.0pt;
	font-family:"Tahoma","sans-serif";
	mso-fareast-font-family:Calibri;
	mso-fareast-theme-font:minor-latin;}
span.BalloonTextChar
	{mso-style-name:"Balloon Text Char";
	mso-style-noshow:yes;
	mso-style-priority:99;
	mso-style-unhide:no;
	mso-style-locked:yes;
	mso-style-link:"Balloon Text";
	mso-ansi-font-size:8.0pt;
	mso-bidi-font-size:8.0pt;
	font-family:"Tahoma","sans-serif";
	mso-ascii-font-family:Tahoma;
	mso-hansi-font-family:Tahoma;
	mso-bidi-font-family:Tahoma;}
p.Style, li.Style, div.Style
	{mso-style-name:Style;
	mso-style-unhide:no;
	mso-style-parent:"";
	margin:0in;
	margin-bottom:.0001pt;
	mso-pagination:none;
	mso-layout-grid-align:none;
	text-autospace:none;
	font-size:12.0pt;
	font-family:"Arial","sans-serif";
	mso-fareast-font-family:"Times New Roman";
	mso-fareast-theme-font:minor-fareast;}
span.SpellE
	{mso-style-name:"";
	mso-spl-e:yes;}
.MsoChpDefault
	{mso-style-type:export-only;
	mso-default-props:yes;
	font-size:10.0pt;
	mso-ansi-font-size:10.0pt;
	mso-bidi-font-size:10.0pt;
	font-family:"Calibri","sans-serif";
	mso-ascii-font-family:Calibri;
	mso-ascii-theme-font:minor-latin;
	mso-fareast-font-family:Calibri;
	mso-fareast-theme-font:minor-latin;
	mso-hansi-font-family:Calibri;
	mso-hansi-theme-font:minor-latin;
	mso-bidi-font-family:"Times New Roman";
	mso-bidi-theme-font:minor-bidi;}
@page WordSection1
	{size:8.5in 11.0in;
	margin:13.5pt .5in 22.5pt 49.5pt;
	mso-header-margin:.5in;
	mso-footer-margin:.5in;
	mso-paper-source:0;}
div.WordSection1
	{page:WordSection1;}
-->
</style>
<!--[if gte mso 10]>
<style>
 /* Style Definitions */
 table.MsoNormalTable
	{mso-style-name:"Table Normal";
	mso-tstyle-rowband-size:0;
	mso-tstyle-colband-size:0;
	mso-style-noshow:yes;
	mso-style-priority:99;
	mso-style-parent:"";
	mso-padding-alt:0in 5.4pt 0in 5.4pt;
	mso-para-margin:0in;
	mso-para-margin-bottom:.0001pt;
	mso-pagination:widow-orphan;
	font-size:10.0pt;
	font-family:"Calibri","sans-serif";
	mso-ascii-font-family:Calibri;
	mso-ascii-theme-font:minor-latin;
	mso-hansi-font-family:Calibri;
	mso-hansi-theme-font:minor-latin;}
</style>
<![endif]--><!--[if gte mso 9]><xml>
 <o:shapedefaults v:ext="edit" spidmax="1026"/>
</xml><![endif]--><!--[if gte mso 9]><xml>
 <o:shapelayout v:ext="edit">
  <o:idmap v:ext="edit" data="1"/>
 </o:shapelayout></xml><![endif]-->
</head>

<body lang=EN-US style='tab-interval:.5in'>

<div class=WordSection1>

<div align=center>
<%
  java.sql.Connection  con , repCon  =null;
	  con = (java.sql.Connection)session.getAttribute("con");

	  repCon = (java.sql.Connection)session.getAttribute("repCon");
	  //String loggedUser = Misc.getConnectionUserName(con); 
	  java.sql.Statement stmt = con.createStatement();  
	  String uniqueColumnName = "contRowIdValue";
	  String rowIdValue =  request.getParameter("contRowIdValue");
	  Vector pramNames = new Vector() ; pramNames.add("$$"+uniqueColumnName) ;  
	  Vector pramValues = new Vector() ; pramValues.add(rowIdValue) ;
	  Support.SqlReader sqlReader = new Support.SqlReader(repCon,"LU_QUERIES" , "QUERY_BODY",  "26158", pramNames , pramValues , false);
	  String queryStatment = sqlReader.getQueryStatments()[0] ;  
	  
  
  	//-------------------------------------------
  	java.sql.ResultSet rs= null;
  	try{ rs =  stmt.executeQuery(queryStatment);  	}
  	catch (Exception  e) {throw new Exception("Unable to execute : \n" + queryStatment + "\n Due to : \n" + e.getMessage());}
	rs.next();
 	String company = rs.getString ("COMPANY"); 	
 	String branch = rs.getString("BRANCH") ;
 	String branch_id = rs.getString("BRANCH_ID") ;
//------------Customer Info -----------	
	String custName = rs.getString("CUST_NAME") ; 	String idNo = rs.getString("ID_NO") ; 	String idType = rs.getString("ID_TYPE") ; 	
	String idDateExpire = rs.getString("ID_DATE_EXPIRE") ; String idSource = rs.getString("ID_SOURCE") ; 	
	String licNo = rs.getString("LIC_NO") ;  String licDateExpire = rs.getString("LIC_DATE_EXPIRE") ;
	String licSource = rs.getString("LIC_SOURCE") ; String custOfficeAddress = rs.getString("CUST_OFFICE_ADDRESS") ;  String office_tel = rs.getString("OFFICE_TEL") ;
	String cust_address = rs.getString("CUST_ADDRESS") ; String extraDriverName = rs.getString("EXTRA_DRIVER_NAME") ; String  extra_driver_lic_id = rs.getString("EXTRA_DRIVER_LIC_ID") ;
	String extra_driver_lic_edate = rs.getString("EXTRA_DRIVER_LIC_EDATE") ;
	String custMobile =   rs.getString("CUST_MOBILE") ; 
	String custNo = rs.getString("CUST_NO") ; 
	String custNationality = rs.getString("CUST_NATIONALITY") ; 
//----------- Car Info -----------------
	String carCode = rs.getString ("CAR_CODE"); String carType = rs.getString("CAR_TYPE"); String carYear = rs.getString("CAR_YEAR");
	String carColor = rs.getString("CAR_COLOR") ; String carDailyRate = rs.getString("CAR_DAILY_RATE") ;  String carKmDay = rs.getString("CAR_KM_DAY");
	String carExtraHourRate = rs.getString("CAR_EXTRA_HOUR_RATE") ; 
	String carExtraKmRate = rs.getString("CAR_EXTRA_KM_RATE") ;
	String carFmkmreading = rs.getString("CAR_FM_KM_READING"); 
	String carRrturnKM = rs.getString("CAR_RETURN_KM");
	String discount = rs.getString("DISCOUNT");  
	
	
	
//-----------Contract Info -------------
	String serialNo = rs.getString("SERIALNO") ; String status = rs.getString("STATUS"); 
	String usedKM = rs.getString("USED_KM") ; BigDecimal totalPayments = rs.getBigDecimal("total_Payments"); BigDecimal total_penalty = rs.getBigDecimal("total_penalty");
	String extra_hours = rs.getString("extra_hours") ; String extraHoursVal = rs.getString("EXTRA_HOURS_VAL") ;	
	String extraKm = rs.getString("EXTRA_KM"); String extra_km_val = rs.getString("EXTRA_KM_VAL");
	String interval = rs.getString("INTERVAL") ; String damgeValue = rs.getString("DAMGEVALUE") ;   
	String carincome = rs.getString("CARINCOME") ; 
	String conOutDate = rs.getString("out_date") ; String conOutTime = rs.getString("out_time") ;
	String conInDate = rs.getString("in_date") ; String conInTime = rs.getString("in_time") ;
	String conDaysValue = rs.getString("con_days_value");	 
	String contExpiryDate = rs.getString("cont_expiry_date");
	String contNeedToCllect = rs.getString("need_to_cllect") ;
	String contNeedToRefund = rs.getString("need_to_refund") ; 
 

	stmt.close();
    rs.close();
%>

<table class=MsoNormalTable dir=rtl border=1 cellspacing=0 cellpadding=0
 width=660 style='border-collapse:collapse;mso-table-layout-alt:fixed;
 border:none;mso-border-alt:solid windowtext .5pt;mso-yfti-tbllook:1184;
 mso-padding-alt:0in .05in 0in .05in;mso-table-dir:bidi;mso-border-insideh:
 .5pt solid windowtext;mso-border-insidev:.5pt solid windowtext'>
 <tr style='mso-yfti-irow:0;mso-yfti-firstrow:yes;height:28.5pt'>
  <td width=198 colspan=12 rowspan=2 valign=top style='width:148.7pt;
  border:none;padding:0in .05in 0in .05in;height:28.5pt'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><span
  lang=AR-SA style='font-size:16.0pt;font-family:"Segoe UI","sans-serif"'>شركة
  المريشد </span><span dir=LTR></span><span lang=AR-SA dir=LTR
  style='font-size:16.0pt;font-family:"Segoe UI","sans-serif"'><span dir=LTR></span><span
  style='mso-spacerun:yes'> </span></span><span lang=AR-SA style='font-size:
  16.0pt;font-family:"Segoe UI","sans-serif"'><o:p></o:p></span></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><span
  lang=AR-SA style='font-size:10.0pt;mso-ansi-font-size:14.0pt;font-family:
  "Segoe UI","sans-serif"'>لتأجير السيارات </span><span dir=LTR></span><span
  dir=LTR style='font-size:14.0pt;mso-bidi-font-size:10.0pt;font-family:"Segoe UI","sans-serif"'><span
  dir=LTR></span><span style='mso-spacerun:yes'>  </span></span><span
  lang=AR-SA style='font-family:"Times New Roman","serif";mso-ascii-font-family:
  Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:Calibri;
  mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>رقم العضوية 20889<o:p></o:p></span></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>شركة ذات مسئولية محدودة </span><span dir=LTR></span><span
  lang=AR-SA dir=LTR style='font-size:8.0pt'><span dir=LTR></span><span
  style='mso-spacerun:yes'> </span></span><span lang=AR-SA style='font-size:
  8.0pt;font-family:"Times New Roman","serif";mso-ascii-font-family:Calibri;
  mso-ascii-theme-font:minor-latin;mso-hansi-font-family:Calibri;mso-hansi-theme-font:
  minor-latin;mso-bidi-font-family:"Times New Roman";mso-bidi-theme-font:minor-bidi'>س
  ت 1010305720</span><span dir=LTR style='font-size:14.0pt;font-family:"Segoe UI","sans-serif"'><o:p></o:p></span></p>
  </td>
  <td width=91 colspan=5 rowspan=2 valign=top style='width:68.25pt;border:none;
  padding:0in .05in 0in .05in;height:28.5pt'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><span
  dir=LTR style='font-size:16.0pt;font-family:"Segoe UI","sans-serif";
  mso-no-proof:yes'><!--[if gte vml 1]><v:shapetype id="_x0000_t75"
   coordsize="21600,21600" o:spt="75" o:preferrelative="t" path="m@4@5l@4@11@9@11@9@5xe"
   filled="f" stroked="f">
   <v:stroke joinstyle="miter"/>
   <v:formulas>
    <v:f eqn="if lineDrawn pixelLineWidth 0"/>
    <v:f eqn="sum @0 1 0"/>
    <v:f eqn="sum 0 0 @1"/>
    <v:f eqn="prod @2 1 2"/>
    <v:f eqn="prod @3 21600 pixelWidth"/>
    <v:f eqn="prod @3 21600 pixelHeight"/>
    <v:f eqn="sum @0 0 1"/>
    <v:f eqn="prod @6 1 2"/>
    <v:f eqn="prod @7 21600 pixelWidth"/>
    <v:f eqn="sum @8 21600 0"/>
    <v:f eqn="prod @7 21600 pixelHeight"/>
    <v:f eqn="sum @10 21600 0"/>
   </v:formulas>
   <v:path o:extrusionok="f" gradientshapeok="t" o:connecttype="rect"/>
   <o:lock v:ext="edit" aspectratio="t"/>
  </v:shapetype><v:shape id="Picture_x0020_1" o:spid="_x0000_i1025" type="#_x0000_t75"
   style='width:57pt;height:57pt;visibility:visible;mso-wrap-style:square'>
   <v:imagedata src="contract_print_files/image001.jpg" o:title=""/>
  </v:shape><![endif]--><![if !vml]><img width=76 height=76
  src="contract_print_files/image002.jpg" v:shapes="Picture_x0020_1"><![endif]></span></p>
  </td>
  <td width=201 colspan=4 rowspan=2 valign=top style='width:150.9pt;border:
  none;border-left:solid windowtext 1.0pt;mso-border-left-alt:solid windowtext .5pt;
  padding:0in .05in 0in .05in;height:28.5pt'>
  <p class=MsoNormal dir=LTR style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:justify;line-height:normal'><span class=SpellE><b><span
  style='font-size:14.0pt;font-family:"Copperplate Gothic Bold","sans-serif";
  mso-bidi-font-family:"Segoe UI"'>Muraishid</span></b></span><b><span
  style='font-size:14.0pt;font-family:"Copperplate Gothic Bold","sans-serif";
  mso-bidi-font-family:"Segoe UI"'> Co.</span></b><b><span lang=AR-SA dir=RTL
  style='font-size:14.0pt;font-family:"Segoe UI","sans-serif";mso-ascii-font-family:
  "Copperplate Gothic Bold";mso-hansi-font-family:"Copperplate Gothic Bold"'><o:p></o:p></span></b></p>
  <p class=MsoNormal dir=LTR style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:justify;line-height:normal'><b><span lang=AR-SA dir=RTL
  style='font-size:10.0pt;mso-ansi-font-size:14.0pt;font-family:"Segoe UI","sans-serif";
  mso-ascii-font-family:"Copperplate Gothic Bold";mso-hansi-font-family:"Copperplate Gothic Bold"'><span
  style='mso-spacerun:yes'> </span></span></b><b><span style='mso-bidi-font-size:
  14.0pt;font-family:"Copperplate Gothic Bold","sans-serif";mso-bidi-font-family:
  "Segoe UI"'>For<span style='mso-spacerun:yes'>  </span>Car Rental</span></b><b><span
  style='font-size:14.0pt;font-family:"Copperplate Gothic Bold","sans-serif";
  mso-bidi-font-family:"Segoe UI"'> </span></b><b><span lang=AR-SA dir=RTL
  style='font-size:14.0pt;font-family:"Segoe UI","sans-serif";mso-ascii-font-family:
  "Copperplate Gothic Bold";mso-hansi-font-family:"Copperplate Gothic Bold"'><o:p></o:p></span></b></p>
  <p class=MsoNormal dir=LTR style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:justify;line-height:normal'><span style='font-size:9.0pt'><o:p>&nbsp;</o:p></span></p>
  </td>
  <td width=96 valign=center style='width:72.3pt;border:solid windowtext 1.0pt;
  border-right:none;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in;height:28.5pt'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>عقد إجار رقم<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Contract NO.<o:p></o:p></span></b></p>
  </td>
  <td width="73" valign="center" style="width:54.85pt;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in">
  <p class="MsoNormal" dir="RTL" style="margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed"><b><span dir="RTL" style="font-size:9.0pt"><o:p><%=serialNo%> </o:p></span></b></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:1;height:19.75pt'>
  <td width=96 style='width:72.3pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in;height:19.75pt'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>الفرع</span></b><b><span dir=LTR
  style='font-size:9.0pt'><o:p></o:p></span></b></p>
  </td>
  <td width=73 valign=center style='width:54.85pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in;height:19.75pt'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><span
  dir=LTR style='font-size:8.0pt'><o:p><%=branch %></o:p></span></p>
  </td>
  
 </tr>
 <tr style='mso-yfti-irow:2;height:27.6pt'>
  <td width=490 colspan=21 style='width:367.85pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-left-alt:solid windowtext .5pt;mso-border-bottom-alt:solid windowtext .5pt;
  padding:0in .05in 0in .05in;height:27.6pt'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><span
  lang=AR-SA style='font-size:9.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>الرقم الموحد 920001913<span
  style='mso-spacerun:yes'>      </span></span><b><span lang=AR-SA
  style='font-size:8.0pt;font-family:"Times New Roman","serif";mso-ascii-font-family:
  Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:Calibri;
  mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>الى من يهمه الامر/ فى حالة إنتهاء مدة العقد
  الرجاء التحفظ غلى السيارة و المستأجر و إبلاغنا</span></b><span dir=LTR
  style='font-size:9.0pt'><o:p></o:p></span></p>
  </td>
  <td width=96 valign=top style='width:72.3pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in;height:27.6pt'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>رقم و نوع العضوية<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=LTR style='margin-bottom:0in;margin-bottom:.0001pt;
  line-height:normal'><b><span style='font-size:8.0pt'>Membership No.<o:p></o:p></span></b></p>
  </td>
  <td width=73 valign=top style='width:54.85pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in;height:27.6pt'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><span
  dir=LTR style='font-size:9.0pt'><o:p><%=custNo %></o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:3;height:22.0pt'>
  <td width=95 colspan=4 valign=top style='width:71.35pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0in .05in 0in .05in;height:22.0pt'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>اسم المستأجر<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'><span style='mso-spacerun:yes'>  </span></span></b><b><span
  dir=LTR style='font-size:8.0pt'>Renter’s Name<o:p></o:p></span></b></p>
  </td>
  <td width=233 colspan=15 style='width:174.55pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in;height:22.0pt'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><span
  dir=LTR style='font-size:10.0pt;mso-bidi-font-family:"Times New Roman"'><%=custName%><o:p></o:p></span></p>
  </td>
  <td width=83 valign=top style='width:62.35pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in;height:22.0pt'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>تاريخ إنتهاء العقد<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=LTR style='margin-bottom:0in;margin-bottom:.0001pt;
  line-height:normal'><b><span style='font-size:6.0pt'>Cont. Exp. Date<o:p></o:p></span></b></p>
  </td>
  <td width=79 valign=top style='width:59.6pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in;height:22.0pt'>
  <p class=MsoNormal align=right dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:left;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:7.0pt'><o:p><%=contExpiryDate %></o:p></span></b></p>
  </td>
  <td width=96 valign=top style='width:72.3pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in;height:22.0pt'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>منطقة الاستخدام<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=LTR style='margin-bottom:0in;margin-bottom:.0001pt;
  line-height:normal'><b><span style='font-size:8.0pt'>Usage Geo. Area <o:p></o:p></span></b></p>
  </td>
  <td width=73 valign=top style='width:54.85pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in;height:22.0pt'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><span
  dir=LTR style='font-size:9.0pt'><o:p>&nbsp;</o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:4'>
  <td width=95 colspan=4 valign=center style='width:71.35pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:8.0pt'>نوع الهوية : <%=idType %><o:p></o:p></span></b></p>
  </td>
  <td width=69 colspan=4 valign=top style='width:51.45pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=LTR style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal'><b><span lang=AR-SA dir=RTL
  style='font-size:8.0pt;font-family:"Times New Roman","serif";mso-ascii-font-family:
  Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:Calibri;
  mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>تاريخ الانتهاء<o:p></o:p></span></b></p>
  <p class=MsoNormal align=center dir=LTR style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal'><b><span style='font-size:8.0pt'>Exp.
  Date<o:p></o:p></span></b></p>
  </td>
  <td width=92 colspan=7 valign=top style='width:69.15pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>المصدر<o:p></o:p></span></b></p>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Issued at<o:p></o:p></span></b></p>
  </td>
  <td width=72 colspan=4 valign=top style='width:53.95pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>الجنسية<o:p></o:p></span></b></p>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Nationality<o:p></o:p></span></b></p>
  </td>
  <td width=83 valign=top style='width:62.35pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>توع السيارة<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Car Type <o:p></o:p></span></b></p>
  </td>
  <td width=79 valign=center style='width:59.6pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=right dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:7.0pt'><o:p><%=carType%></o:p></span></b></p>
  </td>
  <td width=96 valign=top style='width:72.3pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>الايجار اليومى<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Daily Rate<o:p></o:p></span></b></p>
  </td>
  <td width=73 valign=center style='width:54.85pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:9.0pt'><o:p><%=carDailyRate %> ريال</o:p></span></b></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:5'>
  <td width=95 colspan=4 valign=center style='width:71.35pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:8.0pt'><%=idNo%></span><o:p></o:p></span></b></p>
  </td>
  <td width=69 colspan=4 valign=center style='width:51.45pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal'><b><span style='font-size:8.0pt'><%=idDateExpire %><o:p></o:p></span></b></p>
  </td>
  <td width=92 colspan=7 valign=center style='width:69.15pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:8.0pt'><%=idSource%><o:p></o:p></span></b></p>
  </td>
  <td width=72 colspan=4 valign=center style='width:53.95pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:9.0pt'><o:p><%=custNationality%></o:p></span></b></p>
  </td>
  <td width=83 valign=top style='width:62.35pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>رقم اللوحة<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Plate No.<o:p></o:p></span></b></p>
  </td>
  <td width=79 valign=center style='width:59.6pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=right dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:9.0pt'><o:p><%=carCode%></o:p></span></b></p>
  </td>
  <td width=96 valign=top style='width:72.3pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>كم مسموح يوميا<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>KM Allowed Daily<o:p></o:p></span></b></p>
  </td>
  <td width=73 valign=center style='width:54.85pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:9.0pt'><o:p><%=carKmDay %> كم </o:p></span></b></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:6'>
  <td width=95 colspan=4 valign=top style='width:71.35pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=right dir=LTR style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:right;line-height:normal'><b><span lang=AR-SA dir=RTL
  style='font-size:8.0pt;font-family:"Times New Roman","serif";mso-ascii-font-family:
  Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:Calibri;
  mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>رقم الرخصة </span></b><b><span
  style='font-size:8.0pt'><o:p></o:p></span></b></p>
  </td>
  <td width=69 colspan=4 valign=top style='width:51.45pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=LTR style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal'><b><span lang=AR-SA dir=RTL
  style='font-size:8.0pt;font-family:"Times New Roman","serif";mso-ascii-font-family:
  Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:Calibri;
  mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>تاريخ الانتهاء<o:p></o:p></span></b></p>
  <p class=MsoNormal align=center dir=LTR style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal'><b><span style='font-size:8.0pt'>Exp.
  Date<o:p></o:p></span></b></p>
  </td>
  <td width=92 colspan=7 valign=top style='width:69.15pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>المصدر<o:p></o:p></span></b></p>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Issued at<o:p></o:p></span></b></p>
  </td>
  <td width=72 colspan=4 valign=top style='width:53.95pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>الجنسية<o:p></o:p></span></b></p>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Nationality<o:p></o:p></span></b></p>
  </td>
  <td width=83 valign=top style='width:62.35pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>اللون<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Color<o:p></o:p></span></b></p>
  </td>
  <td width=79 valign=center style='width:59.6pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=right dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:9.0pt'><o:p><%=carColor %></o:p></span></b></p>
  </td>
  <td width=96 valign=top style='width:72.3pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>سعر الساعة الزائدة<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Extra Hour Rate<o:p></o:p></span></b></p>
  </td>
  <td width=73 valign=center style='width:54.85pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:9.0pt'><o:p><%=carExtraHourRate %> ريال</o:p></span></b></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:7'>
  <td width=95 colspan=4 valign=center style='width:71.35pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:9.0pt'><o:p><%=licNo %></o:p></span></b></p>
  </td>
  <td width=69 colspan=4 valign=center style='width:51.45pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:9.0pt'><%=licDateExpire %><o:p></o:p></span></b></p>
  </td>
  <td width=92 colspan=7 valign=center style='width:69.15pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:9.0pt'><%=licSource %><o:p></o:p></span></b></p>
  </td>
  <td width=72 colspan=4 valign=center style='width:53.95pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:9.0pt'><o:p></o:p></span></b></p>
  </td>
  <td width=83 valign=top style='width:62.35pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>موديل السيارة<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Car Model<o:p></o:p></span></b></p>
  </td>
  <td width=79 valign=center style='width:59.6pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=right dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:9.0pt'><o:p><%=carYear %></o:p></span></b></p>
  </td>
  <td width=96 valign=top style='width:72.3pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>سعر الكيلو الزائد<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Extra KM Rate<o:p></o:p></span></b></p>
  </td>
  <td width=73 valign=center style='width:54.85pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:9.0pt'><o:p><%=carExtraKmRate %> هلله </o:p></span></b></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:8'>
  <td width=95 colspan=4 valign=top style='width:71.35pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>السائق الاضافى </span></b><b><span dir=LTR
  style='font-size:8.0pt'><o:p></o:p></span></b></p>
  </td>
  <td width=102 colspan=7 valign=center style='width:76.25pt;border:none;
  border-bottom:solid windowtext 1.0pt;mso-border-top-alt:solid windowtext .5pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-bottom-alt:solid windowtext .5pt;
  padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:9.0pt'><%=extraDriverName %><o:p></o:p></span></b></p>
  </td>
  <td width=131 colspan=8 valign=top style='width:98.3pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:
  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-bottom-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Additional Driver <o:p></o:p></span></b></p>
  </td>
  <td width=83 rowspan=2 valign=center style='width:62.35pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>تاريخ الخروج<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:8.0pt'>Date Out<o:p></o:p></span></b></p>
  </td>
  <td width=79 rowspan=2 valign=center style='width:59.6pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=right dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:left;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:9.0pt'><o:p><%=conOutDate %></o:p></span></b></p>
  </td>
  <td width=96 rowspan=2 valign=center style='width:72.3pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>تاريخ العودة<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:8.0pt'>Arrival Date<o:p></o:p></span></b></p>
  </td>
  <td width=73 rowspan=2 valign=center style='width:54.85pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:9.0pt'><o:p><%=conInDate %></o:p></span></b></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:9'>
  <td width=95 colspan=4 valign=top style='width:71.35pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>رقم رخصة القيادة<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Driving License<o:p></o:p></span></b></p>
  </td>
  <td width=69 colspan=4 valign=top style='width:51.45pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>تاريخ الانتهاء<o:p></o:p></span></b></p>
  <p class=MsoNormal align=center dir=LTR style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal'><b><span style='font-size:8.0pt'>Exp.
  Date<o:p></o:p></span></b></p>
  </td>
  <td width=92 colspan=7 valign=top style='width:69.15pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>مصدرها<o:p></o:p></span></b></p>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Issued From<o:p></o:p></span></b></p>
  </td>
  <td width=72 colspan=4 valign=top style='width:53.95pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>نوعها<o:p></o:p></span></b></p>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Type<o:p></o:p></span></b></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:10'>
  <td width=95 colspan=4 valign=center style='width:71.35pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:9.0pt'><o:p><%=extra_driver_lic_id %></o:p></span></b></p>
  </td>
  <td width=69 colspan=4 valign=center style='width:51.45pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:8.0pt'><o:p><%=extra_driver_lic_edate %></o:p></span></b></p>
  </td>
  <td width=92 colspan=7 valign=top style='width:69.15pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'><o:p>--</o:p></span></b></p>
  </td>
  <td width=72 colspan=4 valign=top style='width:53.95pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'><o:p>&nbsp;</o:p></span></b></p>
  </td>
  <td width=83 valign=top style='width:62.35pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>وقت الخروج<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Time Out<o:p></o:p></span></b></p>
  </td>
  <td width=79 valign=center style='width:59.6pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=right dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:9.0pt'><o:p><%=conOutTime %></o:p></span></b></p>
  </td>
  <td width=96 valign=top style='width:72.3pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>وقت العودة<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Return Time<o:p></o:p></span></b></p>
  </td>
  <td width=73 valign=center style='width:54.85pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:9.0pt'><o:p><%=conInTime %></o:p></span></b></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:11'>
  <td width=95 colspan=4 valign=top style='width:71.35pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>عنوان العمل<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Office Address<o:p></o:p></span></b></p>
  </td>
  <td width=233 colspan=15 valign=center style='width:174.55pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:9.0pt'><o:p><%=custOfficeAddress %></o:p></span></b></p>
  </td>
  <td width=83 valign=top style='width:62.35pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>عداد الخروج<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Km. Out<o:p></o:p></span></b></p>
  </td>
  <td width=79 valign=center style='width:59.6pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:9.0pt'><o:p><%=carFmkmreading %> كم </o:p></span></b></p>
  </td>
  <td width=96 valign=top style='width:72.3pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>عداد العودة<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Return Km<o:p></o:p></span></b></p>
  </td>
  <td width=73 valign=center style='width:54.85pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:9.0pt'><o:p><%=carRrturnKM %> كم </o:p></span></b></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:12'>
  <td width=95 colspan=4 valign=top style='width:71.35pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>هاتف العمل<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Office Tel.<o:p></o:p></span></b></p>
  </td>
  <td width=84 colspan=6 valign=center style='width:62.65pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:8.0pt'><o:p><%=office_tel%></o:p></span></b></p>
  </td>
  <td width=60 colspan=4 valign=top style='width:45.0pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>هاتف المنزل</span></b><b><span dir=LTR
  style='font-size:8.0pt'><o:p></o:p></span></b></p>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Home Tel</span></b><b><span lang=AR-SA
  style='font-size:8.0pt;font-family:"Times New Roman","serif";mso-ascii-font-family:
  Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:Calibri;
  mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'><o:p></o:p></span></b></p>
  </td>
  <td width=89 colspan=5 valign=center style='width:66.9pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:9.0pt'><o:p></o:p></span></b></p>
  </td>
  <td width=83 valign=top style='width:62.35pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>كم مستهلك <o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Km. Used<o:p></o:p></span></b></p>
  </td>
  <td width=79 valign=center style='width:59.6pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=right dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:9.0pt'><o:p><%=usedKM %> كم </o:p></span></b></p>
  </td>
  <td width=96 valign=top style='width:72.3pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>قيمة إصلاحات<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Repair Costs<o:p></o:p></span></b></p>
  </td>
  <td width=73 valign=center style='width:54.85pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:9.0pt'><o:p><%=damgeValue %> ريال </o:p></span></b></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:13'>
  <td width=95 colspan=4 valign=top style='width:71.35pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>عنوان المنزل<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Home Address<o:p></o:p></span></b></p>
  </td>
  <td width=84 colspan=6 valign=center style='width:62.65pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:5.0pt'><o:p><%=cust_address%></o:p></span></b></p>
  </td>
  <td width=60 colspan=4 valign=top style='width:45.0pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>جوال<o:p></o:p></span></b></p>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Mobile<o:p></o:p></span></b></p>
  </td>
  <td width=89 colspan=5 valign=top style='width:66.9pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'><o:p><%=custMobile %></o:p></span></b></p>
  </td>
  <td width=83 valign=top style='width:62.35pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>عدد أيام الايجار<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>No. of Days<o:p></o:p></span></b></p>
  </td>
  <td width=79 valign=center style='width:59.6pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=right dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:9.0pt'><o:p><%=interval%></o:p></span></b></p>
  </td>
  <td width=96 valign=top style='width:72.3pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>مبلغ الايجار<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Total Rent Value<o:p></o:p></span></b></p>
  </td>
  <td width=73 valign=center style='width:54.85pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:9.0pt'><o:p><%=conDaysValue %> ريال </o:p></span></b></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:14'>
  <td width=328 colspan=19 valign=top style='width:245.9pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:none;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;
  mso-border-left-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;tab-stops:right 180.25pt;direction:rtl;
  unicode-bidi:embed'><b><span lang=AR-SA style='font-size:8.0pt;font-family:
  "Times New Roman","serif";mso-ascii-font-family:Calibri;mso-ascii-theme-font:
  minor-latin;mso-hansi-font-family:Calibri;mso-hansi-theme-font:minor-latin;
  mso-bidi-font-family:"Times New Roman";mso-bidi-theme-font:minor-bidi'>ملاحظات
  الخروج: </span></b><b><span dir=LTR style='font-size:8.0pt'>Exit Remarks<o:p></o:p></span></b></p>
  </td>
  <td width=83 valign=top style='width:62.35pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>ساعات زائدة<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Extra Hours<o:p></o:p></span></b></p>
  </td>
  <td width=79 valign=center style='width:59.6pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=right dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:9.0pt'><o:p><%=extra_hours %> ساعة</o:p></span></b></p>
  </td>
  <td width=96 valign=top style='width:72.3pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>مبلغ ساعات زائدة<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Extra Hours Cost<o:p></o:p></span></b></p>
  </td>
  <td width=73 valign=center style='width:54.85pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:9.0pt'><o:p><%=extraHoursVal %> ريال</o:p></span></b></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:15'>
  <td width=328 colspan=19 rowspan=4 valign=top style='width:245.9pt;
  border:solid windowtext 1.0pt;border-top:none;mso-border-left-alt:solid windowtext .5pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'><o:p>&nbsp;</o:p></span></b></p>
  </td>
  <td width=83 valign=top style='width:62.35pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>كم زائد<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Extra<span style='mso-spacerun:yes'> 
  </span>Km<o:p></o:p></span></b></p>
  </td>
  <td width=79 valign=center style='width:59.6pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=right dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:9.0pt'><o:p><%=extraKm %> كم </o:p></span></b></p>
  </td>
  <td width=96 valign=top style='width:72.3pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>مبلغ كم زائد<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Extra Km<span style='mso-spacerun:yes'> 
  </span>Cost<o:p></o:p></span></b></p>
  </td>
  <td width=73 valign=center style='width:54.85pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:9.0pt'><o:p><%=extra_km_val %> ريال </o:p></span></b></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:16'>
  <td width=83 valign=top style='width:62.35pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>أخرى<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Others<o:p></o:p></span></b></p>
  </td>
  <td width=79 valign=top style='width:59.6pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=right dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:left;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:7.0pt'><o:p>&nbsp;</o:p></span></b></p>
  </td>
  <td width=96 valign=top style='width:72.3pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>الغرامات<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Penalties<o:p></o:p></span></b></p>
  </td>
  <td width=73 valign=center style='width:54.85pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:9.0pt'><o:p><%=total_penalty %> ريال </o:p></span></b></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:17'>
  <td width=83 valign=top style='width:62.35pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>الخصم <o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Discount<o:p></o:p></span></b></p>
  </td>
  <td width=79 valign=center style='width:59.6pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:9.0pt'><o:p><%=discount %> ريال </o:p></span></b></p>
  </td>
  <td width=96 valign=top style='width:72.3pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>المجموع الكلى <o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Grand Total <o:p></o:p></span></b></p>
  </td>
  <td width=73 valign=center style='width:54.85pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:10.0pt'><o:p><%=carincome %> ريال </o:p></span></b></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:18'>
  <td width=83 valign=top style='width:62.35pt;border:none;mso-border-top-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'><o:p>&nbsp;</o:p></span></b></p>
  </td>
  <td width=79 valign=top style='width:59.6pt;border:none;border-left:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;
  mso-border-left-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:7.0pt'><o:p>&nbsp;</o:p></span></b></p>
  </td>
  <td width=96 valign=top style='width:72.3pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>إجمالى المدفوعات<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Total Payments</span></b><span dir=RTL></span><b><span
  style='font-size:8.0pt;font-family:"Times New Roman","serif";mso-ascii-font-family:
  Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:Calibri;
  mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'><span dir=RTL></span> </span></b><b><span
  dir=LTR style='font-size:8.0pt'><o:p></o:p></span></b></p>
  </td>
  <td width=73 valign=center style='width:54.85pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:9.0pt'><o:p><%=totalPayments %> ريال </o:p></span></b></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:19'>
  <td width=71 colspan=3 rowspan=2 style='width:53.6pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:7.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>كيفية تغطية الاضرار الناجمة</span></b><b><span
  dir=LTR style='font-size:7.0pt'><o:p></o:p></span></b></p>
  </td>
  <td width=78 colspan=3 rowspan=2 valign=top style='width:58.25pt;border-top:
  none;border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <div align=center>
  <table class=MsoNormalTable dir=rtl border=1 cellspacing=0 cellpadding=0
   style='border-collapse:collapse;mso-table-layout-alt:fixed;border:none;
   mso-border-alt:solid black .5pt;mso-border-themecolor:text1;mso-yfti-tbllook:
   1184;mso-padding-alt:0in 5.4pt 0in 5.4pt;mso-table-dir:bidi'>
   <tr style='mso-yfti-irow:0;mso-yfti-firstrow:yes;mso-yfti-lastrow:yes'>
    <td width=18 style='width:13.85pt;border:solid black 1.0pt;mso-border-themecolor:
    text1;mso-border-alt:solid black .5pt;mso-border-themecolor:text1;
    padding:0in 5.4pt 0in 5.4pt'>
    <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;
    margin-bottom:.0001pt;text-align:center;line-height:normal;direction:rtl;
    unicode-bidi:embed'><b><span dir=LTR style='font-size:7.0pt'><o:p>&nbsp;</o:p></span></b></p>
    </td>
   </tr>
  </table>
  </div>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:7.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>بشكل مباشر من قبل العميل</span></b><b><span
  dir=LTR style='font-size:7.0pt'><o:p></o:p></span></b></p>
  </td>
  <td width=107 colspan=9 rowspan=2 valign=top style='width:80.1pt;border-top:
  none;border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <div align=center>
  <table class=MsoNormalTable dir=rtl border=1 cellspacing=0 cellpadding=0
   style='border-collapse:collapse;mso-table-layout-alt:fixed;border:none;
   mso-border-alt:solid black .5pt;mso-border-themecolor:text1;mso-yfti-tbllook:
   1184;mso-padding-alt:0in 5.4pt 0in 5.4pt;mso-table-dir:bidi'>
   <tr style='mso-yfti-irow:0;mso-yfti-firstrow:yes;mso-yfti-lastrow:yes'>
    <td width=16 style='width:11.8pt;border:solid black 1.0pt;mso-border-themecolor:
    text1;mso-border-alt:solid black .5pt;mso-border-themecolor:text1;
    padding:0in 5.4pt 0in 5.4pt'>
    <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;
    margin-bottom:.0001pt;text-align:center;line-height:normal;direction:rtl;
    unicode-bidi:embed'><b><span dir=LTR style='font-size:7.0pt'><o:p>&nbsp;</o:p></span></b></p>
    </td>
   </tr>
  </table>
  </div>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:7.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>عن طريق شركة التأمين التى يحمل المستأجر وثيقة
  منها</span></b><b><span dir=LTR style='font-size:7.0pt'><o:p></o:p></span></b></p>
  </td>
  <td width=72 colspan=4 rowspan=2 valign=top style='width:53.95pt;border-top:
  none;border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <div align=right>
  <table class=MsoNormalTable dir=rtl border=1 cellspacing=0 cellpadding=0
   style='margin-left:22.75pt;border-collapse:collapse;mso-table-layout-alt:
   fixed;border:none;mso-border-alt:solid black .5pt;mso-border-themecolor:
   text1;mso-yfti-tbllook:1184;mso-padding-alt:0in 5.4pt 0in 5.4pt;mso-table-dir:
   bidi'>
   <tr style='mso-yfti-irow:0;mso-yfti-firstrow:yes;mso-yfti-lastrow:yes'>
    <td width=18 valign=top style='width:13.5pt;border:solid black 1.0pt;
    mso-border-themecolor:text1;mso-border-alt:solid black .5pt;mso-border-themecolor:
    text1;padding:0in 5.4pt 0in 5.4pt'>
    <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;
    margin-bottom:.0001pt;text-align:center;line-height:normal;direction:rtl;
    unicode-bidi:embed'><b><span dir=LTR style='font-size:7.0pt'><o:p>&nbsp;</o:p></span></b></p>
    </td>
   </tr>
  </table>
  </div>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:7.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>قبول التغطية التأمينية بقدما له المؤجر</span></b><b><span
  dir=LTR style='font-size:7.0pt'><o:p></o:p></span></b></p>
  </td>
  <td width=83 valign=top style='width:62.35pt;border:none;mso-border-right-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:7.0pt'><o:p>&nbsp;</o:p></span></b></p>
  </td>
  <td width=79 valign=top style='width:59.6pt;border:none;border-left:solid windowtext 1.0pt;
  mso-border-left-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:7.0pt'><o:p>&nbsp;</o:p></span></b></p>
  </td>
  <td width=96 valign=top style='width:72.3pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>مطلوب من العميل<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Need To Collect<o:p></o:p></span></b></p>
  </td>
  <td width=73 valign=center style='width:54.85pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:12.0pt'><o:p><%=contNeedToCllect %> ريال </o:p></span></b></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:20'>
  <td width=83 valign=top style='width:62.35pt;border:none;mso-border-right-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:7.0pt'><o:p>&nbsp;</o:p></span></b></p>
  </td>
  <td width=79 valign=top style='width:59.6pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-left-alt:
  solid windowtext .5pt;mso-border-bottom-alt:solid windowtext .5pt;padding:
  0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:7.0pt'><o:p>&nbsp;</o:p></span></b></p>
  </td>
  <td width=96 valign=top style='width:72.3pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>مطلوب رد الى العميل<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Refund Amount<o:p></o:p></span></b></p>
  </td>
  <td width=73 valign=center style='width:54.85pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=RTL style='font-size:10.0pt'><o:p><%=contNeedToRefund %> ريال </o:p></span></b></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:21'>
  <td width=95 colspan=4 rowspan=2 valign=top style='width:71.35pt;border-top:
  none;border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:9.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>توقيع العميل</span></b><span dir=LTR></span><b><span
  dir=LTR style='font-size:9.0pt'><span dir=LTR></span><span
  style='mso-spacerun:yes'>    </span></span></b><span dir=RTL></span><b><span
  style='font-size:9.0pt;font-family:"Times New Roman","serif";mso-ascii-font-family:
  Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:Calibri;
  mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'><span dir=RTL></span><span
  style='mso-spacerun:yes'> </span></span></b><b><span dir=LTR
  style='font-size:9.0pt'><o:p></o:p></span></b></p>
  </td>
  <td width=233 colspan=15 rowspan=2 valign=top style='width:174.55pt;
  border-top:none;border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:
  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-bottom-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=right dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:left;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Customer Signature</span></b><b><span
  dir=LTR style='font-size:9.0pt'><o:p></o:p></span></b></p>
  </td>
  <td width=83 valign=top style='width:62.35pt;border:none;border-left:solid windowtext 1.0pt;
  mso-border-right-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><span
  dir=LTR style='font-size:9.0pt'><o:p>&nbsp;</o:p></span></p>
  </td>
  <td width=79 valign=top style='width:59.6pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><span
  dir=LTR style='font-size:9.0pt'><o:p>&nbsp;</o:p></span></p>
  </td>
  <td width=96 valign=top style='width:72.3pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>إجمالى المستحق<o:p></o:p></span></b></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:8.0pt'>Total Amount</span></b><span dir=LTR
  style='font-size:8.0pt'><o:p></o:p></span></p>
  </td>
  <td width=73 valign=top style='width:54.85pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><span
  dir=LTR style='font-size:9.0pt'><o:p>&nbsp;</o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:22'>
  <td width=83 valign=top style='width:62.35pt;border:none;border-left:solid windowtext 1.0pt;
  mso-border-right-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><span
  dir=LTR style='font-size:9.0pt'><o:p>&nbsp;</o:p></span></p>
  </td>
  <td width=249 colspan=3 valign=top style='width:186.75pt;border:none;
  border-left:solid windowtext 1.0pt;mso-border-top-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;
  mso-border-left-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>ملاجظات العودة<span
  style='mso-spacerun:yes'>       </span></span><span dir=LTR style='font-size:
  8.0pt'>Return Remarks</span><span lang=AR-SA style='font-size:8.0pt;
  font-family:"Times New Roman","serif";mso-ascii-font-family:Calibri;
  mso-ascii-theme-font:minor-latin;mso-hansi-font-family:Calibri;mso-hansi-theme-font:
  minor-latin;mso-bidi-font-family:"Times New Roman";mso-bidi-theme-font:minor-bidi'><o:p></o:p></span></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><span
  lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>1----------</span><span dir=LTR></span><span
  dir=LTR style='font-size:8.0pt'><span dir=LTR></span>----------------</span><span
  dir=RTL></span><span lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'><span dir=RTL></span>------------------------------</span><span
  dir=LTR style='font-size:8.0pt'><o:p></o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:23'>
  <td width=37 rowspan=3 style='width:27.45pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:6.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>الحالة الضمنية للسيارة</span></b><b><span
  dir=LTR style='font-size:6.0pt'><o:p></o:p></span></b></p>
  </td>
  <td width=31 valign=top style='width:23.6pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-top:0in;margin-right:0in;margin-bottom:
  0in;margin-left:-24.8pt;margin-bottom:.0001pt;text-align:right;line-height:
  normal;direction:rtl;unicode-bidi:embed'><b><span dir=LTR style='font-size:
  5.0pt'><o:p>&nbsp;</o:p></span></b></p>
  </td>
  <td width=27 colspan=2 valign=top style='width:20.3pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:5.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>فرش<o:p></o:p></span></b></p>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:5.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>ارضية</span></b><b><span dir=LTR
  style='font-size:5.0pt'><o:p></o:p></span></b></p>
  </td>
  <td width=27 valign=top style='width:19.95pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:5.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>كفر <o:p></o:p></span></b></p>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:5.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>إحتياط</span></b><b><span dir=LTR
  style='font-size:5.0pt'><o:p></o:p></span></b></p>
  </td>
  <td width=30 colspan=2 valign=top style='width:22.5pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:5.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>رافعة</span></b><b><span dir=LTR
  style='font-size:5.0pt'><o:p></o:p></span></b></p>
  </td>
  <td width=24 colspan=2 valign=top style='width:.25in;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:5.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>مفتاح كفر</span></b><b><span dir=LTR
  style='font-size:5.0pt'><o:p></o:p></span></b></p>
  </td>
  <td width=30 colspan=4 valign=top style='width:22.15pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:5.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>ولاعة سجاير</span></b><b><span dir=LTR
  style='font-size:5.0pt'><o:p></o:p></span></b></p>
  </td>
  <td width=33 valign=top style='width:25.05pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:5.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>مسجل<o:p></o:p></span></b></p>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:5.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>راديو</span></b><b><span dir=LTR
  style='font-size:5.0pt'><o:p></o:p></span></b></p>
  </td>
  <td width=35 colspan=2 valign=top style='width:26.55pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:5.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>مكيف<o:p></o:p></span></b></p>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:5.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'><span style='mso-spacerun:yes'> </span>سيارة</span></b><b><span
  dir=LTR style='font-size:5.0pt'><o:p></o:p></span></b></p>
  </td>
  <td width=30 colspan=2 valign=top style='width:22.35pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in 5.75pt 0in 5.75pt'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:5.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>شنطة<o:p></o:p></span></b></p>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;tab-stops:right 4.65pt;
  direction:rtl;unicode-bidi:embed'><b><span lang=AR-SA style='font-size:5.0pt;
  font-family:"Times New Roman","serif";mso-ascii-font-family:Calibri;
  mso-ascii-theme-font:minor-latin;mso-hansi-font-family:Calibri;mso-hansi-theme-font:
  minor-latin;mso-bidi-font-family:"Times New Roman";mso-bidi-theme-font:minor-bidi'><span
  style='mso-spacerun:yes'> </span>عدة</span></b><b><span dir=LTR
  style='font-size:5.0pt'><o:p></o:p></span></b></p>
  </td>
  <td width=24 style='width:.25in;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in 5.75pt 0in 5.75pt'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:3.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>طبلون</span></b><b><span dir=LTR
  style='font-size:3.0pt'><o:p></o:p></span></b></p>
  </td>
  <td width=83 valign=top style='width:62.35pt;border:none;border-left:solid windowtext 1.0pt;
  mso-border-right-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><span
  dir=LTR style='font-size:7.0pt'><o:p>&nbsp;</o:p></span></p>
  </td>
  <td width=249 colspan=3 style='width:186.75pt;border:none;border-left:solid windowtext 1.0pt;
  mso-border-right-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'></td>
 </tr>
 <tr style='mso-yfti-irow:24'>
  <td width=31 valign=top style='width:23.6pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:6.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>خروج</span></b><b><span dir=LTR
  style='font-size:6.0pt'><o:p></o:p></span></b></p>
  </td>
  <td width=27 colspan=2 valign=top style='width:20.3pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:6.0pt'><o:p>&nbsp;</o:p></span></b></p>
  </td>
  <td width=27 valign=top style='width:19.95pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:6.0pt'><o:p>&nbsp;</o:p></span></b></p>
  </td>
  <td width=30 colspan=2 valign=top style='width:22.5pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:6.0pt'><o:p>&nbsp;</o:p></span></b></p>
  </td>
  <td width=24 colspan=2 valign=top style='width:.25in;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:6.0pt'><o:p>&nbsp;</o:p></span></b></p>
  </td>
  <td width=30 colspan=4 valign=top style='width:22.15pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:6.0pt'><o:p>&nbsp;</o:p></span></b></p>
  </td>
  <td width=33 valign=top style='width:25.05pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:6.0pt'><o:p>&nbsp;</o:p></span></b></p>
  </td>
  <td width=35 colspan=2 valign=top style='width:26.55pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:6.0pt'><o:p>&nbsp;</o:p></span></b></p>
  </td>
  <td width=30 colspan=2 valign=top style='width:22.35pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:6.0pt'><o:p>&nbsp;</o:p></span></b></p>
  </td>
  <td width=24 valign=top style='width:.25in;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:6.0pt'><o:p>&nbsp;</o:p></span></b></p>
  </td>
  <td width=83 valign=top style='width:62.35pt;border:none;border-left:solid windowtext 1.0pt;
  mso-border-right-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><span
  dir=LTR style='font-size:9.0pt'><o:p>&nbsp;</o:p></span></p>
  </td>
  <td width=249 colspan=3 rowspan=2 valign=top style='width:186.75pt;
  border-top:none;border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-right-alt:solid windowtext .5pt;mso-border-left-alt:
  solid windowtext .5pt;mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><span
  dir=RTL></span><span lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'><span dir=RTL></span>2----------</span><span
  dir=LTR></span><span dir=LTR style='font-size:8.0pt'><span dir=LTR></span>----------------</span><span
  dir=RTL></span><span lang=AR-SA style='font-size:8.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'><span dir=RTL></span>------------------------------</span><span
  dir=LTR style='font-size:8.0pt'><o:p></o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:25'>
  <td width=31 valign=top style='width:23.6pt;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:6.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>عودة</span></b><b><span dir=LTR
  style='font-size:6.0pt'><o:p></o:p></span></b></p>
  </td>
  <td width=27 colspan=2 valign=top style='width:20.3pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:6.0pt'><o:p>&nbsp;</o:p></span></b></p>
  </td>
  <td width=27 valign=top style='width:19.95pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:6.0pt'><o:p>&nbsp;</o:p></span></b></p>
  </td>
  <td width=30 colspan=2 valign=top style='width:22.5pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:6.0pt'><o:p>&nbsp;</o:p></span></b></p>
  </td>
  <td width=24 colspan=2 valign=top style='width:.25in;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:6.0pt'><o:p>&nbsp;</o:p></span></b></p>
  </td>
  <td width=30 colspan=4 valign=top style='width:22.15pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:6.0pt'><o:p>&nbsp;</o:p></span></b></p>
  </td>
  <td width=33 valign=top style='width:25.05pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:6.0pt'><o:p>&nbsp;</o:p></span></b></p>
  </td>
  <td width=35 colspan=2 valign=top style='width:26.55pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:6.0pt'><o:p>&nbsp;</o:p></span></b></p>
  </td>
  <td width=30 colspan=2 valign=top style='width:22.35pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:6.0pt'><o:p>&nbsp;</o:p></span></b></p>
  </td>
  <td width=24 valign=top style='width:.25in;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:solid windowtext 1.0pt;border-right:none;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in .05in 0in .05in'>
  <p class=MsoNormal align=center dir=RTL style='margin-bottom:0in;margin-bottom:
  .0001pt;text-align:center;line-height:normal;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:6.0pt'><o:p>&nbsp;</o:p></span></b></p>
  </td>
  <td width=83 valign=top style='width:62.35pt;border-top:none;border-left:
  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
  none;mso-border-right-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><span
  dir=LTR style='font-size:9.0pt'><o:p>&nbsp;</o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:26;height:52.9pt'>
  <td width=328 colspan=19 rowspan=2 valign=top style='width:245.9pt;
  border:solid windowtext 1.0pt;border-top:none;mso-border-top-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in;height:52.9pt'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><span
  lang=AR-SA style='font-size:9.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'><o:p>&nbsp;</o:p></span></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><span
  lang=AR-SA style='font-size:9.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>إقر أنا مستأجر السيارة بأننى قرأت الملاحظات المدونة
  بالعقد والشروط الواردة خلفه و ان يتم المحاسبة النهائية بموجب الاسعار المبينة
  بالعقد دون اي تخفيض و اننى موافق عليها و اتعهد بسداد جميع المخالفات المرورية
  و علي ذلك اوقع و انني على استعداد بتحمل كافة التكاليف و المبالغ المادية نتيجة
  حادث أو سرقة أو اصلاحات .كما يحق للشركة سحب السيارة بعد انتهاء مدة العقد.<o:p></o:p></span></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><span
  lang=AR-SA style='font-size:9.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'><o:p>&nbsp;</o:p></span></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><span
  lang=AR-SA style='font-size:9.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>التوقيع :<span
  style='mso-spacerun:yes'>            </span></span><span dir=LTR
  style='font-size:9.0pt'>Signature:<span style='mso-spacerun:yes'>     
  </span></span><span dir=RTL></span><span lang=AR-SA style='font-size:9.0pt;
  font-family:"Times New Roman","serif";mso-ascii-font-family:Calibri;
  mso-ascii-theme-font:minor-latin;mso-hansi-font-family:Calibri;mso-hansi-theme-font:
  minor-latin;mso-bidi-font-family:"Times New Roman";mso-bidi-theme-font:minor-bidi'><span
  dir=RTL></span><span style='mso-spacerun:yes'>  </span></span><span dir=LTR></span><span
  dir=LTR style='font-size:9.0pt'><span dir=LTR></span><span
  style='mso-spacerun:yes'>            </span></span><span dir=RTL></span><span
  lang=AR-SA style='font-size:9.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'><span dir=RTL></span><span
  style='mso-spacerun:yes'>   </span>الموظف المؤجر<span
  style='mso-spacerun:yes'>    </span></span><span dir=LTR style='font-size:
  9.0pt'>Prepared By</span><span lang=AR-SA style='font-size:9.0pt;font-family:
  "Times New Roman","serif";mso-ascii-font-family:Calibri;mso-ascii-theme-font:
  minor-latin;mso-hansi-font-family:Calibri;mso-hansi-theme-font:minor-latin;
  mso-bidi-font-family:"Times New Roman";mso-bidi-theme-font:minor-bidi'><o:p></o:p></span></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><span
  lang=AR-SA style='font-size:9.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'><o:p>&nbsp;</o:p></span></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><span
  lang=AR-SA style='font-size:9.0pt;font-family:"Times New Roman","serif";
  mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-hansi-font-family:
  Calibri;mso-hansi-theme-font:minor-latin;mso-bidi-font-family:"Times New Roman";
  mso-bidi-theme-font:minor-bidi'>التاريخ<span
  style='mso-spacerun:yes'>           </span></span><span dir=LTR
  style='font-size:9.0pt'>Date:<span
  style='mso-spacerun:yes'>                          </span><o:p></o:p></span></p>
  </td>
  <td width=332 colspan=4 valign=top style='width:249.1pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in;
  height:52.9pt'>
  <p class=Style dir=RTL style='margin-right:.2pt;text-align:right;line-height:
  7.2pt;mso-line-height-rule:exactly;direction:rtl;unicode-bidi:embed'><span
  lang=AR-SA style='font-size:8.0pt'><o:p>&nbsp;</o:p></span></p>
  <p class=Style dir=RTL style='margin-right:.2pt;text-align:right;line-height:
  7.2pt;mso-line-height-rule:exactly;direction:rtl;unicode-bidi:embed'><span
  lang=AR-SA style='font-size:8.0pt'>الكفالة: نعم انا الموقع ادناه اكفل المدعو/
  <o:p></o:p></span></p>
  <p class=Style dir=RTL style='margin-left:.2pt;text-align:right;line-height:
  9.8pt;mso-line-height-rule:exactly;direction:rtl;unicode-bidi:embed'><span
  lang=AR-SA style='font-size:8.0pt'>‏والمستاجرللسيارة الموضحة بالعقد كفالة
  حضورية وغرامية على جميع ما يترتب على السيارة من إيجارات ومصاريف إصلاحات
  وخلافه وعليه اوفع <o:p></o:p></span></p>
  <p class=Style dir=RTL style='margin-left:.2pt;text-align:right;line-height:
  9.8pt;mso-line-height-rule:exactly;direction:rtl;unicode-bidi:embed'><span
  lang=AR-SA style='font-size:8.0pt'>الاسم<span style='mso-spacerun:yes'>  
  </span>: ----------------------------------------------------- </span><span
  dir=LTR style='font-size:8.0pt'>Name :</span><span lang=AR-SA
  style='font-size:8.0pt'><o:p></o:p></span></p>
  <p class=Style dir=RTL style='margin-left:.2pt;text-align:right;line-height:
  9.8pt;mso-line-height-rule:exactly;direction:rtl;unicode-bidi:embed'><span
  lang=AR-SA style='font-size:8.0pt'><o:p>&nbsp;</o:p></span></p>
  <p class=Style dir=RTL style='margin-left:.2pt;text-align:right;line-height:
  9.8pt;mso-line-height-rule:exactly;direction:rtl;unicode-bidi:embed'><span
  lang=AR-SA style='font-size:8.0pt'>العنوان :
  -----------------------------------------------------</span><span dir=LTR></span><span
  dir=LTR style='font-size:8.0pt'><span dir=LTR></span> Address : <o:p></o:p></span></p>
  <p class=Style dir=RTL style='margin-left:.2pt;text-align:right;line-height:
  9.8pt;mso-line-height-rule:exactly;direction:rtl;unicode-bidi:embed'><span
  lang=AR-SA style='font-size:8.0pt'><o:p>&nbsp;</o:p></span></p>
  <p class=Style dir=RTL style='margin-left:.2pt;text-align:right;line-height:
  9.8pt;mso-line-height-rule:exactly;direction:rtl;unicode-bidi:embed'><span
  lang=AR-SA style='font-size:8.0pt'>التوقيع<span style='mso-spacerun:yes'> 
  </span>: -----</span><span dir=LTR></span><span dir=LTR style='font-size:
  8.0pt'><span dir=LTR></span>--</span><span dir=RTL></span><span lang=AR-SA
  style='font-size:8.0pt'><span dir=RTL></span>----------------------------------------------</span><span
  dir=LTR style='font-size:8.0pt'>Signature: <o:p></o:p></span></p>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'><span
  dir=LTR style='font-size:8.0pt'><o:p>&nbsp;</o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:27;height:52.85pt'>
  <td width=332 colspan=4 valign=top style='width:249.1pt;border-top:none;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;
  border-right:none;mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:
  solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;padding:0in .05in 0in .05in;
  height:52.85pt'>
  <p class=Style dir=RTL style='margin-right:.2pt;text-align:right;line-height:
  7.2pt;mso-line-height-rule:exactly;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:9.0pt'><o:p>&nbsp;</o:p></span></b></p>
  <p class=Style dir=RTL style='margin-right:.2pt;text-align:right;line-height:
  7.2pt;mso-line-height-rule:exactly;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:9.0pt'>توقيع المستأجر بإعادة السيارة و إنهاء
  العقد و التعهد بسداد الحساب<o:p></o:p></span></b></p>
  <p class=Style dir=RTL style='margin-right:.2pt;text-align:right;line-height:
  7.2pt;mso-line-height-rule:exactly;direction:rtl;unicode-bidi:embed'><b><span
  dir=LTR style='font-size:7.0pt'>Renter’s Signature to return the car and contract
  termination and settlement </span></b><b><span lang=AR-SA style='font-size:
  7.0pt'><o:p></o:p></span></b></p>
  <p class=Style dir=RTL style='margin-right:.2pt;text-align:right;line-height:
  7.2pt;mso-line-height-rule:exactly;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:9.0pt'><o:p>&nbsp;</o:p></span></b></p>
  <p class=Style dir=RTL style='margin-right:.2pt;text-align:right;line-height:
  7.2pt;mso-line-height-rule:exactly;direction:rtl;unicode-bidi:embed'><b><span
  lang=AR-SA style='font-size:9.0pt'>توقيع المستأجر<span
  style='mso-spacerun:yes'>             </span>توقيع الموظف بإستلام السيارة</span></b><b><span
  dir=LTR style='font-size:9.0pt'><o:p></o:p></span></b></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:28;mso-yfti-lastrow:yes'>
  <td width=660 colspan=23 valign=top style='width:495.0pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0in .05in 0in .05in'>
  <p class=MsoNormal dir=RTL style='margin-bottom:0in;margin-bottom:.0001pt;  text-align:right;line-height:normal;direction:rtl;unicode-bidi:embed'>
  	
  <span dir=LTR style='font-size:8.0pt'><o:p> 
  	<jsp:include page="../renderQueryResult.jsp?id=26158&lookupTableName=LU_QUERIES">
        	<jsp:param value="1" name="queryIndex"/>
        	<jsp:param value="<%=rowIdValue%>" name="rowId"/>
        	<jsp:param value="true" name="printable"/>
        	
    </jsp:include>
    <a href = "../editAndExecute.jsp?id=26158&lookupTableName=LU_QUERIES&ContRowIdValue=<%=rowIdValue%>" title ="Click To Maintain">Maintain Report</a>
  </o:p></span></p>
  </td>
 </tr>
 <![if !supportMisalignedColumns]>
 <tr height=0>
  <td width=37 style='border:none'></td>
  <td width=31 style='border:none'></td>
  <td width=3 style='border:none'></td>
  <td width=24 style='border:none'></td>
  <td width=27 style='border:none'></td>
  <td width=27 style='border:none'></td>
  <td width=3 style='border:none'></td>
  <td width=12 style='border:none'></td>
  <td width=12 style='border:none'></td>
  <td width=3 style='border:none'></td>
  <td width=18 style='border:none'></td>
  <td width=1 style='border:none'></td>
  <td width=7 style='border:none'></td>
  <td width=33 style='border:none'></td>
  <td width=17 style='border:none'></td>
  <td width=18 style='border:none'></td>
  <td width=15 style='border:none'></td>
  <td width=15 style='border:none'></td>
  <td width=24 style='border:none'></td>
  <td width=83 style='border:none'></td>
  <td width=79 style='border:none'></td>
  <td width=96 style='border:none'></td>
  <td width=73 style='border:none'></td>
 </tr>
 <![endif]>
</table>

</div>

<p class=MsoNormal dir=RTL style='text-align:right;direction:rtl;unicode-bidi:
embed'><span dir=LTR style='font-size:9.0pt;line-height:115%'><o:p>&nbsp;</o:p></span></p>

</div>

</body>

</html>
