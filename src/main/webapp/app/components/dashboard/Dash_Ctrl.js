(function () {
    var Dash_Ctrl = function ($scope, $rootScope, Core_Service, Core_ModalService, urlConfig) {
        var vm = this, dashBoardTable;
        $rootScope.isDashBoard = true;
        vm.taskStatus = [];
        var panelStyle = ["success","primary","danger","info","warning"];
        var fontIcon = ["fa-check-circle","fa-exclamation-circle","fa-times-circle","fa-check-circle","fa-exclamation-circle"];
        Core_Service.getDashCount().then(function (response) {
            var count = 0,colors;
            for(var key in response){
                var obj = {
                    name:key,
                    count:response[key] ? response[key] : 0,
                    panelClass:panelStyle[count],
                    fontIcon:fontIcon[count]
                };
                count++;
                vm.taskStatus.push(obj);
            }
        }, function (error) {
            console.log(error)
        });
        

        $rootScope.active = 'dashboard';
        angular.element(document).ready(function () {
            
            dashBoardTable = angular.element('#tasksList').DataTable({
//                data: taskList,
                ajax: urlConfig.http + window.location.host + urlConfig.api_root_path + "dashboard/getDashboardTasks",
                serverSide: true,
                bDestroy: true,
                processing: true,
                responsive: true,
                bFilter:false,
                fnDrawCallback: function (settings, ajax) {

                },
                language: {
                    zeroRecords: 'No data to display',
                    infoEmpty: '',
                    infoFiltered: ''
                },
                order: [[0, "desc"]],
                aoColumns: [{
                        title: "#",
                        data: 'id',
                        sClass: "task-label-container",
                        render: function (data, type, row) {
                            var className;
                                className = row.status.toLowerCase() == 'completed' ? 'label-success' : 'label-warning'
                            $rootScope.showLoader = false;
                            return '<div class="circle '+ className +'"><p>T'+data+'</p></div>'
                        }
                    }, {
                        title: "Task",
                        data: 'masterTask.taskDesc',
                        render: function (data, type, row) {
                            return '<div class="data-task-container"><h4 class="col-md-12 col-lg-12">'
                            +data+'</h4><p class="col-md-12 col-lg-12">'+data+'</p></div>'
                            //return data == null ? "" : data;
                        }
                    }, {
                    	title: "Status",
                        data: 'status',
                        bSortable: true,
                        sClass: "button-column",
                        render: function (data) {
                            var className;
                                className = data.toLowerCase() == 'completed' ? 'btn-success' : 'btn-warning'
                            $rootScope.showLoader = false;
                            return '<button type="button" class="btn ' +className+ '">' +data+ '</button>'
                        }
                    }]
            });
            vm.selectFilter = function(filter){
            var filters;
            switch(filter){
                case 'completed':{
                    filters = dashBoardTable.columns(3).search('PENDING');
                }
                break;
                case 'pending':{
                        
                }
                break;
                case 'critical':{
                        
                }
                break;
                case 'nonCritical':{
                        
                }
                break;
                case 'mediumCritical':{
                        
                }
                break;
            default:             
                
            }
            if (filters)
                filters.draw();
        };
            $('#tasksList').on('click', '.action-view', function () {
                var data = dashBoardTable.data()[$(this).parents("tr").index()];
                vm.showAdvanced(data);
            });
            $('#tasksList tbody').on('click', 'tr', function () {
                if ($(this).hasClass('selected')) {
                    $(this).removeClass('selected');
                } else {
                    dashBoardTable.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
            });

        });
        $scope.close = function () {
            $mdDialog.hide();
        }
        vm.showAdvanced = function (data) {
            Core_ModalService.openTaskDetails(data);
        };

    };

    Dash_Ctrl.$inject = ["$scope", '$rootScope', 'Core_Service', 'Core_ModalService', 'urlConfig'];
    angular.module('coreModule').controller('Dash_Ctrl', Dash_Ctrl);
})();
