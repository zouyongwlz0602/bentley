'use strict';

define(['./module'], function(module) {
    module.directive('spTreeNodeDrtv', ['$compile', '$timeout', function($compile, $timeout){
        return {
            restrict: 'E',
            scope: {
                node: '=',
                helper: '='
            },
            templateUrl: '/pages/templates/sp-tree-node-tmpl.html',
            link: function($scope, $element) {
                $scope.label = $scope.helper.getLabel($scope.node);
                $scope.children = $scope.helper.getChildren($scope.node);

                var createChildren = _.once(function(){
                    var childrenElement = $compile('<sp-tree-drtv parent="node" helper="helper" ng-if="node.expanded"></sp-tree-drtv>')($scope)
                    $element.append(childrenElement);
                });

                $scope.toggleChildren = function(){
                    $scope.node.expanded = !$scope.node.expanded;
                    if($scope.node.expanded){
                        createChildren();
                        $timeout(function(){$scope.$broadcast('expand-tree', $scope.helper.getValue($scope.node), $scope.node.status)});
                    }
                };
                $scope.nodeCheckHandler = function(){
                    $scope.$broadcast('suppress-nodes', $scope.helper.getValue($scope.node));
                    $timeout(function(){
                        $scope.node.expanded = false;
                    });
                    $scope.node.status = $scope.node.status === 1 ? -1 : 1;
                    $scope.$emit('report-node', $scope.helper.getValue($scope.node), $scope.node.status);
                };
                $scope.$on('expand-tree', function(event, value, status){
                    var myValue = $scope.helper.getValue($scope.node);
                    if(myValue !== value){
                        event.preventDefault();
                        if(status === 1){
                            $scope.node.status = 1;
                        }

                        if($scope.node.expanded){
                            createChildren();
                            $timeout(function(){$scope.$broadcast('expand-tree', myValue, $scope.node.status)});
                        }
                    }
                });
                $scope.$on('suppress-nodes', function(event, value){
                    if($scope.helper.getValue($scope.node) !== value){
                        if($scope.node.status !== 0 && $scope.node.expanded !== true){
                            event.preventDefault();
                        }
                        $scope.helper.clearNode($scope.node);
                    }
                });
                $scope.$on('report-node', function(event, value, status){
                    if($scope.helper.getValue($scope.node) !== value){
                        var identical = (status === 1 || status === -1) &&
                            _.every($scope.children, function(child){
                                return status === child.status;
                            });

                        var newStatus = identical ? status : 0;
                        if(newStatus === $scope.node.status){
                            event.stopPropagation();
                        }
                        $scope.node.status = newStatus;
                    }
                });
                $scope.helper.emitOnce($scope, 'node-created');
            },
            replace: true
        };
    }]);
});
