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

package com.smartValue;


import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;

import com.sideinternational.sas.BaseException;
import com.sideinternational.sas.event.Event;
import com.sideinternational.sas.event.logging.Console;
import com.sideinternational.sas.event.resource.debug.EventDbug9998;
import com.sideinternational.sas.event.resource.system.EventSysm1025;
import com.sideinternational.sas.license.LicenseException;
import com.sideinternational.sas.service.Operator;
import com.sideinternational.sas.service.authentication.AuthenticationService;
import com.sideinternational.sas.service.authentication.AuthenticationServiceFactory;
import com.sideinternational.sas.service.authorization.ApplicationServiceFactory;
import com.sideinternational.sas.service.authorization.AuthorizationServiceFactory;
import com.sideinternational.web.swaf.ModuleNavigator;
import com.sideinternational.web.swaf.SWAF;
import com.sideinternational.web.swaf.SessionManager;
import com.sideinternational.web.swaf.event.security.EventSecu2002;
import com.sideinternational.web.swaf.event.security.EventSecu2004;
import com.sideinternational.web.swaf.event.system.EventSysm2006;
import com.sideinternational.web.swaf.event.system.EventSysm2016;
import com.sideinternational.web.swaf.event.system.EventSysm2017;
import com.sideinternational.web.swaf.util.FieldValidator;
import com.sideinternational.web.swaf.util.MessageBox;

/**
 * Represents the user session. This class has many useful services allowing
 * the server side to 'communicate' (via popup or text message) with the end-user presentation. 
 */
public abstract class AbstractSession extends com.sideinternational.web.swaf.AbstractSession 
{
	/** The session status description. */
	private String m_statusDescription;
	/** The servlet context path. */
	private String m_servletContextPath;
	/** The current session identifier. */
	private String m_sessionId;
	/** The user login name of the login window */
	protected String m_loginName;
	/** The user login password of the login window */
	protected String m_loginPassword;
	/** The user's new password */
	protected String m_newPassword;
	/** The confirmation of user's new password */
	protected String m_confirmNewPassword;
	
	/** The current connected user. */
	protected Operator m_activeOperator;
	/** Represents the message showed to the end-user. */
	protected MessageBox m_messageBox;
	/** The module navigator manager. */
	private ModuleNavigator m_moduleNavigator;
	/** The current connected user profile. */
	//protected AbstractProfile m_connectedUserProfile;
	/** The current user is properly connected and authenticated.*/
	protected boolean m_isAuthenticated;
	/** The active server transaction flag. */
	protected boolean m_activeServerTrx;
	
	/**
	 * Constructor.
	 */
	public AbstractSession()
	{
		// Creating a new SWAF session
		new EventDbug9998("Creating a new SWAF session...");

		m_messageBox = new MessageBox();
		m_moduleNavigator = new ModuleNavigator();
		
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    	m_sessionId = session.getId();
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		m_servletContextPath = request.getContextPath();

    	// SWAF session successfully created
    	new EventSysm2006(m_sessionId, m_servletContextPath);
	}

	/**
	 * Tries to start the session.
	 * @return Faces navigation information (success or failure).
	 */
	public synchronized String startSession()
	{
		// Starting the SWAF session
		new EventDbug9998("Starting the SWAF user session...");

		setAuthenticated(false);
		
		// Check the license
		try
		{
			SWAF.checkApplicationLicense();
		}
		catch (LicenseException e)
		{
			e.printStackTrace();
			return SWAF.INVALID_LICENSE;
		}

		// Core session code
		if (!onSessionStarted()) return SWAF.FAILURE;

		try
		{
			// Check if the application is granted ?
			if (!AuthorizationServiceFactory
					.createAuthorizationService()
					.hasApplicationAccess(SWAF.getApplicationId(), m_activeOperator)) 
			{
				SWAF.addErrorMessage(null, new EventSecu2002(m_activeOperator.getLoginName(), SWAF.getApplicationId()));
				return SWAF.FAILURE;
			}
		}
		catch (BaseException be)
		{
			SWAF.addErrorMessage(null, be.getMessage());
			return SWAF.FAILURE;
		}

		setAuthenticated(true);

		String outcome = getDefaultModule();
		return (outcome != null) ? outcome : SWAF.SUCCESS;
	}
	
	/**
	 * Returns the module outcome that should be display by default when the
	 * application startup. Returns "null" if no default module.
	 * @return Module outcome.
	 */
	public String getDefaultModule()
	{
		return null;
	}
	
	/**
	 * Resets the login form (user name and user password).
	 * @return Faces navigation information (success).
	 */
	public synchronized String clearLoginForm()
	{
		m_loginName = null;
		m_loginPassword = null;
		
		return SWAF.SUCCESS;
	}

	/**
	 * Resets all members attributes regarding the change password form
	 */
	protected void resetAttributes()
	{
		m_loginName = null;
		m_loginPassword = null;
		m_newPassword = null;
		m_confirmNewPassword = null;
	}
	
	/**
	 * Returns the active operator.
	 * @return Active operator.
	 */
	public Operator getOperator()
	{
		return m_activeOperator;
	}
	
	/**
	 * The servlet context path.
	 * @return Servlet context path.
	 */
	public String getContextPath()
	{
		return m_servletContextPath;
	}

	/**
	 * Gets the session status description
	 * @return Session status.
	 */
	public String getStatusDescription()
	{
		return m_statusDescription;
	}
	
	/**
	 * Sets the session status description.
	 * @param pm_description The new session status description.
	 */
	public void setStatusDescription(String pm_description)
	{
		m_statusDescription = pm_description;
	}

	/**
	 * Sets the active server transaction flag.
	 * @param pm_flag Active server transaction flag.
	 */
	public synchronized void setActiveServerTrx(boolean pm_flag)
	{
		m_activeServerTrx = pm_flag;
	}
	
	/**
	 * Sets the active server transaction flag.
	 * @param pm_flag Active server transaction flag.
	 */
	public void setActiveServerTrx(String pm_flag)
	{
		setActiveServerTrx(Boolean.getBoolean(pm_flag));
	}

	/**
	 * Returns the active server transaction flag.
	 * @return Active server transaction flag.
	 */
	public synchronized boolean getActiveServerTrx()
	{
		return m_activeServerTrx;
	}

	/**
	 * Stops the current session.
	 * @return Faces navigation information (success).
	 */
	public synchronized String logout()
	{ 
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if (session != null) session.invalidate();

		// Logout the session
		new EventSecu2004(getOperator().getLoginName());
		
		return SWAF.SUCCESS;
	}

	/**
	 * The class is being bound to a session.
	 */
	public void valueBound(HttpSessionBindingEvent pm_event) 
    {
		SessionManager.getInstance().register(this);
    }
  
	/**
	 * The class is being unbound to a session.
	 */
    public synchronized void valueUnbound(HttpSessionBindingEvent pm_event) 
    {
    	SessionManager.getInstance().unregister(this);
    }
    
    /**
     * Returns the current session identifier.
     * @return The session id.
     */
    public String getSessionId()
    {
    	return m_sessionId;
    }

    /**
     * Gets the module navigator manager.
     * @return The module navigator manager.
     */
    public ModuleNavigator getModuleNavigator()
    {
    	return m_moduleNavigator;
    }
    
    /**
     * Gets the application name.
     * @return The application name.
     */
    public String getApplicationName()
    {
    	try
    	{
    		return ApplicationServiceFactory.createApplicationService().find(SWAF.getApplicationId()).getLocalName();
    	}
    	catch (Exception e)
    	{
    		return SWAF.getApplicationId();
    	}
    }
    
    /**
     * Gets the application version.
     * @return The application version.
     */
    public String getApplicationVersion()
    {
    	return SWAF.getApplicationVersion();
    }
    
    /**
     * Returns true if the user is connected and authenticated.
     * @return True if authenticated.
     */
    public boolean isAuthenticated()
    {
    	return m_isAuthenticated;
    }
    
    /**
     * Sets the user authenticated status. 
     * @param pm_status The authenticated status.
     */
    public void setAuthenticated(boolean pm_status)
    {
    	m_isAuthenticated = pm_status;
    }
    
    /**
     * Returns the user login name.
     * @return Login name.
     */
    public String getLoginName()
    {
    	return m_loginName ;
    }
    
    /**
     * Sets the user login name.
     * @param pm_name Login name.
     */
    public void setLoginName(String pm_name)
    {
    	m_loginName = pm_name;
    }
    
    /**
     * Returns the user login password.
     * @return Login password.
     */
    public String getLoginPassword()
    {
    	return (m_loginPassword == null)? "1" : m_loginPassword ;
    }
    
    /**
     * Sets the user login password.
     * @param pm_passwd Login password.
     */
    public void setLoginPassword(String pm_passwd)
    {
    	m_loginPassword = pm_passwd;
    }
    
    /**
     * Returns the server time zone.
     * @return Server time zone.
     */
    public TimeZone getServerTimeZone()
    {
    	return TimeZone.getDefault();
    }
    
    /**
     * Returns the client time.
     * @return Client time.
     */
    public Date getClientTime()
    {
    	GregorianCalendar gc = new GregorianCalendar(FacesContext.getCurrentInstance().getViewRoot().getLocale());
    	return gc.getTime();
    }
    
    /**
     * Stops the current session and propose a new user session.
     * @return The faces navigation.
     */
    public synchronized String reConnect()
    {
    	Console.log("AbstractSession: reConnect", getClass());
    	logout();
    	
    	return SWAF.SUCCESS;
    }
    
    /**
     * Change the user password.
     * @return The faces navigation.
     */
    public synchronized String changePassword()
    {
    	Console.log("AbstractSession: changePassword", getClass());
    	
    	resetAttributes();
    	
    	return SWAF.SUCCESS;
    }
    
    /**
     * Password change cancelled. Turn back to login page.
     * @return The faces navigation.
     */
    public synchronized String cancelPassword()
    {
    	Console.log("AbstractSession: password change cancelled", getClass());
    	
    	resetAttributes();
    	
    	return SWAF.SUCCESS;
    }
    
	protected abstract boolean onSessionStarted();
	
	/**
	 * Call back method indicating the password has been successfully changed
	 */
	protected void onPasswordChanged()
	{
		SWAF.getSession().createInformationBox(SWAF.getDisplayMessage(SWAF.SWAF_BUNDLE_BASENAME, "PasswordSuccessfullyChanged"));
	}
    
	// --------------------------------------------------------------------------------------------------
	// Message box methods
	// --------------------------------------------------------------------------------------------------
	public void createErrorBox(String szError)
	{ 
		m_messageBox.init(SWAF.getDisplayMessage("ErrorDialogTitle"), szError, "error");
	}

	public void createErrorBox(String szError, String szAction)
	{ 
		m_messageBox.init(SWAF.getDisplayMessage("ErrorDialogTitle"), szError, "error", SWAF.getDisplayMessage("RecommendedActionLabel") + "\n" + szAction);
	}

	public void createErrorBox(Event pm_event)
	{ 
		Locale locale = SWAF.getClientLocale();

		if (pm_event.getRecommandedAction() != null) createErrorBox(pm_event.getMessage(locale), pm_event.getRecommandedAction(locale));
		else createErrorBox(pm_event.getMessage(locale));
	}

	public void createInformationBox(String pm_message)
	{ 
		m_messageBox.init(SWAF.getDisplayMessage("InfoDialogTitle"), pm_message, "info");
	}

	public void createInformationBox(String szInfo, String szAction)
	{ 
		m_messageBox.init(SWAF.getDisplayMessage("InfoDialogTitle"), szInfo, "info", SWAF.getDisplayMessage("RecommendedActionLabel") + "\n" + szAction);
	}

	public void createWarningBox(String pm_message)
	{ 
		m_messageBox.init(SWAF.getDisplayMessage("WarningDialogTitle"), pm_message, "info");
	}

	public String getMessageBoxText()	
	{	
		String s = m_messageBox.getText();	
		m_messageBox.setText("");	
		return s; 
	}
	
	public String getMessageBoxTitle()	
	{	
		return m_messageBox.getTitle(); 
	}
	
	public String getMessageBoxIcon()	
	{	
		return m_messageBox.getIcon(); 
	}
	
	public String getMessageBoxAction()
	{
		String message = m_messageBox.getAction();
		m_messageBox.setAction("");
		return message; 
	}

	/**
	 * Returns the confirmation of the new password value
	 * @return The confirmation of the new password value
	 */
	public String getConfirmNewPassword()
	{
		return m_confirmNewPassword;
	}

	/**
	 * Sets the confirmation of the new password value
	 * @param pm_password The password value
	 */
	public void setConfirmNewPassword(String pm_password)
	{
		m_confirmNewPassword = pm_password;
	}

	/**
	 * Returns the new user password value
	 * @return The confirmation of the new user password value
	 */
	public String getNewPassword()
	{
		return m_newPassword;
	}

	/**
	 * Sets the new user password value
	 * @param pm_password The new password value
	 */
	public void setNewPassword(String pm_password)
	{
		m_newPassword = pm_password;
	}
	
	/**
	 * Change the user password
	 * @return Faces navigation information (success or failure). 
	 */
	public synchronized String applyChangePassword()
	{
		new EventDbug9998("Changing the user password...");
		
		if (startSession() == SWAF.FAILURE)
			return SWAF.FAILURE;
		
		if (isValidPasswordValues() == false)
			return SWAF.FAILURE;
		
		try
		{
			
			AuthenticationService service = AuthenticationServiceFactory.createAuthenticationService(); //new AuthenticationServiceImpl();
			service.changePassword(m_loginName, m_loginPassword, m_newPassword);
		}
		catch(BaseException be)
		{
			SWAF.addErrorMessage(null, be.getMessage());
			return SWAF.FAILURE;
		}
		catch(Exception e)
		{
			SWAF.addErrorMessage(null, new EventSysm1025(e));
			return SWAF.FAILURE;
		}
		
		onPasswordChanged();
		new EventDbug9998("The password of user [" + m_loginName + "] has been successfully changed");
		
		return SWAF.SUCCESS;
	}
	
	/**
	 * Check if all fields required to change the password are well initialized.
	 * The Old password and the new one must be different.
	 * Moreover the new password must match the confirmation value.
	 * @return true if valid or false if invalid
	 */
	protected boolean isValidPasswordValues()
	{
		Event error = null;
		if (FieldValidator.isInitialized(m_loginName) == false) 
			error = new EventSysm2016(SWAF.getDisplayMessage(SWAF.SWAF_BUNDLE_BASENAME, "UserNameLabel"));
		else if (FieldValidator.isInitialized(m_loginPassword) == false)
			error = new EventSysm2016(SWAF.getDisplayMessage(SWAF.SWAF_BUNDLE_BASENAME, "OldUserPasswordLabel"));
		else if (FieldValidator.isInitialized(m_newPassword) == false)
			error = new EventSysm2016(SWAF.getDisplayMessage(SWAF.SWAF_BUNDLE_BASENAME, "NewUserPasswordLabel"));
		else if (FieldValidator.isInitialized(m_confirmNewPassword) == false)
			error = new EventSysm2016(SWAF.getDisplayMessage(SWAF.SWAF_BUNDLE_BASENAME, "ConfirmNewUserPasswordLabel"));
		else if (!m_newPassword.equals(m_confirmNewPassword))
			error = new EventSysm2017();
			
		if (error != null)
		{
			SWAF.addErrorMessage(null, error);
			return false;
		}

		return true;
	}
	
	
	private String selectedEnv;
	public void setSelectedEnv(String selectedEnv) {
		this.selectedEnv = selectedEnv;
	}

	public String getSelectedEnv() {
		return (selectedEnv== null) ? "ERPINS" : selectedEnv;
	}

	private int userLang = 2;
	 
	public int getUserLang() {		
		return userLang;
	}


	public void setUserLang(int pm_userLang) {
		this.userLang = pm_userLang;
		
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			String viewId = (facesContext.getViewRoot() != null) ? facesContext.getViewRoot().getViewId() : null;
			boolean isLoginPhase = (viewId != null && viewId.equals("/SWAF/jsp/login.xhtml")) ? true : false;
			if(!isLoginPhase)
			{
				//ApplicationContext.getModuleServicesContainer().getDbServices().setLoggedUserLang(pm_userLang);
				externalContext.redirect(externalContext.encodeResourceURL(externalContext.getRequestContextPath()+"/templates/newTemplate.jsf"));
			}
		} catch (Exception e) {}
	}
}
