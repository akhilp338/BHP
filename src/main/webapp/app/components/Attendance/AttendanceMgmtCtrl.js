(function () {
    var AttendanceMgmtCtrl = function ($scope, $rootScope, Core_Service, Core_ModalService, urlConfig) {
        var vm = this, attendanceTable;
        $rootScope.isAttendance = true;
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
                $scope.$watchCollection('vm.reimFile', function() {
                if(vm.reimFile){
                   var fd = new FormData();
                       fd.append('file', vm.reimFile); 
                Core_Service.saveReimFile(fd).then(function (res) {
                    console.log(res);
                }, function (err) {
                    console.log(err)
                });
                }
            });
            attendanceTable = angular.element('#attendanceTable').DataTable({
                ajax: urlConfig.http + window.location.host + urlConfig.api_root_path + "attendance/getAttendances",
                serverSide: true,
                bDestroy: true,
                processing: true,
                responsive: true,
                sScrollX: '100%', 
                bLengthChange:false,
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
                        render: function (data, display, row) {
                            return data == null ? "" : moment(data).format("DD MMM YYYY");
                            ;
                        }
                    }, {
                        title: "Employee Code",
                        data: 'employee.employeeId',
                    }, {
                        title: "Employee Name",
                        data: 'employee.id',
                        render: function (data, display, row) {
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
            var filters;
            if ( vm.attendance.filter.emplyee != undefined ) {
            	if( vm.attendance.filter.emplyee.id == null )
            		filters = attendanceTable.columns(3).search('');
            	else
            		filters = attendanceTable.columns(3).search(vm.attendance.filter.emplyee.id);
            }
            if ( vm.attendance.filter.status != undefined ) {
            	if( vm.attendance.filter.status.id == null )
            		filters = attendanceTable.columns(9).search('');
            	else
            		filters = attendanceTable.columns(9).search(vm.attendance.filter.status.id);
            }
            if (filters)
                filters.draw();
            else
            	attendanceTable.search( '' ).columns().search( '' ).draw();
            console.log(vm.attendance.filter)
        };

    };

    AttendanceMgmtCtrl.$inject = ["$scope", '$rootScope', 'Core_Service', 'Core_ModalService', 'urlConfig'];
    angular.module('coreModule').controller('AttendanceMgmtCtrl', AttendanceMgmtCtrl);
})();
