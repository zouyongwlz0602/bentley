'use strict';

define(['./module', 'bootstrap-colorpicker'], function(module) {
    module.directive('spColourPicker', ['$compile', function($compile) {
        return {
            restrict: 'A',
            require: 'ngModel',
            scope: false,
            link: function($scope, $element, attrs, model) {
                $element.colorpicker().colorpicker().on('changeColor.colorpicker', function(event){
                    $scope.$apply(function() {
                        model.$setViewValue(event.color.toHex());
                    });
                });;
            }
        };
    }]);
});
