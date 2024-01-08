package com.implex.service.impl;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.LdapContext;

import com.sideinternational.sas.BaseException;
import com.sideinternational.sas.service.Operator;
import com.sideinternational.sas.service.authentication.AuthenticationService;
import com.sideinternational.web.swaf.SWAF;



public class AuthenticationServiceLdapImpl implements AuthenticationService{

	
	public Operator authenticate(String pm_operatorId, String pm_password)
	{
		//TODO 
		return null;	

	}

	
	public void changePassword(String pm_operatorId, String pm_oldPassword,
			String pm_newPassword) throws BaseException {
		
		SWAF.addErrorMessage(null, "Your Password Changed Successfully....");
		
	}
	
	public static void main(String[] args) {

		// This is code to find users in a specified container (and below) where the users do not have full names
		// A full name will be assembled from the given name and surname as found
		// an admin level username and password need to be put in below to make this work
		// and you need to specify the context to look for users
		// There's no looping in here. Either add it yourself or use cron to schedule repeat runs of
		// this app
		// there is no multi-threading, no persistent search use, etc. so this may not scale well to large
		// numbers of users

	     try {
	       Hashtable env = new Hashtable();

	       env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	       env.put(Context.STATE_FACTORIES, "PersonStateFactory");
	       env.put(Context.OBJECT_FACTORIES, "PersonObjectFactory");
	       env.put(Context.PROVIDER_URL, "ldap://127.0.0.1:389/");  // SET YOUR SERVER AND STARTING CONTEXT HERE
	       env.put(Context.SECURITY_PRINCIPAL, "cn=admin, o=myorg");  // SET USER THAT CAN SEARCH AND MODIFY FULL NAME HERE
	       env.put(Context.SECURITY_CREDENTIALS, "password");  // SET PASSWORD HERE

	       env.put(LdapContext.CONTROL_FACTORIES, "com.sun.jndi.ldap.ControlFactory");
	       DirContext ctx = new InitialDirContext(env);


	       // Specify the search filter to match all users with no full name
	       String filter = "(&(objectClass=Person) (!(fullName=*)))";
	       // limit returned attributes to those we care about
	       String[] attrIDs = {"sn", "givenName", "fullName"};
	       SearchControls ctls = new SearchControls();
	       ctls.setReturningAttributes(attrIDs);
	       // comment out next line to limit to one container otherwise it'll walk down the tree
	       ctls.setSearchScope(ctls.SUBTREE_SCOPE);

	       // Search for objects using filter and controls
	       NamingEnumeration answer = ctx.search("", filter, ctls);

	       // cycle through result set
	       while (answer.hasMore()) {
	           SearchResult sr = (SearchResult)answer.next();
	           System.out.println(">>>" + sr.getName());
	           String dn = sr.getName();
	           Attributes aa = sr.getAttributes();
	           
	           Attributes attrs = sr.getAttributes();
	           String givenName = " ";
	           String surName = " ";
	           try {
	             givenName = attrs.get("givenName").toString();
	           } catch (Exception err) {
	             givenName = " ";
	           }
	           try {
	             surName = attrs.get("sn").toString();
	           } catch (Exception e2) {
	             surName = " ";
	           }
	           // eDir returns "attribute name : attribute value on get method so strip off up to ": "
	           attrs.put("fullName", givenName.substring(givenName.indexOf(':')+2) + ' ' + surName.substring(surName.indexOf(':')+2));
	           ctx.modifyAttributes(sr.getName(), DirContext.REPLACE_ATTRIBUTE, attrs);
	       }

	      // Close the context when we're done
	      ctx.close();
	     }
	     catch(NamingException ne) {
	       System.err.println(ne);
	       ne.printStackTrace();
	     }
	   }



}
