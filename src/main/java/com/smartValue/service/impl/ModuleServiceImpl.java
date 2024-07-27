package com.smartValue.service.impl;

import com.sideinternational.sas.BaseException;
import com.sideinternational.sas.service.authorization.Application;
import com.sideinternational.sas.service.authorization.Module;
import com.sideinternational.sas.service.authorization.ModuleService;
import com.sideinternational.sas.service.authorization.Profile;
import com.sideinternational.sas.service.impl.scdb.entity.ProfileRights;

public class ModuleServiceImpl implements ModuleService{

	
	public Module[] find(String pm_applicationId) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Module find(String pm_moduleName, String pm_applicationName)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Module[] findAvailableModules(Profile pm_profile,
			Application pm_application) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Module[] findModules(Profile pm_profile, Application pm_application)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ProfileRights[] findModulesProfileRights(Profile pm_profile,
			Application pm_application)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
