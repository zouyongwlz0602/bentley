"use strict";

define(['../module'], function(module) {

    module.controller("WashCarCtrl", ['$scope','$http',function($scope,$http){

        $scope.washCarList=[];
        $scope.errorMessage = '';
        initList();
        function initList (){
            $http.get("get/washCarList").then(function(data){
                if(data.data.success){
                    $scope.washCarList = data.data.data;
                }else{
                    $scope.errorMessage = '洗车服务查询失败，请重新操作!';
                }
            });
        }

        $scope.handle = function(id){
            $http.get('handle/washCar?id='+id).then(function(data){
                if(data.data.success){
                    $scope.errorMessage = '洗车服务操作成功!';
                    initList();
                }else{
                    $scope.errorMessage = data.data.msg;
                }
            });
        }
    }]);

});

