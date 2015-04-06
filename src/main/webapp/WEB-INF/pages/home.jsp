<%--
  User: Ivaykin Timofey
  Date: 2/12/14
--%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Домашняя страница</title>
    <link type="text/css" rel="stylesheet" href="webjars/bootstrap/3.1.1/css/bootstrap.css"/>
    <link type="text/css" rel="stylesheet" href="webjars/toastr/2.0.1/toastr.min.css"/>
    <link type="text/css" rel="stylesheet" href="webjars/jquery-ui/1.10.4/themes/base/jquery-ui.css"/>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/style.css'/>"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/validationEngine/validationEngine.jquery.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/jtable.2.4.0/themes/metro/blue/jtable.css"/>
    <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/img/favicon.ico" />

    <script type="text/javascript" src="webjars/jquery/2.1.0/jquery.js"></script>
    <script type="text/javascript" src="webjars/jquery-ui/1.10.4/ui/jquery-ui.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.1.1/js/bootstrap.js"></script>
    <script type="text/javascript" src="webjars/toastr/2.0.1/toastr.js"></script>

    <script
      src="//d2wy8f7a9ursnm.cloudfront.net/bugsnag-2.min.js"
      data-apikey="fb2081a6fd097d08afacf11427c94960">
    </script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/datef.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jtable.2.4.0/external/json2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jtable.2.4.0/jquery.jtable.tmedia.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jtable.2.4.0/localization/jquery.jtable.ru.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/validationEngine/languages/jquery.validationEngine-ru.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/validationEngine/jquery.validationEngine.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/requests.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/warehouse.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/cash.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/settings.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/reports.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/home.js"></script>
</head>
<body>

<header class="mainheader">
    <img id="logo" src="${pageContext.request.contextPath}/img/logo.png" alt="Служба компьютерного ремонта (логотип)" />
    <h1>Служба компьютерного ремонта</h1>
    <h2>Techno Media Ltd</h2>

</header>

<div id="lightBulb" class="util-button"></div>
<svg height="0" xmlns="http://www.w3.org/2000/svg">
    <filter id="drop-shadow">
        <feGaussianBlur in="SourceAlpha" stdDeviation="4"/>
        <feOffset dx="12" dy="12" result="offsetblur"/>
        <feFlood flood-color="rgba(0,0,0,0.5)"/>
        <feComposite in2="offsetblur" operator="in"/>
        <feMerge>
            <feMergeNode/>
            <feMergeNode in="SourceGraphic"/>
        </feMerge>
    </filter>
</svg>

<div id="lock" class="util-button"></div>
<svg height="0" xmlns="http://www.w3.org/2000/svg">
    <filter id="drop-shadow">
        <feGaussianBlur in="SourceAlpha" stdDeviation="4"/>
        <feOffset dx="12" dy="12" result="offsetblur"/>
        <feFlood flood-color="rgba(0,0,0,0.5)"/>
        <feComposite in2="offsetblur" operator="in"/>
        <feMerge>
            <feMergeNode/>
            <feMergeNode in="SourceGraphic"/>
        </feMerge>
    </filter>
</svg>


<div id="open-reformal-widget-button" class="util-button"></div>
<svg height="0" xmlns="http://www.w3.org/2000/svg">
    <filter id="drop-shadow">
        <feGaussianBlur in="SourceAlpha" stdDeviation="4"/>
        <feOffset dx="12" dy="12" result="offsetblur"/>
        <feFlood flood-color="rgba(0,0,0,0.5)"/>
        <feComposite in2="offsetblur" operator="in"/>
        <feMerge>
            <feMergeNode/>
            <feMergeNode in="SourceGraphic"/>
        </feMerge>
    </filter>
</svg>

<!-- Категории - Добавлять, удалять, изменять категории может администратор в разделе Настройка. -->
<a id="settingsButton" class="tableButton" href="#">
    <img class="tableIcon" alt="Настройки" src="${pageContext.request.contextPath}/img/settings.png" />
</a>


<nav class="panel main">
    <main>
    </main>
</nav>

<main id="listPanel" class="main">
    <div id="cashModuleContainer">
        <div class="cash-button cash">
            <div class="cash-plus"></div>
            <div class="cash-minus"></div>
            <div class="cash-saldo">- руб.</div>
        </div>
        <div class="cash-button credit">
            <div class="cash-plus"></div>
            <div class="cash-minus"></div>
            <div class="cash-saldo">- руб.</div>
        </div>
        <div class="cash-button wire">
            <div class="cash-plus"></div>
            <div class="cash-minus"></div>
            <div class="cash-saldo">- руб.</div>
        </div>

        <div class="cash-operation-window">
            <h1 class="cash-operation-name"><!--Расход--></h1>
            <label style="margin-right: 8px;">Введите сумму:</label>
            <input id="cash-operation-amount" type="text"/>
            <button id="commit-cash-operation-button">Произвести операцию</button>
        </div>
    </div>

    <!--
        На этой форме отображаются таблицы созданных заявок,
        которые разделяются по статусам [(!) (Статусы: Принят, Готов, Выдан/Выполнен)].
    -->
    <%@include file="elements/request-status-selector.jsp" %>
    <div id="requestsTableContainer"></div>

    <div id="warehouseTableContainer">
        <%@include file="elements/searchbox.jsp" %>
    </div>



    <div id="settingsContainer">

        <%@include file="elements/category-hardware-selector.jsp" %>


        <div id="manufacturersTableContainer"></div>
        <div id="categoryNamesTableContainer"></div>
        <!--<div id="hardwareModelsTableContainer"></div>-->
        <div id="hardwareNamesTableContainer"></div>
        <div id="employeeTableContainer"></div>
        <div id="sourcesTableContainer"></div>
        <div id="appearancesTableContainer"></div>
        <!--<div id="completenessTableContainer"></div>    -->



        <div id="currentUserProfile">
            <form>
                <label for="current-user-profile-name">
                    ФИО:
                </label>
                <input id="current-user-profile-name" type="text" value="{{name}}" name="name"/>

                <label for="current-user-profile-email">
                    email:
                </label>
                <input id="current-user-profile-email" type="email" value="{{email}}" name="email"/>

                <label for="current-user-profile-login">
                    Логин:
                </label>
                <input id="current-user-profile-login" type="text" value="{{login}}" name="login"/>

                <input id="current-user-profile-save-button" value="Сохранить" title="Сохранить данные текущего пользователя" type="button"/>

                <p class="status">Изменения успешно сохранены</p>
            </form>
        </div>
    </div>
    <div id="reportsModuleContainer"></div>

    <a href="<c:url value="logout" />" >Выйти</a></h2>
</main>

<div class="blur-lock"></div>

</body>
</html>
