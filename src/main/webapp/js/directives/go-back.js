'use strict';

define(['./module'], function(module) {
    module.directive('goBack', ['$window', function($window) {
        return {
            restrict: 'A',
            link: function(scope, element) {
                element.on('click', function() {
                    $window.history.back();
                });
            }
        };
    }]);
});