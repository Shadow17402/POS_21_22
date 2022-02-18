package at.kaindorf.examdb.api;

import at.kaindorf.examdb.database.ClassnameRepository;
import at.kaindorf.examdb.pojos.Classname;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/classname")
@CrossOrigin(origins= {"*"}, methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH})
public class ClassnameController {

    @Autowired
    ClassnameRepository classnameRepository;

    @GetMapping
    public ResponseEntity<List<Classname>> getAllClassnames() {
        List<Classname> classnames = classnameRepository.findAll().stream().sorted(Comparator.comparing(Classname::getClassname)).collect(Collectors.toList());
        return ResponseEntity.ok(classnames);
    }
}
