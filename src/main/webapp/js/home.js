/**
 * User: Ivaykin Timofey
 * Date: 2/18/14
 */

angular.module('project', ['restangular', 'ngRoute']).
    config(function($routeProvider, RestangularProvider) {
        $routeProvider.
            when('/', {
                controller:ListCtrl,
                templateUrl:'list.html'
            }).
            when('/edit/:projectId', {
                controller:EditCtrl,
                templateUrl:'detail.html',
                resolve: {
                    project: function(Restangular, $route){
                        return Restangular.one('projects', $route.current.params.projectId).get();
                    }
                }
            }).
            when('/new', {controller:CreateCtrl, templateUrl:'detail.html'}).
            otherwise({redirectTo:'/'});

        RestangularProvider.setBaseUrl('https://api.mongolab.com/api/1/databases/angularjs/collections');
        RestangularProvider.setDefaultRequestParams({ apiKey: '4f847ad3e4b08a2eed5f3b54' })
        RestangularProvider.setRestangularFields({
            id: '_id.$oid'
        });

        RestangularProvider.setRequestInterceptor(function(elem, operation, what) {

            if (operation === 'put') {
                elem._id = undefined;
                return elem;
            }
            return elem;
        })
    });


function ListCtrl($scope, Restangular) {
    $scope.projects = Restangular.all("projects").getList().$object;
}


function CreateCtrl($scope, $location, Restangular) {
    $scope.save = function() {
        Restangular.all('projects').post($scope.project).then(function(project) {
            $location.path('/list');
        });
    }
}

function EditCtrl($scope, $location, Restangular, project) {
    var original = project;
    $scope.project = Restangular.copy(original);


    $scope.isClean = function() {
        return angular.equals(original, $scope.project);
    }

    $scope.destroy = function() {
        original.remove().then(function() {
            $location.path('/list');
        });
    };

    $scope.save = function() {
        $scope.project.put().then(function() {
            $location.path('/');
        });
    };
}

function openRequests() {
}

 $(document).ready(function() {

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