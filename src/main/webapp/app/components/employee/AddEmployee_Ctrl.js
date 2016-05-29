var addEmployeeTable
(function () {
    var AddEmployee_Ctrl = function ($scope, $state, $rootScope, Core_Service,urlConfig, $stateParams, Core_HttpRequest, validationService) {
        var vm = this;
        $rootScope.showLoader = true;
        vm.registration = {};
        if ($stateParams.id) {
            Core_Service.getCandidateImpl("api/employee/getAnEmployee", $stateParams.id).then(function (res) {
                vm.registration = res.data;               
                vm.isCheckboxEnable = true;
                vm.isChecked = true;
                $rootScope.showLoader = false;
            }, function (err) {
                vm.registration = {};
            });
        }
        vm.urlForLookups = "api/employee/getDropDownData";
        Core_Service.getAllLookupValues(vm.urlForLookups)
                .then(function (response) {
                    vm.lookups = response.data;
                }, function (error) {

                });

        $rootScope.active = 'employee';
        vm.cancelRegisteration = function (){
            $state.go("coreuser.employee")
        };
        vm.employeeRegister = function () {
            vm.registerUrl = "api/employee/saveOrUpdateEmployee";
            Core_Service.registerImpl(vm.registerUrl, vm.registration)
                    .then(function (response) {
                    }, function (error) {

                    });
        };        
        
        angular.element(document).ready(function () {
                addEmployeeTable = angular.element('#candidatesList').DataTable({
                ajax: urlConfig.http + window.location.host + urlConfig.api_root_path + "candidate/getCandidates?employee=true",
                serverSide: true,
                bDestroy: true,
                processing: true,
                responsive: true,
                sScrollX: '100%',                
                fnDrawCallback: function (settings, ajax) {
                    
                },
                language: {
                	zeroRecords: 'No data to dispay',
                    searchPlaceholder: 'Search',
                    search: '',
                    infoEmpty: '',
                    infoFiltered:''
                },
                order: [[ 0, "desc" ]],
                aoColumns: [ {
                    	data: 'id',
                    	visible : false
                	},{
                        title: "Candidate ID",
                        data: 'candidateId',
                    }, {
                        title: "Name",
                        data: 'firstName',
                    }, {
                        title: "Contact No",
                        data: 'officialContactNo',
                    }, {
                        title: "Country To Visit",
                        data: 'countryToVisit.description',
                    }, {
                        title: "Division",
                        data: 'division.description',
                    }, {
                        title: "Designation",
                        data: 'designation.code',
                    }, {
                        title: "Employment Status",
                        data: 'employmentStatus.description',
                    },{
                        data: 'id',
                        bSortable: false,
                        sClass: "button-column",
                        render: function (data) {
                            $rootScope.showLoader = false;
                            return '<div class="action-buttons">' +
                                    '<span  value="' + data + '" class="actions action-view fa-stack fa-lg pull-left" title="View">'+
                                    '<i class="fa fa-eye" aria-hidden="true"></i></span>' +
                                    '</div>'
                        }
                    }]
            });
            $('#candidatesList').on('click', '.action-view', function () {
            	console.log(this.getAttribute('value'));
                vm.getCandidate(this.getAttribute('value'));
            });
            $('#candidatesList tbody').on( 'click', 'tr', function () {
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                }
                else {
                    addEmployeeTable.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
                $rootScope.selectedCandId = addEmployeeTable.row($('tr.selected').index()).data().id;
                localStorage["selectedCandidate"] = addEmployeeTable.row($('tr.selected').index()).data().candidateId;
                localStorage["selectedCandidateId"] =  $rootScope.selectedCandId ;
            } );
            
            vm.addEmployeeNextStep=function(candidateId){        	
        	$scope.candidateId=candidateId;
        	$state.go('coreuser.employee.nextStep', {id: $rootScope.selectedCandId});
        	 Core_Service.getCandidateImpl("api/employee/getAnEmployee", $stateParams.id).then(function (res) {
                 vm.registration = res.data;               
                 vm.isCheckboxEnable = true;
                 vm.isChecked = true;
                 $rootScope.showLoader = false;
             }, function (err) {
                 vm.registration = {};
             });
        }
            
        });
        
        Core_Service.calculateSidebarHeight();
        $rootScope.showLoader = false;
    };

    AddEmployee_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'Core_Service','urlConfig', '$stateParams', 'Core_HttpRequest', 'validationService'];
    angular.module('coreModule')
            .controller('AddEmployee_Ctrl', AddEmployee_Ctrl);
})();




