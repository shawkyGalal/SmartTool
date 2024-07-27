package com.smartValue.database;

import java.io.File;
import java.util.ArrayList;

public class BlobAttribute extends Attribute {

	public BlobAttribute(String pmKey, Object pmAttributeValue,
			PersistantObject pmParentPersistantObject) {
		super(pmKey, pmAttributeValue, pmParentPersistantObject);
		// TODO Auto-generated constructor stub
	}
	
	private AttributeFileUploader fileUploadBean ;
	
	public AttributeFileUploader getFileUploadBean() {
		if (fileUploadBean == null)
		{
			fileUploadBean = new AttributeFileUploader(this);
		}
		return fileUploadBean;
	}
	
	public void setValueFromFileUploader() throws Exception
	{
		ArrayList<File> uploadedFiles = this.getFileUploadBean().getPhysicalFiles();
		File f = null  ; 
		if (uploadedFiles != null && !uploadedFiles.isEmpty())
			f = uploadedFiles.get(0);
		this.setValue( f );
	}
	
	public File getValueAsFile() throws Exception
	{
		return (File)getValue();
	}
	
	public void setValueAsFile(File f)
	{
		this.setValue(f);
	}

	public BytesFile getFile()
	{
		ArrayList<BytesFile> files = this.getFileUploadBean().getFiles();
		if(!files.isEmpty())
			return files.get(0);
		return null;
	}
}
