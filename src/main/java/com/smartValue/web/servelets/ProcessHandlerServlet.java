package com.smartValue.web.servelets;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedOutputStream;
import java.io.PrintWriter;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet("/process/{identifier}")
public class ProcessHandlerServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final ConcurrentHashMap<String, ProcessData> processes = new ConcurrentHashMap<>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String identifier = extractIdentifier(request);
	    ProcessData processData = processes.get(identifier);
	    if (processData != null) {
	        // Handle output request
	        response.setContentType("text/plain");
	        PrintWriter out = response.getWriter();
	        for (String line : processData.getOutputLines()) {
	            out.println(line);
	            out.flush();
	        }
	    } else {
	        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	    }
	}

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String identifier = extractIdentifier(request);
        ProcessData processData = processes.get(identifier);
        if (processData != null) {
            // Handle kill request
            processData.getProcess().destroy();
            processes.remove(identifier);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse  response) throws ServletException, IOException 
    {
    		        String  identifier = extractIdentifier(request);
    		        ProcessData processData = processes.get(identifier);
    		        if (processData != null) {
    		            String action = extractAction(request);
    		            if ("pause".equals(action)) {
    		                // Handle pause request
    		                processData.pause();
    		                response.setStatus(HttpServletResponse.SC_OK);
    		            } else if ("resume".equals(action)) {
    		                // Handle resume request
    		                processData.resume();
    		                response.setStatus(HttpServletResponse.SC_OK);
    		            } else {
    		                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    		            }
    		        } else {
    		            String command = request.getParameter("command");
    		            if (command != null) {
    		                // Handle start request
    		                identifier = startProcess(command);
    		                response.setStatus(HttpServletResponse.SC_CREATED);
    		                response.setHeader("Location", "/process/" + identifier);
    		            } else {
    		                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    		            }
    		        }
    		    }

    private String startProcess(String command) throws IOException {
        String identifier = UUID.randomUUID().toString();
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process process = processBuilder.start();
        ProcessData processData = new ProcessData(process);
        processes.put(identifier, processData);

        // Start a thread to write output directly to the output stream
        new Thread(new Runnable() {
            @Override
            public void run() {
            	 try (
            		 PipedOutputStream outputStream = processData.getOutputStream()) {
                     BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                     String line;
                     while ((line = reader.readLine()) != null) 
                     {
                         outputStream.write(line.getBytes());
                         outputStream.write('\n');
                     }
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
            }
        }).start() ;         
        return identifier;
    }
    
    private String extractIdentifier(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        Pattern pattern = Pattern.compile("/process/([^/]+)");
        Matcher matcher = pattern.matcher(pathInfo);

        if (matcher.matches()) {
            return matcher.group(1);
        }

        return null; // Identifier not found
    }

    private String extractAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        Pattern pattern = Pattern.compile("/process/[^/]+/action/([^/]+)");
        Matcher matcher = pattern.matcher(pathInfo);

        if (matcher.matches()) {
            return matcher.group(1);
        }

        return null; // Action not found
    }
}

// ... rest of the code