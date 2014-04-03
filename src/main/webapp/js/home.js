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
        "hideDuration": "1000",
        "timeOut": "5000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    }})();
/* Message system ends */

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
             callback: openRequests
         },
         'warehouse': {
             label: 'Склад',
             icon: 'warehouseIcon.png',
             path: '/warehouse.html'
         },
         'store': {
             label: 'Магазин',
             icon: 'storeIcon.png',
             path: '/store.html'
         },
         'cash': {
             label: 'Касса',
             icon: 'cashIcon.png',
             path: '/cash.html'
         } ,
         'reports': {
             label: 'Отчеты',
             icon: 'reportsIcon.png',
             path: '/reports.html'
         }
     };

     function openDictionary(dictionaryID) {
         $('#loading-bar').show();

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