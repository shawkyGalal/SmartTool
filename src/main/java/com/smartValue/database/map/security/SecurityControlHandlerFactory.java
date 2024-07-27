package com.smartValue.database.map.security;

public class SecurityControlHandlerFactory
{
	private static PersistentObjectSecurityControl securityControlHandlerTmdImpl = new SecurityControlHandlerTmdImpl();
	
	public static PersistentObjectSecurityControl getTmdImpl()
	{
		return securityControlHandlerTmdImpl;
	}

}
