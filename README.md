# Time Registration System


## Documentation of API 
This project consists of one big API (Time Registration System) that uses another smaller API ([AbisEmployeeSystem](https://github.com/janaheit/AbisEmployeeSystem)). Since the front end only accesses the TRS API, which then internally accesses the AES API, only the urls for this one will be documented here.

### Time Registration System (http://localhost:8888/api

#### Activity controller
- POST /activity/add -> adds activity through a request body (activity dto)
- POST /activity/edit -> edits existing activity through request body (activity dto)
- POST /activity/dayactivities/{id} -> returns the day activities for an employee, takes in a request body for the date 
- GET /activity/person/{id} -> returns all activities of an employee
- GET /activity/{id} -> returns an activity by activity id 
- DELETE /activity/{id} -> deletes activity by activity id
- GET /activity -> returns all activities 

#### Project controller
-  GET /projetc/ongoing -> returns all projects that are currently going on 
-  GET /project -> returns all projects 
-  POST /project/add -> add project with requast body(project dto)

#### Invoice controller 
- GET /invoice/calculate/{id} -> calculates the invoices based on project id 
- GET /invoice/{id}-> get the invoice based on project id
- GET /invoice/finalise/{id}-> Finalize an invoice based on its id, means closed is set to true 

#### Working time controller
- GET /workingtime/start/{id} -> starts the clock for a working time for consultants
- GET /workingtime/end/{id} -> ends the clock for a working time for consultants 
- GET /workingtime/{id} -> returns the working times today of a consultant
- GET /workingtime/open/{id} -> returns the open working time today of a consultant
- GET /workingtime/salaries/{year}/{month} -> get salaries for every consultant of a specific month (and year) 

#### Analyse controller
- POST /analyze -> analyes depending on input in request body AnalyzeForm 

#### Employee controller
- POST /employees/login -> logs an employee in with a login dto (abbreviation and password)
- GET /employees -> gets all employees

#### Category controller 
- GET /category -> returns all categories 

#### Company controller
- GET /company -> returns all companies  


