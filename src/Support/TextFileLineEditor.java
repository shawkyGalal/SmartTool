package Support;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.crystaldecisions.report.web.event.br;
import com.sideinternational.sas.event.resource.debug.EventDbug9998;

public class TextFileLineEditor {
	private File m_inputFile ;
	private File m_outputFile;
	
	private boolean isSameFile ;
	
	public TextFileLineEditor(String inputFile , String outputFile) throws Exception  
	{
		m_inputFile = new java.io.File(inputFile );
		m_outputFile = new java.io.File(outputFile );
		this.isSameFile =  this.m_inputFile.equals(this.m_outputFile);
	}
	
	public TextFileLineEditor(File inputFile , File outputFile) throws Exception
	{
		m_inputFile = inputFile;
		m_outputFile = outputFile;
		this.isSameFile =  this.m_inputFile.equals(this.m_outputFile);
	}
	
	private String getModifiedContents(String[] oldString , String[] newString ,int sartLine , int endLine ) throws Exception
	{
		if (oldString.length != newString.length){ throw new IllegalArgumentException("Input Parameters size does not match");}
		StringBuffer  newFileContents= new StringBuffer();
		 try
	      {
			 //-Reading file Contents
			java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader (m_inputFile));
	        String line = "";
	        int lineNumber = 0;
	        while(( line = br.readLine() ) != null  )
	        {
	          lineNumber++;
	          String addLine = (lineNumber != 1)? "\n" : "";
	          if (lineNumber >=  sartLine && lineNumber < endLine)
	          {          
	        	  String modifiedLine = line;
	        	  for (int i = 0 ; i< oldString.length ; i++)
	        	  {
	        		  modifiedLine = Misc.repalceAll(modifiedLine, oldString[i], newString[i]);
	        		  //modifiedLine = modifiedLine.replaceAll(oldString[i], newString[i]); 
	        	  }
	        	  newFileContents.append( addLine + modifiedLine  );
	          }
	          else 
	          {		
	        	  newFileContents.append( addLine +line );
	          }
	        }
	        br.close();
		
	      }
		 catch (Exception e) 
		 { throw new Exception ("Unable to read " + m_inputFile.getAbsolutePath() + " Due to " + e.getMessage());}
		 return newFileContents.toString();
	}
		 
	public void replace(String oldString[] , String newString[] ,int sartLine , int endLine ) throws Exception
	{
		if (isSameFile)
		{
			String x = this.getModifiedContents(oldString, newString, sartLine, endLine);
			java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter (m_outputFile));
			bw.write(x);
			bw.close();
			return ;
		}
		java.io.BufferedReader br= null;
		java.io.BufferedWriter bw = null;
		 try
	      {
			 //-Reading file Contents
			br = new java.io.BufferedReader(new java.io.FileReader (m_inputFile));
			bw = new java.io.BufferedWriter(new java.io.FileWriter (m_outputFile));
	        String line = "";
	        int lineNumber = 0;
	        
	        while(( line = br.readLine() ) != null  )
	        {
	          lineNumber++;
	          //String addLine = (lineNumber == 1)? "" : "\n";
	          if (lineNumber >=  sartLine && lineNumber < endLine)
	          {          
	        	  String modifiedLine = line;
	        	  for (int i = 0 ; i< oldString.length ; i++)
	        	  {
	        		  //modifiedLine = modifiedLine.replaceAll(oldString[i], newString[i]);
	        		  modifiedLine = Misc.repalceAll(modifiedLine, oldString[i], newString[i]);
	        	  }
	        	  if (lineNumber != 1) bw.newLine();
	        	  bw.write(modifiedLine);
	          }
	          else 
	          {		
	        	  bw.write( line);
	          }
	        }
	        br.close();
	        bw.close();
        
	      }
	      catch (Exception e) { 
	    	  if (br!= null) br.close();
	    	  if (bw!= null) bw.close();
	    	  throw new Exception ("Unable to read " + m_inputFile.getAbsolutePath() + " Due to " + e.getMessage());
	    	  }
	      finally {
	    	  if (br!= null) br.close();
	    	  if (bw!= null) bw.close();
	      }

	}
	/**
	 * 
	 * @throws IOException
	 */
	  public void copy(boolean skipUnchanged)      throws Exception {
		if (!this.m_inputFile.exists())
			  {
				new EventDbug9998("FileCopy: " + "no such source file: " + this.m_inputFile.getAbsolutePath());
				System.exit(-1);
			  }
	    if (this.m_inputFile.isDirectory())
	    { String[] x = this.m_inputFile.list();
	    	for  (int i = 0 ; i< x.length ; i++)
	    	{	File fromFile2 = new File (this.m_inputFile.getAbsoluteFile() +File.separator + x[i]+ File.separator);
	    		File toFile2= new File (this.m_outputFile.getAbsoluteFile() +File.separator + x[i]);
	    		if (fromFile2.isDirectory())
	    		{
	    			toFile2.mkdir();
	    		}
   		   		TextFileLineEditor fe = new TextFileLineEditor(fromFile2 , toFile2);
	    		fe.copy(skipUnchanged);
	    	}
	    }
	    else 
	    {
		    String error = "";
	    	if (!this.m_inputFile.canRead())
		    {
			   error = "FileCopy: " + "source file is unreadable: " + this.m_inputFile.getAbsolutePath();
			   new EventDbug9998(error);
			   throw new IOException(error);
		    }
		
		    if (this.m_outputFile.isDirectory())
		    	this.m_outputFile = new File(this.m_outputFile, this.m_inputFile.getName());
		
		    if (this.m_outputFile.exists()) 
		    {
		      if ( ! skipUnchanged && !this.m_outputFile.canWrite())
		      {
		    	  String warnning = "Warnnning : " + "destination file is unwriteable: " + this.m_outputFile.getAbsolutePath(); 
		    	  new EventDbug9998(warnning);		    	  
		    	  throw new IOException(warnning);  
		      }

		      if (skipUnchanged && (this.m_outputFile.lastModified() == this.m_inputFile.lastModified()) )
		      {
		    	  //System.out.println("Skiped : Destination File Date ["+this.m_inputFile.getAbsolutePath()+"]  =  Source File Date" + this.m_outputFile.getAbsolutePath());
		    	  return;
		      }
   
		    } 
		    else 
		    {
		      String parent = this.m_outputFile.getParent();
		      if (parent == null)
		        parent = System.getProperty("user.dir");
		      File dir = new File(parent);
		      error ="";
		      if (!dir.exists())
		      {
		    	error = "FileCopy: destination directory doesn't exist: " + parent;
		    	new EventDbug9998(error);
		    	throw new IOException(error);
		      }
		        
		      if (dir.isFile())
		      {
		    	  error = "FileCopy:  destination is not a directory: " + parent;
		    	  new EventDbug9998(error);
		    	  throw new IOException(error);
		    	  
		      }
		      if (!dir.canWrite())
		      {   error = "FileCopy: destination directory is unwriteable: " + parent;
		      	  new EventDbug9998(error);
		    	  throw new IOException(error);  
		      }
		       
		    }
	
		    FileInputStream from = null;
		    FileOutputStream to = null;
		    try {
		      from = new FileInputStream(this.m_inputFile);
		      to = new FileOutputStream(this.m_outputFile);
		      byte[] buffer = new byte[4096];
		      int bytesRead;
		      String msg = "Start Copying :" +this.m_inputFile.getAbsolutePath() + "   To: " +  this.m_outputFile.getAbsolutePath();
		      System.out.println(msg);
		      new EventDbug9998(msg);
	  		  
		      
	  		  long x = m_outputFile.lastModified();
	  		  while ((bytesRead = from.read(buffer)) != -1)
		        to.write(buffer, 0, bytesRead); // write
       

		    } finally {

		      if (from != null)
		        try {
		          from.close();
		        } catch (IOException e) {
		          ;
		        }
		      if (to != null)
		        try {
		          to.close();
			    	boolean ok = m_outputFile.setLastModified(m_inputFile.lastModified());
		        } catch (IOException e) {
		          ;
		        }
		    }
	  }
  }

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String inFile = "D:\\MyWork\\JavaWork\\Smart Value Products\\SmartTool\\WebContent\\";
		String outFile = "I:\\MyWork\\JavaWork\\Smart Value Products\\SmartTool\\WebContent\\";
		TextFileLineEditor x = new TextFileLineEditor(inFile,outFile);
		x.copy(true);
		//String oldString[] = {"$$LOCATION","xxx","yyyy"};
		//String newString[] = {"scdb-mapping/SasDomainMap.map.xml","",""};
		//x.replace(oldString , newString, 0, 200);


	}

}
