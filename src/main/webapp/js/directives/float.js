'use strict';

define(['./module'], function(module) {
    module.directive('float', function() {
        return {
            restrict: 'A',
            require: 'ngModel',
            link: function(scope, elem, attrs, ngModel) {
                ngModel.$parsers.push(function(value) {
                    var result = parseFloat(value);
                    return isNaN(result) ? 0 : result;
                });
                ngModel.$formatters.push(function(value) {
                    return value == '0' ? undefined : value;
                });
            }
        };
    });
});