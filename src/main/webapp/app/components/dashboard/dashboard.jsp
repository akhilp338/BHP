<div class="sub-content dashboard" ng-init="vm.init()">

	<div class="row">

		<div class="col-lg-4 col-md-6">
			<div class="panel panel-green">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-3">
							<i class="fa fa-tasks fa-5x"></i>
						</div>
						<div class="col-xs-9 text-right">
							<div class="huge">12</div>
							<div>New Tasks!</div>
						</div>
					</div>
				</div>
				<a href="#">
					<div class="panel-footer">
						<span class="pull-left">View Details</span> <span
							class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
		</div>
		<div class="col-lg-4 col-md-6">
			<div class="panel panel-yellow">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-3">
							<i class="fa fa-shopping-cart fa-5x"></i>
						</div>
						<div class="col-xs-9 text-right">
							<div class="huge">124</div>
							<div>New Orders!</div>
						</div>
					</div>
				</div>
				<a href="#">
					<div class="panel-footer">
						<span class="pull-left">View Details</span> <span
							class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
		</div>
		<div class="col-lg-4 col-md-6">
			<div class="panel panel-red">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-3">
							<i class="fa fa-support fa-5x"></i>
						</div>
						<div class="col-xs-9 text-right">
							<div class="huge">13</div>
							<div>Support Tickets!</div>
						</div>
					</div>
				</div>
				<a href="#">
					<div class="panel-footer">
						<span class="pull-left">View Details</span> <span
							class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
		</div>
	</div>
	<!-- /.row -->

	<div class="row tasklist">
		<table id="tasksList" class="display"></table>
	</div>
	<!-- /.row -->

	<!-- <div class="news row">
		<div class="col-lg-12">

			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<i class="fa fa-wpforms fa-fw"></i> News and Broadcasts
					</h3>
				</div>
				<div class="panel-body">
					<md-button class="md-primary md-raised md-news"
						ng-click="showPrompt($event)"> Broadcast a message </md-button>
					<div class="well well-sm">
						<div class="row">
							<div class="col-sm-12">
								<input type="number" class="form-control"
									ng-model="selectedItem" placeholder="Search for a broadcast">
							</div>
						</div>
					</div>
					<div id="scroll-test-container" class="panel-body"
						ng-init="vm.itemsss()">
						<div ng-repeat="item in items" scroll-when="selectedItem === item"
							scroll-watch-enabled="true"
							scroll-container="#scroll-test-container" scroll-offset="200"
							scroll-speed="100" class="item-container"
							ng-class="{'active': selectedItem === item}">
							<span>{{item}}</span>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div> -->

</div>

