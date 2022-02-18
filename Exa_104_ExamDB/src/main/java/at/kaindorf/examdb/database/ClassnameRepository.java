package at.kaindorf.examdb.database;

import at.kaindorf.examdb.pojos.Classname;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassnameRepository extends JpaRepository<Classname, Long> {
}