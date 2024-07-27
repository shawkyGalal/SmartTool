package com.smartValue.database.map.services;

import java.util.TreeMap;

import com.smartValue.database.DataSet;
import com.smartValue.database.DbServices;
import com.smartValue.database.DbTable;
import com.smartValue.database.map.DataTableDisplayProperities;
import com.smartValue.database.map.TableMaintMaster;

public class DataTableDisplayProperServices extends ModuleServices {

	private static final String DEFAULT_ID = "DEFAULT";

	@Override
	public boolean isChanged() {
		return this.getDataSet().isChanged() ; 
	}
	
	public DataTableDisplayProperServices(DbServices pmDbServices) {
		super(pmDbServices);
	}

	@Override
	public void afterModuleRemoved()  {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeModuleRemoved() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public DbTable getDbTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getOrginalQuery() {
		return  TableMaintMaster.getAllItemsQuery(DataTableDisplayProperities.DB_TABLE_NAME , TableMaintMaster.CDB_SCHEMA_OWNER) ;
	}

	@Override
	public Class getPersistentClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCanHaveMultipleInstances() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	private DataTableDisplayProperities defaultDisplayProperties ;
	public DataTableDisplayProperities getDefaultDisplayProperties()
	{
		if ( defaultDisplayProperties == null)
		{
			defaultDisplayProperties  = getTheDefaultOne() ; 
		}
		return defaultDisplayProperties ; 
	}
	
	
	TreeMap<String , DataTableDisplayProperities> displayPropertiesMap  = new TreeMap<String , DataTableDisplayProperities>();
	
	public DataTableDisplayProperities getDataTableDisPropByID(String pm_id , DataSet pm_ds) throws Exception
	{
		DataTableDisplayProperities result ;
		if (pm_id == null || pm_id.equals(""))
		{
			result = getDefaultDisplayProperties() ;
		}
		else 
		{
			result = displayPropertiesMap.get(pm_id) ;
			if (result == null)
			{
			
					DataSet ds = null ; 
					try{
						ds = this.getDbServices().queryForDataSet(this.getOrginalQuery() +  " Where " + DataTableDisplayProperities.ID + " = '"+pm_id+"' ", DataTableDisplayProperities.class) ;
						result = (DataTableDisplayProperities) ds.getPersistantObjectList().get(0);
					}
					catch (Exception e)
					{	
						ds.addNew();
						result  = (DataTableDisplayProperities)ds.getCurrentItem();
						result.setIdValue(pm_id);
						result.save();
						
					}
				
				displayPropertiesMap.put(pm_id, result);
			}
		}
		
		result.setDataSet(pm_ds);
		return result ; 
	}
	
	private DataTableDisplayProperities getTheDefaultOne() 
	{
		DataTableDisplayProperities result = null ; 
		DataSet ds = null ; 
		try{
			ds  =  this.getDbServices() .queryForDataSet(this.getOrginalQuery() +  " Where " + DataTableDisplayProperities.ID + " = '"+DataTableDisplayProperServices.DEFAULT_ID+"' ", DataTableDisplayProperities.class) ; 
			result = (DataTableDisplayProperities) ds.getPersistantObjectList().get(0);
			}
		catch (IndexOutOfBoundsException ex)
		{
			//IF Not Found Create The Default One.
			try {
					ds.addNew();
					result  = (DataTableDisplayProperities)ds.getCurrentItem();
					result.setIdValue(this.DEFAULT_ID);
					result.save();
				} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result ; 
	}
	
	

}
