package rs.ac.uns.ftn.isa.pharmacy.users.user.domain;

import rs.ac.uns.ftn.isa.pharmacy.users.employee.domain.Employee;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EmployeeRating extends Rating {
    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
