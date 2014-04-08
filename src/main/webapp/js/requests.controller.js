
(function() {
    var requestsApp = angular.module('requestsApp', ['ngGrid']);

    requestsApp.controller('RequestListCtrl', function ($scope) {
        $scope.requests = [
            {'name': 'Nexus S',
                'snippet': 'Fast just got faster with Nexus S.', age: 43},
            {'name': 'Motorola XOOM™ with Wi-Fi',
                'snippet': 'The Next, Next Generation tablet.', age: 43},
            {'name': 'MOTOROLA XOOM™',
                'snippet': 'The Next, Next Generation tablet.', age: 43}
        ];
        $scope.gridOptions = { data: 'requests' };
    });
})();