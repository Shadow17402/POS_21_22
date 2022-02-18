package at.kaindorf.employeedb.web;

import at.kaindorf.employeedb.database.DepartmentRepository;
import at.kaindorf.employeedb.database.EmployeeRepository;
import at.kaindorf.employeedb.pojos.Department;
import at.kaindorf.employeedb.pojos.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Slf4j
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository empRepo;

    @ModelAttribute("employee")
    public Employee populateEmployee(){
        return new Employee();
    }

    @GetMapping
    public String showEmployeeForm(){
        return "employeeView";
    }

    @PostMapping
    public String addEmployee(Model model, @Valid @ModelAttribute("employee") Employee employee, Errors errors,
                              @SessionAttribute("department")Department department){
        if(errors.hasErrors()){
            model.addAttribute("department",department);
            log.info(errors.getObjectName() + " " + errors.getAllErrors().get(0));
            return "employeeView";
        }
        log.info("new Employee " + employee);
        log.info("department " + department);
        department.addEmployee(employee);
        employee.setDepartment(department);
        empRepo.save(employee);
        return "forward:/administration/sorted";
    }
}
