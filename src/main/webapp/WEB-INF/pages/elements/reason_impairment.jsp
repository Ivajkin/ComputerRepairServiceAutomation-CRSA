<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<br/>
<br/>

<div id="Edit-impairment_type_id">

    <div class="reason_impairment_element">
        <div class="input-group input_impairment">
            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                Причина списания
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" role="menu">
                <!--
                <li class="divider"></li>
                -->
            </ul>
        </div><!-- /btn-group -->
        <input type="text" class="form-control" placeholder="Поиск причин списаний...">
                  <span class="input-group-btn">
                    <button class="btn btn-default add" type="button" disabled>
                        <span class="glyphicon glyphicon-plus add" aria-hidden="true"></span> Добавить
                    </button>
                  </span>
    </div>
</div>

