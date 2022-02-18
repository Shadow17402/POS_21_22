package at.kaindorf.customerdb.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Customer implements Serializable {
    @Id
    @GeneratedValue
    private Long customer_id;

    @Column(length = 100)
    @NonNull
    private String firstname;

    @Column(length = 100)
    @NonNull
    private String lastname;

    @NonNull
    @Column(length = 1, nullable = false)
    private Character gender;

    @NonNull
    @Column(nullable = false)
    private Boolean active;

    @Column(length = 255)
    @NonNull
    private String email;

    @NonNull
    private LocalDate since;

    @JoinColumn(name = "address")
    @ManyToOne( fetch = FetchType.EAGER)
    @ToString.Exclude
    @NonNull
    @JsonIgnore
    private Address address;
}
