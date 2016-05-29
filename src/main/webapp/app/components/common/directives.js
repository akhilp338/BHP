(function (angular) {
    var showDataDirective = function ($compile) {
        var directive = {
            scope: true,
            link: link
        };
        return directive;
        function link(scope, elem, attrs, ctrl) {
            var el;
            attrs.$observe('template', function (tpl) {
                if (angular.isDefined(tpl)) {
                    el = $compile(tpl)(scope);
                    elem.html("");
                    elem.append(el);
                }
            });
        }
    };
    angular.module('coreModule')
            .directive('ngShowData', showDataDirective);

})(angular);



