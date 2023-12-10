package Support.Configuration;

import java.io.FileInputStream;

import com.sideinternational.clrt.XmlRepParamDoc;

import com.sideinternational.sas.configuration.FileResource;
import com.sideinternational.sas.database.DbType;
import com.sideinternational.sas.event.logging.Console;
import com.sideinternational.sas.util.xml.XmlDocument;
import com.sideinternational.sas.util.xml.XmlNode;
/*
 * This Class is a simple static Implementation for Clrt Configuration Parameters
 */
public  class SmartConfigXmlImpl implements SmartConfig  
{
	private String configFilePath;
	private XmlDocument xmlConfigDoc ;
	
	public SmartConfigXmlImpl(String pm_configFilePath) throws Exception
	{
	
		this.init(pm_configFilePath, FileResource.getResourceLocation(FileResource.CONFIGURATION_XML_SCHEMA));	
	}
	
	public SmartConfigXmlImpl(String pm_configFilePath , String pm_schemaFilePath) throws Exception
	{
		this.init(pm_configFilePath, pm_schemaFilePath);	
	}

	private void init(String pm_configFilePath, String pm_schemaFilePath) throws Exception
	{
		if (xmlConfigDoc != null)
		{
			Console.log("WARNING - The object is already initialized", SmartConfigXmlImpl.class);
			return;
		}
		Console.log("Loading the XML Configuration file...", XmlRepParamDoc.class);
		xmlConfigDoc = new XmlDocument(new FileInputStream(pm_configFilePath), pm_schemaFilePath);
		configFilePath = pm_configFilePath;
	}
	/**
	 * @return User Name of  scdb
	 * from clrt_config.xml
	 */
	public  String getSCDBUserName() {
		XmlNode x = this.xmlConfigDoc.findNode("SCDB_INFO");
		return x.findNode("USER_NAME").getValue();
	}
	/**
	 *@return Password of scdb
	 * from clrt_config.xml
	 */
	public  String getSCDBPassword() {
		XmlNode x = this.xmlConfigDoc.findNode("SCDB_INFO");
		return x.findNode("PASSWORD").getValue();
	}
	/**
	 * @return user name of SideDB
	 * from clrt_config.xml
	 */
	public  String getSIDEDBUserName() {
		XmlNode x = this.xmlConfigDoc.findNode("SIDEDB_INFO");
		return x.findNode("USER_NAME").getValue();
	}
	/**
	 * @return Password of SideDB 
	 * from clrt_config.xml
	 */
	public  String getSIDEDBPassword() {
		XmlNode x = this.xmlConfigDoc.findNode("SIDEDB_INFO");
		return x.findNode("PASSWORD").getValue();
	}
	/**
	 * @return Email Server used in CLRT application
	 * from clrt_config.xml
	 */
	public  String getMailServerIP() {
		XmlNode x = this.xmlConfigDoc.findNode("EMAIL_INFO");
		return x.findNode("EMAIL_SERVER").getValue();
	}
	/**
	 * @return Email user name used in CLRT application
	 * from clrt_config.xml
	 */
	public  String getMailUserName() {
		XmlNode x = this.xmlConfigDoc.findNode("EMAIL_INFO");
		return x.findNode("SENDER_USER_NAME").getValue();

	}
	/**
	 * @return Email password used in CLRT application
	 * from clrt_config.xml
	 */
	public  String getMailPassword() {
		XmlNode x = this.xmlConfigDoc.findNode("EMAIL_INFO");
		return x.findNode("SENDER_PASSWORD").getValue();
	}
	/**
	 * @return Email address of the sender used in CLRT application
	 * from clrt_config.xml
	 */
	public  String getEmailSender() {
		XmlNode x = this.xmlConfigDoc.findNode("EMAIL_INFO");
		return x.findNode("SENDER_EMAIL").getValue();
	}
	/**
	 * returns the path to the rpt files in the file system
	 */
	public  String getReportInputPath() {
		return this.xmlConfigDoc.findNode("REPORTS_BASE_DIRECTORY").getValue();
	}
	/**
	 * returns the path to the generated reports files.
	 */
	public  String getResultOutputPath() {
		return this.xmlConfigDoc.findNode("REPORTS_OUT_DIRECTORY").getValue();
	}
/**
 * @return the name of the CLRT implementaion class 
 */
	public String getClrtImplClass()
	{ 
		return this.xmlConfigDoc.findNode("CLRT_IMPL_CLASS").getValue();
	}
	public static void main(String[] args ) throws Exception
	{
		SmartConfigXmlImpl x = new SmartConfigXmlImpl("C:\\Sources\\Reporting\\Test - SideReportingCMD\\clrt_config.xml");
		System.out.print(x.getResultOutputPath());
	}
/**
 * @return the name o
 */
	public String getScdbDriverClass() {
		XmlNode x = this.xmlConfigDoc.findNode("SCDB_INFO");
		return x.findNode("CLASS").getValue();
	}

	public String getScdbURL() {
		XmlNode x = this.xmlConfigDoc.findNode("SCDB_INFO");
		return x.findNode("URL").getValue();
	}

	public String getSidedbDriverClass() {
		XmlNode x = this.xmlConfigDoc.findNode("SIDEDB_INFO");
		return x.findNode("CLASS").getValue();
	}
	
	public String getSideDbURL() {
		XmlNode x = this.xmlConfigDoc.findNode("SIDEDB_INFO");
		return x.findNode("URL").getValue();
	}
	/**
	 * @return true if oracle client is used
	 */
	public boolean isUseOraClient() {
		String  value = this.xmlConfigDoc.findNode("USE_ORACLE_CLIENT").getValue();
		return (value.equalsIgnoreCase("Y"));
	}
	
	public String getExecDbUrl(String pm_serviceName) throws Exception {
		String result = null;
		if (this.isUseOraClient())
		{
			result = "jdbc:oracle:oci:@"+pm_serviceName;
		}
		else 
		{
			result = getUserDefinedExecDbUrl(pm_serviceName);
		}
		return result;
	}
	private String getUserDefinedExecDbUrl(String pm_serviceName) throws Exception {
		String result = null;
		XmlNode[] x = this.xmlConfigDoc.findNodes("Exec_DB_INFO");
		for (int i= 0; i< x.length; i++ )
		{
			if ( x[i].findNode("Name").getValue().equalsIgnoreCase(pm_serviceName) )	
				{
					result =  x[i].findNode("URL").getValue();
				}
		}
		
		if (result == null)
		{
			throw new Exception ("DB Info Not Found in XML File " + this.configFilePath + "with a Name = ["+pm_serviceName+"]" );
		}
		
		return result;
	}

public DbType getSCDBdbType()
{
	
	XmlNode x = this.xmlConfigDoc.findNode("SCDB_INFO");
	
	return ( x.findNode("URL").getValue().indexOf("oracle") != -1) ? DbType.ORACLE : DbType.SQL_SERVER;

}

public DbType getSideDBdbType()
{
	XmlNode x = this.xmlConfigDoc.findNode("SIDEDB_INFO");
	return ( x.findNode("URL").getValue().indexOf("oracle") != -1) ? DbType.ORACLE : DbType.SQL_SERVER;

}

}
