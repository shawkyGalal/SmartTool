package com.implex.database;


import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.faces.model.SelectItem;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.internal.OraclePreparedStatement;
import oracle.sql.BLOB;
import oracle.sql.ROWID;

import org.apache.commons.lang.ArrayUtils;

import com.implex.database.audit.AuditInDbTriggerHandler;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.SysMsg;
import com.implex.database.map.SysPoAttachmentGroup;
import com.implex.database.map.SysPoAttachments;
import com.implex.database.map.TableMaintDetails;
import com.implex.database.map.TableMaintMaster;
import com.implex.database.map.UserDefVarGroups;
import com.implex.database.map.UsrDefVar;
import com.implex.database.map.security.PersistentObjectSecurityControl;
import com.implex.database.map.services.ModuleServices;
import com.implex.database.map.services.ModuleServicesContainer;
import com.implex.database.map.services.SysMsgServices;
import com.implex.database.map.services.TableMaintMasterServices;
import com.implex.database.map.services.UsrDefVarServices;
import com.implex.database.notifications.AttributeChangeNotifier;
import com.implex.database.trigger.TriggerHandler;
import com.sideinternational.sas.BaseException;
import com.sideinternational.sas.configuration.Configuration;
import com.sideinternational.sas.event.logging.Console;
import com.sideinternational.sas.util.xml.XmlNode;

public abstract class  PersistantObject implements Serializable  
{
	private static final long serialVersionUID = 1L;
	static final String VIEW_TIMESTAMP_KEY = "@@VIEW_TIMESTAMP";
	public static final String LAST_UPDATE_AT_COLUMN_NAME = "MODIFIED_DT";
	public static final String LAST_UPDATE_BY_COLUMN_NAME = "MODIFIED_BY";
	public static final String CREATED_BY_COLUMN_NAME = "PREPARED_BY";
	public static final String CREATED_AT_COLUMN_NAME = "PREPARED_DT";
	public static final String CMP_ID = "CMP_ID";
	
	public static final String OBJECT_KEY_NAME = "ROWID";
	
	
	private Map<String, Attribute> data ; //= new HashMap<String, Attribute>();
	private String[] columns = null; 
	private ArrayList<String> columnList = null;
	private HashMap<String, DataSet> childernDataSetMap = new HashMap<String, DataSet>();
	private boolean selectedInParentDataSet = false;
	private boolean refreshColumns = false; 
	public abstract AttributeChangeValidator getAttributeChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po , String pm_key);
	
	private ParentPersistantObject parent = null;
	private DbServices dbService;
	private TableMaintMaster tableMaintMaster;
	
	public static final String USER_DEFINED_DELIMTER = "::";
	public static final String SYS_DEFINED_DELIMTER = "@$";
	public static final String LOGGED_USER_PROPERTY_DELIMTER = "@@";//Linked with sec_user_data
	public static final String INPUT_PARAMETER_DELIMTER = "$$";
	public static final String OTHER_BENEFIT_DEFINED_DELIMTER = "%%";
	

	private DataSet parentDataSet;
	private ArrayList<PersistantObject> reInitializeAfterSaveList = new ArrayList<PersistantObject>();
	private HashMap<UsrDefVar , DataSet> usrDefVarValues = new HashMap<UsrDefVar , DataSet>();
	private static TriggerHandler triggerHandler ;
	
	public DataSet getUsrDefVarValue(UsrDefVar pm_usrDefVar)
	{
		DataSet result = this.usrDefVarValues.get(pm_usrDefVar);
		if (result == null)
		{
			result = this.reCalcUsrDefValue(pm_usrDefVar) ;

		}
		
		return result ; 
	}
	
	private DataSet reCalcUsrDefValue(UsrDefVar pm_usrDefVar)
	{
		DataSet result = pm_usrDefVar.calcDataSetValue(this, false) ;
		this.usrDefVarValues.put(pm_usrDefVar, result) ;
		return result ; 
	}
	
	public void reCalcAllUsrDefValue()
	{
		for (PersistantObject po : this.getTableMaintMaster().getUserDefinedVarGrpsDS().getPersistantObjectList() )
		{
			UserDefVarGroups udg = (UserDefVarGroups) po ; 
			for (PersistantObject udvPo : udg.getUserDefVarDS().getPersistantObjectList())
			{
				UsrDefVar udv = (UsrDefVar) udvPo ;
				this.reCalcUsrDefValue(udv);
			}
		}
	}
	
	public  void setParent(ParentPersistantObject pm_parent)
	{
		this.parent = pm_parent;
	}

	public ParentPersistantObject getParent()
	{
		return parent;
	}

	/**
	 * This method should be used to apply parent object keys (Foreign Keys) to this object 
	 */
	public void applyParentKeys()
	{
		ParentPersistantObject ppo = this.getParent(); 
		if (ppo != null)
		{
			DbForignKey[] parentkeys = ppo.getForignKeys().getDbForignKeyArray();
			for (int i = 0 ; i< parentkeys.length ; i++ )
			{
				DbForignKey currentKey = parentkeys[i];
				Attribute attTobeApplied = this.getAttribute(currentKey.getChildColumnName()) ; 
				Object valueFromParent = ppo.getPersistantObject().getAttributeValue(currentKey.getParentColumnName()) ;
				if (attTobeApplied != null)
				attTobeApplied.setValue(valueFromParent );
			}
		}
	}
	/**
	 * 
	 * @return true if this object or any of its children is changed with reference to values fetched from DB  
	 */
	private boolean isChanged()
	{		
		return ( ! this.getModifiedAttributes().isEmpty() 
				|| isChildrenHasChanges()
				);
	}
	
	
	private boolean isChildrenHasChanges() {
	
		HashMap<String, DataSet> childern = this.getChildernDataSetMap();

		Iterator<String> it = childern.keySet().iterator();
		boolean childrenHasChanges = false;
		while (it.hasNext()) {
			DataSet childDataSet = childern.get(it.next());
			childrenHasChanges = childDataSet.isChanged();
			if (childrenHasChanges)	break;
		}
		return childrenHasChanges;
	}
	
	/**
	 * 
	 * @return {@link HashMap} for all Object {@link Attribute}
	 */
	public Map<String, Attribute> getData() {
		return data;
	}
	
	/**
	 * 
	 * @return A {@link DbTable} represent this Table. 
	 */
	public  abstract  DbTable getTable();
	
	public Object getAttributeValue(String pm_key , boolean createIfNotExist)
	{
		return (this.getAttribute(pm_key , createIfNotExist) != null)? this.getAttribute(pm_key , createIfNotExist).getValue() : null;
	}
	
	/**
	 * 
	 * @param pm_key
	 * @return {@link Object} reprenent the value of the input {@link String} pm_key
	 */
	public Object getAttributeValue(String pm_key)
	{
		return (this.getAttribute(pm_key) != null)? this.getAttribute(pm_key).getValue() : null;
	}
	
	/**
	 * 
	 * @param pm_key
	 * @return {@link Attribute} with the given pm_key
	 */
	public Attribute getAttribute(String pm_key , boolean createIfNotExist)
	{
		if (!this.getData().containsKey(pm_key) && ! pm_key.equals(OBJECT_KEY_NAME) ) 
		{
			Console.log("Implex: Column " + pm_key + " Not Found in the Persistent Object of Type " + this.getClass().getName() ,this.getClass() );
			if (createIfNotExist) this.getData().put(pm_key, new Attribute(pm_key , null , this));		
		}	
		return this.getData().get(pm_key);
	}
	
	public Attribute getAttribute(String pm_key )
	{
		return this.getAttribute(pm_key,false);
	}
	/**
	 * 
	 * @param pm_index
	 * @return {@link Attribute} by Index
	 */
	public Attribute getAttribute(int pm_index) throws IndexOutOfBoundsException 
	{	
		String key = getKeyNameFromTmd(pm_index);
		return this.getAttribute(key);
	}
	
	public Attribute getAttributeByOrder(int pm_index)
	{
		
		 Iterator<Attribute> it = this.getData().values().iterator();
		 Attribute result = null ; 
		 for (int i= 0 ; i<= pm_index ; i++)
		 {
			 result = it.next();
		 }
		 
		return result;
	}
	
	private String  getKeyNameFromTmd(int pm_index)
	{
		String key = "RESULT" ;
		try{
		TableMaintDetails tmd = (TableMaintDetails) this.getTableMaintMaster().getTableMaintDetailss().getPersistantObjectList().get(pm_index);
		key = tmd.getColumnNameValue();
		}
		catch (Exception e){}
		return key ; 
	}
	/**
	 * 
	 * @param pm_index
	 * @return {@link Attribute} Value by Index
	 */
	public Object getAttributeValue(int pm_index) throws IndexOutOfBoundsException
	{	
		Object result = null ;
		Attribute att = this.getAttribute(pm_index) ;
		if (att != null)
		result =  att.getValue();
		return result;
	}
	/**
	 * 
	 * @param pm_key
	 * @param pm_forModifiedValues
	 * @return a {@link String} representation for the value of the {@link Attribute} of the given pm_key
	 */
	public String getAttributeSQLValue(String pm_key, boolean pm_forModifiedValues)
	{
		return this.getAttribute(pm_key).getAttributeSQLValue(pm_forModifiedValues);
	}
	
	
	@Deprecated
	public String constructInsertStatement(String pm_tableName , boolean forModifiedValues) {
		String[] keys =  this.getColumns();
		return this.constructInsertStatement(pm_tableName, forModifiedValues, keys) ; 
	}
	
	public String constructInsertStatementFromPO(String pm_tableName , boolean forModifiedValues, String[] extraKeys) {
		String[] keys = this.getColumns();
		String[] keysWithExtra = (String[]) ArrayUtils.addAll(keys, extraKeys);;
		
		return this.constructInsertStatement(pm_tableName, forModifiedValues, keysWithExtra) ;
	}
	public String constructInsertStatement(String pm_tableName , boolean forModifiedValues , String[] keys) {
		applyParentKeys();
		this.setCreateAuditInfo();
		String insertString = "Insert into " + pm_tableName + "( ";
		for (int i = 0 ; i< keys.length ; i++)
		{
			Object key = keys[i];
			insertString +=  ((i==0)? " ":" , " ) + key.toString()  ;
		}
		insertString += " ) " ;
		for (int i = 0 ; i< keys.length ; i++)
		{	
			insertString += ((i==0)? " Values ( " : " , ") + this.getAttributeSQLValue(keys[i] , forModifiedValues) ; 
		}	
		insertString += " ) ";
		return insertString;
	}
	
	private String parametrizedInsertString ; 
	private int rowIdOutParamIndex ;
	public int getRowIdOutParamIndex()
	{
		return rowIdOutParamIndex ; 
	}
	public CallableStatement constructInsertCallableStatement(Connection pmCon) throws SQLException, IOException 
	{
		applyParentKeys();
		this.setCreateAuditInfo();
		 CallableStatement insertCallableStatment ;
		String[] keys =  this.getColumns();
		parametrizedInsertString = "Insert into " + this.getTableMaintMaster().getOwnerValue()+"." + this.getTableMaintMaster().getTableNameValue() ;
		String keysWithComma = ""; 
		String valuesQuestionMarks ="" ;
		ArrayList<String> notNullKeys = new ArrayList<String>() ; 
		boolean fisrtItem = true ;
		for (int i = 0 ; i< keys.length ; i++)
		{
			String key = keys[i];
			Object keyValue = this.getAttributeValue(key, false) ;
			 
			if (key != null && keyValue != null && ! String.valueOf(keyValue).equals("") )
			{
				notNullKeys.add(key) ; 
				keysWithComma +=  ((fisrtItem )? " ( ":" , " ) + key.toString()  ;
				valuesQuestionMarks += ((fisrtItem)? " Values ( " : " , ") + "?" ;
				fisrtItem = false ; 
			}
		}
		keysWithComma += " ) " ;
		
		valuesQuestionMarks += " ) ";
		
		parametrizedInsertString = "begin " + parametrizedInsertString + keysWithComma + valuesQuestionMarks +" RETURNING ROWID INTO ? ; end;" ;
		
		insertCallableStatment = pmCon.prepareCall(parametrizedInsertString);
		
		for (int i =0 ; i< notNullKeys.size() ; i++)
		{	
			String key =  notNullKeys.get(i);
			Attribute att = this.getAttribute(key);
			this.setStatmentParam(att, insertCallableStatment, i+1);
		}
		rowIdOutParamIndex = notNullKeys.size()+ 1; 
		insertCallableStatment.registerOutParameter(rowIdOutParamIndex, OracleTypes.ROWID);
		return insertCallableStatment;
	}
	
	private  void setStatmentParam(Attribute att , PreparedStatement stmt , int index) throws SQLException, IOException
	{
		// if Object Type have been changed from java.sql.timeStamp to java.util.Date in the Interface layer (using jsf calender )
		// so simply convert it again to java.sql.timeStamp
		String dataType = att.getTableMaintDetail().getDataTypeValue() ; 
		Object attValue = att.getValue();
		if (  dataType.equalsIgnoreCase("VARCHAR2") || dataType.equalsIgnoreCase("NVARCHAR2")  )
		{
			if (attValue instanceof String)
			{
				stmt.setString(index, (String)attValue);
			}
			else if (attValue != null)
			{
				stmt.setString(index, attValue.toString());
			}
			else
			{
				stmt.setString(index, null);
			}
		}
		else if ( ( dataType.equalsIgnoreCase("NUMBER") || dataType.equalsIgnoreCase("FLOAT") )  )
 		{
			if ( attValue instanceof String )
			{
	 			if (attValue != null)
	 			stmt.setObject(index, new BigDecimal((String) attValue));
	 			else 
	 			stmt.setObject(index, null);
			}
			else
			{
				stmt.setObject(index, attValue);
			}
 		}
		
		else if ( dataType.equalsIgnoreCase("CLOB"))
		{
			stmt.setClob(index, PersistantObject.string2Clob(String.valueOf(attValue) , stmt.getConnection()));
		}
		
 		
		else if (dataType.equalsIgnoreCase("DATE") && attValue instanceof java.util.Date  )
		{
			java.sql.Timestamp ObjectValueAsTimeStamp = new Timestamp ( ((Date) attValue).getTime() ) ;
			stmt.setTimestamp(index,  ObjectValueAsTimeStamp);
		}
		
		else if (dataType.equalsIgnoreCase("BLOB")  )
		{
			if (attValue instanceof BytesFile)
			{
				BytesFile bf = (BytesFile)attValue ;
				BLOB newBlob = BLOB.createTemporary(this.getDbService().getConnection(),false, oracle.sql.BLOB.DURATION_SESSION);
				newBlob.putBytes(1,bf.getData());
				stmt.setBlob(index, newBlob);
			}
			else if (attValue instanceof Blob)
			{
				stmt.setBlob(index, (Blob) attValue) ;
			}
			else
			{
				stmt.setBytes(index, new byte[0]);
			}
		}
		
		else {stmt.setObject(index, attValue);}
	}
	private ArrayList<BLOB> tempBlobs ; 
	public ArrayList<BLOB> getTempBlobs()
	{
		return tempBlobs ; 
	}
	
	public void freeTempBlobs()
	{
		if (tempBlobs == null)
		{
			return ;
		}
		for (BLOB blob : tempBlobs)
		{
			try {
				blob.freeTemporary() ;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public String getParametrizedInsertString()
	{
		return this.parametrizedInsertString ;
	}
	
	public static Clob string2Clob(String str, Connection pm_con) throws SQLException, IOException
	{
		oracle.sql.CLOB  myClob = oracle.sql.CLOB.createTemporary(pm_con,true,oracle.sql.CLOB.DURATION_SESSION);
	    java.io.Writer writer = myClob.getCharacterOutputStream();
	    
	    int size = str.length();
	    writer.write(str,0,size);
	    writer.flush();
	    writer.close();
	    return myClob ;
	  
	}
	/**
	 * @deprecated
	 * @return an SQL Update Statement for this Object
	 * @throws BaseException
	 */
	@Deprecated
	public String constructUpdateStatment() throws Exception 
	{
		this.checkObjectKey();
		this.setUpdateAuditInfo();
		ArrayList<Attribute> modifieData = this.getModifiedAttributes();
		String updateString = "Update " + this.getTable().getTableName();
		int i = 0;
		for (Attribute att : modifieData ) //int i = 0 ; i< keys.length ; i++)
		{
			String key = att.getKey();//(String) keys[i];
			updateString += (i==0)? " Set " : " , " ;
			updateString += key + " = " + this.getAttributeSQLValue(key , true) ;
			i++;
		}
		updateString += " Where "+OBJECT_KEY_NAME+" = '" + ((oracle.sql.ROWID)this.data.get(OBJECT_KEY_NAME).getValue()).stringValue()+ "'";
		return updateString;
	}
	
	private String parametrizedUpdateSting = ""; 
	
	public String getParametrizedUpdateSting()
	{
		return parametrizedUpdateSting ; 
	}
	public PreparedStatement constructUpdatePreparedStatment(Connection pmCon) throws SQLException, IOException {
		OraclePreparedStatement updatePreparedStatment ; 
		this.setUpdateAuditInfo();
		ArrayList<Attribute> modifieData = this.getModifiedAttributes();
		String tableOwner = this.getTableMaintMaster().getOwnerValue() ;
		parametrizedUpdateSting = "Update " + ((tableOwner != null) ? ( tableOwner+".") :"" ) +this.getTable().getTableName();
		int i = 0;
		for (Attribute att : modifieData ) 
		{
			String key = att.getKey() ; 
			parametrizedUpdateSting += (i==0)? " Set " : " , " ;
			parametrizedUpdateSting += key + " =  ? "  ;
			i++ ;
		}
		parametrizedUpdateSting += " Where "+OBJECT_KEY_NAME+" = '" + ((oracle.sql.ROWID)this.data.get(OBJECT_KEY_NAME).getValue()).stringValue()+ "'";
		
		updatePreparedStatment =  (OraclePreparedStatement) pmCon.prepareStatement(parametrizedUpdateSting);
		
		i = 0;
		for (Attribute att : modifieData ) 
		{
			String key = att.getKey() ; 
			parametrizedUpdateSting += ((i!=0)? " , ":" Values { ") + this.getAttributeValue(key);
			Attribute attribute = this.getAttribute(key) ;
			this.setStatmentParam(attribute, updatePreparedStatment, i+1);
			i++ ;
		}
		
		parametrizedUpdateSting += "}";
		
		return updatePreparedStatment ; 
		
	}

	
	private void setUpdateAuditInfo()
	{
		String loggedUserName = "Not Yet"; //ApplicationContext.getCurrentLoggedUser().getUsrNameValue();
		this.setAttributeValue(LAST_UPDATE_BY_COLUMN_NAME , loggedUserName);
		this.setAttributeValue(LAST_UPDATE_AT_COLUMN_NAME , new Timestamp(new Date().getTime()));
	}
	
	private void setCreateAuditInfo()
	{
		String loggedUserName = "Not Yet"; //ApplicationContext.getCurrentLoggedUser().getUsrNameValue();
		this.setAttributeValue(CREATED_BY_COLUMN_NAME , loggedUserName);
		this.setAttributeValue(CREATED_AT_COLUMN_NAME , new Timestamp(new Date().getTime()));
		setUpdateAuditInfo();
	}
	
	public void setAttributeValue(String pm_key, Object pm_attValue) {
		Attribute att = this.getAttribute(pm_key , false);
		if ( att!= null)
		{
			att.setValue(pm_attValue);
		}
		else
		{
			Console.log("Implex: Warnning : Column " + pm_key + " Not Found in the Persistent Object of Type " + this.getClass().getName() ,this.getClass() );
		}
		
	}

	/**
	 * 
	 * @return a {@link HashMap} for Modified {@link Attribute}s Only
	 */


	public ArrayList<Attribute> getModifiedAttNeedNotification()
	{
		ArrayList<Attribute>  result = new ArrayList<Attribute>();
		for (Attribute att :  getModifiedAttributes())
		{
			ArrayList<AttributeChangeNotifier> changeNotifiers = att.getChangeNotifiers() ;
			if ( changeNotifiers != null &&  ! changeNotifiers.isEmpty())
			{
				result.add(att);
			}
		}
		return result;
		
	}
	/**
	 * 
	 * @param pm_selectedEnv
	 * @return true if this Object is Modified in DB after creation time in Java Memory. 
	 * 	this method is used to prohibit update if it returns true 
	 * @throws BaseException
	 */
	public boolean isOldView(String pm_selectedEnv ) throws Exception {
		boolean result = false;
		Date viewTimeStamp = this.getViewTimeStamp();
		Date lastUpdateTimeStamp = this.getLastUpdateTime(pm_selectedEnv );
		if (lastUpdateTimeStamp == null ) return false;
		else 
			{
			 result =   (viewTimeStamp.before(lastUpdateTimeStamp));
			 if (result)
			 {
				SysMsgServices sysmsgservices=this.getModuleServiceContainer().getSysMsgServices(); 
				SysMsg sysMsg=sysmsgservices.getMsgViewTime();
				ArrayList<String>param=new ArrayList<String>();
				param.add(viewTimeStamp.toString());
				param.add(lastUpdateTimeStamp.toString());	
				sysMsg.setParams(param);
				this.getModuleServiceContainer().getMessageCommnunicatorService().sendMessageToUser(sysMsg);
				Console.log(sysMsg.getMsgDescWithParam(), this.getClass());
			 }
			
			}
		return result;
		
	}

	private Date getLastUpdateTime(String pm_selectedEnv ) throws Exception {
		Date result = null; 
		if (this.getTable() == null)
		{
			throw new Exception("Table Name is Not Defined for this Persistant Object" );
		}
		
		DbServices dbs = this.getDbService() ; 
		dbs.initialize(pm_selectedEnv, dbs.getLoggedUserLang());
		
		List<PersistantObject> data = dbs.queryForList("Select "+PersistantObject.LAST_UPDATE_AT_COLUMN_NAME +" FROM " + this.getTable().getTableOwner()+"."+this.getTable().getTableName() + " Where rowid = '" + ((oracle.sql.ROWID)this.data.get(OBJECT_KEY_NAME).getValue()).stringValue()+ "'");
		
		Iterator<PersistantObject> it = data.iterator();
		while (it.hasNext())
		{
			result = (Date)((PersistantObject)it.next()).data.get(PersistantObject.LAST_UPDATE_AT_COLUMN_NAME).getValue();
		}
		return result;
	}

	private Date getViewTimeStamp() {
		return (Date)this.data.get(VIEW_TIMESTAMP_KEY).getValue();
	}


	/**
	 * 
	 * @return true if this object contains unique key value... 
	 * if false it means that object is new and save() method will insert (not update) 
	 */
	public boolean isContainsObjectKey() 
	{
		return this.getAttributeValue(OBJECT_KEY_NAME) != null;
	}


	/**
	 * 
	 * @param pm_selectedEnv
	 * @throws BaseException if this object is old view 
	 * ( its Corresponding Db Object have been modified by other user /way after object creation in Java memory)
	 */
	public void checkIfObjectIsOld(String pm_selectedEnv ) throws Exception{
		if (this.isOldView(pm_selectedEnv ))
	  	{
		  throw new Exception("View is Old... Data have been Changed Since last fetch");
	  	}
	}
	
	/**
	 * 
	 * @throws BaseException if this object does not contains object unique key value
	 */
    public void checkObjectKey() throws Exception
    {
		if (!this.isContainsObjectKey())
		{
			throw new Exception(("Persistant Object : <"+ this.toString() + ">" + " Has No ObjectKey"));
		}
    }

//	public void setRowData(HashMap<String , Attribute> pm_rowData) {
//		this.data = pm_rowData;
//		
//	}
	
	/**
	 * 
	 * @return an Array of {@link SelectItem} for all Logical Operations
	 */
	public SelectItem[] getAllLogicalOperations()
	{
		return Operation.getAllAvailableLogicalOperations();
	}

	protected void setData(Map<String , Attribute>  pm_data) {
		this.data = pm_data ;
		
	}


	public HashMap<String, DataSet> getChildernDataSetMap()
	{
		return childernDataSetMap;
	}
	
	public ArrayList<DataSet> getChildernDataSetList()
	{
		ArrayList<DataSet> result = new ArrayList<DataSet>();
		Collection<DataSet> dss = childernDataSetMap.values();
		for (DataSet ds : dss)
		{
			result.add(ds);
		}		
		return result;
	}


	/**
	 * Clears all this Object's {@link Attribute}(s) value to null
	 */
	public void clearData()
	{
		String[] columns = this.getColumns();
		for (int i = 0 ; i< columns.length ; i++)
		{
			this.getData().get(columns[i]).setValue(null) ; //, new Attribute(columns[i] , null , this));
		}
		
	}

	private ArrayList<String> getColumnList(ArrayList <Attribute > attList)
	{
		ArrayList<String> m_columnList = new ArrayList<String>();
		for (Attribute att : attList ) 
		{	
			if (att != null)
			{
				String key = att.getKey() ; 
				if (! ( key.equals("@@VIEW_TIMESTAMP") || key.equals(OBJECT_KEY_NAME) ) )
				{
					m_columnList.add(key);
				}
			}
		}
		
		return m_columnList;
	}
	
	/**
	 * 
	 * @return a {@link List} for all column names of this Object
	 */
	public ArrayList<String> getColumnList()
	{  
		if (this.columnList==null || this.isRefreshColumns())
		{
			this.columnList = this.getColumnList(this.getAttributesList());
		}	
		return columnList;
	}
	
	/**
	 * 
	 * @return a {@link List} for modified Columns Only
	 */
	public ArrayList<String> getModifiedColumnList()
	{
		return this.getColumnList(this.getModifiedAttributes());
	}

	/**
	 * 
	 * @return an Array of {@link String} for Columns
	 */
	public String[] getColumns()
	{
		if (this.columns == null || this.isRefreshColumns())
		{
			List<String> columnList = this.getColumnList();
			columns = new String[columnList.size()];
			columns = (String[]) columnList.toArray(columns);
		}
		return columns;
	}


	public void setSelectedInParentDataSet(boolean selectedInParentDataSet)
	{
		this.selectedInParentDataSet = selectedInParentDataSet;
		this.getParentDataSet().refreshSelectedItems() ;
	}


	public boolean isSelectedInParentDataSet()
	{
		return selectedInParentDataSet;
	}
 
	public  abstract PersistentObjectSecurityControl getSecurityController() ;
	
	/**
	 * Used in case of supplying {@link PersistentObjectSecurityControl} implementation from Application Configuration File 
	 * @param pm_className
	 * @return
	 */
	public static String getImplementationClass(String pm_className)
	    {
				
		  String result = null;
		  XmlNode[] implNodes = Configuration.get().findNodes("SECURITY_CONTROL_HANDLERS");
		  for (int i = 0 ; i<implNodes.length ; i++ )
		  {
			  XmlNode xx = implNodes[i].findNode("PERSISTENT_OBJECT_CLASS");
			  if (xx.getValue().equalsIgnoreCase(pm_className))
			  {
				  result = implNodes[i].getNodeValue("SCH");
				  break;
			  }
		  }
		  return result;
	    }
	
	public  TriggerHandler getTriggerHandler()
	{
		 boolean auditable = this.getTableMaintMaster().getAuditable().getBooleanValue(); 
		 if (PersistantObject.triggerHandler == null && auditable ) 
			{ 
			 triggerHandler = new AuditInDbTriggerHandler(); 
			} 
		 return triggerHandler; 
	}
	
	public int getAllChildrenCount()
	{
		int result = 0;
		HashMap<String, DataSet> childerDS = this.getChildernDataSetMap();
		Iterator<String> it = childerDS.keySet().iterator();
		while (it.hasNext())
		{
			result += childerDS.get( it.next()).getPersistantObjectList().size();
		}
		return result;
	}

	public void copyDataFrom(PersistantObject sourceItem, boolean withDetails)
	{
		HashMap<String, Attribute> newData = new HashMap<String, Attribute>();
		
		Iterator<String> it =  sourceItem.getData().keySet().iterator();
		
		while (it.hasNext())
		{
			String key = it.next();
			Object objectValue = sourceItem.getData().get(key).getValue();
			Attribute att = new Attribute(key , objectValue,this );
			newData.put(key , att  );
		}
		this.setData(newData);
		this.setTableMaintMaster(sourceItem.getTableMaintMaster());
		//--------Copying Details---------------------
		if (withDetails)
		{
			HashMap<String, DataSet> childerDS = sourceItem.getChildernDataSetMap();
			Iterator<String> it2 = childerDS.keySet().iterator();
			while (it2.hasNext())
			{
				String dataSetKey = it2.next();
				DataSet sourceDs = childerDS.get( dataSetKey);
				int childSize  = sourceDs.getPersistantObjectList().size();
				DataSet desDs = new DataSet(new ArrayList<PersistantObject>(childSize) , this.getDbService());
				List<PersistantObject> sourcePersObjectList =  sourceDs.getPersistantObjectList();
				for (int i = 0; i< sourcePersObjectList.size() ; i++ )
				{
					PersistantObject soutcePersiObject = sourcePersObjectList.get(i);
					PersistantObject newDesObject = null;
					try
					{
						newDesObject = sourceDs.createNew(false, false);
						newDesObject.setParent(this.getMeAsParent(dataSetKey));
						newDesObject.applyParentKeys();
						
					} catch (Exception e)
					{
						e.printStackTrace();
					}
					newDesObject.copyDataFrom(soutcePersiObject, withDetails); 
					desDs.getPersistantObjectList().add(i , newDesObject);
					 
				}
				this.getChildernDataSetMap().put(dataSetKey, desDs);				
			}			
		}		
	}

	public void setRefreshColumns(boolean pm_columnsChanged)
	{
		this.refreshColumns = pm_columnsChanged ;
	}
	public boolean isRefreshColumns()
	{
		return this.refreshColumns;
	}

	public String getRowIdString()
	{
		return ((ROWID) this.getAttribute(OBJECT_KEY_NAME).getValue()).stringValue();
	}

	public void setDbService(DbServices pm_dbService)
	{
		dbService = pm_dbService ; 
	}
	
	public DbServices getDbService()
	{
		return dbService  ; 
	}
	
	private ParentPersistantObject getMeAsParent(String pm_tableName )
	{
		DbForignKeyArray forignKeys  = this.getChildrenForignKeys().get(pm_tableName);
		return getMeAsParent(forignKeys);
	}

	private ParentPersistantObject getMeAsParent(DbForignKeyArray forignKeys )
	{
		ParentPersistantObject parent = new ParentPersistantObject(this);
		parent.setForignKeys(forignKeys);
		return parent;
	}

	public static String convertTreeMapToString(TreeMap<String, Object> pm_extraWhere)
	{
		String extraQuery="";
		Set set = pm_extraWhere.entrySet(); 
		Iterator i = set.iterator();  
		
		while(i.hasNext()) 
		{ 
			Map.Entry me = (Map.Entry)i.next(); 
			extraQuery=extraQuery+" And " +me.getKey()+"='"+me.getValue()+"'";
		}
		return extraQuery ;
	}
	protected DataSet  fetchChildrenDataSet(String pm_dataSetKey  , DbForignKeyArray forignKeys , Class<PersistantObject> childPersistentClass , String pm_extraClause) throws Exception
	{
		DataSet result ; 
		String query = constractQueryForChildren( pm_dataSetKey, forignKeys , pm_extraClause) ;
		result =  this.getDbService().queryForDataSet(query, null , childPersistentClass , this.getMeAsParent(forignKeys));
		result.setForignKey(forignKeys);
		return result ;
	}

	protected String constractQueryForChildren(String pm_dataSetKey , DbForignKeyArray pm_forignKeys , String pm_extraClause)
	{
		String whereClause = "";
		DbForignKey[] forignKeys = pm_forignKeys.getDbForignKeyArray() ;
		for (int i = 0 ; i< forignKeys.length ; i++)
		{
			DbForignKey forignKey = forignKeys[i];
			whereClause += ((i==0)? "Where ": " And " ) + forignKey.getChildColumnName() + " = " + this.getAttributeSQLValue(forignKey.getParentColumnName(), true);
		}
		 
		TableMaintMaster childTable =  pm_forignKeys.getChildTable();
		String query ;
		if (childTable != null)
		{
			query = childTable.getAllItemsQuery() + whereClause + ( (pm_extraClause== null)?  "" : pm_extraClause );;
		}
		else 
		{ 
			String childTableNameFromFK = pm_forignKeys.getChildFullTableName() ;
			query = "select t.* , t.rowid from "+((childTableNameFromFK == null)? pm_dataSetKey : childTableNameFromFK ) +" t " + whereClause + ( (pm_extraClause== null)?  "" : pm_extraClause );
		}
		return query;
	}

	protected DataSet getChildrenDataSet(String pm_dataSetKey , Class persisObjectClass , boolean refresh) throws Exception 
	{
		if (pm_dataSetKey == null) return null ;
		return getChildrenDataSet( pm_dataSetKey ,  persisObjectClass ,  refresh ,  "");
	}
	protected DataSet getChildrenDataSet(String dataSetKey , Class persisObjectClass , boolean refresh ,  TreeMap<String, Object> pm_extraWhere) throws Exception 
	{
		
			DataSet result = null ; 
			String extraWhereAsString = PersistantObject.convertTreeMapToString(pm_extraWhere) ; 
			result= this.getChildrenDataSet(dataSetKey , persisObjectClass ,refresh , extraWhereAsString );
			result.setExtraWhere(pm_extraWhere);
			return result ;
		
	}
	
	protected DataSet getChildrenDataSet(String pm_dataSetKey , Class persisObjectClass , boolean refresh ,  String pm_extraClause) throws Exception 
	{
		if (pm_dataSetKey== null)
		{ return null;}
		DataSet result = this.getChildernDataSetMap().get(pm_dataSetKey) ; 
		if ( refresh ||  result == null )
		 {
			 DbForignKeyArray ctfk =this.getChildrenForignKeys().get(pm_dataSetKey);
			 ctfk.setDbService(dbService);
			 if (ctfk != null)
			 {
				 result = this.fetchChildrenDataSet(pm_dataSetKey , ctfk , persisObjectClass , pm_extraClause ) ;
				 this.getChildernDataSetMap().put(pm_dataSetKey, result);
			 }
			 	
		 }
			 
		 return result ; 
	}
	/**
	 * 
	 * @return A map for Database Table Name and its corresponding foreign keys as a children for this object
	 */
	protected abstract HashMap<String, DbForignKeyArray> getChildrenForignKeys();

	/**
	 * Saves this Object to another environment ... 
	 * Useful in case of copy object from production environment to Test environment 
	 * for Investigating a bug
	 * @param dbs DbService representing the new Environment
	 * @throws BaseException in case of any failure to save object.
	 */
	public void save( DbServices dbs) throws Exception {
		this.setDbServiceIncludingChildren(dbs);
		this.save();
	}
	private void setDbServiceIncludingChildren(DbServices dbs)
	{
		this.setDbService(dbs);
		//TODO.... set all Children objects...
	}
	private boolean childFKAlreadyAddedToReInitAfterSaveList = false ;
	public void save() throws Exception {
		boolean newObject = ! this.isContainsObjectKey() ; 
		if (this.isNeedSave())
		{
			this.getModuleServiceContainer().getMessageCommnunicatorService().startNewTransaction() ;
			ArrayList< ValidationResult > invalidValidationResults = new ArrayList< ValidationResult >() ;
			PersistantObject.addInvalidResults(invalidValidationResults, this);
			if (! invalidValidationResults.isEmpty())
			{
				this.getModuleServiceContainer().getMessageCommnunicatorService().sendValidationResultsToUser(invalidValidationResults );
				return;
			}
			
			if (! childFKAlreadyAddedToReInitAfterSaveList)
			{
				this.getReInitializeAfterSaveList().addAll(this.getChildrenForignKeysTmds()) ;
				childFKAlreadyAddedToReInitAfterSaveList  = true ;
			}
			
			Batch batch = new Batch(this.getSaveCommandList());
			this.getDbService().executeBatch(batch);
			if (newObject)
			{
				this.getParentDataSet().getNewList().remove(this);
			}
		}
		else
		{
			Console.log("No Changes to Save Object " + this , this.getClass());
		}
	}
	
	public  void beforeUpdate() throws Exception 
	{
		
	}
	
	public void afterUpdate() throws Exception 
	{
		
	}
	
	public  void beforeInsert() throws Exception 
	{
		
	}
	
	public void afterInsert()
	{ 
		ModuleServices ms = this.getModuleServiceContainer().getCurrentActiveModule();
		if (ms != null)
		{
			DbTable dbt = ms.getDbTable() ; 
			if(ms != null && dbt!= null && dbt.getTableName().equals(this.getTable().getTableName()))
			{
				ms.setDataSetWithCondition(this.getRowIdString(),true);
			}
		}
	}
	
	public  void beforeDelete() throws Exception 
	{
		
	}
	
	public void afterDelete() throws Exception 
	{
		
	}

	public ArrayList<Command> getSaveCommandList()
	{
		ArrayList<Command>  saveCommandList = new ArrayList<Command> ();
		this.appendMeToSaveCommandList(saveCommandList);
		return saveCommandList ; 
	}
	boolean  masterThenCildren = true ; 
	
	public boolean isMasterThenCildren() {
		return masterThenCildren;
	}
	public void setMasterThenCildren(boolean masterThenCildren) {
		this.masterThenCildren = masterThenCildren;
	}

	private void execChildDatSetsBeforeSaveAll()
	{
		Map<String, DataSet> childern = this.getChildernDataSetMap();
		
		Iterator<String> it0 = childern.keySet().iterator();
		
		//Execute dataSet.beforeSaveAll for all childdatasets 
		while (it0.hasNext())
		{
			DataSet childDataSet = childern.get(it0.next());
			TableMaintMaster tmm =  childDataSet.getTableMaintMaster() ; 
			DatasetEventListener dsel ;
			if (tmm != null)
			{
				dsel =  tmm.getDataSetEventListner() ;
				if (dsel != null)
					try{
						dsel.beforeSaveAll(childDataSet) ; 
					} catch(Exception e){}
			}
		}
	}
	
	public void  appendMeToSaveCommandList(ArrayList<Command>  pm_saveCommandList)
	{
		boolean changed = this.isChanged() ; 
		boolean newObject = !this.isContainsObjectKey() ; 
		if (newObject || changed)
		{
			this.execChildDatSetsBeforeSaveAll() ;
		}
		if (! changed && ! newObject  ) 
		{	
			return ; 
		}
		
		if (pm_saveCommandList == null)
		{
			pm_saveCommandList = new ArrayList<Command> () ; 
		}
		
		Map<String, DataSet> childern = this.getChildernDataSetMap();
		Iterator<String> it1 = childern.keySet().iterator();
		
		//1- Delete children if marked to be deleted
		while (it1.hasNext())
		{
			DataSet childDataSet = childern.get(it1.next());
			for (PersistantObject posTobeDeleted : childDataSet.getToBeDeletedList() )
			{
				pm_saveCommandList.add(new Command(posTobeDeleted ,CommandType.DELETE_COMMAND) );
			}			
		}
		if (masterThenCildren)
		{
		//2 Update object itself (this)
		pm_saveCommandList.add(new Command( this, CommandType.UPDATE_COMMAND));
		}
		
		Iterator<String> it2 = childern.keySet().iterator();

		// Update Childrens 
		while (it2.hasNext())
		{
			DataSet childDataSet = childern.get(it2.next());
			for (PersistantObject posTobeUpdated : childDataSet.getUpdatedList() )
			{
				posTobeUpdated.appendMeToSaveCommandList(pm_saveCommandList);
			}
			
		}
		
		Iterator<String> it3 = childern.keySet().iterator();

		// Save New Childrens 
		while (it3.hasNext())
		{
			DataSet childDataSet = childern.get(it3.next());
			for (PersistantObject newPosToBeSaved : childDataSet.getNewList() )
			{
				newPosToBeSaved.appendMeToSaveCommandList(pm_saveCommandList);
			}			
		}
		if (! masterThenCildren)
		{
			//2 Update object itself (this)
			pm_saveCommandList.add(new Command( this, CommandType.UPDATE_COMMAND));
		}		
	}
	
	public  ArrayList<Command> getDeleteCommendList()
	{
		
		ArrayList<Command> deleteCommandList = new ArrayList<Command> () ; 
		Map<String, DataSet> childern = this.getChildernDataSetMap();
		Iterator<String> it = childern.keySet().iterator();
		
		//1- Delete children if oncascade Delete = true 
		while (it.hasNext())
		{
			DataSet childDataSet = childern.get(it.next());
			if (childDataSet.getForignKeys().isOnCascadeDelete())
			{
				for (PersistantObject posTobeDeleted : childDataSet.getToBeDeletedList() )
				{
					deleteCommandList.addAll( posTobeDeleted.getDeleteCommendList() ) ;
				}
			}
			
		}
		//2- Then Delete Object Itself 
		deleteCommandList.add(new Command(this, CommandType.DELETE_COMMAND));
		return deleteCommandList ;
		
	}

	private static void addInvalidResults( ArrayList< ValidationResult > invalidValidationResults , PersistantObject pm_po)
	{
		ArrayList<Attribute> attsToBeValidated = null ; 
		if (pm_po.isContainsObjectKey())
		attsToBeValidated = pm_po.getModifiedAttributes();
		else attsToBeValidated = pm_po.getAttributesList();
		Iterator<Attribute> it = attsToBeValidated.iterator();
		while (it.hasNext())
		{
			Attribute att = it.next();
			ValidationResult vr = null;
			if (att!= null && ( vr= att.getValidationResult()) != null && ! vr.isValidResult() )
			{
				invalidValidationResults.add( vr);
			}
			
		}
		
		Map<String, DataSet> childern = pm_po.getChildernDataSetMap();
		Iterator<String> it2 = childern.keySet().iterator();
		
		while (it2.hasNext())
		{
			DataSet childDataSet = childern.get(it2.next());
		
			for (PersistantObject posTobeUpdated : childDataSet.getPersistantObjectList() )
			{
			PersistantObject.addInvalidResults(invalidValidationResults, posTobeUpdated );
			}
		}
		 		
	}
	
	/**
	 * 
	 * @return {@link TableMaintMaster} representing this Object (from which you could get a wealth of info about this Object )
	 */
	public TableMaintMaster getTableMaintMaster()
	{
		if (tableMaintMaster == null)
		{
			TableMaintMasterServices tms = this.getModuleServiceContainer().getTableMaintServices() ; // ApplicationContext.getTableMaintServices(this.getDbService());// new TableMaintMasterServices(this.getDbService()); 
			tableMaintMaster=  tms.getTableMaintMaster(this.getTable());
		}
		return tableMaintMaster;
		
	}
	
	void setTableMaintMaster(TableMaintMaster pm_tableMaintMaster )
	{
		this.tableMaintMaster = pm_tableMaintMaster ; 
	}
	
	public List<SelectItem> getListForSearchAsSelectItems()
	{
		return this.getTableMaintMaster().getListForSearchAsSelectItems();
	}
	
	
	public String toString()
	{
		return this.getData().toString();
	}
	public String toStringAsHtmlTableHeader()
	{
		StringBuffer result = new StringBuffer("");
		result.append("<tr>" ) ;
		for ( Attribute al : this.getAttributesList()) 
		{
			result.append("<td>" +((al != null )? al.getKey() : "Error !!!" )  + "</td>") ; 
		}
		result.append("</tr>" ) ;
		return result.toString(); 
	}
	public String toStringAsHtmlTable()
	{
		StringBuffer result = new StringBuffer("<table border = '1'>" ) ;
		result.append(toStringAsHtmlTableHeader()); 
		result.append(toStringAsHtmlTableData()) ;
		result.append("</table>"); 
		return result.toString(); 
	}
	public String toStringAsHtmlTableData()
	{
		StringBuffer result = new StringBuffer(); 		
		result.append("<tr>" ) ;
		for ( Attribute al : this.getAttributesList()) 
		{
			result.append("<td>" + this.getAttribute(al.getKey()).getValue() + "</td>") ; 
		}
		result.append("</tr>" ) ;
		return result.toString(); 
	}

	public String toJson(boolean newLineForEachAttr)
	{
		StringBuffer result = new StringBuffer(); 		
		result.append("{" ) ;
		int attSize = this.getAttributesList().size() ; 
		int attCount = 0 ; 
		for ( Attribute al : this.getAttributesList()) 
		{
			attCount++ ; 
			String key = al.getKey() ; 
			result.append(((newLineForEachAttr)? "\n" : "") + key + ": " + "\""+ this.getAttribute(key).getValue() + "\"" + ((attCount < attSize)?", ":"") ) ;
		}
		this.getChildrenJSON() ; 
		result.append("\n}" ) ;
		
		return result.toString(); 
	}
	// Return children datasets as a json 
	private String getChildrenJSON() 
	{
		StringBuffer result = new StringBuffer("\n //-- Object Children ") ;
		/**TODO need to be tested */
		int chDatasetSize = this.getChildernDataSetList().size();
		int chDatasetCount = 0 ; 
		for ( DataSet chds : this.getChildernDataSetList())
		{
			chDatasetCount++; 
			result.append("Child_"+chDatasetCount+" : [") ;
			int childCout = chds.getPersistantObjectList().size() ; 
			int counter = 0 ; 
			for (PersistantObject chpo : chds.getPersistantObjectList() ) 
			{
				result.append(chpo.toJson(false)) ;
				counter++; 
				if (counter != childCout ) result.append(",\n " ) ;  
			}
			result.append("]" + ( (chDatasetCount < chDatasetSize )? "\n , " : "") ) ;
		}
		 
		
		return result.toString() ;  
	}

	
	/**
	 * 
	 * @param exp
	 * @param delimeter
	 * @return A {@link List} of Used Defined Variable Names in the input {@link String} exp with a Variable delimiter 
	 */
	public static ArrayList<String> getListOfVariables(String exp , String delimeter)
	{
		  ArrayList<String> list = new ArrayList<String>();
		  String var, att;
		  
		  if(exp == null || !exp.contains(delimeter)){return list;}
		  StringTokenizer tok1 = new StringTokenizer(exp, delimeter); 
		  
		  
		  while (tok1.hasMoreTokens()) {
		   var = tok1.nextToken();
		  
		  int endIndex = getEndIndex(var);
		   att = var.substring(0, endIndex);
		   if(att.trim().contains("%%")|| att.trim().contains(USER_DEFINED_DELIMTER)
				   				   || att.trim().contains(SYS_DEFINED_DELIMTER)
				   				   ||att.trim().contains(LOGGED_USER_PROPERTY_DELIMTER)||att.trim().equals(""))
				   			   continue;
		   if (!list.contains(att.trim()))
		   	{
			   list.add(att.trim());
		   	}
		  }
		  return list;
	}
	
	private static int getEndIndex(String var) {
		String space = " ";
		String comma = "'";
		int indexOfSpace = var.indexOf(space);
		int indexOfComma = var.indexOf(comma);
		int endIndex = 0;
		if (indexOfSpace == -1 && indexOfComma == -1) {
			endIndex = var.length();
		}

		else if (indexOfSpace == -1 && indexOfComma != -1) {
			endIndex = indexOfComma;

		} else if (indexOfSpace != -1 && indexOfComma == -1) {
			endIndex = indexOfSpace;
		}

		else {
			endIndex = (indexOfSpace < indexOfComma) ? indexOfSpace : indexOfComma;
		}
		return endIndex;
	}
	/**
	 * 
	 * @param exp in the form of   "4+ 3* $$ABC /4 + ::XYZ"
	 * 			<p> Where $$ABC is an attribute in this object 
	 * 			<p> and  ::XYZ a user defined query in the USR_DEF_VAR table (BenVar Object ) 
	 * 			after substituting the query parameters with its corresponding object attribute 
	 * @return the value of the input exp String
	 * @throws BaseException 
	 */
	 public BigDecimal evaluatExpression(String exp) throws Exception 
	 {
	  BigDecimal result = null;
	 
	  // 1- Extract User defined Variables
	  ArrayList<String> userDeFinedVariables = PersistantObject.getListOfVariables(exp, USER_DEFINED_DELIMTER);

	  
	  // 2- substituting User Defined variables
	  for (String attribute : userDeFinedVariables) 
	  {
		  String attValue;
		  attValue = this.calculateUserDefinedVariableValue( attribute);
		  exp = exp.replace(USER_DEFINED_DELIMTER + attribute, attValue);
	  }
	  
	  // 3 - Extract System variables
	  ArrayList<String> systemDefinedVariables = PersistantObject.getListOfVariables(exp,  SYS_DEFINED_DELIMTER);
	  
	  // 4- substituting system variables
	  for (String attribute : systemDefinedVariables) 
	  {
		  String attValue;
		  attValue = this.getAttribute(attribute.toUpperCase()).getAttributeSQLValue(true);
		  exp = exp.replace(SYS_DEFINED_DELIMTER + attribute, attValue);
	  }
	  
	
	  
	  //3- Calculate the expression
	  try {
	   DbServices dbs = this.getDbService();
	   PersistantObject po = dbs.queryForList("select round (" + exp + " , 2) as result from dual ").get(0);
	   result = (BigDecimal) po.getAttributeValue("result".toUpperCase());
	  } catch (Exception e) {
		  result = null;
		  e.printStackTrace();
	  }
	  return result;
	 }
	
	
	private UsrDefVarServices usrDefVarServices ; 

	private UsrDefVarServices getUsrDefVarServices()
	{
		if (usrDefVarServices == null)
		{
			usrDefVarServices = this.getModuleServiceContainer().getUsrDefVarServices();			
		}
		return usrDefVarServices;
	}
	
	private String calculateUserDefinedVariableValue(String attribute) throws Exception  {
		String result = ""; 
		UsrDefVar bv =  this.getUsrDefVarServices().getUserDefinedVariable(attribute);
		 if (bv != null) result = bv.calculateValue(this);
		return result ; 
		 
	}
	

	/**
	 * Reverts all changes done - and not saved - to this Object 
	 */
	public void revert(){
		ArrayList< Attribute > modifiesAttributes = this.getModifiedAttributes() ;
		
		for(int i= 0 ; i < modifiesAttributes.size() ; i++)
		{
			modifiesAttributes.get(i).revert();
		}
		
		Map<String, DataSet> childern = this.getChildernDataSetMap();
		Iterator<String> it = childern.keySet().iterator();
		
		while (it.hasNext())
		{
			DataSet childDataSet = childern.get(it.next());
			try {
				childDataSet.revertAll();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		this.informParntDS();
	}
	public boolean equals(Object ob)
	{
		return super.equals(ob) || equalData(ob)  ; 
	}
	public boolean equalData (Object object)
	{
		boolean result = false ;
		if ( ! (object instanceof PersistantObject) )
			return result ;
		else
		{
			PersistantObject persObject = (PersistantObject) object ;
			try {
				DBKey uk1 = persObject.getUniqeKey();
				DBKey uk2 = this.getUniqeKey();
				
				if (uk1 == null || uk1.getUniqueMap().isEmpty())
				{
					Console.log("Warnning : Object Does Not Have a Unique Key", this.getClass());
					return result ; 
				}
				
				result = uk1.equal(uk2);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result ; 
	}

	
	DBKey uniqueKey = null ;
	
	public DBKey getUniqeKey() throws Exception
	 {
		if (uniqueKey== null)
		{
			DBKey uniqueKeyEmpty = null ; 
			uniqueKeyEmpty = this.getTableMaintMaster().getPrimaryKeys();
			if(uniqueKeyEmpty == null)
			uniqueKeyEmpty = this.getTableMaintMaster().getUniqueKeys();
		
			if (uniqueKeyEmpty!= null)
			{
				  HashMap<String, Attribute> uniqueMap = uniqueKeyEmpty.getUniqueMap();
				  Set<String> set = uniqueMap.keySet()  ; 
				  uniqueKey = new DBKey(uniqueKeyEmpty.getKeyType());
				  for (String key : set )
				  {
					  uniqueKey.getUniqueMap().put(key , this.getAttribute(key) );
				  }
			}
		}
	  return uniqueKey ; 
	 }
	private String getLoggedUsrPropertyName(String pm_property){
				String loggedUserPropertyName = pm_property;
				TableMaintMaster tmm = this.getDbService().getLoggedUser().getTableMaintMaster();
				TableMaintDetails tmd = tmm.getTmdByColumnName(pm_property);
				if(tmd != null)
					loggedUserPropertyName = (String)tmd.getDisplayNameAutoLang().getValue();
				return loggedUserPropertyName; 
			}
	
	public String translateExpression(String expression) throws Exception{
		String exp = expression;
		ArrayList<String> userDefindVarList = PersistantObject.getListOfVariables(exp,PersistantObject.USER_DEFINED_DELIMTER);
		for (String varName : userDefindVarList) 
		{			
			UsrDefVar usrDefVar = this.getModuleServiceContainer().getUsrDefVarServices().getUserDefinedVariable(varName);				
			if(usrDefVar != null)				
			exp = exp.replace(PersistantObject.USER_DEFINED_DELIMTER + varName, usrDefVar.getUdvVarDescAutoLang().getValue().toString());
		}
		
		ArrayList<String>loggedUsrProperties  = PersistantObject.getListOfVariables(exp,LOGGED_USER_PROPERTY_DELIMTER);
				for (String loggedUsrProperty : loggedUsrProperties) 
				{	if(loggedUsrProperty != null)
					exp = exp.replace(LOGGED_USER_PROPERTY_DELIMTER+loggedUsrProperty, this.getLoggedUsrPropertyName(loggedUsrProperty));
				}
		
		ArrayList<String> sysVarList = PersistantObject.getListOfVariables(exp,PersistantObject.SYS_DEFINED_DELIMTER);
		ModuleServicesContainer msc = this.getModuleServiceContainer() ; 
		msc.getTableMaintServices().setTableName(msc.getUsrDefVarServices().getSelectedTableName());

		for (String sysVar : sysVarList) 
		{
			
			TableMaintDetails tmd = msc.getTableMaintServices().getCurrentTable().getTmdByColumnName(sysVar);
			if(tmd != null)
				exp = exp.replace(PersistantObject.SYS_DEFINED_DELIMTER+sysVar, tmd.getDisplayNameAutoLang().getValue().toString());
		}
		 translatedExpression=exp;		
		return exp;
	}
	public String getTranslatedExpression() {
		return translatedExpression;
	}

	private String translatedExpression = "";

	protected void setParentDataSet(DataSet parentDataSet) {
		this.parentDataSet = parentDataSet;
	}

	public DataSet getParentDataSet() {
		return parentDataSet;
	}
	
	public void setAllFieldsToNull()
	{
		Field[] fields =  this.getClass().getFields();
		for (int i = 0; i<fields.length ; i++)
		{
			 try {
				 fields[i].set(this, null);
				} catch (IllegalArgumentException e) {
				
				e.printStackTrace();
				} catch (IllegalAccessException e) {
					
					Console.log(e.getMessage(), this.getClass());
				}
		}
		
	}
	public PersistantObject createNew(boolean pm_likeMe, boolean withDetails)
	{
		PersistantObject result = null;
		if (this.getParentDataSet()!= null)
			try {
				result = this.getParentDataSet().createNew(this, pm_likeMe, withDetails , true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		else {this.getModuleServiceContainer().getMessageCommnunicatorService().sendMessageToUser("Object Should be In a DataSet To Create a New ...");}
		return result;
	}
	
	public String getObjectRowId()
	{
		return ((oracle.sql.ROWID) this.getAttribute(PersistantObject.OBJECT_KEY_NAME).getValue()).stringValue();
	}
	
	public UsrDefVar getUsrDefVar(String userDefUniqueName)
	{
		return getUsrDefVar(userDefUniqueName, TableMaintMaster.USR_DEFAULTS_GRP_NAME); 
	}
	
	public UsrDefVar getUsrDefVar(String userDefUniqueName, String pm_UserDefVarGrpName) 
	{
		DataSet allUserDefVars = null;
		try{
			UserDefVarGroups userDefVarGrp = this.getTableMaintMaster().getUserDefVarGroupByCodeName(pm_UserDefVarGrpName);
			allUserDefVars = userDefVarGrp.getUserDefVarDS();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return  this.getTableMaintMaster().searchForUserDefvarByName(allUserDefVars, userDefUniqueName);

	}
	public String getUsrDefVarValue( String userDefUniqueName)
	{
		UsrDefVar udf = this.getUsrDefVar(userDefUniqueName);
		String result = "";
		if (udf != null)
		{
			result =  udf.calculateValue(this);
		}
		return result ;
	}
	
	private ArrayList<Attribute> attributesList ;
	public ArrayList<Attribute> getAttributesList()
	{
		if (attributesList == null)
		{
			attributesList = new ArrayList<Attribute>();
			List<PersistantObject> list = this.getTableMaintMaster().getTableMaintDetailss().getPersistantObjectList();
			for (PersistantObject po : list) {
				TableMaintDetails tmd = (TableMaintDetails) po;
				attributesList.add(getAttribute(tmd.getColumnNameValue()));
			}	
		}
		return attributesList;
	}
	/**
	 * Apply the default values from tableMainsDetails 
	 */
	public void applyDefautValues() {
		if(this.getTableMaintMaster() == null)
			return;
		 List<PersistantObject>  tmds =  this.getTableMaintMaster().getTableMaintDetailss().getPersistantObjectList();
		 for (PersistantObject po : tmds)
		 {
			 TableMaintDetails tmd =  (TableMaintDetails)po ; 
			 if (tmd.getDefaultValueValue()!= null)
			 {  Attribute att = this.getAttribute(tmd.getColumnNameValue()) ;
			 //DO Not Apply default values for date	
			 if (att!= null && att.getTableMaintDetail() != null && !att.getTableMaintDetail().getDataTypeValue().equalsIgnoreCase("DATE"))
			 	{	
			 		String defaultValue = tmd.getDefaultValueValue(); 
			 		String dataType = tmd.getDataTypeValue() ; 
			 		if (dataType.equalsIgnoreCase("NUMBER") || dataType.equalsIgnoreCase("FLOAT"))
			 		{
			 			try{
			 			att.setValue(new BigDecimal(defaultValue));
			 			}
			 			catch(Exception e){}
			 		}
			 		else {att.setValue(defaultValue);}
				}
			 }
		 }
		
	}
	
	public Attribute getCreatedBy()
	{
		return this.getAttribute(PersistantObject.CREATED_BY_COLUMN_NAME , false);
	}
	
	public Attribute getCreatedAt()
	{
		return this.getAttribute(PersistantObject.CREATED_AT_COLUMN_NAME , false);
	}
	
	public Attribute getLastUpdatedBy()
	{
		return this.getAttribute(PersistantObject.LAST_UPDATE_BY_COLUMN_NAME , false);
	}
	public Attribute getLastUpdatedAt()
	{
		return this.getAttribute(PersistantObject.LAST_UPDATE_AT_COLUMN_NAME , false);
	}
	public static BigDecimal getPercWithBoundary(BigDecimal benValue , BigDecimal benPerc, BigDecimal min , BigDecimal max){
		BigDecimal result = (benValue.multiply(benPerc)).divide(new BigDecimal(100));
		if(result.compareTo(max)==1)
			result = max;
		else if (result.compareTo(min)==-1)
			result = min;
		return result;
	}

	private ArrayList<Attribute> modifiedAttributes = new ArrayList<Attribute>(); 
	private ArrayList<Attribute> invalidModifiedAttributes = new ArrayList<Attribute>();

	public ArrayList<Attribute> getModifiedAttributes() {
		return modifiedAttributes;
	}

	public ArrayList<Attribute> getInvalidModifiedAttributes() {
		return invalidModifiedAttributes;
	}

	protected synchronized void clearModifiedAttributesList()
	{
		ArrayList<Attribute> mas = this.getModifiedAttributes() ;  
		
		for (int i = 0 ; i< mas.size() ; i++)
		{
			Attribute matt = mas.get(i); 
			matt.revert() ; 
		}
		this. modifiedAttributes = new ArrayList<Attribute>(); 
		this.invalidModifiedAttributes = new ArrayList<Attribute>();
	}
	
	protected void addToModifiedAttList(Attribute pm_att)
	{
		ArrayList<Attribute> mal = this.getModifiedAttributes() ; 
		if (! mal.contains(pm_att))
		{
			mal.add(pm_att);
			this.informParntDS();
		}
		this.addToInvalidAttList(pm_att) ; 
		
	}
	protected void addToInvalidAttList(Attribute pm_att)
	{
		ArrayList<Attribute> invalidMal = this.getInvalidModifiedAttributes() ;  
		if (! pm_att.getValidationResult().isValidResult() && !invalidMal.contains(pm_att))
		{
			invalidMal.add(pm_att) ; 
		}
	}
	protected void removeFromModifiedAttList(Attribute pm_att)
	{
		this.getModifiedAttributes().remove(pm_att) ;
		this.getInvalidModifiedAttributes().remove(pm_att) ;
		this.informParntDS();
		
	}


	protected void informParntDS() {
		DataSet pds = this.getParentDataSet();
		if ( ! this.isContainsObjectKey() || pds == null) {return;} 

		ArrayList<PersistantObject> parentUpdatedList = pds.getUpdatedList();
		boolean alreadyIncluded = parentUpdatedList.contains(this);
		boolean changed = this.isChanged();
		
		if ( changed && ! alreadyIncluded)
		{
			parentUpdatedList.add(this);
			
		}
		else if ( !changed && alreadyIncluded)
		{
			parentUpdatedList.remove(this);
		}
		ParentPersistantObject ppo =  pds.getParent();
		if (ppo != null)
		{
			ppo.getPersistantObject().informParntDS();
		}
		pds.resetSaveAllCommandList();
		pds.getDsCalculator().reset();
	}
	
	public String getDisplayTxtWithDQ()
	{
		return this.getDisplayTxt().replace("'", "''");
	}
	public String getDisplayTxt()
	{
		String poStringVal = this.getClass().getName() +"@"+this.hashCode() ;
		try {
			DBKey uk = this.getUniqeKey();
			if (uk!= null)
			{
				poStringVal= uk.getEqualSqlString() ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	return poStringVal ;
	}
	
	
	public static ArrayList<SelectItem> convertListOfposToSelectItems(ArrayList<PersistantObject> pos , String pm_columnNameForId , String pm_columnNameForLable )
	{
		ArrayList<SelectItem> result =new ArrayList<SelectItem>();
		for (PersistantObject po : pos)
		{
			result.add(new SelectItem(po.getAttributeValue(pm_columnNameForId), (String) po.getAttributeValue(pm_columnNameForLable)));
		}
		return result ;
		
	}
	
	
	void afterSaveInitialization()
	{
		for (PersistantObject po : this.getReInitializeAfterSaveList())
		{
			po.initialize();
		}
	}
	
	// Write your own initialization code here this will help you greatly improve performance especially 
	// apply our standard rule < Minimise code inside any getXyz() method - simply return object -  > 
	public void initialize()
	{
	}

	public ArrayList<PersistantObject> getReInitializeAfterSaveList() {
		return reInitializeAfterSaveList;
	}
	
	public void addToReInitializeAfterSaveList(PersistantObject pm_ref)
	{
		this.getReInitializeAfterSaveList().add(pm_ref);
	}

	/**
	 * 
	 * @return A List of Tmd's for the forien keys of all the children 
	 */
	public ArrayList<TableMaintDetails> getChildrenForignKeysTmds()
	{
		ArrayList<TableMaintDetails> result = new ArrayList<TableMaintDetails>();
		 for ( DataSet ds :this.getChildernDataSetList())
		 {
			 DbForignKey[] fks = ds.getForignKeys().getDbForignKeyArray();
			 for (int i = 0 ; i< fks.length ; i++)
			 {
				 TableMaintMaster tmm = ds.getTableMaintMaster() ;
				 if ( tmm != null)
				 {
					 TableMaintDetails childTmd  = tmm.getTmdByColumnName(fks[i].getChildColumnName()) ;
					 if (childTmd != null)
					 {
						 result.add(childTmd) ;
					 }
				 }
			 }
		 }
		 return result ; 
	}
	
	public boolean isNeedSave()
	{
		return (! this.isContainsObjectKey() )  || isChanged() ;
	}
	
	
	/**
	 * This method takes percentage to get its value
	 * @param percent the percentage that you want to get its value
	 * @param value the calculated value
	 * @return percentage of value
	 */
	public static double getValueOfPercent(double percent, double value)
	{
		return (percent/100) * value;
	}
	
	/**
	 * This method takes value to get its percentage
	 * @param value The value that you want its percentage.
	 * @param totalValue The value that you want to get percentage from it.
	 * @return percentage of of value from totalValue
	 */
	public static  double getPercentOfValue(double value, double totalValue)
	{
		return (value*100) / totalValue;
	}
	
	/**
	 * 
	 */
	public boolean isSelectionAllowedInParentDataSet()
	{
		return true;
	}
    public  boolean checkFormulaSyntax(String pm_Expression)
    	{
    		ArrayList<String> usrDefVars= PersistantObject.getListOfVariables(pm_Expression, USER_DEFINED_DELIMTER);
    		if(usrDefVars != null )
    			for(String usrDefVarName : usrDefVars)
    			{
    				if(!usrDefVarName.equals(""))
    				{
    				 	UsrDefVar usrDefVar = this.getModuleServiceContainer().getUsrDefVarServices().getUserDefinedVariable(usrDefVarName,this.getTable().getTableName());
    					if(usrDefVar == null || !usrDefVar.getValidity().getBooleanValue())
    					{
    						return false;
    					}
    				pm_Expression = pm_Expression.replace(PersistantObject.USER_DEFINED_DELIMTER+usrDefVarName, "0");
    				}
    			}
    		ArrayList<String> sysVars= getListOfVariables(pm_Expression, SYS_DEFINED_DELIMTER);
    		if(sysVars != null)
    			for(String sysVar : sysVars)
    			{
    				pm_Expression = pm_Expression.replace(SYS_DEFINED_DELIMTER+sysVar, "0");
    			}
    		
    		ArrayList<String> loggedUsrProperties= getListOfVariables(pm_Expression, LOGGED_USER_PROPERTY_DELIMTER);
    		if(sysVars != null)
    			for(String loggedUsrProperty : loggedUsrProperties)
    			{
    				pm_Expression = pm_Expression.replace(LOGGED_USER_PROPERTY_DELIMTER+loggedUsrProperty, "0");
    			}
    		
    		ArrayList<String> otherBens= getListOfVariables(pm_Expression, OTHER_BENEFIT_DEFINED_DELIMTER);
    		if(sysVars != null)
    			for(String otherBen : otherBens)
    			{
    				pm_Expression = pm_Expression.replace(OTHER_BENEFIT_DEFINED_DELIMTER+otherBen, "0");
    			}
    
    		String query =  "select round (" + pm_Expression + " , 2) as result  from dual";
    		UsrDefVarServices uds = this.getModuleServiceContainer().getUsrDefVarServices() ; 
    		uds.checkFomulaSyntax(query);
    		return uds.isValidFormula();
    	}
    
    public void selectMeInParentDataSet()
    {
    	DataSet pds = this.getParentDataSet() ;
    	if (pds != null )
    	{
    		pds.setSelectedObject(this); 
    	}

    }
    
    public void markAsNew()
    {
    	this.getData().remove(OBJECT_KEY_NAME) ;
    	this.getData().remove(VIEW_TIMESTAMP_KEY) ;    	
    	Date nowDate = new Date() ;  
    	String createdBy = (String) this.getDbService().getLoggedUser().getUsrName().getValue() ; 
    	this.setAttributeValue(PersistantObject.CREATED_AT_COLUMN_NAME, nowDate) ;
    	this.setAttributeValue(PersistantObject.LAST_UPDATE_AT_COLUMN_NAME, nowDate) ;
    	this.setAttributeValue(PersistantObject.CREATED_BY_COLUMN_NAME, createdBy) ;
    	this.setAttributeValue(PersistantObject.LAST_UPDATE_BY_COLUMN_NAME, createdBy) ;
    	DataSet parentDataSet = this.getParentDataSet() ;  
    	parentDataSet.getNewList().add(this) ; 

 //   	parentDataSet.getPersistantObjectList().remove(this) ; 
    	
    	HashMap<String, DataSet> childerDS = this.getChildernDataSetMap();
		Iterator<String> it2 = childerDS.keySet().iterator();
		while (it2.hasNext())
		{
			String dataSetKey = it2.next();
			DataSet sourceDs = childerDS.get( dataSetKey);
			List<PersistantObject> sourcePersObjectList =  sourceDs.getPersistantObjectList();
			for (PersistantObject cpo : sourcePersObjectList )
			{
				cpo.markAsNew() ;
			}
							
		}			
		
    	parentDataSet.refreshSaveAllCommandList() ;
    	 
    }

	public void refreshInvalidAttList() {
		this.invalidModifiedAttributes = new ArrayList<Attribute>() ;
		ArrayList<Attribute> all =  this.getAttributesList() ;
		for (Attribute att : all)
		{
			if (att != null && !att.getValidationResult().isValidResult())
			{
				invalidModifiedAttributes.add(att) ; 
			}
		}
		
	}

	protected void reset()
	{
		this.clearModifiedAttributesList();
		this.informParntDS();
	}
	
	private boolean reloadAfterUpdate = true; 

	public void setReloadAfterUpdate(boolean reloadAfterUpdate) {
		this.reloadAfterUpdate = reloadAfterUpdate;
	}

	public boolean isReloadAfterUpdate() {
		return reloadAfterUpdate;
	}
	
	protected void onSuccessfulUpdate()
	{
		this.getData().remove(PersistantObject.VIEW_TIMESTAMP_KEY);
		this.getData().put(VIEW_TIMESTAMP_KEY, new Attribute(PersistantObject.VIEW_TIMESTAMP_KEY, new Date() , this));
		
		Attribute luAt = this.getData().remove(PersistantObject.LAST_UPDATE_AT_COLUMN_NAME);
		if (luAt != null)
		{
			this.getData().put(LAST_UPDATE_AT_COLUMN_NAME, new Attribute(PersistantObject.LAST_UPDATE_AT_COLUMN_NAME, new Date() , this));
			
		}
		
		Attribute luBy = this.getData().remove(PersistantObject.LAST_UPDATE_BY_COLUMN_NAME);
		if (luBy != null)
		{
			String userName = this.getDbService().getLoggedUser().getUsrNameValue() ;
			this.getData().put(LAST_UPDATE_BY_COLUMN_NAME, new Attribute(PersistantObject.LAST_UPDATE_BY_COLUMN_NAME, userName , this));
			
		}
	}
	
	public  String subLoggedUserProperties(String query)
	{
		String result =  query ;
		ArrayList<String> listOfLoggedUserPropertieNames = PersistantObject.getListOfVariables(query, PersistantObject.LOGGED_USER_PROPERTY_DELIMTER);
			
		  SecUsrDta loggedUser = this.getDbService().getLoggedUser() ; 
		  
		  for (String loogedUserPropertieName : listOfLoggedUserPropertieNames) 
		  {
			  Object attValue = loggedUser.getAttributeValue(loogedUserPropertieName.toUpperCase()) ;  
			  //If Not found in loggedUserinfor Search for it in the Emp Data
			  if (attValue == null  )
			  {
				  PersistantObject empDta =  loggedUser.getHrMasEmployeeData() ;
				  if (empDta != null)
				  {
					  attValue = empDta.getAttributeValue(loogedUserPropertieName.toUpperCase());
				  }
			  }

			  if ( attValue != null)
			   {
				  result = result.replace(PersistantObject.LOGGED_USER_PROPERTY_DELIMTER + loogedUserPropertieName, "'"+attValue+"'");
			   }
			  
		  }
		  
		  
		  return result ; 
	}

	public String createLikeMe()
	{
		String rowid = "";
		try
		{
			Connection con = this.getDbService().getConnection();
		    String statement= "BEGIN " +
		    		 "	CREATE_LIKE."+this.getTable().getTableName()+"(?,?);" +
		    		 "END;";
		    CallableStatement callStat = con.prepareCall(statement); 
		    callStat.setString(1, this.getRowIdString());
		    callStat.registerOutParameter(2, Types.VARCHAR);
		    callStat.execute();
		    rowid = callStat.getString(2);
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
		return rowid;
	}
	/**
	 * Flag to show Create Like Me button in search result actions 
	 * @return
	 */
	public boolean isShowCreateLikeMe()
	{
		return false;
	}
	/**
	 * Flag to delete the object by Stored Procedure if exist
	 * @return
	 */
	public boolean isDeletedByStoredProcedure()
	{
		return false;
	}

	private DataSet sysPoAttachmentGroupDS = null;
	public DataSet getSysPoAttachmentGroupDS() 
	{
		if(sysPoAttachmentGroupDS == null)
		try
		{
			sysPoAttachmentGroupDS = this.getTableMaintMaster().getSysPOAttachGroupDS() ; 
			sysPoAttachmentGroupDS.getProperties().put(SysPoAttachmentGroup.ATTACHMENT_OWNER_KEY, this);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return sysPoAttachmentGroupDS ;
	}
	public String getDisplayTxtWithoutQut()
	{
		return this.getDisplayTxt().replace("'", "");
	}
	private DataSet sysPoAttachmentsDS = null;
	public DataSet getSysPoAttachmentsDS() 
	{
		if(sysPoAttachmentsDS == null)
		try
		{
			String whereCluase = " where "+ SysPoAttachmentGroup.TABLE_OWNER +" = '"+this.getTableMaintMaster().getOwnerValue()+ "' and "+
								 SysPoAttachmentGroup.TABLE_NAME+" ='"+this.getTable().getTableName()+"' and " +
								 SysPoAttachmentGroup.PO_KEY+" ='"+ this.getDisplayTxtWithoutQut() +"'";
			String query = "select t.rowid, t.* from "+ SysPoAttachments.DB_TABLE_NAME +" t " + whereCluase;
			 sysPoAttachmentsDS =  this.getDbService().queryForDataSet(query, SysPoAttachments.class);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return sysPoAttachmentsDS ;
	}
	
	public void clear()
	{
		this.data = null;
		this.columns = null; 
		this.columnList = null;
		this.childernDataSetMap.clear();
		this.selectedInParentDataSet = false;
		this.refreshColumns = false; 
		this.parentDataSet = null;
		this.reInitializeAfterSaveList.clear();
		this.usrDefVarValues.clear();
		this.tempBlobs = null;
		this.parametrizedUpdateSting = "";
		this.columnList = null;
		this.uniqueKey = null ;
		this.translatedExpression = "";
		this.attributesList = null;
		this. modifiedAttributes.clear(); 
		this.invalidModifiedAttributes.clear();
		this.sysPoAttachmentGroupDS = null;
		this.sysPoAttachmentsDS = null;
		parent = null;
		dbService = null;
		tableMaintMaster = null;
		parametrizedInsertString = null;
		parametrizedUpdateSting = null;
		usrDefVarServices = null;
	}
	private static final String DEFAULT_PAGE_EDITOR = "/templates/include/poDefaultEditor.xhtml" ; 
	public String getDefaultEditorPage()
	{
		String result =  DEFAULT_PAGE_EDITOR;
			if (this.getTableMaintMaster() != null)
			{
				result = this.getTableMaintMaster().getObjectEditorXhtmlPageValue(); 
			}
		return result ;
	}
	
	private String selectedFkName ;
	private DbForignKeyArray userSelectedForignKeyArray ;

	public void setSelectedFkName(String pm_selectedChildTableName) {
		this.selectedFkName = pm_selectedChildTableName;
		this.refreshUserSelectedForignKeyArray();
	}

	private void refreshUserSelectedForignKeyArray() {
		String selectedFkName = this.getSelectedFkName();
		this.userSelectedForignKeyArray =  this.getTableMaintMaster().getDbForignKey(selectedFkName) ;
	}

	public DbForignKeyArray getUserSelectedForignKeyArray ()
	{
		return userSelectedForignKeyArray ;
		
	}

	public String getSelectedFkName() {
		return selectedFkName;
	}
	
	public DataSet geChildDsByForignKeyName(String fkName)
	{
		DataSet result = null;
		HashMap<String, DbForignKeyArray> cfks= this.getChildrenForignKeys();
		Class mapClass = null;
		
		DbForignKeyArray  dbfkArray = this.getTableMaintMaster().getDbForignKey(fkName) ;
		if (fkName != null && cfks != null && !cfks.containsKey(fkName))
		{
			if (dbfkArray.getDbForignKeyArray().length > 0) 
			{
				cfks.put(fkName, dbfkArray);
			}
		}
			String childTableName = dbfkArray.getDbForignKeyArray()[0].getChildTableName();
			String childTableOwner = dbfkArray.getDbForignKeyArray()[0].getChildTableOwner();
			TableMaintMasterServices tms = this.getModuleServiceContainer().getTableMaintServices();// new TableMaintMasterServices(this.getDbService()); 
			
			try 
				{
				String mapClassName = tms.getTableMaintMaster(new DbTable( childTableOwner , childTableName , this.getDbService())).getMapClassNameValue();
				if  (mapClassName != null)
				mapClass = Class.forName(mapClassName);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			
		
		
		try 
		{
			result = this.getChildrenDataSet(fkName, mapClass, false);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		 return result ;
		
	}
	public DataSet getUserSelectedChildDs()
	{
		String selectedFkName = this.getSelectedFkName();
		return this.geChildDsByForignKeyName(selectedFkName);
	}

	public int getHashCode()
	{
		return this.hashCode();
	}

	public ModuleServicesContainer getModuleServiceContainer()
	{
		return this.getDbService().getModuleServiceContainer(); // ApplicationContext.getClientModuleServicesContainer(this.getDbService()) ; 
	}
	
	public abstract void beforeAttributeChange(Attribute att) throws Exception;
	
	public abstract void afterAttributeChange(Attribute att);
	
	public MessagesCommnuicatorService getMessageCommnunicatorService()
	{
		return this.getModuleServiceContainer().getMessageCommnunicatorService(); 
	}
	
	public void auditDelete(Connection con) throws SQLException
	{
		String tableOwner = this.getTableMaintMaster().getOwnerValue() ;
		String tableName = this.getTableMaintMaster().getTableNameValue() ;
		String rowid = this.getRowIdString() ; 
		// Audit Master
		String insMasterStm = "Insert Into Support.Audit_Delete_master (Table_owner , Table_name , rowid_value )" 
				+ "Values ('"+tableOwner+"' , '"+tableName+"' , '"+rowid+"' )" ;
		Statement stmt = null ; 
		CallableStatement cs = null ; 
		try {
			stmt = con.createStatement() ; 
			stmt.execute(insMasterStm) ; 
			// Audit Details 		
			String insDetailStm = "Insert Into Support.Audit_Delete_details (Table_owner , Table_name , rowid_value , column_name , column_value ) "
			             +" values ('"+tableOwner+"' , '"+tableName+"' , '"+rowid+"' , ? , ?)" ; 
			cs = con.prepareCall(insDetailStm) ; 
			
			for ( String columnName : this.getColumnList())
			{
				Object columnValObj = this.getAttributeValue(columnName) ; 
				String columnValue = ( columnValObj != null ) ?  columnValObj.toString() : "" ;
				cs.setString(1, columnName); cs.setString(2, columnValue);
				cs.execute() ; 
			}
		} catch (Exception e)
		{
			throw new SQLException("Unable To Audit The Delete Operation Due to " + e.getMessage()) ; 
		}
		finally { 	
					if (stmt!= null) stmt.close();
					if (cs!= null) cs.close();
				}
	}
}
