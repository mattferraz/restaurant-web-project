<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mateus
  Date: 7/19/22
  Time: 2:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customers</title>
</head>
<body>
<h1>Clientes</h1>
<a href="${pageContext.request.contextPath}/home">Voltar</a>
<table border="1">
    <tr>
        <th>CPF</th>
        <th>Nome</th>
        <th>Telefone</th>
        <th>Email</th>
    </tr>
    <c:forEach items="${customers}" var="customer">
        <tr>
            <td>${customer.cpf}</td>
            <td>${customer.name}</td>
            <td>${customer.phoneNumber}</td>
            <td>${customer.email}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
