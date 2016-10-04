(function () {
    var ViewConsultant_Ctrl = function ($scope, $uibModalInstance, CLIENT, Core_Service, clientDetails) {
        var vm = this, fields = CLIENT.fieldMapping;
        vm.template = "<div class = 'candidate-details-wrapper'>";
        vm.viewClient = {};
        var data = clientDetails;
        vm.viewClient["Client Name"] = data.clientName ? data.clientName : "-";
        vm.viewClient["Client Id"] = data.clientId ? data.clientId : "-";
        vm.viewClient["Email Id"] = data.email ? data.email : "-";
        vm.viewClient["Website"] = data.webUrl ? data.webUrl : "-";
        vm.viewClient["Status"] = data.clientStatus ? data.clientStatus.description : "-";
        vm.viewClient["Country code"] = data.contactNo.country ? data.contactNo.country.phoneCode : "-";
        vm.viewClient["Contact Number"] = data.contactNo.number ? data.contactNo.number : "-";
        vm.viewClient["Address Line 1"] = data.clientAddress ? data.clientAddress.address1 : "-";
        vm.viewClient["Address Line 2"] = data.clientAddress ? data.clientAddress.address2 : "-";
        vm.viewClient["Created Date"] = data.createdDate ? moment(data.createdDate).format("DD MMM YYYY hh:mm a") : "-";
        vm.viewClient["Created By"] = data.createdBy ? data.createdBy.email  : "-";
        vm.viewClient["Updated Date"] = data.updatedDate ? moment(data.updatedDate).format("DD MMM YYYY hh:mm a") : "-";
        vm.viewClient["Updated By"] = data.updatedBy ? data.updatedBy.email : "-";
        for(var key in vm.viewClient){
            vm.template+= '<div class="cat-row"><span class="catagory">'+key+'</span><span class="cat-value">'+vm.viewClient[key]+'</span></div></div>';
        }
        vm.template += "</div>";
        vm.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
        vm.close = function () {
            $uibModalInstance.dismiss('cancel');
        };

    };
    ViewConsultant_Ctrl.$inject = ["$scope", '$uibModalInstance', 'CLIENT',
        'Core_Service', 'candidateDetails'];
    angular.module('coreModule').controller('ViewConsultant_Ctrl', ViewConsultant_Ctrl);
})();