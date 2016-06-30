(function () {
    var DeleteCandidate_Ctrl = function ($scope, $uibModalInstance, Core_Service, candidateDetails) {
        var vm = this;
        vm.delete = function () {          
          $uibModalInstance.dismiss('cancel');
        };
        vm.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
    };

    DeleteCandidate_Ctrl.$inject = ["$scope", '$uibModalInstance', 'Core_Service', 'candidateDetails'];
    angular.module('coreModule')
            .controller('DeleteCandidate_Ctrl', DeleteCandidate_Ctrl);
})();



