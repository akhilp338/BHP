(function() {    
    var Core_Modal = function($uibModalInstance) {   
        return {
            modalOpen: function(windowClass,url,controller,controllerAs,data) {
                return $uibModalInstance.open({
                    animation: true,
                    templateUrl: url || "",
                    controller: controller || "",
                    windowClass: windowClass || "",
                    controllerAs:controllerAs || "",
                    keyboard: false,
                    backdrop: 'static',
                    resolve:{
                        candidateDetails: function (){
                           return data;
                        }
                    }
                });
            },
            modalDismiss: function($uibModalInstance) {
                $uibModalInstance.close(false);
            },            
            modalClose: function($uibModalInstance) {
                $uibModalInstance.close();
            }
        };        
    };
    Core_Modal.$inject = ['$uibModal'];
    angular.module('app.common')
            .service('Core_Modal', Core_Modal);
})();