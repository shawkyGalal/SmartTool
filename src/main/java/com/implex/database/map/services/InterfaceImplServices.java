package com.implex.database.map.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.model.SelectItem;

import com.implex.database.DbServices;
import com.implex.database.DbTable;
import com.implex.database.PersistantObject;
import com.implex.database.map.SysInterfaceImplementors;
import com.implex.database.map.TableMaintMaster;



public  class InterfaceImplServices  extends ModuleServices {

	//private DbServices dbService ; 

	private static HashMap<String, ArrayList<PersistantObject>>  implementorsList = new HashMap<String, ArrayList<PersistantObject>>();
	
	@Override
	public boolean isChanged() {
		return this.getDataSet().isChanged() ; 
	}
	
	public InterfaceImplServices(DbServices pm_dbServices)
	{
		super (pm_dbServices) ;
	}
	
	/**
	 * get list of implementors of the specified Interface 
	 * @param implementedInterface
	 * @return
	 */
	private ArrayList<PersistantObject> getImplementorsList(String implementedInterface)
	{
		ArrayList<PersistantObject> result = implementorsList.get(implementedInterface) ; 
		if( result == null)
		{
			ArrayList<PersistantObject> newItems= null ; 
			try {
				newItems = (ArrayList<PersistantObject>) this.getDbServices().queryForList(
						TableMaintMaster.getAllItemsQuery(SysInterfaceImplementors.DB_TABLE_NAME, TableMaintMaster.CDB_SCHEMA_OWNER) +" where t.IMP_INTERFACE = '"+implementedInterface+"'"
						,SysInterfaceImplementors.class);
				implementorsList.put(implementedInterface, newItems);
				result =  newItems;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * get the implementer class by code
	 * @param implCode : the implementor class code
	 * @param implInterface : the implemented class 
	 * @return
	 */
	public SysInterfaceImplementors getImplementorByCode(String implCode, String implInterface)
	{
		ArrayList<PersistantObject> il = getImplementorsList(implInterface) ;
		if (il != null)
		{
			for(PersistantObject po : il)
			{
				SysInterfaceImplementors sysImpl = (SysInterfaceImplementors) po;
				if(sysImpl.getImpCodeValue().toString().equals(implCode))
					return sysImpl;
			}
		}
		return null;
	}

	public ArrayList<SelectItem> getAllAvailableImpls(String implementedInterface) {
		ArrayList<SelectItem> result = new ArrayList<SelectItem>();
		List<PersistantObject> implsList = this.getImplementorsList(implementedInterface);
		for (PersistantObject po : implsList)
		{
			SysInterfaceImplementors sysImpl = (SysInterfaceImplementors) po;
			result.add(new SelectItem(sysImpl.getImpCodeValue() , (String) sysImpl.getImpDescAutoLang().getValue() ) );
		}
		return result;
	}
	
	public ArrayList<SelectItem> getAllAvailableImplsForPickList(String implementedInterface) {
		ArrayList<SelectItem> result = new ArrayList<SelectItem>();
		List<PersistantObject> implsList = this.getImplementorsList(implementedInterface);
		for (PersistantObject po : implsList)
		{
			SysInterfaceImplementors sysImpl = (SysInterfaceImplementors) po;
			result.add(new SelectItem(sysImpl , (String) sysImpl.getImpDescAutoLang().getValue() ) );
		}
		return result;
	}
//	public ArrayList<PersistantObject> getAllAvailableImpls(String implementedInterface) {
//		return this.getImplementorsList(implementedInterface); 
//	}

	@Override
	public DbTable getDbTable() {
		// TODO Auto-generated method stub
		return new DbTable(TableMaintMaster.CDB_SCHEMA_OWNER ,  SysInterfaceImplementors.DB_TABLE_NAME , this.getDbServices());
	}

	@Override
	public String getOrginalQuery() {
		// TODO Auto-generated method stub
		return "select * from " + SysInterfaceImplementors.DB_TABLE_NAME + " Where 1 <> 1";
	}

	@Override
	public Class getPersistentClass() {
		// TODO Auto-generated method stub
		return SysInterfaceImplementors.class;
	}

	@Override
	public boolean isCanHaveMultipleInstances() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void afterModuleRemoved()  {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeModuleRemoved() throws Exception {
		// TODO Auto-generated method stub
		
	}
	

}
