<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="project.model.Gear"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Manage Gear</title>
</head>
<body>
	<h2>Create Gear</h2>
	<form action="manageGear" method="post">
		Item Category ID: <input type="number" name="itemCategoryID"><br>
		Item Name: <input type="text" name="itemName"><br> Max
		Stack Size: <input type="number" name="maxStackSize"><br>
		Vendor Price: <input type="number" step="0.01" name="vendorPrice"><br>
		Item Level: <input type="number" name="itemLevel"><br>
		Required Level: <input type="number" name="requiredLevel"><br>
		Defence Rating: <input type="number" name="defenceRating"><br>
		Magic Defence Rating: <input type="number" name="magicDefenceRating"><br>
		<input type="submit" value="Create">
	</form>

	<h2>Get Gear</h2>
	<form action="manageGear" method="get">
		Item Category ID: <input type="number" name="itemCategoryID"><br>
		<input type="submit" value="Search">
	</form>

	<hr>

	<%
	Gear gear = (Gear) request.getAttribute("gear");
	if (gear != null) {
		out.println("<h3>Gear Details:</h3>");
		out.println("<p>Item Category ID: " + gear.getItemCategoryID() + "</p>");
		out.println("<p>Item Name: " + gear.getItemName() + "</p>");
		out.println("<p>Max Stack Size: " + gear.getMaxStackSize() + "</p>");
		out.println("<p>Vendor Price: " + gear.getVendorPrice() + "</p>");
		out.println("<p>Item Level: " + gear.getItemLevel() + "</p>");
		out.println("<p>Required Level: " + gear.getRequiredLevel() + "</p>");
		out.println("<p>Defence Rating: " + gear.getDefenceRating() + "</p>");
		out.println("<p>Magic Defence Rating: " + gear.getMagicDefenceRating() + "</p>");
	}
	%>
</body>
</html>
