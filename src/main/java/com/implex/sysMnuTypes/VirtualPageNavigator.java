package com.implex.sysMnuTypes;

import com.implex.database.ApplicationContext;
import com.implex.database.map.SysMnu;
import com.implex.database.map.services.ModuleServicesContainer;
import com.implex.database.map.services.SysFrmServices;
import com.sideinternational.web.swaf.SWAF;

public class VirtualPageNavigator implements SysMnuSelectionProcessor {


	
	public void processSelection(SysMnu selectecSysMnu) {
		ModuleServicesContainer msc = selectecSysMnu.getModuleServiceContainer() ;  //(ModuleServicesContainer) SWAF.getManagedBean("moduleServicesContainer");
		msc.getTableMaintServices().setTableName(selectecSysMnu.getVpdatabasetableValue());

		msc.getTableMaintServices().getCurrentTable().getViewControllar().setSearchOpened(false);
		msc.getTableMaintServices().getCurrentTable().getViewControllar().setShowSearchResult(false);
		msc.getTableMaintServices().getCurrentTable().getSearchResult().setSelectedObject(null);
		msc.setPageId(SysFrmServices.GLOBAL_PAGE_PATH);


	}

	
	public void applyDefaults(SysMnu selectecSysMnu) {
		
		selectecSysMnu.getMnuPrgName().setValue(SysMnu.VIRTUAL_PAGE);
		
		
	}

}
