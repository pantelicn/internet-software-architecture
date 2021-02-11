package rs.ac.uns.ftn.isa.pharmacy.schedule.exceptions;

import javax.persistence.PersistenceException;

public class EmployeeShiftException extends PersistenceException {
    public EmployeeShiftException() {
        super("Employee doesn't have a shift created in given pharmacy.");
    }
}
