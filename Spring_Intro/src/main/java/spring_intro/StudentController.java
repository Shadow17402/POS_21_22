package spring_intro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import spring_intro.beans.Student;

@Controller
public class StudentController {

    @GetMapping("/stu")
    public Student getStudent(){return new Student("Bart","Simpson",12);}

}
