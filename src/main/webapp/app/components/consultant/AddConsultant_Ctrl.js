(function () {
    var AddConsultant_Ctrl = function ($scope, $state, $rootScope, Core_Service, $stateParams, $timeout, validationService, $mdConstant) {
        var vm = this;
        vm.isFileInput = false;
        $rootScope.showLoader = true;
        var countryType = ["permenant", "current", "onsite", "bank"];
        vm.setDpOpenStatus = function (id) {
            vm[id] = true
        };
        vm.add = {};
        $rootScope.isShowLoader = true;
        vm.registration = {};
        vm.attendance = {};
        vm.attendance.filter = {};
        vm.registration.skillSet = [];
        vm.back = function () {
            $state.go('coreuser.consultant');
        };
        vm.dobDateOptions = {
            maxDate: new Date().getTime()
        };
        vm.dobMax = new Date().getTime();
        if ($stateParams.id) {
            Core_Service.getCandidateImpl("api/consultant/getConsultant", $stateParams.id).then(function (res) {
                vm.registration = res.data;
                for (var i = 0; i < countryType.length; i++) {
                    vm.getStatesByCountry(vm.registration.permanentAddress.city.state.country.id, countryType[i]);
                    vm.getCitiesByStates(vm.registration.permanentAddress.city.state.id, countryType[i]);
                }
                if (res.data.skillSet) {
                    var expYr, expMnth;
                    for (var m = 0; m < vm.registration.skillSet.length; m++) {
                        expYr = vm.registration.skillSet[m].experienceYear ? vm.registration.skillSet[m].experienceYear : 0;
                        expMnth = vm.registration.skillSet[m].experienceMonth ? vm.registration.skillSet[m].experienceMonth : 0
                        vm.registration.skillSet[m].chipString = vm.registration.skillSet[m].skillName + "(" + expYr + " yrs " + expMnth + " months)";
                    }
                }
                vm.isCheckboxEnable = true;
                vm.isChecked = true;
                $rootScope.showLoader = false;
            }, function (err) {
                vm.registration = {};
            });
        }

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
        
        vm.keys = [$mdConstant.KEY_CODE.ENTER, $mdConstant.KEY_CODE.COMMA];
        // Any key code can be used to create a custom separator
        var semicolon = 186;
        vm.customKeys = [$mdConstant.KEY_CODE.ENTER, $mdConstant.KEY_CODE.COMMA, semicolon];
        vm.contacts = ['test@example.com'];


        vm.isCheckboxEnable = false;
        vm.urlForLookups = "api/consultant/getDropDownData";
        Core_Service.getAllLookupValues(vm.urlForLookups)
                .then(function (response) {
                    vm.lookups = Core_Service.processDateObjects(['dob', 'doj'], response);
                    if (!$stateParams.id)
                        vm.mainSkillList = vm.lookups.SKILL;
                }, function (error) {
                });


        $scope.steps = [
            'Personal',
            'Employment',
            'Official',
            'Family'
        ];
        $scope.selection = $scope.steps[0];
        $scope.getCurrentStepIndex = function () {
            return _.indexOf($scope.steps, $scope.selection);
        };

        // Go to a defined step index
        $scope.goToStep = function (index) {
            var flag = index > $scope.getCurrentStepIndex();
            if (!_.isUndefined($scope.steps[index]) && (!flag || vs.checkFormValidity($scope))) {
                $scope.selection = $scope.steps[index];
                $timeout(function () {
                    angular.element('input[type=file]').bootstrapFileInput()
                    vm.isFileInput = $scope.steps[index] == "Official" ? true : false;
                }, 500)

            }
        };

        vm.contacts = [];
        vm.filterSelected = true;


        vm.addSkill = function () {
            var chipString = "";
            if (vm.add.skill && vm.add.expYr && vm.add.expMnth) {
                chipString = vm.add.skill + "(" + vm.add.expYr + " yrs " + vm.add.expMnth + "months)";
            } else if (vm.add.skill && vm.add.expYr) {
                chipString = vm.add.skill + "(" + vm.add.expYr + " yrs " + "0 months)";
            }
            if (chipString) {
                vm.registration.skillSet.push({
                    skillName: vm.add.skill,
                    experienceYear: vm.add.expYr,
                    experienceMonth: vm.add.expMnth,
                    chipString: chipString
                });
                vm.add.skill = "";
                vm.add.expYr = "";
                vm.add.expMnth = "";
            }
        };

        vm.removeChip = function (chipInfo) {
        };

        $scope.hasNextStep = function () {
            var stepIndex = $scope.getCurrentStepIndex();
            var nextStep = stepIndex + 1;
            // Return true if there is a next step, false if not
            return !_.isUndefined($scope.steps[nextStep]);
        };

        $scope.hasPreviousStep = function () {
            var stepIndex = $scope.getCurrentStepIndex();
            var previousStep = stepIndex - 1;
            // Return true if there is a next step, false if not
            return !_.isUndefined($scope.steps[previousStep]);
        };

        $scope.incrementStep = function () {
            var stepIndex = $scope.getCurrentStepIndex();
            if ($scope.hasNextStep() && vs.checkFormValidity($scope))
            {
                var nextStep = stepIndex + 1;
                $scope.selection = $scope.steps[nextStep];
            }

        };

        $scope.decrementStep = function () {
            if ($scope.hasPreviousStep())
            {
                var stepIndex = $scope.getCurrentStepIndex();
                var previousStep = stepIndex - 1;
                $scope.selection = $scope.steps[previousStep];
            }

        };

        $rootScope.active = 'consultant';
        vm.copyAddress = function ($event) {
            if (vm.registration.permanentAddress) {
                var _this = $event.target;
                vm.registration.currentAddress = {};
                vm.isCheckboxEnable = true;
                for (var key in vm.registration.permanentAddress) {
                    vm.registration.currentAddress[key] = vm.registration.permanentAddress[key];
                }
//                angular.element('.permntCountry').triggerHandler('change');//temp
                vm.statesCurnt = vm.statesPerm;
                vm.citiesCurnt = vm.citiesPerm;
            } else {
                vm.isCheckboxEnable = false;
            }
            if (!vm.isChecked) {
                for (var key in vm.registration.currentAddress) {
                    vm.registration.currentAddress[key] = "";
                }
                vm.statesCurnt = [];
                vm.citiesCurnt = [];
            }
        };

        vm.checkAddress = function () {
            if (vm.registration.permanentAddress) {
                for (var key in vm.registration.permanentAddress) {
                    if (vm.registration.permanentAddress[key] != "") {
                        vm.isCheckboxEnable = true;
                        return;
                    }
                }
                vm.isCheckboxEnable = false;
            }
        };
        vm.addConsultant = function () {
            $state.go("coreuser.consultant.add");
        };

        vm.cancelRegisteration = function () {
            $state.go("coreuser.consultant")
        };
        vm.consultantRegister = function () {
            if (vs.checkFormValidity($scope["regForm"])) {
                vm.registerUrl = "api/consultant/saveOrUpdateConsultant";
                Core_Service.sweetAlertWithConfirm("Consultant details filled!", "Are you sure to register this Consultant?", "warning", function(){
                Core_Service.candidateRegisterImpl(vm.registerUrl, vm.registration)
                        .then(function (response) {                            
                            Core_Service.sweetAlertWithConfirm("Consultant Registered successfully...", "Do you want to upload any documents?", "warning", function(isConfirm){
                                 if (isConfirm) {
                                     $rootScope.isEmpDocs = false;
                                     $state.go("coreuser.upload");
                                 } else { 
                                     $timeout(function(){
                                         Core_Service.sweetAlert("Done!", "No Docs Uploaded", "success", "coreuser.consultant");
                                     },200);
                                    
                                 } 
                            });
                        }, function (error) { 
                            Core_Service.sweetAlert("Ouch!", "Something went wrong", "error", "coreuser.consultant");
                        });
                    });
            }
          
        };

        //To Do(move these methods to base controller)
        vm.getStatesByCountry = function (countryId, flag) {
            var data = {"id": countryId};
            vm.apiUrl = "api/getStatesByCountry";
            Core_Service.defaultApiByIdAndUrlImpl(vm.apiUrl, data)
                    .then(function (response) {
                        switch (flag) {
                            case "permenant":
                                vm.statesPerm = response.data;
                                break;
                            case "current":
                                vm.statesCurnt = response.data;
                                break;
                            case "onsite":
                                vm.statesOnsite = response.data;
                                break;
                            case "bank":
                                vm.statesBank = response.data;
                                break;
                            default:
                                break;
                        }
                    }, function (error) {
                        console.log(error)
                    });
        };
        vm.getCitiesByStates = function (stateId, flag) {
            var data = {"id": stateId};
            vm.apiUrl = "api/getCitiesByState";
            Core_Service.defaultApiByIdAndUrlImpl(vm.apiUrl, data)
                    .then(function (response) {
                        switch (flag) {
                            case "permenant":
                                vm.citiesPerm = response.data;
                                break;
                            case "current":
                                vm.citiesCurnt = response.data;
                                break;
                            case "onsite":
                                vm.citiesOnsite = response.data;
                                break;
                            case "bank":
                                vm.citiesBank = response.data;
                                break;
                            default:
                                break;
                        }
                    }, function (error) {
                        console.log(error)
                    });
        };

        vm.getCitiesByStatesBank = function (stateId) {
            var data = {"id": stateId};
            vm.apiUrl = "api/getCitiesByState";
            Core_Service.defaultApiByIdAndUrlImpl(vm.apiUrl, data)
                    .then(function (response) {
                        vm.citiesBank = response.data;
                    }, function (error) {
                    });
        };

        Core_Service.calculateSidebarHeight();
        $rootScope.isShowLoader = false;
    };

    AddConsultant_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'Core_Service', '$stateParams', '$timeout', 'validationService', '$mdConstant'];
    angular.module('coreModule')
            .controller('AddConsultant_Ctrl', AddConsultant_Ctrl);
})();



