<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mateus
  Date: 7/18/22
  Time: 11:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu</title>
</head>
<body>
<h1>MENU</h1>
<a href="${pageContext.request.contextPath}/my/orders">See my orders</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
        <th>Operations</th>
    </tr>
<c:forEach items="${dishes}" var="dish">
    <tr>
        <td>${dish.id}</td>
        <td>${dish.name}</td>
        <td>${dish.description}</td>
        <td>${currencyFormatter.format(dish.price)}</td>
        <td><a href="${pageContext.request.contextPath}/order/${dish.id}/payment">Select</a></td>
    </tr>
</c:forEach>
</table>
</body>
</html>
