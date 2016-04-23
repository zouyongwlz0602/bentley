"use strict";

define(['../module'], function(module) {

    module.controller("YachtDestineCtrl", ['$scope','$http',function($scope,$http){

        $scope.yachtDestineList=[];
        $scope.errorMessage = '';
        initList();
        function initList (){
            $http.get("get/yachtList").then(function(data){
                if(data.data.success){
                    $scope.yachtDestineList = data.data.data;
                }else{
                    $scope.errorMessage = '游艇预订查询失败，请重新操作!';
                }
            });
        }

        $scope.handle = function(id){
            $http.get('handle/yacht?id='+id).then(function(data){
                if(data.data.success){
                    $scope.errorMessage = '游艇预订处理成功';
                    initList();
                }else{
                    $scope.errorMessage = '游艇预订处理失败，请重新操作!';
                }
            });
        }
    }]);

});

