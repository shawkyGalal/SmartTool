package com.implex.database;

public class ToolbarVisabilityController
{
	private boolean showSearchControl = true;
	private boolean showDsExplorer = true;
	private boolean showNewControl = true;
	private boolean showDeleteControl = true;
	private boolean showSaveControl = true;
	private boolean showSaveAllControl= true;
	private boolean showFormMaintControl = true; 
	private boolean showCancelChanges = true;
	private boolean showNavigation = true ; 
	
	/**
	 * This class is a Controller for the toolBar object 
	 */
	public ToolbarVisabilityController()
	{
		
	}
	/**
	 * 
	 * @return
	 */
	public boolean isShowSearchControl()
	{
		return showSearchControl;
	}
	public void setShowSearchControl(boolean showSearchControl)
	{
		this.showSearchControl = showSearchControl;
	}
	public boolean isShowDsExplorer()
	{
		return showDsExplorer;
	}
	public void setShowDsExplorer(boolean showDsExplorer)
	{
		this.showDsExplorer = showDsExplorer;
	}
	public boolean isShowNewControl()
	{
		return showNewControl;
	}
	public void setShowNewControl(boolean showNewControl)
	{
		this.showNewControl = showNewControl;
	}
	public boolean isShowDeleteControl()
	{
		return showDeleteControl;
	}
	public void setShowDeleteControl(boolean showDeleteControl)
	{
		this.showDeleteControl = showDeleteControl;
	}
	public boolean isShowSaveControl()
	{
		return showSaveControl;
	}
	public void setShowSaveControl(boolean showSaveControl)
	{
		this.showSaveControl = showSaveControl;
	}
	public boolean isShowSaveAllControl()
	{
		return showSaveAllControl;
	}
	public void setShowSaveAllControl(boolean showSaveAllControl)
	{
		this.showSaveAllControl = showSaveAllControl;
	}
	public void setShowFormMaintControl(boolean showFormMaintControl)
	{
		this.showFormMaintControl = showFormMaintControl;
	}
	public boolean isShowFormMaintControl()
	{
		return showFormMaintControl;
	}
	public void setShowCancelChanges(boolean showCancelChanges)
	{
		this.showCancelChanges = showCancelChanges;
	}
	public boolean isShowCancelChanges()
	{
		return showCancelChanges;
	}
	public void setShowNavigation(boolean showNavigation) {
		this.showNavigation = showNavigation;
	}
	public boolean isShowNavigation() {
		return showNavigation;
	}

	
	

}
