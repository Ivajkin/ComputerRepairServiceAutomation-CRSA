<%--
  User: hairymax
  Date: 24.03.14 1:30
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
    <link type="text/css" rel="stylesheet" href="/css/style.css"/>
    <link type="text/css" rel="stylesheet" href="/css/style.login.css"/>
<%--    <link rel="icon" href="" type="">
    <link rel="shortcut icon" href="" type="">
--%>
    <script type="text/javascript" src="webjars/jquery/2.1.0/jquery.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            if("${error}".length == 0) {
                $('#img').hide();
                $('#block').hide();
                setTimeout(function(){$("#img").fadeIn(1500);}, 500);
                setTimeout(function(){$("#img").animate({'margin-top':'100px'},1000);},2000);
                setTimeout(function(){$("#block").fadeIn(500);}, 3500);
            } else {
                $('#img').show().animate({'margin-top':'100px'},0);
                $('#block').show();
            }

            $('#lightBulb').click(function() {$('body').toggleClass('darkTheme')});
        });
    </script>
</head>
<body onLoad='document.loginForm.username.focus();'> <!--="main()"-->
    <form id="loginForm" name="loginForm" action="<c:url value='login' />" method="post">
        <div id="img">
            <img src="../../img/logo.png" alt="лого">
        </div>
        <div id="block">
            <label id="user" for="login">p</label>
            <input type="text" name="username" id="login" placeholder="Логин" required/>
            <label id="pass" for="password">k</label>
            <input type="password" name="password" id="password" placeholder="Пароль" required />
            <input type="submit" id="submit" name="submit" value="a"/>
            <a href="#">Забыли пароль?</a>
        </div>
    </form>
    <p class="error">${error}</p>
    <p class="logout">${logout}</p>

    <div id="lightBulb"></div>
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
</body>
</html>