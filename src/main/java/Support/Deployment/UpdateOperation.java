package Support.Deployment;

import java.io.File;

import com.sideinternational.sas.event.resource.debug.EventDbug9998;
import com.sideinternational.sas.util.xml.XmlNode;

import Support.TextFileLineEditor;

public class UpdateOperation extends BasicOperation implements Operation {

	private String[] oldString;
	private String[] newString;
	private int startLine;
	private int endLine;

	
	public UpdateOperation(File pm_source , File pm_dest, String[] pm_oldString , String[] pm_newString , int pm_startline , int pm_endLine)
	{
		super(pm_source , pm_dest);
		this.oldString = pm_oldString;
		this.newString = pm_newString;
		this.startLine = pm_startline;
		this.endLine = pm_endLine;
		
	}
	public UpdateOperation(XmlNode pm_updateXmlNode) {
		super(pm_updateXmlNode);
		this.startLine = Integer.parseInt(pm_updateXmlNode.getNodeValue("START_LINE"));
		this.endLine = Integer.parseInt(pm_updateXmlNode.getNodeValue("END_LINE"));
		XmlNode[] changes =  pm_updateXmlNode.findNodes("CHANGES");
		this.oldString = new String[changes.length];
		this.newString = new String[changes.length];
		
		for (int i = 0 ; i< changes.length ; i++)
		{
			this.oldString[i] = changes[i].getNodeValue("OLD_STRING");
			this.newString[i] = changes[i].getNodeValue("NEW_STRING");
		}
		
		// TODO Auto-generated constructor stub
	}
	
	public OperationResult execute() throws Exception 
	{
		OperationResult or = new OperationResult();
		TextFileLineEditor tfe = new TextFileLineEditor(this.source , this.destination);
		new EventDbug9998("+++++++++++++++++++++Start Update Operation ["+this.description+"]++++++++++++++++++++");
		tfe.replace(this.oldString, this.newString, this.startLine, this.endLine);
		new EventDbug9998("++++++++++++++++++++++End  Update Operation ["+this.description+"]+++++++++++++++++++++"); 


		return or;
	}

}
