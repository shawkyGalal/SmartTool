package com.smartValue.database;

import java.math.BigDecimal;
public class DataSetSimpleCalculator extends DataSetCalculator {



	public DataSetSimpleCalculator(DataSet pmDataSet) {
		super(pmDataSet);
	}

	@Override
	public void reset() {
		sumValues = null ;
		//avgValues = null;
	}

	@Override
	protected void runCalculations() {
		this.sumValues = this.calcSumValues() ;
		
	}
	
	public BigDecimal getAvg(String pm_columnName , int pm_roundMode)
	{
		BigDecimal result = null ; 
		int size =  this.getDataSet().getPersistantObjectList().size() ;
		if (size > 0)
		{
			BigDecimal sumValue = this.getSumValues().get(pm_columnName) ; 
			result =  sumValue.divide(new BigDecimal(size),pm_roundMode );
		}
		return result ;
	}


}
