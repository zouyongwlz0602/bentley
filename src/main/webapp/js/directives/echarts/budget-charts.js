'use strict';

define(['../module', 'lodash'], function(module, _) {
    module.directive('consumeProgressChart', ['$filter', function($filter) {
        return {
            restrict: 'A',
            require: 'echarts',
            scope: {
                config: '=?',
                data: '='
            },
            link: function(scope, elem, attrs, echarts) {
                scope.$watch(function() {
                    return scope.data;
                }, function() {
                    if (!scope.data) return;
                    scope.config = scope.config || {};
                    var smartNumber = $filter('smartNumber');
                    var config = _.extend({
                        baseRadius: 80,
                        itemWidth: 10
                    }, scope.config);
                    var data = scope.data;
                    var baseRadius = config.baseRadius;
                    var itemWidth = config.itemWidth;
                    var dataStyle = {
                        normal: {
                            label: {show: false},
                            labelLine: {show: false}
                        }
                    };
                    var placeHolderStyle = {
                        normal: {
                            color: 'rgba(200,200,200,0.5)',
                            label: {show: false},
                            labelLine: {show: false}
                        },
                        emphasis: {
                            color: 'rgba(200,200,200,0.5)'
                        }
                    };
                    var innerPlaceHolderStyle = {
                        normal: {
                            color: 'rgba(0,0,0,0)',
                            label: {show: false},
                            labelLine: {show: false}
                        },
                        emphasis: {
                            color: 'rgba(0,0,0,0)'
                        }
                    };
                    var total = data[0];
                    var series = _.map(_.tail(data), function(obj, index) {
                        return {
                            name: '' + (index + 1),
                            type: 'pie',
                            clockWise: true,
                            radius: [baseRadius - itemWidth * (index + 1), baseRadius - itemWidth * index],
                            itemStyle: dataStyle,
                            data: [{
                                name: obj.name,
                                value: obj.value,
                                total: data[index].value
                            }, {
                                name: 'invisible',
                                value: (index == 0 && total.value == 0) ? 1 : total.value - obj.value, // 为了让总数为0时也显示圆环
                                itemStyle: index == 0 ? placeHolderStyle : innerPlaceHolderStyle,
                                tooltip: {show: false}
                            }]
                        };
                    });
                    var option = {
                        title: {
                            text: data[0].name + ':\n' + smartNumber(data[0].value),
                            x: 'center',
                            y: 'center',
                            itemGap: 10,
                            textStyle: {
                                color: 'rgba(30,144,255,0.8)',
                                fontSize: 12,
                                fontWeight: 'bolder'
                            }
                        },
                        tooltip: {
                            show: true,
                            formatter: function(params) {
                                if (!params.data || !params.data.total) return '';
                                var percent = params.value / params.data.total;
                                return params.name + ':' + smartNumber(params.value) + '(' + smartNumber(percent * 100) + '%)';
                            }
                        },
                        legend: {
                            orient: 'horizontal',
                            x: 'center',
                            y: 'bottom',
                            itemGap: 6,
                            data: _.pluck(_.tail(data), 'name')
                        },
                        series: series
                    };
                    echarts.setChartOption(option);
                });
            }
        };
    }]);
});