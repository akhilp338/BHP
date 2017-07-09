(function () {
    var Offer_Review_Ctrl = function ($scope, $state, $rootScope, Core_Service, urlConfig, $stateParams, $window, validationService, Core_HttpRequest) {
        var vm = this;
        vm.offerSummary = {};
        vm.offerReviewData = {};
        vs = new validationService({
            controllerAs: vm
        });
        vs.setGlobalOptions({
            debounce: 1500,
            scope: $scope,
            isolatedScope: $scope,
            preValidateFormElements: false,
            displayOnlyLastErrorMsg: true
        });
        
        if ($stateParams.taskId) {
            
        //Core_Service.getOfferReviewDetails($stateParams.taskId).then(function(res){
           // console.log(res);
        //});
    }
};
    Offer_Review_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'Core_Service', 'urlConfig', '$stateParams', '$window', 'validationService', 'Core_HttpRequest'];
    angular.module('coreModule')
            .controller('Offer_Review_Ctrl', Offer_Review_Ctrl);
})();