(function () {
    var Offer_Letter_Home_Ctrl = function ($scope, $state, $rootScope, urlConfig, Core_Service,Core_ModalService) {
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
                language: {
                	zeroRecords: 'No data to dispay',
                    searchPlaceholder: 'Search',
                    search: '',
                    infoEmpty: '',
                    infoFiltered:''
                },
                order: [[ 0, "desc" ]],
                aoColumns: [ {
                    	data: 'id',
                    	visible : false
                	},{
                        title: "Candidate Id",
                        data: 'candidate.candidateId',
                    },{
                        title: "Candidate Name",
                        data: 'candidate.firstName',
                    },  {
                        title: "Status",
                        data: 'status',
                    },{
                        title: "Gross CTC",
                        data: 'grossCTC',
                    },{
                        title: "Modified Date",
                        title: "Grade",
                        data: 'grade.grade',
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
                    },{
                        data: 'id',
                        bSortable: false,
                        sClass: "button-column",
                        render: function (data) {
                            $rootScope.showLoader = false;
                            return '<div class="action-buttons">' +
                                    '<span value="' + data + '" class="actions action-edit fa-stack fa-lg pull-left" title="Edit">'+
                                    '<i class="fa fa-pencil-square-o" aria-hidden="true"></i></i></span></div>'
                        }
                    }]
            });
            
            vm.getFormattedDate = function(data){
            	var today = new Date(data);
        	    return today.getDate()+'-'+(today.getMonth()+1)+'-'+today.getFullYear();
            }
            
            $('#offerLetterList tbody').on( 'click', 'tr', function () {
            	
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                }
                else {
                	oTable.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
            } );
         
        })
        Core_Service.calculateSidebarHeight();
         };

         Offer_Letter_Home_Ctrl.$inject = ["$scope", '$state', '$rootScope', 'urlConfig', 'Core_Service','Core_ModalService'];
    angular.module('coreModule')
            .controller('Offer_Letter_Home_Ctrl', Offer_Letter_Home_Ctrl);
})();

