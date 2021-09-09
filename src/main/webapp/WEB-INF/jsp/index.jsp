<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/WEB-INF/css/style.css">
</head>
<%@include file="header.jsp" %>

<body>
<div>
    <p>
    <h1>This is main page of my application</h1>
    </p>
    <p>
        This application is named Investor
    </p>
</div>

<div>
    <a href="${pageContext.request.contextPath}/login">
        <button type="button">Личный кабинет</button>
    </a>
</div>

<div class="block">

</div>

</body>

<%@include file="footer.jsp" %>
</html>
