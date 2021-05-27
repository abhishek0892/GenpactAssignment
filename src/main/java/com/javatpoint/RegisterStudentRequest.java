package com.javatpoint;

import com.javatpoint.model.Course;
import com.javatpoint.model.Student;
import java.util.List;

public class RegisterStudentRequest {

  private Student      student;
  private List<Course> courses;


  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public List<Course> getCourses() {
    return courses;
  }

  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }
}
