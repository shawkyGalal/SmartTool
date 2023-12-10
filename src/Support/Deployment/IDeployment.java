package Support.Deployment;

import com.sideinternational.sas.database.DbType;

public  interface  IDeployment {

	public String getSCDBUserName(); 
	public String getSCDBPassword(); 
	public String getSIDEDBUserName() ;
	public String getSIDEDBPassword() ;
	public String getMailServerIP() ;
	public String getMailUserName() ;
	public String getMailPassword() ;
	public String getReportInputPath() ;
	public String getResultOutputPath() ;
	public String getEmailSender() ;
	public String getClrtImplClass();
	//-- Added for Generic Driver Class - for both sql or oracle
	public String getSidedbDriverClass();
	public String getScdbDriverClass();
	public String getScdbURL();
	public String getSideDbURL();
	public boolean isUseOraClient();
	public String getExecDbUrl(String pm_serviceName) throws Exception;
	public DbType getSideDBdbType();
	public DbType getSCDBdbType();
	
}
