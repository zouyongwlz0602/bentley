'use strict';

define(['./module', 'lodash'], function(module, _) {
    module.filter('propsFilter', function() {
        return function(items, props) {
            if (_.isEmpty(items)) return [];
            var pairs = _.pairs(props);
            return _.filter(items, function(item) {
                return _.any(pairs, function(pair) {
                    return _.startsWith(item[pair[0]].toString().toLowerCase(), pair[1].toLowerCase());
                });
            });
        }
    });
});