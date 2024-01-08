package Support.Deployment;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import Support.Configuration.SmartConfigXmlImpl;

import com.sideinternational.clrt.XmlRepParamDoc;
import com.sideinternational.sas.configuration.FileResource;
import com.sideinternational.sas.event.logging.Console;
import com.sideinternational.sas.util.xml.XmlDocument;
import com.sideinternational.sas.util.xml.XmlNode;

public class FileOperationsXmlDoc {
	private static final String COPY_NODE_NAME = "COPY";
	private static final String UPDATE_NODE_NAME = "UPDATE";
	private String deploymentFilePath;
	private XmlDocument xmlDeploymentDoc ;
	
	public FileOperationsXmlDoc(String pm_configFilePath) throws Exception
	{
	
		this.init(pm_configFilePath, FileResource.getResourceLocation(FileResource.CONFIGURATION_XML_SCHEMA));	
	}
	
	public FileOperationsXmlDoc(String pm_configFilePath , String pm_schemaFilePath) throws Exception
	{
		this.init(pm_configFilePath, pm_schemaFilePath);	
	}

	private void init(String pm_configFilePath, String pm_schemaFilePath) throws Exception
	{
		if (xmlDeploymentDoc != null)
		{
			Console.log("WARNING - The object is already initialized", SmartConfigXmlImpl.class);
			return;
		}
		Console.log("Loading the XML Deployment file...", XmlRepParamDoc.class);
		// With XML Validation
		//xmlDeploymentDoc = new XmlDocument(new FileInputStream(pm_configFilePath), pm_schemaFilePath);
		xmlDeploymentDoc = new XmlDocument(new FileInputStream(pm_configFilePath));
		setDeploymentFilePath(pm_configFilePath);
	}

	@SuppressWarnings("unchecked")
	public List  getAllOperations() {
		List operationList = new ArrayList();
		XmlNode[] copyOperationNodes= this.xmlDeploymentDoc.findNodes(COPY_NODE_NAME);
		for (int i = 0 ; i< copyOperationNodes.length ; i++)
		{
			operationList.add(new CopyOperation(copyOperationNodes[i]));
		}
		XmlNode[] updateOperationNodes= this.xmlDeploymentDoc.findNodes(UPDATE_NODE_NAME);
		for (int i = 0 ; i< updateOperationNodes.length ; i++)
		{
			operationList.add(new UpdateOperation(updateOperationNodes[i]));
		}
		return operationList;
	}
	
	
	public boolean hasNext()
	{
		//TODO 
		return true;
	}

	public Operation getNextOperation() {
		// TODO Auto-generated method stub
		
		return null;
	}

	public void setDeploymentFilePath(String deploymentFilePath) {
		this.deploymentFilePath = deploymentFilePath;
	}

	public String getDeploymentFilePath() {
		return deploymentFilePath;
	}
}
