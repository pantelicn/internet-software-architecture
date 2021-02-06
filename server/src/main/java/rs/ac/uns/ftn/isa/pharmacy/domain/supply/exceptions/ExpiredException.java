package rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions;

public class ExpiredException extends MessageException {
    public ExpiredException(String entityName) {
        super(entityName + " has expired.");
    }
}
