package at.kaindorf.examdb.database;

import at.kaindorf.examdb.pojos.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select s from Student s where s.className.classname = :classname")
    Page<Student> findStudentByClassName(@Param("classname") String classname, Pageable pageable);
}