package com.implex.service.impl;

import com.sideinternational.sas.BaseException;
import com.sideinternational.sas.service.Operator;
import com.sideinternational.sas.service.OperatorServiceFactory;
import com.sideinternational.sas.service.authentication.AuthenticationService;
import com.sideinternational.web.swaf.SWAF;

public class AuthenticationServiceImpl implements AuthenticationService{

	
	public Operator authenticate(String pm_operatorId, String pm_password)
			throws BaseException {		
		return OperatorServiceFactory.createOperatorService().find(pm_operatorId, pm_password); 
	}

	
	public void changePassword(String pm_operatorId, String pm_oldPassword,
			String pm_newPassword) throws BaseException {
		// TODO Sakr..
		// 1- Simulate Login
		// 2- getCurrentlyLoggedUser
		// 3- set new password
		// 4- save SecUsrDta object
		
		SWAF.addErrorMessage(null, "Your Password Changed Successfully....");
		
	}

}
