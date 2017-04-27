(function () {
    var Settings_Ctrl = function ($scope, $rootScope, $state, Core_Service, Core_ModalService, urlConfig) {
        var vm = this, attendanceTable;
            $rootScope.isSettings = true;
            Core_Service.getRolesForSettings().then(function(res){
                vm.settingsData = res;
            },function(err){
                console,log(err)
            });
            vm.getRoleBasedTabs = function(){
                Core_Service.getRoleBasedTabs(vm.selectedRole.id).then(function(res){
                    console.log(res)
                },function(err){
                    console,log(err)
                });
            };
            vm.apply = function(){
                console.log(vm.settingsData);
            };
            vm.back = function(){
                $rootScope.isSettings = false;
                $state.go('coreuser.dashboard');
            };
            vm.processSelection = function(tabs,selectedTabs){

            };
}
    Settings_Ctrl.$inject = ["$scope", '$rootScope', '$state', 'Core_Service', 'Core_ModalService', 'urlConfig'];
    angular.module('coreModule').controller('Settings_Ctrl', Settings_Ctrl);
})();
