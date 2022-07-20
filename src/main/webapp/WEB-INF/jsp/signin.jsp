<%--
  Created by IntelliJ IDEA.
  User: mateus
  Date: 7/18/22
  Time: 10:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign In</title>
</head>
<body>
<h1>Realizar Login</h1>
<a href="/">Voltar</a>
<form method="post" action="${pageContext.request.contextPath}/signin/checkinfo">
    Email: <input type="text" name="email" required><br/>
    Senha: <input type="password" name="password" required><br/>
    <input type="submit" value="Continuar">
</form>
</body>
</html>
