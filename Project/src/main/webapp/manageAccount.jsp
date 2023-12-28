<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="project.model.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
...

<html>
<head>
    <title>Manage Account</title>
</head>
<body>

<h2>Create Account</h2>
<%-- Display message or error if any --%>
<% if (request.getAttribute("message") != null) { %>
    <p><%= request.getAttribute("message") %></p>
<% } %>
<% if (request.getAttribute("error") != null) { %>
    <p style="color: red;"><%= request.getAttribute("error") %></p>
<% } %>

<form action="manageAccount" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required>

    <label for="isActive">Is Active:</label>
    <input type="checkbox" id="isActive" name="isActive">

    <input type="submit" value="Create Account">
</form>

<hr>

<h2>Search Account 1</h2>
<form action="manageAccount" method="get">
    <input type="hidden" name="action" value="search">
    <label for="accountID">Account ID:</label>
    <input type="number" id="accountID" name="accountID" required>

    <input type="submit" value="Search Account">
</form>

<%-- Display search results --%>
<%
    project.model.Account account = (project.model.Account) request.getAttribute("account");
    if (account != null) {
%>
    <h3>Account Details</h3>
    <p>ID: <%= account.getAccountID() %></p>
    <p>Name: <%= account.getName() %></p>
    <p>Email: <%= account.getEmail() %></p>
    <p>Is Active: <%= account.isActive() ? "Yes" : "No" %></p>
<% } %>



</body>
</html>
