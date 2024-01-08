package com.implex.database.map;

import java.util.HashMap;

import com.implex.database.ApplicationContext;
import com.implex.database.Attribute;
import com.implex.database.AttributeChangeValidator;
import com.implex.database.Condition;
import com.implex.database.DbForignKeyArray;
import com.implex.database.DbServices;
import com.implex.database.PersistantObject;
import com.implex.database.map.auto._SavedSearchDetails;
import com.implex.database.map.security.PersistentObjectSecurityControl;
import com.implex.database.trigger.TriggerHandler;


public class SavedSearchDetail extends _SavedSearchDetails

{
	public SavedSearchDetail()
	{
		
	}
	public SavedSearchDetail(TableMaintDetails tmd , String pm_savedSearchName)
	{
		this.setOperatorValue(tmd.getCondition().getOperation());
		this.setLogicalOperationValue(tmd.getLogicalOperation());
		this.setDateExpressionIdValue( tmd.getCondition().getDateExpressionId());
		String columnName   =  tmd.getColumnNameValue();
		this.setColumnNameValue(columnName);
		this.setSearchNameValue(pm_savedSearchName);
		Attribute att = tmd.getCondition().getAttribute();
		Object attValue = att.getValue();
		if (attValue!=null)
		{
			if (attValue instanceof java.lang.String)
			{
				this.setAttributeValueValue(attValue.toString());
	
			}
			else if (attValue instanceof java.sql.Timestamp)
			{
				this.setAttributeDateValueValue((java.sql.Timestamp) attValue);
			}
		}
	}
	 
	

	

	
	public TableMaintDetails getTableMaintDetail() throws Exception
	{
		TableMaintDetails tmd = null; 
		DbServices ds = this.getDbService();
		tmd = this.getDbService().getModuleServiceContainer().getTableMaintServices().getTableMaintDetails(this.getTableNameValue(),this.getColumnNameValue());
		 if (tmd != null)
		 {
			 tmd.setLogicalOperation((String) this.getLogicalOperation().getValue());
			 tmd.setSelectedOperation(this.getOperatorValue());
			 Object attributeValue = (this.getAttributeValue() == null)? this.getAttributeDateValue().getDateValue() : this.getAttributeValueValue() ; 
			 Attribute att = new Attribute(tmd.getColumnNameValue(), attributeValue, tmd );
			 Condition cond = new Condition( this.getOperatorValue() , att);
			 cond.setDateExpressionId(this.getDateExpressionIdValue());
			 tmd.setCondition(cond);
		 }
		 return tmd;
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
	public AttributeChangeValidator getAttributeChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po,
			String pm_key)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected HashMap<String, DbForignKeyArray> getChildrenForignKeys()
	{
		// TODO Auto-generated method stub
		return null;
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
