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
        vm.picker6 = {
            date: new Date()
        };
        vm.picker7 = {
            date: new Date()
        };

        vm.openCalendar = function (e, picker) {
            vm[picker].open = true;
        };

        if (candidateDetails) {            
            var start, end;
            if (candidateDetails.start) {
                start = new Date(candidateDetails.start.format()),
                start = new Date(start.getTime() + start.getTimezoneOffset() * 60000);
                vm.addEventData.start = start.getTime();
                vm.picker7.date = start;
            }
            else if(candidateDetails._d){
                 vm.picker7.date = candidateDetails._d;
            }
            if (candidateDetails.end) {
                end = new Date(candidateDetails.end.format()),
                end = new Date(end.getTime() + end.getTimezoneOffset() * 60000);
                vm.addEventData.end = end.getTime();
                vm.picker6.date = end;
            }
            else if(candidateDetails._d){
                 vm.picker6.date = candidateDetails._d;
            }
            vm.addEventData.id = candidateDetails.id;  
            vm.addEventData.title = candidateDetails.title;
            vm.addEventData.description = candidateDetails.description;
            vm.addEventData.location = candidateDetails.location;
            vm.addEventData.allDay = candidateDetails.allDay;
        }
        vm.addEvent = function () {
            vm.addEventData.start = vm.picker7.date;
            vm.addEventData.end = vm.picker6.date
            var url = "api/event/addEvent";
            Core_Service.addEventDetails(url, vm.addEventData).then(function (response) {
                if (response.data)
                    $uibModalInstance.close(response.data);
            },
                    function (error) {
                        console.log(error)
                    });

        };
        vm.cancelEvent = function(){
            var url = "api/event/deleteEvent";
            Core_Service.addEventDetails(url, vm.addEventData.id).then(function (response) {
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


