<%--
  Created by IntelliJ IDEA.
  User: mateus
  Date: 7/19/22
  Time: 5:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Payment Method</title>
</head>
<body>
<h1>Novo Método de Pagamento</h1>
<form method="post" action="${pageContext.request.contextPath}/payment/register/confirm">
    Descrição:<input name="description" type="text" required>
    <input type="submit" value="Criar">
</form>
</body>
</html>
