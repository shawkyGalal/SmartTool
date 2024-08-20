package com.smartValue.authenticators;

import java.sql.SQLException;

public interface IUserNotFoundHandler { 
	public void handleUserNotFound( String m_userUniqueId  , java.sql.Connection m_repCon  ) throws SQLException ; 

}
