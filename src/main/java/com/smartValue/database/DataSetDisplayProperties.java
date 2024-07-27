package com.smartValue.database;

import java.util.ArrayList;

public class DataSetDisplayProperties {
	private boolean useAttDefaultControl = false ;
	private int maxPages = 5 ; 
	ArrayList<Header> allAvailableHeaders ;
	ArrayList<Header> userSelectedHeaders ;
	private String reRenderIds ; 
	private DataSet dataSet ; 
	private boolean showFooter = true ; 

	public DataSetDisplayProperties(DataSet ds)
	{
		allAvailableHeaders = ds.getHeaders() ;
		userSelectedHeaders = allAvailableHeaders; 
		this.dataSet = ds ; 
	}
	
	public DataSet getDataSet()
	{
		return this.dataSet ; 
	}
	
	public ArrayList<Header> getUserSelectedHeaders()
	{
		return userSelectedHeaders; 
	}
	public void toggleUseAttDefaultControl()
	{
		this.useAttDefaultControl = ! useAttDefaultControl ; 
	}

	public void setUseAttDefaultControl(boolean pm_useAttDefaultControl) {
		useAttDefaultControl = pm_useAttDefaultControl;
	}

	public boolean isUseAttDefaultControl() {
		return useAttDefaultControl;
	}

	public void setMaxPages(int maxPages) {
		this.maxPages = maxPages;
	}

	public int getMaxPages() {
		return maxPages;
	}

	public void setReRenderIds(String reRenderIds) {
		this.reRenderIds = reRenderIds;
	}

	public String getReRenderIds() {
		return reRenderIds;
	}

	public void setShowFooter(boolean showFooter) {
		this.showFooter = showFooter;
	}

	public boolean isShowFooter() {
		return showFooter;
	}

}
