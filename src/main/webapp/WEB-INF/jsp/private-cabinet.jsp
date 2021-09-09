<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Private Cabinet</title>
</head>
<body>
<%@include file="header.jsp" %>
<div><h1>Личный кабинет</h1></div>


<div>
    <p>Имя: ${sessionScope.client.detail.firstName}</p>
    <p>Фамилия: ${sessionScope.client.detail.lastName}</p>
    <p>Дата рождения: ${sessionScope.client.detail.birthday}</p>
</div>

<div>
    <p>Паспортные данные:</p>
    <p>Личный номер: ${sessionScope.client.detail.passportCode}</p>
    <p>Гражданство: ${sessionScope.client.detail.citizenship}</p>
    <button type="button">Изменить</button>
</div>


<%--<div>
   Список акций:
    <c:forEach var="stock" items="${sessionScope.client.stocks}">
        <p>${stock.name} <button type="button">Купить</button> <button type="button">Продать</button></p>
    </c:forEach>
</div>--%>

<div>
    <table border="1">
        <caption>Список акций</caption>
        <tr>
            <th>Id:</th>
            <th>Имя:</th>
            <th>Тикер:</th>
            <th>Дивиденды:</th>
            <th>Цена 1 акции:</th>
            <th>Количество:</th>
            <th>Валюта:</th>
        </tr>
        <c:forEach var="stock" items="${sessionScope.client.stocks}">
            <tr>
                <td>${stock.id}</td>
                <td>${stock.name}</td>
                <td>${stock.ticker}</td>
                <td>${stock.dividend}</td>
                <td>${stock.costOneStock}</td>
                <td>${stock.amount.toString()}</td>
                <td>${stock.currency}</td>
                <td>


                    <a href="${pageContext.request.contextPath}/buy">
                        <button type="button">Купить</button>
                    </a>


                </td>

                <td>

                    <a href="${pageContext.request.contextPath}/buy">
                        <button type="button">Продать</button>
                    </a>

                </td>
            </tr>

        </c:forEach>
    </table>
</div>


<div>
    <table border="1">
        <caption>Позиция по валютам</caption>
        <tr>
            <th>Валюта:</th>
            <th>Количество:</th>
        </tr>

        <c:forEach var="money" items="${sessionScope.client.monies}">
            <tr>
                <td>${money.currency.ticker}</td>
                <td>${money.amount}</td>
                <td>
                    <button type="submit">Пополнить</button>
                </td>
                <td>
                    <button type="submit">Вывести</button>
                </td>


            </tr>
        </c:forEach>


    </table>
</div>

<%@include file="footer.jsp" %>

</body>
</html>
