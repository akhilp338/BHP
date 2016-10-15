(function () {
var Reimbursement_Ctrl = function ($scope, $state, $rootScope, Core_Service, validationService) {
$rootScope.active = 'reimbursement';
        var vm = this;
        vm.reim = {};
        vm.reimDetails = [];
        vs = new validationService({
        controllerAs: vm
        });
        vm.setDpOpenStatus = function (id) {
        vm[id] = true
        };
        Core_Service.getReimburseDropDownData().then(function (res) {
        	debugger;
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
        Core_Service.getLoggedInUserDetails().then(function (res) {
vm.reim.empno = res.employeeId;
        vm.reim.empname = res.employeeMaster.firstName + " " + res.employeeMaster.lastName;
        vm.reim.division = res.employeeMaster.division.description;
        vm.reim.currDate = moment().format("MM-HH-YYYY");
        vm.reim.manager = res.employeeMaster.firstName + " " + res.employeeMaster.lastName;
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
        targets: [ 0, 1, 2 ],
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
                return data == null ? "" : data;
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
                data: 'currency',
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
                $rootScope.showLoader = false;
                        return '<div class="action-buttons">' +
                        '<span  class="actions action-delete fa-stack fa-lg pull-left" title="View">' +
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
        console.log(vm.reimDetails)
        vm.reimTable.row(angular.element(this).parents('tr'))
        .remove()
        .draw(true);
        //});            
});
        vm.addToTable = function (evt) {
        var arr = [];
                vm.reimSet = {
                date: angular.element("#reim-date").val(),
                        description: angular.element("#reim-description").val(),
                        currency: angular.element("#reim-currency").val(),
                        amount: angular.element("#reim-amount").val(),
                        remarks: angular.element("#reim-remarks").val()
                };
                arr.push(vm.reimSet);
                vm.reimDetails.push(vm.reimSet);
                vm.reimTable.rows.add(arr).draw(false);
        };
        vm.back = function () {
        $state.go("coreuser.dashboard");
        };
        vm.reimburse = function () {
        console.log(vm.reimDetails);
        };
};
        Reimbursement_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'Core_Service', 'validationService'];
        angular.module('coreModule')
        .controller('Reimbursement_Ctrl', Reimbursement_Ctrl);
        })();

