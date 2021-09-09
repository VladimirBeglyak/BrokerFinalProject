<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Stocks</title>
</head>
<body>

<%@include file="header.jsp"%>

<h1>Список акций:</h1>

<c:forEach var="stock" items="${requestScope.stocks}">
    <p><a href="/stock?id=${stock.id}">${stock.name}</a></p>
</c:forEach>

<%@include file="footer.jsp"%>
</body>
</html>
