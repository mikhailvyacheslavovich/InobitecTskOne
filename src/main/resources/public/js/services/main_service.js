'use strict';

App.factory('MainService', ['$http', '$q', function ($http, $q) {
    return {
        getAllOrders: function () {
            return $http.get('/orders')
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('error while getting free disks');
                        return $q.reject(errResponse);
                    }
                )
        },
    }
}]);
