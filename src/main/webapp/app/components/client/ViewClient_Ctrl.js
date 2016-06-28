(function() {
	var ViewClient_Ctrl = function($scope, $uibModalInstance, CLIENT, Core_Service, clientDetails) {
		var vm = this, fields = CLIENT.fieldMapping;
		vm.template = "<div class = 'candidate-details-wrapper'>"
		var array = [];

		for ( var key in clientDetails) {
			if (key == 'id') {
				continue;
			} else if (clientDetails[key] == undefined
					&& clientDetails[key] == null) {
				vm.template += "<div class='cat-row'><span class = 'catagory'>"
						+ fields[key] + " </span><span class='cat-value'>"
						+ '-' + "</span></div>";
			} else if (key == 'dob' || key == 'doj' || key == 'createdDate'
					|| key == 'updatedDate') {
				vm.template += "<div class='cat-row'><span class = 'catagory'>"
						+ fields[key] + " </span><span class='cat-value'>"
						+ Core_Service.getFormattedDate(clientDetails[key])
						+ "</span></div>";
			} else if (typeof (clientDetails[key]) == 'object') {
				if (key == 'createdBy' || key == 'updatedBy'
						|| key == 'deletedBy') {
					continue;
				} else {
					var thisobject = clientDetails[key];
					for ( var newKey in thisobject) {
						if (newKey == 'id') {
							continue;
						} else if (fields[newKey] == undefined) {
							continue;
						}
						vm.template += "<div class='cat-row'><span class = 'catagory'>"
								+ fields[newKey]
								+ " </span><span class='cat-value'>"
								+ thisobject[newKey] + "</span></div>";
					}
				}
			} else if (fields[key] == undefined) {
				console.log(key);
			} else {
				vm.template += "<div class='cat-row'><span class = 'catagory'>"
						+ fields[key] + " </span><span class='cat-value'>"
						+ clientDetails[key] + "</span></div>";
			}
		}
		vm.template += "</div>";
		vm.cancel = function() {
			$uibModalInstance.dismiss('cancel');
		};
		vm.close = function() {
			$uibModalInstance.dismiss('cancel');
		};

	};
	ViewClient_Ctrl.$inject = [ "$scope", '$modalInstance', 'CLIENT',
			'Core_Service', 'candidateDetails' ];
	angular.module('coreModule').controller('ViewClient_Ctrl', ViewClient_Ctrl);
})();