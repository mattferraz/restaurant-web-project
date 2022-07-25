<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mateus
  Date: 7/19/22
  Time: 6:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Orders</title>
</head>
<body>
<h1>My Orders</h1>
<a href="${pageContext.request.contextPath}/dishes">Back</a>
<table border="1">
    <tr>
        <th>Dish</th>
        <th>Date and Time</th>
        <th>Price</th>
        <th>Payment</th>
        <th>Note</th>
    </tr>
    <c:forEach items="${customerOrders}" var="customerOrder">
        <tr>
            <td>${customerOrder.dish.name}</td>
            <td>${customerOrder.dateTime.format(dateTimeFormatter)}</td>
            <td>${currencyFormatter.format(customerOrder.price)}</td>
            <td>${customerOrder.paymentMethod.name}</td>
            <td>${customerOrder.note}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
