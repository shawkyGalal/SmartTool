package com.smartValue.database.map.services;

import javax.servlet.http.HttpSessionBindingEvent;

public class AbcModuleServiceContainer extends ModuleServicesContainer {


	public  AbcModuleServiceContainer() throws Exception
	{
		super();
	}
	
	public  AbcModuleServiceContainer(String SelectedEnv , int selectedLang) throws Exception
	{
		super(SelectedEnv, selectedLang );
	}

	@Override
	public void loadAllModules() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
