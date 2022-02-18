package at.kaindorf.employeedb.pojos;

import at.kaindorf.employeedb.util.LocalDateDeserializer;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "employees")
public class Employee {
    @Id
    @Column(name = "emp_no")
    @JsonAlias("emp_no")
    @NotNull(message = "employee-number is required")
    @Range(min = 1000, max = 500000, message = "Must be between 1000 and 500000")
    private Integer employeeNo;

    @NotBlank(message = "First name is required")
    @Column(name = "first_name", length = 14, nullable = false)
    private String firstname;

    @NotBlank(message = "Last name is required")
    @Column(name = "last_name", length = 16, nullable = false)
    private String lastname;

    @NotNull(message = "Gender is required")
    @Column(nullable = false, length = 5)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull(message = "birth date is required")
    @Column(name = "birth_date", nullable = false)
    @JsonAlias("birthDate")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "dept_no")
    private Department department;
}
