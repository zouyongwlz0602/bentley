'use strict';

define(['./module'], function(module) {
    module.directive('catchEyeDrtv', ['$timeout', function($timeout) {
        return {
            restrict: 'A',
            scope: {
                context: '=catchEyeDrtv',
                shakeClass: '@?'
            },
            link: function($scope, $element) {
                if($scope.context.eyeCatching){
                    if($scope.shakeClass){
                        var className = $scope.shakeClass + ' shake-constant shake-constant--hover';
                        $element.addClass(className);
                        $timeout(function(){
                            $element.removeClass(className);
                        }, 1300);
                    }

                    $element.find('input').focus();
                    $scope.context.eyeCatching = false;
                }
            }
        };
    }]);
});
