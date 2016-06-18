(function () {
    var Offerletter_Ctrl = function ($scope, $state, $rootScope, Core_Service, urlConfig,$stateParams, Core_HttpRequest, validationService) {
        var vm = this;
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

        if ($stateParams.id) {
            Core_Service.getCandidateImpl("api/candidate/getCandidate", $stateParams.id).then(function (res) {
                console.log(res.data);
            }, function (err) {
                vm.registration = {};
            });
        }
        vm.offerletter = {};
        vm.offerletter.earnings = {};
        vm.offerletter.deductions = {};
        vm.offerletter.flexi = 0;
        vm.offerletter.fixed = 0;
        vm.offerletter.earnings.basicSal = 0;
        vm.offerletter.earnings.hra = 0;
        vm.offerletter.earnings.medical = 0;
        vm.offerletter.earnings.conveyance = 0;
        vm.offerletter.earnings.telephone = 0;
        vm.offerletter.earnings.specialAllow = 0;
        vm.offerletter.earnings.personalAllow = 0;
        vm.offerletter.earnings.lta = 0;
        vm.offerletter.earnings.fbk = 0;
        vm.offerletter.earnings.incentive = 0;
        vm.offerletter.earnings.statutory = 0;

        vm.offerletter.deductions.proTax = 0;
        vm.offerletter.deductions.pfEmployee = 0;
        vm.offerletter.deductions.pfEmployer = 0;
        vm.offerletter.deductions.ESIEmployer = 0;
        vm.offerletter.deductions.ESIEmployee = 0;
        vm.offerletter.deductions.epfEmployer = 0;
        vm.offerletter.deductions.epfEmployee = 0;
        vm.offerletter.deductions.leaveEncash = 0;
        vm.offerletter.deductions.insurance = 0;
        vm.offerletter.deductions.gratuity = 0;
        vm.offerletter.deductions.deductions = 0;

        vm.offerletter.total = vm.offerletter.flexi + vm.offerletter.fixed;
        vm.verifyOfferLetter = function(){
           $state.go("coreuser.candidate.offerletter.verify") 
        };
        
        vm.getSalarySplits = function(){
            vm.url = "api/candidate/getSalarySplit";
            var salaryDto={};
            salaryDto.salary=vm.offerletter.grossSalary;
            salaryDto.grade=vm.offerletter.grade;
            Core_Service.getSalaryDetails(vm.url,this.offerletter.grossSalary)
                    .then(function (response) {
                    	Core_Service.sweetAlert("Done!",response.Message,"success","coreuser.candidate");  
                        console.log(response)
                    }, function (error) {
                        console.log(error)
                    });
        }
        
        vm.back = function (){
          $state.go("coreuser.candidate.offerletter")   
        };
        angular.element(document).ready(function () {
            addEmployeeTable = angular.element('#candidatesList').DataTable({
            ajax: urlConfig.http + window.location.host + urlConfig.api_root_path + "candidate/getCandidates?employee=true",
            serverSide: true,
            bDestroy: true,
            processing: true,
            responsive: true,
            sScrollX: '100%', 
            iDisplayLength: 2,
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
            sDom: '<"search-box"r>lftip',
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
                }]
        });
        $("#candidatesList").on('click',' tbody tr', function (){
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
            vm.employeeSummary["Contact No"] = data.personalContactNo;
            vm.employeeSummary["Skillset"] = data.skillSet.join(", ");                
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
        $('#candidatesList tbody').on( 'click', 'tr', function () {
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

    };
    Offerletter_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'Core_Service', 'urlConfig','$stateParams', 'Core_HttpRequest', 'validationService'];
    angular.module('coreModule')
            .controller('Offerletter_Ctrl', Offerletter_Ctrl);
})();



