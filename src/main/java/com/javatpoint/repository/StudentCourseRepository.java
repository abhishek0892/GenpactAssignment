package com.javatpoint.repository;

import com.javatpoint.model.Student;
import com.javatpoint.model.StudentCourse;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {

  List<StudentCourse> findById(long id);
  List<StudentCourse> findAllByCourseId(long id);

  @Query("select sco from StudentCourse sco where sco.studentId not in (SELECT sc.studentId FROM StudentCourse sc WHERE sc.courseId = :courseId)")
  List<StudentCourse> findAllByCourseIdNot(@Param("courseId") long courseId);

  public void deleteByStudentId(long id);

}
