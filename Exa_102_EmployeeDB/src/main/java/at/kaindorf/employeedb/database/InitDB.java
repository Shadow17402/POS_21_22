package at.kaindorf.employeedb.database;

import at.kaindorf.employeedb.pojos.Department;
import at.kaindorf.employeedb.pojos.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class InitDB {

    @Autowired
    private DepartmentRepository deptRepo;
    @Autowired
    private EmployeeRepository empRepo;

    @PostConstruct
    public void initDatabaseFromJson(){
        Path JSONpath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "employeeData.json");
        ObjectMapper om = new ObjectMapper();
        List<Department> departments = null;
        try {
            departments = om.readValue(JSONpath.toFile(), new TypeReference<List<Department>>() {});
            if(departments!=null){
                for (Department department: departments){
                    empRepo.saveAllAndFlush(department.getEmployees());
                    empRepo.save(department.getDeptManager());
                    deptRepo.save(department);
                    for (Employee employee: department.getEmployees()){
                        employee.setDepartment(department);
                    }
                    empRepo.saveAllAndFlush(department.getEmployees());
                }

            }else{
                log.error("Json read failed");
            }
        } catch (IOException e) {
            log.error(e.toString());
        }
    }
}
