var addEmployeeTable
(function () {
    var AddEmployee_Ctrl = function ($scope, $state, $rootScope, Core_Service,urlConfig, $stateParams, Core_HttpRequest, validationService) {
        var vm = this;
        $rootScope.showLoader = true;
        vm.registration = {};
        vm.employeeSummary = {};
        vm.isInit = true;
        vm.Employeetemplate = 'Please select a candidate from the above table.';
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
        vm.back = function (){
            $state.go('coreuser.employee');
        };
        
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
                iDisplayLength: 3,
                fnDrawCallback: function (settings, ajax) {
                    
                },
                language: {
                    zeroRecords: 'No data to display',
                    searchPlaceholder: 'Search',
                    search: '',
                    infoEmpty: '',
                    infoFiltered:''
                },
                order: [[ 0, "desc" ]],
                sDom: '<"search-box"r>lftip',
                aoColumns: [ {
                    	data: 'id',
                    	visible : false
                	},{
                        title: "Candidate ID",
                        data: 'candidateId',
                        render: function (data) {
                        	return data == null? "":data;
                        }
                        
                    }, {
                        title: "Name",
                        data: 'firstName',
                        render: function (data) {
                        	return data == null? "":data;
                        }
                    }, {
                        title: "Contact No",
                        data: 'officialContactNo.number',
                        render: function (data) {
                        	return data == null? "":data;
                        }
                    }, {
                        title: "Country To Visit",
                        data: 'countryToVisit.description',
                        render: function (data) {
                        	return data == null? "":data;
                        }
                    }, {
                        title: "Division",
                        data: 'division.description',
                        render: function (data) {
                        	return data == null? "":data;
                        }
                    }, {
                        title: "Designation",
                        data: 'designation.code',
                        render: function (data) {
                        	return data == null? "":data;
                        }
                    }, {
                        title: "Employment Status",
                        data: 'employmentStatus.description',
                        render: function (data) {
                        	return data == null? "":data;
                        }
                    }]
            });
            $("#candidatesList").on('click',' tbody tr', function (){
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                }
                else {
                    addEmployeeTable.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
                var data = addEmployeeTable.row($('tr.selected').index()).data();
                $rootScope.selectedCandId = data.id;
                localStorage["selectedCandidate"] = data.candidateId;
                localStorage["selectedCandidateName"] = data.firstName + " " + data.lastName;
                localStorage["selectedCandidateId"] =  $rootScope.selectedCandId ;
                vm.Employeetemplate = "";
                $(".candidate-summary").removeClass("init")
                var data = addEmployeeTable.data()[$(this).index()];
                vm.employeeSummary["Name"] = data.firstName + " " + data.lastName;
                vm.employeeSummary["Candidate Id"] = data.candidateId;
                vm.employeeSummary["Country"] = data.countryOfOrigin.description;
                vm.employeeSummary["DOB"] = moment(data.dob).format("DD MMM YYYY hh:mm a");
                vm.employeeSummary["Designation"] = data.designation.description;
                vm.employeeSummary["Passport"] = data.passport.passportNo;;
                vm.employeeSummary["Email Id"]= data.personalEmail;
                vm.employeeSummary["Country Code"] = "+"+data.personalContactNo.country.phoneCode;
                vm.employeeSummary["Contact No"] = data.personalContactNo.number;
//                vm.employeeSummary["Skillset"] = data.skillSet.skillName.join(", ");                
                for (var key in vm.employeeSummary){
                    vm.Employeetemplate += '<div class="item col-md-4 col-lg-4 col-sm-6 col-xs-12">'+  
                                '<label class="item-label">'+ key +
                                '</label><p class="item-label-desc"> :   '+ vm.employeeSummary[key] +
                                '</p></div>';
                }
               $(".candidate-summary").html(vm.Employeetemplate);
            });
            
            $('#candidatesList').on('click', '.action-view', function () {
            	console.log(this.getAttribute('value'));
                vm.getCandidate(this.getAttribute('value'));
            });
            
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




