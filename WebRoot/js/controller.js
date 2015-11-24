 /**
 * 这里是登录模块
 * @type {[type]}
 */
var loginModule = angular.module("loginModule", []);
loginModule.controller('loginCtrl', function($scope, $http ,$rootScope, AUTH_EVENTS, AuthService) {

	$scope.userInfo = {
		username: '',
		password: ''
	};
	$scope.login = function(userInfo) {
		//最终应该是向服务端提交数据，返回结果
		console.log("checkUser:---------userInfo");
		console.log(userInfo);
		AuthService.login(userInfo).then(function (username) {
	      $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
	      $rootScope.setCurrentUser(username);
	    }, function () {
	      $rootScope.$broadcast(AUTH_EVENTS.loginFailed);
	    });
	};

	$scope.resetForm = function() {
		$scope.userInfo = {
			username: "",
			password: ""
		};
	};
});
loginModule.constant('AUTH_EVENTS', {
	loginSuccess: 'auth-login-success',
	loginFailed: 'auth-login-failed',
	logoutSuccess: 'auth-logout-success',
	sessionTimeout: 'auth-session-timeout',
	notAuthenticated: 'auth-not-authenticated',
	notAuthorized: 'auth-not-authorized'
});
loginModule.constant('USER_ROLES', {
	all: '*',
	admin: 'admin',
	editor: 'editor',
	guest: 'guest'
});
//一些操作的身份认证授权服务 登录的具体实现逻辑应该在这里，登录认证应该解耦，登录Controller应该只关心表单的事情
loginModule.factory('AuthService', function($http, Session) {
	var authService = {};

	authService.login = function(userInfo){
		return $http
			.post('LoginServlet', userInfo)
			.then(function(res) {
				Session.create(res.data.id, res.data.userid,
					res.data.role);
				Session.toLocalString();
				return res.data.userid;
			});
	}
	

	authService.isAuthenticated = function() {
		return !!Session.userId;
	};

	authService.isAuthorized = function(authorizedRoles) {
		if (!angular.isArray(authorizedRoles)) {
			authorizedRoles = [authorizedRoles];
		}
		return (authService.isAuthenticated() &&
			authorizedRoles.indexOf(Session.userRole) !== -1);
	};
	return authService;
});
loginModule.service('Session', function() {
	this.create = function(sessionId, userId, userRole) {
		this.id = sessionId;
		this.userId = userId;
		this.userRole = userRole;
	};
	this.destroy = function() {
		this.id = null;
		this.userId = null;
		this.userRole = null;
	};
	this.getSessionFromServer = function(){
		
	};
	this.toLocalString = function(){
		console.log("localToString----");
		console.log("sessionid="+this.id+",userId="+this.userId);

	};
	return this;
});