package connectionPoolManager;

import java.sql.Connection;
import java.sql.SQLException;

//import oracle.jdbc.pool.OracleConnectionCacheImpl;
import javax.sql.ConnectionPoolDataSource;

import Support.Misc;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import oracle.jdbc.pool.OracleDataSource;

public class SmartValueConnectionCashImpl   { //OracleConnectionCacheImpl{

	private OracleConnectionPoolDataSource ocpds ; 	
/*public SmartValueConnectionCashImpl() throws SQLException {
	super();
	setExplicitCachingEnabled (true) ;	
	
	}*/
public OracleConnectionPoolDataSource getOcpds()
{
	return ocpds ; 
}
public SmartValueConnectionCashImpl(OracleConnectionPoolDataSource m_ocpds )  throws SQLException {
	ocpds = m_ocpds ; 
	ocpds.setExplicitCachingEnabled (true) ; 
	//super(ocpds);
}
@SuppressWarnings("deprecation")
public Connection getConnection() throws SQLException
{
	return ocpds.getConnection();
}

public static void main(String[] args) throws Exception {
		 
	OracleConnectionPoolDataSource ocpds = new OracleConnectionPoolDataSource();
	
    Support.XMLConfigFileReader supportConfig =  Misc.getXMLConfigFileReader(false ) ; 
	ocpds.setURL(supportConfig.reposatoryConn.url ); 
	ocpds.setUser(supportConfig.reposatoryConn.userName);
	ocpds.setPassword(supportConfig.reposatoryConn.password);
	ocpds.setImplicitCachingEnabled(true);
		SmartValueConnectionCashImpl cpm = new SmartValueConnectionCashImpl(ocpds);
 		Connection con1 = cpm.getConnection();
 		cpm.printInfor();
		Connection con2 = cpm.getConnection();
		cpm.printInfor();
		Connection con3 = cpm.getConnection();
		cpm.printInfor();
		con1.close();
		con1.getMetaData();
		Connection con4 = cpm.getConnection();
		cpm.printInfor();
		Connection con5 = cpm.getConnection();
		cpm.printInfor();
 
	}
	
	public void printInfor()
	{
		//System.out.println("============");
		//System.out.println("Active size : " + this.ocpds.getActiveSize());
	    //System.out.println("Cache Size is " + this.getCacheSize());

	}
	
	

}
