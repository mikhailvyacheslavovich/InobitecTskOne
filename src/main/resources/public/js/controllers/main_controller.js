'use strict';

App.controller("MainController", ['$rootScope', 'MainService', function ($rootScope, MainService) {
    var self = this;

    self.order = {customerName: '', customerPhone: '', customerComment: '', orderItems: []};
    self.orders = [];
    self.greetingMessage = "Микросервис Order";

    self.getAllOrders = function () {
        MainService.getAllOrders().then(
            function (d) {
                self.orders = d;
                var t = 8;
            },
            function (errResponse) {
                console.error('error while getting taken disks' + errResponse);
            }
        )
    };

    self.showItems = function () {

    }

    self.deleteOrder = function () {

    }

    self.addOrder = function () {

    }

    self.getAllOrders();
}]);