

package com.smartValue;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import javax.faces.context.FacesContext;

import com.sideinternational.sas.util.Util;

/**
 * Backing bean collecting the last java unexpected exception and format it. 
 */
public class UnexpectedErrorBean extends com.sideinternational.web.swaf.BaseBean 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** The error stack trace. */
	private String m_error;
	
	/**
	 * Constructor.
	 */
	public UnexpectedErrorBean()
	{
		Throwable ex = this.getServeletException() ;
		if (ex != null)
		{
			m_error = getError(ex);
	    	if (m_error == null || m_error.length() == 0) 
	    	{
				FacesContext facesContext = FacesContext.getCurrentInstance();
				if (facesContext != null && facesContext.getApplication() != null && facesContext.getApplication().getNavigationHandler() != null)
				{
					facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, "", "reConnect");
				}
	    	}
		}
	}

	/**
	 * Returns the error message (the full stack trace).
	 * @return Error message.
	 */
	private String getError(Throwable ex )
	{
        String result = null  ; 
		
        if (ex != null)
		{
	        StringWriter writer = new StringWriter();
	        PrintWriter pw = new PrintWriter(writer);
	        Util.fillStackTrace(ex, pw);
	
	        // Log this error.
	        //new EventSysm2012(ex);
	        
	        result =  writer.toString();
		}
        return result ;
	}
	
	/**
	 * Formats the last java unexpected exception stack trace.
	 * @return The formatted exception stack trace.
	 */
    public String getStackTrace() 
    {
    	Throwable ex = getServeletException () ;
    	if (ex != null)
    	{
    		m_error  = getError( ex) ; 
    	}
    	return m_error;
    }
    
    public  Throwable getServeletException()
	{
	       FacesContext context = FacesContext.getCurrentInstance();
	        Map requestMap = context.getExternalContext().getRequestMap();
	        Throwable ex = (Throwable) requestMap.get("javax.servlet.error.exception");
	        return ex ;
	}
}
