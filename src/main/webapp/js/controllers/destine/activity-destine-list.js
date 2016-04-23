"use strict";

define(['../module'], function(module) {

    module.controller("ActivityDestineCtrl", ['$scope','$http',function($scope,$http){

        $scope.activityDestineList=[];
        $scope.errorMessage = '';
        initList();
        function initList (){
            $http.get("get/activityDestineList").then(function(data){
                if(data.data.success){
                    $scope.activityDestineList = data.data.data;
                }else{
                    $scope.errorMessage = '私人活动预订查询失败，请重新操作!';
                }
            });
        }
        $scope.handle = function(id){
            $http.get('handle/activityDestine?id='+id).then(function(data){
                if(data.data.success){
                    $scope.errorMessage = '私人活动预订处理成功';
                    initList();
                }else{
                    $scope.errorMessage = '私人活动预订处理失败，请重新操作!';
                }
            });
        }
    }]);

});

