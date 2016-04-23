'use strict';

define(['./module'], function(module) {
    module.directive('spDateRange', ['$compile', '$timeout', '$filter', function($compile, $timeout, $filter) {
        return {
            restrict: 'A',
            scope: {
                startDate: '=',
                endDate: '='
            },
            link: function($scope, $element) {
                var popoverHidden = true;
                var EVENT_NAME = 'mousedown';
                function initPopover(){
                    var content = $compile('<div class="bottom-toolbars"><label class="date-range-header">起始时间</label><label class="date-range-header">结束时间</label><div date-range start="startDate" end="endDate"></div><div class="confirmation"><button class="btn btn-primary" ng-click="doneClickHandler()">确认</button> <button class="btn btn-cancel" ng-click="cancelClickHandler()">重置</button></div></div>')($scope);
                    $element.popover({
                        content: content,
                        placement: 'bottom',
                        container: 'body',
                        animation: true,
                        trigger: 'manual',
                        html: true
                    }).on('shown.bs.popover', function(){
                        _.defer(function(){
                            $('body').on(EVENT_NAME, blurHandler);
                            $('.popover').on(EVENT_NAME, hijackIt);
                        });
                    });
                }
                function tearDown(){
                    if(!popoverHidden){
                        $element.popover('toggle');
                    }
                    $('body').off(EVENT_NAME, blurHandler);
                    $('.popover').off(EVENT_NAME, hijackIt);
                    $element.popover('destroy');
                    popoverHidden = true;
                }
                function hijackIt(event){
                    event.stopPropagation();
                }
                function blurHandler(){
                    tearDown();
                };
                if($scope.startDate && $scope.endDate){
                    var dateFilter = $filter('date');
                    $element.val(dateFilter($scope.startDate) + ' 至 ' + dateFilter($scope.endDate));
                }

                $element.bind(EVENT_NAME, function (e) {
                    if(popoverHidden){
                        initPopover();
                        $timeout(function(){
                            $element.popover('toggle');
                        }, 0);
                        popoverHidden = false;
                    }
                    else{
                        tearDown();
                    }
                    e.preventDefault();
                });

                $scope.doneClickHandler = function(){
                    var dateFilter = $filter('date');
                    $element.val(dateFilter($scope.startDate) + ' 至 ' + dateFilter($scope.endDate));
                    $scope.$emit('spDateRangeUpdate');
                    tearDown();
                };
                $scope.cancelClickHandler = function(){
                    $scope.startDate = null;
                    $scope.endDate = null;
                    $element.val('');
                    tearDown();
                };

                $scope.$on('$destroy', tearDown);
            }
        };
    }]);
});
