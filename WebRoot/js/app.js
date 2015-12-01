/*
 * 这一块主要是初始化system 还有路由的配置
 */
var addSystem = angular.module('addSystem', ['ui.router', 'loginModule','gridModule','chartsModule']);


addSystem.run(function($rootScope,$state, $stateParams, Session ,AUTH_EVENTS) {
	//root作用域一些默认的参数生成以及绑定
	$rootScope.$state = $state;
	$rootScope.$stateParams = $stateParams;
	
	
	$rootScope.user = {
		username:'游客'
	};
	$rootScope.setCurrentUser = function(username){
		$rootScope.user = {
				username:username
			};
	};
	$rootScope.test = function(){
		Session.toLocalString();
	};
	
	//尝试从服务器获取已经存在的session并跳转页面
	Session.getSessionFromServer();
	
	
	//初始化事件监听
	$rootScope.$on('$stateChangeStart', function(event, next) {
		console.log("stateChange");
	});
	$rootScope.$on(AUTH_EVENTS.loginSuccess, function(event) {
	      console.log("--LoginSuccess");
	      $state.go('home');
	});
	$rootScope.$on(AUTH_EVENTS.loginFailed, function(event) {
	      console.log("--serverERROR!!!!");
	});
	$rootScope.$on(	AUTH_EVENTS.passwordError, function(event) {
	      console.log("--LoginFailed,passwordError");
	});

});


addSystem.config(function($httpProvider,$stateProvider, $urlRouterProvider) {

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
                    query += encodeURIComponent(name) + '='
                            + encodeURIComponent(value) + '&';
                }
            }
 
            return query.length ? query.substr(0, query.length - 1) : query;
        };
 
        return angular.isObject(data) && String(data) !== '[object File]'
                ? param(data)
                : data;
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
		.state('home.stuinfo', {
			url: '/stuinfo',
			views: {
				'main_content@home': {
					templateUrl: 'tpls/gridList.html'
				}
			}
		})
		.state('home.charts', {
			url: '/charts',
			views: {
				'main_content@home': {
					templateUrl: 'tpls/charts.html'
				}
			}
		})
		.state('home.userform', {
			url: '/charts',
			views: {
				'main_content@home': {
					templateUrl: 'tpls/userform.html'
				}
			}
		})
		.state('home.dropzone', {
			url: '/dropzone',
			views: {
				'main_content@home': {
					templateUrl: 'tpls/dropzone.html'
				}
			}
		});


});

addSystem.factory('Test',[function(){
	var sudo = {};
	sudo.username = "defaultName";
	sudo.password = "defaultPwd";
	sudo.power = "1";
	return sudo;
	
}]);
