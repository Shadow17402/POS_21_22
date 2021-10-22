package at.kaindorf.jpa_intro.pojos;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "school_class")
@NamedQueries({
    @NamedQuery(name = "SchoolClass.GetByStudentName",query = "SELECT s FROM school_class s JOIN s.students stu WHERE stu.lastname = :lastname")
})
public class SchoolClass implements Serializable {
    @Id
    @Column(length = 10, name = "school_classname")
    @NonNull
    private String schoolClasname;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "schoolClass")
    @OrderBy("lastname desc, firstname asc")
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student){
        if(!students.contains(student)) {
            student.setSchoolClass(this);
            students.add(student);
        }
    }
}
