package at.kaindorf.springburger.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Burger implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @Size(min = 5, message = "Name must have at least 5 characters")
    private String name;
    @NotNull
    @Size(min = 2, message = "choose at least 2 ingredients")
    @ManyToMany
    private List<Ingredient> ingredients = new ArrayList<>();
    @ManyToOne
    private Order order;

}
