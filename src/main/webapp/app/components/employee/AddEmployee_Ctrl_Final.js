
(function () {
    var AddEmployee_Ctrl_Final = function ($scope, $state, $rootScope, Core_Service,urlConfig, $stateParams, Core_HttpRequest, validationService) {
        var vm = this;
        $rootScope.showLoader = true;
        console.log($stateParams.id); 
        vm.candidateId = localStorage["selectedCandidate"];
        vm.candId = localStorage["selectedCandidateId"];
        vm.display = {};
        vm.display.name = localStorage["selectedCandidateName"];
        vm.display.candidateId = vm.candidateId;
        vm.registration = {};
        if ($stateParams.id) {
            Core_Service.getCandidateImpl("api/employee/getAnEmployee", $stateParams.id).then(function (res) {
                vm.isCheckboxEnable = true;
                vm.isChecked = true;
                $rootScope.showLoader = false;
                vm.registration.id=res.data.id;
                vm.registration.hrManager=res.data.hrManager;
                vm.registration.accountManager=res.data.accountManager;
                vm.registration.businessUnit= res.data.businessUnit;
                vm.registration.joiningDate= res.data.joiningDate;
                vm.registration.employeeMasterId=res.data.employeeMasterId;
                vm.registration.workLocation= res.data.workLocation;
                vm.registration.timeZone=res.data.timeZone;
                vm.registration.hrRecruiter= res.data.hrRecruiter;
                vm.registration.reportingManager=res.data.reportingManager;
                vm.registration.baseLocation =res.data.baseLocation;
        	console.log("edit");
        	console.log(vm.registration);
            }, function (err) {
                vm.registration = {};
            });
        }
                
        vm.back = function (){
            $state.go('coreuser.employee');
        };
        
        vm.setDpOpenStatus = function (id) {
            vm[id] = true
        };	
        vm.urlForLookups = "api/employee/getDropDownData";
        Core_Service.getAllLookupValues(vm.urlForLookups)
                .then(function (response) {
                    vm.lookups = response.data;
                    console.log(vm.lookups);	
                }, function (error) {

                });
        $rootScope.active = 'employee';
        vm.cancelRegisteration = function (){
            $state.go("coreuser.employee")
        };
        vm.employeeRegister = function () {
            vm.registerUrl = "api/employee/saveOrUpdateEmployee";
            vm.registration.employeeMasterId=vm.candId ;
            
            Core_Service.sweetAlertWithConfirm("Employee details filled!", "Are you sure to register this employee?", "warning", function(){
                   Core_Service.registerImpl(vm.registerUrl, vm.registration)
                    .then(function (response) {
                        $state.go("coreuser.upload");
                    	  //Core_Service.sweetAlert("Done!", response.Message, "success", "coreuser.employee");
                    }, function (error) {
                    	 Core_Service.sweetAlert("Oops!", "System error please try after some time.", "error", "coreuser.employee");
                    });
            });                                      
            
        };
     
        $scope.candidateId=$rootScope.id;
        Core_Service.calculateSidebarHeight();
        $rootScope.showLoader = false;
    };

    AddEmployee_Ctrl_Final.$inject = ["$scope", '$state', '$rootScope', 'Core_Service','urlConfig', '$stateParams', 'Core_HttpRequest', 'validationService'];
    angular.module('coreModule')
            .controller('AddEmployee_Ctrl_Final', AddEmployee_Ctrl_Final);
})();




