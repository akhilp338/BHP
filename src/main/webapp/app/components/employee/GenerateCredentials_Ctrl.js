(function () {
	var GenerateCredentials_Ctrl = function ($scope, $uibModalInstance, CANDIDATE, Core_Service, candidateDetails) {
		var vm = this,
			fields = CANDIDATE.fieldMapping;
		
		var employeeId = candidateDetails[ 'employeeId' ] || '-';
		
		vm.template = "<div class = 'generate-login-wrapper'>";
		vm.template += "<div class='row'>" +
				    "<span class = 'catagory'>" 
					+ fields[ 'employeeId' ] 
					+ ": </span><span class='cat-value'>"
					+ employeeId + "</span></div>";
		
		vm.template += "<div class='row'> <span class = 'catagory'>Official email </span>" +
				
				"<input type='text' name='officialEmail' id='officialEmail'>" +
				"<br></div></div>" ;
		vm.mail = candidateDetails[ 'mail' ];
		if( vm.mail == "-999"){
			Core_Service.sweetAlert("Oops!","Please update personal email of the candidate before continuing.","error"); 
		}
		
		vm.template += "Welcome mail with login credentials will be send to his/her personal email ( " 
					+ vm.mail + ")</div>";
		
		vm.submitGenerateCredentialsRequest = function( ){
			var data = {"email":vm.mail,'officialEmail':$('#officialEmail').val() }
			Core_Service.sendPassword(data).then(function(res){
				if(res.data.success){
	                  Core_Service.sweetAlertWithConfirm("Done!",res.data.data,"success");  
                }
                else{
                   Core_Service.sweetAlert("Oops!",res.data.data,"error"); 
                }
			},
            function(error){
//				Core_Service.sweetAlert("Oops!",error.data.data,"error"); 
				Core_Service.sweetAlertWithConfirm("Done!",error.data.data,"success"); 
            });
        };
		
        vm.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
        vm.close = function () {
            $uibModalInstance.dismiss('cancel');
        };
	};
	GenerateCredentials_Ctrl.$inject = ["$scope", '$uibModalInstance', 'CANDIDATE', 'Core_Service', 'candidateDetails'];
    angular.module('coreModule')
            .controller('GenerateCredentials_Ctrl', GenerateCredentials_Ctrl);
})();