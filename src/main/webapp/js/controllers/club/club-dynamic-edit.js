"use strict";

define(['../module'], function(module) {

    module.controller("ClubDynamicEditCtrl", ['$scope','$http','$stateParams',function($scope,$http,$stateParams){
        $scope.clubDynamic={};
        $scope.errorMessage = '';
        $http.get("get/clubDynamic?id="+$stateParams.id).then(function(data){
            if(data.data.success){
                $scope.clubDynamic = data.data.data;
            }else{
                $scope.errorMessage = '俱乐部动态查询失败，请重新操作!';
            }
        });

        $scope.updateClubDynamic = function(){
            $http.post('update/clubDynamic',$scope.clubDynamic).then(function(data){
                if(data.data.success){
                    $scope.clubDynamic = data.data.data;
                    $scope.errorMessage = '俱乐部动态更新成功!';
                }else{
                    $scope.errorMessage = '俱乐部动态更新失败!';
                }
            });
        }
    }]);

});

