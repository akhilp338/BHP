var dashBoardTable;
(function () {
    var Dash_Ctrl = function ($scope, $state, $rootScope, Core_Service, Core_ModalService, urlConfig) {
        var vm = this;
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
                    console.log(settings)
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
                                className = row.status == 'completed' ? 'label-success' : 'label-warning'
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
                        sClass: "button-column action-view",
                        render: function (data) {
                            var className;
                                className = data == 'completed' ? 'btn-success' : 'btn-warning'
                            return '<button type="button" class="btn ' +className+ '">' +data+ '</button>'
                        }
                    }]
            });
            vm.selectFilter = function(filter,event){
            var filters;
            angular.element('.dashboard .panel').removeClass('selected');
            angular.element(event.currentTarget).addClass('selected');
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
                var id = {id:data.taskEntityId}
                Core_Service.getTaskReviewDetails(id).then(function(res){
                    console.log(res)
                },function(err){
                     console.log(err)
                })
                $state.go(data.masterTask.taskRoute)
            });            

        });
        $scope.close = function () {
            $mdDialog.hide();
        }
        vm.showAdvanced = function (data) {
            Core_ModalService.openTaskDetails(data);
        };
    };

    Dash_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'Core_Service', 'Core_ModalService', 'urlConfig'];
    angular.module('coreModule').controller('Dash_Ctrl', Dash_Ctrl);
})();
