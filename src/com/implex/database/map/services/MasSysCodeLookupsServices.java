package com.implex.database.map.services;

import java.util.ArrayList;

import javax.faces.model.SelectItem;

import com.implex.database.DbServices;
import com.implex.database.DbTable;
import com.implex.database.map.TableMaintMaster;

public class MasSysCodeLookupsServices extends ModuleServices {

	
	public MasSysCodeLookupsServices(DbServices pmDbServices) {
		super(pmDbServices);
	}
	private ArrayList<SelectItem> allYearData = null;
	private ArrayList<SelectItem> allLocationData = null;
	private ArrayList<SelectItem> allEmployeeData = null;
	private ArrayList<SelectItem> allMonthData = null;
	
	private ArrayList<SelectItem> allRegionData = null;
	private ArrayList<SelectItem> allCountryData = null;
	private ArrayList<SelectItem> allCityData = null;
	private ArrayList<SelectItem> allTeriorityData = null;
	 
	@Override
	public boolean isChanged() {
		return this.getDataSet().isChanged() ; 
	}
	//Begin region hierarchy
	private String regionId = null;
	public void setRegionId(String regionId) {
		this.regionId = regionId;
		this.calculateCountry();
	}
	public String getRegionId() {
		return regionId;
	}

	private String countryId = null;
	public void setCountryId(String countryId) {
		this.countryId = countryId;
		this.calculateCity();
	}
	public String getCountryId() {
		return countryId;
	}

	private String cityId = null;
	public void setCityId(String cityId) {
		this.cityId = cityId;
		this.calculateTeriority();
	}
	public String getCityId() {
		return cityId;
	}
	
	private String triorityId = null;
	public void setTriorityId(String triorityId) {
		this.triorityId = triorityId;
	}
	public String getTriorityId() {
		return triorityId;
	}

	private void calculateRegion()
	{
		try{
			String query = "select T.REG_ID, T.REG_NAME_, T.REG_NAME from MAS_REGION t";
			allRegionData = this.getDbServices().getSelectItems(query, true);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void calculateCountry()
	{
		try{
			if(this.regionId == null)
			{
				SelectItem item = new SelectItem(null, "<Select>");
				allCountryData = new ArrayList<SelectItem>();
				allCountryData.add(item);
			}else{
				String query = "select T.CTY_ID, T.CTY_NAME_, T.CTY_NAME from MAS_COUNTRY t where T.REG_ID = " + this.regionId;
				allCountryData = this.getDbServices().getSelectItems(query, true);
			}
		}catch (Exception e) {

		}
	}
	
	private void calculateCity()
	{
		try{
			if(this.regionId == null || this.countryId ==null)
			{
				SelectItem item = new SelectItem(null, "<Select>");
				allCityData = new ArrayList<SelectItem>();
				allCityData.add(item);
			}else{
				String query = "select T.CITY_ID, T.CITY_NAME_, T.CITY_NAME from MAS_CITY t where T.CTY_ID = '" + this.countryId + "' and T.REG_ID = " + this.regionId;
				allCityData = this.getDbServices().getSelectItems(query, true);
			}
		}catch (Exception e) {

		}
	}
	
	private void calculateTeriority()
	{
		try{
			if(this.regionId == null || this.countryId ==null || this.cityId == null)
			{
				SelectItem item = new SelectItem(null, "<Select>");
				allTeriorityData = new ArrayList<SelectItem>();
				allTeriorityData.add(item);
			}else{
				String query = "select T.TER_ID, T.TER_NAME_, T.TER_NAME from MAS_TERRITORY t where T.REG_ID = " + this.regionId +" and T.CTY_ID = '" + this.countryId +"' and T.CITY_ID = " + this.cityId;
				allTeriorityData = this.getDbServices().getSelectItems(query, true);
			}
		}catch (Exception e) {

		}
	}
	
	public ArrayList<SelectItem> getAllRegionData() 
	{
		if(allRegionData == null)
		{
			calculateRegion();
		}
		return allRegionData;
	}
	
	public ArrayList<SelectItem> getAllCountryData() 
	{
		if(allCountryData == null)
		{
			calculateCountry();
		}
		return allCountryData;
	}
	
	public ArrayList<SelectItem> getAllCityData() 
	{
		if(allCityData == null)
		{
			calculateCity();
		}
		return allCityData;
	}
	
	public ArrayList<SelectItem> getAllTeriorityData() 
	{
		if(allTeriorityData == null)
		{
			calculateTeriority();
		}
		return allTeriorityData;
	}
	
	//End region hierarchy
	public ArrayList<SelectItem> getAllMonthData() throws Exception {
		if (allMonthData == null) {
			String query = "SELECT t.CODE_ID, t.CODE_NAME_, CODE_NAME FROM SYS_CODE t where T.TBL_ID = 'MONTHS_DTA'";
			allMonthData = this.getDbServices().getSelectItems(query, true);
		}
		return allMonthData;
	}
	 
	public ArrayList<SelectItem> getAllYearData() throws Exception {
		if (allYearData == null) {
			String query = "SELECT t.CODE_ID, t.CODE_NAME_, CODE_NAME FROM SYS_CODE t where T.TBL_ID = 'YEARS_DTA'";
			allYearData = this.getDbServices().getSelectItems(query, true);
		}
		return allYearData;
	}

	public ArrayList<SelectItem> getAllLocationData() throws Exception {
		if (allLocationData == null) {
			allLocationData = new ArrayList<SelectItem>() ;
			allLocationData.add(new SelectItem(null,"<Select>") );
		}
		return allLocationData;
	}
	//TODO Ahmed ... There is already method in empdataservices for that... please use it.
	public ArrayList<SelectItem> getAllEmployeeData() throws Exception {
		if (allEmployeeData == null) {
			String query = "SELECT EMPLOYEE_ID , EMPLOYEE_NAME_ , EMPLOYEE_NAME FROM HR_MAS_EMPLOYEE_DATA T where T.CMP_ID="+this.getDbServices().getLoggedUser().getUsrCmpIdValue();
			allEmployeeData = this.getDbServices().getSelectItems(query, true);
		}
		return allEmployeeData;
	}
	private ArrayList<SelectItem> allInsTypes = null;
	// TODO : Ahmed , this should be generic method with input string for  'INS_TYPES' ... to use it for any other syscodes
	public ArrayList<SelectItem> getAllInsTypes() throws Exception {
		if (allInsTypes == null) {
			String query = "Select T.CODE_ID, T.CODE_NAME_, T.CODE_NAME from  "+ TableMaintMaster.CDB_SCHEMA_OWNER+".sys_code t where T.TBL_ID = 'INS_TYPES'";
			allInsTypes = this.getDbServices().getSelectItems(query, false);
		}
		return allInsTypes;
	}
	
	@Override
	public DbTable getDbTable() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getOrginalQuery() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Class getPersistentClass() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isCanHaveMultipleInstances() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void afterModuleRemoved() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeModuleRemoved() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
