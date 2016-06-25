(function () {
    'use strict';
    angular.module('app.common', []);
    angular.module('app.constants', []);
    window.app = angular.module('coreModule', [
        'ui.router',
        'ui.calendar',
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
        'ngIdle',
        'nvd3',
        'angular-carousel',
        'bb.scrollWhen'
    ]);
    window.app.config(['$locationProvider','KeepaliveProvider', 'IdleProvider', function ($locationProvider,KeepaliveProvider, IdleProvider) {
            $locationProvider.html5Mode({
                enabled: true,
                requireBase: false
            });
            IdleProvider.idle(2400);
            IdleProvider.timeout(2400);
            KeepaliveProvider.interval(2400);
        }]);

})();
