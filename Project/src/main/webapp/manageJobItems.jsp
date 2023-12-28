<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="project.model.Character"%>
<%@ page import="project.model.Job"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Manage Job Items</title>
</head>
<body>

	<h2>Create Job Items Record</h2>

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

	<form action="manageJobItems" method="post">

		<%
		if (request.getAttribute("characters") != null) {
		%>
		<label for="characterID">Character:</label> <select id="characterID"
			name="characterID">
			<%
			for (Character character : (List<Character>) request.getAttribute("characters")) {
			%>
			<option value="<%=character.getCharacterID()%>"><%=character.getFirstName()%>
				<%=character.getLastName()%></option>
			<%
			}
			%>
		</select>
		<%
		}
		%>


		<%
		if (request.getAttribute("jobs") != null) {
		%>
		<label for="jobID">Job:</label> <select id="jobID" name="jobID">
			<%
			for (Job job : (List<Job>) request.getAttribute("jobs")) {
			%>
			<option value="<%=job.getJobID()%>"><%=job.getJobName()%></option>
			<%
			}
			%>
		</select>
		<%
		}
		%>

		<label for="exp">Experience Points:</label> <input type="number"
			id="exp" name="exp" required> <label for="levelID">Level
			ID:</label> <input type="number" id="levelID" name="levelID" required>

		<label for="isCurrentJob">Is Current Job:</label> <input
			type="checkbox" id="isCurrentJob" name="isCurrentJob"> <input
			type="submit" value="Create Job Items Record">
	</form>

</body>
</html>
