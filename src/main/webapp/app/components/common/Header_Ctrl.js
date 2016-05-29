(function () {
    var Header_Ctrl = function ($scope, $state, $rootScope, Core_Service) {
        var vm = this;
        vm.getUserName = localStorage["userName"];
        vm.logout = function () {
            Core_Service.ClearCredentials();
            $state.go('login');
        }
        vm.triggerButton = function (event) {
            event.preventDefault();
            localStorage.clear();
            angular.element(".link-btn").trigger("click");
        };

        
    };
    Header_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'Core_Service'];
    angular.module('coreModule')
            .controller('Header_Ctrl', Header_Ctrl);
})();


