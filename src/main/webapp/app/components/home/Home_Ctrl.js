(function () {
    var Home_Ctrl = function ($scope, $state, $rootScope, Core_Service) {
        var vm = this;
        vm.services = [
            {name:"dashboard",label: "Dashboard", icon: "fa-dashboard",state:"coreuser.dashboard", title:'Dashboard'},
            {name:"candidate",label: "Candidate Management", icon: "fa-briefcase",state:"coreuser.candidate", title:'Candidate Management'},
            {name:"employee",label: "Employee Management", icon: "fa-users",state:"coreuser.employee", title:'Employee Management'},
            {name:"client",label: "Client Management", icon: "fa-flag",state:"coreuser.client", title:'Client Management'},
            {name:"offerletter",label: "Offer Letter Processing", icon: "fa-envelope",state:"coreuser.offerletterhome", title:'Offerletter Processing'},
            {name:"event",label: "Event Management", icon: "fa-gift",state:"coreuser.event", title:'Event Management'},
            {name:"consultant",label: "Consultant Management", icon: "fa-gift",state:"coreuser.consultant", title:'Consultant Management'},
            {name:"vendor",label: "Vendor Management", icon: "fa-gift",state:"coreuser.vendor", title:'Vendor Management'},
            {name:"attendance",label: "Attendance Details", icon: "fa-gift",state:"coreuser.attendance", title:'Attendance Management'},
            {name:"reimbursement",label: "Reimbursement", icon: "fa-suitcase",state:"coreuser.reimbursement", title:'Reimbursement Processing'},
            {name:"po",label: "PO Management", icon: "fa-suitcase",state:"coreuser.reimbursement", title:'Purchase order Management'}
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