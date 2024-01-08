/**
 * 
 */
package com.implex.database;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;


/**
 * @author Shawky Foda
 *
 */
public class AttributeFileUploader extends FileUploadBean {
	

	protected int uploadsAvailable = 1;
	private Attribute attribute ; 
	public final static String COLUMN_FOR_FILE_NAME= "FILE_NAME" ; 
	
	
	public AttributeFileUploader(Attribute att) {
		this.attribute = att ;
	}
	
	public void listener(UploadEvent event) throws Exception{
		
		UploadItem item = event.getUploadItem();
	    BytesFile file = new BytesFile();
	    file.setLength(item.getData().length);
	    file.setName(item.getFileName());
	    file.setData(item.getData());
	    files.add(file);
	    uploadsAvailable--;
	    this.getAttribute().setValue(file);
	    Attribute fileNameAtt = this.getAttribute().getParentPersistantObject().getAttribute(COLUMN_FOR_FILE_NAME);
	    if (fileNameAtt != null)
	    {
	    fileNameAtt.setValue(item.getFileName());
	    }
	}  
	  

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public String clearUploadData() {
		files.clear();
		Attribute att = this.getAttribute() ;
		att.setValue(null);
		Attribute fileNameAtt = att.getParentPersistantObject().getAttribute(COLUMN_FOR_FILE_NAME) ;
		if (fileNameAtt != null )
		{
			fileNameAtt.setValue(null);
		}
		
		setUploadsAvailable(1);
		return null;
	}
}
