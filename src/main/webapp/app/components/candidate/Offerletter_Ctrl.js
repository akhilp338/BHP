(function () {
    var Offerletter_Ctrl = function ($scope, $state, $rootScope, Core_Service, urlConfig, $stateParams, Core_HttpRequest, validationService) {
        var vm = this;
        vs = new validationService({
            controllerAs: vm
        });

        vm.offerletter = {};
        $rootScope.salaryCreds = {};
        vm.offerletter.earnings = {};
        vm.offerletter.deductions = {};
        vm.offerletter.display = {};
        vs.setGlobalOptions({
            debounce: 1500,
            scope: $scope,
            isolatedScope: $scope,
            preValidateFormElements: false,
            displayOnlyLastErrorMsg: true
        });

        if ($stateParams.id) {
            Core_Service.getCandidateImpl("api/candidate/getCandidate", $stateParams.id).then(function (res) {
                vm.getSalaryGrades();
                vm.offerletter.display.candidateId = res.data.candidateId;
                vm.offerletter.display.name = res.data.firstName + " " + res.data.lastName;
            }, function (err) {
                vm.registration = {};
            });
        }

        vm.verifyOfferLetter = function () {
            $state.go("coreuser.candidate.verify", {verifyId: $stateParams.id})
        };

        vm.getSalaryGrades = function () {
            vm.getSalaryGradesUrl = "api/candidate/getSalaryGrades";
            Core_Service.getSalaryGradesUrl(vm.getSalaryGradesUrl)
                    .then(function (response) {
                        vm.offerletter.grades = response.data;
                    }, function (error) {

                    });
        };

        vm.getSalarySplits = function () {
            vm.url = "api/candidate/getSalarySplit";
            Core_Service.getSalaryDetails(vm.url, this.offerletter.grossSalary)
                    .then(function (response) {                           
                            if($stateParams.id)
                              vm.offerletter = response.data;
                            else if($stateParams.verifyId)
                            
                    }, function (error) {
                        console.log(error)
                    });
        };

        vm.back = function () {
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
                vm.employeeSummary["Name"] = data.firstName + " " + data.lastName;
                vm.employeeSummary["Candidate Id"] = data.candidateId;
                vm.employeeSummary["Country"] = data.countryOfOrigin.description;
                vm.employeeSummary["DOB"] = moment(data.dob).format("DD MMM YYYY hh:mm a");
                vm.employeeSummary["Designation"] = data.designation.description;
                vm.employeeSummary["Passport"] = data.passport.passportNo;
                ;
                vm.employeeSummary["Email Id"] = data.personalEmail;
                vm.employeeSummary["Contact No"] = data.personalContactNo;
                vm.employeeSummary["Skillset"] = data.skillSet.join(", ");
                for (var key in vm.employeeSummary) {
                    vm.Employeetemplate += '<div class="item col-md-4 col-lg-4 col-sm-6 col-xs-12">' +
                            '<label class="item-label">' + key +
                            '</label><p class="item-label-desc"> :   ' + vm.employeeSummary[key] +
                            '</p></div>';
                }
                $(".candidate-summary").html(vm.Employeetemplate);
            });

            $('#candidatesList').on('click', '.action-view', function () {
                console.log(this.getAttribute('value'));
                vm.getCandidate(this.getAttribute('value'));
            });
            $('#candidatesList tbody').on('click', 'tr', function () {
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

        });

    };
    Offerletter_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'Core_Service', 'urlConfig', '$stateParams', 'Core_HttpRequest', 'validationService'];
    angular.module('coreModule')
            .controller('Offerletter_Ctrl', Offerletter_Ctrl);
})();



