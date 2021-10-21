package at.kaindof.jpa_kundendaten.beans;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "Address.countAll", query = "SELECT COUNT(address) FROM Address address")
})
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Exclude
    private Long address_id;

    @NonNull
    @Column(length = 100)
    private String street_name;
    @NonNull
    @Column(nullable = true)
    private int street_number;
    @NonNull
    @Column(length = 50)
    private String postal_code;
    @NonNull
    @Column(length = 100)
    private String city;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @NonNull
    @JoinColumn(name = "country")
    private Country country;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "address")
    @NonNull
    @EqualsAndHashCode.Exclude
    private List<Customer> customers = new ArrayList<>();

}
