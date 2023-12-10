package com.implex.components.model.tree;

import java.util.HashMap;

import com.implex.database.Attribute;
import com.implex.database.AttributeChangeValidator;
import com.implex.database.DbForignKeyArray;
import com.implex.database.DbTable;
import com.implex.database.PersistantObject;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.security.PersistentObjectSecurityControl;
import com.implex.database.map.services.ModuleServicesContainer;
import com.implex.database.trigger.TriggerHandler;
import com.sideinternational.sas.BaseException;
import com.sideinternational.web.swaf.SWAF;

public class PersistantTreeObject extends PersistantObject
{
 /**
	 * 
	 */
private static final long serialVersionUID = 1L;

public String getUniqueId() 
 {
	String value = String.valueOf(this.getAttributeByOrder(0).getValue());
	return value;
	
 }
 
 public String getArabicText() 
 {
	 return (String) this.getAttributeByOrder(1).getValue();
 }
 
 public String getEnglishText() 
 {
	 return (String) this.getAttributeByOrder(2).getValue();
 }
 
 
 public String getParentUniqueCode() 
 {
	 String result = "Unknow Parent" ; 
	 
	 try{
		 Object attValue = this.getAttributeByOrder(3);
		 if ( attValue!= null)
		 {
			 result = attValue.toString();
		 }
	 }
	 catch (Exception e) {}
	 return result; 
 }

	public boolean isUrlType()
	{			
		return true;
	}
	public boolean isNew()
	{
		return false;
	}
 public String getDisplayText() throws BaseException
 {
	String  result = "";
	ModuleServicesContainer msc =  (ModuleServicesContainer) SWAF.getModuleServiceContatiner();
	int loggedUserLang = msc.getDbServices().getLoggedUserLang();
	if (loggedUserLang == 1)
	{
		result = this.getArabicText();
	}
	else 
	{
		result = this.getEnglishText();
	}
	return result;
 }

 
@Override
public AttributeChangeValidator getAttributeChangeValidator(SecUsrDta pmSecUserData, PersistantObject pmPo, String pmKey)
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
public PersistentObjectSecurityControl getSecurityController()
{
	// TODO Auto-generated method stub
	return null;
}

@Override
public DbTable getTable()
{
	// TODO Auto-generated method stub
	return new DbTable("SYS" , "DUAL" , this.getDbService());
}

@Override
public TriggerHandler getTriggerHandler()
{
	// TODO Auto-generated method stub
	return null;
}

public void initialize()
{
	
	
}

	private boolean parentNode = false;
	public void setParentNode(boolean parentNode) {
		this.parentNode = parentNode;
	}
	
	public boolean isParentNode() {
		return parentNode;
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
