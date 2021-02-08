package rs.ac.uns.ftn.isa.pharmacy.schedule.dtos;

import rs.ac.uns.ftn.isa.pharmacy.schedule.domain.Appointment;

import java.time.LocalDateTime;

public class AppointmentHistoryEntryDto {
    private long appointmentId;
    private String doctorsFirstName;
    private String doctorsLastName;
    private String price;
    private LocalDateTime start;
    private LocalDateTime end;
    private String pharmacyName;

    public AppointmentHistoryEntryDto(Appointment appointment){
        this.appointmentId = appointment.getId();
        this.doctorsFirstName = appointment.getShift().getEmployee().getPerson().getFirstName();
        this.doctorsLastName = appointment.getShift().getEmployee().getPerson().getLastName();
        this.price = appointment.getPrice().getAmount() + " " + appointment.getPrice().getCurrency();
        this.start = appointment.getTerm().getStart();
        this.end = appointment.getTerm().getEnd();
        this.pharmacyName = appointment.getShift().getPharmacy().getName();
    }

    public long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDoctorsFirstName() {
        return doctorsFirstName;
    }

    public void setDoctorsFirstName(String doctorsFirstName) {
        this.doctorsFirstName = doctorsFirstName;
    }

    public String getDoctorsLastName() {
        return doctorsLastName;
    }

    public void setDoctorsLastName(String doctorsLastName) {
        this.doctorsLastName = doctorsLastName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }
}
