package Support.Deployment;

import java.io.File;

import com.sideinternational.sas.util.xml.XmlNode;

public class BasicOperation {
	 
	File destination;
	File source;
	String description;
	
	public BasicOperation(File pm_source , File pm_destination)
	{
		this.destination = pm_destination;
		this.source = pm_destination; 
	}
	public BasicOperation(XmlNode operationNode)
	{
		this.source = new File( operationNode.getNodeValue("SOURCE"));
		this.destination = new File (operationNode.getNodeValue("DESTINATION"));
		this.description = operationNode.getNodeValue("DESCRIPTION");
		
	}

	
	public File getDestination() {
		return destination;
	}
	public File getSource() {
		return source;
	}
	public String getDescription() {
		return description;
	}
}
