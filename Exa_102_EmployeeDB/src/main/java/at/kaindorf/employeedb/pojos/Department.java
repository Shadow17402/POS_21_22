package at.kaindorf.employeedb.pojos;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "departments")

public class Department implements Serializable {
    @Id
    @Column(name = "dept_no", length = 5)
    @JsonAlias("number")
    private String deptNo;

    @Column(name = "dept_name", length = 40, nullable = false)
    @JsonAlias("name")
    private String deptName;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "emp_no")
    private Employee deptManager;

    public void addEmployee(Employee emp){
        if(!employees.contains(emp)) {
            employees.add(emp);
        }
    }
}
