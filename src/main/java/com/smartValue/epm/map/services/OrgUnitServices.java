package com.smartValue.epm.map.services;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.DbServices;
import com.smartValue.database.DbTable;
import com.smartValue.database.map.services.ModuleServices;
import com.smartValue.database.map.services.ModuleServicesContainer;
import com.smartValue.epm.map.OrgUnit;

public class OrgUnitServices extends ModuleServices{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public OrgUnitServices (DbServices pmDbServices) { 
		super(pmDbServices); 
	 }
	 @Override 
	public String getOrginalQuery()	
	{
		return  "select t.rowid , t.* from JCCS.ORG_UNIT t " ;
	}
	@Override
	public Class getPersistentClass()
	{
		return OrgUnit.class;
	}

	@Override
	public boolean isCanHaveMultipleInstances()
	{
		return false;
	}
	@Override
	public DbTable getDbTable() {
		return new DbTable(OrgUnit.DB_TABLE_OWNER , OrgUnit.DB_TABLE_NAME , this.getDbServices());
 }
	 @Override
	 public void afterModuleRemoved() {
	 	// TODO Auto-generated method stub
	 }
	 @Override
	 public void beforeModuleRemoved() throws Exception {
	 	// TODO Auto-generated method stub
	 }
	@Override
	public boolean isChanged() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public OrgUnit getOrgUnitByID(String m_orgUnitId)
	{
		OrgUnit orgUnit = null ; 
		String query = this.getOrginalQuery() + " Where " + OrgUnit.ORG_UNIT_ID + " = '" + m_orgUnitId + "'" ;
		 orgUnit = (OrgUnit) this.getDbServices().queryForDataSet(query, com.smartValue.epm.map.OrgUnit.class).getPersistantObjectList().get(0) ;
		return orgUnit ; 
	}
	//private static OrgUnitServices orgUnitServices ;  
	public static OrgUnitServices getInstance(String selectedEnv , int userLang)
	{	OrgUnitServices orgUnitServices = null ; 
		
		orgUnitServices = new OrgUnitServices (ApplicationContext.generateModuleServicesContainer(selectedEnv , userLang).getDbServices() ) ;
		
		return orgUnitServices ; 
	}
	
}