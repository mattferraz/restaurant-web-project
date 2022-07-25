<%--
  Created by IntelliJ IDEA.
  User: mateus
  Date: 7/19/22
  Time: 4:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Details</title>
</head>
<body>
<h1>Order Details</h1>
<a href="${pageContext.request.contextPath}/orders">Back</a>
<section>
    <h4>ID: ${order.id}</h4>
    <h4>Date and Time: ${order.dateTime.format(dateTimeFormatter)}</h4>
    <h4>Final Price: ${currencyFormatter.format(order.price)}</h4>
    <h4>Payment Method: ${order.paymentMethod.name}</h4>
    <h4>Note: ${order.note}</h4>
</section>
<h1>Customer</h1>
<section>
    <h4>Name: ${order.user.name}</h4>
    <h4>Email: ${order.user.email}</h4>
    <h4>CPF: ${order.user.cpf}</h4>
    <h4>Phone Number: ${order.user.phoneNumber}</h4>
</section>
<h1>Dish</h1>
<section>
    <h4>Name: ${order.dish.name}</h4>
    <h4>Price: ${currencyFormatter.format(order.dish.price)}</h4>
    <h4>Description: ${order.dish.description}</h4>
</section>
</body>
</html>
