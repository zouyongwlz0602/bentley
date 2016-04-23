"use strict";

define(['../module'], function(module) {

    module.controller("ClubActivityCtrl", ['$scope','$http',function($scope,$http){

        $scope.clubActivityList=[];

        $http.get("get/activityList").then(function(data){
            if(data.data.success){
                $scope.clubActivityList = data.data.data;
            }else{
                $.messager.alert('会员活动','会员活动查询失败，请重新操作!','error');
            }
        });
    }]);

});

