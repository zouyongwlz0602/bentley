'use strict';

define(['./module'], function(module) {
    module.directive('budgetChartDrtv', ['$timeout', function($timeout) {
        return {
            restrict: 'A',
            scope: {
                data: '=budgetChartDrtv'
            },
            link: function($scope, $element) {
                var centerX = $element.width() / 2;
                var padding = 10;
                var lineHeight = 10;
                var startAngle = -Math.PI / 2;
                var radius = centerX - padding;
                var startX = centerX;
                var startY = centerX / 10;
                function getPoint(angle){
                    return {
                        x : centerX + (Math.cos(angle) * radius),
                        y : centerX + (Math.sin(angle) * radius)
                    };
                }
                var ctx = $element[0].getContext('2d');

                var data = $scope.data;
                var total = data.all.value;
                var assignedAngle = (data.assigned.value / total * 2 - 0.5) * Math.PI;
                var spentAngle = (data.spent.value / total * 2 - 0.5) * Math.PI;
                var assignedDestination = getPoint(assignedAngle);
                var spentDestination = getPoint(spentAngle);

                var gradAssigned= ctx.createLinearGradient(startX, startY, assignedDestination.x, assignedDestination.y);
                gradAssigned.addColorStop(0, "#0f0");
                gradAssigned.addColorStop(0.7, "#0b0");
                gradAssigned.addColorStop(1, "#060");

                var gradSpent= ctx.createLinearGradient(startX, startY, spentDestination.x, spentDestination.y);
                gradSpent.addColorStop(0, "#f00");
                gradSpent.addColorStop(1, "#900");

                ctx.beginPath();
                ctx.arc(centerX, centerX, radius, startAngle, Math.PI * 1.5, false);
                ctx.lineWidth = 7;
                ctx.strokeStyle = '#5070a0';
                ctx.stroke();

                ctx.beginPath();
                ctx.lineWidth = 1;
                ctx.font = lineHeight + 'px 宋体';
                ctx.textAlign = 'center';
                ctx.strokeText(data.all.label, centerX, centerX * 0.9);
                ctx.strokeText(data.all.value, centerX, centerX * 1.2);

                ctx.beginPath();
                ctx.strokeStyle = gradAssigned;
                ctx.arc(centerX, centerX, radius, startAngle, assignedAngle, false);
                ctx.lineWidth = 10;
                ctx.stroke();

                var bottomY = centerX * 2;

                ctx.beginPath();
                ctx.strokeStyle = "#060";
                ctx.lineWidth = 3;
                ctx.lineTo(assignedDestination.x, assignedDestination.y);
                ctx.lineTo(padding, bottomY);
                ctx.lineTo(centerX - padding * 2, bottomY);
                ctx.stroke();

                ctx.beginPath();
                ctx.lineWidth = 1;
                ctx.textAlign = 'center';
                ctx.fillStyle = '#060';
                ctx.fillRect(0, bottomY + padding, lineHeight, lineHeight);
                ctx.strokeText(data.assigned.label, centerX / 2, bottomY + padding * 2);
                ctx.strokeText(data.assigned.value, centerX / 2, bottomY + lineHeight + padding * 3);

                ctx.beginPath();
                ctx.strokeStyle = gradSpent;
                ctx.arc(centerX, centerX, radius, startAngle, spentAngle, false);
                ctx.lineWidth = 15;
                ctx.stroke();

                ctx.beginPath();
                ctx.lineWidth = 1;
                ctx.textAlign = 'center';
                ctx.fillStyle = '#900';
                ctx.fillRect(centerX * 1.1, bottomY + padding, lineHeight, lineHeight);
                ctx.strokeText(data.spent.label, centerX * 1.6, bottomY + padding * 2);
                ctx.strokeText(data.spent.value, centerX * 1.6, bottomY + lineHeight + padding * 3);
            }
        };
    }]);
});
