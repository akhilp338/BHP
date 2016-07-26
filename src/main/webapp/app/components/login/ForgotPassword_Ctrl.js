(function () {
    var ForgotPassword_Ctrl = function ($scope, validationService, Core_Service, $uibModalInstance ) {
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
    ForgotPassword_Ctrl.$inject = ["$scope", 'validationService', 'Core_Service', '$uibModalInstance'];
    angular.module('coreModule')
            .controller('ForgotPassword_Ctrl', ForgotPassword_Ctrl);
})();