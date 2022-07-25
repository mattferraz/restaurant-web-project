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
<h1>Payment</h1>
<form method="post" action="${pageContext.request.contextPath}/order/${dishID}/confirm">
    Payment Method: <select name="paymentID">
        <c:forEach items="${paymentMethods}" var="paymentMethod">
            <option name="paymentID" value="${paymentMethod.id}">${paymentMethod.name}</option>
        </c:forEach>
    </select><br/>
    Note: <textarea name="note"></textarea><br/>
    <input type="submit" value="Finish">
</form>
</body>
</html>
