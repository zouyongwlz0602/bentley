'use strict';

define(['./module', 'lodash'], function(module, _) {
    module.directive('loginAvatar', ['$window','$http',function($window,$http) {
        return {
            restrict: 'E',
            templateUrl: '/pages/templates/login-avatar.html',
            link: function(scope) {
                scope.user =undefined;
                scope.loginMessage = undefined;

                $http.get("/user").then(function(data){
                    if(data.data.success){
                        scope.user = data.data.data;
                        scope.loginMessage =undefined;
                    }
                });
                scope.logout = function() {
                    $http.get("/logout").then(function(data){
                        if(data.data.success){
                            scope.user =undefined;
                        }
                    });
                };
                scope.login = function(user) {
                    $http.post('/login',user).then(function(data){
                        if(data.data.success){
                            scope.user =data.data.data;
                        }else{
                            scope.loginMessage ='用户名或密码失败!';
                        }
                    });
                };
            }
        };
    }]);
});