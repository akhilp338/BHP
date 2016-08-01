(function () {
    var AddClient_Ctrl = function ($scope, $state, $rootScope, Core_Service, $stateParams, Core_HttpRequest, validationService) {
        var vm = this;
        $rootScope.showLoader = true;
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
            Core_Service.getCandidateImpl("api/client/getClient", $stateParams.id).then(function (res) {
                vm.registration = res.data;
                vm.getStatesByCountry(vm.registration.clientAddress.city.state.country.id, "client");
                vm.getCitiesByStates(vm.registration.clientAddress.city.state.id, "client");
                vm.isCheckboxEnable = true;
                vm.isChecked = true;
                $rootScope.showLoader = false;
            }, function (err) {
                vm.registration = {};
            });
        }
        vm.back = function (){
            $state.go('coreuser.client');
        };
        vm.urlForLookups = "api/client/getDropDownData";
        Core_Service.getAllLookupValues(vm.urlForLookups)
                .then(function (response) {
                    vm.lookups = response.data;
                    console.log(vm.lookups);
                }, function (error) {

                });

        $rootScope.active = 'client';
        
        vm.clientRegister = function () {
            vm.registerUrl = "api/client/saveOrUpdateClient";
            if(vs.checkFormValidity($scope.regForm)){
                Core_Service.sweetAlertWithConfirm("Client details filled!", "Are you sure to register this Client?", "warning", function(){
            Core_Service.registerImpl(vm.registerUrl, vm.registration)
                    .then(function (response) {
                    	Core_Service.sweetAlert("Done!",response.data["message "],"success","coreuser.client");
                    }, function (error) {
                    	Core_Service.sweetAlert("Oops!","An internal error occcured.Please try after some time.",
                    			"error","coreuser.client");
                    });
                });
            }
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
        $rootScope.showLoader = false;
    };

    AddClient_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'Core_Service', '$stateParams', 'Core_HttpRequest', 'validationService'];
    angular.module('coreModule')
            .controller('AddClient_Ctrl', AddClient_Ctrl);
})();