"use strict";

define(['../module'], function(module) {

    module.controller("HotelDestineCtrl", ['$scope','$http',function($scope,$http){

        $scope.hotelDestineList=[];
        $scope.errorMessage = '';
        initList();
        function initList (){
            $http.get("get/hotelList").then(function(data){
                if(data.data.success){
                    $scope.hotelDestineList = data.data.data;
                }else{
                    $scope.errorMessage = '酒店预订查询失败，请重新操作!';
                }
            });
        }

        $scope.handle = function(id){
            $http.get('handle/hotel?id='+id).then(function(data){
                if(data.data.success){
                    $scope.errorMessage = '酒店预订处理成功!';
                    initList();
                }else{
                    $scope.errorMessage = '酒店预订处理失败,请重新操作!';
                }
            });
        }
    }]);

});

