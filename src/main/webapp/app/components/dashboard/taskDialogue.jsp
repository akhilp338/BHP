<div class="modal-header">
<span id='close' ng-click="vm.close()">x</span>
    <h3 class="modal-title">Task Details</h3>
</div>
<div class="modal-body" ng-show-data template="{{vm.template}}">
</div>
<div class="modal-footer">
    <button class="logout-button btn-formsubmit" type="button" ng-click="vm.gotoState()">Goto Task</button>
    <button class="logout-button btn-formsubmit" type="button" ng-click="vm.close()">Close</button>
</div>