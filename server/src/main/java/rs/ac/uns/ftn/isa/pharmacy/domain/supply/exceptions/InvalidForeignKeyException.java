package rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions;

import java.util.function.Supplier;

public class InvalidForeignKeyException extends MessageException {

    public InvalidForeignKeyException(String entityName) {
        super("Foreign key referencing for '" + entityName + "' failed");
    }
}
