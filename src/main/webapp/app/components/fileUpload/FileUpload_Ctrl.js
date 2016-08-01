(function () {
    var FileUpload_Ctrl = function ($rootScope, $scope, Upload, $timeout) {
        $rootScope.addPage = true;
        var vm = this;
        vm.bankDocs = {};
        vm.passport = {};
        vm.pancard = {};
        vm.forexcard = {};
        vm.liscence = {};
        
        $scope.$watch('vm.bankDocs.files', function () {
            $scope.upload(vm.bankDocs.files,vm.bankDocs);
        });
         $scope.$watch('vm.passport.files', function () {
            $scope.upload(vm.passport.files,vm.bankDocs);
        });
         $scope.$watch('vm.pancard.files', function () {
            $scope.upload(vm.pancard.files,vm.bankDocs);
        });
         $scope.$watch('vm.forexcard.files', function () {
            $scope.upload(vm.forexcard.files,vm.bankDocs);
        });
         $scope.$watch('vm.liscence.files', function () {
            $scope.upload(vm.liscence.files,vm.bankDocs);
        });
        
        $scope.upload = function (gFiles, modal) {
            if (gFiles && gFiles.length) {
                for (var i = 0; i < gFiles.length; i++) {
                    var file = gFiles[i];
                    if (!file.$error) {
                        Upload.upload({
                            url: 'https://angular-file-upload-cors-srv.appspot.com/upload',
                            data: {
                                file: file
                            }
                        })
                                .then(function (response) {
                                    $timeout(function () {});
                                }, function (response) {
                                    console.log('Error status: ' + response.status);
                                }, function (evt) {
                                    var progressPercentage = parseInt(100.0 *
                                            evt.loaded / evt.total);
                                    modal.progress = progressPercentage + '% ';
                                });
                    }
                }
            }
        }
    };
    FileUpload_Ctrl.$inject = ['$rootScope', '$scope', 'Upload', '$timeout'];
    angular.module('coreModule')
            .controller('FileUpload_Ctrl', FileUpload_Ctrl);
})();

