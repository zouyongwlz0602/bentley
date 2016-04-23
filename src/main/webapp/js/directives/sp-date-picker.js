'use strict';

define(['./module'], function(module) {
    module.directive('spDatePicker', ['$compile', '$timeout', '$filter', function($compile, $timeout, $filter) {
        return {
            restrict: 'A',
            scope: {
                value: '=ngModel',
                view: '@?'
            },
            link: function($scope, $element) {
                var popoverHidden = true;
                var EVENT_NAME = 'mousedown';
                function initPopover(){
                    var content = $compile('<div style="width:220px;height:290px" date-picker="value" ng-attr-min-view="{{view || \'date\'}}" ng-attr-view="{{view || \'date\'}}"/>')($scope);
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

                $scope.$on('setDate', tearDown);

                $scope.$on('$destroy', tearDown);

                $scope.$watch('value', function(newValue){
                    $element.val($filter('date')(newValue));
                })
            }
        };
    }]);
});
