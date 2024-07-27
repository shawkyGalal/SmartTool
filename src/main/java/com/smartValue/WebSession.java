package com.smartValue;

/**
 * Copyright (c) 2006 SIDE International
 * SIDE Resources & Development Belgium S.A.
 * Rue du Cerf, 200 B-1330 Rixensart, Belgium
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of SIDE International ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with SIDE International. 
 */


import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.sideinternational.sas.BaseException;
import com.sideinternational.sas.configuration.Configuration;
import com.sideinternational.sas.event.resource.debug.EventDbug9998;
import com.sideinternational.sas.event.resource.system.EventSysm1025;
import com.sideinternational.sas.service.authentication.AuthenticationServiceFactory;
import com.sideinternational.web.swaf.SWAF;
import com.sideinternational.web.swaf.event.security.EventSecu2000;
import com.smartValue.database.ApplicationContext;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.services.ModuleServicesContainer;

/**
 * Represents the user Web session. This class has many useful services allowing
 * the server side to 'communicate' (via popup or text message) with the end-user presentation. 
 * Moreover, the user Web session handles the authentication operator process.
 */
public class WebSession extends AbstractSession 
{
	/** The module access reference. */
	//private ModuleAccess m_moduleAccess;
	
	/**
	 * The session is started.
	 * The user login request is handled here.
	 */
	protected boolean onSessionStarted()
	{
		// Processing the new login request
		new EventDbug9998("SWAF session started. Processing LOGIN request for user [" + m_loginName + "]");

		if (!checkUserLogin()) return false;
		
		try
		{
			String className = Configuration.getImplementationClass("module-access-class", "services-implementation");
			//m_moduleAccess = (ModuleAccess)com.sideinternational.sas.util.Util.instantiateClass(className);
		}
		catch(Exception e)
		{
			SWAF.addErrorMessage(null, new EventSysm1025(e));			
			return false;
		}
		
		setStatusDescription(SWAF.getDisplayMessage("ReadyStatus"));
   		return true;
	}

	/**
	 * Checks if the user login is valid.
	 * @return True if the user login is valid.
	 */
	private boolean checkUserLogin()
	{
		try 
		{
			m_activeOperator = AuthenticationServiceFactory.createAuthenticationService().authenticate(m_loginName, m_loginPassword);
			
			// Successful login
			new EventSecu2000(m_activeOperator.getLoginName());
			HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			session.setAttribute("activeOperator", m_activeOperator);
			// keep a refrence to userSession in the httpSession 
			session.setAttribute(ModuleServicesContainer.UserSession , this);
			
			ApplicationContext.addLoggedUser((SecUsrDta)m_activeOperator);
			return true;
		} 
		catch (BaseException be)
		{
			SWAF.addErrorMessage(null, be.getMessage());
		}
		catch (Exception e)
		{
			SWAF.addErrorMessage(null, new EventSysm1025(e));
		}
		return false;
	}
	
	/**
//	 * Returns the module access.
//	 * @return ModuleAccess.
//	 */
//	public ModuleAccess getModuleAccess()
//	{
//		return m_moduleAccess;
//	}
//	
//	/**
//	 * Sets the module acess.
//	 * @param pm_moduleAccess the new module access.
//	 */
//	public void setModuleAccess(ModuleAccess pm_moduleAccess)
//	{
//		m_moduleAccess = pm_moduleAccess;
//	}	
}
