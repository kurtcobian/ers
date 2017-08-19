angular.module("ers", ["ngRoute"]);

angular.module("ers").controller("createCtrl", function($scope, $http, $route){
	var types = [{"id":1, "type":"Lodging"},
        {"id":2, "type":"Travel"},
        {"id":3, "type":"Food"},
        {"id":4, "type":"Other"}];
	$scope.createReimb = function(){
		$scope.newReimb.type = types[$scope.type];
		$http({
			method: "POST", url: "createReimb.do", data: $scope.newReimb
		}).then(function(response){
			console.log(response);
		});
		console.log($scope.newReimb);
	};
});
angular.module("ers").controller("displayCtrl", function($scope, $http, $route){
	$http({
		method: "GET", url: "display.do"
	}).then(function(response){
		console.log(response.data);
		$scope.reimbursements = response.data[1];
		$scope.role = response.data[0];
	});
	$scope.approve = function($event){
		var r_id = $event.currentTarget.getAttribute("id");
		var reimbursement = $scope.reimbursements.filter(function(i){ return i.id == r_id})[0];
		console.log(reimbursement);
		$http({
			method: "POST", url: "approve.do", data: reimbursement
		}).then(function(response){
			$route.reload();
		});
	}
	$scope.deny = function($event){
		var r_id = $event.currentTarget.getAttribute("id");
		var reimbursement = $scope.reimbursements.filter(function(i){ return i.id == r_id})[0];
		$http({
			method: "POST", url: "deny.do", data: reimbursement
		}).then(function(response){
			$route.reload();
		});;
	}
});

angular.module("ers")
		.config(function($routeProvider, $locationProvider) {
			$locationProvider.hashPrefix("");
			$routeProvider.when("/create", {
				templateUrl: "create.html",
				controller: "createCtrl"
			}).otherwise({
				templateUrl: "tables.html",
				controller:  "displayCtrl"
			});
});