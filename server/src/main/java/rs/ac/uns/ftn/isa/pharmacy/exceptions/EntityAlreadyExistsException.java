package rs.ac.uns.ftn.isa.pharmacy.exceptions;

import javax.persistence.PersistenceException;

public class EntityAlreadyExistsException extends PersistenceException {
    public EntityAlreadyExistsException(String entityName, Long entityId) {
        super(entityName + " with id: " + entityId + " already exists!");
    }

    public EntityAlreadyExistsException(String entityName) {
        super(entityName + " already exists.");
    }
}
