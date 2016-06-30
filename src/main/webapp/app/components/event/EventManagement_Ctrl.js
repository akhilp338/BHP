(function () {
    var EventManagement_Ctrl = function ($scope, Core_Service, uiCalendarConfig, $rootScope, Core_ModalService) {
        var vm = this;   
        Core_Service.getAllEvents("api/event/getEvents").then(function (res){
            vm.calander = angular.element("#calendar").fullCalendar({
    header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
    },
    defaultDate: '2016-05-12',
    buttonIcons: false, // show the prev/next text
    weekNumbers: true,
    editable: true,
    eventLimit: true, // allow "more" link when too many events
    events: res.data,
        dayClick: function(date, jsEvent, view) {
            Core_ModalService.openAddEventModal(date);
        //$('#calendar').fullCalendar('rerenderEvents');               
        //alert('Clicked on: ' + date.format());
    },
    eventResize: function (event, dayDelta, minuteDelta) {
                 console.log(event)
             }
        });
        },function (err){
            console.log(err)
        });
        
       
    }
    EventManagement_Ctrl.$inject = ["$scope", 'Core_Service','uiCalendarConfig', '$rootScope', 'Core_ModalService'];
    angular.module('coreModule')
            .controller('EventManagement_Ctrl', EventManagement_Ctrl);
})();