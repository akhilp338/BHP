(function () {
    var AddCandidate_Ctrl = function ($scope, $state, $rootScope, Core_Service, $stateParams, Core_HttpRequest, validationService) {
        var vm = this;
        $rootScope.showLoader = true;
        var countryType = ["permenant", "current", "onsite", "bank"];
        vm.setDpOpenStatus = function (id) {
            vm[id] = true
        };

        vm.registration = {};
        vm.mainSelectedSkillList = [];
        vm.subSelectedSkillList = [];
        vm.deSelectedSkills = [];
        vm.registration.skillSet = [];
        
        if ($stateParams.id) {
            Core_Service.getCandidateImpl("api/candidate/getCandidate", $stateParams.id).then(function (res) {
                vm.registration = res.data;
                for (var i = 0; i < countryType.length; i++) {
                    vm.getStatesByCountry(vm.registration.permanentAddress.city.state.country.id, countryType[i]);
                    vm.getCitiesByStates(vm.registration.permanentAddress.city.state.id, countryType[i]);
                }
                vm.mainSkillList = res.data.unselectedSkillSet;
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
        vm.isCheckboxEnable = false;
        vm.urlForLookups = "api/candidate/getDropDownData";
        Core_Service.getAllLookupValues(vm.urlForLookups)
                .then(function (response) {
                    vm.lookups = Core_Service.processDateObjects(['dob', 'doj'], response.data);
            if (!$stateParams.id)
                    vm.mainSkillList = vm.lookups.SKILL;
                }, function (error) {
                });
        
        vs.setGlobalOptions({
            debounce: 1500,
            scope: $scope,
            isolatedScope: $scope,
            preValidateFormElements: false,
            displayOnlyLastErrorMsg: true
        });

        vm.addSkills = function () {
            vm.mainSelectedSkillList = vm.mainSelectedSkillList.concat(vm.subSelectedSkillList);
            vm.removeFromMainListArray(vm.getIndexesToRemove(vm.mainSkillList, vm.subSelectedSkillList));
        };
        vm.removeSkills = function () {
            vm.mainSkillList = vm.mainSkillList.concat(vm.deSelectedSkills);
            vm.removeFromSelectedListArray(vm.getIndexesToRemove(vm.mainSelectedSkillList, vm.deSelectedSkills));
        };


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
            }
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

        $rootScope.active = 'candidate';
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
        vm.addCandidate = function () {
            $state.go("coreuser.candidate.add");
        };

        vm.cancelRegisteration = function (){
            $state.go("coreuser.candidate")
        };
        vm.candidateRegister = function () {
            if (vs.checkFormValidity($scope["regForm"])) {
                vm.registerUrl = "api/candidate/saveOrUpdateCandidate";
                var skillSet = vm.getUpdatedSkillSet(vm.registration.skillSet);
                vm.registration.skillSet = skillSet;
                Core_Service.candidateRegisterImpl(vm.registerUrl, vm.registration)
                        .then(function (response) {
                        	Core_Service.sweetAlert("Done!",response.Message,"success","coreuser.candidate");  
                            console.log(response)
                        }, function (error) {
                            console.log(error)
                        });
            }
        };
        
        vm.getUpdatedSkillSet = function(param){
        	var array=[];
        	for(var key in param){
        		var skillSet = {};
        		skillSet.id =param[key];
        		skillSet.skillName =param[key]; 
        		array.push(skillSet);
        	}
        	return array;
        }
        
        vm.getIndexesToRemove = function (array, data) {
            var indexes = [];
            for (var i = 0; i < data.length; i++) {
                for (var j = 0; j < array.length; j++) {
                    if (data[i].id == array[j].id) {
                        indexes.push(j);
                    }
                }
            }
            return  indexes;
        };

        vm.removeFromSelectedListArray = function (indexes) {
            var selected = [];
            for (var i = indexes.length - 1; i >= 0; i--)
                vm.mainSelectedSkillList.splice(indexes[i], 1);
            vm.deSelectedSkills = [];
            for (var j = 0; j < vm.mainSelectedSkillList.length; j++) {
                selected.push(vm.mainSelectedSkillList[j].id)
            }
            vm.registration.skillSet = selected;
        };
        vm.removeFromMainListArray = function (indexes) {
            var selected = [];
            for (var i = indexes.length - 1; i >= 0; i--)
                vm.mainSkillList.splice(indexes[i], 1);
            vm.subSelectedSkillList = [];
            for (var j = 0; j < vm.mainSelectedSkillList.length; j++) {
                selected.push(vm.mainSelectedSkillList[j].id)
            }
            vm.registration.skillSet = selected;
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

    };


    AddCandidate_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'Core_Service', '$stateParams', 'Core_HttpRequest', 'validationService'];
    angular.module('coreModule')
            .controller('AddCandidate_Ctrl', AddCandidate_Ctrl);
})();




