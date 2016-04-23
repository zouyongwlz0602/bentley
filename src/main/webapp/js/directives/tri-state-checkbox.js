'use strict';

define(['./module', 'angular', 'lodash'], function(module, angular, _) {
    module.directive('triStateCheckbox', function() {
        return {
            replace: true,
            restrict: 'E',
            scope: {
                checkboxes: '=',
                selectedProp: '@?',
                disabledProp: '@?',
                disabled: '&?',
                innerText: '@?',
                sort: '@?',
                onChange: '&?'
            },
            templateUrl: '/pages/templates/tri-state-checkbox.html',
            controller: function($scope, $element) {
                var isSelected = angular.isDefined($scope.selectedProp) ? $scope.selectedProp : 'isSelected';
                var isDisabled = function(x) {
                    return angular.isDefined($scope.disabledProp) && x[$scope.disabledProp] ||
                        angular.isDefined($scope.disabled) && $scope.disabled({checkbox: x});
                };
                $scope.masterChange = function() {
                    if ($scope.master) {
                        angular.forEach($scope.checkboxes, function(cb) {
                            if (!isDisabled(cb)) {
                                cb[isSelected] = true;
                            }
                        });
                    } else {
                        angular.forEach($scope.checkboxes, function(cb) {
                            if (!isDisabled(cb)) {
                                cb[isSelected] = false;
                            }
                        });
                    }
                    if ($scope.onChange) {
                        $scope.onChange();
                    }
                };
                var indeterminate = function(value) {
                    angular.element($element.find('input')[0]).prop('indeterminate', value);
                };
                $scope.$watch('checkboxes', function() {
                    var allSet = true, allClear = true;
                    angular.forEach(_.reject($scope.checkboxes, isDisabled), function(cb) {
                        if (cb[isSelected]) {
                            allClear = false;
                        } else {
                            allSet = false;
                        }
                    });
                    if (allClear) {
                        $scope.master = false;
                        indeterminate(false);
                    }
                    else if (allSet) {
                        $scope.master = true;
                        indeterminate(false);
                    }
                    else {
                        $scope.master = false;
                        indeterminate(true);
                    }
                });
            }
        };
    });
});