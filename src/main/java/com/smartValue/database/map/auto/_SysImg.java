package com.smartValue.database.map.auto;

import com.smartValue.database.Attribute;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;


public abstract class _SysImg extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */

	public _SysImg(){
	}

	 public DbTable getTable() 
	 { 
		return new DbTable("ICDB" , "SYS_IMG" , this.getDbService()); 
	 } 

	public static final String IMG_ID ="IMG_ID" ;

	public void setImgIdValue(java.lang.String   pm_imgId){
		this.getAttribute("IMG_ID" ).setValue( pm_imgId );
	}
 
	public java.lang.String getImgIdValue(){
		return (java.lang.String) this.getAttribute ( "IMG_ID").getValue()  ;
	}
 
	public Attribute getImgId(){
		return this.getAttribute ( "IMG_ID")  ;
	}

	public static final String IMG_DESC ="IMG_DESC" ;

	public void setImgDescValue(java.lang.String   pm_imgDesc){
		this.getAttribute("IMG_DESC" ).setValue( pm_imgDesc );
	}
 
	public java.lang.String getImgDescValue(){
		return (java.lang.String) this.getAttribute ( "IMG_DESC").getValue()  ;
	}
 
	public Attribute getImgDesc(){
		return this.getAttribute ( "IMG_DESC")  ;
	}

	public static final String IMG_NAME ="IMG_NAME" ;

	public void setImgNameValue(java.lang.String   pm_imgName){
		this.getAttribute("IMG_NAME" ).setValue( pm_imgName );
	}
 
	public java.lang.String getImgNameValue(){
		return (java.lang.String) this.getAttribute ( "IMG_NAME").getValue()  ;
	}
 
	public Attribute getImgName(){
		return this.getAttribute ( "IMG_NAME")  ;
	}

	public static final String IMG_NAME_ ="IMG_NAME_" ;

	public void setImgName_Value(java.lang.String   pm_imgName_){
		this.getAttribute("IMG_NAME_" ).setValue( pm_imgName_ );
	}
 
	public java.lang.String getImgName_Value(){
		return (java.lang.String) this.getAttribute ( "IMG_NAME_").getValue()  ;
	}
 
	public Attribute getImgName_(){
		return this.getAttribute ( "IMG_NAME_")  ;
	}

	public static final String IMG_PATH ="IMG_PATH" ;

	public void setImgPathValue(java.lang.String   pm_imgPath){
		this.getAttribute("IMG_PATH" ).setValue( pm_imgPath );
	}
 
	public java.lang.String getImgPathValue(){
		return (java.lang.String) this.getAttribute ( "IMG_PATH").getValue()  ;
	}
 
	public Attribute getImgPath(){
		return this.getAttribute ( "IMG_PATH")  ;
	}

	public static final String MODIFIED_DT ="MODIFIED_DT" ;

	public void setModifiedDtValue(java.sql.Timestamp   pm_modifiedDt){
		this.getAttribute("MODIFIED_DT" ).setValue( pm_modifiedDt );
	}
 
	public java.sql.Timestamp getModifiedDtValue(){
		return (java.sql.Timestamp) this.getAttribute ( "MODIFIED_DT").getValue()  ;
	}
 
	public Attribute getModifiedDt(){
		return this.getAttribute ( "MODIFIED_DT")  ;
	}

	public void setImgNameAutoLang(java.lang.String   pm_imgName){
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		this.getImgName().setValue( pm_imgName );
		 else 
		this.getImgName_().setValue( pm_imgName );
	}
 
	public Attribute getImgNameAutoLang(){
		 Attribute result ;
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		 result =   this.getImgName();
		 else 
		 result =   this.getImgName_();
		 return result;
	}
}