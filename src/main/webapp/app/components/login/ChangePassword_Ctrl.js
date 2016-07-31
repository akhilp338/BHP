(function () {
    var ChangePassword_Ctrl = function ($rootScope, $stateParams, $scope, validationService, Core_Service) {
         $rootScope.isLogin = true;
            	var vm = this,
    		vs = new validationService({
    		    controllerAs: vm
    		});
    	vm.changePassword = {};
    	vs.setGlobalOptions({
    		debounce: 1500,
    		scope: $scope,
    		isolatedScope: $scope,
    		preValidateFormElements: false,
    		displayOnlyLastErrorMsg: true
    	});

    	vm.errorMessage = "";
    	
    	vm.changePassword.token = $stateParams.token || undefined;
            vm.submitChangePasswordReq = function (data) {
            	console.log($rootScope);
           if (vs.checkFormValidity($scope) ) {
       			var data = {
       					"id":$rootScope.currentUser.id,
       					"resetToken":data.token, 
       					"currentPassword": data.currentPassword, 
       					"newPassword" : data.newPassword
       					}
       			Core_Service.changePassword(data).then(function(res){
                            Core_Service.sweetAlert("Success !","Password successfully changed.","success","login"); 
       			},
       			function(error){ 
                            Core_Service.sweetAlert("Oops !","An internal error occcured.Please try after some time.",
                    			"error","changePassword");                            
       			});
       		}
        }
    };
    ChangePassword_Ctrl.$inject = ['$rootScope', '$stateParams', '$scope', 'validationService', 'Core_Service'];
    angular.module('coreModule')
            .controller('ChangePassword_Ctrl', ChangePassword_Ctrl);
})();