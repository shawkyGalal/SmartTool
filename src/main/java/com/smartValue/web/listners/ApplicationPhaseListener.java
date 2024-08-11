package com.smartValue.web.listners;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;

import com.sideinternational.sas.util.ApplicationStatus;
import com.sideinternational.web.swaf.SWAF;
import com.sideinternational.web.swaf.event.system.EventSysm2011;
import com.smartValue.UserSession;
import com.smartValue.database.ApplicationContext;

public class ApplicationPhaseListener extends com.sideinternational.web.swaf.ApplicationPhaseListener{

	/**
	 * Handle a notification that the processing for a particular 
	 * phase of the request processing lifecycle is about to begin.
	 */
	private boolean debugMode = true ; 
	public void beforePhase(PhaseEvent event) 
	{
		if ( debugMode ) //ApplicationContext.getModuleServicesContainer().isDebugMode())
		{
		event.getFacesContext().getExternalContext().log("Before "+event.getPhaseId());
		}
		System.gc();
		UserSession userSession = (UserSession) SWAF.getSession(); 

		if (event.getPhaseId() == PhaseId.RENDER_RESPONSE)
		{
			if (ApplicationStatus.getCurrentStatus().getStatus() == ApplicationStatus.LICENSE_ERROR)
			{
				// Kill the current session.
				if (userSession != null) userSession.logout();
				
				forceNavigation(SWAF.INVALID_LICENSE, event.getFacesContext());
				return;
			}

			String viewId = (event.getFacesContext().getViewRoot() != null) ? event.getFacesContext().getViewRoot().getViewId() : null;
			boolean isLoginPhase = (viewId == null || viewId.equals("/SWAF/jsp/login.xhtml")) ? true : false;
			boolean isChangePasswordPhase = (viewId == null || viewId.equals("/SWAF/jsp/change_password.xhtml")) ? true : false;
			if (!isLoginPhase && !isChangePasswordPhase)
			{
				if (userSession == null || ( userSession!= null && !userSession.isAuthenticated()) )
				{
					// Session timed-out.
					new EventSysm2011(viewId);
	
					forceNavigation("sessionExpired", event.getFacesContext());
				}
			}
			else if (isLoginPhase && (userSession != null && userSession.isAuthenticated()))
			{	
				userSession.logout();
			}
		}
		else if (event.getPhaseId() == PhaseId.INVOKE_APPLICATION)
		{
			String viewId = (event.getFacesContext().getViewRoot() != null) ? event.getFacesContext().getViewRoot().getViewId() : null;
			if (viewId != null && viewId.equals("/SWAF/jsp/sys_exception.xhtml"))
			{
				// Kill the current session.
				if (SWAF.getSession() != null) SWAF.getSession().logout();
				
				forceNavigation("reConnect", event.getFacesContext());
				return;
			}
		}
	}
	
	public void afterPhase(PhaseEvent event) 
	{
		if ( debugMode ) //ApplicationContext.getModuleServicesContainer().isDebugMode())
		{
		event.getFacesContext().getExternalContext().log("AFTER "+event.getPhaseId());
		}
	}
}
