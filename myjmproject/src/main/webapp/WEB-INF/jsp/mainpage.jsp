<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>All Users</title>
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/buttonstyle.css" rel="stylesheet" type="text/css">

</head>
<body class="container-table100">


<table class="wrap-table100">
    <tr>
        <th class="column1">ID</th>
        <th class="column2">Name</th>
        <th class="column3">Username</th>
        <th class="column4">Password</th>
    </tr>
    <c:forEach items="${usersFromServer}" var="user">
        <tr>
            <td class="column1">${user.id}</td>
            <td class="column2">${user.name}</td>
            <td class="column3">${user.username}</td>
            <td class="column4">${user.password}</td>

            <td class="column5">
                <a href="${pageContext.request.contextPath}/admin/edit?id=${user.id} " methods="get"
                   class="button">Edit</a>
            </td>
            <td class="column6">
                <a href="${pageContext.request.contextPath}/admin/delete?id=${user.id}" class="buttonRed">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="/admin/signUp" class="button">Add</a>
<a href="/logout" methods="get" class="buttonRed">Exit</a>

</body>
</html>



