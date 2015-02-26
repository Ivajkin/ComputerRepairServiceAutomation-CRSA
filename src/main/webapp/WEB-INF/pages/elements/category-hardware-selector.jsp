<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<br/>
<br/>



<div class="input-group category-selector">
    <div class="input-group-btn">
        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
            Категории
            <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
            <!--
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li class="divider"></li>
            <li><a href="#">Separated link</a></li>
            -->
        </ul>
    </div><!-- /btn-group -->
    <input type="text" class="form-control" placeholder="Поиск категорий...">
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
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li class="divider"></li>
            <li><a href="#">Separated link</a></li>
        </ul>
    </div><!-- /btn-group -->
</div><!-- /input-group -->

<style>
    .btn.btn-default .glyphicon.glyphicon-plus.add {
        color: #00a300;
    }
    .btn.btn-default:disabled .glyphicon.glyphicon-plus.add {
        color: rgba(0, 67, 0, 0.09);
    }

</style>
<script type="application/javascript">
    $(document).ready(function() {
        var selected_category_id;
        var category_by_name_dictionary = {};

        function load_hardware_dropdown(category_id) {
            $.get('/hardware/list/json', {category_id: category_id}, function(hardware) {
                if(hardware.length) {
                    $('.hardware-by-category-selector .btn').prop("disabled", false);
                    var elements = '';
                    for (key in hardware) {
                        elements += '<li><a href="#' + hardware[key].id + '">' + hardware[key].name + '</a></li>';
                    }
                    $('.hardware-by-category-selector .dropdown-menu').html(elements);

                    $('.hardware-by-category-selector ul li').click(function () {
                        // TODO: Событие, когда выбрано наименование оборудования
                        //$('.hardware-by-category-selector input.form-control').val($(this).text());
                    });
                } else {
                    $('.hardware-by-category-selector .btn').prop("disabled", true);
                }
            });
        }
        function load_categories_to_selector() {
            $.get('/category/list/json', function(categories) {
                var elements = '';
                for(key in categories) {
                    elements += '<li><a href="#">' + categories[key].name + '</a></li>';
                    category_by_name_dictionary[categories[key].name] = categories[key];
                }
                $('.category-selector .dropdown-menu').html(elements);

                $('.category-selector ul li').click(function() {
                    var category_name = $(this).text();
                    $('.category-selector input.form-control').val(category_name);
                    selected_category_id = category_by_name_dictionary[category_name].id;
                    // Запрашиваем список наименований hardware по category_id
                    load_hardware_dropdown(selected_category_id);
                });

                $('.category-selector .form-control').on('input', function() {
                    var category_name_inserted = $(this).val();
                    // Если категория уже существует или имеет слишком мало символов, то мы не можем её создать
                    var can_add_category = category_name_inserted.length > 3;
                    for(var key in category_by_name_dictionary) {
                        if(category_by_name_dictionary[key].name === category_name_inserted)
                            can_add_category = false;
                    }

                    $('.category-selector .btn.btn-default.add').prop("disabled", !can_add_category);
                });
            });
        }
        load_categories_to_selector();

        $('.category-selector .btn.btn-default.add').click(function() {
            var category_name_inserted = $('.category-selector .form-control').val();
            $.post('/category/create/json', {name: category_name_inserted}, load_categories_to_selector);
        });
    });
</script>

<br/>
<br/>