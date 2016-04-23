
"use strict";

define(['../module'], function(module) {

    module.controller("ClubDynamicFormCtrl", ['$scope','$http',function($scope,$http){
        $scope.clubDynamic={};
        $scope.saveClubDynamic = function (){
            $http.post("save/clubDynamic",$scope.clubDynamic).then(function(data){
                if(data.data.success){
                    $scope.clubDynamic = data.data.data;
                    $scope.errorMessage = '俱乐部动态保存成功!';
                    $.messager.alert('俱乐部动态','俱乐部动态保存成功!','info');
                }else{
                    $scope.errorMessage = '俱乐部动态查询失败，请重新操作!';
                }
            });
        }
    }]);

});

