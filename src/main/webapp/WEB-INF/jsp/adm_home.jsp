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
    <h1>O que você deseja fazer?</h1>
    <a href="${pageContext.request.contextPath}/orders">Visualizar Pedidos</a><br/>
    <a href="${pageContext.request.contextPath}/customers">Visualizar Clientes</a><br/>
    <a href="${pageContext.request.contextPath}/registerDish/form">Cadastrar Prato</a><br/>
    <a href="${pageContext.request.contextPath}/payment/register">Cadastrar Formas de Pagamento</a>
</body>
</html>
