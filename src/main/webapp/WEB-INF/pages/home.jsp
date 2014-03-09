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
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/style.css'/>"/>
    <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/img/favicon.ico" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/module/jquery-2.1.0.min.js"></script>
</head>
<body>
<!--
<img src="/img/advice.cat.png" style="
    position: absolute;
    max-width: 80px;
                                        ">
<div style="
    width: 200px;
    background: white;
    height: 100px;
">Чем я могу вам помочь?</div><input type="text">
-->
<header class="mainheader">
    <img id="logo" src="/img/logo.png" alt="Служба компьютерного ремонта (логотип)" />
    <h1>Служба компьютерного ремонта</h1>
    <h2>Techno Media Ltd</h2>

</header>

<nav class="panel main">
    <!--<header>
        <h1>Таблицы</h1>
    </header>-->

    <main>
    </main>
</nav>

<main id="listPanel" class="main">
    <!--<iframe id="listPanelFrame" scrolling="no" src="${pageContext.request.contextPath}/discipline/list.html" seamless>         -->
        <!-- //required for browser compatibility -->
    <!--</iframe> -->
</main>

<section id="actionPanel" class="main">
    <!--<iframe id="actionPanelFrame" scrolling="no" src="${pageContext.request.contextPath}/discipline/create.html" seamless>    -->
        <!-- //required for browser compatibility -->
    <!--</iframe>             -->
</section>

<footer class="panel">
    ${message}
    <img id="loading-bar" src="${pageContext.request.contextPath}/img/loading-bar.gif" alt="Загрузка...">
</footer>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/home.js"></script>
</body>
</html>