"use strict";

define(['../module'], function(module) {

    module.controller("RescueCtrl", ['$scope','$http',function($scope,$http){

        $scope.rescueList=[];
        $scope.errorMessage = '';
        initList();
        function initList (){
            $http.get("get/rescueList").then(function(data){
                if(data.data.success){
                    $scope.rescueList = data.data.data;
                }else{
                    $scope.errorMessage = '市内救援服务查询失败，请重新操作!';
                }
            });
        }

        $scope.handle = function(id){
            $http.get('handle/rescue?id='+id).then(function(data){
                if(data.data.success){
                    $scope.errorMessage = '市内救援服务操作成功!';
                    initList();
                }else{
                    $scope.errorMessage = data.data.msg;
                }
            });
        }
    }]);

});


