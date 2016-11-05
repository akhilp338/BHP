
(function () {
    var AddEmployee_Ctrl_Final = function ($scope, $state, $rootScope, Core_Service,$timeout) {
        var vm = this;
        $rootScope.isShowLoader = true;
        vm.candidateId = localStorage["selectedCandidate"] ? localStorage["selectedCandidate"] : "";
        vm.candId = localStorage["selectedCandidateId"] ? localStorage["selectedCandidateId"] : "";
        vm.display = {};
        vm.display.name = localStorage["selectedCandidateName"] ? localStorage["selectedCandidateName"] : "";
        vm.display.candidateId = vm.candidateId;
        vm.registration = {};
        if (vm.candId) {
            Core_Service.getCandidateImpl("api/employee/getAnEmployee", vm.candId).then(function (res) {
                vm.isCheckboxEnable = true;
                vm.isChecked = true;
                $rootScope.isShowLoader = false;
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
                vm.registration.belhopatDesignation=res.vm.belhopatDesignation;
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
                         Core_Service.sweetAlertWithConfirm("Employee Registered successfully...", "Do you want to upload any documents?", "warning", function(isConfirm){
                                 if (isConfirm) {
                                     $state.go("coreuser.employee.upload");
                                 } else { 
                                     $timeout(function(){
                                         Core_Service.sweetAlert("Done!", "No Docs Uploaded", "success", "coreuser.employee");
                                     },200);                                    
                                 } 
                             });                       
                    }, function (error) {
                    	 Core_Service.sweetAlert("Oops!", "System error please try after some time.", "error", "coreuser.employee");
                    });
            });                                      
            
        };
     
        $scope.candidateId=$rootScope.id;
        Core_Service.calculateSidebarHeight();
        $rootScope.isShowLoader = false;
    };

    AddEmployee_Ctrl_Final.$inject = ["$scope", '$state', '$rootScope', 'Core_Service','$timeout'];
    angular.module('coreModule')
            .controller('AddEmployee_Ctrl_Final', AddEmployee_Ctrl_Final);
})();




