/**
 * 这里是登录模块
 * @type {[type]}
 */
var loginModule = angular.module("loginModule", []);
loginModule.controller('loginCtrl', function($scope, $http, $rootScope, AUTH_EVENTS, AuthService) {
	$scope.userInfo = {
		username: '',
		password: ''
	};
	$scope.loginfail = false;
	$scope.login = function(userInfo) {
		AuthService.login(userInfo).then(function(data) {
			if (!(data.role === "guest")) {
				$rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
				$rootScope.setCurrentUser(data.username);
			} else {
				$scope.loginfail = true;
			}
		}, function() {
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
	passwordError: 'auth-login-error',
	logoutSuccess: 'auth-logout-success',
	sessionTimeout: 'auth-session-timeout',
	notAuthenticated: 'auth-not-authenticated',
	notAuthorized: 'auth-not-authorized'
});




//一些操作的身份认证授权服务 登录的具体实现逻辑应该在这里，登录认证应该解耦，登录Controller应该只关心表单的事情
loginModule.factory('AuthService', function($http, Session) {
	var authService = {};
	authService.login = function(userInfo) {
		return $http
			.post('LoginServlet', userInfo)
			.then(function(res) {
				Session.create(res.data.password, res.data.username,
					res.data.role);
				return res.data;
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


loginModule.service('Session', function($http) {
	this.create = function(sessionId, userId, userRole) {
		this.id = sessionId;
		this.userId = userId;
		this.userRole = userRole;
	};
	this.getRole = function(){
		return this.userRole;
	};
	this.destroy = function() {
		this.id = null;
		this.userId = null;
		this.userRole = null;
	};
	this.getSessionFromServer = function() {
		/*$http.post('LoginServlet', userInfo)
		.then(function(res) {
			Session.create(res.data.password, res.data.username,
				res.data.role);
			return res.data.username;
		});*/
	};
	this.toLocalString = function() {
		console.log("localToString----");
		console.log("sessionid=" + this.id + ",userId=" + this.userId);

	};
	return this;
});

/**
 * 这里是数据表单版块
 * 
 */
var gridModule = angular.module('gridModule', ['ngGrid']);
gridModule.controller('StuListCtrl', function($scope) {
	var vm = $scope.vm = {};
	vm.page = {
		size: 5,
		index: 1
	};
	vm.sort = {
		column: 'id',
		direction: -1,
		toggle: function(column) {
			if (column.sortable === false)
				return;

			if (this.column === column.name) {
				this.direction = -this.direction || -1;
			} else {
				this.column = column.name;
				this.direction = -1;
			}
		}
	};
	// 构建模拟数据
	vm.columns = [{
		label: '学号',
		name: 'id',
		type: 'string'
	}, {
		label: '姓名',
		name: 'name',
		type: 'string'
	}, {
		label: '性别',
		name: 'sex',
		type: 'boolean'
	}, {
		label: '班级',
		name: 'sclass',
		type: 'string'
	}, {
		label: '联系电话',
		name: 'phone',
		type: 'string'
	}, {
		label: '地址',
		name: 'phone',
		type: 'string'
	}, {
		label: '政治背景',
		name: 'party',
		type: 'string'
	}, {
		label: '',
		name: 'actions',
		sortable: false
	}];

	vm.items = [{
		"address": "广东工业大学",
		"id": "3114004900",
		"name": "御坂2号",
		"party": "共青团员",
		"phone": "1323220000",
		"sclass": "14信管2班",
		"sex": "男",
		"uuid": "B6DE3F6B92C749738576983E6929314C"
	}, {
		"address": "广州",
		"id": "3114004921",
		"name": "御坂1号",
		"party": "共青团员",
		"phone": "1323220000",
		"sclass": "14信管2班",
		"sex": "男",
		"uuid": "D2B42D03F93741369D54E2D61C55737D"
	}, {
		"address": "广州",
		"id": "3114004922",
		"name": "御坂3号",
		"party": "共青团员",
		"phone": "1323220000",
		"sclass": "14信管2班",
		"sex": "男",
		"uuid": "DEC61A93C32C47049C138AE6E353922C"
	}, {
		"address": "广州",
		"id": "3114004921",
		"name": "御坂1号",
		"party": "共青团员",
		"phone": "1323220000",
		"sclass": "14信管2班",
		"sex": "男",
		"uuid": "D2B42D03F93741369D54E2D61C55737D"
	}, {
		"address": "广州",
		"id": "3114004922",
		"name": "御坂4号",
		"party": "共青团员",
		"phone": "1323220000",
		"sclass": "14信管2班",
		"sex": "男",
		"uuid": "DEC61A93C32C47049C138AE6E353922C"
	}, {
		"address": "广州",
		"id": "3114004921",
		"name": "御坂5号",
		"party": "共青团员",
		"phone": "1323220000",
		"sclass": "14信管2班",
		"sex": "男",
		"uuid": "D2B42D03F93741369D54E2D61C55737D"
	}, {
		"address": "广州",
		"id": "3114004922",
		"name": "御坂6号",
		"party": "共青团员",
		"phone": "1323220000",
		"sclass": "14信管2班",
		"sex": "男",
		"uuid": "DEC61A93C32C47049C138AE6E353922C"
	}];

});
var chartsModule = angular.module('chartsModule', ['ngGrid']);
chartsModule.controller('chartsCtrl', function($scope) {
	$scope.option = {};
	$scope.chartInit = function() {
		var myChart = echarts.init(document.getElementById('main'));
		var option = {
			title: {
				text: '某站点用户访问来源',
				subtext: '纯属虚构',
				x: 'center'
			},
			tooltip: {
				trigger: 'item',
				formatter: "{a} <br/>{b} : {c} ({d}%)"
			},
			legend: {
				orient: 'vertical',
				x: 'left',
				data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
			},
			toolbox: {
				show: true,
				feature: {
					mark: {
						show: true
					},
					dataView: {
						show: true,
						readOnly: false
					},
					magicType: {
						show: true,
						type: ['pie', 'funnel'],
						option: {
							funnel: {
								x: '25%',
								width: '50%',
								funnelAlign: 'left',
								max: 1548
							}
						}
					},
					restore: {
						show: true
					},
					saveAsImage: {
						show: true
					}
				}
			},
			calculable: true,
			series: [{
				name: '访问来源',
				type: 'pie',
				radius: '55%',
				center: ['50%', '60%'],
				data: [{
					value: 335,
					name: '直接访问'
				}, {
					value: 310,
					name: '邮件营销'
				}, {
					value: 234,
					name: '联盟广告'
				}, {
					value: 135,
					name: '视频广告'
				}, {
					value: 1548,
					name: '搜索引擎'
				}]
			}]
		};
		myChart.setOption(option);
	}
});