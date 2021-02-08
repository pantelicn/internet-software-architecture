package rs.ac.uns.ftn.isa.pharmacy.schedule.domain;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Prescription;

import javax.persistence.*;

@Entity
@Table(name = "appointment_reports")
@SequenceGenerator(name="appointment_report_seq", initialValue=100, allocationSize=1)
public class AppointmentReport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_report_seq")
    private long id;
    private String appointmentInfo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="appointment_id")
    private Appointment appointment;
    @OneToOne(mappedBy = "appointmentReport",cascade = CascadeType.ALL)
    private Prescription prescription;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAppointmentInfo() {
        return appointmentInfo;
    }

    public void setAppointmentInfo(String appointmentInfo) {
        this.appointmentInfo = appointmentInfo;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }
}
