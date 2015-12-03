/*
 * 这一块主要是初始化system 还有路由的配置
 */
var addSystem = angular.module('addSystem', ['ui.router', 'loginModule', 'gridModule', 'chartsModule']);

/**
 * 定义一些没什么用的全局函数
 */

addSystem.run(function($rootScope, $state, $stateParams, Session, AUTH_EVENTS ,AuthService) {
	//root作用域一些默认的参数生成以及绑定
	$rootScope.$state = $state;
	$rootScope.$stateParams = $stateParams;

	$rootScope.user = {
		username: 'guest'
	};

	//全局函数绑定，这里不应该涉及到任何权限的设置
	$rootScope.setCurrentUser = function(username) {
		$rootScope.user = {
			username: username
		};
	};
	$rootScope.test = function() {
		Session.toLocalString();
	};
	$rootScope.getDate = function() {
		var myDate = new Date();
		return myDate.toLocaleString();
	};
	$rootScope.getRole = function() {
		var role = Session.getRole();
		if (!role || role === "guest") {
			return false;
		} else {
			return role;
		}
	};
	$rootScope.first = false;
	$rootScope.destory = function() {
		Session.destroy();
	};

	//尝试从服务器获取已经存在的session并跳转页面
	Session.getSessionFromServer();

	$rootScope.$on('$stateChangeStart', function(event, next) {
		var authorizedRoles = next.data.authorizedRoles;
		if ($rootScope.first&&(!AuthService.isAuthorized(authorizedRoles))&&(next.name!=="login")) {
			event.preventDefault();
			if (AuthService.isAuthenticated()) {
				// user is not allowed
				$rootScope.$broadcast(AUTH_EVENTS.notAuthorized);
			} else {
				// user is not logged in
				$rootScope.$broadcast(AUTH_EVENTS.notAuthenticated);
			}
		}
	});
	$rootScope.$on(AUTH_EVENTS.notAuthorized, function(event) {
		console.log("--权限不足");
		$state.go('home.error');
	});
	$rootScope.$on(AUTH_EVENTS.notAuthenticated, function(event) {
		console.log("--尚未登录，无法操作");
		$state.go('login');
		
	});
	$rootScope.$on(AUTH_EVENTS.loginSuccess, function(event) {
		console.log("--LoginSuccess");
		$state.go('home');
	});
	$rootScope.$on(AUTH_EVENTS.loginFailed, function(event) {
		console.log("--serverERROR!!!!");
	});


});


addSystem.config(function($httpProvider, $stateProvider, $urlRouterProvider,USER_ROLES) {

	$httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
	$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

	// Override $http service's default transformRequest
	$httpProvider.defaults.transformRequest = [function(data) {
		/**
		 * The workhorse; converts an object to x-www-form-urlencoded serialization.
		 * @param {Object} obj
		 * @return {String}
		 */
		var param = function(obj) {
			var query = '';
			var name, value, fullSubName, subName, subValue, innerObj, i;

			for (name in obj) {
				value = obj[name];

				if (value instanceof Array) {
					for (i = 0; i < value.length; ++i) {
						subValue = value[i];
						fullSubName = name + '[' + i + ']';
						innerObj = {};
						innerObj[fullSubName] = subValue;
						query += param(innerObj) + '&';
					}
				} else if (value instanceof Object) {
					for (subName in value) {
						subValue = value[subName];
						fullSubName = name + '[' + subName + ']';
						innerObj = {};
						innerObj[fullSubName] = subValue;
						query += param(innerObj) + '&';
					}
				} else if (value !== undefined && value !== null) {
					query += encodeURIComponent(name) + '=' + encodeURIComponent(value) + '&';
				}
			}

			return query.length ? query.substr(0, query.length - 1) : query;
		};

		return angular.isObject(data) && String(data) !== '[object File]' ? param(data) : data;
	}];
	$urlRouterProvider.otherwise('/login');
	$stateProvider
		.state('login', {
			url: '/login',
			views: {
				'': {
					templateUrl: 'tpls/empty.html'
				},
				'main@login': {
					templateUrl: 'tpls/login.html'
				}
			},
			data: {
		      authorizedRoles: [USER_ROLES.admin, USER_ROLES.teacher, USER_ROLES.student, USER_ROLES.guest]
		    }
		})
		.state('home', {
			url: '/home',
			views: {
				'': {
					templateUrl: 'tpls/index.html'
				},
				'topbar@home': {
					templateUrl: 'tpls/header.html'
				},
				'sidebar@home': {
					templateUrl: 'tpls/sidebar.html'
				},
				'main_content@home': {
					templateUrl: 'tpls/profile.html'
				}
			},
			data: {
		      authorizedRoles: [USER_ROLES.admin, USER_ROLES.teacher, USER_ROLES.student, USER_ROLES.guest]
		    }
		})
		.state('home.stuinfo', {
			url: '/stuinfo',
			views: {
				'main_content@home': {
					templateUrl: 'tpls/gridList.html'
				}
			},
			data: {
		      authorizedRoles: [USER_ROLES.admin, USER_ROLES.teacher, USER_ROLES.student]
		    }
		})
		.state('home.super', {
			url: '/super',
			views: {
				'main_content@home': {
					templateUrl: 'tpls/superAdmin.html'
				}
			},
			data: {
		      authorizedRoles: [USER_ROLES.admin]
		    }
		})
		.state('home.charts', {
			url: '/charts',
			views: {
				'main_content@home': {
					templateUrl: 'tpls/charts.html'
				}
			},
			data: {
		      authorizedRoles: [USER_ROLES.admin, USER_ROLES.teacher]
		    }
		})
		.state('home.userform', {
			url: '/userform',
			views: {
				'main_content@home': {
					templateUrl: 'tpls/userform.html'
				}
			},
			data: {
		      authorizedRoles: [USER_ROLES.admin, USER_ROLES.teacher, USER_ROLES.student]
		    }
		})
		.state('home.passwordchange', {
			url: '/passchange',
			views: {
				'main_content@home': {
					templateUrl: 'tpls/passwordform.html'
				}
			},
			data: {
		      authorizedRoles: [USER_ROLES.admin, USER_ROLES.teacher, USER_ROLES.student]
		    }
		})
		.state('home.dropzone', {
			url: '/dropzone',
			views: {
				'main_content@home': {
					templateUrl: 'tpls/dropzone.html'
				}
			},
			data: {
		      authorizedRoles: [USER_ROLES.admin, USER_ROLES.teacher]
		    }
		})
		.state('home.error', {
			url: '/error',
			views: {
				'main_content@home': {
					templateUrl: 'tpls/error-notpower.html'
				}
			},
			data: {
		      authorizedRoles: [USER_ROLES.admin, USER_ROLES.teacher, USER_ROLES.student, USER_ROLES.guest]

		    }
		});


});

addSystem.factory('Test', [function() {
	var sudo = {};
	sudo.username = "defaultName";
	sudo.password = "defaultPwd";
	sudo.power = "1";
	return sudo;

}]);