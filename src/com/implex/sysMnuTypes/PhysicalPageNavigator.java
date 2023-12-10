package com.implex.sysMnuTypes;

import com.implex.database.ApplicationContext;
import com.implex.database.map.SysMnu;
import com.implex.database.map.services.ModuleServicesContainer;

public class PhysicalPageNavigator implements SysMnuSelectionProcessor {



	
	public void processSelection(SysMnu selectecSysMnu) {
		String goToPageName = (String) selectecSysMnu.getMnuPrgName().getValue();		
		goToPageName = goToPageName != null ? goToPageName : ModuleServicesContainer.DEFAULT_HOME_PAGE ;
		String pageTitle = (String) selectecSysMnu.getMnuTxtAutoLang().getValue();
		pageTitle = pageTitle !=null? pageTitle : ModuleServicesContainer.DEFAULT_PAGE_TITLE;
		
		ModuleServicesContainer msc = selectecSysMnu.getModuleServiceContainer() ;  //(ModuleServicesContainer) SWAF.getManagedBean("moduleServicesContainer");
		msc.setPageId(goToPageName);
		msc.setPageTitle(pageTitle);



	}

	
	public void applyDefaults(SysMnu selectecSysMnu) {
		
		selectecSysMnu.getMnuPrgName().revert();
		
	}

}
