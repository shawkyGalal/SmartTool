package com.smartValue.database.mapGeneration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import Support.XMLConfigFileReader;

import com.sideinternational.sas.configuration.Configuration;
import com.smartValue.database.ApplicationContext;
import com.smartValue.database.DbForignKeyArray;
import com.smartValue.database.DbServices;
import com.smartValue.database.DbTable;
import com.smartValue.database.MessagesCommnuicatorService;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.SysMnu;
import com.smartValue.database.map.TableMaintDetails;
import com.smartValue.database.map.TableMaintMaster;
import com.smartValue.database.map.services.AbcModuleServiceContainer;
import com.smartValue.database.map.services.ModuleServices;
import com.smartValue.database.map.services.ModuleServicesContainer;
import com.smartValue.database.map.services.SystemMenusServices;
import com.smartValue.database.map.services.TableMaintMasterServices;
import com.smartValue.listeners.ApplicationContextListener;

public class MapGenerator extends ModuleServices{
	
	public MapGenerator(DbServices pmDbServices) {
		super(pmDbServices);
		// TODO Auto-generated constructor stub
	}
	private String 	tableName ;
	private String  tableOwner ;
	private String 	xhtmlFolder = "HR" ;
	
	private String 	sourceOutPath = "D:\\SmartValue\\Sources\\App\\Implex-core\\src\\";
	private String 	packageName = "com.implex.database.map";
	private String 	xHtmlPath = "D:\\SmartValue\\Sources\\App\\SmartTool\\WebContent\\"+xhtmlFolder;
	
	private boolean generateChilren = false;
	private boolean generateValidationClass = false;
	private boolean generateXhtmlMaintForm = false;
	private boolean generateXhtmlMaintainance = false;
	private boolean generateServiceClass = false;
	private boolean generateSecurityHandlerClass = false;
	private boolean addAsTreeNode = false;
	private boolean addToFavorite = false;
	private String message = "";
	private String treeNodeParent = "";
	
	private String[] columnNames;
	private String[] columnClassNames;
	
	private String mainClassName;
	private String appEnvironment = "smartTool" ;
	private String connName = "PNU_PROD" ;  
	private ArrayList<MapCalculatedVariable> mapCalculatedVariables = new ArrayList<MapCalculatedVariable>(); 
	private CodeGeneratorView codeGeneratorView = new CodeGeneratorView();
	//private StringBuffer codeForMaintForm = new StringBuffer();

	/**
	 * @param args
	 * @throws Exception 
	 * @throws MalformedURLException 
	 */
	

	public String getXhtmlFolder() {
		return xhtmlFolder;
	}

	public void setXhtmlFolder(String xhtmlFolder) {
		this.xhtmlFolder = xhtmlFolder;
	}

	public String getTableFullName()
	{
		return ((this.getTableOwner()!= null)? this.getTableOwner() +"." : "" )+ this.getTableName() ;
	}
	public void generateMapClasses()
	{
	try{
			
		Support.XMLConfigFileReader ConParam = 
			new XMLConfigFileReader(new URL(
				"File:\\" + Configuration.getConfigurationHome()
						+ File.separator + this.getAppEnvironment() + File.separator
						+ "Connections_config.xml"), false);
		
		Connection con = ConParam.getConnParmsByName(this.connName).generateConnection();
		ResultSet rs = con.createStatement().executeQuery("select * from " + this.getTableFullName() + "\n" + " where 1<>1" );
		
		
		ResultSetMetaData rsmd =  rs.getMetaData();
		int columnCount = rsmd.getColumnCount();

		 mainClassName = this.getNamingConvention(this.getTableName(), "class");
		
		
		String mapClass = "package "+this.getPackageName()+";";
		mapClass += "\n\nimport "+this.getPackageName()+".auto._"+mainClassName+"; " ;
		if (this.isGenerateSecurityHandlerClass())
			mapClass += "\n\nimport "+this.getPackageName()+".security."+mainClassName+"SecurityControlHandlerImpl; " ;
		mapClass += "\nimport com.implex.database.PersistantObject;";
		mapClass += "\nimport com.implex.database.Attribute;";
		mapClass += "\nimport com.implex.database.AttributeChangeValidator;";
		if(this.isGenerateValidationClass())
			mapClass += "\nimport "+this.getPackageName()+".validators."+mainClassName+"ChangeValidator;";
		mapClass += "\nimport com.implex.database.map.SecUsrDta;";
		mapClass += "\nimport com.implex.database.map.security.PersistentObjectSecurityControl;";
		mapClass += "\nimport com.implex.database.trigger.TriggerHandler;";
		mapClass += "\nimport com.implex.database.audit.AuditInDbTriggerHandler;";
		
		mapClass += "\nimport com.implex.database.DbForignKeyArray;";
		mapClass += "\nimport java.util.HashMap;";

		
		mapClass +=	"\n\npublic class " + mainClassName + " extends _"+mainClassName+" {" ;
		if (this.isGenerateSecurityHandlerClass())
			mapClass += "\n\tprivate "+mainClassName+"SecurityControlHandlerImpl securityControlHandlerImpl = new "+mainClassName+"SecurityControlHandlerImpl();";
		mapClass += "\n\t private static HashMap<String, DbForignKeyArray> childrenForignKeys;";
		mapClass += "\n\t@Override";
		mapClass += "\n\tpublic HashMap<String, DbForignKeyArray> getChildrenForignKeys() ";
		mapClass += "\n\t{";
		mapClass += "\n\t\t if (childrenForignKeys == null) ";
		mapClass += "\n\t\t  { childrenForignKeys = new HashMap<String, DbForignKeyArray>(); " ;
		
		mapClass += "\n\t\t\t  for (DbForignKeyArray fka : this.getTableMaintMaster().getAllForignKeysArrays()) ";
		mapClass += "\n\t\t\t  { ";
		mapClass += "\n\t\t\t  childrenForignKeys.put(fka.getName(), fka); ";
		mapClass += "\n\t\t\t  } ";
		mapClass += "\n\t\t\t  //TODO... Fill Your Childern ";
		mapClass += "\n\t\t\t  //Example ... this.getTableMaintMaster().getDbForignKey(new DbTable(\"ICDB\" , SavedSearchDetail.DB_TABLE_NAME)); ";
		mapClass += "\n\t\t  } " ;
		mapClass += "\n\t return childrenForignKeys; " ;
		mapClass +="\n\t}" ;
		
		mapClass += "\n\t@Override";
		mapClass += "\n\tpublic PersistentObjectSecurityControl getSecurityController() ";
		mapClass += "\n\t{"; 
		if (this.isGenerateSecurityHandlerClass())
			mapClass += "\n\t	return securityControlHandlerImpl; " ;
		else
			mapClass += "\n\t	return null;" ;
		mapClass +="\n\t}" ;
		mapClass +="\n ";
		mapClass += "\n\t private static TriggerHandler triggerHandler = null; ";
		mapClass += "\n\t@Override";
		mapClass += "\n\tpublic TriggerHandler getTriggerHandler() ";
		mapClass += "\n\t{";
		mapClass += "\n\t\t boolean auditable = this.getTableMaintMaster().getAuditable().getBooleanValue(); ";
		mapClass += "\n\t\t if (triggerHandler == null && auditable ) ";
		mapClass += "\n\t\t\t{ ";
		mapClass += "\n\t\t\t	triggerHandler = new AuditInDbTriggerHandler(); ";
		mapClass += "\n\t\t\t} ";
		mapClass += "\n\t\t return triggerHandler; ";
		mapClass +="\n\t}" ;

		mapClass += "\n\t@Override " ;
		mapClass += "\n\tpublic AttributeChangeValidator getAttributeChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key)";
		mapClass += "\n\t{ ";
		if (this.isGenerateValidationClass())
			mapClass += "\n\t return new "+this.getChangeValidatorClassName()+"(pm_secUserData , pm_po  , pm_key); ";
		else
			mapClass += "\n\t\treturn null; ";
		mapClass += "\n\t}" ;
			
		mapClass += "\n\t public void initialize() ";
		mapClass += "\n\t { " ;
		mapClass += "\n\t\t//Write your own initialization code here this will help you greatly improve performance especially" ; 
		mapClass += "\n\t\t// apply our standard rule < Minimise code inside any getXyz() method - simply return object -  > " ; 
		for (MapCalculatedVariable mcv : this.getMapCalculatedVariables())
		{
			if (! mcv.isLazyInitialized())
			{
				mapClass += "\n\t\t refresh"+ mcv.getVarNameWithFirstCapital() +"(); " ;
			}
		}
		
		mapClass += "\n\t } " ; 

		for (MapCalculatedVariable mcv : this.getMapCalculatedVariables())
		{
			mapClass += "\n\t  private "+mcv.getVarClass().getName()+"  "+ mcv.getVarName() +" ;  " ;
			mapClass += "\n\t  public "+mcv.getVarClass().getName()+" get"+ mcv.getVarNameWithFirstCapital() +"() " ;
			mapClass += "\n\t  { " ;
			if ( mcv.isLazyInitialized())
			{
				mapClass += "\n\t\t if ( this."+ mcv.getVarName() +"== null)" ; 
				mapClass += "\n\t\t {" ; 
				mapClass += "\n\t\t\t refresh"+ mcv.getVarNameWithFirstCapital() +"() ;  " ; 
				mapClass += "\n\t\t }" ; 
			}
			mapClass += "\n\t\t return "+ mcv.getVarName() +" ; " ;
			mapClass += "\n\t  } " ;
			  
			mapClass += "\n\t  private "+mcv.getVarClass().getName()+" calc"+ mcv.getVarNameWithFirstCapital() +"() " ;
			mapClass += "\n\t  { " ;
			mapClass += "\n\t\t  "+mcv.getVarClass().getName()+" result = null ; " ; 
			mapClass += "\n\t\t  //Write Down Your Code here to calculate the value of "+mcv.getVarName()+"... " ;
			mapClass += "\n\t\t  return result ;  " ;
			mapClass += "\n\t  } " ;
			mapClass += "\n\t " ;
			mapClass += "\n\t  public void refresh"+ mcv.getVarNameWithFirstCapital() +"() " ;
			mapClass += "\n\t  { " ;
			mapClass += "\n\t\t  //Important Please call this method in the afterAttributeChange() method for any attribute used to calculate this variable " ;
			mapClass += "\n\t\t  this."+ mcv.getVarName() +" = calc"+ mcv.getVarNameWithFirstCapital() +"() ; " ;
			mapClass += "\n\t  } " ;
		}
		
		mapClass +="\n\t	@Override" ; 
		mapClass +="\n\t	public void beforeAttributeChange(Attribute pm_att) throws Exception {" ;
		mapClass +="\n\t	}" ;
		mapClass +="\n\t	@Override" ;
		mapClass +="\n\t	public void afterAttributeChange(Attribute pm_att) {" ;
		mapClass +="\n\t	}" ;

		mapClass +="\n}" ;
		
		String autoClass = "package "+this.getPackageName()+".auto;";
		autoClass += "\n\nimport com.implex.database.PersistantObject; " ;
		autoClass += "\nimport com.implex.database.DataSet;" ;
		autoClass += "\nimport com.implex.database.Attribute;";
		autoClass += "\nimport com.implex.database.DbTable;";
		autoClass += "\nimport com.implex.database.ApplicationContext;";
		autoClass += "\n\npublic abstract class _" + mainClassName + " extends PersistantObject {" ;
		autoClass += "\n/* Dynamically Generated Mapping Class " ;
		autoClass += "\n * By : Shawky Foda. Please Never Update This Class.. Only regenerate... " ;
		autoClass += "\n */" ;
		autoClass += "\n private static final long serialVersionUID = 1L ; " ;
		autoClass += "\n public static final String DB_TABLE_NAME = \""+this.getTableName().toUpperCase()+"\"; ";
		autoClass += "\n public static final String DB_TABLE_OWNER = \""+this.getTableOwner().toUpperCase()+"\"; ";
		// Constructor 			
		autoClass += "\n\n\tpublic _" + mainClassName + "(){" ;
		//autoClass += " \n\t\tthis.setTable(new DbTable(\""+mg.getTableName().toUpperCase()+"\"));";
		autoClass += "\n\t}";
		
		autoClass += "\n\n\t public DbTable getTable() ";
		autoClass += "\n\t { " ;
		autoClass += "\n\t	return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); ";
		autoClass += "\n\t } " ;
		
		List<String> biLangualColumns = new ArrayList<String>();
		columnNames = new String[columnCount+1];
		columnClassNames = new String[columnCount+1];
		
		for ( int i = 1 ; i<= columnCount ; i++)
		{
			
			columnNames[i] = rsmd.getColumnName(i);
			columnClassNames[i] = rsmd.getColumnClassName(i);
			if (columnNames[i].endsWith("_") && columnClassNames[i].equalsIgnoreCase("java.lang.String") )
			{
				biLangualColumns.add(columnNames[i].substring(0, columnNames[i].lastIndexOf('_')));
			}
			autoClass += "\n\n\tpublic static final String "+  columnNames[i] + " =\""+columnNames[i]+"\" ;" ;
			autoClass += "\n\n\tpublic void set"+this.getNamingConvention(columnNames[i], "setter")+"Value("+columnClassNames[i]+"   pm_"+this.getNamingConvention(columnNames[i], "attribute")+"){";
			autoClass += "\n\t\tthis.getAttribute("+columnNames[i]+" ).setValue( pm_"+this.getNamingConvention(columnNames[i], "attribute")+" );";	
			autoClass += "\n\t}";
			
			autoClass += "\n \n\tpublic "+columnClassNames[i] +" get"+this.getNamingConvention(columnNames[i], "getter")+"Value(){";
			autoClass += "\n\t\treturn ("+columnClassNames[i] +") this.getAttribute ( "+columnNames[i]+").getValue()  ;";	
			autoClass += "\n\t}";
			
			autoClass += "\n \n\tpublic Attribute get"+this.getNamingConvention(columnNames[i], "getter")+"(){";
			autoClass += "\n\t\treturn this.getAttribute ( "+columnNames[i]+")  ;";	
			autoClass += "\n\t}";			
		}	
		for ( int i = 0 ; i< biLangualColumns.size() ; i++)
		{
			String biLangualColumnName = biLangualColumns.get(i);
			//Auto Bilingual set method
			autoClass += "\n\n\tpublic void set"+this.getNamingConvention(biLangualColumnName, "setter")+"AutoLang"+"(java.lang.String   pm_"+this.getNamingConvention(biLangualColumnName, "attribute")+"){";
			autoClass += "\n\t\t int userlang = this.getDbService().getLoggedUserLang() ;";
			autoClass += "\n\t\t int sysDefaultLang = 2; //TODO getSysDefault";
			autoClass += "\n\t\t if (userlang == sysDefaultLang) ";
			autoClass += "\n\t\tthis.get"+this.getNamingConvention(biLangualColumnName, "getter")+"().setValue( pm_"+this.getNamingConvention(biLangualColumnName, "attribute")+" );";	
			autoClass += "\n\t\t else ";			
			autoClass += "\n\t\tthis.get"+this.getNamingConvention(biLangualColumnName, "getter")+"_().setValue( pm_"+this.getNamingConvention(biLangualColumnName, "attribute")+" );";	

			autoClass += "\n\t}";
			
			//Auto Bilingual get method
			autoClass += "\n \n\tpublic Attribute get"+this.getNamingConvention(biLangualColumnName, "getter")+"AutoLang"+"(){";
			autoClass += "\n\t\t Attribute result ;"; 
			autoClass += "\n\t\t int userlang = this.getDbService().getLoggedUserLang() ;";
			autoClass += "\n\t\t int sysDefaultLang = 2; //TODO getSysDefault";
			
			autoClass += "\n\t\t if (userlang == sysDefaultLang) ";			
			autoClass += "\n\t\t result =   this.get"+this.getNamingConvention(biLangualColumnName, "getter")+"();";
			autoClass += "\n\t\t else ";			
			autoClass += "\n\t\t result =   this.get"+this.getNamingConvention(biLangualColumnName, "getter")+"_();";

			autoClass += "\n\t\t return result;" ;				
			autoClass += "\n\t}";
			
		}
		autoClass += this.getCodeForGettingChildDataSets(con);
		autoClass+= "\n}" ;

		String path = this.getSourceOutPath()+ this.getPackagePathName(); 
		String fileName1 = path + File.separator+mainClassName + ".java";
		String fileName2 = path + File.separator+"auto"+File.separator+"_" + mainClassName + ".java";
		
		File file=new File(fileName1);
	    if( !file.exists())
	    	this.writeClass(mapClass, fileName1);
	    
		this.writeClass(autoClass, fileName2);
				
		con.close();
		
		
	}
	catch(Exception ex){
		ex.printStackTrace(); 
	}
	
	}
	
	private String getCodeForGettingChildDataSets(Connection con) {
		StringBuffer result = new StringBuffer();
		StringBuffer query = new StringBuffer ("select ucc.constraint_name , uc.table_name  aname , uc.table_name  ename" ) ;
		query.append("\n from all_constraints uc , all_cons_columns ucc  , all_cons_columns ucc2") ; 
		query.append("\n where uc.table_name = ucc.table_name " );
		query.append("\n and uc.owner = ucc.owner " );
		query.append("\n and uc.constraint_name = ucc.constraint_name " );
		query.append("\n and ucc2.constraint_name = uc.r_constraint_name " ); 
		query.append("\n and ucc2.position = ucc.position  " );
		query.append("\n and ucc2.table_name = '"+this.getTableName()+"' " ); 
		if (this.getTableOwner() != null) query.append("\n and ucc2.owner = '"+this.getTableOwner()+"' " );
		query.append("\n and uc.constraint_type = 'R' " );
		query.append("\n and uc.validated = 'VALIDATED' " ); 
		query.append("\n and uc.status = 'ENABLED' " );
		query.append("\n Order By  uc.table_name ") ;
		ResultSet rs = null ;
		try{
			rs = con.createStatement().executeQuery( query.toString());
			result.append("\n\n\t// ********Start of Child DataSets getter methods******** ");
			while (rs.next())
			{
				String fkeyConstrainName = rs.getString(1);
				String javaConstrainName = getNamingConvention(fkeyConstrainName, "class"); 
				result.append("\n\tpublic DataSet getDataset" + javaConstrainName +"()");
				result.append("\n\t{");
				result.append("\n\t\t return this.geChildDsByForignKeyName(\""+fkeyConstrainName+"\");");
				result.append("\n\t}");
				
			}
			result.append("\n\t// *******End of Child DataSets getter methods******** ");
		}
		catch (Exception e)
		{
			try { rs.close(); } 
			catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
		}
		finally 
		{
			try { rs.close(); } 
			catch (SQLException e1) { e1.printStackTrace(); }
		
		}
		return result.toString();
	}

	private void generateTheSecurityHandlerClass() {
		String secHandlerClass = "package "+getPackageName()+".security;" +
				"\nimport com.implex.database.PersistantObject;" +
				"\nimport com.implex.database.map.security.PersistentObjectSecurityControl;" +
				"\nimport com.sideinternational.sas.BaseException;" +
				"\nimport com.implex.database.Attribute;" +
				"\npublic class "+mainClassName+"SecurityControlHandlerImpl implements PersistentObjectSecurityControl" +
				"\n{" +
				"\n	@Override" +
				"\n	public boolean isStoredEncrypted(Attribute pm_att) {" +
				"\n	return false;" +
				"\n}" +
				"\n	@Override" +
				"\n	public boolean isSecured( Attribute pm_att) {" +
				"\n	return false;" +
				"\n	}" +
				"\n	@Override" +
				"\n	public boolean isRequired( Attribute pm_att) {" +
				"\n	return false;" +
				"\n}" +
				"\n	@Override" +
				"\n	public boolean isRendered( Attribute pm_att) {" +
				"\n	return true;" +
				"\n}" +
				"\n	@Override" +
				"\n public boolean isObjectCanBeSaved(	PersistantObject pmPersistentObject) {" +
				"\n	return true;" +
				"\n	}" +
				"\n	@Override" +
				"\n	public boolean isObjectCanBeDeleted( PersistantObject pmPersistentObject) {" +
				"\n return true;" +
				"\n}" +
				"\n	@Override" +
				"\n	public boolean isObjectCanBeAdded( PersistantObject pmPersistentObject) {" +
				"\n	return true;" +
				"\n}" +
				"\n	@Override" +
				"\n	public boolean isDisabled( Attribute pm_att) {" +
				"\n	return false;" +
				"\n}" +
				"\n	@Override" +
				"\n	public String getOnMouseOver( Attribute pm_att) {" +
				"\n	return null;" +
				"\n	}" +
				"\n	@Override" +
				"\n public String getOnChangeRerender( Attribute pm_att) {" +
				"\n	return null;" +
				"\n}" +
				"\n	@Override" +
				"\n	public String getOnChange( Attribute pm_att) {" +
				"\n	return null;" +
				"\n}" +
				"\n	@Override" +
				"\n	public String getHint( Attribute pm_att) {" +
				"\n	return null;" +
				"\n}" +
				"\n}";	
		String path = this.getSourceOutPath()+ this.getPackagePathName(); 
		String fileName = path + File.separator+"security"+File.separator+mainClassName+"SecurityControlHandlerImpl.java";
		
		File file=new File(fileName);
	    if( !file.exists())
	    	this.writeClass(secHandlerClass, fileName);
	}

	private void addToTree() throws Exception {
		ModuleServicesContainer msc ;
		Configuration.initializeForWeb(this.getAppEnvironment(), "en-config.xml");
		String configurationHome = Configuration.getConfigurationHome() ;
		ApplicationContext.setPools(ApplicationContextListener.initializeConnections(new URL ("File:\\"+ configurationHome+File.separator +this.getAppEnvironment()+ File.separator + "Connections_config.xml")));
		msc = new AbcModuleServiceContainer(this.getAppEnvironment() , 1);
		msc.getDbServices().setLogRequestToConsole(false);
		SecUsrDta loggedUser = msc.getSecUserDataService().getUserByUserName("admin"); 
		msc.getDbServices().setLoggedUser(loggedUser);
		DbServices dbs = msc.getDbServices();
		SystemMenusServices sysMnuServices =  msc.getSysMenuServices();
		//msc.getDbServices().setLogRequestToConsole(true);
		try {
			sysMnuServices.getDataSet().addNew();
			SysMnu sm  = (SysMnu) sysMnuServices.getDataSet().getCurrentItem() ;
			sm.setMnuParentValue(this.getTreeNodeParent());
			sm.setMnuPrgNameValue("/"+this.getXhtmlFolder()+"/"+ this.getxHtmlMaintainanceFileName());
			sm.setMnuTypeValue(new BigDecimal(SysMnu.PHYSICAL_PAGE_TYPE));
			sm.setMnuCodeValue(this.mainClassName+"999");
			sm.setMnuCallIdValue(this.mainClassName+"999");
			sm.setMnuTxt_Value(this.mainClassName+" maintainance");
			sysMnuServices.getDbServices().setLogRequestToConsole(false);
			sysMnuServices.getDataSet().saveAll();
			if(this.isAddToFavorite())
			{
				loggedUser.getDbService().setLogRequestToConsole(false);
				loggedUser.addToFavorite(sm);
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}	
	}

	private void generateTheServiceClass() {
		String serviceClass = "package "+getPackageName()+".services;" +
				"\n\nimport "+this.getPackageName()+"."+this.mainClassName+";" +
				"\nimport com.implex.database.DbTable;" +
				"\nimport com.implex.database.DbServices;" +
				"\nimport com.implex.database.map.services.ModuleServices;" +
				"\n\npublic class "+mainClassName+"Services extends ModuleServices{" +
				
				"\n\n\t public "+mainClassName+"Services (DbServices pmDbServices) { " +
				"\n\t	super(pmDbServices); " +
				"\n\t }" +
				
				"\n\t @Override " +
				"\n	public String getOrginalQuery()	" +
				"\n	{" +
				"\n		return  \"select t.rowid , t.* from "+this.getTableFullName()+" t \" ;" +
				"\n	}" +
				"\n	@Override" +
				"\n	public Class getPersistentClass()" +
				"\n	{" +
				"\n		return "+this.mainClassName+".class;" +
				"\n	}" +
				"\n" +
				"\n	@Override" +
				"\n	public boolean isCanHaveMultipleInstances()" +
				"\n	{" +
				"\n		return false;" +
				"\n	}" +
				"\n	@Override" +
				"\n	public DbTable getDbTable() {" +
				"\n		return new DbTable("+this.mainClassName+".DB_TABLE_NAME);" +
				"\n }" +
				"\n\t @Override" +
				"\n\t public void afterModuleRemoved() {" +
				"\n\t 	// TODO Auto-generated method stub" +					
				"\n\t }" +
				"\n\t @Override" +
				"\n\t public void beforeModuleRemoved() throws Exception {" +
				"\n\t 	// TODO Auto-generated method stub" +					
				"\n\t }" +
				"\n	" +
				"\n}";
		String path = this.getSourceOutPath()+ this.getPackagePathName(); 
		String fileName = path + File.separator+"services"+File.separator+mainClassName+"Services.java";
		
		File file=new File(fileName);
	    if( !file.exists())
	    	this.writeClass(serviceClass, fileName);
	}


	private String getPackagePathName()
	{
		return Support.Misc.repalceAll(this.getPackageName(), ".", File.separator);
	}
	public ModuleServicesContainer msc ;
	private TableMaintMaster tableMaintMaster ;
	
	private TableMaintMaster calcTableMaintMaster() throws Exception
	{
		TableMaintMaster result; 
		Configuration.initializeForWeb(this.getAppEnvironment(), "en-config.xml");
		String configurationHome = Configuration.getConfigurationHome() ;
		ApplicationContext.setPools(ApplicationContextListener.initializeConnections(new URL ("File:\\"+ configurationHome+File.separator +this.getAppEnvironment()+ File.separator + "Connections_config.xml")));
		msc = new AbcModuleServiceContainer(this.connName , 1);

		SecUsrDta loggedUser = new SecUsrDta();
		
		msc.getDbServices().setLogRequestToConsole(false);
		String userName = "JCCS" ;
		loggedUser = msc.getSecUserDataService().getUserByUserName(userName); 
		
		msc.getDbServices().setLoggedUser(loggedUser);
		
		TableMaintMasterServices tms = new TableMaintMasterServices(msc.getDbServices()) ;  //msc.getTableMaintServices();
		msc.getDbServices().setLogRequestToConsole(false) ;
		tms.setDbService(msc.getDbServices());
		tms.setTableOwner(this.getTableOwner()); 
		tms.setTableName(this.getTableName());
		result =  tms.getCurrentTable() ;
		
		return result ;
	}
	public void setTableMaintMaster(TableMaintMaster tmm)
	{
		this.tableMaintMaster = tmm;
		this.tableName = tmm.getTableNameValue();
		this.tableOwner = tmm.getOwnerValue();
	}
	private TableMaintMaster getTableMaintMaster() throws Exception
	{
		if (tableMaintMaster == null)
		{
			tableMaintMaster = calcTableMaintMaster();
		}
		return tableMaintMaster ;
	}
	private TableMaintMaster generateTableMaintDetailsDefaults() throws Exception
	{
		TableMaintMaster tmm = this.getTableMaintMaster();
		
		tmm.synchronizeWithDb();
		tmm.setMapClassNameValue(this.getPackageName()+"." + this.mainClassName) ;
		
		//if (tmm.getObjectEditorXhtmlPageValue() == null)
		{
			String objectEditorXhtmlPage = this.getXHtmlPath()+ File.separator +this.getxHtmlMaintFormFileName();
			String webPath = objectEditorXhtmlPage.substring(objectEditorXhtmlPage.indexOf("WebContent")+ 10) ; 
			webPath= webPath.replace('\\', '/');
			tmm.setObjectEditorXhtmlPageValue(webPath) ;
		}
		
		String instanceNameInXhtml = this.getClassInstanseName() ; 
		tmm.setInstanceNameInXhtmlEditorValue(instanceNameInXhtml);
		tmm.setObjectNameValue(instanceNameInXhtml);
		tmm.setObjectName_Value(instanceNameInXhtml);
		
		tmm.save();
		return tmm ; 
	}
	private void generateXhtmlMaintForm() throws Exception
	{
	
		String xHtmlMaintFormFileName = this.getxHtmlMaintFormFileName();
		StringBuffer xHtmlMaintFormFileContent = new StringBuffer("");
		
		String xhtmlFullPath = this.getXHtmlPath()+ File.separator +xHtmlMaintFormFileName ; 
		File file=new File(xhtmlFullPath);
	    if( !file.exists())
	    {	
			xHtmlMaintFormFileContent.append("\n<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\" ");
			xHtmlMaintFormFileContent.append("\n	xmlns:ui=\"http://java.sun.com/jsf/facelets\" ");
			xHtmlMaintFormFileContent.append("\n	xmlns:h=\"http://java.sun.com/jsf/html\" " );
			xHtmlMaintFormFileContent.append("\n	xmlns:f=\"http://java.sun.com/jsf/core\" " );
			xHtmlMaintFormFileContent.append("\n	xmlns:rich=\"http://richfaces.org/rich\" " );
			xHtmlMaintFormFileContent.append("\n	xmlns:a4j=\"http://richfaces.org/a4j\" " );
			xHtmlMaintFormFileContent.append("\n	xmlns:jsp=\"http://java.sun.com/JSP/Page\"> " );
			xHtmlMaintFormFileContent.append("\n " );

			xHtmlMaintFormFileContent.append("\n<a4j:form>");
			xHtmlMaintFormFileContent.append("\n  <h:panelGrid  columns=\"2\"  style=\"width: 100%\"> ");
			TableMaintMaster tmm = this.generateTableMaintDetailsDefaults();
	
			for (int i = 1 ; i< this.columnNames.length ; i++)
			{
				TableMaintDetails tmd =  tmm.getTmdByColumnName(columnNames[i]);
				if (tmd != null)
				{
					xHtmlMaintFormFileContent.append("\n\t <ui:include src=\"#{"+this.getClassInstanseName()+"."+this.getNamingConvention(columnNames[i], "attribute")+".jsfTemplateFile}\" > ");				
				}
				else 
				{
					if (  columnClassNames[i].equalsIgnoreCase("java.lang.String") || columnClassNames[i].equalsIgnoreCase("java.math.BigDecimal") ) 
					{
						xHtmlMaintFormFileContent.append("\n\t <ui:include src=\"/templates/include/controls/inputText.xhtml\" > ");
					}
					else if (  columnClassNames[i].equalsIgnoreCase("java.util.Date") || columnClassNames[i].equalsIgnoreCase("java.sql.Timestamp"))
					{
						xHtmlMaintFormFileContent.append("\n\t <ui:include src=\"/templates/include/calendar.xhtml\" > ");
					}
					
					else 
					{
						xHtmlMaintFormFileContent.append("\n\t <ui:include src=\"/"+xhtmlFolder+"/"+columnClassNames[i]+".xhtml\" > ");
					}
				}
				xHtmlMaintFormFileContent.append("\n\t 	<ui:param name=\"attribute\" value=\"#{"+this.getClassInstanseName()+"."+this.getNamingConvention(columnNames[i], "attribute")+"  }\" /> ");
				xHtmlMaintFormFileContent.append("\n\t </ui:include>	");
				xHtmlMaintFormFileContent.append("\n\t");
				
			}

			xHtmlMaintFormFileContent.append("\n </h:panelGrid>\n</a4j:form> ");
			
			xHtmlMaintFormFileContent.append("\n <rich:tabPanel dir=\"#{userSession.langDirection}\" width=\"1200\" switchType=\"client\" headerAlignment=\"#{userSession.alignDirection }\"> ") ;
			 
			for (DbForignKeyArray fka : this.getTableMaintMaster().getAllForignKeysArrays())
			{
				if (fka.isGenerateDsTabInMaintForm())
				{
					String fkeyConstrainName = fka.getName();
					String javaConstrainName = getNamingConvention(fkeyConstrainName, "class"); 
					
					xHtmlMaintFormFileContent.append("\n\t<rich:tab styleClass=\"labelStyle\" label=\""+fkeyConstrainName+"\"> ") ;
					xHtmlMaintFormFileContent.append("\n\t\t<ui:include src=\"/templates/include/dataTable.xhtml\">") ;
					xHtmlMaintFormFileContent.append("\n\t\t\t<ui:param name=\"dataSet\" value=\"#{"+getClassInstanseName()+".dataset" + javaConstrainName +"}\" /> ");
					xHtmlMaintFormFileContent.append("\n\t\t\t<ui:param name=\"TableId\" value=\""+fkeyConstrainName+"\" /> " );
					xHtmlMaintFormFileContent.append("\n\t\t\t<ui:param name = \"onSelectRerender\"  value = \"Nothing\" />" );	
					xHtmlMaintFormFileContent.append("\n\t\t\t<ui:param name=\"overFlowWidth\" value=\"1200\" />");
					xHtmlMaintFormFileContent.append("\n\t\t\t<ui:param name=\"simpleToggleLable\" value=\""+fkeyConstrainName+"\" />");
					xHtmlMaintFormFileContent.append("\n\t\t\t<ui:param name=\"showToolbar\" value=\"true\" />");
					xHtmlMaintFormFileContent.append("\n\t\t\t<ui:param name = \"dataTableExtraColumnPathAtFirst\" value=\"/templates/include/dataTableExtraColumnsPath/columnForPoDefaultEditor.xhtml\"/>");
					xHtmlMaintFormFileContent.append("\n\t\t</ui:include>");
	
					xHtmlMaintFormFileContent.append("</rich:tab>");
				}
			}
			xHtmlMaintFormFileContent.append("\n </rich:tabPanel> ") ;
			
			xHtmlMaintFormFileContent.append("\n </ui:composition> ");
			
		    this.writeClass(xHtmlMaintFormFileContent.toString(), xhtmlFullPath);
	    }
		
	}

	private void generateXhtmlMaintainance() throws Exception
	{
	
		String xHtmlMaintainanceFileName = this.getxHtmlMaintainanceFileName();
		StringBuffer xHtmlMaintFormFileContent = new StringBuffer("");
		
		xHtmlMaintFormFileContent.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"> ");
		xHtmlMaintFormFileContent.append("\n<html xmlns=\"http://www.w3.org/1999/xhtml\" ");
		xHtmlMaintFormFileContent.append("\n	xmlns:ui=\"http://java.sun.com/jsf/facelets\" ");
		xHtmlMaintFormFileContent.append("\n	xmlns:h=\"http://java.sun.com/jsf/html\" " );
		xHtmlMaintFormFileContent.append("\n	xmlns:f=\"http://java.sun.com/jsf/core\" " );
		xHtmlMaintFormFileContent.append("\n	xmlns:rich=\"http://richfaces.org/rich\" " );
		xHtmlMaintFormFileContent.append("\n	xmlns:a4j=\"http://richfaces.org/a4j\" " );
		xHtmlMaintFormFileContent.append("\n	xmlns:jsp=\"http://java.sun.com/JSP/Page\"> " );
		xHtmlMaintFormFileContent.append("\n " );
		
		xHtmlMaintFormFileContent.append("\n" +
				"\n 	<a4j:include viewId=\"/templates/include/moduleServiceMaintainance.xhtml\" >"+
				"\n 		<ui:param 	name=\"moduleServices\" 	value=\"#{moduleServicesContainer."+getClassInstanseName()+"Services}\" />"+
				"\n 		<ui:param 	name=\"poMaintForm\" 	value=\"/"+xhtmlFolder+"/"+getClassInstanseName()+"MaintForm.xhtml\" />" +
				"\n 		<ui:param 	name=\"poName\" 	value=\""+getClassInstanseName()+"\" />"+
				"\n 	</a4j:include>");
				
		xHtmlMaintFormFileContent.append("\n </html> ");
		
		String xhtmlFullPath = this.getXHtmlPath()+ File.separator +xHtmlMaintainanceFileName ; 
		File file=new File(xhtmlFullPath);
	    if( !file.exists())
	    	this.writeClass(xHtmlMaintFormFileContent.toString(), xhtmlFullPath);
		
		
	}
	private String getxHtmlMaintFormFileName()
	{
		return getClassInstanseName()+"MaintForm.xhtml" ; 
	}

	private String getxHtmlMaintainanceFileName()
	{
		return getClassInstanseName()+"Maintainance.xhtml" ; 
	}
	
	private String getClassInstanseName()
	{
		String first = this.mainClassName.substring(0,1).toLowerCase();
		return first +this.mainClassName.substring(1);
	}


	private void generateValidationClass()
	{
		String validtorClassName = this.getChangeValidatorClassName();
		String autoValidtorClassName = "_" +validtorClassName;
		StringBuffer autoValidationClass = new StringBuffer("package "+this.getPackageName()+".validators.auto;");
		autoValidationClass.append( "\n\nimport com.implex.database.AttributeChangeValidator; " );
		autoValidationClass.append( "\nimport com.implex.database.PersistantObject;");
		autoValidationClass.append( "\nimport com.implex.database.map.SecUsrDta;");
		
		autoValidationClass.append("\nimport com.implex.database.ValidationResult;"); 
		autoValidationClass.append("\nimport "+this.getPackageName()+"."+this.mainClassName+";"); 
		
		autoValidationClass.append("\n\npublic abstract class " + autoValidtorClassName + " extends AttributeChangeValidator {") ;
		autoValidationClass.append("\n/* Dynamically Generated Mapping Class ") ;
		autoValidationClass.append("\n * By : Shawky Foda. Please Never Update This Class.. Only regenerate... ") ;
		autoValidationClass.append("\n */" );
		
		autoValidationClass.append("\n\n\tprotected SecUsrDta secUser = null ; ");
		autoValidationClass.append("\n\tprotected PersistantObject po = null; ");
		autoValidationClass.append( "\n\tprotected String key = null; ");
	
		// Constructor 			
		autoValidationClass .append("\n\n\tpublic " + autoValidtorClassName + "(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key){") ;
		autoValidationClass .append( "\n\t\t this.secUser = pm_secUserData ;");
		autoValidationClass .append( "\n\t\t this.po = pm_po ; ");
		autoValidationClass .append( "\n\t\t this.key = pm_key;");
			
		autoValidationClass .append( "\n\t}" );
		autoValidationClass .append( "\n\t@Override" );
		
		autoValidationClass .append( "\n\tpublic ValidationResult validate(Object newValue) {" );
		autoValidationClass .append( "\n\t\t ValidationResult result = new ValidationResult();" );
		
		for (int i = 1 ; i< this.columnNames.length ; i++)
		{
			String methodSignature = "validate"+this.getNamingConvention(columnNames[i], "")+"( newValue)";
			autoValidationClass .append( "\n\t\t "+((i==1)? "" : "else " )+"if (this.key.equals("+this.mainClassName+"."+columnNames[i]+")) " );
			autoValidationClass .append( "\n\t\t {" );
			autoValidationClass .append( "\n\t\t\t result  = "+methodSignature+";" );
			autoValidationClass .append( "\n\t\t }" );
		
		}
		autoValidationClass .append( "\n\t\t return result ;" );
		autoValidationClass .append( "\n } " );
		for (int i = 1 ; i< this.columnNames.length ; i++)
		{
			autoValidationClass .append( "\n\tpublic abstract ValidationResult validate"+this.getNamingConvention(columnNames[i], "")+"( Object newValue);" );
		}
		
		autoValidationClass .append( "\n } " );
		
		StringBuffer validationClassTxt = new StringBuffer("package "+this.getPackageName()+".validators;");
		

		validationClassTxt.append( "\nimport com.implex.database.PersistantObject;");
		validationClassTxt.append( "\nimport com.implex.database.map.SecUsrDta;");
		
		validationClassTxt.append("\nimport com.implex.database.ValidationResult;"); 
		validationClassTxt.append("\nimport "+this.getPackageName()+".validators.auto."+autoValidtorClassName+";"); 
		
		validationClassTxt.append("\n\npublic class "+validtorClassName+" extends "+autoValidtorClassName ) ;
		validationClassTxt.append(" \n{ ");
		
		validationClassTxt.append("\n\t public "+validtorClassName+"(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key) ");
		validationClassTxt.append("\n\t{");
		validationClassTxt.append("\n\t\tsuper(pm_secUserData, pm_po, pm_key); " );
		validationClassTxt.append("\n\t}");
		
		for (int i = 1 ; i< this.columnNames.length ; i++)
		{
			validationClassTxt .append( "\n\tpublic ValidationResult validate"+this.getNamingConvention(columnNames[i], "")+"( Object newValue)" );
			validationClassTxt .append( "\n{" );
			validationClassTxt .append( "\n\t// TODO Return Your ValidationResult (Status + Message) ... " );
			validationClassTxt .append( "\n\treturn null;" );
			validationClassTxt .append( "\n}" );
		}
		
		validationClassTxt.append(" \n} ");
		
		String path = this.getSourceOutPath() + this.getPackagePathName() + "/validators/"; 
		String validationClassPath = path + validtorClassName + ".java";
		String autoFileName = path + "auto/" + autoValidtorClassName + ".java";
		
		File file=new File(validationClassPath);
	    if( !file.exists())
	    	this.writeClass(validationClassTxt.toString(), validationClassPath);
	    
		this.writeClass(autoValidationClass.toString(), autoFileName);
	}

	public void execute()
	{
		this.generateMapClasses();
		
		if (this.isGenerateValidationClass())
		{
			this.generateValidationClass( );
		}
		if (this.isGenerateSecurityHandlerClass())
		{
			this.generateTheSecurityHandlerClass();
		}
		if (this.isGenerateServiceClass())
		{
			this.generateTheServiceClass();
		}		
		if (this.isGenerateXhtmlMaintForm())
		{
			try {
				this.generateXhtmlMaintForm();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (this.isGenerateXhtmlMaintainance())
		{
			try {
				this.generateXhtmlMaintainance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (this.isAddAsTreeNode())
		{
			try {
				this.addToTree();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (this.isGenerateMapForChildren())
		{
			try {
				MapGenerator mgForChildren = new MapGenerator(this.getDbServices());
				mgForChildren.setAppEnvironment(this.getAppEnvironment());
				mgForChildren.setGenerateValidationClass(this.isGenerateValidationClass());
				mgForChildren.setGenerateServiceClass(this.isGenerateServiceClass());
				mgForChildren.setGenerateXhtmlMaintForm(this.isGenerateXhtmlMaintForm());
				mgForChildren.setGenerateXhtmlMaintainance(this.isGenerateXhtmlMaintainance());
				mgForChildren.setSourceOutPath(this.getSourceOutPath());
				mgForChildren.setPackageName(this.getPackageName());
				mgForChildren.setXHtmlPath(this.getXHtmlPath());

				ArrayList<DbForignKeyArray> afka= this.getTableMaintMaster().getAllForignKeysArrays();
				for (DbForignKeyArray fka : afka)
				{
					if (fka.isGenerateMapForChildTable())
					{
						mgForChildren.setTableOwner(fka.getDbForignKeyArray()[0].getChildTableOwner());
						mgForChildren.setTableName(fka.getTableName());
					
						mgForChildren.execute();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws MalformedURLException, Exception 
	{
		MapGenerator mg = new MapGenerator(null);
		mg.getMapCalculatedVariables().add(new MapCalculatedVariable("abc", com.smartValue.database.PersistantObject.class)) ;
		mg.setGenerateValidationClass(false);
		mg.setGenerateServiceClass(false);
		mg.setGenerateXhtmlMaintForm(false);
		mg.setGenerateXhtmlMaintainance(false);
		mg.setGenerateSecurityHandlerClass(false);
		mg.setAddAsTreeNode(false);
		mg.setTreeNodeParent("HR");// the parent tree Node node XHTML page
		mg.setAddToFavorite(false);
		
		mg.setXhtmlFolder("test");
		mg.setSourceOutPath("D:\\SmartValue\\Sources\\App\\SmartTool\\src\\");
		mg.setTableOwner("APIGEE") ;
		mg.setPackageName("com.masterworks.httpclient.map");
		mg.setXHtmlPath("D:\\SmartValue\\Sources\\App\\SmartTool\\WebContent\\"+mg.xhtmlFolder);
		//mg.setDbService(ApplicationContext.getClientModuleServicesContainer(mg.connName, 1).getDbServices());
		mg.setTableName("HTTP_REQUEST");
	
		mg.execute();
	}


	public String getNamingConvention(String pm_name, String pm_type){
		String newName = "";
		boolean endsWith_ = pm_name.endsWith("_");
 		StringTokenizer tok = new StringTokenizer(pm_name,"_");
		String token = "" , firstChar = "";
		if(pm_type.equals("attribute"))
			newName = tok.nextToken().toLowerCase(); 
		while(tok.hasMoreTokens()){
			token = tok.nextToken().toLowerCase();
			firstChar = (token.substring(0, 1)).toUpperCase();
			newName += firstChar + token.substring(1);			
		}
		return newName + ((endsWith_) ? "_" : "");
	}
	
	public void writeClass(String pm_fileContents, String pm_fileName){

		MessagesCommnuicatorService msc = null ; 
		try{
		msc = ApplicationContext.generateModuleServicesContainer(this.connName , 1).getMessageCommnunicatorService() ;			}
		catch(Exception e ){}
		try{
		    FileWriter fstream = new FileWriter(pm_fileName);
	        BufferedWriter out = new BufferedWriter(fstream);
		    out.write(pm_fileContents);
		    StringBuffer sb = new StringBuffer(pm_fileName) ;
		    sb.append("\n  file has been created succefully ....") ; 
		      
		    out.close();
		    //ApplicationContext.sendMessageToUser(sb.toString());
		    }catch (Exception e){
		      System.err.println("Error: " + e.getMessage());
		      //ApplicationContext.sendMessageToUser("Error: " + e.getMessage()); 
		    }
	}

	
	private String getChangeValidatorClassName()  
	{
		return mainClassName+"ChangeValidator" ; 
	}
	
	public void setSourceOutPath(String outPath)
	{
		this.sourceOutPath = outPath;
	}
	public String getSourceOutPath()
	{
		return sourceOutPath;
	}
	public void setGenerateChilren(boolean generateChilren)
	{
		this.generateChilren = generateChilren;
	}
	public boolean isGenerateChilren()
	{
		return generateChilren;
	}
	public void setGenerateValidationClass(boolean generateValidationClass)
	{
		this.generateValidationClass = generateValidationClass;
	}
	public boolean isGenerateValidationClass()
	{
		return generateValidationClass;
	}

	public void setGenerateXhtmlMaintForm(boolean pm_generateXhtmlMaintForm)
	{
		this.generateXhtmlMaintForm = pm_generateXhtmlMaintForm;
	}
	public boolean isGenerateXhtmlMaintForm()
	{
		return generateXhtmlMaintForm;
	}

	public void setGenerateXhtmlMaintainance(boolean generateXhtmlMaintainance) {
		this.generateXhtmlMaintainance = generateXhtmlMaintainance;
	}

	public boolean isGenerateXhtmlMaintainance() {
		return generateXhtmlMaintainance;
	}

	public void setPackageName(String packageName)
	{
		this.packageName = packageName;
	}


	public String getPackageName()
	{
		return packageName;
	}


	public void setXHtmlPath(String xHtmlPath)
	{
		this.xHtmlPath = xHtmlPath;
	}


	public String getXHtmlPath()
	{
		return xHtmlPath;
	}


	public void setTableName(String tableName) throws Exception
	{
		this.tableName = tableName;
		this.refreshTableMaintMaster();
	}


	private void refreshTableMaintMaster() throws Exception {
		this.tableMaintMaster = this.calcTableMaintMaster();
		
	}

	public String getTableName()
	{
		return tableName;
	}


	public void setGenerateServiceClass(boolean generateServiceClass)
	{
		this.generateServiceClass = generateServiceClass;
	}


	public boolean isGenerateServiceClass()
	{
		return generateServiceClass;
	}

	public String getMessage() {
		return message;
	}

	public void setAddAsTreeNode(boolean addAsTreeNode) {
		this.addAsTreeNode = addAsTreeNode;
	}

	public boolean isAddAsTreeNode() {
		return addAsTreeNode;
	}

	public void setGenerateSecurityHandlerClass(boolean generateSecurityHandlerClass) {
		this.generateSecurityHandlerClass = generateSecurityHandlerClass;
	}

	public boolean isGenerateSecurityHandlerClass() {
		return generateSecurityHandlerClass;
	}

	public void setTreeNodeParent(String treeNodeParent) {
		this.treeNodeParent = treeNodeParent;
	}

	public String getTreeNodeParent() {
		return treeNodeParent;
	}

	public void setAddToFavorite(boolean addToFavorite) {
		this.addToFavorite = addToFavorite;
	}

	public boolean isAddToFavorite() {
		return addToFavorite;
	}

	public void setAppEnvironment(String appEnvironment) {
		this.appEnvironment = appEnvironment;
	}

	public String getAppEnvironment() {
		return appEnvironment;
	}

	public void setMapCalculatedVariables(ArrayList<MapCalculatedVariable> calculatedVariables) {
		this.mapCalculatedVariables = calculatedVariables;
	}

	public ArrayList<MapCalculatedVariable> getMapCalculatedVariables() {
		return mapCalculatedVariables;
	}


	public CodeGeneratorView getCodeGeneratorView() {
		return codeGeneratorView;
	}
	public void addVarFromCodeGeneratedView()
	{
		MapCalculatedVariable xx = this.getCodeGeneratorView().getMapCalculatedVariable() ; 
		MapCalculatedVariable mcv = new MapCalculatedVariable ( "" ,PersistantObject.class ) ;
		mcv.setLazyInitialized(xx.isLazyInitialized());
		try {
			mcv.clone(xx);
			this.getMapCalculatedVariables().add(mcv);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	@Override
	public void afterModuleRemoved() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeModuleRemoved() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DbTable getDbTable() {
		// TODO Auto-generated method stub
		return new DbTable(TableMaintMaster.CDB_SCHEMA_OWNER , "TASKS" , this.getDbServices());
	}

	@Override
	public String getOrginalQuery() {
		// TODO Auto-generated method stub
		return "Dummy Query";
	}

	@Override
	public Class getPersistentClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCanHaveMultipleInstances() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChanged() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setTableOwner(String tableOwner) {
		this.tableOwner = tableOwner;
		this.setPackageName("com.implex."+this.getTableOwner().toLowerCase()+".map");
		this.setXhtmlFolder(this.getTableOwner().toLowerCase());
	}

	public String getTableOwner() {
		return tableOwner;
	}
	
	private boolean generateMapForChildren ;
	public void setGenerateMapForChildren(boolean generateMapForChildren) {
		this.generateMapForChildren = generateMapForChildren;
	}

	public boolean isGenerateMapForChildren() {
		return generateMapForChildren;
	}
	

}
