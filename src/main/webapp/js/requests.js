/**
 * User: Ivaykin Timofey
 * Date: 06.05.14
 */

var isRequestsTableLoaded = false;

d = new Date();
datef('YYYY-MM-dd', d);

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
            paging: true,
            pageSize: 150,
            sorting: true,
            defaultSorting: 'date_of_issue ASC',
            //defaultDateFormat: 'dd.mm.yy',
            actions: {
                listAction: '/requests/list',
                createAction: '/requests/create',
                updateAction: '/requests/update',
                deleteAction: '/requests/delete'
            },
			fields: {
                // TODO: Добавить все поля и настроить для каждого свойства (в первую очередь для тех, что выбираем из списка)
                // TODO: Применить дочерние таблицы (CHILD TABLE), http://www.jtable.org/demo/masterchild
                req_num_id: {
                    title: 'Номер заявки',
                    key: true,
                    create: false,
                    edit: false,
                    list: false
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
                date_of_receipt: {
                    title: 'Дата получения',
                    defaultValue: datef('YYYY-MM-dd'),
                    width: '10%',
                    type: 'date'
                },
                date_of_issue: {
                    title: 'Дата выдачи',
                    width: '10%',
                    type: 'date'
                },
                request_status_id: {
                    title: 'Статус заявки',
                    options: '/request_status/list',
                    list: false
                },
                amount: {
                    title: 'Сумма в рублях',
                    width: '5%'
                },
                phone: {
                    title: 'Номер телефона клиента',
                    width: '7%'

                },
                acceptor_id: {
                    title: 'Приёмщик',
                    list: false
                },
                prepayment: {
                    title: 'Предоплата',
                    width: '3%',
                    defaultValue: 0,
                    edit: false
                },
                note: {
                    title: 'Примечание',
                    list: false
                },
				method_of_payment: {
                     title: 'Метод оплаты',
					 options: '/payment/list',
                     list: false
                }
            },
			toolbar: {
                items:{
                    icon: '/images/pdf.png',
                    text: 'Экспортировать в PDF',
                    click: function () {
                        //perform your custom job...
                    }
                }
            },


            //Initialize validation logic when a form is created
            formCreated: function (event, data) {
                // TODO: Добавить валидайию для всех полей
                data.form.find('input[name="fault"]').addClass('validate[required]');
                data.form.find('input[name="amount"]').addClass('validate[required],custom[integer],min[10],max[500000]');
                data.form.find('input[name="prepayment"]').addClass('validate[required],custom[integer],min[0],max[500000]');
                data.form.find('input[name="date_of_receipt"]').addClass('validate[required,custom[date],past[NOW]]');   // var dateMMDDYYYRegex = '^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\d\d$';
                data.form.find('input[name="phone"]').addClass('validate[required,custom[phone],minSize[6],maxSize[18]] text-input');
                data.form.validationEngine();
            },
            //Validate form when it is being submitted
            formSubmitting: function (event, data) {
                return data.form.validationEngine('validate');
            },
            //Dispose validation logic when form is closed
            formClosed: function (event, data) {
                data.form.validationEngine('hide');
                data.form.validationEngine('detach');
            }
        });

        $('.jtable-toolbar-item-add-record').click(function() {
            setTimeout(function() {
                $('.ui-dialog-buttonpane.ui-widget-content').append('<button onclick="window.print();">Печать</button>');
                $('.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ui-dialog-buttons.ui-draggable.ui-resizable')
                    .css('width', '500px');
            }, 100);
        });

        $('#requestsTableContainer').jtable('load');
    }
}
