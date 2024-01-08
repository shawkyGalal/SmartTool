package com.smartValue.pnu.map.auto;

import com.implex.database.PersistantObject; 
import com.implex.database.DataSet;
import com.implex.database.Attribute;
import com.implex.database.DbTable;
import com.implex.database.ApplicationContext;

public abstract class _IotPressureHistory extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "IOT_PRESSURE_HISTORY"; 
 public static final String DB_TABLE_OWNER = "PNU"; 

	public _IotPressureHistory(){
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

	public static final String TRANS_DATE ="TRANS_DATE" ;

	public void setTransDateValue(java.sql.Timestamp   pm_transDate){
		this.getAttribute(TRANS_DATE ).setValue( pm_transDate );
	}
 
	public java.sql.Timestamp getTransDateValue(){
		return (java.sql.Timestamp) this.getAttribute ( TRANS_DATE).getValue()  ;
	}
 
	public Attribute getTransDate(){
		return this.getAttribute ( TRANS_DATE)  ;
	}

	public static final String PRESSURE0 ="PRESSURE0" ;

	public void setPressure0Value(java.math.BigDecimal   pm_pressure0){
		this.getAttribute(PRESSURE0 ).setValue( pm_pressure0 );
	}
 
	public java.math.BigDecimal getPressure0Value(){
		return (java.math.BigDecimal) this.getAttribute ( PRESSURE0).getValue()  ;
	}
 
	public Attribute getPressure0(){
		return this.getAttribute ( PRESSURE0)  ;
	}

	public static final String PRESSURE1 ="PRESSURE1" ;

	public void setPressure1Value(java.math.BigDecimal   pm_pressure1){
		this.getAttribute(PRESSURE1 ).setValue( pm_pressure1 );
	}
 
	public java.math.BigDecimal getPressure1Value(){
		return (java.math.BigDecimal) this.getAttribute ( PRESSURE1).getValue()  ;
	}
 
	public Attribute getPressure1(){
		return this.getAttribute ( PRESSURE1)  ;
	}

	public static final String PRESSURE2 ="PRESSURE2" ;

	public void setPressure2Value(java.math.BigDecimal   pm_pressure2){
		this.getAttribute(PRESSURE2 ).setValue( pm_pressure2 );
	}
 
	public java.math.BigDecimal getPressure2Value(){
		return (java.math.BigDecimal) this.getAttribute ( PRESSURE2).getValue()  ;
	}
 
	public Attribute getPressure2(){
		return this.getAttribute ( PRESSURE2)  ;
	}

	public static final String PRESSURE3 ="PRESSURE3" ;

	public void setPressure3Value(java.math.BigDecimal   pm_pressure3){
		this.getAttribute(PRESSURE3 ).setValue( pm_pressure3 );
	}
 
	public java.math.BigDecimal getPressure3Value(){
		return (java.math.BigDecimal) this.getAttribute ( PRESSURE3).getValue()  ;
	}
 
	public Attribute getPressure3(){
		return this.getAttribute ( PRESSURE3)  ;
	}

	public static final String PRESSURE4 ="PRESSURE4" ;

	public void setPressure4Value(java.math.BigDecimal   pm_pressure4){
		this.getAttribute(PRESSURE4 ).setValue( pm_pressure4 );
	}
 
	public java.math.BigDecimal getPressure4Value(){
		return (java.math.BigDecimal) this.getAttribute ( PRESSURE4).getValue()  ;
	}
 
	public Attribute getPressure4(){
		return this.getAttribute ( PRESSURE4)  ;
	}

	public static final String PRESSURE5 ="PRESSURE5" ;

	public void setPressure5Value(java.math.BigDecimal   pm_pressure5){
		this.getAttribute(PRESSURE5 ).setValue( pm_pressure5 );
	}
 
	public java.math.BigDecimal getPressure5Value(){
		return (java.math.BigDecimal) this.getAttribute ( PRESSURE5).getValue()  ;
	}
 
	public Attribute getPressure5(){
		return this.getAttribute ( PRESSURE5)  ;
	}

	public static final String PRESSURE6 ="PRESSURE6" ;

	public void setPressure6Value(java.math.BigDecimal   pm_pressure6){
		this.getAttribute(PRESSURE6 ).setValue( pm_pressure6 );
	}
 
	public java.math.BigDecimal getPressure6Value(){
		return (java.math.BigDecimal) this.getAttribute ( PRESSURE6).getValue()  ;
	}
 
	public Attribute getPressure6(){
		return this.getAttribute ( PRESSURE6)  ;
	}

	// ********Start of Child DataSets getter methods******** 
	// *******End of Child DataSets getter methods******** 
}