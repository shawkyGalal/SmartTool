package com.smartValue.pnu.map;

import Support.HttpPoster;

import com.smartValue.pnu.map.auto._IotDevice; 
import com.implex.database.PersistantObject;
import com.implex.database.Attribute;
import com.implex.database.AttributeChangeValidator;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.security.PersistentObjectSecurityControl;
import com.implex.database.trigger.TriggerHandler;
import com.implex.database.audit.AuditInDbTriggerHandler;
import com.implex.database.DbForignKeyArray;

import java.math.BigDecimal;
import java.util.HashMap;

public class IotDevice extends _IotDevice {
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
		@Override
		public void beforeAttributeChange(Attribute pm_att) throws Exception {
		}
		@Override
		public void afterAttributeChange(Attribute pm_att) {
		}
		
		
		public BigDecimal readPressure (int m_point_no) throws Exception
		{
			BigDecimal v_result = new BigDecimal(0); 
			String // url =  "https://api.spark.io/v1/devices/54ff74066672524851441167/pressure0?access_token=ad3450c09c801f56eb95a96245c7090571a9b5e2" ; 
			url = "https://api.spark.io/v1/devices/"+this.getDeviceIdValue()+"/pressure"+m_point_no+"?access_token="+this.getAccessTokenValue() ;
			//URL
			HttpPoster htpp = new HttpPoster(url , null , null , null) ; 
				
			return v_result ; 
			
		}
		
		public void storeNewValues() throws Exception
		{
			this.getDatasetIotPressureHistoryFk1().addNew();
			IotPressureHistory history = (IotPressureHistory) this.getDatasetIotPressureHistoryFk1().getCurrentItem();
			history.setPressure0Value(readPressure(0)) ;
			history.setPressure1Value(readPressure(1)) ;
			history.setPressure2Value(readPressure(2)) ;
			history.setPressure3Value(readPressure(3)) ;
			
			history.save(); 
			   
		}
}