(function () {
    var Offerletter_Ctrl = function ($scope, $state, $rootScope, Core_Service, $stateParams, Core_HttpRequest, validationService) {
        var vm = this;
        vs = new validationService({
            controllerAs: vm
        });
        
        vm.offerletter = {};
        vm.offerletter.earnings = {};
        vm.offerletter.deductions = {};
        vm.offerletter.display = {};
        vs.setGlobalOptions({
            debounce: 1500,
            scope: $scope,
            isolatedScope: $scope,
            preValidateFormElements: false,
            displayOnlyLastErrorMsg: true
        });
        
        if ($stateParams.id) {
            Core_Service.getCandidateImpl("api/candidate/getCandidate", $stateParams.id).then(function (res) {
                vm.getSalaryGrades();
                vm.offerletter.display.candidateId = res.data.candidateId;
                vm.offerletter.display.name = res.data.firstName + " " + res.data.lastName;
            }, function (err) {
                vm.registration = {};
            });
        }
        
//        vm.offerletter.flexi = 0;
//        vm.offerletter.fixed = 0;
//        vm.offerletter.earnings.basicSal = 0;
//        vm.offerletter.earnings.hra = 0;
//        vm.offerletter.earnings.medical = 0;
//        vm.offerletter.earnings.conveyance = 0;
//        vm.offerletter.earnings.telephone = 0;
//        vm.offerletter.earnings.specialAllow = 0;
//        vm.offerletter.earnings.personalAllow = 0;
//        vm.offerletter.earnings.lta = 0;
//        vm.offerletter.earnings.fbk = 0;
//        vm.offerletter.earnings.incentive = 0;
//        vm.offerletter.earnings.statutory = 0;
//
//        vm.offerletter.deductions.proTax = 0;
//        vm.offerletter.deductions.pfEmployee = 0;
//        vm.offerletter.deductions.pfEmployer = 0;
//        vm.offerletter.deductions.ESIEmployer = 0;
//        vm.offerletter.deductions.ESIEmployee = 0;
//        vm.offerletter.deductions.epfEmployer = 0;
//        vm.offerletter.deductions.epfEmployee = 0;
//        vm.offerletter.deductions.leaveEncash = 0;
//        vm.offerletter.deductions.insurance = 0;
//        vm.offerletter.deductions.gratuity = 0;
//        vm.offerletter.deductions.deductions = 0;

 //       vm.offerletter.total = vm.offerletter.flexi + vm.offerletter.fixed;
        vm.verifyOfferLetter = function(){
           $state.go("coreuser.candidate.offerletter.verify") 
        };       
        
        vm.getSalaryGrades = function(){
        	vm.getSalaryGradesUrl = "api/candidate/getSalaryGrades";
            Core_Service.getSalaryGradesUrl(vm.getSalaryGradesUrl)
            .then( function(response) {
            vm.offerletter.grades = response.data;
            },function(error){
            	
            });
        };
        
        vm.getSalarySplits = function(){
            vm.url = "api/candidate/getSalarySplit";
            Core_Service.getSalaryDetails(vm.url,this.offerletter.grossSalary)
                    .then(function (response) {  
                    for(var key in response.data){                        
                        vm.offerletter[key] = response.data[key] ? response.data[key] : 0;
                    }
                    }, function (error) {
                        console.log(error)
                    });
        }
        
        vm.back = function (){
          $state.go("coreuser.candidate.offerletter")   
        };
    };
    Offerletter_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'Core_Service', '$stateParams', 'Core_HttpRequest', 'validationService'];
    angular.module('coreModule')
            .controller('Offerletter_Ctrl', Offerletter_Ctrl);
})();



