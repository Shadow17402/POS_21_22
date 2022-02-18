package at.kaindorf.employeedb.database;

import at.kaindorf.employeedb.pojos.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @Query("select d from departments d where d.deptNo = ?1")
    Department findDepartmentByDeptNo(String deptno);

}