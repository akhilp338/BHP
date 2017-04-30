(function () {
    var Settings_Ctrl = function ($scope, $rootScope, $state, Core_Service, Core_ModalService, urlConfig) {
        var vm = this, attendanceTable;
            $rootScope.isSettings = true;
            Core_Service.getRolesForSettings().then(function(res){
                vm.roleData = res;
            },function(err){
                console,log(err)
            });
            vm.getRoleBasedTabs = function(){
                Core_Service.getRoleBasedTabs(vm.selectedRole.id).then(function(res){
                    vm.settingsData = res;
                },function(err){
                    console,log(err)
                });
            };
            vm.clear = function(){
                for (var i=0; i < vm.settingsData.length; i++){
                    vm.settingsData[i].activeStatus = 'inactive'
                }
            };
            vm.back = function(){
                $rootScope.isSettings = false;
                $state.go('coreuser.dashboard');
            };
            vm.setRoleBasedTabs = function(){
                var activeTabs = [];
                for (var i=0; i < vm.settingsData.length; i++){
                    if(vm.settingsData[i].activeStatus == 'active'){
                        activeTabs.push(vm.settingsData[i].id)
                    }
                }
                Core_Service.setRoleBasedTabs(vm.selectedRole.id,activeTabs).then(function(res){
                    vm.settingsData = res;
                    Core_Service.sweetAlert("Done!", "Settings Changed", "success");
                },function(err){
                    console,log(err)
                });
            };
}
    Settings_Ctrl.$inject = ["$scope", '$rootScope', '$state', 'Core_Service', 'Core_ModalService', 'urlConfig'];
    angular.module('coreModule').controller('Settings_Ctrl', Settings_Ctrl);
})();
