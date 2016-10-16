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
        vm.setDpOpenStatus = function (id) {
            vm[id] = true;
        };
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
                    }, {
                        title: "Date",
                        data: 'date',
                        render: function (data,display,row) {
                            var date = new Date(data);
                            var datestring = date.getDate() + "-" + (date.getMonth() + 1) + "-" + date.getFullYear();
                            return datestring;
                        }
                    }, {
                        title: "Employee Code",
                        data: 'employee.employeeId',
                    }, {
                        title: "Employee Name",
                        data: 'employee.id',
                        render: function (data,display,row) {
                        	debugger;
                            return row.employee.employeeMaster.firstName + " " + row.employee.employeeMaster.lastName;
                        }
                    }, {
                        title: "In Time",
                        data: 'inTime',
                    }, {
                        title: "Out Time",
                        data: 'outTime',
                    }, {
                        title: "Late Minutes",
                        data: 'lateMinutes',
                    }, {
                        title: "Early Departure",
                        data: 'earlyMinutes',
                    }, {
                        title: "Work Hours",
                        data: 'workHours',
                    }, {
                        title: "Status",
                        data: 'status',
                    }]

            });

        });
        vm.filterChange = function () {
        	debugger;
        	vm.attendance.filter
//        	var filters;
//    		if(vm.attendance.filter.emplyee == undefined || tradeFrom==''){
//    			tradeFrom = "01-01-1010";
//    		}
//    		if(vm.attendance.filter.status == undefined || tradeFrom==''){
//    			tradeTo = "31-12-2999";
//    		}
//    		filters=attendanceTable.columns(3).search(vm.attendance.filter.employee);
//    		filters=attendanceTable.columns(9).search("@"+tradeFrom+"@"+tradeTo.split(" ")[0]);
//    		
//    		if(filters) 
//                filters.draw();
//        	attendanceTable.column( $(this).data('index') ).search( this.value ).draw();
//            console.log(vm.attendance.filter)
        };

    };

    AttendanceMgmtCtrl.$inject = ["$scope", '$rootScope', 'Core_Service', 'Core_ModalService', 'urlConfig'];
    angular.module('coreModule').controller('AttendanceMgmtCtrl', AttendanceMgmtCtrl);
})();
