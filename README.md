# Employee-management-system
RESTFUL API System for managing database of employees
employee has following entities: employee and department

All application configuration present in application.properties file.
Please modify according to your system configuration

Some sample api to run the project
+ http://localhost:8096/rest/

Employee
+ http://localhost:8096/rest/employee/			RequestMethod=GET
+ http://localhost:8096/rest/employee/{id}		RequestMethod=GET
+ http://localhost:8096/rest/employee/{id} 		RequestMethod=DELETE
+ http://localhost:8096/rest/employee/create		RequestMethod=POST
+ http://localhost:8096/rest/employee/{id}		RequestMethod=PUT
+ http://localhost:8096/rest/employee/{id}		RequestMethod=PATCH

{"firstName":"Test", "lastName":"User", "salary":"2000", "departmentId":"101", "address":"Tempe,AZ" }

Department
+ http://localhost:8096/rest/department/			RequestMethod=GET
+ http://localhost:8096/rest/department/{id}		RequestMethod=GET
+ http://localhost:8096/rest/department/{id} 		RequestMethod=DELETE
+ http://localhost:8096/rest/department/create	RequestMethod=POST
+ http://localhost:8096/rest/department/{id}		RequestMethod=PUT
+ http://localhost:8096/rest/department/{id}		RequestMethod=PATCH

{"name":"HR" }
