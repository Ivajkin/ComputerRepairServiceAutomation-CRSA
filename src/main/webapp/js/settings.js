/**
 * Ивайкина Галина 25.05.2014
 */

var isSettingsTableLoaded = false;

function loadDictionary(id, name, path) {

    $(id).jtable({
        title: name,
        paging: true,
        pageSize: 150,
        sorting: true,
        defaultSorting: 'name ASC',
        //defaultDateFormat: 'dd.mm.yy',
        actions: {
            listAction: path + '/list',
            createAction: path + '/create',
            updateAction: path + '/update',
            deleteAction: path + '/delete'
        },
        fields: {
            // TODO: Добавить все поля и настроить для каждого свойства (в первую очередь для тех, что выбираем из списка)
            // TODO: Применить дочерние таблицы (CHILD TABLE), http://www.jtable.org/demo/masterchild
            id: {
                title: 'id',
                key: true,
                create: false,
                edit: false,
                list: false
            },

            name: {
                title: 'Наименование',
                width: '5%'
            }
        },
        //Initialize validation logic when a form is created
        formCreated: function (event, data) {
            // TODO: Добавить валидайию для всех полей
            data.form.find('input[name="name"]').addClass('validate[required]');
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
    $(id).jtable('load');
}

function loadExtendedDictionary(id, name, path) {

    $(id).jtable({
        title: name,
        paging: true,
        pageSize: 150,
        sorting: true,
        defaultSorting: 'name ASC',
        //defaultDateFormat: 'dd.mm.yy',
        actions: {
            listAction: path + '/list',
            createAction: path + '/create',
            updateAction: path + '/update',
            deleteAction: path + '/delete'
        },
        fields: {
            // TODO: Добавить все поля и настроить для каждого свойства (в первую очередь для тех, что выбираем из списка)
            // TODO: Применить дочерние таблицы (CHILD TABLE), http://www.jtable.org/demo/masterchild
            id: {
                title: 'id',
                key: true,
                create: false,
                edit: false,
                list: false
            },

            name: {
                title: 'Наименование',
                width: '5%'
            },
            description: {
                title: 'Описание',
                width: '15%'
            },
            category_id: {
                title: 'Категория',
                width: '5%',
                options: '/category/list'
            }
        },
        //Initialize validation logic when a form is created
        formCreated: function (event, data) {
            // TODO: Добавить валидайию для всех полей
            data.form.find('input[name="name"]').addClass('validate[required]');
            data.form.find('input[name="description"]').addClass('validate[required]');
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
    $(id).jtable('load');
}

function openSettings() {
    $('#warehouseTableContainer').hide();
    $('#requestsTableContainer').hide();

    $('#settingsContainer').show();

    if(!isSettingsTableLoaded) {
        isSettingsTableLoaded = true;
        loadDictionary('#manufacturersTableContainer', 'Производители', '/manufacturer');
        loadDictionary('#hardwareModelsTableContainer', 'Модели', '/hardware_model');
        loadExtendedDictionary('#hardwareNamesTableContainer', 'Виды оборудования', '/hardware');

    }
}