'use strict';

define([
        'angular',
        'angularSanitize',
        'angular-ui-router',
        'angular-breadcrumb',
        'angular-ui-select',
        'smart-table',
        'monospaced.elastic',
        'angular-ui-validate',
        'angular-pattern-restrict',
        'angular-ui-bootstrap',
        'angular-growl',
        'angular-chart',
        'ui-grid',
        'datePicker',
        'lodash',
        'ngLodash',
        'angular-busy',
        'angular-qrcode',
        'lodash-mixins',
        'controllers/index',
        'directives/index',
        'filters/index',
        'services/index'],
    function(angular) {
        return angular.module('bentleyApp', [
            'ngSanitize',
            'ngLodash',
            'ui.router',
            'ncy-angular-breadcrumb',
            'ui.select',
            'smart-table',
            'monospaced.elastic',
            'ui.validate',
            'ngPatternRestrict',
            'ui.bootstrap',
            'angular-growl',
            'chart.js',
            'ui.grid',
            'datePicker',
            'cgBusy',
            'monospaced.qrcode',
            'salesPortal.controllers',
            'salesPortal.directives',
            'salesPortal.filters',
            'bentley.services']
        ).config(['$httpProvider', 'uiSelectConfig', 'growlProvider', '$breadcrumbProvider', function($httpProvider, uiSelectConfig, growlProvider, $breadcrumbProvider) {
                $httpProvider.interceptors.push('HttpInterceptor');
                uiSelectConfig.theme = 'bootstrap';
                growlProvider.globalTimeToLive(10000);
                growlProvider.globalDisableCountDown(false);
                $breadcrumbProvider.setOptions({
                    //prefixStateName: 'home'
                });
            }])
            .value('cgBusyDefaults', {
                message: '加载中...'
            })
            .run(['$templateCache', '$rootScope', '$state', function($templateCache, $rootScope, $state) {
                $rootScope.isState = function(state) {
                    return $state.includes(state);
                };
                $rootScope.stateEquals = function(state) {
                    return $state.is(state);
                };
                $templateCache.put('template/accordion/accordion-group.html', '\
                <div class="panel panel-primary">\
                    <div class="panel-heading">\
                        <h4 class="panel-title">\
                           <a href="#" class="accordion-toggle" accordion-transclude="heading" ng-click="$event.preventDefault(); toggleOpen()">\
                               {{heading}}\
                               <i class="pull-right" ng-class="{\'icon-chevron-down\':isOpen,\'icon-chevron-right\':!isOpen}"></i>\
                            </a>\
                        </h4>\
                    </div>\
                    <div class="panel-collapse collapse" collapse="!isOpen">\
                        <div class="panel-body" ng-transclude></div>\
                    </div>\
                </div>'
                );
                $templateCache.put('bootstrap/match.tpl.html', '\
                <div class="ui-select-match" ng-hide="$select.open" ng-disabled="$select.disabled">\
                    <span tabindex="-1"\
                        class="form-control ui-select-toggle"\
                        aria-label="{{ $select.baseTitle }} activate"\
                        ng-disabled="$select.disabled"\
                        ng-click="$select.activate()"\
                        style="outline: 0;">\
                        <span ng-show="$select.isEmpty()" class="ui-select-placeholder text-muted">{{$select.placeholder}}</span>\
                        <span ng-hide="$select.isEmpty()" class="ui-select-match-text pull-left" ng-class="{\'ui-select-allow-clear\': $select.allowClear && !$select.isEmpty()}" ng-transclude=""></span>\
                        <i class="caret pull-right" ng-click="$select.toggle($event)"></i>\
                        <i ng-show="$select.allowClear && !$select.isEmpty()" aria-label="{{ $select.baseTitle }} clear" style="margin-right: 10px"\
                            ng-click="$select.clear($event)" class="icon-remove-sign clickable desc"></i>\
                    </span>\
                </div>\
                ');
                $templateCache.put('bootstrap/match-multiple.tpl.html','\
                <span class="ui-select-match">\
                    <span ng-repeat="$item in $select.selected">\
                        <span class="ui-select-match-item btn btn-primary btn-xs" tabindex="-1" ng-disabled="$select.disabled"\
                              ng-click="$selectMultiple.activeMatchIndex = $index;" ng-class="{\'btn-primary\':$selectMultiple.activeMatchIndex === $index, \'select-locked\':$select.isLocked(this, $index)}"\
                              ui-select-sort="$select.selected">\
                              <span uis-transclude-append="\"></span>\
                              <i class="icon-remove-sign clickable desc" ng-hide="$select.disabled" ng-click="$selectMultiple.removeChoice($index)"></i>\
                        </span>\
                    </span>\
                </span>');
            }]);
    });
