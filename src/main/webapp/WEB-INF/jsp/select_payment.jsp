<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mateus
  Date: 7/19/22
  Time: 11:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment</title>
</head>
<body>
<h1>Pagamento</h1>
<form method="post" action="${pageContext.request.contextPath}/order/${dishID}/confirm">
    Método de Pagamento: <select name="paymentID">
        <c:forEach items="${paymentMethods}" var="paymentMethod">
            <option name="paymentID" value="${paymentMethod.id}">${paymentMethod.description}</option>
        </c:forEach>
    </select><br/>
    Observação: <textarea name="note"></textarea><br/>
    <input type="submit" value="Realizar Pedido">
</form>
</body>
</html>
