<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<br/>
<br/>

<div id="category-hardware-selector-element">

    <div class="input-group category-selector">
        <div class="input-group-btn">
            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                Категории
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" role="menu">
                <!--
                <li class="divider"></li>
                -->
            </ul>
        </div><!-- /btn-group -->
        <input type="text" id="c1ategory-search-create-input" class="form-control" placeholder="Поиск категорий...">
                  <span class="input-group-btn">
                    <button class="btn btn-default add" type="button" disabled>
                        <span class="glyphicon glyphicon-plus add" aria-hidden="true"></span> Добавить
                    </button>
                  </span>
    </div><!-- /input-group -->

    <br/>

    <div class="input-group hardware-by-category-selector">
        <div class="input-group-btn">
            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false" disabled>
                Наименование товара
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" role="menu">
                <!--
                <li class="divider"></li>
                -->
            </ul>
        </div><!-- /btn-group -->
    </div><!-- /input-group -->

</div>

<br/>
<br/>