/**
 * User: Ivaykin Timofey
 * Date: 2/18/14
 */



/* Message system */
var messagebox = {
    info: toastr.info,
    warning: toastr.warning,
    success: toastr.success,
    error: toastr.error
};
(function initialiseMessageSystem() {
    toastr.options = {
        "closeButton": true,
        "debug": true,
        "positionClass": "toast-bottom-right",
        "showDuration": "300",
        "hideDuration": "1500",
        "timeOut": "6500",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    }})();
/* Message system ends */

/* Assertions */
function assert(expression, expectedMessage) {
    if(!expression) {
        assert(expectedMessage, "Сообщение для assert не должно быть пустым");
        assert(messagebox, "Объект для вывода сообщений (messagebox) не существует");
        messagebox.error(expectedMessage, "Ошибка при проверке выражения (assert)");
    }
}
/* Assertions ends */

function documentLoaded() {
    messagebox.info('Начните работу с добавления заявок', 'Приложение готово к работе.');
}

 $(document).ready(function() {

     /* Настройка темной темы интерфейса */
     $('#lightBulb').click(function() {$('body').toggleClass('darkTheme')});

     documentLoaded();

     $('#loading-bar').hide();

     var mainMenuItems = {
         'requests': {
             label: 'Заявки',
             icon: 'requestsIcon.png',
             path: '/requests.html',
             message: {header: "Раздел работы с заявками на ремонт", text: "В этом разделе вы можете создавать, изменять заявки, печатать талоны, счета."},
             callback: openRequests
         },
         'warehouse': {
             label: 'Склад',
             icon: 'warehouseIcon.png',
             path: '/warehouse.html',
             message: {header: "Склад", text: "Оприходование, списание и работа с категориями."},
             callback: openWarehouse
         },
         'store': {
             label: 'Магазин',
             icon: 'storeIcon.png',
             path: '/store.html',
             message: {header: "Продажа товаров", text: "Раздел продажи товаров, включающий подготовку соответствующих документов."}
         },
         'cash': {
             label: 'Касса',
             icon: 'cashIcon.png',
             path: '/cash.html',
             message: {header: "Управление кассой", text: "Раздел управления кассами - приход, расход по типам."},
             callback: openCashModule
         } ,
         'reports': {
             label: 'Отчеты',
             icon: 'reportsIcon.png',
             path: '/reports.html',
             message: {header: "Форма подготовки отчетов", text: "Позволяет подготовить и экспортировать основные отчеты филиала" +
                 " - зарплата мастерам, движение денежных средств, остатки на складе, списание со склада, принятие на склад, валовая прибыль."}
         }
     };
     for(var i in mainMenuItems) {
         assert(mainMenuItems[i].message && mainMenuItems[i].message.header && mainMenuItems[i].message.text, "У элемента меню " + i + " нет сообщения при открытии пункта меню");
     }

     function openDictionary(dictionaryID) {
         $('#loading-bar').show();

         var message = mainMenuItems[dictionaryID].message;
         messagebox.info(message.text, message.header);

         mainMenuItems[dictionaryID].callback();
         /*$('#listPanel').html('');
         $.ajax(mainMenuItems[dictionaryID]['listPath']).success(function(data) {
             $('#listPanel').html(data);

             $('#loading-bar').hide();
         });
         $('#actionPanel').html('');
         $.ajax(mainMenuItems[dictionaryID]['createPath']).success(function(data) {
             $('#actionPanel').html(data);
         });  */
         $('#loading-bar').hide();
     }

     for(var dic in mainMenuItems) {
         $('<a class="tableButton" id="'+ dic + '" href="#">'
             + '<img class="tableIcon" src="/img/' + mainMenuItems[dic]['icon'] + '" />'
             + mainMenuItems[dic]['label'] + '</a><br/>').appendTo("nav main");
         $('#' + dic).click(function () {
             openDictionary(this.id);
         });
     }


     $('#settingsButton').click(function() {
         openSettings();
         messagebox.info("Раздел настроек", "В этом разделе администратор может добавлять, удалять, изменять категории и прочие вспомогательные словари.");
     });

     openRequests();

 });