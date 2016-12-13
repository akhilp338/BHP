(function () {
    var ViewConsultant_Ctrl = function ($scope, $uibModalInstance, CANDIDATE, Core_Service, consultantDetails) {
        var vm = this,
                fields = CANDIDATE.fieldMapping;
        vm.template = "<div class = 'candidate-details-wrapper'>";
        vm.viewConsultant = {};
        var data = consultantDetails;
        console.log(data);
        debugger;
        vm.viewConsultant["Full Name"] = data.fullName ? data.fullName : "-";
        vm.viewConsultant["Consultant Id"] = data.consultantId ? data.consultantId : "-";
        vm.viewConsultant["Email Id"] = data.personalEmail ? data.personalEmail : "-";
        vm.viewConsultant["Gender"] = data.gender.description? data.gender.description: "-";
        vm.viewConsultant["Status"] = data.status ? data.status.description : "-";
        vm.viewConsultant["Sourced By"] = data.sourcedBy ? data.sourcedBy : "-";
        vm.viewConsultant["Prior Experience"] = 
        	data.priorExperienceYear ? 
        			( data.priorExperienceMonth ? data.priorExperienceYear + " years and "  + data.priorExperienceMonth + " months": data.priorExperienceYear + " yrs" ):
        				( data.priorExperienceMonth ? data.priorExperienceMonth + " months" : "-" );
        vm.viewConsultant["Created Date"] = data.createdDate ? moment(data.createdDate).format("DD MMM YYYY hh:mm a") : "-";
        vm.viewConsultant["Created By"] = data.createdBy ? data.createdBy.email  : "-";
        vm.viewConsultant["Updated Date"] = data.updatedDate ? moment(data.updatedDate).format("DD MMM YYYY hh:mm a") : "-";
        vm.viewConsultant["Updated By"] = data.updatedBy ? data.updatedBy.email : "-";
        for(var key in vm.viewConsultant){
            vm.template+= '<div class="cat-row"><span class="catagory">'+key+'</span><span class="cat-value">'+vm.viewConsultant[key]+'</span></div></div>';
        }
        vm.template += "</div>";
        vm.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
        vm.close = function () {
            $uibModalInstance.dismiss('cancel');
        };


    };
    ViewConsultant_Ctrl.$inject = ["$scope", '$uibModalInstance', 'CANDIDATE', 'Core_Service', 'candidateDetails'];
    angular.module('coreModule')
            .controller('ViewConsultant_Ctrl', ViewConsultant_Ctrl);
})();