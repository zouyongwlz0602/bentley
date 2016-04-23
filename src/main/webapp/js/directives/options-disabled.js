'use strict';

define(['./module'], function(module) {
    module.directive('optionsDisabled', ['$parse', function($parse) {
        var disableOptions = function($scope, attr, $element, data, fnDisableIfTrue) {
            $element.find('option:not([value="?"])').each(function(i, e) { //1
                var locals = {};
                locals[attr] = data[i];
                $(this).attr('disabled', fnDisableIfTrue($scope, locals));
            });
        };
        return {
            priority: 0,
            require: 'ngModel',
            link: function($scope, $element, attributes) { //2
                var expElements = attributes.optionsDisabled.match(/^\s*(.+)\s+for\s+(.+)\s+in\s+(.+)?\s*/),
                    attrToWatch = expElements[3],
                    fnDisableIfTrue = $parse(expElements[1]);
                $scope.$watch(attrToWatch, function(newValue, oldValue) {
                    if (!newValue) return;

                    disableOptions($scope, expElements[2], $element, newValue, fnDisableIfTrue);
                }, true);

                $scope.$watch(attributes.ngModel, function(newValue, oldValue) { //3
                    var disabledOptions = $parse(attrToWatch)($scope);
                    if (!newValue) return;

                    disableOptions($scope, expElements[2], $element, disabledOptions, fnDisableIfTrue);
                });
            }
        };
    }]);
});