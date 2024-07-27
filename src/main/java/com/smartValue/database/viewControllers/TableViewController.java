package com.smartValue.database.viewControllers;

import java.io.Serializable;


public class TableViewController implements ViewController , Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public void handleView() {
		// TODO Auto-generated method stub

	}
	

	private boolean searchOpened = false; 
	private boolean showSearchResult = false; 
	private String selectedDetailsPage ; 
	
	public void toggleOpenSearch() {
		this.searchOpened = !searchOpened;
	}
	public boolean isSearchOpened() {
		return searchOpened;
	}
	
	public void setSearchOpened(boolean searchOpened) {
		this.searchOpened = searchOpened;
	}

	
	public boolean isShowSearchResult() {
		return showSearchResult;
	}
	public void setShowSearchResult(boolean showSearchResult) {
		this.showSearchResult = showSearchResult;
	}
	
	public void hidAllSearchPanels()
	{
		this.setSearchOpened(false);
		this.setShowSearchResult(false) ;
	}
	public void setSelectedDetailsPage(String selectedDetailsPage) {
		this.selectedDetailsPage = selectedDetailsPage;
	}
	public String getSelectedDetailsPage() {
		return selectedDetailsPage;
	}

	private String columnNumbers = "2";

	public void setColumnNumbers(String columnNumbers) {
		this.columnNumbers = columnNumbers;
	}
	public String getColumnNumbers() {
		return columnNumbers;
	}



}
