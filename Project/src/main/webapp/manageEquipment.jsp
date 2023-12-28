<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="project.model.Equipment"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Manage Equipment</title>
</head>
<body>
	<h2>Create Equipment</h2>
	<form action="manageEquipment" method="post">
		Item Category ID: <input type="number" name="itemCategoryID"><br>
		Item Name: <input type="text" name="itemName"><br> Max
		Stack Size: <input type="number" name="maxStackSize"><br>
		Vendor Price: <input type="number" step="0.01" name="vendorPrice"><br>
		Item Level: <input type="number" name="itemLevel"><br>
		Required Level: <input type="number" name="requiredLevel"><br>
		<input type="submit" value="Create">
	</form>

	<hr>

	<%
	Equipment equipment = (Equipment) request.getAttribute("equipment");
	if (equipment != null) {
		out.println("<h3>Equipment Details:</h3>");
		out.println("<p>Item Category ID: " + equipment.getItemCategoryID() + "</p>");
		out.println("<p>Item Name: " + equipment.getItemName() + "</p>");
		out.println("<p>Max Stack Size: " + equipment.getMaxStackSize() + "</p>");
		out.println("<p>Vendor Price: " + equipment.getVendorPrice() + "</p>");
		out.println("<p>Item Level: " + equipment.getItemLevel() + "</p>");
		out.println("<p>Required Level: " + equipment.getRequiredLevel() + "</p>");
	}
	%>
</body>
</html>
