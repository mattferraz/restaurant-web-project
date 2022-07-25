<%--
  Created by IntelliJ IDEA.
  User: mateus
  Date: 7/19/22
  Time: 12:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Dish</title>
</head>
<body>
<h1>New Dish</h1>
<form method="post" action="${pageContext.request.contextPath}/registerDish">
    Name: <input type="text" name="name" required><br/>
    Description: <input type="text" name="description" required><br/>
    Price: <input type="text" name="price" required><br/>
    <input type="submit" value="Register">
</form>
</body>
</html>
