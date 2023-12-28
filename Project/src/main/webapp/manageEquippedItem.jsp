<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="project.model.EquippedItem"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Manage Equipped Item</title>
</head>
<body>
	<h2>Create Equipped Item</h2>
	<form action="manageEquippedItem" method="post">
		Character ID: <input type="number" name="characterID"><br>
		Slot Name: <select name="slotName">
			<option value="MainHand">MainHand</option>
			<option value="Head">Head</option>
			<option value="Body">Body</option>
			<option value="Hands">Hands</option>
			<option value="Feet">Feet</option>
			<option value="OffHand">OffHand</option>
			<option value="Earring">Earring</option>
			<option value="Wrist">Wrist</option>
			<option value="Ring">Ring</option>
		</select><br> Slot ID: <input type="number" name="slotID"><br>
		<input type="submit" value="Create">
	</form>

	<h2>Get Equipped Item</h2>
	<form action="manageEquippedItem" method="get">
		Character ID: <input type="number" name="characterID"><br>
		Slot Name: <select name="slotName">
			<option value="MainHand">MainHand</option>
			<option value="Head">Head</option>
			<option value="Body">Body</option>
			<option value="Hands">Hands</option>
			<option value="Feet">Feet</option>
			<option value="OffHand">OffHand</option>
			<option value="Earring">Earring</option>
			<option value="Wrist">Wrist</option>
			<option value="Ring">Ring</option>
		</select><br> <input type="submit" value="Search">
	</form>

	<hr>

	<%
	EquippedItem equippedItem = (EquippedItem) request.getAttribute("equippedItem");
	String error = (String) request.getAttribute("error");
	if (equippedItem != null) {
		out.println("<h3>Equipped Item Details:</h3>");
		out.println("<p>Character ID: " + equippedItem.getCharacter().getCharacterID() + "</p>");
		out.println("<p>Slot Name: " + equippedItem.getSlotName() + "</p>");
		out.println("<p>Slot ID: " + equippedItem.getSlotID() + "</p>");
	} else if (error != null && !error.isEmpty()) {
		out.println("<p>Error: " + error + "</p>");
	}
	%>
</body>
</html>
