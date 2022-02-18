package at.kaindorf.customerdb.pojos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@RequiredArgsConstructor
public class Country implements Serializable{
    @Id
    @GeneratedValue
    private Long country_id;

    @Column(length = 50, name = "name")
    @NonNull
    private String country_name;


    @Column(length = 10, name = "code")
    @NonNull
    private String country_code;

    @OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private Set<Address> addressList= new HashSet<>();

    public void addAddress(Address a){
        addressList.add(a);
    }
}
