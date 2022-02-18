package at.kaindorf.examdb.database;

import at.kaindorf.examdb.pojos.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query("SELECT s FROM Subject  s ORDER BY s.longname")
    List<Subject> getAllSubjects();

}