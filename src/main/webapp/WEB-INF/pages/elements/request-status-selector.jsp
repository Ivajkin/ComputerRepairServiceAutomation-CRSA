<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div id="request-status-selector">

        <div class="input-group input-word">
            <span class="input-group-btn">
            </span>
            <script>
                $(document).ready(function() {
                    <!-- Загрузка статусов заявок из базы -->
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

                        var filter_requests_table_by_status;

                        <!-- Выделение текущего статуса -->
                        $('.status-btn').click(function() {
                            $('.status-btn').removeClass('active');
                            $(this).addClass('active');

                            var status_name = $(this).text();
                            filter_requests_table_by_status(status_name);
                        });

                        <!-- Подгрузка таблицы заявок в зависимости от текущего статуса заявки -->
                        filter_requests_table_by_status = function(status_name) {
                            $('#requestsTableContainer .jtable-data-row').hide();
                            $('#requestsTableContainer .jtable-data-row').map(function(i, row) {
                                row = $(row);
                                if(row.text().indexOf(status_name) != -1) {
                                    row.show();
                                }
                            });
                        };

                        <!-- Автоматически выбираем первый статус заявок для фильтрации -->
                        $('.status-btn:first').click();
                    });
                });
            </script>
        </div><!-- /input-group -->
</div>

<br/>