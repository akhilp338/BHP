(function () {
    var Home_Ctrl = function ($scope, $state, $rootScope, Core_Service) {
        var vm = this;
        vm.services = [
            {name:"dashboard",label: "Dashboard", icon: "fa-dashboard",state:"coreuser.dashboard"},
            {name:"candidate",label: "Candidate Management", icon: "fa-briefcase",state:"coreuser.candidate"},
            {name:"employee",label: "Employee Management", icon: "fa-users",state:"coreuser.employee"},
            {name:"client",label: "Client Management", icon: "fa-flag",state:"coreuser.client"},
            {name:"opportunity",label: "Opportunity Management", icon: "fa-gift",state:"coreuser.opportunity"},
            {name:"holiday",label: "Holiday Management", icon: "fa-paper-plane",state:"coreuser.holiday"}
        ];
        $rootScope.isLogin = false;
        vm.showMenu = function (e) {
           $(".collapse").slideToggle("1000");            
        };
        vm.menuClick = function (e) {
            angular.element('.sidebar-nav li').removeClass("active");
            angular.element(e.currentTarget).addClass("active"); 
                     
        };
    };

    Home_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'Core_Service'];
    angular.module('coreModule')
            .controller('Home_Ctrl', Home_Ctrl);
})();