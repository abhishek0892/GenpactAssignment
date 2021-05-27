package com.javatpoint.controller;

import com.javatpoint.RegisterStudentRequest;
import com.javatpoint.model.Student;
import com.javatpoint.service.StudentResgisterationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

  @Autowired
  private StudentResgisterationService studentResgisterationService;


  @PostMapping("/registerStudent")
  public ResponseEntity<String> registerStudent(
      @RequestBody RegisterStudentRequest registerStudentRequest) {

    studentResgisterationService
        .registerStudent(registerStudentRequest.getStudent(), registerStudentRequest.getCourses());
    return new ResponseEntity<String>("ok", HttpStatus.OK);
  }

  @DeleteMapping("/deleteStudent")
  public ResponseEntity<String> deleteStudent(
      @RequestParam long stundentId) {

    studentResgisterationService.deleteStudent(stundentId);
    return new ResponseEntity<String>("ok", HttpStatus.OK);
  }

  @GetMapping("/getStudentsByCourseId")
  public ResponseEntity<List> getStudentsByCourseId(
      @RequestParam long courseId) {

    List<String> studentsByCourseId = studentResgisterationService.getStudentsByCourseId(courseId);
    return new ResponseEntity<List>(studentsByCourseId, HttpStatus.OK);
  }

  @GetMapping("/getNonRegisterStudentsForCourseId")
  public ResponseEntity<List> getNonRegisterStudentsForCourseId(
      @RequestParam long courseId) {

    List<String> nonRegisterStudentsForCourseId = studentResgisterationService
        .getNonRegisterStudentsForCourseId(courseId);
    return new ResponseEntity<List>(nonRegisterStudentsForCourseId, HttpStatus.OK);
  }


}
