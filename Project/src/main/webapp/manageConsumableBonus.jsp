<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Create ConsumableBonus</title>
</head>
<body>

	<h2>Create ConsumableBonus</h2>
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

	<form action="manageConsumableBonus" method="post">
		<input type="hidden" name="action" value="create"> <label
			for="itemCategoryID">Item Category ID:</label> <input type="number"
			id="itemCategoryID" name="itemCategoryID" required> <label
			for="attributeName">Attribute Name:</label> <select
			id="attributeName" name="attributeName" required>
			<option value="Strength">Strength</option>
			<option value="Dexterity">Dexterity</option>
			<option value="Vitality">Vitality</option>
			<option value="Mind">Mind</option>
			<option value="Intelligence">Intelligence</option>
		</select> <label for="attributeBonusPercent">Attribute Bonus Percent:</label> <input
			type="number" step="0.01" id="attributeBonusPercent"
			name="attributeBonusPercent" required> <label
			for="attributeBonusCap">Attribute Bonus Cap:</label> <input
			type="number" id="attributeBonusCap" name="attributeBonusCap"
			required> <input type="submit" value="Create ConsumableBonus">
	</form>

	<h2>Get ConsumableBonus by Item Category ID and Attribute Name</h2>

	<form action="manageConsumableBonus" method="get">
		<input type="hidden" name="action"
			value="getByItemCategoryIDAndAttributeName"> <label
			for="searchItemCategoryID">Item Category ID:</label> <input
			type="number" id="searchItemCategoryID" name="searchItemCategoryID"
			required> <label for="searchAttributeName">Attribute
			Name:</label> <select id="searchAttributeName" name="searchAttributeName"
			required>
			<option value="Strength">Strength</option>
			<option value="Dexterity">Dexterity</option>
			<option value="Vitality">Vitality</option>
			<option value="Mind">Mind</option>
			<option value="Intelligence">Intelligence</option>
		</select> <input type="submit" value="Get ConsumableBonus">
	</form>

	<%
		if (request.getAttribute("consumableBonus") != null) {
	%>
	<table border="1">
		<tr>
			<th>ItemCategoryID</th>
			<th>AttributeName</th>
			<th>AttributeBonusPercent</th>
			<th>AttributeBonusCap</th>
		</tr>
		<tr>
			<td><c:out value="${consumableBonus.consumable.itemCategoryID}" /></td>
			<td><c:out value="${consumableBonus.attributeName}" /></td>
			<td><c:out value="${consumableBonus.attributeBonusPercent}" /></td>
			<td><c:out value="${consumableBonus.attributeBonusCap}" /></td>
		</tr>
	</table>
	<%
	}
	%>

</body>
</html>
