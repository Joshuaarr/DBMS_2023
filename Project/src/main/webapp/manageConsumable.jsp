<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Create Consumable</title>
</head>
<body>

	<h2>Create Consumable</h2>
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

	<form action="manageConsumable" method="post">
		<input type="hidden" name="action" value="create"> <label
			for="itemName">Item Name:</label> <input type="text" id="itemName"
			name="itemName" required> <label for="maxStackSize">Max
			Stack Size:</label> <input type="number" id="maxStackSize"
			name="maxStackSize" required> <label for="vendorPrice">Vendor
			Price:</label> <input type="number" step="0.01" id="vendorPrice"
			name="vendorPrice" required> <label for="itemLevel">Item
			Level:</label> <input type="number" id="itemLevel" name="itemLevel" required>

		<label for="description">Description:</label> <input type="text"
			id="description" name="description" required> <input
			type="submit" value="Create Consumable">
	</form>

	<h2>Get Consumable by ID</h2>
	<%-- Display message or error if any --%>
	<%
	if (request.getAttribute("error") != null) {
	%>
	<p style="color: red;"><%=request.getAttribute("error")%></p>
	<%
	}
	%>

	<form action="manageConsumable" method="get">
		<input type="hidden" name="action" value="getById"> <label
			for="itemCategoryId">Item Category ID:</label> <input type="number"
			id="itemCategoryId" name="itemCategoryId" required> <input
			type="submit" value="Get Consumable">
	</form>

	<%
	if (request.getAttribute("consumable") != null) {
	%>
	<table border="1">
		<tr>
			<th>Item Category ID</th>
			<th>Item Name</th>
			<th>Max Stack Size</th>
			<th>Vendor Price</th>
			<th>Item Level</th>
			<th>Description</th>
		</tr>
		<%--  <c:set var="consumable" value="${consumable}" /> --%>
		<tr>
			<td><c:out value="${consumable.itemCategoryID}" /></td>
			<td><c:out value="${consumable.itemName}" /></td>
			<td><c:out value="${consumable.maxStackSize}" /></td>
			<td><c:out value="${consumable.vendorPrice}" /></td>
			<td><c:out value="${consumable.itemLevel}" /></td>
			<td><c:out value="${consumable.description}" /></td>
		</tr>
	</table>
	<%
	}
	%>

</body>
</html>
