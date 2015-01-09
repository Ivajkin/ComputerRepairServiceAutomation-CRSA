/**
 * User: Ivaykin Timofey
 * Date: 06.05.14
 */


var isRequestsTableLoaded = false;

function onPrint() {
    var divContents = $("#Edit-request_status_id").val();
    var printWindow = window.open('', '', 'height=400,width=800');
    printWindow.document.write('<html><head><title>DIV Contents</title></head><body>');
    printWindow.document.write(divContents);
    printWindow.document.write('</body></html>');
    printWindow.document.close();
    printWindow.print();
}

function deleteButton() {

    $('.jtable-command-button.jtable-delete-command-button').remove();

}


var is_amount_field_disabled;
function toggleDoneState() {
    var request_status_id_edit = $('#Edit-request_status_id');
    var status_id = request_status_id_edit.find('option:selected').val();
    var status_name = request_status_id_edit.find('option:selected').text();
    var disable_status = true;
    if(status_id == 3) {
        assert(status_name === 'Выдан/Выполнен', 'Значение поля статуса заявки (' + status_id + ') указывает на статус "Выдан/Выполнен" но имеет значение "' + status_name + '". Возможно порядок статусов перепутан.');
        disable_status = false;
    } else if(status_id == 2) {
        assert(status_name === 'Готов', 'Значение поля статуса заявки (' + status_id + ') указывает на статус "Готов" но имеет значение "' + status_name + '". Возможно порядок статусов перепутан.');
        disable_status = false;
    }

    $('#Edit-date_of_issue').prop( "disabled", disable_status);
    $('#Edit-amount').prop( "disabled", disable_status);
    is_amount_field_disabled = !disable_status;
}

function onOpenEditDialog() {
    $('.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ui-dialog-buttons.ui-draggable.ui-resizable')
        .css('width', '400px');
    toggleDoneState();
    $('#Edit-request_status_id').change(toggleDoneState);
}
/*$('.jtable-toolbar-item-add-record').click(onOpenEditDialog);
 $('.jtable-data-row').click(onOpenEditDialog);*/


function openRequests() {
    $('#warehouseTableContainer').hide();
    $('#settingsContainer').hide();
    $('#cashModuleContainer').hide();
    $('#reportsModuleContainer').hide();

    $('#requestsTableContainer').show();

    if (!isRequestsTableLoaded) {
        isRequestsTableLoaded = true;
        $('#requestsTableContainer').jtable({
            title: 'Заявки',
            paging: false,
            pageSize: 15000,
            sorting: false,
            defaultSorting: 'date_of_issue ASC',
            //defaultDateFormat: 'dd.mm.yy',
            actions: {
                listAction: '/requests/list',
                createAction: '/requests/create',
                updateAction: '/requests/update'
             //   deleteAction: '/requests/delete'
            },
			fields: {
                req_num_id: {
                    title: 'Номер заявки',
                    key: true,
                    create: false,
                    edit: false,
                    list: false
                },
                request_number: {
                    title: 'Номер заявки',
                    width: '5%',
                    create: false,
                    edit: true,
                    list: true
                },
                phone: {
                    title: 'Номер телефона клиента',
                    width: '7%'
                },
                request_status_id: {
                    title: 'Статус заявки',
                    options: '/request_status/list',
                    defaultValue: 1, /* Принят */
                    create: false,
                    edit: false
                },
                hardware_id: {
                    title: 'Оборудование',
                    width: '5%',
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
                slot_id: {
                    title: 'Номер ячейки'
                },
                serial_number: {
                    title: 'Серийный номер',
                    width: '10%',
                    list: true
                },
                fault_id: {
                    title: 'Неисправность',
                    width: '5%',
                    options: '/fault/list'
                },
                responsible_id: {
                    title: 'Ответственный',
                    options:'/employee/options',
                    list: false
                },
                date_of_receipt: {
                    title: 'Дата получения',
                    defaultValue: datef('YYYY-MM-dd'),
                    width: '10%',
                    type: 'date'
                },
                date_of_issue: {
                    title: 'Дата выдачи',
                    width: '10%',
                    type: 'date',
                    create: false,
                    edit: false
                },
                amount: {
                    title: 'Сумма в рублях',
                    width: '5%'
                },
                appearance_id: {
                    title: 'Внешний вид',
                    options:'/appearance/options',
                    list: false
                },
                completeness_id: {
                    title: 'Комплектность',
                    options:'/completeness/options',
                    list: false
                },
                address: {
                    title: 'Адрес клиента',
                    list: false
                },
                customer_name: {
                    title: 'ФИО клиента',
                    list: false,
                    defaultValue: 'Частное лицо'
                },
                source_id: {
                    title: 'Источник',
                    options:'/source/options',
                    list: false
                },
                date_of_call: {
                    title: 'Дата звонка',
                    width: '10%',
                    type: 'date',
                    defaultValue: datef('YYYY-MM-dd'),
                    list: false
                },
                note: {
                    title: 'Примечание',
                    list: false
                },
                approximate_cost: {
                    title: 'Примерная стоимость',
                    list: false
                },
                prepayment: {
                    title: 'Предоплата',
                    width: '3%',
                    defaultValue: 0,
                    list: false
                },
                acceptor_id: {
                    title: 'Приёмщик',
                    create: true,
                    edit: true,
                    list: false,
                    options: '/employee/options',
                    defaultValue: current_user.id,
                    visibility: 'hidden',
                    type: 'hidden'
                },
                delivery: {
                    title: 'Доставка' /* checkbox */,
                    list: false
                },
                print_accept_talon: {
                    title: 'Печать талона приёма' /* checkbox */,
                    list: false
                },
                completed_works: {
                    title: '',
                    width: '2%',
                    sorting: false,
                    edit: false,
                    create: false,
                    display: function (requestData) {
                        var $img = $('<button class="jtable-command-button tasks-command-button"><img src="/img/repair.png" title="Выполненные работы" /></button>');
                        $img.click(function () {
                            $('#requestsTableContainer').jtable('openChildTable',
                                $img.closest('tr'),
                                {
                                    title: requestData.record.request_number + ' - выполненные работы',
                                    actions: {
                                        listAction: '/task/list?req_num_id=' + requestData.record.req_num_id,
                                        deleteAction: '/task/delete',
                                        updateAction: '/task/update',
                                        createAction: '/task/create'
                                    },
                                    fields: {
                                        id: {
                                            key: true,
                                            create: false,
                                            edit: false,
                                            list: false
                                        },
                                        engineer_id: {
                                            title: 'инженер',
                                            options:'/employee/options'
                                        },
                                        task_type_id: {
                                            title: 'наименование выполненной работы',
                                            options:'/task_type/options'
                                        },
                                        price: {
                                            title: 'цена'
                                        },
                                        request_id: {
                                            type: 'hidden',
                                            defaultValue: requestData.record.req_num_id
                                        }
                                    }
                                }, function (data) {
                                    data.childTable.jtable('load');
                                });
                        });
                        return $img;
                    }
                },
                parts_installed: {
                    title: '',
                    width: '2%',
                    sorting: false,
                    edit: false,
                    create: false,
                    display: function (requestData) {
                        var $img = $('<button class="jtable-command-button parts-installed-command-button"><img src="/img/parts_installed.png" title="Установленные запчасти" /></button>');
                        $img.click(function () {
                            $('#requestsTableContainer').jtable('openChildTable',
                                $img.closest('tr'),
                                {
                                    title: requestData.record.request_number + ' - установленные запчасти',
                                    actions: {
                                        listAction: '/parts_installed/list?req_num_id=' + requestData.record.req_num_id,
                                        deleteAction: '/parts_installed/delete',
                                        updateAction: '/parts_installed/update',
                                        createAction: '/parts_installed/create'
                                    },
                                    fields: {
                                        id: {
                                            key: true,
                                            create: false,
                                            edit: false,
                                            list: false
                                        },
                                        hardware_id: {
                                            title: 'наименование',
                                            options:'/hardware/options'
                                        },
                                        count: {
                                            title: 'количество'
                                        },
                                        price: {
                                            title: 'цена'
                                        },
                                        request_id: {
                                            type: 'hidden',
                                            defaultValue: requestData.record.req_num_id
                                        }
                                    }
                                }, function (data) {
                                    data.childTable.jtable('load');
                                });
                        });
                        return $img;
                    }
                },
				method_of_payment: {
                     title: 'Метод оплаты',
					 options: '/payment/list',
                     list: false
                },
                parts_installed_id: {
                    title: 'Установленные запчасти',
                    options: '/hardware/options',
                    list: false
                }
            },

            //Initialize validation logic when a form is created
            formCreated: function (event, data) {
                // TODO: Добавить валидайию для всех полей
                data.form.find('input[name="fault"]').addClass('validate[required]');
                if(!is_amount_field_disabled) {
                    data.form.find('input[name="amount"]').addClass('validate[required],custom[integer],min[10],max[500000]');
                }
                data.form.find('input[name="prepayment"]').addClass('validate[required],custom[integer],min[0],max[500000]');
                data.form.find('input[name="date_of_receipt"]').addClass('validate[required,custom[date],past[NOW]]');   // var dateMMDDYYYRegex = '^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\d\d$';
                data.form.find('input[name="phone"]').addClass('validate[required,custom[phone],minSize[6],maxSize[18]] text-input');
                data.form.find('input[name="request_number"]').addClass('validate[required,minSize[7],maxSize[8]] text-input');
                data.form.find('input[name="model"]').addClass('validate[required,minSize[1],maxSize[100]] text-input');
                data.form.find('input[name="serial_number"]').addClass('validate[required,minSize[1],maxSize[100]] text-input');
                data.form.find('input[name="customer_name"]').addClass('validate[required,minSize[1],maxSize[500]] text-input');
                data.form.validationEngine();

                onOpenEditDialog();
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
                    //var row_id = $(this).attr('data-record-key');
                    //alert('clicked row with id '+row_id);
                    $(this.children[this.children.length-1].children[0]).click();
                });
            }
        });

        $(deleteButton);

        $('.ui-dialog-buttonpane.ui-widget-content').append('<button id="btnPrint" style="position:absolute; right:185px;" onclick="onPrint();">Печать</button>');



        $('#requestsTableContainer').jtable('load');

    }
}
