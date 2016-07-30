(function () {
    var AddEvent_Ctrl = function ($scope, $uibModalInstance, CANDIDATE, Core_Service, candidateDetails) {
        var vm = this;
        vm.isStartDatePickerOpen = false;
        vm.isEndDatePickerOpen = false;
        vm.addEventData = {};
        vm.isEdit = false;
        vm.buttonText = "Add Event"
        vm.addEventData.allDay = false;
        vm.addEventData.guests = [];
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

        vm.processObjecToArray = function(obj){
            var guestObjArray = [];
            for(var i=0; i<obj.length; i++){
            	var tempobj = {};
            	tempobj.id = obj[i]["id"]
                guestObjArray.push(tempobj);
            }
            return guestObjArray;
        };
        vm.removeSelectedGuest = function(parentObjArray, objToRemove){
            if(objToRemove){
            vm.addEventData.guests.push(objToRemove);
            for(var i=0; i<parentObjArray.length; i++){
                if(parentObjArray[i] && (parentObjArray[i].id == objToRemove.id)){
                    parentObjArray.splice(i,1);
                }
            }
            vm.eventguests = parentObjArray;
        }
        };        
        
        vm.guestRemove = function(chipInfo){
            vm.eventguests.push(chipInfo);
        };
        
        if (candidateDetails) {
            var start, end;
            if (candidateDetails.start) {
                start = new Date(candidateDetails.start.format());
                start = new Date(start.getTime() + start.getTimezoneOffset() * 60000);
                vm.addEventData.start = start.getTime();
                vm.picker7.date = start;
                vm.buttonText = "Edit Event";
                vm.isEdit = true;
            } else if (candidateDetails.date._d) {
                vm.picker7.date = candidateDetails.date._d;
                vm.buttonText = "Add Event";
                vm.isEdit = false;
            }
            if (candidateDetails.end) {
                end = new Date(candidateDetails.end.format());
                end = new Date(end.getTime() + end.getTimezoneOffset() * 60000);
                vm.addEventData.end = end.getTime();
                vm.picker6.date = end;
                vm.buttonText = "Edit Event";
                vm.isEdit = true;
            } else if (candidateDetails.date._d) {
                vm.picker6.date = candidateDetails.date._d;
                vm.buttonText = "Add Event";
                vm.isEdit = false;
            }
            vm.addEventData.id = candidateDetails.id;
            vm.addEventData.title = candidateDetails.title;
            vm.addEventData.description = candidateDetails.description;
            vm.addEventData.location = candidateDetails.location;
            vm.addEventData.allDay = candidateDetails.allDay;
        }
        if(candidateDetails.guestList){
            vm.eventguests = candidateDetails.guestList;
        }
        vm.addEvent = function () {
            vm.addEventData.start = vm.picker7.date;
            vm.addEventData.end = vm.picker6.date;
            vm.addEventData.guestList = vm.processObjecToArray(vm.addEventData.guests);
            var url = "api/event/addEvent";
            Core_Service.addEventDetails(url, vm.addEventData).then(function (response) {
                if (response.data)
                    $uibModalInstance.close(response.data);
            },
                    function (error) {
                        console.log(error)
                    });

        };
        
        vm.cancelEvent = function () {
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


