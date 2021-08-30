<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Stock</title>
</head>
<body>
<h1>${requestScope.stock.name}</h1>
<p>Стоимость: ${requestScope.stock.cost}</p>
<p>Валюта: ${requestScope.stock.currency}</p>
<p>Дивидендная доходность: ${requestScope.stock.dividend}</p>
<p><button type="submit">Buy</button></p>
<p><button type="submit">Sell</button></p>
</body>
</html>
