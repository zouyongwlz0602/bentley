'use strict';

define(['./module'], function(module) {
    module.directive('enrollHistoryTooltip', ['$compile', '$timeout', '$q', function($compile, $timeout, $q) {
        return {
            restrict: 'A',
            scope: {
                product: '=enrollHistoryTooltip',
                getHistoryData: '&',
                getHistoryContext: '='
            },
            link: function($scope, $element) {
                function loadPopover(){
                    $scope.getHistoryData()($q.defer(), $scope.product, $scope.getHistoryContext).then(function(result){
                        if(result.length > 0){
                            $scope.historyData = result;
                            $scope.getHistoryContext.dataType.sumHistoryData($scope);

                            var content = $compile($scope.getHistoryContext.dataType.historyTooltipTemplate)($scope);
                            $element.popover({
                                content: content,
                                placement: 'right',
                                container: 'body',
                                animation: true,
                                trigger: 'manual',
                                html: true
                            });
                        }
                        else{
                            $element.popover({
                                content: '<h5>无上期记录</h5>',
                                placement: 'right',
                                container: 'body',
                                animation: true,
                                trigger: 'manual',
                                html: true
                            });
                        }
                        $timeout(function(){
                            $element.popover('show');
                        }, 100);
                    })
                }
                function hidePopover(){
                    $element.popover('hide');
                }
                $element.bind('focus', loadPopover);
                $element.bind('blur', hidePopover);
                $scope.$on('$destroy', function handleDestroyEvent() {
                    $element.popover('destroy');
                    $element.unbind('focus', loadPopover);
                    $element.unbind('blur', hidePopover);
                });
            }
        };
    }]);
});
