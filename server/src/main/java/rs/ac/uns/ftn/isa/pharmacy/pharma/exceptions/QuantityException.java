package rs.ac.uns.ftn.isa.pharmacy.pharma.exceptions;

import javax.persistence.PersistenceException;

public class QuantityException extends PersistenceException {
    public QuantityException() {
        super("Quantity has to be greater than 0.");
    }

    public QuantityException(String message) {
        super(message);
    }
}
