package com.smartValue.service.impl;

import com.sideinternational.sas.BaseException;
import com.sideinternational.sas.service.Operator;
import com.sideinternational.sas.service.Zone;
import com.sideinternational.sas.service.authorization.AuthorizationService;

public class AuthorizationServiceImpl implements AuthorizationService {

	public boolean hasApplicationAccess(String pm_applicationName, Operator pm_operator) throws BaseException 
	{
		return true;
	}

	public boolean hasFunctionAccess(String pm_functionName, String pm_moduleName, String pm_applicationName, Operator pm_operator) throws BaseException 
	{
		return true;
	}

	public boolean hasFunctionAccess(Zone pm_zone, String pm_functionName, String pm_moduleName, String pm_applicationName, Operator pm_operator) throws BaseException 
	{
		return true;
	}

	public boolean hasModuleAccess(String pm_moduleName, String pm_applicationName, Operator pm_operator) throws BaseException 
	{
		return true;
	}

	public boolean hasWriteZoneAccess(String pm_zoneName, Operator pm_operator) throws BaseException 
	{
		return true;
	}

}
