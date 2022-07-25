<%--
  Created by IntelliJ IDEA.
  User: mateus
  Date: 7/18/22
  Time: 12:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>The Restaurant</title>
</head>
<body>
<h1>The Restaurant</h1>
<h2>${msg}</h2>
<form method="post" action="${pageContext.request.contextPath}/validate/login">
    Email: <input type="text" name="email" required><br/>
    Password: <input type="password" name="password" required><br/>
    <input type="submit" value="Continue">
</form>
<a href="${pageContext.request.contextPath}/signup">Don't have an account? Sign Up!</a>
</body>
</html>
