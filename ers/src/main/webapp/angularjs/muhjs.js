angular.module("ers", ["ngRoute"]);

angular.module("ers")
		.config(function($routeProvider, $locationProvider) {
			$locationProvider.hashPrefix("");
			$routeProvider.when("/create", {
				templateUrl: "create.html"
			}).otherwise({
				templateUrl: "tables.html"
			});
});