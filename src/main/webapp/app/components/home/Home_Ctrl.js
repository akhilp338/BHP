(function () {
    var Home_Ctrl = function ($rootScope, $state, $rootScope, $timeout) {
        var vm = this;        
        $rootScope.isLogin = false;
        vm.showMenu = function (e) {
           $(".collapse").slideToggle("1000");            
        };
        vm.menuClick = function (e) {
            $rootScope.isShowLoader = true;
            angular.element('.navbar-nav li').removeClass("active");
            angular.element(e.currentTarget).addClass("active"); 
            $timeout(function(){
               $rootScope.isShowLoader = false; 
            },1500);
            if(angular.element(".navbar-toggle").css("display") != "none"){
                vm.showMenu();
            }
        };       
    };

    Home_Ctrl.$inject = ["$scope", '$state', '$rootScope', '$timeout'];
    angular.module('coreModule')
            .controller('Home_Ctrl', Home_Ctrl);
})();