<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <title>Edit Users</title>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/buttonstyle.css" rel="stylesheet" type="text/css">

</head>
<body>

<div class="form-style-8">


    <form method="POST" action="/admin/edit">

        <label> Name:</label>
        <input type="text" name="name" value="${user.name}"/>
        <label> Username: </label>
        <input type="text" name="username" value="${user.username}"/>
        <label> Password:</label>
        <input type="text" name="password" value="${user.password}"/>
        <label> Role:</label>
        <input type="text" name="role" value="${user.role}"/>
        <input type="hidden" name="id" value="${id}"/>


        <input type="submit" value="Submit">


    </form>
</div>
</body>
</html>


