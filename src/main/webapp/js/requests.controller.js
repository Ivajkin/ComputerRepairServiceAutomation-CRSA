
(function() {
    var requestsApp = angular.module('requestsApp', ['ngGrid']);

    requestsApp.controller('RequestListCtrl', function ($scope) {
        $scope.requests = [
            {'name': 'Nexus S',
                'snippet': 'Fast just got faster with Nexus S.', age: 43, checkmark: true},
            {'name': 'Motorola XOOM™ with Wi-Fi',
                'snippet': 'The Next, Next Generation tablet.', age: 42, checkmark: false},
            {'name': 'MOTOROLA XOOM™',
                'snippet': 'The Next, Next Generation tablet.', age: 33, checkmark: true}
        ];
        $scope.gridOptions = {
            data: 'requests',
            columnDefs: [
                {field:'name', displayName:'Name',  cellTemplate: '<div class="ngCellText">{{row.getProperty(col.field)}}</div>'},
                {field:'snippet', displayName:'Snippet',  cellTemplate: '<div class="ngCellText">{{row.getProperty(col.field)}}</div>'},
                {field:'age', displayName:'Age',  cellTemplate: '<div ng-class="{green: row.getProperty(col.field) > 30}"><div class="ngCellText">{{row.getProperty(col.field)}}</div></div>'}
            ]
        };
    });
    requestsApp.filter('checkmark', function() {
        return function(input) {
            return input ? '\u2713' : '\u2718';
        };
    });
})();