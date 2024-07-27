package com.smartValue.database.map.services;
import com.smartValue.database.DbServices;
import com.smartValue.database.DbTable;
import com.smartValue.database.map.TableMaintMaster;
import com.smartValue.database.map.Tasks;
import com.smartValue.event.logging.Console;
public class TasksServices extends ModuleServices{

	@Override
	public boolean isChanged() {
		return this.getDataSet().isChanged() ; 
	}
	
	public TasksServices(DbServices pmDbServices) {
		super(pmDbServices);
	}
	@Override 
	public String getOrginalQuery()	
	{
		return  "select t.rowid , t.* from "+TableMaintMaster.CDB_SCHEMA_OWNER +".TASKS t where 1<> 1 " ;
	}
	@Override
	public Class getPersistentClass()
	{
		return Tasks.class;
	}

	@Override
	public boolean isCanHaveMultipleInstances()
	{
		return false;
	}
	@Override
	public DbTable getDbTable() {
		return new DbTable(TableMaintMaster.CDB_SCHEMA_OWNER , Tasks.DB_TABLE_NAME , this.getDbServices());
 }

	@Override
	public void beforeModuleRemoved() throws Exception 
	{
		Console.log("\nTasks Module Will Removed ", this.getClass());
				
	}
	@Override
	public void afterModuleRemoved()  {
		Console.log("\nTasks Module Removed Successfully ", this.getClass());
		
	}

	
}