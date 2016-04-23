'use strict';

define(['./module', 'lodash', 'bootstrap-timepicker'], function(module, _) {
    module.directive('spTimePicker', [function() {
        return {
            restrict: 'A',
            templateUrl: '/pages/templates/sp-time-picker-tmpl.html',
            scope: {
                time: '=spTimePicker',
                readOnly: '=?'
            },
            link: function($scope, $element) {
                var defaultTime = $scope.time || '0:0:0';
                var inputElement = $element.find('input');
                inputElement.timepicker({
                    defaultTime: defaultTime,
                    minuteStep: 1,
                    secondStep: 1,
                    showSeconds: true,
                    showMeridian: false
                }).on('changeTime.timepicker', function(evt){
                    $scope.$emit('spTimePickerUpdate');
                    $scope.time = evt.time.value;
                });
                $scope.timeIconClickHandler = function(){
                    inputElement.focus();
                };
                $scope.$watch('time', function(newValue){
                    if(newValue){
                        inputElement.val(newValue);
                    }
                });
            }
        };
    }]);
});
