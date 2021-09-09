<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>

</head>
<body>

<%@include file="header.jsp"%>


<div>

    <form action="${pageContext.request.contextPath}/login" method="post">

        <label for="emailId">Email:
            <input type="text" id="emailId" name="email" value="${param.email}">
        </label>
        <br>

        <label for="passwordId">Password:
            <input type="password" id="passwordId" name="password">
        </label>
        <br>

        <button type="submit">Login</button>
        <br>
        <br>
        <br>

        <div>
            <a href="${pageContext.request.contextPath}/register">
                <button type="button">Register</button>
            </a>
        </div>


            <c:if test="${param.error!=null}">
                <div style="color: red">
                    <span>Email or password is not correct</span>
                </div>
            </c:if>

    </form>

</div>

<%@include file="footer.jsp"%>

</body>
</html>
