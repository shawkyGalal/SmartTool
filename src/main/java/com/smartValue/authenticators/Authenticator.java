package com.smartValue.authenticators;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.SysParams;

import Support.ConnParms;

public abstract class Authenticator {
	
	public abstract void authenticate(HttpSession session , HttpServletRequest request , HttpServletResponse response, JspWriter out , ServletContext application  ) throws Exception ;
	
	protected void afterSuccessLogin()
	{
		
	}
	
	protected static void sendNotificationEmail(SecUsrDta m_loggedUser, ConnParms selectedConnParms ,  HttpServletRequest request)
	{
	    //-------Send a notification mail --- 
	    try{
	        String remoteAddr = java.net.InetAddress.getByName(request.getRemoteAddr().toString()).getHostName();
	        //--- Getting User Mac address 
	         String remoteMac = " 'Unknow Mac Address' " ; 
	         java.net.InetAddress address = java.net.InetAddress.getByName(remoteAddr);
			
		  /* Using a seperate thred to send an email notification ..
	        Thanks to Java threading this is greatly enhances the user satisfaction
	      */
	      com.smartValue.database.DataSet sysParams = m_loggedUser.getUserCompany().getSysParams() ; 
	      String appURL = Support.Misc.getAppURL(request) ;
	      String message = m_loggedUser.getUsrNameValue().toUpperCase() + " Have logged now to "+selectedConnParms.name+" using Support Tool Running on :\n"+appURL+"\n  from machine  "  +remoteAddr  + " From Mac Address: " + remoteMac;
	      
	      String supportAdminMailSender = ((SysParams) sysParams.getFirstFilteredPO("E_NAME" , "Support User Email")).getValValue(); // Support.SysConfigParams.getSupport_User_Email();
	      String supporEmailReciver = ((SysParams) sysParams.getFirstFilteredPO("E_NAME" , "Admin_Notify_Mail_Address_Receiver")).getValValue(); //Support.SysConfigParams.getAdmin_Notify_Mail_Address_Receiver();
	      
	      String[] to = {supporEmailReciver};
          Support.mail.EmailMessage em = new Support.mail.EmailMessage();
	      em.setFrom(supportAdminMailSender);
	      em.setTo(to);
	      em.setSubject(message);
	      em.setBody(message);
	     
	      Support.mail.MailSender ms = new Support.mail.MailSender(sysParams);
	      Support.mail.MailSenderThread mst = new Support.mail.MailSenderThread(em ,ms);
	      mst.start();
	    }
	    catch (Exception e){}
		
	}
}
