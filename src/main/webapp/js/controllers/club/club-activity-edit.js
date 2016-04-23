"use strict";

define(['../module'], function(module) {

    module.controller("ClubActivityEditCtrl", ['$scope','$http','$stateParams',function($scope,$http,$stateParams){
        $scope.clubActivity={};
        $http.get("get/clubActivity?id="+$stateParams.id).then(function(data){
            if(data.data.success){
                $scope.clubActivity = data.data.data;
            }else{
                $scope.errorMessage = '会员活动查询失败，请重新操作!';
            }
        });

        $scope.updateClubActivity = function(){
            $http.post('update/clubActivity',$scope.clubActivity).then(function(data){
                if(data.data.success){
                    $scope.clubActivity = data.data.data;
                    $scope.errorMessage = '会员活动更新成功!';
                }else{
                    $scope.errorMessage = '会员活动更新失败，请重新操作!';
                }
            });
        }
    }]);

});

