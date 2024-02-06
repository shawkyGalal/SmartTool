<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
    <%@ page import="com.google.cloud.bigquery.BigQuery , com.google.cloud.bigquery.BigQueryOptions
                  , com.google.cloud.bigquery.FieldValueList
                  , com.google.cloud.bigquery.Job 
                  , com.google.cloud.bigquery.JobId 
                  , com.google.cloud.bigquery.JobInfo 
                  , com.google.cloud.bigquery.QueryJobConfiguration 
                  , com.google.cloud.bigquery.QueryResponse 
                  , com.google.cloud.bigquery.TableResult
                  , com.google.auth.oauth2.GoogleCredentials
				  , com.google.auth.oauth2.ServiceAccountCredentials 
				  , com.google.cloud.bigquery.Field
				  , java.io.File
				  , java.io.FileInputStream
				  , java.io.IOException
                  , java.util.UUID" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Execute Google BigQuery</title>
</head>
<body>
<%

GoogleCredentials credentials;
File credentialsPath = new File("D:/SmartValue/Sources/app/SMARTVALUE_CONFIG_HOME/smartTool/smartvalue.epm.json");  // TODO: update to your key path.
FileInputStream serviceAccountStream = new FileInputStream(credentialsPath); 
credentials = ServiceAccountCredentials.fromStream(serviceAccountStream);


BigQuery bigQuery = BigQueryOptions.newBuilder().setCredentials(credentials).build().getService();

// implicit credential using system environmental variable GOOGLE_APPLICATION_CREDENTIALS to point to the json key file  
// BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
// [END bigquery_simple_app_client]
// [START bigquery_simple_app_query]
QueryJobConfiguration queryConfig =
    QueryJobConfiguration.newBuilder(
      "SELECT "
          + "CONCAT('https://stackoverflow.com/questions/', CAST(id as STRING)) as url, "
          + "view_count "
          + "FROM `bigquery-public-data.stackoverflow.posts_questions` "
          + "WHERE tags like '%google-bigquery%' "
          //+ " and 1<> 1"
          + "ORDER BY favorite_count DESC LIMIT 10")
        // Use standard SQL syntax for queries.
        // See: https://cloud.google.com/bigquery/sql-reference/
        .setUseLegacySql(false)
        .build();

// Create a job ID so that we can safely retry.
JobId jobId = JobId.of(UUID.randomUUID().toString());
Job queryJob = bigQuery.create(JobInfo.newBuilder(queryConfig).setJobId(jobId).build());

// Wait for the query to complete.
queryJob = queryJob.waitFor();

// Check for errors
if (queryJob == null) {
  throw new RuntimeException("Job no longer exists");
} else if (queryJob.getStatus().getError() != null) {
  // You can also look at queryJob.getStatus().getExecutionErrors() for all
  // errors, not just the latest one.
  throw new RuntimeException(queryJob.getStatus().getError().toString());
}
// [END bigquery_simple_app_query]

// [START bigquery_simple_app_print]
// Get the results.
QueryResponse qResponse = bigQuery.getQueryResults(jobId);

TableResult result = queryJob.getQueryResults();
out.print("<table border = 1 ><tr>");
for (Field f : result.getSchema().getFields())
{
	out.print("<td>"+ f.getName() + "</td>" ) ; 
}
out.print("</tr>");
// Print all pages of the results.
for (FieldValueList row : result.iterateAll()) {
	out.print("<tr>"); 
	for (Field f : result.getSchema().getFields())
	{
		out.print("<td>"+ row.get(f.getName()).getStringValue() + "</td>" )  ; 
	}
	out.print("</tr>");
  //String url = row.get("url").getStringValue();
  //long viewCount = row.get("view_count").getLongValue();
  //out.print("<br>url: "+url+" views: " + viewCount);
}
out.print("</table>");
%>
</body>
</html>