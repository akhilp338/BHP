(function () {
    var DashModal_Ctrl = function ($state, $uibModalInstance, CLIENT, Core_Service, candidateDetails) {
        var vm = this;
        vm.template = "<div class='task_container'><p class='task_desc'>" + candidateDetails.task.taskDesc + "</p></div>";
        vm.gotoState = function () {
            $uibModalInstance.dismiss('cancel');
            $state.go(candidateDetails.task.taskRoute);
        };
        vm.close = function () {
            $uibModalInstance.dismiss('cancel');
        };
    };
    DashModal_Ctrl.$inject = ['$state', '$uibModalInstance', 'CLIENT', 'Core_Service', 'candidateDetails'];
    angular.module('coreModule').controller('DashModal_Ctrl', DashModal_Ctrl);
})();
