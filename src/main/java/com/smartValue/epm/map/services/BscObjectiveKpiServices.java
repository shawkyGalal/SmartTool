package com.smartValue.epm.map.services;

import com.smartValue.database.DbServices;
import com.smartValue.database.DbTable;
import com.smartValue.database.map.services.ModuleServices;
import com.smartValue.epm.map.BscObjectiveKpi;

public class BscObjectiveKpiServices extends ModuleServices{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BscObjectiveKpiServices (DbServices pmDbServices) { 
		super(pmDbServices); 
	 }
	 @Override 
	public String getOrginalQuery()	
	{
		return  "select t.rowid , t.* from GIHAZ.BSC_OBJECTIVE_KPI t " ;
	}
	@Override
	public Class<BscObjectiveKpi> getPersistentClass()
	{
		return BscObjectiveKpi.class;
	}

	@Override
	public boolean isCanHaveMultipleInstances()
	{
		return false;
	}
	@Override
	public DbTable getDbTable() {
		return new DbTable(BscObjectiveKpi.DB_TABLE_OWNER , BscObjectiveKpi.DB_TABLE_NAME , this.getDbServices());
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
	
}