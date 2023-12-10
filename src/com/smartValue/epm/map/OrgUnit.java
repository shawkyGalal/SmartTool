package com.smartValue.epm.map;


import com.implex.database.DataSet;

public class OrgUnit extends com.implex.database.map.OrgUnit {
	
		private DataSet bscObjectivesDs ; 
		public DataSet getBscObjectives()
		{
			if (bscObjectivesDs == null)
			{
				String queryStr = "Select * from "+ BscObjective.DB_TABLE_OWNER +"."+ BscObjective.DB_TABLE_NAME  
					+" Where "+ BscObjective.BUSINESS_UNIT + " = '" + this.getOrgUnitIdValue() + "'"
					+" Order by  "+ BscObjective.OBJECTIVE_ID ;
				
				 bscObjectivesDs = this.getDbService().queryForDataSet(queryStr, com.smartValue.epm.map.BscObjective.class) ;

			}
			return bscObjectivesDs ;
		}
		
}