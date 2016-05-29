(function() {
	var Client_Ctrl = function($scope, $state, $rootScope, Core_Service, urlConfig, Core_ModalService, validationService) {
		var vm = this;
		$rootScope.active = 'client';
		vm.addClient = function() {
			$state.go("coreuser.client.add");
		}
		angular.element(document).ready(
		function() {
	                    var oTable = angular.element('#clientList').DataTable({
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
								},
								{
									title : "Name",
									data : 'clientName',
								},
								{
									title : "Revenue",
									data : 'revenue',
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

		})
	};
	Client_Ctrl.$inject = [ "$scope", '$state', '$rootScope', 'Core_Service', 'urlConfig', 'Core_ModalService', 'validationService' ];
	angular.module('coreModule').controller('Client_Ctrl', Client_Ctrl);
})();