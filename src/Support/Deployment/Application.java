/**
 * 
 */
package Support.Deployment;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author admin
 *
 */
public class Application {

	/**
	 * 
	 */
	public Application() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
				
		FileOperationsXmlDoc foxd = new FileOperationsXmlDoc("D:\\MyWork\\JavaWork\\Smart Value Products\\SmartTool\\Deploy_CLRT_2_Smart_Value_Projects.xml");
		List allOperations = foxd.getAllOperations();
		Iterator it = allOperations.iterator();
		while (it.hasNext())
		{	
			Operation op = (Operation)it.next();
			op.execute();
		}
	
	}

}
