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
//                data: attendanceDetails,
            	ajax: urlConfig.http + window.location.host + urlConfig.api_root_path + "attendance/getAttendances",
                serverSide: true,
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
                    },{
                        title: "Date",
                        data: 'date',
                        render: function (data,row,display) {
                        	var date =  new Date(data);
                        	var datestring = date.getDate()  + "-" + (date.getMonth()+1) + "-" + date.getFullYear();
                        	return datestring;
                        }
                    }, {
                        title: "Employee Code",
                        data: 'employee.employeeId',
                    }, {
                        title: "Employee Name",
                        data: 'employee.employeeMaster',
                        render: function (data,row,display) {
                        	return data.firstName+" "+data.lastName;
                        }
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
                        data: 'earlyMinutes',
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
