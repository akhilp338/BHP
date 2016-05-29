(function () {
    var Employee_Ctrl = function ($scope, $state, $rootScope, urlConfig, Core_Service) {
        var vm = this;
        $rootScope.active = 'employee';
        vm.addEmployee = function(){
        	$state.go("coreuser.employee.add");
        }
        angular.element(document).ready(function () {
            var oTable = angular.element('#employeeList').DataTable({
                ajax: urlConfig.http + window.location.host + urlConfig.api_root_path + "employee/getEmployee",
                serverSide: true,
                bDestroy: true,
                processing: true,
                responsive: true,
                sScrollX: '100%',                
                fnDrawCallback: function (settings, ajax) {
                    Core_Service.calculateSidebarHeight();
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
                        title: "Employee ID",
                        data: 'employeeId',
                    }, {
                        title: "Name",
                        data: 'employeeMaster.firstName',
                    },  {
                        title: "Employment Status",
                        data: 'employeeMaster.employmentStatus.description',
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
            $('#employeeList').on('click', '.action-edit', function () {
                $rootScope.showLoader = true;
                $rootScope.id = this.getAttribute('value');
                $state.go('coreuser.employee.edit', {id: $rootScope.id});
            });
            $('#employeeList tbody').on( 'click', 'tr', function () {
            	
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                }
                else {
                	oTable.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
            } );
         
        })
        Core_Service.calculateSidebarHeight();
         };

    Employee_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'urlConfig', 'Core_Service'];
    angular.module('coreModule')
            .controller('Employee_Ctrl', Employee_Ctrl);
})();

