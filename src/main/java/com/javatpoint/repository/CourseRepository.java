package com.javatpoint.repository;

import com.javatpoint.model.Course;
import com.javatpoint.model.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

  Course findById(long id);

  List<Course> findByName(String name);
}
