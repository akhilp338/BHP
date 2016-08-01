(function () {
    var ViewClient_Ctrl = function ($scope, $uibModalInstance, CLIENT, Core_Service, clientDetails) {
        var vm = this, fields = CLIENT.fieldMapping;
        vm.template = "<div class = 'candidate-details-wrapper'>"
        var array = [];
        vm.viewClient = {};

//		for ( var key in clientDetails) {
//			if (key == 'id') {
//				continue;
//			} else if (clientDetails[key] == undefined
//					&& clientDetails[key] == null) {
//				vm.template += "<div class='cat-row'><span class = 'catagory'>"
//						+ fields[key] + " </span><span class='cat-value'>"
//						+ '-' + "</span></div>";
//			} else if (key == 'dob' || key == 'doj' || key == 'createdDate'
//					|| key == 'updatedDate') {
//				vm.template += "<div class='cat-row'><span class = 'catagory'>"
//						+ fields[key] + " </span><span class='cat-value'>"
//						+ Core_Service.getFormattedDate(clientDetails[key])
//						+ "</span></div>";
//			} else if (typeof (clientDetails[key]) == 'object') {
//				if (key == 'createdBy' || key == 'updatedBy'
//						|| key == 'deletedBy') {
//					continue;
//				} else {
//					var thisobject = clientDetails[key];
//					for ( var newKey in thisobject) {
//						if (newKey == 'id') {
//							continue;
//						} else if (fields[newKey] == undefined) {
//							continue;
//						}
//						vm.template += "<div class='cat-row'><span class = 'catagory'>"
//								+ fields[newKey]
//								+ " </span><span class='cat-value'>"
//								+ thisobject[newKey] + "</span></div>";
//					}
//				}
//			} else if (fields[key] == undefined) {
//				console.log(key);
//			} else {
//				vm.template += "<div class='cat-row'><span class = 'catagory'>"
//						+ fields[key] + " </span><span class='cat-value'>"
//						+ clientDetails[key] + "</span></div>";
//			}
//		}
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
    ViewClient_Ctrl.$inject = ["$scope", '$uibModalInstance', 'CLIENT',
        'Core_Service', 'candidateDetails'];
    angular.module('coreModule').controller('ViewClient_Ctrl', ViewClient_Ctrl);
})();