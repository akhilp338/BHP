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
        
        vm.getDisplayTemplate = function(data, keys){
            var template = "<div class='reim-summary-container col-md-10 col-lg-10 col-sm-10 col-xs-12'>";
            for(var i=0; i<keys.length; i++){                
                if(data[keys[i]]){
                    template += '<div class="item col-md-6 col-lg-6 col-sm-6 col-xs-12">'+  
                                '<label class="col-md-6 col-lg-6 col-sm-6 col-xs-12 item-label">'+ keys[i] +
                                '</label><p class="col-md-6 col-lg-6 col-sm-6 col-xs-12 item-label-desc"> :   '+ data[keys[i]] +
                                '</p></div>';
                }
            }
            template += '</div>';
            return template;
        };
    }
};
    Offer_Review_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'Core_Service', 'urlConfig', '$stateParams', '$window', 'validationService', 'Core_HttpRequest'];
    angular.module('coreModule')
            .controller('Offer_Review_Ctrl', Offer_Review_Ctrl);
})();