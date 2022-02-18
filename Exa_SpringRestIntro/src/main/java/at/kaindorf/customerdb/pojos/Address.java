package at.kaindorf.customerdb.pojos;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor

public class Address implements Serializable{
    @Id
    @GeneratedValue
    private Long address_id;

    @Column(length = 100)
    @NonNull
    private String street_name;


    @NonNull
    private int street_number;

    @Column(length = 50)
    @NonNull
    private String postal_code;

    @Column(length = 100)
    @NonNull
    private String city;

    @JoinColumn(name = "country")
    @ManyToOne( fetch = FetchType.EAGER)
    @NonNull
    private Country country;

    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER)
    private List<Customer> customers= new ArrayList<>();

    public void addCustomer(Customer c){
        customers.add(c);
    }
}
