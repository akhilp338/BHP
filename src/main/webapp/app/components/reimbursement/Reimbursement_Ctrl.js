(function () {
    var Reimbursement_Ctrl = function ($scope, $state, $rootScope, Core_Service, $stateParams) {
        $rootScope.active = 'reimbursement';
        var vm = this;
        Core_Service.getLoggedInUserDetails().then(function (res){
            console.log(res)
        },function (err){
           console.log(err)
        });
        angular.element(document).ready(function () {
            var table = $('#reim-table').DataTable(
                    {
                        "paging": false,
                        "ordering": false,
                        "info": false,
                        "bFilter": false
                    }
            );
        });
        
        vm.addToTable = function(evt) {
            
        };
        vm.back = function(){
           $state.go("coreuser.dashboard"); 
        };
        vm.reimburse = function (){};
        
    };

    Reimbursement_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'Core_Service', '$stateParams'];
    angular.module('coreModule')
            .controller('Reimbursement_Ctrl', Reimbursement_Ctrl);
})();

