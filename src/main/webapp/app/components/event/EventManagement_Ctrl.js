(function () {
    var EventManagement_Ctrl = function ($scope, uiCalendarConfig, $rootScope, Core_Service,urlConfig, $stateParams, Core_HttpRequest, validationService) {
        var vm = this;
        angular.element("#calendar").fullCalendar({
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
				events: [
					{
						title: 'All Day Event',
						start: '2016-05-01'
					},
					{
						title: 'Long Event',
						start: '2016-05-07',
						end: '2016-05-10'
					},
					{
						id: 999,
						title: 'Repeating Event',
						start: '2016-05-09T16:00:00'
					},
					{
						id: 999,
						title: 'Repeating Event',
						start: '2016-05-16T16:00:00'
					},
					{
						title: 'Conference',
						start: '2016-05-11',
						end: '2016-05-13'
					},
					{
						title: 'Meeting',
						start: '2016-05-12T10:30:00',
						end: '2016-05-12T12:30:00'
					},
					{
						title: 'Lunch',
						start: '2016-05-12T12:00:00'
					},
					{
						title: 'Meeting',
						start: '2016-05-12T14:30:00'
					},
					{
						title: 'Happy Hour',
						start: '2016-05-12T17:30:00'
					},
					{
						title: 'Dinner',
						start: '2016-05-12T20:00:00'
					},
					{
						title: 'Birthday Party',
						start: '2016-05-13T07:00:00'
					},
					{
						title: 'Click for Google',
						url: 'http://google.com/',
						start: '2016-05-28'
					}
				]
    

    })

    }
    EventManagement_Ctrl.$inject = ["$scope", 'uiCalendarConfig', '$rootScope', 'Core_Service','urlConfig', '$stateParams', 'Core_HttpRequest', 'validationService'];
    angular.module('coreModule')
            .controller('EventManagement_Ctrl', EventManagement_Ctrl);
})();