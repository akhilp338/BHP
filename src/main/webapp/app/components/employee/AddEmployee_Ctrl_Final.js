
(function () {
    var AddEmployee_Ctrl_Final = function ($scope, $state, $rootScope, Core_Service,urlConfig, $stateParams, Core_HttpRequest, validationService) {
        var vm = this;
        $rootScope.showLoader = true;
        console.log($stateParams.id); 
        vm.candidateId = localStorage["selectedCandidate"];
        vm.candId = localStorage["selectedCandidateId"];
        vm.registration = {};
        if ($stateParams.id) {
            Core_Service.getCandidateImpl("api/employee/getAnEmployee", $stateParams.id).then(function (res) {
                vm.isCheckboxEnable = true;
                vm.isChecked = true;
                $rootScope.showLoader = false;
                vm.setFields(res.data);
            }, function (err) {
                vm.registration = {};
            });
        }
        vm.setFields = function(editObj){
        	vm.registration.id=editObj.employeeId;
        	vm.registration.employeeMasterId=editObj.employeeMaster;
        	vm.registration.hrManager=editObj.hrManager;
        	vm.registration.accountManager=editObj.accountManager;
        	vm.registration.businessUnit=editObj.businessUnit;
        	vm.registration.joiningDate=editObj.joiningDate
        	vm.registration.workLocation=editObj.workLocation;
        	vm.registration.timeZone=editObj.timeZone;
        	
        }
        vm.setDpOpenStatus = function (id) {
            vm[id] = true
        };	
        vm.urlForLookups = "api/employee/getEmployeeDropdowns";
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
            Core_Service.registerImpl(vm.registerUrl, vm.registration)
                    .then(function (response) {
                    }, function (error) {

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




