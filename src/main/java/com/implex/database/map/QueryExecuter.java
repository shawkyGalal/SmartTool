package com.implex.database.map;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import Support.SqlReaderOld; 

import com.implex.database.ApplicationContext;
import com.implex.database.Attribute;
import com.implex.database.ClobAttribute;
import com.implex.database.DataSet;
import com.implex.database.DbServices;
import com.implex.database.PersistantObject;
import com.implex.database.map.services.SqlBoundVarsServices;

public class QueryExecuter {
	
	private ClobAttribute query ;
	private DbServices dbServices ; 
	private ArrayList<DataSet> dataSetQueryResults = new ArrayList<DataSet>();
	private ArrayList<Exception> queryCompilingResult ;
//	private int selectedDataSetIndex;
	private PersistantObject queryParamValues ;
	// Array for linked Reports .. 
	// In presentation layer, each item in this list could be displayed as a link to another report after setting its queryParamValues with the execution result of this query
	private ArrayList<SysMnu> linkedSysMnuReports ;
	private SysMnu  parentSysMnu ; 
	private SqlReaderOld sqlReader = null ;
	
	public String getUniqueDataSetkey()
	{
		String result = "sqlResult_"; 
		SysMnu parentSysMnu = this.getParentSysMnu() ;  
 		result += (parentSysMnu != null )? parentSysMnu.getMnuCodeValue()+"_" : ""  ;
 		
 		int selectedDataSetIndex = this.getSelectedDataSetIndex() ;
 		result += (selectedDataSetIndex != -1 )? selectedDataSetIndex : ""  ;
 		
		return result ; 
	}
	public int getSelectedDataSetIndex()
	{
		return this.dataSetQueryResults.indexOf(this.selectedDS) ; 
	}

	public String getSelectedDataSetTitle()
	{
		String result ="" ;
		int index = this.getSelectedDataSetIndex() ;
		if ( index != -1  )
		{
			result=  this.getSqlReader().getQueryTitles()[index] ;
		}
		return result ; 
		
	}
	public SysMnu getParentSysMnu()
	{
		return parentSysMnu ;
	}
	public QueryExecuter(SysMnu sysMnu , DbServices pm_dbs) 
	{	parentSysMnu = sysMnu ;
		Attribute mnuSql = sysMnu.getMnuSql() ;  
		if ( mnuSql != null && mnuSql instanceof ClobAttribute )
		{
			query  = (ClobAttribute)mnuSql;
		}
		dbServices = pm_dbs;
	}
	public QueryExecuter(ClobAttribute pm_query , DbServices pm_dbs) 
	{
		query  = pm_query; 
		dbServices = pm_dbs;
	}
	

	public SqlReaderOld getSqlReader() 
	{
		if (sqlReader == null)
			this.refreshSqlReader();
		return sqlReader;
	}
	
	private void refreshSqlReader()
	{
		Vector<String> names = new Vector<String>();
		Vector<String> values = new Vector<String>();
		List<PersistantObject> sqlbondvars=  this.getSqlBindVars();
		for (PersistantObject po : sqlbondvars)
		{
			SqlBoundVars sbv =  (SqlBoundVars)po;
			Object objVal = sbv.getCurrentValue();
			String value = sbv.getDefaultValValue(); 
			if (objVal != null)
			 value = ( objVal instanceof Date )? Attribute.convertDateToSql((Date)objVal) :  objVal.toString();
			values.add( value );
			names.add( sbv.getBoundVarNameValue()); 
		}
		//Adding parameters passed from external
		PersistantObject externalParm = this.getQueryParamValues();
		if (externalParm != null)
		{
			for (Attribute at : externalParm.getData().values())
			{
				if (at != null)
				{
					names.add(PersistantObject.INPUT_PARAMETER_DELIMTER + at.getKey());
					values.add(String.valueOf(at.getValue()));
				}
			}
		}
		try{
			ClobAttribute query = (ClobAttribute) this.getQuery() ; 
		sqlReader =  new SqlReaderOld(query.getClobStrValue(), names , values , false);
		}
		catch(Exception e )
		{
			e.printStackTrace();
			//ApplicationContext.getMessageCommnunicatorService().sendExceptionToUser(e);
		}
	}
	
	List<PersistantObject> sqlBondVars ; 
	public List<PersistantObject> getSqlBindVars()
	{
		if (this.getQuery().isChanged() || sqlBondVars == null)
		{
			SqlBoundVarsServices sbvs =  this.getDbServices().getModuleServiceContainer().getSqlBoundVarsServices();
			ClobAttribute query = (ClobAttribute) this.getQuery() ; 
			sqlBondVars = sbvs.getSqlBoundVarByNames( PersistantObject.getListOfVariables(query.getClobStrValue(), SqlBoundVars.VAR_DELIMITER) );
		}
		return sqlBondVars ; 
		
	}
	

	public void executeQuery(){
			
			this.refreshSqlReader();
			SqlReaderOld sqlr =  this.getSqlReader();
			if (sqlr == null ) { return ; }
			String[] qs = sqlr.getQueryStatments();
			dataSetQueryResults = new ArrayList<DataSet>();
			queryCompilingResult = new ArrayList<Exception>();
			DbServices dbs = this.getDbServices();
			for (int i =0 ; i< qs.length ; i++)
			{
				try{
					if (qs[i] != null)
					{
						DataSet ds = dbs.queryForDataSet(qs[i],null) ;
						if (ds!= null)
						this.dataSetQueryResults.add( ds );
					}
				}
				catch(Exception e)
				{	
					queryCompilingResult.add(e);
				}
			}
			
}
	
	private DataSet selectedDS ; 
	
	public DataSet getSelectedDS()
	{ 
		return selectedDS ; 
//		if( !this.dataSetQueryResults.isEmpty() && this.selectedDataSetIndex < this.dataSetQueryResults.size() )
//			return this.dataSetQueryResults.get(selectedDataSetIndex);
//		return null;
	}


	public Attribute getQuery() {
		return (this.parentSysMnu != null)? this.parentSysMnu.getMnuSql(): query	;
	}

	public DbServices getDbServices() {
		return dbServices;
	}

	public ArrayList<DataSet> getDataSetQueryResults()
	{
		return this.dataSetQueryResults ; 
	}
	
	public ArrayList<Exception> getQueryCompilingResult()
	{
		return this.queryCompilingResult ;
	}
	
	public void setQueryParamValues(PersistantObject queryParamValues) {
		this.queryParamValues = queryParamValues;
	}

	public PersistantObject getQueryParamValues() {
		return queryParamValues;
	}

	public void setLinkedSysMnuReports(ArrayList<SysMnu> linkedSysMnuReports) {
		this.linkedSysMnuReports = linkedSysMnuReports;
	}

	public ArrayList<SysMnu> getLinkedSysMnuReports() {
		return linkedSysMnuReports;
	}

	public void setSelectedDS(DataSet selectedDS) {
		this.selectedDS = selectedDS;
	}

	public void clearResults()
	{
		dataSetQueryResults.clear();
	}
}
