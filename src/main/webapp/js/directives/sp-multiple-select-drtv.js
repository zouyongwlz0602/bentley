'use strict';

define(['./module', 'chosen'], function(module) {
    module.directive('spMultipleSelectDrtv', ['$compile', function($compile) {
        return {
            restrict: 'A',
            scope: {
                options: '=',
                labelKey: '@',
                valueKey: '@',
                ngModel: '='
            },
            link: function($scope, $element) {
                $scope.$watch('options', function(value){
                    if(value){
                        var content = '';
                        _.each(value, function(option){
                            content += '<option value=\"' + option[$scope.valueKey] + '\">' + option[$scope.labelKey] + '</option>';
                        })
                        var childrenElement = $compile(content)($scope);
                        $element.append(childrenElement);
                        $element.chosen({placeholder_text: '请选择'});
                    }
                });
                $scope.$watch('ngModel', _.once(function(value){
                    if(value){
                        $element.val(value);
                        $element.trigger('chosen:updated');
                    }
                }));
            }
        };
    }]);
});
