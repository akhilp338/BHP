(function () {
    var Candidate_Ctrl = function ($scope, $state, $rootScope, Core_Service, urlConfig, Core_ModalService, validationService) {
        $rootScope.showLoader = true;
        var vm = this,
                vs = new validationService({
                    controllerAs: vm
                });

        vm.registration = {};
        vs.setGlobalOptions({
            debounce: 1500,
            scope: $scope,
            isolatedScope: $scope,
            preValidateFormElements: false,
            displayOnlyLastErrorMsg: true
        });
        
        vm.isCheckboxEnable = false;
        $scope.steps = [
            'Step 1: Personal Information',
            'Step 2: Employment Details',
            'Step 3: Official Information',
            'Step 4: Family Details'
        ];
        $scope.selection = $scope.steps[0];

        $scope.getCurrentStepIndex = function () {
            // Get the index of the current step given selection
            return _.indexOf($scope.steps, $scope.selection);
        };

        // Go to a defined step index
        $scope.goToStep = function (index) {
            if (!_.isUndefined($scope.steps[index]))
            {
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
            vs.checkFormValidity($scope)
            if ($scope.hasNextStep())
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
        vm.toggler = function () {
            $('#pro_expander').toggle();
        };

        
        vm.copyAddress = function () {
            console.log(vm.registration.permenant)
            if (vm.registration.permenant) {
                vm.registration.current = {};
                vm.isCheckboxEnable = true;
                for (var key in vm.registration.permenant) {
                    vm.registration.current[key] = vm.registration.permenant[key];
                }
            } else {
                vm.isCheckboxEnable = false;
            }
        };

        vm.checkAddress = function () {
            if (vm.registration.permenant) {
                for (var key in vm.registration.permenant) {
                    if (vm.registration.permenant[key] != "") {
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

        vm.getSelectedCandidate = function (event) {
            console.log(event);
        };

        vm.viewCandidate = function (data) {
            Core_ModalService.openViewCandidateModal(data);
        };

        vm.deleteCandidate = function (data) {
            Core_ModalService.opendeleteCandidateModal(data);
        };
        
        angular.element(document).ready(function () {
            var oTable = angular.element('#candidatesList').DataTable({
                ajax: urlConfig.http + window.location.host + urlConfig.api_root_path + "candidate/getCandidates?employee=false",
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
                                    '<span value="' + data + '" class="actions action-edit fa-stack fa-lg pull-left" title="Edit">'+
                                    '<i class="fa fa-pencil-square-o" aria-hidden="true"></i></i></span></div>'
                        }
                    }]
            });
            $('#candidatesList').on('click', '.action-view', function () {
                vm.getCandidate(this.getAttribute('value'));
            });
            $('#candidatesList').on('click', '.action-edit', function () {
                $rootScope.showLoader = true;
                $rootScope.id = this.getAttribute('value');
                $state.go('coreuser.candidate.edit', {id: $rootScope.id});
            });
            $('#candidatesList tbody').on( 'click', 'tr', function () {
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                }
                else {
                	oTable.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
            } );
            
        });
        
        vm.getCandidate = function(id){
        	vm.getCandidateUrl = "api/candidate/getCandidate";
            Core_Service.getCandidateImpl(vm.getCandidateUrl,id)
            .then( function(response) {
               vm.viewCandidate(response.data);
            },function(error){
            	
            });
        };
        
        vm.candidateDelete = function(id){
        	vm.deleteUrl = "api/candidate/deleteCandidate";
            Core_Service.candidateDeleteImpl(vm.deleteUrl,id)
            .then( function(response) {
               Core_Service.sweetAlert("Done!",response.data.data,"success","coreuser.candidate");  
               angular.element('#candidatesList').DataTable().draw();
            },function(error){
            	Core_Service.sweetAlert("Failed!",response.data.data,"failure","coreuser.candidate");  
            });
        };
        
        
    };

    Candidate_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'Core_Service', 'urlConfig', 'Core_ModalService', 'validationService'];
    angular.module('coreModule')
            .controller('Candidate_Ctrl', Candidate_Ctrl);
})();
