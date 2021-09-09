<%--
  Created by IntelliJ IDEA.
  User: BEGLYAK
  Date: 09.09.2021
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/buy" method="post">
    <label for="amountId">Введи количество для покупки:
        <input type="text" id="amountId" name="amount">
    </label>
    <button type="submit">Подтвердить</button>
</form>

</body>
</html>
