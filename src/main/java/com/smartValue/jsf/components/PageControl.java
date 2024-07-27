package com.smartValue.jsf.components;

import com.smartValue.database.PersistantObject;

public class PageControl {

	private boolean rendered = true ;
	private String pageRelUrl ; 
	private boolean disabled = false ;
	private String switchType = "ajax" ;
	private String pageHeader = "Page Header"; 
	private String pageId = "" ;
	
	public static final String CLIENT_SWICH_TYPE = "client" ;
	public static final String SERVER_SWICH_TYPE = "server" ;
	public static final String AJAX_SWICH_TYPE = "ajax" ;
	// Persistence Object which will control the behaviour of this control 
	// Not Yet
	
	private PersistantObject persistantObject ;
	
	private boolean opened = false ;
	
	public PageControl(PersistantObject po)
	{
		this.persistantObject = po ;
	}
	public boolean isRendered() {
		return rendered;
	}
	public void setRendered(boolean rendered) {
		this.rendered = rendered;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public String getPageRelUrl() {
		return pageRelUrl;
	}
	public void setPageRelUrl(String pageRelUrl) {
		this.pageRelUrl = pageRelUrl;
	}
	public void setSwitchType(String switchType) {
		this.switchType = switchType;
	}
	public String getSwitchType() {
		return switchType;
	}
	public void setPageHeader(String pageHeader) {
		this.pageHeader = pageHeader;
	}
	public String getPageHeader() {
		return pageHeader;
	}
	public void setOpened(boolean opened) {
		this.opened = opened;
	}
	public boolean isOpened() {
		return opened;
	}

	public PersistantObject getPersistantObject() {
		return persistantObject;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getPageId() {
		return pageId;
	}

	
}
