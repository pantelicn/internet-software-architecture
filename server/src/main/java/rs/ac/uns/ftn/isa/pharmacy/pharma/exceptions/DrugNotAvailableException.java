package rs.ac.uns.ftn.isa.pharmacy.pharma.exceptions;

import javax.persistence.PersistenceException;

public class DrugNotAvailableException extends PersistenceException {
    public DrugNotAvailableException(){
        super("Required drug is not available at this moment.");
    }
}
