(function () {
    var AttendanceMgmtCtrl = function ($scope, $rootScope, Core_Service, Core_ModalService, urlConfig) {
        var vm = this, attendanceTable;
        var attendanceDetails = [{
                "empCode": "T12",
                "empName": "Surya",
                "inTime": "10:30 AM",
                "outTime": "10:30 PM",
                "lateMinutes": "60",
                "earlyDepTime": "11:00 AM",
                "workHours": "10",
                "status": "empty"
            },
            {
                "empCode": "T12",
                "empName": "Arun",
                "inTime": "10:30 AM",
                "outTime": "10:30 PM",
                "lateMinutes": "60",
                "earlyDepTime": "11:00 AM",
                "workHours": "10",
                "status": "empty"
            },{
                "empCode": "T12",
                "empName": "Susy",
                "inTime": "10:30 AM",
                "outTime": "10:30 PM",
                "lateMinutes": "60",
                "earlyDepTime": "11:00 AM",
                "workHours": "10",
                "status": "empty"
            },{
                "empCode": "T345",
                "empName": "Shitinto",
                "inTime": "10:30 AM",
                "outTime": "10:30 PM",
                "lateMinutes": "60",
                "earlyDepTime": "11:00 AM",
                "workHours": "10",
                "status": "empty"
            },{
                "empCode": "T093",
                "empName": "Pattu mon",
                "inTime": "10:30 AM",
                "outTime": "10:30 PM",
                "lateMinutes": "60",
                "earlyDepTime": "11:00 AM",
                "workHours": "10",
                "status": "empty"
            }];

        $rootScope.active = 'attendance';
        angular.element(document).ready(function () {
            attendanceTable = angular.element('#attendanceTable').DataTable({
                data: attendanceDetails,
                serverSide: false,
                bDestroy: true,
                processing: true,
                responsive: true,
                fnDrawCallback: function (settings, ajax) {

                },
                language: {
                    zeroRecords: 'No data to display',
                    infoFiltered: ''
                },
                order: [[0, "desc"]],
                aoColumns: [{
                        data: 'id',
                        visible: false
                    }, {
                        title: "Employee Code",
                        data: 'empCode',
                    }, {
                        title: "Employee Name",
                        data: 'empName',
                    },{
                        title: "In Time",
                        data: 'inTime',
                    }, {
                        title: "Out Time",
                        data: 'outTime',
                    },{
                        title: "Late Minutes",
                        data: 'lateMinutes',
                    }, {
                        title: "Early Departure",
                        data: 'earlyDepTime',
                    },{
                        title: "Work Hours",
                        data: 'workHours',
                    }, {
                        title: "Status",
                        data: 'status',
                    }]
            });
            
        });

    };

    AttendanceMgmtCtrl.$inject = ["$scope", '$rootScope', 'Core_Service', 'Core_ModalService', 'urlConfig'];
    angular.module('coreModule').controller('AttendanceMgmtCtrl', AttendanceMgmtCtrl);
})();
