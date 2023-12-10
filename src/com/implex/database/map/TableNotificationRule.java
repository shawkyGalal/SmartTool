package com.implex.database.map;

import Support.Misc;
import Support.SqlReader;

import com.implex.database.ApplicationContext;
import com.implex.database.DataSet;
import com.implex.database.PersistantObject;
import com.implex.database.Attribute;
import com.implex.database.AttributeChangeValidator;
import com.implex.database.map.auto._TableNotificationRule;
import com.implex.database.map.security.PersistentObjectSecurityControl;
import com.implex.database.trigger.TriggerHandler;
import com.implex.database.audit.AuditInDbTriggerHandler;
import com.implex.database.DbForignKeyArray;
import com.smartValue.support.map.QueryNotifier;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class TableNotificationRule extends _TableNotificationRule {
	 private static HashMap<String, DbForignKeyArray> childrenForignKeys;
	@Override
	public HashMap<String, DbForignKeyArray> getChildrenForignKeys() 
	{
		 if (childrenForignKeys == null) 
		  { childrenForignKeys = new HashMap<String, DbForignKeyArray>(); 
			  for (DbForignKeyArray fka : this.getTableMaintMaster().getAllForignKeysArrays()) 
			  { 
			  childrenForignKeys.put(fka.getName(), fka); 
			  } 
			  //TODO... Fill Your Childern 
			  //Example ... this.getTableMaintMaster().getDbForignKey(new DbTable("ICDB" , SavedSearchDetail.DB_TABLE_NAME)); 
		  } 
	 return childrenForignKeys; 
	}
	@Override
	public PersistentObjectSecurityControl getSecurityController() 
	{
		return null;
	}
 
	 private static TriggerHandler triggerHandler = null; 
	@Override
	public TriggerHandler getTriggerHandler() 
	{
		 boolean auditable = this.getTableMaintMaster().getAuditable().getBooleanValue(); 
		 if (triggerHandler == null && auditable ) 
			{ 
				triggerHandler = new AuditInDbTriggerHandler(); 
			} 
		 return triggerHandler; 
	}
	@Override 
	public AttributeChangeValidator getAttributeChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key)
	{ 
		return null; 
	}
	 public void initialize() 
	 { 
		//Write your own initialization code here this will help you greatly improve performance especially
		// apply our standard rule < Minimise code inside any getXyz() method - simply return object -  > 
	 } 
	  private com.implex.database.PersistantObject  abc ;  
	  public com.implex.database.PersistantObject getAbc() 
	  { 
		 if ( this.abc== null)
		 {
			 refreshAbc() ;  
		 }
		 return abc ; 
	  } 
	  private com.implex.database.PersistantObject calcAbc() 
	  { 
		  com.implex.database.PersistantObject result = null ; 
		  //Write Down Your Code here to calculate the value of abc... 
		  return result ;  
	  } 
	 
	  public void refreshAbc() 
	  { 
		  //Important Please call this method in the afterAttributeChange() method for any attribute used to calculate this variable 
		  this.abc = calcAbc() ; 
	  } 
		@Override
		public void beforeAttributeChange(Attribute pm_att) throws Exception {
		}
		@Override
		public void afterAttributeChange(Attribute pm_att) {
		}
		private String getEmailTo(Connection m_con ,HashMap<String, String> params  ) throws Exception
		{
			return getQueryResult(m_con , this.getEmailtoQueryListValue() , params) ;
		}
		private String getEmailCc(Connection m_con ,HashMap<String, String> params) throws Exception
		{
			return getQueryResult(m_con, this.getEmailccQueryListValue() , params) ; 
		}
		private String getQueryResult(Connection m_con , String m_query , HashMap<String, String> m_params) throws Exception 
		{
			String result = null;
			if (m_query == null ) return result ; 
			Iterator<Map.Entry<String , String>> it = m_params.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry<String , String> pair = (Map.Entry<String , String>)it.next();
		        m_query = Misc.repalceAll(m_query, (String)pair.getKey(), (String)pair.getValue()) ;
		        
		    }
		    Statement stmt = null ;
		    ResultSet rs = null ; 
		    try {
		    	stmt = m_con.createStatement() ; 
		    	rs = stmt.executeQuery(m_query); 
		    	while (rs.next())
		    	{result = rs.getString(1);}
		    	if (rs!= null) rs.close() ; 
	    		if (stmt !=null) stmt.close() ;
		     }
		    catch ( Exception e) { 
		    	if (rs!= null) rs.close() ; 
	    		if (stmt !=null) stmt.close() ;
		    	throw new Exception ("Unable to Extrcat <Notify Email To> from Query \n" +  m_query ) ; }
		    return result ; 
		}
		
		public QueryNotifier toQueryNotifier(Connection m_con , HashMap<String, String> m_params) throws Exception 
		{
			DataSet qnDataSet = this.getDbService().queryForDataSet("Select * from support." + QueryNotifier.DB_TABLE_NAME + " Where 1<>1", QueryNotifier.class); 
			qnDataSet.addNew() ; 
			
			QueryNotifier result = (QueryNotifier)qnDataSet.getCurrentItem();
			result.setEmailToValue( this.getEmailTo(m_con , m_params)) ; 
			result.setEmailCcValue(this.getEmailCc(m_con, m_params)) ;
			result.setEmailSubjectValue(this.getEmailSubjectValue()); 
			result.setDescriptionValue(this.getEmailHeaderValue());
			result.setQueryIdValue(new BigDecimal(-222)) ; // Dummy query Id to fetch an empty parameters child dataset   
						
			return result;
		}
}