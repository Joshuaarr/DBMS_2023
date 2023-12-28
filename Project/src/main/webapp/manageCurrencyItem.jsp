<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page
	import="project.model.CurrencyItem, project.model.Character, project.model.Currency"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Manage Currency Item</title>
</head>
<body>
	<h2>Create New Currency Item</h2>
	<form action="manageCurrencyItem" method="post">
		Character ID: <input type="number" name="characterID"><br>
		Currency Name: <input type="text" name="currencyName"><br>
		Total Amount: <input type="number" name="totalAmount"><br>
		Weekly Amount: <input type="number" name="weeklyAmount"><br>
		<input type="submit" value="Create">
	</form>

	<h2>Get Currency Item by Character ID and Currency Name</h2>
	<form action="manageCurrencyItem" method="get">
		Character ID: <input type="number" name="characterID"><br>
		Currency Name: <input type="text" name="currencyName"><br>
		<input type="submit" value="Search">
	</form>

	<hr>

	<%
	String action = (String) request.getAttribute("action");
	CurrencyItem currencyItem = (CurrencyItem) request.getAttribute("currencyItem");
	if ("create".equals(action) && currencyItem != null) {
		out.println("<h3>New Currency Item Created:</h3>");
		// Display created currency item details
	} else if ("get".equals(action) && currencyItem != null) {
		out.println("<h3>Currency Item Details:</h3>");
		// Display retrieved currency item details
	}
	%>


	<%
	if (request.getAttribute("currencyItem") != null) {
	%>
	<table border="1">
		<tr>
			<th>CharacterID</th>
			<th>CurrencyName</th>
			<th>TotalAmount</th>
			<th>WeeklyAmount</th>
		</tr>
		<tr>
			<td><c:out value="${currencyItem.character.characterID}" /></td>
			<td><c:out value="${currencyItem.currency.currencyName}" /></td>
			<td><c:out value="${currencyItem.totalAmount}" /></td>
			<td><c:out value="${currencyItem.weeklyAmount}" /></td>
		</tr>
	</table>
	<%
	}
	%>
</body>
</html>
