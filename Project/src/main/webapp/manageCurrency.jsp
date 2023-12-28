<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="project.model.Currency"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Manage Currency</title>
</head>
<body>
	<h2>Create New Currency</h2>
	<form action="manageCurrency" method="post">
		Currency Name: <input type="text" name="currencyName"><br>
		Total Cap Amount: <input type="number" name="totalCapAmount"><br>
		Weekly Cap Amount: <input type="number" name="weeklyCapAmount"><br>
		Is Weekly Cap: <input type="checkbox" name="isWeeklyCap"><br>
		Is Discontinued: <input type="checkbox" name="isDiscontinued"><br>
		<input type="submit" value="Create">
	</form>

	<h2>Get Currency by Name</h2>
	<form action="manageCurrency" method="get">
		Currency Name: <input type="text" name="currencyName"><br>
		<input type="submit" value="Search">
	</form>

	<hr>

	<%
	String action = (String) request.getAttribute("action");
	Currency currency = (Currency) request.getAttribute("currency");
	if ("create".equals(action) && currency != null) {
		out.println("<h3>New Currency Created:</h3>");
		// Display created currency details
	} else if ("get".equals(action) && currency != null) {
		out.println("<h3>Currency Details:</h3>");
		// Display retrieved currency details
	}
	%>


	<%
	if (request.getAttribute("currency") != null) {
		Currency currencyTemp = (Currency) request.getAttribute("currency");
		request.setAttribute("isDiscontinued", currencyTemp.isDiscontinued());
		request.setAttribute("isWeeklyCap", currencyTemp.isWeeklyCap());
	%>
	<table border="1">
		<tr>
			<th>CurrencyName</th>
			<th>TotalCapAmount</th>
			<th>WeeklyCapAmount</th>
			<th>isWeeklyCap</th>
			<th>isDiscontinued</th>
		</tr>
		<tr>
			<td><c:out value="${currency.currencyName}" /></td>
			<td><c:out value="${currency.totalCapAmount}" /></td>
			<td><c:out value="${currency.weeklyCapAmount}" /></td>
			<td><c:out value="${isDiscontinued}" /></td>
			<td><c:out value="${isWeeklyCap}" /></td>
		</tr>
	</table>
	<%
	}
	%>
</body>
</html>
