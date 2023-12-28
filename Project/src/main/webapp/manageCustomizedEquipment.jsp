<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page
	import="project.model.CustomizedEquipment, project.model.Character, project.model.CustomizedEquipment.Quality"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Manage Customized Equipment</title>
</head>
<body>
	<h2>Create Customized Equipment</h2>
	<form action="manageCustomizedEquipment" method="post">
		Owner ID: <input type="number" name="ownerID"><br> Slot
		ID: <input type="number" name="slotID"><br> Dye Color: <input
			type="text" name="dyeColor"><br> Quality: <select
			name="quality">
			<option value="high">High</option>
			<option value="normal">Normal</option>
		</select><br> Condition: <input type="text" name="condition"><br>
		Maker ID: <input type="number" name="makerID"><br> <input
			type="submit" value="Create">
	</form>

	<h2>Get Customized Equipment by Owner ID and Slot ID</h2>
	<form action="manageCustomizedEquipment" method="get">
		Owner ID: <input type="number" name="ownerID"><br> Slot
		ID: <input type="number" name="slotID"><br> <input
			type="submit" value="Search">
	</form>

	<hr>

	<%
	CustomizedEquipment equipment = (CustomizedEquipment) request.getAttribute("equipment");
	if (equipment != null) {
		out.println("<h3>Customized Equipment Details:</h3>");
		out.println("<p>Owner ID: " + equipment.getOwner().getCharacterID() + "</p>");
		out.println("<p>Slot ID: " + equipment.getSlotID() + "</p>");
		out.println("<p>Dye Color: " + equipment.getDyeColor() + "</p>");
		out.println("<p>Quality: " + equipment.getQuality() + "</p>");
		out.println("<p>Condition: " + equipment.getCondition() + "</p>");
		Character maker = equipment.getMaker();
		if (maker != null) {
			out.println("<p>Made By (Character ID): " + maker.getCharacterID() + "</p>");
		} else {
			out.println("<p>Made By: N/A</p>");
		}
	} else {
		out.println("<p>No details available.</p>");
	}
	%>

</body>
</html>
