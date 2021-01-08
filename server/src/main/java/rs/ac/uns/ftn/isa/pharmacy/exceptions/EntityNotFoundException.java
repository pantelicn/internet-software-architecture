package rs.ac.uns.ftn.isa.pharmacy.exceptions;

import javax.persistence.PersistenceException;

public class EntityNotFoundException extends PersistenceException {
    public EntityNotFoundException(String entityName, Long id) {
        super(entityName + " with id: " + id + " not found!");
    }
}
