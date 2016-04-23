'use strict';

define(['./module'], function(module) {
    module.filter('setDecimal', function () {
        return function (input, places) {
            if (isNaN(input)) return '-';
            if (input == Infinity) return '-';
            var factor = "1" + Array(+(places > 0 && places + 1)).join("0");
            return Math.round(input * factor) / factor;
        };
    });
});