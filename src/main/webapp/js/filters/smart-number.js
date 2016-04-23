'use strict';

define(['./module'], function(module) {
    module.filter('smartNumber', ['$filter', function($filter) {
        return function(input) {
            if (isNaN(input)) return '-';
            if (input == Infinity) return '-';
            var n = $filter('number')(input, 2);
            return n.replace(/\.?0+$/, '');
        }
    }]);
});