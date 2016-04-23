"use strict";

define(['../module'], function(module) {

    module.controller("MaintenanceCtrl", ['$scope','$http',function($scope,$http){

        $scope.maintenanceList=[];
        $scope.errorMessage = '';
        initList();
        function initList (){
            $http.get("get/maintenanceList").then(function(data){
                if(data.data.success){
                    $scope.maintenanceList = data.data.data;

                }else{
                    $scope.errorMessage = '预约保养服务查询失败，请重新操作!';
                }
            });
        }

        $scope.handle = function(id){
            $http.get('handle/maintenance?id='+id).then(function(data){
                if(data.data.success){
                    $scope.errorMessage = '预约保养服务操作成功!';
                    initList();
                }else{
                    $scope.errorMessage = data.data.msg;
                }
            });
        }
    }]);

});

