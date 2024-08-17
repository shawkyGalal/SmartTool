package com.smartValue.web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;


import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.smartvalue.apigee.configuration.AppConfig;
import com.smartvalue.apigee.configuration.infra.ManagementServer;
import com.smartvalue.moj.clients.environments.Environment;

public class AppContext {
 public static final String APP_CONFIG_VAR_NAME = "appConfig" ; 
 public static final String GOOGLE_ID_TOKEN_VAR_NAME = "GoogleIdToken" ;
 public static final String APIGEE_MANAGEMENT_SERVER = "ApigeeManagementServer" ;
 public static final String MOJ_ENV = "mojEnv" ; 
 
 public static AppConfig getAppConfig(ServletContext sc)
 {
	 return (AppConfig) sc.getAttribute(APP_CONFIG_VAR_NAME) ; 
 }
 
 public static GoogleIdToken getGoogleIdToken(HttpSession  session)
 {
	 return (GoogleIdToken) session.getAttribute(GOOGLE_ID_TOKEN_VAR_NAME) ; 
 }
 
 public static void  setGoogleIdToken(HttpSession  session , GoogleIdToken googleIdToken )
 {
	 session.setAttribute(GOOGLE_ID_TOKEN_VAR_NAME ,googleIdToken ) ; 
 }
 
 public static void googleIdTokenLogoff(HttpSession  session)
 {
	 AppContext.setGoogleIdToken(session, null); 
	 AppContext.setApigeeManagementServer(session , null) ; 
 }
 
 public static void  setApigeeManagementServer(HttpSession  session , ManagementServer m_managementServer )
 {
	 session.setAttribute(APIGEE_MANAGEMENT_SERVER ,m_managementServer ) ; 
 }
 
 public static ManagementServer  getApigeeManagementServer(HttpSession  session  )
 {
	 return (ManagementServer) session.getAttribute(APIGEE_MANAGEMENT_SERVER ) ; 
 }
 
 public static void  setMojEnv(HttpSession  session , Environment env )
 {
	 session.setAttribute(MOJ_ENV , env ); 
 }
 
 public static Environment  getMojEnv(HttpSession  session )
 {
	 return (Environment) session.getAttribute(MOJ_ENV ); 
 }
 

 
}
