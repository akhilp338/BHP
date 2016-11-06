(function () {
    var AddVendor_Ctrl = function ($scope, $state, $rootScope, Core_Service, $stateParams, Core_HttpRequest, validationService) {
        var vm = this;
        $rootScope.isShowLoader = true;
        vm.registration = {};        
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
            Core_Service.getCandidateImpl("api/vendor/getVendor", $stateParams.id).then(function (res) {
                vm.registration = res.data;
                vm.getStatesByCountry(vm.registration.vendortAddress.city.state.country.id, "vendor");
                vm.getCitiesByStates(vm.registration.vendorAddress.city.state.id, "vendor");
                vm.isCheckboxEnable = true;
                vm.isChecked = true;
                $rootScope.isShowLoader = false;
            }, function (err) {
                vm.registration = {};
            });
        }
        vm.back = function (){
            $state.go('coreuser.vendor');
        };
        vm.urlForLookups = "api/vendor/getDropDownData";
        Core_Service.getAllLookupValues(vm.urlForLookups)
                .then(function (response) {
                    vm.lookups = response.data;
                }, function (error) {

                });

        $rootScope.active = 'client';
        
        vm.vendorRegister = function () {
            vm.registerUrl = "api/vendor/saveOrUpdateVendor";
//            if(vs.checkFormValidity($scope.regForm)){
                Core_Service.sweetAlertWithConfirm("Vendor details filled!", "Are you sure to register this Vendor?", "warning", function(){
            Core_Service.registerImpl(vm.registerUrl, vm.registration)
                    .then(function (response) {
                    	Core_Service.sweetAlert("Done!",response.data["message "],"success","coreuser.vendor");
                    }, function (error) {
                    	Core_Service.sweetAlert("Oops!","An internal error occcured.Please try after some time.",
                    			"error","coreuser.vendor");
                    });
                });
//            }
        };
        vm.getPocList=function(poc){
        	var array = [];
        		var obj={},
        		countryObj={},
        		desgnObj = {};
        		countryObj.id = poc.country.id;
        		obj.pocCountry=countryObj;
        		obj.pocName = poc.pocName;
        		desgnObj.id = poc.designation.id;
        		obj.pocDesignation=desgnObj;
        		obj.contactNo=poc.contactNo;
        		obj.mobNo=poc.mobNo;
        		obj.areaOfWork=poc.areaOfWork;
        		array.push(obj);
        	return array;
        }
        
        vm.getStatesByCountry = function (countryId, flag) {
            var data = {"id": countryId};
            vm.apiUrl = "api/getStatesByCountry";
            Core_Service.defaultApiByIdAndUrlImpl(vm.apiUrl, data)
                    .then(function (response) {
                        switch (flag) {
                            case "client":
                                vm.statesClient = response.data;
                                break;
                            default:
                                break;
                        }
                    }, function (error) {
                        console.log(error)
                    });
        };
        vm.getCitiesByStates = function (stateId, flag) {
            var data = {"id": stateId};
            vm.apiUrl = "api/getCitiesByState";
            Core_Service.defaultApiByIdAndUrlImpl(vm.apiUrl, data)
                    .then(function (response) {
                        switch (flag) {
                            case "client":
                                vm.citiesClient = response.data;
                                break;
                            default:
                                break;
                        }
                    }, function (error) {
                        console.log(error)
                    });
        };

        Core_Service.calculateSidebarHeight();
        $rootScope.isShowLoader = false;
    };

    AddVendor_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'Core_Service', '$stateParams', 'Core_HttpRequest', 'validationService'];
    angular.module('coreModule')
            .controller('AddVendor_Ctrl', AddVendor_Ctrl);
})();