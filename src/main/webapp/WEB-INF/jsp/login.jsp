<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <script type="text/javascript" src="/resources/js/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="/resources/js/demo.js"></script>
</head>
<body>
<div>
    <h1 style="color: red">Вход в личный кабинет</h1>
</div>

<div>
    <form action="${pageContext.request.contextPath}/register" method="post"></form>
    <label for="emailId">Login:
        <input type="text" id="emailId" name="email">
    </label>
    <br>
    <br>

    <label for="passwordId">Password:
        <input type="password" id="passwordId" name="password">
    </label>
    <br>
    <br>

    <button type="submit">Login</button>
    <br>

    <%--Some text:
    <input type="text" id="data-input"/>
    <button type="button" onclick="getInputData()">Send Data</button>

    <button type="button" onclick="replaceDataOnDisplay()">Replace</button>
    <p id="display-data">Original Data</p>--%>
</div>
</body>
</html>
