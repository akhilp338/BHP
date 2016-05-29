(function () {
    var Holiday_Ctrl = function ($scope, $state, $rootScope, Core_Service) {
        var vm = this;
        $rootScope.active = 'holiday';
        
         };

    Holiday_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'Core_Service'];
    angular.module('coreModule')
            .controller('Holiday_Ctrl', Holiday_Ctrl);
})();

