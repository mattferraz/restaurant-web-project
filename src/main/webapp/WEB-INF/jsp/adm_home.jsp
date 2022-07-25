<%--
  Created by IntelliJ IDEA.
  User: mateus
  Date: 7/19/22
  Time: 12:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <h1>MENU</h1>
    <a href="${pageContext.request.contextPath}/orders">View Orders</a><br/>
    <a href="${pageContext.request.contextPath}/customers">View Customers</a><br/>
    <a href="${pageContext.request.contextPath}/registerDish/form">Register Dish</a><br/>
    <a href="${pageContext.request.contextPath}/payment/register">Register Payment Methods</a>
</body>
</html>
