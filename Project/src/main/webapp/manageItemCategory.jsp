<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="project.model.ItemCategory"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Manage Item Category</title>
</head>
<body>
	<h2>Create Item Category</h2>
	<form action="manageItemCategory" method="post">
		Item Name: <input type="text" name="itemName" required><br>
		Max Stack Size: <input type="number" name="maxStackSize" required><br>
		Vendor Price: <input type="number" step="0.01" name="vendorPrice"
			required><br> <input type="submit" value="Create">
	</form>

	<h2>Retrieve Item Category by ID</h2>
	<form action="manageItemCategory" method="get">
		Item Category ID: <input type="number" name="itemCategoryID" required><br>
		<input type="submit" value="Search">
	</form>

	<hr>

	<%
	ItemCategory itemCategory = (ItemCategory) request.getAttribute("itemCategory");
	String error = (String) request.getAttribute("error");
	if (itemCategory != null) {
		out.println("<h3>Item Category Details:</h3>");
		out.println("<p>Item Category ID: " + itemCategory.getItemCategoryID() + "</p>");
		out.println("<p>Item Name: " + itemCategory.getItemName() + "</p>");
		out.println("<p>Max Stack Size: " + itemCategory.getMaxStackSize() + "</p>");
		out.println("<p>Vendor Price: " + itemCategory.getVendorPrice() + "</p>");
	} else if (error != null && !error.isEmpty()) {
		out.println("<p>Error: " + error + "</p>");
	}
	%>
</body>
</html>
