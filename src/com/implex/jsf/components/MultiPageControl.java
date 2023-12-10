package com.implex.jsf.components;

import java.util.ArrayList;

public class MultiPageControl {
private ArrayList<PageControl> pages ;
public static final String TAB_PANEL = "0" ;
public static final String MULTI_SIMPLE_TOGGLE = "1" ;
public static final String PANEL_GRID = "2" ;
private boolean detailsLink = true;
private String pageType = TAB_PANEL; 
private int panelGridColumns = 2 ;
private String selectedTab;

public String getPageType() {
	return pageType;
}

public void setPageType(String pageType) {
	this.pageType = pageType;
}




public void setPages(ArrayList<PageControl> pages) {
	this.pages = pages;
}

public ArrayList<PageControl> getPages() {
	return pages;
}

public boolean isTabPanel()
{
	return this.getPageType().equals(TAB_PANEL) ;
}

public boolean isPanelGrid()
{
	return this.getPageType().equals(PANEL_GRID) ;
}

public boolean isSimpleTogglePanl()
{
	return this.getPageType().equals(MULTI_SIMPLE_TOGGLE) ;
}

public void setPanelGridColumns(int panelGridColumns) {
	this.panelGridColumns = panelGridColumns;
}

public int getPanelGridColumns() {
	return panelGridColumns;
}
private boolean moreInfoPanelRendered = false;
public boolean isMoreInfoPanelRendered() {
	return moreInfoPanelRendered;
}
public void seeMoreInfoPanel()
{
	this.moreInfoPanelRendered = true;
}
public void hideMoreInfoPanel()
{
	this.moreInfoPanelRendered = false;
}

public void setDetailsLink(boolean detailsLink) {
	this.detailsLink = detailsLink;
}

public boolean isDetailsLink() {
	return detailsLink;
}

public void setSelectedTab(String selectedTab) {
	this.selectedTab = selectedTab;
}

public String getSelectedTab() {
	return selectedTab;
}

}
