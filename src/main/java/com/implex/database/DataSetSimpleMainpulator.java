package com.implex.database;

import java.util.ArrayList;

import javax.faces.model.SelectItem;

public class DataSetSimpleMainpulator extends DataSetManipulator {
	
	private static final String pageUlr  = "/templates/include/dataSetSimpleManipulator.xhtml"; 
	private static final SelectItem[] availableOpTypes = new SelectItem[]{} ;
	private int selectedOpType ; 
	private SelectItem[] availableOperations = new SelectItem[]{} ;
	private String operation ; 
	

	public DataSetSimpleMainpulator(DataSet ds) {
		super(ds);
	}

	@Override
	public String getPageUlr() {
		return pageUlr;
	}

	@Override
	public void manipulateDataSet() {
		
			ArrayList<PersistantObject> persistantObjectList = this.getDataSet().getPersistantObjectList();
			String columnName =this.getSelectedTableMaintDetail().getColumnNameToSwitch();
			Object columnNameValue = this.getSelectedTableMaintDetail().getCondition().getAttribute().getValue();

			for (PersistantObject po : persistantObjectList) 
			{
				if (po.isSelectedInParentDataSet())
				{
					po.getAttribute(columnName).setValue(columnNameValue);
				}
			}			
		
	}
	public void markSelectedObjectToBeDeleted(){
		ArrayList<PersistantObject> persistantObjectList = this.getDataSet().getSelectedItems();
		try {
			if(persistantObjectList != null){
				for(PersistantObject po: persistantObjectList)
				{
					this.getDataSet().markObjectToBeDeleted(po);
				}
			}
		}catch (Exception e) {
		}
	}

}
