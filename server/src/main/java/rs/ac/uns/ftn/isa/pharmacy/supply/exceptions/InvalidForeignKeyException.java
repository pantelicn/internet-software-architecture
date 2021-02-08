package rs.ac.uns.ftn.isa.pharmacy.supply.exceptions;

public class InvalidForeignKeyException extends MessageException {

    public InvalidForeignKeyException(String entityName) {
        super("Foreign key referencing for '" + entityName + "' failed");
    }
}
