package rs.ac.uns.ftn.isa.pharmacy.supply.exceptions;

public class EntityNotFoundException extends MessageException {
    public EntityNotFoundException(String entityName) {
        super(entityName + " not found.");
    }
}
