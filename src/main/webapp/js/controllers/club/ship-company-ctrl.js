"use strict";

define(['../module'], function(module) {

    module.controller("ShipCompanyCtrl", ['$scope','$http',function($scope,$http){
        $scope.clubSummary={};
        $scope.errorMessage='';

        $http.get("get/summary").then(function(data){
            if(data.data.success){
                $scope.clubSummary = data.data.data;
            }else{
                $.messager.alert('简介保存','简介查询失败，请重新操作!','error');
            }
        });

        $scope.updateSummary = function(){

            $http.post("update/summary",$scope.clubSummary).then(function(data){
                if(data.data.success){
                    $scope.clubSummary = data.data.data;
                    $scope.errorMessage='俱乐部简介保存成功';
                }else{
                    $scope.errorMessage='';
                }


            });
        }
    }]);

});

