angular.module('MyApp').controller('viewAllCtrl',viewAllCtrl);

viewAllCtrl.$inject=['$scope','$http','$route','$timeout','EmployeeService'];

function viewAllCtrl($scope,$http, $route,$timeout,EmployeeService){
	$scope.payload={};
	$scope.emps=[];
	$scope.msg="";
	$scope.hideform = true;
	
	/*For Pagination*/
	/*$scope.curPage = 1; 
	$scope.itemsPerPage = 3; 
	$scope.maxSize = 5;*/
	

	/* For Getting Employee Record to display Table */
	this.getAllEmps=function(){
		EmployeeService.getAllEmps($scope);
	}
	this.getAllEmps();
	
	/* for Pagination */
	/* $scope.numOfPages = function () { 
		    return Math.ceil($scope.emps.length / $scope.itemsPerPage); 
		      
	}; 
	
	$scope.$watch('curPage + numPerPage', function() { 
	    var begin = (($scope.curPage - 1) * $scope.itemsPerPage); 
	    var end = begin + $scope.itemsPerPage; 
	      
	    $scope.filteredItems = $scope.emps.slice(begin, end); 
	  }); */
	
	/* Fetching data for Edit Functionality */
	$scope.edit= function(id){
		$scope.hideform = false;
		$scope.emp={};
		EmployeeService.getEmpById($scope,id);
	}
	/* For Update Employee Data*/
	$scope.editEmp= function(){
		/*EmployeeService.updateEmp($scope);*/
		$http.put('/emp/api/update', $scope.emp).then(function successCallback(response){
			var respData=response.data;
			
			if(respData.status=='OK'){ // responseStatus=OK
				toastr.info(respData.message);
				console.log("Successfully fetch");
			}else if(respData.status=='INTERNAL_SERVER_ERROR'){ // responseStatus=INTERNAL_SERVER_ERROR
				toastr.error(respData.message);
				console.log("NOT FOUND");
			}
			
			$scope.hideform = true;
		}, function errorCallback(response){
			$scope.msg=response.data.status;
			$scope.hideform = true;
		});

		$timeout(function() {
			/*window.location.reload();*/
			$route.reload();
		}, 1000);
		
	}
	
	/* For Cancel Button */
	$scope.cancelEditEmp=function(){
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
		/*EmployeeService.changeStatus($scope,id);*/
		$http.put('/emp/api/changeStatus/'+id).then(function successCallback(response){
			/*$scope.msg=response.data.message;*/
			var respData=response.data;
			
			if(respData.status=='OK'){ // responseStatus=OK
				toastr.info(respData.message);
				console.log("Successfully");
			}else if(respData.status=='INTERNAL_SERVER_ERROR'){ // responseStatus=INTERNAL_SERVER_ERROR
				toastr.error(respData.message);
				console.log("NOT FOUND");
			}
		},function errorCallback(response){
			$scope.msg=response.status+":"+response.data;
		});
		console.log($scope.msg);
		$timeout(function() {
			/*window.location.reload();*/
			$route.reload();
		}, 1000);
	}
}