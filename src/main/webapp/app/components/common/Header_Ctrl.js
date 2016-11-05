(function () {
    var Header_Ctrl = function ($timeout, $state, $rootScope, Core_Service) {
        var vm = this;
        vm.getUserName = localStorage["userName"] || "Rafique";
        vm.logout = function () {
            Core_Service.ClearCredentials();
            $state.go('login');
        }
        vm.triggerButton = function (event) {
            event.preventDefault();
            localStorage.clear();
            vm.logout();
            angular.element(".link-btn").trigger("click");
        };
        vm.goHome = function(){
            $state.go('coreuser.dashboard');
            $rootScope.isShowLoader = true;
            $timeout(function(){
                $rootScope.isShowLoader = false;
            },1000)
        };
        vm.getUserName= function(data){
        	   vm.sessionCheck();
       	 Core_Service.getCurrentUser(data).then(function (res){
       		$rootScope.currentUser=res;
            },function (error){
                console.log(error)
            }); 
       	
       }
        vm.sessionCheck= function(){
        	 Core_Service.sessionCheck().then(function (res){
        		if(res.data.startsWith("<!DOC")){
                   Core_Service.sweetAlert("No Session!", "Please login again", "success", "login");
                   
          		}
             },function (error){
           	  /*Core_Service.sweetAlert("Session already Exists!", "Please login again", "success", "login");
           	  vm.logout();*/
             }); 
        	
        }

        
    };    
    Header_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'Core_Service'];
    angular.module('coreModule')
            .controller('Header_Ctrl', Header_Ctrl);
})();


