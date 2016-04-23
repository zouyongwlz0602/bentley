'use strict';

define(['./module', 'lodash'], function(module, _) {
    module.filter('productsFilter', function() {
        var numberComparatorPattern = new RegExp("^\\d+-\\d+$");
        function stringComparator(actual, expectation) {
            return expectation ? ('' + actual).indexOf(expectation) > -1 : true;
        };
        function numberComparator(actual, expectation) {
            var range = expectation.split('-');
            var low = parseFloat(range[0].trim());
            var high = parseFloat(range[1].trim());
            if(_.isNumber(low) && actual < low){
                return false;
            }
            if(_.isNumber(high) && actual > high){
                return false;
            }
            return true;
        }

        return function (arr, options){
            var expectation = options.$;
            var fields = options.fields.split(',');
            var numberFields = fields[1].split(' ');
            var stringFields = fields[0].split(' ');
            return _.filter(arr, function(product){
                if(_.isUndefined(expectation) || product.trashed){
                    return true;
                }
                if(numberComparatorPattern.test(expectation)){
                    return _.some(numberFields, function(numberField){
                        return numberComparator(_.propertyOf(product)(numberField), expectation);
                    });
                }
                else{
                    return _.some(stringFields, function(stringField){
                        return stringComparator(_.propertyOf(product)(stringField), expectation);
                    })
                }
            });
        };

    });
});