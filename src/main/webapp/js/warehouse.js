/**
 * User: Ivaykin Timofey
 * Date: 06.05.14
 */

var isWarehouseTableLoaded = false;

function openWarehouse() {
    $('#requestsTableContainer').hide();
    $('#settingsContainer').hide();

    $('#warehouseTableContainer').show();

    if(!isWarehouseTableLoaded) {
        isWarehouseTableLoaded = true;

        $('#warehouseTableContainer').jtable({
            title: 'Склад',
            paging: true,
            pageSize: 150,
            sorting: true,
            defaultSorting: 'invoice_number ASC',
            actions: {
                listAction: '/warehouse/list',
                createAction: '/warehouse/create',
                updateAction: '/warehouse/update',
                deleteAction: '/warehouse/delete'
            },
            fields: {
                provider_id: {
                    title: 'Поставщик',
                    width: '5%',
                    options: '/provider/list'
                },
                invoice_number: {
                    title: 'Номер накладной',
                    width: '10%',
                    key: true,
                    edit: false,
                    create: true
                },
                item_count: {
                    title: 'Кол-во штук',
                    width: '10%'
                },
                repair_price: {
                    title: 'Цена ремонтная',
                    width: '5%'
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
        $('#requestsTableContainer').jtable('load');
    }
}
