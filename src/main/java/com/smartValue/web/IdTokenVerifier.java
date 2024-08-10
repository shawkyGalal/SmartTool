package com.smartValue.web;

import com.google.api.client.json.jackson2.JacksonFactory; 
import com.google.api.client.http.javanet.NetHttpTransport ; 


import com.google.api.client.http.HttpTransport ; 
import com.google.api.client.json.gson.GsonFactory ;

import java.util.Arrays;
import java.util.Collections ;

import javax.servlet.http.HttpServletRequest;

import java.io.InputStreamReader ;
import java.security.GeneralSecurityException;
import java.io.InputStream ; 
import java.io.BufferedReader ;
import java.io.IOException;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken ; 
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload ; 
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier ; 

public class IdTokenVerifier {
	
	public static GoogleIdToken buildFromRequest(HttpServletRequest request  ) throws IOException
	{
		// String client_id= "743562068929-2m0gujbpdcs9g3gebrroeaj4hbkelc3b.apps.googleusercontent.com" ;  
		com.google.api.client.json.JsonFactory jsonFactory = new GsonFactory(); 
	
		
		//(Receive idTokenString by HTTPS POST)
	 	String idTokenString = null;
	    StringBuilder stringBuilder = new StringBuilder();
	    BufferedReader bufferedReader = null;
	    
	    InputStream inputStream = request.getInputStream();
	    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	    char[] charBuffer = new char[128];
	    int bytesRead = -1;
	    while ((bytesRead = bufferedReader.read(charBuffer)) > 0) 
	    {
	         stringBuilder.append(charBuffer, 0, bytesRead);
	    }
	    idTokenString = stringBuilder.toString();
	    int equalIndex = idTokenString.indexOf("=");  
	    idTokenString = idTokenString.substring(equalIndex+1); 
	    String idTokenStringOnly= idTokenString.split("&g_csrf_token")[0]; 
	    String gCsrfToken = idTokenString.split("&g_csrf_token")[1]; 
	    
	    
		GoogleIdToken idToken =  GoogleIdToken.parse(jsonFactory, idTokenStringOnly) ;  

		
		return idToken ; 
	}
	
	public static GoogleIdToken verifyBasicsOnly(GoogleIdToken googleIdToken , String m_client_id ,  String m_issuer )
	{
		GoogleIdToken result = null ; 
		boolean validateResult = true; 
		
		com.google.api.client.auth.openidconnect.IdTokenVerifier verifier =  
				new com.google.api.client.auth.openidconnect.IdTokenVerifier.Builder()  
				  .setIssuer(m_issuer)
				  .setAudience(Arrays.asList( m_client_id ))
				  .build();
		
		validateResult = verifier.verify(googleIdToken) ;
		if (validateResult)
		{result = googleIdToken ;  }
		 
		return result ; 
		
	}
	
	public static GoogleIdToken verifyGoogleSignature ( GoogleIdToken googleIdToken, String m_client_id , String m_issuer ) throws GeneralSecurityException, IOException
	{
		GoogleIdToken result = null ; 
		HttpTransport httpTransport = new NetHttpTransport() ;
	
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, new JacksonFactory())
				// Specify the CLIENT_ID of the app that accesses the backend:
				.setAudience(Collections.singletonList(m_client_id))
				.setIssuer( m_issuer )
				// Or, if multiple clients access the backend:
				//.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
				.build();
		
		 boolean validateResult  =  verifier.verify(googleIdToken); 
		 if (validateResult )
		 {
			 result = googleIdToken  ;  
		 }
		 
		 return result; 
		
	}
	
}
