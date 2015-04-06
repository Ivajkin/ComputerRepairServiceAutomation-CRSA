<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div id="request-status-selector">

        <div class="input-group input-word">
            <span class="input-group-btn">
                <!-- TODO: Сделать кнопки скругленными слева -->
                <!-- TODO: Загрузить статусы из базы -->
                <!-- TODO: Сделать выделение текущего статуса -->
                <!-- TODO: Сделать подгрузку таблицы в зависимости от текущего статуса -->
                <button id="status-1-button" class="btn btn-default" type="button">
                    <span class="glyphicon add" aria-hidden="true"></span> Готов
                </button>
                <button id="status-2-button" class="btn btn-default" type="button">
                    <span class="glyphicon add" aria-hidden="true"></span> Принят
                </button>
                <button id="status-3-button" class="btn btn-default" type="button">
                    <span class="glyphicon add" aria-hidden="true"></span> Выполнен/Выдан
                </button>
            </span>
        </div><!-- /input-group -->
</div>

<br/>