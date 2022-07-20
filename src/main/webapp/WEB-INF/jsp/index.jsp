<%--
  Created by IntelliJ IDEA.
  User: mateus
  Date: 7/18/22
  Time: 12:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>The Restaurant</title>
</head>
<body>
<h1>Bem vindo ao The Restaurant!</h1>
<h2>${msg}</h2>
<a href="${pageContext.request.contextPath}/signin">Realizar Login</a><br/>
<a href="${pageContext.request.contextPath}/signup">Realizar Cadastro</a>
</body>
</html>
