package com.implex.database;

public interface DatasetEventListener 
{
	public void beforeRefresh(DataSet ds) throws Exception;
	public void afterRefresh(DataSet ds);
	
    public void beforeAddNew(DataSet ds) throws Exception;
    public void afterAddNew(DataSet ds);
    
    public void beforeSaveAll(DataSet ds) throws Exception;
    public void afterSaveAll(DataSet ds);
    
    public void beforeMarkObjectToBeDeleted(PersistantObject pm_po); // throws Exception;
    public void afterMarkObjectToBeDeleted(PersistantObject pm_po); 

    //public void doOtherForSave(DataSet ds);
}
