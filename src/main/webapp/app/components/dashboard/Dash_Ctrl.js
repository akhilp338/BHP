(function() {
	var Dash_Ctrl = function($scope, $rootScope, Core_Service, Core_ModalService, urlConfig) {
		var vm = this, dashBoardTable;
		$rootScope.active = 'dashboard';
		vm.init = function() {
                    vm.url = "api/candidate/getCurrentUserTasks";
                    Core_Service.getUserTasks(vm.url).then(function(response) {
                            console.log(response.data)

                    }, function(error) {
                            console.log(error)
                    });
		}
		angular.element(document).ready(function () {
            dashBoardTable = angular.element('#tasksList').DataTable({
            ajax: urlConfig.http + window.location.host + urlConfig.api_root_path + "getUserTasks",
            serverSide: true,
            bDestroy: true,
            processing: true,
            responsive: true,
            sScrollX: '100%',      
            iDisplayLength: 5,
            fnDrawCallback: function (settings, ajax) {
                
            },
            language: {
            	zeroRecords: 'No data to display',
                searchPlaceholder: 'Search Your Tasks',
                search: '',
                infoEmpty: '',
                infoFiltered:''
            },
            order: [[ 0, "desc" ]],
            aoColumns: [ {
                	data: 'id',
                	visible : false
            	},{
                    title: "Task ID",
                    data: 'id'
                }, {
                    title: "Task Name",
                    data: 'task.taskKey'
                }, {
                    title: "Task Description",
                    data: 'task.taskDesc',
                    render: function (data) {
                    	return data == null? "":data;
                    }
                },{
                    data: 'task',
                    bSortable: false,
                    sClass: "button-column",
                    render: function (data) {
                        $rootScope.showLoader = false;
                        return '<div class="action-buttons">' +
                                '<span  value="' + data + '" class="actions action-view fa-stack fa-lg pull-left" title="View">'+
                                '<i class="fa  fa-arrow-circle-o-right" aria-hidden="true"></i></span>'
                               }
                    }]
        });
            $('#tasksList').on('click', '.action-view', function () {
                var data = dashBoardTable.data()[$(this).parents("tr").index()];
                vm.showAdvanced(data);
            });
        $('#tasksList tbody').on( 'click', 'tr', function () {
            if ( $(this).hasClass('selected') ) {
                $(this).removeClass('selected');
            }
            else {
                dashBoardTable.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        } );
        
    });
		$scope.close= function(){
			$mdDialog.hide();
		}
		vm.showAdvanced = function(data) {
		   Core_ModalService.openTaskDetails(data);		   
		  };
    
	};

	Dash_Ctrl.$inject = [ "$scope", '$rootScope', 'Core_Service', 'Core_ModalService', 'urlConfig' ];
	angular.module('coreModule').controller('Dash_Ctrl', Dash_Ctrl);
})();
