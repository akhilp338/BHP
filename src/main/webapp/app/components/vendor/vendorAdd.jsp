<div class="form-horizontal vendor-add" name="vendor_reg_form">
    <div class="top-main-head pull-left width-100">
        <h4 class="pull-left">Fill Vendor Details</h4>	
        <button ng-click="vm.back()"
			class="logout-button pull-right add-button" title="Back Button">
			<span class="fa-stack fa-lg pull-left"><i
				class="fa fa-arrow-left" aria-hidden="true"></i></span>
	                        <span class="text">Back</span>              
		</button>
    </div>
    <form name="regForm" class="vendor-form" id="form_1" novalidate>
        <div class="row">
            <div class="data-outer col-md-6 col-lg-6 col-sm-6 col-xs-12">                        
                <div class="data-main-container">
                    <header class="data-main-container-header">Basic Information</header>

                    <div
                        class="control-group col-lg-4 col-md-4 col-sm-6 col-xs-6 pull-left">
                        <label class="control-label" for="vendorName">Client Name</label>
                        <div class="controls">
                            <input type="text" name="vendorName" id="vendorName"
                                   ng-model="vm.registration.vendorName"
                                   class="form-input width-100" placeholder="Client Name"
                                   validation="required:alt=Required field" />
                        </div>
                    </div>
                    <div
                        class="control-group col-lg-3 col-md-3 col-sm-6 col-xs-6 pull-left">
                        <label class="control-label" for="revenue">Revenue (%)</label>
                        <div class="controls">
                            <input type="text" name="revenue" id="revenue"
                                   ng-model="vm.registration.revenue" class="form-input width-100"
                                   placeholder="Revenue (in %)"
                                   validation="required:alt=Required field" />
                        </div>
                    </div>
                     <div class="control-group col-lg-2 col-md-2 col-sm-2 col-xs-2 pull-left">
                            <label class="control-label" for="contact_code">Code</label>
                                <div class="controls small-drop">
                                    <select name="contact_code" ng-model="vm.registration.contactNo.country.id"
                                        class="form-input width-100" 
                                        ng-options="country.id as ( country.description  + ' - ' + '+' + country.phoneCode) for country in vm.lookups.COUNTRY"
										onselect = "country.id as (country.phoneCode)"
                                        >
                                        <option value="">-SELECT-</option>
                                    </select>                                    
                                </div>
                            </div>
                    <div class="control-group col-lg-3 col-md-3 col-sm-4 col-xs-4 pull-left">
                        <label class="control-label" for="contactNo">Contact no</label>
                        <div class="controls">
                            <input type="text" name="contactNo" id="contactNo"
                                   ng-model="vm.registration.contactNo.number"
                                   class="form-input width-100"
                                   placeholder="Contact No"
                                   validation="max_len:10:alt=max. 10 digits|numeric:alt=Number only|required:alt=Required field"
                                   maxlength="10" />
                        </div>
                    </div>
                    <div
                        class="control-group col-lg-4 col-md-4 col-sm-6 col-xs-6 pull-left">
                        <label class="control-label" for="email">Email</label>
                        <div class="controls">
                            <input type="text" name="email" id="email"
                                   ng-model="vm.registration.email" class="form-input width-100"
                                   placeholder="Email" validation="required:alt=Required field" />
                        </div>
                    </div>
                    <div
                        class="control-group col-lg-4 col-md-4 col-sm-6 col-xs-6 pull-left">
                        <label class="control-label" for="webUrl">Website URL</label>
                        <div class="controls">
                            <input type="text" name="webUrl" id="webUrl"
                                   ng-model="vm.registration.webUrl" class="form-input width-100"
                                   placeholder="Website URL"
                                   validation="required:alt=Required field" />
                        </div>
                    </div>
                    <div
                        class="control-group col-lg-4 col-md-4 col-sm-6 col-xs-6 pull-left">
                        <label class="control-label" for="vendorStatus">Client
                            Status</label>
                        <div class="controls">
                            <select name="vendorStatus"
                                    ng-model="vm.registration.vendorStatus.id"
                                    class="form-input width-100"
                                    ng-options="vendorStatus.id as (vendorStatus.description) for vendorStatus in vm.lookups.CLSTATS"
                                    validation="required:alt=required">
                                <option value="">--SELECT--</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>

            <div class="data-outer col-md-6 col-lg-6 col-sm-6 col-xs-12">                        
                <div class="data-main-container">   
                    <header class="data-main-container-header">Belhopat Management
                        Details</header>

                    <div
                        class="control-group col-lg-4 col-md-4 col-sm-6 col-xs-6 pull-left">
                        <label class="control-label" for="division">Business Unit</label>
                        <div class="controls">
                            <select name="division" ng-model="vm.registration.businessUnit.id"
                                    class="form-input width-100"
                                    ng-options="division.id as (division.description) for division in vm.lookups.DIV"
                                    validation="required:alt=required">
                                <option value="">--SELECT--</option>
                            </select>
                        </div>
                    </div>
                    <div
                        class="control-group col-lg-4 col-md-4 col-sm-6 col-xs-6 pull-left">
                        <label class="control-label" for="dob">Business Unit Head</label>
                        <div class="controls">
                            <select name="businessUnitHead"
                                    ng-model="vm.registration.bussUnitHead.id"
                                    validation="required:alt=required" class="form-input width-100"
                                    ng-options="business.id as (accounts.employeeMaster.firstName+' '+accounts.employeeMaster.lastName ) for accounts in vm.lookups.BUH">
                                <option value="">--SELECT--</option>
                            </select>
                        </div>
                    </div>
                    <div
                        class="control-group col-lg-4 col-md-4 col-sm-6 col-xs-6 pull-left">
                        <label class="control-label" for="dob">Account Manager</label>
                        <div class="controls">
                            <select name="accountManager"
                                    ng-model="vm.registration.accountManager.id"
                                    validation="required:alt=required" class="form-input width-100"
                                    ng-options="accounts.id as (accounts.employeeMaster.firstName+' '+accounts.employeeMaster.lastName ) for accounts in vm.lookups.AM">
                                <option value="">--SELECT--</option>
                            </select>
                        </div>
                    </div>
                    <div
                        class="control-group col-lg-4 col-md-4 col-sm-6 col-xs-6 pull-left">
                        <label class="control-label" for="bussDManager">Business
                            Dev Manager</label>
                        <div class="controls">
                            <select name="bussDManager"
                                    ng-model="vm.registration.bussDManager.id"
                                    validation="required:alt=required" class="form-input width-100"
                                    ng-options="bussDManager.id as (accounts.employeeMaster.firstName+' '+accounts.employeeMaster.lastName ) for accounts in vm.lookups.BDM">
                                <option value="">--SELECT--</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="data-outer col-md-6 col-lg-6 col-sm-6 col-xs-12">                        
                    <div class="data-main-container">   
                        <header class="data-main-container-header">Address Details</header> 
                        <div class="control-group col-lg-4 col-md-4 col-sm-6 col-xs-6 pull-left">
                            <label class="control-label" for="vendorAddress_address_line1">Address
                                Line 1</label>
                            <div class="controls">
                                <input type="text" name="vendorAddress_address_line1" id="address_line1"
                                       ng-model="vm.registration.vendorAddress.address1"
                                       class="form-input width-100" placeholder="Address Line 1"
                                       validation="required:alt=Required field" />
                            </div>
                        </div>
                        <div class="control-group col-lg-4 col-md-4 col-sm-6 col-xs-6 pull-left">
                            <label class="control-label" for="address_line2">Address
                                Line 2</label>
                            <div class="controls">
                                <input type="text" name="vendorAddress_address_line2" id="address_line1"
                                       ng-model="vm.registration.vendorAddress.address2"
                                       class="form-input width-100" placeholder="Address Line 2"
                                       ng-change="vm.checkAddress()" />
                            </div>
                        </div>
                        <div class="control-group col-lg-4 col-md-4 col-sm-6 col-xs-6 pull-left">
                            <label class="control-label" for="vendorAddress_country">Country</label>
                            <div class="controls">
                                <select name="vendorAddress_country" ng-model="vm.registration.vendorAddress.city.state.country.id"
                                        ng-change="vm.getStatesByCountry(vm.registration.vendorAddress.city.state.country.id, 'vendor')"
                                        class="form-input width-100" ng-options="country.id as (country.description) for country in vm.lookups.COUNTRY"
                                        validation="required:alt=required">
                                    <option value="">--SELECT--</option>
                                </select>                                   
                            </div>
                        </div>
                        <div class="control-group col-lg-4 col-md-4 col-sm-6 col-xs-6 pull-left">
                            <label class="control-label" for="vendorAddress_state">State</label>
                            <div class="controls">
                                <select name="vendorAddress_state" ng-model="vm.registration.vendorAddress.city.state.id"
                                        ng-change="vm.getCitiesByStates(vm.registration.vendorAddress.city.state.id, 'vendor')"
                                        class="form-input width-100" ng-options="states.id as (states.description) for states in vm.statesClient"
                                        validation="required:alt=required">
                                    <option value="">--SELECT--</option>
                                </select>
                            </div>
                        </div>
                        <div class="control-group col-lg-4 col-md-4 col-sm-6 col-xs-6 pull-left">
                            <label class="control-label" for="vendorAddress_city">City</label>
                            <div class="controls">
                                <select name="vendorAddress_city" ng-model="vm.registration.vendorAddress.city.id"
                                        class="form-input width-100" ng-options="city.id as (city.description) for city in vm.citiesClient">
                                    <option value="">--SELECT--</option>   
                                </select> 

                            </div>
                        </div>
                        <div class="control-group col-lg-4 col-md-4 col-sm-6 col-xs-6 pull-left">
                            <label class="control-label" for="zip_code">ZIP Code</label>
                            <div class="controls">
                                <input type="text" name="vendorAddress_zip_code" id="zip_code"
                                       ng-model="vm.registration.vendorAddress.zipCode"
                                       class="form-input width-100" placeholder="ZIP Code"
                                       validation="digits:6:alt=Must be 6 digits|required:alt=Required field"
                                       ng-change="vm.checkAddress()" />                                    
                            </div>
                        </div>
                    </div>
                </div>
                <div class="data-outer col-md-6 col-lg-6 col-sm-6 col-xs-12">                        
                    <div class="data-main-container">   
                        <header class="data-main-container-header">Point Of Contact Details</header>
                        <div class="poc-section">
                            <div
                                class="control-group col-lg-4 col-md-4 col-sm-6 col-xs-6 pull-left">
                                <label class="control-label" for="pocName">POC Name</label>
                                <div class="controls">
                                    <input type="text" name="pocName" id="pocName"
                                           ng-model="vm.registration.poc.pocName"
                                           class="form-input width-100" placeholder="POC Name"
                                           validation="required:alt=Required field" />
                                </div>
                            </div>
                            <div 
                                class="control-group col-lg-4 col-md-4 col-sm-6 col-xs-6 pull-left">
                                <label class="control-label" for="designation">Designation</label>
                                <div class="controls">
                                    <select name="designation" ng-model="vm.registration.poc.pocDesignation.id"
                                            class="form-input width-100" ng-options="designation.id as (designation.description) for designation in vm.lookups.DESIG"
                                            validation="required:alt=required">
                                        <option value="">--SELECT--</option>
                                    </select>
                                </div>
                            </div>
                            <div 
                                class="control-group col-lg-4 col-md-4 col-sm-6 col-xs-6 pull-left">
                                <label class="control-label" for="country">Country</label>
                                <div class="controls">
                                    <select name="onsiteAddress_country" ng-model="vm.registration.poc.pocCountry.id"
                                            class="form-input width-100" ng-options="country.id as (country.description) for country in vm.lookups.COUNTRY"
                                            validation="required:alt=required">
                                        <option value="">--SELECT--</option>
                                    </select> 
                                </div>
                            </div>
                            <div class="control-group col-lg-2 col-md-2 col-sm-2 col-xs-4 pull-left">
                            <label class="control-label" for="poc_country_code">Code</label>
                                <div class="controls small-drop">
                                    <select name="poc_country_code" ng-model="vm.registration.poc.contactNo.country.id"
                                        class="form-input width-100" 
                                        ng-options="country.id as (country.description  + ' - ' + '+' + country.phoneCode) for country in vm.lookups.COUNTRY"
										onselect = "country.id as (country.phoneCode)"
                                        >
                                        <option value="">-SELECT-</option>
                                    </select>                                    
                                </div>
                            </div>
                            <div
                                class="control-group col-lg-4 col-md-4 col-sm-4 col-xs-8 pull-left">
                                <label class="control-label" for="contactNo">Contact no</label>
                                <div class="controls">
                                    <input type="text" name="poc.contactNo" id="poc.contactNo"
                                           ng-model="vm.registration.poc.contactNo.number"
                                           class="form-input width-100"
                                           placeholder="Contact No"
                                           validation="max_len:10:alt=max. 10 digits|numeric:alt=Number only|required:alt=Required field"
                                           maxlength="10"/>
                                </div>
                            </div>
                            <div class="control-group col-lg-2 col-md-2 col-sm-2 col-xs-4 pull-left">
                            <label class="control-label" for="poc_mob_country_code">Code</label>
                                <div class="controls small-drop">
                                    <select name="poc_mob_country_code" ng-model="vm.registration.poc.mobNo.country.id"
                                        class="form-input width-100" 
                                        ng-options="country.id as (country.description  + ' - ' + '+' + country.phoneCode) for country in vm.lookups.COUNTRY"
										onselect = "country.id as (country.phoneCode)"
                                        >
                                        <option value="">-SELECT-</option>
                                    </select>                                    
                                </div>
                            </div>
                            <div
                                class="control-group col-lg-4 col-md-4 col-sm-4 col-xs-8 pull-left">
                                <label class="control-label" for="contactNo">Mobile no</label>
                                <div class="controls">
                                    <input type="text" name="poc.mobNo" id="poc.mobNo"
                                           ng-model="vm.registration.poc.mobNo.number"
                                           class="form-input width-100"
                                           placeholder="Mobile No"
                                           validation="max_len:10:alt=max. 10 digits|numeric:alt=Number only|required:alt=Required field"
                                           maxlength="10"/>
                                </div>
                            </div>
                            <div
                                class="control-group col-lg-4 col-md-4 col-sm-6 col-xs-6 pull-left">
                                <label class="control-label" for="areaOfWork">Area Of Work</label>
                                <div class="controls">
                                    <input type="text" name="areaOfWork" id="areaOfWork"
                                           ng-model="vm.registration.poc.areaOfWork"
                                           class="form-input width-100"
                                           placeholder="Area Of Work"
                                           validation="required:alt=Required field" />
                                </div>
                            </div>
                        </div>
						</div>
					</fieldset>
				</div>
			</div>

        </div>
    </form>
     <div class="bottom-button-liner">        
        <button ng-click="vm.vendorRegister()"
		class="logout-button pull-right add-button confirm-btn" title="Confirm and Register">
		<span class="fa-stack fa-lg pull-left"><i
			class="fa fa-check" aria-hidden="true"></i></span>
                        <span class="text">Confirm and Register</span>              
	</button>
        </div>
</div>




