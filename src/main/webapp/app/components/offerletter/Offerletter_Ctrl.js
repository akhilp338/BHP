(function () {
    var Offerletter_Ctrl = function ($scope, $state, $rootScope, Core_Service, urlConfig, $stateParams, Core_HttpRequest, validationService) {
        var vm = this;
        vs = new validationService({
            controllerAs: vm
        });
        vm.isCandidateSelected = false;
        vm.offerletter = {};
        $rootScope.salaryCreds = {};
        vm.employeeSummary = {};
        vm.offerletter.display = {};
        vm.offerletter.candidate ='';
        vm.offerletter.id = $rootScope.selectedCandId ;
        vs.setGlobalOptions({
            debounce: 1500,
            scope: $scope,
            isolatedScope: $scope,
            preValidateFormElements: false,
            displayOnlyLastErrorMsg: true
        });
        
            Core_Service.getCandidateImpl("api/candidate/getCandidate", $rootScope.selectedCandId).then(function (res) {
                vm.getSalaryGrades();
                vm.offerletter.display.candidateId = res.data.candidateId;
                vm.offerletter.display.name = res.data.firstName + " " + res.data.lastName;
            }, function (err) {
                vm.registration = {};
            });
        
        vm.verifyOfferLetter = function () {
            $rootScope.verifyParams = {
                fixed:vm.offerletter.grossSalary,
                grade:vm.offerletter.selectedGrade
            };
            $state.go("coreuser.offerletter.verify", {verifyId: $rootScope.selectedCandidate})
        };

        vm.getSalaryGrades = function () {
            vm.getSalaryGradesUrl = "api/candidate/getSalaryGrades";
            Core_Service.getSalaryGradesUrl(vm.getSalaryGradesUrl)
                    .then(function (response) {
                        vm.offerletter.grades = response.data;
                    }, function (error) {

                    });
        };

        vm.getSalarySplits = function (params) {
            vm.url = "api/candidate/getSalarySplit";
            if(!params){
                params = {
                    fixed:vm.offerletter.grossSalary,
                    grade:vm.offerletter.selectedGrade
                }
            }
            Core_Service.getSalaryDetails(vm.url,params)
                    .then(function (response) {    
                        angular.extend(vm.offerletter,params);
                        angular.extend(vm.offerletter,response.data);
                    }, function (error) {
                        console.log(error)
                    });
        };
        ($stateParams.verifyId && $rootScope.verifyParams) ? vm.getSalarySplits($rootScope.verifyParams) : $state.go("coreuser.offerletter");
        vm.back = function () {
            $state.go("coreuser.offerletter");
        };
        $rootScope.active = 'offerletter';
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
                    infoFiltered: ''
                },
                order: [[0, "desc"]],
                sDom: '<"search-box"r>lftip',
                aoColumns: [{
                        data: 'id',
                        visible: false
                    }, {
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
            $("#candidatesList").on('click', ' tbody tr', function () {
                vm.Employeetemplate = "";
                $(".candidate-summary").removeClass("init")
                var data = addEmployeeTable.data()[$(this).index()];
                vm.getEmployeeSummary(data)
            });

            $('#candidatesList').on('click', '.action-view', function () {
                console.log(this.getAttribute('value'));
                vm.getCandidate(this.getAttribute('value'));
            });
            $('#candidatesList tbody').on('click', 'tr', function () {
                vm.isCandidateSelected = true;
                $scope.$apply()
                if ($(this).hasClass('selected')) {
                    $(this).removeClass('selected');
                } else {
                    addEmployeeTable.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
                var data = addEmployeeTable.row($('tr.selected').index()).data();
                $rootScope.selectedCandId = data.id;
                localStorage["selectedCandidate"] = data.candidateId;
                localStorage["selectedCandidateName"] = data.firstName + " " + data.lastName;
                localStorage["selectedCandidateId"] = $rootScope.selectedCandId;
            });

            vm.addEmployeeNextStep = function (candidateId) {
                $scope.candidateId = candidateId;
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

        vm.getEmployeeSummary = function(data){
        	$rootScope.selectedCandidate=data;
            vm.employeeSummary["Name"] = data.firstName + " " + data.lastName;
            vm.employeeSummary["Candidate Id"] = data.candidateId;
            vm.employeeSummary["Country"] = data.countryOfOrigin.description;
            vm.employeeSummary["DOB"] = moment(data.dob).format("DD MMM YYYY hh:mm a");
            vm.employeeSummary["Designation"] = data.designation.description;
            vm.employeeSummary["Passport"] = data.passport.passportNo;;
            vm.employeeSummary["Email Id"] = data.personalEmail;
            vm.employeeSummary["Contact No"] = data.personalContactNo;
            vm.employeeSummary["Skillset"] = [];
            for(var j=0; j<data.skillSet.length; j++){
                vm.employeeSummary.Skillset.push(data.skillSet[j].skillName)
            }
            for (var key in vm.employeeSummary) {
                vm.Employeetemplate += '<div class="item col-md-4 col-lg-4 col-sm-6 col-xs-12">' +
                        '<label class="item-label">' + key +
                        '</label><p class="item-label-desc"> :   ' + vm.employeeSummary[key] +
                        '</p></div>';
            }
            $(".candidate-summary").html(vm.Employeetemplate);
        }
        
        vm.generateOfferLetter = function () {
        	vm.offerletter.candidate=$rootScope.selectedCandidate;
            vm.generateOfferLetterUrl = "api/candidate/saveSalaryAndOfferLetter";
            Core_Service.generateOfferLetterImpl(vm.generateOfferLetterUrl,vm.offerletter)
                    .then(function (response) {
                    	Core_Service.sweetAlert("Done!",response.data.data,"success","coreuser.offerletter");  
                    }, function (error) {

                    });
        };
        
        
        });

    };
    Offerletter_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'Core_Service', 'urlConfig', '$stateParams', 'Core_HttpRequest', 'validationService'];
    angular.module('coreModule')
            .controller('Offerletter_Ctrl', Offerletter_Ctrl);
})();



