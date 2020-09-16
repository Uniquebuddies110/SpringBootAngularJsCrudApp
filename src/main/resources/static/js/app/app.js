var app=angular.module('MyApp',['ngRoute']);
app.config(function($routeProvider) {
	console.log("From Config......");

	$routeProvider.when("/", {
		templateUrl : "/home"
	});
	
	$routeProvider.when("/addReg", {
		templateUrl : "/emp/api/addNewEmp"
	});

	$routeProvider.when("/displayAll", {
		templateUrl : "/emp/api/displayAll"
	});

});