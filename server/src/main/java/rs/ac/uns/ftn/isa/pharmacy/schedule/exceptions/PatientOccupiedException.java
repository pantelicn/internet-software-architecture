package rs.ac.uns.ftn.isa.pharmacy.schedule.exceptions;

import javax.persistence.PersistenceException;

public class PatientOccupiedException extends PersistenceException {

    public PatientOccupiedException(){
        super("Appointment for a patient can't be scheduled at a given point of time.");
    }
}
