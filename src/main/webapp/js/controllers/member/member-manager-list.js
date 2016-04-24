"use strict";

define(['../module'], function(module) {

    module.controller("MemberManagerCtrl", ['$scope','$http',function($scope,$http){

        $scope.memberList=[];
        $http.get("get/memberList").then(function(data){
            if(data.data.success){
                $scope.memberList = data.data.data;
            }else{
                $scope.errorMessage ='会员查询失败，请重新操作';
            }
        });
    }]);

});


