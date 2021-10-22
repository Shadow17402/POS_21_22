package at.kaindorf.jpa_intro.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentPK implements Serializable {
    private Long catNo;
    private String className;
}
