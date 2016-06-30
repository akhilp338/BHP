(function () {
    var AddEvent_Ctrl = function ($scope, $uibModalInstance, CANDIDATE, Core_Service, candidateDetails) {
        var vm = this;
        vm.isStartDatePickerOpen = false;
        vm.isEndDatePickerOpen = false;  
        vm.addEventData = {};
        vm.addEventData.allDay = false;
        vm.setDpOpenStatus = function (id) {
            vm[id] = true;
        };
        vm.addEvent = function () {
            var url = "api/event/addEvent";
            Core_Service.addEventDetails(url,vm.addEventData).then(function(response){
                if(response.data)
               $uibModalInstance.close(response.data);
            },
            function(error){
               console.log(error) 
            });
           
        };
        vm.close = function () {
            $uibModalInstance.dismiss('cancel');
        };
        
    };
    AddEvent_Ctrl.$inject = ["$scope", '$uibModalInstance', 'CANDIDATE', 'Core_Service', 'candidateDetails'];
    angular.module('coreModule')
            .controller('AddEvent_Ctrl', AddEvent_Ctrl);
})();


