package rs.ac.uns.ftn.isa.pharmacy.schedule.mappers;

import rs.ac.uns.ftn.isa.pharmacy.schedule.domain.Appointment;
import rs.ac.uns.ftn.isa.pharmacy.schedule.dtos.FreeAppointmentTermDto;

public class AppointmentTermMapper {
    public static FreeAppointmentTermDto objectToDto(Appointment appointment){
        return new FreeAppointmentTermDto(
                appointment.getTerm().getStart(),
                appointment.getTerm().getDuration(),
                appointment.getId());
    }
}
