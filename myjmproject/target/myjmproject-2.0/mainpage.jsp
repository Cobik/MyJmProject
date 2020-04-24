<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ page import="model.User" %>
<%@ page import="service.UserService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Users</title>
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/buttonstyle.css" rel="stylesheet" type="text/css">

</head>
<body class="container-table100">
<%
    List<User> users = UserService.getInstance().getAllUsers();
%>


<table class="wrap-table100">

    <tr>
        <th class="column1">ID</th>
        <th class="column2">Name</th>
        <th class="column3">Username</th>
        <th class="column4">Password</th>
    </tr>
    <% for (User user : users) {%>
    <tr>
        <td class="column1"><%=user.getId()%></td>
        <td class="column2"><%=user.getName()%></td>
        <td class="column3"><%=user.getUsername()%></td>
        <td class="column4"><%=user.getPassword()%></td>

        <td class="column5">
            <a href="/admin/edit?id=<%=user.getId()%> " methods="get" class="button">Edit</a>
        </td>
        <td class="column6">
            <a href="/delete.jsp?id=<%=user.getId()%>" class="buttonRed">Delete</a>
        </td>
    </tr>
    <%}%>


</table>

    <a href="/admin/signUp" class="button">Add</a>
    <a href="/logout"  methods="get" class="buttonRed">Exit</a>

</body>
</html>



