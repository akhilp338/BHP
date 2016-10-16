(function () {
    var Home_Ctrl = function ($scope, $state, $rootScope, $timeout) {
        var vm = this;        
        $rootScope.isLogin = false;
        vm.showMenu = function (e) {
           $(".collapse").slideToggle("1000");            
        };
        vm.menuClick = function (e) {
            angular.element('.navbar-nav li').removeClass("active");
            angular.element(e.currentTarget).addClass("active");                     
        };       
    };

    Home_Ctrl.$inject = ["$scope", '$state', '$rootScope', '$timeout'];
    angular.module('coreModule')
            .controller('Home_Ctrl', Home_Ctrl);
})();