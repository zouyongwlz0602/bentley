'use strict';

define(['./module'], function(module) {
    module.directive('spUploadDrtv', ['$compile', '$timeout', function($compile, $timeout) {
        return {
            restrict: 'A',
            scope: {
                value: '=spUploadDrtv',
                upload: '&',
                cancellable: '@?',
                parent: '=?',
                fieldName: '@?'
            },
            link: function($scope, $element, attrs) {
                var cancellable = $scope.cancellable !== 'false';
                var cancelIcon = cancellable ? 'icon-remove' : null;

                var accept = attrs.accept;
                var imageOnly = accept.length > 4 && accept.substr(0, 5) === 'image';
                var placeHolder = imageOnly ? 'icon-picture' : 'icon-cloud-upload';

                $element.ace_file_input({
                    style:'well',
                    no_icon: placeHolder,
                    droppable: true,
                    thumbnail: 'small',
                    icon_remove: cancelIcon,
                    btn_choose: '点击上传',
                    btn_change: '点击上传',
                    before_change: function(){$element.parent().width(170); return true;}
                });

                var divElement = $element.parent();
                if($scope.value){
                    $timeout(function(){
                        var aceElement = $element.next();
                        aceElement.find('i').replaceWith('<img src="' + $scope.value + '" width="100%">');
                    });
                    divElement.width('70%');
                }
                else{
                    divElement.width(170);
                }
                $element.bind('change', {parent: $scope.parent, fieldName: $scope.fieldName}, $scope.upload());
            }
        };
    }]);
});
