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
<h1>Meus Pedidos</h1>
<table border="1">
    <tr>
        <th>Prato</th>
        <th>Data e Horário</th>
        <th>Preço</th>
        <th>Pagamento</th>
        <th>Observação</th>
    </tr>
    <c:forEach items="${customerOrders}" var="customerOrder">
        <tr>
            <td>${customerOrder.dish.name}</td>
            <td>${customerOrder.dateTime.format(dateTimeFormatter)}</td>
            <td>${currencyFormatter.format(customerOrder.price)}</td>
            <td>${customerOrder.paymentMethod.description}</td>
            <td>${customerOrder.note}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
