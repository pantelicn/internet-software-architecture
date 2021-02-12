package rs.ac.uns.ftn.isa.pharmacy.schedule.exceptions;

import javax.persistence.PersistenceException;

public class PenaltiesException extends PersistenceException {
    public PenaltiesException(String message) {
        super(message);
    }
}
