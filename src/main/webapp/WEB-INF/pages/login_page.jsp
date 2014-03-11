<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  User: Ivaykin Timofey
  Date: 3/11/14
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добро пожаловать! "Служба компьютерного ремонта"</title>
</head>
<body>

<form:form method="POST" commandName="user" action="${pageContext.request.contextPath}/login/process.html">
    <table>
        <tbody>
        <tr>
            <td>
                <form:input path="login" placeholder="логин"></form:input>
            </td>
        </tr>
        <tr>
            <td>
                <form:input path="password" placeholder="пароль"></form:input>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Войти" /></td>
        </tr>
        </tbody>
    </table>
</form:form>

</body>
</html>