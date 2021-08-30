<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<div>
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

    <label for="imageId">Passport:
        <input type="file" id="imageId" name="image">
    </label>
    <br>
    <br>

    <button type="submit">Register</button>

</div>
</body>
</html>
