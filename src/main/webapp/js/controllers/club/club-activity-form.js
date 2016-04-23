"use strict";

define(['../module'], function(module) {
    module.controller("ClubActivityFormCtrl", ['$scope','$http',function($scope,$http){
        $scope.clubActivity={};

        $scope.errorMessage = '';
        $scope.saveClubActivity = function (){
            $http.post("save/clubActivity",$scope.clubActivity).then(function(data){
                if(data.data.success){
                    $scope.clubActivity = data.data.data;
                    $scope.errorMessage = '会员活动保存成功!';
                }else{
                    $scope.errorMessage = '会员活动保存失败，请重新操作!';
                }
            });
        }
    }]);

});

