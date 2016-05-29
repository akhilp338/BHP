(function () {
    var ViewCandidate_Ctrl = function ($scope, $uibModalInstance, CANDIDATE, Core_Service, candidateDetails) {
        var vm = this,
                fields = CANDIDATE.fieldMapping;
        vm.template = "<div class = 'candidate-details-wrapper'>"
        var array = [];
        
        for (var key in candidateDetails) {
            if(key =='id'){
            	continue;
            }else if (candidateDetails[key] == undefined && candidateDetails[key] == null) {
                vm.template += "<div class='cat-row'><span class = 'catagory'>" + fields[key] + " </span><span class='cat-value'>"
                        + '-' + "</span></div>";
            }
            else if (key == 'dob' || key == 'doj'||key=='createdDate'||key=='updatedDate') {
                vm.template += "<div class='cat-row'><span class = 'catagory'>" + fields[key] + " </span><span class='cat-value'>"
                        + Core_Service.getFormattedDate(candidateDetails[key]) + "</span></div>";
            }else if(typeof (candidateDetails[key])=='object'){
            	if(key=='createdBy'||key=='updatedBy'||key=='deletedBy'){
            		continue;
            	}else{
                	var thisobject = candidateDetails[key];
                	for(var newKey in thisobject){
                		if(newKey =='id'){
                        	continue;
                        }else if(fields[newKey]==undefined){
                			continue;
                		}
                		vm.template += "<div class='cat-row'><span class = 'catagory'>" + fields[newKey] + " </span><span class='cat-value'>"
                        + thisobject[newKey] + "</span></div>";
                	}
            	}
            }else if(fields[key]==undefined){
            	console.log(key);
            }else {
                vm.template += "<div class='cat-row'><span class = 'catagory'>" + fields[key] + " </span><span class='cat-value'>"
                        + candidateDetails[key] + "</span></div>";
            }
        }
        vm.template += "</div>";
        vm.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
        vm.close = function () {
            $uibModalInstance.dismiss('cancel');
        };


    };
    ViewCandidate_Ctrl.$inject = ["$scope", '$uibModalInstance', 'CANDIDATE', 'Core_Service', 'candidateDetails'];
    angular.module('coreModule')
            .controller('ViewCandidate_Ctrl', ViewCandidate_Ctrl);
})();