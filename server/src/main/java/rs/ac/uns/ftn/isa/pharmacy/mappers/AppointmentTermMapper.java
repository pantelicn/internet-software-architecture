package rs.ac.uns.ftn.isa.pharmacy.mappers;

import rs.ac.uns.ftn.isa.pharmacy.domain.schedule.Appointment;
import rs.ac.uns.ftn.isa.pharmacy.dtos.FreeAppointmentTermDto;

public class AppointmentTermMapper {
    public static FreeAppointmentTermDto objectToDto(Appointment appointment){
        return new FreeAppointmentTermDto(
                appointment.getTerm().getStart(),
                appointment.getTerm().getDuration(),
                appointment.getId());
    }
}
