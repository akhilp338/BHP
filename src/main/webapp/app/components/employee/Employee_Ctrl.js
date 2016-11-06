(function () {
    var Employee_Ctrl = function ($scope, $state, $rootScope, urlConfig, Core_Service,Core_ModalService) {
        var vm = this,addEmployeeTable;
        $rootScope.active = 'employee';
        vm.addEmployee = function(){
        	$state.go("coreuser.employee.add");
        };        
        vm.getEmployee = function(data, actionType){
            	if(actionType=='action-view'){
            		vm.viewEmployee(data);
            	}else if(actionType=='generate-credentials' ){
            		vm.generateCredentials(data);
            	}
        };
        vm.viewEmployee = function (data) {
            Core_ModalService.openViewEmployeeModal(data);
        };
        vm.generateCredentials = function(data){
        	Core_ModalService.openGenerateCredentialsModal(data);
        }
        
        vm.getEmpFullName = function( rowData ){
            if(rowData.employeeMaster){
        	var firstName = rowData.employeeMaster.firstName != null ? rowData.employeeMaster.firstName : '',
        		lastName = rowData.employeeMaster.lastName != null ? rowData.employeeMaster.lastName : ' - ',
        		name = rowData != null ? firstName + " " + lastName : '-';
            }
            else{
                name = "";
            }
        	return name;
        };
        
        vm.nullCheck = function ( data ){
        	var displayData = data != null ? data : '-';
        	return displayData;
        };

        angular.element(document).ready(function () {
                addEmployeeTable = angular.element('#employeeList').DataTable({
                ajax: urlConfig.http + window.location.host + urlConfig.api_root_path + "employee/getEmployee",
                serverSide: true,
                bDestroy: true,
                processing: true,
                responsive: true,
                sScrollX: '100%',                
                fnDrawCallback: function (settings, ajax) {
                    Core_Service.calculateSidebarHeight();
                },
                language: {
                	zeroRecords: 'No data to display',
                    searchPlaceholder: 'Search',
                    search: '',
                    infoEmpty: '',
                    infoFiltered:''
                },
                order: [[ 0, "desc" ]],
                aoColumns: [ {
                    	data: 'id',
                    	visible : false,
                    	render : function (data) {
                        	return vm.nullCheck(data);
                        }
                	},{
                        title: "Employee ID",
                        data: 'employeeId',
                        render : function (data) {
                        	return vm.nullCheck(data);
                        }
                    }, {
                        title: "Full Name",
                        data: 'employeeMaster.id',
                        render: function (data, type, row) {
                        	return vm.getEmpFullName(row);
                        }
                    },  {
                        title: "First Name",
                        data: 'employeeMaster.firstName',
                        visible: false,
                        render : function (data) {
                        	return vm.nullCheck(data);
                        }
                    },  {
                        title: "Last Name",
                        data: 'employeeMaster.lastName',
                        visible: false,
                        render : function (data) {
                        	return vm.nullCheck(data);
                        }
                    },  {
                        title: "Designation",
                        data: 'employeeMaster.designation.description',
                        render : function (data) {
                        	return vm.nullCheck(data);
                        }

                    }, {
                        title: "Location",
                        data: 'workLocation.description',
                        render : function (data) {
                        	return vm.nullCheck(data);
                        }
                    },{
                        title: "BU",
                        data: 'businessUnit.description',
                        render : function (data) {
                        	return vm.nullCheck(data);
                        }
                    },{
                        title: "Official Email ID",
                        data: 'employeeMaster.officialEmail',
                        render : function (data) {
                        	return vm.nullCheck(data);
                        }
                    },{
                        title: "Employment Status",
                        data: 'employeeMaster.employmentStatus.description',
                        render : function (data) {
                        	return vm.nullCheck(data);
                        }
                    },{
                        data: 'id',
                        bSortable: false,
                        sClass: "button-column",
                        render: function (data) {
                            return '<div class="action-buttons">' +
                                    '<span  value="' + data + '" class="actions action-view fa-stack fa-lg pull-left" title="View">'+
                                    '<i class="fa fa-eye" aria-hidden="true"></i></span>' +
                                    '<span value="' + data + '" class="actions action-edit fa-stack fa-lg pull-left" title="Edit">'+
                                    '<i class="fa fa-pencil-square-o" aria-hidden="true"></i></i></span></div>'
                        },
                        initComplete: function( settings, json ) {
                            $rootScope.isShowLoading = false;
                         }
                    }]
            });
            $('#employeeList').on('click', '.action-view', function () {
                vm.getEmployee(addEmployeeTable.data()[$(this).parents("tr").index()],'action-view');
            });
            $('#employeeList').on('click', '.action-edit', function () {
                var data = addEmployeeTable.row($(this).parents("tr").index()).data();
                $rootScope.selectedCandId = data.id;
                localStorage["selectedCandidate"] = data.employeeId;
                localStorage["selectedCandidateName"] = vm.getEmpFullName(data);
                localStorage["selectedCandidateId"] =  data.employeeMaster.id;
                $rootScope.isShowLoader = true;
                var empId = this.getAttribute('value');
                $state.go('coreuser.employee.edit', {id: empId});
            });
            $('#employeeList tbody').on( 'click', 'tr', function () {            	
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                }
                else {
                    addEmployeeTable.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
            } );
            $("#generateCredentials").on('click',function () {
                var empDetails = addEmployeeTable.data()[$('#employeeList tbody .selected').index()]
            	var selectedEmployee =$('#employeeList tbody .selected .action-view')[0];
            	if( selectedEmployee != undefined ){
            		$rootScope.id = selectedEmployee.getAttribute('value');
            		vm.getEmployee(empDetails,'generate-credentials');
            	}else{
                    Core_Service.sweetAlert("Hey!", "Please select atleast one candidate", "warning");
                }
            	$rootScope.isShowLoader = true;
            	
            });
        })
        Core_Service.calculateSidebarHeight();
         };

    Employee_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'urlConfig', 'Core_Service','Core_ModalService'];
    angular.module('coreModule')
            .controller('Employee_Ctrl', Employee_Ctrl);
})();

