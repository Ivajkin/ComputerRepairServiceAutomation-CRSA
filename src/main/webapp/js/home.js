/**
 * User: Ivaykin Timofey
 * Date: 2/18/14
 */

// Substring text with html tags
function htmlSubstring(s, n) {
    var m, r = /<([^>\s]*)[^>]*>/g,
        stack = [],
        lasti = 0,
        result = '';

    //for each tag, while we don't have enough characters
    while ((m = r.exec(s)) && n) {
        //get the text substring between the last tag and this one
        var temp = s.substring(lasti, m.index).substr(0, n);
        //append to the result and count the number of characters added
        result += temp;
        n -= temp.length;
        lasti = r.lastIndex;

        if (n) {
            result += m[0];
            if (m[1].indexOf('/') === 0) {
                //if this is a closing tag, than pop the stack (does not account for bad html)
                stack.pop();
            } else if (m[1].lastIndexOf('/') !== m[1].length - 1) {
                //if this is not a self closing tag than push it in the stack
                stack.push(m[1]);
            }
        }
    }

    //add the remainder of the string, if needed (there are no more tags in here)
    result += s.substr(lasti, n);

    //fix the unclosed tags
    while (stack.length) {
        result += '</' + stack.pop() + '>';
    }

    return result;

}

/* Message system */
var messagebox = {
    info: toastr.info,
    warning: toastr.warning,
    success: toastr.success,
    error: (!Audio ?  toastr.error :
                            function(message, messageTitle) {
                            var snd = new Audio("css/sounds/error.wav"); // buffers automatically when created
                            snd.play();
                            toastr.error(message, messageTitle);
                            Bugsnag.notify(messageTitle, message);
                        })
};
window.onerror = function myErrorHandler(errorMsg, url, lineNumber) {
    var errorMessage = "Необработанная ошибка: " + errorMsg + "<br/>Путь: "+url+"<br/>Номер строки: "+lineNumber;
    messagebox.error(errorMessage, "Необработанная ошибка");
};
$(document).ajaxError(function( event, jqxhr, settings, thrownError ) {
    var errorMessage = thrownError
        + '<br/>Error: ' + htmlSubstring(jqxhr.responseText, 199)+'...'
        + '<br/>URL: ' + settings.url;
    messagebox.error(errorMessage, "Ошибка AJAX");
});
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
        if(!expectedMessage) messagebox.error("Сообщение для assert не должно быть пустым", "Ошибка проверки (assert)");
        if(!messagebox) console.log("Объект для вывода сообщений (messagebox) не существует!");
        messagebox.error(expectedMessage, "Ошибка проверки (assert)");
    }
}
/* Assertions ends */

function documentLoaded() {
    messagebox.info('Начните работу с добавления заявок', 'Приложение готово к работе.');
}

var reformalOptions;
function setupReformalWidget() {
    reformalOptions = {
        project_id: 750291,
        show_tab: false,
        project_host: "crsa.reformal.ru"
    };
    (function() {
        var script = document.createElement('script');
        script.type = 'text/javascript'; script.async = true;
        script.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'media.reformal.ru/widgets/v3/reformal.js';
        document.getElementsByTagName('head')[0].appendChild(script);
    })();
}

var current_user;
current_user = {
    is_admin: undefined,
    id: undefined,
    name: undefined,
    email: undefined,
    login: undefined,
    password_hash: undefined,
    load: function(callback) {
        $.getJSON("user/current", function(user) {
            current_user = user;
            current_user.is_admin = 1 === user.role_id;

            var is_morning = (new Date()).getHours() < 12 && (new Date()).getHours() > 3;
            var is_evening = (new Date()).getHours() > 16;
            messagebox.success((is_morning ? "Доброе утро, " : (is_evening ? "Добрый вечер, " : "Добрый день, ")) + current_user.name + "!", "Профиль пользователя успешно загружен");

            callback();
        }).fail(function() {
            messagebox.error("Профиль текущего пользователя не удалось загрузить", "Ошибка загрузки пользователя");
        });
    },
    set: function(changed_user_fields) {
        messagebox.warning("This feature is not implemented yet", "Not Implemented");
        $.post("user/current", function() {
        });
    }
};

// Элементы интерфейса, которые будем использовать для вставки в определенных частях приложения
var elements;
$(document).ready(function() {
   elements = {
        // Сборный элемент из master-detail набора (категория - товар/оборудование)
        "category-hardware-selector-element": $('#category-hardware-selector-element').detach()
    };
});

function hideAllModuleContainers() {

    $('#requestsTableContainer').hide();
    $('#settingsContainer').hide();
    $('#warehouseTableContainer').hide();
    $('#reportsModuleContainer').hide();
    $('#cashModuleContainer').hide();
}

 $(document).ready(function() {
     $('.blur-lock').hide();
     $('#lock').click(function() {$('body').toggleClass('blurred-locked-body'); $('.blur-lock').fadeIn();});


     /* Настройка темной темы интерфейса */
     $('#lightBulb').click(function() {$('body').toggleClass('darkTheme')});

     setupReformalWidget();
     $('#open-reformal-widget-button')
         .click(function() { Reformal.widgetOpen(); return false; })
         .hover(function() { Reformal.widgetPreload(); });


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
                 " - зарплата мастерам, движение денежных средств, остатки на складе, списание со склада, принятие на склад, валовая прибыль."},
             callback: openReportsModule
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


     current_user.load(function() {
         openRequests();
     });

 });
