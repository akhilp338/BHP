(function () {
    var Offer_Letter_Home_Ctrl = function ($scope, $state, $rootScope, urlConfig, Core_Service,Core_ModalService,Core_HttpRequest) {
        var vm = this;
        $rootScope.active = 'offerletter';
       /* vm.addOfferLetter = function(){
        	$state.go("coreuser.employee.add");
        }*/
        
        
        vm.processOfferLetter = function () {
            var index = $("#candidatesList tbody tr.selected").index();
            var data = [];
            data.id=1;
              $state.go("coreuser.offerletter");  
        };
        angular.element(document).ready(function () {
            var oTable = angular.element('#offerLetterList').DataTable({
                ajax: urlConfig.http + window.location.host + urlConfig.api_root_path + "candidate/getOfferLetters",
                serverSide: true,
                bDestroy: true,
                processing: true,
                responsive: true,
                sScrollX: '100%',                
                fnDrawCallback: function (settings, ajax) {
                    Core_Service.calculateSidebarHeight();
                },
                oLanguage: {
                    "sEmptyTable":     "My Custom Message On Empty Table"
                },
                language: {
                    zeroRecords: 'No data to display',
                    infoEmpty: "No data to display",
                    emptyTable: "No data to display",
                    searchPlaceholder: 'Search',
                    search: '',
                    infoFiltered:''
                },
                order: [[ 0, "desc" ]],
                aoColumns: [ {
                    	data: 'id',
                    	visible : false
                	},{
                        title: "Candidate Id",
                        data: 'candidate.candidateId',
                        render: function (data) {
                        	return data == null? "":data;
                        }
                    },{
                        title: "Candidate Name",
                        data: 'candidate.firstName',
                        render: function (data) {
                        	return data == null? "":data;
                        }
                    }, {
                        title: "Gross CTC",
                        data: 'grossCTC',
                        render: function (data) {
                        	return data == null? "":data;
                        }
                    },{
                        title: "Grade",
                        data: 'grade.grade',
                        render: function (data) {
                        	return data == null? "":data;
                        }
                    },{
                        title: "Created Date",
                        data: 'createdDate',
                        "render": function(data) {
                            return vm.getFormattedDate(data)
                        }
                    },{
                    	title: "Modified Date",
                        data: 'updatedDate',
                        "render": function(data) {
                            return vm.getFormattedDate(data)
                        }
                    },{
                        title: "Approved By",
                        data: 'updatedBy.username',
                        render: function (data) {
                        	return data == null? "":data;
                        }
                    }, {
                        title: "Status",
                        data: 'status',
                        render: function (data) {
                        	return data == null? "":data;
                        }
                    },{
                        data: 'id',
                        bSortable: false,
                        sClass: "button-column",
                        render: function (data) {
                            return '<div class="action-buttons">' +
                                    '<span value="' + data + '" class="actions action-download fa-stack fa-lg pull-left" title="Download">'+
                                    '<i class="fa fa-cloud-download" aria-hidden="true"></i></i></span></div>'
                        }
                    }]
            });
            
            $('#offerLetterList').on('click', '.action-download', function () {
            	var empSalId = this.getAttribute('value');
            	var url = "api/previewOfferLetter?empSalId="+empSalId;
           	    url = Core_HttpRequest.getUrl(url);
           	    var win = window.open(url,"_blank");
           	    win.focus();
            });
            
            
            
            vm.getFormattedDate = function(data){
            	var today = new Date(data);
        	    return today.getDate()+'-'+(today.getMonth()+1)+'-'+today.getFullYear();
            }
            
         
        })
        Core_Service.calculateSidebarHeight();
         };

         Offer_Letter_Home_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'urlConfig', 'Core_Service','Core_ModalService','Core_HttpRequest'];
    angular.module('coreModule')
            .controller('Offer_Letter_Home_Ctrl', Offer_Letter_Home_Ctrl);
})();

