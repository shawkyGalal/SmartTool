<%@page import="com.smartValue.web.AppContext"%>
<%@page import="com.smartvalue.apigee.resourceManager.Renderer"%>
<%@page import="com.smartvalue.apigee.configuration.AppConfig"%>
<%@page import="com.smartvalue.apigee.configuration.Partner"%>
<%@page import="com.smartvalue.apigee.configuration.Customer"%>
<%@page import="com.smartvalue.apigee.configuration.infra.*"%>

<%@page import ="com.smartvalue.apigee.rest.schema.environment.Environment"%>
<%@page import ="com.smartvalue.apigee.rest.schema.organization.Organization"%>
<%@page import ="com.smartvalue.apigee.rest.schema.product.ProductsServices"%>
<%@page import ="com.smartvalue.apigee.rest.schema.proxy.Proxy"%>
<%@page import ="com.smartvalue.apigee.rest.schema.server.MPServer"%>
<%@page import ="com.smartvalue.apigee.rest.schema.server.Postgres"%>
<%@page import ="com.smartvalue.apigee.rest.schema.server.Router"%>
<%@page import ="com.smartvalue.apigee.rest.schema.server.Server"%>
<%@page import ="com.smartvalue.apigee.rest.schema.server.ServerServices"%>
<%@page import ="com.smartvalue.apigee.rest.schema.sharedFlow.SharedFlow"%>
<%@page import ="com.smartvalue.apigee.rest.schema.TargetServer"%>
<%@page import ="com.smartvalue.apigee.rest.schema.virtualHost.VirtualHost"%>
<%@page import ="com.smartvalue.apigee.configuration.infra.ManagementServer"%>
<%@page import ="java.util.*"%>
<%@page import ="java.io.InputStream"%>
<%@page import ="java.lang.reflect.Type"%>
<%@page import ="com.smartvalue.moj.clients.environments.JsonParser"%>
<%@page import="com.smartvalue.apigee.configuration.infra.Infra"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<%
 
	AppConfig ac = AppContext.getAppConfig(application);
	
	if (request.getParameter("submit") == null)
	{
	  String jsonData = ac.getFileContent();
	
	%>
	<script>

		document.addEventListener("DOMContentLoaded", function() {
	    const partnerSelect = document.getElementById("partnerSelect");
	    const customerSelect = document.getElementById("customerSelect");
	    const infraSelect = document.getElementById("infraSelect");
	    const jsonData = <%=jsonData%> ;

	    // Populate partner options
	    for (const index in jsonData.Partners) {
	        const option = document.createElement("option");
	        option.value = jsonData.Partners[index].Name;
	        option.textContent = jsonData.Partners[index].Name;
	        partnerSelect.appendChild(option);
	    }
	    var intialPartner = jsonData.Partners[0].Name ; 
	    var intialCustomer = jsonData.Partners[0].Customers[0].Name  ; 
	    var intialInfra = jsonData.Partners[0].Customers[0].Infras[0].Name ; 
	    populateCustomer (jsonData , intialPartner); 
	    populateInfra(jsonData , intialPartner , intialCustomer ) ; 
	 
    	// Populate customer options based on the selected partner
    	partnerSelect.addEventListener("change", function() {
	        const selectedPartner = partnerSelect.value;
	        populateCustomer(jsonData , selectedPartner) ;
	        var partner = jsonData.Partners.find(obj => obj.Name === selectedPartner);
	        populateCustomer (jsonData , partner.Name);
	        populateInfra(jsonData , selectedPartner , partner.Customers[0].Name ); 
	    });
	    
	    // Populate infrastructure options based on the selected customer
	    customerSelect.addEventListener("change", function() {
	        const selectedPartner = partnerSelect.value;
	        const selectedCustomer = customerSelect.value;
	        var partner = jsonData.Partners.find(obj => obj.Name === selectedPartner);
	        var customer = partner.Customers.find(obj => obj.Name === selectedCustomer);
	        populateInfra(jsonData , selectedPartner , selectedCustomer ) ;
	    });
	
	});
		
		function populateCustomer (jsonData , partnerName)
		{
			const partner = jsonData.Partners.find(obj => obj.Name === partnerName); 
			customerSelect.innerHTML = "";
	        for (const index in partner.Customers) {
	            const option = document.createElement("option");
	            option.value = partner.Customers[index].Name;
	            option.textContent = partner.Customers[index].Name;
	            customerSelect.appendChild(option);
	        }
		}
		
		function populateInfra(jsonData , selectedPartner , selectedCustomer )
		{
			const partner = jsonData.Partners.find(obj => obj.Name === selectedPartner);
			const customer = partner.Customers.find(obj => obj.Name === selectedCustomer); ; //jsonData.Partners[selectedPartner].Customers[selectedCustomer];
	        infraSelect.innerHTML = "";
	        for (const index in customer.Infras) {
	            const option = document.createElement("option");
	            option.value = customer.Infras[index].Name;
	            option.textContent = customer.Infras[index].Name;
	            infraSelect.appendChild(option);
	        }
		}
		
	</script>
	<form action="InfraSelector.jsp" method="post" >
		<label for="partnerSelect">Select Partner:</label>
		<select id="partnerSelect" name = "partnerSelect">
	        <!-- Add more partners here -->
	    </select>
	    
	    <br><br>
	    
	    <label for="customerSelect"  >Select Customer:</label>
	    <select id="customerSelect"  name = "customerSelect"  >
	        <!-- Options will be populated based on the selected partner -->
	    </select>
	    
	    <br><br>
	    
	    <label for="infraSelect">Select Infrastructure:</label>
	    <select id="infraSelect" name = "infraSelect" >
	        <!-- Options will be populated based on the selected customer -->
	    </select>
		<br><br>
	    <input type="hidden" id="refreshSessionInfo" name="refreshSessionInfo" value="true">
	    <input type="hidden" id="targetPage" name="targetPage" value="<%=request.getParameter("targetPage")%>">
	    
	    <button type="submit" name= "submit" id = "submit">Submit</button>
	    <br> OR 
	    <br> <a href= "../loginWithGoogle/authorize.jsp?googleCloud=true" >Access Your Apigee Orgs in Google Cloud</a>
	</form>
    
    
    <%
	}
	else if (request.getParameter("submit") != null)
    {
		String partnerSelect = request.getParameter("partnerSelect") ; 
		String customerSelect = request.getParameter("customerSelect")  ;
		String infraSelect = request.getParameter("infraSelect") ;
		Partner partnr =  (Partner) ac.getPartnerByName(partnerSelect) ; 
		Customer customer = partnr.getCustomerByName(customerSelect) ; 
		Infra infra = ac.getInfra(partnerSelect, customerSelect, infraSelect) ;
		ManagementServer ms = null ; 
		Region region0 = infra.getRegions().get(0) ; 
		ms = infra.getManagementServer(region0.getName()); // ManagementServer(infra) ;
		ms.setOnPremise(true); 
		AppContext.setApigeeManagementServer(session, ms) ; 
		session.setAttribute("infra", infra) ;
		String accessTokenSource = infra.getAccessTokenSource() ; 
		response.sendRedirect( (accessTokenSource !=null && accessTokenSource.equalsIgnoreCase(AppConfig.GoogleWebAppCredential)) ? "../loginWithGoogle/authorize.jsp":   "index.jsp" ) ;
		
    }
    
	
    %>
</html>