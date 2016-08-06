(function () {
    'use strict';
    var Core_Routes = function ($stateProvider, $locationProvider, $urlRouterProvider, urlConfig) {
        $locationProvider.html5Mode(true);
        $urlRouterProvider.otherwise(function () {
            window.location = urlConfig.root_path;
        });

        $stateProvider
                .state('coreuser', {
                    url: '',
                    abstract: true,
                    views: {
                        'header': {
                            templateUrl: '/BelhopatBackOffice/app/components/common/innerHeader.html',
                            controller: 'Header_Ctrl',
                            controllerAs: 'vm'
                        },
                        'sidebar': {
                            templateUrl: '/BelhopatBackOffice/app/components/common/sidebar.html',
                            controller: 'Home_Ctrl',
                            controllerAs: 'vm'
                        }
                    }
                })
                .state('login', {
                    url: urlConfig.root_path,
                    views: {
                        'content@': {
                            templateUrl: '/BelhopatBackOffice/app/components/login/login-sample.html',
                            controller: 'Login_Ctrl',
                            controllerAs: 'vm'
                        }
                    }
                })
                .state('changePassword', {
                    url: urlConfig.root_path + 'changePassword/:token',
                    views: {
                        'content@': {
                            templateUrl: '/BelhopatBackOffice/app/components/login/changePassword.html',
                            controller: 'ChangePassword_Ctrl',
                            controllerAs: 'vm'
                        }
                    }
                })
                .state('coreuser.upload', {
                    url: urlConfig.root_path + 'upload',
                    views: {
                        'sidebar@': {
                            templateUrl: '/BelhopatBackOffice/app/components/common/defaultTemplate.html'
                        },
                        'content@': {
                            templateUrl: '/BelhopatBackOffice/app/components/fileUpload/fileUpload.html',
                            controller: 'FileUpload_Ctrl',
                            controllerAs: 'vm'
                        }
                    }
                })
                .state('coreuser.dashboard', {
                    url: urlConfig.root_path + 'dashboard',
                    views: {
                        'content@': {
                            templateUrl: '/BelhopatBackOffice/app/components/dashboard/dashboard.html',
                            controller: 'Dash_Ctrl',
                            controllerAs: 'vm'
                        }
                    }
                }).state('coreuser.success', {
            url: urlConfig.root_path + 'loginSuccess',
            views: {
                'content@': {
                    templateUrl: '/BelhopatBackOffice/app/components/dashboard/dashboard.html',
                    controller: 'Dash_Ctrl',
                    controllerAs: 'vm'
                }
            }
        }).state('login.error', {
            url: urlConfig.root_path + 'loginerror',
            views: {
                'content@': {
                    templateUrl: '/BelhopatBackOffice/app/components/login/login.html',
                    controller: 'Login_Ctrl',
                    controllerAs: 'vm'
                }
            }
        })
                //Candidate States Routing
                .state('coreuser.candidate', {
                    url: urlConfig.root_path + 'candidate',
                    views: {
                        'content@': {
                            templateUrl: '/BelhopatBackOffice/app/components/candidate/candidate.html',
                            controller: 'Candidate_Ctrl',
                            controllerAs: 'vm'
                        }
                    }
                }).state('coreuser.candidate.add', {
            url: '^/BelhopatBackOffice/candidate/add',
            views: {
                'sidebar@': {
                    templateUrl: '/BelhopatBackOffice/app/components/common/defaultTemplate.html'
                },
                'content@': {
                    templateUrl: '/BelhopatBackOffice/app/components/candidate/candidateAdd.html',
                    controller: 'AddCandidate_Ctrl',
                    controllerAs: 'vm'
                }
            }
        }).state('coreuser.candidate.edit', {
            url: '/edit/:id',
            views: {
                'sidebar@': {
                    templateUrl: '/BelhopatBackOffice/app/components/common/defaultTemplate.html'
                },
                'content@': {
                    templateUrl: '/BelhopatBackOffice/app/components/candidate/candidateAdd.html',
                    controller: 'AddCandidate_Ctrl',
                    controllerAs: 'vm'
                }
            }
        }).state('coreuser.offerletter', {
            url: urlConfig.root_path + 'offerletter',
            views: {
                'content@': {
                    templateUrl: '/BelhopatBackOffice/app/components/offerletter/offerletter.html',
                    controller: 'Offerletter_Ctrl',
                    controllerAs: 'vm'
                }
            }
        })
                .state('coreuser.offerletterhome', {
                    url: urlConfig.root_path + 'offerletterhome',
                    views: {
                        'content@': {
                            templateUrl: '/BelhopatBackOffice/app/components/offerletter/offerletterhome.html',
                            controller: 'Offer_Letter_Home_Ctrl',
                            controllerAs: 'vm'
                        }
                    }
                }).state('coreuser.offerletter.verify', {
            url: '/offerletter/verify/:verifyId/:grade',
            views: {
                'content@': {
                    templateUrl: '/BelhopatBackOffice/app/components/offerletter/offerVerify.html',
                    controller: 'Offerletter_Ctrl',
                    controllerAs: 'vm'
                }
            }
        })
                //Employee States Routing
                .state('coreuser.employee', {
                    url: urlConfig.root_path + 'employee',
                    views: {
                        'content@': {
                            templateUrl: '/BelhopatBackOffice/app/components/employee/employee.html',
                            controller: 'Employee_Ctrl',
                            controllerAs: 'vm'
                        }
                    }
                }).state('coreuser.employee.add', {
            url: '/addEmployee',
            views: {
                'sidebar@': {
                    templateUrl: '/BelhopatBackOffice/app/components/common/defaultTemplate.html'
                },
                'content@': {
                    templateUrl: '/BelhopatBackOffice/app/components/employee/employeeAdd.html',
                    controller: 'AddEmployee_Ctrl',
                    controllerAs: 'vm'
                }
            }
        }).state('coreuser.employee.edit', {
            url: '/edit/:id',
            views: {
                'sidebar@': {
                    templateUrl: '/BelhopatBackOffice/app/components/common/defaultTemplate.html'
                },
                'content@': {
                    templateUrl: '/BelhopatBackOffice/app/components/employee/employeeAddFinal.html',
                    controller: 'AddEmployee_Ctrl_Final',
                    controllerAs: 'vm'
                }
            }
        }).state('coreuser.employee.nextStep', {
            url: urlConfig.root_path + 'addEmployeeFinal/:id:candId',
            views: {
                'content@': {
                    templateUrl: '/BelhopatBackOffice/app/components/employee/employeeAddFinal.html',
                    controller: 'AddEmployee_Ctrl_Final',
                    controllerAs: 'vm'
                }
            }
        })
                //Client States Routing        
                .state('coreuser.client', {
                    url: urlConfig.root_path + 'client',
                    views: {
                        'content@': {
                            templateUrl: '/BelhopatBackOffice/app/components/client/client.html',
                            controller: 'Client_Ctrl',
                            controllerAs: 'vm'
                        }
                    }
                }).state('coreuser.client.add', {
            url: '^/BelhopatBackOffice/client/add',
            views: {
                'sidebar@': {
                    templateUrl: '/BelhopatBackOffice/app/components/common/defaultTemplate.html'
                },
                'content@': {
                    templateUrl: '/BelhopatBackOffice/app/components/client/clientAdd.html',
                    controller: 'AddClient_Ctrl',
                    controllerAs: 'vm'
                }
            }
        }).state('coreuser.client.edit', {
            url: '/edit/:id',
            views: {
                'sidebar@': {
                    templateUrl: '/BelhopatBackOffice/app/components/common/defaultTemplate.html'
                },
                'content@': {
                    templateUrl: '/BelhopatBackOffice/app/components/client/clientAdd.html',
                    controller: 'AddClient_Ctrl',
                    controllerAs: 'vm'
                }
            }
        })
                //Oppurtunity States Routing
                .state('coreuser.opportunity', {
                    url: urlConfig.root_path + 'opportunity',
                    views: {
                        'content@': {
                            templateUrl: '/BelhopatBackOffice/app/components/opportunity/opportunity.html',
                            controller: 'Opp_Ctrl',
                            controllerAs: 'vm'
                        }
                    }
                })
                //holiday states routing
                .state('coreuser.holiday', {
                    url: urlConfig.root_path + 'holiday',
                    views: {
                        'content@': {
                            templateUrl: '/BelhopatBackOffice/app/components/holiday/holiday.html',
                            controller: 'Holiday_Ctrl',
                            controllerAs: 'vm'
                        }
                    }
                })
                //Events States Routing
                .state('coreuser.event', {
                    url: urlConfig.root_path + 'event',
                    views: {
                        'content@': {
                            templateUrl: '/BelhopatBackOffice/app/components/event/event.html',
                            controller: 'EventManagement_Ctrl',
                            controllerAs: 'vm'
                        }
                    }
                });
    };
    angular.module('coreModule').config(Core_Routes);
})();
