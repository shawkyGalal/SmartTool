package com.implex.database;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.implex.database.map.TableMaintDetails;
import com.implex.database.map.TableMaintMaster;

public abstract class DataSetCalculator {
	
	private DataSet dataSet ;
	protected HashMap<String , BigDecimal> sumValues ; 
	//private HashMap<String , BigDecimal> avgValues ;
	
	public abstract void reset();
	
	protected abstract void runCalculations() ;

	public HashMap<String , BigDecimal> getSumValues()
	{
		if (sumValues == null)
		{
			sumValues =this.calcSumValues() ; 
		}
		return sumValues ; 
	}
	
	protected HashMap<String , BigDecimal> calcSumValues() {
		
		HashMap<String , BigDecimal> result  = new HashMap<String, BigDecimal>();
			//avgValues = new HashMap<String, BigDecimal>();
			BigDecimal tempValue,avg,attValue;
		
			TableMaintMaster tmm = this.getDataSet().getTableMaintMaster() ;
			if (tmm != null)
			{
				List<PersistantObject> poList = this.getDataSet().getPersistantObjectList(); 
				for (PersistantObject po :  poList )
				{
					List<PersistantObject> list = po.getTableMaintMaster().getTableMaintDetailss().getPersistantObjectList();
					for (PersistantObject po1 : list ) 
					{
						TableMaintDetails tmd = (TableMaintDetails) po1 ; 
						if(tmd.isNumric())
						{
							tempValue = result.get(tmd.getColumnNameValue());
							if(tempValue == null)
								tempValue = new BigDecimal("0.00");
							try{
								attValue = (BigDecimal)po.getAttributeValue(tmd.getColumnNameValue());
								attValue = attValue != null ? attValue : new BigDecimal("0");
								tempValue = tempValue.add(attValue);
								result.put(tmd.getColumnNameValue(), tempValue);
							}
							catch(Exception e) {}
						}
					}
				}
			}
			else
				
			{
				ArrayList<Header> headers =this.getDataSet().getHeaders() ; 
				if (headers != null)
				{
					List<PersistantObject> poList = this.getDataSet().getPersistantObjectList(); 
					for (Header header : headers ) 
					{
						tempValue = new BigDecimal("0.00");
						String columnName = header.getColumnName() ;
						
						for (PersistantObject po :  poList )
						{
							try
							{
									
								attValue = (BigDecimal)po.getAttributeValue(columnName);
								attValue = attValue != null ? attValue : new BigDecimal("0");
								tempValue = tempValue.add(attValue);
								result.put(columnName, tempValue);
							}
							catch(Exception e) {}
						}
					}
				}
			}
				
	 return result ; 
	}
	
	public DataSetCalculator(DataSet pm_dataSet) {
		if (pm_dataSet == null) throw new IllegalArgumentException();
		this.dataSet = pm_dataSet ;
		this.runCalculations();
	}

	
	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
		this.runCalculations();
	}

	public DataSet getDataSet() {
		return dataSet;
	}


}
