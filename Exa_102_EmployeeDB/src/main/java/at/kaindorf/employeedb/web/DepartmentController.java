package at.kaindorf.employeedb.web;

import at.kaindorf.employeedb.database.DepartmentRepository;
import at.kaindorf.employeedb.database.EmployeeRepository;
import at.kaindorf.employeedb.pojos.Department;
import at.kaindorf.employeedb.pojos.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/administration")
@SessionAttributes({"department", "sortOrder"})
public class DepartmentController {

    @Autowired
    private DepartmentRepository deptRepo;
    @Autowired
    private EmployeeRepository empRepo;

    @ModelAttribute("depts")
    public List<Department> populateDepartments(){
        List<Department> departments = deptRepo.findAll();
        departments.sort(Comparator.comparing(Department::getDeptName));
        return departments;
    }

    @ModelAttribute("department")
    public Department populateDepartment(){
        return new Department();
    }

    @ModelAttribute("sortOrder")
    public boolean populateSortOrder(){
        return Boolean.FALSE;
    }

    @GetMapping
    public String showDepartments(){
        return "departmentView";
    }

    @PostMapping
    public String showEmployees(Model model, @RequestParam("deptNo") String deptNo){
        Department department = deptRepo.findDepartmentByDeptNo(deptNo);
        updateModel(model, department, null);
        model.addAttribute("department", department);
        model.addAttribute("sortOrder", Boolean.TRUE);
        return "departmentView";
    }

    @PostMapping("/sorted")
    public String sortEmployees(Model model, @SessionAttribute("department") Department department,
                                @SessionAttribute("sortOrder") Boolean sortOrder){
        Comparator comparator = Comparator.comparing(Employee::getLastname).thenComparing(Employee::getFirstname);
        if(!sortOrder){
            comparator = comparator.reversed();
        }
        sortOrder= !sortOrder;
        updateModel(model, department, comparator);
        model.addAttribute("sortOrder", sortOrder);
        return "departmentView";
    }

    @PostMapping("/{id}")
    public String removeEmployees(Model model, @SessionAttribute("department") Department department,
                                @PathVariable("id") int employeeNo) {
        Employee employee= empRepo.findById(employeeNo).get();
        empRepo.deleteById(employeeNo);
        department = deptRepo.findDepartmentByDeptNo(department.getDeptNo());
        department.getEmployees().remove(employee);
        deptRepo.flush();
        updateModel(model, department, Comparator.comparing(Employee::getLastname)
                .thenComparing(Employee::getFirstname));
        return "departmentView";
    }

    private void updateModel(Model model, Department department, Comparator comparator){
        department = deptRepo.findDepartmentByDeptNo(department.getDeptNo());
        List<Employee> employees = department.getEmployees();
        if(comparator!=null){
            employees.sort(comparator);
        }
        model.addAttribute("employees", employees);
        model.addAttribute("selDept", department.getDeptName());
    }
}