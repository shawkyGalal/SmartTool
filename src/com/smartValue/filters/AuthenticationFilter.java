package com.smartValue.filters;
 
import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.implex.database.map.MasCompanyData;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.services.ModuleServicesContainer;
import com.sideinternational.sas.BaseException;
import com.sideinternational.sas.license.entity.Certificate;
import com.sideinternational.sas.util.Time;

import Support.Misc;
import Support.SysConfigParams;
import Support.XMLConfigFileReader;
 
// @WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {
 
    private ServletContext context;
    Date lastDayCertChecked = null ;
    
    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }
    
    long lastCheckTime = 0 ; 
    long checkInterval = 10*60*1000 ; //check every 10 minutes
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
 
    	HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

    	boolean anonymousLogin = isAllowedAnonymousLogin (req) ;
        if (anonymousLogin)
    	{	if (session == null ) session = req.getSession(true);
    		try { simulateAnonymousLogin(request , response , session );}
    		catch (Exception e ) {throw new ServletException("Unable To Simulate Anynomus Login Due To " + e.getMessage()) ; }
    		// pass the request along the filter chain
        	chain.doFilter(request, response);
            //--TO be used at Normal Production ---
        	return ;
    	}
        String uri = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
    	
        //boolean autoLog  = ( uri.endsWith(".jsp") && req.getParameter("AutoLog") != null );
        /*
        if (autoLog)
        {
	        try 
	        { 
	        	XMLConfigFileReader aa = Misc.getXMLConfigFileReader(false ) ; // new XMLConfigFileReader( false);
	        	String autologinDB =SysConfigParams.getAutoLogin_Environment_Name();
	        	String autoLoginUserName = SysConfigParams.getAutoLogin_Username();
	        	String autoLoginPassword = SysConfigParams.getAutoLogin_Password();	  	     
	        	Connection con = aa.getConnParmsByName(autologinDB).generateConnection(autoLoginUserName, autoLoginPassword , "NORMAL");
	        	session.setAttribute("con",con);
	        	
			    Connection repCon = aa.reposatoryConn.generateConnection();
			    session.setAttribute("repCon",repCon);
			    
	  	     }
	  	     catch ( Exception e) {}
	  	     chain.doFilter(request, response);
	  	     return ; 
        }
        */
     

       // this.context.log("Requested Resource::"+uri);
         
        boolean isAloginPage = (uri.contains("loginScreen")) ;
        boolean passwordResetPage = uri.contains("resetPassword.jsp") ; 
        boolean notJsp = !uri.endsWith(".jsp") ; 
        boolean allowedPages = isAloginPage || notJsp || passwordResetPage; 
        boolean directToLoginScreen = (session == null) && ! allowedPages  ;
        
        directToLoginScreen = directToLoginScreen 
         || ( getConnectionFromSession(session , "con")  == null &&  !allowedPages ) 
         || ( getConnectionFromSession(session , "repCon")  == null &&  !allowedPages );
        // directToLoginScreen = directToLoginScreen && !autoLog ;
        
        //-------------Check Cert Expiry -------------------------
        boolean directToCetExpiryPage = false ;
 
        boolean certExpired = false ;
        boolean usersExceeds = false ; 
        boolean orgUnitsExceeds = false ; 
        long nowTime = (new Date()).getTime() ; 
        
        boolean intervalPassed =  nowTime > lastCheckTime + checkInterval ; 
        
        boolean needToCheckLicence = intervalPassed && !notJsp ;  
        if ( needToCheckLicence )
        {
        	try { com.sideinternational.web.swaf.SWAF.loadLicense(); 
        	lastCheckTime = nowTime ; } 
        	catch (BaseException e) { 	// TODO Auto-generated catch block
        			e.printStackTrace();
        		   }
            com.sideinternational.sas.license.entity.Certificate cert = com.sideinternational.web.swaf.SWAF.getCertificate();
            certExpired = ! cert.isGranted("SmartTool");     
        
        	
        	if (session !=null)
        	{
        		SecUsrDta loggedUser = Misc.getLoggedUserFromSession(session) ; 
	            if (loggedUser != null)
	            {
		            MasCompanyData userCompany =  loggedUser.getUserCompany() ; 
		            usersExceeds = userCompany.isUserExceeds(cert) ; 
		            orgUnitsExceeds = userCompany.isOrgUnitsExceeds (cert ) ;
		        }
        	}
        }
        
        boolean certExpiryPage = uri.endsWith("certExpiry.jsp");
        directToCetExpiryPage = (certExpired || usersExceeds || orgUnitsExceeds) && ! certExpiryPage && ! isAloginPage && !notJsp ; 
       	if (directToCetExpiryPage )
        {
        	res.sendRedirect(Misc.getAppURL((HttpServletRequest) request)+"/"+"certExpiry.jsp");	
        }
      //-------------End of Check Cert Expiry -------------------------
       	else if( directToLoginScreen )
        {
        	Cookie cookie = Misc.getCookiByName ( request , Misc.LoginScreenPageNameCookiVarName) ; 
        	String loginScreenPage = (cookie!= null) ? cookie.getValue() : "Company/45/loginScreen.jsp" ; // session.getAttribute("loginScreenPage") ;
        	
        	Cookie comeFromCookie = Misc.getCookiByName ( request ,"comeFrom") ;
        	if (comeFromCookie == null )    { comeFromCookie	=	new Cookie ("comeFrom" ,null); }
        	comeFromCookie.setValue(uri + "?"+req.getQueryString()) ;
        	comeFromCookie.setMaxAge(60*10); // Keep the comeFrom info for 10 minutes
        	comeFromCookie.setPath("/"); 
        	res.addCookie(comeFromCookie);
        	//session.setAttribute("comeFrom" ,uri + "?"+req.getQueryString() ) ; 
            res.sendRedirect(Misc.getAppURL((HttpServletRequest) request)+"/"+loginScreenPage);
        }
        else
        {
        	
            // pass the request along the filter chain
        	chain.doFilter(request, response);
            //--TO be used at Normal Production ---
 
        }
    }
 
    public void destroy() {
        //close any resources here
    }
    private Connection getConnectionFromSession (HttpSession session , String name)
    {
   	 Connection con = (session != null)? (Connection) session.getAttribute(name) : null ; 
   	 
   	 return con ; 
   	 
    }
    // Check if the request is allowed for anonymous login
    final static String anonymEnv ="PNU_PROD" ; //SysConfigParams.getAutoLogin_Environment_Name();
    final static String anonymUserName = "anonym" ;// SysConfigParams.getAutoLogin_Username();
    final static String anonymPassword = "anonym123" ; //SysConfigParams.getAutoLogin_Password();
    private boolean isAllowedAnonymousLogin ( HttpServletRequest request )
    {
    	boolean v_result ; 
    	v_result = request.getRequestURI().contains("/SmartTool/EPM/Public/") ; // public folder mainly for publishing and getting info from public
    	v_result = v_result || request.getRequestURI().contains("/SmartTool/Public/") ;
    	String queryString = request.getQueryString() ; 
    	v_result = v_result || ( request.getRequestURI().contains("TableInsertForm.jsp") 
			    			     && queryString != null 
			    			     && (  queryString.contains("AnonymousLogin=true&owner=JCCS&tableName=JOB_APPLICATION_REQUEST") 
			    			    	|| queryString.contains("AnonymousLogin=true&owner=SUPPORT&tableName=PROSP_CUSTOMER") 
			    			        ) 
    			   				);
    	v_result = v_result || request.getRequestURI().contains("EX_EMP_MANAGER.jsp") ; 
    	return v_result ; 
    }
    
    private Connection getAnonymousConnection()throws Exception
    {	
    	Connection v_result = null ; 
    	XMLConfigFileReader aa = Misc.getXMLConfigFileReader(false ) ; // new XMLConfigFileReader( false);
	    	    v_result = aa.getConnParmsByName(anonymEnv).generateConnection(anonymUserName, anonymPassword , "NORMAL");
    	
    	return v_result ; 
    	
    }
    
    private void simulateAnonymousLogin( ServletRequest req , ServletResponse res , HttpSession session) throws Exception
    {
    	if ( session.getAttribute("con")== null)
    	{
	    	Connection anonymousConnection = getAnonymousConnection();
	    	anonymousConnection.setAutoCommit(true); // Anonymous user has not option to rollback his transaction
	    	session.setAttribute("con",anonymousConnection);
	    	
	    	XMLConfigFileReader aa = Misc.getXMLConfigFileReader(false ) ; // new XMLConfigFileReader( false);
		    Connection repCon = aa.reposatoryConn.generateConnection();
		    session.setAttribute("repCon",repCon);
		    
		    session.setAttribute ("selectedConnParms" , aa.connParms.elementAt(4)) ; // Corresponding to PNU_PROD
		    ModuleServicesContainer msc = Support.Misc.getModuleServiceContainer(anonymEnv , 1 );  
			session.setAttribute(Support.Misc.MscSessionKey , msc); 
	
			com.implex.database.map.services.SecUserDataService secUsrDtaServices = msc.getSecUserDataService();
			SecUsrDta loggedUser = secUsrDtaServices.getUserByUserName(anonymUserName.toUpperCase());
			if ( loggedUser == null ) { throw new Exception ("User " + anonymUserName.toUpperCase() + "Does Not Exist"); }
			session.setAttribute("loggedUser" , loggedUser) ;
    	}  
    }
    
}