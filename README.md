# College Application

## Setup 

**Run Project** 

*Run the project*

./gradlew run

**Pre-requisites** 

1. Install MySQL Server
2. Setup username and password
3. Create a Database named - "college" 
4. Change the username and password in src/main/resources/application.yaml

## Modules 

1. Students Management
2. Students Attendance Management
3. Grades Management
   
## Endpoints 

### Students Management endpoints

#### 1. Add a student record 
Endpoint - ```/students/``` 

HTTP Method - `POST`


#### 2. Update a student record
Endpoint - ```/students/{id}``` 

HTTP Method - `PATCH`


#### 3. Get a student record
Endpoint - ```/students/{id}``` 

HTTP Method - `GET`

#### 4. Find students who uses college bus 

Endpoint - ```/students/useCollegeBus``` 

HTTP Method - `GET`

#### 5. Filter students by Department 

Endpoint - ```/students/find/{dept}``` 

HTTP Method - `GET`

#### 6. Filter students by Department And Year

Endpoint - ```/students/find/{dept}/{year}```

HTTP Method - `GET`


#### 7. Delete a Student

Endpoint - ```/students/{id}``` 

HTTP Method - `DELETE`

### Students Attendance Management endpoints

#### 1. Add a absence for a student  
Endpoint - ```/students/{id}/absenceDays/{absenceDays}``` 

HTTP Method - `PUT`

#### 2. Get the attendance record for a student 

Endpoint - ```/students/{id}/attendance```

HTTP Method - `GET`




