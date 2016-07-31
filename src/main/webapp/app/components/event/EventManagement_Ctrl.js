(function () {
    var EventManagement_Ctrl = function ($state, Core_Service, $rootScope, Core_ModalService) {
        var vm = this;
        vm.eventData = {};
        vm.updateEvents = function (event) {
            var start = new Date(event.start.format()),
                    end = new Date(event.end.format()),
                    obj = {};
            obj.id = event.id;
            obj.start = start.getTime();
            obj.end = end.getTime();
            obj.title = event.title;
            obj.description = event.description;
            obj.location = event.location;
            obj.allDay = event.allDay;
            Core_Service.updateEventDetails("api/event/updateEvent", obj).then(function (response) {
                if (response)
                    console.log(response)
            },
                    function (error) {
                        console.log(error)
                    });
        };
        vm.processArrayToObject = function (list) {
            var guestObjArray = [];
            for (var i = 0; i < list.length; i++) {
                var guestObj = {};
                guestObj.id = list[i][0];
                guestObj.text = list[i][1];
                guestObjArray.push(guestObj);
            }
            return guestObjArray;
        };
        Core_Service.getAllEvents("api/event/getEvents").then(function (res) {
            vm.calander = angular.element("#calendar").fullCalendar({
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'month,agendaWeek,agendaDay'
                },
                buttonIcons: false, // show the prev/next text
                weekNumbers: true,
                timezone: "UTC",
                editable: true,
                eventLimit: true, // allow "more" link when too many events
                events: res.data,
                dayClick: function (date, jsEvent, view) {
                    if (!date.isBefore(moment())) {
                        Core_Service.getAllGuests("api/event/getEmployeesDropDownData").then(function (res) {
                            if (res.data) {
                                vm.eventData.guestList = vm.processArrayToObject(res.data.EMPLOYEES);
                                date = new Date(date.format());
                                date = moment(date.getTime() + date.getTimezoneOffset() * 60000);
                                vm.eventData.date = date;
                                Core_ModalService.openAddEventModal(vm.eventData).result.then(function (response) {
                                    if (response)
                                        $state.reload();
                                });
                            }
                        });


                    }
                },
                eventResize: function (event, dayDelta, minuteDelta) {
                    if (!event.end.isBefore(moment())) {
                        vm.updateEvents(event);
                    }
                },
                eventDrop: function (event, dayDelta, minuteDelta) {
                    vm.updateEvents(event);
                },
                eventClick: function (calEvent, jsEvent, view) {
                    Core_Service.getAllGuests("api/event/getEmployeesDropDownData").then(function (res) {
                        calEvent.end = calEvent.end ? calEvent.end : view.end; 
                        if (res.data && calEvent.end && !calEvent.end.isBefore(moment())) {                                                           
                            calEvent.guestList = vm.processArrayToObject(res.data.EMPLOYEES);
                            Core_ModalService.openAddEventModal(calEvent).result.then(function (response) {
                                if (response)
                                    $state.reload();
                            });
                        }
                    });
                }
            });
        }, function (err) {
            console.log(err)
        });


    }
    EventManagement_Ctrl.$inject = ["$state", 'Core_Service', '$rootScope', 'Core_ModalService'];
    angular.module('coreModule')
            .controller('EventManagement_Ctrl', EventManagement_Ctrl);
})();