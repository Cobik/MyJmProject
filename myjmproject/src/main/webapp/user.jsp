<%--
  Created by IntelliJ IDEA.
  User: javidanhajizada
  Date: 4/24/20
  Time: 22:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>User</title>
    <link href="${pageContext.request.contextPath}/css/buttonstyle.css" rel="stylesheet" type="text/css">
</head>

<%
    String username = request.getParameter("username");
%>
<body>
<label><%=username%></label>


<a href="/logout"  methods="get" class="buttonRed">Exit</a>
</body>
</html>
