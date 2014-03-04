<%--
  User: Ivaykin Timofey
  Date: 2/12/14
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Страница изненме</title>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/style.css'/>"/>
</head>
<body class="invisibleBackground">

<div class="panel">
    <header>
        <h1>Действия</h1>
    </header>

    <main>
        <p>Здесь вы можете создать новую запись.</p>
        <form:form method="POST" commandName="dictionaryItem" action="${pageContext.request.contextPath}${dictionaryTypePath}/create/process.html">
            <table>
                <tbody>
                <tr>
                    <td>Название:</td>
                    <td><form:input path="name" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Создать" /></td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </form:form>

    </main>
</div>
</body>
</html>