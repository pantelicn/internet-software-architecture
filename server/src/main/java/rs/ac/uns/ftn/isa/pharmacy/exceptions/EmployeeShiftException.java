package rs.ac.uns.ftn.isa.pharmacy.exceptions;

import rs.ac.uns.ftn.isa.pharmacy.domain.users.employee.Employee;

import javax.persistence.PersistenceException;

public class EmployeeShiftException extends PersistenceException {
    public EmployeeShiftException() {
        super("Employee doesn't have a shift created in given pharmacy.");
    }
}
