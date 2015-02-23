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
                options: '/category/options'
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

function loadEmployeeTable() {

    $('#employeeTableContainer').jtable({
        title: 'Сотрудники',
        paging: true,
        pageSize: 150,
        sorting: true,
        defaultSorting: 'name ASC',
        //defaultDateFormat: 'dd.mm.yy',
        actions: {
            listAction: '/user/list',
            createAction: '/user/create',
            updateAction: '/user/update'
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
                title: 'ФИО',
                width: '5%'
            },
            email: {
                title: 'email',
                width: '5%'
            },
            login: {
                title: 'Логин',
                width: '5%'
            },
            password_hash: {
                title: 'Пароль',
                width: '5%'
            },
            fee: {
                title: 'Процент',
                width: '5%'
            },
            role_id: {
                title: 'Роль',
                width: '15%',
                options: '/user/list-roles'
            }
        },
        //Initialize validation logic when a form is created
        formCreated: function (event, data) {
            // TODO: Добавить валидайию для всех полей
            data.form.find('input[name="name"]').addClass('validate[required]');
            data.form.find('input[name="fee"]').addClass('validate[required],custom[integer],min[0],max[100]');
            data.form.find('input[name="email"]').addClass('validate[required]');
            data.form.find('input[name="password_hash"]').addClass('validate[required]');
            data.form.find('input[name="login"]').addClass('validate[required]');
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
    $('#employeeTableContainer').jtable('load');
}



function loadProfileSettings() {
    $('#currentUserProfile #current-user-profile-name').val(current_user.name);
    $('#currentUserProfile #current-user-profile-email').val(current_user.email);
    $('#currentUserProfile #current-user-profile-login').val(current_user.login);
    $('#current-user-profile-save-button').click(function() {
        current_user.set({});
        $('#currentUserProfile .status').fadeOut();
    });
}

function openSettings() {

        hideAllModuleContainers();

        $('#settingsContainer').show();

        if (!isSettingsTableLoaded) {
            isSettingsTableLoaded = true;

            if (current_user.is_admin) {
                loadDictionary('#manufacturersTableContainer', 'Производители', '/manufacturer');
                //loadDictionary('#hardwareModelsTableContainer', 'Модели', '/hardware_model');
                loadExtendedDictionary('#hardwareNamesTableContainer', 'Виды оборудования', '/hardware');
                loadDictionary('#categoryNamesTableContainer', 'Категории', '/category');
                loadDictionary('#sourcesTableContainer', 'Источники', '/source');
                loadDictionary('#appearancesTableContainer', 'Внешний вид', '/appearance');
                loadDictionary('#completenessTableContainer', 'Комплектность', '/completeness');
                loadEmployeeTable();
            }
            loadProfileSettings();
        }
}