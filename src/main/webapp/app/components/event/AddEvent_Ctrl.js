(function () {
    var AddEvent_Ctrl = function ($scope, $uibModalInstance, CANDIDATE, Core_Service, candidateDetails) {
        var vm = this;
        vm.datePickerOpen = false;
        vm.setDpOpenStatus = function (id) {
            vm[id] = true;
        };
        vm.addEvent = function () {
            $uibModalInstance.dismiss('cancel');
        };
        vm.close = function () {
            $uibModalInstance.dismiss('cancel');
        };
    };
    AddEvent_Ctrl.$inject = ["$scope", '$modalInstance', 'CANDIDATE', 'Core_Service', 'candidateDetails'];
    angular.module('coreModule')
            .controller('AddEvent_Ctrl', AddEvent_Ctrl);
})();


