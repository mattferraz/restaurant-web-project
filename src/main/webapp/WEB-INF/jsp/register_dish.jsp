<%--
  Created by IntelliJ IDEA.
  User: mateus
  Date: 7/19/22
  Time: 12:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Dish</title>
</head>
<body>
<h1>Registrar Prato Novo</h1>
<form method="post" action="${pageContext.request.contextPath}/registerDish">
    Nome: <input type="text" name="name" required><br/>
    Descrição: <input type="text" name="description" required><br/>
    Preço: <input type="text" name="price" required><br/>
    <input type="submit" value="Cadastrar">
</form>
</body>
</html>
