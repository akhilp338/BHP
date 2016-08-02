(function () {
    var FileUpload_Ctrl = function ($rootScope, FileUploader, $scope, $timeout) {
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
        uploadManager(bankDetailsUploader);
        uploadManager(passportUploader);
        uploadManager(licenceUploader);
        uploadManager(panUploader);
        uploadManager(forexUploader);
        uploadManager(employeeDocsUploader);
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
                var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
                return '|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1;
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
    
        
    }
    FileUpload_Ctrl.$inject = ['$rootScope', 'FileUploader', '$scope', '$timeout'];
    angular.module('coreModule')
            .controller('FileUpload_Ctrl', FileUpload_Ctrl);
})();

