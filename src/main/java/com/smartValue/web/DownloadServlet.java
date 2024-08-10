package com.smartValue.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smartvalue.openapi.SDKGeneratoer;
import com.smartvalue.zip.ZipUtility;

@WebServlet("/SDK_Generator/sdk/moj_sdk")
public class DownloadServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		File zipFile = generateSdk(request); 

        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=\"download.zip\"");

        try (FileInputStream fis = new FileInputStream(zipFile);
             ServletOutputStream sos = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                sos.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	private File  generateSdk(HttpServletRequest request ) throws FileNotFoundException, IOException
	{
		String specsUrl = request.getParameter("specsUrl"); //"https://api.moj.gov.local/v1/najiz-services/portal/openapi.json" ;   // "https://raw.githubusercontent.com/openapitools/openapi-generator/master/modules/openapi-generator/src/test/resources/3_0/petstore.json" ;
		String lang = request.getParameter("lang"); // "java";
		String packageName = request.getParameter("packageName"); //"org.moj.najiz.sdk" ; 
		String validateSpecs = request.getParameter("validateSpecs"); 
		String outputDirectory = request.getHeader("outputDirectory")  + "_" +lang ;
		String zipFileName = outputDirectory+".zip" ; 
		
		SDKGeneratoer sdkg = new SDKGeneratoer() ;
		sdkg.setLang(lang);
		sdkg.setOutputDir(outputDirectory ) ; 
		sdkg.setPackageName(packageName);
		sdkg.setValidateSpecs(validateSpecs!=null && validateSpecs.equalsIgnoreCase("on")); 
		sdkg.generateSDK(specsUrl);
	
		//-- Start Compress the Generated SDK folder ----
		File outFile = new File(outputDirectory) ; 
		List<File> fileList = new ArrayList<File>() ; 
		fileList.add(outFile); 
		ZipUtility.zip(fileList, zipFileName);
		return new File(zipFileName) ; 
	}
}
