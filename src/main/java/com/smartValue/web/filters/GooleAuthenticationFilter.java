package com.smartValue.web.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.smartValue.web.AppContext;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns ="/ResourceManager/*")
public class GooleAuthenticationFilter implements Filter 
{
	private static String INDEX_PATH = "/ResourceManager/index.jsp" ; 
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)     throws IOException, ServletException {

    	 HttpServletRequest request = (HttpServletRequest) req;
         HttpServletResponse response = (HttpServletResponse) res;
         HttpSession session = request.getSession(false);

         String loginURI = request.getContextPath() + INDEX_PATH;
         boolean authRequired = isAuthRequired(request ); 
         if (! authRequired)
         {
        	 chain.doFilter(request, response);
         }
         else
         {
	         if (session != null)
	         {
	        	GoogleIdToken googleIdToken = (GoogleIdToken)session.getAttribute(AppContext.GOOGLE_ID_TOKEN_VAR_NAME); 
	        	
		        if (googleIdToken == null)
		     	{
		     		response.sendRedirect(loginURI +"?state=InvalidIdToken"); 
		     	}
		     	else 
		     	{ 
		     		long currentTimeMillis = new java.util.Date().getTime() ;
		     		if  (! googleIdToken.verifyExpirationTime(currentTimeMillis, 3))
		     		{
		     			response.sendRedirect(loginURI+"?state=IdToken_Expired");
		     		}
		     		else 
		     		{
		     			 chain.doFilter(request, response);
		     		}
		     	}
	         }
	         else 
	         {
	        	 response.sendRedirect(loginURI+"?state=WebSession_Expired");
	         }
         }
    }
    
    private static boolean isAuthRequired(HttpServletRequest request)
    {
    	String requestPath = request.getRequestURI().substring(request.getContextPath().length()) ; 
    	for (String regex : getNonAuthPaths()) {
            Pattern pattern = Pattern.compile(regex);
            if (pattern.matcher(requestPath).matches()) {
                return false;
            }
        }
        return true;
    	//return getNonAuthPaths().contains(request.getRequestURI().substring(request.getContextPath().length())) ;
    }
    
    static ArrayList<String> nonAuthPaths ;  
    private static ArrayList<String> getNonAuthPaths()
    {
    	if (nonAuthPaths == null)
    	{
	    	String loginURI 	=  INDEX_PATH ;
	    	String loginWithApigee = "^/ResourceManager/loginWithGoogle/.*$";
	    	String najizLikeApp    = "^/ResourceManager/NajizLikeSampleApp/.*$";
	    	
	    	nonAuthPaths = new ArrayList<String>(); 
	    	nonAuthPaths.add(loginURI); 
	    	nonAuthPaths.add(loginWithApigee);
	    	nonAuthPaths.add(najizLikeApp);
    	}
    	
    	return nonAuthPaths ; 
    	
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed
    }

    public void destroy() {
        // Cleanup code, if needed
    }

	
}

