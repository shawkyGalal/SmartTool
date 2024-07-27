/**
 * 
 */
package com.smartValue.database;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;


public class FileUploadBean{
	
	protected ArrayList<BytesFile> files = new ArrayList<BytesFile>();
	protected int uploadsAvailable = 5;
	private boolean autoUpload = true;
	private boolean useFlash = false;
	private String  fileDestination = System.getProperty("user.home");
	private String  acceptedTypes = "jpg, gif, png, bmp, xls , doc , docx, pdf" ;
	public int getSize() {
		if (getFiles().size()>0){
			return getFiles().size();
		}else 
		{
			return 0;
		}
	}

	public FileUploadBean() {
	}

	public void paint(OutputStream stream, Object object) throws IOException {
		stream.write(getFiles().get((Integer)object).getData());
	}
	public void listener(UploadEvent event) throws Exception{
	    UploadItem item = event.getUploadItem();
	    BytesFile file = new BytesFile();
	    file.setLength(item.getData().length);
	    file.setName(item.getFileName());
	    file.setData(item.getData());
	    files.add(file);
	    uploadsAvailable--;
	}  
	  
	public String clearUploadData() {
		files.clear();
		setUploadsAvailable(5);
		return null;
	}
	
	public long getTimeStamp(){
		return System.currentTimeMillis();
	}
	
	public ArrayList<BytesFile> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<BytesFile> files) { 
		this.files = files;
	}

	public int getUploadsAvailable() {
		return uploadsAvailable;
	}

	public void setUploadsAvailable(int uploadsAvailable) {
		this.uploadsAvailable = uploadsAvailable;
	}

	public boolean isAutoUpload() {
		return autoUpload;
	}

	public void setAutoUpload(boolean autoUpload) {
		this.autoUpload = autoUpload;
	}

	public boolean isUseFlash() {
		return useFlash;
	}

	public void setUseFlash(boolean useFlash) {
		this.useFlash = useFlash;
	}

	
	public ArrayList<java.io.File> getPhysicalFiles() throws Exception
	{
		ArrayList<java.io.File> physicalFiles = new ArrayList<java.io.File>();
		for(int i=0; i < files.size(); i++ )
		{
			BytesFile bf = files.get(i);
			java.io.File xlsFile = new java.io.File( this.getFileDestination() + File.separatorChar + bf.getName() );
			java.io.FileOutputStream fos = new java.io.FileOutputStream(xlsFile);
			
			fos.write( bf.getData() );
			
			fos.flush();
			fos.close();
			
			physicalFiles.add(xlsFile);
			
		}
		return physicalFiles;
	}

	public void setFileDestination(String fileDestination) {
		this.fileDestination = fileDestination;
	}

	public String getFileDestination() {
		return fileDestination;
	}

	public void setAcceptedTypes(String acceptedTypes) {
		this.acceptedTypes = acceptedTypes;
	}

	public String getAcceptedTypes() {
		return acceptedTypes;
	}

}
