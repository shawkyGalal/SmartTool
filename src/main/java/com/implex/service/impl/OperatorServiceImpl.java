package com.implex.service.impl;

import java.util.HashMap;
import java.util.List;

import com.implex.database.DbServiceFactory;
import com.implex.database.DbServices;
import com.implex.database.PersistantObject;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.TableMaintMaster;
import com.implex.database.map.services.ModuleServicesContainer;
import com.sideinternational.sas.BaseException;
import com.sideinternational.sas.event.resource.security.EventSecu1000;
import com.sideinternational.sas.event.resource.security.EventSecu1001;
import com.sideinternational.sas.service.Group;
import com.sideinternational.sas.service.Operator;
import com.sideinternational.sas.service.OperatorException;
import com.sideinternational.sas.service.OperatorService;
import com.sideinternational.web.swaf.SWAF;


public class OperatorServiceImpl implements OperatorService{

	
	public void changePassword(Operator pm_operator, String pm_newPassword)
			throws BaseException {
		// TODO Auto-generated method stub
		
	}

	
	public Operator find(String pm_operatorId, String pm_password) throws BaseException {
		DbServices dbService = DbServiceFactory.create();
		List<PersistantObject> users ;
		try {
			dbService.initializeForWeb();
			users = dbService.queryForDataSet("Select * from "+ TableMaintMaster.CDB_SCHEMA_OWNER+".SEC_USR_DTA where USR_NAME = '" + pm_operatorId + "'" , SecUsrDta.class).getPersistantObjectList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseException(e);
		}
		if (users.isEmpty()) throw new OperatorException(new EventSecu1001(pm_operatorId));
		SecUsrDta loggedUser = (SecUsrDta) users.get(0);
		
		Operator operator =(Operator) loggedUser;
		// Checks if the password is correct.
		if (operator.getPassword() == null)
		{
			if (pm_password != null) throw new OperatorException(new EventSecu1000(pm_operatorId));
		}
		else if (!operator.getPassword().equals(pm_password)) throw new OperatorException(new EventSecu1000(pm_operatorId));
		else 
			{
				ModuleServicesContainer msc =  (ModuleServicesContainer) SWAF.getModuleServiceContatiner();
				dbService.setLoggedUser(loggedUser);
				msc.setDbServices(dbService);
				loggedUser.setDbService(dbService);
			}
		
		return operator;
	}

	
	public Operator[] find(HashMap pm_attributes, Operator pm_operator)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Operator[] find(Group pm_group) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean isPasswordExpired(Operator pm_operator) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void remove(Operator[] pm_operators, Operator pm_operator)
			throws BaseException {
		// TODO Auto-generated method stub
		
	}

	
	public void save(Operator pm_source, HashMap pm_newAttributes,
			Operator pm_operator) throws BaseException {
		// TODO Auto-generated method stub
		
	}

	
	public void save(HashMap pm_attributes, Operator pm_operator)
			throws BaseException {
		// TODO Auto-generated method stub
		
	}

}
