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
                    "candidateId":"Candidate Id",
                    "name":"Name",
                    "dob":"D.O.B",
                    "gender":{
                        "label":"Gender",
                        "valueKey":"description"
                    }, 
                    "bloodGroup":{
                        "label": "Blood Group",
                        "valueKey": "code"
                    },
                    "personalEmail":"Personal Email",
                    "personalContactNo":{
                        "label":"Personal Contact No.",
                        "valueKey": "number"
                    },
                    "createdDate":"Created Date",
                    "updatedDate":"Updated Date",
                    "createdBy":{
                        "label":"Created By",
                        "valueKey": "email"
                    },
                    "updatedBy":{
                        "label":"Updated By",
                        "valueKey": "email"
                    },  
                    "officialEmail":"Official Email", 
                    "officialContactNo":{
                        "label":"Official Contact No.",
                        "valueKey": "number"
                    },
                    "onsiteContactNo":{
                        "label":"Onsite Contact No.",
                        "valueKey": "number"
                    }, 
                    "currentAddress":{
                        "label":"Current Address"
                    }, 
                    "permanentAddress":{
                        "label":"Permanent Address"
                    },
                    "onsiteAddress":{
                        "label":"Onsite Address"
                    },
                    "priorExperienceYear":"Prior Experience Years",
                    "priorExperienceMonth":"Prior Experience In Months", 
                    "countryOfOrigin":{
                        "label":"Country Of Origin",
                        "valueKey": "description"
                    }, 
                    "countryToVisit":{
                        "label":"Country To Visit",
                        "valueKey": "description"
                    }, 
                    "client":{
                        "label":"Client",
                        "valueKey": "clientName"
                    }, 
                    "partner":"Partner",
                    "passport":{
                        "label":"Passport"
                    },
                    "doj":"D.O.J",
                    "division":{
                        "label": "Division",
                        "valueKey": "description"
                    },
                    "designation":{
                        "label": "Designation",
                        "valueKey": "description"
                    },
                    "sourcedBy":"Sourced By",
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
                    "createdBy":"Created By",
                    "updatedBy":"Updated By",
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
