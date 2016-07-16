(function () {
	var GenerateCredentials_Ctrl = function ($scope, $uibModalInstance, CANDIDATE, Core_Service, candidateDetails) {
		var vm = this,
			fields = CANDIDATE.fieldMapping;
		
		var employeeId = candidateDetails[ 'employeeId' ] || '-';
		
		vm.template = "<div class = 'candidate-details-wrapper'>";
		vm.template += "<div class='cat-row'><span class = 'catagory'>" 
					+ fields[ 'employeeId' ]
					+ " </span><span class='cat-value'>"
					+ employeeId + "</span></div>";
		
		vm.mail = candidateDetails[ 'mail' ];
		if( vm.mail == "-999"){
			Core_Service.sweetAlert("Oops!","Please update personal email of the candidate before continuing.","error"); 
		}
		
		vm.template += "<div class='cat-row'><span class = 'catagory'>" 
					+ fields[ 'personalEmail' ]
					+ " </span><span class='cat-value'>"
					+ vm.mail + "</span></div>";
		vm.template += "</div>";
		
		vm.submitGenerateCredentialsRequest = function( ){
			var data = {"email":vm.mail }
			Core_Service.sendPassword(data).then(function(res){
				if(res.data.success){
	                  Core_Service.sweetAlert("Done!",res.data.data,"success");  
                }
                else{
                   Core_Service.sweetAlert("Oops!",res.data.data,"error"); 
                }
			},
            function(error){
				Core_Service.sweetAlert("Oops!",error.data.data,"error"); 
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