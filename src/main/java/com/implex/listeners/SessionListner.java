package com.implex.listeners;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.implex.database.ApplicationContext;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.services.ModuleServicesContainer;

public class SessionListner implements HttpSessionListener
{

	
	public void sessionCreated(HttpSessionEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	
	public void sessionDestroyed(HttpSessionEvent arg0)
	{
		SecUsrDta loggedUser =  (SecUsrDta) arg0.getSession().getAttribute("loggedUser");
		ApplicationContext.removeLoggedUser(loggedUser);
		ModuleServicesContainer msc = loggedUser.getModuleServiceContainer() ;
		if (msc != null)
		{
		msc.closeAllConnections();
		}
		
	}

}
