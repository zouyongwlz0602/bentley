
"use strict";

define(['../module'], function(module) {

    module.controller("ClubDynamicCtrl", ['$scope','$http',function($scope,$http){
        $scope.clubDynamicList=[];
        $http.get("get/clubDynamicList").then(function(data){
            if(data.data.success){
                $scope.clubDynamicList = data.data.data;
            }else{
                $.messager.alert('俱乐部动态','俱乐部动态查询失败，请重新操作!','error');
            }
        });
    }]);

});


