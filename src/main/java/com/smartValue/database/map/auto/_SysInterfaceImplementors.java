package com.smartValue.database.map.auto;

import com.smartValue.database.Attribute;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;


public abstract class _SysInterfaceImplementors extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "SYS_INTERFACE_IMPLEMENTORS"; 
 public static final String DB_TABLE_OWNER = "ICDB";

	public _SysInterfaceImplementors(){
	}

	 public DbTable getTable() 
	 { 
		return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String IMP_CODE ="IMP_CODE" ;

	public void setImpCodeValue(java.math.BigDecimal   pm_impCode){
		this.getAttribute("IMP_CODE" ).setValue( pm_impCode );
	}
 
	public java.math.BigDecimal getImpCodeValue(){
		return (java.math.BigDecimal) this.getAttribute ( "IMP_CODE").getValue()  ;
	}
 
	public Attribute getImpCode(){
		return this.getAttribute ( "IMP_CODE")  ;
	}

	public static final String IMP_DESC ="IMP_DESC" ;

	public void setImpDescValue(java.lang.String   pm_impDesc){
		this.getAttribute("IMP_DESC" ).setValue( pm_impDesc );
	}
 
	public java.lang.String getImpDescValue(){
		return (java.lang.String) this.getAttribute ( "IMP_DESC").getValue()  ;
	}
 
	public Attribute getImpDesc(){
		return this.getAttribute ( "IMP_DESC")  ;
	}

	public static final String IMP_DESC_ ="IMP_DESC_" ;

	public void setImpDesc_Value(java.lang.String   pm_impDesc_){
		this.getAttribute("IMP_DESC_" ).setValue( pm_impDesc_ );
	}
 
	public java.lang.String getImpDesc_Value(){
		return (java.lang.String) this.getAttribute ( "IMP_DESC_").getValue()  ;
	}
 
	public Attribute getImpDesc_(){
		return this.getAttribute ( "IMP_DESC_")  ;
	}

	public static final String IMP_CLASS ="IMP_CLASS" ;

	public void setImpClassValue(java.lang.String   pm_impClass){
		this.getAttribute("IMP_CLASS" ).setValue( pm_impClass );
	}
 
	public java.lang.String getImpClassValue(){
		return (java.lang.String) this.getAttribute ( "IMP_CLASS").getValue()  ;
	}
 
	public Attribute getImpClass(){
		return this.getAttribute ( "IMP_CLASS")  ;
	}

	public static final String IMP_INTERFACE ="IMP_INTERFACE" ;

	public void setImpInterfaceValue(java.lang.String   pm_impInterface){
		this.getAttribute("IMP_INTERFACE" ).setValue( pm_impInterface );
	}
 
	public java.lang.String getImpInterfaceValue(){
		return (java.lang.String) this.getAttribute ( "IMP_INTERFACE").getValue()  ;
	}
 
	public Attribute getImpInterface(){
		return this.getAttribute ( "IMP_INTERFACE")  ;
	}

	public void setImpDescAutoLang(java.lang.String   pm_impDesc){
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		this.getImpDesc().setValue( pm_impDesc );
		 else 
		this.getImpDesc_().setValue( pm_impDesc );
	}
 
	public Attribute getImpDescAutoLang(){
		 Attribute result ;
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		 result =   this.getImpDesc();
		 else 
		 result =   this.getImpDesc_();
		 return result;
	}
}
