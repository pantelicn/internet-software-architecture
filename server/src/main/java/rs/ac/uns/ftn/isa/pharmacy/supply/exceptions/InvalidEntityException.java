package rs.ac.uns.ftn.isa.pharmacy.supply.exceptions;

public class InvalidEntityException extends MessageException {
    public InvalidEntityException(String entityName) {
        super(entityName + " not formed correctly.");
    }
}
