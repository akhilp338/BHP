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
        if (typeof candidateDetails != "object") {
            vm.addEventData.start = candidateDetails;
            vm.addEventData.end = candidateDetails;
        } else {
            var start,end;
            if(candidateDetails.start){
              start = candidateDetails.start ? new Date(candidateDetails.start.format()) : "",
              start = new Date(start.getTime() + start.getTimezoneOffset() * 60000);
        }
        if(candidateDetails.start){
              end = candidateDetails.end ? new Date(candidateDetails.end.format()) : "",
              end = candidateDetails.end ? new Date(candidateDetails.end.format()) : "";
        }               
            vm.addEventData.id = candidateDetails.id;
            vm.addEventData.start = start.getTime();
            vm.addEventData.end = end.getTime();
            vm.addEventData.title = candidateDetails.title;
            vm.addEventData.description = candidateDetails.description;
            vm.addEventData.location = candidateDetails.location;
            vm.addEventData.allDay = candidateDetails.allDay;
        }
        vm.addEvent = function () {
            var url = "api/event/addEvent";
            Core_Service.addEventDetails(url, vm.addEventData).then(function (response) {
                if (response.data)
                    $uibModalInstance.close(response.data);
            },
                    function (error) {
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


