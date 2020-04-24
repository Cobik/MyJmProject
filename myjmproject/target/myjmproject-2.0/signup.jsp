
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sing Up</title>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form-style-8">

    <div class="form-style-8 header"> Please Sign Up!</div>

    <form method="post" action="/admin/signUp">
        <label for="name">User name
            <input class="input-field" type="text" id="name" name="name">
        </label>
        <label for="username">UserName
            <input class="input-field" type="text" id="username" name="username">
        </label>
        <label for="password">Password
            <input class="input-field" type="password" id="password" name="password">
        </label>
        <label for="role">Role
            <input class="input-field" type="text" id="role" name="role">
        </label>
        <input type="submit" value="Sign Up">
    </form>
</div>

</body>
</html>
