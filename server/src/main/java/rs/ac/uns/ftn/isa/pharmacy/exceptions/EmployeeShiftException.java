package rs.ac.uns.ftn.isa.pharmacy.exceptions;

import rs.ac.uns.ftn.isa.pharmacy.domain.users.employee.Employee;

import javax.persistence.PersistenceException;

public class EmployeeShiftException extends PersistenceException {
    public EmployeeShiftException(Employee employee) {
        super(String.format("Employee %s %s doesn't have a shift created in given pharmacy.",
                employee.getPerson().getFirstName(),
                employee.getPerson().getLastName()));
    }
}
