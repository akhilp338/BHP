(function () {
    var AttendanceMgmtCtrl = function ($scope, $rootScope, Core_Service, Core_ModalService, urlConfig) {
        var vm = this, attendanceTable;
        vm.attendance = {};
        $rootScope.active = 'attendance';
        Core_Service.getAttendanceDropDownData().then(function (res) {
    		vm.attendance.lookups = res;
	    }, function (err) {
	    	console.log(err)
	    });
        angular.element(document).ready(function () {
            attendanceTable = angular.element('#attendanceTable').DataTable({
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
