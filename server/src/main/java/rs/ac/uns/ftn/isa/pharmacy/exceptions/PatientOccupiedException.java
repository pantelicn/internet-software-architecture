package rs.ac.uns.ftn.isa.pharmacy.exceptions;

import rs.ac.uns.ftn.isa.pharmacy.domain.users.user.Patient;

import javax.persistence.PersistenceException;

public class PatientOccupiedException extends PersistenceException {

    public PatientOccupiedException(Patient patient){
        super(String.format("Appointment for a patient %s %s can't be scheduled at a given point of time.",
                patient.getPerson().getFirstName(),
                patient.getPerson().getLastName()));
    }
}
