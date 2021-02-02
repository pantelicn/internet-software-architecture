package rs.ac.uns.ftn.isa.pharmacy.exceptions;

import rs.ac.uns.ftn.isa.pharmacy.domain.users.employee.Employee;
import javax.persistence.PersistenceException;

public class EmployeeOccupiedException extends PersistenceException {
    public EmployeeOccupiedException(){
        super("Appointment for a employee can't be scheduled at a given point of time.");
    }
}
