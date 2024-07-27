package com.smartValue.pnu.map.auto;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.Attribute;
import com.smartValue.database.DataSet;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;

public abstract class _IotDevice extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "IOT_DEVICE"; 
 public static final String DB_TABLE_OWNER = "PNU"; 

	public _IotDevice(){
	}

	 public DbTable getTable() 
	 { 
		return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String DEVICE_ID ="DEVICE_ID" ;

	public void setDeviceIdValue(java.lang.String   pm_deviceId){
		this.getAttribute(DEVICE_ID ).setValue( pm_deviceId );
	}
 
	public java.lang.String getDeviceIdValue(){
		return (java.lang.String) this.getAttribute ( DEVICE_ID).getValue()  ;
	}
 
	public Attribute getDeviceId(){
		return this.getAttribute ( DEVICE_ID)  ;
	}

	public static final String ACCESS_TOKEN ="ACCESS_TOKEN" ;

	public void setAccessTokenValue(java.lang.String   pm_accessToken){
		this.getAttribute(ACCESS_TOKEN ).setValue( pm_accessToken );
	}
 
	public java.lang.String getAccessTokenValue(){
		return (java.lang.String) this.getAttribute ( ACCESS_TOKEN).getValue()  ;
	}
 
	public Attribute getAccessToken(){
		return this.getAttribute ( ACCESS_TOKEN)  ;
	}

	public static final String PATIENT_ID ="PATIENT_ID" ;

	public void setPatientIdValue(java.math.BigDecimal   pm_patientId){
		this.getAttribute(PATIENT_ID ).setValue( pm_patientId );
	}
 
	public java.math.BigDecimal getPatientIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( PATIENT_ID).getValue()  ;
	}
 
	public Attribute getPatientId(){
		return this.getAttribute ( PATIENT_ID)  ;
	}

	public static final String PATIENT_NAME ="PATIENT_NAME" ;

	public void setPatientNameValue(java.lang.String   pm_patientName){
		this.getAttribute(PATIENT_NAME ).setValue( pm_patientName );
	}
 
	public java.lang.String getPatientNameValue(){
		return (java.lang.String) this.getAttribute ( PATIENT_NAME).getValue()  ;
	}
 
	public Attribute getPatientName(){
		return this.getAttribute ( PATIENT_NAME)  ;
	}

	// ********Start of Child DataSets getter methods******** 
	public DataSet getDatasetFk1IotDeviceConfig()
	{
		 return this.geChildDsByForignKeyName("FK1_IOT_DEVICE_CONFIG");
	}
	public DataSet getDatasetIotPressureHistoryFk1()
	{
		 return this.geChildDsByForignKeyName("IOT_PRESSURE_HISTORY_FK1");
	}
	// *******End of Child DataSets getter methods******** 
}