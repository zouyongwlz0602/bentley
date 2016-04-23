'use strict';

define(['../module', 'echarts'], function(module) {
    module.directive('echarts', function() {
        return {
            restrict: 'EA',
            controller: function($scope, $element) {
                var chart = echarts.init($element[0]);
                this.setChartOption = function(option) {
                    chart.setOption(option);
                };
            }
        };
    });
});