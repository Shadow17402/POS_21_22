package at.kaindorf.jpa_intro.pojos;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "student")
@IdClass(StudentPK.class)
@NamedQueries({
  @NamedQuery(name = "Student.CountByClassname", query = "SELECT count(s) FROM student s WHERE s.schoolClass.schoolClasname = '5DHIF'")
})
public class Student implements Serializable {
    @Id
    @NonNull
    private String className;
    @Id
    @NonNull
    private Long catNo;

    /*@Column(name = "student_id")
    @GeneratedValue
    private Long studentId;*/
    @NonNull
    @Column(nullable = false, length = 100)
    @Basic(fetch = FetchType.LAZY)
    private String firstname;
    @NonNull
    @Column(nullable = false, length = 100)
    private String lastname;
    @NonNull
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Transient
    private String fullname;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address")
    private Address address;

    @ManyToOne()
    @JoinColumn(name = "schoolClass")
    @ToString.Exclude
    private SchoolClass schoolClass;

    public String getFullname() {
        return String.format("%s %s", lastname, firstname);
    }

}
