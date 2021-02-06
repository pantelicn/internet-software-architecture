package rs.ac.uns.ftn.isa.pharmacy.repository.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.domain.users.employee.Employee;

import java.util.List;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Modifying
    @Query(value = "select emp from Employee emp where emp.employeeType=1")
    List<Employee> getDermatologist();

    @Modifying
    @Query(value ="select emp from Employee emp where emp.employeeType=0")
    List<Employee> getPharmacist();

    Employee findByPersonId(long personId);
}
