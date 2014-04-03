/**
 * User: Ivaykin Timofey
 * Date: 2/18/14
 */

angular.module('request', ['restangular', 'ngRoute']).
    config(function($routeProvider, RestangularProvider) {
        $routeProvider.
            when('', {
                controller:ListCtrl,
                templateUrl:'list.html'
            }).
            when('/edit/:requestId', {
                controller:EditCtrl,
                templateUrl:'detail.html',
                resolve: {
                    request: function(Restangular, $route){
                        return Restangular.one('requests', $route.current.params.requestId).get();
                    }
                }
            }).
            when('/new', {controller:CreateCtrl, templateUrl:'detail.html'}).
            otherwise({redirectTo:''});

        RestangularProvider.setBaseUrl('/requests');
        // RestangularProvider.setDefaultRequestParams({ apiKey: '4f847ad3e4b08a2eed5f3b54' })
        /*RestangularProvider.setRestangularFields({
            id: '_id.$oid'
        });   */

        RestangularProvider.setRequestInterceptor(function(elem, operation, what) {

            if (operation === 'put') {
                elem.id = undefined;
                return elem;
            }
            return elem;
        })
    });


function ListCtrl($scope, Restangular) {
    $scope.requests = Restangular.all("requests").getList().$object;
}


function CreateCtrl($scope, $location, Restangular) {
    $scope.save = function() {
        Restangular.all('requests').post($scope.request).then(function(request) {
            $location.path('/list');
        });
    }
}

function EditCtrl($scope, $location, Restangular, request) {
    var original = request;
    $scope.request = Restangular.copy(original);


    $scope.isClean = function() {
        return angular.equals(original, $scope.request);
    }

    $scope.destroy = function() {
        original.remove().then(function() {
            $location.path('/list');
        });
    };

    $scope.save = function() {
        $scope.request.put().then(function() {
            $location.path('/');
        });
    };
}

function openRequests() {
}

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
             message: {header: "Склад", text: "Оприходование, списание и работа с категориями."}
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
             message: {header: "Управление кассой", text: "Раздел управления кассами - приход, расход по типам."}
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
     }

     for(var dic in mainMenuItems) {
         $('<a class="tableButton" id="'+ dic + '" href="#">'
             + '<img class="tableIcon" src="/img/' + mainMenuItems[dic]['icon'] + '" />'
             + mainMenuItems[dic]['label'] + '</a><br/>').appendTo("nav main");
         $('#' + dic).click(function () {
             openDictionary(this.id);
         });
     }

 });