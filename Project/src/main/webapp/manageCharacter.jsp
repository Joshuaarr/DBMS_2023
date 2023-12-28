<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>Manage Characters</title>
</head>
<body>

	<h2>Create Character</h2>
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

	<form action="manageCharacter" method="post">
		<input type="hidden" name="action" value="create"> <label
			for="firstName">First Name:</label> <input type="text" id="firstName"
			name="firstName" required> <label for="lastName">Last
			Name:</label> <input type="text" id="lastName" name="lastName" required>

		<input type="submit" value="Create Character">
	</form>

	<h2>Search Character by ID</h2>
	<form action="manageCharacter" method="get">
		<input type="hidden" name="action" value="searchById"> <label
			for="characterId">Character ID:</label> <input type="text"
			id="characterId" name="characterId" required> <input
			type="submit" value="Search">
	</form>

	<h2>Search Characters by Last Name</h2>
	<form action="manageCharacter" method="get">
		<input type="hidden" name="action" value="searchByLastName"> <label
			for="lastName">Last Name:</label> <input type="text" id="lastName"
			name="lastName" required> <input type="submit" value="Search">
	</form>

	<h2>Characters</h2>

<!-- 通过ID查询 -->
	<%
	if (request.getAttribute("character") != null) {
	%>
	<table border="1">
		<tr>
			<th>Character ID</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Actions</th>
		</tr>
		<tr>
			<td><c:out value="${character.characterID}" /></td>
			<td><c:out value="${character.firstName}" /></td>
			<td><c:out value="${character.lastName}" /></td>
			<td><a
				href="manageCharacter?action=update&characterId=${character.characterID}">Update</a>
				<a
				href="manageCharacter?action=delete&characterId=${character.characterID}">Delete</a>
			</td>
		</tr>
	</table>
	<%
	}
	%>


<!-- 通过LastName查询 -->
	<%
	if (request.getAttribute("characters") != null) {
	%>
	<table border="1">
		<tr>
			<th>Character ID</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Actions</th>
		</tr>
		<c:forEach var="character" items="${characters}">
			<tr>
				<td><c:out value="${character.characterID}" /></td>
				<td><c:out value="${character.firstName}" /></td>
				<td><c:out value="${character.lastName}" /></td>
				<td><a
					href="manageCharacter?action=update&characterId=${character.characterID}">Update</a>
					<a
					href="manageCharacter?action=delete&characterId=${character.characterID}">Delete</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%
	}
	%>

</body>
</html>
