package com.smartValue.database.map.auto;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.Attribute;
import com.smartValue.database.DataSet;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;

public abstract class _DataTableDisplayProperities extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "DATA_TABLE_DISPLAY_PROPERITIES"; 
 public static final String DB_TABLE_OWNER = "ICDB";

	public _DataTableDisplayProperities(){
	}

	 public DbTable getTable() 
	 { 
			return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String ID ="ID" ;

	public void setIdValue(java.lang.String   pm_id){
		this.getAttribute(ID ).setValue( pm_id );
	}
 
	public java.lang.String getIdValue(){
		return (java.lang.String) this.getAttribute ( ID).getValue()  ;
	}
 
	public Attribute getId(){
		return this.getAttribute ( ID)  ;
	}

	public static final String DISPLAY_DEFAULT_CONTROL ="DISPLAY_DEFAULT_CONTROL" ;

	public void setDisplayDefaultControlValue(java.lang.String   pm_displayDefaultControl){
		this.getAttribute(DISPLAY_DEFAULT_CONTROL ).setValue( pm_displayDefaultControl );
	}
 
	public java.lang.String getDisplayDefaultControlValue(){
		return (java.lang.String) this.getAttribute ( DISPLAY_DEFAULT_CONTROL).getValue()  ;
	}
 
	public Attribute getDisplayDefaultControl(){
		return this.getAttribute ( DISPLAY_DEFAULT_CONTROL)  ;
	}

	public static final String ON_MOUSE_OVER_STYLE ="ON_MOUSE_OVER_STYLE" ;

	public void setOnMouseOverStyleValue(java.lang.String   pm_onMouseOverStyle){
		this.getAttribute(ON_MOUSE_OVER_STYLE ).setValue( pm_onMouseOverStyle );
	}
 
	public java.lang.String getOnMouseOverStyleValue(){
		return (java.lang.String) this.getAttribute ( ON_MOUSE_OVER_STYLE).getValue()  ;
	}
 
	public Attribute getOnMouseOverStyle(){
		return this.getAttribute ( ON_MOUSE_OVER_STYLE)  ;
	}

	public static final String SHOW_FOOTER ="SHOW_FOOTER" ;

	public void setShowFooterValue(java.lang.String   pm_showFooter){
		this.getAttribute(SHOW_FOOTER ).setValue( pm_showFooter );
	}
 
	public java.lang.String getShowFooterValue(){
		return (java.lang.String) this.getAttribute ( SHOW_FOOTER).getValue()  ;
	}
 
	public Attribute getShowFooter(){
		return this.getAttribute ( SHOW_FOOTER)  ;
	}

	public static final String MAX_PAGES ="MAX_PAGES" ;

	public void setMaxPagesValue(java.lang.String   pm_maxPages){
		this.getAttribute(MAX_PAGES ).setValue( pm_maxPages );
	}
 
	public java.lang.String getMaxPagesValue(){
		return (java.lang.String) this.getAttribute ( MAX_PAGES).getValue()  ;
	}
 
	public Attribute getMaxPages(){
		return this.getAttribute ( MAX_PAGES)  ;
	}

	public static final String DATA_TABLE_ROWS ="DATA_TABLE_ROWS" ;

	public void setDataTableRowsValue(java.lang.String   pm_dataTableRows){
		this.getAttribute(DATA_TABLE_ROWS ).setValue( pm_dataTableRows );
	}
 
	public java.lang.String getDataTableRowsValue(){
		return (java.lang.String) this.getAttribute ( DATA_TABLE_ROWS).getValue()  ;
	}
 
	public Attribute getDataTableRows(){
		return this.getAttribute ( DATA_TABLE_ROWS)  ;
	}

	public static final String ON_MOUSE_OUT_STYLE ="ON_MOUSE_OUT_STYLE" ;

	public void setOnMouseOutStyleValue(java.lang.String   pm_onMouseOutStyle){
		this.getAttribute(ON_MOUSE_OUT_STYLE ).setValue( pm_onMouseOutStyle );
	}
 
	public java.lang.String getOnMouseOutStyleValue(){
		return (java.lang.String) this.getAttribute ( ON_MOUSE_OUT_STYLE).getValue()  ;
	}
 
	public Attribute getOnMouseOutStyle(){
		return this.getAttribute ( ON_MOUSE_OUT_STYLE)  ;
	}

	public static final String PREPARED_BY ="PREPARED_BY" ;

	public void setPreparedByValue(java.lang.String   pm_preparedBy){
		this.getAttribute(PREPARED_BY ).setValue( pm_preparedBy );
	}
 
	public java.lang.String getPreparedByValue(){
		return (java.lang.String) this.getAttribute ( PREPARED_BY).getValue()  ;
	}
 
	public Attribute getPreparedBy(){
		return this.getAttribute ( PREPARED_BY)  ;
	}

	public static final String PREPARED_DT ="PREPARED_DT" ;

	public void setPreparedDtValue(java.sql.Timestamp   pm_preparedDt){
		this.getAttribute(PREPARED_DT ).setValue( pm_preparedDt );
	}
 
	public java.sql.Timestamp getPreparedDtValue(){
		return (java.sql.Timestamp) this.getAttribute ( PREPARED_DT).getValue()  ;
	}
 
	public Attribute getPreparedDt(){
		return this.getAttribute ( PREPARED_DT)  ;
	}

	public static final String MODIFIED_BY ="MODIFIED_BY" ;

	public void setModifiedByValue(java.lang.String   pm_modifiedBy){
		this.getAttribute(MODIFIED_BY ).setValue( pm_modifiedBy );
	}
 
	public java.lang.String getModifiedByValue(){
		return (java.lang.String) this.getAttribute ( MODIFIED_BY).getValue()  ;
	}
 
	public Attribute getModifiedBy(){
		return this.getAttribute ( MODIFIED_BY)  ;
	}

	public static final String MODIFIED_DT ="MODIFIED_DT" ;

	public void setModifiedDtValue(java.sql.Timestamp   pm_modifiedDt){
		this.getAttribute(MODIFIED_DT ).setValue( pm_modifiedDt );
	}
 
	public java.sql.Timestamp getModifiedDtValue(){
		return (java.sql.Timestamp) this.getAttribute ( MODIFIED_DT).getValue()  ;
	}
 
	public Attribute getModifiedDt(){
		return this.getAttribute ( MODIFIED_DT)  ;
	}

	public static final String SHOW_DS_EXPLORER_BUTTON ="SHOW_DS_EXPLORER_BUTTON" ;

	public void setShowDsExplorerButtonValue(java.lang.String   pm_showDsExplorerButton){
		this.getAttribute(SHOW_DS_EXPLORER_BUTTON ).setValue( pm_showDsExplorerButton );
	}
 
	public java.lang.String getShowDsExplorerButtonValue(){
		return (java.lang.String) this.getAttribute ( SHOW_DS_EXPLORER_BUTTON).getValue()  ;
	}
 
	public Attribute getShowDsExplorerButton(){
		return this.getAttribute ( SHOW_DS_EXPLORER_BUTTON)  ;
	}

	public static final String SHOW_NAVIGATION ="SHOW_NAVIGATION" ;

	public void setShowNavigationValue(java.lang.String   pm_showNavigation){
		this.getAttribute(SHOW_NAVIGATION ).setValue( pm_showNavigation );
	}
 
	public java.lang.String getShowNavigationValue(){
		return (java.lang.String) this.getAttribute ( SHOW_NAVIGATION).getValue()  ;
	}
 
	public Attribute getShowNavigation(){
		return this.getAttribute ( SHOW_NAVIGATION)  ;
	}

	public static final String SHOW_SAVE_BUTTON ="SHOW_SAVE_BUTTON" ;

	public void setShowSaveButtonValue(java.lang.String   pm_showSaveButton){
		this.getAttribute(SHOW_SAVE_BUTTON ).setValue( pm_showSaveButton );
	}
 
	public java.lang.String getShowSaveButtonValue(){
		return (java.lang.String) this.getAttribute ( SHOW_SAVE_BUTTON).getValue()  ;
	}
 
	public Attribute getShowSaveButton(){
		return this.getAttribute ( SHOW_SAVE_BUTTON)  ;
	}

	public static final String SHOW_ADD_NEW ="SHOW_ADD_NEW" ;

	public void setShowAddNewValue(java.lang.String   pm_showAddNew){
		this.getAttribute(SHOW_ADD_NEW ).setValue( pm_showAddNew );
	}
 
	public java.lang.String getShowAddNewValue(){
		return (java.lang.String) this.getAttribute ( SHOW_ADD_NEW).getValue()  ;
	}
 
	public Attribute getShowAddNew(){
		return this.getAttribute ( SHOW_ADD_NEW)  ;
	}

	public static final String SHOW_FILTER ="SHOW_FILTER" ;

	public void setShowFilterValue(java.lang.String   pm_showFilter){
		this.getAttribute(SHOW_FILTER ).setValue( pm_showFilter );
	}
 
	public java.lang.String getShowFilterValue(){
		return (java.lang.String) this.getAttribute ( SHOW_FILTER).getValue()  ;
	}
 
	public Attribute getShowFilter(){
		return this.getAttribute ( SHOW_FILTER)  ;
	}

	public static final String SHOW_SORT ="SHOW_SORT" ;

	public void setShowSortValue(java.lang.String   pm_showSort){
		this.getAttribute(SHOW_SORT ).setValue( pm_showSort );
	}
 
	public java.lang.String getShowSortValue(){
		return (java.lang.String) this.getAttribute ( SHOW_SORT).getValue()  ;
	}
 
	public Attribute getShowSort(){
		return this.getAttribute ( SHOW_SORT)  ;
	}

	public static final String SHOW_TOOLBAR ="SHOW_TOOLBAR" ;

	public void setShowToolbarValue(java.lang.String   pm_showToolbar){
		this.getAttribute(SHOW_TOOLBAR ).setValue( pm_showToolbar );
	}
 
	public java.lang.String getShowToolbarValue(){
		return (java.lang.String) this.getAttribute ( SHOW_TOOLBAR).getValue()  ;
	}
 
	public Attribute getShowToolbar(){
		return this.getAttribute ( SHOW_TOOLBAR)  ;
	}

	public static final String EXTENDED_DATA_TABLE ="EXTENDED_DATA_TABLE" ;

	public void setExtendedDataTableValue(java.lang.String   pm_extendedDataTable){
		this.getAttribute(EXTENDED_DATA_TABLE ).setValue( pm_extendedDataTable );
	}
 
	public java.lang.String getExtendedDataTableValue(){
		return (java.lang.String) this.getAttribute ( EXTENDED_DATA_TABLE).getValue()  ;
	}
 
	public Attribute getExtendedDataTable(){
		return this.getAttribute ( EXTENDED_DATA_TABLE)  ;
	}

	public static final String ALLOW_MULTIBLE_SELECTION ="ALLOW_MULTIBLE_SELECTION" ;

	public void setAllowMultibleSelectionValue(java.lang.String   pm_allowMultibleSelection){
		this.getAttribute(ALLOW_MULTIBLE_SELECTION ).setValue( pm_allowMultibleSelection );
	}
 
	public java.lang.String getAllowMultibleSelectionValue(){
		return (java.lang.String) this.getAttribute ( ALLOW_MULTIBLE_SELECTION).getValue()  ;
	}
 
	public Attribute getAllowMultibleSelection(){
		return this.getAttribute ( ALLOW_MULTIBLE_SELECTION)  ;
	}

	public static final String SHOW_DS_MANIPULATOR ="SHOW_DS_MANIPULATOR" ;

	public void setShowDsManipulatorValue(java.lang.String   pm_showDsManipulator){
		this.getAttribute(SHOW_DS_MANIPULATOR ).setValue( pm_showDsManipulator );
	}
 
	public java.lang.String getShowDsManipulatorValue(){
		return (java.lang.String) this.getAttribute ( SHOW_DS_MANIPULATOR).getValue()  ;
	}
 
	public Attribute getShowDsManipulator(){
		return this.getAttribute ( SHOW_DS_MANIPULATOR)  ;
	}

	public static final String SHOW_REFRESH_BUTTON ="SHOW_REFRESH_BUTTON" ;

	public void setShowRefreshButtonValue(java.lang.String   pm_showRefreshButton){
		this.getAttribute(SHOW_REFRESH_BUTTON ).setValue( pm_showRefreshButton );
	}
 
	public java.lang.String getShowRefreshButtonValue(){
		return (java.lang.String) this.getAttribute ( SHOW_REFRESH_BUTTON).getValue()  ;
	}
 
	public Attribute getShowRefreshButton(){
		return this.getAttribute ( SHOW_REFRESH_BUTTON)  ;
	}

	// ********Start of Child DataSets getter methods******** 
	// *******End of Child DataSets getter methods******** 
}