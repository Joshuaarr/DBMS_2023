<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="project.model.EquipmentBonus"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Manage Equipment Bonus</title>
</head>
<body>
	<h2>Create Equipment Bonus</h2>
	<form action="manageEquipmentBonus" method="post">
		Item Category ID: <input type="number" name="itemCategoryID"><br>
		Attribute Name: <select name="attributeName">
			<option value="Strength">Strength</option>
			<option value="Dexterity">Dexterity</option>
			<option value="Vitality">Vitality</option>
			<option value="Mind">Mind</option>
			<option value="Intelligence">Intelligence</option>
		</select><br> Attribute Bonus: <input type="number" name="attributeBonus"><br>
		<input type="submit" value="Create">
	</form>

	<hr>

	<h2>Get Equipment Bonus</h2>
	<form action="manageEquipmentBonus" method="get">
		Item Category ID: <input type="number" name="itemCategoryID"><br>
		Attribute Name: <select name="attributeName">
			<option value="Strength">Strength</option>
			<option value="Dexterity">Dexterity</option>
			<option value="Vitality">Vitality</option>
			<option value="Mind">Mind</option>
			<option value="Intelligence">Intelligence</option>
		</select><br> <input type="submit" value="Search">
	</form>

	<hr>

	<%
	EquipmentBonus bonus = (EquipmentBonus) request.getAttribute("equipmentBonus");
	String error = (String) request.getAttribute("error");
	if (bonus != null) {
		out.println("<h3>Equipment Bonus Details:</h3>");
		out.println("<p>Item Category ID: " + bonus.getEquipment().getItemCategoryID() + "</p>");
		out.println("<p>Attribute Name: " + bonus.getAttributeName() + "</p>");
		out.println("<p>Attribute Bonus: " + bonus.getAttributeBonus() + "</p>");
	} else if (error != null && !error.isEmpty()) {
		out.println("<p>Error: " + error + "</p>");
	}
	%>
</body>
</html>
