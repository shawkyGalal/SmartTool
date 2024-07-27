package com.smartValue.sysMnuTypes;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.map.SysMnu;
import com.smartValue.database.map.services.ModuleServicesContainer;

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
