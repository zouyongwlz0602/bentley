"use strict";

define(['../module'], function(module) {

    module.controller("GroupCtrl", ['$scope','$http','$stateParams',function($scope,$http,$stateParams){

        $scope.groupList=[];
        $scope.pageObj={};
        $scope.errorMessage = '';
        $scope.showFlag = false;


        $http.get("/groups").then(function(data){
                if(data.data.success){
                    $scope.showFlag = true;
                    $scope.groupList = data.data.data;
                }else{
                    $scope.showFlag = false;
                    $scope.errorMessage = '分组信息获取失败';
                }
        });



        $scope.sendMessage = function(){
            $http.get('/sendMessage?id='+$scope.pageObj.groupId+'&context='+$scope.pageObj.context).then(function(data){
                if(data.data.success){
                    $scope.member = data.data.data;
                    $scope.errorMessage = '消息发送成功！';
                }else{
                    $scope.errorMessage = '消息发送失败';
                }
            });
        }
    }]);

});


