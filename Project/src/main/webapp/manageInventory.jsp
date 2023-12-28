<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="project.model.Inventory"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Manage Inventory</title>
</head>
<body>
	<h2>Create Inventory Item</h2>
	<form action="manageInventory" method="post">
		Character ID: <input type="number" name="characterID" required><br>
		Slot ID: <input type="number" name="slotID" required><br>
		Item Category Type: <select name="itemCategoryType">
			<option value="Gear">Gear</option>
			<option value="Weapon">Weapon</option>
		</select><br> Item Name: <input type="text" name="itemName"><br>
		Max Stack Size: <input type="number" name="maxStackSize"><br>
		Vendor Price: <input type="number" step="0.01" name="vendorPrice"><br>
		Stack Size: <input type="number" name="stackSize" required><br>
		<input type="submit" value="Create">
	</form>

	<h2>Retrieve Inventory Item</h2>
	<form action="manageInventory" method="get">
		Character ID: <input type="number" name="characterID" required><br>
		Slot ID: <input type="number" name="slotID" required><br>
		<input type="submit" value="Search">
	</form>

	<hr>

	<%
	Inventory inventory = (Inventory) request.getAttribute("inventory");
	if (inventory != null) {
		out.println("<h3>Inventory Details:</h3>");
		out.println("<p>Character ID: " + inventory.getCharacter().getCharacterID() + "</p>");
		out.println("<p>Slot ID: " + inventory.getSlotID() + "</p>");
		out.println("<p>Item Category ID: " + inventory.getItemCategory().getItemCategoryID() + "</p>");
		out.println("<p>Item Name: " + inventory.getItemCategory().getItemName() + "</p>");
		out.println("<p>Stack Size: " + inventory.getStackSize() + "</p>");
	}
	%>
</body>
</html>
