(function () {
    var Home_Ctrl = function ($scope, $state, $rootScope, Core_Service) {
        var vm = this;
        vm.services = [
            {name:"dashboard",label: "Dashboard", icon: "fa-dashboard",state:"coreuser.dashboard",isOptional:false},
            {name:"candidate",label: "Candidate Management", icon: "fa-briefcase",state:"coreuser.candidate",isOptional:false},
            {name:"employee",label: "Employee Management", icon: "fa-users",state:"coreuser.employee",isOptional:false},
            {name:"client",label: "Client Management", icon: "fa-flag",state:"coreuser.client",isOptional:false},
            {name:"offerletter",label: "Offer Letter Processing", icon: "fa-users",state:"coreuser.offerletterhome",isOptional:false},
            {name:"event",label: "Event Management", icon: "fa-gift",state:"coreuser.event",isOptional:false},
            {name:"consultant",label: "Consultant Management", icon: "fa-gift",state:"coreuser.consultant",isOptional:false},
            {name:"vendor",label: "Vendor Management", icon: "fa-gift",state:"coreuser.vendor",isOptional:false},
            {name:"bankacc",label: "Bank Account Management", icon: "fa-gift",state:"coreuser.bankacc",isOptional:false}
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