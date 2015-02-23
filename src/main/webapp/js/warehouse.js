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
                updateAction: '/warehouse/update',
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
                serial_number: {
                    title: 'Серийный номер',
                    width: '10%',
                    list: true
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
                    width: '10%'
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
                }
            },

            //Initialize validation logic when a form is created
            formCreated: function (event, data) {
                // TODO: Добавить валидайию для всех полей
                data.form.find('input[name="provider_id"]').addClass('validate[required]');
                data.form.find('input[name="invoice_number"]').addClass('validate[required,minSize[3],maxSize[18]] text-input');
                data.form.find('input[name="repair_price"]').addClass('validate[required],custom[integer],min[0],max[500000]');
                data.form.find('input[name="item_count"]').addClass('validate[required],custom[integer],min[1],max[500]');
                data.form.validationEngine();


                $('#Edit-not_serial_number').click( function(){
                    if( $(this).is(':checked') )
                        $('#Edit-serial_number').val('не серийный').prop('disabled', true);
                    else
                        $('#Edit-serial_number').val('').prop('disabled', false);
                });
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
        $('#warehouseTableContainer').jtable('load');
    }
}
