var app = angular.module("EmployeeManagement", ['ngComboBox']);

// Controller Part
app.controller("EmployeeController", function ($scope, $http) {


    $scope.employees = [];
    $scope.employeeForm = {
        empId: -1,
        empNo: [],
        empName: ""
    };
    $scope.namebtn = 'Создать';

    // $scope.months = ['January','February','March','April','May','June','July','August','September','October','November','December'];


    // Now load the data from server
    _refreshEmployeeData();

    // HTTP POST/PUT methods for add/edit employee
    // Call: http://localhost:8080/employee
    $scope.submitEmployee = function () {

        var method = "";
        var url = "";
        var headId = "";
        if ($scope.employeeForm.empNo)
            headId = $scope.employeeForm.empNo.id;
        if ($scope.withoutHeadOrg)
            headId = "";
        if ($scope.employeeForm.empId == -1) {
            $http({
                method: "POST",
                url: '/api/organization/add?name=' + $scope.employeeForm.empName + '&headId=' + headId
            }).then(function (res) { // success
                if (res.data != "") {
                    res.data.count = 0;
                    $scope.employees.push(res.data);
                } else
                    alert("Такая организация уже есть")
            }, _error);
        } else {
            method = "PUT";
            url = '/api/organization/update';
            console.log($scope.employeeForm)
            var employeeDTO={
                id: $scope.employeeForm.empId,
                name: $scope.employeeForm.empName,
                headId: headId
            }
            $http({
                method: method,
                url: url,
                data: angular.toJson(employeeDTO),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(_success, _error);
        }
    };

    $scope.createEmployee = function () {
        _clearFormData();
    }

    // HTTP DELETE- delete employee by Id
    // Call: http://localhost:8080/employee/{empId}
    $scope.deleteEmployee = function (employee) {
        console.log("emp",employee)
        $http({
            method: 'DELETE',
            url: '/api/organization/delete?id=' + employee.id
        }).then(_success, _error);
    };

    // In case of edit
    $scope.editEmployee = function (employee) {
        $scope.namebtn = 'Изменит';
        $scope.withoutHeadOrg=false;
        $scope.employeeForm.empId = employee.id;
        $scope.employeeForm.empNo=null;
        $scope.employees.filter(function (value) {
            if(value.id == employee.headId)
                $scope.employeeForm.empNo=value;
        });

        $scope.employeeForm.empName = employee.name;
    };

    // Private Method
    // HTTP GET- get all employees collection
    // Call: http://localhost:8080/employees
    function _refreshEmployeeData() {
        $http({
            method: 'GET',
            url: '/api/organization/getall'
        }).then(
            function (res) { // success
                console.log(res.data)
                $scope.employees = res.data;
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    function _success(res) {
        _refreshEmployeeData();
        _clearFormData();
    }

    function _error(res) {
        console.log("ERROR")
        var data = res.data;
        var status = res.status;
        alert("Error: " + status + ":" + data);
    }

    // Clear the form
    function _clearFormData() {
        $scope.namebtn = 'Создать';
        $scope.employeeForm.empId = -1;
        $scope.employeeForm.empNo = "";
        $scope.employeeForm.empName = ""
    };
});


