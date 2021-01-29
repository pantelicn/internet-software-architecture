package rs.ac.uns.ftn.isa.pharmacy.mappers;

import rs.ac.uns.ftn.isa.pharmacy.domain.schedule.Appointment;
import rs.ac.uns.ftn.isa.pharmacy.dtos.FreeAppointmentDto;

public class AppointmentMapper {
    public static FreeAppointmentDto objectToDto(Appointment appointment) {
        return new FreeAppointmentDto(
                appointment.getId(),
                appointment.getTerm().getStart(),
                appointment.getTerm().getDuration(),
                appointment.getPrice(),
                appointment.getShift().getEmployee().getPerson().getFirstName(),
                appointment.getShift().getEmployee().getPerson().getLastName());
    }
}
