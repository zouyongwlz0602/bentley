'use strict';

define(['lodash'], function(_) {
    _.mixin({
        watch: function($scope, expression, listener) {
            var _watch = function() {
                var _unwatch = $scope.$watch(expression, function(newVal, oldVal, scope) {
                    if (newVal !== oldVal) {
                        _unwatch();
                        listener(newVal, oldVal, scope);
                        _watch();
                    }
                });
            };
            _watch();
        },
        watchers: function(scope) {
            var watchers = (scope.$$watchers) ? scope.$$watchers.length : 0;
            var child = scope.$$childHead;
            while (child) {
                watchers += (child.$$watchers) ? child.$$watchers.length : 0;
                child = child.$$nextSibling;
            }
            return watchers;
        }
    });
});