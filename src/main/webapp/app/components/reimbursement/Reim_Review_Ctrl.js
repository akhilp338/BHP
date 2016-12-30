(function () {
    var Reim_Review_Ctrl = function ($scope, $state, $rootScope, Core_Service, urlConfig, $stateParams, $window, validationService, Core_HttpRequest) {
        var vm = this;
        if ($stateParams.id) {
        Core_Service.getTaskReviewDetails($stateParams.id).then(function(res){
            console.log(res);
        },function(err){
             console.log(err);
        });
    }
};
    Reim_Review_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'Core_Service', 'urlConfig', '$stateParams', '$window', 'validationService', 'Core_HttpRequest'];
    angular.module('coreModule')
            .controller('Reim_Review_Ctrl', Reim_Review_Ctrl);
})();