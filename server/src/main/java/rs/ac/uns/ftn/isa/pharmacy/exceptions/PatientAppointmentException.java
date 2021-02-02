package rs.ac.uns.ftn.isa.pharmacy.exceptions;

import javax.persistence.PersistenceException;

public class PatientAppointmentException extends PersistenceException {
    public PatientAppointmentException() {
        super("Patient has no access to that appointment.");
    }
}
