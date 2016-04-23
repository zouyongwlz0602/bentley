"use strict";

define(['../module'], function(module) {

    module.controller("MemberEditCtrl", ['$scope','$http','$stateParams',function($scope,$http,$stateParams){

        $scope.member={};
        $scope.errorMessage = '';

        $scope.radioArray =[
            {
                code:1,
                name:'是'

            },
            {
                code:0,
                name:'否'
            }];
        $http.get("get/member?id="+$stateParams.id).then(function(data){
            if(data.data.success){
                $scope.member = data.data.data;
            }else{
                $scope.errorMessage = '会员信息查询失败，请重新操作!';
            }
        });

        $scope.updateMember = function(){
            $http.post('update/member',$scope.member).then(function(data){
                if(data.data.success){
                    $scope.member = data.data.data;
                    $scope.errorMessage = '会员信息更新成功';
                }else{
                    $scope.errorMessage = '会员信息更新失败，请重新操作!';
                }
            });
        }
    }]);

});


