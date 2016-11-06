var consultantListTable;
(function () {
    var Consultant_Ctrl = function ($scope, $state, $rootScope, Core_Service, urlConfig, Core_ModalService, validationService) {
        $rootScope.showLoader = true;        
        var vm = this,
                vs = new validationService({
                    controllerAs: vm
                });

        vm.registration = {};
        vm.errorAlert = false;
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

        $rootScope.active = 'consultant';
        vm.toggler = function () {
            $('#pro_expander').toggle();
        };

        
        vm.copyAddress = function () {
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
        vm.addConsultant = function () {
            $state.go("coreuser.consultant.add");
        };
        vm.processOfferLetter = function () {
            var index = $("#consultantList tbody tr.selected").index();
            var data = consultantListTable.data()[index];
            if(data && data.id){
              vm.errorAlert = false;              
              $state.go("coreuser.offerletter",{id:data.id});  
            }
            else{
              vm.errorAlert = true;
              vm.errorMessage = "Please select one consultant from table";
            }            
        };
        
        vm.getSelectedConsultant = function (event) {
        };

        vm.viewConsultant = function (data) {
            Core_ModalService.openViewConsultantModal(data);
        };

        vm.deleteConsultant = function (data) {
            Core_ModalService.opendeleteConsultantModal(data);
        };
        angular.element(document).ready(function () {
                consultantListTable = angular.element('#consultantList').DataTable({
                ajax: urlConfig.http + window.location.host + urlConfig.api_root_path + "consultant/getConsultants",
                serverSide: true,
                bDestroy: true,
                processing: true,
                responsive: true,
                sScrollX: '100%',                
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
                aoColumns: [ {
                    	data: 'id',
                    	visible : false
                	},{
                        title: "Consultant ID",
                        data: 'consultantId',
                        render: function (data) {
                        	return data == null? "":data;
                        }
                    }, {
                        title: "Name",
                        data: 'fullName',
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
                        title: "Role",
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
            $('#consultantList').on('click', '.action-view', function () {
                vm.getConsultant(this.getAttribute('value'));
            });
            $('#consultantList').on('click', '.action-edit', function () {
                $rootScope.showLoader = true;
                $rootScope.id = this.getAttribute('value');
                $state.go('coreuser.consultant.edit', {id: $rootScope.id});
            });
            $('#consultantList tbody').on( 'click', 'tr', function () {
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                }
                else {
                    consultantListTable.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
            } );
            
        });
        
        vm.getConsultant = function(id){
        	vm.getConsultantUrl = "api/consultant/getConsultant";
            Core_Service.getConsultantImpl(vm.getConsultantUrl,id)
            .then( function(response) {
               vm.viewConsultant(response.data);
            },function(error){
            	
            });
        }; 
        
        vm.consultantDelete = function(id){
        	vm.deleteUrl = "api/consultant/deleteConsultant";
            Core_Service.consultantDeleteImpl(vm.deleteUrl,id)
            .then( function(response) {
               Core_Service.sweetAlert("Done!",response.data.data,"success","coreuser.consultant");  
               angular.element('#consultantList').DataTable().draw();
            },function(error){
            	Core_Service.sweetAlert("Failed!",response.data.data,"failure","coreuser.consultant");  
            });
        };
        
        
    };

    Consultant_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'Core_Service', 'urlConfig', 'Core_ModalService', 'validationService'];
    angular.module('coreModule')
            .controller('Consultant_Ctrl', Consultant_Ctrl);
})();
