(function () {
    'use strict';
    angular.module('app.constants')
            .constant('urlConfig', {
                "http": "http://",
                "api_endpoint": "/BelhopatBackOffice/app/assets/data/",
                "root_path": "/BelhopatBackOffice/",
                "api_root_path": "/BelhopatBackOffice/api/"
            })
            .constant('CANDIDATE', {
                "fieldMapping": {
                    "id":"ID",
                    "employeeId":"Employee ID",
                    "createdDate":"Created Date",
                    "updatedDate":"Updated Date",
                    "deletedDate":"Deleted Date",
                    "createdBy":"Created By",
                    "updatedBy":"Updated By",
                    "deletedBy":"Deleted By",
                    "deleted":"Deleted",
                    "candidateId":"Candidate Id",
                    "firstName":"First Name",
                    "middleName":"Middle Name", 
                    "lastName":"Last Name",
                    "dob":"D.O.B",
                    "gender":"Gender", 
                    "bloodGroup":"Blood Group",
                    "personalEmail":"Personal Email",
                    "personalContactNo":"Personal Contact No.",
                    "officialEmail":"Official Email", 
                    "officialContactNo":"Official Contact No.",
                    "familyContact1":"Family Contact 1",
                    "familyContact2":"Family Contact 2",
                    "familyEmail":"Family Email",
                    "onsiteContactNo":"Onsite Contact No.", 
                    "currentAddress":"Current Address",
                    "permanentAddress":"Permanent Address",
                    "onsiteAddress":"Onsite Address",
                    "priorExperienceYear":"Prior Experience Years",
                    "priorExperienceMonth":"Prior Experience In Months", 
                    "countryOfOrigin":"Country Of Origin",
                    "countryToVisit":"Country To Visit",
                    "client":"Client",
                    "partner":"Partner",
                    "bankAccount":"Bank Account",
                    "passport":"Passport",
                    "officialDetails":"Official Details",
                    "doj":"D.O.J",
                    "division":"Division",
                    "designation":"Designation",
                    "purpose":"Purpose", 
                    "employmentStatus":"Employment Status",
                    "registrationStatus":"Registration Status",
                    "sourcedBy":"Sourced By",
                    "drivingLicenceNo":" Driving License No.",
                    "forexCardNo":"Forex Card No.",
                    "forexCardAgency":"Forex Card Agency",
                    "panno":"PAN No.",
                    "esino":"ESI No.",
                    "pfno":"PF No."
                }
            })
            .constant('CLIENT', {
                "fieldMapping": {
                    "id":"ID",
                    "clientId":"Client ID",
                    "createdDate":"Created Date",
                    "updatedDate":"Updated Date",
                    "deletedDate":"Deleted Date",
                    "createdBy":"Created By",
                    "updatedBy":"Updated By",
                    "deletedBy":"Deleted By",
                    "deleted":"Deleted",
                    "clientName":"Client Name",
                    "revenue":"Revenue",
                    "clientAddress":"Client Address", 
                    "contactNo":"Contact No",
                    "emailId":"Email Id",
                    "webUrl":"Web URL",
                    "mobNo":"Mobile No",
                    "areaOfWork":"Area Of Work", 
                    "pocCountry":"POC Country",
                    "pocDesignation":"POC Designation",
                    "clientStatus":"Client Status",
                    "businessUnit":"Business Unit",
                }
            
            });

})();
