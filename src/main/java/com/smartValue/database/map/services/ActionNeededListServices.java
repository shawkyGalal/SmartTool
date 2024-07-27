package com.smartValue.database.map.services;

import java.util.ArrayList;

import com.smartValue.database.DataSet;
import com.smartValue.database.DbServices;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.map.ActionNeededList;
import com.smartValue.database.map.TableMaintMaster;

public class ActionNeededListServices extends ModuleServices{

	private ActionNeededList  actionNeededList;
	
	@Override
	public boolean isChanged() {
		return this.getDataSet().isChanged() ; 
	}
	
	public ActionNeededList getActionNeededList() {
		return actionNeededList;
	}

	public void setActionNeededList(ActionNeededList actionNeededList) {
		this.actionNeededList = actionNeededList;
	}


	
	public ActionNeededListServices (DbServices pmDbServices) { 
		super(pmDbServices); 
	 }
	 @Override 
	public String getOrginalQuery()	
	{
		return  "select t.rowid , t.* from "+TableMaintMaster.CDB_SCHEMA_OWNER+".ACTION_NEEDED_LIST t " ;
	}
	@Override
	public Class getPersistentClass()
	{
		return ActionNeededList.class;
	}

	@Override
	public boolean isCanHaveMultipleInstances()
	{
		return false;
	}
	@Override
	public DbTable getDbTable() {
		return new DbTable(TableMaintMaster.CDB_SCHEMA_OWNER ,  ActionNeededList.DB_TABLE_NAME , this.getDbServices());
 }
	@Override
	public void afterModuleRemoved()  {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void beforeModuleRemoved() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
  public  int  getTotalCount()
    {
    int totalCount=0;	
    int postiveValue=0 ;
    DataSet ds	=this.getDataSet();
    ArrayList <PersistantObject>   list     = ds.getPersistantObjectList(); 
    
    for(PersistantObject po:list )
    {
    	ActionNeededList      actionList=    (ActionNeededList) po;
    	if (actionList.getCount()==-1) postiveValue=0;  else  postiveValue=actionList.getCount();   
    	totalCount=totalCount+postiveValue;
    }
    return totalCount;
}



}