package rs.ac.uns.ftn.isa.pharmacy.exceptions;

import rs.ac.uns.ftn.isa.pharmacy.domain.users.employee.Employee;
import javax.persistence.PersistenceException;

public class EmployeeOccupiedException extends PersistenceException {
    public EmployeeOccupiedException(Employee employee){
        super(String.format("Appointment for a employee %s %s can't be scheduled at a given point of time.",
                employee.getPerson().getFirstName(),
                employee.getPerson().getLastName()));
    }
}
