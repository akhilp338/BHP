(function () {
    var Reim_Review_Ctrl = function ($scope, $timeout, $rootScope, Core_Service, urlConfig, $stateParams, $window, validationService, Core_HttpRequest) {
        var vm = this;
        vm.isInit = true;
        vm.reimSummary = {};
        vm.reimReviewData = {};
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
        
        vm.Reimtemplate = 'No Data fetched';
        angular.element(document).ready(function() {
                $rootScope.isDataLoader = true;
                vm.reimReviewTable = angular.element('#reim-review').DataTable({
                                serverSide: false,
                                "autoWidth": false,
                                bDestroy: true,
                                "paging": false,
                                "ordering": false,
                                "info": false,
                                "bFilter": false,
	                        processing: true,
	                        responsive: true,                
	                        fnDrawCallback: function (settings, ajax) {
	                        	Core_Service.calculateSidebarHeight();
	                        },
                                language : {
                                        zeroRecords : 'No data to display',
                                        infoEmpty : 'No records available',
                                        infoFiltered : 'No data to display'
                                },
                                aoColumns : [
                                            {
                                                "sTitle": "Id",
                                                "mDataProp": 'id',
                                                "visible": false
                                            },
                                            {   
                                                "sTitle": "<input type='checkbox' class='hand-cursor header-check'></input>",
                                                "mDataProp": null,
                                                "sWidth": "10px",
                                                "sDefaultContent": "<input type='checkbox' class='hand-cursor data-check'></input>",
                                                "bSortable": false
                                            },
                                            {
                                                "sTitle": "Date",
                                                "sWidth": "20%",
                                                "mDataProp": 'date',
                                                "bSortable": true,
                                                render: function (data) {
                                                    return moment(data).format("MM-HH-YYYY");
                                                }
                                            },
                                            {
                                                "sTitle": "Description",
                                                "sWidth": "30%",
                                                "mDataProp": 'description',
                                                "bSortable": true
                                            },
                                            {
                                                "sTitle": "Currency",
                                                "sWidth": "20%",
                                                "mDataProp": 'currency.description',
                                                "bSortable": true
                                            },
                                            {
                                                "sTitle": "Remarks",
                                                "sWidth": "20%",
                                                "mDataProp": 'remarks',
                                                "bSortable": true
                                            }],
            });
          
             });
        
        if ($stateParams.id) {
        Core_Service.getTaskReviewDetails($stateParams.id).then(function(res){
            console.log(vm.reimReviewTable)
            vm.reimReviewTable.rows.add(res.expenses).draw();
            vm.Reimtemplate = "<div class='reim-summary-container col-md-10 col-lg-10 col-sm-10 col-xs-12'>";
            $(".reim-summary").removeClass("init");
            vm.reimSummary['Employee No.'] = res.employee.employeeId;
            vm.reimSummary['Current Date'] = moment().format("MM-HH-YYYY");
            vm.reimSummary['Name'] = res.employee.employeeMaster.firstName + ' ' + res.employee.employeeMaster.lastName ;
            vm.reimSummary['Reporting Manager'] = res.employee.employeeMaster.division.description;
            vm.reimSummary['Division/BU'] = res.employee.employeeMaster.division.description + '/' + res.employee.businessUnit.description;
            vm.reimSummary['Amount'] = res.employee.amount ? res.employee.amount : null;
            for (var key in vm.reimSummary){
                if(vm.reimSummary[key]){
                    vm.Reimtemplate += '<div class="item col-md-6 col-lg-6 col-sm-6 col-xs-12">'+  
                                '<label class="col-md-6 col-lg-6 col-sm-6 col-xs-12 item-label">'+ key +
                                '</label><p class="col-md-6 col-lg-6 col-sm-6 col-xs-12 item-label-desc"> :   '+ vm.reimSummary[key] +
                                '</p></div>';
                }
            }
            vm.Reimtemplate += '</div>';
               angular.element(".reim-summary").html(vm.Reimtemplate);
           $timeout(function(){
               $rootScope.isDataLoader = false;  
           },1000);     
        },function(err){
             console.log(err);
        });
    }
    vm.approve = function(){
        if(vs.checkFormValidity($scope)){
            
        }
    };
};
    Reim_Review_Ctrl.$inject = ["$scope", '$timeout', '$rootScope', 'Core_Service', 'urlConfig', '$stateParams', '$window', 'validationService', 'Core_HttpRequest'];
    angular.module('coreModule')
            .controller('Reim_Review_Ctrl', Reim_Review_Ctrl);
})();