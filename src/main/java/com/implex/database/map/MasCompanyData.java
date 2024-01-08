package com.implex.database.map;

import java.util.HashMap;

import com.implex.database.Attribute;
import com.implex.database.AttributeChangeValidator;
import com.implex.database.DataSet;
import com.implex.database.DbForignKeyArray;
import com.implex.database.PersistantObject;
import com.implex.database.audit.AuditInDbTriggerHandler;
import com.implex.database.map.auto._MasCompanyData;
import com.implex.database.map.security.PersistentObjectSecurityControl;
import com.implex.database.trigger.TriggerHandler;
import com.sideinternational.sas.license.entity.Certificate;

public class MasCompanyData extends _MasCompanyData {
	
	private static HashMap<String, DbForignKeyArray> childrenForignKeys;
	private DataSet sysParamDs = null ;
	
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
 
	 private static TriggerHandler triggerHandler = null; 
	@Override
	public TriggerHandler getTriggerHandler() 
	{
		 boolean auditable = this.getTableMaintMaster().getAuditable().getBooleanValue(); 
		 if (triggerHandler == null && auditable ) 
			{ 
				triggerHandler = new AuditInDbTriggerHandler(); 
			} 
		 return triggerHandler; 
	}
	@Override 
	public AttributeChangeValidator getAttributeChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key)
	{ 
	 return null;
	}
	 public void initialize() 
	 { 
		//Write your own initialization code here this will help you greatly improve performance especially
		// apply our standard rule < Minimise code inside any getXyz() method - simply return object -  > 
	 }
	@Override
	public void afterAttributeChange(Attribute att) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void beforeAttributeChange(Attribute att) throws Exception {
		// TODO Auto-generated method stub
		
	}
	  
	public DataSet getSysParams()
	{
		
		if ( this.sysParamDs == null )
		{
			
			String query = "Select t.* , t.rowid \n From Support" +"." + SysParams.DB_TABLE_NAME + " t \n Where t.COMPANY_ID = " + this.getCmpIdValue() ;   
			sysParamDs= this.getDbService().queryForDataSet(query,SysParams.class) ;
		}
		return sysParamDs ; 
	}
	
	DataSet users ; 
	public DataSet getUsers(boolean m_refresh)
	{
		if ( this.users == null || m_refresh)
		{
			
			String query = "Select t.* , t.rowid \n From ICDB" +"." + SecUsrDta.DB_TABLE_NAME + " t \n Where t."+SecUsrDta.USR_CMP_ID+" = " + this.getCmpIdValue() ;   
			users= this.getDbService().queryForDataSet(query,SecUsrDta.class) ;
		}
		return users ;
	}
	DataSet orgUnits ; 
	public DataSet getOrgUnits(boolean m_refresh)
	{
		if ( this.orgUnits == null || m_refresh)
		{
			
			String query = "Select t.* , t.rowid \n From JCCS" +"." + OrgUnit.DB_TABLE_NAME + " t \n Where t.COMPANY_ID = " + this.getCmpIdValue() ;   
			orgUnits= this.getDbService().queryForDataSet(query,OrgUnit.class) ;
		}
		return orgUnits ;
	}
	/*
	public boolean isUserExceeds(Certificate cert)
    {
    	boolean result = false ; 
    	int companyActualUsersCount =this.getUsers(true).getPersistantObjectList().size() ; 
    	int certUsersCount = cert.getNumberOfUsers() ; 
    	
    	result =  companyActualUsersCount > certUsersCount ; 
    	
    	return result ; 
    }
    
    public boolean isOrgUnitsExceeds(Certificate cert)
    {
    	boolean result = false ; 
    	int companyActualOrgUnitsCount =this.getOrgUnits(true).getPersistantObjectList().size() ; 
    	int certOrgUnitsCount = cert.getLicensedOrgUnitsCount() ; 
    	
    	result =  companyActualOrgUnitsCount > certOrgUnitsCount ;
    	return result ; 
    }
    */
    PersistantObject masSystem ; 
	public PersistantObject getMasSystem()
	{
		if (masSystem == null) 
		{
			String query = "Select t.* , t.rowid \n From ICDB.MAS_SYSTEM " + " t \n Where t.SYSTEM_ID = " + this.getSystemIdValue() ;   
			masSystem = this.getDbService().queryForDataSet(query, null).getPersistantObjectList().get(0) ;
		}
		return masSystem ; 
	}
	
}