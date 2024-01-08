package com.implex.sysMnuTypes;

import com.implex.database.ApplicationContext;
import com.implex.database.map.SysMnu;
import com.implex.database.map.services.ModuleServicesContainer;
import com.implex.database.map.services.SysFrmServices;

public class SqlExecuter implements SysMnuSelectionProcessor {

	
	public void processSelection(SysMnu selectecSysMnu) {
		ModuleServicesContainer msc = selectecSysMnu.getModuleServiceContainer() ;  //(ModuleServicesContainer) SWAF.getManagedBean("moduleServicesContainer");
		msc.setPageId(SysFrmServices.SQL_EXECUTER_PAGE);
	}

	
	public void applyDefaults(SysMnu selectecSysMnu) {
		selectecSysMnu.getMnuPrgName().setValue(SysMnu.Menu_SQL_PAGE);
		
	}

}
