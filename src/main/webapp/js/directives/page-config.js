'use strict';

define(['./module', 'lodash'], function(module, _) {
    module.directive('pageConfig', ['PageConfigService' ,function( PageConfigService) {
        return {
            restrict: 'E',
            link: function(scope) {
                PageConfigService.configInfo().then(function(data) {
                    scope.configInfo = data;
                });
            }
        };
    }]);
});