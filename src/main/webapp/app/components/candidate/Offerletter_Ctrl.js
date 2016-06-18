(function () {
    var Offerletter_Ctrl = function ($scope, $state, $rootScope, Core_Service, $stateParams, Core_HttpRequest, validationService) {
        var vm = this;
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
            Core_Service.getCandidateImpl("api/candidate/getCandidate", $stateParams.id).then(function (res) {
                console.log(res.data);
            }, function (err) {
                vm.registration = {};
            });
        }
        vm.offerletter = {};
        vm.offerletter.earnings = {};
        vm.offerletter.deductions = {};
        vm.offerletter.flexi = 0;
        vm.offerletter.fixed = 0;
        vm.offerletter.earnings.basicSal = 0;
        vm.offerletter.earnings.hra = 0;
        vm.offerletter.earnings.medical = 0;
        vm.offerletter.earnings.conveyance = 0;
        vm.offerletter.earnings.telephone = 0;
        vm.offerletter.earnings.specialAllow = 0;
        vm.offerletter.earnings.personalAllow = 0;
        vm.offerletter.earnings.lta = 0;
        vm.offerletter.earnings.fbk = 0;
        vm.offerletter.earnings.incentive = 0;
        vm.offerletter.earnings.statutory = 0;

        vm.offerletter.deductions.proTax = 0;
        vm.offerletter.deductions.pfEmployee = 0;
        vm.offerletter.deductions.pfEmployer = 0;
        vm.offerletter.deductions.ESIEmployer = 0;
        vm.offerletter.deductions.ESIEmployee = 0;
        vm.offerletter.deductions.epfEmployer = 0;
        vm.offerletter.deductions.epfEmployee = 0;
        vm.offerletter.deductions.leaveEncash = 0;
        vm.offerletter.deductions.insurance = 0;
        vm.offerletter.deductions.gratuity = 0;
        vm.offerletter.deductions.deductions = 0;

        vm.offerletter.total = vm.offerletter.flexi + vm.offerletter.fixed;
        vm.verifyOfferLetter = function(){
           $state.go("coreuser.candidate.offerletter.verify") 
        };
        
        vm.getSalarySplits = function(){
            vm.url = "api/candidate/getSalarySplit";
            Core_Service.getSalaryDetails(vm.url,this.offerletter.grossSalary)
                    .then(function (response) {
                    	Core_Service.sweetAlert("Done!",response.Message,"success","coreuser.candidate");  
                        console.log(response)
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



