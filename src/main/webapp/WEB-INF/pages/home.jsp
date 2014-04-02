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
    <link type="text/css" rel="stylesheet" href="webjars/ng-grid/2.0.8/ng-grid.css"/>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/style.css'/>"/>
    <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/img/favicon.ico" />

    <script type="text/javascript" src="webjars/jquery/2.1.0/jquery.js"></script>

    <script type="text/javascript" src="webjars/angularjs/1.2.14/angular.js"></script>
    <script type="text/javascript" src="webjars/angularjs/1.2.14/angular-resource.js"></script>
    <script type="text/javascript" src="webjars/angularjs/1.2.14/angular-route.js"></script>
    <script type="text/javascript" src="webjars/angularjs/1.2.14/angular-sanitize.js"></script>
    <script type="text/javascript" src="webjars/angularjs/1.2.14/angular-cookies.js"></script>
    <script type="text/javascript" src="webjars/angularjs/1.2.14/angular-animate.js"></script>

    <script type="text/javascript" src="webjars/ng-grid/2.0.8/ng-grid.js"></script>

    <script type="text/javascript" src="webjars/bootstrap/3.1.1/js/bootstrap.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.1.1/js/bootstrap.js"></script>
    <script type="text/javascript" src="webjars/toastr/2.0.1/toastr.js"></script>

    <script type="text/javascript" src="webjars/underscorejs/1.6.0/underscore.js"></script>

    <script type="text/javascript" src="webjars/restangular/1.3.1/restangular.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/home.js"></script>
</head>
<body>

<header class="mainheader">
    <img id="logo" src="/img/logo.png" alt="Служба компьютерного ремонта (логотип)" />
    <h1>Служба компьютерного ремонта</h1>
    <h2>Techno Media Ltd</h2>

</header>

<nav class="panel main">
    <main>
    </main>
</nav>

<main id="listPanel" class="main">
    <div ng-app="project">
        <div ng-view></div>



        <!-- CACHE FILE: list.html -->
        <script type="text/ng-template" id="list.html">
            <input type="text" ng-model="search" class="search-query" placeholder="Search">
            <table>
                <thead>
                <tr>
                    <th>Project</th>
                    <th>Description</th>
                    <th><a href="#/new"><i class="glyphicon glyphicon-plus-sign"></i></a></th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="project in projects | filter:search | orderBy:'name'" ng-show="project._id.$oid">
                    <td><a href="{{project.site}}" target="_blank">{{project.name}}</a></td>
                    <td>{{project.description}}</td>
                    <td>
                        <a href="#/edit/{{project._id.$oid}}"><i class="glyphicon glyphicon-pencil"></i></a>
                    </td>
                </tr>
                </tbody>
            </table>
        </script>



        <!-- CACHE FILE: detail.html -->
        <script type="text/ng-template" id="detail.html">
            <form name="myForm">
                <div class="control-group" ng-class="{error: myForm.name.$invalid}">
                    <label>Name</label>
                    <input type="text" name="name" ng-model="project.name" required>
          <span ng-show="myForm.name.$error.required" class="help-inline">
              Required</span>
                </div>

                <div class="control-group" ng-class="{error: myForm.site.$invalid}">
                    <label>Website</label>
                    <input type="url" name="site" ng-model="project.site" required>
          <span ng-show="myForm.site.$error.required" class="help-inline">
              Required</span>
          <span ng-show="myForm.site.$error.url" class="help-inline">
              Not a URL</span>
                </div>

                <label>Description</label>
                <textarea name="description" ng-model="project.description"></textarea>

                <br>
                <a href="#/" class="btn">Cancel</a>
                <button ng-click="save()" ng-disabled="isClean() || myForm.$invalid"
                        class="btn btn-primary">Save</button>
                <button ng-click="destroy()"
                        ng-show="project._id" class="btn btn-danger">Delete</button>
            </form>
        </script>
    </div>
</main>

<footer class="panel">
    ${message}
    <img id="loading-bar" src="${pageContext.request.contextPath}/img/loading-bar.gif" alt="Загрузка...">
</footer>

</body>
</html>