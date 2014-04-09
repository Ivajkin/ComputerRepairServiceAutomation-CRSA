<%--
  User: Ivaykin Timofey
  Date: 2/12/14
--%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Домашняя страница</title>
    <link type="text/css" rel="stylesheet" href="webjars/bootstrap/3.1.1/css/bootstrap.css"/>
    <link type="text/css" rel="stylesheet" href="webjars/toastr/2.0.1/toastr.min.css"/>
    <link type="text/css" rel="stylesheet" href="webjars/jquery-ui/1.10.4/themes/base/jquery-ui.css"/>
    <link type="text/css" rel="stylesheet" href="webjars/ng-grid/2.0.8/ng-grid.css"/>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/style.css'/>"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/validationEngine/validationEngine.jquery.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/jtable.2.4.0/themes/metro/blue/jtable.css"/>
    <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/img/favicon.ico" />

    <script type="text/javascript" src="webjars/jquery/2.1.0/jquery.js"></script>
    <script type="text/javascript" src="webjars/jquery-ui/1.10.4/ui/jquery-ui.js"></script>

    <script type="text/javascript" src="webjars/bootstrap/3.1.1/js/bootstrap.js"></script>
    <script type="text/javascript" src="webjars/toastr/2.0.1/toastr.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jtable.2.4.0/external/json2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jtable.2.4.0/jquery.jtable.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jtable.2.4.0/localization/jquery.jtable.ru.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/validationEngine/languages/jquery.validationEngine-ru.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/validationEngine/jquery.validationEngine.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/home.js"></script>
</head>
<body>

<header class="mainheader">
    <img id="logo" src="${pageContext.request.contextPath}/img/logo.png" alt="Служба компьютерного ремонта (логотип)" />
    <h1>Служба компьютерного ремонта</h1>
    <h2>Techno Media Ltd</h2>

</header>

<!-- Категории - Добавлять, удалять, изменять категории может администратор в разделе Настройка. -->
<a id="settingsButton" class="tableButton" href="#">
    <img class="tableIcon" alt="Настройки" src="${pageContext.request.contextPath}/img/settings.png" />
</a>


<nav class="panel main">
    <main>
    </main>
</nav>

<main id="listPanel" class="main">
    <div id="requestsTableContainer"></div>
</main>

<footer class="panel">
    ${message}
    <img id="loading-bar" src="${pageContext.request.contextPath}/img/loading-bar.gif" alt="Загрузка...">
</footer>

</body>
</html>