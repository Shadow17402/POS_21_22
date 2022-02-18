package at.kaindorf.examdb.api;

import at.kaindorf.examdb.database.StudentRepository;
import at.kaindorf.examdb.pojos.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins= {"*"}, methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH})
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/getStudents")
    public ResponseEntity<Page<Student>> getStudentsFromClass (@RequestParam(value = "page", defaultValue = "0") int pageNo, @RequestParam("classname") String classname) {
        Pageable page = PageRequest.of(pageNo, 10, Sort.by("lastname").ascending());
        Page<Student> students = studentRepository.findStudentByClassName(classname, page);
        System.out.println(students.getContent());
        return ResponseEntity.of(Optional.of(students));
    }
}
