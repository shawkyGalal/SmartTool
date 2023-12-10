package Support.Deployment;

import java.io.File;

import com.sideinternational.sas.event.resource.debug.EventDbug9998;
import com.sideinternational.sas.util.xml.XmlNode;

import Support.TextFileLineEditor;

public class CopyOperation extends BasicOperation implements Operation{

	boolean skipUnchanged = true;
	
	public CopyOperation(File pm_source , File pm_dest, boolean pm_skipUnchanged)
	{
		super(pm_source , pm_dest);
		this.skipUnchanged = pm_skipUnchanged;
		
	}
	public CopyOperation(XmlNode copyOperNode) {
		// TODO Auto-generated constructor stub
		super(copyOperNode);
		String skipUnchangedStr = copyOperNode.getNodeValue("SKIP_UNCHANGED");
		skipUnchanged = (skipUnchangedStr.equalsIgnoreCase("Y"));
	}
	
	public OperationResult execute() throws Exception 
	{
		OperationResult or = new OperationResult();
		TextFileLineEditor tfe = new TextFileLineEditor(this.source , this.destination);
		new EventDbug9998("+++++++++++++++++++++Start Copy Operation ["+this.description+"]++++++++++++++++++++");
		tfe.copy(skipUnchanged)	;
		new EventDbug9998("+++++++++++++++++++++End Copy Operation ["+this.description+"]+++++++++++++++++++++");		
		//--TODO
		return or;
	}

}
