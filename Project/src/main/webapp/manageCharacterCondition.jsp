<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Manage Characters</title>
</head>
<body>

	<h2>Character Search By Conditon</h2>


	<form action="manageCharacter" method="get">
		<input type="hidden" name="action" value="condition"> <label
			for="firstName">firstName:</label> <input type="text" id="firstName"
			name="firstName"> <label for="lastName">lastName:</label> <input
			type="text" id="lastName" name="lastName"> <label for="email">email:</label>
		<input type="text" id="email" name="email"> <input
			type="submit" value="Search">
	</form>


	<%
	if (request.getAttribute("characters") != null) {
	%>
	<table border="1">
		<tr>
			<th>AccountID</th>
			<th>FirstName</th>
			<th>LastName</th>
			<th>AccountName</th>
			<th>Email</th>
		</tr>
		<c:forEach var="item" items="${characters}">
			<tr>
				<td><c:out value="${item.account.accountID}" /></td>
				<td><c:out value="${item.firstName}" /></td>
				<td><c:out value="${item.lastName}" /></td>
				<td><c:out value="${item.account.name}" /></td>
				<td><c:out value="${item.account.email}" /></td>
				<td><a
					href="manageAccount?action=detail&accountID=${item.account.accountID}">Detail</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%
	}
	%>

</body>
</html>
