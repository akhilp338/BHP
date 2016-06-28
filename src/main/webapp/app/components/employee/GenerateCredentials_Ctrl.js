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
		
		vm.employeeMasterObject = candidateDetails[ 'employeeMaster' ];
		vm.officialEmail = vm.employeeMasterObject[ 'officialEmail' ] || '-';
		
		vm.template += "<div class='cat-row'><span class = 'catagory'>" 
					+ fields[ 'officialEmail' ]
					+ " </span><span class='cat-value'>"
					+ vm.officialEmail + "</span></div>";
		vm.template += "</div>";
		
		vm.submitGenerateCredentialsRequest = function( ){
			var data = {"email":vm.officialEmail }
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
	GenerateCredentials_Ctrl.$inject = ["$scope", '$modalInstance', 'CANDIDATE', 'Core_Service', 'candidateDetails'];
    angular.module('coreModule')
            .controller('GenerateCredentials_Ctrl', GenerateCredentials_Ctrl);
})();