(function() {
	var BankAcc_Ctrl = function($scope, $state, $rootScope, Core_Service, urlConfig, Core_ModalService, validationService) {
		var vm = this, clientAddTable;
		$rootScope.active = 'bankacc';
		vm.addBankAcc = function() {
			$state.go("coreuser.bankacc.add");
		};
		vm.getClient = function(data){
        	vm.getClientUrl = "api/client/getClient";
//            Core_Service.getCandidateImpl(vm.getClientUrl,id)
//            .then( function(response) {
               vm.viewClient(data);
//            },function(error){
//            	
//            });
        };        
        
        vm.viewClient = function (data) {
            Core_ModalService.openViewClientModal(data);
        };
		angular.element(document).ready(
		function() {
	                    clientAddTable = angular.element('#clientList').DataTable({
	                        ajax:urlConfig.http+ window.location.host+ urlConfig.api_root_path+ "client/getClients",
	                        serverSide: true,
	                        bDestroy: true,
	                        processing: true,
	                        responsive: true,
	                        sScrollX: '100%',                
	                        fnDrawCallback: function (settings, ajax) {
	                        	Core_Service.calculateSidebarHeight();
	                        },
                            language : {
                                    zeroRecords : 'No data to display',
                                    searchPlaceholder : 'Search',
                                    search : '',
                                    infoEmpty : 'No records available',
                                    infoFiltered : 'No data to display'
                            },
                            order : [ [ 0, "desc" ] ],
                            aoColumns : [
                                            {
                                                    data : 'id',
                                                    visible : false
                                            },
                                            {
                                                    title : "Client ID",
                                                    data : 'clientId',
                                                    render: function (data) {
                                    return data == null? "":data;
                            }
                                            },
                                            {
                                                    title : "Name",
                                                    data : 'clientName',
                                                    render: function (data) {
                                    return data == null? "":data;
                            }
                                            },
                                            {
                                                    title : "Email",
                                                    data : 'email',
                                                    render: function (data) {
                                    return data == null? "":data;
                            }
                                            },
                                            {
                                                    title : "contactNo",
                                                    data : 'contactNo.number',
                                                    render: function (data,display,row) {
                                    return row.contactNo == null? "":row.contactNo.number == null? "":data;
                            }
                                            },
                                            {
                                                    title : "Revenue",
                                                    data : 'revenue',
                                                    render: function (data) {
                                    return data == null? "":data;
                            }
                                            },
                                            {
                                                    title : "POC Name",
                                                    data : 'poc.pocName',
                                                    render: function (data,display,row) {
                                    return row.poc == null? "":row.poc.pocName == null? "":data;
                            }
                                            },
                                            {
                                                    title : "Bussiness Unit",
                                                    data : 'businessUnit.description',
                                                    render: function (data,display,row) {
                                    return row.businessUnit == null? "":row.businessUnit.description == null? "":data;
                            }
                                            },
                                            {
                                                    data : 'id',
                                                    bSortable : false,
                                                    sClass : "button-column",
                                                    render : function(data) {
                                                            $rootScope.showLoader = false;
                                                            return data != null ?
                                                             '<div class="action-buttons">'
                                                                            + '<span  value="'
                                                                            + data
                                                                            + '" class="actions action-view fa-stack fa-lg pull-left" title="View">'
                                                                            + '<i class="fa fa-eye" aria-hidden="true"></i></span>'
                                                                            + '<span value="'
                                                                            + data
                                                                            + '" class="actions action-edit fa-stack fa-lg pull-left" title="Edit">'
                                                                            + '<i class="fa fa-pencil-square-o" aria-hidden="true"></i></i></span></div>' : ""
                                                    }
                                            }],
					});
			
            $('#clientList').on('click', '.action-edit', function () {
                $rootScope.showLoader = true;
                $rootScope.id = this.getAttribute('value');
                $state.go('coreuser.client.edit', {id: $rootScope.id});
            });
            $('#clientList').on('click', '.action-view', function () {
                var data = clientAddTable.data()[$(this).parents("tr").index()];
                vm.getClient(data);
            });

		})
	};
	BankAcc_Ctrl.$inject = [ "$scope", '$state', '$rootScope', 'Core_Service', 'urlConfig', 'Core_ModalService', 'validationService' ];
	angular.module('coreModule').controller('BankAcc_Ctrl', BankAcc_Ctrl);
})();