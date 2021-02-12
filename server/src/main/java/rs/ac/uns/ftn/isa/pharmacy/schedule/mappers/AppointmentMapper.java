package rs.ac.uns.ftn.isa.pharmacy.schedule.mappers;

import rs.ac.uns.ftn.isa.pharmacy.schedule.domain.Appointment;
import rs.ac.uns.ftn.isa.pharmacy.schedule.dtos.AppointmentDto;

public class AppointmentMapper {
    public static AppointmentDto objectToDto(Appointment appointment) {
        return new AppointmentDto(
                appointment.getId(),
                appointment.getTerm().getStart(),
                appointment.getTerm().getDuration(),
                appointment.getPrice(),
                appointment.getShift().getEmployee().getPerson().getFirstName(),
                appointment.getShift().getEmployee().getPerson().getLastName(),
                appointment.getType(),
                5,
                appointment.getShift().getEmployee().getId(),
                appointment.getShift().getPharmacy().getId()
        );
    }
}
