package rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions;

public class EntityNotFoundException extends MessageException {
    public EntityNotFoundException(String entityName) {
        super(entityName + " not found.");
    }
}
