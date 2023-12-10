package com.implex.database;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public class DataSetPickList

{
ArrayList<PersistantObject> intialValue ; 	
public String columnNameForSet,columnNameForGet;
private List<PersistantObject> userSelectedFromPickList=intialValue ;
private DataSet  ds ;
private String converterId ;
private ArrayList<SelectItem> allAvailableItems ;


	public  DataSetPickList(String col_Set,String col_Get , DataSet dataset , ArrayList<PersistantObject> pm_intialValue)
	{
		this.columnNameForSet=col_Set;
		this.columnNameForGet=col_Get;
		this.ds   =dataset;  
		this.intialValue = pm_intialValue ; 
		
	}

	private void  updateWithUserSelectedFromList(String pm_columnNameForSet , String pm_columnNameForGet)
	{
		ds.markAllToBeDeleted();
		
		for(PersistantObject po :userSelectedFromPickList)
		{
			try 
			{
			this.ds.addNew();
			PersistantObject	persisobj	=(PersistantObject)ds.getCurrentItem();
			persisobj.setAttributeValue(pm_columnNameForSet ,po.getAttribute(pm_columnNameForGet).getValue());
			} 
			catch (Exception e)
			{
				
				e.printStackTrace();
			}
			
		}
	}
	
	
	public List<PersistantObject> getUserSelectedFromPickList() 
	{
		if(userSelectedFromPickList == null)
		{
			userSelectedFromPickList = this.intialValue;
		}
		return userSelectedFromPickList;
	}
	
	public void setUserSelectedFromPickList(List<PersistantObject> userSelectedFromPickList) 
	{
		this.userSelectedFromPickList = userSelectedFromPickList;
		this.updateWithUserSelectedFromList(columnNameForSet,columnNameForGet);
	}

	public void setConverterId(String convertedId) {
		this.converterId = convertedId;
	}

	public String getConverterId() {
		return converterId;
	}

	public void setAllAvailableItems(ArrayList<SelectItem> allAvailableItems) {
		this.allAvailableItems = allAvailableItems;
	}

	public ArrayList<SelectItem> getAllAvailableItems() {
		return allAvailableItems;
	}
	


}
