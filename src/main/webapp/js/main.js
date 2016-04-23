'use strict';

require.config({
    paths: {
        'jquery': '/assets/ace/js/jquery-2.0.3.min',
        'bootstrap': '/assets/ace/js/bootstrap.min',
        'angular': '/assets/angular/angular',
        'angularSanitize': '/assets/angular/angular-sanitize.min',
        'ngLodash': '/assets/angular/ng-lodash.min',
        'ace': '/assets/ace/js/ace',
        'angular-locale': '/assets/angular/angular-locale_zh-cn',
        'chart': '/assets/angular-chart/js/chart',
        'ace-elements': '/assets/ace/js/ace-elements.min',
        'ace-extra': '/assets/ace/js/ace-extra.min',
        'lodash': '/assets/js/lodash.min',
        'lodash-mixins': '/assets/js/lodash-mixins',
        'smart-table': '/assets/angular/smart-table.min',
        'angular-ui-router': '/assets/angular/angular-ui-router.min',
        'angular-breadcrumb': '/assets/angular/angular-breadcrumb.min',
        'angular-ui-select': '/assets/angular/angular-ui-select.patch.min',
        'monospaced.elastic': '/assets/angular/angular-ui-elastic',
        'angular-ui-validate': '/assets/angular/angular-ui-validate.min',
        'angular-pattern-restrict': '/assets/angular/angular-pattern-restrict',
        'angular-ui-bootstrap': '/assets/angular/angular-ui-bootstrap-tpls-0.13.1.min',
        'ui-grid': '/assets/angular/ui-grid.min',
        'angular-growl': '/assets/angular/angular-growl.min',
        'angular-chart': '/assets/angular-chart/js/angular-chart.min',
        'datePicker': '/assets/angular/angular-datepicker.min',
        'bootstrap-timepicker': '/assets/ace/js/date-time/bootstrap-timepicker.min',
        'bootstrap-colorpicker': '/assets/ace/js/bootstrap-colorpicker.min',
        'chosen': '/assets/ace/js/chosen.jquery.min',
        'jquery-ui': '/assets/ace/js/jquery-ui-1.10.3.custom.min',
        'bootbox': '/assets/ace/js/bootbox.min',
        'angular-busy': '/assets/angular/angular-busy.min',
        'echarts': '/assets/echarts/echarts-all',
        'qrcode': '/assets/js/qrcode',
        'qrcode_UTF8': '/assets/js/qrcode_UTF8',
        'angular-qrcode': '/assets/angular/angular-qrcode'
    },
    shim: {
        'bootstrap': ['jquery'],
        'angular': {deps: ['jquery'], exports: 'angular'},
        'angularSanitize': ['angular'],
        'ngLodash': ['angular'],
        'lodash-mixins': ['lodash'],
        'smart-table': ['angular'],
        'angular-ui-router': ['angular'],
        'angular-breadcrumb': ['angular'],
        'angular-ui-select': ['angular'],
        'monospaced.elastic': ['angular'],
        'angular-ui-validate': ['angular'],
        'angular-pattern-restrict': ['angular'],
        'angular-ui-bootstrap': ['angular'],
        'ui-grid': ['angular'],
        'angular-growl': ['angular'],
        'angular-chart': ['angular'],
        'angular-locale': ['angular'],
        'datePicker': ['angular', 'ngLodash', 'angular-locale'],
        'ace': ['ace-elements'],
        'ace-elements': ['ace-extra'],
        'ace-extra': ['bootstrap'],
        'angular-busy': ['angular'],
        'bootstrap-timepicker': ['bootstrap'],
        'bootstrap-colorpicker': ['bootstrap'],
        'chosen': ['jquery'],
        'jquery-ui': ['jquery'],
        'bootbox': ['bootstrap'],
        'qrcode_UTF8': ['qrcode'],
        'angular-qrcode': ['angular', 'qrcode', 'qrcode_UTF8']
    }
});

require(['angular', 'ace', 'app', 'routes'], function(angular) {
        angular.element().ready(function() {
            angular.bootstrap(document, ['bentleyApp']);
        });
    }
);
