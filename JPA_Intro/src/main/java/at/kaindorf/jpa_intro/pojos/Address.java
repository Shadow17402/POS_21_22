package at.kaindorf.jpa_intro.pojos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "address")
@NamedQueries({
        @NamedQuery(name = "Address.GetALl", query = "SELECT a FROM address a WHERE a.street LIKE :street"),
        @NamedQuery(name = "Address.GetByClassname", query = "SELECT a FROM address a WHERE a.student.address.student.address.student.address.student.address.student.schoolClass.schoolClasname = :classname")
})
public class Address implements Serializable
{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long AddressID;
    @NonNull
    @Column(length = 100,nullable = false)
    private String city;
    @NonNull
    @Column(length = 100,nullable = false)
    private String street;
    @NonNull
    @Column(length = 100,nullable = false)
    private String number;

    @OneToOne(mappedBy = "address")
    @ToString.Exclude
    private Student student;

}
