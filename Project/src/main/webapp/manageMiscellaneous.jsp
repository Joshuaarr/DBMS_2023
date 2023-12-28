<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="project.model.Miscellaneous"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Manage Miscellaneous Items</title>
</head>
<body>
	<h2>Create Miscellaneous Item</h2>
	<form action="manageMiscellaneous" method="post">
		Item Name: <input type="text" name="itemName" required><br>
		Max Stack Size: <input type="number" name="maxStackSize" required><br>
		Vendor Price: <input type="number" step="0.01" name="vendorPrice"
			required><br> Description:
		<textarea name="description" required></textarea>
		<br> <input type="submit" value="Create">
	</form>

	<h2>Retrieve Miscellaneous Item by ID</h2>
	<form action="manageMiscellaneous" method="get">
		Item Category ID: <input type="number" name="itemCategoryID" required><br>
		<input type="submit" value="Search">
	</form>

	<hr>

	<%
	Miscellaneous miscellaneous = (Miscellaneous) request.getAttribute("miscellaneous");
	if (miscellaneous != null) {
		out.println("<h3>Miscellaneous Item Details:</h3>");
		out.println("<p>Item Category ID: " + miscellaneous.getItemCategoryID() + "</p>");
		out.println("<p>Item Name: " + miscellaneous.getItemName() + "</p>");
		out.println("<p>Max Stack Size: " + miscellaneous.getMaxStackSize() + "</p>");
		out.println("<p>Vendor Price: " + miscellaneous.getVendorPrice() + "</p>");
		out.println("<p>Description: " + miscellaneous.getDescription() + "</p>");
	}
	%>
</body>
</html>
