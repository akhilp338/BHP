(function () {
    var ViewCandidate_Ctrl = function ($scope, $uibModalInstance, CANDIDATE, Core_Service, candidateDetails) {
        var vm = this,
                fields = CANDIDATE.fieldMapping;
        vm.template = "<div class = 'candidate-details-wrapper'>"
        var array = [];
        
        for (var key in fields) {
            if (candidateDetails[key] === undefined){
                if (key == 'name') {
                    var fname = candidateDetails['firstName'] ? candidateDetails['firstName'] : '',
                        mname = candidateDetails['middleName'] ? candidateDetails['middleName'] : '',
                        lname = candidateDetails['lastName'] ? candidateDetails['lastName'] : '',
                        name = fname + ' ' + mname + ' ' + lname;
                    vm.template += "<div class='cat-row'><span class = 'catagory'>" + fields[key] + " </span><span class='cat-value'>"
                            + name + "</span></div>";
                }                
                else{
                    vm.template += "<div class='cat-row'><span class = 'catagory'>" + fields[key] + " </span><span class='cat-value'>"
                        + '-' + "</span></div>";
                }
            }
            else if(typeof (candidateDetails[key])=='object'){
                if (candidateDetails[key] === 'null' || candidateDetails[key] === null || candidateDetails[key] === 'undefined'){
                    vm.template += "<div class='cat-row'><span class = 'catagory'>" + fields[key].label + " </span><span class='cat-value'>"
                        + '-' + "</span></div>";
                }
                else{
                    if(key.indexOf('ContactNo') != -1){
                        var phnNo = '+' + candidateDetails[key]['country'].phoneCode +' - '+ candidateDetails[key][fields[key].valueKey]
                        vm.template += "<div class='cat-row'><span class = 'catagory'>" + fields[key].label + " </span><span class='cat-value'>"
                                + phnNo + "</span></div>";
                    }
                    else if(key.indexOf('Address') != -1){                    
                        vm.template += "<div class='cat-row'><span class = 'catagory'>" + fields[key].label + " </span><span class='cat-value'>"
                                + candidateDetails[key].address1+ "<br/>" + candidateDetails[key].address2 + "</span></div>";
                    }
                    else if(key.indexOf('passport') != -1){                    
                        vm.template += "<div class='cat-row'><span class = 'catagory'>" + fields[key].label + " </span><span class='cat-value'>"
                                + "Passport No : " + candidateDetails[key].passportNo + "<br/>" 
                                + "Issued On. : " + Core_Service.getFormattedDate(candidateDetails[key].issueDate) + "<br/>"
                                + "Expired On. : " + Core_Service.getFormattedDate(candidateDetails[key].expiryDate) +"</span></div>";
                    }
                    else{
                        vm.template += "<div class='cat-row'><span class = 'catagory'>" + fields[key].label + " </span><span class='cat-value'>"
                            + candidateDetails[key][fields[key].valueKey] + "</span></div>";
                    }
                }
            }
            else if (key == 'dob' || key == 'doj'||key=='createdDate'||key=='updatedDate') {
                 vm.template += "<div class='cat-row'><span class = 'catagory'>" + fields[key] + " </span><span class='cat-value'>"
                        + Core_Service.getFormattedDate(candidateDetails[key]) + "</span></div>";
            }
            else{
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