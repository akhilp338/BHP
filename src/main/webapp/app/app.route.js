(function () {
    'use strict';
    var Core_Routes = function ( $stateProvider, $locationProvider, $urlRouterProvider, urlConfig ) {
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
                .state('coreuser.dashboard', {
                    url: urlConfig.root_path + 'dashboard',
                    views: {
                        'content@': {
                            templateUrl: '/BelhopatBackOffice/app/components/dashboard/dashboard.html',
                            controller: 'Dash_Ctrl',
                            controllerAs: 'vm'
                        }
                    }
                })
                .state('coreuser.success', {
		            url: urlConfig.root_path + 'loginSuccess',
		            views: {
		                'content@': {
		                    templateUrl: '/BelhopatBackOffice/app/components/dashboard/dashboard.html',
		                    controller: 'Dash_Ctrl',
		                    controllerAs: 'vm'
		                }
		            }
		        })
		        .state('login.error', {
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
                })
                .state('coreuser.candidate.add', {
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
		        })
		        .state('coreuser.candidate.edit', {
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
		        })
		        .state('coreuser.candidate.upload', {
		            url: urlConfig.root_path + 'upload',
		            views: {
		                'sidebar@': {
		                    templateUrl: '/BelhopatBackOffice/app/components/common/defaultTemplate.html'
		                },
		                'content@': {
		                    templateUrl: '/BelhopatBackOffice/app/components/fileUpload/candidateDocs.html',
		                    controller: 'FileUpload_Ctrl',
		                    controllerAs: 'vm'
		                }
		            }
		        })
		        .state('coreuser.offerletter', {
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
                })
                .state('coreuser.offerletter.verify', {
		            url: '/offerletter/verify/:verifyId/:grade',
		            views: {
		                'content@': {
		                    templateUrl: '/BelhopatBackOffice/app/components/offerletter/offerVerify.html',
		                    controller: 'Offerletter_Ctrl',
		                    controllerAs: 'vm'
		                }
		            }
		})
                .state('coreuser.offerletter.review', {
		            url: '/offerletter/review',
		            views: {
		                'content@': {
		                    templateUrl: '/BelhopatBackOffice/app/components/offerletter/offer-review.html',
		                    controller: 'Offer_Review_Ctrl',
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
                })
                .state('coreuser.employee.add', {
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
            	})
            	.state('coreuser.employee.edit', {
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
        		})
        		.state('coreuser.employee.nextStep', {
		            url: urlConfig.root_path + 'addEmployeeFinal/:id:candId',
		            views: {
		                'content@': {
		                    templateUrl: '/BelhopatBackOffice/app/components/employee/employeeAddFinal.html',
		                    controller: 'AddEmployee_Ctrl_Final',
		                    controllerAs: 'vm'
		                }
		            }
		        })
		        .state('coreuser.employee.upload', {
		            url: urlConfig.root_path + 'upload',
		            views: {
		                'sidebar@': {
		                    templateUrl: '/BelhopatBackOffice/app/components/common/defaultTemplate.html'
		                },
		                'content@': {
		                    templateUrl: '/BelhopatBackOffice/app/components/fileUpload/empDocs.html',
		                    controller: 'FileUpload_Ctrl',
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
                })
                .state('coreuser.client.add', {
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
                })
                .state('coreuser.client.edit', {
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
                })

                //consultant states routing
                .state('coreuser.consultant', {
                    url: urlConfig.root_path + 'consultant',
                    views: {
                        'content@': {
                            templateUrl: '/BelhopatBackOffice/app/components/consultant/consultant.html',
                            controller: 'Consultant_Ctrl',
                            controllerAs: 'vm'
                        }
                    }
                }).state('coreuser.consultant.add', {
		            url: '^/BelhopatBackOffice/consultant/add',
		            views: {
		                'sidebar@': {
		                    templateUrl: '/BelhopatBackOffice/app/components/common/defaultTemplate.html'
		                },
		                'content@': {
		                    templateUrl: '/BelhopatBackOffice/app/components/consultant/consultantAdd.html',
		                    controller: 'AddConsultant_Ctrl',
		                    controllerAs: 'vm'
		                }
		            }
                })
                .state('coreuser.consultant.edit', {
		            url: '/edit/:id',
		            views: {
		                'sidebar@': {
		                    templateUrl: '/BelhopatBackOffice/app/components/common/defaultTemplate.html'
		                },
		                'content@': {
		                    templateUrl: '/BelhopatBackOffice/app/components/consultant/consultantAdd.html',
		                    controller: 'AddConsultant_Ctrl',
		                    controllerAs: 'vm'
		                }
		            }
		        })
		        .state('coreuser.consultant.upload', {
		            url: urlConfig.root_path + 'upload',
		            views: {
		                'sidebar@': {
		                    templateUrl: '/BelhopatBackOffice/app/components/common/defaultTemplate.html'
		                },
		                'content@': {
		                    templateUrl: '/BelhopatBackOffice/app/components/fileUpload/consultantDocs.html',
		                    controller: 'FileUpload_Ctrl',
		                    controllerAs: 'vm'
		                }
		            }
		        })
                //vendor states routing
                .state('coreuser.vendor', {
                    url: urlConfig.root_path + 'vendor',
                    views: {
                        'content@': {
                            templateUrl: '/BelhopatBackOffice/app/components/vendor/vendor.html',
                            controller: 'Vendor_Ctrl',
                            controllerAs: 'vm'
                        }
                    }
                })
                .state('coreuser.vendor.add', {
		            url: '^/BelhopatBackOffice/vendor/add',
		            views: {
		                'sidebar@': {
		                    templateUrl: '/BelhopatBackOffice/app/components/common/defaultTemplate.html'
		                },
		                'content@': {
		                    templateUrl: '/BelhopatBackOffice/app/components/vendor/vendorAdd.html',
		                    controller: 'AddVendor_Ctrl',
		                    controllerAs: 'vm'
		                }
		            }
                })
                .state('coreuser.vendor.edit', {
		            url: '/edit/:id',
		            views: {
		                'sidebar@': {
		                    templateUrl: '/BelhopatBackOffice/app/components/common/defaultTemplate.html'
		                },
		                'content@': {
		                    templateUrl: '/BelhopatBackOffice/app/components/vendor/vendorAdd.html',
		                    controller: 'AddVendor_Ctrl',
		                    controllerAs: 'vm'
		                }
		            }
		        })
                //Bank Account states routing
                .state('coreuser.bankacc', {
                    url: urlConfig.root_path + 'bankacc',
                    views: {
                        'content@': {
                            templateUrl: '/BelhopatBackOffice/app/components/bankacc/bankacc.html',
                            controller: 'BankAcc_Ctrl',
                            controllerAs: 'vm'
                        }
                    }
                })
                .state('coreuser.bankacc.add', {
		            url: '^/BelhopatBackOffice/bankacc/add',
		            views: {
		                'sidebar@': {
		                    templateUrl: '/BelhopatBackOffice/app/components/common/defaultTemplate.html'
		                },
		                'content@': {
		                    templateUrl: '/BelhopatBackOffice/app/components/bankacc/bankaccAdd.html',
		                    controller: 'AddbankAcc_Ctrl',
		                    controllerAs: 'vm'
		                }
		            }
		        })
		        .state('coreuser.bankacc.edit', {
		            url: '/edit/:id',
		            views: {
		                'sidebar@': {
		                    templateUrl: '/BelhopatBackOffice/app/components/common/defaultTemplate.html'
		                },
		                'content@': {
		                    templateUrl: '/BelhopatBackOffice/app/components/bankacc/bankaccAdd.html',
		                    controller: 'AddbankAcc_Ctrl',
		                    controllerAs: 'vm'
		                }
		            }
		        })
                //Bank Account states routing
                .state('coreuser.reimbursement', {
                    url: urlConfig.root_path + 'reimbursement',
                    views: {
                        'content@': {
                            templateUrl: '/BelhopatBackOffice/app/components/reimbursement/reimbursement.html',
                            controller: 'Reimbursement_Ctrl',
                            controllerAs: 'vm'
                        }
                    }
                })
                .state('coreuser.reimbursement.upload', {
		            url: urlConfig.root_path + 'upload',
		            views: {
		                'sidebar@': {
		                    templateUrl: '/BelhopatBackOffice/app/components/common/defaultTemplate.html'
		                },
		                'content@': {
		                    templateUrl: '/BelhopatBackOffice/app/components/fileUpload/reimDocs.html',
		                    controller: 'FileUpload_Ctrl',
		                    controllerAs: 'vm'
		                }
		            }
		})
                .state('coreuser.reimbursement.review', {
		            url: urlConfig.root_path + 'reimbursement/review/:taskId/:id',
		            views: {
		                'content@': {
		                    templateUrl: '/BelhopatBackOffice/app/components/reimbursement/reim-review.html',
		                    controller: 'Reim_Review_Ctrl',
		                    controllerAs: 'vm'
		                }
		            }
		})
                //Attendance States Routing
                .state('coreuser.attendance', {
                    url: urlConfig.root_path + 'attendance',
                    views: {
                        'content@': {
                            templateUrl: '/BelhopatBackOffice/app/components/Attendance/attendance.html',
                            controller: 'AttendanceMgmtCtrl',
                            controllerAs: 'vm'
                        }
                    }
                })
                //Attendance States Routing
                .state('coreuser.po', {
                    url: urlConfig.root_path + 'po',
                    views: {
                        'content@': {
                            templateUrl: '/BelhopatBackOffice/app/components/PurchaseOrder/po.html',
                            controller: 'PO_Ctrl',
                            controllerAs: 'vm'
                        }
                    }
                })
                .state('coreuser.po.add', {
		            url: '/add',
		            views: {
		                'sidebar@': {
		                    templateUrl: '/BelhopatBackOffice/app/components/common/defaultTemplate.html'
		                },
		                'content@': {
		                    templateUrl: '/BelhopatBackOffice/app/components/PurchaseOrder/poAdd.html',
		                    controller: 'AddPO_Ctrl',
		                    controllerAs: 'vm'
		                }
		            }
		        })
		        .state('coreuser.po.edit', {
	            url: '/edit/:id',
	            views: {
	                'sidebar@': {
	                    templateUrl: '/BelhopatBackOffice/app/components/common/defaultTemplate.html'
	                },
	                'content@': {
	                    templateUrl: '/BelhopatBackOffice/app/components/PurchaseOrder/poAdd.html',
	                    controller: 'AddPO_Ctrl',
	                    controllerAs: 'vm'
	                }
	            }
	        });
	};
    angular.module('coreModule').config(Core_Routes);
})();
