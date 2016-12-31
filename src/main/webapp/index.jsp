<!DOCTYPE html>
<html ng-app="coreModule" class="theme4" ng-class="{'dashboard-page':isDashBoard}">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
        <link rel="shortcut icon" href="/BelhopatBackOffice/app/assets/images/B-Icon.ico">
        <title>Belhopat Global Services Pvt Ltd</title>        
        <link rel="stylesheet" ng-href="/BelhopatBackOffice/app/assets/libs/css/bootstrap.min.css">
        <link rel="stylesheet" ng-href="/BelhopatBackOffice/app/assets/libs/css/font-awesome.min.css">
        <link rel="stylesheet" ng-href="/BelhopatBackOffice/app/assets/libs/css/awesome-checkbox.css">
        <link rel="stylesheet" ng-href="/BelhopatBackOffice/app/assets/libs/css/angular-material.css">
        <link rel="stylesheet" ng-href="/BelhopatBackOffice/app/assets/libs/css/sweetalert.css">
        <link rel="stylesheet" ng-href="/BelhopatBackOffice/app/assets/libs/css/fullcalendar.css">
        <link rel="stylesheet" ng-href="/BelhopatBackOffice/app/assets/libs/css/nv.d3.css">
        <link href="/BelhopatBackOffice/app/assets/libs/css/sb-admin.css" rel="stylesheet">
        <link href='/BelhopatBackOffice/app/assets/libs/css/angular-carousel.css' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" ng-href="/BelhopatBackOffice/app/assets/libs/css/jquery.dataTables.min.css">
        <link href='/BelhopatBackOffice/app/assets/libs/css/jquery.datepick.css' rel='stylesheet' type='text/css'> 
        <link href="/BelhopatBackOffice/app/assets/libs/css/nvd3/axis.css" rel="stylesheet">
        <link href="/BelhopatBackOffice/app/assets/libs/css/nvd3/bars.css" rel="stylesheet">
        <link href="/BelhopatBackOffice/app/assets/libs/css/nvd3/boxplot.css" rel="stylesheet">
        <link href="/BelhopatBackOffice/app/assets/libs/css/nvd3/bullet.css" rel="stylesheet">
        <link href="/BelhopatBackOffice/app/assets/libs/css/nvd3/candlestick.css" rel="stylesheet">
        <link href="/BelhopatBackOffice/app/assets/libs/css/nvd3/forceDirectedGraph.css" rel="stylesheet">
        <link href="/BelhopatBackOffice/app/assets/libs/css/nvd3/pie.css" rel="stylesheet">
        <link href="/BelhopatBackOffice/app/assets/libs/css/nvd3/scatter.css" rel="stylesheet">
        <link href="/BelhopatBackOffice/app/assets/libs/css/nvd3/tooltip.css" rel="stylesheet">
        <link rel="stylesheet" ng-href="/BelhopatBackOffice/app/assets/styles/sidebar.css">
        <link rel="stylesheet" ng-href="/BelhopatBackOffice/app/assets/styles/style1.css">
        <!-- Libraries -->
        <script src="/BelhopatBackOffice/app/assets/libs/js/jquery-2.2.3.min.js"></script>
        <script src="/BelhopatBackOffice/app/assets/libs/js/moment.js"></script> 
        <script src="/BelhopatBackOffice/app/assets/libs/js/fullcalendar.js"></script>
        <script src="/BelhopatBackOffice/app/assets/libs/js/gcal.js"></script>
        <script src="/BelhopatBackOffice/app/components/common/sidebar.js"></script>
        <script src="/BelhopatBackOffice/app/assets/libs/js/angular.min.js"></script>
        <script src="/BelhopatBackOffice/app/assets/libs/js/angular-ui-router.js"></script>
        <script src="/BelhopatBackOffice/app/assets/libs/js/angular-cookies.js"></script>
        <script src="/BelhopatBackOffice/app/assets/libs/js/ngstorage.js"></script>
        <script src="/BelhopatBackOffice/app/assets/libs/js/jquery.dataTables.min.js"></script>
        <script src="/BelhopatBackOffice/app/assets/libs/js/jquery-ui.js"></script>
        <script src="/BelhopatBackOffice/app/assets/libs/js/bootstrap-filestyle.min.js"></script>
        <script src="/BelhopatBackOffice/app/assets/libs/js/jquery.plugin.js"></script>
        <script src="/BelhopatBackOffice/app/assets/libs/js/dateTimePicker.js"></script>
        <script src="/BelhopatBackOffice/app/assets/libs/js/calendar.js"></script>
        <script src="/BelhopatBackOffice/app/assets/libs/js/underscore.js"></script>
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
        <script src="/BelhopatBackOffice/app/assets/libs/js/d3.v3.min.js" charset="utf-8"></script>
        <script src="/BelhopatBackOffice/app/assets/libs/js/nv.d3.js"></script>
        <script src="/BelhopatBackOffice/app/assets/libs/js/angular-nvd3.js"></script>
        <script type="text/javascript" src="/BelhopatBackOffice/app/assets/libs/js/spin.min.js"></script>
        <script src="/BelhopatBackOffice/app/assets/libs/js/angular-spinner.min.js"></script>
        <script src="/BelhopatBackOffice/app/assets/libs/js/angular-loading-spinner.js"></script>
        <script src="/BelhopatBackOffice/app/assets/libs/js/waterbubble.js"></script>
        <script src="/BelhopatBackOffice/app/assets/libs/js/ngUpload.js"></script>
        <script src="/BelhopatBackOffice/app/assets/libs/js/ngUploadShim.js"></script>
        <script src="/BelhopatBackOffice/app/assets/libs/js/angular-touch.min.js"></script>
         <script src="/BelhopatBackOffice/app/assets/libs/js/ui-bootstrap-1.3.3.js"></script>
        <script src="/BelhopatBackOffice/app/assets/libs/js/ui-bootstrap-tpls-1.3.3.min.js"></script>
        <script src="/BelhopatBackOffice/app/assets/libs/js/angular-carousel.js"></script>
        <script src="/BelhopatBackOffice/app/assets/libs/js/scroll.js"></script>

        <!-- AngularJS custom codes -->

        <script src="/BelhopatBackOffice/app/app.module.js"></script>
        <script src="/BelhopatBackOffice/app/app.route.js"></script>
        <script src="/BelhopatBackOffice/app/shared/encode-util.js"></script>
        <script src="/BelhopatBackOffice/app/run.js"></script>
        <script src="/BelhopatBackOffice/app/shared/appConstants.js"></script>
        <script src="/BelhopatBackOffice/app/directives/digitDirective.js"></script>
        <script src="/BelhopatBackOffice/app/shared/HttpRequest.js"></script>
        <script src="/BelhopatBackOffice/app/shared/CoreService.js"></script>
        <script src="/BelhopatBackOffice/app/shared/Core_Modal.js"></script>
        <script src="/BelhopatBackOffice/app/shared/CommonModalService.js"></script>
        <script src="/BelhopatBackOffice/app/components/common/directives.js"></script>
        <script src="/BelhopatBackOffice/app/components/login/LoginCtrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/login/ForgotPassword_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/login/ChangePassword_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/common/Header_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/home/Home_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/dashboard/Dash_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/dashboard/DashModal_Ctrl.js"></script> 
        <script src="/BelhopatBackOffice/app/components/candidate/Candidate_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/candidate/AddCandidate_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/candidate/ViewCandidate_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/candidate/DeleteCandidate_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/employee/Employee_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/employee/AddEmployee_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/employee/AddEmployee_Ctrl_Final.js"></script>
        <script src="/BelhopatBackOffice/app/components/client/Client_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/client/AddClient_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/event/AddEvent_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/holiday/Holiday_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/opportunity/Opp_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/candidate/ViewCandidate_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/event/EventManagement_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/offerletter/Offerletter_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/employee/ViewEmployee_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/employee/GenerateCredentials_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/offerletter/Offer_Letter_Home_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/fileUpload/FileUpload_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/client/ViewClient_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/consultant/AddConsultant_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/consultant/Consultant_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/consultant/ViewConsultant_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/vendor/AddVendor_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/vendor/Vendor_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/vendor/ViewVendor_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/bankacc/AddbankAcc_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/bankacc/BankAcc_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/vendor/ViewVendor_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/Attendance/AttendanceMgmtCtrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/reimbursement/Reimbursement_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/reimbursement/Reim_Review_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/PurchaseOrder/PO_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/PurchaseOrder/AddPO_Ctrl.js"></script>
        <script src="/BelhopatBackOffice/app/components/PurchaseOrder/ViewPO_Ctrl.js"></script>
        <style>
            [ng:cloak], [ng-cloak], [data-ng-cloak], [x-ng-cloak], .ng-cloak, .x-ng-cloak {
                display: none !important;
            }
        </style>
    </head>

    <body ng-class="{'login-page':isLogin,'loader-visible': isShowLoader,'inner-page':addPage, 'dashboard-page':isDashBoard, 'attendance-page':isAttendance}" ng-cloak>
        <div ng-if="isShowLoader || isDataLoader" class="loader-container"></div>
        <div id="errorUser">${error}</div>
        <div id="successUser">${user}</div>     
        <div class="response-loader" ng-show="!showLoader"></div>
        <img src="/BelhopatBackOffice/app/assets/images/loader-img.gif" class="loader-img loader-language" ng-if="languageSwitching" alt="loader">
        <header ui-view="header" class="header-div"></header>
        <span us-spinner="{radius:30, width:8, length: 16}"></span>
        <div ui-view="sidebar" class="page-side-bar"></div>
        <div ui-view="content" class="main">
        </div>
        <footer ui-view="footer" class="footer-div"></footer>
        <script type="text/javascript">
        </script>
</html>
