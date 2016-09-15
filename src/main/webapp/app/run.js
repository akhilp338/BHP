(function () {
    'use strict';
    var Core_Run = function ($rootScope, $state, $cookieStore, $timeout, Core_Service, $http,Idle) {
        $rootScope.globals = $cookieStore.get('globals') || {};
        $rootScope.showLoader = false;
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
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
        }
        $rootScope.$on('$stateNotFound', function (event, unfoundState, fromState, fromParams) {

        });

        $rootScope.$on('moduleRunLoaded', function (e) {
             alert("sda run")
        });

        $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {            
            $rootScope.showLoader = true;
            $rootScope.spinnerActive =true;
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
                                  toState.name == "coreuser.consultant.edit" ||
                                  toState.name == "coreuser.consultant.add" ||
                                  toState.name == "coreuser.vendor.edit" ||
                                  toState.name == "coreuser.vendor.add" ||
                                  toState.name == "coreuser.bankacc.edit" ||
                                  toState.name == "coreuser.bankacc.add") ? true : false;
                           //$rootScope.showLoader = false;
            $rootScope.spinnerActive =false;
           $timeout(function(){
               //$rootScope.showLoader = false;
           },500);            
        });
    };
    angular.module('coreModule')
            .run(Core_Run);
})();
