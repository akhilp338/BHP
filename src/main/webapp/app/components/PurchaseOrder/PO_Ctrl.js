(function() {
	var PO_Ctrl = function($scope, $state, $rootScope, Core_Service, urlConfig, Core_ModalService, validationService) {
		var vm = this, poAddTable;
		$rootScope.active = 'po';
		vm.addPO = function() {
            $state.go("coreuser.po.add");
		};
        vm.back = function() {
            $state.go("coreuser.po");
		};
		//vm.getClient = function(data){
        	//vm.getClientUrl = "api/client/getClient";
//            Core_Servic/etCandidateImpl(vm.getClientUrl,id)
//            .then( function(response) {
               //vm.viewClient(data);
//            },function(error){
//            	
//            });
        //};        
        
        vm.viewPO = function (data) {
            
        };
		angular.element(document).ready(
		function() {
	                    poAddTable = angular.element('#poList').DataTable({
	                        ajax:urlConfig.http+ window.location.host+ urlConfig.api_root_path+ "purchaseOrder/getAllPurchaseOrders",
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
                                                    title : "PO No",
                                                    data : 'purchaseOrderNo',
                                                    render: function (data) {
                                    return data == null? "":data;
                            }
                                            },
                                            {
                                                    title : "PO Date",
                                                    data : 'poDate',
                                                    render: function (data) {
                                    return data == null? "":data;
                            }
                                            },
                                            {
                                                    title : "Monthly Rate",
                                                    data : 'monthlyRate',
                                                    render: function (data) {
                                    return data == null? "":data;
                            }
                                            },
                                            {
                                                    title : "PO Value",
                                                    data : 'poValue',
                                                    render: function (data,display,row) {
                                                    	return data;
                                                    }
                                            },
                                            {
                                                    title : "Currency",
                                                    data : 'currency',
                                                    render: function (data) {
                                                    	return data == null? "":data;
                                                	}
                                            },
                                            {
                                                    title : "Billed Amount",
                                                    data : 'billedAmount',
                                                    render: function (data,display,row) {
                                                    	return data;
                                                    }
                                            },
                                            {
                                                    title : "Balance Amount",
                                                    data : 'balanceAmount',
                                                    render: function (data,display,row) {
                                                    	return data;
                                                    }
                                            },
                                            {
                                                    title : "Expiry Date",
                                                    data : 'expiry',
                                                    render: function (data,display,row) {
                                                		return data;
                                                	}
                                            },
                                            {
                                                title : "Status",
                                                data : 'status',
                                                render: function (data,display,row) {
                                                	return data;
                                                }
                                        },
                                        {
                                            title : "Vendor No",
                                            data : 'vendorName.vendorCode',
                                            render: function (data,display,row) {
                                            	return data;
                                            }
                                    },
                                    {
                                        title : "Name",
                                        data : 'vendorName.vendorName',
                                        render: function (data,display,row) {
                                        	return null;
                                    	}
                                },
                                {
                                    title : "Description",
                                    data : 'poDesc',
                                    render: function (data,display,row) {
                                    	return null;
                                	}
                            },
                            {
                                title : "Doc Uploaded",
                                data : 'poDesc',
                                render: function (data,display,row) {
                                	return null;
                            	}
                        },
                                            {
                                                    data : 'id',
                                                    bSortable : false,
                                                    sClass : "button-column",
                                                    render : function(data) {
                                                            $rootScope.isShowLoader = false;
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
                $state.go('coreuser.client.edit', {id: $rootScope.id});
            });
            $('#clientList').on('click', '.action-view', function () {
                var data = clientAddTable.data()[$(this).parents("tr").index()];
                vm.getClient(data);
            });

		})
	};
	PO_Ctrl.$inject = [ "$scope", '$state', '$rootScope', 'Core_Service', 'urlConfig', 'Core_ModalService', 'validationService' ];
	angular.module('coreModule').controller('PO_Ctrl', PO_Ctrl);
})();