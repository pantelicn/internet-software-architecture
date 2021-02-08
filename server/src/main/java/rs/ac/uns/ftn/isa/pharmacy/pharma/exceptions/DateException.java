package rs.ac.uns.ftn.isa.pharmacy.pharma.exceptions;

import javax.persistence.PersistenceException;

public class DateException extends PersistenceException {
    public DateException() {
        super("Date cannot be set in the past.");
    }

    public DateException(String message) {
        super(message);
    }
}
