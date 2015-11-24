/*
 * 这一块主要是初始化system 还有路由的配置
 */
var addSystem = angular.module('addSystem', ['ui.router', 'loginModule']);


addSystem.run(function($rootScope, $state, $stateParams, Session ,AUTH_EVENTS) {
	//root作用域一些默认的参数生成以及绑定
	$rootScope.$state = $state;
	$rootScope.$stateParams = $stateParams;
	
	$rootScope.user = {
		username:'游客'
	};
	$rootScope.setCurrentUser = function(username){
		//console.log("username = "+username);
		$rootScope.user = {
				username:username
			};
	};
	$rootScope.test = function(){
		Session.toLocalString();
	};
	
	//尝试从服务器获取已经存在的session并跳转页面
	
	
	//初始化事件监听
	$rootScope.$on('$stateChangeStart', function(event, next) {
		console.log("stateChange");
	});
	$rootScope.$on(AUTH_EVENTS.loginSuccess, function(event) {
	      console.log("--LoginSuccess");
	      $state.go('home');
	});
	$rootScope.$on(AUTH_EVENTS.loginFailed, function(event) {
	      console.log("--LoginFailed!!!!");
	});

});


addSystem.config(function($stateProvider, $urlRouterProvider) {
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
			}
		})
		.state('stuinfo', {
			url: '/home/stu',
			views: {
				'': {
					templateUrl: 'tpls/index.html'
				},
				'topbar@stuinfo': {
					templateUrl: 'tpls/header.html'
				},
				'sidebar@stuinfo': {
					templateUrl: 'tpls/sidebar.html'
				},
				'main_content@stuinfo': {
					templateUrl: 'tpls/main_content.html'
				}
			}
		})


});

addSystem.factory('Test',[function(){
	var sudo = {};
	sudo.username = "defaultName";
	sudo.password = "defaultPwd";
	sudo.power = "1";
	return sudo;
	
}]);
