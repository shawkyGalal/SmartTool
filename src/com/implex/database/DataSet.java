package com.implex.database;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import javax.faces.model.SelectItem;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import Support.XLSUploading.XLSUpload;
import Support.mail.EmailMessage;
import Support.mail.MailSender;

import com.implex.database.audit.AuditInDbTriggerHandler;
import com.implex.database.map.DataTableDisplayProperities;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.SysMsg;
import com.implex.database.map.TableMaintDetails;
import com.implex.database.map.TableMaintMaster;
import com.implex.database.map.security.PersistentObjectSecurityControl;
import com.implex.database.map.services.DataTableDisplayProperServices;
import com.implex.database.map.services.ModuleServicesContainer;
import com.implex.database.map.services.SysMsgServices;
import com.implex.database.map.services.SysParamsServices;
import com.implex.database.map.services.TableMaintMasterServices;
import com.implex.database.trigger.TriggerHandler;
import com.implex.event.logging.Console;
import com.sideinternational.sas.BaseException;
import com.sideinternational.web.swaf.SWAF;


public class DataSet implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<PersistantObject> persistantObjectList ; 
	ArrayList<PersistantObject> toBeDeletedList = new ArrayList<PersistantObject>();
	private ArrayList<PersistantObject> newList = new ArrayList<PersistantObject>();
	private ArrayList<PersistantObject> updatedList = new ArrayList<PersistantObject>();

	private DbServices dbService;
	private DataTableDisplayProperities dataTableDisplayProperities ;  
	public static final int DATA_TABLE_DISPLAY_PROPERITES_ID = 1;
	
	
	private Query query = null;
	/**
	 * Type of Persistent Objects in this DataSet
	 */
	private Class<PersistantObject> persistantClass;
	/**
	 * A Pointer for the current row
	 */
	private  int currentRow = 0;
	private int displayedCurrentRow = 1;
	private ParentPersistantObject parent;
	private ArrayList<Header> headersFromRs ;
	/**
	 * A DataSet Contains Only Updated Objects
	 */
	private boolean filterApplied; 
	private TableMaintMaster tableMaintMaster ; 
	private HashMap<String, Object> properties ;
	
//	private void initialize (DataSet pm_freshDataSet , boolean keepOrignalQuery)
//	{
//		this.persistantObjectList = pm_freshDataSet.getPersistantObjectList();
//		this.toBeDeletedList = new ArrayList<PersistantObject>();
//		if (!keepOrignalQuery)
//		{
//		this.query = pm_freshDataSet.getQuery();
//		}
//		this.dbService = pm_freshDataSet.getDbService();
//		this.persistantClass = pm_freshDataSet.getPersistantClass();
//		this.setCurrentRow (0);
//		this.displayedCurrentRow = 1;
//		this.tableMaintMaster = pm_freshDataSet.getTableMaintMaster();
//		this.newList = new ArrayList<PersistantObject>() ; 
//		this.poToBeExplored = null ;
//		this.saveAllCommandList = new ArrayList<Command>();
//	}
	public int getCurrentRow() {
		currentRow = (currentRow >= this.getPersistantObjectList().size() )? this.getPersistantObjectList().size()-1 : currentRow;
		currentRow = (currentRow < 0 )? 0 : currentRow;
		displayedCurrentRow = currentRow + 1;
		return currentRow;
	}

	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
		displayedCurrentRow = currentRow + 1;
		this.setSelectedObject(this.getCurrentItem());
	}
	
	public PersistantObject getCurrentItem() {
		if (this.getPersistantObjectList().isEmpty()) return null;
		PersistantObject currentItem = this.getPersistantObjectList().get(this.getCurrentRow()); 
		return currentItem;
	}

	public void setCurrentItem(PersistantObject pm_currentItem) {
		
		int index = this.getPersistantObjectList().indexOf(pm_currentItem) ;
		if (index < 0 )
		{
			ModuleServicesContainer msc =  (ModuleServicesContainer) (SWAF.getModuleServiceContatiner());
			msc.getMessageCommnunicatorService().sendMessageToUser("Object Not Found in the List");
		}		
		else {this.setCurrentRow(index); }
	}
	
	
	public void useCurrentItem() throws Exception
	{
		if (this.getCurrentItem() != null)
		this.getPersistantObjectList().set(this.getCurrentRow(), this.getCurrentItem());
	}
	
	private void initialize(DbServices dbs)
	{
		this.toBeDeletedList = new ArrayList<PersistantObject>();
		this.newList = new ArrayList<PersistantObject>() ;
		this.updatedList = new ArrayList<PersistantObject>() ;
		this.dbService = dbs;
		this.currentRow = 0;
		this.displayedCurrentRow =1;
		this.saveAllCommandList = new ArrayList<Command> ();
	}
	private void initialize(ArrayList<PersistantObject> pm_pol, DbServices dbs)
	{
		this.persistantObjectList = pm_pol;
		this.selectedObject = null; 
		for (PersistantObject po : pm_pol) //check for enhancement
		{
			po.setParentDataSet(this);
			if (this.selectedObjectRowId != null && po.getObjectRowId() != null && po.getObjectRowId().equalsIgnoreCase(this.selectedObjectRowId)) 
			{
				this.selectedObject = po; 
			}
		}
	
		this.initialize(dbs);
		
		
	}
	public DataSet(ArrayList<PersistantObject> pm_pol,  DbServices dbs) 
	{
		initialize(pm_pol , dbs);
	}
//	private DataSet(String pm_sql ,  Class<PersistantObject> pm_class , DbServices dbs , ParentPersistantObject pm_parent) throws Exception
//	{
//		initialize(dbs.queryForList(pm_sql, null , pm_class , pm_parent) , dbs);
//		this.setParent(pm_parent);
//		this.setQuery(new Query(pm_sql));
//		this.setPersistantClass(pm_class);
//	}
	public ParentPersistantObject getParent()
	{
		return this.parent;
	}
	protected void setParent(ParentPersistantObject pm_parent)
	{
		this.parent = pm_parent;
	}

	public ArrayList<PersistantObject> getPersistantObjectList() {
		return persistantObjectList;
	}
	public void setPersistantObjectList(ArrayList<PersistantObject> persistantObjectList) {
		this.persistantObjectList = persistantObjectList;
	}
	public ArrayList<PersistantObject> getToBeDeletedList() {
		return toBeDeletedList;
	}
	
//	public DataSet getTobeDeletedDataSet()
//	{
//		DataSet tobeDeleted = new DataSet(this.getToBeDeletedList() , this.getDbService());
//		tobeDeleted.setTableMaintMaster(this.getTableMaintMaster());
//		tobeDeleted.setPersistantClass(this.getPersistantClass());
//		return tobeDeleted; 
//	}
	public void setToBeDeletedList(ArrayList<PersistantObject> pm_toBeDeletedList) {
		this.toBeDeletedList = pm_toBeDeletedList;
	}
	

	
	public String  remove()
	{
		remove(this.currentRow);
		return "SUCCESS";
	}
	//TODO Sakr... You Still Using Index to point to objects by index ... however we can directly point to object itself... thanks to richfaces ... I'm worried
	public void removeAndSave(int index)
	{
		remove(index);
		this.saveAll();
		
	}

	public boolean remove(int index)
	{ 
		PersistantObject poToBeRemoved =  this.persistantObjectList.get(index) ; 
		return this.remove(poToBeRemoved) ;
	
	}
	
	public void markObjectToBeDeleted(PersistantObject pm_po) //throws Exception
	{
		DatasetEventListener dsel = this.getDataSetEventListner() ; 
		
		if (dsel != null)
		{
			dsel.beforeMarkObjectToBeDeleted(pm_po) ;			
		}
		
		this.markChildstoBeDeleted(pm_po);
		if ( pm_po.isContainsObjectKey())
		{
			this.toBeDeletedList.add(pm_po);
		}
		else
		{
			this.getNewList().remove(pm_po);
			
		}
		ParentPersistantObject parent = this.getParent() ;
		if (parent != null)
		{
			PersistantObject parentPo =  parent.getPersistantObject();
			parentPo.informParntDS();
		}
		this.persistantObjectList.remove(pm_po);
		synchronizeDisplayedRow();
		if (this.currentRow == persistantObjectList.size() )// the last item
				   currentRow--;	
		if(this.currentRow < 0)
				currentRow = 0;
		this.getDsCalculator().reset();
		this.resetSaveAllCommandList() ; 
		if (dsel != null)
		{
			dsel.afterMarkObjectToBeDeleted(pm_po) ;
		}
	}
	
	private void markChildstoBeDeleted(PersistantObject pmPo) {

		HashMap<String, DataSet> childern = pmPo.getChildernDataSetMap();

		Iterator<String> it = childern.keySet().iterator();
		
		while (it.hasNext()) {
			DataSet childDataSet = childern.get(it.next());
			DbForignKeyArray fk = childDataSet.getForignKeys() ;
			if (fk.isOnCascadeDelete())
			{
				childDataSet.markAllToBeDeleted() ;
			}
			
		}
		this.getUpdatedList().remove(pmPo);	
	}
	public boolean  remove (PersistantObject pm_po)
	{
		try {
			markObjectToBeDeleted(pm_po) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return true;
	
	}
	
	public boolean markAllToBeDeleted()
	{
		boolean allRemoved = true;
		int size =  this.getPersistantObjectList().size() ;
		for (int i=0; i< size; i++)
		{	
			allRemoved &= this.remove(0);
		}
		this.currentRow = 0;
		return allRemoved;
	}


	public boolean isCurrentItemChanged() throws Exception
	{
		return this.getCurrentItem().isNeedSave();
	}
	
	public boolean isCanBeMarkedAsDeleted()
	{
		return this.getPersistantObjectList().isEmpty();
	}
	
	public boolean isChanged()
	{

		return ( ! this.getUpdatedList().isEmpty() 
				|| ! this.getToBeDeletedList().isEmpty())
				|| ! this.getNewList().isEmpty();
	}

	
	public MessagesCommnuicatorService getMessageCommnunicatorService()
	{
		return this.getDbService().getLoggedUser().getModuleServiceContainer().getMessageCommnunicatorService() ; 
	}
	public void save()  {
		try{
		this.save(this.getCurrentItem());
		}
		catch (Exception e) {
			this.getMessageCommnunicatorService().sendExceptionToUser(e);
		}
		
	}
	/**
	 * Saves Item of this DataSet
	 * @throws BaseException
	 */
	private void save(PersistantObject pm_currentItem) throws Exception {
		
		pm_currentItem.save();
	}
	private DatasetEventListener getDataSetEventListner()
	{
		DatasetEventListener result = null ;
		TableMaintMaster tmm =  this.getTableMaintMaster() ;
		
		if (tmm != null)
		{
			result = tmm.getDataSetEventListner();
		}
		return result ; 
	}
	/**
	 * Saves all Items of this DataSet
	 */
	public void saveAll() 
	{
		this.getMessageCommnunicatorService().startNewTransaction() ;
		DatasetEventListener dsel = this.getDataSetEventListner() ; 
		
		if (! this.isChanged())
		{
			String displayText = ((String) this.getTableMaintMaster().getObjectNameAutoLang().getValue())+" DataSet Has No Changes to be Saved";
			this.getMessageCommnunicatorService().sendMessageToUser(displayText) ; 
			return ; 
		}
		
		if (dsel != null)
			{
				try
				{ 
					dsel.beforeSaveAll(this);
				}
				catch (Exception e) {
					this.getMessageCommnunicatorService().sendExceptionToUser(e);
					return ; 
				}
			}
		
		try 
		{

			Batch batch = new Batch(this.getSaveAllCommandList());
		
			if (! batch.getAllInvalidAttributes().isEmpty() )
			{
				this.getMessageCommnunicatorService().sendValidationResultsToUser(batch.getInvalidResults());
				return ; 
			}
			BatchExecuteResult result = this.dbService.executeBatch(batch);
			ArrayList<Exception> es =  (ArrayList<Exception>) result.getExceptions() ; 	
			if (es.isEmpty()&&  this.query != null)
				{
					TableMaintMaster tmm = this.getTableMaintMaster() ; 
					if (tmm != null && tmm.getReloadDatasetAfterSaveall().getBooleanValue())
					{ 
						this.restore();
					}
					else 
					{
						this.initialize(this.dbService) ;
						for (PersistantObject po : this.getPersistantObjectList())
						{
							po.onSuccessfulUpdate();
						}
					}
					
				}
			if (dsel != null)	
				  dsel.afterSaveAll(this);	

		} catch (Exception e) {
			this.getMessageCommnunicatorService().sendExceptionToUser(e);
		}
	}
	
	ArrayList<Command> saveAllCommandList ;
	public ArrayList<Command> getSaveAllCommandList()
	{
		if (saveAllCommandList == null)
		{
			this.refreshSaveAllCommandList(); 
		}
		return saveAllCommandList ; 
	}
	protected void refreshSaveAllCommandList()
	{
		this.saveAllCommandList = calcSaveAllCommandList();
	}
	private ArrayList<Command> calcSaveAllCommandList()
	{
		ArrayList<Command> result = new ArrayList<Command> ();
		Iterator<PersistantObject> it1 = this.getToBeDeletedList().iterator();
		while (it1.hasNext()) 
		{
			PersistantObject potoBeDeleted = it1.next() ;
			result.addAll(potoBeDeleted.getDeleteCommendList());	
		}
		Iterator<PersistantObject> it2 = this.getUpdatedList().iterator();
		
		while (it2.hasNext()) 
		{
			PersistantObject po = it2.next() ; 
			result.addAll(po.getSaveCommandList());	
		}
		
		Iterator<PersistantObject> it3 = this.getNewList().iterator();
		
		while (it3.hasNext()) 
		{
			PersistantObject po = it3.next() ; 
			result.addAll(po.getSaveCommandList());
			
		}
		
		return result ; 
	}
	
	public void  resetSaveAllCommandList()
	{
		this.saveAllCommandList = null ; 
	}
	
	public void saveDeletedOnly()
	{
		Iterator<PersistantObject> it2 = this.getToBeDeletedList().iterator();
		while (it2.hasNext()) {
			try {
				this.getDbService().delete(it2.next());
			} catch (Exception e) {
				this.getMessageCommnunicatorService().sendExceptionToUser(e);
				e.printStackTrace();
			}
		}
	}
	
	
	public void restore() throws Exception
	{
		DataSet freshDataSet = this.dbService.queryForDataSet(this.getQuery(),this.getQuery().getArgsParameter(), this.getPersistantClass(),this.getParent());
		initialize(freshDataSet.getPersistantObjectList() , this.getDbService());
		if (this.getParent() != null && this.getParent().getPersistantObject() != null )
		{	this.getParent().getPersistantObject().informParntDS(); }
	}
	/**
	 * Ignores all User Changes and Reload the DataSet again from the Persistent source.
	 * @throws BaseException
	 */
	public void ignoreChanges() throws Exception {
		if (this.query != null)    
		{
			DatasetEventListener drl = this.getDataSetEventListner() ; 
			if (drl != null)
				drl.beforeRefresh(this) ;
			restore();
			if (drl != null)
				drl.afterRefresh(this) ;
		}
	}
	
	public void revertAll()
	{
		List<PersistantObject> newObjects = new ArrayList<PersistantObject>(); ;
		ArrayList<PersistantObject> allPos = this.getPersistantObjectList() ;
		for(int i = 0 ; i < allPos.size() ; i++)
			{
				PersistantObject po = allPos.get(i);
				if (po.isContainsObjectKey())
					po.revert();
				else newObjects.add(po);
			}
		
		this.getPersistantObjectList().removeAll(newObjects);
		for(PersistantObject po : this.getToBeDeletedList())
		{	
			this.getPersistantObjectList().add(po);
		}
		this.selectedObject = null ; 
		this.toBeDeletedList= new ArrayList<PersistantObject>();
		this.updatedList = new ArrayList<PersistantObject>();
		this.newList = new ArrayList<PersistantObject>();
		this.saveAllCommandList = new ArrayList<Command> ();
	}
	
	
	
	public PersistantObject getPointerItem()
	{
		if (this.getPersistantObjectList().isEmpty()) 
			return new PersistantObject()
		{	
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			TriggerHandler triggerHandler = new AuditInDbTriggerHandler();
			
			@Override
			public PersistentObjectSecurityControl getSecurityController()
			{
				return null;
			}
			@Override
			public DbTable getTable()
			{
				return null;
			}
			@Override
			public TriggerHandler getTriggerHandler()
			{

				return triggerHandler;
			}
			@Override
			public AttributeChangeValidator getAttributeChangeValidator(SecUsrDta pm_secUserData,
					PersistantObject pm_po, String pm_key)
			{
				return null;
			}
			@Override
			protected HashMap<String, DbForignKeyArray> getChildrenForignKeys()
			{
				return null;
			}
			@Override
			public void afterAttributeChange(Attribute att){
				// TODO Auto-generated method stub
				
			}
			@Override
			public void beforeAttributeChange(Attribute att) throws Exception {
				// TODO Auto-generated method stub
				
			}
		};
		return this.getPersistantObjectList().get(currentRow);
	}
	
	public void next() throws Exception
	{	
		this.setCurrentRow(++currentRow);
		
		synchronizeDisplayedRow();
	}
	
	public void setMove(String moveType) throws Exception
	{
		String moveLeft = "1";
		String moveRight = "2";
		if(moveType.equals(moveLeft))
			this.next();
		else if(moveType.equals(moveRight))
			this.previous();
	}
	
	public void previous() throws Exception
	{
		if (this.currentRow > 0 )
		{
		this.setCurrentRow(--currentRow);
		synchronizeDisplayedRow();
		}
	}
	
	public boolean isHasNext()
	{
		return (currentRow < this.getPersistantObjectList().size()-1);
	}
	
	public boolean isHasPrevious()
	{
		return (currentRow > 0);
	}
	
	public DbServices getDbService() {
		return dbService;
	}
	public PersistantObject createNew(boolean pm_likeCurrent , boolean withDetails ) throws Exception
	{
		return this.createNew(pm_likeCurrent, withDetails , true) ;
	}
	public PersistantObject createNew(boolean pm_likeCurrent , boolean withDetails, boolean applyDefaults) throws Exception
	{
		return this.createNew(this.getCurrentItem(),pm_likeCurrent, withDetails , true) ;
	}

	public PersistantObject createNew(PersistantObject poToBeCopied, boolean pm_likeCurrent , boolean withDetails, boolean applyDefaults) throws Exception
	{
		PersistantObject newItem  = DirectJdbcServiceImpl.instantiatePersistentObject(this.persistantClass, this.query);
		newItem.setDbService(this.getDbService());
		newItem.setParent(this.getParent());
		newItem.setParentDataSet(this);
		
		newItem.setData( this.getCleanDataFromDbColumns(newItem ) );
		
		if (pm_likeCurrent && poToBeCopied != null) {
			newItem.copyDataFrom(poToBeCopied, withDetails);
		}
		
		newItem.getData().put(PersistantObject.VIEW_TIMESTAMP_KEY, null);
		newItem.getData().put(PersistantObject.OBJECT_KEY_NAME , null); // remove Object Key
		if (newItem.getTable() != null)
		newItem.setTableMaintMaster(this.getTableMaintMaster( ));
		if (applyDefaults)
		{
		newItem.applyDefautValues();
		}
		newItem.applyParentKeys();
		this.applyExtraWhereValues(newItem);
		newItem.initialize();
		
		return newItem;
	}
	private void applyExtraWhereValues(PersistantObject newItem) 
	{
		if(this.getExtraWhere()!=null)
		{
			TreeMap<String, Object> tm=this.getExtraWhere();
			Set set = tm.entrySet(); 
			Iterator i = set.iterator();  
			while(i.hasNext()) 
			{ 
				Map.Entry me = (Map.Entry)i.next(); 
				String columnName=((String)me.getKey());
				newItem.setAttributeValue(columnName,me.getValue() );                                             
			}
		}
	}
	
	private HashMap<String , Attribute> getCleanDataFromDbColumns(PersistantObject po) throws Exception
	{
		HashMap<String , Attribute> data = new HashMap<String, Attribute>();
		TableMaintMaster tmm =  this.getTableMaintMaster();
		if (tmm == null)
		{
			ArrayList<Header> hfrs = this.getHeadersFromRs();
			if (hfrs != null)
			{
				for (Header hrd : hfrs )
				{
					String cn = hrd.getColumnName();
					Object value = null;
					data.put(cn , new Attribute(cn ,value, po  ));
				}
			}
			
		}
		else
		{
			ArrayList<PersistantObject> dbc =  tmm.getTableMaintDetailss().getPersistantObjectList();
			
			if(dbc != null)
			{
				if (dbc.size()==0)
				{
					tmm.loadDefaultColumns();
					tmm.save();
					dbc =  tmm.getTableMaintDetailss().getPersistantObjectList();
				}
				for (PersistantObject poTmd : dbc)
				{
					TableMaintDetails  tmd = (TableMaintDetails) poTmd ; 
					String cn = tmd.getColumnNameValue();
					Object value = null;
					try{value = tmd.getEmptyInstanceOfColumnType();}
					catch (Exception e) {
						//e.printStackTrace();
					}
					Attribute att ; 
					if (tmd.getDataTypeValue().equals("BLOB"))
					{
						att = new BlobAttribute(cn ,value, po ) ;
					}
					else if (tmd.getDataTypeValue().equals("CLOB"))
					{
						att = new ClobAttribute(cn ,value, po ) ;
					}
					else 
					{
						att = new Attribute(cn ,value, po  );
					}
					data.put(cn , att );
				}
			}
		}
		return data;
	}
	/**
	 * Adds a new blank object to this DataSet 
	 * @throws BaseException
	 */
	public void addNew() throws Exception
	{
		DatasetEventListener dsel = this.getDataSetEventListner();
		if (dsel != null)
		{
			dsel.beforeAddNew(this);
		}
		addNew(false );
		if (dsel != null)
		{
			dsel.afterAddNew(this);
		}
	}
	public void addNew(int pm_Items)throws Exception
	{ 
		for( int i=0; i< pm_Items; i++){
			this.addNew();
		}
	}
	/**
	 * Adds a new object to the this DataSet 
	 * @param pm_likeCurrent = true ---> new object will be a copy from the current object , = false --> new object will be blank
	 * @throws BaseException
	 */
	public void addNewWithDetails( ) throws Exception
	{	
		this.getPersistantObjectList().add(this.currentRow, createNew(true , true) );
	}
	
	public void addNew(PersistantObject po) throws Exception 
	{
		addNew(false );
		this.getCurrentItem().copyDataFrom(po, true);
	}
	
	/**
	 * Adds a new object to the this DataSet 
	 * @param pm_likeCurrent = true ---> new object will be a copy from the current object , = false --> new object will be blank
	 * @throws BaseException
	 */
	public void addNew(boolean pm_likeCurrent ) throws Exception
	{	
		PersistantObject newObject = createNew(pm_likeCurrent , false) ;
		this.getPersistantObjectList().add(this.currentRow, newObject );
		this.getNewList().add(newObject);
		ParentPersistantObject parent = this.getParent() ;
		if (parent != null)
		{
			PersistantObject parentPo =  parent.getPersistantObject();
			parentPo.informParntDS();
		}
		this.getDsCalculator().reset();
		this.resetSaveAllCommandList(); 
		this.getCurrentItem().setParentDataSet(this);
	}
	 
	 public void addNewWithDefaultCompId()
	 {
	  try {
	   this.addNew();
	   PersistantObject newObj = this.getCurrentItem();
	   Attribute cmpIdAtt = newObj.getAttribute(PersistantObject.CMP_ID);
	   if(cmpIdAtt != null && newObj.getTableMaintMaster().getMandatoryFields().contains(PersistantObject.CMP_ID)
	     && (cmpIdAtt.getValue() == null || cmpIdAtt.getValue().toString().equals("")))
	    cmpIdAtt.setValue(this.getDbService().getLoggedUser().getUsrCmpIdValue());
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	 }
	
	public void first()
	 {
		this.currentRow = 0;
		this.setCurrentRow(currentRow);
		synchronizeDisplayedRow();
	 }
	 public void last()
	 { 
		 this.setCurrentRow(persistantObjectList.size()-1);
		 synchronizeDisplayedRow();
	 }

	public void setQuery(Query query)
	{
		this.query = query;
	}

	public Query getQuery()
	{
		return query;
	}

	public void setPersistantClass(Class<PersistantObject> persistantClass)
	{
		this.persistantClass = persistantClass;
	}

	public Class<PersistantObject> getPersistantClass()
	{
		return persistantClass;
	}

	public void goTo()
	{
		this.setCurrentRow(getDisplayedCurrentRow() -1);
	}
	public void setDisplayedCurrentRow(int pm_displayedCurrentRow) throws Exception
	{
		this.displayedCurrentRow = pm_displayedCurrentRow;
	}

	public int getDisplayedCurrentRow()
	{
		return (this.getPersistantObjectList().isEmpty())? 0: this.displayedCurrentRow;
	}
	private void synchronizeDisplayedRow()
	{
		this.displayedCurrentRow = this.currentRow+1;
	}
	

	public ArrayList<PersistantObject> getUpdatedList()
	{
		return updatedList ;
	}

	

//	public DataSet getUpdatedDataSet()
//	{
//		DataSet  updatedDataSet; 
//		updatedDataSet = new DataSet(this.getUpdatedList(), this.getDbService());
//		updatedDataSet.setTableMaintMaster(this.getTableMaintMaster());
//		updatedDataSet.setPersistantClass(this.getPersistantClass());
//		
//		return updatedDataSet;
//	}


	
	public ArrayList<PersistantObject> getNewList()
	{
		return newList; 
		
	}
	
	private ArrayList<PersistantObject> calcNewList()
	{
		ArrayList<PersistantObject> result = new ArrayList<PersistantObject>();
		for (int i=0; i< this.getPersistantObjectList().size() ; i++)
		{	PersistantObject item = this.getPersistantObjectList().get(i) ;
			if (! item.isContainsObjectKey()) result.add(item);
		}
		return result;
	}
	
//	public DataSet getNewDataSet()
//	{
//		return getNewDataSet(this.getNewList());
//	}
//	
//	private DataSet getNewDataSet(ArrayList<PersistantObject> list)
//	{
//		DataSet newDataSet = new DataSet( list, this.getDbService()) ;
//		newDataSet.setTableMaintMaster(this.getTableMaintMaster());
//		newDataSet.setPersistantClass(this.getPersistantClass());
//		return newDataSet;
//	}
	
	public boolean isEmptySet()
	{
		return this.getPersistantObjectList().isEmpty();
	}

	ArrayList<PersistantObject> selectedItems ; 



	public ArrayList<PersistantObject> getSelectedItems()
	{
		return selectedItems ; 
	}
	private ArrayList<PersistantObject> calcSelectedItems()
	{
		ArrayList<PersistantObject> result = new ArrayList<PersistantObject>();
		for (int i=0; i< this.getPersistantObjectList().size() ; i++)
		{	PersistantObject item = this.getPersistantObjectList().get(i);
			if (item.isSelectedInParentDataSet())
			{
				result.add(item);
			}
		}
		return result;
	}
	
	public void refreshSelectedItems()
	{
		this.selectedItems = calcSelectedItems() ;
	}
	
//	public void applyFilter(TableMaintMasterServices tmms) throws Exception
//	{
//		String filteredQuery;
//		if (tmms == null || tmms.getQuery()==null ||tmms.getQuery().equals("") ) filteredQuery = this.query.getQueryStr();
//		else{ filteredQuery = getFilterdQuery(tmms);}
//		
//		DataSet filteredDataset = new DataSet(filteredQuery , this.getPersistantClass() ,this.getDbService() , this.getParent() );
//		this.initialize(filteredDataset , true);
//		
//		setFilterApplied(true);
//		
//	}
	
	public void cancelFilter() throws Exception
	{
		this.restore();
		this.setFilterApplied(false);
	}

	public void setFilterApplied(boolean filterApplied)
	{
		this.filterApplied = filterApplied;
	}

	public boolean isFilterApplied()
	{
		return filterApplied;
	}
//	private String getFilterdQuery(TableMaintMasterServices tmms)
//	{
//		String filteredQuery ; 
//		filteredQuery = "select * from (" + this.query + ") where " + tmms.getCurrentTable().getQuery().getQueryStr(); 
//		return filteredQuery;
//	}

//	public void search()
//	{
//		TableMaintMasterServices tmds = this.getSearchServer();
//		tmds.setCallerDataSet(this);
//		try{
//			if (tmds.getSelectedItems().isEmpty())
//				tmds.addNewCondition();
//			}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		tmds.setEnableModuleChange(false);
//
//	}
	private TableMaintMasterServices getSearchServer()
	{
		ModuleServicesContainer msc = (ModuleServicesContainer) SWAF.getManagedBean("moduleServicesContainer");
		return  msc.getTableMaintServices();
	}
	public void clear()
	{
		this.persistantObjectList.clear();
		this.toBeDeletedList.clear();
		this.setCurrentRow (0);
		this.displayedCurrentRow = 0;	
		
		 
		toBeDeletedList.clear();
		newList.clear();
		updatedList.clear();
		dbService = null;
		dataTableDisplayProperities = null;  
		query = null;
		persistantClass = null;
		parent = null; 
		headersFromRs = null;
		tableMaintMaster = null; 
		saveAllCommandList = null;
		
		this.selectedObject = null ; 
		dsCalculator = null;
		forignKeys = null;
		dataSetManipulator = null;
		
	}
	
	public PersistantObject searchByAttribute2(Attribute att) throws Exception
	{
		PersistantObject result = null;
		int count = 0;
		int currentRow = this.getCurrentRow();
		for (PersistantObject po : this.getPersistantObjectList())
		{
			if ( count !=  currentRow )
			{
				Attribute thisAtt = po.getAttribute(att.getKey()) ; 
				if ( thisAtt.equals(att))
				{
					result = po;
					break;
				}
			}
			count++;
		}

		return result;
	}
	
	public ArrayList<PersistantObject> searchByAttribute(Attribute att) throws Exception
	{
		ArrayList<PersistantObject> pos = null; 
		String tableName = att.getParentPersistantObject().getTable().getTableName();
		try{
			pos= (ArrayList<PersistantObject>) this.getDbService().queryForList("select t.* , t.rowid from "+tableName +" t where "+ att.getWhereClause(),this.getPersistantClass());
		}
		catch (Exception e)
		{
			
		}
		return pos;
	}
	 
	public void setCurrrentItemByAttribute(Attribute att) throws Exception
	{
		ArrayList<PersistantObject> pos =  this.searchByAttribute(att);
		if (pos == null || pos.isEmpty())
		{
			this.addNew();
			this.getCurrentItem().getAttribute(att.getKey()).setValue(att.getValue());
			this.setCurrentRow(0);
			ModuleServicesContainer msc = this.getParent().getPersistantObject().getDbService().getModuleServiceContainer() ;
			SysMsgServices sysmsgservices= msc.getSysMsgServices();
			SysMsg sysMsg=sysmsgservices.getMsgNoItemFoundWithYourSearch();
			this.getMessageCommnunicatorService().sendMessageToUser(sysMsg);
		}
		else 
		{
			att.revert();
			this.initialize(pos , this.getDbService());
		}
		att.revert();
	}
	
//	public DataSet filterMeForLoggedUserOnly(String ownerColumnName)
//	{
//		DataSet result = this.getNewDataSet(new ArrayList<PersistantObject>()); 
//		String loggedName = SWAF.getSession().getLoginName();
//		for (PersistantObject po : this.getPersistantObjectList())
//		{
//			
//			String objectOwner = (String) po.getAttributeValue(ownerColumnName);
//			if (objectOwner.equalsIgnoreCase(loggedName))
//			{
//				result.getPersistantObjectList().add(po);
//			}
//		}
//		return result;
//	
//	}
	

	public void sendToUser(SecUsrDta sud)
	{
		ModuleServicesContainer msc = this.getParent().getPersistantObject().getDbService().getModuleServiceContainer() ;
		SysParamsServices sps = msc.getSysParamsServices();
		MailSender ms = sps.getMailSender();
		try {

			EmailMessage em = new EmailMessage();
			em.setBody(this.getPersistantObjectList().toString());
			String[] attachedFiles = {this.constructHtmlFile()};
			em.setAttFileNames(attachedFiles);
			String[] to = {sud.getEmail()} ;
			em.setTo(to);
			ms.sendMail(em);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**\
	 * Saves The contents of this dataSet as an Html File 
	 * @return complete path of the saved file
	 */
	private String constructHtmlFile()
	{
		//TODO 
		return null;
	}
	/**
	 * This method imports data from xls
	 */
	public void importFromXls(File xlsFile , int sheetNo) throws Exception
	{	
		XLSUpload xlsUploader = new XLSUpload(xlsFile, sheetNo) ;
		Vector[] xx =  xlsUploader.getXLSData();
		
		Workbook wb =  Workbook.getWorkbook(xlsFile );
	    Sheet sheet = wb.getSheet(sheetNo);
	    
	    int rows = sheet.getRows();
	    int cols = sheet.getColumns();
	    
	    Cell[] firstRow = sheet.getRow(0);
	    ValidationResult vr = this.isXlsColNameValid(cols, firstRow);
	    if( !vr.isValidResult() )
	    {
	    	this.getMessageCommnunicatorService().sendValidationresultToUser(vr);
	    	return;
	    }
	    Attribute att;
	    String firstRowColContent="";
	    String cellContent = "";
	    for (int row = 1; row < rows; row++)
	    {
	    	this.addNew();
	    	for(int col = 0; col < cols; col++)
	    	{
				firstRowColContent = firstRow[col].getContents();
	    		cellContent = (String) xx[col].get(row);
	    		
	    		if ( !( firstRowColContent == null || firstRowColContent.equals("") ) && 
	    			 !( cellContent == null || cellContent.equals("") ) )
 	    		{
	    			att = this.getCurrentItem().getAttribute(firstRowColContent);
	    			TableMaintDetails tmd = att.getTableMaintDetail();
	    			if( tmd.getDataTypeValue().equalsIgnoreCase("DATE"))
		    		{
		    			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		    			Date date = df.parse(cellContent);
		    			att.setValue(new Timestamp(date.getTime()));
		    			continue;
		    		}
	    			else
	    			{
	    				if(tmd.isComboBox() && tmd.getSelectListQueryValue() != null)
	    				{
	    					cellContent = calcAttributeValue(att , cellContent);
	    				}
	    			}
		    		att.setValue(cellContent);
 	    		}
    		}
	    }
	    attQueryResult = null;
	}
	private HashMap<String, ArrayList<String[]>> attQueryResult ;
	private String calcAttributeValue(Attribute att, String cellContent) {		
		if(attQueryResult == null || attQueryResult.get(att.getKey()) == null)
		{
			attQueryResult = new HashMap<String, ArrayList<String[]>>();
			Connection con =  this.getDbService().getConnection();	
			ResultSet rs = null;
			Statement stmt = null;
			cellContent = cellContent.trim();
			TableMaintDetails tmd = att.getTableMaintDetail();
			String pm_sql =  tmd.getSelectListQueryValue();
			try{
				Console.log("DataBase Service : Will Execute " + pm_sql, this .getClass() , Console.NOTIFICATION_LOGGING_LEVEL);
				
				stmt =  con.createStatement() ; 	
				rs =    stmt.executeQuery(pm_sql);
				int columnCount = rs.getMetaData().getColumnCount();
				boolean has3columns = (columnCount == 3 ) ;
				ArrayList<String[]> values = new ArrayList<String[]>();
				while (rs.next())
				{
					String[] attValues = new String[3];
					attValues[0] = rs.getString(1);
					attValues[1] = rs.getString(2);
					if (has3columns)
					{
						attValues[2] = rs.getString(3) ;
					}
					values.add(attValues);
				}
				attQueryResult.put(att.getKey(),  values);
				DirectJdbcServiceImpl.releaseResources(rs, stmt , null);
			}
			catch (Exception e)
			{	
				e.printStackTrace();
			}
		}
		ArrayList<String[]> values = attQueryResult.get(att.getKey());
		if(values != null)
		{
			boolean valueExist = false;
			for(String[] value : values)
			{
				if(value[1] != null && value[1].equalsIgnoreCase(cellContent))
				{
					cellContent = value[0];
					valueExist = true;
				}
				else if (value[2] != null && value[2].equalsIgnoreCase(cellContent))
				{
					cellContent = value[0];
					valueExist = true;
				}
			}
			if(!valueExist)
				cellContent = null;
		}
		return cellContent;
	}

	/**
	 * This method imports data from more than xls file to DataSet
	 * @param xlsFiles list of files to be imported
	 * @throws Exception
	 */
	public void importFromXlsFiles()throws Exception
	{
		ArrayList<File>  xlsFiles = this.getFileUploadBean().getPhysicalFiles();
		for( int i = 0; i < xlsFiles.size(); i++)
		{
			this.importFromXls(xlsFiles.get(i), 0);
		}
	}
	private ValidationResult isXlsColNameValid(int cols, Cell[] firstRow)
	{
		ValidationResult result = new ValidationResult() ;
		TableMaintMaster tmm = this.getTableMaintMaster() ;
		for(int col = 0; col < cols; col++)
	    {
			String tableColumnNameInXls  = firstRow[col].getContents() ; 
			if(! tmm.containsColumn(tableColumnNameInXls) )
			{
				result.setValidResult(false);
				ArrayList<String> param = new ArrayList<String>();
				param.add(tableColumnNameInXls);
				if(this.getTableName()!= null)
				param.add(this.getTableName());
				ModuleServicesContainer msc = this.getParent().getPersistantObject().getDbService().getModuleServiceContainer() ;
				SysMsg sysMsg = msc.getSysMsgServices().getInvalidColumnTableNameMsg();
				String msg = sysMsg.getMsgDescWithParam(param);
				result.getInvalidMessages().add(msg);
			}
	    }
		
		return result;
	}
	
	public  void setValues( String pm_column2BeUpdated , String[] pm_selectedGroups) {
		try {
			this.setCurrentRow(0);
			//Adjust DataSet size to be equal the to the input String Array
			for (int i = 0 ; i< pm_selectedGroups.length - this.getPersistantObjectList().size(); i++)
			{
				this.addNew();
			}
			for (int i = 0 ; i< this.getPersistantObjectList().size() - pm_selectedGroups.length ; i++)
			{
				this.remove();
			}
			
			for (int i = 0; i < pm_selectedGroups.length; i++) {
				if (pm_selectedGroups[i] != null) 
				{
					PersistantObject po = this.getCurrentItem();
					po.getAttribute(pm_column2BeUpdated).setValue(pm_selectedGroups[i]);
					this.next();
				}
				}
			} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	public String[] getValues(String pm_columnName) {
		
		String[] result=null;
			
				int size = this.getPersistantObjectList().size();
				result = new String[size];
				ArrayList<PersistantObject> pos = this.getPersistantObjectList() ;
				for (int i = 0; i < size; i++)
				{
					result[i] =  pos.get(i).getAttribute(pm_columnName).getValue().toString();
				}
			
		return result;
	}
	public String getTableName() {
		return this.getTableMaintMaster().getTableNameValue(); 
		
	}
	
	private FileUploadBean fileUploadBean ;
	
	public FileUploadBean getFileUploadBean() {
		if (fileUploadBean == null)
		{
			fileUploadBean = new FileUploadBean();
		}
		return fileUploadBean;
	}
	protected void setTableMaintMaster(TableMaintMaster tableMaintMaster) {
		this.tableMaintMaster = tableMaintMaster;
	}
	public TableMaintMaster getTableMaintMaster() {
		return tableMaintMaster;
	}
	
	public ArrayList<Header> getHeaders()
	{
		ArrayList<Header> headers = new ArrayList<Header> ();
		TableMaintMaster tmm =  this.getTableMaintMaster(); 
		if (tmm != null)
		{
			ArrayList<PersistantObject> tmds = tmm.getListForResult();
			for (PersistantObject po : tmds)
			{
				TableMaintDetails tmd = (TableMaintDetails) po ;
				
				headers.add(new Header( tmd.getColumnNameValue() ,(String) tmd.getDisplayNameAutoLang().getValue()));
			}
		}
		else 
		{
			headers = this.getHeadersFromRs();
		}
		return headers ; 
	}
	public void setHeadersFromRs(ArrayList<Header> headersFromRs) {
		this.headersFromRs = headersFromRs;
	}
	public ArrayList<Header> getHeadersFromRs() {
		return headersFromRs;
	}
	private String displayPropId ;
	public String getDisplayPropId()
	{
		return displayPropId ;
	}
	public DataTableDisplayProperities getDisplayProperties() {
		return dataTableDisplayProperities ;
	}
	public DataTableDisplayProperities getDisplayProperties(String pm_DataTableDisplayProperitiesId) {
		if (dataTableDisplayProperities  == null || ! dataTableDisplayProperities.getIdValue().equalsIgnoreCase(pm_DataTableDisplayProperitiesId) )
		{
			DbServices dbs = this.getDbService() ;  
			ModuleServicesContainer msc = this.getParent().getPersistantObject().getDbService().getModuleServiceContainer() ;
			DataTableDisplayProperServices dtdps =  msc.getDataTableDisplayProperitiesServices() ;
			try{
				dataTableDisplayProperities = dtdps.getDataTableDisPropByID(pm_DataTableDisplayProperitiesId, this);
				displayPropId = pm_DataTableDisplayProperitiesId ;
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		dataTableDisplayProperities.setDataSet(this);
		return dataTableDisplayProperities;
	}
	TreeMap<String, Object> extraWhere;
	
	public void setExtraWhere(TreeMap<String, Object> pmExtraWhere) {
		extraWhere = pmExtraWhere ;
		
	}

	public TreeMap<String, Object> getExtraWhere() {
		return extraWhere ;
		
	}
	
    private DataSetCalculator dsCalculator; 

    public void setDsCalculator(DataSetCalculator dsCalculator) 
    {
                    this.dsCalculator = dsCalculator;
    }
    public DataSetCalculator getDsCalculator() 
    {
         if (dsCalculator == null)
            {
                dsCalculator = new DataSetSimpleCalculator(this);
            }
          return dsCalculator;
    }
    
   
    private  DbForignKeyArray forignKeys ; 
	public void setForignKey(DbForignKeyArray pm_forignKeys) {
		
		forignKeys = pm_forignKeys ;
	}
	public DbForignKeyArray getForignKeys()
	{
		return forignKeys ;
	}
	
	private PersistantObject selectedObject ;
	private String selectedObjectRowId ; 
	public void setSelectedObject(PersistantObject poToBeExplored) {
		this.selectedObject = poToBeExplored;
	
		if (poToBeExplored != null && poToBeExplored.isContainsObjectKey() )
		{
			this.selectedObjectRowId = poToBeExplored.getObjectRowId(); 
		}
		
		TableMaintMaster tmm = this.getTableMaintMaster() ;
		if(tmm != null)
		{
		tmm.getViewControllar().setShowSearchResult(false);
		tmm.getViewControllar().setSearchOpened(false);
		}
	}
	public PersistantObject getSelectedObject() {
		return selectedObject;
	}
	
	public String getSelectedObjectRowId() {
		return selectedObjectRowId;
	}
	
	private  DataSetManipulator dataSetManipulator  ; 
	public void setDataSetManipulator(DataSetManipulator dataSetMainpulator) {
		this.dataSetManipulator = dataSetMainpulator;
	}

	public DataSetManipulator getDataSetManipulator() {
		if (dataSetManipulator == null )
		{
			dataSetManipulator = new DataSetSimpleMainpulator(this);
		}
		return dataSetManipulator;
	}

	public void addAllNew(ArrayList<PersistantObject> pos)
	{
		this.getPersistantObjectList().addAll(pos);
		this.getNewList().addAll(pos);
		this.resetSaveAllCommandList();
		this.getDsCalculator().reset();
	}
	
	public ArrayList<PersistantObject> getUserSelection()
	{
		ArrayList<PersistantObject> userSelection = new ArrayList<PersistantObject>();
		for (PersistantObject po : this.getPersistantObjectList())
		{
			if (po.isSelectedInParentDataSet())
			{
				userSelection .add(po) ;
			}
		}
		return userSelection ;
	}
	
	
	private boolean selectAllStatus = false ; 
	
	
	public void toggelSelectAll()
	{
		this.markAll(! selectAllStatus) ;
	}
	
	private void markAll(boolean pm_selectedStatus)
	{
		for (PersistantObject po : this.getPersistantObjectList())
		{
			po.setSelectedInParentDataSet(pm_selectedStatus) ;
		}
		 
	}

	public void setSelectAllStatus(boolean selectAllStatus) {
		markAll(selectAllStatus);
		this.selectAllStatus = selectAllStatus;
	}

	public boolean isSelectAllStatus() {
		return selectAllStatus;
	}
	
	public String getListExtraColumnFirstPathValue()
	{
		String result = "/templates/include/dummyBlankPage.xhtml" ; 
		TableMaintMaster tmm =  this.getTableMaintMaster(); 
		if (tmm != null)
			result = tmm.getListExtraColumnFirstPathValue(); 
		
		return result ; 
	}

	public Object getSingleValue()
	{
		Object result = null ;
		if (!this.getPersistantObjectList().isEmpty())
		{
			PersistantObject firstRow = this.getPersistantObjectList().get(0) ;
			if (firstRow != null)
			{
				result = firstRow.getAttribute(0); 
			}
		}
		return result ; 
	}
	// Returns the first object matched key/value pairs   
	public PersistantObject getFirstFilteredPO(String m_keyName , Object m_key_value)
	{
		PersistantObject result = null ;
		if (!this.getPersistantObjectList().isEmpty())
		{
			for ( PersistantObject po: this.getPersistantObjectList() )
			{
				Attribute att = po.getAttribute(m_keyName) ;  
				String attStrValue = (att.getValue() == null) ? null :  (String) att.getValue().toString() ; 
				if (att!= null && att.getValue() != null && attStrValue.equals(m_key_value))
				{
					result = po; 
					break ; 
				}
			}
		}
		return result ; 
	}

	ArrayList<SelectItem> selectItems ;
	
	public ArrayList<SelectItem> getSelectItems()
	{
		if (selectItems == null)
		{
			selectItems=  this.getDbService().getSelectItems(this.getQuery().getQueryStr(), true);
		}
		
		return selectItems ; 
	}

	public HashMap<String, Object> getProperties() {
		if (properties == null)
		{
			properties = new HashMap<String, Object>();
		}
		return properties;
	}
	
	public String toStringAsHtmlTable()
	{
		StringBuffer result = new StringBuffer("");
		int counter = 0 ;  
		result.append("<table>");  
		for ( PersistantObject po : this.getPersistantObjectList())
		{	if ( counter == 0 )
			{
				result.append(po.toStringAsHtmlTableHeader());
			}
			result.append(po.toStringAsHtmlTableData());
			counter++; 
		}
		result.append("</table>");
		return result.toString(); 
		
	}

}
