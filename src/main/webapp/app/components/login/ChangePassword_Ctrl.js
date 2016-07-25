(function () {
    var ChangePassword_Ctrl = function ( $scope, $uibModalInstance, $route, $routeParams, $location, validationService, Core_Service) {

    	var vm = this,
    		vs = new validationService({
    		    controllerAs: vm
    		});
    	
    	vs.setGlobalOptions({
    		debounce: 1500,
    		scope: $scope,
    		isolatedScope: $scope,
    		preValidateFormElements: false,
    		displayOnlyLastErrorMsg: true
    	});

    	vm.errorMessage = "";
    	
    	vm.token = $routeParams.token;
    	
        vm.submitChangePasswordReq = function (data) {
           if (vm.changePassword.currentPassword && vm.changePassword.newPassword && 
    		   vm.changePassword.newPassword == vm.changePassword.confirmNewPassword
    		   && vs.checkFormValidity($scope) ) {
       			var data = {
       					"resetToken":vm.token, 
       					"currentPassword": vm.changePassword.currentPassword, 
       					"newPassword" : vm.changePassword.newPassword
       					}
       			Core_Service.changePassword(data).then(function(res){
       				$uibModalInstance.close(res); 
       			},
       			function(error){
       				$uibModalInstance.close(error);  
       			});
       		}
        }
           
       	vm.cancel = function () {
       		$uibModalInstance.dismiss('cancel');
       	};
       	
        vm.changePassword = function (size) {          
            Core_ModalService.openChangePassword().result.then(function(res){
                if(res.data.success){
                  Core_Service.sweetAlert("Done!",res.data.data,"success","login");  
                }
                else{
                   Core_Service.sweetAlert("Oops!",res.data.data,"error"); 
                }
            },function(error){
               Core_Service.sweetAlert("Oops!",res.data.data,"error");  
            });
        };

    };
    ChangePassword_Ctrl.$inject = ["$scope", '$uibModalInstance', '$route', '$routeParams', '$location', 'validationService', 'Core_Service'];
    angular.module('coreModule')
            .controller('ChangePassword_Ctrl', ChangePassword_Ctrl);
            
    })();



