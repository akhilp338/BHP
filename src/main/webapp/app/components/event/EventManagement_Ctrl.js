(function () {
    var EventManagement_Ctrl = function ($scope, uiCalendarConfig, $rootScope, Core_ModalService) {
        var vm = this;        
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
    events: [{"title":"Free Pizza","allday":"false","borderColor":"#5173DA","color":"#99ABEA","textColor":"#000000","description":"<p>This is just a fake description for the Free Pizza.</p><p>Nothing to see!</p>","start":"2016-06-18T00:32:53","end":"2016-06-18T01:32:53","url":"http://www.mikesmithdev.com/blog/worst-job-titles-in-internet-and-info-tech/"},{"title":"DNUG Meeting","allday":"false","borderColor":"#5173DA","color":"#99ABEA","textColor":"#000000","description":"<p>This is just a fake description for the DNUG Meeting.</p><p>Nothing to see!</p>","start":"2016-06-19T00:32:53","end":"2016-06-19T01:32:53","url":"http://www.mikesmithdev.com/blog/worst-job-titles-in-internet-and-info-tech/"},{"title":"Staff Meeting","allday":"false","borderColor":"#5173DA","color":"#99ABEA","textColor":"#000000","description":"<p>This is just a fake description for the Staff Meeting.</p><p>Nothing to see!</p>","start":"2016-06-20T00:32:53","end":"2016-06-22T00:32:53","url":"http://www.mikesmithdev.com/blog/worst-job-titles-in-internet-and-info-tech/"},{"title":"Poker Night","allday":"false","borderColor":"#5173DA","color":"#99ABEA","textColor":"#000000","description":"<p>This is just a fake description for the Poker Night.</p><p>Nothing to see!</p>","start":"2016-06-06T00:32:53","end":"2016-06-06T01:32:53","url":"http://www.mikesmithdev.com/blog/worst-job-titles-in-internet-and-info-tech/"},{"title":"Beer Garden","allday":"false","borderColor":"#5173DA","color":"#99ABEA","textColor":"#000000","description":"<p>This is just a fake description for the Beer Garden.</p><p>Nothing to see!</p>","start":"2016-06-22T00:32:53","end":"2016-06-22T01:32:53","url":"http://www.mikesmithdev.com/blog/worst-job-titles-in-internet-and-info-tech/"},{"title":"XBox Tourney","allday":"false","borderColor":"#5173DA","color":"#99ABEA","textColor":"#000000","description":"<p>This is just a fake description for the XBox Tourney.</p><p>Nothing to see!</p>","start":"2016-06-23T00:32:53","end":"2016-06-25T00:32:53","url":"http://www.mikesmithdev.com/blog/worst-job-titles-in-internet-and-info-tech/"},{"title":"Pool Party","allday":"false","borderColor":"#5173DA","color":"#99ABEA","textColor":"#000000","description":"<p>This is just a fake description for the Pool Party.</p><p>Nothing to see!</p>","start":"2016-06-24T00:32:53","end":"2016-06-24T01:32:53","url":"http://www.mikesmithdev.com/blog/worst-job-titles-in-internet-and-info-tech/"},{"title":"Staff Meeting","allday":"false","borderColor":"#5173DA","color":"#99ABEA","textColor":"#000000","description":"<p>This is just a fake description for the Staff Meeting.</p><p>Nothing to see!</p>","start":"2016-06-10T00:32:53","end":"2016-06-10T01:32:53","url":"http://www.mikesmithdev.com/blog/worst-job-titles-in-internet-and-info-tech/"},{"title":"Poker Night","allday":"false","borderColor":"#5173DA","color":"#99ABEA","textColor":"#000000","description":"<p>This is just a fake description for the Poker Night.</p><p>Nothing to see!</p>","start":"2016-07-02T00:32:53","end":"2016-07-04T00:32:53","url":"http://www.mikesmithdev.com/blog/worst-job-titles-in-internet-and-info-tech/"},{"title":"Hackathon","allday":"false","borderColor":"#5173DA","color":"#99ABEA","textColor":"#000000","description":"<p>This is just a fake description for the Hackathon.</p><p>Nothing to see!</p>","start":"2016-06-27T00:32:53","end":"2016-06-27T01:32:53","url":"http://www.mikesmithdev.com/blog/worst-job-titles-in-internet-and-info-tech/"},{"title":"Beta Testing","allday":"false","borderColor":"#5173DA","color":"#99ABEA","textColor":"#000000","description":"<p>This is just a fake description for the Beta Testing.</p><p>Nothing to see!</p>","start":"2016-06-28T00:32:53","end":"2016-06-28T01:32:53","url":"http://www.mikesmithdev.com/blog/worst-job-titles-in-internet-and-info-tech/"},{"title":"Perl Meetup","allday":"false","borderColor":"#820F20","color":"#A6113C","textColor":"#ffffff","description":"<p>This is just a fake description for the Perl Meetup.</p><p>Nothing to see!</p>","start":"2016-06-18T00:32:53","end":"2016-06-18T02:32:53","url":"http://www.mikesmithdev.com/blog/migrating-from-asp-net-to-ghost-node-js/"},{"title":"Node.js Meetup","allday":"false","borderColor":"#820F20","color":"#A6113C","textColor":"#ffffff","description":"<p>This is just a fake description for the Node.js Meetup.</p><p>Nothing to see!</p>","start":"2016-06-19T00:32:53","end":"2016-06-19T02:32:53","url":"http://www.mikesmithdev.com/blog/migrating-from-asp-net-to-ghost-node-js/"},{"title":"Javascript Meetup","allday":"false","borderColor":"#820F20","color":"#A6113C","textColor":"#ffffff","description":"<p>This is just a fake description for the Javascript Meetup.</p><p>Nothing to see!</p>","start":"2016-06-20T00:32:53","end":"2016-06-21T00:32:53","url":"http://www.mikesmithdev.com/blog/migrating-from-asp-net-to-ghost-node-js/"},{"title":"HTML Meetup","allday":"false","borderColor":"#820F20","color":"#A6113C","textColor":"#ffffff","description":"<p>This is just a fake description for the HTML Meetup.</p><p>Nothing to see!</p>","start":"2016-06-14T00:32:53","end":"2016-06-14T02:32:53","url":"http://www.mikesmithdev.com/blog/migrating-from-asp-net-to-ghost-node-js/"},{"title":"CSS Meetup","allday":"false","borderColor":"#820F20","color":"#A6113C","textColor":"#ffffff","description":"<p>This is just a fake description for the CSS Meetup.</p><p>Nothing to see!</p>","start":"2016-06-22T00:32:53","end":"2016-06-22T02:32:53","url":"http://www.mikesmithdev.com/blog/migrating-from-asp-net-to-ghost-node-js/"},          {
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
        ],
        dayClick: function(date, jsEvent, view) {
            Core_ModalService.openAddEventModal(date);
        //$('#calendar').fullCalendar('rerenderEvents');               
        //alert('Clicked on: ' + date.format());
    },
    eventResize: function (event, dayDelta, minuteDelta) {
                 
             }
        });
       
    }
    EventManagement_Ctrl.$inject = ["$scope", 'uiCalendarConfig', '$rootScope', 'Core_ModalService'];
    angular.module('coreModule')
            .controller('EventManagement_Ctrl', EventManagement_Ctrl);
})();