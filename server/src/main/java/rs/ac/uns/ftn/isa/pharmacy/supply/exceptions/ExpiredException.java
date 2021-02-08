package rs.ac.uns.ftn.isa.pharmacy.supply.exceptions;

public class ExpiredException extends MessageException {
    public ExpiredException(String entityName) {
        super(entityName + " has expired.");
    }
}
