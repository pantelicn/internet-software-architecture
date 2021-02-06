package rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions;

public class EntityExistsException extends MessageException {

    public EntityExistsException(String entityName) {
        super("Entity '" + entityName + "' already exists and cannot be created.");
    }
}
