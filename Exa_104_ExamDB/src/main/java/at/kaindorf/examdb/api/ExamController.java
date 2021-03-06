package at.kaindorf.examdb.api;


import at.kaindorf.examdb.database.ExamRepository;
import at.kaindorf.examdb.database.StudentRepository;
import at.kaindorf.examdb.database.SubjectRepository;
import at.kaindorf.examdb.pojos.Exam;
import at.kaindorf.examdb.pojos.ExamRequest;
import at.kaindorf.examdb.pojos.Student;
import at.kaindorf.examdb.pojos.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exam")
@CrossOrigin(origins= {"*"}, methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH})
public class ExamController {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping("/getExams")
    public ResponseEntity<List<Exam>> getExams (@RequestParam("studentId") Long studentId) {
        Student student = studentRepository.findById(studentId).get();
        List<Exam> exams = examRepository.findAllByStudent(student);
        return ResponseEntity.of(Optional.of(exams));
    }

    @PostMapping("/createExam")
    public ResponseEntity<Exam> createExam (@RequestBody ExamRequest examRequest) {
        try {
            if (studentRepository.existsById(examRequest.getStudentId()) && subjectRepository.existsById(examRequest.getSubjectId())) {
                Student student = studentRepository.findById(examRequest.getStudentId()).get();
                Subject subject = subjectRepository.findById(examRequest.getSubjectId()).get();

                Long examId = examRepository.getMaxExamId() + 1;
                Exam exam = new Exam(examId, examRequest.getDateOfExam(), examRequest.getDuration(), student, subject);
                examRepository.save(exam);
                return ResponseEntity.ok(exam);
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/deleteExam")
    public ResponseEntity<Exam> deleteExam (@RequestParam("examId") Long examId) {
        if (examRepository.existsById(examId)) {
            examRepository.deleteById(examId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
