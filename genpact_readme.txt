
- Techstack used :
1. Spring boot
2. H2 in memeory Database
3. spring jpa repository
4. Java
5. Maven


- H2 database Tables.
1. STUDENTS (ID column is pk)
	ID  	NAME  
2. COURSES
	ID  	NAME  (ID column is pk)
3. STUDENT_COURSES (ID column is pk)
	ID  	COURSE_ID  	STUDENT_ID  



- Go to http://localhost:8080/h2console/ and use username = "sa" without quots after application starts to check tables 

- Application will run on : http://localhost:8080


-To answer this question : What if we want to record course scores? What possible changes need to be made?
  we can add sccore column in 



1. This api will add student with list of course, API: registerStudent, method = POST

http://localhost:8080/registerStudent
@Autowired
  private CourseRepository courseRepository;

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private StudentCourseRepository studentCourseRepository;
RequestBody :
{
  "student": {
    "id": 10,
    "name": "Devendra"
  },
  "courses": [
    {
      "id": 4,
      "name": "English"
    },
    {
      "id": 2,
      "name": "Hindi"
    }
    ,{
      "id": 5,
      "name": "Sanskrit"
    }
  ]
}

Response :
ok


2. This API will get the list of student which are registered with courseId (in query param), API: getStudentsByCourseId, method = GET
http://localhost:8080/getStudentsByCourseId?courseId=1
Response:
[
	"Abhishek",
	"Sandeep"
]



3. This API will get the list of student which are not registered with courseId (in query param), API: getStudentsByCourseId, method = GET
API:http://localhost:8080/getNonRegisterStudentsForCourseId?courseId=1
Response:
[
    "Devendra",
    "Sadaf"
]

4. THis api will deleted a student with stundentId, (in query param), API: deleteStudent, method = DELETE
http://localhost:8080/deleteStudent?stundentId=10
Response:
Ok




- There are three repository to handle crud operation
1. StudentCourseRepository
2. CourseRepository
3. StudentRepository


- There is one service which performe the basic bussiness logic with to serve the user cases.
1.StudentResgisterationService

**Outofscope :Validations are skipped, ServiceImpl using interface, transaction management, concurrency, logging, foreien key  etc


