package rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions;

public class InvalidEntityException extends MessageException {
    public InvalidEntityException(String entityName) {
        super(entityName + " not formed correctly.");
    }
}
