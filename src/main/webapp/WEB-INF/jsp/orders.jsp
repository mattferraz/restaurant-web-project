<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mateus
  Date: 7/18/22
  Time: 11:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<h1>Orders</h1>
<a href="${pageContext.request.contextPath}/adm/home">Back</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Customer</th>
        <th>Dish</th>
        <th>Date and Time</th>
        <th>Price</th>
        <th>Payment</th>
        <th>Operations</th>
    </tr>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td>${order.id}</td>
            <td>${order.user.name}</td>
            <td>${order.dish.name}</td>
            <td>${order.dateTime.format(dateTimeFormatter)}</td>
            <td>${currencyFormatter.format(order.price)}</td>
            <td>${order.paymentMethod.name}</td>
            <td><a href="/order/${order.id}">See Details</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
