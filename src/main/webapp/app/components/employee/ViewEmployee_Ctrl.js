(function () {
    var ViewEmployee_Ctrl = function ($scope, $uibModalInstance, CANDIDATE, Core_Service, candidateDetails) {
        var vm = this;
        vm.template = '<div class="candidate-details-wrapper ng-scope">';
        vm.viewEmployeeSummary = {};    	
    	vm.Employeetemplate = "";
        $(".candidate-summary").removeClass("init")
        var data = candidateDetails;
        vm.viewEmployeeSummary["Employee Name"] = data.employeeMaster.firstName || "" + " " + data.employeeMaster.lastName || "";
        vm.viewEmployeeSummary["Employee Id"] = data.employeeId;
        vm.viewEmployeeSummary["Country"] = data.employeeMaster.countryOfOrigin ? data.employeeMaster.countryOfOrigin.description : "-";
        vm.viewEmployeeSummary["Date Of Birth"] = data.employeeMaster.dob ? moment(data.employeeMaster.dob).format("DD MMM YYYY hh:mm a") : "-";
        vm.viewEmployeeSummary["Date Of Joining"] = data.employeeMaster.dob ? moment(data.employeeMaster.dob).format("DD MMM YYYY hh:mm a") : "-";
        vm.viewEmployeeSummary["Designation"] = data.employeeMaster.designation ? data.employeeMaster.designation.description : "-";
        vm.viewEmployeeSummary["Passport"] = data.employeeMaster.passport ? data.employeeMaster.passport.passportNo : "-";
        vm.viewEmployeeSummary["Email Id"]= data.employeeMaster.personalEmail;
        vm.viewEmployeeSummary["Country Code"] = "+"+data.employeeMaster.personalContactNo ? data.employeeMaster.personalContactNo.country.phoneCode : "-";
        vm.viewEmployeeSummary["Contact No"] = data.employeeMaster.personalContactNo ? data.employeeMaster.personalContactNo.number : "-";
        
        for(var key in vm.viewEmployeeSummary){
            vm.template+= '<div class="cat-row"><span class="catagory">'+key+'</span><span class="cat-value">'+vm.viewEmployeeSummary[key]+'</span></div></div>';
        }
        vm.template+='</div>'
    	vm.mail = candidateDetails[ 'mail' ];
    	if( vm.mail == "-999"){
    		Core_Service.sweetAlert("Oops!","Please update personal email of the candidate before continuing.","error"); 
    	}
        vm.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
        vm.close = function () {
            $uibModalInstance.dismiss('cancel');
        };


    };
    ViewEmployee_Ctrl.$inject = ["$scope", '$uibModalInstance', 'CANDIDATE', 'Core_Service', 'candidateDetails'];
    angular.module('coreModule')
            .controller('ViewEmployee_Ctrl', ViewEmployee_Ctrl);
})();