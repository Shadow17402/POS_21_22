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
        @NamedQuery(name = "Country.countAll", query = "SELECT COUNT(c) FROM Country c"),
        @NamedQuery(name = "Country.findByName", query = "SELECT c FROM Country c WHERE UPPER(c.country_name) = UPPER(:name) "),
        @NamedQuery(name= "Country.findAll", query = "SELECT c FROM Country c"),
})
public class Country implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    @EqualsAndHashCode.Exclude
    private Long country_id;

    @NonNull
    @Column(length = 50, name = "name")
    private String country_name;

    @NonNull
    @Column(length = 10, name = "code")
    private String country_code;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "country")
    @NonNull
    @EqualsAndHashCode.Exclude
    private List<Address> addresses = new ArrayList<>();

}
