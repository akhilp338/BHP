<div class="form-horizontal reim-form" name="reim-form">
    <div class="top-main-head pull-left width-100">
        <h4 class="pull-left">Fill Reimbursement Form</h4>	
        <button ng-click="vm.back()"
                class="logout-button back pull-right add-button" title="Back Button">
            <span class="fa-stack fa-lg pull-left"><i
                    class="fa fa-arrow-left" aria-hidden="true"></i></span>
            <span class="text">Back</span>              
        </button>
    </div>
    
    <div class="row">        
        <div class="data-outer col-md-12 col-lg-12 col-sm-12 col-xs-12">                        
            <div class="data-main-container">
                <header class="data-main-container-header">Basic Information</header>

                <div
                    class="control-group col-lg-4 col-md-4 col-sm-6 col-xs-6 pull-left">
                    <label class="control-label" for="empno">Employee Number</label>
                    <div class="controls">
                        <input type="text" name="empno" id="empno"
                               ng-model="vm.reim.empno"
                               class="form-input width-100" placeholder="Employee Number"
                               readonly/>
                    </div>
                </div>
                <div
                    class="control-group col-lg-4 col-md-4 col-sm-6 col-xs-6 pull-left">
                    <label class="control-label" for="empname">Employee Name</label>
                    <div class="controls">
                        <input type="text" name="empname" id="empname"
                               ng-model="vm.reim.empname" class="form-input width-100"
                               placeholder="Employee Name"
                               readonly />
                    </div>
                </div>

                <div class="control-group col-lg-4 col-md-4 col-sm-6 col-xs-6 pull-left">
                    <label class="control-label" for="division">Division/BU</label>
                    <div class="controls">
                        <input type="text" name="division" id="division"
                               ng-model="vm.reim.division"
                               class="form-input width-100"
                               placeholder="Division/BU"
                               readonly/>
                    </div>
                </div>
                <div
                    class="control-group col-lg-4 col-md-4 col-sm-6 col-xs-6 pull-left">
                    <label class="control-label" for="currDate">Current Date</label>
                    <div class="controls">
                        <input type="text" name="currDate" id="currDate"
                               ng-model="vm.reim.currDate" class="form-input width-100"
                               placeholder="Current Date"
                               readonly/>
                    </div>
                </div>
                <div
                    class="control-group col-lg-4 col-md-4 col-sm-6 col-xs-6 pull-left">
                    <label class="control-label" for="webUrl">Reporting manager</label>
                    <div class="controls">
                        <input type="text" name="manager" id="manager"
                               ng-model="vm.reim.manager" class="form-input width-100"
                               placeholder="Reporting manager"
                               readonly/>
                    </div>
                </div>
            </div>
        </div>
    </div>        
 
    <div>
        <div class="bottom-button-liner">        
            <button ng-click="vm.addToTable($event)"
                    class="logout-button pull-right add-button confirm-btn" title="Confirm and Register">
                <span class="fa-stack fa-lg pull-left"><i
                        class="fa fa-plus" aria-hidden="true"></i></span>
                <span class="text">Add data to table</span>              
            </button>
            
            <button ng-click="vm.reimburse()" id="reimburse-button"
                class="logout-button pull-right add-button confirm-btn" title="Confirm and Register">
            <span class="fa-stack fa-lg pull-left"><i
                    class="fa fa-check" aria-hidden="true"></i></span>
            <span class="text">Reimburse</span>              
        </button>
        </div>
        <table id="reim-table" class="display cell-border" cellspacing="0" width="100%">
            <thead>                    
            </thead>
            <tbody>

                <tr class="input-row">    
            <!--<form name="reim-addForm" id="reim-addForm">-->
                <td><input validation="required:alt=Required field"type="text"  id="reim-date" ng-model="vm.reim.reimSet.date" id="reim-date" name="reim-date" ></td>
                <td><input validation="required:alt=Required field"type="text"  id="reim-description" ng-model="vm.reim.reimSet.description" id="reim-desc" name="reim-desc" ></td>
                <td><select name="currency"  id="reim-currency"
                                    class="form-input width-100"
                                    >
                                <option value="">--SELECT--</option>
                                <option ng-repeat="currency in vm.currency" value="{{currency.id}}">{{currency.decription}}</option>
                            </select></td>
                <td><input id="reim-amount" validation="required:alt=Required field" ng-model="vm.reim.reimSet.amount" type="text" id="reim-amount" name="reim-amount" ></td>
                <td><input id="reim-remarks" validation="required:alt=Required field" ng-model="vm.reim.reimSet.remarks" type="text" id="reim-remarks" name="reim-remarks" ></td>
                <td></td>
            <!--</form>-->
            </tr>

            </tbody>
        </table>            
    </div>
</div>




