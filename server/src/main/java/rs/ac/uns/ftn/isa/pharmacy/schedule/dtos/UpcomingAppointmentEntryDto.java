package rs.ac.uns.ftn.isa.pharmacy.schedule.dtos;

import rs.ac.uns.ftn.isa.pharmacy.schedule.domain.Appointment;

import java.time.LocalDateTime;

public class UpcomingAppointmentEntryDto {
    private long appointmentId;
    private long patientId;
    private String patientFirstName;
    private String patientLastName;
    private LocalDateTime start;
    private LocalDateTime end;
    private String price;
    private long pharmacyId;
    private String pharmacyName;

    public UpcomingAppointmentEntryDto(Appointment appointment) {
        this.appointmentId = appointment.getId();
        this.start = appointment.getTerm().getStart();
        this.end = appointment.getTerm().getEnd();
        this.pharmacyId = appointment.getShift().getPharmacy().getId();
        this.pharmacyName = appointment.getShift().getPharmacy().getName();
        this.price = appointment.getPrice().getAmount() + " " + appointment.getPrice().getCurrency();
        if(appointment.getPatient() != null){
            this.patientId = appointment.getPatient().getId();
            this.patientFirstName = appointment.getPatient().getPerson().getFirstName();
            this.patientLastName = appointment.getPatient().getPerson().getLastName();
        }
    }

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

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
