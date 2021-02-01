package rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions;

public class InvalidForeignKeyException extends MessageException {

    public InvalidForeignKeyException(String entityName) {
        this.message = "Foreign key referencing for '" + entityName + "' failed";
    }
}
