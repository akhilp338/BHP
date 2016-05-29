(function () {
    var Dash_Ctrl = function ($scope, $state, $rootScope, Core_Service) {
        var vm = this;
        $rootScope.active = 'dashboard';
        
         };

    Dash_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'Core_Service'];
    angular.module('coreModule')
            .controller('Dash_Ctrl', Dash_Ctrl);
})();


