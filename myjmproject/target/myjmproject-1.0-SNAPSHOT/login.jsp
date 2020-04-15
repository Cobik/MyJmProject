<%--
  Created by IntelliJ IDEA.
  User: javidanhajizada
  Date: 4/12/20
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Login</title>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form-style-8">

    <div class="form-style-8 header"> Please Sign In!</div>

    <form method="post" action="/myjmproject_war/login">
        <label for="username">UserName
            <input class="input-field" type="text" id="username" name="username">
        </label>
        <label for="password">Password
            <input class="input-field" type="password" id="password" name="password">
        </label>
        <input type="submit" value="Sign In">
    </form>
</div>
</body>
</html>