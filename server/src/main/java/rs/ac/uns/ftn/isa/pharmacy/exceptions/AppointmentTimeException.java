package rs.ac.uns.ftn.isa.pharmacy.exceptions;

import javax.persistence.PersistenceException;

public class AppointmentTimeException extends PersistenceException {
    public AppointmentTimeException() {
        super("Appointment not 24 hours in the future.");
    }
}
