<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="project.model.Job"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Manage Jobs</title>
</head>
<body>

	<h2>Create a New Job</h2>
	<%
	if (request.getAttribute("message") != null) {
	%>
	<p><%=request.getAttribute("message")%></p>
	<%
	}
	%>
	<%
	if (request.getAttribute("error") != null) {
	%>
	<p style="color: red;"><%=request.getAttribute("error")%></p>
	<%
	}
	%>
	<form action="manageJob" method="post">
		<label for="jobName">Job Name:</label> <input type="text" id="jobName"
			name="jobName" required> <input type="submit"
			value="Create Job">
	</form>

	<hr>

	<!-- Search Job by ID -->
	<h2>Search a Job by ID</h2>
	<form action="manageJob" method="get">
		<input type="hidden" name="action" value="searchByID"> <label
			for="jobID">Search by Job ID:</label> <input type="text" id="jobID"
			name="jobID"> <input type="submit" value="Search">
	</form>

	<hr>

	<!-- Display Search Outcome -->
	<%
	Job searchResult = (Job) request.getAttribute("searchResult");
	if (searchResult != null) {
	%>
	<p>
		Found Job: ID =
		<%=searchResult.getJobID()%>, Name =
		<%=searchResult.getJobName()%></p>
	<%
	} else if ("searchByID".equals(request.getParameter("action"))) {
	%>
	<p>No job found for the given ID.</p>
	<%
	}
	%>

	<hr>

	<!-- Display all jobs -->
	<h2>All Jobs</h2>
	<%
	List<Job> jobs = (List<Job>) request.getAttribute("jobs");
	if (jobs != null && !jobs.isEmpty()) {
		for (Job job : jobs) {
	%>
	<p>
		ID:
		<%=job.getJobID()%>, Name:
		<%=job.getJobName()%></p>
	<%
	}
	} else {
	%>
	<p>No jobs available.</p>
	<%
	}
	%>
	
		<%
	if (request.getAttribute("job") != null) {
	%>
	<table border="1">
		<tr>
			<th>JobID</th>
			<th>JobName</th>
		</tr>
		<tr>
			<td><c:out value="${job.jobID}" /></td>
			<td><c:out value="${job.jobName}" /></td>
		</tr>
	</table>
	<%
	}
	%>

</body>
</html>
