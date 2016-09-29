(function () {
    var Reimbursement_Ctrl = function ($scope, $state, $rootScope, Core_Service, validationService) {
        $rootScope.active = 'reimbursement';
        var vm = this;
        vm.reim = {};
        vm.reimDetails = [];
        vm.reimSet = {};

        vs = new validationService({
            controllerAs: vm
        });
        vs.setGlobalOptions({
            debounce: 1500,
            scope: $scope,
            isolatedScope: $scope,
            preValidateFormElements: false,
            displayOnlyLastErrorMsg: true
        });

        Core_Service.getLoggedInUserDetails().then(function (res) {
            vm.reim.empno = res.employeeId;
            vm.reim.empname = res.employeeMaster.firstName + " " + res.employeeMaster.lastName;
            vm.reim.division = res.employeeMaster.division.description;
            vm.reim.currDate = moment().format("MM-HH-YYYY");
            vm.reim.manager = res.employeeMaster.firstName + " " + res.employeeMaster.lastName;
        }, function (err) {
            console.log(err)
        });

        Core_Service.getCurrency().then(function (res) {
            vm.currency = res;
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
                        language: {
                            zeroRecords: 'please fill the fields',
                            search: '',
                            infoEmpty: '',
                            infoFiltered: ''
                        },
                        aoColumns: [ {
                    	visible : false
                	},{
                        title: "Description",
                        data: 'description',
                        render: function (data) {
                        	return data == null? "":data;
                        }
                    }, {
                        title: "Currency",
                        data: 'currency',
                        render: function (data) {
                        	return data == null? "":data;
                        }
                    }, {
                        title: "Amount",
                        data: 'amount',
                        render: function (data) {
                        	return data == null? "":data;
                        }
                    }, {
                        title: "Remarks",
                        data: 'remarks',
                        render: function (data) {
                        	return data == null? "":data;
                        }
                    }]
                    }
            );
        });

        vm.addToTable = function (evt) {
            vm.reimTable.rows.add({"description": "dsfd","currency": "dsfd","amount": "dsfd","remarks": "dsfd"})
        };
        vm.back = function () {
            $state.go("coreuser.dashboard");
        };
        vm.reimburse = function () {};

    };

    Reimbursement_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'Core_Service', 'validationService'];
    angular.module('coreModule')
            .controller('Reimbursement_Ctrl', Reimbursement_Ctrl);
})();

