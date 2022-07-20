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
    <title>Detalhes do Pedido</title>
</head>
<body>
<h1>Detalhes do Pedido</h1>
<section>
    <h4>ID: ${order.id}</h4>
    <h4>Data e Horário: ${order.dateTime.format(dateTimeFormatter)}</h4>
    <h4>Preço Total: ${currencyFormatter.format(order.price)}</h4>
    <h4>Forma de Pagamento: ${order.paymentMethod.description}</h4>
    <h4>Observação: ${order.note}</h4>
</section>
<h1>Cliente</h1>
<section>
    <h4>Nome: ${order.customer.name}</h4>
    <h4>Email: ${order.customer.email}</h4>
    <h4>CPF: ${order.customer.cpf}</h4>
    <h4>Telefone: ${order.customer.phoneNumber}</h4>
</section>
<h1>Prato</h1>
<section>
    <h4>Nome: ${order.dish.name}</h4>
    <h4>Preço: ${currencyFormatter.format(order.dish.price)}</h4>
    <h4>Descrição: ${order.dish.description}</h4>
</section>
</body>
</html>
