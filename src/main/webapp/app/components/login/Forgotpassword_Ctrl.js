(function () {
    var Forgotpassword_Ctrl = function ($scope, $uibModalInstance, validationService, Core_Service) {
        var vm = this,
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
        vm.submit = function () {
            if (vs.checkFormValidity($scope)) { 
                var data = {"email":vm.forgot.email}
               Core_Service.sendPassword(data).then(function(res){
                  $uibModalInstance.close(res); 
               },
               function(error){
                  $uibModalInstance.close(error);  
               });
               // 
            }
        };
        vm.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
    };
    Forgotpassword_Ctrl.$inject = ["$scope", '$uibModalInstance', 'validationService', 'Core_Service'];
    angular.module('coreModule')
            .controller('Forgotpassword_Ctrl', Forgotpassword_Ctrl);
})();