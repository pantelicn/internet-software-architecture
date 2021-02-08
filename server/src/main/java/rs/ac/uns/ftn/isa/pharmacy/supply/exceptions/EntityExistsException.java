package rs.ac.uns.ftn.isa.pharmacy.supply.exceptions;

public class EntityExistsException extends MessageException {

    public EntityExistsException(String entityName) {
        super("Entity '" + entityName + "' already exists and cannot be created.");
    }
}
