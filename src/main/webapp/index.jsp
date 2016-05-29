<!DOCTYPE html>
<html ng-app="coreModule" class="theme4">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<link rel="shortcut icon" href="/BelhopatBackOffice/app/assets/images/B-Icon.ico">
<title>Belhopat Global Services Pvt Ltd</title>
<link rel="stylesheet" ng-href="/BelhopatBackOffice/app/assets/libs/css/bootstrap.min.css">
<link rel="stylesheet" ng-href="/BelhopatBackOffice/app/assets/libs/css/font-awesome.min.css">
<link rel="stylesheet" ng-href="/BelhopatBackOffice/app/assets/libs/css/awesome-checkbox.css">
<link rel="stylesheet" ng-href="/BelhopatBackOffice/app/assets/libs/css/angular-material.css">
<link rel="stylesheet" ng-href="/BelhopatBackOffice/app/assets/libs/css/sweetalert.css">
<link rel="stylesheet" ng-href="/BelhopatBackOffice/app/assets/styles/sidebar.css">
<!--<link rel="stylesheet" ng-href="/BelhopatBackOffice/app/assets/styles/style.css">-->
<link rel="stylesheet" ng-href="/BelhopatBackOffice/app/assets/styles/style1.css">
<link rel="stylesheet" ng-href="/BelhopatBackOffice/app/assets/libs/css/jquery.dataTables.min.css">
<link rel="stylesheet" ng-href="/BealhopatBackOffice/app/assets/libs/css/jquery.datepick.css">
	<!-- Libraries -->
<script src="/BelhopatBackOffice/app/assets/libs/js/jquery-2.2.3.min.js"></script>
<script src="/BelhopatBackOffice/app/components/common/sidebar.js"></script>
<script src="/BelhopatBackOffice/app/assets/libs/js/angular.min.js"></script>
<script src="/BelhopatBackOffice/app/assets/libs/js/angular-ui-router.js"></script>
<script src="/BelhopatBackOffice/app/assets/libs/js/angular-cookies.js"></script>
<script src="/BelhopatBackOffice/app/assets/libs/js/ngstorage.js"></script>
<script src="/BelhopatBackOffice/app/assets/libs/js/jquery.dataTables.min.js"></script>
<script src="/BelhopatBackOffice/app/assets/libs/js/jquery-ui.js"></script>
<script src="/BelhopatBackOffice/app/assets/libs/js/jquery.plugin.js"></script>
<script src="/BelhopatBackOffice/app/assets/libs/js/jquery.datepick.js"></script>
<!-- <script src="/BelhopatBackOffice/app/assets/libs/js/angular.min.js"></script> -->
<script src="/BelhopatBackOffice/app/assets/libs/js/underscore.js"></script>
<script src="/BelhopatBackOffice/app/assets/libs/js/ui-bootstrap-tpls-1.3.1.min.js"></script>
<script src="/BelhopatBackOffice/app/assets/libs/js/sweetalert.js"></script>
<script src="/BelhopatBackOffice/app/assets/libs/js/sweet-alert.js"></script>
<script src="/BelhopatBackOffice/app/assets/libs/js/angular-animate.min.js"></script>
<script src="/BelhopatBackOffice/app/assets/libs/js/angular-aria-min.js"></script>
<script src="/BelhopatBackOffice/app/assets/libs/js/angular-messages.js"></script>
<script src="/BelhopatBackOffice/app/assets/libs/js/angular-material.js"></script>
<script src="/BelhopatBackOffice/app/assets/libs/js/angular-ui-router.js"></script>
<script src="/BelhopatBackOffice/app/assets/libs/js/angular-translate.min.js"></script>
<script src="/BelhopatBackOffice/app/assets/libs/js/angular-translate-loader-static-files.min.js"></script>
<script src="/BelhopatBackOffice/app/assets/libs/js/angular-validation.min.js"></script>
<script src="/BelhopatBackOffice/app/assets/libs/js/jquery.spring-friendly.min.js"></script>
<script src="/BelhopatBackOffice/app/assets/libs/js/angular-idle.js"></script>

<!-- AngularJS custom codes -->

<script src="/BelhopatBackOffice/app/app.module.js"></script>
<script src="/BelhopatBackOffice/app/app.route.js"></script>
<script src="/BelhopatBackOffice/app/shared/encode-util.js"></script>
<script src="/BelhopatBackOffice/app/run.js"></script>
<script src="/BelhopatBackOffice/app/shared/appConstants.js"></script>
<script src="/BelhopatBackOffice/app/shared/HttpRequest.js"></script>
<script src="/BelhopatBackOffice/app/shared/CoreService.js"></script>
<script src="/BelhopatBackOffice/app/shared/Core_Modal.js"></script>
<script src="/BelhopatBackOffice/app/shared/CommonModalService.js"></script>
<script src="/BelhopatBackOffice/app/components/common/directives.js"></script>
<script src="/BelhopatBackOffice/app/components/login/LoginCtrl.js"></script>
<script src="/BelhopatBackOffice/app/components/login/Forgotpassword_Ctrl.js"></script>
<script src="/BelhopatBackOffice/app/components/common/Header_Ctrl.js"></script>
<script src="/BelhopatBackOffice/app/components/home/Home_Ctrl.js"></script>
<script src="/BelhopatBackOffice/app/components/dashboard/Dash_Ctrl.js"></script>
<script src="/BelhopatBackOffice/app/components/candidate/Candidate_Ctrl.js"></script>
<script src="/BelhopatBackOffice/app/components/candidate/AddCandidate_Ctrl.js"></script>
<script src="/BelhopatBackOffice/app/components/candidate/ViewCandidate_Ctrl.js"></script>
<script src="/BelhopatBackOffice/app/components/candidate/DeleteCandidate_Ctrl.js"></script>
<script src="/BelhopatBackOffice/app/components/employee/Employee_Ctrl.js"></script>
<script src="/BelhopatBackOffice/app/components/employee/AddEmployee_Ctrl.js"></script>
<script src="/BelhopatBackOffice/app/components/employee/AddEmployee_Ctrl_Final.js"></script>
<script src="/BelhopatBackOffice/app/components/client/Client_Ctrl.js"></script>
<script src="/BelhopatBackOffice/app/components/client/AddClient_Ctrl.js"></script>
<script src="/BelhopatBackOffice/app/components/holiday/Holiday_Ctrl.js"></script>
<script src="/BelhopatBackOffice/app/components/opportunity/Opp_Ctrl.js"></script>
<script src="/BelhopatBackOffice/app/components/candidate/ViewCandidate_Ctrl.js"></script>


<style>
[ng:cloak], [ng-cloak], [data-ng-cloak], [x-ng-cloak], .ng-cloak, .x-ng-cloak {
display: none !important;
}
</style>
</head>

<body ng-class="{'login-page':isLogin,'loader-visible': showLoader,'inner-page':addPage}" ng-cloak>
    <div id="errorUser">${error}</div>
     <div id="successUser">${user}</div>     
<div class="response-loader" ng-class="{show: showLoader}"></div>
<img src="/BelhopatBackOffice/app/assets/images/loader-img.gif" class="loader-img loader-language" ng-if="languageSwitching" alt="loader">
    <header ui-view="header" class="header-div"></header>
    <div ui-view="sidebar" class="page-side-bar"></div>
    <div ui-view="content" class="main"></div>
    <footer ui-view="footer" class="footer-div"></footer>
</html>
