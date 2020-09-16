angular.module('MyApp').controller('viewAllCtrl',viewAllCtrl);

viewAllCtrl.$inject=['$scope','EmployeeService'];

function viewAllCtrl($scope, EmployeeService){
	
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
	
	/* For Edit Functionality */
	$scope.edit= function(id){
		$scope.hideform = false;
		$scope.emp={};
		EmployeeService.getEmpById($scope,id);
	}
	
	$scope.editEmp= function(){
		EmployeeService.updateEmp($scope);
		toastr.info('success : '+$scope.msg);
		window.location.reload();
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
		EmployeeService.changeStatus($scope,id);
		console.log($scope.msg);
		window.location.reload();
	}
}