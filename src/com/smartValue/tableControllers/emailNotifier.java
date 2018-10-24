package com.smartValue.tableControllers;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;


import Support.Misc;
import Support.SqlReader;

import com.implex.database.ApplicationContext;
import com.implex.database.DirectJdbcServiceImpl;
import com.implex.database.PersistantObject;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.TableMaintMaster;
import com.implex.database.map.TableNotificationRule;
import com.smartValue.UnCommittedDbTransaction;
import com.smartValue.support.map.QueryNotifier;

public class emailNotifier extends com.smartValue.tableControllers.ItableTriggerController
{

	@Override
	public void afterDelete(Connection mCon, String mRowId) throws Exception 
	{
		TableMaintMaster tmm =  this.getTableMaintMaster(); 
		//TODO				
	}

	@Override
	public void afterInsert(Connection mCon, String mRowId) throws Exception{
		TableMaintMaster tmm =  this.getTableMaintMaster(); 
		//TODO	
	}

	@Override
	public void afterUpdate(Connection mCon, String mRowId, String mColumnName, Object m_newValue)		throws Exception 
	{
		TableMaintMaster tmm =  this.getTableMaintMaster(); 
		//TODO				
	
	}

	@Override
	public void beforeDelete(Connection mCon, String mRowId) throws Exception {
		TableMaintMaster tmm =  this.getTableMaintMaster(); 
		//TODO				
		
	}

	@Override
	public void beforeInsert(Connection mCon) throws Exception {
		TableMaintMaster tmm =  this.getTableMaintMaster(); 
		//TODO		
		
	}

	@Override
	public void beforeUpdate(Connection mCon, String mRowId, String mColumnName , Object m_newValue)	throws Exception {
		TableMaintMaster tmm =  this.getTableMaintMaster(); 
		//TODO				
		
	}

	@Override
	public void upponInserCommit(Connection mCon, SecUsrDta m_logged_user, String mRowId) throws Exception 
	{
		ArrayList<PersistantObject> tnrs =  (ArrayList<PersistantObject>) this.getTableMaintMaster().getTableNotificationRuleDS(true).getPersistantObjectList() ;
		for (PersistantObject po : tnrs)
		{
			TableNotificationRule tnr = (TableNotificationRule) po ;
			if (!tnr.getActive().getBooleanValue()){continue ; }
			boolean applyRule = false ; 
			applyRule = isRuleApplicable( mCon, mRowId , tnr ) ; 
			if ( ! applyRule ) {continue ; }
			if ( tnr.getOperationValue().intValue() ==  UnCommittedDbTransaction.INSERT_OPERATION ) 
			{
				BigDecimal queryID = tnr.getInsertQueryIdValue() ; 
				if (queryID == null ){throw new Exception ("Table Notification Rule of Type Insert to Table <"+ tnr.getTableNameValue()+"> Without Defining Query ID for Notification"); } 
				boolean notificationMandatory = tnr.getIsMandatory().getBooleanValue() ; 
				Vector<String> parmNames = new Vector<String>() ; 
				Vector<String> parmValues = new Vector<String>() ;
				parmNames.add("$$objectRowId");
				parmValues.add(mRowId);
				parmNames.add("$$appUrl");
				parmValues.add(Misc.appUrl );
				
				try 
				{
					SqlReader sqlr = new SqlReader(mCon , "LU_QUERIES", "Query_body", queryID.toString(), parmNames, parmValues, false , this.getTableMaintMaster().getDbService().getSelectedEnv()) ;
					sqlr.setLoggedUser(m_logged_user); 
					
					HashMap<String, String> params = new HashMap<String, String>(); 
					params.put("$$objectRowId", mRowId);
					params.put("$$appUrl", Misc.appUrl);				
					sqlr.sendNotificationByTableNotifiRule(-1, mCon,  tnr , params , true) ;
				}
				catch (Exception e) 
				{
					String msg = "Failure to use TableNotificationRule "+tnr+ "to Notify Users Due To " + e.getMessage() ; 
					m_logged_user.getDbService().getModuleServiceContainer().getMessageCommnunicatorService().sendMessageToUser(msg);
					if (notificationMandatory)
					{
						throw new Exception (msg) ; 
					}
				}
			}
		} 
	}

	private boolean isRuleApplicable(Connection mCon, String mRowId, TableNotificationRule tnr) throws SQLException {
		
		boolean result = false ; 
		String applyScope = tnr.getApplyScopeValue() ; 
		String qs = "Select * From " + tnr.getTableOwnerValue() + "." + tnr.getTableNameValue() + " t Where " + applyScope + " And t.rowid = '" + mRowId + "'" ;
		Statement stmt = mCon.createStatement() ;  
		ResultSet rs = stmt.executeQuery(qs) ; 
		while (rs.next())
		{
			result= true ; break; 
		}
		DirectJdbcServiceImpl.releaseResources(rs, stmt, null) ; 
		return result;
	}

	@Override
	public void upponUpdateCommit(Connection mCon, SecUsrDta m_logged_user , String mRowId, String mColumnName, Object mOldValue, Object mNewValue) throws Exception {
		ArrayList<PersistantObject> tnrs =  (ArrayList<PersistantObject>) this.getTableMaintMaster().getTableNotificationRuleDS(true).getPersistantObjectList() ;
		for (PersistantObject po : tnrs)
		{
			TableNotificationRule tnr = (TableNotificationRule) po ;
			if (!tnr.getActive().getBooleanValue()){continue ; }
			boolean applyRule = false ; 
			applyRule = isRuleApplicable( mCon, mRowId , tnr ) ; 
			if ( ! applyRule ) {continue ; }
			if ( tnr.getOperationValue().intValue() ==  UnCommittedDbTransaction.UPDATE_OPERATION ) 
			{

				BigDecimal queryID = tnr.getUpdateQueryIdValue() ; 
				if (queryID == null ){throw new Exception ("Table Notification Rule of Type Update of Table <"+ tnr.getTableNameValue()+"> Without Defining Query ID for Notification"); }
				boolean notificationMandatory = tnr.getIsMandatory().getBooleanValue() ; 
				String tnrColumnName = tnr.getColumnNameValue() ;
							
				if (mColumnName == null || (mColumnName != null && mColumnName.equals(tnrColumnName)))
				{
					Vector<String> parmNames = new Vector<String>() ; 
					Vector<String> parmValues = new Vector<String>() ;
					parmNames.add("$$objectRowId");
					parmValues.add(mRowId);
					parmNames.add("$$newValue");
					parmValues.add((String)mNewValue);
					parmNames.add("$$oldValue");
					parmValues.add((String)mOldValue);
					parmNames.add("$$appUrl");
					parmValues.add(Misc.appUrl );
					try 
					{
						SqlReader sqlr = new SqlReader(mCon , "LU_QUERIES", "Query_body", queryID.toString(), parmNames, parmValues, false ,  this.getTableMaintMaster().getDbService().getSelectedEnv()) ;
						sqlr.setLoggedUser(m_logged_user); 
						HashMap<String, String> params = new HashMap<String, String>(); 
						params.put("$$objectRowId", mRowId); 
						params.put("$$appUrl", Misc.appUrl);		
						params.put("$$oldValue", (String)mOldValue);
						params.put("$$newValue", (String)mNewValue);
						
						sqlr.sendNotificationByTableNotifiRule(-1, mCon,  tnr , params , true ) ;
					}
					catch (Exception e) 
					{
						if (notificationMandatory)
						{
							throw new Exception ("Failure to Notify Users Due To " + e.getMessage()) ; 
						}
					}
				}
			}
		}
		
		
	}
	

}
