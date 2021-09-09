<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<html>
<head>
    <title>Register</title>
</head>
<body>
<div>
    <form action="${pageContext.request.contextPath}/register" method="post">

        <label for="firstNameId">First Name:
            <input type="text" id="firstNameId" name="firstName">
        </label>
        <br>
        <br>

        <label for="lastNameId">Last Name:
            <input type="text" id="lastNameId" name="lastName">
        </label>
        <br>
        <br>

        <label for="fatherNameId">Father Name:
            <input type="text" id="fatherNameId" name="fatherName">
        </label>
        <br>
        <br>

        <label for="citizenshipId">Citizenship:
            <input type="text" id="citizenshipId" name="citizenship">
        </label>
        <br>
        <br>

        <label for="passportId">Passport Code:
            <input type="text" id="passportId" name="passportCode">
        </label>
        <br>
        <br>

        <label for="birthdayId">Birthday:
            <input type="date" id="birthdayId" name="birthday">
        </label>
        <br>
        <br>


        <label for="emailId">Email:
            <input type="text" id="emailId" name="email">
        </label>
        <br>
        <br>

        <label for="passwordId">Password:
            <input type="password" id="passwordId" name="password">
        </label>
        <br>
        <br>

<%--        <label for="imageId">Passport:--%>
<%--            <input type="file" id="imageId" name="image">--%>
<%--        </label>--%>
<%--        <br>--%>
<%--        <br>--%>


        <button type="submit">Register</button>

        <c:if test="${not empty requestScope.errors}">
            <div style="color: red">
                <c:forEach var="error" items="${requestScope.errors}">
                    <span>${errors.message}</span>
                    <br>
                </c:forEach>
            </div>
        </c:if>

    </form>


</div>
</body>
</html>
