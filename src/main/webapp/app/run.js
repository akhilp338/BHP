(function () {
    'use strict';
    var Core_Run = function ($rootScope, $state, $timeout, Core_Service, $http,Idle,$window) {
//        $rootScope.globals = $cookieStore.get('globals') || {};
        Idle.watch();
        var userName = angular.element("#successUser").text(),
            errorText = angular.element("#erorUser").text()
        if (userName != "") {
            localStorage["userName"] = userName;
            $state.go("coreuser.candidate");
        }
        else{
        	$state.go("login");  
        }
       
//        if ($rootScope.globals.currentUser) {
//            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
//        }
        $rootScope.$on('$stateNotFound', function (event, unfoundState, fromState, fromParams) {

        });
        $rootScope.datePickerValidation=function(date){
        	var selectedDate = new Date(date);
        	var currentDate = new Date();
        	var timeDiff = Math.abs(currentDate.getTime() - selectedDate.getTime());
        	var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
        	if (diffDays<18){
        		alert("Age should be above 18 years")
        		return false;
        	}
        		
        	
        }
         $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {            
            var parts = toState.name.split(".");
            if(toState.name == "coreuser.offerletter.verify" && fromState.name == ""){                
                $state.go("coreuser.offerletter")
            }
            if(toState.name == "coreuser.upload" && 
              (fromState.name.indexOf("candidate") != -1)){                
                $rootScope.isEmpDocs = false;
            }
            if(toState.name == "coreuser.upload" && 
              (fromState.name.indexOf("employee") != -1)){                
                $rootScope.isEmpDocs = true;
            }
            $rootScope.active = parts[1];
        });
        $rootScope.$on('IdleTimeout', function() {
          Core_Service.sweetAlert("Session timeout", "Your session has timed out. Please login again", "warning", "login");
          Core_Service.ClearCredentials();
        });
        $rootScope.$on('$stateChangeSuccess', function (event, toState, toParams, fromState, fromParams) {
            $rootScope.addPage = (toState.name == "coreuser.candidate.add" ||
                                  toState.name == "coreuser.employee.add" ||
                                  toState.name == "coreuser.candidate.edit" ||
                                  toState.name == "coreuser.employee.edit"||
                                  toState.name == "coreuser.client.edit" ||
                                  toState.name == "coreuser.client.add" ||
                                  toState.name == "coreuser.po.edit" ||
                                  toState.name == "coreuser.po.add" ||
                                  toState.name == "coreuser.consultant.edit" ||
                                  toState.name == "coreuser.consultant.add" ||
                                  toState.name == "coreuser.vendor.edit" ||
                                  toState.name == "coreuser.vendor.add" ||
                                  toState.name == "coreuser.bankacc.edit" ||
                                  toState.name == "coreuser.bankacc.add") ? true : false;
            $rootScope.showLoader = toState.name != "coreuser.dashboard" ?  false : true;
            $rootScope.spinnerActive =false;
            $timeout(function(){
            $rootScope.isShowLoader = false;
            },500);            
        });
    };
    angular.module('coreModule')
            .run(Core_Run);
})();
