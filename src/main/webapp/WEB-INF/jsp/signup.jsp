<%--
  Created by IntelliJ IDEA.
  User: mateus
  Date: 7/18/22
  Time: 10:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/signup/redirect">
    CPF (Apenas números): <input type="text" name="cpf" maxlength="11"><br/>
    Nome: <input type="text" name="name" required><br/>
    Telefone: <input type="text" name="phoneNumber" required><br/>
    Email: <input type="text" name="email" required><br/>
    Senha: <input type="password" name="password" required><br/>
    <input type="submit" value="Cadastrar-se">
</form>
</body>
</html>
