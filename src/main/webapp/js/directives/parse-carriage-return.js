'use strict';

define(['./module'], function(module) {
    module.directive('parseCarriageReturn', function() {
        return {
            restrict: 'A',
            require: 'ngModel',
            link: function(scope, element, attrs, ngModel) {
                ngModel.$parsers.push(function(value) {
                    if (!value) return value;
                    return value.split('\n');
                });
            }
        };
    });
});