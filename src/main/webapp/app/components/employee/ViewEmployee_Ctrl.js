(function () {
    var ViewEmployee_Ctrl = function ($scope, $uibModalInstance, CANDIDATE, Core_Service, candidateDetails) {
//        var vm = this,
//                fields = CANDIDATE.fieldMapping;
//        vm.template = "<div class = 'candidate-details-wrapper'>"
//        var array = [];
//        
//        for (var key in candidateDetails) {
//            if(key =='id'){
//            	continue;
//            }else if (candidateDetails[key] == undefined && candidateDetails[key] == null) {
//                vm.template += "<div class='cat-row'><span class = 'catagory'>" + fields[key] + " </span><span class='cat-value'>"
//                        + '-' + "</span></div>";
//            }
//            else if (key == 'dob' || key == 'doj'||key=='createdDate'||key=='updatedDate') {
//                vm.template += "<div class='cat-row'><span class = 'catagory'>" + fields[key] + " </span><span class='cat-value'>"
//                        + Core_Service.getFormattedDate(candidateDetails[key]) + "</span></div>";
//            }else if(typeof (candidateDetails[key])=='object'){
//            	if(key=='createdBy'||key=='updatedBy'||key=='deletedBy'){
//            		continue;
//            	}else{
//                	var thisobject = candidateDetails[key];
//                	for(var newKey in thisobject){
//                		if(newKey =='id'){
//                        	continue;
//                        }else if(fields[newKey]==undefined){
//                			continue;
//                		}
//                		vm.template += "<div class='cat-row'><span class = 'catagory'>" + fields[newKey] + " </span><span class='cat-value'>"
//                        + thisobject[newKey] + "</span></div>";
//                	}
//            	}
//            }else if(fields[key]==undefined){
//            	console.log(key);
//            }else {
//                vm.template += "<div class='cat-row'><span class = 'catagory'>" + fields[key] + " </span><span class='cat-value'>"
//                        + candidateDetails[key] + "</span></div>";
//            }
//        }
//        vm.template += "</div>";
    	var vm = this,
		fields = CANDIDATE.fieldMapping;
    	vm.employeeSummary = {};
//	var employeeId = candidateDetails[ 'employeeId' ] || '-';
//	
//	vm.template = "<div class = 'candidate-details-wrapper'>";
//	vm.template += "<div class='cat-row'><span class = 'catagory'>" 
//				+ fields[ 'employeeId' ]
//				+ " </span><span class='cat-value'>"
//				+ employeeId + "</span></div>";
//	
//	vm.mail = candidateDetails[ 'mail' ];
//	if( vm.mail == "-999"){
//		Core_Service.sweetAlert("Oops!","Please update personal email of the candidate before continuing.","error"); 
//	}
//	
//	vm.template += "<div class='cat-row'><span class = 'catagory'>" 
//				+ fields[ 'personalEmail' ]
//				+ " </span><span class='cat-value'>"
//				+ vm.mail + "</span></div>";
//	vm.template += "</div>";
    	
    	
    	vm.Employeetemplate = "";
        $(".candidate-summary").removeClass("init")
        var data = addEmployeeTable.data()[$(this).index()];
        vm.employeeSummary["Name"] = data.firstName + " " + data.lastName;
        vm.employeeSummary["Candidate Id"] = data.candidateId;
        vm.employeeSummary["Country"] = data.countryOfOrigin.description;
        vm.employeeSummary["DOB"] = moment(data.dob).format("DD MMM YYYY hh:mm a");
        vm.employeeSummary["Designation"] = data.designation.description;
        vm.employeeSummary["Passport"] = data.passport.passportNo;;
        vm.employeeSummary["Email Id"]= data.personalEmail;
        vm.employeeSummary["Country Code"] = "+"+data.personalContactNo.country.phoneCode;
        vm.employeeSummary["Contact No"] = data.personalContactNo.number;
        
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