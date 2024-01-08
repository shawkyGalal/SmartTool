package com.implex;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSessionBindingEvent;

import org.richfaces.component.state.TreeState;

import Support.XMLConfigFileReader;

import com.implex.components.model.tree.TreeStateController;
import com.implex.database.ApplicationContext;
import com.implex.database.DbServiceFactory;
import com.implex.database.DbServices;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.services.ModuleServicesContainer;
import com.sideinternational.sas.configuration.Configuration;
import com.sideinternational.web.swaf.SWAF;


public class UserSession extends WebSession

{
	public static final String MscSessionKey = "moduleServiceContainer" ;
	private SelectItem[] userEnvironment = {};
	private ArrayList<SelectItem> languages = null;


	private XMLConfigFileReader m_connectionsData = null;
	private TreeStateController m_treeStateController;

	public UserSession() {
		if (m_treeStateController == null)
			m_treeStateController = new TreeStateController();
		
	}

		
	public TreeState getTreeState(Integer key) {
		return m_treeStateController.getTreeState(key);
	}

	public SelectItem[] getUserEnvironment() {
		return ApplicationContext.getEnvironmentNames() ; // this.userEnvironment;
	}

	public void setUserEnvironment(SelectItem[] pm_userEnvironment) {
		this.userEnvironment = pm_userEnvironment;
	}

	public void userChanged() throws Exception {
		if (m_loginName != null)
			if (this.m_loginName.equals("train")) {
				userEnvironment = new SelectItem[4];
				userEnvironment[0] = new SelectItem("train1", "training 1");
				userEnvironment[1] = new SelectItem("train2", "training 2");
				userEnvironment[2] = new SelectItem("train3", "training 3");
				userEnvironment[3] = new SelectItem("train4", "training 4");
			} else if (m_loginName.equals("product")) {
				userEnvironment = new SelectItem[4];
				userEnvironment[0] = new SelectItem("product1", "product 1");
				userEnvironment[1] = new SelectItem("product2", "product 2");
				userEnvironment[2] = new SelectItem("product3", "product 3");
				userEnvironment[3] = new SelectItem("product4", "product 4");
			} else if (m_loginName.equals("account")) {
				userEnvironment = new SelectItem[4];
				userEnvironment[0] = new SelectItem("account1", "account 1");
				userEnvironment[1] = new SelectItem("account2", "account 2");
				userEnvironment[2] = new SelectItem("account3", "account 3");
				userEnvironment[3] = new SelectItem("account4", "account 4");
			} else {
				m_connectionsData = new XMLConfigFileReader(
						getConnectionsFile(), false);
				Vector conParms = m_connectionsData.connParms;
				userEnvironment = new SelectItem[conParms.size()];
				for (int i = 0; i < conParms.size(); i++) {
					Support.ConnParms thisConParms = (Support.ConnParms) conParms
							.elementAt(i);
					if (thisConParms.active.equals("Y")) {
						userEnvironment[i] = new SelectItem(thisConParms.name,
								thisConParms.name);
					}
				}
			}
	}

	private URL getConnectionsFile() throws MalformedURLException {
		return new URL("File:\\" + Configuration.getConfigurationHome()
				+ File.separator + SWAF.getConfigurationSubDirectory()
				+ File.separator + "Connections_config.xml");
	}


	public void setLoginName(String pm_name) {
		try {
	//		userChanged();
			super.setLoginName(pm_name);
		} catch (Exception e) {
			e.printStackTrace();
			SWAF.addErrorMessage(null, "Unable to Change User..");
		}
	}


	public void setLanguages(ArrayList<SelectItem> languages) {
		this.languages = languages;
	}

	public ArrayList<SelectItem> getLanguages() {
		String selectedEnv = this.getSelectedEnv() ; 
		languages = ApplicationContext.getSysLanguages(selectedEnv) ;
		if (languages == null ||languages.size() == 0 ) 
		{
			try {
				DbServices dbs = DbServiceFactory.create();
				dbs.initialize( selectedEnv, 1);
				languages = dbs.getSelectItems("SELECT lng_id, lng_name a_desc , lng_name e_desc FROM sys_lng" , false);
				 
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			if (languages.isEmpty() )
			{
				//languages = new ArrayList<SelectItem>();
				languages.add(new SelectItem(1, "العربية"));
				languages.add(new SelectItem(2, "English"));
			}
			ApplicationContext.setSysLanguages(languages , selectedEnv);
			
		}
		return languages;
	}

	public String getLangDirection()
	{
		return (this.getUserLang()==1)? "rtl" : "ltr" ;
	}

	public String getAlignDirection()
	{
		return (this.getUserLang()==1)? "right" : "left" ;
	}
	public String logout(){
		org.jboss.seam.web.Session.instance().invalidate();
		ApplicationContext.removeLoggedUser((SecUsrDta)this.getOperator()); 
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		try {
			externalContext.redirect(externalContext.encodeResourceURL(externalContext.getRequestContextPath()));
		} catch (IOException e) {}
		return "";
	}

//	public int getWidth() {
//		
//		return (this.getUserLang()==1)? 0:300;
//	}

	public String getShortLangDesc() {
		return (this.getUserLang()==1)? "Ar":"" ;
	}


//	public void setSessionConnections(HashMap <String , Connection> sessionConnections) {
//		this.sessionConnections = sessionConnections;
//	}


	
	public String startSession()
	{
		return super.startSession(); 
	}



	/**
	 * The class is being bound to a session.
	 */
	public void valueBound(HttpSessionBindingEvent pm_event) 
    {
			super.valueBound(pm_event) ;
    }
  
	/**
	 * The class is being unbound to a session.
	 */
    public synchronized void valueUnbound(HttpSessionBindingEvent pm_event) 
    {
    	super.valueUnbound(pm_event);
    	ModuleServicesContainer msc = (ModuleServicesContainer)pm_event.getSession().getAttribute(UserSession.MscSessionKey) ;
		if (msc != null)
		{
		msc.closeAllConnections();
		}
    }

    public String getUserLangName() {
		return (this.getUserLang()==1)? "ar":"en" ;
	}
    
    public void setShowSystemNavigationTree(boolean showSystemNavigationTree) {
		this.showSystemNavigationTree = showSystemNavigationTree;
	}


	public boolean isShowSystemNavigationTree() {
		return showSystemNavigationTree;
	}

	private boolean showSystemNavigationTree = true; 
    
}
