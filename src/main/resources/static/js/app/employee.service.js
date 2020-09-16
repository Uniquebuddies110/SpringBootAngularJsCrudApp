angular.module('MyApp').service('EmployeeService',EmployeeService);

EmployeeService.$inject=['$http'];

function EmployeeService($http){
	this.msg="";
	
	/* Add New Employee Function */
	this.addEmp= function($scope) { 
		this.employee=$scope.emp;
		$http.post('/emp/api/save', this.employee).then(function successCallback(response){
			$scope.msg="Employee is added successfully!!";
		}, function errorCallback(response){
			return response.status;
		});
	}
	
	/* Get All Employees Records */
	this.getAllEmps= function($scope){
		$http.get('/emp/api/getAll').then(function successCallback(response){
			$scope.emps=response.data;
			return response.data;
		}, function errorCallback(response){
			alert(response.status);
			return [];
		});
	}
	
	/* Get One Employee Record */
	this.getEmpById= function($scope,id){
		$http.get('/emp/api/getAll/'+id).then(function successCallback(response){
			/* alert(response.data); */
			$scope.emp=response.data;
		}, function errorCallback(response){
			$scope.msg=response.status;
		});
	}
	
	/* Update Employee Data*/
	this.updateEmp=function($scope){
		$scope.msg="";
		$http.put('/emp/api/update', $scope.emp).then(function successCallback(response){
			$scope.msg="Success :"+response.data;
			$scope.hideform = true;
		}, function errorCallback(response){
			$scope.msg=response.status;
			$scope.hideform = true;
		});
	}
	
	/* Change Status */
	this.changeStatus=function($scope,id){
		$scope.msg="";
		$http.put('/emp/api/changeStatus/'+id).then(function successCallback(response){
			$scope.msg="Success :"+response.data;
		},function errorCallback(response){
			$scope.msg=response.status+":"+response.data;
		});
	}
}