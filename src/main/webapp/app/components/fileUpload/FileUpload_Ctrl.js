(function () {
    var FileUpload_Ctrl = function ($scope, $state,$rootScope, FileUploader, $scope, Core_Service) {
        var vm = this;
    	vm.redirect = function (stateName){ 
            var state;
            switch(stateName){
                case 'candidate':{
                   state = 'coreuser.candidate';
                }
                break
                case 'employee':{
                   state = 'coreuser.employee'; 
                }
                break
                case 'reimburse':{
                   state = 'coreuser.reimbursement';     
                }
                break
            default:
            }
            $state.go(state);
        };    	
        var bankDetailsUploader = $scope.bankDetailsUploader = new FileUploader({
            url: 'upload.php'
        });
        var passportUploader =  $scope.passportUploader = new FileUploader({
            url: 'upload.php'
        });
        var licenceUploader = $scope.licenceUploader = new FileUploader({
            url: 'upload.php'
        });
        var panUploader =  $scope.panUploader = new FileUploader({
            url: 'upload.php'
        });
        var forexUploader =  $scope.forexUploader = new FileUploader({
            url: 'upload.php'
        });
        var employeeDocsUploader =  $scope.employeeDocsUploader = new FileUploader({
            url: 'upload.php'
        });
        var reimDocsUploader =  $scope.employeeDocsUploader = new FileUploader({
            url: '/api/attendance/uploadAttendanceExcel'
        });
        uploadManager(bankDetailsUploader);
        uploadManager(passportUploader);
        uploadManager(licenceUploader);
        uploadManager(panUploader);
        uploadManager(forexUploader);
        uploadManager(employeeDocsUploader);
        uploadManager(reimDocsUploader);
        $scope.oneAtATime = true;
        $scope.showbtn = false;
        $scope.status = {
            isCustomHeaderOpen: false,
            isFirstOpen: true,
            isFirstDisabled: false
        };
        $rootScope.addPage = true;
        // FILTERS

    function uploadManager(uploader){
        uploader.filters.push({
            name: 'imageFilter',
            fn: function (item /*{File|FileLikeObject}*/, options) {
                return this.queue.length < 10;
            }
        });

        // CALLBACKS

        uploader.onWhenAddingFileFailed = function (item /*{File|FileLikeObject}*/, filter, options) {
            console.info('onWhenAddingFileFailed', item, filter, options);
        };
        uploader.onAfterAddingFile = function (fileItem) {
            console.info('onAfterAddingFile', fileItem);
        };
        uploader.onAfterAddingAll = function (addedFileItems) {
            console.info('onAfterAddingAll', addedFileItems);
        };
        uploader.onBeforeUploadItem = function (item) {
            console.info('onBeforeUploadItem', item);
        };
        uploader.onProgressItem = function (fileItem, progress) {
            console.info('onProgressItem', fileItem, progress);
        };
        uploader.onProgressAll = function (progress) {
            console.info('onProgressAll', progress);
        };
        uploader.onSuccessItem = function (fileItem, response, status, headers) {
            console.info('onSuccessItem', fileItem, response, status, headers);
        };
        uploader.onErrorItem = function (fileItem, response, status, headers) {
            console.info('onErrorItem', fileItem, response, status, headers);
        };
        uploader.onCancelItem = function (fileItem, response, status, headers) {
            console.info('onCancelItem', fileItem, response, status, headers);
        };
        uploader.onCompleteItem = function (fileItem, response, status, headers) {
            console.info('onCompleteItem', fileItem, response, status, headers);
        };
        uploader.onCompleteAll = function () {
            console.info('onCompleteAll');
        };

        console.info('uploader', uploader);
    }
    
      vm.uploadDocs = function(){
          var state = $rootScope.isEmpDocs ? "coreuser.employee" : "coreuser.candidate";
          Core_Service.sweetAlert("Done!", "Docs uploaded successfully", "success", state);
      };  
    };
    FileUpload_Ctrl.$inject = ["$scope", '$state','$rootScope', 'FileUploader', '$scope', 'Core_Service'];
    angular.module('coreModule')
            .controller('FileUpload_Ctrl', FileUpload_Ctrl);
})();

