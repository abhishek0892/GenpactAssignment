package com.javatpoint.service;


import com.javatpoint.model.Course;
import com.javatpoint.model.Student;
import com.javatpoint.model.StudentCourse;
import com.javatpoint.repository.CourseRepository;
import com.javatpoint.repository.StudentCourseRepository;
import com.javatpoint.repository.StudentRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentResgisterationService {

  @Autowired
  private CourseRepository courseRepository;

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private StudentCourseRepository studentCourseRepository;


  public void registerStudent(Student student, List<Course> courseList) {

    List<StudentCourse> studentCourses = new ArrayList<>();
    for (Course course : courseList) {
      StudentCourse studentCourse = new StudentCourse();
      studentCourse.setStudentId(student.getId());
      studentCourse.setCourseId(course.getId());
      studentCourses.add(studentCourse);
    }

    Student studentById = studentRepository.findOneById(student.getId());
    if (studentById == null) {
      studentRepository.save(student);
      courseRepository.saveAll(courseList);
      studentCourseRepository.saveAll(studentCourses);
    } else {
      courseRepository.saveAll(courseList);
      studentCourseRepository.saveAll(studentCourses);
    }

  }

  public List<String> getStudentsByCourseId(long courseId) {

    Course course = courseRepository.findById(courseId);
    List<StudentCourse> studentCourses = new ArrayList<>();
    if (course != null) {
      studentCourses = studentCourseRepository.findAllByCourseId(courseId);
    }
    Set<Long> studentIds = studentCourses.stream().map(e -> e.getStudentId())
        .collect(Collectors.toSet());
    List<Student> students = studentRepository.findAllById(studentIds);

    if (students != null) {
      List<String> names = students.stream().map(e -> e.getName()).collect(Collectors.toList());
      Collections.sort(names);
      return names;
    }

    return null;
  }

  @Transactional
  public void deleteStudent(long studentId){
    studentRepository.deleteById(studentId);
    studentCourseRepository.deleteByStudentId(studentId);
  }

  //@Query("select sco from StudentCourse sco where sco.studentId not in (SELECT sc.studentId FROM StudentCourse sc WHERE sc.courseId = :courseId)")
  //this service method will get list of non register students wich are not regsitered for courseId
  public List<String> getNonRegisterStudentsForCourseId(long courseId) {

    Course course = courseRepository.findById(courseId);

    //@Query("select sco from StudentCourse sco where sco.studentId not in (SELECT sc.studentId FROM StudentCourse sc WHERE sc.courseId = :courseId)")
    List<StudentCourse> studentCourses = studentCourseRepository.findAllByCourseIdNot(courseId);

    Set<Long> studentIds = studentCourses.stream().map(e -> e.getStudentId())
        .collect(Collectors.toSet());
    List<Student> students = studentRepository.findAllById(studentIds);

    if (students != null) {
      List<String> names = students.stream().map(e -> e.getName()).collect(Collectors.toList());
      Collections.sort(names);
      return names;
    }

    return null;
  }
}
