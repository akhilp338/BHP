(function () {
    var ViewVendor_Ctrl = function ($scope, $uibModalInstance, CLIENT, Core_Service, clientDetails) {
        var vm = this, fields = CLIENT.fieldMapping;
        vm.template = "<div class = 'candidate-details-wrapper'>";
        vm.viewVendor = {};
        var data = vendorDetails;
        vm.viewVendor["Vendor Code"] = data.vendorCode ? data.vendorCode : "-";
        vm.viewVendor["Vendor Name"] = data.vendorName ? data.vendorName : "-";        
        vm.viewVendor["Category"] = data.category.description? data.category.description : "-";
        vm.viewVendor["Description"] = data.description ? data.description : "-";
        vm.viewVendor["Contact Number"] = data.phoneNo ? data.phoneNo : "-";       
        for(var key in vm.viewVendor){
            vm.template+= '<div class="cat-row"><span class="catagory">'+key+'</span><span class="cat-value">'+vm.viewVendor[key]+'</span></div></div>';
        }
        vm.template += "</div>";
        vm.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
        vm.close = function () {
            $uibModalInstance.dismiss('cancel');
        };

    };
    ViewVendor_Ctrl.$inject = ["$scope", '$uibModalInstance', 'VENDOR',
        'Core_Service', 'candidateDetails'];
    angular.module('coreModule').controller('ViewVendor_Ctrl', ViewVendor_Ctrl);
})();