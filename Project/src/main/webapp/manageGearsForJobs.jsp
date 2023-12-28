<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="project.model.GearsForJobs"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Manage Gears for Jobs</title>
</head>
<body>
	<h2>Create Gears for Jobs</h2>
	<form action="manageGearsForJobs" method="post">
		Item Category ID: <input type="number" name="itemCategoryID"><br>
		Job ID: <input type="number" name="jobID"><br> <input
			type="submit" value="Create">
	</form>

	<h2>Get Gears for Jobs</h2>
	<form action="manageGearsForJobs" method="get">
		Item Category ID: <input type="number" name="itemCategoryID"><br>
		<input type="submit" value="Search">
	</form>

	<hr>

	<%
	GearsForJobs gearsForJobs = (GearsForJobs) request.getAttribute("gearsForJobs");
	if (gearsForJobs != null) {
		out.println("<h3>Gears for Jobs Details:</h3>");
		out.println("<p>Item Category ID: " + gearsForJobs.getGear().getItemCategoryID() + "</p>");
		out.println("<p>Gear Defence Rating: " + gearsForJobs.getGear().getDefenceRating() + "</p>");
		out.println("<p>Job Name: " + gearsForJobs.getAssociatedJob().getJobName() + "</p>");
	}
	%>
</body>
</html>
