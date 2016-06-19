(function () {
    var Home_Ctrl = function ($scope, $state, $rootScope, Core_Service) {
        var vm = this;
        vm.services = [
            {name:"dashboard",label: "Dashboard", icon: "fa-dashboard",state:"coreuser.dashboard"},
            {name:"candidate",label: "Candidate Management", icon: "fa-briefcase",state:"coreuser.candidate"},
            {name:"employee",label: "Employee Management", icon: "fa-users",state:"coreuser.employee"},
            {name:"client",label: "Client Management", icon: "fa-flag",state:"coreuser.client"},
            {name:"offerletter",label: "Offer Letter Processing", icon: "fa-users",state:"coreuser.offerletter"},
            {name:"event",label: "Event Management", icon: "fa-gift",state:"coreuser.event"}
        ];
        $rootScope.isLogin = false;
        vm.showMenu = function (e) {
           $(".collapse").slideToggle("1000");            
        };
        vm.menuClick = function (e) {
            angular.element('.navbar-nav li').removeClass("active");
            angular.element(e.currentTarget).addClass("active");                     
        };
    };

    Home_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'Core_Service'];
    angular.module('coreModule')
            .controller('Home_Ctrl', Home_Ctrl);
})();