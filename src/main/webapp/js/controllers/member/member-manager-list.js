"use strict";

define(['../module'], function(module) {

    module.controller("MemberManagerCtrl", ['$scope','$http',function($scope,$http){

        $scope.memberList=[];
        $http.get("get/memberList").then(function(data){
            if(data.data.success){
                $scope.memberList = data.data.data;
            }else{
                $.messager.alert('会员用户','会员查询失败，请重新操作!','error');
            }
        });
    }]);

});


