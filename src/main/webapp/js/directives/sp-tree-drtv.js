'use strict';

define(['./module'], function(module) {
    module.directive('spTreeDrtv', [function() {
        return {
            restrict: 'E',
            scope: {
                parent: '=',
                helper: '='
            },
            templateUrl: '/pages/templates/sp-tree-tmpl.html',
            link: function($scope, $element) {
                $scope.nodes = $scope.helper.getChildren($scope.parent);
            },
            replace: true
        };
    }]);
});
