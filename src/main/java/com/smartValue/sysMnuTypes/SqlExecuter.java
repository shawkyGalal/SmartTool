package com.smartValue.sysMnuTypes;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.map.SysMnu;
import com.smartValue.database.map.services.ModuleServicesContainer;
import com.smartValue.database.map.services.SysFrmServices;

public class SqlExecuter implements SysMnuSelectionProcessor {

	
	public void processSelection(SysMnu selectecSysMnu) {
		ModuleServicesContainer msc = selectecSysMnu.getModuleServiceContainer() ;  //(ModuleServicesContainer) SWAF.getManagedBean("moduleServicesContainer");
		msc.setPageId(SysFrmServices.SQL_EXECUTER_PAGE);
	}

	
	public void applyDefaults(SysMnu selectecSysMnu) {
		selectecSysMnu.getMnuPrgName().setValue(SysMnu.Menu_SQL_PAGE);
		
	}

}
