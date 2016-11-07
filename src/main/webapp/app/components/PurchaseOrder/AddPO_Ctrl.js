(function () {
    var AddPO_Ctrl = function ($scope, $state, $rootScope, Core_Service, $stateParams, Core_HttpRequest, validationService) {
        var vm = this;
        vm.registration = {}
        vm.back = function (){
            $state.go('coreuser.po');
        };
        vm.setDpOpenStatus = function (id) {
            vm[id] = true;
        };
        vm.urlForLookups = "api/purchaseOrder/getDropDownData";
        Core_Service.getAllLookupValues(vm.urlForLookups)
                .then(function (response) {
                    vm.poStatus = response.data;
                }, function (error) {

                });

        $rootScope.active = 'po';
        
    };
           
    AddPO_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'Core_Service', '$stateParams', 'Core_HttpRequest', 'validationService'];
    angular.module('coreModule')
            .controller('AddPO_Ctrl', AddPO_Ctrl);
})();