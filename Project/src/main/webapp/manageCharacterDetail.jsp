<%@page import="project.model.Account"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Manage Characters</title>
</head>
<body>

	<h2>Character Detail</h2>

	<%
	if (request.getAttribute("account") != null) {
		Account account = (Account) request.getAttribute("account");
	%>

	<div>
		<p>
			accountID:<%=account.getAccountID()%></p>
		<p>
			name:<%=account.getName()%></p>
		<p>
			email:<%=account.getEmail()%></p>
	</div>

	<%
	}
	%>

</body>
</html>
