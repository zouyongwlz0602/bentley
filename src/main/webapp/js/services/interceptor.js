'use strict';

define(['./module'], function(module) {
    return module.factory('HttpInterceptor', ['$q', 'growl', function($q, growl) {
        return {
            request: function(config) {
                //if (config.url.indexOf('.html') < 0) {
                //    config.url = '/data' + config.url + ".json";
                //}
                return config;
            },
            requestError: function(rejection) {
                return $q.reject(rejection);
            },
            response: function(response) {
                return response;
            },
            responseError: function(rejection) {
                var error = '请求错误';
                if (rejection instanceof Object) {
                    if (rejection.status == 0 || Math.floor(rejection.status / 100) == 3) {
                        error = '您的会话已过期，请刷新页面，重新登录';
                    } else if (rejection.status == 403) {
                        error = '没有权限';
                    }
                } else if (rejection instanceof String) {
                    error = rejection;
                }
                growl.error(error);
                return $q.reject(rejection);
            }
        };
    }]);
});