<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div id="request-status-selector">

        <div class="input-group input-word">
            <span class="input-group-btn">
                <!-- TODO: Сделать подгрузку таблицы в зависимости от текущего статуса -->
            </span>
            <script>
                $(document).ready(function() {
                    <!-- Загрузить статусы из базы -->
                    $.get("/request_status/list/json", function(statuses) {
                        for(var key in statuses) {
                            var status = statuses[key];
                            $('#request-status-selector .input-group-btn').append(
                                    '<button id="status-' +
                                    status.id +
                                    '-button" class="status-btn btn btn-default" type="button">' +
                                    '<span class="glyphicon add" aria-hidden="true"></span>' +
                                    status.name +
                                    '</button>');
                        }

                        <!-- Сделать выделение текущего статуса -->
                        $('.status-btn').click(function() {
                            $('.status-btn').removeClass('active');
                            $(this).addClass('active');
                        });
                    });
                });
            </script>
        </div><!-- /input-group -->
</div>

<br/>