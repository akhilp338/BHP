(function () {
    var Core_ModalService = function (Core_Modal) {        
        var service = this;
        service.openForgotModal = function (data) {
            return Core_Modal.modalOpen('forgot-modal', '/BelhopatBackOffice/app/components/login/forgotPassword.html', 'ForgotPassword_Ctrl', 'vm', data);
        }; 
        service.openViewCandidateModal = function (data) {
            return Core_Modal.modalOpen('view-modal', '/BelhopatBackOffice/app/components/candidate/viewCandidate.html', 'ViewCandidate_Ctrl', 'vm', data);
        };
        service.opendeleteCandidateModal = function (data) {
            return Core_Modal.modalOpen('forgot-modal', '/BelhopatBackOffice/app/components/candidate/deleteCandidate.html', 'DeleteCandidate_Ctrl', 'vm', data);
        };
        service.openViewEmployeeModal = function (data) {
            return Core_Modal.modalOpen('view-modal', '/BelhopatBackOffice/app/components/employee/viewEmployee.html', 'ViewEmployee_Ctrl', 'vm', data);
        };
        service.openAddEventModal = function (data) {
            return Core_Modal.modalOpen('event-modal', '/BelhopatBackOffice/app/components/event/addEvent.html', 'AddEvent_Ctrl', 'vm', data);
        };
        service.openGenerateCredentialsModal = function (data) {
            return Core_Modal.modalOpen('generate-credentials-modal', '/BelhopatBackOffice/app/components/employee/generateCredentials.html', 'GenerateCredentials_Ctrl', 'vm', data);
        };
        service.openViewClientModal = function (data) {
            return Core_Modal.modalOpen('view-modal', '/BelhopatBackOffice/app/components/client/viewClient.html', 'ViewClient_Ctrl', 'vm', data);
        };
        service.openChangePassword = function (data) {
            return Core_Modal.modalOpen('forgot-modal', '/BelhopatBackOffice/app/components/login/changePasswordModaL.html', 'ChangePasswordModal_Ctrl', 'vm', data);
        };
        service.openTaskDetails = function (data) {
            return Core_Modal.modalOpen('task-modal', '/BelhopatBackOffice/app/components/dashboard/taskDialogue.html', 'DashModal_Ctrl', 'vm', data);
        };
        service.openViewConsultantModal = function (data) {
            return Core_Modal.modalOpen('view-modal', '/BelhopatBackOffice/app/components/consultant/viewConsultant.html', 'ViewConsultant_Ctrl', 'vm', data);
        };
    };
    angular.module('app.common')
        .service('Core_ModalService', Core_ModalService);
})();
