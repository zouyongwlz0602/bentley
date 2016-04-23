"use strict";

define(['../module'], function(module) {

    module.controller("WaxingCtrl", ['$scope','$http',function($scope,$http){

        $scope.waxingList=[];
        $scope.errorMessage = '';
        initList();
        function initList (){
            $http.get("get/waxingList").then(function(data){
                if(data.data.success){
                    $scope.waxingList = data.data.data;
                }else{
                    $scope.errorMessage = '打蜡服务查询失败，请重新操作!';
                }
            });
        }

        $scope.handle = function(id){
            $http.get('handle/waxing?id='+id).then(function(data){
                if(data.data.success){
                    $scope.errorMessage = '打蜡服务操作成功!';
                    initList();
                }else{
                    $scope.errorMessage = data.data.msg;
                }
            });
        }
    }]);

});

