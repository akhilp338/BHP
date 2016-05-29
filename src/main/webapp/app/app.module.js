(function () {
    'use strict';
    angular.module('app.common', []);
    angular.module('app.constants', []);
    window.app = angular.module('coreModule', [
        'ui.router',
        'ngAnimate',
        'ui.bootstrap',
        'app.constants',
        'app.common',
        'pascalprecht.translate',
        'ghiscoding.validation',
        'oitozero.ngSweetAlert',
        'ngCookies',
        'ngStorage',
        'ngAria',
        'ngMaterial',
        'ngIdle'
    ]);
    window.app.config(['$locationProvider','KeepaliveProvider', 'IdleProvider', function ($locationProvider,KeepaliveProvider, IdleProvider) {
            $locationProvider.html5Mode({
                enabled: true,
                requireBase: false
            });
            IdleProvider.idle(300);
            IdleProvider.timeout(300);
            KeepaliveProvider.interval(300);
        }]);

})();
