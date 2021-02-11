package rs.ac.uns.ftn.isa.pharmacy.users.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.domain.Employee;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Modifying
    @Query(value = "select emp from Employee emp where emp.employeeType=1")
    List<Employee> getDermatologist();

    @Modifying
    @Query(value ="select emp from Employee emp where emp.employeeType=0")
    List<Employee> getPharmacist();

    @Query(value="select emp.id from Employee emp where emp.person.id =:personId")
    Long getEmployeeIdByPersonId(@Param("personId") Long personId);

    Employee findByPersonId(long personId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Employee> findById(Long id);
}
