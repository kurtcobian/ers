angular.module("ers", ["ngRoute"]);

angular.module("ers").controller("displayCtrl", function($scope, $http){
	$http({
		method: "GET", url: "display.do"
	}).then(function(value){
		console.log(value.data);
		$scope.reimbursements = value.data[1];
		$scope.role = value.data[0];
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