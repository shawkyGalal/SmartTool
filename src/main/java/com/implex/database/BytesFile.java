package com.implex.database;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BytesFile {

	private String Name;
	private String mime;
	private long length;
	private byte[] data;
	private String classId ;
	private String element ;
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public String getName() {
		return Name;
	}
	public static String getFilePath()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession)context.getExternalContext().getSession(true);
    	String contextPath = session.getServletContext().getRealPath("");
    	String path = contextPath +"\\uploadedFiles\\" ;
    	return path;
	}
	
	public void writeFile(String pm_fullPath )
	{
    	try {
    		FileOutputStream fos = new FileOutputStream(pm_fullPath);
    		fos.write(this.getData());
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
		
	}
	public void writeFile()
	{		
		this.writeFile(getFilePath() + this.getName()) ;
	}
	
	public void download()
	{
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
			response.setContentType(getMime());
			response.setHeader("Content-Disposition", " attachment; filename="+Name);
			OutputStream out = response.getOutputStream();	
			out.write(this.getData());
			out.flush();
			fc.responseComplete();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void setName(String name) {
		Name = name;
		int extDot = name.lastIndexOf('.');
		if(extDot > 0){
			String extension = name.substring(extDot +1);
			if("bmp".equals(extension))
			{
				mime="image/bmp";
				this.setElement("img") ;
				
			} 
			else if("jpg".equals(extension))
			{
				mime="image/jpeg";
				this.setElement("img") ;
			} 
			else if("gif".equals(extension))
			{
				mime="image/gif";
				this.setElement("img") ;
			} 
			else if("png".equals(extension))
			{
				mime="image/png";
				this.setElement("img") ;
			}
			else if("txt".equals(extension))
			{
				mime="text/plain";
				this.setElement("object");
			}
			else if("xlsx".equals(extension))
			{
				mime="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
				this.setElement("object");
			
			}
			else if("xls".equals(extension))
			{
				mime="application/msexcel";
				this.setElement("object");
			
			}
			else if("doc".equals(extension))
			{
				mime="application/msword";
				this.setElement("object");
				
			}
			else if("docx".equals(extension))
			{
				mime="application/vnd.openxmlformats-officedocument.wordprocessingml.document";
				this.setElement("object");
				
			}
			else if("pdf".equals(extension))
			{
				mime="application/pdf";
				this.setElement("object");
			}			
			else 
			{
				mime = "image/unknown";
			}
		}
	}
	public long getLength() {
		return length;
	}
	public void setLength(long length) {
		this.length = length;
	}
	
	public String getMime(){
		return mime;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getClassId() {
		return classId;
	}
	public void setElement(String element) {
		this.element = element;
	}
	public String getElement() {
		return element;
	}
}
