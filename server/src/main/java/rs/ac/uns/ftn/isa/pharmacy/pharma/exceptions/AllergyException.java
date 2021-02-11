package rs.ac.uns.ftn.isa.pharmacy.pharma.exceptions;

import javax.persistence.PersistenceException;

public class AllergyException extends PersistenceException {
    public AllergyException() {
        super("User is allergic to that drug.");
    }
}
