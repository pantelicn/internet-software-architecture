package rs.ac.uns.ftn.isa.pharmacy.schedule.dtos;

public class PredefinedAppointmentReservationDto {
    private long appointmentId;
    private long patientId;

    public long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }
}
