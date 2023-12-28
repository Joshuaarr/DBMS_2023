<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Manage Job Level</title>
</head>
<body>

	<h2>Create Job Level</h2>
	<%-- Display message or error if any --%>
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

	<form action="manageJobLevel" method="post">
		<label for="minExp">Minimum Experience Points:</label> <input
			type="number" id="minExp" name="minExp" required> <label
			for="maxExp">Maximum Experience Points:</label> <input type="number"
			id="maxExp" name="maxExp" required> <label for="isMaxLevel">Is
			Maximum Level:</label> <input type="checkbox" id="isMaxLevel"
			name="isMaxLevel"> <input type="submit"
			value="Create Job Level">
	</form>

</body>
</html>
