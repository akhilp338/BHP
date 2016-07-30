(function () {
    var Header_Ctrl = function ($scope, $state, $rootScope, Core_Service) {
        var vm = this;
        vm.getUserName = localStorage["userName"] || "Rafique";
        vm.logout = function () {
            Core_Service.ClearCredentials();
            $state.go('login');
        }
        vm.triggerButton = function (event) {
            event.preventDefault();
            localStorage.clear();
            angular.element(".link-btn").trigger("click");
        };
        vm.getUserName= function(data){
       	 Core_Service.getCurrentUser(data).then(function (res){
       		$rootScope.currentUser=res;
            },function (error){
            }); 
       	
       }

        
    };    
    Header_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'Core_Service'];
    angular.module('coreModule')
            .controller('Header_Ctrl', Header_Ctrl);
})();


