package at.kaindof.jpa_kundendaten.beans;

import at.kaindof.jpa_kundendaten.json.JSONDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@JsonDeserialize(using = JSONDeserializer.class)
@NamedQueries({
        @NamedQuery(name = "Customer.countAll", query = "SELECT COUNT(c) FROM Customer c"),
        @NamedQuery(name = "Customer.findFromCountry", query = "SELECT c FROM Customer c WHERE c.address.country.country_code = (:name) OR c.address.country.country_name = (:name)"),
        @NamedQuery(name = "Customer.findYears", query = "SELECT DISTINCT EXTRACT(YEAR FROM c.since) FROM Customer c ORDER BY EXTRACT(YEAR FROM c.since) ASC")
})
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Exclude
    private Long customer_id;

    @NonNull
    @Column(length = 100, nullable = false)
    private String firstname;

    @NonNull
    @Column(length = 100, nullable = false)
    private String lastname;

    @NonNull
    @Column(length = 1,nullable = false)
    private char gender;

    @NonNull
    @Column(nullable = false)
    private boolean active;

    @NonNull
    @Column(length = 255, nullable = false)
    private String email;

    @NonNull
    @Column
    private LocalDate since;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @NonNull
    @JoinColumn(name = "address")
    private Address address;
}
