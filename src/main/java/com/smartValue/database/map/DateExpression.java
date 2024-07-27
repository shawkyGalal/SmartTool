package com.smartValue.database.map;

import java.util.HashMap;

import com.smartValue.database.Attribute;
import com.smartValue.database.AttributeChangeValidator;
import com.smartValue.database.DbForignKeyArray;
import com.smartValue.database.DbServices;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.map.security.PersistentObjectSecurityControl;
import com.smartValue.database.trigger.TriggerHandler;


public class DateExpression extends PersistantObject{
	
	/* Dynamically Generated Mapping Class 
	 * By : Shawky Foda 
	 */
	
	private Attribute dateValue;
	
	public DateExpression() 
	{

	}

	@Override
	public DbTable getTable()
	{
		return new DbTable(TableMaintMaster.CDB_SCHEMA_OWNER  , "DATE_EXPRSSIONS" , this.getDbService());
	}
	 public void setId(java.math.BigDecimal   pm_Id)
	{
		this.getAttribute("ID").setValue(pm_Id);
		try {
			DbServices ds;
			ds = this.getDbService();
			DateExpression me = (DateExpression) ds.queryForList(
					"select * from Date_Expressions where id = "
							+ pm_Id.intValue(), DateExpression.class).get(0);
			this.setDescription(me.getDescription());
			this.setExpression(me.getExpression());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	 public java.math.BigDecimal getId()
	{
		 return (java.math.BigDecimal) this.getAttribute("ID").getValue();
	}

	 public void setExpression(java.lang.String   pm_EXPRESSION)
	{
		 this.getAttribute("EXPRESSION").getValue() ;
	}
	 
	 public java.lang.String getExpression()
	{
		 return (java.lang.String) this.getAttribute("EXPRESSION").getValue();
	}

	 
	 public Attribute getDateValue() throws Exception
	{
		if (this.dateValue == null && this.getExpression() != null)
		{
		 DbServices ds;
		 ds = this.getDbService(); 
		 this.dateValue =  ds.queryForList(this.getExpression()).get(0).getAttribute(0);
		 
		}
		return this.dateValue;
	}

	 public void setDescription(java.lang.String   pm_DESCRIPTION)
	{
		 this.getAttribute("DESCRIPTION").setValue( pm_DESCRIPTION );
	}
	 
	 public java.lang.String getDescription()
	{
		 return (java.lang.String) this.getAttribute("DESCRIPTION").getValue();
	}

	@Override
	public PersistentObjectSecurityControl getSecurityController()
	{
		return null;
	}

	@Override
	public TriggerHandler getTriggerHandler()
	{
		return null;
	}

	@Override
	public AttributeChangeValidator getAttributeChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po,
			String pm_key)
	{
		return null;
	}

	@Override
	protected HashMap<String, DbForignKeyArray> getChildrenForignKeys()
	{
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
