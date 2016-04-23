/**
 * Created by Sunday on 15/9/9.
 */

'use strict';

define(['./module', 'lodash'], function(module, _) {
    var modalComponentDirective = function($modal, growl, CityService, DepartmentService, RoleService) {
        return {
            restrict: 'A',
            scope: {
                select: '=?',
                drop: '=?',
                result: '=',
                maxLevel: "@?",
                branchId: "@?",
                modalComponent: '@'
            },
            controller: function($scope, $element) {
                var controller, templateUrl, resolve;

                switch ($scope.modalComponent) {
                    case 'city' :
                        controller = 'CityComponentCtrl';
                        templateUrl = '/pages/components/city.html';
                        var initial;
                        switch (typeof $scope.select) {
                            case 'object':
                                if (angular.isArray($scope.select)) {
                                    if ($scope.select.length > 0) {
                                        if ((typeof $scope.select[0]) == "object") {
                                            initial = $scope.select;
                                        } else if ((typeof $scope.select[0] == "number")) {
                                            initial = [];
                                            for (var i = 0; i < $scope.select.length; i++) {
                                                initial.push({id: $scope.select[i]});
                                            }
                                        }
                                    } else {
                                        initial = $scope.select;
                                    }
                                } else {
                                    initial = new Array($scope.select);
                                }
                                initial.dropCity = $scope.drop;
                                break;
                            case 'string':
                                var selectString = $scope.select.replace(/\s/g, '');
                                selectString = selectString.replace(/，/g, ',');
                                var selectList = selectString.split(',');
                                var cityList;
                                break;
                            default :
                                initial = [];
                                initial.dropCity = $scope.drop;
                                break;
                        }
                        var selectedCities = angular.copy(initial);
                        delete selectedCities.dropCity;
                        CityService.getCities(selectedCities).then(function (data) {
                            $scope.result = data;
                        });
                        break;

                    case 'role' :
                        controller = 'RoleComponentCtrl';
                        templateUrl = '/pages/components/role.html';
                        //RoleService.getRoleByIds($scope.select).then(function (data) {
                        //    $scope.result = data;
                        //});
                        break;

                    case 'department' :
                        controller = 'DepartmentComponentCtrl';
                        templateUrl = '/pages/components/department.html';
                        break;
                }

                var openModal = function(e) {
                    switch ($scope.modalComponent) {
                        case 'city' :
                            e.preventDefault();
                            resolve = {
                                init: function() {
                                    return initial;
                                }
                            };
                            break;
                        case 'role' :
                            e.preventDefault();
                            resolve = {
                                init: function() {
                                    return $scope.select;
                                }
                            };
                            break;
                        case 'department' :
                            resolve = {
                                selectedId: function() {
                                    return $scope.select;
                                },
                                maxLevel: function() {
                                    return $scope.maxLevel;
                                },
                                branchId: function() {
                                    return $scope.branchId;
                                }
                            };
                            break;
                        default :
                            growl.error("非法组件类型" + $scope.modalComponent);
                    }
                    var modalInstance = $modal.open({
                        animation: false,
                        backdrop: false,
                        templateUrl: templateUrl,
                        controller: controller,
                        resolve: resolve
                    });
                    modalInstance.result.then(function(data) {
                        $scope.result = data;
                        switch ($scope.modalComponent) {
                            case 'city':
                                if (angular.isArray($scope.select)) {
                                    if ($scope.select.length > 0) {
                                        if (typeof $scope.select[0] == "object") {
                                            $scope.select = $scope.result;
                                        } else {
                                            $scope.select = _.pluck($scope.result, "id");
                                        }
                                    } else {
                                        $scope.select = _.pluck($scope.result, "id");
                                    }
                                } else {
                                    $scope.select = _.pluck($scope.result, "id");
                                }
                                break;
                        }
                        $scope.$emit('modelChange',data);
                    });
                };

                $element.bind('mousedown', openModal);
            }
        }
    };

    module.directive('modalComponent', ['$modal', 'growl', 'CityService', 'DepartmentService', 'RoleService', modalComponentDirective]);
});