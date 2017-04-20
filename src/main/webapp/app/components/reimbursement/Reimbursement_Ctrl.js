(function () {
    var Reimbursement_Ctrl = function ($scope, $state, $rootScope, Core_Service, validationService, $timeout) {
        $rootScope.active = 'reimbursement';
        var vm = this;
        vm.reim = {};
        vm.tableData = {};
        vm.reimDetails = [];
        vm.basicInfoObj = {};
        vm.basicInfo = '';
        vs = new validationService({
            controllerAs: vm
        });
        vm.setDpOpenStatus = function (id) {
            vm[id] = true
        };
        Core_Service.getReimburseDropDownData().then(function (res) {
            vm.reim.lookups = res;
        }, function (err) {
            console.log(err)
        });
        vs.setGlobalOptions({
            debounce: 1500,
            scope: $scope,
            isolatedScope: $scope,
            preValidateFormElements: false,
            displayOnlyLastErrorMsg: true
        });
            
            vm.basicInfoObj['Employee No.'] = 'EMP-10-10'; 
            vm.basicInfoObj['Employee Name'] = 'Rajesh Kumar';
            vm.basicInfoObj['Division'] = 'HRMS';
            vm.basicInfoObj['Current Date'] = moment().format("MM-HH-YYYY");
            vm.basicInfoObj['Manager'] = 'Rafique';            
            for (var key in vm.basicInfoObj) {
                    vm.basicInfo += '<div class="item col-md-4 col-lg-4 col-sm-6 col-xs-12">' +
                            '<label class="item-label">' + key +
                            '</label><p class="item-label-desc"> :   ' + vm.basicInfoObj[key] +
                            '</p></div>';
            }
        Core_Service.getLoggedInUserDetails().then(function (res) {
            /*service is not working now, When service is active remove the above hardcoded section and uncomment this*/
            
            // vm.basicInfoObj['Employee No.'] = res.employeeId;
            // vm.basicInfoObj['Employee Name'] = res.employeeMaster.firstName + " " + res.employeeMaster.lastName;
            // vm.basicInfoObj['Division'] = res.employeeMaster.division.description;
            // vm.basicInfoObj['Current Date'] = moment().format("MM-HH-YYYY");
            // vm.basicInfoObj['Manager'] = res.employeeMaster.firstName + " " + res.employeeMaster.lastName;
            // for (var key in vm.basicInfoObj) {
            //         vm.basicInfo += '<div class="item col-md-4 col-lg-4 col-sm-6 col-xs-12">' +
            //                 '<label class="item-label">' + key +
            //                 '</label><p class="item-label-desc"> :   ' + vm.basicInfoObj[key] +
            //                 '</p></div>';
            // }
        }, function (err) {
            console.log(err)
        });
        angular.element(document).ready(function () {
            vm.reimTable = $('#reim-table').DataTable(
                    {
                        "paging": false,
                        "ordering": false,
                        "info": false,
                        "bFilter": false,
                        columnDefs: [
                            {
                                targets: [0, 1, 2],
                                className: 'mdl-data-table__cell--non-numeric'
                            }
                        ],
                        language: {
                            zeroRecords: 'please fill the fields',
                            search: '',
                            infoEmpty: '',
                            infoFiltered: ''
                        },
                        aoColumns: [{
                                title: "Date",
                                data: 'date',
                                render: function (data) {
                                    return data == null ? "" : moment(data).format("DD MMM YYYY");
                                }
                            }, {
                                title: "Description",
                                data: 'description',
                                render: function (data) {
                                    return data == null ? "" : data;
                                }
                            },
                            {
                                title: "Currency",
                                data: 'currency.description',
                                render: function (data) {
                                    return data == null ? "" : data;
                                }
                            }, {
                                title: "Amount",
                                data: 'amount',
                                render: function (data) {
                                    return data == null ? "" : data;
                                }
                            }, {
                                title: "Remarks",
                                data: 'remarks',
                                render: function (data) {
                                    return data == null ? "" : data;
                                }
                            }, {
                                data: 'id',
                                bSortable: false,
                                sClass: "button-column",
                                render: function (data) {
                                    return '<div class="action-buttons">' +
                                            '<span  class="actions action-delete fa-stack fa-lg pull-left" title="Remove">' +
                                            '<i class="fa fa-remove" aria-hidden="true"></i></span></div>'
                                }
                            }]
                    }
            );
        });
        $('#reim-table tbody').on('click', '.action-delete', function () {
//Core_Service.sweetAlertWithConfirm("Delete ???", "Are you sure to delete this details?", "warning", function(){
            var index = angular.element(this).parents('tr').index() + 1;
            vm.reimDetails.splice(index, 1);
            vm.reimTable.row(angular.element(this).parents('tr'))
                    .remove()
                    .draw(true);
            //});            
        });
        vm.addToTable = function (evt) {
            if(vs.checkFormValidity($scope.tableDataForm)){
                var arr = [];
//            vm.tableData.date = moment(vm.tableData.date).format("DD MMM YYYY");
            arr.push(vm.tableData);
            vm.reimDetails.push(vm.tableData);
            vm.reimTable.rows.add(arr).draw(false);
            vm.tableData = {};
            }
            
        };
        vm.reimburse = function () {

            var data = vm.reimDetails;

            Core_Service.sweetAlertWithConfirm("Reimburse details filled!", "Are you sure to submit?", "warning", function () {
//               
                Core_Service.saveReimburse(data).then(function (res) {
                    if (res) {
                        Core_Service.sweetAlertWithConfirm("Reimburse successfully sent for approval!", "Do You want to upload supporting Docs?", "success", function (isConfirm) {
                            if (isConfirm) {
                                $rootScope.isReimDocs = true;
                                $rootScope.isEmpDocs = false;
                                $state.go("coreuser.reimbursement.upload");
                            } else {
                                $timeout(function () {
                                    Core_Service.sweetAlert("Done!", "No Docs Uploaded", "success", "coreuser.reimbursement");
                                }, 200);

                            }
                        });
                    } else {
                        Core_Service.sweetAlert("Oops!", "data", "error", "coreuser.reimbursement");
                    }
                },
                        function (error) {
                            Core_Service.sweetAlertWithConfirm("Done!", "error", "success");
                        });
            });
            console.log(vm.reimDetails);
        };
    };
    Reimbursement_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'Core_Service', 'validationService','$timeout'];
    angular.module('coreModule')
            .controller('Reimbursement_Ctrl', Reimbursement_Ctrl);
})();

