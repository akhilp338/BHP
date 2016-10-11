(function () {
    var Dash_Ctrl = function ($scope, $rootScope, Core_Service, Core_ModalService, urlConfig) {
        var vm = this, dashBoardTable;
        $rootScope.isDashBoard = true; 
        var taskList = [{
                "id": "T12",
                "task": {
                    "taskDesc": "This is the first task",
                    "status": "pending",
                    "taskName": "First"
                }
            },
            {
                "id": "T156",
                "task": {
                    "taskDesc": "This is the second task",
                    "status": "pending",
                    "taskName": "Second"
                }
            },
            {
                "id": "T122",
                "task": {
                    "taskDesc": "This is the third task",
                    "status": "Completed",
                    "taskName": "Third"
                }
            },
            {
                "id": "T152",
                "task": {
                    "taskDesc": "This is the fourth task",
                    "status": "pending",
                    "taskName": "Fourth"
                }
            }];

        $rootScope.active = 'dashboard';
        vm.init = function () {
            vm.url = "api/candidate/getCurrentUserTasks";
            Core_Service.getUserTasks(vm.url).then(function (response) {
                console.log(response.data)

            }, function (error) {
                console.log(error)
            });
        }
        angular.element(document).ready(function () {
            dashBoardTable = angular.element('#tasksList').DataTable({
                data: taskList,
                serverSide: false,
                bDestroy: true,
                processing: true,
                responsive: true,
                fnDrawCallback: function (settings, ajax) {

                },
                language: {
                    zeroRecords: 'No data to display',
                    searchPlaceholder: 'Search Your Tasks',
                    search: '',
                    infoEmpty: '',
                    infoFiltered: ''
                },
                order: [[0, "desc"]],
                aoColumns: [{
                        data: 'id',
                        visible: false
                    }, {
                        title: "#",
                        data: 'id',
                        sClass: "task-label-container",
                        render: function (data, type, row) {
                            var className;
                                className = row.task.status.toLowerCase() == 'completed' ? 'label-success' : 'label-warning'
                            $rootScope.showLoader = false;
                            return '<div class="circle '+ className +'"><p>'+data+'</p></div>'
                        }
                    }, {
                        title: "Task",
                        data: 'task.taskDesc',
                        render: function (data, type, row) {
                            return '<div class="data-task-container"><h4 class="col-md-12 col-lg-12">'+row.task.taskName+'</h4><p class="col-md-12 col-lg-12">'+data+'</p></div>'
                            //return data == null ? "" : data;
                        }
                    }, {
                        data: 'task.status',
                        title: "Status",
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
