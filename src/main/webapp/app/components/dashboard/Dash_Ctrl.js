(function() {
	var Dash_Ctrl = function($scope, $state, $rootScope, Core_Service,
			$mdDialog, $mdMedia,urlConfig) {
		var vm = this;
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
            candidatesListTable = angular.element('#tasksList').DataTable({
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
            	zeroRecords: 'No data to dispay',
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
                vm.showAdvanced(this.getAttribute('value'));
            });
        $('#tasksList tbody').on( 'click', 'tr', function () {
            if ( $(this).hasClass('selected') ) {
                $(this).removeClass('selected');
            }
            else {
                candidatesListTable.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        } );
        
    });
		vm.showAdvanced = function(res) {
		    var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))  && $scope.customFullscreen;
		    $mdDialog.show({
		      controller: Dash_Ctrl,
		      templateUrl: 'app/components/dashboard/taskDialogue.html',
		      parent: angular.element(document.body),
		      clickOutsideToClose:true,
		      fullscreen: useFullScreen
		    })
		   
		  };
    
	};

	Dash_Ctrl.$inject = [ "$scope", '$state', '$rootScope', 'Core_Service',
			'$mdDialog', '$mdMedia','urlConfig' ];
	angular.module('coreModule').controller('Dash_Ctrl', Dash_Ctrl);
})();
