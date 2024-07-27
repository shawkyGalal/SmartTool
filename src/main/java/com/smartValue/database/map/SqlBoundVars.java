package com.smartValue.database.map;

import java.util.ArrayList;
import java.util.HashMap;

import javax.faces.model.SelectItem;

import com.smartValue.database.Attribute;
import com.smartValue.database.AttributeChangeValidator;
import com.smartValue.database.DbForignKeyArray;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.map.auto._SqlBoundVars;
import com.smartValue.database.map.security.PersistentObjectSecurityControl;
import com.smartValue.database.trigger.TriggerHandler;

public class SqlBoundVars extends _SqlBoundVars {
	
	public static final String VAR_DELIMITER = "$$";
	private Object currentValue;

	private static HashMap<String, DbForignKeyArray> childrenForignKeys;
	@Override
	public HashMap<String, DbForignKeyArray> getChildrenForignKeys() 
	{
		 if (childrenForignKeys == null) 
		  { childrenForignKeys = new HashMap<String, DbForignKeyArray>(); 
			  //TODO... Fill Your Childern 
			  //Example ... this.getTableMaintMaster().getDbForignKey(new DbTable(SavedSearchDetail.DB_TABLE_NAME)); 
		  } 
		 return childrenForignKeys; 
	}
	
	@Override
	public PersistentObjectSecurityControl getSecurityController() 
	{
		return null; 
	}
	@Override
	public TriggerHandler getTriggerHandler() 
	{
		// TODO Auto-generated method stub
	 return null; 
	}
	@Override
	public AttributeChangeValidator getAttributeChangeValidator(
			SecUsrDta pmSecUserData, PersistantObject pmPo, String pmKey) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<SelectItem> getSelectItems() throws Exception
	{
		return this.getDbService().getSelectItems(this.getQueryForListValue(), true);  
	}

	public void setCurrentValue(Object currentValue) {
		this.currentValue = currentValue;
	}
	public Object getCurrentValue() {
		return currentValue;
	}
	
	public boolean isText()
	{
		return this.getDataTypeValue().equalsIgnoreCase("Text");
	}
	
	public boolean isDate()
	{
		return this.getDataTypeValue().equalsIgnoreCase("Date");
	}
	
	public boolean isList()
	{
		return this.getDataTypeValue().equalsIgnoreCase("List");
	}

	@Override
	public void afterAttributeChange(Attribute att) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeAttributeChange(Attribute att) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}