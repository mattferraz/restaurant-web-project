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
<h1>Pedidos</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Cliente</th>
        <th>Prato</th>
        <th>Data e Horário</th>
        <th>Preço</th>
        <th>Pagamento</th>
        <th>Operações</th>
    </tr>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td>${order.id}</td>
            <td>${order.customer.name}</td>
            <td>${order.dish.name}</td>
            <td>${order.dateTime.format(dateTimeFormatter)}</td>
            <td>${currencyFormatter.format(order.price)}</td>
            <td>${order.paymentMethod.description}</td>
            <td><a href="/order/${order.id}">Ver Detalhes</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
