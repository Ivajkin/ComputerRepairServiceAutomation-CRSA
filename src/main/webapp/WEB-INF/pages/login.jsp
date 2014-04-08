<%--
  Created by IntelliJ IDEA.
  User: hairymax
  Date: 24.03.14
  Time: 1:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
    <link type="text/css" rel="stylesheet" href="/css/style.login.css"/>
<%--    <link rel="icon" href="" type="">
    <link rel="shortcut icon" href="" type="">
--%>
    <script type="text/javascript" src="/module/jquery-2.1.0.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            if("${message}".length == 0) {
                $('#img').hide();
                $('#block').hide();
                setTimeout(function(){$("#img").fadeIn(3000);}, 1000);
                setTimeout(function(){$("#img").animate({'margin-top':'100px'},2000);},4000);
                setTimeout(function(){$("#block").fadeIn(1000);}, 7000);
            } else {
                $('#img').show().animate({'margin-top':'100px'},0);
                $('#block').show();
            }
        });
    </script>
</head>
<body onLoad="main()">
    <form id="form" name="form" action="/login/process" method="post">
        <div id="img">
            <img src="../../img/logo.png" alt="лого">
        </div>
        <div id="block">
            <label id="user" for="login">p</label>
            <input type="text" name="login" id="login" placeholder="Логин" required/>
            <label id="pass" for="password">k</label>
            <input type="password" name="password" id="password" placeholder="Пароль" required />
            <input type="submit" id="submit" name="submit" value="a"/>
            <a href="#">Забыли пароль?</a>
        </div>
    </form>
    <p class="error">${message}</p>
</body>
</html>