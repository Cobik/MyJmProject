


<%@ page import="model.User" %>
<%@ page import="service.UserService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Users</title>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form-style-2">
<%
    UserService userService = new UserService();
    List<User> users =  userService.getAllUsers();
%>
<table>

    <tr>
        <th>Name</th>
        <th>Username</th>
    </tr>
    <% for (User user : users) {
    %>
    <tr>
        <th><%=user.getName()%>
        </th>
        <th><%=user.getUsername()%>
        </th>
    </tr>
    <%}%>
</table>
</div>
</body>
</html>
