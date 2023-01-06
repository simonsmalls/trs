# Time Registration System


## Documentation of API 
This project consists of one big API (Time Registration System) that uses another smaller API ([AbisEmployeeSystem](https://github.com/janaheit/AbisEmployeeSystem)). The URLs for both APIs will be documented here. 

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
- TBD

#### Invoice controller 
- TBD

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


### Abis Employee System (http://localhost:8080/)
- POST employees/login -> Takes a request body (login model with abbreviation and password) and returns the Employee if they exist
- GET employees -> Returns a list of all employees 
- GET 

