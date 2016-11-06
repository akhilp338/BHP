<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<form name='loginForm' action="<c:url value='/login'/>" class="loginForm" method='POST'>
	<div class="login-container">
	<div class="login-form">
	    <div class="form-group">
	        <span class="alert" ng-if="vm.errorMessage != ''">{{vm.errorMessage}}</span>
	    </div>
	    <h3>Belhopat Employee Portal</h3>
	    <div class="form-group ">
	        <input type="text" required class="form-input width-100" placeholder="Username " name="username" id="UserName" ng-model="vm.loginCreds.username" ng-change="vm.errorMessage = ''" ng-keyup="$event.keyCode == 13 && vm.login(vm.loginCreds)">
	        <i class="fa fa-user"></i>
	    </div>
	    <div class="form-group log-status">
	        <input type="password" required class="form-input width-100" placeholder="Password" name="password" id="rm-control Password" ng-model="vm.loginCreds.password" ng-change="vm.errorMessage = ''" ng-keyup="$event.keyCode == 13 && vm.login(vm.loginCreds)">
	        <i class="fa fa-lock"></i>
	    </div>    
	    <input type="hidden" 
			name="${_csrf.parameterName}"
			value="${_csrf.token}" />    
	    <button type="submit" class="log-btn" >Log in</button>
	    <a class="link" href="#" ng-click="vm.forgotPassword()">Forgot Password?</a>
	    </div>
	</div>
</form>