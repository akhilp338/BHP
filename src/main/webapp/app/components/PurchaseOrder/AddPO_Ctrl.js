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
        
        vm.purchaseOrder = {}; 
		 Core_Service.getPODropDownData().then(function (res) {
	            vm.purchaseOrder.lookups = res;
	        }, function (err) {
	            console.log(err)
	        });
        vs = new validationService({
            controllerAs: vm
        });
        vs.setGlobalOptions({
            debounce: 1500,
            scope: $scope,
            isolatedScope: $scope,
            preValidateFormElements: false,
            displayOnlyLastErrorMsg: true
        });
        
        if ($stateParams.id) {
            Core_Service.getPOImpl("api/purchaseOrder/getPurchaseOrder", $stateParams.id).then(function (data) {
                vm.registration = data;                
                $rootScope.isShowLoader = false;
            }, function (err) {
                vm.registration = {};
            });
        }
        
        vm.urlForLookups = "api/purchaseOrder/getDropDownData";
        Core_Service.getAllLookupValues(vm.urlForLookups)
                .then(function (response) {
                    vm.purchaseOrder.lookups = response;
                }, function (error) {

                });

        $rootScope.active = 'po';
        
        vm.addPO = function () {
            vm.registerUrl = "api/purchaseOrder/saveOrUpdatePurchaseOrder";
           if(vs.checkFormValidity($scope.regForm)){
                Core_Service.sweetAlertWithConfirm("PO Details filled!", "Are you sure to add this PO?", "warning", function(){
                	vm.registration.expiry = new Date();
                	vm.registration.poDate = new Date();
            Core_Service.registerImpl(vm.registerUrl, vm.registration)
                    .then(function (response) {
                    	Core_Service.sweetAlert("Done!",response.data["message "],"success","coreuser.po");
                    }, function (error) {
                    	Core_Service.sweetAlert("Oops!","An internal error occcured.Please try after some time.",
                    			"error","coreuser.po");
                    });
                });
            }
        };
        
    };
           
    AddPO_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'Core_Service', '$stateParams', 'Core_HttpRequest', 'validationService'];
    angular.module('coreModule')
            .controller('AddPO_Ctrl', AddPO_Ctrl);
})();