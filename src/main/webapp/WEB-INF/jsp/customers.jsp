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
<h1>Customers</h1>
<a href="${pageContext.request.contextPath}/adm/home">Back</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>CPF</th>
        <th>Name</th>
        <th>Phone Number</th>
        <th>Email</th>
    </tr>
    <c:forEach items="${customers}" var="customer">
        <tr>
            <th>${customer.id}</th>
            <td>${customer.cpf}</td>
            <td>${customer.name}</td>
            <td>${customer.phoneNumber}</td>
            <td>${customer.email}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
