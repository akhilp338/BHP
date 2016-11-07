(function() {
	var Vendor_Ctrl = function($scope, $state, $rootScope, Core_Service, urlConfig, Core_ModalService, validationService) {
		var vm = this, vendorAddTable;
		$rootScope.active = 'vendor';
		vm.addVendor = function() {
                    $state.go("coreuser.vendor.add");
		};
		vm.getVendor = function(data){
                    vm.getVendorUrl = "api/vendor/getVendor";
                    Core_Service.getCandidateImpl(vm.getVendorUrl,data.id)
                    .then( function(response) {
                       vm.viewVendor(data);
                    },function(error){

                    });
                };        
        
        vm.viewVendor = function (data) {
            Core_ModalService.openViewClientModal(data);
        };
		angular.element(document).ready(
		function() {
	                    vendorAddTable = angular.element('#clientList').DataTable({
	                        ajax:urlConfig.http+ window.location.host+ urlConfig.api_root_path+ "vendor/getApprovedVendors",
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
                                                    title : "Vendor Code",
                                                    data : 'vendorCode',
                                                    render: function (data) {
                                    return data == null? "":data;
                            }
                                            },
                                            {
                                                    title : "Name",
                                                    data : 'vendorName',
                                                    render: function (data) {
                                    return data == null? "":data;
                            }
                                            },
                                            {
                                                    title : "Category",
                                                    data : 'category',
                                                    render: function (data) {
                                    return data == null? "-":data.description;
                            }
                                            },
                                            {
                                                    title : "Description",
                                                    data : 'description',
                                                    render: function (data,display,row) {
                                    return data== null? "":data;
                            }
                                            },
                                            {
                                                    title : "Contact Number",
                                                    data : 'phoneNo.number',
                                                    render: function (data) {
                                    return data == null? "":data;
                            }
                                            },
                                            {
                                                    data : 'id',
                                                    bSortable : false,
                                                    sClass : "button-column",
                                                    render : function(data) {
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
                $rootScope.isShowLoader = true;
                $rootScope.id = this.getAttribute('value');
                $state.go('coreuser.vendor.edit', {id: $rootScope.id});
            });
            $('#clientList').on('click', '.action-view', function () {
                var data = vendorAddTable.data()[$(this).parents("tr").index()];
                vm.getVendor(data);
            });

		});
	};
	Vendor_Ctrl.$inject = [ "$scope", '$state', '$rootScope', 'Core_Service', 'urlConfig', 'Core_ModalService', 'validationService' ];
	angular.module('coreModule').controller('Vendor_Ctrl', Vendor_Ctrl);
})();