package rs.ac.uns.ftn.isa.pharmacy.schedule.exceptions;

import javax.persistence.PersistenceException;

public class NoUpcomingAppointmentsException extends PersistenceException {
    public NoUpcomingAppointmentsException(){
        super("There are no upcoming appointments for this employee.");
    }
}
