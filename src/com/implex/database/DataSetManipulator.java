package com.implex.database;

import com.implex.database.map.TableMaintDetails;

public abstract class  DataSetManipulator {

	private DataSet dataSet ; 
	private String dbColumnName ; 
	private TableMaintDetails selectedTableMaintDetail ;
	private Object inputValue ; 
	private String onManipulateRerender ; 

	
	public DataSetManipulator (DataSet ds )
	{
		this.setDataSet(ds); 
	}
	
	private void setDataSet(DataSet dataSet) 
	{
		this.dataSet = dataSet;
		onManipulateRerender = this.dataSet.getDisplayPropId();
	}

	public DataSet getDataSet() {
		return dataSet;
	}
	
	public abstract void manipulateDataSet() ; 
	
	public void addnewItemInDataSet(){
		try{
			this.getDataSet().addNew(this.getNumberOfNewItems());	
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private int numberOfNewItems;
	public void setNumberOfNewItems(int numberOfItems) {
		this.numberOfNewItems = numberOfItems;
	}

	public int getNumberOfNewItems() {
		return numberOfNewItems;
	}


	public TableMaintDetails getSelectedTableMaintDetail() {
		return selectedTableMaintDetail;
	}

	public void setInputValue(Object inputValue) {
		this.inputValue = inputValue;
	}

	public Object getInputValue() {
		return inputValue;
	}


	public abstract String getPageUlr() ;  

	public void setDbColumnName(String dbColumnName) {
		this.dbColumnName = dbColumnName;
		this.selectedTableMaintDetail = this.getDataSet().getTableMaintMaster().getTmdByColumnName(dbColumnName);
	}

	public String getDbColumnName() {
		return dbColumnName;
	}

	public void setOnManipulateRerender(String onManipulateRerender) {
		this.onManipulateRerender = onManipulateRerender;
	}

	public String getOnManipulateRerender() {
		return onManipulateRerender;
	}


}
