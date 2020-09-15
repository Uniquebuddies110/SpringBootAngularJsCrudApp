var app = angular.module("MyApp", [ "ngRoute" ]);
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
	
	/* Emp Service */
	app.service('empService',function($http){
		this.addEmp= function(empObj) {
			$http.post('/emp/api/save', empObj).then(function successCallback(response){
				return "Employee '"+empObj+"' is added successfully!!";
			}, function errorCallback(response){
				return response.status;
			});
		}
	});

	/* For Register Page */
	app.controller("regCtrl", function($scope,$http,empService) {
		$scope.emp={};
		
		/* Add Functionality */
		$scope.add=function(){
			/*alert("From Emp Reg Controller Add Function.....name:"+$scope.emp.name+" ,Dept:"+$scope.emp.dept+" ,Salary:"+$scope.emp.salary);*/
			
			$scope.msg=empService.addEmp($scope.emp);
			$scope.emp={};
			/*window.location.reload();*/
		}
	});
	
	/* For ViewAll Page */
	app.controller("viewAllCtrl",function($scope,$http){
		
		$scope.emps=[];
		$scope.msg="";
		$scope.hideform = true;
		
		/* For Getting Employee Record to display Table */
		$http.get('/emp/api/getAll').then(function successCallback(response){
			$scope.emps=response.data;
		}, function errorCallback(response){
			alert(response.status);
		});
		
		
		/* For Edit Functionality */
		$scope.edit= function(id){
			$scope.hideform = false;
			$scope.emp={};
			$http.get('/emp/api/getAll/'+id).then(function successCallback(response){
				/* alert(response.data); */
				$scope.emp=response.data;
				/*$scope.msg="From Emp Reg Controller Add Function.....Id:"+$scope.emp.id+", name:"+$scope.emp.name+" ,Dept:"+$scope.emp.dept+" ,Salary:"+$scope.emp.salary;*/
			}, function errorCallback(response){
				alert(response.status);
			});
		}
		
		$scope.editEmp= function(){
			$http.put('/emp/api/update', $scope.emp).then(function successCallback(response){
				console.log("Success :"+response.data);
				$scope.hideform = true;
				alert(response.data);
			}, function errorCallback(response){
				alert(response.status);
				$scope.hideform = true;
			});
			
			window.location.reload();
		}
		
		/* For Cancel Button */
		$scope.cancelEditEmp=function(){
			console.log("cancel Edit Emp");
			$scope.hideform = true;
		}
		
		/* Delete Employee / change Status */
		/* $scope.deleteEmp= function(id){
			console.log("Delete Emp"+id);
			$http.delete('/emp/api/delete/'+id).then(function successCallback(response){
				alert(response.data);
			},function errorCallback(response){
				alert(response.status+":"+response.data);
			});
			window.location.reload();
		} */
		
		/* Change Status by clicking on Active/Inactive Button */
		$scope.changeStatus= function(id,status){
			console.log("Delete Emp-->Id:"+id+"Status:"+status);
			$http.put('/emp/api/changeStatus/'+id).then(function successCallback(response){
				alert(response.data);
			},function errorCallback(response){
				alert(response.status+":"+response.data);
			});
			window.location.reload();
		}
	});