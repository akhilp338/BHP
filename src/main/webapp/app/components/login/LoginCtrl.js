(function () {
    var Login_Ctrl = function ($scope, $state, $rootScope, Core_Service, Core_ModalService, SweetAlert) {

        var vm = this;
        vm.loginCreds = {};
        $rootScope.isLogin = true;
        vm.errorMessage = "";
        vm.login = function (data) {
            if (vm.loginCreds.username && vm.loginCreds.password) {
           Core_Service.login(data).then(function (res){
               if(res){
            	   Core_Service.SetCredentials(data.username,data.password);
                   $state.go("coreuser.dashboard");
                   vm.errorMessage = "";
               }
               else{
                   vm.errorMessage = "Invalid Credentials";
               }
           },function (error){
               vm.errorMessage = "Invalid Credentials";
           }); 
       }
       else{
          vm.errorMessage = "Both fields are required"; 
       }
        };

        vm.forgotPassword = function (size) {          
            Core_ModalService.openForgotModal().result.then(function(res){
                if(res.data.success){
                  Core_Service.sweetAlert("Done!",res.data.data,"success");  
                }
                else{
                   Core_Service.sweetAlert("Oops!",res.data.data,"error"); 
                }
            },function(error){
               Core_Service.sweetAlert("Oops!",res.data.data,"error");  
            });
        };

    };
    Login_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'Core_Service', 'Core_ModalService', 'SweetAlert'];
    angular.module('coreModule')
            .controller('Login_Ctrl', Login_Ctrl)
            
    })();
