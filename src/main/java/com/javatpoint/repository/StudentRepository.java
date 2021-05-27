package com.javatpoint.repository;

import com.javatpoint.model.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

  Student findOneById(long id);

  List<Student> findByName(String name);
}
