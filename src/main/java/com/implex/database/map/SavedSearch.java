package com.implex.database.map;


import java.util.HashMap;
import java.util.List;

import com.implex.database.ApplicationContext;
import com.implex.database.Attribute;
import com.implex.database.AttributeChangeValidator;
import com.implex.database.ClobAttribute;
import com.implex.database.DataSet;
import com.implex.database.DbForignKeyArray;
import com.implex.database.DbTable;
import com.implex.database.PersistantObject;
import com.implex.database.map.auto._SavedSearch;
import com.implex.database.map.security.PersistentObjectSecurityControl;
import com.implex.database.trigger.TriggerHandler;

public class SavedSearch extends _SavedSearch {
	
	private  static final String SAVED_SEARCH_DETAILS = "SAVED_SEARCH_DETAILS";
	private QueryExecuter queryExecuter ; 

	public DbTable getTable()
	{
		return new DbTable(TableMaintMaster.CDB_SCHEMA_OWNER  ,DB_TABLE_NAME , this.getDbService()); 
	}
	
	public DataSet getSavedSearchDetails() throws Exception {
		String orderByClause = " order by " + SavedSearchDetail.ORDERS ; 
		return this.getChildrenDataSet(SAVED_SEARCH_DETAILS,SavedSearchDetail.class, false , orderByClause);
	}
	
	public List<PersistantObject> getSavedSearchDetailsList() throws Exception
	{
		return this.getSavedSearchDetails().getPersistantObjectList();
	}
	
	private static HashMap<String, DbForignKeyArray> childrenForignKeys;
	@Override
	protected HashMap<String, DbForignKeyArray> getChildrenForignKeys()
	{
		if (childrenForignKeys == null)
		{
			childrenForignKeys = new HashMap<String, DbForignKeyArray>();
			DbForignKeyArray xx = this.getTableMaintMaster().getDbForignKey("SAVED_SEARCH_DETAILS_FK1");
			childrenForignKeys.put(SavedSearchDetail.DB_TABLE_NAME,  xx ) ;
		}
		return childrenForignKeys;
	}
	
//	private static DbForignKeyArray getForignKeyForSavedSearchDetails() {
//		
//		return new DbForignKeyArray ( new DbForignKey[]{new DbForignKey( SavedSearch.TABLE_NAME ,SavedSearchDetail.TABLE_NAME ),
//				new DbForignKey( SavedSearch.SEARCH_NAME ,SavedSearchDetail.SEARCH_NAME ) } , true );
//        
//	}
	@Override
	public PersistentObjectSecurityControl getSecurityController()
	{
		return new SavedSearchSecurityControler();
	}
	@Override
	public TriggerHandler getTriggerHandler()
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public AttributeChangeValidator getAttributeChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po,
			String pm_key)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	String ownerName;
	
	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public void copyMeSavedSearchToUser()
	{
		TableMaintMaster tmm	=(TableMaintMaster)this.getParent().getPersistantObject();
		DataSet			ds	=tmm.getLoggedUserSavedSearchDS();
		
		try {
			ds.addNew();
			SavedSearch		saveSearchObj		= 	(SavedSearch)(ds.getCurrentItem());
			saveSearchObj.setSearchNameValue(this.getSearchNameValue());
			saveSearchObj.setPublicSearchValue(this.getPublicSearchValue());
			saveSearchObj.setTableNameValue(this.getTableNameValue());
			saveSearchObj.setDescriptionValue(this.getDescriptionValue());
			saveSearchObj.setOwnerValue(this.getOwnerName());
			saveSearchObj.save();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void beforeUpdate() throws Exception
	{
		if (this.getOwnerValue()==null || this.getOwnerValue().equalsIgnoreCase(""))
		{
			this.setOwnerValue(this.getDbService().getLoggedUser().getUsrNameValue());
		}
	}


	public QueryExecuter getQueryExecuter() {
		
		return queryExecuter;
	}
	
	public void initialize()
	{
		com.implex.database.Attribute queryBody = this.getQueryBody() ;
		if (queryBody != null && queryBody instanceof ClobAttribute)
		{
			queryExecuter = new QueryExecuter((ClobAttribute) queryBody, this.getDbService());
		}
	}
	
	public void beforeInsert()
	{
		String loggedUserName = this.getDbService().getLoggedUser().getUsrNameValue();
		this.setOwnerValue(loggedUserName);
	}

	@Override
	public void afterAttributeChange(Attribute att) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeAttributeChange(Attribute att) throws Exception {
		String key = att.getKey() ; 
		if ( key.equals(SavedSearch.QUERY_BODY) )
		{
			SavedSearch ss =  (SavedSearch) att.getParentPersistantObject() ; 
			ss.initialize();
		}

	}
}
