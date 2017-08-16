angular.module("ers", ["ngRoute"]);

angular.module("ers").controller("displayCtrl", function($scope, $http){
	$http({
		method: "GET", url: "display.do"
	}).then(function(value){
		$scope.reimbursements = value.data;
	});
});

angular.module("ers")
		.config(function($routeProvider, $locationProvider) {
			$locationProvider.hashPrefix("");
			$routeProvider.when("/create", {
				templateUrl: "create.html"
			}).otherwise({
				templateUrl: "tables.html",
				controller:  "displayCtrl"
			});
});