<%@ page import="service.UserService" %>
<%@ page import="model.User" %>
<%@ page import="java.sql.SQLException" %>
<html>
<head>
    <title>Edit Users</title>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/buttonstyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<%
    UserService userService = new UserService();
    Long id = Long.parseLong(request.getParameter("d"));
    User user = userService.getUserById(id);
%>
<div class="form-style-8">

<form method="POST" action="/myjmproject_war/main" >

    <label> Name:</label>
    <input type="text" name="name" value="<%= user.getName()%>"/>
    <label> Username: </label>
    <input type="text" name="username" value="<%= user.getUsername()%>"/>
    <label> Password:</label>
    <input type="text" name="password" value="<%= user.getPassword()%>"/>
    <input type="hidden" name="id" value="<%=id%>">

    <input type="submit" value="Submit">


</form>
</div>
</body>
</html>


