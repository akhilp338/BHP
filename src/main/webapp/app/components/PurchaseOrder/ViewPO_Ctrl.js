(function () {
    var ViewPO_Ctrl = function ($scope, $uibModalInstance, PO, Core_Service, candidateDetails) {
        var vm = this, fields = PO.fieldMapping;
        vm.template = "<div class = 'candidate-details-wrapper'>";
        vm.viewPO = {};
        console.log(candidateDetails)
        
        for (var key in fields) {
            
            if(typeof (candidateDetails[key])=='object'){
                if (candidateDetails[key] === 'null' || candidateDetails[key] === null || candidateDetails[key] === 'undefined'){
                    vm.template += "<div class='cat-row'><span class = 'catagory'>" + fields[key].label + " </span><span class='cat-value'>"
                        + '-' + "</span></div>";
                }
                else{                   
                        vm.template += "<div class='cat-row'><span class = 'catagory'>" + fields[key].label + " </span><span class='cat-value'>"
                        + candidateDetails[key][fields[key].valueKey] + "</span></div>";
                }
            }
            else if (key.indexOf('Date') > 0) {
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
    ViewPO_Ctrl.$inject = ["$scope", '$uibModalInstance', 'PO',
        'Core_Service', 'candidateDetails'];
    angular.module('coreModule').controller('ViewPO_Ctrl', ViewPO_Ctrl);
})();