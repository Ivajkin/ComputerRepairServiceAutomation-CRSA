/**
 * User: Ivaykin Timofey
 * Date: 06.05.14
 */

var isWarehouseTableLoaded = false;


function openWarehouse() {
    hideAllModuleContainers();

    $('#warehouseTableContainer').show();

    if(!isWarehouseTableLoaded) {
        isWarehouseTableLoaded = true;

        $('#warehouseTableContainer').jtable({
            title: 'Склад',
            paging: true,
            pageSize: 150,
            sorting: true,
            defaultSorting: 'invoice_number ASC',
            defaultDateFormat: 'dd.mm.yy',
            actions: {
                listAction: '/warehouse/list',
                createAction: '/warehouse/create',
                /*updateAction: '/warehouse/update', */
                deleteAction: '/warehouse/delete'
            },
            fields: {
                id: {
                    title: 'Идентификатор элемента склада',
                    key: true,
                    create: false,
                    edit: false,
                    list: false
                },
                category_id: {
                    title: 'Категория',
                    width: '5%',
                    options: '/category/options'
                },
                hardware_id: {
                    title: 'Наименование товара',
                    width: '10%',
                    options: '/hardware/options'
                },
                manufacturer_id: {
                    title: 'Производитель',
                    width: '5%',
                    options: '/manufacturer/options'
                },
                model: {
                    title: 'Модель',
                    width: '5%'
                },
                item_count: {
                        title: 'Кол-во штук',
                        width: '10%'
                },
                retail_price: {
                        title: 'Розничная цена',
                        width: '10%',
                        create: false,
                        edit: false,
                    display: function(requestData) {
                        var purchase_price = requestData.record.purchase_price;
                        var zero_price_percent = requestData.record.zero_price_percent;
                        var its_price_percent = requestData.record.its_price_percent;
                        var retail_price_percent = requestData.record.retail_price_percent;
                        var repair_price_percent = requestData.record.repair_price_percent;

                        return purchase_price * (100 + retail_price_percent)/100.0;
                    }
                },
                warranty: {
                        title: 'Гарантия',
                        width: '5%'
                },
                posting_date: {
                    title: 'Дата оприходования',
                    defaultValue: datef('dd.MM.YYYY'),
                    width: '10%',
                    edit: false
                    //visibility: 'hidden',
                    //type: 'hidden'

                },
                provider_id: {
                        title: 'Поставщик',
                        width: '5%',
                        options: '/provider/list',
                        list: false
                },
                invoice_number: {
                        title: 'Номер накладной',
                        width: '10%',
                        edit: false,
                        create: true,
                        list: false
                },
                note: {
                        title: 'Комментарии',
                        list: false
                },
                purchase_price: {
                        title: 'Закупочная цена',
                        width: '5%',
                        list: false
                },
                zero_price_percent: {
                        title: 'Нулевая цена (%)',
                        width: '5%',
                        list: false
                },
                its_price_percent: {
                        title: 'Своя цена (%)',
                        width: '5%',
                        list: false
                },
                retail_price_percent: {
                    title: 'Розничная цена (%)',
                    width: '5%',
                    list: false
                },
                repair_price_percent: {
                    title: 'Ремонтная цена (%)',
                    width: '5%',
                    list: false
                },
                not_serial_number: {
                    title: 'Не серийный товар' /* checkbox */,
                    list: false,
                    type: 'checkbox',
                    values: { 'false': false, 'true': true },
                    defaultValue: 'false'
                },
                /* Списать товар*/
                impairment: {
                    title: '',
                    width: '2%',
                    sorting: false,
                    edit: false,
                    create: false,
                    display: function (requestData) {
                        var $img = $('<button class="jtable-command-button delete-command-button"><img style="background:white;" width="16px" height="16px' +
                            '" src="/img/impaired.png" title="Списанный товар" /></button>');
                        $img.click(function () {
                            $('#warehouseTableContainer').jtable('openChildTable',
                                $img.closest('tr'),
                                {
                                    title: requestData.record.invoice_number + ' - причина списания',
                                    defaultDateFormat: 'dd.mm.yy',
                                    actions: {
                                        listAction: '/impairment/list?id=' + requestData.record.id,
                                        deleteAction: '/impairment/delete',
                                        updateAction: '/impairment/update',
                                        createAction: '/impairment/create'
                                    },
                                    fields: {
                                        id: {
                                            key: true,
                                            create: false,
                                            edit: false,
                                            list: false
                                        },
                                        impairment_type_id: {
                                            title: 'причина списания',
                                            options:'/impairment_type/options'
                                        },
                                        count_hardware_impairment: {
                                            title: 'Количество'
                                        },
                                        date_impairment: {
                                            title: 'дата списания',
                                            defaultValue: datef('dd.MM.YYYY'),
                                            edit: false
                                        },
                                        warehouse_item_id: {
                                            type: 'hidden',
                                            defaultValue: requestData.record.id
                                        }
                                    },
                                    formCreated: function (event, data) {
                                        $('#Edit-date_impairment').prop('readonly', true);
                                    }
                                }, function (data) {
                                    data.childTable.jtable('load');
                                });
                        });
                        return $img;
                    }
                }
            },

            //Initialize validation logic when a form is created
            formCreated: function (event, data) {
                // TODO: Добавить валидайию для всех полей
                data.form.find('input[name="provider_id"]').addClass('validate[required]');
                data.form.find('input[name="invoice_number"]').addClass('validate[required,minSize[3],maxSize[18]] text-input');
                data.form.find('input[name="repair_price"]').addClass('validate[required],custom[integer],min[0],max[500000]');
                data.form.find('input[name="item_count"]').addClass('validate[required],custom[integer],min[1],max[500]');
                data.form.find('input[name="date_impairment"]').addClass('validate[required,custom[date],past[NOW]]');   // var dateMMDDYYYRegex = '^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\d\d$';
                data.form.find('input[name="repair_price_percent"]').addClass('validate[required],custom[integer],min[1],max[100]');
                data.form.find('input[name="retail_price_percent"]').addClass('validate[required],custom[integer],min[1],max[100]');
                data.form.find('input[name="its_price_percent"]').addClass('validate[required],custom[integer],min[1],max[100]');
                data.form.find('input[name="zero_price_percent"]').addClass('validate[required],custom[integer],min[1],max[100]');

                data.form.validationEngine();

                //$('#Edit-posting_date').prop('edit', false);
                $('#Edit-posting_date').prop('readonly', true);

                $('#Edit-category_id').parent().parent().hide();
                $('#Edit-hardware_id').parent().parent().hide();

                $('#Edit-not_serial_number').click( function(){
                    if( $(this).is(':checked') )
                        $('#Edit-serial_number').val('не серийный').prop('disabled', true);
                    else
                        $('#Edit-serial_number').val('').prop('disabled', false);
                });
                // При изменении количества товаров на число больше нуля и меньше ста, создаем input сколько товаров
                // При нажатии кнопки Сохранить, даные отправляются в таблицу Склад, после этого
                // все Серийные номера из input посылаются на
                // id формы копируется в таблицу серийных номеров с разными id серийными номерами и по нажатии кнопки сохранить отправляются в таблицу Серийные номера
                //данные формы отправляются в таблицу Склад по нажатию кнопки Сохранить
                //У Таблицы Склад есть Номер Накладной (primary key)
                //У таблицы Серийных номеров Серийные номера (primary key)
                //При сохранении мы отправим на сервер запрос о добавлении Серийных номеров по номеру накладной




                $('#Edit-item_count').change(function(){
                    if($(this).val() > 0 && $(this).val() < 100) {
                        var snd = new Audio("css/sounds/serial_panel_opens.wav"); // buffers automatically when created
                        snd.play();
                        $('.serial.popover').remove();
                        var serial_popover =
                            '<div id="serial-numbers-popover" class="serial popover left">' +
                                    '<h3 class="popover-title">Введите серийные номера:</h3>' +
                                    '<div class="popover-content">' +
                                    '<p id="serial-inputs">' +
                                    '</p>' +
                                '</div>' +
                            '</div>';
                        var form_content = $(this).parent().parent().parent().parent();
                        form_content.before(serial_popover);
                        var form = form_content.parent();
                        form.animate({width: '810px', left: '277px'});
                        var inputs = '';
                        for(var i = 0; i < $(this).val(); ++i) {
                            inputs += '<input type="text"/>';
                        }
                        $('#serial-inputs').html(inputs);

                        $('.serial.popover').hide();
                        $('.serial.popover').show('fast');
                    } else {
                        messagebox.error('Введено неверное количество!', 'Введите верное количество.');
                    }
                 });


                data.form.parent().parent().addClass('warehouse-ui-dialog');

                    data.form.addClass('warehouseForm');
                    elements["category-hardware-selector-element"].addClass('jtable-input-field-container');
                    //$('.ui-dialog-content')
                    //    .first()
                    data.form
                        .append(elements["category-hardware-selector-element"]);
                    var category_search_create_input_edit_category_id_relation = function() {
                        var value = $('#category-search-create-input')
                            .val();
                        // Deselect
                        $('#Edit-category_id').val('');

                        $('#Edit-category_id option').filter(function() {
                            return ($(this).text() == value);
                        }).prop('selected', true);
                    };

                    (function() {
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

                                        // Событие, когда выбрано наименование оборудования

                                        var hardware_name = $(this).text();
                                        var hardware_selector_button_text = $('#hardware-selector-button-text');
                                        hardware_selector_button_text.text(hardware_name);
                                        // Deselect
                                        $('#Edit-hardware_id').val('');

                                        $('#Edit-hardware_id option').filter(function() {
                                            return ($(this).text() == hardware_name);
                                        }).prop('selected', true);
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
                                    elements += '<li><a >' + categories[key].name + '</a></li>';
                                    category_by_name_dictionary[categories[key].name] = categories[key];
                                }
                                $('.category-selector .dropdown-menu').html(elements);

                                var options = '';
                                for(key in categories) {
                                    options += '<option value="' + categories[key].id + '">'
                                        + categories[key].name + '</option>';
                                }

                                $('#Edit-category_id').html(options);
                                category_search_create_input_edit_category_id_relation();

                                $('.category-selector ul li').click(function() {
                                    var category_name = $(this).text();
                                    $('.category-selector input.form-control').val(category_name);
                                    selected_category_id = category_by_name_dictionary[category_name].id;
                                    // Запрашиваем список наименований hardware по category_id
                                    load_hardware_dropdown(selected_category_id);

                                    category_search_create_input_edit_category_id_relation();
                                    $('#category-search-create-input')
                                        .on('input',
                                            category_search_create_input_edit_category_id_relation);
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
                            var category_name_inserted = $('#category-search-create-input').val();
                            $.post('/category/create/json', {name: category_name_inserted}, load_categories_to_selector);
                        });
                    }) ();
            },


            //Validate form when it is being submitted
            formSubmitting: function (event, data) {
                return data.form.validationEngine('validate');
            },
            //Dispose validation logic when form is closed
            formClosed: function (event, data) {
                data.form.validationEngine('hide');
                data.form.validationEngine('detach');
            },
            recordsLoaded: function(event, data) {
                $('.jtable-data-row').dblclick(function() {
                    $(this.children[this.children.length-1].children[0]).click();
                });
            }
        });
        $('#warehouseTableContainer').jtable('load');

        (function() {
            var search = function() {
                $('tr.jtable-data-row').each(function() {
                    if($(this).text().toUpperCase().indexOf($('#searchbox-input').val().toUpperCase())!=-1)
                        $(this).show();
                    else
                        $(this).hide();
                });
            };
            $('#searchbox-input').on('input', search);
        }) ();

    }

}
