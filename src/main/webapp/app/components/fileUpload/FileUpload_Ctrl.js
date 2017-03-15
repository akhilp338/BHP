(function () {
    var FileUpload_Ctrl = function ($scope, $state, $stateParams, $rootScope, FileUploader, $scope, Core_Service) {
        var vm = this;
        
    	vm.redirect = function( stateName ){
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
                break;
                case 'consultant':{
                    state = 'coreuser.consultant';     
                 }
                break
            default:
            }
            $state.go(state);
        }; 
        vm.getFileFromS3 = function(id){
            Core_Service.getS3File(id).then(function(res){
                        console.log(res);
                },function(err){
                        console.log(err);
            });
        }   	
        var bankDetailsUploader = $scope.bankDetailsUploader = new FileUploader({
            url: '/BelhopatBackOffice/api/candidate/uploadFile?type="bank"&userId='+$stateParams.candidateUploadId, 
            type:'post',
            success:function(resp){ 
                    console.log(resp);
            } 
        });
        var passportUploader =  $scope.passportUploader = new FileUploader({
            url: '/BelhopatBackOffice/api/candidate/uploadFile?type="passport"&userId='+$stateParams.candidateUploadId,
            type:'post',
            success:function(resp){ console.log(resp); } 
        });
        var licenceUploader = $scope.licenceUploader = new FileUploader({
            url: '/BelhopatBackOffice/api/candidate/uploadFile?type="licence"&userId='+$stateParams.candidateUploadId,
            type:'post',
            success:function(resp){ console.log(resp); } 
        });
        var panUploader =  $scope.panUploader = new FileUploader({
            url: '/BelhopatBackOffice/api/candidate/uploadFile?type="pan"&userId='+$stateParams.candidateUploadId,
            type:'post',
            success:function(resp){ console.log(resp); } 
        });
        var forexUploader =  $scope.forexUploader = new FileUploader({
            url: '/BelhopatBackOffice/api/candidate/uploadFile?type="forex"&userId='+$stateParams.candidateUploadId,
            type:'post',
            success:function(resp){ console.log(resp); } 
        });
        var employeeDocsUploader =  $scope.employeeDocsUploader = new FileUploader({
            url: '/BelhopatBackOffice/api/candidate/uploadFile?type="emp"&userId='+$stateParams.employeeUploadId,
            type:'post',
            success:function(resp){ console.log(resp); } 
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

        Core_Service.getCandidateFiles($stateParams.candidateUploadId).then(function(res){
                            vm.s3files = res;                            
                        },function(err){
                            console.log(err);
                    });

        
        
    }
    
      vm.uploadDocs = function(){        
          var state = "coreuser."+$rootScope.active;
          Core_Service.sweetAlert("Done!", "Docs uploaded successfully", "success", state);
      };  
    };
    FileUpload_Ctrl.$inject = ["$scope", '$state', '$stateParams', '$rootScope', 'FileUploader', '$scope', 'Core_Service'];
    angular.module('coreModule')
            .controller('FileUpload_Ctrl', FileUpload_Ctrl);
})();

