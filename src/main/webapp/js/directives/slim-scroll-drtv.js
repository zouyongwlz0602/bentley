'use strict';

define(['./module', 'slim-scroll'], function(module) {
    module.directive('uiGridViewport', ['$timeout', function($timeout) {
        return {
            restrict: 'C',
            scope: false,
            link: function($scope, $element) {
                /*var selector = $scope.selector;
                var target = $element;
                if(selector){
                    target = $element.find(selector);
                }*/
                $element.slimScroll({
                    height: '50px'
                });
            }
        };
    }]);
});