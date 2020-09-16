angular.module('MyApp').controller("regCtrl",regCtrl);

regCtrl.$inject=['$scope','EmployeeService'];

function regCtrl($scope,EmployeeService) {
	$scope.emp={};
	$scope.msg="";
	
	/* Add Employee Functionality */
	$scope.add=function(){
		/*alert("From Emp Reg Controller Add Function.....name:"+$scope.emp.name+" ,Dept:"+$scope.emp.dept+" ,Salary:"+$scope.emp.salary);*/
		var employee=$scope.emp;
		if(employee.name!=undefined && employee.dept!=undefined && employee.salary!=undefined){
			EmployeeService.addEmp($scope); //calling function from Service
			toastr.info("success ");
			$scope.emp={};
			/*window.location.reload();*/
		}
	}
	
	
}