(function () {
    var Opp_Ctrl = function ($scope, $state, $rootScope, Core_Service) {
        var vm = this;
        $rootScope.active = 'opportunity';
        
         };

    Opp_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'Core_Service'];
    angular.module('coreModule')
            .controller('Opp_Ctrl', Opp_Ctrl);
})();

