package at.kaindorf.examdb.api;

import at.kaindorf.examdb.database.ClassnameRepository;
import at.kaindorf.examdb.pojos.Classname;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/classname")
public class ClassnameController {

    @Autowired
    ClassnameRepository classnameRepository;

    @GetMapping
    public ResponseEntity<List<Classname>> getAllClassnames() {
        List<Classname> classnames = classnameRepository.findAll();
        return ResponseEntity.ok(classnames);
    }
}
