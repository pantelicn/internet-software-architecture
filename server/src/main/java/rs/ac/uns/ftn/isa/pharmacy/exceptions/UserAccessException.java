package rs.ac.uns.ftn.isa.pharmacy.exceptions;

import javax.persistence.PersistenceException;

public class UserAccessException extends PersistenceException {
    public UserAccessException() {
        super("User has no access.");
    }
}
